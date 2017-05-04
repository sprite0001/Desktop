package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GrouponComment;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentVo;

/**
 * @Title
 * @Description 团购管理--评论
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月5日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月5日  上午10:57:41 		创建
 *==========================================================
 * 
 */
public interface GrouponCommentService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title
	 * @Description 根据团购商品ID查找评论
	 * @author:YK
	 * @CreateDate:2016年8月5日 上午10:43:26
	 * @param grouponCommentVo
	 * @return List<GrouponCommentVo>
	 */
    public List<GrouponCommentVo> selectByGrouponGoodsId(GrouponCommentVo grouponCommentVo);
    
    /**
     * 
     * @Title
     * @Description 根据主键查找评论
     * @author:YK
     * @CreateDate:2016年8月5日 下午2:38:35
     * @param grouponCommentVo
     * @return GrouponComment
     */
    public GrouponComment selectById(Long id);
    
    /**
     * 
     * @Title
     * @Description 删除
     * @author:YK
     * @CreateDate:2016年8月5日 下午3:05:34
     * @param grouponComment
     * @return boolean
     */
    public boolean deleteGrouponComment(GrouponComment grouponComment);
    
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
