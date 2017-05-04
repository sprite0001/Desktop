package com.wooxun.geekdol.geekstore.vo;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wooxun.geekdol.geekstore.model.GrouponGoodsExtendInfo;
import com.wooxun.geekdol.system.vo.BaseVo;

public class GrouponGoodsVo extends BaseVo{
	/** 主键Id */
    private Long id;

    /** 团购编号 */
    private String serialCode;

    /** 团购分类 */
    private Integer grouponClassific;

    /** 团购名称 */
    private String grouponName;

    /** 副标题 */
    private String subtitle;

    /** 团购数量 */
    private Integer maxQuantity;

    /** 最低数量 */
    private Integer minQuantity;

    /** 团购价格-直销价格 */
    private BigDecimal grouponPrice;

    /** 处理模式为合作店的时候价格 */
    private BigDecimal storePrice;

    /** 成本价 */
    private BigDecimal costPrice;

    /** 直销、合作店、直销+合作店 */
    private String detailModel;

    /** 团购起始日期 */
    private Date startTime;
    
    private String startTimeStr;

    /** 团购结束日期 */
    private Date endTime;
    
    private String endTimeStr;

    /** 供应商(卖家)自营、第三方 */
    private Long seller;

    /** 状态:生效、结束、未开始 */
    private String status;
    /**数据字典中订单状态Type类型*/
    private String statusType;
    
    private String statusStr;
    
    /** 审核-拒绝缘由 */
    private String rejectReason;
    
    /** 浏览量 */
    private Integer grouponView;
    
    /** 一星 */
    private Integer oneStar;

    /** 两星 */
    private Integer towStar;

    /** 三星 */
    private Integer threeStar;

    /** 四星 */
    private Integer fourStar;

    /** 五星 */
    private Integer fiveStar;

    /** 详情 */
    private String details;
    
    private String url;
    /**总评价数量*/
    private Integer countStar;
    /**好评数*/
    private Integer goodStar;
    /**中评数量*/
    private Integer commonStar;
    /**差评数量*/
    private Integer badStar;
    /**小区id*/
    private String villageId;
    /**扩展属性*/
    private List<GrouponGoodsExtendInfo> grouponGoodsExtendInfos;
    /**展示图*/
    private String picOne;
    private String picTow;
    private String picThree;
    
    /**
     *
     * 获取  主键Id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  主键Id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  团购编号
     *
     * @return  serialCode
     */
    public String getSerialCode() {
        return serialCode;
    }

