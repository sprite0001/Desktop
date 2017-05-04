package com.wooxun.geekdol.hbridge.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.hbridge.mapper.IncorruptCommentRecommendMapper;
import com.wooxun.geekdol.hbridge.mapper.IncorruptGovernmentCommentMapper;
import com.wooxun.geekdol.hbridge.mapper.IncorruptGovernmentCommentRMapper;
import com.wooxun.geekdol.hbridge.mapper.IncorruptGovernmentMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentComment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentCommentR;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentCommentService;
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
 * 1. 	 YK	2016年9月9日  下午3:58:51 		创建
 *==========================================================
 * 
 */
@Service
public class IncorruptGovernmentCommentServiceImpl extends CrudServiceImpl<IncorruptGovernmentComment> implements
		IncorruptGovernmentCommentService<IncorruptGovernmentComment> {
	
	private IncorruptGovernmentCommentMapper<IncorruptGovernmentComment> incorruptGovernmentCommentMapper;
	@Autowired
	private IncorruptGovernmentCommentRMapper<IncorruptGovernmentCommentR> commentRMapper;
	@Autowired
	private IncorruptGovernmentMapper<IncorruptGovernment> incorruptGovernmentMapper;
	@Autowired
	private IncorruptCommentRecommendMapper<IncorruptCommentRecommend> commentRecommendMapper;
	
	@Autowired
	public IncorruptGovernmentCommentServiceImpl(IncorruptGovernmentCommentMapper
				<IncorruptGovernmentComment> incorruptGovernmentCommentMapper) {
		super(incorruptGovernmentCommentMapper);
		this.incorruptGovernmentCommentMapper = incorruptGovernmentCommentMapper;
	}
	
	@Override
	public Long selectCountByIncorruptGovernmentId(IncorruptGovernmentCommentVo incorruptGovernmentCommentVo) {
		// 搜索条件加密搜索
		if(incorruptGovernmentCommentVo != null && StringUtils.isNotBlank(incorruptGovernmentCommentVo.getNickName())){
			incorruptGovernmentCommentVo.setNickName(UnescapeUtil.escape(incorruptGovernmentCommentVo.getNickName()));
		}
		// 内容搜索条件加密搜索
		if(incorruptGovernmentCommentVo != null && StringUtils.isNotBlank(incorruptGovernmentCommentVo.getContent())){
			incorruptGovernmentCommentVo.setContent(UnescapeUtil.escape(incorruptGovernmentCommentVo.getContent()));
		}
		return incorruptGovernmentCommentMapper.selectCountByIncorruptGovernmentId(incorruptGovernmentCommentVo);
	}
	
	@Override
	public List<IncorruptGovernmentCommentVo> selectByIncorruptGovernmentId(
			IncorruptGovernmentCommentVo incorruptGovernmentCommentVo) {
		// 因为在查询总数的时候，搜索条件的昵称与内容已经加密，所以不需要再次加密
		return incorruptGovernmentCommentMapper.selectByIncorruptGovernmentId(incorruptGovernmentCommentVo);
	}

	@Override
	public boolean delteIncorruptGovernmentComment(IncorruptGovernmentComment comment) {
		// 更新删除评论主数据信息
		int i = incorruptGovernmentCommentMapper.updateByPrimaryKeySelective(comment);
		// 查找评论的所有回复信息
		List<IncorruptGovernmentCommentR> commentRList = commentRMapper.selectByCommentId(comment.getId());
		for(IncorruptGovernmentCommentR commentR:commentRList){
			// 设置删除标示符
			commentR.setDelFlag(ConstantStr.DELETE_Y);
			//设置更新人
			commentR.setUpdId(comment.getUpdId());
			// 设置更新回数
			commentR.setUpdEac(commentR.getUpdEac()+1);
			// 设置更新时间
			commentR.setUpdYmdhms(new Date());
		}
		// 批量更新删除评论的回复
		if(commentRList != null && commentRList.size()>0){
			i = commentRMapper.batchUpdate(commentRList);
		}
		// 查找风清气正主数据信息并更新回复量、违规量
		IncorruptGovernment incorruptGovernment = 
				incorruptGovernmentMapper.selectByPrimaryKey(comment.getIncorruptGovernmentId());
		// 设置风清气正回复量-1
		incorruptGovernment.setReplyNumber(incorruptGovernment.getReplyNumber()-1);
		// 根据评论的违规状态判断是否更新风清气正违规量-1
		if(ConstantStr.WG_SUM.equals(comment.getIllegalStatus())){
			incorruptGovernment.setIllegalNumber(incorruptGovernment.getIllegalNumber()-1);
		}
		// 设置风清气正更新人
		incorruptGovernment.setUpdId(comment.getUpdId());
		// 设置风清气正更新回数+1
		incorruptGovernment.setUpdEac(incorruptGovernment.getUpdEac()+1);
		// 设置风清气正更新时间
		incorruptGovernment.setUpdYmdhms(new Date());
		// 更新风清气正数据信息
		i = incorruptGovernmentMapper.updateByPrimaryKeySelective(incorruptGovernment);
		return i>0?true:false;
	}

	@Override
	public IncorruptGovernmentComment findById(Long id) {
		return incorruptGovernmentCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public IncorruptGovernmentCommentVo findCommentVoById(Long id) {
		return incorruptGovernmentCommentMapper.findCommentVoById(id);
	}

	@Override
	public boolean saveComment(IncorruptGovernmentComment comment) {
		// 保存评论内容
		int i = incorruptGovernmentCommentMapper.insertSelective(comment);
		// 更新风清气正的主数据
		IncorruptGovernment incorruptGovernment = 
				incorruptGovernmentMapper.selectByPrimaryKey(comment.getIncorruptGovernmentId());
		// 修改风清气正违规量
		if(ConstantStr.WG_SUM.equals(comment.getIllegalStatus())){
			incorruptGovernment.setIllegalNumber(incorruptGovernment.getIllegalNumber()+1);
		}
		// 修改风清气正回复量+1
		incorruptGovernment.setReplyNumber(incorruptGovernment.getReplyNumber()+1);
		// 修改风情气正更新回数
		incorruptGovernment.setUpdEac(incorruptGovernment.getUpdEac()+1);
		// 更新风清气正主数据信息
		i = incorruptGovernmentMapper.updateByPrimaryKeySelective(incorruptGovernment);
		return i>0?true:false;
	}

	@Override
	public boolean updateComent(IncorruptGovernmentComment comment,IncorruptCommentRecommend commendRecommend) {
		// 更新评论主数据信息
		int i = incorruptGovernmentCommentMapper.updateByPrimaryKeySelective(comment);
		if(commendRecommend.getId() != null){ // 取消点赞删除点赞历史记录
			i = commentRecommendMapper.deteByCommentIdAndUserId(commendRecommend);
		}else{ // 新增点赞记录
			i = commentRecommendMapper.insertSelective(commendRecommend);
		}
		return i>0?true:false;
	}
}
