package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.CooperationStoreVillage;

public interface CooperationStoreVillageMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title
	 * @Description 批量插入合作店小区关系
	 * @author:YK
	 * @CreateDate:2016年8月4日 上午9:53:06
	 * @param list
	 * @return int
	 */
	public int insertBatch(List<CooperationStoreVillage> list);
	/**
	 * 
	 * @Title
	 * @Description 根据店铺id删除关系表所有相关的数据
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午1:31:12
	 * @param storeId
	 * @return int
	 */
	public int deleteByStoreId(Long storeId);
	
	/**
	 * 
	 * @Title
	 * @Description 根据合作店铺id查找相关的小区
	 * @author:YK
	 * @CreateDate:2016年7月28日 上午11:25:50
	 * @return List
	 */
	public List<CooperationStoreVillage> selectCooperationStoreVillageByStoreId(Map<String,Object> map);
}