package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.ActivityCollectionVillage;

public interface ActivityCollectionVillageMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * 
	 * @Title
	 * @Description 批量插入活动汇小区关系表
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 上午9:44:57
	 * @param list
	 */
	public void insertBatch(List<ActivityCollectionVillage> list);

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id查找相关的小区
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:41:26
	 * @param map
	 * @return
	 */
	public List<ActivityCollectionVillage> selectActivityCollectionVillageByActivityCollectionId(Map<String, Object> map);

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id删除关系表所有相关的数据
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 上午11:11:55
	 * @param activityCollectionId
	 * @return
	 */
	public int deleteByActivityCollectionId(Long activityCollectionId);

}