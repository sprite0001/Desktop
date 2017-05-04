package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.CommunityPhone;
import com.wooxun.geekdol.hmedia.vo.AppCommunityPhoneVo;
import com.wooxun.geekdol.hmedia.vo.CommunityPhoneVo;

public interface CommunityPhoneMapper <T extends Serializable> extends CrudMapper<T>{
    /**
     * 
     * @Title
     * @Description 根据页面查询条件返回列表
     * @author:QZG
     * @CreateDate:2016年7月30日 上午11:18:13
     * @param communityPhoneVo
     * @return
     */
    public List<CommunityPhone> findAll(CommunityPhoneVo communityPhoneVo);
    /**
     * 
     * @Title
     * @Description 根据页面查询条件返回列表条数
     * @author:QZG
     * @CreateDate:2016年7月30日 上午11:18:46
     * @param communityPhoneVo
     * @return
     */
    public Long findAllCount(CommunityPhoneVo communityPhoneVo);
    /**
     * 
     * @Title
     * @Description 常用电话管理 禁用功能
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:00:14
     * @param communityPhone
     * @return
     */
    public int disableCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title 
     * @Description 常用电话管理 启用功能
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:00:44
     * @param communityPhone
     * @return
     */
    public int enableCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description 社区电话逻辑删除功能 返回值大于0 删除成功
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:48:29
     * @param communityPhone
     * @return
     */
    public int deleteCommunityPhone(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description 新增时查询新增该电话号码在数据库中的数量
     * @author:QZG
     * @CreateDate:2016年8月1日 下午2:24:14
     * @param communityPhone
     * @return
     */
    public Long findCount(CommunityPhone communityPhone);
    /**
     * 
     * @Title
     * @Description app接口用  根据小区id查询电话列表社区电话
     * @author:QZG
     * @CreateDate:2016年8月11日 上午11:28:01
     * @param communtiyPhone
     * @return
     */
    public List<AppCommunityPhoneVo> findPhoneSQ(CommunityPhone communtiyPhone);
    /**
     * 
     * @Title
     * @Description app接口用  根据小区id查询电话列表服务电话
     * @author:QZG
     * @CreateDate:2016年8月11日 上午11:28:01
     * @param communtiyPhone
     * @return
     */
    public List<AppCommunityPhoneVo> findPhoneFW(CommunityPhone communtiyPhone);
}