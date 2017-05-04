package com.wooxun.geekdol.geekstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.GroupBuyOrderMapper;
import com.wooxun.geekdol.geekstore.mapper.GroupPurchaseCodeMapper;
import com.wooxun.geekdol.geekstore.model.GroupBuyOrder;
import com.wooxun.geekdol.geekstore.model.GroupPurchaseCode;
import com.wooxun.geekdol.geekstore.service.GroupPurchaseCodeService;
import com.wooxun.geekdol.geekstore.vo.GroupBuyOrderVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 田振兴团购码	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴2016年8月8日  上午10:39:44 		创建
 *==========================================================
 * 
 */
@Service
public class GroupPurchaseCodeServiceImpl extends CrudServiceImpl<GroupPurchaseCode> 
	implements GroupPurchaseCodeService<GroupPurchaseCode> {

	private  GroupPurchaseCodeMapper<GroupPurchaseCode> groupPurchaseCodeMapper;
	@Autowired
	private GroupBuyOrderMapper<GroupBuyOrder> groupBuyOrderMapper;
	@Autowired
	public GroupPurchaseCodeServiceImpl(GroupPurchaseCodeMapper<GroupPurchaseCode> groupPurchaseCodeMapper) {
		super(groupPurchaseCodeMapper);
		this.groupPurchaseCodeMapper = groupPurchaseCodeMapper;
	}
	@Override
	public GroupPurchaseCode verificationCode(
			GroupPurchaseCode groupPurchaseCode) {
		return groupPurchaseCodeMapper.verificationCode(groupPurchaseCode);
	}
	@Override
	public List<GroupPurchaseCode> findCode(GroupPurchaseCode groupPurchaseCode) {
		return groupPurchaseCodeMapper.findCode(groupPurchaseCode);
	}
	@Override
	public int updateGroup(GroupPurchaseCode groupPurchaseCode) {
		//改变团购码状态
		groupPurchaseCodeMapper.updateByPrimaryKeySelective(groupPurchaseCode);
		//创建订单VO
		GroupBuyOrderVo groupBuyOrderVo = new GroupBuyOrderVo();
		//给订单Vo赋值（id）
		groupBuyOrderVo.setId(groupPurchaseCode.getOrderId());
		//根据订单ID（传的值是VO）查询订单信息，把订单信息赋给订单对象
		GroupBuyOrder groupBuyOrder = groupBuyOrderMapper.findGroupBuyOrder(groupBuyOrderVo);
		//给订单对象赋值（订单ID，公共字段，订单状态）
		groupBuyOrder.setStatus(ConstantStr.YWC);
		groupBuyOrder.setId(groupPurchaseCode.getOrderId());
		groupBuyOrder.setUpdEac(groupBuyOrder.getUpdEac()+1);
		groupBuyOrder.setUpdYmdhms(new Date());
		groupBuyOrder.setUpdId(groupPurchaseCode.getUpdId());
		//改变订单状态，变成已完成
		int i = groupBuyOrderMapper.updateByPrimaryKeySelective(groupBuyOrder);
		return i;
	}

}
