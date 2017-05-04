package com.wooxun.geekdol.system.service;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudService;

public interface UserRoleService<T extends Serializable> extends CrudService<T> {

	// 根据角色id查询出是否有关联的用户
	public boolean selectUserRoleByRoleId(Long roleId);
}
