package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hbridge.mapper.NoticeVillageMapper;
import com.wooxun.geekdol.hbridge.model.NoticeVillage;
import com.wooxun.geekdol.hbridge.service.NoticeVillageService;
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
 * 1.  zhangyang	2016年7月29日  上午11:23:26 		创建
 *==========================================================
 * 
 */
@Service
public class NoticeVillageServiceImpl extends CrudServiceImpl<NoticeVillage> implements NoticeVillageService<NoticeVillage>{
    
    private NoticeVillageMapper<NoticeVillage> noticeVillageMapper;

    @Autowired
    public NoticeVillageServiceImpl(NoticeVillageMapper<NoticeVillage> noticeVillageMapper) {
        super(noticeVillageMapper);
        this.noticeVillageMapper = noticeVillageMapper;
    }

    @Override
    public List<NoticeVillage> queryListByParam(NoticeVillageVo noticeVillageVo) {
        return noticeVillageMapper.queryListByParam(noticeVillageVo);
    }

    @Override
    public void insertList(List<NoticeVillage> noticeVillageList) {
        noticeVillageMapper.insertList(noticeVillageList);
    }

    @Override
    public List<NoticeVillageVo> queryListWithName(
            NoticeVillageVo noticeVillageVo) {
        return noticeVillageMapper.queryListWithName(noticeVillageVo);
    }

    @Override
    public void deleteForNoticeId(Long noticeId) {
        noticeVillageMapper.deleteForNoticeId(noticeId);
    }

}
