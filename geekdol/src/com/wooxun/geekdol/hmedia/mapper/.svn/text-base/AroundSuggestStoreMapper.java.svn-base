package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;

/**
 * @Title
 * @Description 本网格推荐周边店Mapper
 * @version 1.0.0
 * @Author kangtianyu
 * @CreateDate 2016年8月4日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. Administrator 2016年8月4日 下午9:44:51 创建
 *           ==========================================================
 *
 */
public interface AroundSuggestStoreMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 本网格推荐周边店符合条件的分页列表查询
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午9:44:44
	 * @param param
	 * @return
	 */
	List<AroundSuggestStoreVo> selectAroundSuggestStoreList(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 本网格推荐周边店符合条件的总记录数查询
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午9:45:22
	 * @param param
	 * @return
	 */
	Long selectAroundSuggestStoreListCount(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据主键id查询本网格推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午11:15:31
	 * @param id
	 * @return
	 */
	AroundSuggestStoreVo selectAroundSuggestStore(Long id);

	/**
	 * @Title
	 * @Description 根据源记录id和推荐类型查询本网格推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午11:01:42
	 * @param id
	 * @return
	 */
	AroundSuggestStore selectAroundSuggestStoreByParam(AroundSuggestStore aroundSuggestStore);

	/**
	 * @Title
	 * @Description 根据主键id查询推荐店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午2:50:51
	 * @param param
	 * @return
	 */
	List<AroundSuggestStore> selectAroundSuggestStoreByIdList(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 判断组织代码证是否存在
	 * @author:kangtianyu
	 * @param organizationCode
	 * @CreateDate:2016年8月17日 下午10:53:54
	 * @return
	 */
	Integer findCountOrganizationCode(String organizationCode);

	/**
	 * 
	 * @Title
	 * @Description 判断当前用户是否对推荐店进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	AroundSuggestStore selectAroundSuggestStoreByDay(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 判断店状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月30日 上午10:46:39
	 * @param storeId
	 * @return
	 */
	Integer selectStatus(Long storeId);

}