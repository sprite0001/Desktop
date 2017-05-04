package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月1日  下午2:38:45 		创建
 *==========================================================
 * 
 */
public class GrouponDemandVo extends BaseVo{
	/** 主键Id */
    private Long id;

    /** 小区id */
    private Long villageId;

    /** 团购标题 */
    private String title;

    /** 商品名称 */
    private String goodsName;

    /** 备注 */
    private String details;

    /** 团购人数量 */
    private Integer number;

    /** 关联分类表 */
    private Long classificId;

    /** 团购截止日期 */
    private String endDate;

    /** 联系人 */
    private Long contacts;

    /** 联系电话 */
    private String contactsPhone;

    /** 0:未发布;1:已发布; */
    private String status;

    /** 0:未收藏;1:收藏; */
    private String collectFlag;

    /** 操作用户 */
    private Long insId;

    /** 登记时间 */
    private Date insYmdhms;

    /** 更新用户 */
    private Long updId;

    /** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 0:未删除;1:删除; */
    private String delFlag;
    
    /**以下为自定义属性*/
    
    /** 商品分类名称 */
    private String classificName;
    /**联系人*/
    private String contactsName;
    /** 开始日期 */
    private String startDate;
    /** 结束日期 */
    private String endDateStr;   
	/**状态*/
    private String publishStatus;
    /**用户区域权限*/
    private String areaLevel;
    /**当前用户id*/
    private Long currentUserId;
    /**字典表：商品类型TYPE*/
    private String classificType;
    /**字典表：发布类型TYPE*/
    private String releaseType;
    /**已发布*/
    private String releaseStatus;
    /**未发布*/
    private String unReleaseStatus;
    /**已发布*/
    private String releaseNumber;
    /**未发布*/
    private String unReleaseNumber;
    
    
    public String getReleaseNumber() {
		return releaseNumber;
	}

	public void setReleaseNumber(String releaseNumber) {
		this.releaseNumber = releaseNumber;
	}

	public String getUnReleaseNumber() {
		return unReleaseNumber;
	}

	public void setUnReleaseNumber(String unReleaseNumber) {
		this.unReleaseNumber = unReleaseNumber;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getUnReleaseStatus() {
		return unReleaseStatus;
	}

	public void setUnReleaseStatus(String unReleaseStatus) {
		this.unReleaseStatus = unReleaseStatus;
	}

	public String getReleaseType() {
		return releaseType;
	}

	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}

	public String getClassificType() {
		return classificType;
	}

	public void setClassificType(String classificType) {
		this.classificType = classificType;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
    public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getClassificName() {
		return classificName;
	}

	public void setClassificName(String classificName) {
		this.classificName = classificName;
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
     * 获取  小区id
     *
     * @return  villageId
     */
    public Long getVillageId() {
        return villageId;
    }

    /**
     *
     * 设置  小区id
     *
     * @param villageId
     */
    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    /**
     *
     * 获取  团购标题
     *
     * @return  title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * 设置  团购标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     *
     * 获取  商品名称
     *
     * @return  goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     *
     * 设置  商品名称
     *
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     *
     * 获取  备注
     *
     * @return  details
     */
    public String getDetails() {
        return details;
    }

    /**
     *
     * 设置  备注
     *
     * @param details
     */
    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    /**
     *
     * 获取  团购人数量
     *
     * @return  number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * 设置  团购人数量
     *
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     *
     * 获取  关联分类表
     *
     * @return  classificId
     */
    public Long getClassificId() {
        return classificId;
    }

    /**
     *
     * 设置  关联分类表
     *
     * @param classificId
     */
    public void setClassificId(Long classificId) {
        this.classificId = classificId;
    }

    /**
     *
     * 获取  团购截止日期
     *
     * @return  endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * 设置  团购截止日期
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * 获取  联系人
     *
     * @return  contacts
     */
    public Long getContacts() {
        return contacts;
    }

    /**
     *
     * 设置  联系人
     *
     * @param contacts
     */
    public void setContacts(Long contacts) {
        this.contacts = contacts;
    }

    /**
     *
     * 获取  联系电话
     *
     * @return  contactsPhone
     */
    public String getContactsPhone() {
        return contactsPhone;
    }

    /**
     *
     * 设置  联系电话
     *
     * @param contactsPhone
     */
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    /**
     *
     * 获取  0:未发布;1:已发布;
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:未发布;1:已发布;
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * 获取  0:未收藏;1:收藏;
     *
     * @return  collectFlag
     */
    public String getCollectFlag() {
        return collectFlag;
    }

    /**
     *
     * 设置  0:未收藏;1:收藏;
     *
     * @param collectFlag
     */
    public void setCollectFlag(String collectFlag) {
        this.collectFlag = collectFlag == null ? null : collectFlag.trim();
    }

    /**
     *
     * 获取  操作用户
     *
     * @return  insId
     */
    public Long getInsId() {
        return insId;
    }

    /**
     *
     * 设置  操作用户
     *
     * @param insId
     */
    public void setInsId(Long insId) {
        this.insId = insId;
    }

    /**
     *
     * 获取  登记时间
     *
     * @return  insYmdhms
     */
    public Date getInsYmdhms() {
        return insYmdhms;
    }

    /**
     *
     * 设置  登记时间
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
     * 获取  0:未删除;1:删除;
     *
     * @return  delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     *
     * 设置  0:未删除;1:删除;
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
