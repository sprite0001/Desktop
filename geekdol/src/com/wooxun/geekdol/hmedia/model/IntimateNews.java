package com.wooxun.geekdol.hmedia.model;

import java.util.Date;
import java.util.List;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.Attach;

public class IntimateNews extends CommonEntity {

	private static final long serialVersionUID = 1L;

	/** 贴心报Id */
	private Long id;

	/** 贴心报标题 */
	private String title;

	/** 贴心报摘要 */
	private String summary;

	/** 贴心报来源 */
	private String source;

	/** 图标 */
	private String icon;

	/** 类型：文字类、图片类 */
	private String type;

	/** 报道时间 */
	private Date reportTime;

	/** 是否允许回复：是，否 0不允许回复 1允许回复 */
	private String replyFlag;

	/** 置顶:是，否 */
	private String topFlag;

	/** 置顶开始时间 */
	private Date topStart;

	/** 置顶结束时间 */
	private Date topEnd;

	/** 状态：未提交(0)，已提交(1)，通过(2)，未通过(3) */
	private String verifyStatus;

	/** 审核意见 */
	private String verifyOpinion;

	/** 审核人id */
	private Long verifyId;

	/** 审核时间 */
	private Date verifyTime;

	/** 发布状态：已发布，未发布 */
	private String publishStatus;

	/** 发布时间 */
	private Date publishTime;

	/** 发布人 */
	private Long publishPersonid;

	/** 点赞数量 */
	private Integer likesNumber;

	/** 负赞数量 */
	private Integer hateNumber;

	/** 浏览量 */
	private Integer viewNumber;

	/** 回复量 */
	private Integer replyNumber;

	/** 违规量 */
	private Integer illegalNumber;

	/** 在新增时直接发布为快捷发布(审核状态为已审核)、在新增过后，通过审核(未提交--提交--审核)审核发布为审核发布 */
	private String isQuick;

	/** 如果是从别人的贴心包转发为1,不是转发为0 */
	private String repeatFlag;

	/** 内容 */
	private String content;

	/** 扩展属性 (附件字段) */
	private List<Attach> attachs;

	/** 贴心报与小区关系 **/
	private List<IntimateVillage> intimateVillageList;

	/** 图片扩展字段 */
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;

	/**
	 *
	 * 获取 贴心报Id
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 *
	 * 设置 贴心报Id
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *
	 * 获取 贴心报标题
	 *
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *
	 * 设置 贴心报标题
	 *
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 *
	 * 获取 贴心报摘要
	 *
	 * @return summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 *
	 * 设置 贴心报摘要
	 *
	 * @param summary
	 */
	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	/**
	 *
	 * 获取 贴心报来源
	 *
	 * @return source
	 */
	public String getSource() {
		return source;
	}

	/**
	 *
	 * 设置 贴心报来源
	 *
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	/**
	 *
	 * 获取 图标
	 *
	 * @return icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 *
	 * 设置 图标
	 *
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	/**
	 *
	 * 获取 类型：文字类、图片类
	 *
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 *
	 * 设置 类型：文字类、图片类
	 *
	 * @param type
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 *
	 * 获取 报道时间
	 *
	 * @return reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 *
	 * 设置 报道时间
	 *
	 * @param reportTime
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 *
	 * 获取 是否允许回复：是，否
	 *
	 * @return replyFlag
	 */
	public String getReplyFlag() {
		return replyFlag;
	}

	/**
	 *
	 * 设置 是否允许回复：是，否
	 *
	 * @param replyFlag
	 */
	public void setReplyFlag(String replyFlag) {
		this.replyFlag = replyFlag == null ? null : replyFlag.trim();
	}

	/**
	 *
	 * 获取 置顶:是，否
	 *
	 * @return topFlag
	 */
	public String getTopFlag() {
		return topFlag;
	}

