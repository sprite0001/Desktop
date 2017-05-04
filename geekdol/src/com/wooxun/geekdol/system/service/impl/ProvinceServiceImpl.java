package com.wooxun.geekdol.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.mapper.ProvinceMapper;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.vo.ProvinceQueryVo;

/**
 * @Title 省管理
 * @Description 省管理的一些具体操作(省的查询，添加，修改，删除，禁用，启用)
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月18日  上午10:08:27 		创建
 *==========================================================
 * 
 */

@Service
public class ProvinceServiceImpl extends CrudServiceImpl<Province> implements ProvinceService<Province>{
	
	private ProvinceMapper<Province> provinceMapper;
	
	@Autowired
	public ProvinceServiceImpl(ProvinceMapper<Province> provinceMapper) {
		super(provinceMapper);
		this.provinceMapper = provinceMapper;
	}

	@Override
	public List<Province> selectList(ProvinceQueryVo provinceQueryVo) {
		return provinceMapper.selectList(provinceQueryVo);
	}

	@Override
	public Long selectListCount(ProvinceQueryVo provinceQueryVo) {
		return provinceMapper.selectListCount(provinceQueryVo);
	}
	
	@Override
	public int insertProvince(Province province) {		
		return provinceMapper.insert(province);
	}
	
	@Override
	public Province selectByid(Long ProvinceId){
		return provinceMapper.selectByPrimaryKey(ProvinceId);
	}
	
	@Override
	public int delete(Province province){
		
		return provinceMapper.updateByPrimaryKeySelective(province);
	}
	
	@Override
	public int updateByid(Province province){
		return provinceMapper.updateByPrimaryKeySelective(province);
	}
	
	@Override
	public Long selectProvince(Province province){
		return provinceMapper.selectProvince(province);
	}

	@Override
	public List<Province> selectListNoUser(ProvinceQueryVo provinceQueryVo) {
		return provinceMapper.selectListNoUser(provinceQueryVo);
	}

	@Override
	public List<Province> findProvince(ProvinceQueryVo provinceQueryVo) {
		return provinceMapper.findProvince(provinceQueryVo);
	}

	@Override
	public boolean findProvinceName(String provinceName, Long id) {
		// 创建Map对象用于封装查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中封装参数 */
		param.put("provinceName", provinceName);
		param.put("id", id);
		// 调用mapper方法看省名字是否重复
		int result = provinceMapper.findProvinceName(param);
		if (result > 0) { // 如果有则返回存在
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int updateByidAndOrder(Province province) {
		return provinceMapper.updateByPrimaryKeySelectiveAndOrder(province);
	}

	@Override
	public Province hasEffectProvince(Long id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("provinceId", id);
		params.put("status", ConstantStr.QY_FLAG);
		return provinceMapper.hasEffectProvince(params);
	}
}
