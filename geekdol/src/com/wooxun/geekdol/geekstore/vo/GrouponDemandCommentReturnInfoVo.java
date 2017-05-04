package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月2日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月2日  下午9:39:32 		创建
 *==========================================================
 * 
 */
public class GrouponDemandCommentReturnInfoVo extends BaseVo{
	/** 主键Id */
    private Long id;

    /** 评论Id */
    private Long commentId;
    
    /** 回复人 */
    private Long opreater;

	/** 回复人Ip */
    private String opreaterIp;

    /** 评论回答内容 */
    private String commentReturnInfo;

    /** 0:违规;1:正常 */
    private String status;

    /** 录入用户 */
    private Long insId;

    /** 创建时间 */
    private Date insYmdhms;

    /** 更新用户 */
    private Long updId;

    /** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 0:未删除;1:删除 */
    private String delFlag;
    
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

    public Long getOpreater() {
		return opreater;
	}

	public void setOpreater(Long opreater) {
		this.opreater = opreater;
	}

	public String getOpreaterIp() {
		return opreaterIp;
	}

	public void setOpreaterIp(String opreaterIp) {
		this.opreaterIp = opreaterIp;
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
     * 获取  录入用户
     *
     * @return  insId
     */
    public Long getInsId() {
        return insId;
    }

    /**
     *
     * 设置  录入用户
     *
     * @param insId
     */
    public void setInsId(Long insId) {
        this.insId = insId;
    }

    /**
     *
     * 获取  创建时间
     *
     * @return  insYmdhms
     */
    public Date getInsYmdhms() {
        return insYmdhms;
    }

    /**
     *
     * 设置  创建时间
     *
     * @param insYmdhms
     */
    public void setInsYmdhms(Date insYmdhms) {
        this.insYmdhms = insYmdhms;
    }

    /**
     *
     * 获取  更新用户
     *
     * @return  updId
     */
    public Long getUpdId() {
        return updId;
    }

    /**
     *
     * 设置  更新用户
     *
     * @param updId
     */
    public void setUpdId(Long updId) {
        this.updId = updId;
    }

    /**
     *
     * 获取  更新时间
     *
     * @return  updYmdhms
     */
    public Date getUpdYmdhms() {
        return updYmdhms;
    }

    /**
     *
     * 设置  更新时间
     *
     * @param updYmdhms
     */
    public void setUpdYmdhms(Date updYmdhms) {
        this.updYmdhms = updYmdhms;
    }

    /**
     *
     * 获取  更新回数
     *
     * @return  updEac
     */
    public Long getUpdEac() {
        return updEac;
    }

    /**
     *
     * 设置  更新回数
     *
     * @param updEac
     */
    public void setUpdEac(Long updEac) {
        this.updEac = updEac;
    }

    /**
     *
     * 获取  0:未删除;1:删除
     *
     * @return  delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     *
     * 设置  0:未删除;1:删除
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
