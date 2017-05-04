package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.ActivityCollectionCommentR;
import com.wooxun.geekdol.hmedia.vo.ActivityCollectionCommentReplyVo;

public interface ActivityCollectionCommentRMapper<T extends Serializable> extends CrudMapper<T> {

	//int deleteByActivityCollectionCommentId(Long id);

	public Long selectActivityCollectionCommentReplyListCount(
			ActivityCollectionCommentReplyVo activityCollectionCommentReplyVo);

	public List<ActivityCollectionCommentReplyVo> selectActivityCollectionCommentReplyList(
			ActivityCollectionCommentReplyVo activityCollectionCommentReplyVo);

	int updateByActivityCollectionCommentId(ActivityCollectionCommentR activityCollectionCommentReply);
}