package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.ServiceClassific;

public interface ServiceClassificMapper <T extends Serializable> extends CrudMapper<T>{
	
	public List<ServiceClassific> queryServiceClassificByType(ServiceClassific serviceClassific);
}