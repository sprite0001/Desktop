package com.wooxun.geekdol.geekstore.model;

import java.math.BigDecimal;

import com.soft863.dolphin.common.CommonEntity;

public class DistributionGoods extends CommonEntity{
    
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;

    /** 铺货商品 */
    private Long distributionId;

    /** 商品分类id */
    private Long goodsClassificId;

    /** 商品id */
    private Long goodsId;

    /** 销售价格 */
    private BigDecimal sellPrice;

    /** 0:上架;1:下架 */
    private String status;
    
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

    /**
     *
     * 获取  0:上架;1:下架
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:上架;1:下架
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}