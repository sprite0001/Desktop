package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GoodsClassificMapper;
import com.wooxun.geekdol.geekstore.model.GoodsClassific;
import com.wooxun.geekdol.geekstore.service.GoodsClassificService;
import com.wooxun.geekdol.geekstore.vo.GoodsClassificVo;

/**
 * 
* @Title
* @Description  商品分类业务实现类
* @version 1.0.0
* @Author 863soft-王肖东	
* @CreateDate 2016年7月25日 
* @param 
* @see 
* @modified 
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 王肖东	2016年7月25日  上午11:10:07 		创建
*==========================================================
*
 */
@Service
public class GoodsClassificServiceImpl extends CrudServiceImpl<GoodsClassific> implements GoodsClassificService<GoodsClassific> {
	
	private GoodsClassificMapper<GoodsClassific> goodsClassificMapper;
	@Autowired
	public GoodsClassificServiceImpl(GoodsClassificMapper<GoodsClassific> goodsClassificMapper) {
		super(goodsClassificMapper);
		this.goodsClassificMapper = goodsClassificMapper;
	}

	@Override
	public GoodsClassific selectGoodsClassificById(Long id) {
		
		return goodsClassificMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<GoodsClassific> findAllGoodsClassific(GoodsClassificVo goodsClassificVo) {
		
		return null;
	}

	@Override
	public Long findAllGoodsClassificCount(GoodsClassificVo goodsClassificVo) {
		
		return null;
	}

	@Override
	public Long queryCountByYanzheng(GoodsClassific yanzhengGoodsClassific) {
		
		return goodsClassificMapper.selectListCount(yanzhengGoodsClassific);
	}

	@Override
	public int updateGoodsClassific(GoodsClassific goodsClassific) {
		
		return goodsClassificMapper.updateByPrimaryKeySelective(goodsClassific);
	}

	@Override
	public List<GoodsClassific> getGoodsTypeJsonTreeAll() {
        return goodsClassificMapper.selectAll();
	}
	
	@Override
	  public List<GoodsClassific> getGoodsListByPid(Long pId) {
	      
	        return goodsClassificMapper.selectGoodsTypeByPid(pId);
	  }
	@Override
	public GoodsClassific selectClassific(GoodsClassific goodsClassific) {
		return goodsClassificMapper.selectClassific(goodsClassific);
	}
}
