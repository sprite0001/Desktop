package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.NetizenSecurity;
import com.wooxun.geekdol.hbridge.vo.NetizenSecurityVo;

/**
 * @Title
 * @Description 网安报service接口
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年9月8日  上午10:00:35 		创建
 *==========================================================
 * 
 */
public interface NetizenSecurityService<T extends Serializable> extends CrudService<T> {
	
	/**
	 * @Title
	 * @Description 根据参数查询网安报个数
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午10:23:03
	 * @param netizenSecurityVo
	 * @return
	 */
	public Long findNetizenSecurityCountByParam(NetizenSecurityVo netizenSecurityVo);
	
	/**
	 * @Title
	 * @Description 根据参数查询网安报列表
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午10:20:01
	 * @param netizenSecurityVo
	 * @return
	 */
	public List<NetizenSecurityVo> findNetizenSecurityListByParam(NetizenSecurityVo netizenSecurityVo);

	/**
	 * @Title
	 * @Description 保存网安报信息
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午2:33:42
	 * @param netizenSecurityVo
	 * @return
	 */
	public boolean saveNetizenSecurity(NetizenSecurityVo netizenSecurityVo);

	/**
	 * @Title
	 * @Description 更新网安报信息
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午2:57:09
	 * @param netizenSecurityVo
	 * @return
	 */
	public boolean updateNetizenSecurity(NetizenSecurityVo netizenSecurityVo);

	/**
	 * @Title
	 * @Description 获取网安报Vo对象信息
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午5:17:18
	 * @param id
	 * @return
	 */
	public NetizenSecurityVo getNetizenSecurityVo(Long id);

	/**
	 * @Title
	 * @Description 更新网安报的一些参数
	 * @author:kangtianyu
	 * @CreateDate:2016年9月9日 上午11:12:03
	 * @param netizenSecurity
	 * @return
	 */
	public Integer updateNetizenSecurityAnyParam(NetizenSecurity netizenSecurity);

	/**
	 * @Title
	 * @Description 根据参数查询推荐网安报列表
	 * @author:kangtianyu
	 * @CreateDate:2016年9月14日 下午2:35:40
	 * @param netizenSecurityVo
	 * @return
	 */
	public List<NetizenSecurityVo> findHotNetizenSecurityListByParam(
			NetizenSecurityVo netizenSecurityVo);
	
}
