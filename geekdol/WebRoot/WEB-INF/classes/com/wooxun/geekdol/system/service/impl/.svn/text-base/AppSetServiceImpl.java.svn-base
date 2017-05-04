package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.AppSetMapper;
import com.wooxun.geekdol.system.model.AppSet;
import com.wooxun.geekdol.system.service.AppSetService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 张洋
 * @CreateDate 2016年8月10日09:36:35
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员           修改日期                描述
 * 1.    张洋    2016年8月10日09:36:35       创建
 *==========================================================
 * 
 */
@Service
public class AppSetServiceImpl extends CrudServiceImpl<AppSet> implements AppSetService<AppSet> {
	
	
	private AppSetMapper<AppSet> appSetMapper;
	
	@Autowired
	public AppSetServiceImpl(AppSetMapper<AppSet> appSetMapper) {
		super(appSetMapper);
		this.appSetMapper = appSetMapper;
	}

    @Override
    public List<AppSet> selectAll(AppSet appSet) {
        return appSetMapper.selectAll(appSet);
    }
	
}
