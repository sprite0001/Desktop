package com.wooxun.geekdol.system.model;

import com.soft863.dolphin.common.CommonEntity;

public class City extends CommonEntity{

	private static final long serialVersionUID = 1L;

    /** 市主键id */
    private Long cityId;

    /** 省份主键id */
    private Long provinceId;

    /** 市名称 */
    private String cityName;

    /** 市编码 */
    private String cityCode;

    /** 推荐显示顺序 */
    private Long ordering;

    /** 状态:禁用;启用 */
    private String status;
    
    
    /**自定义属性**/
    private Province province;
   
    public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	/**
     *
     * 获取  市主键id
     *
     * @return  cityId
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     *
     * 设置  市主键id
     *
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     *
     * 获取  省份主键id
     *
     * @return  provinceId
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     *
     * 设置  省份主键id
     *
     * @param provinceId
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     *
     * 获取  市名称
     *
     * @return  cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * 设置  市名称
     *
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     *
     * 获取  市编码
     *
     * @return  cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     *
     * 设置  市编码
     *
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     *
     * 获取  推荐显示顺序
     *
     * @return  ordering
     */
    public Long getOrdering() {
        return ordering;
    }

    /**
     *
     * 设置  推荐显示顺序
     *
     * @param ordering
     */
    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    /**
     *
     * 获取  状态:禁用;启用
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  状态:禁用;启用
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
   
}