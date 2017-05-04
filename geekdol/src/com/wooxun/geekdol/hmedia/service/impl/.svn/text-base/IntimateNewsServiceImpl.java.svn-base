package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsCommentMapper;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsCommentRecommendMapper;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsCommentReplyMapper;
import com.wooxun.geekdol.hmedia.mapper.IntimateNewsMapper;
import com.wooxun.geekdol.hmedia.mapper.IntimateVillageMapper;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.model.IntimateNewsComment;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentRecommend;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentReply;
import com.wooxun.geekdol.hmedia.model.IntimateVillage;
import com.wooxun.geekdol.hmedia.service.IntimateNewsService;
import com.wooxun.geekdol.hmedia.vo.AppIntimateNewsVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.mapper.SysDataMapper;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.User;

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
@Service
public class IntimateNewsServiceImpl extends CrudServiceImpl<IntimateNews> implements IntimateNewsService<IntimateNews> {

	private IntimateNewsMapper<IntimateNews> intimateNewsMapper;

	@Autowired
	private AttachMapper<Attach> attachMapper;

	@Autowired
	private SysDataMapper<SysData> sysDataMapper;

	@Autowired
	private IntimateVillageMapper<IntimateVillage> intimateVillageMapper;

	@Autowired
	private IntimateNewsCommentMapper<IntimateNewsComment> intimateNewsCommentMapper;
	
	@Autowired
	private IntimateNewsCommentRecommendMapper<IntimateNewsCommentRecommend> intimateNewsCommentRecommendMapper;

	@Autowired
	private IntimateNewsCommentReplyMapper<IntimateNewsCommentReply> intimateNewsCommentReplyMapper;

	@Autowired
	public IntimateNewsServiceImpl(IntimateNewsMapper<IntimateNews> intimateNewsMapper) {
		super(intimateNewsMapper);
		this.intimateNewsMapper = intimateNewsMapper;
	}

