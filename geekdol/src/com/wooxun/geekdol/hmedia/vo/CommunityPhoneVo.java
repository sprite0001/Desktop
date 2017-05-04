package com.wooxun.geekdol.hmedia.vo;

import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月30日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月30日  上午11:06:22 		创建
 *==========================================================
 * 
 */
public class CommunityPhoneVo extends BaseVo{
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
    private Long countyId;

    /** 社区id */
    private Long communityId;

    /** 小区id */
    private Long villageId;
    
    private String provinceName;
    
    private String cityName;
    
    private String countyName;
    
    private String communityName;
    
    private String villageName;
    
    private Province province;
    
    private City city;
    
    private County county;
    
    private Community community;
    
    private long userId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getStatus() {
        return status;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

   
    public Long getCommunityId() {
        return communityId;
    }

    public Long getVillageId() {
        return villageId;
    }

    public Province getProvince() {
        return province;
    }

    public City getCity() {
        return city;
    }

    public County getCounty() {
        return county;
    }

    public Community getCommunity() {
        return community;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
 

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    
}
