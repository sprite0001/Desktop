package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GrouponVillage;

public interface GrouponVillageMapper <T extends Serializable> extends CrudMapper<T>{
	
   /**
    * 	
    * @Title
    * @Description 更新团购商品的时候，先根据团购商品id删除记录
    * @author:YK
    * @CreateDate:2016年8月1日 下午4:30:38
    * @param grouponGoods
    * @return int
    */
   public int deleteGrouponVillageByGrouponGoods(Long grouponId);
   
   /**
    * 
    * @Title
    * @Description 批量增加
    * @author:YK
    * @CreateDate:2016年8月9日 下午4:35:43
    * @param list
    */
   public void insertBatch(List<GrouponVillage> list);
}