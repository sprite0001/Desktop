package com.wooxun.geekdol.geekstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsCommentMapper;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsComment;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.DistributionGoodsCommentService;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 即可送评论信息
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午4:14:15 		创建
 *==========================================================
 * 
 */
@Service
public class DistributionGoodsCommentServiceImpl extends CrudServiceImpl<DistributionGoodsComment> 
	implements DistributionGoodsCommentService<DistributionGoodsComment>{
	
	private DistributionGoodsCommentMapper<DistributionGoodsComment> distributionGoodsCommentMapper;
	@Autowired
	private DistributionGoodsCommentReturnInfoMapper<DistributionGoodsCommentReturnInfo> distributionGoodsCommentReturnInfoMapper;
	@Autowired
	public DistributionGoodsCommentServiceImpl(
			DistributionGoodsCommentMapper<DistributionGoodsComment> distributionGoodsCommentMapper) {
		super(distributionGoodsCommentMapper);
		this.distributionGoodsCommentMapper = distributionGoodsCommentMapper;
	}
	
	@Override
	public DistributionGoodsCommentVo findDistributionGoodsComment(
			DistributionGoodsCommentVo distributionGoodsCommentVo) {
		return distributionGoodsCommentMapper.findOneComments(distributionGoodsCommentVo);
	}
	
	@Override
	public int deleteDistributionGoodsComment(
			DistributionGoodsComment distributionGoodsComment) {
		// 删除评论
		int i = distributionGoodsCommentMapper.updateByPrimaryKeySelective(distributionGoodsComment);
		// 删除评论的回复
		distributionGoodsCommentReturnInfoMapper.deleteBycommentId(distributionGoodsComment.getId());
		return i;
	}
	@Override
	public DistributionGoodsComment findOneCommentsEasyDG(Long id) {
		return distributionGoodsCommentMapper.selectByPrimaryKey(id);
	}

}
