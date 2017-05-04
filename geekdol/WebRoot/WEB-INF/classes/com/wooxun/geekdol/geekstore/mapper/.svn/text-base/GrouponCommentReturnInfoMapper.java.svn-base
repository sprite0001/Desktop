package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GrouponCommentReturnInfo;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentReturnInfoVo;

public interface GrouponCommentReturnInfoMapper <T extends Serializable> extends CrudMapper<T>{
    
	/**
	 * 
	 * @Title
	 * @Description 根据评论id查找回复
	 * @author:YK
	 * @CreateDate:2016年8月5日 下午3:26:45
	 * @param commentId
	 * @return List
	 */
	public List<GrouponCommentReturnInfo> selectByCommentId(Long commentId);
	
	/**
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年8月5日 下午5:52:35
	 * @param grouponCommentReturnInfo
	 * @return Long
	 */
	public Long getGrouponCommentReturnInfoCount(GrouponCommentReturnInfo grouponCommentReturnInfo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查找评论的回复列表
	 * @author:YK
	 * @CreateDate:2016年8月8日 上午10:26:37
	 * @param grouponCommentReturnInfoVo
	 * @return List
	 */
	public List<GrouponCommentReturnInfoVo> queryByParams(GrouponCommentReturnInfoVo grouponCommentReturnInfoVo);
}