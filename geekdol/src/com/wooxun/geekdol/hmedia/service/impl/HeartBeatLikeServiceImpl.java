package com.wooxun.geekdol.hmedia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatLikeMapper;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatLike;
import com.wooxun.geekdol.hmedia.service.HeartBeatLikeService;
/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author zhangyang	
* @CreateDate 2016年9月20日17:52:24
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1.  zhangyang	2016年9月20日17:52:20 		创建
*==========================================================
*
 */

@Service
public class HeartBeatLikeServiceImpl extends CrudServiceImpl<HeartBeatLike> implements HeartBeatLikeService<HeartBeatLike> {

	private HeartBeatLikeMapper<HeartBeatLike> heartBeatLikeMapper;
	@Autowired
	private HeartBeatMapper<HeartBeat> heartBeatMapper;

	@Autowired
	public HeartBeatLikeServiceImpl(HeartBeatLikeMapper<HeartBeatLike> heartBeatLikeMapper) {
		super(heartBeatLikeMapper);
		this.heartBeatLikeMapper = heartBeatLikeMapper;
	}

    @Override
    public List<HeartBeatLike> queryListByParam(HeartBeatLike heartBeatLike) {
        return heartBeatLikeMapper.queryListByParam(heartBeatLike);
    }

	@Override
	public boolean updateHeartBeatLike(HeartBeatLike hbl, HeartBeat hb,List<HeartBeatLike> list) {
        int i = 0;
        if(list.size() > 0){
            //已经点过赞，取消赞，并将随心拍内的点赞量减一
            for (HeartBeatLike heartBeatLike : list) {
            	// 删除点赞历史记录
                i = heartBeatLikeMapper.deleteByPrimaryKey(heartBeatLike.getId());
                if(hb.getLikesNumber()==null||hb.getLikesNumber()==0){
                	hb.setLikesNumber(new Integer(0));
                }else{
                	hb.setLikesNumber(hb.getLikesNumber() - 1);
                }
            }
        }else{
            //点赞，并将随心拍内的点赞量加一
            hbl.setInsYmdhms(new Date());
            // 插入点赞历史记录
            i = heartBeatLikeMapper.insertSelective(hbl);
            hb.setLikesNumber(hb.getLikesNumber() + 1);
        }
        // 更新随心拍主数据的点赞量
        i = heartBeatMapper.updateByPrimaryKeySelective(hb);
		return i>0?true:false;
	}

}
