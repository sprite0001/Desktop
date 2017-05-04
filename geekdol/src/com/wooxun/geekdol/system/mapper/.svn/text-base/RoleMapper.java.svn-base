package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.soft863.dolphin.model.Permission;
import com.soft863.dolphin.model.Role;
import com.wooxun.geekdol.system.vo.RoleVo;

/**
 * Mybatis通过该接口和RoleMapper.xml文件完成对数据库的操作，
 * @param <T>
 *            泛型类型。
 */
public interface RoleMapper<T extends Serializable> extends CrudMapper<T>{
	
	  public List<Role> selectRoleList(RoleVo searchRole);
	    
	  public Long selectCount(RoleVo searchRole);
	  
	  public Long findRoleByName(Role role);
	  
	  //查询出角色对应的权限
	  public List<Permission> selectPermission(Long roleId);
	  
	  //修改角色
	  public int update(Role role);
	  
}
