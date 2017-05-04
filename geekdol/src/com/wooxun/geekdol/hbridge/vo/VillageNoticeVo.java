package com.wooxun.geekdol.hbridge.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class VillageNoticeVo extends BaseVo{
    /** 主键id */
    private Long id;

    /** 公告标题 */
    private String title;

    /** 摘要 */
    private String summary;

    /** 公告的落款时间 */
    private Date noticeTime;

    /** 发布状态 */
    private String publishStatus;

    /** 发布人id */
    private Long publishUser;

    /** 发布时间 */
    private Date publishTime;

    /** 浏览量 */
    private Long scannedNumber;

    /** 是否置顶：0不置顶，1置顶 */
    private String topFlag;

    /** 置顶开始时间 */
    private Date topStart;

    /** 置顶结束时间 */
    private Date topEnd;

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

    /** 删除标记 */
    private String delFlag;

    /** 内容 */
    private String content;
    
    /** 来源 */
    private String source;

    
    //其他参数，方便查询和显示

    /** 排除的id */
    private Long outId;
    /** 公告的落款时间字符串 */
    private String noticeTimeStr;
    
    /** 公告的发布时间起点 */
    private Date publishTimeBegin;

    /** 公告的发布时间起点字符串 */
    private String publishTimeBeginStr;
    
    /** 公告的发布时间结点 */
    private Date publishTimeEnd;
    
    /** 公告的发布时间结点字符串 */
    private String publishTimeEndStr;

    /** 置顶开始时间字符串 */
    private String topStartStr;

    /** 置顶结束时间字符串 */
    private String topEndStr;

    /** 选择的社区id */
    private String villageIds;

    /** 选择的社区id-单个 */
    private Long villageId;

    /** 选择的社区名称 */
    private String villageNames;

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

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
     * 获取  公告标题
     *
     * @return  title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * 设置  公告标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
     * 获取  公告的落款时间
     *
     * @return  noticeTime
     */
    public Date getNoticeTime() {
        return noticeTime;
    }

    /**
     *
     * 设置  公告的落款时间
     *
     * @param noticeTime
     */
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    /**
     *
     * 获取  发布状态
     *
     * @return  publishStatus
     */
    public String getPublishStatus() {
        return publishStatus;
    }

    /**
     *
     * 设置  发布状态
     *
     * @param publishStatus
     */
    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus == null ? null : publishStatus.trim();
    }

    /**
     *
     * 获取  浏览量
     *
     * @return  scannedNumber
     */
    public Long getScannedNumber() {
        return scannedNumber;
    }

    /**
     *
     * 设置  浏览量
     *
     * @param scannedNumber
     */
    public void setScannedNumber(Long scannedNumber) {
        this.scannedNumber = scannedNumber;
    }

    /**
     *
     * 获取  是否置顶：0不置顶，1置顶
     *
     * @return  topFlag
     */
    public String getTopFlag() {
        return topFlag;
    }

    /**
     *
     * 设置  是否置顶：0不置顶，1置顶
     *
     * @param topFlag
     */
    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag == null ? null : topFlag.trim();
    }

    /**
     *
     * 获取  置顶开始时间
     *
     * @return  topStart
     */
    public Date getTopStart() {
        return topStart;
    }

    /**
     *
     * 设置  置顶开始时间
     *
     * @param topStart
     */
    public void setTopStart(Date topStart) {
        this.topStart = topStart;
    }

    /**
     *
     * 获取  置顶结束时间
     *
     * @return  topEnd
     */
    public Date getTopEnd() {
        return topEnd;
    }

    /**
     *
     * 设置  置顶结束时间
     *
     * @param topEnd
     */
    public void setTopEnd(Date topEnd) {
        this.topEnd = topEnd;
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
     * 获取  删除标记
     *
     * @return  delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     *
     * 设置  删除标记
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
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

    public String getNoticeTimeStr() {
        return noticeTimeStr;
    }

    public void setNoticeTimeStr(String noticeTimeStr) {
        this.noticeTimeStr = noticeTimeStr;
    }

    public Date getPublishTimeBegin() {
        return publishTimeBegin;
    }

    public void setPublishTimeBegin(Date publishTimeBegin) {
        this.publishTimeBegin = publishTimeBegin;
    }

    public String getPublishTimeBeginStr() {
        return publishTimeBeginStr;
    }

    public void setPublishTimeBeginStr(String publishTimeBeginStr) {
        this.publishTimeBeginStr = publishTimeBeginStr;
    }

    public Date getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(Date publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd;
    }

    public String getPublishTimeEndStr() {
        return publishTimeEndStr;
    }

    public void setPublishTimeEndStr(String publishTimeEndStr) {
        this.publishTimeEndStr = publishTimeEndStr;
    }

    public String getTopStartStr() {
        return topStartStr;
    }

    public void setTopStartStr(String topStartStr) {
        this.topStartStr = topStartStr;
    }

    public String getTopEndStr() {
        return topEndStr;
    }

    public void setTopEndStr(String topEndStr) {
        this.topEndStr = topEndStr;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getVillageIds() {
        return villageIds;
    }

    public void setVillageIds(String villageIds) {
        this.villageIds = villageIds;
    }

    public String getVillageNames() {
        return villageNames;
    }

    public void setVillageNames(String villageNames) {
        this.villageNames = villageNames;
    }

    public Long getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(Long publishUser) {
        this.publishUser = publishUser;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    
}