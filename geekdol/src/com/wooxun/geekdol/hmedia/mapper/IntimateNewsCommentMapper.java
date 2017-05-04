package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.IntimateNewsComment;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentVo;

public interface IntimateNewsCommentMapper<T extends Serializable> extends CrudMapper<T> {

	public Long selectIntimateNewsCommentListCount(IntimateNewsCommentVo intimateNewsCommentVo);

	public List<IntimateNewsCommentVo> selectIntimateNewsCommentList(IntimateNewsCommentVo intimateNewsCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 后台使用 根据评论id 查到评论详情
	 * @author:王肖东
	 * @CreateDate:2016年8月17日 下午4:48:40
	 * @param id
	 * @return
	 */
	public IntimateNewsCommentVo selectIntimateNewsCommentDetail(Long id);

	/**
	 * 
	 * @Title
	 * @Description app查询一级评论数据
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午6:14:04
	 * @param intimateNewsCommentVo
	 * @return
	 */
	public List<IntimateNewsComment> selectCommentList(IntimateNewsCommentVo intimateNewsCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 查询当前已经评论过的记录
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	public List<IntimateNewsComment> selectIntimateNewsCommentByDay(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description app使用 根据评论id 查到评论详情
	 * @author:王肖东
	 * @CreateDate:2016年8月17日 下午4:48:40
	 * @param id
	 * @return
	 */
	public IntimateNewsCommentVo findAppIntimateNewsCommentDetail(Long id);

	/**
	 * 
	 * @Title
	 * @Description app使用 查询当天是否对贴心报一级评论进行过点赞倒赞行为
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	//public IntimateNewsComment selectCommentByDay(Map<String, Object> param);

}