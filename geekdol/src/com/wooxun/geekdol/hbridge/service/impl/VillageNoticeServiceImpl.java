package com.wooxun.geekdol.hbridge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.hbridge.mapper.NoticeVillageMapper;
import com.wooxun.geekdol.hbridge.mapper.VillageNoticeMapper;
import com.wooxun.geekdol.hbridge.model.NoticeVillage;
import com.wooxun.geekdol.hbridge.model.VillageNotice;
import com.wooxun.geekdol.hbridge.service.VillageNoticeService;
import com.wooxun.geekdol.hbridge.vo.VillageNoticeVo;

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
public class VillageNoticeServiceImpl extends CrudServiceImpl<VillageNotice> implements VillageNoticeService<VillageNotice>{

    private VillageNoticeMapper<VillageNotice> villageNoticeMapper;
    private NoticeVillageMapper<NoticeVillage> noticeVillageMapper;

    @Autowired
    public VillageNoticeServiceImpl(VillageNoticeMapper<VillageNotice> villageNoticeMapper,
            NoticeVillageMapper<NoticeVillage> noticeVillageMapper) {
        super(villageNoticeMapper);
        this.villageNoticeMapper = villageNoticeMapper;
        this.noticeVillageMapper = noticeVillageMapper;
    }

    @Override
    public List<VillageNotice> queryListByParam(VillageNoticeVo villageNoticeVo) {
        return villageNoticeMapper.queryListByParam(villageNoticeVo);
    }

    @Override
    public Integer insertBackId(VillageNotice villageNotice,String villageIds,Long userId) {
        Integer count = villageNoticeMapper.insertBackId(villageNotice);
        if(count >= 1){
            List<NoticeVillage> lists = new ArrayList<>();
            if(villageIds != null && !villageIds.trim().equals("")){
                String[] idS = villageIds.split(",");
                for (int i = 0; i < idS.length; i++) {
                    NoticeVillage nv = new NoticeVillage();
                    nv.setVillageId(Long.valueOf(idS[i]));
                    nv.setNoticeId(villageNotice.getId());
                    nv.setInsYmdhms(new Date());
                    nv.setInsId(userId);
                    nv.setUpdEac(0L);
                    nv.setUpdYmdhms(new Date());
                    nv.setUpdId(userId);
                    nv.setDelFlag(ConstantStr.DELETE_N);
                    lists.add(nv);
                }
                noticeVillageMapper.insertList(lists);
            }
        }
        return count;
    }

    @Override
    public List<VillageNotice> queryListByVillageId(Map<String, Object> map) {
        return villageNoticeMapper.queryListByVillageId(map);
    }

    @Override
    public List<VillageNotice> queryListByParamOrder(
            VillageNoticeVo villageNoticeVo) {
        return villageNoticeMapper.queryListByParamOrder(villageNoticeVo);
    }

    @Override
    public Integer updateBackId(VillageNotice villageNotice, String villageIds,
            Long userId) {
        noticeVillageMapper.deleteForNoticeId(villageNotice.getId());
        
        List<NoticeVillage> lists = new ArrayList<>();
        if(villageIds != null && !villageIds.trim().equals("")){
            String[] idS = villageIds.split(",");
            for (int i = 0; i < idS.length; i++) {
                NoticeVillage nv = new NoticeVillage();
                nv.setVillageId(Long.valueOf(idS[i]));
                nv.setNoticeId(villageNotice.getId());
                nv.setInsYmdhms(new Date());
                nv.setInsId(userId);
                nv.setUpdEac(0L);
                nv.setUpdYmdhms(new Date());
                nv.setUpdId(userId);
                nv.setDelFlag(ConstantStr.DELETE_N);
                lists.add(nv);
            }
        }
        if(lists.size() > 0){
            noticeVillageMapper.insertList(lists);
        }
        return villageNoticeMapper.updateByPrimaryKeySelective(villageNotice);
    }

    @Override
    public long queryCountByParam(VillageNoticeVo villageNoticeVo) {
        return villageNoticeMapper.queryCountByParam(villageNoticeVo);
    }

}
