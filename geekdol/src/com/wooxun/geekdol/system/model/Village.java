package com.wooxun.geekdol.system.model;

import com.soft863.dolphin.common.CommonEntity;

public class Village extends CommonEntity{
   
    private static final long serialVersionUID = 1L;

    /** 小区主键id */
    private Long villageId;

    /** 关联省主键 */
    private Long provinceId;

    /** 市主键id */
    private Long cityId;

    /** 区主键id */
    private Long countyId;

    /** 社区主键id */
    private Long communityId;

    /** 户数 */
    private Long houseHolds;

    /** 人口 */
    private Long population;

    /** 建成年份 */
    private String buildYear;

    /** 均价 */
    private Long averagePrice;

    /** 小区名称 */
    private String villageName;

    /** 小区地址 */
    private String villageAddress;

    /** 小区中心点经度 */
    private String villageLongitude;

    /** 小区中心点纬度 */
    private String villageLatitude;

    /** 推荐显示顺序 */
    private Long ordering;

    /** 状态:禁用;启用 */
    private String status;
    
    
    
    
    //关联属性
    private Province province;
    
    private City city;
    
    private County county;
    
    private Community community;
    
    //是否开店
    private String  kaidian;
    
    // 合作店铺名称
    private String storeName;
    
    /**
     *
     * 获取  小区主键id
     *
     * @return  villageId
     */
    public Long getVillageId() {
        return villageId;
    }

    /**
     *
     * 设置  小区主键id
     *
     * @param villageId
     */
    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    /**
     *
     * 获取  关联省主键
     *
     * @return  provinceId
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     *
     * 设置  关联省主键
     *
     * @param provinceId
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
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
     * 获取  区主键id
     *
     * @return  countyId
     */
    public Long getCountyId() {
        return countyId;
    }

    /**
     *
     * 设置  区主键id
     *
     * @param countyId
     */
    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    /**
     *
     * 获取  社区主键id
     *
     * @return  communityId
     */
    public Long getCommunityId() {
        return communityId;
    }

    /**
     *
     * 设置  社区主键id
     *
     * @param communityId
     */
    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    /**
     *
     * 获取  户数
     *
     * @return  houseHolds
     */
    public Long getHouseHolds() {
        return houseHolds;
    }

    /**
     *
     * 设置  户数
     *
     * @param houseHolds
     */
    public void setHouseHolds(Long houseHolds) {
        this.houseHolds = houseHolds;
    }

    /**
     *
     * 获取  人口
     *
     * @return  population
     */
    public Long getPopulation() {
        return population;
    }

    /**
     *
     * 设置  人口
     *
     * @param population
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    /**
     *
     * 获取  建成年份
     *
     * @return  buildYear
     */
    public String getBuildYear() {
        return buildYear;
    }

    /**
     *
     * 设置  建成年份
     *
     * @param buildYear
     */
    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear == null ? null : buildYear.trim();
    }

    /**
     *
     * 获取  均价
     *
     * @return  averagePrice
     */
    public Long getAveragePrice() {
        return averagePrice;
    }

    /**
     *
     * 设置  均价
     *
     * @param averagePrice
     */
    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }

    /**
     *
     * 获取  小区名称
     *
     * @return  villageName
     */
    public String getVillageName() {
        return villageName;
    }

    /**
     *
     * 设置  小区名称
     *
     * @param villageName
     */
    public void setVillageName(String villageName) {
        this.villageName = villageName == null ? null : villageName.trim();
    }

    /**
     *
     * 获取  小区地址
     *
     * @return  villageAddress
     */
    public String getVillageAddress() {
        return villageAddress;
    }

    /**
     *
     * 设置  小区地址
     *
     * @param villageAddress
     */
    public void setVillageAddress(String villageAddress) {
        this.villageAddress = villageAddress == null ? null : villageAddress.trim();
    }

    /**
     *
     * 获取  小区中心点经度
     *
     * @return  villageLongitude
     */
    public String getVillageLongitude() {
        return villageLongitude;
    }

    /**
     *
     * 设置  小区中心点经度
     *
     * @param villageLongitude
     */
    public void setVillageLongitude(String villageLongitude) {
        this.villageLongitude = villageLongitude == null ? null : villageLongitude.trim();
    }

    /**
     *
     * 获取  小区中心点纬度
     *
     * @return  villageLatitude
     */
    public String getVillageLatitude() {
        return villageLatitude;
    }

    /**
     *
     * 设置  小区中心点纬度
     *
     * @param villageLatitude
     */
    public void setVillageLatitude(String villageLatitude) {
        this.villageLatitude = villageLatitude == null ? null : villageLatitude.trim();
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

	public String getKaidian() {
		return kaidian;
	}

	public void setKaidian(String kaidian) {
		this.kaidian = kaidian;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}