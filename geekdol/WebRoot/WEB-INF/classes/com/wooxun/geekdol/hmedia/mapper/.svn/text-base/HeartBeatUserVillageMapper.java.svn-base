package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeatUserVillage;

public interface HeartBeatUserVillageMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 查询最近选择的小区
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午2:51:15
	 * @param params
	 * @return List
	 */
	public List<Map<String,Object>> queryLastVillage(Map<String,Object> params);
	
	/**
	 * 
	 * @Title
	 * @Description 根据用户id删除上次历史纪录
	 * @author:YK
	 * @CreateDate:2016年9月21日 上午10:51:17
	 * @param userId
	 * @return int
	 */
	public int deleteByUserId(Long userId);
	
	/**
	 * 
	 * @Title
	 * @Description 批量保存
	 * @author:YK
	 * @CreateDate:2016年9月21日 上午11:00:35
	 * @param list
	 * @return int
	 */
	public int batchInnsert(List<HeartBeatUserVillage> list);
}