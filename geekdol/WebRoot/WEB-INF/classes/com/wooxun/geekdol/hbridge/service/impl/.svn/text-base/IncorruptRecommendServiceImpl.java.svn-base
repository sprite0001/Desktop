package com.wooxun.geekdol.hbridge.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.IncorruptRecommendMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptRecommend;
import com.wooxun.geekdol.hbridge.service.IncorruptRecommendService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月21日  上午9:29:29 		创建
 *==========================================================
 * 
 */
@Service
public class IncorruptRecommendServiceImpl extends CrudServiceImpl<IncorruptRecommend> implements
		IncorruptRecommendService<IncorruptRecommend> {
	
	private IncorruptRecommendMapper<IncorruptRecommend> incorruptRecommendMapper;
	
	@Autowired
	public IncorruptRecommendServiceImpl(IncorruptRecommendMapper<IncorruptRecommend> incorruptRecommendMapper){
		super(incorruptRecommendMapper);
		this.incorruptRecommendMapper = incorruptRecommendMapper;
	}

	@Override
	public IncorruptRecommend queryIncorruptRecommend(Long id, Long userId, String operatType) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("incorruptId", id);
		params.put("operatorId", userId);
		params.put("operatorType", operatType);
		return incorruptRecommendMapper.queryIncorruptRecommend(params);
	}
}
