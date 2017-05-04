package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.StaffLevelMapper;
import com.wooxun.geekdol.hbridge.model.StaffLevel;
import com.wooxun.geekdol.hbridge.service.StaffLevelService;
import com.wooxun.geekdol.hbridge.vo.StaffLevelVo;

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
 * 1.  zhangyang	2016年7月26日  上午10:33:28 		创建
 *==========================================================
 * 
 */

@Service
public class StaffLevelServiceImpl extends CrudServiceImpl<StaffLevel> implements StaffLevelService<StaffLevel>{
    
    private StaffLevelMapper<StaffLevel> staffLevelMapper;
    
    @Autowired
    public StaffLevelServiceImpl(StaffLevelMapper<StaffLevel> staffLevelMapper) {
        super(staffLevelMapper);
        this.staffLevelMapper = staffLevelMapper;
    }

    @Override
    public List<StaffLevel> queryListByParam(StaffLevelVo staffLevelVo) {
        return staffLevelMapper.queryListByParam(staffLevelVo);
    }
}
