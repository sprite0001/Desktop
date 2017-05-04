package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.NetizenSecurityMapper;
import com.wooxun.geekdol.hbridge.model.NetizenSecurity;
import com.wooxun.geekdol.hbridge.service.NetizenSecurityService;
import com.wooxun.geekdol.hbridge.vo.NetizenSecurityVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年9月8日  上午10:12:54 		创建
 *==========================================================
 * 
 */
@Service
public class NetizenSecurityServiceImpl extends CrudServiceImpl<NetizenSecurity> implements NetizenSecurityService<NetizenSecurity> {

	private NetizenSecurityMapper<NetizenSecurity> netizenSecurityMapper;
	
	@Autowired
	public NetizenSecurityServiceImpl(NetizenSecurityMapper<NetizenSecurity> netizenSecurityMapper) {
		super(netizenSecurityMapper);
		this.netizenSecurityMapper = netizenSecurityMapper;
	}

	@Override
	public Long findNetizenSecurityCountByParam(
			NetizenSecurityVo netizenSecurityVo) {
		// 调用mapper方法根据网安报相关参数查询网安报总数量
		return netizenSecurityMapper.selectNetizenSecurityCountByParam(netizenSecurityVo);
	}

	@Override
	public List<NetizenSecurityVo> findNetizenSecurityListByParam(
			NetizenSecurityVo netizenSecurityVo) {
		// 调用mapper方法根据网安报相关参数查询网安报列表数据
		return netizenSecurityMapper.selectNetizenSecurityListByParam(netizenSecurityVo);
	}

	@Override
	public boolean saveNetizenSecurity(NetizenSecurityVo netizenSecurityVo) {
		// 创建网安报Model变量
		NetizenSecurity netizenSecurity = new NetizenSecurity();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(netizenSecurityVo, netizenSecurity);
		// 调用mapper方法插入周边店主数据
		int result = netizenSecurityMapper.insertSelective(netizenSecurity);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateNetizenSecurity(NetizenSecurityVo netizenSecurityVo) {
		// 创建网安报Model变量
		NetizenSecurity netizenSecurity = new NetizenSecurity();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(netizenSecurityVo, netizenSecurity);
		// 调用mapper方法插入周边店主数据
		int result = netizenSecurityMapper.updateByPrimaryKeySelective(netizenSecurity);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public NetizenSecurityVo getNetizenSecurityVo(Long id) {
		// 调用mapper方法根据id获取网安报对象
		NetizenSecurity netizenSecurity = netizenSecurityMapper.selectByPrimaryKey(id);
		// 创建网安报Vo对象
		NetizenSecurityVo netizenSecurityVo = new NetizenSecurityVo();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(netizenSecurity, netizenSecurityVo);
		// 返回结果
		return netizenSecurityVo;
	}

	@Override
	public Integer updateNetizenSecurityAnyParam(NetizenSecurity netizenSecurity) {
		// 调用mapper方法更新网安报相关参数
		return netizenSecurityMapper.updateNetizenSecurityAnyParam(netizenSecurity);
	}

	@Override
	public List<NetizenSecurityVo> findHotNetizenSecurityListByParam(
			NetizenSecurityVo netizenSecurityVo) {
		// 调用mapper方法根据网安报相关参数查询网安报推荐列表数据
		return netizenSecurityMapper.selectHotNetizenSecurityListByParam(netizenSecurityVo);
	}

}
