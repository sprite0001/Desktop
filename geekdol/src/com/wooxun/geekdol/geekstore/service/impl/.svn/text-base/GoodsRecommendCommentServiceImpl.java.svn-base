package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendCommentMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendComment;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendCommentService;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 新产品的评论信息
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午4:02:40 		创建
 *==========================================================
 * 
 */
@Service
public class GoodsRecommendCommentServiceImpl extends CrudServiceImpl<GoodsRecommendComment> 
		implements GoodsRecommendCommentService<GoodsRecommendComment>{
	@Autowired
	private GoodsRecommendCommentMapper<GoodsRecommendComment> goodsRecommendCommentMapper;
	@Autowired
	private GoodsRecommendCommentReturnInfoMapper<GoodsRecommendCommentReturnInfo> goodsRecommendCommentReturnInfoMapper;
	@Autowired
	public GoodsRecommendCommentServiceImpl(
			GoodsRecommendCommentMapper<GoodsRecommendComment> goodsRecommendCommentMapper) {
		super(goodsRecommendCommentMapper);
		this.goodsRecommendCommentMapper = goodsRecommendCommentMapper;
	}
	
	@Override
	public List<GoodsRecommendCommentVo> findGoodsRecommendComment(
			GoodsRecommendCommentVo goodsRecommendCommentVo) {
		return goodsRecommendCommentMapper.findgoodsCommment(goodsRecommendCommentVo);
	}
	
	@Override
	public GoodsRecommendCommentVo findOneCommentsGR(
			GoodsRecommendCommentVo goodsRecommendCommentVo) {
		return goodsRecommendCommentMapper.findOneComments(goodsRecommendCommentVo);
	}
	
	@Override
	public Long findAllCount(GoodsRecommendCommentVo goodsRecommendCommentVo) {
		return goodsRecommendCommentMapper.findAllCount(goodsRecommendCommentVo);
	}
	@Override
	public int deleteGoodsRecommendComment(GoodsRecommendComment goodsRecommendComment) {
		// 删除评论
		int i = goodsRecommendCommentMapper.updateByPrimaryKeySelective(goodsRecommendComment);
		// 删除评论的回复
		goodsRecommendCommentReturnInfoMapper.deleteBycommentId(goodsRecommendComment.getId());
		return i;
	}
	
	@Override
	public GoodsRecommendComment findByCommentId(Long id) {
		return goodsRecommendCommentMapper.selectByPrimaryKey(id);
	}

}
