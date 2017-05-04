package com.wooxun.geekdol.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft863.dolphin.util.AjaxObject;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.service.UserService;

/**
 * 
 * @ProjectName: [rmas] 
 * @Package: [com.hnrbyl.rmas.controller.IndexController.java]  
 * @ClassName: [IndexController] 
 * @Description: [TODO]
 * @Author [Administrator]
 * @CreateDate [2014-7-21 上午10:46:23] 
 *
 */
@Controller
@RequestMapping("/main/index")
public class IndexController {
	
	//private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	private static final String INDEX = "main/index";
	private static final String UPDATE_PASSWORD = "main/updatePwd";
	private static final String TO_SIDEBAR_2 = "main/sidebar_2";
	private static final String UPDATE_BASE = "main/updateBase";
	private static final String DOWNLOAD_APP = "main/downloadApp";
	@Autowired
	private UserService<User> userService;
	  
	/**
	 * 
	 * @Title
	 * @Description 首页跳转
	 * @author:863soft-zhougp
	 * @CreateDate:2016年6月12日 下午2:54:20
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method={RequestMethod.GET})
	public String index( HttpServletRequest request,Model model, Map<String, Object> map) throws Exception {
		 
		return INDEX;
	}
	
	@RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String error(HttpServletRequest request) {
		return AjaxObject.newError("权限不足！").setCallbackType("").toString();
	}
	
	@RequestMapping(value="/updatePwd", method=RequestMethod.GET)
	public String preUpdatePassword() {
		return UPDATE_PASSWORD;
	}
	
	@RequestMapping(value="/tosidebar2", method=RequestMethod.GET)
	public String tosidebar2() {
		return TO_SIDEBAR_2;
	}

	@RequestMapping(value="/updatePwd", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(HttpServletRequest request, String plainPassword, 
			String newPassword, String rPassword) {
		return "";
	}
	
	@RequestMapping(value="/toUpdateBase", method=RequestMethod.GET)
	public String preUpdateBase(Map<String, Object> map) {
		Subject currentUser = SecurityUtils.getSubject();
		String userName = currentUser.getPrincipal().toString();
		User user = userService.getByUserName(userName);
		map.put("user", user);
		return UPDATE_BASE;
	}
	
	@RequestMapping(value="/updateBase", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String update(User user,HttpServletRequest request) throws Exception {
		
		return "";
	}
	
	@RequestMapping(value="/toDownloadApp", method=RequestMethod.GET)
	public String preDownloadApp(Map<String, Object> map) {
		return DOWNLOAD_APP;
	}
}
