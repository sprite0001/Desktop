package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Village;

public class CommunityPhone extends CommonEntity{
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

    /** 启用禁用 */
    private String status;

    /** 省id */
    private Long provinceId;

    /** 市id */
    private Long cityId;

    /** 区id */
    private Long countryId;

    /** 社区id */
    private Long communityId;

    /** 小区id */
    private Long villageId;
    
    private String villageName;
    
    private Province province;
    
    private City city;
    
    private County county;
    
    private Community community;
    
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

    /**
     *
     * 获取  启用禁用
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  启用禁用
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * 获取  省id
     *
     * @return  provinceId
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     *
     * 设置  省id
     *
     * @param provinceId
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     *
     * 获取  市id
     *
     * @return  cityId
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     *
     * 设置  市id
     *
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     *
     * 获取  区id
     *
     * @return  countryId
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     *
     * 设置  区id
     *
     * @param countryId
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    /**
     *
     * 获取  社区id
     *
     * @return  communityId
     */
    public Long getCommunityId() {
        return communityId;
    }

    /**
     *
     * 设置  社区id
     *
     * @param communityId
     */
    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
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

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
     
    
}