package com.wooxun.geekdol.geekstore.model;

import java.math.BigDecimal;
import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

public class CooperationApplication extends CommonEntity{
    
	private static final long serialVersionUID = 1L;

	/** 合作申请Id */
    private Long id;

    /** 小区Id */
    private Long villageId;

    /** 店名 */
    private String storeName;

    /** 申请人姓名 */
    private String applicationName;

    /** 联系电话 */
    private String contactNumber;

    /** 店铺地址 */
    private String storeAdress;

    /** 超市id、便利店id、百货商店id */
    private String typeId;

    /** 经营面积 */
    private BigDecimal size;

    /** 申请日期 */
    private Date applicationDate;

    /**
     *
     * 获取  合作申请Id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  合作申请Id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  小区Id
     *
     * @return  villageId
     */
    public Long getVillageId() {
        return villageId;
    }

    /**
     *
     * 设置  小区Id
     *
     * @param villageId
     */
    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    /**
     *
     * 获取  店名
     *
     * @return  storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     *
     * 设置  店名
     *
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    /**
     *
     * 获取  申请人姓名
     *
     * @return  applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     *
     * 设置  申请人姓名
     *
     * @param applicationName
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    /**
     *
     * 获取  联系电话
     *
     * @return  contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     *
     * 设置  联系电话
     *
     * @param contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    /**
     *
     * 获取  店铺地址
     *
     * @return  storeAdress
     */
    public String getStoreAdress() {
        return storeAdress;
    }

    /**
     *
     * 设置  店铺地址
     *
     * @param storeAdress
     */
    public void setStoreAdress(String storeAdress) {
        this.storeAdress = storeAdress == null ? null : storeAdress.trim();
    }

    /**
     *
     * 获取  超市id、便利店id、百货商店id
     *
     * @return  typeId
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     *
     * 设置  超市id、便利店id、百货商店id
     *
     * @param typeId
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     *
     * 获取  经营面积
     *
     * @return  size
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     *
     * 设置  经营面积
     *
     * @param size
     */
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    /**
     *
     * 获取  申请日期
     *
     * @return  applicationDate
     */
    public Date getApplicationDate() {
        return applicationDate;
    }

    /**
     *
     * 设置  申请日期
     *
     * @param applicationDate
     */
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

}