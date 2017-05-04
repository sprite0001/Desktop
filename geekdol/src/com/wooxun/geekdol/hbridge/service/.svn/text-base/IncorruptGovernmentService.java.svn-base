package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernment;
import com.wooxun.geekdol.hbridge.model.IncorruptRecommend;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月8日  下午2:16:54 		创建
 *==========================================================
 * 
 */
public interface IncorruptGovernmentService <T extends Serializable> extends CrudService<T> {
	
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
	 * @Description 保存风清气正
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午5:48:46
	 * @param incorruptGovernment
	 * @return boolean
	 */
	public boolean insertIncorruptGovernment(IncorruptGovernment incorruptGovernment);
	
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
	 * @Description 根据主键查找风清气正
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午10:52:48
	 * @param id
	 * @return IncorruptGovernment
	 */
	public IncorruptGovernment findIncorruptGovernment(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 更新风清气正信息
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午11:02:45
	 * @param incorruptGovernment
	 * @param recommend
	 * @return boolean
	 */
	public boolean updateIncorruptGovernment(IncorruptGovernment incorruptGovernment,IncorruptRecommend recommend);
	
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
