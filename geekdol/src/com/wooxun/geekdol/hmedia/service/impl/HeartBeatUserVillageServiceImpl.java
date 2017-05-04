package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatUserVillageMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeatUserVillage;
import com.wooxun.geekdol.hmedia.service.HeartBeatUserVillageService;

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
 * 1. 	 YK	2016年9月20日  下午12:22:28 		创建
 *==========================================================
 * 
 */
@Service
public class HeartBeatUserVillageServiceImpl extends CrudServiceImpl<HeartBeatUserVillage> implements
		HeartBeatUserVillageService<HeartBeatUserVillage> {
	
	private HeartBeatUserVillageMapper<HeartBeatUserVillage> heartBeatUserVillageMapper;
	
	@Autowired
	public HeartBeatUserVillageServiceImpl(HeartBeatUserVillageMapper<HeartBeatUserVillage> heartBeatUserVillageMapper) {
		super(heartBeatUserVillageMapper);
		this.heartBeatUserVillageMapper = heartBeatUserVillageMapper;
	}

	@Override
	public List<Map<String, Object>> queryLastVillage(Long userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("qiyong", ConstantStr.QY_FLAG);
		return heartBeatUserVillageMapper.queryLastVillage(params);
	}

	@Override
	public boolean saveHeartBeatUserVillage(Long userId, String[] villages) {
		// 删除上次历史记录
		int i = heartBeatUserVillageMapper.deleteByUserId(userId);
		// 插入本次搜索记录
		List<HeartBeatUserVillage> list = new ArrayList<HeartBeatUserVillage>();
		for(int k = 0;k<villages.length; k++){
			if(StringUtil.isBlank(villages[k])){
				continue;
			}
			HeartBeatUserVillage userVillage = new HeartBeatUserVillage();
			userVillage.setUserId(userId);
			userVillage.setVillageId(Long.valueOf(villages[k]));
			list.add(userVillage);
		}
		if(list.size()>0){
			i = heartBeatUserVillageMapper.batchInnsert(list);
		}
		return i>0?true:false;
	}
}
