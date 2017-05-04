package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.vo.QuartersVo;

/**
 * 
* @Title
* @Description 系统设置-小区信息查询Service
* @version 1.0.0
* @Author YK	
* @CreateDate 2016年7月21日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 YK             	2016年7月21日  下午4:34:48 		创建
*==========================================================
*
 */
public interface QuartersService <T extends Serializable> extends CrudService<T>{
	
	/**
	 * 
	 * @Title
	 * @Description 后台查询小区列表
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午4:39:11
	 * @param searchVillage
	 * @return List
	 */
	public List<QuartersVo> queryQuartersByParams(QuartersVo searchVillage);
	
	/**
	 * 
	 * @Title
	 * @Description 后台查询小区条数
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午4:39:20
	 * @param searchVillage
	 * @return Long
	 */
	public Long queryQuartersCountByParams(QuartersVo searchVillage);
	
	
}