	@Override
	public boolean insertIntimateNews(IntimateNews intimateNews, String villageId) {
		int i = intimateNewsMapper.insertSelective(intimateNews);
		if (i <= 0) {
			return false;
		} else {

			// 保存小区贴心报关系表
			List<IntimateVillage> listIntimateVillage = new ArrayList<IntimateVillage>();
			String[] village = villageId.split(",");
			for (int j = 0; j < village.length; j++) {
				IntimateVillage intimateVillage = new IntimateVillage();
				intimateVillage.setIntimateNewId(intimateNews.getId());
				intimateVillage.setVillageId(Long.valueOf(village[j]));
				listIntimateVillage.add(intimateVillage);
			}
			intimateVillageMapper.insertBatch(listIntimateVillage);

			// 保存附件
			List<Attach> listAttach = new ArrayList<Attach>();
			int k = 0;
			for (Attach attach : intimateNews.getAttachs()) {
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(intimateNews.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_04);
				attach.setOrdering(k);
				attach.setOpreator(intimateNews.getInsId());
				attach.setInsId(intimateNews.getInsId());
				attach.setInsYmdhms(intimateNews.getInsYmdhms());
				attach.setUpdId(intimateNews.getUpdId());
				attach.setUpdEac(intimateNews.getUpdEac());
				attach.setUpdYmdhms(intimateNews.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				listAttach.add(attach);
				k++;
			}
			attachMapper.insertBatch(listAttach);

		}
		return true;
	}

	@Override
	public Long queryCountByParams(IntimateNewsVo searchIntimateNews) {
		return intimateNewsMapper.queryCountByParams(searchIntimateNews);
	}

	@Override
	public List<IntimateNewsVo> queryIntimateNewsByParams(IntimateNewsVo searchIntimateNews) {

		return intimateNewsMapper.queryIntimateNewsByParams(searchIntimateNews);
	}

	@Override
	public IntimateNewsVo selectIntimateNewsVo(Long id) {

		IntimateNews intimateNews = intimateNewsMapper.selectByPrimaryKey(id);
		// 返回封装好的数据
		IntimateNewsVo intimateNewsVo = formateData(intimateNews);

		return intimateNewsVo;
	}

	/**
	 * 
	 * @Title
	 * @Description 返回封装好的 intimateNewsVo 数据
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:22:14
	 * @param intimateNews
	 * @return
	 */
	private IntimateNewsVo formateData(IntimateNews intimateNews) {
		IntimateNewsVo intimateNewsVo = new IntimateNewsVo();
		BeanUtils.copyProperties(intimateNews, intimateNewsVo);
		String villageId = "";
		String villageName = "";
		for (IntimateVillage intimateVillage : intimateNews.getIntimateVillageList()) {
			// 如果为null 证明小区有可能被禁用 或者删除
			if (intimateVillage.getVillage() != null) {
				villageId += intimateVillage.getVillageId() + ",";
				villageName += intimateVillage.getVillage().getVillageName() + ",";
			}
		}
		intimateNewsVo.setVillageId(villageId.substring(0, villageId.lastIndexOf(",")));
		intimateNewsVo.setVillageName(villageName.substring(0, villageName.lastIndexOf(",")));

		intimateNewsVo.setHotnumber(intimateNews.getViewNumber() + "/" + intimateNews.getReplyNumber() + "/"
				+ intimateNews.getIllegalNumber());

		intimateNewsVo.setLikesOrhate(intimateNews.getLikesNumber() + "/" + intimateNews.getHateNumber());

		intimateNewsVo.setReportTimeStr(DateUtil.dateToString(intimateNews.getReportTime()));
		// 如果是置顶的话
		if (intimateNews.getTopFlag().equals("1")) {
			intimateNewsVo.setTopStartStr(DateUtil.dateToString(intimateNews.getTopStart()));
			intimateNewsVo.setTopEndStr(DateUtil.dateToString(intimateNews.getTopEnd()));
		}
		return intimateNewsVo;
	}

	@Override
	public boolean deleteIntimateNews(IntimateNews intimateNews) {

		int i = intimateNewsMapper.updateByPrimaryKeySelective(intimateNews);
		if (i >= 0) {
			i = intimateVillageMapper.deleteByIntimateNewsId(intimateNews.getId());
		}
		// 删除附件表
		int j = attachMapper.deleteByIdAndName(intimateNews.getId(), "04");
		return j > 0 ? true : false;
	}

	// 查询评价列表总条数
	@Override
	public Long queryCountCommentByParams(IntimateNewsCommentVo intimateNewsCommentVo) {

		return intimateNewsCommentMapper.selectIntimateNewsCommentListCount(intimateNewsCommentVo);
	}

	// 查询评价列表
	@Override
	public List<IntimateNewsCommentVo> queryListCommentByParams(IntimateNewsCommentVo intimateNewsCommentVo) {

		return intimateNewsCommentMapper.selectIntimateNewsCommentList(intimateNewsCommentVo);
	}

	@Override
	public List<IntimateNewsVo> queryListWeishenhe(IntimateNewsVo searchIntimateNews) {

		return intimateNewsMapper.queryIntimateNewsByParams(searchIntimateNews);
	}

	@Override
	public Long queryCountWeishenhe(IntimateNewsVo searchIntimateNews) {

		return intimateNewsMapper.queryCountByParams(searchIntimateNews);
	}

	@Override
	public IntimateNewsCommentVo findIntimateNewsCommentDetail(Long id) {

		return intimateNewsCommentMapper.selectIntimateNewsCommentDetail(id);
	}

	@Override
	public List<IntimateNewsCommentReplyVo> findIntimateNewsCommentReplyList(
			IntimateNewsCommentReplyVo intimateNewsCommentReplyVo) {

		return intimateNewsCommentReplyMapper.selectIntimateNewsCommentReplyList(intimateNewsCommentReplyVo);
	}

	@Override
	public Long findIntimateNewsCommentReplyListCount(IntimateNewsCommentReplyVo intimateNewsCommentReplyVo) {

		return intimateNewsCommentReplyMapper.selectIntimateNewsCommentReplyListCount(intimateNewsCommentReplyVo);
	}

	@Override
	public Long findIntimateNewsYitijiaoCount(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.queryYitijiaoCount(intimateNewsVo);
	}

	@Override
	public List<IntimateNewsVo> findIntimateNewsYitijiaoList(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.queryYitijiaoList(intimateNewsVo);
	}

	@Override
	public List<IntimateNewsVo> findselfIntimateNewsYitijiaoList(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.querySelfYitijiaoList(intimateNewsVo);
	}

	@Override
	public Long findselfIntimateNewsYitijiaoCount(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.querySelfYitijiaoCount(intimateNewsVo);
	}

	@Override
	public List<IntimateNewsVo> findIntimateNewsYishenheList(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.querYishenheList(intimateNewsVo);
	}

	@Override
	public Long findIntimateNewsYiShenheCount(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.queryYishenheCount(intimateNewsVo);
	}

	@Override
	public List<IntimateNewsVo> querotherpersonList(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.querotherpersonList(intimateNewsVo);
	}

	@Override
	public Long querotherpersonListCount(IntimateNewsVo intimateNewsVo) {
		return intimateNewsMapper.querotherpersonListCount(intimateNewsVo);
	}

	@Override
	public boolean deleteIntimateNewsComment(IntimateNewsComment intimateNewsComment,
			IntimateNewsCommentVo intimateNewsCommentVo) {
		int result = intimateNewsCommentMapper.updateByPrimaryKeySelective(intimateNewsComment);
		// 查询贴心报主数据，并更新回复量
		IntimateNews intimateNews = intimateNewsMapper.selectByPrimaryKey(intimateNewsCommentVo.getIntimateNewsId());
		if (intimateNews != null) {
			// 如果删除的是违规的评论 则违规数量减1
			if ("1".equals(intimateNewsCommentVo.getIllegalStatus())) {
			    if(intimateNews.getIllegalNumber() > 0){
	                intimateNews.setIllegalNumber(intimateNews.getIllegalNumber() - 1);
			    }
			}
			// 评论数量减1
			if(intimateNews.getReplyNumber() > 0){
	            intimateNews.setReplyNumber(intimateNews.getReplyNumber() - 1);
			}
			intimateNews.setUpdEac(intimateNews.getUpdEac() + 1);
			intimateNews.setUpdId(getUserId());
			intimateNews.setUpdYmdhms(new Date());
			result = intimateNewsMapper.updateByPrimaryKeySelective(intimateNews);
		}
		if (result >= 0) {
			IntimateNewsCommentReplyVo icVo = new IntimateNewsCommentReplyVo();
			icVo.setInCommentId(intimateNewsComment.getId());
			Long countNumber = intimateNewsCommentReplyMapper.selectIntimateNewsCommentReplyListCount(icVo);
			if (countNumber > 0) {
				IntimateNewsCommentReply itimateNewsCommentReply = new IntimateNewsCommentReply();
				itimateNewsCommentReply.setDelFlag("1");
				itimateNewsCommentReply.setInCommentId(intimateNewsComment.getId());
				itimateNewsCommentReply.setUpdYmdhms(new Date());
				itimateNewsCommentReply.setUpdId(getUserId());
				result = intimateNewsCommentReplyMapper.updateByIntimateNewsCommentId(itimateNewsCommentReply);
			}
		}

		// 返回结果
		return result > 0 ? true : false;
	}

	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

	@Override
	public IntimateNewsCommentReplyVo findIntimateNewsCommentReply(Long id) {
		IntimateNewsCommentReply intimateNewsCommentReply = intimateNewsCommentReplyMapper.selectByPrimaryKey(id);
		IntimateNewsCommentReplyVo intimateNewsCommentReplyVo = new IntimateNewsCommentReplyVo();
		BeanUtils.copyProperties(intimateNewsCommentReply, intimateNewsCommentReplyVo);
		return intimateNewsCommentReplyVo;
	}

	@Override
	public boolean deleteIntimateNewsCommentReply(IntimateNewsCommentReply intimateNewsCommentReply,
			IntimateNewsCommentReplyVo intimateNewsCommentReplyVo) {

		int res = intimateNewsCommentReplyMapper.updateByPrimaryKeySelective(intimateNewsCommentReply);
		// 更新一级评论的评论数量(-1)
		IntimateNewsComment intimateNewsComment = intimateNewsCommentMapper
				.selectByPrimaryKey(intimateNewsCommentReplyVo.getInCommentId());
		if (intimateNewsComment != null) {
			// 如果删除的是违规的评论 则违规数量减1
			if ("1".equals(intimateNewsCommentReplyVo.getIllegalStatus())) {
			    if(intimateNewsComment.getIllegalNumber() > 0){
	                intimateNewsComment.setIllegalNumber(intimateNewsComment.getIllegalNumber() - 1);
			    }
			}
			if(intimateNewsComment.getReplyNumber() > 0){
	            intimateNewsComment.setReplyNumber(intimateNewsComment.getReplyNumber() - 1);
			}
			intimateNewsComment.setUpdEac(intimateNewsComment.getUpdEac() + 1);
			intimateNewsComment.setUpdYmdhms(new Date());
			intimateNewsComment.setUpdId(getUserId());
			res = intimateNewsCommentMapper.updateByPrimaryKeySelective(intimateNewsComment);
		}
		return res > 0 ? true : false;
	}

	@Override
	public List<AppIntimateNewsVo> appQueryIntimateNewsByParams(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.appQueryIntimateNewsByParams(intimateNewsVo);
	}

	@Override
	public List<IntimateNewsComment> appQueryComment(IntimateNewsCommentVo intimateNewsCommentVo) {

		return intimateNewsCommentMapper.selectCommentList(intimateNewsCommentVo);
	}

	@Override
	public void updateIntimateNewsComment(IntimateNewsComment intimateNewsComment) {
		intimateNewsCommentMapper.updateByPrimaryKeySelective(intimateNewsComment);
	}

	@Override
	public IntimateNewsComment getIntimateNewsCommentByDay(Long intimateNewsId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("intimateNewsId", intimateNewsId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		List<IntimateNewsComment> ascList = intimateNewsCommentMapper.selectIntimateNewsCommentByDay(param);
		if (ascList != null && ascList.size() > 0) {
			// 返回结果
			return ascList.get(0);
		} else {
			// 返回结果
			return null;
		}
	}

	@Override
	public int addComment(Long intimateNewsId, Long appUserId, String content, boolean illegal, String opreaterIp) {

		// 创建结果变量
		int result = 0;
		// 获取贴心报数据
		IntimateNews original = intimateNewsMapper.selectByPrimaryKey(intimateNewsId);
		if (original == null) { // 如果评论已经被删除
			return result;
		}
		// 创建贴心报Model对象用于更新
		IntimateNews intimateNews = new IntimateNews();
		/* 设置参数用于更新 */
		intimateNews.setId(intimateNewsId);
		intimateNews.setReplyNumber(original.getReplyNumber() + 1);
		intimateNews.setUpdEac(original.getUpdEac() + 1);
		if (illegal) {
			intimateNews.setIllegalNumber(original.getIllegalNumber() + 1);
		}
		// 更新主表数据
		result = intimateNewsMapper.updateByPrimaryKeySelective(intimateNews);
		if (result <= 0) { // 如果更新失败则返回
			return result;
		}
		// 创建贴心报评论Model变量用于数据插入
		IntimateNewsComment intimateNewsComment = new IntimateNewsComment();
		// 设置贴心报id
		intimateNewsComment.setInId(intimateNewsId);
		// 设置评论内容
		intimateNewsComment.setContent(content);
		// 设置违规状态
		intimateNewsComment.setIllegalStatus(illegal ? ConstantStr.ILLEAGL_FLAG_WG : ConstantStr.ILLEAGL_FLAG_ZC);
		// 设置评论人id
		intimateNewsComment.setInsId(appUserId);
		// 设置评论人ip
		intimateNewsComment.setOpreaterIp(opreaterIp);
		// 设置相关初始变量
		addInitAttr(intimateNewsComment);
		// 调用mapper插入数据
		result = intimateNewsCommentMapper.insertSelective(intimateNewsComment);
		// 返回结果
		return result;
	}

	private void addInitAttr(IntimateNewsComment _temp) {
		_temp.setLikesNumber(0);
		_temp.setHateNumber(0);
		_temp.setViewNumber(0);
		_temp.setReplyNumber(0);
		_temp.setIllegalNumber(0);
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	@Override
	public int addCommentReply(Long commentId, Long appUserId, String content, boolean illegal, String opreaterIp) {
		// 创建结果变量
		int result = 0;
		// 获取一级评论数据
		IntimateNewsComment original = intimateNewsCommentMapper.selectByPrimaryKey(commentId);
		if (original == null) { // 如果评论已经被删除
			return result;
		}
		// 创建评论Model对象用于更新
		IntimateNewsComment intimateNewsComment = new IntimateNewsComment();
		/* 设置参数用于更新 */
		intimateNewsComment.setId(commentId);
		intimateNewsComment.setReplyNumber(original.getReplyNumber() + 1);
		intimateNewsComment.setUpdEac(original.getUpdEac() + 1);
		if (illegal) {
			intimateNewsComment.setIllegalNumber(original.getIllegalNumber() + 1);
		}
		// 更新主表数据
		result = intimateNewsCommentMapper.updateByPrimaryKeySelective(intimateNewsComment);
		if (result <= 0) { // 如果更新失败则返回
			return 0;
		}
		// 创建贴心报评论的回复Model变量用于数据插入
		IntimateNewsCommentReply intimateNewsCommentReply = new IntimateNewsCommentReply();
		// 设置贴心报评论id
		intimateNewsCommentReply.setInCommentId(commentId);
		// 设置评论内容
		intimateNewsCommentReply.setContent(content);
		// 设置违规状态
		intimateNewsCommentReply.setIllegalStatus(illegal ? ConstantStr.ILLEAGL_FLAG_WG : ConstantStr.ILLEAGL_FLAG_ZC);
		// 设置评论人id
		intimateNewsCommentReply.setInsId(appUserId);
		// 设置评论人ip
		intimateNewsCommentReply.setOpreaterIp(opreaterIp);
		// 设置相关初始变量
		addInitAttr(intimateNewsCommentReply);
		// 调用mapper插入数据
		result = intimateNewsCommentReplyMapper.insertSelective(intimateNewsCommentReply);
		// 返回结果
		return result;
	}

	private void addInitAttr(IntimateNewsCommentReply _temp) {
		_temp.setViewNumber(0);
		_temp.setReplyNumber(0);
		_temp.setIllegalNumber(0);
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	@Override
	public int addIntimateNewsParam(IntimateNews intimateNews) {
		return intimateNewsMapper.updateByPrimaryKeySelective(intimateNews);
	}

	@Override
	public int addCommentParam(IntimateNewsComment intimateNewsComment,IntimateNewsCommentRecommend commentRecommend) {
		int i = intimateNewsCommentMapper.updateByPrimaryKeySelective(intimateNewsComment);
		if(commentRecommend.getId() != null){ // 取消点赞删除点赞历史记录
			i = intimateNewsCommentRecommendMapper.deleteByPrimaryKey(commentRecommend.getId());
		}else{ // 新增点赞记录
			i = intimateNewsCommentRecommendMapper.insertSelective(commentRecommend);
		}
		return i;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据评论id查到评论详情
	 * @author:王肖东
	 * @CreateDate:2016年8月17日 下午4:42:47
	 * @param id
	 * @return
	 */
	@Override
	public IntimateNewsCommentVo findAppIntimateNewsCommentDetail(Long id) {

		return intimateNewsCommentMapper.findAppIntimateNewsCommentDetail(id);
	}

	@Override
	public List<IntimateNews> queryListByParamOrder(IntimateNewsVo intimateNewsVo) {

		return intimateNewsMapper.queryListByParamOrder(intimateNewsVo);
	}

	@Override
	public IntimateNewsCommentVo getCommentDetailByCommentId(Long id) {

		// 调用mapper方法根据周边店评论的主键
		IntimateNewsComment intimateNewsComment = intimateNewsCommentMapper.selectByPrimaryKey(id);
		/* 格式化查询出的数据 */
		IntimateNewsCommentVo intimateNewsCommentVo = new IntimateNewsCommentVo();
		BeanUtils.copyProperties(intimateNewsComment, intimateNewsCommentVo);
		// 返回周边店评论信息
		return intimateNewsCommentVo;
	}

	@Override
	public IntimateNews selectIntimateNews(Long id) {

		return intimateNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Attach> queryListByParamOrder(Long intimateNewsId) {
		return attachMapper.selectAttachByIntimateNewsId(intimateNewsId);
	}

	@Override
	public boolean updateIntimateNews(IntimateNews intimateNews, String villageId) {
		int i = intimateNewsMapper.updateByPrimaryKeySelective(intimateNews);
		int j = 0;
		if (i > 0) {
			// 删除小区贴心报关系表
			intimateVillageMapper.deleteByIntimateNewsId(intimateNews.getId());

			List<IntimateVillage> listIntimateVillage = new ArrayList<IntimateVillage>();
			String[] village = villageId.split(",");
			for (int k = 0; k < village.length; k++) {
				IntimateVillage intimateVillage = new IntimateVillage();
				intimateVillage.setIntimateNewId(intimateNews.getId());
				intimateVillage.setVillageId(Long.valueOf(village[k]));
				listIntimateVillage.add(intimateVillage);
			}
			// 批量插入贴心报小区中间表
			intimateVillageMapper.insertBatch(listIntimateVillage);

			// 附件更新：1、先删除相关附件;2、新增贴心报附件
			int k = 0;
			List<Attach> attachs = new ArrayList<Attach>();
			// 删除贴心报对应的附件
			i = attachMapper.deleteByIdAndName(intimateNews.getId(), ConstantStr.ATTACH_OWNERTBTYPE_04);
			for (Attach attach : intimateNews.getAttachs()) {
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(intimateNews.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_04);
				attach.setOrdering(k);
				attach.setOpreator(intimateNews.getInsId());
				attach.setInsId(intimateNews.getInsId());
				attach.setInsYmdhms(intimateNews.getInsYmdhms());
				attach.setUpdId(intimateNews.getUpdId());
				attach.setUpdEac(intimateNews.getUpdEac());
				attach.setUpdYmdhms(intimateNews.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				attachs.add(attach);
				k++;
			}
			// 批量插入附件
			j = attachMapper.insertBatch(attachs);

		}
		return j > 0 ? true : false;
	}

	@Override
	public boolean getIntimateNewsByDay(Long intimateNewsId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("intimateNewsId", intimateNewsId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		IntimateNews intimateNews = intimateNewsMapper.selectIntimateNewsByDay(param);
		if (intimateNews != null) {
			// 返回结果
			return true;
		} else {
			// 返回结果
			return false;
		}
	}

	/*@Override
	public boolean getCommentByDay(Long commentId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		 向map中封装数据 
		param.put("commentId", commentId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		IntimateNewsComment intimateNewsComment = intimateNewsCommentMapper.selectCommentByDay(param);
		if (intimateNewsComment != null) {
			// 返回结果
			return true;
		} else {
			// 返回结果
			return false;
		}
	}*/

	@Override
	public IntimateNewsComment selectIntimateNewsComment(Long id) {
		return intimateNewsCommentMapper.selectByPrimaryKey(id);
	}
}
