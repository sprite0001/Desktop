package com.wooxun.geekdol.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.service.AttachService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 张洋	
 * @CreateDate 2016年9月18日15:01:06
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 张洋	2016年9月18日15:01:11 		创建
 *==========================================================
 * 
 */
@Service
public class AttachServiceImpl extends CrudServiceImpl<Attach> implements AttachService<Attach> {
	
	private AttachMapper<Attach> attachMapper;
	
	@Autowired
	public AttachServiceImpl(AttachMapper<Attach> attachMapper) {
		super(attachMapper);
		this.attachMapper = attachMapper;
	}

    @Override
    public int insertBatch(List<Attach> list) {
        return attachMapper.insertBatch(list);
    }

    @Override
    public int insertBatchByMap(Map<String, Object> sqlParam) {
        return attachMapper.insertBatchByMap(sqlParam);
    }

    @Override
    public int deleteByParam(Attach attachTemp) {
        return attachMapper.deleteByParam(attachTemp);
    }

    @Override
    public List<Attach> selectAttachByParam(Attach attach) {
        return attachMapper.selectAttachByParam(attach);
    }
}
