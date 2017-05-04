package com.wooxun.geekdol.system.vo;

import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Province;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. QZG 2016年7月18日 下午1:16:10 创建
 *           ==========================================================
 * 
 */
public class CountyQueryVo extends BaseVo {
    /** 省份名称 */
    private Long provinceId;
    /** 区id */
    private Long countyId;
    /** 省名称 */
    private String provinceName;
    /** 市id */
    private Long cityId;
    /** 市名称 */
    private String cityName;
    /** 行政区名称 */
    private String countyName;
    /** 排序 */
    private Long ordering;
    /** 市编码 */
    private String countyCode;
    /** 市状态 */
    private String status;
    /** 是否开启推荐功能 */
    private String recommendFlag;
    /** 省 */
    private Province province;
    /** 市 */
    private City city;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(String recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
