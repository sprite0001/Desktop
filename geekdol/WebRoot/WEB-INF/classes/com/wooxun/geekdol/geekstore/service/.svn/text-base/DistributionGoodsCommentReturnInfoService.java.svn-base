package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsCommentReturnInfo;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentReturnInfoVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 即可送的评论回复信息
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午4:00:37 		创建
 *==========================================================
 * 
 */
public interface DistributionGoodsCommentReturnInfoService <T extends Serializable> extends CrudService<T> {
	
	/**
    * 
    * @Title
    * @Description 根据评论ID查找即可送回复信息
    * @author:田振兴
    * @CreateDate:2016年8月12日 下午4:51:51
    * @param distributionGoodsCommentReturnInfoVo
    * @return List
    */
	public List<DistributionGoodsCommentReturnInfoVo> findDistributionGoodsCommentReturnInfo
			(DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo);
	
	/**
     * 
     * @Title
     * @Description 根据评论人ID查找即可送回复人的总数和违规数
     * @author:田振兴
     * @CreateDate:2016年8月13日 上午10:07:25
     * @param distributionGoodsCommentReturnInfoVo
     * @return Long
     */
    public Long findReturnInfoCount(DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo);
    
    /**
     * 
     * @Title
     * @Description 根据主键查找铺货模块的评论回复
     * @author:YK
     * @CreateDate:2016年9月2日 上午10:39:22
     * @param id
     * @return
     */
    public DistributionGoodsCommentReturnInfo findById(Long id);
	    
}
