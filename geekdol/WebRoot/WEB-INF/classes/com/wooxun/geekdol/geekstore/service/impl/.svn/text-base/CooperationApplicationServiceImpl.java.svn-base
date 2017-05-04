package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.CooperationApplicationMapper;
import com.wooxun.geekdol.geekstore.model.CooperationApplication;
import com.wooxun.geekdol.geekstore.service.CooperationApplicationService;
import com.wooxun.geekdol.geekstore.vo.CooperationApplicationVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月21日  下午5:52:12 		创建
 *==========================================================
 * 
 */
@Service
public class CooperationApplicationServiceImpl extends CrudServiceImpl<CooperationApplication>
	implements CooperationApplicationService<CooperationApplication> {

	private CooperationApplicationMapper<CooperationApplication> cooperationApplicationMapper;
	
	@Autowired
	public CooperationApplicationServiceImpl(CooperationApplicationMapper<CooperationApplication>
		cooperationApplicationMapper) {
		super(cooperationApplicationMapper);
		this.cooperationApplicationMapper = cooperationApplicationMapper;
	}
	
	@Override
	public void insertCooperationApplication(
			CooperationApplication cooperationApplication) {
		cooperationApplicationMapper.insert(cooperationApplication);
	}

	@Override
	public List<CooperationApplicationVo> findAllCooperationApplication(
			CooperationApplicationVo cooperationApplicationVo) {
		// 设置申请店铺类型在字典表中的type类型
		cooperationApplicationVo.setTypeType(ConstantStr.STORETYPE); 
		return cooperationApplicationMapper.findAllCooperationApplication(cooperationApplicationVo);
	}

	@Override
	public Long findAllCooperationApplicationCount(
			CooperationApplicationVo cooperationApplicationVo) {
		return cooperationApplicationMapper.findAllCooperationApplicationCount(cooperationApplicationVo);
	}

	@Override
	public List<CooperationApplicationVo> findCooperationApplication(
			CooperationApplicationVo cooperationApplicationVo) {
		return cooperationApplicationMapper.findCooperationApplication(cooperationApplicationVo);
	}

	@Override
	public Long findCooperationApplicationCount(
			CooperationApplicationVo cooperationApplicationVo) {
		return cooperationApplicationMapper.findCooperationApplicationCount(cooperationApplicationVo);
	}

}
