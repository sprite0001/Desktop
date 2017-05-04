package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsMapper;
import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.service.DistributionGoodsService;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月16日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月16日  下午3:08:35 		创建
 *==========================================================
 * 
 */
@Service
public class DistributionGoodsServiceImpl extends CrudServiceImpl<DistributionGoods> implements
		DistributionGoodsService<DistributionGoods> {
	private DistributionGoodsMapper<DistributionGoods> distributionGoodsMapper;
	@Autowired
	public DistributionGoodsServiceImpl(DistributionGoodsMapper<DistributionGoods> distributionGoodsMapper){
		super(distributionGoodsMapper);
		this.distributionGoodsMapper = distributionGoodsMapper;
	}
	@Override
	public Long queryCountByParmas(DistributionGoodsVo distributionGoodsVo) {
		return distributionGoodsMapper.queryCountByParmas(distributionGoodsVo);
	}
	@Override
	public List<DistributionGoodsVo> queryByParmas(
			DistributionGoodsVo distributionGoodsVo) {
		return distributionGoodsMapper.queryByParmas(distributionGoodsVo);
	}
}
