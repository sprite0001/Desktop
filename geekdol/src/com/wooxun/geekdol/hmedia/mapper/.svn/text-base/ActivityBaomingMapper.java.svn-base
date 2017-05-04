package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
import com.wooxun.geekdol.hmedia.vo.ActivityBaomingVo;

public interface ActivityBaomingMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * 
	 * @Title
	 * @Description 查询报名总人数
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午9:39:03
	 * @param searchActivityBaoming
	 * @return
	 */
	public Long queryCountByParams(ActivityBaomingVo searchActivityBaoming);

	/**
	 * 
	 * @Title
	 * @Description 查询出报名人数列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:55
	 * @param searchActivityBaoming
	 * @return
	 */
	public List<ActivityBaomingVo> queryActivityBaomingByParams(ActivityBaomingVo searchActivityBaoming);

    /**
     * 
     * @Title
     * @Description app接口验证   同一小区统一活动汇下  报名手机号不能相同 
     * @author:QZG
     * @CreateDate:2016年8月15日 上午10:47:14
     * @param activityBaoming
     * @return
     */
    public int selectByName(ActivityBaomingVo activityBaomingVo);

    /**
     * @Title
     * @Description 调用mapper方法根据用户id查询活动信息
     * @author:kangtianyu
     * @CreateDate:2016年8月24日 上午1:04:45
     * @param appUserId
     * @return
     */
	public List<ActivityBaoming> selectActivityCollectionByUserId(Long appUserId);
}