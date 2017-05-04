package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsComment;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 即可送的评论信息
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午3:56:09 		创建
 *==========================================================
 * 
 */
public interface DistributionGoodsCommentService <T extends Serializable> extends CrudService<T> {
	
	/**
     * 
     * @Title
     * @Description 根据评论ID查找即可送评论信息
     * @author:田振兴
     * @CreateDate:2016年8月12日 下午5:51:59
     * @param distributionGoodsCommentVo
     * @return DistributionGoodsCommentVo
     */
	public DistributionGoodsCommentVo findDistributionGoodsComment(DistributionGoodsCommentVo distributionGoodsCommentVo);
	
	/**
     * 
     * @Title
     * @Description 根据评论ID逻辑删除即可送的评论
     * @author:田振兴
     * @CreateDate:2016年8月13日 下午4:39:33
     * @param distributionGoodsComment
     * @return int
     */
    public int deleteDistributionGoodsComment(DistributionGoodsComment distributionGoodsComment);
    
    /**
     * 
     * @Title
     * @Description 根据评论ID查找即可送评论信息
     * @author:田振兴
     * @CreateDate:2016年8月13日 下午4:40:33
     * @param id
     * @return DistributionGoodsComment
     */
    public DistributionGoodsComment findOneCommentsEasyDG(Long id);

}
