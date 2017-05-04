package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentComment;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月9日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月9日  下午3:57:36 		创建
 *==========================================================
 * 
 */
public interface IncorruptGovernmentCommentService<T extends Serializable> extends CrudService<T> {
	
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
	 * @Description 根据主键查找评论详情
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午6:05:59
	 * @param id
	 * @return IncorruptGovernmentComment
	 */
	public IncorruptGovernmentComment findById(Long id);
	
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
	
	/**
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午6:00:37
	 * @param commentVo
	 * @return boolean
	 */
	public boolean delteIncorruptGovernmentComment(IncorruptGovernmentComment comment);
	
	/**
	 * 
	 * @Title
	 * @Description 保存评论内容
	 * @author:YK
	 * @CreateDate:2016年9月13日 上午11:44:39
	 * @param comment
	 * @return boolean
	 */
	public boolean saveComment(IncorruptGovernmentComment comment);
	
	/**
	 * 
	 * @Title
	 * @Description 更新评论并保存点赞记录
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午9:09:19
	 * @param comment
	 * @param IncorruptCommentRecommend
	 * @return boolean
	 */
	public boolean updateComent(IncorruptGovernmentComment comment,IncorruptCommentRecommend commendRecommend);
}
