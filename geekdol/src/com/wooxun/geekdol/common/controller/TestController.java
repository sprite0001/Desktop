package com.wooxun.geekdol.common.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author 863soft-王肖东	
* @CreateDate 2016年7月13日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 王肖东	2016年7月13日  下午3:19:30 		创建
*==========================================================
*
 */
@Controller
public class TestController {

	
	public static final String EDITOR = "demo/ueditor";
	/**
	 *   
	 * @Title
	 * @Description
	 * @author:王肖东
	 * @CreateDate:2016年7月13日 下午4:06:35
	 * @param map
	 * @return
	 */
	@RequestMapping("/selectshuangxiang")
	public ModelAndView  selectshuangxiangSelect(Map<String, Object> map) {
		Map model = new HashMap();
		return new ModelAndView("demo/shuangxiang",model);
	}
	
	/**
	 *   
	 * @Title
	 * @Description
	 * @author:王肖东
	 * 
	 * @CreateDate:2016年7月13日 下午4:06:35
	 * @param map
	 * @return
	 */
	@RequestMapping("/tanchuangsanji")
	public ModelAndView  tanchuangsanji(Map<String, Object> map) {
		Map model = new HashMap();
		return new ModelAndView("main/glsProperty",model);
	}
	
	@RequestMapping(value = "/toueditor", method = {RequestMethod.GET,RequestMethod.POST})
	public String toueditor(HttpServletRequest request) {
		
		String dddString= request.getParameter("content");
		request.setAttribute("content", request.getParameter("content"));
		
		return EDITOR;
	}
	
	
	  
    //根据请求的路径中的参数id,从本地磁盘中读取图片，picUrl是从配置文件中读取出来的  
    @RequestMapping(value = "/tofindPic", method = {RequestMethod.GET,RequestMethod.POST})  
    public void picToJSP(HttpServletRequest request,HttpServletResponse response){  
       
        FileInputStream in;  
        response.setContentType("application/octet-stream;charset=UTF-8");  
        try {  
            //图片读取路径  
           //in=new FileInputStream("E:\\picture\\"+photoName);  
           String url=request.getParameter("url");
           in=new FileInputStream("D:\\"+url);
           int i=in.available();  
           byte[]data=new byte[i];  
           in.read(data);  
           in.close();  
             
           //写图片  
           OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());  
           outputStream.write(data);  
           outputStream.flush();  
           outputStream.close();  
       } catch (Exception e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }  
          
    }  
}
