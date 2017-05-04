package com.wooxun.geekdol.geekstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsMapper;
import com.wooxun.geekdol.geekstore.mapper.DistributionMapper;
import com.wooxun.geekdol.geekstore.mapper.DistributionVillageMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsMapper;
import com.wooxun.geekdol.geekstore.model.Distribution;
import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.model.DistributionVillage;
import com.wooxun.geekdol.geekstore.model.Goods;
import com.wooxun.geekdol.geekstore.service.DistributionService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月13日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月13日  下午5:34:46 		创建
 *==========================================================
 * 
 */
@Service
public class DistributionServiceImpl extends CrudServiceImpl<Distribution> implements
		DistributionService<Distribution> {
	private DistributionMapper<Distribution> distributionMapper;
	@Autowired
	private GoodsMapper<Goods> goodsMapper;
	@Autowired
	private DistributionGoodsMapper<DistributionGoods> distributionGoodsMapper;
	@Autowired
	private DistributionVillageMapper<DistributionVillage> distributionVillageMapper;
	@Autowired
	public DistributionServiceImpl(DistributionMapper<Distribution> distributionMapper){
		super(distributionMapper);
		this.distributionMapper = distributionMapper;
	}
	@Override
	public boolean saveDistribution(Distribution distribution) {
		// 设置铺货数据为销售中
		distribution.setStatus(ConstantStr.GOODSSTATUS_03);
		int i = distributionMapper.insertSelective(distribution);
		// 设置铺货与商品的属性并保存
		List<DistributionGoods> distributionGoodList = new ArrayList<DistributionGoods>();
		for(DistributionGoods distributionGoods:distribution.getDistributionGoods()){
			distributionGoods.setDistributionId(distribution.getId());
			// 设置商品销售中(上架)
			distributionGoods.setStatus(ConstantStr.GOODSSTATUS_03);
			distributionGoods.setInsId(distribution.getInsId());
			distributionGoods.setInsYmdhms(new Date());
			distributionGoods.setUpdId(distribution.getUpdId());
			distributionGoods.setUpdEac(distribution.getUpdEac());
			distributionGoods.setUpdYmdhms(new Date());
			distributionGoods.setDelFlag(ConstantStr.DELETE_N);
			distributionGoodList.add(distributionGoods);
		}
		distributionGoodsMapper.insertBatch(distributionGoodList);
		// 设置铺货小区信息并保存
		List<DistributionVillage> distributionVillages = new ArrayList<DistributionVillage>();
		for(DistributionVillage distributionVillage:distribution.getDistributionVillages()){
			distributionVillage.setDistributionId(distribution.getId());
			distributionVillages.add(distributionVillage);
		}
		distributionVillageMapper.insertBatch(distributionVillages);
		// 更新商品主数据状态
		List<Goods> goodList = new ArrayList<Goods>();
		for(DistributionGoods distributionGoods:distribution.getDistributionGoods()){
			Goods goods = goodsMapper.selectByPrimaryKey(distributionGoods.getGoodsId());
			goods.setUpdId(distribution.getUpdId());
			goods.setUpdEac(goods.getUpdEac()+1);
			goods.setUpdYmdhms(new Date());
			goods.setStatus(ConstantStr.GOODSSTATUS_03);
			goodList.add(goods);
		}
		goodsMapper.batchUpdateStatus(goodList);
		return i>0?true:false;
	}
}
