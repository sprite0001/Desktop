package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.CooperationApplicationVo;

public interface CooperationApplicationMapper <T extends Serializable> extends CrudMapper<T>{
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