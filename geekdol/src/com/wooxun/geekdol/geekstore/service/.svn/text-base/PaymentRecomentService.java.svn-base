package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.vo.PaymentRecomentVo;

/**
 * @Title 
 * @Description 支付流水
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月8日  下午4:12:59 		创建
 *==========================================================
 * 
 */
public interface PaymentRecomentService <T extends Serializable> extends CrudService<T>{
		
	/**
	 * 
	 * @Title 支付流水
	 * @Description 根据条件查询支付流水的列表
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 下午3:44:27
	 * @param PaymentRecomentVo
	 * @return
	 */
	public List<PaymentRecomentVo> findAll(PaymentRecomentVo PaymentRecomentVo);
	
	/**
	 * 
	 * @Title 支付流水
	 * @Description 根据条件查询支付流水的列表总条数
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 下午3:44:27
	 * @param PaymentRecomentVo
	 * @return
	 */
	public Long findAllCount(PaymentRecomentVo PaymentRecomentVo);
}
