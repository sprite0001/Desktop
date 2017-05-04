package com.wooxun.geekdol.system.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.system.model.Cooperation;
import com.wooxun.geekdol.system.service.CooperationService;
import com.wooxun.geekdol.system.vo.CooperationVo;

/**
 * @Title 我要合作
 * @Description 我要合作的查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午3:33:14 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("cooperation")
public class CooperationController {

	//private static final Logger LOG = LoggerFactory.getLogger(CooperationController.class);
	public static final String LIST = "system/cooperation/list";
	@Autowired
	private CooperationService<Cooperation> cooperationService;
	
	/**
	 * 
	 * @Title 合作查询
	 * @Description 进入我要合作的页面
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 上午10:28:03
	 * @return 返回LIST(system/cooperation/list)页面
	 */
	@RequiresPermissions("MyCooperation:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String info() {
		return LIST;
	}
	/**
	 * 
	 * @Title 合作查询
	 * @Description 按条件查询我要合作
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:42:12
	 * @param cooperationVo
	 * @param request
	 * @param response
	 * @return JSON数据
	 * @throws Exception
	 */
	@RequiresPermissions("MyCooperation:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( CooperationVo cooperationVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Cooperation> cooperationlist= new ArrayList<Cooperation>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		// 如果内容查询不为空 则进行表情加密
		if (StringUtils.isNotBlank(cooperationVo.getContent())) {
			cooperationVo.setContent(UnescapeUtil.escape(cooperationVo.getContent()));
		}
		cooperationVo.setPageFlag(true);
		cooperationVo.setStartPage((page-1)*rows);
		cooperationVo.setEndPage(rows);			
		count = cooperationService.findAllCount(cooperationVo);
		cooperationlist = cooperationService.findAll(cooperationVo);
		/* 转义表情 */
		for (Cooperation cooperation : cooperationlist) {
			cooperation.setContent(UnescapeUtil.unescape(cooperation.getContent()));
		}
		map.put("rows", cooperationlist);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
}
