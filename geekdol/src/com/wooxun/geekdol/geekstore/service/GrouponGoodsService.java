package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;
import com.wooxun.geekdol.geekstore.vo.GrouponGoodsVo;

/**
 * @Title
 * @Description 团购商品Service
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 tj	2016年7月27日  下午4:20:51 		创建
 *==========================================================
 * 
 */
public interface GrouponGoodsService <T extends Serializable> extends CrudService<T> {
    
	/**
	 * 
	 * @Title
	 * @Description 后台团购管理：团购商品列表查询
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午2:49:25
	 * @param grouponGoodsVo
	 * @return List
	 */
	public List<GrouponGoodsVo> queryGrouponGoods(GrouponGoodsVo grouponGoodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 后台团购管理：团购商品列表总数查询
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午2:52:08
	 * @param grouponGoodsVo
	 * @return Long
	 */
	public Long queryGrouponGoodsCount(GrouponGoodsVo grouponGoodsVo);
	
	/**
	 * 
	 * @Title
	 * @Description 新增团购商品
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午3:44:44
	 * @param grouponGoods
	 * @return boolean
	 */
	public boolean insertGrouponGoods(GrouponGoods grouponGoods);
	
	/**
	 * 
	 * @Title
	 * @Description 更新团购商品
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午3:45:53
	 * @param grouponGoods
	 * @return boolean
	 */
	public boolean updateGrouponGoods(GrouponGoods grouponGoods);
	
	/**
	 * 
	 * @Title
	 * @Description 删除团购商品
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:35:34
	 * @param grouponGoods
	 * @return boolean
	 * @throws RuntimeException
	 */
	public boolean deleteGrouponGoods(GrouponGoods grouponGoods);
	
	/**
	 * 
	 * @Title
	 * @Description 根据id获取团购商品
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:16:51
	 * @param id
	 * @return GrouponGoods
	 */
	public GrouponGoods findById(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 评论管理详情
	 * @author:YK
	 * @CreateDate:2016年8月5日 上午10:30:47
	 * @param id
	 * @return GrouponGoodsVo
	 */
	public GrouponGoodsVo findCodeAndStar(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 审核团购商品
	 * @author:YK
	 * @CreateDate:2016年8月19日 下午12:00:47
	 * @param grouponGoods
	 * @return boolean
	 */
	public boolean verifyGrouponGoods(GrouponGoods grouponGoods);
}
