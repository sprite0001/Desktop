package com.wooxun.geekdol.geekstore.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tools.ant.util.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.GrouponComment;
import com.wooxun.geekdol.geekstore.model.GrouponCommentReturnInfo;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;
import com.wooxun.geekdol.geekstore.model.GrouponGoodsExtendInfo;
import com.wooxun.geekdol.geekstore.model.GrouponVillage;
import com.wooxun.geekdol.geekstore.service.GrouponCommentReturnInfoService;
import com.wooxun.geekdol.geekstore.service.GrouponCommentService;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsExtendInfoService;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsService;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentReturnInfoVo;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentVo;
import com.wooxun.geekdol.geekstore.vo.GrouponGoodsVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description 团购商品管理
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月1日  下午5:49:22 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("groupongoods")
public class GrouponGoodsController extends BaseController {
	//private static final Logger LOG = LoggerFactory.getLogger(GrouponGoodsController.class);
	
	public static final String GROUPONGOODS = "geekstore/groupongoods/list";
	public static final String GROUPONGOODS_ADD = "geekstore/groupongoods/grouponGoodsAdd";
	public static final String GROUPONGOODS_EDIT = "geekstore/groupongoods/grouponGoodsEdit";
	public static final String GROUPONCOMMENT_VIEW = "geekstore/groupongoods/grouponcommentView";
	public static final String COMMENTRETURNINFO_VIEW = "geekstore/groupongoods/commentReturnInfo";
	@Autowired
	private GrouponGoodsService<GrouponGoods> grouponGoodsService;
	@Autowired
	private GrouponGoodsExtendInfoService<GrouponGoodsExtendInfo> grouponGoodsExtendInfoService;
	@Autowired
	private GrouponCommentService<GrouponComment> grouponCommentService;
	@Autowired
	private GrouponCommentReturnInfoService<GrouponCommentReturnInfo> grouponCommentReturnInfoService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title
	 * @Description 团购商品管理列表初始化
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午5:54:10
	 * @return String
	 */
	@RequiresPermissions("Groupon:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return GROUPONGOODS;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 团购商品数据查询
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午5:55:09
	 * @param cooperationStoreVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(GrouponGoodsVo grouponGoodsVo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		List<GrouponGoodsVo> grouponGoodsVoList = new ArrayList<GrouponGoodsVo>();
		int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "20" : request.getParameter("rows"));
		Long count = 0l;
		
		grouponGoodsVo.setPageFlag(true);
		grouponGoodsVo.setStartPage((page - 1) * rows);
		grouponGoodsVo.setEndPage(rows);
		// 根据登陆人查找自己发布的团购商品
		grouponGoodsVo.setSeller(getUserId());
		// 设置团购商品状态在数据字典中的类型
		grouponGoodsVo.setStatusType(ConstantStr.GROUPONGOODSSTATUS);
		// 团购商品总数查询
		count = grouponGoodsService.queryGrouponGoodsCount(grouponGoodsVo);
		// 团购列表查询
		grouponGoodsVoList = grouponGoodsService.queryGrouponGoods(grouponGoodsVo);
		
		map.put("rows", grouponGoodsVoList); 
		map.put("total", count);

		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除团购商品
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:45:27
	 * @param grouponGoods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/deleteGrouponGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteGrouponGoods(GrouponGoods grouponGoods,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购"};
		// 验证是否已经删除
		GrouponGoods original = grouponGoodsService.findById(grouponGoods.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 更新回数设置
		grouponGoods.setUpdEac(original.getUpdEac());
		// 共同属性修改
		this.editAttr(grouponGoods);
		// 删除操作
		boolean bl = grouponGoodsService.deleteGrouponGoods(grouponGoods);
		if(bl){
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.J_GROUPON_GOODS, grouponGoods.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 新增团购商品
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午6:30:57
	 * @return String
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/toAddGroupongoods", method = {RequestMethod.GET})
	public String toAddGroupongoods(){
		return GROUPONGOODS_ADD;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存团购商品
	 * @author:YK
	 * @CreateDate:2016年8月8日 下午3:26:08
	 * @param grouponGoodsVo
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/saveGrouponGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String saveGrouponGoods(GrouponGoodsVo grouponGoodsVo,@RequestParam("file") MultipartFile[] files,
			@RequestParam String extendInfo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购"};
		
		// 属性拷贝
		GrouponGoods grouponGoods = new GrouponGoods();
		BeanUtils.copyProperties(grouponGoodsVo, grouponGoods);
		// 扩展属性
		JSONArray jsonArray = JSONArray.fromObject(extendInfo);
		if(jsonArray.size()<=0){// 验证是否存在扩展属性
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100095,arr));
			return o.writeValueAsString(map);
		}
		List<GrouponGoodsExtendInfo> grouponGoodsExtendInfos = new ArrayList<GrouponGoodsExtendInfo>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			GrouponGoodsExtendInfo grouponGoodsExtendInfo = new GrouponGoodsExtendInfo();
			grouponGoodsExtendInfo.setExtendAttr(jsonObject.getString("extendAttr"));
			grouponGoodsExtendInfo.setExtendValue(jsonObject.getString("extendValue"));
			grouponGoodsExtendInfo.setExtendMark(jsonObject.getString("extendMark"));
			grouponGoodsExtendInfos.add(grouponGoodsExtendInfo);
		}
		grouponGoods.setGrouponGoodsExtendInfos(grouponGoodsExtendInfos);
		//封装团购小区关系数据
		String villageIds[] = grouponGoodsVo.getVillageId().split(",");
		// 验证团购小区是否选择
		if(StringUtils.isBlank(grouponGoodsVo.getVillageId())){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100094,arr));
			return o.writeValueAsString(map);
		}
		List<GrouponVillage> grouponVillageList = new ArrayList<GrouponVillage>();
		for(String villageId :villageIds){
			GrouponVillage grouponVillage = new GrouponVillage();
			grouponVillage.setVillageId(new Long(villageId));
			grouponVillageList.add(grouponVillage);
		}
		grouponGoods.setGrouponVillage(grouponVillageList);
		//附件上传
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				Attach attach = new Attach();
				attach.setUrl(newFileName);
				attachs.add(attach);
			}
		}
		grouponGoods.setAttachs(attachs);
		// 新增公共属性
		addAttr(grouponGoods);
		//卖方设置
		grouponGoods.setSeller(getUserId());
		// 商品新增
		boolean bl = grouponGoodsService.insertGrouponGoods(grouponGoods);
		if(bl){
			// 系统日志新增
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.J_GROUPON_GOODS, grouponGoods.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化修改页面
	 * @author:YK
	 * @CreateDate:2016年8月9日 上午9:21:16
	 * @param goodsId
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/toEditGroupongoods/{goodsId}", method = {RequestMethod.GET})
	public String toEditGroupongoods(@PathVariable Long goodsId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
		if(goodsId != null){
			// 初始化商品id、分类、编辑器内容
			GrouponGoods original = grouponGoodsService.findById(goodsId);
			map.put("id", goodsId);
			map.put("grouponClassific", original.getGrouponClassific());
			map.put("details", original.getDetails());
			// 铺货小区初始化
			List<Village> villages = new ArrayList<Village>();
			if(original!=null && original.getGrouponVillage()!=null){
				for(GrouponVillage grouponVillage:original.getGrouponVillage()){
					Village village = new Village();
					village.setVillageId(grouponVillage.getVillageId());
					village.setVillageName(grouponVillage.getVillage().getVillageName());
					villages.add(village);
				}
			}
			map.put("village", JSONArray.fromObject(villages));
			map.put("attachs", JSONArray.fromObject(original.getAttachs()));
		}
		return GROUPONGOODS_EDIT;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 查看评论
	 * @author:YK
	 * @CreateDate:2016年8月5日 上午11:31:43
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Groupon:view")
	@RequestMapping(value = "/viewGrouponComment/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String viewGrouponComment(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			GrouponGoodsVo grouponGoodsVo = grouponGoodsService.findCodeAndStar(id);
			map.put("grouponGoods", grouponGoodsVo);
			return GROUPONCOMMENT_VIEW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改数据初始化
	 * @author:YK
	 * @CreateDate:2016年8月9日 上午9:48:18
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/findGrouponGoods/{goodsId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findGrouponGoods(@PathVariable Long goodsId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		ObjectMapper o = new ObjectMapper();
		
		GrouponGoods original = grouponGoodsService.findById(goodsId);
		GrouponGoodsVo grouponGoodsVo = new GrouponGoodsVo();
		BeanUtils.copyProperties(original, grouponGoodsVo);
		grouponGoodsVo.setStartTimeStr(DateUtils.format(original.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		grouponGoodsVo.setEndTimeStr(DateUtils.format(original.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		return o.writeValueAsString(grouponGoodsVo);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 扩展属性查找
	 * @author:YK
	 * @CreateDate:2016年8月9日 上午10:41:37
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/findGrouponGoodsExtendInfo/{goodsId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findGrouponGoodsExtendInfo(@PathVariable Long goodsId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		ObjectMapper o = new ObjectMapper();
		
		List<GrouponGoodsExtendInfo> list = grouponGoodsExtendInfoService.selectByGoodsId(goodsId);
		return o.writeValueAsString(list);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 评论list
	 * @author:YK
	 * @CreateDate:2016年8月5日 上午11:40:54
	 * @param grouponCommentVo
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:view")
	@RequestMapping(value = "/findGrouponCommentList/{goodsId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findGrouponCommentList(GrouponCommentVo grouponCommentVo,@PathVariable Long goodsId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		
		grouponCommentVo.setGoodsId(goodsId);
		List<GrouponCommentVo> list = grouponCommentService.selectByGrouponGoodsId(grouponCommentVo);
		map.put("rows", list);
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 更新团购商品
	 * @author:YK
	 * @CreateDate:2016年8月9日 下午2:02:26
	 * @param grouponGoodsVo
	 * @param files
	 * @param extendInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/updateGrouponGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateGrouponGoods(GrouponGoodsVo grouponGoodsVo,@RequestParam("file") MultipartFile[] files,
			@RequestParam String extendInfo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购"};
		// 验证是否已经删除
		GrouponGoods original = grouponGoodsService.findById(grouponGoodsVo.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		
		// 属性拷贝
		GrouponGoods grouponGoods = new GrouponGoods();
		BeanUtils.copyProperties(grouponGoodsVo, grouponGoods);
		grouponGoods.setInsId(original.getInsId());
		grouponGoods.setInsYmdhms(original.getInsYmdhms());
		// 时间赋值
		grouponGoods.setStartTime(DateUtil.parseDate(grouponGoodsVo.getStartTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		grouponGoods.setEndTime(DateUtil.parseDate(grouponGoodsVo.getEndTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		// 扩展属性
		JSONObject allObject = JSONObject.fromObject(extendInfo);
		JSONArray jsonArray = allObject.getJSONArray("rows");
		if(jsonArray.size()<=0){// 验证是否存在扩展属性
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100095,arr));
			return o.writeValueAsString(map);
		}
		List<GrouponGoodsExtendInfo> grouponGoodsExtendInfos = new ArrayList<GrouponGoodsExtendInfo>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			GrouponGoodsExtendInfo grouponGoodsExtendInfo = new GrouponGoodsExtendInfo();
			grouponGoodsExtendInfo.setExtendAttr(jsonObject.getString("extendAttr"));
			grouponGoodsExtendInfo.setExtendValue(jsonObject.getString("extendValue"));
			grouponGoodsExtendInfo.setExtendMark(jsonObject.getString("extendMark"));
			grouponGoodsExtendInfos.add(grouponGoodsExtendInfo);
		}
		grouponGoods.setGrouponGoodsExtendInfos(grouponGoodsExtendInfos);
		//封装团购小区关系数据
		String villageIds[] = grouponGoodsVo.getVillageId().split(",");
		// 验证团购小区是否选择
		if(StringUtils.isBlank(grouponGoodsVo.getVillageId())){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100094,arr));
			return o.writeValueAsString(map);
		}
		List<GrouponVillage> grouponVillageList = new ArrayList<GrouponVillage>();
		for(String villageId :villageIds){
			GrouponVillage grouponVillage = new GrouponVillage();
			grouponVillage.setVillageId(new Long(villageId));
			grouponVillageList.add(grouponVillage);
		}
		grouponGoods.setGrouponVillage(grouponVillageList);
		//附件上传
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			Attach attach = new Attach();
			attach.setOrdering(i);
			if(file.getSize()<=0){
				if(i == 0 && StringUtils.isNotBlank(grouponGoodsVo.getPicOne())){
					attach.setUrl(grouponGoodsVo.getPicOne());
					attachs.add(attach);
				}else if(i == 1 && StringUtils.isNotBlank(grouponGoodsVo.getPicTow())){
					attach.setUrl(grouponGoodsVo.getPicTow());
					attachs.add(attach);
				}else if(i == 2 && StringUtils.isNotBlank(grouponGoodsVo.getPicThree())){
					attach.setUrl(grouponGoodsVo.getPicThree());
					attachs.add(attach);
				}
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				attach.setUrl(newFileName);
				attachs.add(attach);
			}
		}
		grouponGoods.setAttachs(attachs);
		// 新增公共属性
		grouponGoods.setUpdEac(original.getUpdEac());
		// 公共属性修改
		editAttr(grouponGoods);
		// 主数据更新
		boolean bl = grouponGoodsService.updateGrouponGoods(grouponGoods);
		if(bl){
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.J_GROUPON_GOODS, grouponGoods.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除团购评论及其评论的回复
	 * @author:YK
	 * @CreateDate:2016年8月5日 下午2:32:56
	 * @param grouponCommentVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/deleteGrouponComment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteGrouponComment(GrouponCommentVo grouponCommentVo, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购评论"};

		GrouponComment original = grouponCommentService.selectById(grouponCommentVo.getId());
		// 验证数据是否存在
		if(original==null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		boolean bl = false;
		//修改评论共同属性
		this.editAttrGrouponComment(original);
		// 更新主数据
		bl = grouponCommentService.deleteGrouponComment(original);
		if(bl){
			// 新增系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.J_GROUPON_GOODS_GROUPONCOMMENT, original.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 查看评论的回复
	 * @author:YK
	 * @CreateDate:2016年8月5日 下午4:11:22
	 * @param goodsId
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Groupon:view")
	@RequestMapping(value = "/toViewCommentReturninfo/{goodsId}/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toViewCommentReturninfo(@PathVariable Long goodsId,@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			GrouponCommentVo grouponCommentVo = new GrouponCommentVo();
			grouponCommentVo.setId(id);
			// 团购部分信息查询
			GrouponGoodsVo grouponGoodsVo = grouponGoodsService.findCodeAndStar(goodsId);
			// 评论部分信息查询
			grouponCommentVo = grouponCommentService.selectByParams(grouponCommentVo);
			//回复数量
			GrouponCommentReturnInfo grouponCommentReturnInfo = new GrouponCommentReturnInfo();
			grouponCommentReturnInfo.setCommentId(id);
			Long commentCount = grouponCommentReturnInfoService.getGrouponCommentReturnInfoCount(grouponCommentReturnInfo);
			// 违规数量
			grouponCommentReturnInfo.setStatus(ConstantStr.GROUP_WG);
			Long illegalCommentCount = grouponCommentReturnInfoService.getGrouponCommentReturnInfoCount(grouponCommentReturnInfo);
			map.put("grouponComment", grouponCommentVo);
			map.put("grouponGoods", grouponGoodsVo);
			map.put("commentCount", commentCount);
			map.put("illegalCommentCount", illegalCommentCount);
			return COMMENTRETURNINFO_VIEW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 查找回复列表
	 * @author:YK
	 * @CreateDate:2016年8月8日 上午10:27:27
	 * @param grouponCommentReturnInfo
	 * @param commentId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:view")
	@RequestMapping(value = "/findReturnInfo/{commentId}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findReturnInfo( GrouponCommentReturnInfoVo grouponCommentReturnInfoVo,
			@PathVariable Long commentId,HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		grouponCommentReturnInfoVo.setCommentId(commentId);
		List<GrouponCommentReturnInfoVo> grouponCommentReturnInfolist = 
				grouponCommentReturnInfoService.queryByParams(grouponCommentReturnInfoVo);
		map.put("rows", grouponCommentReturnInfolist);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);					
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除违法的评论回复
	 * @author:YK
	 * @CreateDate:2016年8月15日 下午7:07:25
	 * @param grouponGoods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Groupon:manager")
	@RequestMapping(value = "/destroyReturnInfo", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String destroyReturnInfo(GrouponCommentReturnInfo grouponCommentReturnInfo,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购评论回复"};
		// 验证是否已经删除
		GrouponCommentReturnInfo original = grouponCommentReturnInfoService.findById(grouponCommentReturnInfo.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 设置删除标志、更新回数、公共属性修改
		grouponCommentReturnInfo.setDelFlag(ConstantStr.DELETE_Y);
		grouponCommentReturnInfo.setUpdEac(original.getUpdEac());
		this.editAttrReturnInfo(grouponCommentReturnInfo);
		// 更新回复信息
		boolean bl = grouponCommentReturnInfoService.deleteGrouponCommentReturnInfo(grouponCommentReturnInfo);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.J_GROUPON_DEMAND_COMMENT_RETURN_INFO, grouponCommentReturnInfo.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	@InitBinder  
    private void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
	/**
	 * 
	 * @Title
	 * @Description 公共属性新增
	 * @author:YK
	 * @CreateDate:2016年8月8日 下午3:49:25
	 * @param _temp
	 */
	private void addAttr(GrouponGoods _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改共同属性
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:21:53
	 * @param _temp
	 */
	private void editAttr(GrouponGoods _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改评论的回复共同属性
	 * @author:YK
	 * @CreateDate:2016年8月15日 下午7:13:37
	 * @param _temp
	 */
	private void editAttrReturnInfo(GrouponCommentReturnInfo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改评论
	 * @author:YK
	 * @CreateDate:2016年8月5日 下午3:09:41
	 * @param _temp
	 */
	private void editAttrGrouponComment(GrouponComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
}
