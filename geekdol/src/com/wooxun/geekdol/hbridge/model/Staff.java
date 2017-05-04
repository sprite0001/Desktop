package com.wooxun.geekdol.hbridge.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年7月26日  上午9:31:18 		创建
 *==========================================================
 * 
 */
public class Staff extends CommonEntity{
    private static final long serialVersionUID = 1L;
    /** 主键id */
    private Long id;
    
    /** 内参标题 */
    private String title;
    
    /** 摘要 */
    private String summary;
    
    /** 内容 */
    private String content;
    
    /** 查看人员最低级别:科级,处级,厅级 */
    private String staffLevel;
    
    /** 来源 */
    private String source;
    
    /** 分类:经验,提醒,表扬 */
    private String type;

    /** 公告的发布时间 */
    private Date publishTime;
    
    /** 发布状态 */
    private String publishStatus;

    /** 浏览量 */
    private Long scannedNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Long getScannedNumber() {
        return scannedNumber;
    }

    public void setScannedNumber(Long scannedNumber) {
        this.scannedNumber = scannedNumber;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
}
