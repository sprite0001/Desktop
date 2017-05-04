package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendVillage;

public interface GoodsRecommendVillageMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 批量插入
	 * @author:YK
	 * @CreateDate:2016年8月16日 下午5:34:35
	 * @param list
	 */
   public void insBatch(List<GoodsRecommendVillage> list);
}