package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.ServiceClassificMapper;
import com.wooxun.geekdol.geekstore.model.ServiceClassific;
import com.wooxun.geekdol.geekstore.service.ServiceClassificService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月23日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月23日  上午12:23:37 		创建
 *==========================================================
 * 
 */
@Service
public class ServiceClassificServiceImpl extends CrudServiceImpl<ServiceClassific> 
	implements ServiceClassificService<ServiceClassific> {
	
	private ServiceClassificMapper<ServiceClassific> serviceClassificMapper;
	@Autowired
	public ServiceClassificServiceImpl(ServiceClassificMapper<ServiceClassific> serviceClassificMapper) {
		super(serviceClassificMapper);
		this.serviceClassificMapper = serviceClassificMapper;
	}

	@Override
	public List<ServiceClassific> queryServiceClassificByType() {
		ServiceClassific serviceClassific = new ServiceClassific();
		return serviceClassificMapper.queryServiceClassificByType(serviceClassific);
	}
}
