package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月12日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月12日  下午2:53:38 		创建
 *==========================================================
 * 
 */
public class GoodsRecommendCommentReturnInfoVo extends BaseVo{
	
	/** 主键Id */
    private Long id;

    /** 评论Id */
    private Long commentId;

    /** 评论回答内容 */
    private String commentReturnInfo;

    /** 0:正常;1:违规 */
    private String status;

    /** 回复人 */
    private Long opreater;

    /** 回复Ip */
    private String opreaterIp;

    /** 回复时间 */
    private Date discussTime;
    
    /**自定义*/
    /** 0:正常;1:违规 */
    private String commentStatus;
	/** 评论人 */
    private String realName;
    /** 字典表违规类型的类型指定*/
    private String illegalType;

    public String getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

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
     * 获取  0:正常;1:违规
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:正常;1:违规
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
