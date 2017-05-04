package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.ActivityCollection;
import com.wooxun.geekdol.hmedia.vo.ActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityVo;

public interface ActivityCollectionMapper<T extends Serializable> extends CrudMapper<T> {
	/**
	 * 
	 * @Title
	 * @Description 查询活动汇总数
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午9:42:50
	 * @param searchActivityCollection
	 * @return
	 */
	public Long queryCountByParams(ActivityCollectionVo searchActivityCollection);

	/**
	 * 
	 * @Title
	 * @Description 查询活动汇列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:55
	 * @param searchIntimateNews
	 * @return
	 */
	public List<ActivityCollectionVo> queryActivityCollectionByParams(ActivityCollectionVo searchActivityCollection);

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id查询出封装后的活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午9:43:48
	 * @param id
	 * @return
	 */
	public ActivityCollectionVo queryActivityCollectionVoById(Long id);
	/**
	 * 
	 * @Title
	 * @Description app接口用根据小区Id查询活动汇
	 * @author:QZG
	 * @CreateDate:2016年8月13日 下午2:32:04
	 * @param appActivityCollectionVo
	 * @return
	 */
	public List<AppActivityCollectionVo> queryActivityCollection(AppActivityCollectionVo appActivityCollectionVo);
    /**
     * 
     * @Title
     * @Description app接口用  根据活动汇id查询活动汇详情
     * @author:QZG
     * @CreateDate:2016年8月13日 下午6:50:45
     * @param appActivityVo
     * @return
     */
	public AppActivityVo queryActivity(AppActivityVo appActivityVo);

	/**
	 * @Title
	 * @Description 根据用户id查询活动
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 上午12:42:31
	 * @param appUserId
	 * @return
	 */
	public List<ActivityCollection> selectActivityCollectionByUserId(
			Long appUserId);

	/**
	 * @Title
	 * @Description 根据活动汇id查询活动汇详情
	 * @author:kangtianyu
	 * @CreateDate:2016年9月2日 下午2:37:30
	 * @param id
	 * @return
	 */
	public ActivityCollection getById(Long id);
	
}