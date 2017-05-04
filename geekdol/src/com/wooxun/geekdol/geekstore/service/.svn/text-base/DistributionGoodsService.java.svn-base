package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsVo;

/**
 * @Title
 * @Description 即可送管理service
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月16日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月16日  下午3:04:58 		创建
 *==========================================================
 * 
 */
public interface DistributionGoodsService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title
	 * @Description 即可送:列表总数查询
	 * @author:YK
	 * @CreateDate:2016年8月16日 下午3:06:01
	 * @param distributionGoodsVo
	 * @return Long
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
