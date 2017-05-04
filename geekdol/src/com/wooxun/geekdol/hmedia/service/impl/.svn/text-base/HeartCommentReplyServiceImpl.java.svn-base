package com.wooxun.geekdol.hmedia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatCommentMapper;
import com.wooxun.geekdol.hmedia.mapper.HeartCommentReplyMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.model.HeartCommentReply;
import com.wooxun.geekdol.hmedia.service.HeartCommentReplyService;
import com.wooxun.geekdol.hmedia.vo.HeartCommentReplyVo;
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
public class HeartCommentReplyServiceImpl extends CrudServiceImpl<HeartCommentReply> implements HeartCommentReplyService<HeartCommentReply> {

	private HeartCommentReplyMapper<HeartCommentReply> heartCommentReplyMapper;
	@Autowired
	private HeartBeatCommentMapper<HeartBeatComment> heartBeatCommentMapper;

	@Autowired
	public HeartCommentReplyServiceImpl(HeartCommentReplyMapper<HeartCommentReply> heartCommentReplyMapper) {
		super(heartCommentReplyMapper);
		this.heartCommentReplyMapper = heartCommentReplyMapper;
	}

    @Override
    public List<HeartCommentReplyVo> queryListByUser(
            HeartCommentReplyVo heartCommentReplyVo) {
        return heartCommentReplyMapper.queryListByUser(heartCommentReplyVo);
    }

    @Override
    public Long queryCountByUser(HeartCommentReplyVo heartCommentReplyVo) {
        return heartCommentReplyMapper.queryCountByUser(heartCommentReplyVo);
    }

    @Override
    public List<HeartCommentReplyVo> queryListByUserApp(HeartCommentReplyVo heartCommentReplyVo) {
        return heartCommentReplyMapper.queryListByUserApp(heartCommentReplyVo);
    }

	@Override
	public boolean saveReplay(HeartCommentReply heartCommentReply, HeartBeatComment heartBeatComment) {
		// 保存评论的回复主数据信息
		int i = heartCommentReplyMapper.insertSelective(heartCommentReply);
		
		// 修改随心拍评论的浏览量
		heartBeatComment.setReplyNumber(heartBeatComment.getReplyNumber() + 1);
		heartBeatComment.setUpdEac(heartBeatComment.getUpdEac() + 1);
		heartBeatComment.setUpdYmdhms(new Date());
		i = heartBeatCommentMapper.updateByPrimaryKeySelective(heartBeatComment);
		
		return i>0?true:false;
	}

	@Override
	public boolean deleteReplay(HeartCommentReply heartCommentReply) {
		// 更新评论的回复主数据信息
		int i = heartCommentReplyMapper.updateByPrimaryKeySelective(heartCommentReply);
		// 更新评论的回复量
		HeartBeatComment heartBeatComment = heartBeatCommentMapper.selectByPrimaryKey(heartCommentReply.getHeartCommentId());
		if(heartBeatComment != null){
			heartBeatComment.setReplyNumber(heartBeatComment.getReplyNumber()-1);
			heartBeatComment.setUpdEac(heartBeatComment.getUpdEac()+1);
			heartBeatComment.setUpdId(heartCommentReply.getUpdId());
			heartBeatComment.setUpdYmdhms(new Date());
			i = heartBeatCommentMapper.updateByPrimaryKeySelective(heartBeatComment);
		}
		
		return i>0?true:false;
	}

}
