package com.wooxun.geekdol.geekstore.vo;


import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class VillageLikeRecordVo extends BaseVo{
    
	/** 主键id */
    private Long id;

    /** 小区id */
    private Long villageId;      

    /** 手机号码 */
    private String phone;

    /** 内容 */
    private String content;

    /** 点赞时间 */
    private Date likingDate;
    
    /**自定义gegin(小区名称)*/
    private String villageName;
    /**用户区域权限*/
    private String areaLevel;
    /**当前用户id*/
    private Long currentUserId;
    
    
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

	/**
     * 获取小区名称
     * 
     * */
    public String getVillageName() {
  		return villageName;
  	}

  	public void setVillageName(String villageName) {
  		this.villageName = villageName;
  	}
  	/**自定义end*/

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
     * 获取  手机号码
     *
     * @return  phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * 设置  手机号码
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *
     * 获取  内容
     *
     * @return  content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * 设置  内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *
     * 获取  点赞时间
     *
     * @return  likingDate
     */
    public Date getLikingDate() {
        return likingDate;
    }

    /**
     *
     * 设置  点赞时间
     *
     * @param likingDate
     */
    public void setLikingDate(Date likingDate) {
        this.likingDate = likingDate;
    }
}