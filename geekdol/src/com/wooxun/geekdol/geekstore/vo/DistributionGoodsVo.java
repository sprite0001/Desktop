package com.wooxun.geekdol.geekstore.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class DistributionGoodsVo extends BaseVo{
    
    /** 主键 */
    private Long id;

    /** 铺货商品 */
    private Long distributionId;

    /** 商品分类id */
    private Long goodsClassificId;
    /** 商品分类CODE */
    private String classificCode;

    /** 商品id */
    private Long goodsId;
    /**商品编号*/
    private String goodsCode;
    /**商品名称*/
    private String goodsName;

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

    /** 销售价格 */
    private BigDecimal sellPrice;
    
    /**分类id*/
    private Long classificId;
    
    /** 03:上架;04:下架 */
    private String status;
    
    private String statusStr;
    /**type类型在字典表中的type*/
    private String statusType;
    
    private Date startTime;
    
    private Date endTime;

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
     * 获取  铺货商品
     *
     * @return  distributionId
     */
    public Long getDistributionId() {
        return distributionId;
    }

    /**
     *
     * 设置  铺货商品
     *
     * @param distributionId
     */
    public void setDistributionId(Long distributionId) {
        this.distributionId = distributionId;
    }

    /**
     *
     * 获取  商品分类id
     *
     * @return  goodsClassificId
     */
    public Long getGoodsClassificId() {
        return goodsClassificId;
    }

    /**
     *
     * 设置  商品分类id
     *
     * @param goodsClassificId
     */
    public void setGoodsClassificId(Long goodsClassificId) {
        this.goodsClassificId = goodsClassificId;
    }

    /**
     *
     * 获取  商品id
     *
     * @return  goodsId
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     *
     * 设置  商品id
     *
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     *
     * 获取  销售价格
     *
     * @return  sellPrice
     */
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    /**
     *
     * 设置  销售价格
     *
     * @param sellPrice
     */
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Long getClassificId() {
		return classificId;
	}

	public void setClassificId(Long classificId) {
		this.classificId = classificId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getClassificCode() {
		return classificCode;
	}

	public void setClassificCode(String classificCode) {
		this.classificCode = classificCode;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}