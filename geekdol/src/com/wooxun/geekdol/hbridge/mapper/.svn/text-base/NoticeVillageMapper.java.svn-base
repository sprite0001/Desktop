package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.NoticeVillage;
import com.wooxun.geekdol.hbridge.vo.NoticeVillageVo;

/**
 * @Title
 * @Description 小区公告和小区关联
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月29日11:18:54
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员           修改日期                描述
 * 1.  zhangyang    2016年7月29日11:18:54      创建
 *==========================================================
 * 
 */
public interface NoticeVillageMapper <T extends Serializable> extends CrudMapper<T> {
    /**
     * 
     * @Title 查询小区公告关联数据
     * @Description 查询小区公告关联数据
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param noticeVillageVo
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