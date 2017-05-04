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
import com.wooxun.geekdol.system.mapper.CountyMapper;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月18日  下午1:03:14 		创建
 *==========================================================
 * 
 */
@Service
public class CountyServiceImpl  extends CrudServiceImpl<County> implements  CountyService<County>{
   
    @Autowired
    private CountyMapper<County> countyMapper;
    
    @Autowired
    public CountyServiceImpl(CountyMapper<County> countyMapper) {
        super(countyMapper);
        this.countyMapper=countyMapper;
    }
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表 
     * @author:QZG
     * @CreateDate:2016年7月22日 上午10:34:17
     * @param countyQueryVo
     * @return
     */
    @Override
    public List<CountyQueryVo> findAll(CountyQueryVo countyQueryVo) {
        List<County> countyList=countyMapper.findAll(countyQueryVo);
        return formateData(countyList);
    }
    
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，返回满足查询条件条数
     * @author:QZG
     * @CreateDate:2016年7月22日 上午10:34:53
     * @param countyQueryVo
     * @return
     */
    @Override
    public Long findAllCount(CountyQueryVo countyQueryVo) {
        return countyMapper.findAllCount(countyQueryVo);
    }
    
    /**
     * 
     * @Title
     * @Description 逻辑删除行政区信息 删除成功返回true
     * @author:QZG
     * @CreateDate:2016年7月22日 上午10:35:34
     * @param county
     * @return
     */
    @Override
    public boolean deleteCountyById(County county) {
       int result = countyMapper.deleteCountyById(county);
        return result>0?true:false;
    }
 
 
    /**
     * 
     * @Title
     * @Description 列表数据组装
     * @author:QZG
     * @CreateDate:2016年7月22日 上午10:40:37
     * @param countyList
     * @return
     */
    private List<CountyQueryVo> formateData(List<County> countyList){
        List<CountyQueryVo> result=new ArrayList<CountyQueryVo>();
        for(County county:countyList){
            CountyQueryVo countyqueryvo=new CountyQueryVo();
            BeanUtils.copyProperties(county,countyqueryvo);
            countyqueryvo.setProvinceName(county.getProvince().getProvinceName());
            countyqueryvo.setCityName(county.getCity().getCityName());
            result.add(countyqueryvo);
        }
        return result;
    }
    /**
     * 
     * @Title
     * @Description 行政区启用功能 启用成功返回true
     * @author:QZG
     * @CreateDate:2016年7月22日 上午10:35:59
     * @param county
     * @return
     */
    @Override
    public boolean start(County county) {
        int result =countyMapper.start(county);
        return result>0?true:false;
    }
    /**
     * 
     * @Title
     * @Description 行政区禁用功能 禁用成功返回true
     * @author:QZG
     * @CreateDate:2016年7月22日 上午10:36:09
     * @param county
     * @return
     */
    @Override
    public boolean stop(County county) {
        int result=countyMapper.stop(county);
        return result>0?true:false;
    }
    
    /**
     * 
     * @Title
     * @Description 新增时返回数据库中存在该区编码的数量
     * @author:QZG
     * @CreateDate:2016年7月22日 下午3:16:09
     * @param county
     * @return
     */
    @Override
    public Long findCodeCount(County county) {
        return countyMapper.findCodeCount(county);
    }
    
    
    @Override
    public Long findByCountyCityId(County county){
    	return countyMapper.findByCountyCityId(county);
    }
	@Override
	public List<County> selectByCityIdNoUser(Map<String, Object> parmMap) {
		return countyMapper.selectByCityIdNoUser(parmMap);
	}
	
	@Override
	public List<CountyQueryVo> queryCountryByCity(CountyQueryVo countyQueryVo) {
		//返回 组装好的数据
		return formateData(countyMapper.queryCountryByCity(countyQueryVo));
	}
	@Override
	public List<County> findByUserId(Map<String, Object> parmMap) {
		return countyMapper.findByUserId(parmMap);
	}
    @Override
    public List<County> selectByCityId(Map<String, Object> parmMap) {
        return countyMapper.selectByCityId(parmMap);
    }
    @Override
    public Long selectCountByCityId(Map<String, Object> parmMap) {
        return countyMapper.selectCountByCityId(parmMap);
    }
    @Override
    public List<County> selectCountyByIds(Map<String, Object> parmMap) {
        return countyMapper.selectCountyByIds(parmMap);
    }
	@Override
	public County haEffectCounty(Long countyId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("countyId", countyId);
		params.put("status", ConstantStr.QY_FLAG);
		return countyMapper.haEffectCounty(params);
	}
}
