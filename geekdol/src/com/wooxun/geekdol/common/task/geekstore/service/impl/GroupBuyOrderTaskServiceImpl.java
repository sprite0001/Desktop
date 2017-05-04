package com.wooxun.geekdol.common.task.geekstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.task.geekstore.service.GroupBuyOrderTaskService;
import com.wooxun.geekdol.geekstore.mapper.GroupBuyOrderMapper;
import com.wooxun.geekdol.geekstore.model.GroupBuyOrder;
import com.wooxun.geekdol.geekstore.vo.GroupBuyOrderVo;

/**
 * @Title 团购订单管理
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月26日  下午3:19:45 		创建
 *==========================================================
 * 
 */
@Component
public class GroupBuyOrderTaskServiceImpl implements GroupBuyOrderTaskService{
	
	@Autowired
	private GroupBuyOrderMapper<GroupBuyOrder> groupBuyOrderMapper;
	@Scheduled(cron="0/5 * *  * * ? ") //每5秒执行一次
	@Override
	public void updateAllOrderStatus() {
		/*//创建订单Vo
		GroupBuyOrderVo groupBuyOrderVo = new GroupBuyOrderVo();
		//订单未付款
		groupBuyOrderVo.setStatus(ConstantStr.WFK);
		//找到所有的未付款订单
		List<GroupBuyOrder> orderList = groupBuyOrderMapper.findAllOrder(groupBuyOrderVo);
		//循环所有的未付款订单
		for(GroupBuyOrder groupBuyOrder:orderList){
			//找到当前时间的前一天时间
			Date orderTime = DateUtil.getDate(new Date(), -1);
			//比较下单时间和当前时间的前一天时间
			if(DateUtil.compareTime(orderTime,groupBuyOrder.getOrderTime())){
				//订单状态设为已关闭
				groupBuyOrder.setStatus(ConstantStr.YGB);
				groupBuyOrder.setUpdEac(groupBuyOrder.getUpdEac()+1);
				groupBuyOrder.setUpdYmdhms(new Date());
				// 更新状态为已关闭
				groupBuyOrderMapper.updateByPrimaryKeySelective(groupBuyOrder);
			}
		}*/
	}

}
