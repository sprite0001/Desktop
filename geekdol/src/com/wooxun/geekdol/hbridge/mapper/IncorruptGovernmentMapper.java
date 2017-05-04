package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentVo;

public interface IncorruptGovernmentMapper <T extends Serializable> extends CrudMapper<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 获取清风气正条数
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午2:36:00
	 * @param incorruptGovernmentVo
	 * @return Long
	 */
	public Long findIncorruptGovernmentCount(IncorruptGovernmentVo incorruptGovernmentVo);
	
	/**
	 * 
	 * @Title
	 * @Description 获取清风气正列表数据
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午2:37:03
	 * @param incorruptGovernmentVo
	 * @return
	 */
	public List<IncorruptGovernmentVo> findIncorruptGovernment(IncorruptGovernmentVo incorruptGovernmentVo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据主键查找风清气正信息
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午9:48:04
	 * @param incorruptGovernmentVo
	 * @return IncorruptGovernmentVo
	 */
	public IncorruptGovernmentVo findById(IncorruptGovernmentVo incorruptGovernmentVo);
	
	/**
	 * 
	 * @Title
	 * @Description 手机端获取风清气正列表
	 * @author:YK
	 * @CreateDate:2016年9月12日 下午2:37:25
	 * @param incorruptGovernmentVo
	 * @return List
	 */
	public List<Map<String, Object>> queryListByParam(IncorruptGovernmentVo incorruptGovernmentVo);
	
	/**
	 * 
	 * @Title
	 * @Description 手机端获取热门风清气正列表
	 * @author:YK
	 * @CreateDate:2016年9月12日 下午2:37:25
	 * @param incorruptGovernmentVo
	 * @return List
	 */
	public List<Map<String, Object>> queryHotListByParam(IncorruptGovernmentVo incorruptGovernmentVo);
}