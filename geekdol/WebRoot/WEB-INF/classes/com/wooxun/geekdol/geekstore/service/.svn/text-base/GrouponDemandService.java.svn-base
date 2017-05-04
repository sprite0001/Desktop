package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GrouponDemand;
import com.wooxun.geekdol.geekstore.model.GrouponDemandComment;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentReturnInfoVo;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentVo;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandVo;

/**
 * @Title 吉客店管理-我要团管理
 * @Description 
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月1日  下午2:28:58 		创建
 *==========================================================
 * 
 */
public interface GrouponDemandService <T extends Serializable> extends CrudService<T>{
	
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
	 * @Description 根据ID查询团购信息
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午5:21:00
	 * @param id
	 * @return
	 */
	public GrouponDemand findByIdGrouponDemand(Long id);
	
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 根据团购ID查询出团购的具体信息(传进来的是grouponDemandVo以后可以在其他地方用)
	 * @author:田振兴
	 * @CreateDate:2016年8月2日 下午4:07:23
	 * @param grouponDemandVo
	 * @return
	 */
	public GrouponDemandVo findGrouponDemand(GrouponDemandVo grouponDemandVo);
	
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 评论人列表
	 * @author:田振兴
	 * @CreateDate:2016年8月2日 下午6:33:36
	 * @param grouponDemandCommentVo
	 * @return
	 */
	public List<GrouponDemandCommentVo> findComments(GrouponDemandCommentVo grouponDemandCommentVo);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 根据评论人Id查询评论人的具体信息
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 上午10:24:40
	 * @param id
	 * @return
	 */
	public GrouponDemandCommentVo findOneComments(GrouponDemandCommentVo grouponDemandCommentVo);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 根据评论人Id查询评论人的简单信息
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 下午5:03:58
	 * @param id
	 * @return
	 */
	public GrouponDemandComment findOneCommentsEasy(Long id);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description  回复列表
	 * @author:田振兴
	 * @CreateDate:2016年8月2日 下午9:42:38
	 * @param grouponDemandCommentReturnInfoVo
	 * @return
	 */
	public List<GrouponDemandCommentReturnInfoVo> findReturnInfo(GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 根据评论人ID查询回复的总数以及违规的总数
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 下午2:30:14
	 * @param grouponDemandCommentReturnInfoVo
	 * @return
	 */
	public Long findReturnInfoCount(GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 根据评论人ID删除评论人信息
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 下午5:12:54
	 * @param grouponDemandComment
	 * @return
	 */
	public int deleteGrouponDemandComment(GrouponDemandComment grouponDemandComment);
	
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 统计
	 * @author:田振兴
	 * @CreateDate:2016年8月10日 上午10:54:32
	 * @param grouponDemandVo
	 * @return
	 */
	public List<GrouponDemandVo> findCensus(GrouponDemandVo grouponDemandVo);

}
