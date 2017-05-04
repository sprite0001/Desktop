package com.wooxun.geekdol.geekstore.model;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.Village;

public class GrouponVillage extends CommonEntity{
    private static final long serialVersionUID = 1L;
    /** 主键id */
    private Long id;

    /** 团购id */
    private Long grouponId;

    /** 小区id,来自小区表 */
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
     * 获取  团购id
     *
     * @return  grouponId
     */
    public Long getGrouponId() {
        return grouponId;
    }

    /**
     *
     * 设置  团购id
     *
     * @param grouponId
     */
    public void setGrouponId(Long grouponId) {
        this.grouponId = grouponId;
    }

    /**
     *
     * 获取  小区id,来自小区表
     *
     * @return  villageId
     */
    public Long getVillageId() {
        return villageId;
    }

    /**
     *
     * 设置  小区id,来自小区表
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