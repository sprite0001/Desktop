package com.wooxun.geekdol.hmedia.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.ActivityCollection;
import com.wooxun.geekdol.hmedia.vo.ActivityBaomingVo;
import com.wooxun.geekdol.hmedia.vo.ActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月28日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年7月28日 下午4:46:17 创建
 *           ==========================================================
 * 
 */
public interface ActivityCollectionService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description 保存活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午8:17:33
	 * @param activityCollection
	 * @param villageId
	 * @return
	 */
	public boolean insertActivityCollection(ActivityCollection activityCollection, String villageId);

	/**
	 * 
	 * @Title
	 * @Description 查询活动汇总数
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午8:18:06
	 * @param searchActivityCollection
	 * @return
	 */
	public Long queryCountByParams(ActivityCollectionVo searchActivityCollection);

	/**
	 * 
	 * @Title
	 * @Description:后台查询活动汇列表
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchVillage
	 * @return
	 */
	public List<ActivityCollectionVo> queryActivityCollectionByParams(ActivityCollectionVo searchActivityCollection);

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id找到活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午8:18:40
	 * @param id
	 * @return
	 */
	public ActivityCollectionVo selectActivityCollectionVo(Long id);

	/**
	 * 
	 * @Title
	 * @Description 删除活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午8:19:34
	 * @param activityCollection
	 * @return
	 */
	public boolean deleteActivityCollection(ActivityCollection activityCollection);
 
	 /**
     * @Title
     * @Description
     * @author:Administrator
     * @CreateDate:2016年6月1日 下午1:46:43
     * @param in
     * @param request
     * @param response
     * @throws Exception
     */
    public void export(ActivityBaomingVo activityBaomingVo, HttpServletRequest request, HttpServletResponse response)throws FileNotFoundException, IOException;
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
     * @Description 根据活动汇id获取活动汇详情
     * @author:kangtianyu
     * @CreateDate:2016年9月2日 下午2:35:30
     * @param id
     * @return
     */
	public ActivityCollection getById(Long id);
    
	/**
     * 
     * @Title
     * @Description 修改活动汇 修改成功 返回true
     * @author:QZG
     * @CreateDate:2016年8月7日 下午10:12:26
     * @param advertVo
     * @return
     */
    public boolean updateActivityVo(ActivityCollectionVo activityCollectionVo, String villageId);
    /**
     * 
     * @Title
     * @Description 根据id查询活动汇
     * @author:QZG
     * @CreateDate:2016年9月5日 下午9:54:25
     * @param id
     * @return
     */
    public ActivityCollectionVo selectActivityVo(Long id);

}


