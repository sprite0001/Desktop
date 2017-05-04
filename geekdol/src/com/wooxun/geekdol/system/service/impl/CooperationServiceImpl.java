package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.CooperationMapper;
import com.wooxun.geekdol.system.model.Cooperation;
import com.wooxun.geekdol.system.service.CooperationService;
import com.wooxun.geekdol.system.vo.CooperationVo;

/**
 * @Title 我要合作
 * @Description 我要合作查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午3:53:51 		创建
 *==========================================================
 * 
 */
@Service
public class CooperationServiceImpl extends CrudServiceImpl<Cooperation> implements CooperationService<Cooperation> {
	@Autowired
	private CooperationMapper<Cooperation> cooperationMapper;
	@Autowired
	public CooperationServiceImpl(CooperationMapper<Cooperation> cooperationMapper) {
		super(cooperationMapper);
		this.cooperationMapper = cooperationMapper;
	}

	@Override
	public List<Cooperation> findAll(CooperationVo cooperationVo) {
		return cooperationMapper.findAll(cooperationVo);
	}

	@Override
	public Long findAllCount(CooperationVo cooperationVo) {
		return cooperationMapper.findAllCount(cooperationVo);
	}

}
