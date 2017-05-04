package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.GrouponCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.model.GrouponCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.GrouponCommentReturnInfoService;
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
 * 1. 	 YK	2016年8月5日  上午11:01:51 		创建
 *==========================================================
 * 
 */
@Service
public class GrouponCommentReturnInfoServiceImpl extends CrudServiceImpl<GrouponCommentReturnInfo>
	implements GrouponCommentReturnInfoService<GrouponCommentReturnInfo> {
	
	private GrouponCommentReturnInfoMapper<GrouponCommentReturnInfo> grouponCommentReturnInfoMapper;
	@Autowired
    public GrouponCommentReturnInfoServiceImpl(GrouponCommentReturnInfoMapper
    		<GrouponCommentReturnInfo> grouponCommentReturnInfoMapper) {
        super(grouponCommentReturnInfoMapper);
        this.grouponCommentReturnInfoMapper = grouponCommentReturnInfoMapper;
    }
	@Override
	public Long getGrouponCommentReturnInfoCount(GrouponCommentReturnInfo grouponCommentReturnInfo) {
		return grouponCommentReturnInfoMapper.getGrouponCommentReturnInfoCount(grouponCommentReturnInfo);
	}
	@Override
	public List<GrouponCommentReturnInfoVo> queryByParams(GrouponCommentReturnInfoVo grouponCommentReturnInfoVo) {
		return grouponCommentReturnInfoMapper.queryByParams(grouponCommentReturnInfoVo);
	}
	@Override
	public GrouponCommentReturnInfo findById(Long id) {
		return grouponCommentReturnInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public boolean deleteGrouponCommentReturnInfo(
			GrouponCommentReturnInfo grouponCommentReturnInfo) {
		int i = grouponCommentReturnInfoMapper.updateByPrimaryKeySelective(grouponCommentReturnInfo);
		return i>0?true:false;
	}

}
