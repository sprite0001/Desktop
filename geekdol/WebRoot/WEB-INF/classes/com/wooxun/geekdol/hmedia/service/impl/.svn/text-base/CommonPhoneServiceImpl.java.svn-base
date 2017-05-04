package com.wooxun.geekdol.hmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.CommonPhoneMapper;
import com.wooxun.geekdol.hmedia.model.CommonPhone;
import com.wooxun.geekdol.hmedia.service.CommonPhoneService;
import com.wooxun.geekdol.hmedia.vo.AppCommonPhoneVo;
import com.wooxun.geekdol.hmedia.vo.CommonPhoneVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月29日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月29日  下午4:39:22 		创建
 *==========================================================
 * 
 */
@Service
public class CommonPhoneServiceImpl extends CrudServiceImpl<CommonPhone> implements CommonPhoneService<CommonPhone>{
    
    @Autowired
    private CommonPhoneMapper<CommonPhone> commonPhoneMapper;
    
    @Autowired
    public CommonPhoneServiceImpl(CommonPhoneMapper<CommonPhone> commonPhoneMapper) {
         super(commonPhoneMapper);
         this.commonPhoneMapper=commonPhoneMapper;
    }

    @Override
    public List<CommonPhoneVo> findAll(CommonPhoneVo commonPhoneVo) {
        return commonPhoneMapper.findAll(commonPhoneVo);
    }

    @Override
    public Long findAllCount(CommonPhoneVo commonPhoneVo) {
        return commonPhoneMapper.findAllCount(commonPhoneVo);
    }

    @Override
    public Long findCount(CommonPhone commonPhone) {
        return commonPhoneMapper.findCount(commonPhone);
    }

    @Override
    public boolean updateCommonPhone(CommonPhone commonPhone) {
        int result=commonPhoneMapper.updateByPrimaryKeySelective(commonPhone);
        return result>0?true:false;
    }

    @Override
    public List<AppCommonPhoneVo> findCommonPhone() {
        return commonPhoneMapper.findCommonPhone();
    }
   
}
