package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GrouponDemandCommentMapper;
import com.wooxun.geekdol.geekstore.mapper.GrouponDemandCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.mapper.GrouponDemandMapper;
import com.wooxun.geekdol.geekstore.model.GrouponDemand;
import com.wooxun.geekdol.geekstore.model.GrouponDemandComment;
import com.wooxun.geekdol.geekstore.model.GrouponDemandCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.GrouponDemandService;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentReturnInfoVo;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentVo;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandVo;

/**
 * @Title 吉客店管理
 * @Description 我要团管理
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月1日  下午2:31:21 		创建
 *==========================================================
 * 
 */
@Service
public class GrouponDemandServiceImpl extends CrudServiceImpl<GrouponDemand> 
	implements GrouponDemandService<GrouponDemand>{

	@Autowired
	private GrouponDemandMapper<GrouponDemand> grouponDemandMapper;
	@Autowired
	private GrouponDemandCommentMapper<GrouponDemandComment> grouponDemandCommentMapper;
	@Autowired
	private GrouponDemandCommentReturnInfoMapper<GrouponDemandCommentReturnInfo> GrouponDemandCommentReturnInfoMapper;
	@Autowired
	public GrouponDemandServiceImpl(GrouponDemandMapper<GrouponDemand> grouponDemandMapper) {
		super(grouponDemandMapper);
		this.grouponDemandMapper = grouponDemandMapper;
	}
	@Override
	public List<GrouponDemandVo> findAllGrouponDemand(
			GrouponDemandVo grouponDemandVo) {
		return grouponDemandMapper.findAllGrouponDemand(grouponDemandVo);
	}
	@Override
	public Long findAllGrouponDemandCount(GrouponDemandVo grouponDemandVo) {
		return grouponDemandMapper.findAllGrouponDemandCount(grouponDemandVo);
	}
	@Override
	public GrouponDemand findByIdGrouponDemand(Long id) {
		return grouponDemandMapper.selectByPrimaryKey(id);
	}
	@Override
	public GrouponDemandVo findGrouponDemand(GrouponDemandVo grouponDemandVo) {
		return grouponDemandMapper.findGrouponDemand(grouponDemandVo);
	}
	@Override
	public List<GrouponDemandCommentVo> findComments(
			GrouponDemandCommentVo grouponDemandCommentVo) {
		return grouponDemandCommentMapper.findComments(grouponDemandCommentVo);
	}
	@Override
	public List<GrouponDemandCommentReturnInfoVo> findReturnInfo(
			GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo) {
		return GrouponDemandCommentReturnInfoMapper.findReturnInfo(grouponDemandCommentReturnInfoVo);
	}
	@Override
	public GrouponDemandCommentVo findOneComments(GrouponDemandCommentVo grouponDemandCommentVo) {
		return grouponDemandCommentMapper.findOneComments(grouponDemandCommentVo);
	}
	@Override
	public Long findReturnInfoCount(
			GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo) {
		return GrouponDemandCommentReturnInfoMapper.findReturnInfoCount(grouponDemandCommentReturnInfoVo);
	}
	@Override
	public GrouponDemandComment findOneCommentsEasy(Long id) {
		return grouponDemandCommentMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteGrouponDemandComment(GrouponDemandComment grouponDemandComment) {
		//物理删除评论回复
		GrouponDemandCommentReturnInfoMapper.deleteSelective(grouponDemandComment.getId());
		
		//逻辑删除评论回复
//		GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo = new GrouponDemandCommentReturnInfoVo();
//		grouponDemandCommentReturnInfoVo.setCommentId(grouponDemandComment.getId());
//		List<GrouponDemandCommentReturnInfo> commentReturnInfoList = new ArrayList<GrouponDemandCommentReturnInfo>();
//		grouponDemandCommentReturnInfoVo.setIllegalType(ConstantStr.ILLEGALSTATE);
//		List<GrouponDemandCommentReturnInfoVo> returnInfoList = GrouponDemandCommentReturnInfoMapper.findReturnInfo(grouponDemandCommentReturnInfoVo);
//		if(returnInfoList!=null){
//			//把参数手动封装在Map中  
//		    Map<String, Object> map = new HashMap<String, Object>();  
//			for(GrouponDemandCommentReturnInfoVo CommentReturnInfoVo : returnInfoList){
//				GrouponDemandCommentReturnInfo grouponDemandCommentReturnInfo = new GrouponDemandCommentReturnInfo();
//				grouponDemandCommentReturnInfo.setDelFlag(ConstantStr.DELETE_Y);
//				grouponDemandCommentReturnInfo.setCommentId(grouponDemandComment.getId());
//				grouponDemandCommentReturnInfo.setUpdEac(CommentReturnInfoVo.getUpdEac()+1);
//				grouponDemandCommentReturnInfo.setUpdYmdhms(new Date());
//				grouponDemandCommentReturnInfo.setUpdId(grouponDemandComment.getUpdId());
//				commentReturnInfoList.add(grouponDemandCommentReturnInfo);
//			}
//			 map.put("commentReturnInfoList", commentReturnInfoList);
//			GrouponDemandCommentReturnInfoMapper.updateSelective(map);
//		}
		//根据评论ID逻辑删除评论信息
		return grouponDemandCommentMapper.updateByPrimaryKeySelective(grouponDemandComment);
	}
	@Override
	public List<GrouponDemandVo> findCensus(GrouponDemandVo grouponDemandVo) {
		return grouponDemandMapper.census(grouponDemandVo);
	}	
}
