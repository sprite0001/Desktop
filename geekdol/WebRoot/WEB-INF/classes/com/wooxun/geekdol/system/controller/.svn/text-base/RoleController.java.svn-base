package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft863.dolphin.model.Module;
import com.soft863.dolphin.model.Permission;
import com.soft863.dolphin.model.Role;
import com.soft863.dolphin.model.UserRole;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.ModuleService;
import com.wooxun.geekdol.system.service.RoleService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserRoleService;
import com.wooxun.geekdol.system.vo.RoleVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 角色管理 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年7月18日 下午2:31:23 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService<Role> roleService;

	@Autowired
	private ModuleService<Module> moduleService;
	@Autowired
	private SyslogService<Syslog> syslogService;

	@Autowired
	private UserRoleService<UserRole> userRoleService;

	private static final String LIST = "system/role/roleList";
	private static final String ADD = "system/role/roleAdd";

	public static final String UPDATE = "system/role/roleEditor";

	/**
	 * 
	 * @Title
	 * @Description 跳转到列表页面
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:27:50
	 * @return
	 */
	@RequiresPermissions("Role:view")
	@RequestMapping("list")
	public String list() {

		return LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到新增页面并查询出全部模块
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:27:50
	 * @return
	 */
	@RequiresPermissions("Role:manager")
	@RequestMapping("toAddRole")
	public String toAddRole(Map<String, Object> model, Model model2, HttpServletRequest request) {
		List<Module> moduleList = moduleService.selectALlModules();
		model.put("moduleList", moduleList);
		setToken(model2, request);
		return ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 返回列表查询结果
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午3:03:28
	 * @param searchRole
	 *            request response
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Role:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(RoleVo searchRole, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Role> roleList = new ArrayList<Role>();
		Long count = 0l;
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		searchRole.setPageFlag(true);
		searchRole.setStartPage((page - 1) * rows);
		searchRole.setEndPage(rows);

		count = roleService.queryCountByParams(searchRole);

		if (count > 0) {
			roleList = roleService.queryRoleByParams(searchRole);
		}

		map.put("rows", roleList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 返回所以角色-不分页
	 * @author:张洋
	 * @CreateDate:2016年8月10日16:57:45
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Role:view")
	@RequestMapping("findAllNoPage")
	public @ResponseBody String findAllNoPage() throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		roleList = roleService.queryRoleByParams(new RoleVo());
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(roleList);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到编辑页面
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:43:40
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Role:manager")
	@RequestMapping(value = "/toEditRole/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toEdit(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		map.put("id", id);
		// 根据查询出模块
		List<Module> moduleList = moduleService.selectALlModules();
		map.put("moduleList", moduleList);

		// 根据角色id查到所有用的权限
		List<Permission> listPermissions = roleService.findPermissionBy(id);
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < listPermissions.size(); i++) {
			stringBuffer.append(listPermissions.get(i).getPerId());
			if (i != listPermissions.size() - 1) {
				stringBuffer.append(",");
			}
		}
		map.put("permissionsIds", stringBuffer.toString());

		setToken(model, request);

		return UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 修改初始化角色页面数据
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:44:05
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Role:manager")
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Role role = roleService.selectRole(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(role);
	}

	/**
	 * 
	 * @Title
	 * @Description 新增角色 相同名字的角色不能添加
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:44:18
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Role:manager")
	@RequestMapping(value = "saveRole", method = { RequestMethod.POST })
	public @ResponseBody String saveRole(Role role, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map2 = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Object[] arr = { "角色" };
		addAttr(role);
		if (!verifyToken(request)) {
			map2.put("type", "Error");
			map2.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.ROLE, null);
			return mapper.writeValueAsString(map2);
		}
		Map<String, Object> map = roleService.insertRole(request, role, arr);
		if (map.get("type") == "Success") {
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001, arr), ConstantStr.ROLE,
					role.getRoleId(), getUser());
		}
		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 修改角色
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:44:30
	 * @param role
	 * @param request
	 * @param permissionsIds
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Role:manager")
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String update(Role role, HttpServletRequest request, String permissionsIds,
			HttpServletResponse response) throws Exception {
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "角色" };
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.ROLE, null);
			return o.writeValueAsString(map);
		}
		map.put("type", "Error");
		map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
		boolean result = false;
		editAttr(role);
		result = roleService.updateRole(permissionsIds, role);
		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr), ConstantStr.ROLE,
					role.getRoleId(), getUser());
		}
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 删除角色
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:44:40
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Role:manager")
	@RequestMapping(value = "/deleteRole", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteRole(Role role, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ObjectMapper o = new ObjectMapper();

		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "角色" };
		map.put("type", "Error");
		map.put("Msg", ComDefine.getMsg(ConstantStr.INFO200129));

		// 删除角色前先判断该角色是否被用户使用到 如果有值不能删除
		boolean isHave = userRoleService.selectUserRoleByRoleId(role.getRoleId());
		if (isHave) {
			return o.writeValueAsString(map);
		}

		boolean result = false;
		editAttr(role);
		result = roleService.deleteRole(role);

		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr), ConstantStr.ROLE,
					role.getRoleId(), getUser());
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 添加用户共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void addAttr(Role _temp) {
		Long userId = getUserId();
		// _temp.setStatus(ConstantStr.QY_FLAG);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag("0");
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(Role _temp) {
		Role original = roleService.selectRole(_temp.getRoleId());
		Long userId = getUserId();
		_temp.setUpdEac(original.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);

	}

	/**
	 * 封装用插入日志的方法
	 * 
	 * @Title
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年8月10日 下午4:00:42
	 * @param type
	 *            操作类型
	 * @param log
	 *            日志内容
	 * @param arr
	 *            操作的内容头
	 * @param table
	 *            表名
	 * @param id
	 *            数据ID
	 */
	private void insertLog(String type, String log, Object[] arr, String table, Long id) {
		syslogService.addLog(type, ComDefine.getMsg(log, arr), table, id, getUser());
	}

}
