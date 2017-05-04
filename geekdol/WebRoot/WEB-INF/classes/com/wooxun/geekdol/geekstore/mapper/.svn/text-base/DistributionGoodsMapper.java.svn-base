package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsVo;


public interface DistributionGoodsMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 批量保存
	 * @author:YK
	 * @CreateDate:2016年8月13日 下午5:48:42
	 * @param list
	 */
     public void insertBatch(List<DistributionGoods> list);
     
     /**
      * 
      * @Title
      * @Description 根据商品id查找铺货与商品关系表
      * @author:YK
      * @CreateDate:2016年8月16日 上午11:00:12
      * @param goodsId
      * @return
      */
     public List<DistributionGoods> selectByGoods(Long goodsId);
     
     /**
      * 
      * @Title
      * @Description 禁用商品的时候，下架铺货的商品
      * @author:YK
      * @CreateDate:2016年8月16日 上午11:08:26
      * @param list
      */
     public void updateBatch(List<DistributionGoods> list);
     
     /**
      * 
      * @Title
      * @Description 查找模块下的商品
      * @author:YK
      * @CreateDate:2016年8月16日 下午2:07:25
      * @param map
      * @return
      */
     public List<DistributionGoods> selectByGoodsAndClassfic(Map<String,Object> map);
     
     /**
 	 * 
 	 * @Title
 	 * @Description 即可送:列表总数查询
 	 * @author:YK
 	 * @CreateDate:2016年8月16日 下午3:06:01
 	 * @param distributionGoodsVo
 	 * @return
 	 */
 	public Long queryCountByParmas(DistributionGoodsVo distributionGoodsVo);
 	
 	/**
 	 * 
 	 * @Title
 	 * @Description 即可送:列表查询
 	 * @author:YK
 	 * @CreateDate:2016年8月16日 下午3:07:35
 	 * @param distributionGoodsVo
 	 * @return List
 	 */
 	public List<DistributionGoodsVo> queryByParmas(DistributionGoodsVo distributionGoodsVo);
}