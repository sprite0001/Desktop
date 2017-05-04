package com.wooxun.geekdol.geekstore.model;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.Village;

public class CooperationStoreVillage extends CommonEntity {
    
	private static final long serialVersionUID = 1L;

	/** 主键Id */
    private Long id;

    /** 合作店铺Id */
    private Long storeId;

    /** 小区Id */
    private Long villageId;
    
    private Village village;

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
     * 获取  合作店铺Id
     *
     * @return  storeId
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     *
     * 设置  合作店铺Id
     *
     * @param storeId
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
    
}