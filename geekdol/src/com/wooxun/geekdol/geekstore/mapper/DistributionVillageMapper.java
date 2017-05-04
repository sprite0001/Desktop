package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.DistributionVillage;

public interface DistributionVillageMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 批量保存
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午5:48:42
	 * @param list
	 */
     public void insertBatch(List<DistributionVillage> list);
}