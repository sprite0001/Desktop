package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.AppPositionMapper;
import com.wooxun.geekdol.system.model.AppPosition;
import com.wooxun.geekdol.system.service.AppPositionService;
import com.wooxun.geekdol.system.vo.AppPositionVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 张洋
 * @CreateDate 2016年8月8日10:59:38
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员           修改日期                描述
 * 1.    张洋 2016年8月8日10:59:46       创建
 *==========================================================
 * 
 */
@Service
public class AppPositionServiceImpl extends CrudServiceImpl<AppPosition> implements AppPositionService<AppPosition> {
	
	
	private AppPositionMapper<AppPosition> appPositionMapper;
	
	@Autowired
	public AppPositionServiceImpl(AppPositionMapper<AppPosition> appPositionMapper) {
		super(appPositionMapper);
		this.appPositionMapper = appPositionMapper;
	}

    @Override
    public List<AppPosition> queryListByParam(AppPositionVo appPositionVo) {
        return appPositionMapper.queryListByParam(appPositionVo);
    }
	
}
