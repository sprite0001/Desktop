package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.vo.AdvertVo;
import com.wooxun.geekdol.system.vo.AppLunboVo;
import com.wooxun.geekdol.system.vo.UserAreaVo;

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
public interface AdvertService<T extends Serializable> extends CrudService<T> {

    /**
     * 
     * @Title
     * @Description app图片轮播调用接口
     * @author:王肖东
     * @CreateDate:2016年8月18日 下午11:31:40
     * @param AppLunboVo
     * @return
     */
    public List<Advert> selectPic(AppLunboVo AppLunboVo);

    /**
     * 
     * @Title
     * @Description 广告管理 根据页面查询条件返回列表
     * @author:QZG
     * @CreateDate:2016年8月2日 下午2:07:58
     * @param positionVo
     * @return
     */
    public List<AdvertVo> findAll(AdvertVo advertVo);

    /**
     * 
     * @Title
     * @Description 根据页面查询条件返回列表条数
     * @author:QZG
     * @CreateDate:2016年8月2日 下午2:08:35
     * @param positionVo
     * @return
     */
    public Long findAllCount(AdvertVo advertVo);

    /**
     * 
     * @Title
     * @Description 逻辑删除广告信息 删除成功返回true
     * @author:QZG
     * @CreateDate:2016年8月2日 下午3:19:28
     * @param advert
     * @return
     */
    public boolean deleteAdvert(Advert advert);

    /**
     * 
     * @Title
     * @Description 广告管理 开始 操作成功 返回true
     * @author:QZG
     * @CreateDate:2016年8月2日 下午4:04:53
     * @param advert
     * @return
     */
    public boolean start(Advert advert);

    /**
     * 
     * @Title
     * @Description 广告管理 结束 操作成功 返回true
     * @author:QZG
     * @CreateDate:2016年8月2日 下午4:05:38
     * @param advert
     * @return
     */
    public boolean end(Advert advert);

    /**
     * 
     * @Title 条件查询
     * @Description
     * @author:张洋
     * @CreateDate:2016年8月1日 下午6:06:10
     * @return list
     */
    public List<Advert> selectByParm(Advert adv);

    /**
     * 
     * @Title
     * @Description 广告保存操作 保存成功 返回true
     * @author:QZG
     * @CreateDate:2016年8月3日 下午6:21:54
     * @param advert
     * @return
     */
    public boolean insertAdvert(Advert advert, String villageId);

    /**
     * 
     * @Title
     * @Description 广告管理 新增操作时查询用户管辖的所有小区
     * @author:QZG
     * @CreateDate:2016年8月4日 下午2:18:32
     * @param userId
     * @return
     */
    public List<UserAreaVo> selectAll(Long userId);

    /**
     * 
     * @Title
     * @Description 根据id查询广告
     * @author:QZG
     * @CreateDate:2016年8月5日 下午4:19:43
     * @param id
     * @return
     */
    public AdvertVo selectAdvertVo(Long id);

    /**
     * 
     * @Title
     * @Description 修改广告信息 修改成功 返回true
     * @author:QZG
     * @CreateDate:2016年8月7日 下午10:12:26
     * @param advertVo
     * @return
     */
    public boolean updateAdvertVo(AdvertVo advertVo, String villageId);

    /**
     * @Title
     * @Description 判断广告编码是否重复
     * @author:kangtianyu
     * @CreateDate:2016年9月23日 下午1:57:35
     * @param adverCode
     * @param id
     * @return
     */
	public boolean findAdvertCode(String advertCode, Long id);
}
