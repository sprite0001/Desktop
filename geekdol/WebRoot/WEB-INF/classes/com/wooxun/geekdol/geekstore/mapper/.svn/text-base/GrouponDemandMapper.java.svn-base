package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandVo;

public interface GrouponDemandMapper  <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 根据条件查询列表
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午3:12:18
	 * @param grouponDemandVo
	 * @return
	 */
	public List<GrouponDemandVo> findAllGrouponDemand(GrouponDemandVo grouponDemandVo);
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 统计
	 * @author:田振兴
	 * @CreateDate:2016年8月10日 上午10:54:32
	 * @param grouponDemandVo
	 * @return
	 */
	public List<GrouponDemandVo> census(GrouponDemandVo grouponDemandVo);
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 根据条件查询列表总数
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午3:13:33
	 * @param grouponDemandVo
	 * @return
	 */
	public Long findAllGrouponDemandCount(GrouponDemandVo grouponDemandVo);
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 根据团购ID查询出团购的具体信息
	 * @author:田振兴
	 * @CreateDate:2016年8月2日 下午4:07:23
	 * @param grouponDemandVo
	 * @return
	 */
	public GrouponDemandVo findGrouponDemand(GrouponDemandVo grouponDemandVo);
    
}