    /**
     *
     * 设置  团购编号
     *
     * @param serialCode
     */
    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode == null ? null : serialCode.trim();
    }

    /**
     *
     * 获取  团购分类
     *
     * @return  grouponClassific
     */
    public Integer getGrouponClassific() {
        return grouponClassific;
    }

    /**
     *
     * 设置  团购分类
     *
     * @param grouponClassific
     */
    public void setGrouponClassific(Integer grouponClassific) {
        this.grouponClassific = grouponClassific;
    }

    /**
     *
     * 获取  团购名称
     *
     * @return  grouponName
     */
    public String getGrouponName() {
        return grouponName;
    }

    /**
     *
     * 设置  团购名称
     *
     * @param grouponName
     */
    public void setGrouponName(String grouponName) {
        this.grouponName = grouponName == null ? null : grouponName.trim();
    }

    /**
     *
     * 获取  副标题
     *
     * @return  subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     *
     * 设置  副标题
     *
     * @param subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    /**
     *
     * 获取  团购数量
     *
     * @return  maxQuantity
     */
    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    /**
     *
     * 设置  团购数量
     *
     * @param maxQuantity
     */
    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    /**
     *
     * 获取  最低数量
     *
     * @return  minQuantity
     */
    public Integer getMinQuantity() {
        return minQuantity;
    }

    /**
     *
     * 设置  最低数量
     *
     * @param minQuantity
     */
    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    /**
     *
     * 获取  团购价格
     *
     * @return  grouponPrice
     */
    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    /**
     *
     * 设置  团购价格
     *
     * @param grouponPrice
     */
    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    /**
     *
     * 获取  成本价
     *
     * @return  costPrice
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     *
     * 设置  成本价
     *
     * @param costPrice
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     *
     * 获取  团购起始日期
     *
     * @return  startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     *
     * 设置  团购起始日期
     *
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * 获取  团购结束日期
     *
     * @return  endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     *
     * 设置  团购结束日期
     *
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * 获取  状态:生效、结束、未开始
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  状态:生效、结束、未开始
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    /**
    *
    * 获取  审核-拒绝缘由
    *
    * @return  rejectReason
    */
   public String getRejectReason() {
       return rejectReason;
   }

   /**
    *
    * 设置  审核-拒绝缘由
    *
    * @param rejectReason
    */
   public void setRejectReason(String rejectReason) {
       this.rejectReason = rejectReason == null ? null : rejectReason.trim();
   }
    
    /**
    *
    * 获取  浏览量
    *
    * @return  grouponView
    */
   public Integer getGrouponView() {
       return grouponView;
   }

   /**
    *
    * 设置  浏览量
    *
    * @param grouponView
    */
   public void setGrouponView(Integer grouponView) {
       this.grouponView = grouponView;
   }
    
    /**
    *
    * 获取  一星
    *
    * @return  oneStar
    */
   public Integer getOneStar() {
       return oneStar;
   }

   /**
    *
    * 设置  一星
    *
    * @param oneStar
    */
   public void setOneStar(Integer oneStar) {
       this.oneStar = oneStar;
   }

   /**
    *
    * 获取  两星
    *
    * @return  towStar
    */
   public Integer getTowStar() {
       return towStar;
   }

   /**
    *
    * 设置  两星
    *
    * @param towStar
    */
   public void setTowStar(Integer towStar) {
       this.towStar = towStar;
   }

   /**
    *
    * 获取  三星
    *
    * @return  threeStar
    */
   public Integer getThreeStar() {
       return threeStar;
   }

   /**
    *
    * 设置  三星
    *
    * @param threeStar
    */
   public void setThreeStar(Integer threeStar) {
       this.threeStar = threeStar;
   }

   /**
    *
    * 获取  四星
    *
    * @return  fourStar
    */
   public Integer getFourStar() {
       return fourStar;
   }

   /**
    *
    * 设置  四星
    *
    * @param fourStar
    */
   public void setFourStar(Integer fourStar) {
       this.fourStar = fourStar;
   }

   /**
    *
    * 获取  五星
    *
    * @return  fiveStar
    */
   public Integer getFiveStar() {
       return fiveStar;
   }

   /**
    *
    * 设置  五星
    *
    * @param fiveStar
    */
   public void setFiveStar(Integer fiveStar) {
       this.fiveStar = fiveStar;
   }
    
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(BigDecimal storePrice) {
		this.storePrice = storePrice;
	}

	public String getDetailModel() {
		return detailModel;
	}

	public void setDetailModel(String detailModel) {
		this.detailModel = detailModel;
	}

	public Long getSeller() {
		return seller;
	}

	public void setSeller(Long seller) {
		this.seller = seller;
	}

	public Integer getCountStar() {
		return countStar;
	}

	public void setCountStar(Integer countStar) {
		this.countStar = countStar;
	}

	public Integer getGoodStar() {
		return goodStar;
	}

	public void setGoodStar(Integer goodStar) {
		this.goodStar = goodStar;
	}

	public Integer getCommonStar() {
		return commonStar;
	}

	public void setCommonStar(Integer commonStar) {
		this.commonStar = commonStar;
	}

	public Integer getBadStar() {
		return badStar;
	}

	public void setBadStar(Integer badStar) {
		this.badStar = badStar;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public List<GrouponGoodsExtendInfo> getGrouponGoodsExtendInfos() {
		return grouponGoodsExtendInfos;
	}

	public void setGrouponGoodsExtendInfos(
			List<GrouponGoodsExtendInfo> grouponGoodsExtendInfos) {
		this.grouponGoodsExtendInfos = grouponGoodsExtendInfos;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getPicOne() {
		return picOne;
	}

	public void setPicOne(String picOne) {
		this.picOne = picOne;
	}

	public String getPicTow() {
		return picTow;
	}

	public void setPicTow(String picTow) {
		this.picTow = picTow;
	}

	public String getPicThree() {
		return picThree;
	}

	public void setPicThree(String picThree) {
		this.picThree = picThree;
	}
}