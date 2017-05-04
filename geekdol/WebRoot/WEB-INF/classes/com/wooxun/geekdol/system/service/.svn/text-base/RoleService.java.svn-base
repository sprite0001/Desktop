package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soft863.dolphin.common.CrudService;
import com.soft863.dolphin.model.Permission;
import com.soft863.dolphin.model.Role;
import com.wooxun.geekdol.system.vo.RoleVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 小区业务类 ========================================================== No
 *           修改人员 修改日期 描述 1. 万通 2016年7月18日 上午10:07:49 创建
 *           ==========================================================
 * 
 */
public interface RoleService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description:新增角色
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:24:16
	 * @param role
	 */
	public Map<String, Object> insertRole(HttpServletRequest request, Role role, Object[] arr);

	/**
	 * @Title
	 * @Description 根据角色id查到角色
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:26:05
	 * @param id
	 * @return
	 */
	public Role selectRole(Long id);

	/**
	 * 
	 * @Title
	 * @Description 修改角色
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午7:44:22
	 * @param permissionsIds
	 *            role
	 * @return true:更新成功;false:更新失败
	 */
	public boolean updateRole(String permissionsIds, Role role);

	/**
	 * 
	 * @Title
	 * @Description 删除角色
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午3:49:03
	 * @param role
	 * @return
	 */
	public boolean deleteRole(Role role);

	/**
	 * 
	 * @Title
	 * @Description:后台查询角色列表
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchRoleVo
	 * @return
	 */
	public List<Role> queryRoleByParams(RoleVo searchRoleVo);

	/**
	 * 
	 * @Title
	 * @Description:后台查询角色条数
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param searchRoleVo
	 * @return
	 */
	public Long queryCountByParams(RoleVo searchRoleVo);

	/**
	 * 
	 * @Title
	 * @Description:根据角色名字查到角色
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param searchRoleVo
	 * @return
	 */
	public Long findRoleByName(Role role);

	/**
	 * 
	 * @Title
	 * @Description:根据角色查该角色所拥有的权限
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param role
	 * @return
	 */
	public List<Permission> findPermissionBy(Long roleId);

}
