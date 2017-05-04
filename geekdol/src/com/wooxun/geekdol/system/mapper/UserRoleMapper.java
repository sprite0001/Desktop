package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.soft863.dolphin.model.UserRole;

/**
 * Mybatis通过该接口和UserRoleMapper.xml文件完成对数据库的操作，
 * 
 * @param <T>
 *            泛型类型。
 */
public interface UserRoleMapper<T extends Serializable> extends CrudMapper<T> {

	public void insert(UserRole userRole);

	public void delete(UserRole userRole);

	public List<UserRole> selectRoleIdByUserId(Long userId);

	// 根据角色id查询出中间表
	public List<UserRole> selectUserRoleByRoleId(Long roleId);

}
