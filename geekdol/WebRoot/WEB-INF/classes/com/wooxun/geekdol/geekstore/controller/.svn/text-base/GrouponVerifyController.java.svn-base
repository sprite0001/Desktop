package com.wooxun.geekdol.geekstore.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;
import com.wooxun.geekdol.geekstore.model.GrouponGoodsExtendInfo;
import com.wooxun.geekdol.geekstore.model.GrouponVillage;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsExtendInfoService;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsService;
import com.wooxun.geekdol.geekstore.vo.GrouponGoodsVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description 团购商品审核管理
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
@RequestMapping("grouponVerify")
public class GrouponVerifyController extends BaseController {
	//private static final Logger LOG = LoggerFactory.getLogger(GrouponVerifyController.class);
	
	public static final String GROUPONGOODS = "geekstore/grouponverify/list";
	public static final String GROUPONGOODS_EDIT = "geekstore/grouponverify/grouponGoodsEdit";
	@Autowired
	private GrouponGoodsService<GrouponGoods> grouponGoodsService;
	@Autowired
	private GrouponGoodsExtendInfoService<GrouponGoodsExtendInfo> grouponGoodsExtendInfoService;
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
	@RequiresPermissions("GrouponVerify:view")
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
	@RequiresPermissions("GrouponVerify:view")
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
		// 设置团购起始时间大于当前时间
		grouponGoodsVo.setStartTime(new Date());
		// 设置待审核状态
		grouponGoodsVo.setStatus(ConstantStr.GROUPONGOODSSTATUS_01);
		// 设置团购商品状态在数据字典中的类型
		grouponGoodsVo.setStatusType(ConstantStr.GROUPONGOODSSTATUS);
		// 总数查询
		count = grouponGoodsService.queryGrouponGoodsCount(grouponGoodsVo);
		// 列表查询
		grouponGoodsVoList = grouponGoodsService.queryGrouponGoods(grouponGoodsVo);
		
		map.put("rows", grouponGoodsVoList); 
		map.put("total", count);

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
	@RequiresPermissions("GrouponVerify:manager")
	@RequestMapping(value = "/toEditGroupongoods/{goodsId}", method = {RequestMethod.GET})
	public String toEditGroupongoods(@PathVariable Long goodsId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
		if(goodsId != null){
			// 初始化商品信息
			GrouponGoods original = grouponGoodsService.findById(goodsId);
			map.put("id", goodsId);
			map.put("grouponClassific", original.getGrouponClassific());
			map.put("details", original.getDetails());
			// 初始化团购商品铺货小区
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
	 * @Description 修改数据初始化
	 * @author:YK
	 * @CreateDate:2016年8月9日 上午9:48:18
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponVerify:manager")
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
	@RequiresPermissions("GrouponVerify:manager")
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
	 * @Description 商品审核
	 * @author:YK
	 * @CreateDate:2016年8月19日 上午11:55:06
	 * @param grouponGoods
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponVerify:manager")
	@RequestMapping(value = "/updateGrouponGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateGrouponGoods(GrouponGoodsVo grouponGoodsVo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"团购"};
		
		GrouponGoods original = grouponGoodsService.findById(grouponGoodsVo.getId());
		if(original == null){//验证是否已经删除
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		GrouponGoods grouponGoods = new GrouponGoods();
		grouponGoods.setId(grouponGoodsVo.getId());
		// 设置审核状态
		grouponGoods.setStatus(grouponGoodsVo.getStatusStr());
		//设置拒绝缘由
		grouponGoods.setRejectReason(grouponGoodsVo.getRejectReason());
		// 新增公共属性
		grouponGoods.setUpdEac(original.getUpdEac());
		editAttr(grouponGoods);
		boolean bl = grouponGoodsService.verifyGrouponGoods(grouponGoods);
		if(bl){
			// 日志增加
			syslogService.addLog(ConstantStr.VERIFY, ComDefine.getMsg(ConstantStr.INFO100100,arr), ConstantStr.J_GROUPON_GOODS, grouponGoodsVo.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100100,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100101,arr));
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
	
}
