package com.wooxun.geekdol.hbridge.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.IncorruptCommentRecommendMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;
import com.wooxun.geekdol.hbridge.service.IncorruptCommentRecommendService;

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
 * 1. 	 YK	2016年9月20日  下午8:39:05 		创建
 *==========================================================
 * 
 */
@Service
public class IncorruptCommentRecommendServiceImpl extends CrudServiceImpl<IncorruptCommentRecommend> implements
		IncorruptCommentRecommendService<IncorruptCommentRecommend> {
	private IncorruptCommentRecommendMapper<IncorruptCommentRecommend> incorruptCommentRecommendMapper;
	
	@Autowired
	public IncorruptCommentRecommendServiceImpl(IncorruptCommentRecommendMapper
			<IncorruptCommentRecommend> incorruptCommentRecommendMapper){
		super(incorruptCommentRecommendMapper);
		this.incorruptCommentRecommendMapper = incorruptCommentRecommendMapper;
	}

	@Override
	public IncorruptCommentRecommend queryIncorruptCommentRecommend(
			Long commentId, Long userId, String operatorType) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("commentId", commentId);
		params.put("operatorId", userId);
		params.put("operatorType", operatorType);
		return incorruptCommentRecommendMapper.queryIncorruptCommentRecommend(params);
	}
}
