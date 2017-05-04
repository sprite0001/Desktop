package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GroupBuyOrder;
import com.wooxun.geekdol.geekstore.vo.GroupBuyOrderVo;

public interface GroupBuyOrderMapper <T extends Serializable> extends CrudMapper<T>{

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
	public GroupBuyOrderVo findGroupBuyOrderInit(GroupBuyOrderVo GroupBuyOrderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description  根据团购订单ID查询订单
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 上午10:08:23
	 * @param GroupBuyOrderVo
	 * @return
	 */
	public GroupBuyOrder findGroupBuyOrder(GroupBuyOrderVo GroupBuyOrderVo);
	
	/**
	 * 
	 * @Title 吉客店管理-团购订单管理
	 * @Description 根据条件找到所有的订单
	 * @author:田振兴
	 * @CreateDate:2016年8月26日 下午2:48:51
	 * @param GroupBuyOrderVo
	 * @return
	 */
	public List<GroupBuyOrder> findAllOrder(GroupBuyOrderVo GroupBuyOrderVo);
}