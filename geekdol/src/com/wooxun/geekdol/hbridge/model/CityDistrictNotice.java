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
 * 1.  zhangyang	2016年7月26日  上午9:38:25 		创建
 *==========================================================
 * 
 */
public class CityDistrictNotice extends CommonEntity{
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    private Long id;

    /** 公告标题 */
    private String title;

    /** 摘要 */
    private String summary;

    /** 內容 */
    private String content;

    /** 发布人id */
    private Long publishUser;

    /** 公告的落款时间 */
    private Date noticeTime;

    /** 公告的发布时间 */
    private Date publishTime;
    
    /** 发布状态 */
    private String publishStatus;
    
    /** 来源 */
    private String source;

    /** 浏览量 */
    private Long scannedNumber;

    /** 是否置顶：0不置顶，1置顶 */
    private String topFlag;

    /** 公告的落款时间 */
    private Date topStart;

    /** 公告的落款时间 */
    private Date topEnd;

    /** 地域id，对应小区，社区（办事处），区，市，省的id */
    private Long areaId;

    /** 是否置顶：0不置顶，1置顶 */
    private String areaLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
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

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag;
    }

    public Date getTopStart() {
        return topStart;
    }

    public void setTopStart(Date topStart) {
        this.topStart = topStart;
    }

    public Date getTopEnd() {
        return topEnd;
    }

    public void setTopEnd(Date topEnd) {
        this.topEnd = topEnd;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Long getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(Long publishUser) {
        this.publishUser = publishUser;
    }
    
}
