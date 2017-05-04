package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.IntimateVillage;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsVo;

public interface IntimateVillageMapper<T extends Serializable> extends CrudMapper<T> {
	public void insertBatch(List<IntimateVillage> list);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查找相关的小区
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:41:26
	 * @param map
	 * @return
	 */
	public List<IntimateVillage> selectIntimateVillageByIntimateNewsId(Long id);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id删除关系表所有相关的数据
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 上午11:11:55
	 * @param intimateNewsId
	 * @return
	 */
	public int deleteByIntimateNewsId(Long intimateNewsId);

	/**
	 * 
	 * @Title
	 * @Description 查询贴心报评价列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午6:08:31
	 * @param intimateNewsVo
	 * @return
	 */
	public Long selectIntimateNewsCommentListCount(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title 查询贴心报评价数量
	 * @Description
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午6:08:51
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNewsVo> selectIntimateNewsCommentList(IntimateNewsVo intimateNewsVo);

}