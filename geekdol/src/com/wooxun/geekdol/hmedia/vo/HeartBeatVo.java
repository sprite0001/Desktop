package com.wooxun.geekdol.hmedia.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.system.vo.BaseVo;

public class HeartBeatVo extends BaseVo {
    /** id */
    private Long id;

    /** 内容 */
    private String content;

    /** 内容分类:生活,爆料.从数据字典中取得 */
    private String contentType;

    /** 赞的数量 */
    private Integer likesNumber;

    /** 浏览量 */
    private Integer viewNumber;

    /** 评论量 */
    private Integer replyNumber;

    /** 处理状态：01正常 02违规 03举报,  04已审 */
    private String treatmentStatus;

    /** 发布状态时,当前位置 */
    private String currentLocation;

    /** 发布状态时我常驻的小区 */
    private Long villageId;
    /** 发布状态时我常驻的小区 */
    private String villageIds;

    /** 发布状态时经度 */
    private BigDecimal longitude;

    /** 发布状态时纬度 */
    private BigDecimal latitude;

    /** 发布人ID */
    private Long publishPersonId;

    /** 发布时间 */
    private Date publishTime;

    /** 操作人 */
    private Long insId;

    /** 录入时间 */
    private Date insYmdhms;

    /** 审核人 */
    private Long verifyId;

    /** 审核时间 */
    private Date verifyYmdhms;

    /** 审核内容 */
    private String verrifyContent;

    /** 更新人 */
    private Long updId;

    /** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 删除标记 */
    private String delFlag;


    
    /** 省份id */
    private Long provinceId;
    
    /** 市id */
    private Long cityId;
    
    /** 区县id */
    private Long countyId;
    
    /** 社区id */
    private Long communityId;
    
    /** 小区名 */
    private String villageName;
    
    /** 手机号 */
    private String mobile;
    
    /** 公告的发布时间起点 */
    private Date publishTimeBegin;

    /** 公告的发布时间起点字符串 */
    private String publishTimeBeginStr;
    
    /** 公告的发布时间结点 */
    private Date publishTimeEnd;
    
    /** 公告的发布时间结点字符串 */
    private String publishTimeEndStr;
    
    /** 区域级别 */
    private String areaLevel;
    
    /** 类别名称 */
    private String contentTypeName;
    
    /** 状态名称 */
    private String treatmentStatusName;
    
    /** 排序:搜索条件*/
    private String orderBy;
    /** 举报量*/
    public int reportNumber;
    /**
     *
     * 获取  
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  
     *
     * @return  content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * 设置  
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *
     * 获取  
     *
     * @return  contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     *
     * 设置  
     *
     * @param contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    /**
     *
     * 获取  
     *
     * @return  likesNumber
     */
    public Integer getLikesNumber() {
        return likesNumber;
    }

    /**
     *
     * 设置  
     *
     * @param likesNumber
     */
    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    /**
     *
     * 获取  
     *
     * @return  viewNumber
     */
    public Integer getViewNumber() {
        return viewNumber;
    }

    /**
     *
     * 设置  
     *
     * @param viewNumber
     */
    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    public String getTreatmentStatus() {
        return treatmentStatus;
    }

    public void setTreatmentStatus(String treatmentStatus) {
        this.treatmentStatus = treatmentStatus == null ? null : treatmentStatus.trim();
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation == null ? null : currentLocation.trim();
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Long getPublishPersonId() {
        return publishPersonId;
    }

    public void setPublishPersonId(Long publishPersonId) {
        this.publishPersonId = publishPersonId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Long getInsId() {
        return insId;
    }

    public void setInsId(Long insId) {
        this.insId = insId;
    }

    public Date getInsYmdhms() {
        return insYmdhms;
    }

    public void setInsYmdhms(Date insYmdhms) {
        this.insYmdhms = insYmdhms;
    }

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    public Date getVerifyYmdhms() {
        return verifyYmdhms;
    }

    public void setVerifyYmdhms(Date verifyYmdhms) {
        this.verifyYmdhms = verifyYmdhms;
    }

    public String getVerrifyContent() {
        return verrifyContent;
    }

    public void setVerrifyContent(String verrifyContent) {
        this.verrifyContent = verrifyContent == null ? null : verrifyContent.trim();
    }

    public Long getUpdId() {
        return updId;
    }

    public void setUpdId(Long updId) {
        this.updId = updId;
    }

    public Date getUpdYmdhms() {
        return updYmdhms;
    }

    public void setUpdYmdhms(Date updYmdhms) {
        this.updYmdhms = updYmdhms;
    }

    public Long getUpdEac() {
        return updEac;
    }

    public void setUpdEac(Long updEac) {
        this.updEac = updEac;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }


    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }
    
    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getTreatmentStatusName() {
        return treatmentStatusName;
    }

    public void setTreatmentStatusName(String treatmentStatusName) {
        this.treatmentStatusName = treatmentStatusName;
    }
    
    public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getVillageIds() {
		return villageIds;
	}

	public void setVillageIds(String villageIds) {
		this.villageIds = villageIds;
	}

	/**
     * 根据传递过来的区域区分查询哪个级别下的数据
     * @Title
     * @Description
     * @author:张洋
     * @CreateDate:2016年9月14日 上午11:27:23
     */
    public void initAreaLevl(){
        if(this.communityId != null && this.communityId != 0L){
            this.areaLevel = ConstantStr.COMMUNITY_CODE;
        }else if(this.countyId != null && this.countyId != 0L){
            this.areaLevel = ConstantStr.COUNTY_CODE;
        }else if(this.cityId != null && this.cityId != 0L){
            this.areaLevel = ConstantStr.CITY_CODE;
        }else if(this.provinceId != null && this.provinceId != 0L){
            this.areaLevel = ConstantStr.PROVINCE_CODE;
        }else{
            this.areaLevel = null;
        }
    }
    /**
     * 初始化开始时间和结束时间
     * @Title
     * @Description
     * @author:张洋
     * @CreateDate:2016年9月14日 上午11:27:23
     */
    public void initTime(){
        if(this.getPublishTimeBeginStr() != null && !this.getPublishTimeBeginStr().trim().equals("")){
            this.setPublishTimeBeginStr(this.getPublishTimeBeginStr() + " 00:00:00");
            this.setPublishTimeBegin(DateUtil.parse(this.getPublishTimeBeginStr()));
        }else{
            this.setPublishTimeBegin(null);
        }
        if(this.getPublishTimeEndStr() != null && !this.getPublishTimeEndStr().trim().equals("")){
            this.setPublishTimeEndStr(this.getPublishTimeEndStr() + " 23:59:59");
            this.setPublishTimeEnd(DateUtil.parse(this.getPublishTimeEndStr()));
        }else{
            this.setPublishTimeEnd(null);
        }
    }

	public int getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(int reportNumber) {
		this.reportNumber = reportNumber;
	}
    
}