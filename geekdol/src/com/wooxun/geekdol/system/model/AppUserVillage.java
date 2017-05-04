package com.wooxun.geekdol.system.model;

import com.soft863.dolphin.common.CommonEntity;

public class AppUserVillage extends CommonEntity {
    
	private static final long serialVersionUID = 1L;

	/** 省份主键id */
    private Long id;

    /** app用户id */
    private Long userId;

    /** 小区id */
    private Long villageId;

    /** 用户与小区的关系类型:1关注的,2常驻的 */
    private String villageType;
    
    private Village village;
    
    /** 推送id */
    private String pushId;
    
    private String villageName;

    /**
     *
     * 获取  省份主键id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  省份主键id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  app用户id
     *
     * @return  userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *
     * 设置  app用户id
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * 获取  用户与小区的关系类型:1关注的,2常驻的
     *
     * @return  villageType
     */
    public String getVillageType() {
        return villageType;
    }

    /**
     *
     * 设置  用户与小区的关系类型:1关注的,2常驻的
     *
     * @param villageType
     */
    public void setVillageType(String villageType) {
        this.villageType = villageType == null ? null : villageType.trim();
    }

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
}