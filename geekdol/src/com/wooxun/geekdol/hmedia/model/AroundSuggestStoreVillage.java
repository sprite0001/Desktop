package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.Village;

public class AroundSuggestStoreVillage extends CommonEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 主键id */
    private Long id;

    /** 推荐周边店id */
    private Long aroundSuggestStoreId;

    /** 小区id */
    private Long villageId;
    
    private Village village;

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
     * 获取  推荐周边店id
     *
     * @return  aroundSuggestStoreId
     */
    public Long getAroundSuggestStoreId() {
        return aroundSuggestStoreId;
    }

    /**
     *
     * 设置  推荐周边店id
     *
     * @param aroundSuggestStoreId
     */
    public void setAroundSuggestStoreId(Long aroundSuggestStoreId) {
        this.aroundSuggestStoreId = aroundSuggestStoreId;
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
    
    public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
}