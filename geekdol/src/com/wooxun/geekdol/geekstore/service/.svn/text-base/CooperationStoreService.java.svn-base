package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.CooperationStore;
import com.wooxun.geekdol.geekstore.vo.CooperationStoreVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月25日  上午9:43:37 		创建
 *==========================================================
 * 
 */
public interface CooperationStoreService <T extends Serializable> extends CrudService<T>{
	
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
	 * @Description
	 * @author:田振兴
	 * @CreateDate:2016年7月26日 下午3:46:49
	 * @param cooperationStoreVo
	 * @return boolean
	 */
	public boolean insertCooperationStoreVo(CooperationStoreVo cooperationStoreVo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据id查找合作店铺
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午10:42:05
	 * @param id
	 * @return CooperationStoreVo
	 */
	public CooperationStoreVo selectCooperationStore(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 更新合作数据
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午1:41:16
	 * @param cooperationStoreVo
	 * @return boolean
	 */
	public boolean updateCooperationStoreVo(CooperationStoreVo cooperationStoreVo);
	/**
     * 
     * @Title
     * @Description 查询合作店信息 --app用
     * @author:QZG 
     * @CreateDate:2016年7月27日 下午5:17:11
     * @return
     */
    public List<CooperationStore>  selectStore();
    
    /**
     * 
     * @Title
     * @Description 删除合作店铺
     * @author:YK
     * @CreateDate:2016年7月28日 上午11:19:56
     * @param cooperationStore
     * @return boolean
     */
    public boolean deleteCooperationStore(CooperationStore cooperationStore);
}
