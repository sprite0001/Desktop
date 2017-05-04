package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentVo;

public interface GoodsRecommendCommentMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title 商品管理
	 * @Description 商品管理评论
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 上午11:10:53
	 * @param goodsRecommendCommentVo
	 * @return List
	 */
   public List<GoodsRecommendCommentVo> findgoodsCommment(GoodsRecommendCommentVo goodsRecommendCommentVo);
   /**
    * 
    * @Title 商品管理
    * @Description 根据评论ID查找评论信息
    * @author:田振兴
    * @CreateDate:2016年8月12日 下午5:40:46
    * @param goodsRecommendCommentVo
    * @return
    */
   public GoodsRecommendCommentVo findOneComments(GoodsRecommendCommentVo goodsRecommendCommentVo);
   
   /**
    * 
    * @Title
    * @Description 根据条件查询好评、中评、差评
    * @author:田振兴
    * @CreateDate:2016年8月13日 上午11:24:13
    * @param goodsRecommendCommentVo
    * @return
    */
   public Long findAllCount(GoodsRecommendCommentVo goodsRecommendCommentVo);
}