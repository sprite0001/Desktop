package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;

public interface IncorruptCommentRecommendMapper <T extends Serializable> extends CrudMapper<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 查询评论是否关注记录
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午8:45:12
	 * @param params
	 * @return IncorruptCommentRecommend
	 */
	public IncorruptCommentRecommend queryIncorruptCommentRecommend(Map<String,Object> params);
	
	/**
	 * 
	 * @Title
	 * @Description 删除历史记录
	 * @author:YK
	 * @CreateDate:2016年9月29日 下午5:26:23
	 * @param commendRecommend
	 * @return int
	 */
	public int deteByCommentIdAndUserId(IncorruptCommentRecommend commendRecommend);
}