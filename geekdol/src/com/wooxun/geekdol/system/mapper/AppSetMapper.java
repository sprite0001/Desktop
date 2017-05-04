package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.AppSet;

public interface AppSetMapper<T extends Serializable> extends CrudMapper<T> {
    /**
     * 
     * @Title
     * @Description 查询所有app设置
     * @author:张洋
     * @CreateDate:2016年8月10日09:41:45
     * @param appSet
     * @return
     */
    public List<AppSet> selectAll(AppSet appSet);
}