package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.Village;

public class AdvertVillage extends CommonEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 广告id */
    private Long advertId;

    /** 小区id */
    private Long villageId;
    
    /** 小区 */

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
     * 获取  广告id
     *
     * @return  advertId
     */
    public Long getAdvertId() {
        return advertId;
    }

    /**
     *
     * 设置  广告id
     *
     * @param advertId
     */
    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
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