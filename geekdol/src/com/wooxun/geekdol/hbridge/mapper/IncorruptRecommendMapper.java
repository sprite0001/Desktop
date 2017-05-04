package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptRecommend;

public interface IncorruptRecommendMapper <T extends Serializable> extends CrudMapper<T> {
    
	/**
	 * 
	 * @Title
	 * @Description 根据用户名称与风清气正id查找点赞记录
	 * @author:YK
	 * @CreateDate:2016年9月21日 上午9:39:35
	 * @param params
	 * @return IncorruptRecommend
	 */
	public IncorruptRecommend queryIncorruptRecommend(Map<String,Object> params);
}