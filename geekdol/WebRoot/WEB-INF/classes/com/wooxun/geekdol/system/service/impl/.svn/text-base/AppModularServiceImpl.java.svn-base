package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.AppModularMapper;
import com.wooxun.geekdol.system.model.AppModular;
import com.wooxun.geekdol.system.service.AppModularService;
import com.wooxun.geekdol.system.vo.AppModularVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 张洋
 * @CreateDate 2016年8月8日14:44:44
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员           修改日期                描述
 * 1.    张洋    2016年8月8日14:44:51       创建
 *==========================================================
 * 
 */
@Service
public class AppModularServiceImpl extends CrudServiceImpl<AppModular> implements AppModularService<AppModular> {
	
	
	private AppModularMapper<AppModular> appModularMapper;
	
	@Autowired
	public AppModularServiceImpl(AppModularMapper<AppModular> appModularMapper) {
		super(appModularMapper);
		this.appModularMapper = appModularMapper;
	}

    @Override
    public List<AppModular> queryListByParam(AppModularVo appModularVo) {
        return appModularMapper.queryListByParam(appModularVo);
    }
	
}
