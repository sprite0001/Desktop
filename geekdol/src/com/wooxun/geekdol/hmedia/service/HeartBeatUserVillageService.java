package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;

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
 * 1. 	 YK	2016年9月20日  下午12:20:27 		创建
 *==========================================================
 * 
 */
public interface HeartBeatUserVillageService<T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 查询最近选择的小区
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午2:51:15
	 * @param userId
	 * @return List
	 */
	public List<Map<String,Object>> queryLastVillage(Long userId);
	
	/**
	 * 
	 * @Title
	 * @Description 新增选小区历史记录
	 * @author:YK
	 * @CreateDate:2016年9月21日 上午10:49:17
	 * @param userId
	 * @param villages
	 * @return boolean
	 */
	public boolean saveHeartBeatUserVillage(Long userId,String villages[]);
}
