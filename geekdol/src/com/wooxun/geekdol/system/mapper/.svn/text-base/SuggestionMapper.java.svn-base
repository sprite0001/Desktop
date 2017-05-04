package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Suggestion;
import com.wooxun.geekdol.system.vo.SuggestionVo;

public interface SuggestionMapper <T extends Serializable> extends CrudMapper<T>{
	
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
	 * @Description 根据页面条件查询，查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午3:21:30
	 * @param suggestionVo
	 * @return Long
	 */
	public Long findAllCount(SuggestionVo suggestionVo);
}