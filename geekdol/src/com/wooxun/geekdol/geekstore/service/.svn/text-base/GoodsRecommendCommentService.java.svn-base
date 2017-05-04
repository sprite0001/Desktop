package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendComment;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 新产品评论
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午3:44:13 		创建
 *==========================================================
 * 
 */
public interface GoodsRecommendCommentService <T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title 商品管理
	 * @Description 商品管理评论
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 上午11:10:53
	 * @param goodsRecommendCommentVo
	 * @return List
	 */
   public List<GoodsRecommendCommentVo> findGoodsRecommendComment(GoodsRecommendCommentVo goodsRecommendCommentVo);
   
   /**
    * 
    * @Title 商品管理
    * @Description 根据评论ID查找新商品评论信息
    * @author:田振兴
    * @CreateDate:2016年8月12日 下午5:40:46
    * @param goodsRecommendCommentVo
    * @return GoodsRecommendCommentVo
    */
    public GoodsRecommendCommentVo findOneCommentsGR(GoodsRecommendCommentVo goodsRecommendCommentVo);
    
    /**
     * 
     * @Title
     * @Description 根据条件查询好评、中评、差评
     * @author:田振兴
     * @CreateDate:2016年8月13日 上午11:24:13
     * @param goodsRecommendCommentVo
     * @return Long
     */
    public Long findAllCount(GoodsRecommendCommentVo goodsRecommendCommentVo);
    
    /**
     * 
     * @Title
     * @Description 根据评论ID逻辑删除新商品评论
     * @author:田振兴
     * @CreateDate:2016年8月13日 下午3:04:27
     * @param goodsRecommendComment
     * @return int
     */
    public int deleteGoodsRecommendComment(GoodsRecommendComment goodsRecommendComment);
        
    /**
     * 
     * @Title
     * @Description 根据评论ID查找新商品评论信息
     * @author:田振兴
     * @CreateDate:2016年8月13日 下午4:40:33
     * @param id
     * @return GoodsRecommendComment
     */
    public GoodsRecommendComment findByCommentId(Long id);

}
