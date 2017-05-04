package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentVo;

public interface IncorruptGovernmentCommentMapper <T extends Serializable> extends CrudMapper<T> {
	
	/**
	 * 
	 * @Title
	 * @Description 评论总数查询
	 * @author:YK
	 * @CreateDate:2016年9月26日 下午2:53:47
	 * @param incorruptGovernmentCommentVo
	 * @return Long
	 */
	public Long selectCountByIncorruptGovernmentId(IncorruptGovernmentCommentVo incorruptGovernmentCommentVo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据风清气正查询评论列表
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午4:04:47
	 * @param incorruptGovernmentCommentVo
	 * @return List
	 */
	public List<IncorruptGovernmentCommentVo> selectByIncorruptGovernmentId(IncorruptGovernmentCommentVo incorruptGovernmentCommentVo); 
	
	/**
	 * 
	 * @Title
	 * @Description 根据主键id查找评论vo对象
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午10:09:20
	 * @param id
	 * @return IncorruptGovernmentCommentVo
	 */
	public IncorruptGovernmentCommentVo findCommentVoById(Long id);
}