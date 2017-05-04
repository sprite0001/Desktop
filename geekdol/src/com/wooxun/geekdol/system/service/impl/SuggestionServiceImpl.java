package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.SuggestionMapper;
import com.wooxun.geekdol.system.model.Suggestion;
import com.wooxun.geekdol.system.service.SuggestionService;
import com.wooxun.geekdol.system.vo.SuggestionVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午5:23:23 		创建
 *==========================================================
 * 
 */
@Service
public class SuggestionServiceImpl extends CrudServiceImpl<Suggestion> implements SuggestionService<Suggestion>{
	
	@Autowired
	private SuggestionMapper<Suggestion> suggestionMapper;
	@Autowired
	public SuggestionServiceImpl(SuggestionMapper<Suggestion> suggestionMapper) {
		super(suggestionMapper);
		this.suggestionMapper = suggestionMapper;
	}
	@Override
	public List<Suggestion> findAll(SuggestionVo suggestionVo) {
		return suggestionMapper.findAll(suggestionVo);
	}
	@Override
	public Long findAllCount(SuggestionVo suggestionVo) {
		return suggestionMapper.findAllCount(suggestionVo);
	}

}
