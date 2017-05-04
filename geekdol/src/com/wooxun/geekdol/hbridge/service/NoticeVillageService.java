package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.NoticeVillage;
import com.wooxun.geekdol.hbridge.vo.NoticeVillageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月29日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年7月29日  上午11:22:32 		创建
 *==========================================================
 * 
 */
public interface NoticeVillageService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示列表
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param villageNoticeVo
     * @return List集合
     */
    public List<NoticeVillage> queryListByParam(NoticeVillageVo noticeVillageVo);
    /**
     * 
     * @Title 条件查询,返回小区名
     * @Description 条件查询,返回小区名
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param noticeVillageVo
     * @return List集合
     */
    public List<NoticeVillageVo> queryListWithName(NoticeVillageVo noticeVillageVo);
    /**
     * 
     * @Title 批量插入关系表
     * @Description 批量插入关系表
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param NoticeVillage
     * @return void
     */
    public void insertList(List<NoticeVillage> noticeVillageList);
    /**
     * 
     * @Title 根据公告ID删除所有此文章关联表
     * @Description 根据公告ID删除所有此文章关联表
     * @author:张洋
     * @CreateDate:2016年8月1日09:36:11
     * @param noticeId
     * @return void
     */
    public void deleteForNoticeId(Long noticeId);
}
