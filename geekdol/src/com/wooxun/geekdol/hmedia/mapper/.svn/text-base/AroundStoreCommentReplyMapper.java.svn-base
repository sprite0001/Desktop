package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundStoreCommentReply;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentReplyVo;

public interface AroundStoreCommentReplyMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 根据评论Id删除
	 * @author:kangtianyu
	 * @CreateDate:2016年8月29日 下午2:13:56
	 * @param id
	 * @return
	 */
	int deleteByAroundCommentId(Long id);

	/**
	 * @Title
	 * @Description 获取周边店子评论个数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月29日 下午2:14:19
	 * @param aroundStoreCommentReplyVo
	 * @return
	 */
	Long selectAroundStoreCommentReplyListCount(
			AroundStoreCommentReplyVo aroundStoreCommentReplyVo);

	/**
	 * @Title
	 * @Description 获取周边店子评论列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月29日 下午2:14:34
	 * @param aroundStoreCommentReplyVo
	 * @return
	 */
	List<AroundStoreCommentReplyVo> selectAroundStoreCommentReplyList(
			AroundStoreCommentReplyVo aroundStoreCommentReplyVo);

	/**
	 * @Title
	 * @Description 更新状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月29日 下午2:14:46
	 * @param aroundStoreCommentReply
	 * @return
	 */
	int updateByAroundStoreCommentId(
			AroundStoreCommentReply aroundStoreCommentReply);
    
}