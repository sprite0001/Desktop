package com.wooxun.geekdol.hmedia.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

public class IntimateNewsCommentRecommend extends CommonEntity {

	private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;

    /** 评论id */
    private Long commentId;

    /** 点赞人id */
    private Long operatorId;

    /** 点赞时间 */
    private Date operatorTime;

    /** 0:点赞;1不点赞 */
    private String operatorType;

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
     * 获取  评论id
     *
     * @return  commentId
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     *
     * 设置  评论id
     *
     * @param commentId
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     *
     * 获取  点赞人id
     *
     * @return  operatorId
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     *
     * 设置  点赞人id
     *
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     *
     * 获取  点赞时间
     *
     * @return  operatorTime
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     *
     * 设置  点赞时间
     *
     * @param operatorTime
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    /**
     *
     * 获取  0:点赞;1不点赞
     *
     * @return  operatorType
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     *
     * 设置  0:点赞;1不点赞
     *
     * @param operatorType
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

}