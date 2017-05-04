package com.wooxun.geekdol.hmedia.vo;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月29日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月29日  下午4:23:20 		创建
 *==========================================================
 * 
 */
public class CommonPhoneVo extends BaseVo{

    /** 主键id */
    private Long id;

    /** 电话名称 */
    private String name;

    /** 电话号码 */
    private String phoneNumber;

    /** 社区电话 服务电话 */
    private String phoneType;

    /** 排序序号 */
    private Integer ordering;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
    
}
