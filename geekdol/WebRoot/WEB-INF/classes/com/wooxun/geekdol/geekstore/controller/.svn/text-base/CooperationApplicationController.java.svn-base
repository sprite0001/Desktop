package com.wooxun.geekdol.geekstore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.CooperationApplication;
import com.wooxun.geekdol.geekstore.service.CooperationApplicationService;
import com.wooxun.geekdol.geekstore.vo.CooperationApplicationVo;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title 吉客店管理
 * @Description 合作店申请查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月25日  上午10:20:51 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("cooperationApplication")
public class CooperationApplicationController extends BaseController {
		
	public static final String LIST = "geekstore/cooperationApplication/list";
	@Autowired
	private CooperationApplicationService<CooperationApplication> cooperationApplicationService;
	@Autowired
	private SysDataService<SysData> sysDataService;
	@Autowired
	private UserAreaService<UserArea> userAreaService;
	
	/**
	 * 
	 * @Title 合作店申请查询
	 * @Description 跳转到列表页面
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 上午10:35:49
	 * @return 返回LIST(system/cooperationApplication/list)页面
	 */
	@RequiresPermissions("CooperationA:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("STORETYPE",ConstantStr.STORETYPE);
		return LIST;
	}
	
	/**
	 * 
	 * @Title 合作店申请查询
	 * @Description 申请页面列表
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 上午10:37:05
	 * @param cooperationApplicationVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CooperationA:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( CooperationApplicationVo cooperationApplicationVo,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//翻页功能，找到是第几页
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		//翻页功能，设置一页有多少条数据
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<CooperationApplicationVo> applicationlist= new ArrayList<CooperationApplicationVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		//设置要翻页
		cooperationApplicationVo.setPageFlag(true);
		//设置开始的条数
		cooperationApplicationVo.setStartPage((page-1)*rows);
		//设置一页有多少条数据
		cooperationApplicationVo.setEndPage(rows);
		ObjectMapper o = new ObjectMapper();
		//按小区来查
		// 获取当前用户的级别
			String areaLevel = getCurrentAreaLevel();
			if(StringUtils.isBlank(areaLevel)){
				return o.writeValueAsString("");
			}
			//对VO进行赋值（用户的级别、用户登录ID）
		cooperationApplicationVo.setAreaLevel(areaLevel);
		cooperationApplicationVo.setCurrentUserId(getUserId());
		cooperationApplicationVo.setStoreType(ConstantStr.STORETYPE);
		//把VO当做参数找到合作店申请查询信息的列表总条数
		count = cooperationApplicationService.findCooperationApplicationCount(cooperationApplicationVo);
		//把VO当做参数找到合作店申请查询信息的列表
		applicationlist = cooperationApplicationService.findCooperationApplication(cooperationApplicationVo);		
		map.put("rows", applicationlist);
		map.put("total",count);		
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 获取当前用户的区域级别
	 * @author:YK
	 * @CreateDate:2016年7月29日 下午4:47:03
	 * @return
	 */
	private String getCurrentAreaLevel(){
		UserAreaVo userAreaVo = new UserAreaVo();
		userAreaVo.setUserId(super.getUserId());
		userAreaVo.setPageFlag(false);
		List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
		if(userAreaList.size()<=0){
			return "";
		}
		String areaLevel = userAreaList.get(0).getAreaLevel();
		return areaLevel;
	}
}
