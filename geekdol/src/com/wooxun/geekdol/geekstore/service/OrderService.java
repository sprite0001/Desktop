package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.Order;
import com.wooxun.geekdol.geekstore.model.OrderReturnInfor;
import com.wooxun.geekdol.geekstore.vo.OrderVo;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title 吉客店管理-商品管理-订单管理
 * @Description 
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月25日  下午4:16:11 		创建
 *==========================================================
 * 
 */
public interface OrderService <T extends Serializable> extends CrudService<T>{
		
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
	 * @author: 田振兴
	 * @CreateDate:2016年7月25日 下午4:22:59
	 * @param orderVo
	 * @return Long
	 */
	public Long findAllOrderCount(OrderVo orderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 订单详情查看
	 * @author:田振兴
	 * @CreateDate:2016年7月27日 上午9:30:23
	 * @param orderVo
	 * @return OrderVo
	 */
	public OrderVo findOrderInit(OrderVo orderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 根据订单ID查询订单详情
	 * @author:田振兴
	 * @CreateDate:2016年7月27日 上午9:30:23
	 * @param orderVo
	 * @return Order
	 */
	public Order findOrder(OrderVo orderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 根据订单ID查询订单订单退货详情
	 * @author:田振兴
	 * @CreateDate:2016年7月27日 上午9:30:23
	 * @param orderId
	 * @return OrderReturnInfor
	 */
	public OrderReturnInfor findOrderReturnInfor(Long orderId);
	
	/**
	 * 
	 * @Title 吉客店管理-商品管理-订单管理
	 * @Description 订单发货、关闭
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 下午1:49:59
	 * @param order
	 * @return int
	 */
	public int updateOrder(Order order);
	
	/**
	 * 
	 * @Title  吉客店管理-商品管理-订单管理
	 * @Description 订单退货
	 * @author:田振兴
	 * @CreateDate:2016年7月29日 上午9:18:04
	 * @param order
	 * @return int
	 */
	public int updateReturnGoods(Order order);
	
	/**
	 * 
	 * @Title  吉客店管理-商品管理-订单管理
	 * @Description 订单退款
	 * @author:田振兴
	 * @CreateDate:2016年7月29日 下午3:14:25
	 * @param order
	 * @return int
	 */
	public int updateRefund(Order order);
	
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
	
	/**
	 * 
	 * @Title
	 * @Description 根据订单ID和表名、类型查找附件表信息
	 * @author:田振兴
	 * @CreateDate:2016年8月16日 下午4:52:58
	 * @param attach
	 * @return List
	 */
	public List<Attach> attachList(Attach attach);
}
