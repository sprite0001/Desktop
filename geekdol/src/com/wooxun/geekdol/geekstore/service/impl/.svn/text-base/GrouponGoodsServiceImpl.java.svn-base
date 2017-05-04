package com.wooxun.geekdol.geekstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.GrouponGoodsExtendInfoMapper;
import com.wooxun.geekdol.geekstore.mapper.GrouponGoodsMapper;
import com.wooxun.geekdol.geekstore.mapper.GrouponVillageMapper;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;
import com.wooxun.geekdol.geekstore.model.GrouponGoodsExtendInfo;
import com.wooxun.geekdol.geekstore.model.GrouponVillage;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsService;
import com.wooxun.geekdol.geekstore.vo.GrouponGoodsVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 tj	2016年7月27日  下午4:22:08 		创建
 *==========================================================
 * 
 */
@Service
public class GrouponGoodsServiceImpl extends CrudServiceImpl<GrouponGoods> 
	implements GrouponGoodsService<GrouponGoods>{
    
    private GrouponGoodsMapper<GrouponGoods> grouponGoodsMapper;
    @Autowired
    private AttachMapper<Attach> attachMapper;
    @Autowired
    private GrouponGoodsExtendInfoMapper<GrouponGoodsExtendInfo> grouponGoodsExtendInfoMapper;
    @Autowired
    private GrouponVillageMapper<GrouponVillage> grouponVillageMapper;
    
    @Autowired
    public GrouponGoodsServiceImpl(GrouponGoodsMapper<GrouponGoods> grouponGoodsMapper) {
        super(grouponGoodsMapper);
        this.grouponGoodsMapper=grouponGoodsMapper;
    }
    
    @Override
    public List<GrouponGoodsVo> queryGrouponGoods(GrouponGoodsVo grouponGoodsVo) {
        return grouponGoodsMapper.queryGrouponGoods(grouponGoodsVo);
    }

	@Override
	public Long queryGrouponGoodsCount(GrouponGoodsVo grouponGoodsVo) {
		return grouponGoodsMapper.queryGrouponGoodsCount(grouponGoodsVo);
	}

	@Override
	public boolean insertGrouponGoods(GrouponGoods grouponGoods) {
		grouponGoods.setStatus(ConstantStr.GROUPONGOODSSTATUS_01);
		int i = grouponGoodsMapper.insertSelective(grouponGoods);
		if(i>0){
			int k = 0;
			//附件新增
			List<Attach> attachs = new ArrayList<Attach>();
			for(Attach attach: grouponGoods.getAttachs()){
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(grouponGoods.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_05);
				attach.setOrdering(k);
				attach.setOpreator(grouponGoods.getInsId());
				attach.setInsId(grouponGoods.getInsId());
				attach.setInsYmdhms(grouponGoods.getInsYmdhms());
				attach.setUpdId(grouponGoods.getUpdId());
				attach.setUpdEac(grouponGoods.getUpdEac());
				attach.setUpdYmdhms(grouponGoods.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				attachs.add(attach);
				k++;
			}
			attachMapper.insertBatch(attachs);
			//扩展属性新增
			List<GrouponGoodsExtendInfo> grouponGoodsExtendInfos = new ArrayList<GrouponGoodsExtendInfo>();
			for(GrouponGoodsExtendInfo grouponGoodsExtendInfo :grouponGoods.getGrouponGoodsExtendInfos()){
				grouponGoodsExtendInfo.setGoodsId(grouponGoods.getId());
				grouponGoodsExtendInfo.setInsId(grouponGoods.getInsId());
				grouponGoodsExtendInfo.setInsYmdhms(grouponGoods.getInsYmdhms());
				grouponGoodsExtendInfo.setUpdId(grouponGoods.getUpdId());
				grouponGoodsExtendInfo.setUpdEac(grouponGoods.getUpdEac());
				grouponGoodsExtendInfo.setUpdYmdhms(grouponGoods.getUpdYmdhms());
				grouponGoodsExtendInfo.setDelFlag(ConstantStr.DELETE_N);
				grouponGoodsExtendInfos.add(grouponGoodsExtendInfo);
			}
			grouponGoodsExtendInfoMapper.insertBatch(grouponGoodsExtendInfos);
			// 新增管辖区域
			List<GrouponVillage> list = new ArrayList<GrouponVillage>();
			for(GrouponVillage grouponVillage :grouponGoods.getGrouponVillage()){
				grouponVillage.setGrouponId(grouponGoods.getId());
				list.add(grouponVillage);
			}
			grouponVillageMapper.insertBatch(list);
		}
		return i>0?true:false;
	}

	@Override
	public boolean updateGrouponGoods(GrouponGoods grouponGoods) {
		int i = grouponGoodsMapper.updateByPrimaryKeySelective(grouponGoods);
		if(i>0){
			//附件更新：1、先删除相关附件;2、新增团购商品附件
			int k = 0;
			List<Attach> attachs = new ArrayList<Attach>();
			i = attachMapper.deleteByIdAndName(grouponGoods.getId(),ConstantStr.ATTACH_OWNERTBTYPE_05);
			for(Attach attach: grouponGoods.getAttachs()){
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(grouponGoods.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_05);
				attach.setOrdering(k);
				attach.setOpreator(grouponGoods.getInsId());
				attach.setInsId(grouponGoods.getInsId());
				attach.setInsYmdhms(grouponGoods.getInsYmdhms());
				attach.setUpdId(grouponGoods.getUpdId());
				attach.setUpdEac(grouponGoods.getUpdEac());
				attach.setUpdYmdhms(grouponGoods.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				attachs.add(attach);
				k++;
			}
			// 批量插入附件
			attachMapper.insertBatch(attachs);
			
			//更新扩展属性：1、先删除相关属性;2、新增团购商品属性
			List<GrouponGoodsExtendInfo> grouponGoodsExtendInfos = new ArrayList<GrouponGoodsExtendInfo>();
			i = grouponGoodsExtendInfoMapper.deleteGrouponGoodsExtendInfoByGrouponGoods(grouponGoods.getId());
			for(GrouponGoodsExtendInfo grouponGoodsExtendInfo :grouponGoods.getGrouponGoodsExtendInfos()){
				grouponGoodsExtendInfo.setGoodsId(grouponGoods.getId());
				grouponGoodsExtendInfo.setInsId(grouponGoods.getInsId());
				grouponGoodsExtendInfo.setInsYmdhms(grouponGoods.getInsYmdhms());
				grouponGoodsExtendInfo.setUpdId(grouponGoods.getUpdId());
				grouponGoodsExtendInfo.setUpdEac(grouponGoods.getUpdEac());
				grouponGoodsExtendInfo.setUpdYmdhms(grouponGoods.getUpdYmdhms());
				grouponGoodsExtendInfo.setDelFlag(ConstantStr.DELETE_N);
				grouponGoodsExtendInfos.add(grouponGoodsExtendInfo);
			}
			grouponGoodsExtendInfoMapper.insertBatch(grouponGoodsExtendInfos);
			// 更新发布区域:1、先删除发布区域;2、新增发布区域
			i = grouponVillageMapper.deleteGrouponVillageByGrouponGoods(grouponGoods.getId());
			List<GrouponVillage> list = new ArrayList<GrouponVillage>();
			for(GrouponVillage grouponVillage :grouponGoods.getGrouponVillage()){
				grouponVillage.setGrouponId(grouponGoods.getId());
				list.add(grouponVillage);
			}
			grouponVillageMapper.insertBatch(list);
		}
		return i>0?true:false;
	}

	@Override
	public boolean deleteGrouponGoods(GrouponGoods grouponGoods) {
		int i = grouponGoodsMapper.updateByPrimaryKeySelective(grouponGoods);
		//删除相关附件
		Attach attach = new Attach();
		attach.setOwnerId(grouponGoods.getId());
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_05);
		i = attachMapper.deleteByParam(attach);
		// 删除相关扩展属性
		i = grouponGoodsExtendInfoMapper.deleteGrouponGoodsExtendInfoByGrouponGoods(grouponGoods.getId());
		// 删除相关团购与小区关系表数据
		i = grouponVillageMapper.deleteGrouponVillageByGrouponGoods(grouponGoods.getId());
		return i>0?true:false;
	}

	@Override
	public GrouponGoods findById(Long id) {
		GrouponGoods original = grouponGoodsMapper.selectByPrimaryKey(id);
		Attach attach = new Attach();
		attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
		attach.setOwnerId(id);
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_05);
		original.setAttachs(attachMapper.selectAttachByParam(attach));
		return original;
	}
	
	@Override
	public GrouponGoodsVo findCodeAndStar(Long id) {
		return grouponGoodsMapper.findCodeAndStar(id);
	}

	@Override
	public boolean verifyGrouponGoods(GrouponGoods grouponGoods) {
		int i = grouponGoodsMapper.updateByPrimaryKeySelective(grouponGoods);
		return i>0?true:false;
	}

}
