package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.soft863.dolphin.model.UserRole;
import com.wooxun.geekdol.system.mapper.UserRoleMapper;
import com.wooxun.geekdol.system.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends CrudServiceImpl<UserRole> implements UserRoleService<UserRole> {
	private UserRoleMapper<UserRole> userRoleMapper;

	@Autowired
	public UserRoleServiceImpl(UserRoleMapper<UserRole> userRoleMapper) {
		super(userRoleMapper);
		this.userRoleMapper = userRoleMapper;
	}

	@Override
	public boolean selectUserRoleByRoleId(Long roleId) {
		List<UserRole> list = userRoleMapper.selectUserRoleByRoleId(roleId);
		return list.size() > 0 ? true : false;
	}

}
