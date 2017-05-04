package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;

public interface GoodsRecommendMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 根据商品id查找铺货到新商品模块的产品
	 * @author:YK
	 * @CreateDate:2016年8月16日 上午11:27:04
	 * @param goodsId
	 * @return
	 */
    public List<GoodsRecommend> selectByGoodsId(Long goodsId);
    
    /**
     * 
     * @Title
     * @Description 批量更新铺货到新产品模块的商品
     * @author:YK
     * @CreateDate:2016年8月16日 上午11:31:08
     * @param list
     * @return int
     */
    public int updateBatch(List<GoodsRecommend> list);
    
    /**
     * 
     * @Title
     * @Description 根据条件查找对应的推荐产品
     * @author:YK
     * @CreateDate:2016年8月17日 上午9:46:10
     * @param goodsRecommend
     * @return List
     */
    public List<GoodsRecommend> queryByParams(GoodsRecommend goodsRecommend);
    
}