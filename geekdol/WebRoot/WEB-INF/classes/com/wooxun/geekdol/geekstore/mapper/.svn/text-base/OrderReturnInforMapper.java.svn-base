package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.OrderReturnInfor;

public interface OrderReturnInforMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * @Title 订单管理
	 * @Description 退货信息的修改
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 下午5:26:25
	 * @param orderReturnInfor
	 * @return int(判断是否修改成功，1为成功，0为失败)
	 */
	public int updateByOrderIdSelective(OrderReturnInfor orderReturnInfor);
	
	/**
	 * @Title 订单管理
	 * @Description 根据订单ID查找订单退货的信息
	 * @author:田振兴
	 * @CreateDate:2016年7月29日 上午9:50:55
	 * @param orderId
	 * @return OrderReturnInfor
	 */
	public OrderReturnInfor findOrderReturnInfor(Long orderId);
    
}