	/**
	 *
	 * 设置 置顶:是，否
	 *
	 * @param topFlag
	 */
	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag == null ? null : topFlag.trim();
	}

	/**
	 *
	 * 获取 置顶开始时间
	 *
	 * @return topStart
	 */
	public Date getTopStart() {
		return topStart;
	}

	/**
	 *
	 * 设置 置顶开始时间
	 *
	 * @param topStart
	 */
	public void setTopStart(Date topStart) {
		this.topStart = topStart;
	}

	/**
	 *
	 * 获取 置顶结束时间
	 *
	 * @return topEnd
	 */
	public Date getTopEnd() {
		return topEnd;
	}

	/**
	 *
	 * 设置 置顶结束时间
	 *
	 * @param topEnd
	 */
	public void setTopEnd(Date topEnd) {
		this.topEnd = topEnd;
	}

	/**
	 *
	 * 获取 状态：未提交，已提交，通过，未通过
	 *
	 * @return verifyStatus
	 */
	public String getVerifyStatus() {
		return verifyStatus;
	}

	/**
	 *
	 * 设置 状态：未提交，已提交，通过，未通过
	 *
	 * @param verifyStatus
	 */
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus == null ? null : verifyStatus.trim();
	}

	/**
	 *
	 * 获取 审核意见
	 *
	 * @return verifyOpinion
	 */
	public String getVerifyOpinion() {
		return verifyOpinion;
	}

	/**
	 *
	 * 设置 审核意见
	 *
	 * @param verifyOpinion
	 */
	public void setVerifyOpinion(String verifyOpinion) {
		this.verifyOpinion = verifyOpinion == null ? null : verifyOpinion.trim();
	}

	/**
	 *
	 * 获取 审核人id
	 *
	 * @return verifyId
	 */
	public Long getVerifyId() {
		return verifyId;
	}

	/**
	 *
	 * 设置 审核人id
	 *
	 * @param verifyId
	 */
	public void setVerifyId(Long verifyId) {
		this.verifyId = verifyId;
	}

	/**
	 *
	 * 获取 审核时间
	 *
	 * @return verifyTime
	 */
	public Date getVerifyTime() {
		return verifyTime;
	}

	/**
	 *
	 * 设置 审核时间
	 *
	 * @param verifyTime
	 */
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	/**
	 *
	 * 获取 发布状态：已发布，未发布
	 *
	 * @return publishStatus
	 */
	public String getPublishStatus() {
		return publishStatus;
	}

	/**
	 *
	 * 设置 发布状态：已发布，未发布
	 *
	 * @param publishStatus
	 */
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus == null ? null : publishStatus.trim();
	}

	/**
	 *
	 * 获取 发布时间
	 *
	 * @return publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}

	/**
	 *
	 * 设置 发布时间
	 *
	 * @param publishTime
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 *
	 * 获取 发布人
	 *
	 * @return publishPersonid
	 */
	public Long getPublishPersonid() {
		return publishPersonid;
	}

	/**
	 *
	 * 设置 发布人
	 *
	 * @param publishPersonid
	 */
	public void setPublishPersonid(Long publishPersonid) {
		this.publishPersonid = publishPersonid;
	}

	/**
	 *
	 * 获取 点赞数量
	 *
	 * @return likesNumber
	 */
	public Integer getLikesNumber() {
		return likesNumber;
	}

	/**
	 *
	 * 设置 点赞数量
	 *
	 * @param likesNumber
	 */
	public void setLikesNumber(Integer likesNumber) {
		this.likesNumber = likesNumber;
	}

	/**
	 *
	 * 获取 负赞数量
	 *
	 * @return hateNumber
	 */
	public Integer getHateNumber() {
		return hateNumber;
	}

	/**
	 *
	 * 设置 负赞数量
	 *
	 * @param hateNumber
	 */
	public void setHateNumber(Integer hateNumber) {
		this.hateNumber = hateNumber;
	}

	/**
	 *
	 * 获取 浏览量
	 *
	 * @return viewNumber
	 */
	public Integer getViewNumber() {
		return viewNumber;
	}

	/**
	 *
	 * 设置 浏览量
	 *
	 * @param viewNumber
	 */
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}

	/**
	 *
	 * 获取 回复量
	 *
	 * @return replyNumber
	 */
	public Integer getReplyNumber() {
		return replyNumber;
	}

	/**
	 *
	 * 设置 回复量
	 *
	 * @param replyNumber
	 */
	public void setReplyNumber(Integer replyNumber) {
		this.replyNumber = replyNumber;
	}

	/**
	 *
	 * 获取 违规量
	 *
	 * @return illegalNumber
	 */
	public Integer getIllegalNumber() {
		return illegalNumber;
	}

	/**
	 *
	 * 设置 违规量
	 *
	 * @param illegalNumber
	 */
	public void setIllegalNumber(Integer illegalNumber) {
		this.illegalNumber = illegalNumber;
	}

	/**
	 *
	 * 获取 在新增时直接发布为快捷发布(审核状态为已审核)、在新增过后，通过审核(未提交--提交--审核)审核发布为审核发布
	 *
	 * @return isQuick
	 */
	public String getIsQuick() {
		return isQuick;
	}

	/**
	 *
	 * 设置 在新增时直接发布为快捷发布(审核状态为已审核)、在新增过后，通过审核(未提交--提交--审核)审核发布为审核发布
	 *
	 * @param isQuick
	 */
	public void setIsQuick(String isQuick) {
		this.isQuick = isQuick == null ? null : isQuick.trim();
	}

	/**
	 *
	 * 获取 如果是从别人的贴心包转发为1,不是转发为0
	 *
	 * @return repeatFlag
	 */
	public String getRepeatFlag() {
		return repeatFlag;
	}

	/**
	 *
	 * 设置 如果是从别人的贴心包转发为1,不是转发为0
	 *
	 * @param repeatFlag
	 */
	public void setRepeatFlag(String repeatFlag) {
		this.repeatFlag = repeatFlag == null ? null : repeatFlag.trim();
	}

	/**
	 *
	 * 获取 内容
	 *
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 *
	 * 设置 内容
	 *
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public List<Attach> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Attach> attachs) {
		this.attachs = attachs;
	}

	public List<IntimateVillage> getIntimateVillageList() {
		return intimateVillageList;
	}

	public void setIntimateVillageList(List<IntimateVillage> intimateVillageList) {
		this.intimateVillageList = intimateVillageList;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPic4() {
		return pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
}