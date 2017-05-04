package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.JServiceMapper;
import com.wooxun.geekdol.geekstore.model.JService;
import com.wooxun.geekdol.geekstore.service.JServiceService;
import com.wooxun.geekdol.geekstore.vo.JServiceVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月22日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月22日  下午5:00:52 		创建
 *==========================================================
 * 
 */
@Service
public class JServiceServiceImpl extends CrudServiceImpl<JService> 
	implements JServiceService<JService> {
	
	private JServiceMapper<JService> jServiceMapper;
	@Autowired
	public JServiceServiceImpl(JServiceMapper<JService> jServiceMapper) {
		super(jServiceMapper);
		this.jServiceMapper = jServiceMapper;
	}

	@Override
	public List<JServiceVo> findAllJService(JServiceVo jServiceVo) {
		return jServiceMapper.findAllJService(jServiceVo);
	}

	@Override
	public Long findAllJServiceCount(JServiceVo jServiceVo) {
		return jServiceMapper.findAllJServiceCount(jServiceVo);
	}
}
