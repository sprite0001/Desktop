package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GoodsExtendInfor;

public interface GoodsExtendInforMapper <T extends Serializable> extends CrudMapper<T>{
   
	/**
	 * 
	 * @Title
	 * @Description 批量插入扩展信息
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午2:31:27
	 * @param list
	 */
	public void insertBatch(List<GoodsExtendInfor> list);
	
	/**
	 * 
	 * @Title
	 * @Description 根据商品id查找扩展信息
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午4:43:02
	 * @param goodsId
	 * @return
	 */
	public List<GoodsExtendInfor> selectByGoodsId(Long goodsId);
	
	/**
	 * 
	 * @Title
	 * @Description 根据商品id删除扩展信息
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午4:44:12
	 * @param goodsId
	 * @return
	 */
	public int deleteGoodsExtendInforByGoods(Long goodsId);
}