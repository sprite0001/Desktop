package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.CooperationStore;
import com.wooxun.geekdol.geekstore.vo.CooperationStoreVo;

public interface CooperationStoreMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 组合条件查找合作店铺列表总数
	 * @author:YK
	 * @CreateDate:2016年7月25日 上午9:59:49
	 * @param cooperationStoreVo
	 * @return Long
	 */
	public Long findCooperationStoreCount(CooperationStoreVo cooperationStoreVo);
	
	/**
	 * 
	 * @Title
	 * @Description 组合条件查找合作店铺列表
	 * @author:YK
	 * @CreateDate:2016年7月25日 上午10:00:44
	 * @param cooperationStoreVo
	 * @return List
	 */
	public List<CooperationStoreVo> findCooperationStore(CooperationStoreVo cooperationStoreVo);
	
	
	/**
	 * 
	 * @Title
	 * @Description 查询合作店信息 --app用
	 * @author:QZG
	 * @CreateDate:2016年7月27日 下午5:16:17
	 * @return List
	 */
	public List<CooperationStore>  selectStore();
}