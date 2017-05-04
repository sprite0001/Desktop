package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.AdmNewsflashMapper;
import com.wooxun.geekdol.hbridge.model.AdmNewsflash;
import com.wooxun.geekdol.hbridge.service.AdmNewsflashService;
import com.wooxun.geekdol.hbridge.vo.AdmNewsflashVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月2日09:43:59
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月2日09:43:551 		创建
 *==========================================================
 * 
 */
@Service
public class AdmNewsflashServiceImpl extends CrudServiceImpl<AdmNewsflash> implements AdmNewsflashService<AdmNewsflash>{
    
    private AdmNewsflashMapper<AdmNewsflash> admNewsflashMapper;
    
    @Autowired
    public AdmNewsflashServiceImpl(AdmNewsflashMapper<AdmNewsflash> admNewsflashMapper) {
        super(admNewsflashMapper);
        this.admNewsflashMapper = admNewsflashMapper;
    }

    @Override
    public List<AdmNewsflash> queryListByParam(AdmNewsflashVo admNewsflashVo) {
        return admNewsflashMapper.queryListByParam(admNewsflashVo);
    }

    @Override
    public List<AdmNewsflash> queryListByParamOrder(
            AdmNewsflashVo admNewsflashVo) {
        return admNewsflashMapper.queryListByParamOrder(admNewsflashVo);
    }

    @Override
    public Long queryCountByParam(AdmNewsflashVo admNewsflashVo) {
        return admNewsflashMapper.queryCountByParam(admNewsflashVo);
    }

    @Override
    public List<AdmNewsflashVo> queryListNotDel(AdmNewsflashVo admNewsflashVo) {
        return admNewsflashMapper.queryListNotDel(admNewsflashVo);
    }

    @Override
    public Long queryCountNotDel(AdmNewsflashVo admNewsflashVo) {
        return admNewsflashMapper.queryCountNotDel(admNewsflashVo);
    }
}
