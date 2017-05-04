package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GoodsClassific;

public interface GoodsClassificMapper <T extends Serializable> extends CrudMapper<T>{
	
	public List<GoodsClassific> selectAll();
	
	public List<GoodsClassific> selectGoodsTypeByPid(Long pId);
	
	//检查是否有相同的商品分类
	public Long selectListCount(GoodsClassific yanzhengGoodsClassific);
	/**
	 * 
	 * @Title
	 * @Description 根据CODE查找商品分类信息
	 * @author:田振兴
	 * @CreateDate:2016年8月13日 下午5:25:18
	 * @param goodsClassific
	 * @return
	 */
	public GoodsClassific selectClassific(GoodsClassific goodsClassific);
}