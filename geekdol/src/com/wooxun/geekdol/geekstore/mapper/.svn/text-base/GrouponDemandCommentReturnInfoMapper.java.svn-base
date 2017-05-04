package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentReturnInfoVo;


public interface GrouponDemandCommentReturnInfoMapper <T extends Serializable> extends CrudMapper<T>{
	
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
	 * @Description 批量删除回复(物理删除)
	 * @author:田振兴
	 * @CreateDate:2016年8月16日 上午10:07:06
	 * @param commentId
	 * @return
	 */
	public int deleteSelective(Long commentId);
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 批量删除回复(逻辑删除)
	 * @author:田振兴
	 * @CreateDate:2016年8月16日 上午10:07:06
	 * @param commentId
	 * @return
	 */
	public int updateSelective(Map<String, Object> commentReturnInfoList);
   
}