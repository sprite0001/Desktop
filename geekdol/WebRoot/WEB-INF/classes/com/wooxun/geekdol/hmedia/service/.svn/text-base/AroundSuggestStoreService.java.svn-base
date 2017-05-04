package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreCommentReply;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStorePromotion;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStorePromotionVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.User;

/**
 * 
 * @Title
 * @Description 本网格推荐周边店Service
 * @version 1.0.0
 * @Author kangtianyu
 * @CreateDate 2016年8月4日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. kangtianyu 2016年8月4日 上午11:43:16 创建
 *           ==========================================================
 *
 */
public interface AroundSuggestStoreService<T extends Serializable> extends CrudService<T> {

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查找符合条件的本网格推荐店个数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 上午11:46:20
	 * @param aroundStoreVo
	 * @return
	 */
	public Long findAroundSuggestStoreListCount(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查找符合条件的本网格推荐店信息列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 上午11:47:30
	 * @param param
	 * @return
	 */
	public List<AroundSuggestStoreVo> findAroundSuggestStoreList(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：判断要推荐的小区是否达到推荐数量
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:18:33
	 * @param aroundSuggestStoreVo
	 * @return
	 */
	public Map<String, Object> judgeAroundSuggestIsMax(AroundSuggestStoreVo aroundSuggestStoreVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：保存本网格推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:20:00
	 * @param aroundSuggestStoreVo
	 * @param propagandafiles
	 * @return
	 */
	public boolean saveAroundSuggestStore(AroundSuggestStoreVo aroundSuggestStoreVo, MultipartFile[] propagandafiles);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：根据主键id查询本网格推荐周边店详细信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午11:09:10
	 * @param id
	 * @return
	 */
	public AroundSuggestStoreVo findAroundSuggestStore(Long id);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：更新本网格推荐周边店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午12:28:33
	 * @param aroundSuggestStoreVo
	 * @param propagandafiles
	 * @param attchfiles
	 * @return
	 */
	public boolean updateAroundSuggestStore(AroundSuggestStoreVo aroundSuggestStoreVo, MultipartFile[] propagandafiles,
			String[] attchfiles);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：根据本网格推荐周边店id查找与小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:09:39
	 * @param id
	 * @return
	 */
	public List<AroundSuggestStoreVillage> findAroundSuggestStoreVillage(Long id);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：删除本网格推荐周边店信息及相关其他信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:41:56
	 * @param aroundSuggestStore
	 * @return
	 */
	public boolean deleteAroundSuggestStore(AroundSuggestStore aroundSuggestStore);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查找符合条件的本网格推荐周边店评论的数量
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:47:53
	 * @param aroundSuggestStoreCommentVo
	 * @return
	 */
	public Long findAroundSuggestStoreCommentListCount(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查找符合条件的本网格推荐周边店评论的分页列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:47:59
	 * @param aroundSuggestStoreCommentVo
	 * @return
	 */
	public List<AroundSuggestStoreCommentVo> findAroundSuggestStoreCommentList(
			AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：根据主键id查找本网格推荐周边店评论的信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:49:27
	 * @param id
	 * @return
	 */
	public AroundSuggestStoreComment findAroundSuggestStoreComment(Long id);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：删除本网格推荐周边店的评论以及相关关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:50:07
	 * @param aroundSuggestStoreComment
	 * @return
	 */
	public boolean deleteAroundSuggestStoreComment(AroundSuggestStoreComment aroundSuggestStoreComment);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：根据主键id查询本网格推荐周边店评论的信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:27:01
	 * @param id
	 * @return
	 */
	public AroundSuggestStoreCommentVo findAroundSuggestStoreCommentDetail(Long id);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查询符合条件的周边店评论的评论的总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:28:44
	 * @param aroundSuggestStoreCommentReplyVo
	 * @return
	 */
	public Long findAroundSuggestStoreCommentReplyListCount(
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查询符合条件的周边店评论的评论的分页数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:29:16
	 * @param aroundSuggestStoreCommentReplyVo
	 * @return
	 */
	public List<AroundSuggestStoreCommentReplyVo> findAroundSuggestStoreCommentReplyList(
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：根据主键id查找本网格推荐周边店评论的评论的信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:30:10
	 * @param id
	 * @return
	 */
	public AroundSuggestStoreCommentReply findAroundSuggestStoreCommentReply(Long id);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：删除本网格推荐周边店评论的评论
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:31:05
	 * @param aroundSuggestStoreCommentReply
	 * @return
	 */
	public boolean deleteAroundSuggestStoreCommentReply(AroundSuggestStoreCommentReply aroundSuggestStoreCommentReply);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：再次推荐
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午4:55:23
	 * @param aroundSuggestStoreVo
	 * @return
	 */
	public boolean suggestAgain(AroundSuggestStoreVo aroundSuggestStoreVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：本区推荐
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午5:23:49
	 * @param aroundSuggestStoreVo
	 * @param userId
	 * @return
	 */
	public Map<String, Object> saveCountySuggest(AroundSuggestStoreVo aroundSuggestStoreVo, String userId);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：根据本网格推荐周边店id查询已经推荐的网格长
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午5:34:59
	 * @param aroundSuggestStoreId
	 * @return
	 */
	public List<User> findHasSugUser(Long aroundSuggestStoreId);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：保存本网格推荐店的id查询其促销信息总记录数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午9:44:02
	 * @param aroundSuggestStorePromotionVo
	 * @return
	 */
	public Long findAroundSuggestStorePromotionListCount(AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查询选中的本网格推荐店的id查询其促销信息分页列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午9:44:10
	 * @param aroundSuggestStorePromotionVo
	 * @return
	 */
	public List<AroundSuggestStorePromotionVo> findAroundSuggestStorePromotionList(
			AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：保存选中的本网格推荐店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午10:34:35
	 * @param aroundSuggestStorePromotion
	 * @param propagandafiles
	 * @return
	 */
	public boolean saveAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion,
			MultipartFile[] propagandafiles);

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：查询选中的本网格推荐店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午10:44:46
	 * @param id
	 * @return
	 */
	public AroundSuggestStorePromotion findAroundSuggestStorePromotion(Long id);

	/**
	 * @Title
	 * @Description 根据主键id更新选中的本网格推荐店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午11:28:56
	 * @param aroundSuggestStorePromotion
	 * @param propagandafiles
	 * @return
	 */
	public boolean updateAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion,
			MultipartFile[] propagandafiles);

	/**
	 * @Title
	 * @Description 根据主键id删除选中的本网格推荐店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:09:38
	 * @param aroundSuggestStorePromotion
	 * @return
	 */
	public boolean deleteAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion);

	/**
	 * @Title
	 * @Description 更改本网格推荐店的促销的状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:18:53
	 * @param aroundSuggestStorePromotion
	 * @return
	 */
	public boolean updateAroundSuggestStorePromotionStatus(AroundSuggestStorePromotion aroundSuggestStorePromotion);

	/**
	 * @Title
	 * @Description 根据附件信息查询相关附件
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午11:24:47
	 * @param attach
	 * @return
	 */
	public List<Attach> findAttachByParam(Attach attach);

	/**
	 * @Title
	 * @Description 根据用户信息查找本网格推荐周边店与小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午10:38:05
	 * @param userId
	 * @return
	 */
	public List<AroundSuggestStoreVillage> findAroundSuggestStoreIdByUser(Long userId);

	/**
	 * @Title
	 * @Description 根据周边店信息判断其所在区域是否开启推荐
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午11:30:03
	 * @param aroundSuggestStore
	 * @return
	 */
	public boolean checkRecommend(AroundSuggestStore aroundSuggestStore);

	/**
	 * @Title
	 * @Description 根据小区Id查看推荐店和小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午2:41:25
	 * @param villageId
	 * @return
	 */
	public List<AroundSuggestStoreVillage> findAroundSuggestStoreVillageByVillageId(Long villageId);

	/**
	 * @Title
	 * @Description 根据参数查询推荐店分页列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午2:48:34
	 * @param param
	 * @return
	 */
	public List<AroundSuggestStore> findAroundSuggestStoreByIdList(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据主键查询周边店宣传图片
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 上午10:59:04
	 * @param id
	 * @return
	 */
	public List<String> findAttachById(Long id);

	/**
	 * @Title
	 * @Description 根据推荐店id查询最新的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午2:59:19
	 * @param id
	 * @return
	 */
	public AroundSuggestStorePromotion findOnePromotionByASSId(Long id);

	/**
	 * @Title
	 * @Description 根据参数获取评论列表
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午9:43:02
	 * @param aroundSuggestStoreCommentVo
	 * @return
	 */
	public List<AroundSuggestStoreComment> findCommentList(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo);

	/**
	 * @Title
	 * @Description 根据推荐店id查询所拥有的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午9:59:29
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findPromotionDetailsById(Long id);

	/**
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
	 * @return
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
	 * @return
	 */
	public int addCommentReply(Long commentId, Long appUserId, String content, boolean illegal, String opreaterIp);

	/**
	 * @Title
	 * @Description 增加拨打次数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 上午11:03:09
	 * @param aroundSuggestStore
	 * @return
	 */
	public int addStoreParam(AroundSuggestStore aroundSuggestStore);

	/**
	 * @Title
	 * @Description 增加评论浏览量
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 下午4:12:02
	 * @param aroundSuggestStoreCommentTemp
	 */
	public void updateAroundStoreComment(AroundSuggestStoreComment aroundSuggestStoreCommentTemp);

	/**
	 * @Title
	 * @Description 增加评论属性
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 下午4:22:54
	 * @param temp
	 * @return
	 */
	public int addCommentParam(AroundSuggestStoreComment temp);

	/**
	 * @Title
	 * @Description 判断用户当天有没有评论过
	 * @author:kangtianyu
	 * @CreateDate:2016年8月13日 上午11:37:43
	 * @param storeId
	 * @param appUserId
	 * @return
	 */
	public AroundSuggestStoreComment getAroundSuggestStoreCommentByDay(Long storeId, Long appUserId);

	/**
	 * @Title
	 * @Description 判断组织代码证是否重复
	 * @author:kangtianyu
	 * @CreateDate:2016年8月17日 下午10:48:04
	 * @param organizationCode
	 * @return
	 */
	public boolean findOrganizationCode(String organizationCode);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有对推荐店进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月29日 上午11:31:52
	 * @param storeId
	 * @param appUserId
	 * @return
	 */
	public boolean getAroundSuggestStoreByDay(Long storeId, Long appUserId);

	/**
	 * 
	 * @Title
	 * @Description 判断用户当天有没有对推荐店一级评论进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月29日 上午11:31:38
	 * @param commentId
	 * @param appUserId
	 * @return
	 */
	public boolean getCommentByDay(Long commentId, Long appUserId);

	/**
	 * @Title
	 * @Description 判断店状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月30日 上午10:45:49
	 * @param storeId
	 * @return
	 */
	public boolean judgeStatus(Long storeId);
}
