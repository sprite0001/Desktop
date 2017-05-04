package com.wooxun.geekdol.hbridge.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

public class IncorruptGovernmentComment extends CommonEntity {

	private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;

    /** 风清气正文章id */
    private Long incorruptGovernmentId;

    /** 评论内容 */
    private String content;

    /** 是否违规:是,否 */
    private String illegalStatus;

    /** 对这条评论进行点赞数量 */
    private Integer likesNumber;

    /** 对这条评论进行负赞数量 */
    private Integer hateNumber;

    /** 这一条评论的浏览量 */
    private Integer viewNumber;

    /** 这一条评论的回复量 */
    private Integer replyNumber;

    /** 评论的评论 有多少违规的 */
    private Integer illegalNumber;

    /** 评论人 */
    private Long opreaterId;

    /** 发布评论人的Ip */
    private String opreaterIp;

    /** 评论时间 */
    private Date opreaterTime;

    /** 评论地址 */
    private String opreaterAdd;

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
     * 获取  风清气正文章id
     *
     * @return  incorruptGovernmentId
     */
    public Long getIncorruptGovernmentId() {
        return incorruptGovernmentId;
    }

    /**
     *
     * 设置  风清气正文章id
     *
     * @param incorruptGovernmentId
     */
    public void setIncorruptGovernmentId(Long incorruptGovernmentId) {
        this.incorruptGovernmentId = incorruptGovernmentId;
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
     * 获取  对这条评论进行点赞数量
     *
     * @return  likesNumber
     */
    public Integer getLikesNumber() {
        return likesNumber;
    }

    /**
     *
     * 设置  对这条评论进行点赞数量
     *
     * @param likesNumber
     */
    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    /**
     *
     * 获取  对这条评论进行负赞数量
     *
     * @return  hateNumber
     */
    public Integer getHateNumber() {
        return hateNumber;
    }

    /**
     *
     * 设置  对这条评论进行负赞数量
     *
     * @param hateNumber
     */
    public void setHateNumber(Integer hateNumber) {
        this.hateNumber = hateNumber;
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
     * 获取  评论人
     *
     * @return  opreaterId
     */
    public Long getOpreaterId() {
        return opreaterId;
    }

    /**
     *
     * 设置  评论人
     *
     * @param opreaterId
     */
    public void setOpreaterId(Long opreaterId) {
        this.opreaterId = opreaterId;
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

    /**
     *
     * 获取  评论时间
     *
     * @return  opreaterTime
     */
    public Date getOpreaterTime() {
        return opreaterTime;
    }

    /**
     *
     * 设置  评论时间
     *
     * @param opreaterTime
     */
    public void setOpreaterTime(Date opreaterTime) {
        this.opreaterTime = opreaterTime;
    }

    /**
     *
     * 获取  评论地址
     *
     * @return  opreaterAdd
     */
    public String getOpreaterAdd() {
        return opreaterAdd;
    }

    /**
     *
     * 设置  评论地址
     *
     * @param opreaterAdd
     */
    public void setOpreaterAdd(String opreaterAdd) {
        this.opreaterAdd = opreaterAdd == null ? null : opreaterAdd.trim();
    }
}