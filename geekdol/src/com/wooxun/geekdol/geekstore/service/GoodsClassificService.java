package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GoodsClassific;
import com.wooxun.geekdol.geekstore.vo.GoodsClassificVo;

/**
 * 
* @Title
* @Description  商品分类业务类
* @version 1.0.0
* @Author 863soft-王肖东	
* @CreateDate 2016年7月25日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 王肖东	2016年7月25日  上午11:09:45 		创建
*==========================================================
*
 */
public interface GoodsClassificService <T extends Serializable> extends CrudService<T>{

	/**
	 * 
	 * @Title
	 * @Description  查询出商品分类列表
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:23:44
	 * @param goodsClassificVo
	 * @return List
	 */
	public List<GoodsClassific> findAllGoodsClassific(GoodsClassificVo goodsClassificVo);
	
	/**
	 * 
	 * @Title
	 * @Description 查询出商品分类总条数
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:24:41
	 * @param goodsClassificVo
	 * @return Long
	 */
	public Long findAllGoodsClassificCount(GoodsClassificVo goodsClassificVo);
	
	/**
	 * 
	 * @Title
	 * @Description  根据主键id查询出modle
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:20:38
	 * @param id
	 * @return GoodsClassific
	 */
	public GoodsClassific selectGoodsClassificById(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description   同一个类别下  编号、名称不能相等    
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:38:29
	 * @param yanzhengGoodsClassific
	 * @return Long
	 */
	public Long queryCountByYanzheng(GoodsClassific yanzhengGoodsClassific);
	
	/**
	 * 
	 * @Title
	 * @Description  更新商品分类
	 * @author:王肖东
	 * @CreateDate:2016年7月26日 上午11:37:13
	 * @param goodsClassific
	 * @return int
	 */
	 public int updateGoodsClassific(GoodsClassific goodsClassific);
	
	/**
	 * @Title
	 * @Description 根据父节点id查到所属的下级分类
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 下午6:44:13
	 * @return List
	 */
	public List<GoodsClassific> getGoodsTypeJsonTreeAll();
	
	/**
	 * 
	 * @Title
	 * @Description 根据父节点id查到所属的下级分类
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 下午6:44:13
	 * @return List
	 */
	public List<GoodsClassific> getGoodsListByPid(Long pId);
	
	/**
	 * 
	 * @Title
	 * @Description 根据CODE查找商品分类信息
	 * @author:田振兴
	 * @CreateDate:2016年8月13日 下午5:25:18
	 * @param goodsClassific
	 * @return GoodsClassific
	 */
	public GoodsClassific selectClassific(GoodsClassific goodsClassific);
	
}
