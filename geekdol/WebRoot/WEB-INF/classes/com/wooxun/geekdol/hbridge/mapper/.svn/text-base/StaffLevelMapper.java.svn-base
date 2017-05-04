package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.StaffLevel;
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
 * 1.  zhangyang	2016年7月26日  上午10:26:14 		创建
 *==========================================================
 * 
 */
public interface StaffLevelMapper <T extends Serializable> extends CrudMapper<T> {
    /**
     * 
     * @Title 内参级别查询
     * @Description 根据页面查询条件，显示列表
     * @author:张洋
     * @CreateDate:2016年7月26日11:38:29
     * @param staffLevelVo
     * @return List集合
     */
    public List<StaffLevel> queryListByParam(StaffLevelVo staffLevelVo);
}
