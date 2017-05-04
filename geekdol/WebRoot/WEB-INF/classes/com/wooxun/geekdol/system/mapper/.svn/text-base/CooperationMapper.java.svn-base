package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Cooperation;
import com.wooxun.geekdol.system.vo.CooperationVo;

public interface CooperationMapper <T extends Serializable> extends CrudMapper<T>{
	
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
	 * @Description 根据页面条件查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:21:30
	 * @param cooperationVo
	 * @return Long
	 */
	public Long findAllCount(CooperationVo cooperationVo);
}