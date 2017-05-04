package com.wooxun.geekdol.hmedia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsLikeMapper;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsMapper;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.model.IntimateNewsLike;
import com.wooxun.geekdol.hmedia.service.IntimateNewsLikeService;
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
public class IntimateNewsLikeServiceImpl extends CrudServiceImpl<IntimateNewsLike> implements IntimateNewsLikeService<IntimateNewsLike> {

	private IntimateNewsLikeMapper<IntimateNewsLike> intimateNewsLikeMapper;
	@Autowired
	private IntimateNewsMapper<IntimateNews> intimateNewsMapper;

	@Autowired
	public IntimateNewsLikeServiceImpl(IntimateNewsLikeMapper<IntimateNewsLike> intimateNewsLikeMapper) {
		super(intimateNewsLikeMapper);
		this.intimateNewsLikeMapper = intimateNewsLikeMapper;
	}

    @Override
    public List<IntimateNewsLike> queryListByParam(IntimateNewsLike intimateNewsLike) {
        return intimateNewsLikeMapper.queryListByParam(intimateNewsLike);
    }

	@Override
	public boolean updateIntimateNewsLike(IntimateNewsLike hbl, IntimateNews hb,List<IntimateNewsLike> list) {
        int i = 0;
        if(list.size() > 0){
            //已经点过赞，取消赞，并将贴心报内的点赞量减一
            for (IntimateNewsLike intimateNewsLike : list) {
            	// 删除点赞历史记录
                i = intimateNewsLikeMapper.deleteByPrimaryKey(intimateNewsLike.getId());
                if(hb.getLikesNumber()==null||hb.getLikesNumber()==0){
                	hb.setLikesNumber(new Integer(0));
                }else{
                	hb.setLikesNumber(hb.getLikesNumber() - 1);
                }
            }
        }else{
            //点赞，并将贴心报内的点赞量加一
            hbl.setInsYmdhms(new Date());
            // 插入点赞历史记录
            i = intimateNewsLikeMapper.insertSelective(hbl);
            hb.setLikesNumber(hb.getLikesNumber() + 1);
        }
        // 更新贴心报主数据的点赞量
        i = intimateNewsMapper.updateByPrimaryKeySelective(hb);
		return i>0?true:false;
	}

}
