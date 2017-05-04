package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundStoreComment;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentVo;

public interface AroundStoreCommentMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 获取周边店评论总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午5:23:05
	 * @param aroundStoreCommentVo
	 * @return
	 */
	Long selectAroundStoreCommentListCount(AroundStoreCommentVo aroundStoreCommentVo);

	/**
	 * @Title
	 * @Description 获取周边店评论分页列表数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午5:23:32
	 * @param aroundStoreCommentVo
	 * @return
	 */
	List<AroundStoreCommentVo> selectAroundStoreCommentList(AroundStoreCommentVo aroundStoreCommentVo);

	/**
	 * @Title
	 * @Description 查询评论详情
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午5:23:50
	 * @param id
	 * @return
	 */
	AroundStoreCommentVo selectAroundStoreCommentDetail(Long id);

	/**
	 * @Title
	 * @Description app查找评论列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午5:24:15
	 * @param aroundStoreCommentVo
	 * @return
	 */
	List<AroundStoreComment> selectCommentList(AroundStoreCommentVo aroundStoreCommentVo);

	/**
	 * 
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月13日 上午11:54:06
	 * @param param
	 * @return
	 */
	List<AroundStoreComment> selectAroundStoreCommentByDay(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description app使用 查询当天是否对周边店一级评论进行过点赞倒赞行为
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	AroundStoreComment selectCommentByDay(Map<String, Object> param);

}