package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.system.model.Village;

public interface AroundSuggestStoreVillageMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 批量插入本区推荐周边店关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 下午8:32:07
	 * @param param
	 * @return
	 */
	public int insertBatch(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据小区id查询本网格下本小区的店推荐数量
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 上午10:06:08
	 * @param param
	 * @return
	 */
	public Integer selectCountByVillageId(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据本网格周边店id删除关系表中数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午12:42:15
	 * @param id
	 * @return
	 */
	public int deleteByAroundSuggestStoreId(Long id);

	/**
	 * @Title
	 * @Description 根据本网格周边店id查找对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:11:40
	 * @param id
	 * @return
	 */
	public List<AroundSuggestStoreVillage> selectAroundSuggestStoreVillageByASSId(
			Long id);

	/**
	 * @Title
	 * @Description 根据小区信息查询本网格推荐周边店与小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午10:36:11
	 * @param villageList
	 * @return
	 */
	public List<AroundSuggestStoreVillage> selectAroundSuggestStoreVillageByVillage(
			List<Village> villageList);

	/**
	 * @Title
	 * @Description 根据小区id查询推荐店与小区关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午2:43:32
	 * @param villageId
	 * @return
	 */
	public List<AroundSuggestStoreVillage> selectAroundSuggestStoreVillageByVillageId(
			Long villageId);
}