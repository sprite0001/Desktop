package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.Order;
import com.wooxun.geekdol.geekstore.vo.OrderVo;

public interface OrderMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description  订单的查询列表
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 下午4:21:16
	 * @param orderVo
	 * @return List集合
	 */
	public List<OrderVo> findAllOrder(OrderVo orderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 订单查询列表的总数
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 下午4:22:59
	 * @param orderVo
	 * @return Long
	 */
	public Long findAllOrderCount(OrderVo orderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 根据订单ID查询订单信息
	 * @author:田振兴
	 * @CreateDate:2016年7月31日 下午7:14:14
	 * @param orderVo
	 * @return Order
	 */
	public Order findOrder(OrderVo orderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 根据订单ID查询订单信息
	 * @author:田振兴
	 * @CreateDate:2016年7月31日 下午7:14:14
	 * @param orderVo
	 * @return OrderVo
	 */
	public OrderVo findOrderInit(OrderVo orderVo);
	
	/**
	 * 
	 * @Title
	 * @Description 合作店详情之订单查询
	 * @author:YK
	 * @CreateDate:2016年7月31日 下午3:56:53
	 * @param orderVo
	 * @return List<OrderVo>
	 */
	public List<OrderVo> queryOrderByStore(OrderVo orderVo);
	
	/**
	 * 
	 * @Title
	 * @Description 查询合作店订单数量
	 * @author:YK
	 * @CreateDate:2016年7月31日 下午3:57:42
	 * @param orderVo
	 * @return Long
	 */
	public Long queryOrderCountByStore(OrderVo orderVo);
   
}