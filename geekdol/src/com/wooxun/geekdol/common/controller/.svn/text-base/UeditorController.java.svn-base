package com.wooxun.geekdol.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.ueditor.ActionEnter;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月26日  下午4:04:26 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("ueditor")
public class UeditorController {
	
	@RequestMapping(value = "/uploadAtt/{model}", method = {RequestMethod.GET,RequestMethod.POST})
	public  void uploadAtt(@PathVariable String model,@RequestParam String action,HttpServletRequest request,HttpServletResponse response) throws IOException{ 
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		String rootPath = request.getRealPath( "/");
    	String saveRootPath="D:";
    	PrintWriter out=response.getWriter();
    	out.write(new ActionEnter( request,saveRootPath, rootPath, "geekstore").exec());
    } 
}
