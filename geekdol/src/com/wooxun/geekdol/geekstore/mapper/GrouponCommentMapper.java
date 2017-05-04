package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentVo;

public interface GrouponCommentMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 根据团购商品ID查找评论
	 * @author:YK
	 * @CreateDate:2016年8月5日 上午10:43:26
	 * @param grouponCommentVo
	 * @return List<GrouponComment>
	 */
    public List<GrouponCommentVo> selectByGrouponGoodsId(GrouponCommentVo grouponCommentVo);
    
    /**
     * 
     * @Title
     * @Description 根据条件查找评论
     * @author:YK
     * @CreateDate:2016年8月5日 下午2:38:35
     * @param grouponCommentVo
     * @return GrouponCommentVo
     */
    public GrouponCommentVo selectByParams(GrouponCommentVo grouponCommentVo);
}