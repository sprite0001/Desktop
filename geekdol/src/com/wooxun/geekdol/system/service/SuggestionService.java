package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.Suggestion;
import com.wooxun.geekdol.system.vo.SuggestionVo;

/**
 * @Title 投诉建议
 * @Description 投诉建议查询
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午5:19:20 		创建
 *==========================================================
 * 
 */
public interface SuggestionService <T extends Serializable> extends CrudService<T>{
	
	
	/**
	 * 
	 * @Title 投诉建议
	 * @Description 根据页面条件查询，显示列表
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:20:52
	 * @param suggestionVo
	 * @return List集合
	 */
	public List<Suggestion> findAll(SuggestionVo suggestionVo);
	
	
	/**
	 * 
	 * @Title 投诉建议
	 * @Description 根据页面条件查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:21:30
	 * @param suggestionVo
	 * @return Long
	 */
	public Long findAllCount(SuggestionVo suggestionVo);
}
