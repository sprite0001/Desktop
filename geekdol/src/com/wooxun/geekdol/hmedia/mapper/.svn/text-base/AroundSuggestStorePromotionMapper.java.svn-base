package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStorePromotion;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStorePromotionVo;

/**
* @Title
* @Description 本网格推荐周边店促销管理Mapper
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月8日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月8日  上午9:13:34 		创建
*==========================================================
*
 */
public interface AroundSuggestStorePromotionMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 查询本网格推荐周边店促销信息的总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午9:49:55
	 * @param aroundSuggestStorePromotionVo
	 * @return
	 */
	Long selectAroundSuggestStorePromotionListCount(
			AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo);

	/**
	 * @Title
	 * @Description 查询本网格推荐周边店促销信息的分页后列表信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午9:49:58
	 * @param aroundSuggestStorePromotionVo
	 * @return
	 */
	List<AroundSuggestStorePromotionVo> selectAroundSuggestStorePromotionList(
			AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo);

	/**
	 * @Title
	 * @Description 根据推荐店id查询其促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午10:04:09
	 * @param id
	 * @return
	 */
	List<AroundSuggestStorePromotion> selectPromotionByASSId(Map<String, Object> param);
    
}