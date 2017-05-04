package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendCommentReturnInfo;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentReturnInfoVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 新产品评论回复
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午3:49:23 		创建
 *==========================================================
 * 
 */
public interface GoodsRecommendCommentReturnInfoService <T extends Serializable> extends CrudService<T> {
	   
   /**
	 * 
	 * @Title 商品管理
	 * @Description 根据评论人ID查找所有的新产品回复列表
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 下午2:56:07
	 * @param goodsRecommendCommentReturnInfoVo
	 * @return List
	 */
   public List<GoodsRecommendCommentReturnInfoVo> findGoodsRecommendCommentReturnInfo(
		   GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo);	   	   		
	   
   /**
     * 
     * @Title
     * @Description 根据评论人ID查找新商品回复人的总数和违规数
     * @author:田振兴
     * @CreateDate:2016年8月13日 上午10:07:25
     * @param goodsRecommendCommentReturnInfoVo
     * @return Long
     */
    public Long findReturnInfoCount(GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo);
    
    /**
     * 
     * @Title
     * @Description 根据id查找回复内容
     * @author:YK
     * @CreateDate:2016年9月2日 上午10:14:17
     * @param id
     * @return
     */
    public GoodsRecommendCommentReturnInfo findById(Long id);

}
