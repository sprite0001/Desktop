package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.SysDataMapper;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.vo.SysdataVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 王肖东	2016年7月20日  上午10:50:14 		创建
 *==========================================================
 * 
 */
@Service
public class SysDataServiceImpl extends CrudServiceImpl<SysData> implements
		SysDataService<SysData> {
	
	private SysDataMapper<SysData> sysDataMapper;
	@Autowired
	public SysDataServiceImpl(SysDataMapper<SysData> sysDataMapper){
		super(sysDataMapper);
		this.sysDataMapper = sysDataMapper;
	}
	@Override
	public void insertSysdata(SysData sysData) {
		sysDataMapper.insert(sysData);
	}
	@Override
	public SysData selectSysData(Long id) {
		return sysDataMapper.selectByPrimaryKey(id);
	}
	@Override
	public boolean updateSysdata(SysData sysData) {
		
		boolean result = false;
		int i = sysDataMapper.updateByPrimaryKeySelective(sysData);
		if(i>0){
			result = true;
		}
		return result;
	}
	@Override
	public List<SysData> querySysdataByParams(SysdataVo searchSysdata) {
		       
	    return sysDataMapper.querySysdataByParams(searchSysdata);
	}
	@Override
	public Long queryCountByParams(SysdataVo searchSysdata) {
		
		return sysDataMapper.queryCountByParams(searchSysdata);
	}
	
	/**
	 * 
	 * @Title
	 * @Description  同一个类别下  名称不能相等   value值不能相等
	 * @author:王肖东
	 * @CreateDate:2016年7月20日 下午5:25:58
	 * @param yanzhengSysdata
	 * @return
	 */
	@Override
	public Long queryCountByYanzheng(SysData yanzhengSysdata) {
		
		return sysDataMapper.queryCountByYanzheng(yanzhengSysdata);
	}
	@Override
	public List<SysData> querySysdataByBean(SysdataVo searchSysdata) {
	    return sysDataMapper.querySysdataByBean(searchSysdata);
	}
	
	@Override
	public List<SysData> querySysdataByType(String type) {
		return sysDataMapper.querySysdataByType(type);
	}
	@Override
	public List<SysData> querySysdata(SysdataVo searchSysdata) {
		return sysDataMapper.querySysdata(searchSysdata);
	}
}
