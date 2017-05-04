package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月22日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月22日  下午5:14:25 		创建
 *==========================================================
 * 
 */
public class GoodsClassificVo extends BaseVo{
	/** 主键 */
    private Long id;

    /** 服务编号 */
    private String serviceCode;

    /** 服务分类编号 */
    private Long classificId;
    
    /** 服务分类名称 */
    private String classificName;

    /** 服务名称 */
    private String serviceName;

    /** 联系人 */
    private String contacts;

    /** 联系方式 */
    private String contactsPhone;

    /** 查看次数 */
    private Integer views;

    /** 详细描述 */
    private String detail;
    
    private Date insYmdhms;
    
    private Long villageId;

    /**
     *
     * 获取  主键
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  主键
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  服务编号
     *
     * @return  serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     *
     * 设置  服务编号
     *
     * @param serviceCode
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    /**
     *
     * 获取  服务分类编号
     *
     * @return  classificId
     */
    public Long getClassificId() {
        return classificId;
    }

    /**
     *
     * 设置  服务分类编号
     *
     * @param classificId
     */
    public void setClassificId(Long classificId) {
        this.classificId = classificId;
    }

    /**
     *
     * 获取  服务名称
     *
     * @return  serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     *
     * 设置  服务名称
     *
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    /**
     *
     * 获取  联系人
     *
     * @return  contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     *
     * 设置  联系人
     *
     * @param contacts
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     *
     * 获取  联系方式
     *
     * @return  contactsPhone
     */
    public String getContactsPhone() {
        return contactsPhone;
    }

    /**
     *
     * 设置  联系方式
     *
     * @param contactsPhone
     */
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    /**
     *
     * 获取  查看次数
     *
     * @return  views
     */
    public Integer getViews() {
        return views;
    }

    /**
     *
     * 设置  查看次数
     *
     * @param views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     *
     * 获取  详细描述
     *
     * @return  detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     *
     * 设置  详细描述
     *
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

	public String getClassificName() {
		return classificName;
	}

	public void setClassificName(String classificName) {
		this.classificName = classificName;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public Date getInsYmdhms() {
		return insYmdhms;
	}

	public void setInsYmdhms(Date insYmdhms) {
		this.insYmdhms = insYmdhms;
	}
}
