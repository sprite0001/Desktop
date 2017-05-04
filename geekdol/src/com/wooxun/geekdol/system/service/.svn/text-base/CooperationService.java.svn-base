package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.Cooperation;
import com.wooxun.geekdol.system.vo.CooperationVo;


/**
 * @Title 我要合作
 * @Description 我要合作查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午3:11:56 		创建
 *==========================================================
 * 
 */
public interface CooperationService <T extends Serializable> extends CrudService<T>{
	
	
	/**
	 * 
	 * @Title 我要合作
	 * @Description 根据页面条件查询，显示列表
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:20:52
	 * @param cooperationVo
	 * @return List集合
	 */
	public List<Cooperation> findAll(CooperationVo cooperationVo);
	
	/**
	 * 
	 * @Title 我要合作
	 * @Description 根据页面条件查询，查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:21:30
	 * @param cooperationVo
	 * @return Long
	 */
	public Long findAllCount(CooperationVo cooperationVo);
}
