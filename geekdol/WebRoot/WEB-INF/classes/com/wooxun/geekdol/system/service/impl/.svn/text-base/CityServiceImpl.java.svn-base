package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.mapper.CityMapper;
import com.wooxun.geekdol.system.mapper.ProvinceMapper;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.vo.CityVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 万通	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年7月18日  上午10:13:58 		创建
 *==========================================================
 * 
 */

@Service
public class CityServiceImpl extends CrudServiceImpl<City> implements CityService<City> {
	
	private CityMapper< City> cityMapper;
	@Autowired
	private ProvinceMapper<Province> provinceMapper;
	@Autowired
	public CityServiceImpl(CityMapper<City> cityMapper) {
		super(cityMapper);
		this.cityMapper = cityMapper;
	}
	@Override
	public List<CityVo> findAll(CityVo cityVo) {
		List<City> cityList = cityMapper.findAll(cityVo);
		List<CityVo> cityVoList = new ArrayList<CityVo>();
		cityVoList = formateData(cityList);
		return cityVoList;
	}
	@Override
	public Long findAllCount(CityVo cityVo) {
		return cityMapper.findAllCount(cityVo);
	}
	@Override
	public List<City> findByCityProviceId(Long provinceId) {
		return cityMapper.findByCityProviceId(provinceId);
	}
	
	@Override
	public Long findBycityProvinceIdCount(City city) {
		return cityMapper.findBycityProvinceIdCount(city);
	}
	
	@Override
	public Long selectCityCount(City city){
		return cityMapper.selectCityCount(city);
	}
	@Override
	public int insertCity(City city){
		return cityMapper.insert(city);
	}
	
	public City selectByid(Long cityId){
		return cityMapper.selectByPrimaryKey(cityId);
	}
	
	public int updateByid(City city){
		return cityMapper.updateByPrimaryKeySelective(city);
	}
	
	public int delete(City city){
		return cityMapper.updateByPrimaryKeySelective(city);
	}
	/**
	 * 
	 * @Title
	 * @Description 列表数据组装
	 * @author:YK
	 * @CreateDate:2016年7月18日 下午2:47:00
	 * @param communityList
	 * @return
	 */
	private List<CityVo> formateData(List<City> cityList){
		List<CityVo> result = new ArrayList<CityVo>();
		for(City city : cityList){
			CityVo cityVo = new CityVo();
			BeanUtils.copyProperties(city, cityVo);
			cityVo.setProvinceName(city.getProvince().getProvinceName());
			result.add(cityVo);
		}
		return result;
	}
	@Override
	public List<City> findCityByProviceIdNoUser(Long proviceId) {
		return cityMapper.findCityByProviceIdNoUser(proviceId);
	}
	@Override
	public List<City> queryCityByProvice(Long provinceId) {
		return cityMapper.queryCityByProvice(provinceId);
	}

	@Override
	public List<City> findAllCity() {
		return cityMapper.findAllCity();
	}

	@Override
	public List<City> findByUserId(Map<String, Object> parmMap) {
		return cityMapper.findByUserId(parmMap);
	}
	@Override
	public List<City> findCity(City city) {
		return cityMapper.findCity(city);
	}
    @Override
    public List<City> selectCity(CityVo city) {
        return cityMapper.selectCity(city);
    }
    @Override
    public List<City> selectCityByIds(Map<String, Object> parmMap) {
        return cityMapper.selectCityByIds(parmMap);
    }
	@Override
	public City haEffectCity(Long cityId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityId", cityId);
		params.put("status", ConstantStr.QY_FLAG);
		return cityMapper.haEffectCity(params);
	}
	

}
