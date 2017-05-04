package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreCommentReply;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentReplyVo;

public interface AroundSuggestStoreCommentReplyMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 根据本网格推荐周边店评论id更新数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午2:44:46
	 * @param aroundSuggestStoreCommentReply
	 * @return
	 */
	int updateByAroundSuggestStoreCommentId(
			AroundSuggestStoreCommentReply aroundSuggestStoreCommentReply);

	/**
	 * @Title
	 * @Description 查找符合条件的本网格推荐周边店评论的评论的总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午2:57:56
	 * @param aroundSuggestStoreCommentReplyVo
	 * @return
	 */
	Long selectAroundSuggestStoreCommentReplyListCount(
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo);

	/**
	 * @Title
	 * @Description 查找符合条件的本网格推荐周边店评论的评论的分页列表数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午2:59:05
	 * @param aroundSuggestStoreCommentReplyVo
	 * @return
	 */
	List<AroundSuggestStoreCommentReplyVo> selectAroundSuggestStoreCommentReplyList(
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo);
	
}