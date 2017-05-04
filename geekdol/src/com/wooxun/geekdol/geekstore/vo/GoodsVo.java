package com.wooxun.geekdol.geekstore.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class GoodsVo extends BaseVo{
    /** 商品Id */
    private Long id;

    /** 商品名 */
    private String goodsName;

    /** 商品编号 */
    private String goodsCode;

    /** 短标题 */
    private String shortTopic;

    /** 品牌 */
    private String brand;

    /** 成本价 */
    private BigDecimal costPrice;

    /** 原始价格 */
    private BigDecimal originalPrice;

    /** 产品型号 */
    private String modelNumber;

    /** 上市日期 */
    private Date launchDate;
    /** 上市日期 */
    private String launchDateStr;

    /** 缩略图 */
    private String thumbnail;

    /** 供应商(卖家)自营、第三方 */
    private String seller;

    /** 商品浏览量 */
    private Integer goodsView;

    /** 0:禁用;1:启用 */
    private String status;
    /*状态名称*/
    private String statusStr;
    /**状态在数据字典Type类型*/
    private String statusType;

    /** 商品详情 */
    private String details;
    /*登记日期*/
    private Date insYmdhms;
    /**选择要铺货的商品的状态:02：已发布;03：销售中;04：已下架*/
    private String status_02;
    private String status_03;
    private String status_04;
    /**展示图*/
    private String picOne;
    private String picTow;
    private String picThree;

    /**
     *
     * 获取  商品Id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  商品Id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  商品名
     *
     * @return  goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     *
     * 设置  商品名
     *
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     *
     * 获取  商品编号
     *
     * @return  goodsCode
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     *
     * 设置  商品编号
     *
     * @param goodsCode
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     *
     * 获取  短标题
     *
     * @return  shortTopic
     */
    public String getShortTopic() {
        return shortTopic;
    }

    /**
     *
     * 设置  短标题
     *
     * @param shortTopic
     */
    public void setShortTopic(String shortTopic) {
        this.shortTopic = shortTopic == null ? null : shortTopic.trim();
    }

    /**
     *
     * 获取  品牌
     *
     * @return  brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * 设置  品牌
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
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
     * 获取  
     *
     * @return  originalPrice
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     *
     * 设置  
     *
     * @param originalPrice
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     *
     * 获取  产品型号
     *
     * @return  modelNumber
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     *
     * 设置  产品型号
     *
     * @param modelNumber
     */
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber == null ? null : modelNumber.trim();
    }

    /**
     *
     * 获取  上市日期
     *
     * @return  launchDate
     */
    public Date getLaunchDate() {
        return launchDate;
    }

    /**
     *
     * 设置  上市日期
     *
     * @param launchDate
     */
    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    /**
     *
     * 获取  缩略图
     *
     * @return  thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * 设置  缩略图
     *
     * @param thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     *
     * 获取  供应商(卖家)自营、第三方
     *
     * @return  seller
     */
    public String getSeller() {
        return seller;
    }

    /**
     *
     * 设置  供应商(卖家)自营、第三方
     *
     * @param seller
     */
    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    /**
     *
     * 获取  商品浏览量
     *
     * @return  goodsView
     */
    public Integer getGoodsView() {
        return goodsView;
    }

    /**
     *
     * 设置  商品浏览量
     *
     * @param goodsView
     */
    public void setGoodsView(Integer goodsView) {
        this.goodsView = goodsView;
    }

    /**
     *
     * 获取  0:禁用;1:启用
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:禁用;1:启用
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    
    /**
     *
     * 获取  商品详情
     *
     * @return  details
     */
    public String getDetails() {
        return details;
    }

    /**
     *
     * 设置  商品详情
     *
     * @param details
     */
    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Date getInsYmdhms() {
		return insYmdhms;
	}

	public void setInsYmdhms(Date insYmdhms) {
		this.insYmdhms = insYmdhms;
	}

	public String getLaunchDateStr() {
		return launchDateStr;
	}

	public void setLaunchDateStr(String launchDateStr) {
		this.launchDateStr = launchDateStr;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getStatus_02() {
		return status_02;
	}

	public void setStatus_02(String status_02) {
		this.status_02 = status_02;
	}

	public String getStatus_03() {
		return status_03;
	}

	public void setStatus_03(String status_03) {
		this.status_03 = status_03;
	}

	public String getStatus_04() {
		return status_04;
	}

	public void setStatus_04(String status_04) {
		this.status_04 = status_04;
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