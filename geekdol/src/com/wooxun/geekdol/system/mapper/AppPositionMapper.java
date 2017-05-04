package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.AppPosition;
import com.wooxun.geekdol.system.vo.AppPositionVo;


public interface AppPositionMapper <T extends Serializable> extends CrudMapper<T> {
    /**
     * 
     * @Title
     * @Description APP功能设置查询
     * @author:张洋
     * @CreateDate:2016年8月8日11:34:44
     * @param appPositionVo
     * @return
     */
    public List<AppPosition> queryListByParam(AppPositionVo appPositionVo);
}