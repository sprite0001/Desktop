package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GrouponGoodsExtendInfoMapper;
import com.wooxun.geekdol.geekstore.model.GrouponGoodsExtendInfo;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsExtendInfoService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月9日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月9日  上午10:36:42 		创建
 *==========================================================
 * 
 */
@Service
public class GrouponGoodsExtendInfoServiceImpl extends CrudServiceImpl<GrouponGoodsExtendInfo> implements GrouponGoodsExtendInfoService<GrouponGoodsExtendInfo> {
	 	@Autowired
	    private GrouponGoodsExtendInfoMapper<GrouponGoodsExtendInfo> grouponGoodsExtendInfoMapper;
	    
	    @Autowired
	    public GrouponGoodsExtendInfoServiceImpl(GrouponGoodsExtendInfoMapper<GrouponGoodsExtendInfo> grouponGoodsExtendInfoMapper) {
	        super(grouponGoodsExtendInfoMapper);
	        this.grouponGoodsExtendInfoMapper = grouponGoodsExtendInfoMapper;
	    }

		@Override
		public List<GrouponGoodsExtendInfo> selectByGoodsId(Long goodsId) {
			return grouponGoodsExtendInfoMapper.selectByGoodsId(goodsId);
		}
}
