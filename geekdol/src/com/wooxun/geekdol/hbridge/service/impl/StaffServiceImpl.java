package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.StaffMapper;
import com.wooxun.geekdol.hbridge.model.Staff;
import com.wooxun.geekdol.hbridge.service.StaffService;
import com.wooxun.geekdol.hbridge.vo.StaffVo;

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
 * 1.  zhangyang	2016年7月26日  上午10:34:55 		创建
 *==========================================================
 * 
 */

@Service
public class StaffServiceImpl extends CrudServiceImpl<Staff> implements StaffService<Staff>{

    private StaffMapper<Staff> staffMapper;
    
    @Autowired
    public StaffServiceImpl(StaffMapper<Staff> staffMapper) {
        super(staffMapper);
        this.staffMapper = staffMapper;
    }

    @Override
    public List<Staff> queryListByParam(StaffVo staffVo) {
        return staffMapper.queryListByParam(staffVo);
    }

    @Override
    public List<Staff> queryListByParamOrder(StaffVo staffVo) {
        return staffMapper.queryListByParamOrder(staffVo);
    }

    @Override
    public Long queryCountByParam(StaffVo staffVo) {
        return staffMapper.queryCountByParam(staffVo);
    }
}
