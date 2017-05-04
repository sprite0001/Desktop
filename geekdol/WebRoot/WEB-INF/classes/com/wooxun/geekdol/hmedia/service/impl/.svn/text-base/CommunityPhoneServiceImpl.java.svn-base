package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.CommunityPhoneMapper;
import com.wooxun.geekdol.hmedia.model.CommunityPhone;
import com.wooxun.geekdol.hmedia.service.CommunityPhoneService;
import com.wooxun.geekdol.hmedia.vo.AppCommunityPhoneVo;
import com.wooxun.geekdol.hmedia.vo.CommunityPhoneVo;
import com.wooxun.geekdol.system.mapper.UserAreaMapper;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月30日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月30日  上午11:22:21 		创建
 *==========================================================
 * 
 */
@Service
public class CommunityPhoneServiceImpl extends CrudServiceImpl<CommunityPhone> implements CommunityPhoneService<CommunityPhone>{
    
    @Autowired
    private CommunityPhoneMapper<CommunityPhone> communityPhoneMapper;
    @Autowired
    private UserAreaMapper<UserArea> userAreaMapper;
    @Autowired
    public CommunityPhoneServiceImpl(CommunityPhoneMapper<CommunityPhone> communityPhoneMapper) {
        super(communityPhoneMapper);
        this.communityPhoneMapper=communityPhoneMapper;
    }

    @Override
    public List<CommunityPhoneVo> findAll(CommunityPhoneVo communityPhoneVo) {
        List<CommunityPhone> communityPhoneList = communityPhoneMapper.findAll(communityPhoneVo);
        return formateData(communityPhoneList);
    }

    @Override
    public Long findAllCount(CommunityPhoneVo communityPhoneVo) {
        return communityPhoneMapper.findAllCount(communityPhoneVo);
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
    private List<CommunityPhoneVo> formateData(List<CommunityPhone> communityPhoneList){
        List<CommunityPhoneVo> result=new ArrayList<CommunityPhoneVo>();
        for(CommunityPhone communityPhone:communityPhoneList){
            CommunityPhoneVo communityPhoneVo=new CommunityPhoneVo();
            BeanUtils.copyProperties(communityPhone,communityPhoneVo);
            communityPhoneVo.setProvinceName(communityPhone.getProvince().getProvinceName());
            communityPhoneVo.setCityName(communityPhone.getCity().getCityName());
            communityPhoneVo.setCountyName(communityPhone.getCounty().getCountyName());
            communityPhoneVo.setCommunityName(communityPhone.getCommunity().getCommunityName());
            communityPhoneVo.setVillageName(communityPhone.getVillage().getVillageName());
            result.add(communityPhoneVo);
        }
        return result;
    }

    @Override
    public boolean disableCommunityPhone(CommunityPhone communityPhone) {
        int res = communityPhoneMapper.disableCommunityPhone(communityPhone);
        return res>0?true:false;
    }

    @Override
    public boolean enableCommunityPhone(CommunityPhone communityPhone) {
        int res = communityPhoneMapper.enableCommunityPhone(communityPhone);
        return res>0?true:false;
    }

    @Override
    public boolean deleteCommunityPhone(CommunityPhone communityPhone) {
        int res = communityPhoneMapper.deleteCommunityPhone(communityPhone);
        return res>0?true:false;
    }

    @Override
    public List<UserAreaVo> selectAll(Long userId) {
        return userAreaMapper.selectAll(userId);
    }

    @Override
    public Long findCount(CommunityPhone communityPhone) {
        return communityPhoneMapper.findCount(communityPhone);
    }

    @Override
    public boolean updateCommunityPhone(CommunityPhone communityPhone) {
       int res=communityPhoneMapper.updateByPrimaryKeySelective(communityPhone);
        return res>0?true:false;
    }

    @Override
    public List<AppCommunityPhoneVo> findPhoneSQ(CommunityPhone communtiyPhone) {
        return communityPhoneMapper.findPhoneSQ(communtiyPhone);
    }

    @Override
    public List<AppCommunityPhoneVo> findPhoneFW(CommunityPhone communtiyPhone) {
        return communityPhoneMapper.findPhoneFW(communtiyPhone);
    }

    
}
