package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title 我要团管理
 * @Description  评论人
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月2日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月2日  下午6:12:04 		创建
 *==========================================================
 * 
 */
public class GrouponDemandCommentVo extends BaseVo{
	 /** 主键Id */
    private Long id;

    /** 团购需求id */
    private Long demandId;

    /** 0:正常;1:违规 */
    private String status;

    /** 对这条评论进行点赞数量 */
    private Integer likesNumber;

    /** 对这条评论进行负赞数量 */
    private Integer hateNumber;

    /** 浏览量 */
    private Integer pageView;

    /** 评论人 */
    private Long opreater;

    /** 评论人Ip */
    private String opreaterIp;

    /** 评论时间 */
    private Date discussTime;

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

    /** 评论内容 */
    private String comment;
    
    
    
    
    /**自定义*/
    /** 0:正常;1:违规 */
    private String commentStatus;
    /** 评论人 */
    private String realName;
    /** 回复量*/
    private Long reply;
    /** 违规量*/
    private Long illegal;
    /** 字典表违规类型的类型指定*/
    private String illegalType;
    


	public String getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}

	public Long getReply() {
		return reply;
	}

	public void setReply(Long reply) {
		this.reply = reply;
	}


	public Long getIllegal() {
		return illegal;
	}

	public void setIllegal(Long illegal) {
		this.illegal = illegal;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
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
     * 获取  团购需求id
     *
     * @return  demandId
     */
    public Long getDemandId() {
        return demandId;
    }

    /**
     *
     * 设置  团购需求id
     *
     * @param demandId
     */
    public void setDemandId(Long demandId) {
        this.demandId = demandId;
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
     * 获取  浏览量
     *
     * @return  pageView
     */
    public Integer getPageView() {
        return pageView;
    }

    /**
     *
     * 设置  浏览量
     *
     * @param pageView
     */
    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    /**
     *
     * 获取  评论人
     *
     * @return  opreater
     */
    public Long getOpreater() {
        return opreater;
    }

    /**
     *
     * 设置  评论人
     *
     * @param opreater
     */
    public void setOpreater(Long opreater) {
        this.opreater = opreater;
    }

    /**
     *
     * 获取  评论人Ip
     *
     * @return  opreaterIp
     */
    public String getOpreaterIp() {
        return opreaterIp;
    }

    /**
     *
     * 设置  评论人Ip
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
     * @return  discussTime
     */
    public Date getDiscussTime() {
        return discussTime;
    }

    /**
     *
     * 设置  评论时间
     *
     * @param discussTime
     */
    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
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

    /**
     *
     * 获取  评论内容
     *
     * @return  comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * 设置  评论内容
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}
