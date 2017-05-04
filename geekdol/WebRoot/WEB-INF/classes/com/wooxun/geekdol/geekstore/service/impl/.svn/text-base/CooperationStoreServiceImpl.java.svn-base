package com.wooxun.geekdol.geekstore.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.geekstore.mapper.CooperationStoreMapper;
import com.wooxun.geekdol.geekstore.mapper.CooperationStoreVillageMapper;
import com.wooxun.geekdol.geekstore.model.CooperationStore;
import com.wooxun.geekdol.geekstore.model.CooperationStoreVillage;
import com.wooxun.geekdol.geekstore.service.CooperationStoreService;
import com.wooxun.geekdol.geekstore.vo.CooperationStoreVo;
import com.wooxun.geekdol.system.mapper.SysDataMapper;
import com.wooxun.geekdol.system.model.SysData;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月25日  上午9:44:46 		创建
 *==========================================================
 * 
 */
@Service
public class CooperationStoreServiceImpl extends CrudServiceImpl<CooperationStore> 
	implements CooperationStoreService<CooperationStore> {
	private static final Logger LOG = LoggerFactory.getLogger(CooperationStoreServiceImpl.class);
	
	private CooperationStoreMapper<CooperationStore> cooperationStoreMapper;
	@Autowired
	private CooperationStoreVillageMapper<CooperationStoreVillage> cooperationStoreVillageMapper;
	@Autowired
	private SysDataMapper<SysData> sysDataMapper;
	@Autowired
	public CooperationStoreServiceImpl(CooperationStoreMapper<CooperationStore> cooperationStoreMapper) {
		super(cooperationStoreMapper);
		this.cooperationStoreMapper = cooperationStoreMapper;
	}

	@Override
	public Long findCooperationStoreCount(CooperationStoreVo cooperationStoreVo) {
		return cooperationStoreMapper.findCooperationStoreCount(cooperationStoreVo);
	}

	@Override
	public List<CooperationStoreVo> findCooperationStore(
			CooperationStoreVo cooperationStoreVo) {
		// 设置店铺类型在数据字典中的type类型
		cooperationStoreVo.setStoreTypeType(ConstantStr.STORETYPE);
		// 设置店铺状态在数据字典中定义的type类型
		cooperationStoreVo.setOpenTypeType(ConstantStr.STOREPOENTYPE);
		return cooperationStoreMapper.findCooperationStore(cooperationStoreVo);
	}

	@Override
	public boolean insertCooperationStoreVo(CooperationStoreVo cooperationStoreVo) {
		CooperationStore cooperationStore = new CooperationStore();
		BeanUtils.copyProperties(cooperationStoreVo, cooperationStore);
		//新增的时候，设置合作店的默认状态：(01未审核)
		cooperationStore.setStatus(ConstantStr.STOREPOENTYPE_DEFAULT);
		try{//设置合作店营业时间与关门时间
			cooperationStore.setOpeningTime(DateUtil.parseDate(cooperationStoreVo.getOpeningTime(), "HH:mm:ss"));
			cooperationStore.setClosingTime(DateUtil.parseDate(cooperationStoreVo.getClosingTime(), "HH:mm:ss"));
		}catch(java.text.ParseException e){
			LOG.error("新增合作店设置营业时间、关门时间转化出错"+e.getMessage());
		}
		//新增合作店主数据
		int i = cooperationStoreMapper.insertSelective(cooperationStore);
		// 新增合作店与小区关系表
		String villageIds[] = cooperationStoreVo.getVillageId().split(",");
		List<CooperationStoreVillage> list = new ArrayList<CooperationStoreVillage>();
		for(String villageId: villageIds){
			CooperationStoreVillage cooperationStoreVillage = new CooperationStoreVillage();
			cooperationStoreVillage.setStoreId(cooperationStore.getId());
			cooperationStoreVillage.setVillageId(new Long(villageId));
			list.add(cooperationStoreVillage);
		}
		i = cooperationStoreVillageMapper.insertBatch(list);
		return i>0?true:false;
	}
	
	public CooperationStoreVo selectCooperationStore(Long id){
		CooperationStore cooperationStore = cooperationStoreMapper.selectByPrimaryKey(id);
		// 根据值查询对应的店铺状态
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", ConstantStr.STOREPOENTYPE);
		params.put("value", cooperationStore.getStatus());
		SysData sysData = sysDataMapper.selectSysDataByTypeAndValue(params);
		//返回封装好的数据
		CooperationStoreVo cooperationStoreVo = formateData(cooperationStore);
		cooperationStoreVo.setStatusStr(sysData.getLable());
		return cooperationStoreVo;
	}
	
	public boolean updateCooperationStoreVo(CooperationStoreVo cooperationStoreVo){
		CooperationStore cooperationStore = new CooperationStore();
		BeanUtils.copyProperties(cooperationStoreVo, cooperationStore);
		try{//设置营业、关门时间
			cooperationStore.setOpeningTime(DateUtil.parseDate(cooperationStoreVo.getOpeningTime(), "HH:mm:ss"));
			cooperationStore.setClosingTime(DateUtil.parseDate(cooperationStoreVo.getClosingTime(), "HH:mm:ss"));
		}catch(java.text.ParseException e){
			LOG.error("更新合作店设置营业时间、关门时间转化出错"+e.getMessage());
		}
		int i = cooperationStoreMapper.updateByPrimaryKeySelective(cooperationStore);
		//根据店铺id删除关系表所有相关的数据
		i = cooperationStoreVillageMapper.deleteByStoreId(cooperationStore.getId());
		// 新增合作店与小区关系表
		String villageIds[] = cooperationStoreVo.getVillageId().split(",");
		List<CooperationStoreVillage> list = new ArrayList<CooperationStoreVillage>();
		for(String villageId: villageIds){
			CooperationStoreVillage cooperationStoreVillage = new CooperationStoreVillage();
			cooperationStoreVillage.setStoreId(cooperationStore.getId());
			cooperationStoreVillage.setVillageId(new Long(villageId));
			list.add(cooperationStoreVillage);
		}
		i = cooperationStoreVillageMapper.insertBatch(list);
		return i>0?true:false;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 返回封装好的 cooperationStoreVo 数据
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:18:37
	 * @param cooperationStore
	 * @return cooperationStoreVo
	 */
	private CooperationStoreVo formateData(CooperationStore cooperationStore){
		CooperationStoreVo cooperationStoreVo = new CooperationStoreVo();
		BeanUtils.copyProperties(cooperationStore, cooperationStoreVo);
		String villageId = "";
		String villageName = "";
		for(CooperationStoreVillage cooperationStoreVillage: cooperationStore.getCooperationStoreVillage()){
			villageId += cooperationStoreVillage.getVillageId()+",";
			villageName += cooperationStoreVillage.getVillage().getVillageName()+",";
		}
		cooperationStoreVo.setOpeningTime(DateUtil.format(cooperationStore.getOpeningTime(), "HH:mm:ss"));
		cooperationStoreVo.setClosingTime(DateUtil.format(cooperationStore.getClosingTime(), "HH:mm:ss"));
		cooperationStoreVo.setVillageId(villageId.substring(0, villageId.lastIndexOf(",")));
		cooperationStoreVo.setVillageName(villageName.substring(0, villageName.lastIndexOf(",")));
		return cooperationStoreVo;
	}
    /**
     * 
     * @Title
     * @Description 查询合作店信息--app用
     * @author:QZG
     * @CreateDate:2016年7月27日 下午5:18:12
     * @return
     */
    @Override
    public List<CooperationStore> selectStore() {
        return cooperationStoreMapper.selectStore();
    }

	@Override
	public boolean deleteCooperationStore(CooperationStore cooperationStore) {
		int i = cooperationStoreMapper.updateByPrimaryKeySelective(cooperationStore);
		// 物理删除合作店与小区关系
		i = cooperationStoreVillageMapper.deleteByStoreId(cooperationStore.getId());
		return i>0?true:false;
	}
}
