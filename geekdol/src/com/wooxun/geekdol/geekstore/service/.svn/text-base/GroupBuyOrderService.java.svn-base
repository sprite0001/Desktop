package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GroupBuyOrder;
import com.wooxun.geekdol.geekstore.model.GroupOrderReturnInfor;
import com.wooxun.geekdol.geekstore.vo.GroupBuyOrderVo;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title 团购管理-团购订单管理
 * @Description 
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月4日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月4日  下午2:06:43 		创建
 *==========================================================
 * 
 */
public interface GroupBuyOrderService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 根据条件查询团购订单列表(按卖家来查)
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午2:21:40
	 * @param GroupBuyOrderVo
	 * @return List
	 */
	public List<GroupBuyOrderVo> findAllGroupBuyOrder(GroupBuyOrderVo GroupBuyOrderVo);
	
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 根据条件查询团购订单列表总条数(按卖家来查)
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午2:21:40
	 * @param GroupBuyOrderVo
	 * @return Long
	 */
	public Long findAllGroupBuyOrderCount(GroupBuyOrderVo GroupBuyOrderVo);
	
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 根据条件查询团购订单列表(按小区来查)
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午2:21:40
	 * @param GroupBuyOrderVo
	 * @return List
	 */
	public List<GroupBuyOrderVo> findAllGroupBuyOrderVillage(GroupBuyOrderVo GroupBuyOrderVo);
	
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 根据条件查询团购订单列表总条数(按小区来查)
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午2:21:40
	 * @param GroupBuyOrderVo
	 * @return Long
	 */
	public Long findAllGroupBuyOrderVillageCount(GroupBuyOrderVo GroupBuyOrderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 根据团购订单ID查询订单的详细信息
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午4:24:19
	 * @param GroupBuyOrderVo
	 * @return
	 */
	public GroupBuyOrderVo findGroupBuyOrderInit(GroupBuyOrderVo groupBuyOrderVo);
	
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description  根据团购订单ID查询订单
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 上午10:08:23
	 * @param GroupBuyOrderVo
	 * @return
	 */
	public GroupBuyOrder findGroupBuyOrder(GroupBuyOrderVo groupBuyOrderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 发货、关闭
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 上午10:35:34
	 * @param groupBuyOrder
	 * @return
	 */
	public int updateGroupBuyOrder(GroupBuyOrder groupBuyOrder);
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 退款
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 下午2:31:39
	 * @param groupBuyOrder
	 * @return
	 */
	public int updateRefund(GroupBuyOrder groupBuyOrder);
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 退货
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 下午2:31:39
	 * @param groupBuyOrder
	 * @return
	 */
	public int updateReturnGoods(GroupBuyOrder groupBuyOrder);
	
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
	 * @Title
	 * @Description 根据订单ID和表名、类型查找附件表信息
	 * @author:田振兴
	 * @CreateDate:2016年8月16日 下午4:52:58
	 * @param attach
	 * @return
	 */
	public List<Attach> attachList(Attach attach);
	
	
}
