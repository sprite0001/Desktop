package com.wooxun.geekdol.hbridge.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class IncorruptGovernmentVo extends BaseVo {

    /** 主键id */
    private Long id;

    /** 标题 */
    private String title;

    /** 政府发布信息时间 */
    private Date messageTime;

    /** 从数据字典中获取类别id:诈骗,攻击 */
    private String type;
    /** 类型在数据字典中的type指定*/
    private String typeType;
    /** 从数据字典中获取类别名称*/
    private String typeStr;

    /** 发布状态:已发布,未发布 */
    private String publishStatus;
    /**发布状态在数据字典中type类型指定*/
    private String publishStatusType;
    /** 发布状态名称 */
    private String publishStatusStr;

    /** 发布人 */
    private Long publishPersonId;
    /** 发布人名称 */
    private String publishPerson;

    /** 发布时间 */
    private Date publishTime;
    
    private String publishStartTime;
    
    private String publishEndTime;

    /** 浏览量 */
    private Integer viewNumber;

    /** 关注数量 */
    private Integer likesNumber;

    /** 不关注数量 */
    private Integer hateNumber;

    /** 回复量 */
    private Integer replyNumber;

    /** 违规量 */
    private Integer illegalNumber;

    private String pic;
    /** 内容 */
    private String content;
    /** 来源*/
    private String contentFrom;
    
    private String orderBy;
    
    public Long updEac;

    /**
     *
     * 获取  主键id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  主键id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  标题
     *
     * @return  title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * 设置  标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     *
     * 获取  政府发布信息时间
     *
     * @return  messageTime
     */
    public Date getMessageTime() {
        return messageTime;
    }

    /**
     *
     * 设置  政府发布信息时间
     *
     * @param messageTime
     */
    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    /**
     *
     * 获取  从数据字典中获取类别id:诈骗,攻击
     *
     * @return  type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * 设置  从数据字典中获取类别id:诈骗,攻击
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *
     * 获取  发布状态:已发布,未发布
     *
     * @return  publishStatus
     */
    public String getPublishStatus() {
        return publishStatus;
    }

    /**
     *
     * 设置  发布状态:已发布,未发布
     *
     * @param publishStatus
     */
    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus == null ? null : publishStatus.trim();
    }

    /**
     *
     * 获取  发布人
     *
     * @return  publishPersonId
     */
    public Long getPublishPersonId() {
        return publishPersonId;
    }

    /**
     *
     * 设置  发布人
     *
     * @param publishPersonId
     */
    public void setPublishPersonId(Long publishPersonId) {
        this.publishPersonId = publishPersonId;
    }
    
    /** 获取 发布人名称 */
    public String getPublishPerson() {
		return publishPerson;
	}
    /** 设置 发布人名称 */
	public void setPublishPerson(String publishPerson) {
		this.publishPerson = publishPerson;
	}

	/**
     *
     * 获取  发布时间
     *
     * @return  publishTime
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     *
     * 设置  发布时间
     *
     * @param publishTime
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
    public String getPublishStartTime() {
		return publishStartTime;
	}

	public void setPublishStartTime(String publishStartTime) {
		this.publishStartTime = publishStartTime;
	}

	public String getPublishEndTime() {
		return publishEndTime;
	}

	public void setPublishEndTime(String publishEndTime) {
		this.publishEndTime = publishEndTime;
	}

	/**
     *
     * 获取  浏览量
     *
     * @return  viewNumber
     */
    public Integer getViewNumber() {
        return viewNumber;
    }

    /**
     *
     * 设置  浏览量
     *
     * @param viewNumber
     */
    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    /**
     *
     * 获取  关注数量
     *
     * @return  likesNumber
     */
    public Integer getLikesNumber() {
        return likesNumber;
    }

    /**
     *
     * 设置  关注数量
     *
     * @param likesNumber
     */
    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    /**
     *
     * 获取  不关注数量
     *
     * @return  hateNumber
     */
    public Integer getHateNumber() {
        return hateNumber;
    }

    /**
     *
     * 设置  不关注数量
     *
     * @param hateNumber
     */
    public void setHateNumber(Integer hateNumber) {
        this.hateNumber = hateNumber;
    }

    /**
     *
     * 获取  回复量
     *
     * @return  replyNumber
     */
    public Integer getReplyNumber() {
        return replyNumber;
    }

    /**
     *
     * 设置  回复量
     *
     * @param replyNumber
     */
    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    /**
     *
     * 获取  违规量
     *
     * @return  illegalNumber
     */
    public Integer getIllegalNumber() {
        return illegalNumber;
    }

    /**
     *
     * 设置  违规量
     *
     * @param illegalNumber
     */
    public void setIllegalNumber(Integer illegalNumber) {
        this.illegalNumber = illegalNumber;
    }

    public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
     *
     * 获取  内容
     *
     * @return  content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * 设置  内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getPublishStatusStr() {
		return publishStatusStr;
	}

	public void setPublishStatusStr(String publishStatusStr) {
		this.publishStatusStr = publishStatusStr;
	}

	public String getTypeType() {
		return typeType;
	}

	public void setTypeType(String typeType) {
		this.typeType = typeType;
	}

	public String getPublishStatusType() {
		return publishStatusType;
	}

	public void setPublishStatusType(String publishStatusType) {
		this.publishStatusType = publishStatusType;
	}

	public String getContentFrom() {
		return contentFrom;
	}

	public void setContentFrom(String contentFrom) {
		this.contentFrom = contentFrom;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getUpdEac() {
		return updEac;
	}

	public void setUpdEac(Long updEac) {
		this.updEac = updEac;
	}
}