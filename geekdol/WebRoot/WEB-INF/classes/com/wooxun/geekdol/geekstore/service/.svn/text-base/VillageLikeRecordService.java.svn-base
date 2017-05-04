package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.vo.VillageLikeRecordVo;

/**
 * @Title
 * @Description 小区点赞service
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月22日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月22日  下午5:21:57 		创建
 *==========================================================
 * 
 */
public interface VillageLikeRecordService <T extends Serializable> extends CrudService<T>{
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询:点赞列表查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:39:16
	 * @param villageLikeRecordVo
	 * @return List
	 */
	public List<VillageLikeRecordVo> findAllVillageLikeRecord(VillageLikeRecordVo villageLikeRecordVo);
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询:点赞列表总数查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:40:03
	 * @param villageLikeRecordVo
	 * @return Long
	 */
	public Long findAllVillageLikeRecordCount(VillageLikeRecordVo villageLikeRecordVo);
	
	/**
	 * 
	 * @Title 点赞记录查询
	 * @Description 根据条件查找点赞记录列表
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 下午2:17:27
	 * @param villageLikeRecordVo
	 * @return List集合
	 */
	public List<VillageLikeRecordVo> findVillageLikeRecord(VillageLikeRecordVo villageLikeRecordVo);
	
	/**
	 * 
	 * @Title 点赞记录查询
	 * @Description 根据条件查找点赞记录总数
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 下午2:19:20
	 * @param villageLikeRecordVo
	 * @return Long
	 */
	public Long findVillageLikeRecordCount(VillageLikeRecordVo villageLikeRecordVo);
}
