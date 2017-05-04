package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.system.model.Village;

/**
* @Title
* @Description 周边店小区对应关系mapper
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月3日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月3日  下午4:52:23 		创建
*==========================================================
*
 */
public interface AroundStoreVillageMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据周边店id查询对应小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午12:08:40
	 * @param id
	 * @return
	 */
	public List<AroundStoreVillage> selectAroundStoreVillageByAroundStoreId(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据周边店id删除对应表信息
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午12:09:05
	 * @param storeId
	 * @return
	 */
	public int deleteByAroundStoreId(Long storeId);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：周边店与小区关系批量插入
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 下午4:51:55
	 * @param param
	 * @return
	 */
	public int insertBatch(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据小区查找周边店和小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午9:57:59
	 * @param villageList
	 * @return
	 */
	public List<AroundStoreVillage> selectAroundStoreVillageByVillage(
			List<Village> villageList);

	/**
	 * 
	 * @Title 周边店管理：根据小区id查询与小区对应的周边店id
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午9:55:28
	 * @param villageId
	 * @return
	 */
	public List<AroundStoreVillage> selectAroundStoreVillageByVillageId(
			Long villageId);
	
}