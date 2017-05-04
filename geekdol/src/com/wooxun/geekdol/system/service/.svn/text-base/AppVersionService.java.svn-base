package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.AppVersion;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月2日09:41:32
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月2日09:41:32 		创建
 *==========================================================
 * 
 */
public interface AppVersionService <T extends Serializable> extends CrudService<T>{

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
