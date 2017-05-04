package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.PaymentRecomentMapper;
import com.wooxun.geekdol.geekstore.model.PaymentRecoment;
import com.wooxun.geekdol.geekstore.service.PaymentRecomentService;
import com.wooxun.geekdol.geekstore.vo.PaymentRecomentVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 万通	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年8月8日  下午4:15:24 		创建
 *==========================================================
 * 
 */
@Service
public class PaymentRecomentServiceImpl extends CrudServiceImpl<PaymentRecoment> 
	implements PaymentRecomentService<PaymentRecoment>{
	@Autowired
	private PaymentRecomentMapper<PaymentRecoment> paymentRecomentMapper;
	@Autowired
	public PaymentRecomentServiceImpl(PaymentRecomentMapper<PaymentRecoment> paymentRecomentMapper) {
		super(paymentRecomentMapper);
		this.paymentRecomentMapper = paymentRecomentMapper;
	}
	@Override
	public List<PaymentRecomentVo> findAll(PaymentRecomentVo PaymentRecomentVo) {
		return paymentRecomentMapper.findAll(PaymentRecomentVo);
	}
	@Override
	public Long findAllCount(PaymentRecomentVo PaymentRecomentVo) {
		return paymentRecomentMapper.findAllCount(PaymentRecomentVo);
	}

}
