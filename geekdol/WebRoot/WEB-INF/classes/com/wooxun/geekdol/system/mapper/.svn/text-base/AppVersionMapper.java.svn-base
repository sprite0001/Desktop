package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.AppVersion;


public interface AppVersionMapper <T extends Serializable> extends CrudMapper<T>{
    /**
     * 
     * @Title
     * @Description 查询全部
     * @author:张洋
     * @CreateDate:2016年8月9日11:34:44
     * @param appVersion
     * @return
     */
    public List<AppVersion> selectAll(AppVersion appVersion);
    /**
     * 
     * @Title
     * @Description 根据name查询数据
     * @author:张洋
     * @CreateDate:2016年8月9日11:34:44
     * @param name
     * @return
     */
    public AppVersion selectByName(String name);
}