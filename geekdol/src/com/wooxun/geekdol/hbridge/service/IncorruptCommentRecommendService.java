package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月20日  下午8:35:17 		创建
 *==========================================================
 * 
 */
public interface IncorruptCommentRecommendService<T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 查询评论是否关注记录
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午8:37:50
	 * @param commentId
	 * @param userId
	 * @param operatorType
	 * @return IncorruptCommentRecommend
	 */
	public IncorruptCommentRecommend queryIncorruptCommentRecommend(Long commentId,Long userId,String operatorType);
}
