package com.wooxun.geekdol.hmedia.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsCommentRecommendMapper;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentRecommend;
import com.wooxun.geekdol.hmedia.service.IntimateNewsCommentRecommendService;

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
 * 1. 	 YK	2016年9月30日  上午10:52:10 		创建
 *==========================================================
 * 
 */
@Service
public class IntimateNewsCommentRecommendServiceImpl extends CrudServiceImpl<IntimateNewsCommentRecommend> implements
		IntimateNewsCommentRecommendService<IntimateNewsCommentRecommend> {

	private IntimateNewsCommentRecommendMapper<IntimateNewsCommentRecommend> intimateNewsCommentRecommendMapper;
	@Autowired
	public IntimateNewsCommentRecommendServiceImpl(
			IntimateNewsCommentRecommendMapper<IntimateNewsCommentRecommend> intimateNewsCommentRecommendMapper){
		super(intimateNewsCommentRecommendMapper);
		this.intimateNewsCommentRecommendMapper = intimateNewsCommentRecommendMapper;
	}
	@Override
	public IntimateNewsCommentRecommend selectByCommendAndUserId(
			Long commentId, Long userId, String operationType) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("commentId", commentId);
		params.put("operatorId", userId);
		params.put("operatorType", operationType);
		return intimateNewsCommentRecommendMapper.selectByCommendAndUserId(params);
	}
}
