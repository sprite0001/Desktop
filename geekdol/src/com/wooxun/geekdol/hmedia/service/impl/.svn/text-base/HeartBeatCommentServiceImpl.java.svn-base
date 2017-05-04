package com.wooxun.geekdol.hmedia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatCommentMapper;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.service.HeartBeatCommentService;
import com.wooxun.geekdol.hmedia.vo.HeartBeatCommentVo;
/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author zhangyang	
* @CreateDate 2016年9月13日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1.  zhangyang	2016年9月13日  下午1:46:21 		创建
*==========================================================
*
 */

@Service
public class HeartBeatCommentServiceImpl extends CrudServiceImpl<HeartBeatComment> implements HeartBeatCommentService<HeartBeatComment> {

	private HeartBeatCommentMapper<HeartBeatComment> heartBeatCommentMapper;
	@Autowired
	private HeartBeatMapper<HeartBeat> heartBeatMapper;

	@Autowired
	public HeartBeatCommentServiceImpl(HeartBeatCommentMapper<HeartBeatComment> heartBeatCommentMapper) {
		super(heartBeatCommentMapper);
		this.heartBeatCommentMapper = heartBeatCommentMapper;
	}

    @Override
    public List<HeartBeatComment> queryListByParam(
            HeartBeatCommentVo heartBeatCommentVo) {
        return heartBeatCommentMapper.queryListByParam(heartBeatCommentVo);
    }

    @Override
    public Long queryCountByParam(HeartBeatCommentVo heartBeatCommentVo) {
        return heartBeatCommentMapper.queryCountByParam(heartBeatCommentVo);
    }

    @Override
    public List<HeartBeatCommentVo> queryListByUser(
            HeartBeatCommentVo heartBeatCommentVo) {
        return heartBeatCommentMapper.queryListByUser(heartBeatCommentVo);
    }

    @Override
    public Long queryCountByUser(HeartBeatCommentVo heartBeatCommentVo) {
        return heartBeatCommentMapper.queryCountByUser(heartBeatCommentVo);
    }

    @Override
    public List<HeartBeatCommentVo> queryListByUserApp(
            HeartBeatCommentVo heartBeatCommentVo) {
        return heartBeatCommentMapper.queryListByUserApp(heartBeatCommentVo);
    }

	@Override
	public boolean saveHeartBeatComment(HeartBeatComment heartBeatComment, HeartBeat heartBeat) {
		// 插入评论主数据信息
		int i = heartBeatCommentMapper.insertSelective(heartBeatComment);
		
		// 更新随心拍主数据的评论量+1
		heartBeat.setReplyNumber(heartBeat.getReplyNumber() + 1);
		heartBeat.setUpdEac(heartBeat.getUpdEac() + 1);
		heartBeat.setUpdYmdhms(new Date());
		i = heartBeatMapper.updateByPrimaryKeySelective(heartBeat);
		
		return i>0?true:false;
	}

	@Override
	public boolean deleteHeartBeatComment(HeartBeatComment heartBeatComment) {
		// 更新删除评论
		int i = heartBeatCommentMapper.updateByPrimaryKeySelective(heartBeatComment);
		// 查询随心拍主数据，并更新回复量
		HeartBeat heartBeat = heartBeatMapper.selectByPrimaryKey(heartBeatComment.getHearId());
		if(heartBeat != null){
			heartBeat.setReplyNumber(heartBeat.getReplyNumber()-1);
			heartBeat.setUpdEac(heartBeat.getUpdEac()+1);
			heartBeat.setUpdId(heartBeatComment.getUpdId());
			heartBeat.setUpdYmdhms(new Date());
			i = heartBeatMapper.updateByPrimaryKeySelective(heartBeat);
		}
		return i>0?true:false;
	}

}
