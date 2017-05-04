package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.CountySuggestStore;

/**
 * 本区推荐周边店mapper
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月9日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月9日  上午11:54:46 		创建
*==========================================================
*
 */
public interface CountySuggestStoreMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 根据来源id查询本区推荐周边店id
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:54:20
	 * @param countySuggestStore
	 * @return
	 */
	Long selectCountySuggestStoreIdBySourceId(CountySuggestStore countySuggestStore);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：根据本区推荐周边店的关系表主键获取本区推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 上午11:15:22
	 * @param id
	 * @return
	 */
	CountySuggestStore findCountySuggestStoreById(Long id);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：根据源id和推荐类型删除本区推荐周边店数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月18日 上午10:11:20
	 * @param temp
	 * @return
	 */
	int deleteSelective(CountySuggestStore temp);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：根据参数查找本区推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月19日 上午9:33:11
	 * @param temp
	 * @return
	 */
	List<CountySuggestStore> selectCountySuggestStoreByParam(
			CountySuggestStore temp);
    
}