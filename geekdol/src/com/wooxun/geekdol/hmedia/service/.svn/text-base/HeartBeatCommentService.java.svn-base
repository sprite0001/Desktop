package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.vo.HeartBeatCommentVo;

/**
 * 
 * @Title 随心拍一级评论的service
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年9月13日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. zhangyang 2016年9月13日 下午1:44:28 创建
 *           ==========================================================
 *
 */
public interface HeartBeatCommentService<T extends Serializable> extends
		CrudService<T> {
	/**
	 * 
	 * @Title
	 * @Description 条件查询
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatCommentVo
	 * @return 列表
	 */
	public List<HeartBeatComment> queryListByParam(
			HeartBeatCommentVo heartBeatCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatCommentVo
	 * @return 总数量
	 */
	public Long queryCountByParam(HeartBeatCommentVo heartBeatCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联用户
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatCommentVo
	 * @return 列表
	 */
	public List<HeartBeatCommentVo> queryListByUser(
			HeartBeatCommentVo heartBeatCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 根据随心拍id查出所有评论，关联用户,app用，查出头像和二级回复量
	 * @author:张洋
	 * @CreateDate:2016年9月20日16:07:58
	 * @param heartBeatCommentVo
	 * @return 列表
	 */
	public List<HeartBeatCommentVo> queryListByUserApp(
			HeartBeatCommentVo heartBeatCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联用户
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatCommentVo
	 * @return 总数量
	 */
	public Long queryCountByUser(HeartBeatCommentVo heartBeatCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 保存随心拍评论并更新随心拍的评论量
	 * @author:YK
	 * @CreateDate:2016年9月22日 下午6:56:42
	 * @param heartBeatComment
	 * @param heartBeat
	 * @return boolean
	 */
	public boolean saveHeartBeatComment(HeartBeatComment heartBeatComment,
			HeartBeat heartBeat);
	
	/**
	 * 
	 * @Title
	 * @Description 删除评论并更新随心拍主数据
	 * @author:YK
	 * @CreateDate:2016年9月23日 下午6:18:59
	 * @param heartBeatComment
	 * @return boolean
	 */
	public boolean deleteHeartBeatComment(HeartBeatComment heartBeatComment);
}
