package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.Goods;
import com.wooxun.geekdol.geekstore.vo.GoodsVo;

public interface GoodsMapper <T extends Serializable> extends CrudMapper<T> {
	/**
	 * 
	 * @Title
	 * @Description 根据条件查找数量
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:46:13
	 * @param goodsVo
	 * @return
	 */
	public Long queryGoodsCountByParams(GoodsVo goodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查找list列表
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:46:33
	 * @param goodsVo
	 * @return
	 */
	public List<GoodsVo> queryGoodsByParams(GoodsVo goodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 查找可以铺货的商品
	 * @author:YK
	 * @CreateDate:2016年8月12日 上午11:52:13
	 * @param goods
	 * @return List
	 */
	public List<GoodsVo> findSelectGoods(GoodsVo goodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 铺货的时候，批量更新商品主数据状态 
	 * @author:YK
	 * @CreateDate:2016年8月16日 上午10:12:30
	 * @param list
	 */
	public void batchUpdateStatus(List<Goods> list);
}