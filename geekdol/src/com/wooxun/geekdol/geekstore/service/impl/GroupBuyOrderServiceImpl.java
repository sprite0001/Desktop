package com.wooxun.geekdol.geekstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GroupBuyOrderMapper;
import com.wooxun.geekdol.geekstore.mapper.GroupOrderReturnInforMapper;
import com.wooxun.geekdol.geekstore.model.GroupBuyOrder;
import com.wooxun.geekdol.geekstore.model.GroupOrderReturnInfor;
import com.wooxun.geekdol.geekstore.service.GroupBuyOrderService;
import com.wooxun.geekdol.geekstore.vo.GroupBuyOrderVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title 团购订单管理
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月4日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月4日  下午2:09:16 		创建
 *==========================================================
 * 
 */
@Service
public class GroupBuyOrderServiceImpl extends CrudServiceImpl<GroupBuyOrder> 
	implements GroupBuyOrderService<GroupBuyOrder>{
	
	@Autowired
	private GroupBuyOrderMapper<GroupBuyOrder> groupBuyOrderMapper;
	@Autowired
	private GroupOrderReturnInforMapper<GroupOrderReturnInfor> groupOrderReturnInforMapper;
	@Autowired
	private AttachMapper<Attach> attachMapper;
	@Autowired
	public GroupBuyOrderServiceImpl(GroupBuyOrderMapper<GroupBuyOrder> groupBuyOrderMapper) {
		super(groupBuyOrderMapper);
		this.groupBuyOrderMapper = groupBuyOrderMapper;
	}
	@Override
	public List<GroupBuyOrderVo> findAllGroupBuyOrder(
			GroupBuyOrderVo GroupBuyOrderVo) {
		return groupBuyOrderMapper.findAllGroupBuyOrder(GroupBuyOrderVo);
	}
	@Override
	public Long findAllGroupBuyOrderCount(GroupBuyOrderVo GroupBuyOrderVo) {
		return groupBuyOrderMapper.findAllGroupBuyOrderCount(GroupBuyOrderVo);
	}
	@Override
	public GroupBuyOrderVo findGroupBuyOrderInit(GroupBuyOrderVo GroupBuyOrderVo) {
		return groupBuyOrderMapper.findGroupBuyOrderInit(GroupBuyOrderVo);
	}
	@Override
	public GroupBuyOrder findGroupBuyOrder(GroupBuyOrderVo GroupBuyOrderVo) {
		return groupBuyOrderMapper.findGroupBuyOrder(GroupBuyOrderVo);
	}
	@Override
	public int updateGroupBuyOrder(GroupBuyOrder groupBuyOrder) {
		return groupBuyOrderMapper.updateByPrimaryKeySelective(groupBuyOrder);
	}
	@Override
	public int updateRefund(GroupBuyOrder groupBuyOrder) {
		//创建退货对象
		GroupOrderReturnInfor groupOrderReturnInfor = new GroupOrderReturnInfor();
		//根据订单ID查找订单退货信息
		groupOrderReturnInfor = groupOrderReturnInforMapper.findGroupBuyOrderReturnInfor(groupBuyOrder.getId());
		//如果没有订单的退货信息，返回退款失败
		if(groupOrderReturnInfor==null){
			return 0;
		}
		//如果有订单的退货信息，给退货对象赋值（设置公共属性，处理结果，退款金额，退款理由）
		groupOrderReturnInfor.setUpdEac(groupOrderReturnInfor.getUpdEac()+1);
		groupOrderReturnInfor.setUpdYmdhms(new Date());
		groupOrderReturnInfor.setUpdId(groupBuyOrder.getUpdId());
		groupOrderReturnInfor.setDetailResult(groupBuyOrder.getGroupOrderReturnInfor().getDetailResult());
		groupOrderReturnInfor.setReturnMoney(groupBuyOrder.getGroupOrderReturnInfor().getReturnMoney());
		groupOrderReturnInfor.setReturnMoneyMark(groupBuyOrder.getGroupOrderReturnInfor().getReturnMoneyMark());
		//改变退货信息的数据
		groupOrderReturnInforMapper.updateByOrderIdSelective(groupOrderReturnInfor);
		//设置订单对象的状态
		groupBuyOrder.setStatus(groupOrderReturnInfor.getDetailResult().toString());
		//改变订单信息
		return groupBuyOrderMapper.updateByPrimaryKeySelective(groupBuyOrder);
	}
	@Override
	public GroupOrderReturnInfor findGroupBuyOrderReturnInfor(Long orderId) {
		return groupOrderReturnInforMapper.findGroupBuyOrderReturnInfor(orderId);
	}
	@Override
	public int updateReturnGoods(GroupBuyOrder groupBuyOrder) {
		//创建退货对象
		GroupOrderReturnInfor groupOrderReturnInfor = new GroupOrderReturnInfor();
		//根据订单ID查找订单退货信息
		groupOrderReturnInfor = groupOrderReturnInforMapper.findGroupBuyOrderReturnInfor(groupBuyOrder.getId());
		//如果没有订单的退货信息，返回退货失败
		if(groupOrderReturnInfor==null){
			return 0;
		}
		//如果有订单的退货信息，给退货对象赋值（设置公共属性，处理结果，退货地址，退货理由）
		groupOrderReturnInfor.setUpdEac(groupOrderReturnInfor.getUpdEac()+1);
		groupOrderReturnInfor.setUpdYmdhms(new Date());
		groupOrderReturnInfor.setUpdId(groupBuyOrder.getUpdId());
		groupOrderReturnInfor.setDetailResult(groupBuyOrder.getGroupOrderReturnInfor().getDetailResult());
		groupOrderReturnInfor.setReturnAdress(groupBuyOrder.getGroupOrderReturnInfor().getReturnAdress());
		groupOrderReturnInfor.setDetailResultReason(groupBuyOrder.getGroupOrderReturnInfor().getDetailResultReason());
		//改变退货信息
		groupOrderReturnInforMapper.updateByOrderIdSelective(groupOrderReturnInfor);
		//设置订单对象的状态
		groupBuyOrder.setStatus(groupOrderReturnInfor.getDetailResult());
		//改变订单信息
		return groupBuyOrderMapper.updateByPrimaryKeySelective(groupBuyOrder);
	}
	@Override
	public List<GroupBuyOrderVo> findAllGroupBuyOrderVillage(
			GroupBuyOrderVo GroupBuyOrderVo) {
		return groupBuyOrderMapper.findAllGroupBuyOrderVillage(GroupBuyOrderVo);
	}
	@Override
	public Long findAllGroupBuyOrderVillageCount(GroupBuyOrderVo GroupBuyOrderVo) {
		return groupBuyOrderMapper.findAllGroupBuyOrderVillageCount(GroupBuyOrderVo);
	}
	@Override
	public List<Attach> attachList(Attach attach) {
		return attachMapper.selectAttachByParam(attach);
	}

}
