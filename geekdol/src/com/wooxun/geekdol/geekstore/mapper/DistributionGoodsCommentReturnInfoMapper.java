package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentReturnInfoVo;

public interface DistributionGoodsCommentReturnInfoMapper <T extends Serializable> extends CrudMapper<T>{
    /**
     * 
     * @Title
     * @Description 根据评论ID查找回复信息
     * @author:田振兴
     * @CreateDate:2016年8月12日 下午4:51:51
     * @param distributionGoodsCommentReturnInfoVo
     * @return
     */
	public List<DistributionGoodsCommentReturnInfoVo> commentReturninfo(DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo);
	
	/**
     * 
     * @Title
     * @Description 根据评论人ID查找回复人的总数和违规数
     * @author:田振兴
     * @CreateDate:2016年8月13日 上午10:07:25
     * @param distributionGoodsCommentReturnInfoVo
     * @return
     */
    public Long findReturnInfoCount(DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo);
    
    
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