package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentCommentR;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentRVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月10日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月10日  上午10:58:15 		创建
 *==========================================================
 * 
 */
public interface IncorruptGovernmentCommentRService<T extends Serializable> extends CrudService<T> {
	
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
	 * @Description 根据主键查找评论的回复
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午11:43:39
	 * @param id
	 * @return IncorruptGovernmentCommentR
	 */
	public IncorruptGovernmentCommentR findById(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 删除评论的回复内容
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午11:46:16
	 * @param returnInfo
	 * @return boolean
	 */
	public boolean deleteCommentReturnInfo(IncorruptGovernmentCommentR returnInfo);
	
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
	
	/**
	 * 
	 * @Title
	 * @Description 保存风清气正评论回复内容
	 * @author:YK
	 * @CreateDate:2016年9月14日 上午9:40:05
	 * @param returnInfo
	 * @return boolean
	 */
	public boolean saveCommentR(IncorruptGovernmentCommentR returnInfo);
}
