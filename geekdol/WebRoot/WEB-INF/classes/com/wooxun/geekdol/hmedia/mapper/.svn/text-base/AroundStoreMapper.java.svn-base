package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;

/**
 * @Title
 * @Description 周边店mapper
 * @version 1.0.0
 * @Author kangtianyu
 * @CreateDate 2016年8月3日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. kangtianyu 2016年8月3日 下午4:49:55 创建
 *           ==========================================================
 *
 */
public interface AroundStoreMapper<T extends Serializable> extends CrudMapper<T> {
	/**
	 * 
	 * @Title
	 * @Description 系统设置:小区查询中的周边店查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 上午11:36:48
	 * @param aroundStoreVo
	 * @return List
	 */
	public List<AroundStoreVo> findAllAroundStore(AroundStoreVo aroundStoreVo);

	/**
	 * 
	 * @Title
	 * @Description 系统设置:小区查询中的周边店总数查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午2:49:13
	 * @param aroundStoreVo
	 * @return Long
	 */
	public Long findAllAroundStoreCount(AroundStoreVo aroundStoreVo);

	/**
	 * @Title
	 * @Description 根据相关参数查询周边店总数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 下午4:47:54
	 * @param param
	 * @return
	 */
	public Long selectAroundStoreListCount(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据相关参数查询周边店分页列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 下午4:48:22
	 * @param param
	 * @return
	 */
	public List<AroundStoreVo> selectAroundStoreList(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据周边店主键id查询周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 下午4:48:59
	 * @param id
	 * @return
	 */
	public AroundStoreVo selectAroundStore(Long id);

	/**
	 * @Title
	 * @Description 根据主键id列表查询周边店列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午10:14:36
	 * @param param
	 * @return
	 */
	public List<AroundStore> selectAroundStoreByIdList(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 判断组织代码证是否存在
	 * @author:kangtianyu
	 * @param organizationCode
	 * @CreateDate:2016年8月17日 下午10:53:31
	 * @return
	 */
	public Integer findCountOrganizationCode(String organizationCode);

	/**
	 * 
	 * @Title
	 * @Description 判断当前用户是否对周边店进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	AroundStore selectAroundStoreByDay(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 查找店的状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月30日 上午10:39:47
	 * @param storeId
	 * @return
	 */
	public Integer selectStatus(Long storeId);

}