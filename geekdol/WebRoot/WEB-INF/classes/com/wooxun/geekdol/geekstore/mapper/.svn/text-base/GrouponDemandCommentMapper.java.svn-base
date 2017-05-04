package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentVo;

public interface GrouponDemandCommentMapper <T extends Serializable> extends CrudMapper<T>{
    
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 评论人列表
	 * @author:田振兴
	 * @CreateDate:2016年8月2日 下午6:33:36
	 * @param grouponDemandCommentVo
	 * @return
	 */
	public List<GrouponDemandCommentVo> findComments(GrouponDemandCommentVo grouponDemandCommentVo);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 根据评论ID查询评论人的评论信息
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 上午10:36:50
	 * @param id
	 * @return
	 */
	public GrouponDemandCommentVo findOneComments(GrouponDemandCommentVo grouponDemandCommentVo);
	
}