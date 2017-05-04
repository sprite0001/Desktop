package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.model.IntimateNewsComment;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentRecommend;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentReply;
import com.wooxun.geekdol.hmedia.vo.AppIntimateNewsVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsVo;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author Liys
 * @CreateDate 2016年7月28日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. Liys 2016年7月28日 下午4:46:17 创建
 *           ==========================================================
 * 
 */
public interface IntimateNewsService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description 保存贴心报
	 * @author:王肖东
	 * @CreateDate:2016年7月30日 下午1:58:06
	 * @param intimateNews
	 * @param villageId
	 * @return
	 */
	public boolean insertIntimateNews(IntimateNews intimateNews, String villageId);

	/**
	 * 
	 * @Title
	 * @Description 查询贴心报总数
	 * @author:王肖东
	 * @CreateDate:2016年7月30日 下午1:58:44
	 * @param searchVillage
	 * @return
	 */
	public Long queryCountByParams(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description:后台查询贴心报列表
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchVillage
	 * @return
	 */
	public List<IntimateNewsVo> queryIntimateNewsByParams(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id找到贴心报
	 * @author:王肖东
	 * @CreateDate:2016年7月27日 上午10:42:05
	 * @param id
	 * @return IntimateNewsVo
	 */
	public IntimateNewsVo selectIntimateNewsVo(Long id);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id找到贴心报
	 * @author:王肖东
	 * @CreateDate:2016年7月27日 上午10:42:05
	 * @param id
	 * @return IntimateNewsVo
	 */
	public IntimateNews selectIntimateNews(Long id);

	/**
	 * 
	 * @Title
	 * @Description 删除贴心报
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 上午11:08:18
	 * @param intimateNews
	 * @return
	 */
	public boolean deleteIntimateNews(IntimateNews intimateNews);

	/**
	 * 
	 * @Title
	 * @Description 贴心报管理：删除贴心报回复
	 * @author:QZG
	 * @CreateDate:2016年8月10日 下午10:24:06
	 * @param intimateNewsComment
	 * @param intimateNewsCommentVo
	 * @return
	 */
	public boolean deleteIntimateNewsComment(IntimateNewsComment intimateNewsComment,
			IntimateNewsCommentVo intimateNewsCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 贴心报管理：删除贴心报回复的回复
	 * @author:QZG
	 * @CreateDate:2016年8月10日 下午11:41:23
	 * @param intimateNewsCommentReply
	 * @param intimateNewsCommentReplyVo
	 * @return
	 */
	public boolean deleteIntimateNewsCommentReply(IntimateNewsCommentReply intimateNewsCommentReply,
			IntimateNewsCommentReplyVo intimateNewsCommentReplyVo);

	/**
	 * 
	 * @Title
	 * @Description 查询贴心报的评论总数
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午2:03:42
	 * @param searchIntimateNews
	 * @return
	 */
	public Long queryCountCommentByParams(IntimateNewsCommentVo intimateNewsCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 后台查询贴心报的评论列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午2:04:21
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsCommentVo> queryListCommentByParams(IntimateNewsCommentVo intimateNewsCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 查询未审核的贴心报列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午2:04:21
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsVo> queryListWeishenhe(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 查询未审核的贴心报总数
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午2:04:21
	 * @param searchIntimateNews
	 * @return
	 */
	public Long queryCountWeishenhe(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 根据回复id查找回复的详细信息
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:39:05
	 * @param id
	 * @return
	 */
	public IntimateNewsCommentVo findIntimateNewsCommentDetail(Long id);

	/**
	 * 
	 * @Title
	 * @Description 查看符合条件的贴心报的回复的回复列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:38:07
	 * @param intimateNewsCommentReplyVo
	 * @return
	 */
	public List<IntimateNewsCommentReplyVo> findIntimateNewsCommentReplyList(
			IntimateNewsCommentReplyVo intimateNewsCommentReplyVo);

	/**
	 * 
	 * @Title
	 * @Description 查看符合条件的贴心报的回复的回复的数量
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:39:37
	 * @param intimateNewsCommentReplyVo
	 * @return
	 */
	public Long findIntimateNewsCommentReplyListCount(IntimateNewsCommentReplyVo intimateNewsCommentReplyVo);

	/**
	 * 
	 * @Title
	 * @Description 查找已提交的贴心报总数量（即 verifyStatus>0）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:28:43
	 * @param intimateNewsVo
	 * @return
	 */
	public Long findIntimateNewsYitijiaoCount(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查找已提交的贴心报列表（即 verifyStatus>0）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:38:07
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNewsVo> findIntimateNewsYitijiaoList(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查找已提交的贴心报列表（即 verifyStatus>0 自媒体专用）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:38:07
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNewsVo> findselfIntimateNewsYitijiaoList(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查找已提交的贴心报总数量（即 verifyStatus>0 自媒体专用）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:28:43
	 * @param intimateNewsVo
	 * @return
	 */
	public Long findselfIntimateNewsYitijiaoCount(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查找已审核的贴心报列表（即 verifyStatus=2）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:38:07
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNewsVo> findIntimateNewsYishenheList(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查找已审核的贴心报总数量（即 verifyStatus=2）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:28:43
	 * @param intimateNewsVo
	 * @return
	 */
	public Long findIntimateNewsYiShenheCount(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查询不是本区域的并且是已发布的贴心报(查到后可以转发)
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:38:07
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNewsVo> querotherpersonList(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 查询不是本区域的并且是已发布的贴心报(查到后可以转发)
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:28:43
	 * @param intimateNewsVo
	 * @return
	 */
	public Long querotherpersonListCount(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 贴心报管理：根据id查找贴心报回复的回复
	 * @author:QZG
	 * @CreateDate:2016年8月10日 下午11:28:44
	 * @param id
	 * @return
	 */
	public IntimateNewsCommentReplyVo findIntimateNewsCommentReply(Long id);

	/**
	 * 
	 * @Title
	 * @Description app获取贴心报列表
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午2:50:48
	 * @param villageId
	 * @param appFlag
	 * @return
	 */
	public List<AppIntimateNewsVo> appQueryIntimateNewsByParams(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description app获取评论列表(一级评论)
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午6:09:35
	 * @param intimateNewsCommentVo
	 * @return
	 */
	public List<IntimateNewsComment> appQueryComment(IntimateNewsCommentVo intimateNewsCommentVo);

	/**
	 * @Title
	 * @Description 更新评论浏览量
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 下午4:09:34
	 * @param aroundStoreComment
	 */
	public void updateIntimateNewsComment(IntimateNewsComment intimateNewsComment);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有评论过
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:01:21
	 * @param intimateNewsId
	 * @param appUserId
	 * @return
	 */
	public IntimateNewsComment getIntimateNewsCommentByDay(Long intimateNewsId, Long appUserId);

	/**
	 * 
	 * @Title
	 * @Description 添加贴心报一级评论
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:14:07
	 * @param intimateNewsId
	 * @param appUserId
	 * @param content
	 * @param illegal
	 * @param opreaterIp
	 * @return
	 */
	public int addComment(Long intimateNewsId, Long appUserId, String content, boolean illegal, String opreaterIp);

	/**
	 * 
	 * @Title
	 * @Description 添加贴心报二级评论
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:26:00
	 * @param commentId
	 * @param appUserId
	 * @param content
	 * @param illegal
	 * @param opreaterIp
	 * @return
	 */
	public int addCommentReply(Long commentId, Long appUserId, String content, boolean illegal, String opreaterIp);

	/**
	 * 
	 * @Title
	 * @Description 对贴心报进行点赞 倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:49:01
	 * @param intimateNews
	 * @return
	 */
	public int addIntimateNewsParam(IntimateNews intimateNews);

	/**
	 * 
	 * @Title
	 * @Description 对贴心报一级评论 点赞 倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月17日 上午12:03:19
	 * @param intimateNewsComment
	 * @param commentRecommend
	 * @return int
	 */
	public int addCommentParam(IntimateNewsComment intimateNewsComment,IntimateNewsCommentRecommend commentRecommend);

	/**
	 * 
	 * @Title
	 * @Description 根据回复id查找回复的详细信息 app调用
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:39:05
	 * @param id
	 * @return
	 */
	public IntimateNewsCommentVo findAppIntimateNewsCommentDetail(Long id);

	/**
	 * 
	 * @Title
	 * @Description 根据回复id查找回复的详细信息 app调用
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:39:05
	 * @param id
	 * @return
	 */
	public IntimateNewsCommentVo getCommentDetailByCommentId(Long id);

	/**
	 * 
	 * @Title
	 * @Description 贴心报推荐 查询 app调用 按浏览量降序排列
	 * @author:王肖东
	 * @CreateDate:2016年8月18日 上午9:28:56
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNews> queryListByParamOrder(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查出对应的附件
	 * @author:王肖东
	 * @CreateDate:2016年8月18日 上午9:28:56
	 * @param intimateNewsId
	 * @return
	 */
	public List<Attach> queryListByParamOrder(Long intimateNewsId);

	/**
	 * 
	 * @Title
	 * @Description 更新贴心报
	 * @author:王肖东
	 * @CreateDate:2016年8月26日 上午10:58:29
	 * @param intimateNews
	 * @param villageId
	 * @return
	 */
	public boolean updateIntimateNews(IntimateNews intimateNews, String villageId);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有对贴心报进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月29日 上午11:31:52
	 * @param intimateNewsId
	 * @param appUserId
	 * @return
	 */
	public boolean getIntimateNewsByDay(Long intimateNewsId, Long appUserId);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有对贴心报一级评论进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月29日 上午11:31:38
	 * @param commentId
	 * @param appUserId
	 * @return
	 */
	//public boolean getCommentByDay(Long commentId, Long appUserId);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报评论id找到贴心报评论
	 * @author:王肖东
	 * @CreateDate:2016年7月27日 上午10:42:05
	 * @param id
	 * @return id
	 */
	public IntimateNewsComment selectIntimateNewsComment(Long id);

}
