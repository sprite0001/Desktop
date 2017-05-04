package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;

public class CommonPhone extends CommonEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

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
     * 获取  电话名称
     *
     * @return  name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * 设置  电话名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *
     * 获取  电话号码
     *
     * @return  phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * 设置  电话号码
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     *
     * 获取  社区电话 服务电话
     *
     * @return  phoneType
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     *
     * 设置  社区电话 服务电话
     *
     * @param phoneType
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }

    /**
     *
     * 获取  排序序号
     *
     * @return  ordering
     */
    public Integer getOrdering() {
        return ordering;
    }

    /**
     *
     * 设置  排序序号
     *
     * @param ordering
     */
    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}