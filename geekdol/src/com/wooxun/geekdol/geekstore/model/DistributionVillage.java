package com.wooxun.geekdol.geekstore.model;

import com.soft863.dolphin.common.CommonEntity;

public class DistributionVillage extends CommonEntity{
	
	private static final long serialVersionUID = 1L;
    /** 主键id */
    private Long id;

    /** 小区id */
    private Long villageId;

    /** 铺货id */
    private Long distributionId;

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
     * 获取  铺货id
     *
     * @return  distributionId
     */
    public Long getDistributionId() {
        return distributionId;
    }

    /**
     *
     * 设置  铺货id
     *
     * @param distributionId
     */
    public void setDistributionId(Long distributionId) {
        this.distributionId = distributionId;
    }
}