package com.wooxun.geekdol.hbridge.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.hbridge.mapper.IncorruptGovernmentCommentMapper;
import com.wooxun.geekdol.hbridge.mapper.IncorruptGovernmentCommentRMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentComment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentCommentR;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentCommentRService;
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
 * 1. 	 YK	2016年9月10日  上午10:59:46 		创建
 *==========================================================
 * 
 */
@Service
public class IncorruptGovernmentCommentRServiceImpl extends CrudServiceImpl<IncorruptGovernmentCommentR> 
	implements IncorruptGovernmentCommentRService<IncorruptGovernmentCommentR> {
	
	private IncorruptGovernmentCommentRMapper<IncorruptGovernmentCommentR> returnInfoMapper;
	@Autowired
	private IncorruptGovernmentCommentMapper<IncorruptGovernmentComment> commentMapper;
	
	@Autowired
	public IncorruptGovernmentCommentRServiceImpl(IncorruptGovernmentCommentRMapper<IncorruptGovernmentCommentR> returnInfoMapper){
		super(returnInfoMapper);
		this.returnInfoMapper = returnInfoMapper;
	}
	
	@Override
	public Long queryCountByParams(IncorruptGovernmentCommentRVo returnInfoVo) {
		// 昵称搜索条件加密搜索
		if(returnInfoVo != null && StringUtils.isNotBlank(returnInfoVo.getNickName())){
			returnInfoVo.setNickName(UnescapeUtil.escape(returnInfoVo.getNickName()));
		}
		// 内容搜索条件加密搜索
		if(returnInfoVo != null && StringUtils.isNotBlank(returnInfoVo.getContent())){
			returnInfoVo.setContent(UnescapeUtil.escape(returnInfoVo.getContent()));
		}
		return returnInfoMapper.queryCountByParams(returnInfoVo);
	}
	
	@Override
	public List<IncorruptGovernmentCommentRVo> queryByParams(IncorruptGovernmentCommentRVo returnInfoVo) {
		return returnInfoMapper.queryByParams(returnInfoVo);
	}

	@Override
	public IncorruptGovernmentCommentR findById(Long id) {
		return returnInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean deleteCommentReturnInfo(IncorruptGovernmentCommentR returnInfo) {
		// 删除评论的回复主数据信息
		int i = returnInfoMapper.updateByPrimaryKeySelective(returnInfo);
		// 根据回复的评论id查找评论
		IncorruptGovernmentComment comment = commentMapper.selectByPrimaryKey(returnInfo.getIncorruptGovernmentCommentId());
		if(comment == null){// 如果评论已经被删除，则返回false
			return false;
		}
		// 评论的回复量减1
		comment.setReplyNumber(comment.getReplyNumber()-1);
		// 判断回复的状态是否违规,评论的违规数量-1
		if(ConstantStr.WG_SUM.equals(returnInfo.getIllegalStatus())){
			comment.setIllegalNumber(comment.getIllegalNumber()-1);
		}
		// 设置评论的更新回数、更新人、更新时间属性信息
		comment.setUpdEac(comment.getUpdEac()+1);
		comment.setUpdId(returnInfo.getUpdId());
		comment.setUpdYmdhms(new Date());
		// 更新评论的回复量
		i = commentMapper.updateByPrimaryKeySelective(comment);
		return i>0?true:false;
	}

	@Override
	public List<Map<String, Object>> queryListByParam(IncorruptGovernmentCommentRVo returnInfo) {
		return returnInfoMapper.queryListByParam(returnInfo);
	}

	@Override
	public boolean saveCommentR(IncorruptGovernmentCommentR returnInfo) {
		// 保存回复数据
		int i = returnInfoMapper.insertSelective(returnInfo);
		// 根据回复的评论id查找评论
		IncorruptGovernmentComment comment = commentMapper.selectByPrimaryKey(returnInfo.getIncorruptGovernmentCommentId());
		if(comment == null){// 如果评论已经被删除，则返回false
			return false;
		}
		// 评论的回复量+1
		comment.setReplyNumber(comment.getReplyNumber()+1);
		// 判断回复的状态是否违规,评论的违规数量+1
		if(ConstantStr.WG_SUM.equals(returnInfo.getIllegalStatus())){
			comment.setIllegalNumber(comment.getIllegalNumber()+1);
		}
		// 设置评论的更新回数、更新时间属性信息
		comment.setUpdEac(comment.getUpdEac()+1);
		comment.setUpdYmdhms(new Date());
		// 更新评论的回复量
		i = commentMapper.updateByPrimaryKeySelective(comment);
		return i>0?true:false;
	}
	
}
