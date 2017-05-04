package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundStoreCommentReply;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;

/**
 * @Title
 * @Description 周边店Servicie
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月22日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. YK 2016年7月22日 上午9:45:20 创建
 *           ==========================================================
 * 
 */
public interface AroundStoreService<T extends Serializable> extends CrudService<T> {
	/**
	 * 
	 * @Title
	 * @Description 系统设置:小区查询中的周边店查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 上午11:36:48
	 * @param aroundStoreVo
	 * @return List
	 */
	public List<AroundStoreVo> findAllAroundStore(AroundStoreVo aroundStoreVo);

	/**
	 * 
	 * @Title
	 * @Description 系统设置:小区查询中的周边店总数查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午2:49:13
	 * @param aroundStoreVo
	 * @return Long
	 */
	public Long findAllAroundStoreCount(AroundStoreVo aroundStoreVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查找符合条件的周边店数量
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 上午9:43:30
	 * @param param
	 * @return Long
	 */
	public Long findAroundStoreListCount(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查找符合条件的周边店信息列表
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 上午9:48:59
	 * @param param
	 * @return List
	 */
	public List<AroundStoreVo> findAroundStoreList(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据周边店id获取周边店详情
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午2:29:18
	 * @param id
	 * @return AroundStoreVo
	 */
	public AroundStoreVo findAroundStore(Long id);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：保存周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午5:12:11
	 * @param aroundStoreVo
	 * @return boolean
	 */
	public boolean saveAroundStore(AroundStoreVo aroundStoreVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：更新周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午12:13:36
	 * @param aroundStoreVo
	 * @return boolean
	 */
	public boolean updateAroundStoreVo(AroundStoreVo aroundStoreVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：删除周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午12:22:52
	 * @param aroundStore
	 * @return boolean
	 */
	public boolean deleteAroundStore(AroundStore aroundStore);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据周边店id查询所属小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午8:53:36
	 * @param id
	 * @return List
	 */
	public List<AroundStoreVillage> findAroundStoreVillage(Long id);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理： 查看符合条件的周边店回复数量
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午5:11:40
	 * @param aroundStoreCommentVo
	 * @return Long
	 */
	public Long findAroundStoreCommentListCount(AroundStoreCommentVo aroundStoreCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理： 查看符合条件的周边店回复列表
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午5:11:44
	 * @param aroundStoreCommentVo
	 * @return List
	 */
	public List<AroundStoreCommentVo> findAroundStoreCommentList(AroundStoreCommentVo aroundStoreCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查找周边店回复
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午3:01:47
	 * @param id
	 * @return AroundStoreCommentVo
	 */
	public AroundStoreCommentVo findAroundStoreComment(Long id);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：删除周边店回复
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午3:02:45
	 * @param aroundStoreComment
	 * @return boolean
	 */
	public boolean deleteAroundStoreComment(AroundStoreComment aroundStoreComment);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据id查找回复的详细信息
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午9:39:05
	 * @param id
	 * @return AroundStoreCommentVo
	 */
	public AroundStoreCommentVo findAroundStoreCommentDetail(Long id);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查看符合条件的周边店的回复的回复的数量
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午9:45:43
	 * @param aroundStoreCommentReplyVo
	 * @return Long
	 */
	public Long findAroundStoreCommentReplyListCount(AroundStoreCommentReplyVo aroundStoreCommentReplyVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查看符合条件的周边店的回复的回复列表
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午9:46:01
	 * @param aroundStoreCommentReplyVo
	 * @return List
	 */
	public List<AroundStoreCommentReplyVo> findAroundStoreCommentReplyList(
			AroundStoreCommentReplyVo aroundStoreCommentReplyVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据id查找周边回复的回复的详情
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午10:13:28
	 * @param id
	 * @return AroundStoreCommentReplyVo
	 */
	public AroundStoreCommentReplyVo findAroundStoreCommentReply(Long id);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：删除周边店回复的回复
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午10:14:01
	 * @param aroundStoreCommentReply
	 * @return boolean
	 */
	public boolean deleteAroundStoreCommentReply(AroundStoreCommentReply aroundStoreCommentReply);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：新增本网格推荐周边店
	 * @author:kangtianyu
	 * @CreateDate:2016年8月1日 下午2:27:01
	 * @param aroundStoreVo
	 * @param propagandafiles
	 * @param suggestStart
	 * @param suggestEnd
	 * @return Map<String, Object>
	 */
	public Map<String, Object> saveAroundSuggestStoreByAS(AroundStoreVo aroundStoreVo, MultipartFile[] propagandafiles,
			Date suggestStart, Date suggestEnd);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：新增本区推荐周边店
	 * @author:kangtianyu
	 * @CreateDate:2016年8月1日 下午4:03:28
	 * @param aroundStoreVo
	 * @param userId
	 * @return Map<String, Object>
	 */
	public Map<String, Object> saveCountySuggestStoreByAS(AroundStoreVo aroundStoreVo, String userId);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查找已经被推荐的网格长
	 * @author:kangtianyu
	 * @CreateDate:2016年8月2日 下午11:27:44
	 * @param aroundStoreId
	 * @return List
	 */
	public List<User> findHasSugUser(Long aroundStoreId);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据相关参数查找用户对应域
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 上午12:22:36
	 * @param param
	 * @return List
	 */
	public List<UserArea> findUserAreaByParam(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：判断当前小区是否已经达到推荐最大值
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 上午9:34:36
	 * @param aroundStoreVo
	 * @return Map
	 */
	public Map<String, Object> judgeAroundSuggestIsMax(AroundStoreVo aroundStoreVo);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查找当前用户可以查看的周边店
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午9:39:33
	 * @param userId
	 * @return List
	 */
	public List<AroundStoreVillage> findAroundStoreIdByUser(Long userId);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：查看周边店所在区域是否允许推荐
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午11:01:57
	 * @param aroundStore
	 * @return boolean
	 */
	public boolean checkRecommend(AroundStore aroundStore);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据小区Id查看周边店和小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午9:53:18
	 * @param villageId
	 * @return List
	 */
	public List<AroundStoreVillage> findAroundStoreVillageByVillageId(Long villageId);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据参数查询周边店分页列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午10:08:51
	 * @param param
	 * @return List
	 */
	public List<AroundStore> findAroundStoreByIdList(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 周边店管理：根据主键查询周边店宣传图片
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午10:59:04
	 * @param id
	 * @return List
	 */
	public List<String> findAttachById(Long id);

	/**
	 * 
	 * @Title
	 * @Description 根据参数获取评论列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午4:38:43
	 * @param aroundStoreCommentVo
	 * @return List
	 */
	public List<AroundStoreComment> findCommentList(AroundStoreCommentVo aroundStoreCommentVo);

	/**
	 * 
	 * @Title
	 * @Description 添加一级评论
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午11:21:22
	 * @param storeId
	 * @param appUserId
	 * @param content
	 * @param illegal
	 * @param opreaterIp
	 * @param paramType
	 * @return int
	 */
	public int addComment(Long storeId, Long appUserId, String content, boolean illegal, String opreaterIp,
			String paramType);

	/**
	 * 
	 * @Title
	 * @Description 添加二级评论
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午11:41:18
	 * @param commentId
	 * @param appUserId
	 * @param content
	 * @param illegal
	 * @param opreaterIp
	 * @return int
	 */
	public int addCommentReply(Long commentId, Long appUserId, String content, boolean illegal, String opreaterIp);

	/**
	 * 
	 * @Title
	 * @Description 增加拨打次数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 上午11:03:09
	 * @param aroundStore
	 * @return int
	 */
	public int addStoreParam(AroundStore aroundStore);

	/**
	 * @Title
	 * @Description 更新评论浏览量
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 下午4:09:34
	 * @param aroundStoreComment
	 */
	public void updateAroundStoreComment(AroundStoreComment aroundStoreComment);

	/**
	 * @Title
	 * @Description 增加评论属性
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 下午4:22:28
	 * @param aroundStoreComment
	 * @return int
	 */
	public int addCommentParam(AroundStoreComment aroundStoreComment);

	/**
	 * @Title
	 * @Description 判断用户当天有没有评论过
	 * @author:kangtianyu
	 * @CreateDate:2016年8月13日 上午11:37:43
	 * @param storeId
	 * @param appUserId
	 * @return AroundStoreComment
	 */
	public AroundStoreComment getAroundStoreCommentByDay(Long storeId, Long appUserId);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有对周边店进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月29日 上午11:31:52
	 * @param aroundStoreId
	 * @param appUserId
	 * @return
	 */
	public boolean getAroundStoreByDay(Long aroundStoreId, Long appUserId);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有对周边店一级评论进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月29日 上午11:31:38
	 * @param commentId
	 * @param appUserId
	 * @return
	 */
	public boolean getCommentByDay(Long commentId, Long appUserId);

	/**
	 * @Title
	 * @Description 判断点状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月30日 上午10:38:02
	 * @param storeId
	 * @return
	 */
	public boolean judgeStatus(Long storeId);

}
