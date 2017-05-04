package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;
import com.wooxun.geekdol.geekstore.vo.GrouponGoodsVo;


public interface GrouponGoodsMapper <T extends Serializable> extends CrudMapper<T> {
   
	/**
	 * 
	 * @Title
	 * @Description 后台团购管理：团购商品列表查询
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午2:54:44
	 * @param grouponGoodsVo
	 * @return List<GrouponGoods>
	 */
   public List<GrouponGoodsVo> queryGrouponGoods(GrouponGoodsVo grouponGoodsVo);
   
   /**
    * 
    * @Title
    * @Description 后台团购管理：团购商品列表总数查询
    * @author:YK
    * @CreateDate:2016年8月1日 下午2:55:12
    * @param grouponGoodsVo
    * @return Long
    */
   public Long queryGrouponGoodsCount(GrouponGoodsVo grouponGoodsVo);
   
   /**
    * 
    * @Title
    * @Description 查找团购code和总评论/好评/中评/差评
    * @author:YK
    * @CreateDate:2016年8月5日 上午10:33:47
    * @param id
    * @return GrouponGoodsVo
    */
   public GrouponGoodsVo findCodeAndStar(Long id);
   
   /**
    * 
    * @Title
    * @Description 定时查找符合状态的团购商品
    * @author:YK
    * @CreateDate:2016年8月10日 下午6:51:28
    * @param grouponGoods
    * @return List
    */
   public List<GrouponGoods> queryGrouponGoodsByStatus(GrouponGoods grouponGoods);
}