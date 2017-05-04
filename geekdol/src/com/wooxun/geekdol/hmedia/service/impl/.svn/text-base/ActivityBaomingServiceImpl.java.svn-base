package com.wooxun.geekdol.hmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.ActivityBaomingMapper;
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
import com.wooxun.geekdol.hmedia.service.ActivityBaomingService;
import com.wooxun.geekdol.hmedia.vo.ActivityBaomingVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月27日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. QZG 2016年7月27日 下午5:45:39 创建
 *           ==========================================================
 * 
 */
@Service
public class ActivityBaomingServiceImpl extends CrudServiceImpl<ActivityBaoming> implements
		ActivityBaomingService<ActivityBaoming> {

	private ActivityBaomingMapper<ActivityBaoming> activityBaomingMapper;

	@Autowired
	public ActivityBaomingServiceImpl(ActivityBaomingMapper<ActivityBaoming> activityBaomingMapper) {
		super(activityBaomingMapper);
		this.activityBaomingMapper = activityBaomingMapper;
	}

	@Override
	public Long queryCountByParams(ActivityBaomingVo searchActivityBaomingVo) {

		return activityBaomingMapper.queryCountByParams(searchActivityBaomingVo);
	}

	@Override
	public List<ActivityBaomingVo> queryActivityBaomingByParams(ActivityBaomingVo searchActivityBaomingVo) {

		return activityBaomingMapper.queryActivityBaomingByParams(searchActivityBaomingVo);
	}

    @Override
    public int selectByName(ActivityBaomingVo activityBaomingVo) {
        
        return activityBaomingMapper.selectByName(activityBaomingVo);
    }

	@Override
	public List<ActivityBaoming> selectActivityCollectionByUserId(Long appUserId) {
		// 调用mapper方法根据用户id查询活动信息
		return activityBaomingMapper.selectActivityCollectionByUserId(appUserId);
	}

   

}
