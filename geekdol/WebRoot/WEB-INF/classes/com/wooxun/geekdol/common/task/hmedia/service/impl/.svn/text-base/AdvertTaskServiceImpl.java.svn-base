package com.wooxun.geekdol.common.task.hmedia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.task.hmedia.service.AdvertTaskService;
import com.wooxun.geekdol.hmedia.mapper.AdvertMapper;
import com.wooxun.geekdol.hmedia.model.Advert;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年8月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年8月20日  上午10:37:47 		创建
 *==========================================================
 * 
 */
@Component
public class AdvertTaskServiceImpl implements AdvertTaskService{
    
    @Autowired
    private AdvertMapper<Advert> advertMapper;
    /**
     * 
     * @Title
     * @Description 更新广告开始状态为结束
     * @author:QZG
     * @CreateDate:2016年8月20日 上午11:00:56
     */
    @Scheduled(cron="0 0 0 * * ?") //每天早上0点执行执行一次
    @Override
    public void AdvertToEnd() {
         //新建广告类
         Advert param = new Advert();
         //设置查询条件参数开始状态为展示中
         param.setBeginFlag(ConstantStr.AD_IS_RUNING);
         //查询满足条件的广告列表
         List<Advert> list =advertMapper.queryAdvertByBeignflag(param);
         //遍历广告列表
         for(Advert advert:list){
             //比较当前时间和广告结束时间  当当前时间大于广告结束时间时，返回true
             if(DateUtil.compareTime(new Date(), advert.getEndTime())){
             //设置广告开始标识为已结束
             advert.setBeginFlag(ConstantStr.AD_IS_END);
             //设置更新回数
             advert.setUpdEac(advert.getUpdEac()+1);
             //更新广告
             advertMapper.updateByPrimaryKeySelective(advert);}
         }
        
    }

}
