package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 tj	2016年7月27日  下午1:00:10 		创建
 *==========================================================
 * 
 */
public interface GoodsRecommendService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title
	 * @Description 保存推荐产品
	 * @author:YK
	 * @CreateDate:2016年8月16日 下午5:20:21
	 * @param goodsRecommed
	 * @return boolean
	 */
    public boolean saveGoodsRecommed(GoodsRecommend goodsRecommend);
    
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
    
    /**
     * 
     * @Title
     * @Description 批量更新
     * @author:YK
     * @CreateDate:2016年8月17日 上午9:59:18
     * @param list
     * @return boolean
     */
    public boolean updateBatch(List<GoodsRecommend> list);
}
