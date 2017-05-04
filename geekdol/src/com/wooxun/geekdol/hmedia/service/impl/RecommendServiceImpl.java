package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.service.RecommendService;
import com.wooxun.geekdol.system.mapper.CountyMapper;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月28日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月28日  下午4:36:56 		创建
 *==========================================================
 * 
 */
@Service
public class RecommendServiceImpl extends CrudServiceImpl<County> implements RecommendService<County>{
   
    @Autowired
    private CountyMapper<County> countyMapper;
    
    @Autowired
    public RecommendServiceImpl(CountyMapper<County> countyMapper) {
        super(countyMapper);
        this.countyMapper=countyMapper;
    }

    @Override
    public List<CountyQueryVo> findRecommend(CountyQueryVo countyQueryVo) {
        List<County> countyList=countyMapper.findRecommend(countyQueryVo);
        return formateData(countyList);
    }

    @Override
    public Long findRecommendCount(CountyQueryVo countyQueryVo) {
        return countyMapper.findRecommendCount(countyQueryVo);
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

    @Override
    public boolean deleteRecommend(County county) {
          int res= countyMapper.deleteRecommend(county);
          return  res>0?true:false;
    }

    @Override
    public boolean disableRecommend(County county) {
        int res=countyMapper.disableRecommend(county);
        return res>0?true:false;
    }

    @Override
    public boolean enableRecommend(County county) {
        int res=countyMapper.enableRecommend(county);
        return res>0?true:false;
    }
}
