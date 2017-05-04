package com.wooxun.geekdol.common.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.model.User;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. YK 2016年7月18日 下午3:26:19 创建
 *           ==========================================================
 * 
 */
public class BaseController {

	/**
	 * 取得登陆人ID
	 * 
	 * @return
	 * @author:YK
	 */
	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

	/**
	 * 
	 * @Title
	 * @Description 取得登录用户的信息
	 * @author:田振兴
	 * @CreateDate:2016年7月21日 下午4:03:49
	 * @return
	 */
	public User getUser() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		return user;
	}
	
	/**
	 * 
	 * @Title 将token放入session
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年9月5日 下午5:40:06
	 * @param request,model
	 */
	public void setToken(Model model,HttpServletRequest request){
	    //设置token
        String token = UUID.randomUUID().toString();
        model.addAttribute("token", token);
        request.getSession().setAttribute(token, token);
	}
	
	/**
     * 
     * @Title 验证token，防止重复提交，通过验证返回true，未通过返回false
     * @Description
     * @author:张洋
     * @CreateDate:2016年9月5日 下午5:40:06
     * @param request,model
     */
    public Boolean verifyToken(HttpServletRequest request){
        Object tokenStr = request.getParameter("token");
        if(tokenStr == null){
            return false;
        }
        Object token = request.getSession().getAttribute(tokenStr.toString());
        if(token == null){
            return false;
        }else{
            request.getSession().removeAttribute(tokenStr.toString());
            return true;
        }
    }
}
