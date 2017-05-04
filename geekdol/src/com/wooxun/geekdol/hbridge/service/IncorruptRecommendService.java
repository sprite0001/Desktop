package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.IncorruptRecommend;

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
 * 1. 	 YK	2016年9月21日  上午9:28:14 		创建
 *==========================================================
 * 
 */
public interface IncorruptRecommendService <T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 根据用户id、风清气正id查找关注记录
	 * @author:YK
	 * @CreateDate:2016年9月21日 上午9:36:06
	 * @param id
	 * @param userId
	 * @param operatType
	 * @return IncorruptRecommend
	 */
	public IncorruptRecommend queryIncorruptRecommend(Long id,Long userId,String operatType);
}
