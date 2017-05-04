package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.CommunityPhone;
import com.wooxun.geekdol.hmedia.vo.AppCommunityPhoneVo;
import com.wooxun.geekdol.hmedia.vo.CommunityPhoneVo;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月30日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	  QZG	2016年7月30日  上午11:21:28 		创建
 *==========================================================
 * 
 */
public interface CommunityPhoneService <T extends Serializable> extends CrudService<T>{
   /**
    * 
    * @Title
    * @Description 根据页面查询条件返回列表
    * @author:QZG
    * @CreateDate:2016年7月30日 上午11:29:21
    * @param communityPhoneVo
    * @return
    */
    public List<CommunityPhoneVo> findAll(CommunityPhoneVo communityPhoneVo);
    /**
     * 
     * @Title
     * @Description 根据页面查询条件返回列表条数
     * @author:QZG
     * @CreateDate:2016年7月30日 上午11:29:38
     * @param communityPhoneVo
     * @return
     */
    public Long findAllCount(CommunityPhoneVo communityPhoneVo);
    /**
     * 
     * @Title
     * @Description 社区电话管理  禁用功能 禁用成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:01:37
     * @param county
     * @return
     */
    public boolean disableCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title 
     * @Description 社区电话管理 启用功能 启用成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:00:44
     * @param county
     * @return
     */
    public boolean enableCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description 逻辑删除社区电话  返回值大于0 删除成功
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:49:49
     * @param communityPhone
     * @return
     */
    public boolean deleteCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description 社区电话管理新增时初始小区下拉列表
     * @author:QZG
     * @CreateDate:2016年8月1日 上午11:37:40
     * @param userId
     * @return
     */
    public List<UserAreaVo> selectAll(Long userId);
    /**
     * 
     * @Title
     * @Description 新增社区电话时查询数据库中存在该电话的数量
     * @author:QZG
     * @CreateDate:2016年8月1日 下午2:25:16
     * @param communityPhone
     * @return
     */
    public Long findCount(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description 更新社区电话
     * @author:QZG
     * @CreateDate:2016年8月1日 下午4:25:12
     * @param communityPhone
     * @return
     */
    public boolean updateCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description app接口用  根据小区id查询电话列表社区电话
     * @author:QZG
     * @CreateDate:2016年8月11日 上午11:29:07
     * @param communtiyPhone
     * @return
     */
    public List<AppCommunityPhoneVo> findPhoneSQ(CommunityPhone communtiyPhone);
    /**
     * 
     * @Title
     * @Description app接口用  根据小区id查询电话列表服务电话
     * @author:QZG
     * @CreateDate:2016年8月11日 上午11:29:07
     * @param communtiyPhone
     * @return
     */
    public List<AppCommunityPhoneVo> findPhoneFW(CommunityPhone communtiyPhone);
}
