package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.soft863.dolphin.model.RolePermission;

/**
 * Mybatis通过该接口和RolePermissionMapper.xml文件完成对数据库的操作，
 * @param <T>
 *            泛型类型。
 */
public interface RolePermissionMapper<T extends Serializable> extends CrudMapper<T>{
	
	public void insert(RolePermission rolePermission);
	
	public void delete(RolePermission rolePermission);

	public void insertBatch(List<RolePermission> list);

	public void deleteByRoleId(Long roleId);

}
