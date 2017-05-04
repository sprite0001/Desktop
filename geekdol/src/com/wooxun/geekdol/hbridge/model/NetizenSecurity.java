package com.wooxun.geekdol.hbridge.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

/**
 * 网安报的实体类
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年9月8日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年9月8日  上午9:44:46 		创建
*==========================================================
*
 */
public class NetizenSecurity extends CommonEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 主键id */
    private Long id;

    /** 标题 */
    private String title;

    /** 网安报时间 */
    private Date newsTime;

    /** 摘要 */
    private String summary;

    /** 来源 */
    private String source;

    /** 浏览量 */
    private Integer viewsNumber;

    /** 发布人id */
    private Long publishPersonId;

    /** 发布时间 */
    private Date publishTime;

    /** 发布状态:已发布,未发布 */
    private String publishStatus;

    /** 内容 */
    private String content;
    
    /** 缩略图 */
    private String picture;

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
     * 获取  网安报时间
     *
     * @return  newsTime
     */
    public Date getNewsTime() {
        return newsTime;
    }

    /**
     *
     * 设置  网安报时间
     *
     * @param newsTime
     */
    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    /**
     *
     * 获取  摘要
     *
     * @return  summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * 设置  摘要
     *
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     *
     * 获取  来源
     *
     * @return  source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * 设置  来源
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     *
     * 获取  浏览量
     *
     * @return  viewsNumber
     */
    public Integer getViewsNumber() {
        return viewsNumber;
    }

    /**
     *
     * 设置  浏览量
     *
     * @param viewsNumber
     */
    public void setViewsNumber(Integer viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    /**
     *
     * 获取  发布人id
     *
     * @return  publishPersonId
     */
    public Long getPublishPersonId() {
        return publishPersonId;
    }

    /**
     *
     * 设置  发布人id
     *
     * @param publishPersonId
     */
    public void setPublishPersonId(Long publishPersonId) {
        this.publishPersonId = publishPersonId;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
    
    
}