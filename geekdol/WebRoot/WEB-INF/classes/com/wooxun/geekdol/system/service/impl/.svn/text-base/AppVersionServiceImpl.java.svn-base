package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.AppVersionMapper;
import com.wooxun.geekdol.system.model.AppVersion;
import com.wooxun.geekdol.system.service.AppVersionService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月1日  下午4:12:21 		创建
 *==========================================================
 * 
 */
@Service
public class AppVersionServiceImpl extends CrudServiceImpl<AppVersion> implements AppVersionService<AppVersion>{
    
    private AppVersionMapper<AppVersion> appVersionMapper;
    
    @Autowired
    public AppVersionServiceImpl(AppVersionMapper<AppVersion> appVersionMapper) {
        super(appVersionMapper);
        this.appVersionMapper = appVersionMapper;
    }

    @Override
    public List<AppVersion> selectAll(AppVersion appVersion) {
        return appVersionMapper.selectAll(appVersion);
    }

    @Override
    public AppVersion selectByName(String name) {
        return appVersionMapper.selectByName(name);
    }
}
