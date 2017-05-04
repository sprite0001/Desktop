package com.wooxun.geekdol.geekstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.StoreNoticeMapper;
import com.wooxun.geekdol.geekstore.model.StoreNotice;
import com.wooxun.geekdol.geekstore.service.StoreNoticeService;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;
/**
 * @Title
 * @Description 店铺公告实现类
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月27日  下午3:50:20 		创建
 *==========================================================
 * 
 */
@Service
public class StoreNoticeServiceImpl extends CrudServiceImpl<StoreNotice>
		implements StoreNoticeService<StoreNotice> {
	private StoreNoticeMapper<StoreNotice> storeNoticeMapper;
	@Autowired
	private AttachMapper<Attach> attachMapper;
	@Autowired
	public StoreNoticeServiceImpl(StoreNoticeMapper<StoreNotice> storeNoticeMapper) {
		super(storeNoticeMapper);
		this.storeNoticeMapper = storeNoticeMapper;
	}
	@Override
	public boolean insertStoreNotice(StoreNotice storeNotice) {
		int i = storeNoticeMapper.insertSelective(storeNotice);
		if(i<=0){
			return false;
		}else{
			int k = 0;
			// 保存附件
			List<Attach> listAttach = new ArrayList<Attach>();
			for(Attach attach: storeNotice.getAttachs()){
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(storeNotice.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_02);
				attach.setOrdering(k);
				attach.setOpreator(storeNotice.getInsId());
				attach.setInsId(storeNotice.getInsId());
				attach.setInsYmdhms(storeNotice.getInsYmdhms());
				attach.setUpdId(storeNotice.getUpdId());
				attach.setUpdEac(storeNotice.getUpdEac());
				attach.setUpdYmdhms(storeNotice.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				listAttach.add(attach);
				k++;
			}
			attachMapper.insertBatch(listAttach);
		}
		return true;
	}

}
