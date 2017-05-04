package com.wooxun.geekdol.geekstore.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.wooxun.geekdol.geekstore.model.Distribution;
import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsComment;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsCommentReturnInfo;
import com.wooxun.geekdol.geekstore.model.DistributionVillage;
import com.wooxun.geekdol.geekstore.model.Goods;
import com.wooxun.geekdol.geekstore.model.GoodsClassific;
import com.wooxun.geekdol.geekstore.model.GoodsExtendInfor;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendComment;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.DistributionGoodsCommentReturnInfoService;
import com.wooxun.geekdol.geekstore.service.DistributionGoodsCommentService;
import com.wooxun.geekdol.geekstore.service.DistributionService;
import com.wooxun.geekdol.geekstore.service.GoodsClassificService;
import com.wooxun.geekdol.geekstore.service.GoodsExtendInforService;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendCommentReturnInfoService;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendCommentService;
import com.wooxun.geekdol.geekstore.service.GoodsService;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentReturnInfoVo;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentVo;
import com.wooxun.geekdol.geekstore.vo.DistributionVo;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentReturnInfoVo;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentVo;
import com.wooxun.geekdol.geekstore.vo.GoodsVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description 吉客店-商品管理
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月11日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月11日  上午9:15:55 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {
	//private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);
	public static final String GOODS = "geekstore/goods/list";
	public static final String GOODS_ADD = "geekstore/goods/goodsAdd";
	public static final String GOODS_EDIT = "geekstore/goods/goodsEdit";
	public static final String DISTRIBUTIONGOODS = "geekstore/goods/distributionGoods";
	public static final String GOODSCOMMENT = "geekstore/goods/goodsComment";
	public static final String COMMENTRETURNINFO = "geekstore/goods/commentReturnInfo";
	public static final String GOODS_SELECT = "geekstore/goods/goodsSelectList";
	private static final String MAIN = "geekstore/goods/fenleiMain";
	private static final String LIST = "geekstore/goods/fenleiList";
	
	@Autowired
	private GoodsClassificService<GoodsClassific> goodsClassificService;
	@Autowired
	private GoodsService<Goods> goodsService;
	@Autowired
	private GoodsExtendInforService<GoodsExtendInfor> goodsExtendInforService;
	@Autowired
	private DistributionService<Distribution> distributionService;
	@Autowired
	private DistributionGoodsCommentService<DistributionGoodsComment> distributionGoodsCommentService;
	@Autowired
	private DistributionGoodsCommentReturnInfoService<DistributionGoodsCommentReturnInfo> distributionGoodsCommentReturnInfoService;
	@Autowired
	private GoodsRecommendCommentService<GoodsRecommendComment> goodsRecommendCommentService;
	@Autowired
	private GoodsRecommendCommentReturnInfoService<GoodsRecommendCommentReturnInfo> goodsRecommendCommentReturnInfoService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title
	 * @Description 列表页面初始化
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:32:31
	 * @return
	 */
	@RequiresPermissions("Goods:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return GOODS;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 列表数据查找
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:32:44
	 * @param goodsVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(GoodsVo goodsVo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		Long count = 0l;
		
		goodsVo.setPageFlag(true);
		goodsVo.setStartPage((page - 1) * rows);
		goodsVo.setEndPage(rows);
		// 查找个人添加的商品
		goodsVo.setSeller(getUserId()+"");
		// 设置商品状态在数据字典中的type类型
		goodsVo.setStatusType(ConstantStr.GOODSSTATUS);
		// 商品总数查询
		count = goodsService.queryGoodsCountByParams(goodsVo);
		// 商品list列表查询
		goodsVoList = goodsService.queryGoodsByParams(goodsVo);
		
		map.put("rows", goodsVoList); 
		map.put("total", count);

		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化新增页面
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午11:37:52
	 * @return
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/toAddGoods", method = {RequestMethod.GET})
	public String toAddGoods(){
		return GOODS_ADD;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存商品
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午3:22:37
	 * @param goodsVo
	 * @param files
	 * @param extendInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/saveGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String saveGoods(GoodsVo goodsVo,@RequestParam("file") MultipartFile[] files,
			@RequestParam String extendInfo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购"};
		
		// 属性拷贝
		Goods goods = new Goods();
		BeanUtils.copyProperties(goodsVo, goods);
		// 扩展属性
		JSONArray jsonArray = JSONArray.fromObject(extendInfo);
		if(jsonArray.size()<=0){// 验证是否存在扩展属性
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100095,arr));
			return o.writeValueAsString(map);
		}
		List<GoodsExtendInfor> goodsExtendInfors = new ArrayList<GoodsExtendInfor>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			GoodsExtendInfor goodsExtendInfo = new GoodsExtendInfor();
			goodsExtendInfo.setExtendName(jsonObject.getString("extendName"));
			goodsExtendInfo.setExtendValue(jsonObject.getString("extendValue"));
			goodsExtendInfo.setExtendRemark(jsonObject.getString("extendRemark"));
			goodsExtendInfors.add(goodsExtendInfo);
		}
		goods.setGoodsExtendInfors(goodsExtendInfors);
		//附件上传
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			// 第一个附件为缩略图
			if(i==0 && file.getSize()<=0){
				goods.setThumbnail("");
			}
			if(file.getSize()<=0){
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				if(i==0 && file.getSize()>0){
					goods.setThumbnail(newFileName);
					continue;
				}
				Attach attach = new Attach();
				attach.setUrl(newFileName);
				attachs.add(attach);
			}
		}
		goods.setAttachs(attachs);
		// 新增公共属性
		addAttr(goods);
		//卖方设置
		goods.setSeller(getUserId()+"");
		// 新增商品主数据
		boolean bl = goodsService.insertGoods(goods);
		if(bl){
			// 系统日志新增
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr),
					ConstantStr.J_GOODS, goods.getId(), getUser());
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
	 * @Description 商品删除：只有未发布的可以删除
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午11:37:20
	 * @param goods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/deleteGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteGoods(Goods goods,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"商品"};
		// 验证是否已经删除
		Goods original = goodsService.findById(goods.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 已经发布的商品禁止删除
		if(ConstantStr.GOODSSTATUS_02.equals(original.getStatus())){
			arr = addMessage(arr,"已经发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 销售中的商品禁止删除
		if(ConstantStr.GOODSSTATUS_03.equals(original.getStatus())){
			arr = addMessage(arr,"销售中");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已下架产品禁止删除
		if(ConstantStr.GOODSSTATUS_04.equals(original.getStatus())){
			arr = addMessage(arr,"已下架");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已禁用产品禁止删除
		if(ConstantStr.GOODSSTATUS_05.equals(original.getStatus())){
			arr = addMessage(arr,"已禁用");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置删除标示
		goods.setDelFlag(ConstantStr.DELETE_Y);
		// 更新回数设置
		goods.setUpdEac(original.getUpdEac());
		// 共同属性设置
		this.editAttr(goods);
		// 更新商品数据
		boolean bl = goodsService.deleteGoods(goods);
		if(bl){
			// 新增系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr),
					ConstantStr.J_GOODS, goods.getId(), getUser());
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
	 * @Description 商品下架
	 * @author:YK
	 * @CreateDate:2016年8月16日 上午11:47:55
	 * @param goods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/offGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String offGoods(Goods goods,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"商品"};
		// 验证是否已经删除
		Goods original = goodsService.findById(goods.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 未发布的商品禁止下架
		if(ConstantStr.GOODSSTATUS_01.equals(original.getStatus())){
			arr = addMessage(arr,"未发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已经发布的商品禁止下架
		if(ConstantStr.GOODSSTATUS_02.equals(original.getStatus())){
			arr = addMessage(arr,"已经发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已下架产品禁止下架
		if(ConstantStr.GOODSSTATUS_04.equals(original.getStatus())){
			arr = addMessage(arr,"已下架");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已禁用产品禁止下架
		if(ConstantStr.GOODSSTATUS_05.equals(original.getStatus())){
			arr = addMessage(arr,"已禁用");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置下架状态
		goods.setStatus(ConstantStr.GOODSSTATUS_04);
		// 更新回数设置
		goods.setUpdEac(original.getUpdEac());
		// 共同属性设置
		this.editAttr(goods);
		// 更新产品数据
		boolean bl = goodsService.offGoods(goods);
		if(bl){
			// 新增系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100102,arr),
					ConstantStr.J_GOODS, goods.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100102,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100103,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 发布产品：只有未发布的产品才能发布
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午3:29:28
	 * @param goods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/publishGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String publishGoods(Goods goods, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"商品"};
		// 验证是否已经删除
		Goods original = goodsService.findById(goods.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 已经发布的产品禁止该操作
		if(ConstantStr.GOODSSTATUS_02.equals(original.getStatus())){
			arr = addMessage(arr,"已经发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 处于销售中的产品禁止该操作
		if(ConstantStr.GOODSSTATUS_03.equals(original.getStatus())){
			arr = addMessage(arr,"销售中");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已下架产品禁止再次发布
		if(ConstantStr.GOODSSTATUS_04.equals(original.getStatus())){
			arr = addMessage(arr,"已下架");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已禁用产品禁止再次发布
		if(ConstantStr.GOODSSTATUS_05.equals(original.getStatus())){
			arr = addMessage(arr,"已禁用");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置发布状态
		goods.setStatus(ConstantStr.GOODSSTATUS_02);
		// 更新回数设置
		goods.setUpdEac(original.getUpdEac());
		// 共同属性设置
		this.editAttr(goods);
		// 更新产品状态
		int i = goodsService.updateSelective(goods);
		if(i>0){
			// 日志增加
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,arr),
					ConstantStr.J_GOODS, goods.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 商品禁用：已经删除、未发布、已经禁用的产品禁止禁用
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午4:11:24
	 * @param goods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/rejectGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String rejectGoods(Goods goods, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"商品"};
		// 验证是否已经删除
		Goods original = goodsService.findById(goods.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 未发布的产品禁止该操作
		if(ConstantStr.GOODSSTATUS_01.equals(original.getStatus())){
			arr = addMessage(arr,"未发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 已禁用产品禁止再次发布
		if(ConstantStr.GOODSSTATUS_05.equals(original.getStatus())){
			arr = addMessage(arr,"已禁用");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置禁用状态、更新回数、共同属性修改
		goods.setStatus(ConstantStr.GOODSSTATUS_05);
		goods.setUpdEac(original.getUpdEac());
		this.editAttr(goods);
		int i = goodsService.rejectGoods(goods);
		if(i>0){
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,arr),
					ConstantStr.J_GOODS, goods.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化修改页面
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午4:58:43
	 * @param goodsId
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/toEditGoods/{goodsId}", method = {RequestMethod.GET})
	public String toEditGoods(@PathVariable Long goodsId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
		if(goodsId != null){
			Goods original = goodsService.findById(goodsId);
			map.put("id", goodsId);
			map.put("details", original.getDetails());
			map.put("attachs", JSONArray.fromObject(original.getAttachs()));
		}
		return GOODS_EDIT;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改数据查找
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午5:03:25
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/findGoods/{goodsId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findGoods(@PathVariable Long goodsId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		ObjectMapper o = new ObjectMapper();
		Goods original = goodsService.findById(goodsId);
		GoodsVo goodsVo = new GoodsVo();
		BeanUtils.copyProperties(original, goodsVo);
		goodsVo.setLaunchDateStr(DateUtil.format(original.getLaunchDate(), "yyyy-MM-dd HH:mm:ss"));
		return o.writeValueAsString(goodsVo);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 扩展信息查找
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午5:14:20
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/findGoodsExtendInfo/{goodsId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findGoodsExtendInfo(@PathVariable Long goodsId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<GoodsExtendInfor> list = goodsExtendInforService.selectByGoodsId(goodsId);
		return o.writeValueAsString(list);
	}
	

	/**
	 * 
	 * @Title
	 * @Description 更新商品
	 * @author:YK
	 * @CreateDate:2016年8月12日 上午10:52:31
	 * @param goodsVo
	 * @param files
	 * @param extendInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/updateGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateGoods(GoodsVo goodsVo,@RequestParam("file") MultipartFile[] files,
			@RequestParam String extendInfo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"商品"};
		// 验证是否已经删除
		Goods original = goodsService.findById(goodsVo.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		
		// 属性拷贝
		Goods goods = new Goods();
		BeanUtils.copyProperties(goodsVo, goods);
		goods.setInsId(original.getInsId());
		goods.setInsYmdhms(original.getInsYmdhms());
		// 时间赋值
		goods.setLaunchDate(DateUtil.parseDate(goodsVo.getLaunchDateStr(), "yyyy-MM-dd HH:mm:ss"));
		// 扩展属性
		JSONObject allObject = JSONObject.fromObject(extendInfo);
		JSONArray jsonArray = allObject.getJSONArray("rows");
		if(jsonArray.size()<=0){// 验证是否存在扩展属性
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100095,arr));
			return o.writeValueAsString(map);
		}
		List<GoodsExtendInfor> goodsExtendInfors = new ArrayList<GoodsExtendInfor>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			GoodsExtendInfor goodsExtendInfor = new GoodsExtendInfor();
			goodsExtendInfor.setExtendName(jsonObject.getString("extendName"));
			goodsExtendInfor.setExtendValue(jsonObject.getString("extendValue"));
			goodsExtendInfor.setExtendRemark(jsonObject.getString("extendRemark"));
			goodsExtendInfors.add(goodsExtendInfor);
		}
		goods.setGoodsExtendInfors(goodsExtendInfors);
		//附件上传
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			Attach attach = new Attach();
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				if(i == 0){
					goods.setThumbnail(goodsVo.getThumbnail());
					continue;
				}else if(i == 1 && StringUtils.isNotBlank(goodsVo.getPicOne())){
					attach.setUrl(goodsVo.getPicOne());
					attachs.add(attach);
				}else if(i == 2 && StringUtils.isNotBlank(goodsVo.getPicTow())){
					attach.setUrl(goodsVo.getPicTow());
					attachs.add(attach);
				}else if(i == 3 && StringUtils.isNotBlank(goodsVo.getPicThree())){
					attach.setUrl(goodsVo.getPicThree());
					attachs.add(attach);
				}
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				// 第一个附件为缩略图
				if(i==0 && file.getSize()>0){
					goods.setThumbnail(newFileName);
					continue;
				}else{
					attach.setUrl(newFileName);
					attachs.add(attach);
				}
			}
		}
		goods.setAttachs(attachs);
		// 新增公共属性
		goods.setUpdEac(original.getUpdEac());
		editAttr(goods);
		// 更新商品主数据
		boolean bl = goodsService.updateGoods(goods);
		if(bl){
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr),
					ConstantStr.J_GOODS, goods.getId(), getUser());
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
	 * @Description 初始化铺货页面
	 * @author:YK
	 * @CreateDate:2016年8月12日 下午3:08:22
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/toDistributionGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public String toDistributionGoods(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response){
		Long pId =(long) 0;
		// 查询所有铺货模块
		List<GoodsClassific> goodslist = goodsClassificService.getGoodsListByPid(pId);
		// 去除新商品、小区团模块
		List<GoodsClassific> list = new ArrayList<GoodsClassific>();
		for(GoodsClassific goodsClassific:goodslist){
			if((!goodsClassific.getCode().equals(ConstantStr.XCP)) 
					&& (!goodsClassific.getCode().equals(ConstantStr.XQT))){
				list.add(goodsClassific);
			}
		}
		map.put("goodsClassfic", list);
		return DISTRIBUTIONGOODS;
	}

	/**
	 * 
	 * @Title
	 * @Description 保存铺货信息
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午4:25:09
	 * @param distributionGoodsVo
	 * @param extendInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/saveDistributionGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String saveDistributionGoods(DistributionVo distributionVo,@RequestParam String villageId,
			@RequestParam String goodsInfo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"铺货"};
		// 验证是否选择铺货模块
		if(distributionVo.getClassificId() == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100090));
			return o.writeValueAsString(map);
		}
		//设置铺货与商品关系
		JSONObject allObject = JSONObject.fromObject(goodsInfo);
		JSONArray jsonArray = allObject.getJSONArray("rows");
		List<DistributionGoods> distributionGoods = new ArrayList<DistributionGoods>();
		// 验证是否选择商品铺货
		if(jsonArray.size()<=0){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100088));
			return o.writeValueAsString(map);
		}
		// 处理铺货与商品、分类
		String classificId = "";
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			DistributionGoods distributionGood = new DistributionGoods();
			if((!jsonObject.containsKey("sellPrice"))||
					StringUtils.isBlank(jsonObject.getString("sellPrice"))){
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100092));
				return o.writeValueAsString(map);
			}
			try{
				distributionGood.setSellPrice(new BigDecimal(jsonObject.getString("sellPrice")));
			}catch(Exception ex){
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100092));
				return o.writeValueAsString(map);
			}
			if((!jsonObject.containsKey("classificId"))||
					StringUtils.isBlank(jsonObject.getString("classificId"))){
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100091));
				return o.writeValueAsString(map);
			}
			classificId = jsonObject.getString("classificId");
			distributionGood.setGoodsClassificId(Long.valueOf(classificId));
			distributionGood.setGoodsId(Long.valueOf(jsonObject.getString("id")));
			distributionGoods.add(distributionGood);
		}
		distributionVo.setDistributionGoods(distributionGoods);
		// 处理铺货与小区关系
		String villageIds[] = {};
		if(StringUtils.isNotBlank(villageId)){
			villageIds = villageId.split(",");
		}
		// 验证是否选择铺货小区
		if(villageIds.length<=0){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100089));
			return o.writeValueAsString(map);
		}
		List<DistributionVillage> distributionVillages = new ArrayList<DistributionVillage>();
		for(int i=0;i<villageIds.length;i++){
			DistributionVillage distributionVillage = new DistributionVillage();
			distributionVillage.setVillageId(Long.valueOf(villageIds[i]));
			distributionVillages.add(distributionVillage);
		}
		distributionVo.setDistributionVillages(distributionVillages);
		Distribution distribution = new Distribution();
		BeanUtils.copyProperties(distributionVo, distribution);
		// 设置铺货信息公共属性并保存
		addDistributionAttr(distribution);
		// 保存铺货信息
		boolean bl = distributionService.saveDistribution(distribution);
		if(bl){
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr),
					ConstantStr.J_DISTRIBUTION, distribution.getId(), getUser());
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
	 * @Description 商品选择与查找
	 * @author:YK
	 * @CreateDate:2016年8月12日 上午11:27:30
	 * @return String
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/toSelectGoods", method = { RequestMethod.GET })
	public String toSelectGoods() {
		return GOODS_SELECT;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 查找可以铺货的产品
	 * @author:YK
	 * @CreateDate:2016年8月12日 上午11:50:42
	 * @param goodsVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:view")
	@RequestMapping("/findSelectGoods")
	public @ResponseBody String findSelectGoods(GoodsVo goodsVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		// 设置商品状态在数据字典中的type类型
		goodsVo.setStatusType(ConstantStr.GOODSSTATUS);
		/**选择要铺货的商品的状态:02：已发布;03：销售中;04：已下架*/
		goodsVo.setStatus_02(ConstantStr.GOODSSTATUS_02);
		goodsVo.setStatus_03(ConstantStr.GOODSSTATUS_03);
		goodsVo.setStatus_04(ConstantStr.GOODSSTATUS_04);
		// 查找可以铺货的商品
		goodsVoList = goodsService.findSelectGoods(goodsVo);
		
		map.put("rows", goodsVoList); 

		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化商品分类选择
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午2:57:38
	 * @param goodsId
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/goodsclassificMain/{goodsId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String sysdataMain(@PathVariable Long goodsId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
		map.put("goodsId", goodsId);
        return MAIN;
    }
	
	/**
	 * 
	 * @Title
	 * @Description 商品分类数据查询
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午2:57:02
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTreeAll")
    @ResponseBody
    public String getTreeAll() throws Exception{
		JSONArray  json=JSONArray.fromObject(goodsClassificService.getGoodsTypeJsonTreeAll());
        return json.toString(); 
    }
	
	/**
	 * 
	 * @Title
	 * @Description 商品分类右侧列表初始化
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午2:59:28
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("list")
    public String list(@RequestParam String id,Map<String, Object> map){
    	map.put("id", id);
        return LIST;
    }
	
	/**
	 * 
	 * @Title 评论管理
	 * @Description 跳转到评论页面
	 * @author: 田振兴
	 * @CreateDate:2016年8月12日 上午11:49:13
	 * @param model
	 * @param id
	 * @param goodsCode
	 * @param goodsView
	 * @param goodsName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/goodsCommment/{id}/{goodsCode}/{goodsView}/{goodsName}",method={RequestMethod.POST,RequestMethod.GET})
	public String goodsCommment(Model model,@PathVariable Long id,@PathVariable String goodsCode,
			@PathVariable String goodsName,@PathVariable Long goodsView,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("id",id);
		model.addAttribute("goodsView",goodsView);
		model.addAttribute("goodsCode",encodeStr(goodsCode));
		model.addAttribute("goodsName",encodeStr(goodsName));
		GoodsRecommendCommentVo goodsRecommendCommentVo = new GoodsRecommendCommentVo();
		goodsRecommendCommentVo.setGoodsId(id);	
		//总评论
		Long sumcount = goodsRecommendCommentService.findAllCount(goodsRecommendCommentVo);
		List<Integer> sumlist = new ArrayList<Integer>();
		sumlist.add(ConstantStr.COMMENT_STAR1);
		sumlist.add(ConstantStr.COMMENT_STAR2);
		goodsRecommendCommentVo.setSumList(sumlist);
		//差评
		Long badcount = goodsRecommendCommentService.findAllCount(goodsRecommendCommentVo);
		sumlist.clear();
		sumlist.add(ConstantStr.COMMENT_STAR3);
		goodsRecommendCommentVo.setSumList(sumlist);
		//中评
		Long comments = goodsRecommendCommentService.findAllCount(goodsRecommendCommentVo);
		sumlist.clear();
		sumlist.add(ConstantStr.COMMENT_STAR4);
		sumlist.add(ConstantStr.COMMENT_STAR5);
		goodsRecommendCommentVo.setSumList(sumlist);
		//好评
		Long goodcount = goodsRecommendCommentService.findAllCount(goodsRecommendCommentVo);
		model.addAttribute("badcount",badcount);
		model.addAttribute("comments",comments);
		model.addAttribute("goodcount",goodcount);
		model.addAttribute("sumcount",sumcount);
		return GOODSCOMMENT;
	}
	/**
	 * 
	 * @Title 评论管理
	 * @Description 根据商品ID查询评论人的所有评论
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 上午11:43:00
	 * @param goodsRecommendCommentVo
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/findgoodsCommment/{id}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findgoodsCommment( GoodsRecommendCommentVo goodsRecommendCommentVo,@PathVariable Long id,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		//从商品分类表里找到新产品的信息
		GoodsClassific goodsClassific = new GoodsClassific();
		//查找新产品的分类id,设置到查询条件中
		goodsClassific.setCode(ConstantStr.XCP);		
		goodsClassific = goodsClassificService.selectClassific(goodsClassific);
		goodsRecommendCommentVo.setClassificId(goodsClassific.getId());
		// 设置商品id
		goodsRecommendCommentVo.setGoodsId(id);
		List<GoodsRecommendCommentVo> goodsCommentlist = 
				goodsRecommendCommentService.findGoodsRecommendComment(goodsRecommendCommentVo);	
		map.put("rows", goodsCommentlist);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);					
	}
	
	/**
	 * 
	 * @Title 评论管理--评论回复
	 * @Description 跳转到回复页面
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 下午4:07:22
	 * @param model
	 * @param id
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/commentReturninfo/{id}/{goodsName}/{classificId}/{goodsView}",method={RequestMethod.POST,RequestMethod.GET})
	public String commentReturninfo(Model model,@PathVariable Long id,@PathVariable Long goodsView,@PathVariable String goodsName,
			@PathVariable Long classificId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("commentId",id);
		model.addAttribute("id",id);
		model.addAttribute("goodsView",goodsView);
		//从商品分类表里找到新产品的信息
		GoodsClassific goodsClassific = new GoodsClassific();
		// 新产品id查询,用于区别查询哪一模块的评论回复
		goodsClassific.setCode(ConstantStr.XCP);		
		goodsClassific = goodsClassificService.selectClassific(goodsClassific);
		//如果有新产品的信息并且订单推荐模块ID和新产品的ID相同
		if(goodsClassific!=null && classificId==goodsClassific.getId()){
			GoodsRecommendCommentVo goodsRecommendCommentVo = new GoodsRecommendCommentVo();
			goodsRecommendCommentVo.setId(id);
			//找到评论信息(推荐模块为新产品)
			goodsRecommendCommentVo = goodsRecommendCommentService.findOneCommentsGR(goodsRecommendCommentVo);
			GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo = new GoodsRecommendCommentReturnInfoVo();
			goodsRecommendCommentReturnInfoVo.setCommentId(id);
			//回复量
			Long reply = goodsRecommendCommentReturnInfoService.findReturnInfoCount(goodsRecommendCommentReturnInfoVo);
			goodsRecommendCommentReturnInfoVo.setStatus(ConstantStr.WG_SUM);
			//违规量
			Long illegal = goodsRecommendCommentReturnInfoService.findReturnInfoCount(goodsRecommendCommentReturnInfoVo);
			model.addAttribute("commentList",goodsRecommendCommentVo);
			model.addAttribute("reply",reply);
			model.addAttribute("illegal",illegal);
		}else{
			DistributionGoodsCommentVo distributionGoodsCommentVo = new DistributionGoodsCommentVo();
			distributionGoodsCommentVo.setId(id);
			//找到评论信息(推荐模块为即可送等,不包括新产品)
			distributionGoodsCommentVo = distributionGoodsCommentService.findDistributionGoodsComment(distributionGoodsCommentVo);
			DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo = new DistributionGoodsCommentReturnInfoVo();
			distributionGoodsCommentReturnInfoVo.setCommentId(id);
			//回复量
			Long reply = distributionGoodsCommentReturnInfoService.findReturnInfoCount(distributionGoodsCommentReturnInfoVo);
			distributionGoodsCommentReturnInfoVo.setStatus(ConstantStr.WG_SUM);
			//违规量
			Long illegal = distributionGoodsCommentReturnInfoService.findReturnInfoCount(distributionGoodsCommentReturnInfoVo);
			model.addAttribute("commentList",distributionGoodsCommentVo);
			model.addAttribute("reply",reply);
			model.addAttribute("illegal",illegal);
		}
		model.addAttribute("classificId",classificId);
		model.addAttribute("goodsName",encodeStr(goodsName));
		return COMMENTRETURNINFO;
	}
	
	/**
	 * 
	 * @Title 商品管理-评论管理
	 * @Description 逻辑删除回复
	 * @author:田振兴
	 * @CreateDate:2016年8月13日 下午5:14:35
	 * @param id
	 * @param classificId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/deleteGoodsComment",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteGoodsComment(Long id,Long classificId,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"删除"};
		//从商品分类表里找到新产品的信息
		GoodsClassific goodsClassific = new GoodsClassific();
		// 查询新产品模块id
		goodsClassific.setCode(ConstantStr.XCP);		
		goodsClassific = goodsClassificService.selectClassific(goodsClassific);
		//如果有新产品的信息并且订单推荐模块ID和新产品的ID相同
		if(goodsClassific!=null && classificId==goodsClassific.getId()){
			GoodsRecommendComment goodsRecommendComment = new GoodsRecommendComment();
			goodsRecommendComment.setId(id);
			//根据id找到评论信息(新产品的评论)
			goodsRecommendComment = goodsRecommendCommentService.findByCommentId(id);
			if(goodsRecommendComment==null){
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
				return o.writeValueAsString(map);
			}else{
				goodsRecommendComment.setDelFlag(ConstantStr.DELETE_Y);
				this.editAttrCommentGR(goodsRecommendComment);
				//删除评论
				int count = goodsRecommendCommentService.deleteGoodsRecommendComment(goodsRecommendComment);
				if(count>0){
					syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INF0100058,arr), ConstantStr.J_GOODS_RECOMMEND_COMMENT, goodsRecommendComment.getId(), getUser());
					map.put("type", "Success");
					map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr));
					return o.writeValueAsString(map);
				} else{
					map.put("type", "Error");
					map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr));
					return o.writeValueAsString(map);
				}
			}
		} else {
			DistributionGoodsComment distributionGoodsComment = new DistributionGoodsComment();
			distributionGoodsComment.setId(id);
			//根据id找到评论信息(即可送的评论)
			distributionGoodsComment = distributionGoodsCommentService.findOneCommentsEasyDG(id);
			if(distributionGoodsComment==null){
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
				return o.writeValueAsString(map);
			}else{
				distributionGoodsComment.setDelFlag(ConstantStr.DELETE_Y);
				this.editAttrCommentDG(distributionGoodsComment);
				//删除评论
				int count = distributionGoodsCommentService.deleteDistributionGoodsComment(distributionGoodsComment);
				if(count>0){
					map.put("type", "Success");
					map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr));
					syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INF0100058,arr),
							ConstantStr.J_DISTRIBUTION_GOODS_COMMENT, distributionGoodsComment.getId(), getUser());
					return o.writeValueAsString(map);
				} else{
					map.put("type", "Error");
					map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr));
					return o.writeValueAsString(map);
				}
			}
		}		
		
	}
	
	/**
	 * 
	 * @Title
	 * @Description  商品类别管理页面,根据父ID查询商品类别信息
	 * @author: YK
	 * @CreateDate:2016年7月25日 上午11:31:36
	 * @param searchSysdata
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("typemag")
	public @ResponseBody String typemag(@RequestParam String id,Map<String, Object> map) throws Exception{

		List<GoodsClassific> typeList = goodsClassificService.getGoodsListByPid(Long.valueOf(id));
		 if((typeList != null) && (typeList.size() >0)){
             map.put("rows", typeList);
         }else{
         }
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 根据评论ID和条件查找回复列表
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 下午4:07:57
	 * @param goodsRecommendCommentReturnInfoVo
	 * @param commentId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/findcommentReturninfo/{commentId}/{classificId}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findcommentReturninfo( GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo,
			DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo,@PathVariable Long commentId,
			@PathVariable Long classificId,HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		// 查询新产品模块id，用于区别查找不同模块的评论回复
		GoodsClassific goodsClassific = new GoodsClassific();
		goodsClassific.setCode(ConstantStr.XCP);		
		goodsClassific = goodsClassificService.selectClassific(goodsClassific);
		if(goodsClassific!=null && classificId==goodsClassific.getId()){
			List<GoodsRecommendCommentReturnInfoVo> commentReturnInfolist= new ArrayList<GoodsRecommendCommentReturnInfoVo>();
			goodsRecommendCommentReturnInfoVo.setCommentId(commentId);
			commentReturnInfolist =goodsRecommendCommentReturnInfoService.findGoodsRecommendCommentReturnInfo(goodsRecommendCommentReturnInfoVo);
			map.put("rows", commentReturnInfolist);
			return o.writeValueAsString(map);
		} else{
			List<DistributionGoodsCommentReturnInfoVo> ReturnInfolist= new ArrayList<DistributionGoodsCommentReturnInfoVo>();
			distributionGoodsCommentReturnInfoVo.setCommentId(commentId);
			ReturnInfolist = distributionGoodsCommentReturnInfoService.findDistributionGoodsCommentReturnInfo(distributionGoodsCommentReturnInfoVo);
			map.put("rows", ReturnInfolist);
			return o.writeValueAsString(map);
		}											
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除评论的回复
	 * @author:YK
	 * @CreateDate:2016年8月12日  上午10:04:55
	 * @param id
	 * @param classificId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Goods:manager")
	@RequestMapping(value = "/destroyReturnInfo/{id}/{classificId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String destroyReturnInfo(@PathVariable Long id, @PathVariable Long classificId,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"商品评论回复"};
		
		// 查询新产品模块id，用于区别删除不同模块的评论回复
		GoodsClassific goodsClassific = new GoodsClassific();
		goodsClassific.setCode(ConstantStr.XCP);		
		goodsClassific = goodsClassificService.selectClassific(goodsClassific);
		if(goodsClassific != null && classificId == goodsClassific.getId()){
			// 验证新产品模块的回复是否已经被删除
			GoodsRecommendCommentReturnInfo goodsRecommendCommentReturnInfo = 
					goodsRecommendCommentReturnInfoService.findById(id);
			if(goodsRecommendCommentReturnInfo == null){
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
				return o.writeValueAsString(map);
			}
			// 设置删除标志
			goodsRecommendCommentReturnInfo.setDelFlag(ConstantStr.DELETE_Y);
			// 修改新产品回复公共属性
			goodsRecommendCommentReturnInfo.setUpdEac(goodsRecommendCommentReturnInfo.getUpdEac()+1);
			goodsRecommendCommentReturnInfo.setUpdId(getUserId());
			goodsRecommendCommentReturnInfo.setUpdYmdhms(new Date());
			// 更新数据
			int i = goodsRecommendCommentReturnInfoService.updateSelective(goodsRecommendCommentReturnInfo);
			if(i>0){
				// 系统日志插入
				syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
						ConstantStr.J_GOODS_RECOMMEND_COMMENT_RETURN_INFO, goodsRecommendCommentReturnInfo.getId(), getUser());
				map.put("type", "Success");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
			}else{
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
			}
		}else{
			// 验证新非新产品模块的回复是否已经被删除
			DistributionGoodsCommentReturnInfo commentReturnInfo =
					distributionGoodsCommentReturnInfoService.findById(id);
			if(commentReturnInfo == null){
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
				return o.writeValueAsString(map);
			}
			// 设置删除表示位置
			commentReturnInfo.setDelFlag(ConstantStr.DELETE_Y);
			// 共同属性设置
			commentReturnInfo.setUpdEac(commentReturnInfo.getUpdEac()+1);
			commentReturnInfo.setUpdId(getUserId());
			commentReturnInfo.setUpdYmdhms(new Date());
			// 更新评论回复的主数据
			int i = distributionGoodsCommentReturnInfoService.updateSelective(commentReturnInfo);
			if(i>0){
				// 插入系统日志
				syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
						ConstantStr.J_DISTRIBUTION_GOODS_COMMENT_RETURN_INFO, commentReturnInfo.getId(), getUser());
				map.put("type", "Success");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
			}else{
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
			}
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 公共属性新增
	 * @author:YK
	 * @CreateDate:2016年8月8日 下午3:49:25
	 * @param _temp
	 */
	private void addAttr(Goods _temp) {
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
	 * @Description 新增铺货信息公共属性
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午5:39:10
	 * @param _temp
	 */
	private void addDistributionAttr(Distribution _temp){
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
	private void editAttr(Goods _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改共同属性
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:21:53
	 * @param _temp
	 */
	private void editAttrCommentGR(GoodsRecommendComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	/**
	 * 
	 * @Title
	 * @Description 修改共同属性
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:21:53
	 * @param _temp
	 */
	private void editAttrCommentDG(DistributionGoodsComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 时间提交转换
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午2:58:11
	 * @param binder
	 */
	@InitBinder  
    private void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
	/**
	 * 
	 * @Title
	 * @Description 提示消息添加
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午3:58:11
	 * @param original
	 * @param message
	 * @return
	 */
	private Object[] addMessage(Object[] original,String message){
		List<Object> messageList = new ArrayList<Object>();
		for(Object obj:original){
			messageList.add(obj);
		}
		messageList.add(message);
		return messageList.toArray();
	}
	
	/**
	 * 	 
	 * @Title
	 * @Description 解决中文乱码
	 * @author:田振兴
	 * @CreateDate:2016年8月24日 下午3:21:27
	 * @param str
	 * @return
	 */
	private static String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }    
}