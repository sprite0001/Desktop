package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.GoodsRecommendCommentReturnInfoVo;

public interface GoodsRecommendCommentReturnInfoMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title 商品管理
	 * @Description 根据评论人ID查找所有的回复列表
	 * @author:田振兴
	 * @CreateDate:2016年8月12日 下午2:56:07
	 * @param goodsRecommendCommentReturnInfoVo
	 * @return
	 */
    public List<GoodsRecommendCommentReturnInfoVo> commentReturninfo(GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo);
    
    /**
     * 
     * @Title
     * @Description 根据评论人ID查找回复人的总数和违规数
     * @author:田振兴
     * @CreateDate:2016年8月13日 上午10:07:25
     * @param goodsRecommendCommentReturnInfoVo
     * @return
     */
    public Long findReturnInfoCount(GoodsRecommendCommentReturnInfoVo goodsRecommendCommentReturnInfoVo);
    
    /**
     * 
     * @Title
     * @Description 根据评论id删除回复
     * @author:田振兴
     * @CreateDate:2016年8月13日 下午6:31:16
     * @param commentId
     * @return int
     */
    public int deleteBycommentId(Long commentId);
    
}