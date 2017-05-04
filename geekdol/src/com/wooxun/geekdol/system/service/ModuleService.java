package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.soft863.dolphin.model.Module;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 小区业务类
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年7月18日  上午10:07:49 		创建
 *==========================================================
 * 
 */
public interface ModuleService <T extends Serializable> extends CrudService<T>{
	
	
	/**
	 * 
	 * @Title
	 * @Description:查询出所有的除吉客多管理应用系统以外的模块
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param 
	 * @return
	 */
	public List<Module> selectALlModules();

}

