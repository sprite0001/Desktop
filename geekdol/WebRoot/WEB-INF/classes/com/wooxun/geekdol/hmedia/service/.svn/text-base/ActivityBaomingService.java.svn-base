package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
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
 *           修改人员 修改日期 描述 1. QZG 2016年7月27日 下午5:44:15 创建
 *           ==========================================================
 * 
 */
public interface ActivityBaomingService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description 后台查询报名人列表
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午9:30:49
	 * @param searchActivityBaomingVo
	 * @return
	 */
	public Long queryCountByParams(ActivityBaomingVo searchActivityBaomingVo);

	/**
	 * 
	 * @Title
	 * @Description 后台查询报名人列表
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午9:31:36
	 * @param searchActivityBaomingVo
	 * @return
	 */
	public List<ActivityBaomingVo> queryActivityBaomingByParams(ActivityBaomingVo searchActivityBaomingVo);

	/**
     * 
     * @Title app接口用 判断该人是否已报名  返回值大于0 表示已报名
     * @Description
     * @author:QZG
     * @CreateDate:2016年8月15日 上午10:20:55
     * @param name
     * @return
     */
    public int selectByName(ActivityBaomingVo activityBaomingVo);

    /**
     * @Title
     * @Description 根据用户id查询活动内容
     * @author:kangtianyu
     * @CreateDate:2016年8月24日 上午1:03:33
     * @param appUserId
     * @return
     */
	public List<ActivityBaoming> selectActivityCollectionByUserId(Long appUserId);

}
