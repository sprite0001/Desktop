package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.SyslogVo;

/**
 * @Title 日志管理
 * @Description 日志的查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月21日  上午10:35:35 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("syslog")
public class SyslogController {
	
	//private static final Logger LOG = LoggerFactory.getLogger(SyslogController.class);
	public static final String LIST = "system/syslog/list";
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title 日志查询
	 * @Description 进入日志管理的页面
	 * @author:田振兴
	 * @CreateDate:2016年7月21日 上午10:37:03
	 * @return 返回LIST(system/syslog/list)页面
	 */
	@RequiresPermissions("Syslog:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String info() {
		return LIST;
	}
	/**
	 * 
	 * @Title 日志查询
	 * @Description 按条件查询日志列表
	 * @author:田振兴
	 * @CreateDate:2016年7月21日 上午10:40:03
	 * @param syslogVo
	 * @param request
	 * @param response
	 * @return JSON数据
	 * @throws Exception
	 */
	@RequiresPermissions("Syslog:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( SyslogVo syslogVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Syslog> sysloglist= new ArrayList<Syslog>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		syslogVo.setPageFlag(true);
		syslogVo.setStartPage((page-1)*rows);
		syslogVo.setEndPage(rows);			
		count = syslogService.findAllCount(syslogVo);
		sysloglist = syslogService.findAll(syslogVo);
		map.put("rows", sysloglist);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
}
