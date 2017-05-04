package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentCommentR;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentRVo;

public interface IncorruptGovernmentCommentRMapper <T extends Serializable> extends CrudMapper<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 根据评论id查找回复信息
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午9:10:37
	 * @param id
	 * @return List
	 */
	public List<IncorruptGovernmentCommentR> selectByCommentId(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 批量更新评论的回复
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午9:20:51
	 * @param list
	 * @return int
	 */
	public int batchUpdate(List<IncorruptGovernmentCommentR> list);
	
	/**
	 * 
	 * @Title
	 * @Description 回复总数查询
	 * @author:YK
	 * @CreateDate:2016年9月26日 下午3:33:24
	 * @param returnInfoVo
	 * @return Long
	 */
	public Long queryCountByParams(IncorruptGovernmentCommentRVo returnInfoVo);
	
	/**
	 * 
	 * @Title
	 * @Description 组合条件查找评论的回复列表信息
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午11:04:01
	 * @param returnInfoVo
	 * @return List
	 */
	public List<IncorruptGovernmentCommentRVo> queryByParams(IncorruptGovernmentCommentRVo returnInfoVo);
	
	/**
	 * 
	 * @Title
	 * @Description 手机端根据评论id查找评论的回复列表
	 * @author:YK
	 * @CreateDate:2016年9月13日 下午3:20:02
	 * @param returnInfo
	 * @return List
	 */
	public List<Map<String,Object>> queryListByParam(IncorruptGovernmentCommentRVo returnInfo);
}