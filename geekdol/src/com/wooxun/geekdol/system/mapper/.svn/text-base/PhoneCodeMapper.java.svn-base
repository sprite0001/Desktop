package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;

/**
 * 验证码mapper
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月22日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月22日  下午3:06:57 		创建
*==========================================================
*
 */
public interface PhoneCodeMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 查询验证码是否存在
	 * @author:kangtianyu
	 * @CreateDate:2016年8月22日 下午4:05:07
	 * @param param
	 * @return
	 */
	Integer selectCountCode(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 查询ip当天发送的
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 上午11:03:52
	 * @param param
	 * @return
	 */
	Integer selectIpByDay(Map<String, Object> param);

	/**
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 上午11:04:08
	 * @param param
	 * @return
	 */
	Integer selectPhoneByDay(Map<String, Object> param);
    
}