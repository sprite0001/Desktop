package com.wooxun.geekdol.geekstore.controller;

import java.util.Date;
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

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.GroupPurchaseCode;
import com.wooxun.geekdol.geekstore.service.GroupPurchaseCodeService;
import com.wooxun.geekdol.geekstore.vo.GroupPurchaseCodeVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title 团购管理
 * @Description 团购验证
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月8日  上午10:54:57 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("groupPurchaseCode")
public class GroupPurchaseCodeController  extends BaseController{
	
	public static final String VALIDATE = "geekstore/groupPurchaseCode/validate";
	@Autowired
	private GroupPurchaseCodeService<GroupPurchaseCode> groupPurchaseCodeService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title 团购验证
	 * @Description  跳转到团购验证页面
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 下午3:08:49
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GroupPurchaseCode:view")
	@RequestMapping(value = "/validate", method = {RequestMethod.GET,RequestMethod.POST})
	public String validate(Model model,HttpServletRequest request,HttpServletResponse response){
		return VALIDATE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 团购验证
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 下午3:09:32
	 * @param groupPurchaseCodeVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GroupPurchaseCode:view")
	@RequestMapping(value = "/validateOrder",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String validateOrder(GroupPurchaseCodeVo groupPurchaseCodeVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		//取到团购验证码并放到List里边
		List<String> codeList = groupPurchaseCodeVo.getCodeList();
		Object[] arr = {"验证"};
		//循环团购验证码
		for(int i=0;i<codeList.size();i++){
			String res = "res"+i;
			//如果验证码没填的，这个验证码返回提示
			if(StringUtils.isBlank(codeList.get(i))){
				map.put(res, ComDefine.getMsg(ConstantStr.INFO200028));
				continue;
			}
			//如果验证码填写了，创建团购表对象，给对象赋值（团购编码，团购码）
			GroupPurchaseCode groupPurchaseCode = new GroupPurchaseCode();
			groupPurchaseCode.setSerialCode(groupPurchaseCodeVo.getSerialCode());
			groupPurchaseCode.setCode(codeList.get(i));
			groupPurchaseCode.setStatus(ConstantStr.WYZ);//未验证
			//把团购表对象当参数查找是否有这条团购码数据
			groupPurchaseCode = groupPurchaseCodeService.verificationCode(groupPurchaseCode);
			//如果查询到的这条数据是空的话，这个验证码返回提示
			if(groupPurchaseCode==null){			
				map.put(res, ComDefine.getMsg(ConstantStr.INFO100081));
			}else{
				//给团购表对象赋值（团购码状态、修改公共字段）
				this.editAttr(groupPurchaseCode);
				groupPurchaseCode.setStatus(ConstantStr.YYZ);
				//修改团购码信息
				int count = groupPurchaseCodeService.updateGroup(groupPurchaseCode);
				if(count>0){
					//添加日志
					syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr), ConstantStr.GROUPPURCHASECODE, groupPurchaseCode.getId(), getUser());
					map.put(res, ComDefine.getMsg(ConstantStr.INF0100058,arr));
				}else{
					map.put(res, ComDefine.getMsg(ConstantStr.INF0100059,arr));
				}
			}
		}
		return o.writeValueAsString(map);
	}
	
	/**
     * 修改共同属性(GroupPurchaseCode)
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttr(GroupPurchaseCode _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
}
