package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.AppUser;

public class AroundStoreCommentReply extends CommonEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    private Long id;

    /** 周边店评论的id */
    private Long aroundStoreCommentId;

    /** 评论内容 */
    private String content;

    /** 是否违规:是,否 */
    private String illegalStatus;

    /** 这一条评论的浏览量 */
    private Integer viewNumber;

    /** 这一条评论的回复量 */
    private Integer replyNumber;

    /** 评论的评论 有多少违规的 */
    private Integer illegalNumber;

    /** 发布评论人的Ip */
    private String opreaterIp;
    
    /** 评论人信息 */
    private AppUser appUser;

    /**
     *
     * 获取  主键
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  主键
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  周边店评论的id
     *
     * @return  aroundStoreCommentId
     */
    public Long getAroundStoreCommentId() {
        return aroundStoreCommentId;
    }

    /**
     *
     * 设置  周边店评论的id
     *
     * @param aroundStoreCommentId
     */
    public void setAroundStoreCommentId(Long aroundStoreCommentId) {
        this.aroundStoreCommentId = aroundStoreCommentId;
    }

    /**
     *
     * 获取  评论内容
     *
     * @return  content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * 设置  评论内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *
     * 获取  是否违规:是,否
     *
     * @return  illegalStatus
     */
    public String getIllegalStatus() {
        return illegalStatus;
    }

    /**
     *
     * 设置  是否违规:是,否
     *
     * @param illegalStatus
     */
    public void setIllegalStatus(String illegalStatus) {
        this.illegalStatus = illegalStatus == null ? null : illegalStatus.trim();
    }

    /**
     *
     * 获取  这一条评论的浏览量
     *
     * @return  viewNumber
     */
    public Integer getViewNumber() {
        return viewNumber;
    }

    /**
     *
     * 设置  这一条评论的浏览量
     *
     * @param viewNumber
     */
    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    /**
     *
     * 获取  这一条评论的回复量
     *
     * @return  replyNumber
     */
    public Integer getReplyNumber() {
        return replyNumber;
    }

    /**
     *
     * 设置  这一条评论的回复量
     *
     * @param replyNumber
     */
    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    /**
     *
     * 获取  评论的评论 有多少违规的
     *
     * @return  illegalNumber
     */
    public Integer getIllegalNumber() {
        return illegalNumber;
    }

    /**
     *
     * 设置  评论的评论 有多少违规的
     *
     * @param illegalNumber
     */
    public void setIllegalNumber(Integer illegalNumber) {
        this.illegalNumber = illegalNumber;
    }

    /**
     *
     * 获取  发布评论人的Ip
     *
     * @return  opreaterIp
     */
    public String getOpreaterIp() {
        return opreaterIp;
    }

    /**
     *
     * 设置  发布评论人的Ip
     *
     * @param opreaterIp
     */
    public void setOpreaterIp(String opreaterIp) {
        this.opreaterIp = opreaterIp == null ? null : opreaterIp.trim();
    }

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}