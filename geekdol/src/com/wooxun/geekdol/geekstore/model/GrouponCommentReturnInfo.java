package com.wooxun.geekdol.geekstore.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

public class GrouponCommentReturnInfo extends CommonEntity{
	private static final long serialVersionUID = 1L;
    /** 主键Id */
    private Long id;

    /** 评论Id */
    private Long commentId;

    /** 评论回答内容 */
    private String commentReturnInfo;

    /** 0:违规;1:正常 */
    private String status;

    /** 回复人 */
    private Long opreater;

    /** 回复Ip */
    private String opreaterIp;

    /** 回复时间 */
    private Date discussTime;

    /**
     *
     * 获取  主键Id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  主键Id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  评论Id
     *
     * @return  commentId
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     *
     * 设置  评论Id
     *
     * @param commentId
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     *
     * 获取  评论回答内容
     *
     * @return  commentReturnInfo
     */
    public String getCommentReturnInfo() {
        return commentReturnInfo;
    }

    /**
     *
     * 设置  评论回答内容
     *
     * @param commentReturnInfo
     */
    public void setCommentReturnInfo(String commentReturnInfo) {
        this.commentReturnInfo = commentReturnInfo == null ? null : commentReturnInfo.trim();
    }

    /**
     *
     * 获取  0:违规;1:正常
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:违规;1:正常
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * 获取  回复人
     *
     * @return  opreater
     */
    public Long getOpreater() {
        return opreater;
    }

    /**
     *
     * 设置  回复人
     *
     * @param opreater
     */
    public void setOpreater(Long opreater) {
        this.opreater = opreater;
    }

    /**
     *
     * 获取  回复Ip
     *
     * @return  opreaterIp
     */
    public String getOpreaterIp() {
        return opreaterIp;
    }

    /**
     *
     * 设置  回复Ip
     *
     * @param opreaterIp
     */
    public void setOpreaterIp(String opreaterIp) {
        this.opreaterIp = opreaterIp == null ? null : opreaterIp.trim();
    }

    /**
     *
     * 获取  回复时间
     *
     * @return  discussTime
     */
    public Date getDiscussTime() {
        return discussTime;
    }

    /**
     *
     * 设置  回复时间
     *
     * @param discussTime
     */
    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
    }

}