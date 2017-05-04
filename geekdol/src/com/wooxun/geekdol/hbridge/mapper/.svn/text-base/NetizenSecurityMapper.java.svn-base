package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.NetizenSecurity;
import com.wooxun.geekdol.hbridge.vo.NetizenSecurityVo;

/**
 * 网安报mapper类
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年9月8日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年9月8日  上午9:46:33 		创建
*==========================================================
*
 */
public interface NetizenSecurityMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 根据网安报相关参数查询网安报的总数量
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午10:34:31
	 * @param netizenSecurityVo
	 * @return
	 */
	public Long selectNetizenSecurityCountByParam(NetizenSecurityVo netizenSecurityVo);

	/**
	 * @Title
	 * @Description 根据网安报相关参数查询网安报列表
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午10:35:18
	 * @param netizenSecurityVo
	 * @return
	 */
	public List<NetizenSecurityVo> selectNetizenSecurityListByParam(
			NetizenSecurityVo netizenSecurityVo);

	/**
	 * @Title
	 * @Description 更新网安报相关参数
	 * @author:kangtianyu
	 * @CreateDate:2016年9月9日 上午11:13:41
	 * @param netizenSecurity
	 * @return
	 */
	public Integer updateNetizenSecurityAnyParam(NetizenSecurity netizenSecurity);

	/**
	 * @Title
	 * @Description 根据网安报相关参数查询网安报列表
	 * @author:kangtianyu
	 * @CreateDate:2016年9月14日 下午2:37:02
	 * @param netizenSecurityVo
	 * @return
	 */
	public List<NetizenSecurityVo> selectHotNetizenSecurityListByParam(
			NetizenSecurityVo netizenSecurityVo);
	
}