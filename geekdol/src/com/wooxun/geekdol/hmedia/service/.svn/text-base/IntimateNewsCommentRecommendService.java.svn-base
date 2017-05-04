package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentRecommend;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月30日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月30日  上午10:51:10 		创建
 *==========================================================
 * 
 */
public interface IntimateNewsCommentRecommendService<T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 根据评论id与用户id查找对应的记录
	 * @author:YK
	 * @CreateDate:2016年9月30日 上午10:59:19
	 * @param commentId
	 * @param userId
	 * @param operationType
	 * @return IntimateNewsCommentRecommend
	 */
	public IntimateNewsCommentRecommend selectByCommendAndUserId(Long commentId, Long userId, String operationType);
}
