package com.wooxun.geekdol.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.model.RolePermission;
import com.wooxun.geekdol.system.mapper.RolePermissionMapper;
import com.wooxun.geekdol.system.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService<RolePermission> {
	@Autowired
	private RolePermissionMapper<RolePermission> rolePermissionMapper;
	
	@Override
	public void save(RolePermission rolePermission) {
		rolePermissionMapper.insert(rolePermission);
	}
	
	@Override
	public void delete(RolePermission rolePermission) {
		rolePermissionMapper.delete(rolePermission);
	}

}
