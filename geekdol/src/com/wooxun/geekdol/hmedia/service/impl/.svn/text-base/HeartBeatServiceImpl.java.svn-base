package com.wooxun.geekdol.hmedia.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.BaiduMapUtils;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.RelativeDateFormat;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatLikeMapper;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatLike;
import com.wooxun.geekdol.hmedia.service.HeartBeatService;
import com.wooxun.geekdol.hmedia.vo.HeartBeatVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;
/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author zhangyang	
* @CreateDate 2016年9月13日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1.  zhangyang	2016年9月13日  下午1:46:21 		创建
*==========================================================
*
 */

@Service
public class HeartBeatServiceImpl extends CrudServiceImpl<HeartBeat> implements HeartBeatService<HeartBeat> {

	private HeartBeatMapper<HeartBeat> heartBeatMapper;
	@Autowired
	private HeartBeatLikeMapper<HeartBeatLike>heartBeatLikeMapper;
	@Autowired
	private AttachMapper<Attach> attachMapper;

	@Autowired
	public HeartBeatServiceImpl(HeartBeatMapper<HeartBeat> heartBeatMapper) {
		super(heartBeatMapper);
		this.heartBeatMapper = heartBeatMapper;
	}

    @Override
    public List<HeartBeat> queryListByParam(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryListByParam(heartBeatVo);
    }

    @Override
    public List<HeartBeatVo> queryListAndUser(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryListAndUser(heartBeatVo);
    }

    @Override
    public Long queryCountByParam(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryCountByParam(heartBeatVo);
    }

    @Override
    public Long queryCountAndUser(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryCountAndUser(heartBeatVo);
    }

    @Override
    public List<HeartBeatVo> queryListAndUserPro(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryListAndUserPro(heartBeatVo);
    }

    @Override
    public Long queryCountAndUserPro(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryCountAndUserPro(heartBeatVo);
    }

    @Override
    public List<HeartBeatVo> queryListAndUserCity(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryListAndUserCity(heartBeatVo);
    }

    @Override
    public Long queryCountAndUserCity(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryCountAndUserCity(heartBeatVo);
    }

    @Override
    public List<HeartBeatVo> queryListAndUserCounty(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryListAndUserCounty(heartBeatVo);
    }

    @Override
    public List<HeartBeatVo> queryListAndUserCommunity(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryListAndUserCommunity(heartBeatVo);
    }

    @Override
    public Long queryCountAndUserCounty(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryCountAndUserCounty(heartBeatVo);
    }

    @Override
    public Long queryCountAndUserCommunity(HeartBeatVo heartBeatVo) {
        return heartBeatMapper.queryCountAndUserCommunity(heartBeatVo);
    }

