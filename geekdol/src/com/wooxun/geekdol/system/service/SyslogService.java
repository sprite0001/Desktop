package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.vo.SyslogVo;

/**
 * @Title 日志管理
 * @Description 日志的查询以及日志的service
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月21日  上午10:14:09 		创建
 *==========================================================
 * 
 */
public interface SyslogService <T extends Serializable> extends CrudService<T>{
	
	/**
	 * 
	 * @Title 日志查询
	 * @Description 根据页面条件查询，显示列表
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:20:52
	 * @param syslogVo
	 * @return LIST集合
	 */
	public List<Syslog> findAll(SyslogVo syslogVo);
	
	/**
	 * 
	 * @Title 日志查询
	 * @Description 根据页面条件查询，查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:21:30
	 * @param syslogVo
	 * @return Long
	 */
	public Long findAllCount(SyslogVo syslogVo);
	
	/**
	 * 
	 * @Title 日志管理
	 * @Description 添加日志信息(供别人调用)
	 * @author:田振兴
	 * @CreateDate:2016年7月23日 上午9:30:17
	 * @param type
	 * @param content
	 * @param table
	 * @param dataId
	 * @param user
	 */
	public void addLog(String type,String content,String table,Long dataId,User user);
}
