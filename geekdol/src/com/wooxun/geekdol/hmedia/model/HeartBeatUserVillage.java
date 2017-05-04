package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;

public class HeartBeatUserVillage  extends CommonEntity {

    private static final long serialVersionUID = 1L;
    /** 省份主键id */
    private Long id;

    /** app用户id */
    private Long userId;

    /** 小区id */
    private Long villageId;

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

}