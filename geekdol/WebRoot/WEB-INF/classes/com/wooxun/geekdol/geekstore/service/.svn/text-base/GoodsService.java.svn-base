package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.Goods;
import com.wooxun.geekdol.geekstore.vo.GoodsVo;

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
 * 1. 	 YK	2016年8月11日  上午9:44:39 		创建
 *==========================================================
 * 
 */
public interface GoodsService <T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查找数量
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:46:13
	 * @param goodsVo
	 * @return Long
	 */
	public Long queryGoodsCountByParams(GoodsVo goodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查找list列表
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:46:33
	 * @param goodsVo
	 * @return List
	 */
	public List<GoodsVo> queryGoodsByParams(GoodsVo goodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据主键查找商品
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午11:10:26
	 * @param id
	 * @return Goods
	 */
	public Goods findById(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 删除商品
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午11:12:23
	 * @param goods
	 * @return boolean
	 */
	public boolean deleteGoods(Goods goods);
	
	/**
	 * 
	 * @Title
	 * @Description 保存商品
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午2:20:24
	 * @param goods
	 * @return boolean
	 */
	public boolean insertGoods(Goods goods);
	
	/**
	 * 
	 * @Title
	 * @Description 更新商品
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午6:06:13
	 * @param goods
	 * @return boolean
	 */
	public boolean updateGoods(Goods goods);
	
	/**
	 * 
	 * @Title
	 * @Description 查找可以铺货的商品
	 * @author:YK
	 * @CreateDate:2016年8月12日 上午11:52:13
	 * @param goodsVo
	 * @return List
	 */
	public List<GoodsVo> findSelectGoods(GoodsVo goodsVo);
	
    
	/**
	 * 
	 * @Title
	 * @Description 商品禁用
	 * @author:YK
	 * @CreateDate:2016年8月16日 上午10:53:43
	 * @param goods
	 * @return int
	 */
	public int rejectGoods(Goods goods);
	
	/**
	 * 
	 * @Title
	 * @Description 商品下架
	 * @author:YK
	 * @CreateDate:2016年8月16日 上午11:51:41
	 * @param goods
	 * @return boolean
	 */
	public boolean offGoods(Goods goods);
}
