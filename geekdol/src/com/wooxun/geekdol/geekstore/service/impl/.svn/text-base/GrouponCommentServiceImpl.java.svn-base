package com.wooxun.geekdol.geekstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.GrouponCommentMapper;
import com.wooxun.geekdol.geekstore.mapper.GrouponCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.model.GrouponComment;
import com.wooxun.geekdol.geekstore.model.GrouponCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.GrouponCommentService;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月5日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月5日  上午11:01:51 		创建
 *==========================================================
 * 
 */
@Service
public class GrouponCommentServiceImpl extends CrudServiceImpl<GrouponComment> 
	implements GrouponCommentService<GrouponComment> {
	
	private GrouponCommentMapper<GrouponComment> grouponCommentMapper;
	@Autowired
	private GrouponCommentReturnInfoMapper<GrouponCommentReturnInfo> grouponCommentReturnInfoMapper;
	
	@Autowired
    public GrouponCommentServiceImpl(GrouponCommentMapper<GrouponComment> grouponCommentMapper) {
        super(grouponCommentMapper);
        this.grouponCommentMapper=grouponCommentMapper;
    }

	@Override
	public List<GrouponCommentVo> selectByGrouponGoodsId(GrouponCommentVo grouponCommentVo) {
		return grouponCommentMapper.selectByGrouponGoodsId(grouponCommentVo);
	}

	@Override
	public GrouponComment selectById(Long id) {
		return grouponCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean deleteGrouponComment(GrouponComment grouponComment) {
		grouponComment.setDelFlag(ConstantStr.DELETE_Y);
		int i = grouponCommentMapper.updateByPrimaryKeySelective(grouponComment);
		//修改评论的回复共同属性
		for(GrouponCommentReturnInfo grouponCommentReturnInfo:grouponComment.getGrouponCommentReturnInfos()){
			grouponCommentReturnInfo.setDelFlag(ConstantStr.DELETE_Y);
			grouponCommentReturnInfo.setUpdId(grouponComment.getUpdId());
			grouponCommentReturnInfo.setUpdYmdhms(new Date());
			grouponCommentReturnInfo.setUpdEac(grouponCommentReturnInfo.getUpdEac()+1);
			grouponCommentReturnInfoMapper.updateByPrimaryKeySelective(grouponCommentReturnInfo);
		}
		return i>0?true:false;
	}
	
	@Override
	public GrouponCommentVo selectByParams(GrouponCommentVo grouponCommentVo){
		return grouponCommentMapper.selectByParams(grouponCommentVo);
	}
}
