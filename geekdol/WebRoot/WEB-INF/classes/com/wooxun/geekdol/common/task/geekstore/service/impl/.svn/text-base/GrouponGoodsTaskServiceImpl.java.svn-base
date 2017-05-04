package com.wooxun.geekdol.common.task.geekstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.task.geekstore.service.GrouponGoodsTaskService;
import com.wooxun.geekdol.geekstore.mapper.GrouponGoodsMapper;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;

/**
 * @Title
 * @Description 定时更新团购商品状态
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月10日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月10日  下午6:42:27 		创建
 *==========================================================
 * 
 */
@Component
public class GrouponGoodsTaskServiceImpl implements GrouponGoodsTaskService {
	@Autowired
	private GrouponGoodsMapper<GrouponGoods> grouponGoodsMapper;
	
	@Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次  
	@Override  
	public void GrouponGoodsStatusToEffect() {
		/*// 根据(04:审核通过)状态查找未开始的团购
		GrouponGoods param = new GrouponGoods();
		param.setStatus(ConstantStr.GROUPONGOODSSTATUS_04);
		List<GrouponGoods> list = grouponGoodsMapper.queryGrouponGoodsByStatus(param);
		for(GrouponGoods grouponGoods:list){
			if(DateUtil.compareTime(new Date(),grouponGoods.getStartTime())){
				grouponGoods.setStatus(ConstantStr.GROUPONGOODSSTATUS_02);
				grouponGoods.setUpdEac(grouponGoods.getUpdEac()+1);
				// 更新状态为生效
				grouponGoodsMapper.updateByPrimaryKeySelective(grouponGoods);
			}
		}*/
	}

	@Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次  
	@Override 
	public void GrouponGoodsStatusToInvalid() {
		/*// 根据状态查找正在的团购
		GrouponGoods param = new GrouponGoods();
		param.setStatus(ConstantStr.GROUPONGOODSSTATUS_02);
		List<GrouponGoods> list = grouponGoodsMapper.queryGrouponGoodsByStatus(param);
		for(GrouponGoods grouponGoods:list){
			if(DateUtil.compareTime(new Date(),grouponGoods.getEndTime())){
				grouponGoods.setStatus(ConstantStr.GROUPONGOODSSTATUS_03);
				grouponGoods.setUpdEac(grouponGoods.getUpdEac()+1);
				// 更新状态为失效
				grouponGoodsMapper.updateByPrimaryKeySelective(grouponGoods);
			}
		}*/
	}

}
