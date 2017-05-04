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
import com.wooxun.geekdol.system.model.Suggestion;
import com.wooxun.geekdol.system.service.SuggestionService;
import com.wooxun.geekdol.system.vo.SuggestionVo;

/**
 * @Title 投诉建议
 * @Description 投诉建议的查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午5:30:03 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("suggestion")
public class SuggestionController {
	
	//private static final Logger LOG = LoggerFactory.getLogger(SuggestionController.class);
	public static final String LIST = "system/suggestion/list";
	
	@Autowired
	private SuggestionService<Suggestion> suggestionService;
	
	/**
	 * 
	 * @Title 投诉建议
	 * @Description 进入投诉建议的页面
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 上午10:28:03
	 * @return 返回List(system/suggestion/list)页面
	 */
	@RequiresPermissions("Suggestion:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String info() {
		return LIST;
	}
	
	
	/**
	 * 
	 * @Title 投诉建议
	 * @Description 按条件查询投诉建议
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:42:12
	 * @param suggestionVo
	 * @param request
	 * @param response
	 * @return JSON数据
	 * @throws Exception
	 */
	@RequiresPermissions("Suggestion:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( SuggestionVo suggestionVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Suggestion> suggestionlist= new ArrayList<Suggestion>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		// 如果内容查询不为空 则进行表情加密
		if (StringUtils.isNotBlank(suggestionVo.getContent())) {
			suggestionVo.setContent(UnescapeUtil.escape(suggestionVo.getContent()));
		}
		suggestionVo.setPageFlag(true);
		suggestionVo.setStartPage((page-1)*rows);
		suggestionVo.setEndPage(rows);			
		count = suggestionService.findAllCount(suggestionVo);
		suggestionlist = suggestionService.findAll(suggestionVo);
		/* 转义表情 */
		for (Suggestion suggestion : suggestionlist) {
			suggestion.setContent(UnescapeUtil.unescape(suggestion.getContent()));
		}
		map.put("rows", suggestionlist);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

}
