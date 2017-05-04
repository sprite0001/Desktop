package com.wooxun.geekdol.geekstore.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.OrderMapper;
import com.wooxun.geekdol.geekstore.mapper.OrderReturnInforMapper;
import com.wooxun.geekdol.geekstore.model.Order;
import com.wooxun.geekdol.geekstore.model.OrderReturnInfor;
import com.wooxun.geekdol.geekstore.service.OrderService;
import com.wooxun.geekdol.geekstore.vo.OrderVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title
 * @Description 订单管理
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月25日  下午5:15:45 		创建
 *==========================================================
 * 
 */
@Service
public class OrderServiceImpl extends CrudServiceImpl<Order> implements OrderService<Order> {
	@Autowired
	private OrderMapper<Order> orderMapper;
	@Autowired
	private OrderReturnInforMapper<OrderReturnInfor> OrderReturnInforMapper;
	@Autowired
	private AttachMapper<Attach> attachMapper;
	@Autowired
	public OrderServiceImpl(OrderMapper<Order> orderMapper) {
		super(orderMapper);
		this.orderMapper = orderMapper;
	}
	@Override
	public List<OrderVo> findAllOrder(OrderVo orderVo){
		return orderMapper.findAllOrder(orderVo);
	}
	@Override
	public Long findAllOrderCount(OrderVo orderVo) {
		return orderMapper.findAllOrderCount(orderVo);
	}
	@Override
	public Order findOrder(OrderVo orderVo){
		
		return  orderMapper.findOrder(orderVo);
	}
	@Override
	public int updateOrder(Order order){
		
		return orderMapper.updateByPrimaryKeySelective(order);
	}
	@Override
	public int updateReturnGoods(Order order) {	
		//创建退货对象
		OrderReturnInfor orderReturnInfor = new OrderReturnInfor();
		//根据ID找订单退货信息
		orderReturnInfor = OrderReturnInforMapper.findOrderReturnInfor(order.getOrderId());
		//如果没有订单的退货信息，返回退货失败
		if(orderReturnInfor==null){
			return 0;
		}
		//如果有订单的退货信息，给退货对象赋值（设置公共属性，处理结果，退货地址，退货理由）
		orderReturnInfor.setUpdEac(orderReturnInfor.getUpdEac()+1);
		orderReturnInfor.setUpdYmdhms(new Date());
		orderReturnInfor.setUpdId(order.getUpdId());
		orderReturnInfor.setDetailResult(order.getOrderReturnInfor().getDetailResult());
		orderReturnInfor.setReturnAdress(order.getOrderReturnInfor().getReturnAdress());
		orderReturnInfor.setDetailResultReason(order.getOrderReturnInfor().getDetailResultReason());
		//改变退货信息
		OrderReturnInforMapper.updateByOrderIdSelective(orderReturnInfor);
		//设置订单对象的状态
		order.setStatus(orderReturnInfor.getDetailResult().toString());
		//改变订单信息
		return orderMapper.updateByPrimaryKeySelective(order);
	}
	@Override
	public int updateRefund(Order order){	
		//创建退货对象
		OrderReturnInfor orderReturnInfor = new OrderReturnInfor();
		//根据ID找订单退货信息
		orderReturnInfor = OrderReturnInforMapper.findOrderReturnInfor(order.getOrderId());
		//如果没有订单的退货信息，返回退款失败
		if(orderReturnInfor==null){
			return 0;
		}
		//如果有订单的退货信息，给退货对象赋值（设置公共属性，处理结果，退款金额，退款理由）
		orderReturnInfor.setUpdEac(orderReturnInfor.getUpdEac()+1);
		orderReturnInfor.setUpdYmdhms(new Date());
		orderReturnInfor.setUpdId(order.getUpdId());
		orderReturnInfor.setDetailResult(order.getOrderReturnInfor().getDetailResult());
		orderReturnInfor.setReturnMoney(order.getOrderReturnInfor().getReturnMoney());
		orderReturnInfor.setReturnMoneyMark(order.getOrderReturnInfor().getReturnMoneyMark());
		//改变退货信息的数据
		OrderReturnInforMapper.updateByOrderIdSelective(orderReturnInfor);
		//设置订单对象的状态
		order.setStatus(orderReturnInfor.getDetailResult());
		//改变订单信息
		return orderMapper.updateByPrimaryKeySelective(order);
	}
	@Override
	public OrderVo findOrderInit(OrderVo orderVo) {
		return  orderMapper.findOrderInit(orderVo);
	}
	@Override
	public OrderReturnInfor findOrderReturnInfor(Long orderId) {
		return OrderReturnInforMapper.findOrderReturnInfor(orderId);
	}
	@Override
	public List<OrderVo> queryOrderByStore(OrderVo orderVo) {
		orderVo.setPayModel(ConstantStr.PAYMODEL);
		return orderMapper.queryOrderByStore(orderVo);
	}
	@Override
	public Long queryOrderCountByStore(OrderVo orderVo) {
		return orderMapper.queryOrderCountByStore(orderVo);
	}
	@Override
	public List<Attach> attachList(Attach attach) {
		return attachMapper.selectAttachByParam(attach);
	}
}
