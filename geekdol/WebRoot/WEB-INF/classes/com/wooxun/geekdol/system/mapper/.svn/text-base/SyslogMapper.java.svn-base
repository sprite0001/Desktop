package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.vo.SyslogVo;

public interface SyslogMapper <T extends Serializable> extends CrudMapper<T>{
	
	
	/**
	 * 
	 * @Title 日志管理
	 * @Description 根据页面条件查询，显示列表
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:20:52
	 * @param syslogVo
	 * @return 返回LIST集合
	 */
	public List<Syslog> findAll(SyslogVo syslogVo);
	
	
	/**
	 * 
	 * @Title 日志管理
	 * @Description 根据页面条件查询，查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:21:30
	 * @param syslogVo
	 * @return Long
	 */
	public Long findAllCount(SyslogVo syslogVo);
}