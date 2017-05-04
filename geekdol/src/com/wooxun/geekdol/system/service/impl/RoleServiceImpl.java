package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.soft863.dolphin.model.Permission;
import com.soft863.dolphin.model.Role;
import com.soft863.dolphin.model.RolePermission;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.mapper.RoleMapper;
import com.wooxun.geekdol.system.mapper.RolePermissionMapper;
import com.wooxun.geekdol.system.service.RoleService;
import com.wooxun.geekdol.system.vo.RoleVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 角色 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年7月18日 上午10:08:27 创建
 *           ==========================================================
 * 
 */

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService<Role> {

	private RoleMapper<Role> roleMapper;

	@Autowired
	private RolePermissionMapper<RolePermission> rolePermissionMapper;

	@Autowired
	public RoleServiceImpl(RoleMapper<Role> roleMapper) {
		super(roleMapper);
		this.roleMapper = roleMapper;
	}

	@Override
	public Map<String, Object> insertRole(HttpServletRequest request, Role role, Object[] arr) {

		Map<String, Object> map = new HashMap<String, Object>();

		String[] permiStrings = request.getParameterValues("checkbox"); // 勾选的权限
         if(permiStrings==null)
         {
             map.put("type", "Error");
             map.put("msg", ComDefine.getMsg(ConstantStr.INFO100099, arr));
             return map;
         }
		// 验证是否有相同名字的角色
		Long roleCount = findRoleByName(role);
		if (roleCount > 0) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200025, arr));
			return map;
		}
		int res = roleMapper.insert(role);
		if (res > 0) {
			// 保存角色权限关系表
			List<RolePermission> list = new ArrayList<RolePermission>();
			for (int i = 0; i < permiStrings.length; i++) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getRoleId());
				rolePermission.setPerId(Long.valueOf(permiStrings[i]));
				list.add(rolePermission);
			}
			rolePermissionMapper.insertBatch(list);
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
		}
		return map;
	}

	@Override
	public Role selectRole(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateRole(String permissionsIds, Role role) {
		boolean result = false;
		// 修改角色表
		int i = roleMapper.update(role);
		if (i > 0) {
			// 删除原来的数据表
			rolePermissionMapper.deleteByRoleId(role.getRoleId());
			String[] ids = permissionsIds.split(",");
			// 重新添加选中的权限
			List<RolePermission> list = new ArrayList<RolePermission>();
			for (int j = 0; j < ids.length; j++) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getRoleId());
				rolePermission.setPerId(Long.valueOf(ids[j]));
				list.add(rolePermission);
			}
			rolePermissionMapper.insertBatch(list);
			result = true;
		}
		return result;
	}

	@Override
	public List<Role> queryRoleByParams(RoleVo searchRole) {
		return roleMapper.selectRoleList(searchRole);
	}

	@Override
	public Long queryCountByParams(RoleVo searchRole) {
		return roleMapper.selectCount(searchRole);
	}

	@Override
	public Long findRoleByName(Role role) {

		return roleMapper.findRoleByName(role);
	}

	@Override
	public List<Permission> findPermissionBy(Long roleId) {

		return roleMapper.selectPermission(roleId);
	}

	@Override
	public boolean deleteRole(Role role) {
		boolean result = false;
		// 修改角色表
		int i = roleMapper.update(role);
		// 删除原来的数据表
		rolePermissionMapper.deleteByRoleId(role.getRoleId());
		if (i > 0) {
			result = true;
		}
		return result;
	}
}
