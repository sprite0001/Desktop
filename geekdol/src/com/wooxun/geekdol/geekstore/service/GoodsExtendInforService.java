package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GoodsExtendInfor;

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
 * 1. 	 YK	2016年8月11日  下午5:07:49 		创建
 *==========================================================
 * 
 */
public interface GoodsExtendInforService <T extends Serializable> extends CrudService<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 根据商品id查找扩展信息
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午5:10:11
	 * @param goodsId
	 * @return List
	 */
	public List<GoodsExtendInfor> selectByGoodsId(Long goodsId);
}
