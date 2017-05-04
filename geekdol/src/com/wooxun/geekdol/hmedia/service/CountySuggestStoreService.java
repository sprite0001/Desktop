package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.CountySuggestStore;
import com.wooxun.geekdol.hmedia.model.CountySuggestStoreToperson;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
import com.wooxun.geekdol.hmedia.vo.CountySuggestStoreVo;

/**
 * 本区推荐周边店Service
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
* 1. 	 kangtianyu	2016年8月9日  上午11:40:36 		创建
*==========================================================
*
 */
public interface CountySuggestStoreService <T extends Serializable> extends CrudService<T>{

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：查找符合条件的本区推荐店个数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:43:57
	 * @param param
	 * @return
	 */
	public Long findCountySuggestStoreListCount(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：查找符合条件的本区推荐店信息列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:44:04
	 * @param param
	 * @return
	 */
	public List<CountySuggestStoreVo> findCountySuggestStoreList(
			Map<String, Object> param);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：根据关系表主键查询关系表信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 下午2:39:27
	 * @param id
	 * @return
	 */
	public CountySuggestStoreToperson findCountySuggestStoreTopersonById(Long id);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：更改本区推荐周边店的关系表信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 下午2:52:39
	 * @param countySuggestStoreToperson
	 * @return
	 */
	public boolean updateCountySuggestStoreToperson(
			CountySuggestStoreToperson countySuggestStoreToperson);

	/**
	 * @Title
	 * @Description 本区推荐周边店管理：根据本区推荐周边店的关系表主键获取本区推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 上午11:15:22
	 * @param id
	 * @return
	 */
	public CountySuggestStore findCountySuggestStoreById(Long id);

	/**
	 * @Title
	 * @Description 接受推荐,插入相关数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午3:04:04
	 * @param aroundSuggestStoreVo
	 * @param flag
	 * @param sid
	 * @param countySuggestStoreToPersonId 
	 * @return
	 */
	public Map<String, Object> acceptSuggest(AroundSuggestStoreVo aroundSuggestStoreVo,
			String flag, Long sid, Long countySuggestStoreToPersonId);

}