	@Override
	public List<Map<String, Object>> queryHeartBeatList(HeartBeatVo heartBeatVo) {
		// 查询贴心拍主数据
		// 当选择小区的时候,因为数据库小区类型为Long,程序用,分割的小区字符串所以sql要在程序中拼接成功后传入mybatis中
		if(StringUtil.isNotBlank(heartBeatVo.getVillageIds())){
			heartBeatVo.setVillageIds("("+heartBeatVo.getVillageIds()+")");
		}
		List<Map<String,Object>> list = heartBeatMapper.queryHeartBeatList(heartBeatVo);
		// 附件查询
		List<Map<String,Object>> attachs = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String, Object>();
		// 设置附件归属表
		params.put("ownerTbType", ConstantStr.HEARTBEAT_18);
		for(Map<String,Object> obj:list){
			// 设置附件归属id
			params.put("ownerId", obj.get("id"));
			attachs = attachMapper.getByOwnerIdAndOwnerTbType(params);
			obj.put("attachs", attachs);
			// 昵称解密
			if(obj.get("nickName")!=null&&StringUtils.isNotBlank(obj.get("nickName")+"")){
				obj.put("nickName", UnescapeUtil.unescape(obj.get("nickName")+""));
			}
			// 几小时、几天、几年前
			if(obj.get("publishTime")!=null){
				obj.put("publishTime",RelativeDateFormat.format(DateUtil.parse(obj.get("publishTime").toString())));
			}else{
				obj.put("publishTime","1分钟前");
			}
		}
		// 搜索登录人所点过赞的随心拍
		HeartBeatLike searchModle = new HeartBeatLike();
		// 设置要查询随心拍人的id
		searchModle.setInsId(heartBeatVo.getPublishPersonId());
		// 查询当前登陆人的所有随心拍点赞记录
		List<HeartBeatLike> likeList = heartBeatLikeMapper.queryListByParam(searchModle);
		// 组装点过赞的随心拍id为字符串，用于封装随心拍列表是否点赞
		String hearIdStr = "";
		for(HeartBeatLike heartBeatLike:likeList){
			hearIdStr += heartBeatLike.getHearId() + ",";
		}
		// 百度距离计算
		// 当前浏览纬度
		BigDecimal latitude1 = heartBeatVo.getLatitude();
		// 当前浏览经度
		BigDecimal longitude1 = heartBeatVo.getLongitude();
		// 随心拍发布纬度
		BigDecimal latitude2 = new BigDecimal(0);
		// 随心拍发布经度
		BigDecimal longitude2 = new BigDecimal(0);
		// 差距
		String distance = "0.00";
		for(Map<String,Object> obj:list){
			distance = "0.00";
			latitude2 = (BigDecimal)obj.get("latitude");
			longitude2 = (BigDecimal)obj.get("longitude");
			distance = BaiduMapUtils.Distance(longitude1, latitude1, longitude2, latitude2);
			obj.put("distance", distance);
			// 封装该随心拍是否点赞
			if(StringUtil.isNotBlank(hearIdStr)){
				if(hearIdStr.contains(obj.get("id")+"")){
					obj.put("like", true);
				}else{
					obj.put("like", false);
				}
			}else{
				obj.put("like", false);
			}
		}
		return list;
	}
	
	@Override
	public boolean saveHeartBeat(HeartBeat heartBeat) {
		//设置随心拍默认状态：正常
		heartBeat.setTreatmentStatus(ConstantStr.HEARTBEATSTATUS_01);
		// 设置随心拍发布时间
		heartBeat.setPublishTime(new Date());
		// 新增公共属性
		addAttr(heartBeat);
		// 保存随心拍主数据信息
		int i = heartBeatMapper.insertSelective(heartBeat);
		int k = 0;
		//附件新增
		if(heartBeat.getAttachs() != null && heartBeat.getAttachs().size()>0){
            for(Attach attach: heartBeat.getAttachs()){
                attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
                attach.setOwnerId(heartBeat.getId());
                attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_18);
                attach.setOrdering(k);
                attach.setOpreator(heartBeat.getInsId());
                attach.setInsId(heartBeat.getInsId());
                attach.setInsYmdhms(new Date());
                attach.setUpdId(heartBeat.getUpdId());
                attach.setUpdEac((long) 0);
                attach.setUpdYmdhms(new Date());
                attach.setDelFlag(ConstantStr.DELETE_N);
                k++;
            }
	        
			attachMapper.insertBatch(heartBeat.getAttachs());
		}
		return i>0?true:false;
	}
	
	@Override
	public HeartBeat selectById(Long id) {
		return heartBeatMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public boolean updateHeartBeat(HeartBeat heartBeat) {
		int i = heartBeatMapper.updateByPrimaryKeySelective(heartBeat);
		return i>0?true:false;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 设置随心拍公共属性
	 * @author:YK
	 * @CreateDate:2016年9月20日 上午10:32:55
	 * @param heartBeat
	 * @return HeartBeat
	 */
	private HeartBeat addAttr(HeartBeat heartBeat){
		heartBeat.setInsId(heartBeat.getPublishPersonId());
		heartBeat.setInsYmdhms(new Date());
		heartBeat.setUpdId(heartBeat.getPublishPersonId());
		heartBeat.setUpdYmdhms(new Date());
		heartBeat.setUpdEac((long) 0);
		heartBeat.setDelFlag(ConstantStr.DELETE_N);
		return heartBeat;
	}

}
