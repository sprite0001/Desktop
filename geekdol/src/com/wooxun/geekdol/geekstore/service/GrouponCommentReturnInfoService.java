package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.vo.GrouponCommentReturnInfoVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月5日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月5日  下午5:46:50 		创建
 *==========================================================
 * 
 */
public interface GrouponCommentReturnInfoService <GrouponCommentReturnInfo extends Serializable> extends CrudService<GrouponCommentReturnInfo>{
	
	/**
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年8月5日 下午5:52:35
	 * @param grouponCommentReturnInfo
	 * @return Long
	 */
	public Long getGrouponCommentReturnInfoCount(GrouponCommentReturnInfo grouponCommentReturnInfo);
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查找评论的回复列表
	 * @author:YK
	 * @CreateDate:2016年8月8日 上午10:26:37
	 * @param grouponCommentReturnInfoVo
	 * @return List
	 */
	public List<GrouponCommentReturnInfoVo> queryByParams(GrouponCommentReturnInfoVo grouponCommentReturnInfoVo);
	/**
	 * 
	 * @Title
	 * @Description 根据主键查找团购评论的回复
	 * @author:YK
	 * @CreateDate:2016年8月15日 下午7:10:10
	 * @param id
	 * @return GrouponCommentReturnInfo
	 */
	public GrouponCommentReturnInfo findById(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 删除回复
	 * @author:YK
	 * @CreateDate:2016年8月15日 下午7:10:51
	 * @param grouponCommentReturnInfo
	 * @return boolean
	 */
	public boolean deleteGrouponCommentReturnInfo(GrouponCommentReturnInfo grouponCommentReturnInfo);
}
