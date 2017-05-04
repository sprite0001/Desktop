package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.CityDistrictNoticeMapper;
import com.wooxun.geekdol.hbridge.model.CityDistrictNotice;
import com.wooxun.geekdol.hbridge.service.CityDistrictNoticeService;
import com.wooxun.geekdol.hbridge.vo.CityDistrictNoticeVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年7月26日  上午10:29:58 		创建
 *==========================================================
 * 
 */

@Service
public class CityDistrictNoticeServiceImpl extends CrudServiceImpl<CityDistrictNotice> implements CityDistrictNoticeService<CityDistrictNotice>{

    private CityDistrictNoticeMapper<CityDistrictNotice> cityDistrictNoticeMapper;
    
    @Autowired
    public CityDistrictNoticeServiceImpl(CityDistrictNoticeMapper<CityDistrictNotice> cityDistrictNoticeMapper) {
        super(cityDistrictNoticeMapper);
        this.cityDistrictNoticeMapper = cityDistrictNoticeMapper;
    }

    @Override
    public List<CityDistrictNotice> queryListByParam(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryListByParam(cityDistrictNoticeVo);
    }

    @Override
    public List<CityDistrictNoticeVo> queryListCityByParam(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryListCityByParam(cityDistrictNoticeVo);
    }

    @Override
    public List<CityDistrictNoticeVo> queryListCountyByParam(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryListCountyByParam(cityDistrictNoticeVo);
    }

    @Override
    public List<CityDistrictNoticeVo> queryListCommunityByParam(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryListCommunityByParam(cityDistrictNoticeVo);
    }

    @Override
    public List<CityDistrictNotice> queryListByParamOrder(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryListByParamOrder(cityDistrictNoticeVo);
    }

    @Override
    public Long queryCountCityByParam(CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryCountCityByParam(cityDistrictNoticeVo);
    }

    @Override
    public Long queryCountCountyByParam(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryCountCountyByParam(cityDistrictNoticeVo);
    }

    @Override
    public Long queryCountCommunityByParam(
            CityDistrictNoticeVo cityDistrictNoticeVo) {
        return cityDistrictNoticeMapper.queryCountCommunityByParam(cityDistrictNoticeVo);
    }


}
