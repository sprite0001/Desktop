package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.CountySuggestStoreToperson;
import com.wooxun.geekdol.hmedia.vo.CountySuggestStoreVo;

public interface CountySuggestStoreTopersonMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 根据源id查询对应信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 下午2:30:06
	 * @param countySuggestStoreId
	 * @return
	 */
	List<CountySuggestStoreToperson> selectBySourceId(Long countySuggestStoreId);

	/**
	 * @Title
	 * @Description 批量插入对应信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 下午2:30:35
	 * @param param
	 * @return
	 */
	int insertBatch(Map<String, Object> param);
	
	/**
	 * @Title
	 * @Description 查询符合条件的本区推荐周边店总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:54:58
	 * @param param
	 * @return
	 */
	Long selectCountySuggestStoreListCount(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 查询符合条件的本区推荐周边店分页列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:55:44
	 * @param param
	 * @return
	 */
	List<CountySuggestStoreVo> selectCountySuggestStoreList(
			Map<String, Object> param);
    
}