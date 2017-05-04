package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GroupOrderReturnInfor;

public interface GroupOrderReturnInforMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 根据订单ID查询退货的信息
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 下午2:20:35
	 * @param orderId
	 * @return
	 */
	public GroupOrderReturnInfor findGroupBuyOrderReturnInfor(Long orderId);
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 根据订单ID修改退货和退款的信息
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 下午2:22:06
	 * @param groupOrderReturnInfor
	 * @return
	 */
	public int updateByOrderIdSelective(GroupOrderReturnInfor groupOrderReturnInfor);
    
}