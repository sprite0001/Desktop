package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreComment;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentVo;

/**
 * @Title
 * @Description 本网格推荐周边店评论的mapper
 * @version 1.0.0
 * @Author kangtianyu
 * @CreateDate 2016年8月5日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. Administrator 2016年8月5日 下午2:32:33 创建
 *           ==========================================================
 *
 */
public interface AroundSuggestStoreCommentMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 查找符合条件的本网格推荐周边店评论的总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午2:32:52
	 * @param aroundSuggestStoreCommentVo
	 * @return
	 */
	Long selectAroundSuggestStoreCommentListCount(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo);

	/**
	 * @Title
	 * @Description 查找符合条件的本网格推荐周边店评论分页后的列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午2:39:27
	 * @param aroundSuggestStoreCommentVo
	 * @return
	 */
	List<AroundSuggestStoreCommentVo> selectAroundSuggestStoreCommentList(
			AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo);

	/**
	 * @Title
	 * @Description 根据主键id查询本网格推荐周边店评论的信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午2:40:46
	 * @param id
	 * @return
	 */
	AroundSuggestStoreCommentVo selectAroundSuggestStoreCommentDetail(Long id);

	/**
	 * @Title
	 * @Description 根据参数获取推荐店评论
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午9:44:10
	 * @param aroundSuggestStoreCommentVo
	 * @return
	 */
	List<AroundSuggestStoreComment> selectCommentList(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo);

	/**
	 * @Title
	 * @Description 判断用户当天有没有评论过
	 * @author:kangtianyu
	 * @CreateDate:2016年8月13日 下午12:05:41
	 * @param param
	 * @return
	 */
	List<AroundSuggestStoreComment> selectAroundSuggestStoreCommentByDay(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description app使用 查询当天是否对推荐店一级评论进行过点赞倒赞行为
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	AroundSuggestStoreComment selectCommentByDay(Map<String, Object> param);

}