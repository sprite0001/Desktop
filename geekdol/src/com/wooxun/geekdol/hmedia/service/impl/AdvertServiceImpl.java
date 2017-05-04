package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.hmedia.mapper.AdvertMapper;
import com.wooxun.geekdol.hmedia.mapper.AdvertPositionMapper;
import com.wooxun.geekdol.hmedia.mapper.AdvertVillageMapper;
import com.wooxun.geekdol.hmedia.mapper.PositionMapper;
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.model.AdvertPosition;
import com.wooxun.geekdol.hmedia.model.AdvertVillage;
import com.wooxun.geekdol.hmedia.model.Position;
import com.wooxun.geekdol.hmedia.service.AdvertService;
import com.wooxun.geekdol.hmedia.vo.AdvertVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.mapper.UserAreaMapper;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
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
 *           修改人员 修改日期 描述 1. QZG 2016年7月27日 下午5:45:39 创建
 *           ==========================================================
 * 
 */
@Service
public class AdvertServiceImpl extends CrudServiceImpl<Advert> implements AdvertService<Advert> {
    @Autowired
    private AdvertMapper<Advert> advertMapper;
    @Autowired
    private AttachMapper<Attach> attachMapper;
    @Autowired
    private UserAreaMapper<UserArea> userAreaMapper;
    @Autowired
    private AdvertPositionMapper<AdvertPosition> advertPositionMapper;
    @Autowired
    private AdvertVillageMapper<AdvertVillage> advertVillageMapper;
    @Autowired
    private PositionMapper<Position> positionMapper;

    @Autowired
    public AdvertServiceImpl(AdvertMapper<Advert> advertMapper) {
        super(advertMapper);
        this.advertMapper = advertMapper;
    }

    @Override
    public List<AdvertVo> findAll(AdvertVo advertVo) {
        return advertMapper.findAll(advertVo);
    }

    @Override
    public Long findAllCount(AdvertVo advertVo) {
        return advertMapper.findAllCount(advertVo);
    }

    @Override
    public boolean deleteAdvert(Advert advert) {
        int res = advertMapper.deleteAdvert(advert);
        return res > 0 ? true : false;
    }

    @Override
    public boolean start(Advert advert) {
        int res = advertMapper.start(advert);
        return res > 0 ? true : false;
    }

    @Override
    public boolean end(Advert advert) {
        int res = advertMapper.end(advert);
        return res > 0 ? true : false;
    }

    @Override
    public List<Advert> selectByParm(Advert adv) {
        return advertMapper.selectByParm(adv);
    }

