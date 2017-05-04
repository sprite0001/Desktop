package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GoodsExtendInforMapper;
import com.wooxun.geekdol.geekstore.model.GoodsExtendInfor;
import com.wooxun.geekdol.geekstore.service.GoodsExtendInforService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月11日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月11日  下午5:11:44 		创建
 *==========================================================
 * 
 */
@Service
public class GoodsExtendInforServiceImpl extends CrudServiceImpl<GoodsExtendInfor> implements
		GoodsExtendInforService<GoodsExtendInfor> {
	private GoodsExtendInforMapper<GoodsExtendInfor> goodsExtendInforMapper;
	@Autowired
	public GoodsExtendInforServiceImpl(GoodsExtendInforMapper<GoodsExtendInfor> goodsExtendInforMapper){
		super(goodsExtendInforMapper);
		this.goodsExtendInforMapper = goodsExtendInforMapper;
	}
	@Override
	public List<GoodsExtendInfor> selectByGoodsId(Long goodsId) {
		return goodsExtendInforMapper.selectByGoodsId(goodsId);
	}
}
