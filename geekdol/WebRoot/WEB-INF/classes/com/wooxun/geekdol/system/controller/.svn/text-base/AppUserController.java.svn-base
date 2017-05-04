package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.MD5Util;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.AppUserService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.AppUserVo;

/**
 * @Title
 * @Description 系统设置-内参管理
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月19日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. YK 2016年7月19日 下午5:46:17 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("appUser")
public class AppUserController extends BaseController {
	// private static final Logger LOG =
	// LoggerFactory.getLogger(AppUserController.class);

	public static final String APPUSER = "system/appuser/list";
	public static final String ADDAPPUSER_ADD = "system/appuser/appuserAdd";
	public static final String APPUSER_UPDATE = "system/appuser/appuserEditor";
	public static final String MEMBER = "system/appuser/memberList";

	@Autowired
	private AppUserService<AppUser> appUserService;
	@Autowired
	private SyslogService<Syslog> syslogService;

	/**
	 * 
	 * @Title
	 * @Description 列表页面跳转
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午6:36:02
	 * @return String
	 */
	@RequiresPermissions("StaffUser:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return APPUSER;
	}

	/**
	 * 
	 * @Title
	 * @Description 列表页面数据查找
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午6:35:44
	 * @param appUserVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(AppUserVo appUserVo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		List<AppUserVo> appUserList = new ArrayList<AppUserVo>();
		Long count = 0l;
		appUserVo.setPageFlag(true);
		appUserVo.setStartPage((page - 1) * rows);
		appUserVo.setEndPage(rows);
		count = appUserService.queryAppUserCount(appUserVo);
		appUserList = appUserService.queryAppUser(appUserVo);

		map.put("rows", appUserList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 会员转入列表初始化
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年7月21日 上午11:37:56
	 * @return String
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/toMemberList", method = { RequestMethod.GET })
	public String toMemberList() {
		return MEMBER;
	}

	/**
	 * 
	 * @Title
	 * @Description 会员转入时候，列表查询
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:02:44
	 * @param appUserVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping("/findAllMember")
	public @ResponseBody String findAllMember(AppUserVo appUserVo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		List<AppUserVo> appUserList = new ArrayList<AppUserVo>();
		Long count = 0l;
		appUserVo.setPageFlag(false);
		appUserVo.setStartPage((page - 1) * rows);
		appUserVo.setEndPage(rows);
		appUserVo.setStatus(ConstantStr.QY_FLAG);
		count = appUserService.queryMemberCountByParams(appUserVo);
		appUserList = appUserService.queryMemberByParams(appUserVo);

		map.put("rows", appUserList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 会员转为内参人员
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午2:20:23
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/memberToStaff", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String memberToStaff(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "内参人员" };
		boolean result = false;
		AppUser original = appUserService.selectAppUser(appUser.getId());
		if (original == null) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		appUser.setUpdEac(original.getUpdEac());
		// 设置内参标志与内参来源
		this.memberToStaffOpration(appUser);
		result = appUserService.updateAppUser(appUser);
		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100035));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100035), ConstantStr.APPUSER,
					appUser.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100036));
		}

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 初始化新增页面
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午6:26:37
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/toAddAppUser", method = { RequestMethod.GET })
	public String toAddAppUser(Model model) {
		return ADDAPPUSER_ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 新增保存
	 * @author:YK
	 * @CreateDate:2016年7月19日 上午9:46:23
	 * @param appUser
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "saveAppUser", method = { RequestMethod.POST })
	public @ResponseBody String saveAppUser(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Object[] arr = { "内参人员" };
		map.put("type", "Error");
		map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));

		// 手机号是否在会员管理、内参人员中都存在
		if (hasAppUser(appUser)) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100037));
		} else {
			appUser.setStatus(ConstantStr.QY_FLAG);
			appUser.setStaffFlag(ConstantStr.STAFFFLAG_Y);
			appUser.setStaffSource(ConstantStr.SAFFSOURCE_WEBCONSOLE);
			this.addAttr(appUser);
			boolean res = appUserService.insertAppUser(appUser);
			if (res) {
				map.put("type", "Success");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
				syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001, arr),
						ConstantStr.APPUSER, appUser.getId(), getUser());
			}
		}

		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 修改页面跳转
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午6:26:10
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/toEdit/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toEdit(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		map.put("id", id);
		return APPUSER_UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 修改页面数据初始化
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午6:35:12
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AppUser appUser = appUserService.selectAppUser(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(appUser);
	}

	/**
	 * 
	 * @Title
	 * @Description 更新内参人员
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午6:27:14
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/updateAppUser", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String updateAppUser(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "内参人员" };
		boolean result = false;
		AppUser original = appUserService.selectAppUser(appUser.getId());
		if (original == null) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		appUser.setUpdEac(original.getUpdEac());
		this.editAttr(appUser);
		result = appUserService.updateAppUser(appUser);
		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
					ConstantStr.APPUSER, appUser.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
		}
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 删除
	 * @author:YK
	 * @CreateDate:2016年7月21日 上午11:01:56
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/destroyAppUser", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String destroyAppUser(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "内参人员" };
		boolean result = false;
		AppUser original = appUserService.selectAppUser(appUser.getId());
		if (original == null) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		appUser.setUpdEac(original.getUpdEac());
		this.editAttr(appUser);
		result = appUserService.updateAppUser(appUser);
		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.APPUSER, appUser.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
		}
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description禁用启用
	 * @author:YK
	 * @CreateDate:2016年7月21日 上午10:52:47
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/rejectOrAccept", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String rejectOrAccept(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "内参人员" };
		boolean result = false;
		AppUser original = appUserService.selectAppUser(appUser.getId());
		if (original == null) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		// 禁止重复操作
		if (original.getStatus().equals(appUser.getStatus())) {
			map.put("type", "Error");
			if (ConstantStr.QY_FLAG.equals(appUser.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100013, arr));
			} else if (ConstantStr.JY_FLAG.equals(appUser.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100014, arr));
			}
			return o.writeValueAsString(map);
		}
		appUser.setUpdEac(original.getUpdEac());
		this.editAttr(appUser);
		result = appUserService.updateAppUser(appUser);
		if (result) {
			map.put("type", "Success");
			if (StringUtils.isNotBlank(appUser.getStatus()) && ConstantStr.QY_FLAG.equals(appUser.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100007, arr));
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007, arr),
						ConstantStr.APPUSER, appUser.getId(), getUser());
			} else {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100009, arr));
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009, arr),
						ConstantStr.APPUSER, appUser.getId(), getUser());
			}
		} else {
			map.put("type", "Error");
			if (StringUtils.isNotBlank(appUser.getStatus()) && ConstantStr.QY_FLAG.equals(appUser.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100008, arr));
			} else {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100010, arr));
			}
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 重置密码
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午5:32:17
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("StaffUser:manager")
	@RequestMapping(value = "/restPwd", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String restPwd(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "内参人员" };
		boolean result = false;
		AppUser original = appUserService.selectAppUser(appUser.getId());
		if (original == null) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		if (MD5Util.MD5(ConstantStr.DEFAULT_PSW).equals(original.getPwd())) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100038));
			return o.writeValueAsString(map);
		}
		appUser.setUpdEac(original.getUpdEac());
		// 修改共同属性
		this.editAttr(appUser);
		// 默认密码:123456
		appUser.setPwd(MD5Util.MD5(ConstantStr.DEFAULT_PSW));
		result = appUserService.updateAppUser(appUser);
		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100011, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100011, arr),
					ConstantStr.APPUSER, appUser.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100012, arr));
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 手机号是否在会员管理、内参人员中都存在
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午5:31:00
	 * @param appUser
	 * @return
	 */
	private boolean hasAppUser(AppUser appUser) {
		return appUserService.hasAppUser(appUser);
	}

	/**
	 * 添加用户共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void addAttr(AppUser _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(AppUser _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	/**
	 * 
	 * @Title
	 * @Description 设置内参人员标志，并设置来自会员
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午2:21:59
	 */
	private void memberToStaffOpration(AppUser _temp) {
		_temp.setStaffFlag(ConstantStr.STAFFFLAG_Y);
		_temp.setStaffSource(ConstantStr.SAFFSOURCE_MEMBER);
		editAttr(_temp);
	}

}
