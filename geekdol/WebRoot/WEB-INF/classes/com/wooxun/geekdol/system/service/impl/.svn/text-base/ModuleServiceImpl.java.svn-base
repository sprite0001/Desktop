package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.soft863.dolphin.model.Module;
import com.wooxun.geekdol.system.mapper.ModuleMapper;
import com.wooxun.geekdol.system.service.ModuleService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified  模块service
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年7月18日  上午10:08:27 		创建
 *==========================================================
 * 
 */

@Service
public class ModuleServiceImpl extends CrudServiceImpl<Module> implements ModuleService<Module>{

	private ModuleMapper<Module> moduleMapper;
	@Autowired
	public ModuleServiceImpl(ModuleMapper<Module> moduleMapper) {
		super(moduleMapper);
		this.moduleMapper=moduleMapper;
	}

	@Override
	public List<Module> selectALlModules() {
		return moduleMapper.selectAll();
	}

}
