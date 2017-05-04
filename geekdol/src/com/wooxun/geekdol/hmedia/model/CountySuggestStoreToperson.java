package com.wooxun.geekdol.hmedia.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.User;

/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月2日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 Administrator	2016年8月2日  上午11:27:41 		创建
*==========================================================
*
 */
public class CountySuggestStoreToperson extends CommonEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    private Long id;

    /** 关联键 */
    private Long countySuggestStoreId;

    /** 处理状态:1.待处理,2.接受,3.拒绝 */
    private String handleStatus;

    /** 处理人id */
    private Long handlePersonId;

    /** 处理时间 */
    private Date handleDate;

    /** 推荐人id */
    private Long sugPersonId;

    /** 被推荐人id */
    private Long sugToPersonId;

    /** 是否已经推荐：未推荐，已推荐 */
    private String suggestFlag;
    
    /** 推荐时间 */
    private Date sugDate;
    
    private User user;

    /**
     *
     * 获取  主键
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  主键
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  关联键
     *
     * @return  countySuggestStoreId
     */
    public Long getCountySuggestStoreId() {
        return countySuggestStoreId;
    }

    /**
     *
     * 设置  关联键
     *
     * @param countySuggestStoreId
     */
    public void setCountySuggestStoreId(Long countySuggestStoreId) {
        this.countySuggestStoreId = countySuggestStoreId;
    }

    /**
     *
     * 获取  处理状态:1.待处理,2.接受,3.拒绝
     *
     * @return  handleStatus
     */
    public String getHandleStatus() {
        return handleStatus;
    }

    /**
     *
     * 设置  处理状态:1.待处理,2.接受,3.拒绝
     *
     * @param handleStatus
     */
    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus == null ? null : handleStatus.trim();
    }

    /**
     *
     * 获取  处理人id
     *
     * @return  handlePersonId
     */
    public Long getHandlePersonId() {
        return handlePersonId;
    }

    /**
     *
     * 设置  处理人id
     *
     * @param handlePersonId
     */
    public void setHandlePersonId(Long handlePersonId) {
        this.handlePersonId = handlePersonId;
    }

    /**
     *
     * 获取  处理时间
     *
     * @return  handleDate
     */
    public Date getHandleDate() {
        return handleDate;
    }

    /**
     *
     * 设置  处理时间
     *
     * @param handleDate
     */
    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    /**
     *
     * 获取  推荐人id
     *
     * @return  sugPersonId
     */
    public Long getSugPersonId() {
        return sugPersonId;
    }

    /**
     *
     * 设置  推荐人id
     *
     * @param sugPersonId
     */
    public void setSugPersonId(Long sugPersonId) {
        this.sugPersonId = sugPersonId;
    }

    /**
     *
     * 获取  被推荐人id
     *
     * @return  sugToPersonId
     */
    public Long getSugToPersonId() {
        return sugToPersonId;
    }

    /**
     *
     * 设置  被推荐人id
     *
     * @param sugToPersonId
     */
    public void setSugToPersonId(Long sugToPersonId) {
        this.sugToPersonId = sugToPersonId;
    }

    /**
     *
     * 获取  是否已经推荐：未推荐，已推荐
     *
     * @return  suggestFlag
     */
    public String getSuggestFlag() {
        return suggestFlag;
    }

    /**
     *
     * 设置  是否已经推荐：未推荐，已推荐
     *
     * @param suggestFlag
     */
    public void setSuggestFlag(String suggestFlag) {
        this.suggestFlag = suggestFlag == null ? null : suggestFlag.trim();
    }
    
    public Date getSugDate() {
		return sugDate;
	}

	public void setSugDate(Date sugDate) {
		this.sugDate = sugDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}