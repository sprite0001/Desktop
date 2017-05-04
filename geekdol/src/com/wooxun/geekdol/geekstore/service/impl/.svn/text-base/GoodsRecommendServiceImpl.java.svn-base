package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.GoodsClassificMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendVillageMapper;
import com.wooxun.geekdol.geekstore.model.GoodsClassific;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendVillage;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendService;

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
 * 1. 	 tj	2016年7月27日  下午1:01:32 		创建
 *==========================================================
 * 
 */
@Service
public class GoodsRecommendServiceImpl extends CrudServiceImpl<GoodsRecommend> 
	implements GoodsRecommendService<GoodsRecommend>{
    
    private GoodsRecommendMapper<GoodsRecommend> goodsRecommendMapper;
    @Autowired
    private GoodsRecommendVillageMapper<GoodsRecommendVillage> goodsRecommendVillageMapper;
    @Autowired
    private GoodsClassificMapper<GoodsClassific> goodsClassificMapper;
    
    @Autowired
    public GoodsRecommendServiceImpl(GoodsRecommendMapper<GoodsRecommend> goodsRecommendMapper) {
        super(goodsRecommendMapper);
        this.goodsRecommendMapper = goodsRecommendMapper;
    }

	@Override
	public boolean saveGoodsRecommed(GoodsRecommend goodsRecommend) {
		GoodsClassific goodsClassific = new GoodsClassific();
		goodsClassific.setCode(ConstantStr.XCP);
		goodsClassific = goodsClassificMapper.selectClassific(goodsClassific);
		goodsRecommend.setDistributionId(goodsClassific.getId());
		// 保存主数据
		goodsRecommend.setStatus(ConstantStr.GOODSSTATUS_03);
		int i = goodsRecommendMapper.insertSelective(goodsRecommend);
		// 保存推荐产品与小区关系
		for(GoodsRecommendVillage goodsRecommendVillage:goodsRecommend.getGoodsRecommendVillages()){
			goodsRecommendVillage.setRecommendId(goodsRecommend.getId());
		}
		goodsRecommendVillageMapper.insBatch(goodsRecommend.getGoodsRecommendVillages());
		return i>0?true:false;
	}

	@Override
	public List<GoodsRecommend> queryByParams(GoodsRecommend goodsRecommend) {
		return goodsRecommendMapper.queryByParams(goodsRecommend);
	}

	@Override
	public boolean updateBatch(List<GoodsRecommend> list) {
		int i = goodsRecommendMapper.updateBatch(list);
		return i>0?true:false;
	}


}
