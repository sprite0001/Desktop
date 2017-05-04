package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendCommentReturnInfoService;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentReturnInfoVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 新产品的回复信息
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午4:07:59 		创建
 *==========================================================
 * 
 */
@Service
public class GoodsRecommendCommentReturnInfoServiceImpl extends CrudServiceImpl<GoodsRecommendCommentReturnInfo> 
		implements GoodsRecommendCommentReturnInfoService<GoodsRecommendCommentReturnInfo> {
	
	@Autowired
	private GoodsRecommendCommentReturnInfoMapper<GoodsRecommendCommentReturnInfo> goodsRecommendCommentReturnInfoMapper;
	@Autowired
	public GoodsRecommendCommentReturnInfoServiceImpl(
			GoodsRecommendCommentReturnInfoMapper<GoodsRecommendCommentReturnInfo> goodsRecommendCommentReturnInfoMapper) {
		super(goodsRecommendCommentReturnInfoMapper);
		this.goodsRecommendCommentReturnInfoMapper = goodsRecommendCommentReturnInfoMapper;
	}
	
	@Override
	public List<GoodsRecommendCommentReturnInfoVo> findGoodsRecommendCommentReturnInfo(
			GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo) {
		return goodsRecommendCommentReturnInfoMapper.commentReturninfo(goodsRecommendCommentReturnInfoVo);
	}
	
	@Override
	public Long findReturnInfoCount(
			GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo) {
		return goodsRecommendCommentReturnInfoMapper.findReturnInfoCount(goodsRecommendCommentReturnInfoVo);
	}

	@Override
	public GoodsRecommendCommentReturnInfo findById(Long id) {
		return goodsRecommendCommentReturnInfoMapper.selectByPrimaryKey(id);
	}

}
