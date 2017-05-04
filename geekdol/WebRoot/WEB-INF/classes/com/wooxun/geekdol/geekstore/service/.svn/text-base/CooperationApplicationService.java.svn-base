package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.CooperationApplication;
import com.wooxun.geekdol.geekstore.vo.CooperationApplicationVo;

/**
 * @Title
 * @Description 合作店申请
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月21日  下午5:50:18 		创建
 *==========================================================
 * 
 */
public interface CooperationApplicationService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title
	 * @Description: 新增合作店申请
	 * @author:YK
	 * @CreateDate:2016年7月15日 下午4:39:57
	 * @param cooperationApplication
	 */
	public void insertCooperationApplication(CooperationApplication cooperationApplication);
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询: 申请列表查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:32:09
	 * @param cooperationApplicationVo
	 * @return List
	 */
	public List<CooperationApplicationVo> findAllCooperationApplication(CooperationApplicationVo cooperationApplicationVo);
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询: 申请列表总数查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:32:49
	 * @param cooperationApplicationVo
	 * @return Long
	 */
	public Long findAllCooperationApplicationCount(CooperationApplicationVo cooperationApplicationVo);
	
	/**
	 * 
	 * @Title 合作店申请查询
	 * @Description  根据条件查询合作店申请的列表
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 上午9:52:09
	 * @param cooperationApplicationVo
	 * @return List集合
	 */
	public List<CooperationApplicationVo> findCooperationApplication(CooperationApplicationVo cooperationApplicationVo);
	
	
	/**
	 * 
	 * @Title 合作店申请查询
	 * @Description 根据条件查询合作店申请的总数
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 上午9:55:18
	 * @param cooperationApplicationVo
	 * @return Long
	 */
	public Long findCooperationApplicationCount(CooperationApplicationVo cooperationApplicationVo);
}
