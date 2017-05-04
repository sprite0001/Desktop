package com.wooxun.geekdol.hmedia.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class ActivityBaomingVo extends BaseVo {
	/** 主键id */
	private Long id;

	/** 报名人id */
	private Long userId;

	/** 报名人ip */
	private String ip;

	/** 报名时间 */
	private Date time;

	/** 备注 */
	private String remark;

	/** 活动id */
	private Long activityCollectionId;
	
	/** 报名人姓名 */
	private String name;
	
	/** 报名人手机号  */
	private String phone;
    
	private Long villageId;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getActivityCollectionId() {
		return activityCollectionId;
	}

	public void setActivityCollectionId(Long activityCollectionId) {
		this.activityCollectionId = activityCollectionId;
	}

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }
	
}