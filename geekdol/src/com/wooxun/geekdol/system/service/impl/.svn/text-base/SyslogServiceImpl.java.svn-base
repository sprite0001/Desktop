package com.wooxun.geekdol.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.SyslogMapper;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.SyslogVo;

/**
 * @Title 日志管理
 * @Description 日志的查询以及日志的service
 * @version 1.0.0
 * @Author田振兴	
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月21日  上午10:18:24 		创建
 *==========================================================
 * 
 */
@Service
public class SyslogServiceImpl extends CrudServiceImpl<Syslog> implements SyslogService<Syslog>{
	
	@Autowired
	private SyslogMapper<Syslog> syslogMapper;
	@Autowired
	public SyslogServiceImpl(SyslogMapper<Syslog> syslogMapper) {
		super(syslogMapper);
		this.syslogMapper = syslogMapper;
	}
	@Override
	public List<Syslog> findAll(SyslogVo syslogVo) {
		return syslogMapper.findAll(syslogVo);
	}
	@Override
	public Long findAllCount(SyslogVo syslogVo) {
		return syslogMapper.findAllCount(syslogVo);
	}
	
	
	/**
	 * 
	 * @Title 日志管理
	 * @Description 日志的service(供别人调用)
	 * @author:田振兴
	 * @CreateDate:2016年7月23日 上午9:48:21
	 * @param type
	 * @param content
	 * @param table
	 * @param dataId
	 * @param user
	 */
	@Override
	public void addLog(String type,String content,String table,Long dataId,User user){
		Syslog syslog = new Syslog();
		Long userId = (Long) user.getId();
		String username = user.getUserName();
		syslog.setUserName(username);
		syslog.setOptType(type);
		syslog.setExceptionContent(content);
		syslog.setOptTableName(table);
		String optDataId ="";
		if(dataId !=null){
			optDataId = dataId.toString();
		}
		syslog.setOptDataId(optDataId);
		syslog.setInsId(userId);
		syslog.setUpdId(userId);
		this.addAttr(syslog);
		syslogMapper.insertSelective(syslog);
	}
	
	
	/**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void addAttr(Syslog _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag("0");
	}
	
}