    @Override
    public boolean insertAdvert(Advert advert, String villageId) {
        int i = advertMapper.insertSelective(advert);
        if (i < 0) {
            return false;
        } else {

            // 保存附件
            List<Attach> listAttach = new ArrayList<Attach>();
            int k = 0;
            for (Attach attach : advert.getAttachs()) {
                attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
                attach.setOwnerId(advert.getId());
                attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_06);
                attach.setOrdering(k);
                attach.setOpreator(advert.getInsId());
                attach.setInsId(advert.getInsId());
                attach.setInsYmdhms(advert.getInsYmdhms());
                attach.setUpdId(advert.getUpdId());
                attach.setUpdEac(advert.getUpdEac());
                attach.setUpdYmdhms(advert.getUpdYmdhms());
                attach.setDelFlag(ConstantStr.DELETE_N);
                listAttach.add(attach);
                k++;
            }
            if (listAttach.size() != 0) {
                attachMapper.insertBatch(listAttach);
            }
            // 保存 广告-位置关系
            AdvertPosition advertPosition = new AdvertPosition();
            advertPosition.setAdvertId(advert.getId());
            advertPosition.setPositionId(advert.getPositionId());
            advertPosition.setInsId(getUserId());
            advertPosition.setInsYmdhms(new Date());
            advertPosition.setUpdId(getUserId());
            advertPosition.setUpdEac(0l);
            advertPosition.setUpdYmdhms(new Date());
            advertPosition.setDelFlag(ConstantStr.DELETE_N);
            advertPositionMapper.insertSelective(advertPosition);
            // 保存 广告-小区关系
            List<AdvertVillage> listAdvertVillage = new ArrayList<AdvertVillage>();
            String[] village = villageId.split(",");
            for (int j = 0; j < village.length; j++) {
                AdvertVillage advertVillage = new AdvertVillage();
                advertVillage.setAdvertId(advert.getId());
                advertVillage.setVillageId(Long.valueOf(village[j]));
                advertVillage.setInsId(getUserId());
                advertVillage.setInsYmdhms(new Date());
                advertVillage.setUpdId(getUserId());
                advertVillage.setUpdEac(0l);
                advertVillage.setUpdYmdhms(new Date());
                advertVillage.setDelFlag(ConstantStr.DELETE_N);
                listAdvertVillage.add(advertVillage);
            }
            advertVillageMapper.insertBatch(listAdvertVillage);
        }
        return true;
    }

    public Long getUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
        Long userId = (Long) user.getId();
        return userId;
    }

    @Override
    public List<UserAreaVo> selectAll(Long userId) {
        return userAreaMapper.selectAll(userId);
    }

    @Override
    public AdvertVo selectAdvertVo(Long id) {
        Advert advert = advertMapper.selectByPrimaryKey(id);
        AdvertVo advertVo = formateData(advert);
        return advertVo;
    }

    /**
     * 
     * @Title
     * @Description 返回封装好的advertVo数据
     * @author:QZG
     * @CreateDate:2016年8月5日 下午4:48:00
     * @param advert
     * @return
     */
    private AdvertVo formateData(Advert advert) {
        AdvertVo advertVo = new AdvertVo();
        BeanUtils.copyProperties(advert, advertVo);
        String villageId = "";
        String villageName = "";
        for (AdvertVillage advertVillage : advert.getListAdvertVillage()) {
        	if (advertVillage.getVillage() != null) {
        		villageId += advertVillage.getVillageId() + ",";
                villageName += advertVillage.getVillage().getVillageName() + ",";
        	}
        }
        advertVo.setStartTimeStr(DateUtil.dateToString(advert.getStartTime()));
        advertVo.setEndTimeStr(DateUtil.dateToString(advert.getEndTime()));
        if (StringUtils.isNotBlank(villageId) && StringUtils.isNotBlank(villageName)) {
        	advertVo.setVillageId(villageId.substring(0, villageId.lastIndexOf(",")));
            advertVo.setVillageName(villageName.substring(0, villageName.lastIndexOf(",")));
        }
        advertVo.setPositionId(positionMapper.selectByPrimaryKey(
                advertPositionMapper.selectPositionId(advert.getId()).getPositionId()).getPositionId());
        advertVo.setPositionName(positionMapper.selectByPrimaryKey(
                advertPositionMapper.selectPositionId(advert.getId()).getPositionId()).getPositionName());
        return advertVo;
    }

    @Override
    public boolean updateAdvertVo(AdvertVo advertVo, String villageId) {
        Advert advert = new Advert();
        BeanUtils.copyProperties(advertVo, advert);
        int i = advertMapper.updateByPrimaryKeySelective(advert);
        i = advertVillageMapper.deleteById(advert.getId());
        i = advertPositionMapper.deleteById(advert.getId());
        // 保存附件
        List<Attach> listAttach = new ArrayList<Attach>();
        int k = 0;
        for (Attach attach : advert.getAttachs()) {
            attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
            attach.setOwnerId(advert.getId());
            attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_06);
            attach.setOrdering(k);
            attach.setOpreator(advert.getInsId());
            attach.setInsId(advert.getInsId());
            attach.setInsYmdhms(advert.getInsYmdhms());
            attach.setUpdId(advert.getUpdId());
            attach.setUpdEac(advert.getUpdEac());
            attach.setUpdYmdhms(advert.getUpdYmdhms());
            attach.setDelFlag(ConstantStr.DELETE_N);
            listAttach.add(attach);
            k++;
        }
        if (listAttach.size() != 0) {
            attachMapper.insertBatch(listAttach);
        }
        // 保存 广告-位置关系
        AdvertPosition advertPosition = new AdvertPosition();
        advertPosition.setAdvertId(advert.getId());
        advertPosition.setPositionId(advert.getPositionId());
        advertPosition.setInsId(getUserId());
        advertPosition.setInsYmdhms(new Date());
        advertPosition.setUpdId(getUserId());
        advertPosition.setUpdEac(0l);
        advertPosition.setUpdYmdhms(new Date());
        advertPosition.setDelFlag(ConstantStr.DELETE_N);
        advertPositionMapper.insertSelective(advertPosition);
        // 保存 广告-小区关系
        List<AdvertVillage> listAdvertVillage = new ArrayList<AdvertVillage>();
        String[] village = villageId.split(",");
        for (int j = 0; j < village.length; j++) {
            AdvertVillage advertVillage = new AdvertVillage();
            advertVillage.setAdvertId(advert.getId());
            advertVillage.setVillageId(Long.valueOf(village[j]));
            advertVillage.setInsId(getUserId());
            advertVillage.setInsYmdhms(new Date());
            advertVillage.setUpdId(getUserId());
            advertVillage.setUpdEac(0l);
            advertVillage.setUpdYmdhms(new Date());
            advertVillage.setDelFlag(ConstantStr.DELETE_N);
            listAdvertVillage.add(advertVillage);
        }
        advertVillageMapper.insertBatch(listAdvertVillage);
        return i > 0 ? true : false;
    }

    @Override
    public List<Advert> selectPic(AppLunboVo appLunboVo) {

        return advertMapper.selectPic(appLunboVo);
    }

	@Override
	public boolean findAdvertCode(String advertCode, Long id) {
		// 创建Map对象用于封装查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中封装参数 */
		param.put("advertCode", advertCode);
		param.put("id", id);
		// 调用mapper方法看广告编码是否重复
		int result = advertMapper.findAdvertCode(param);
		if (result > 0) { // 如果有则返回存在
			return true;
		} else {
			return false;
		}
	}
}
