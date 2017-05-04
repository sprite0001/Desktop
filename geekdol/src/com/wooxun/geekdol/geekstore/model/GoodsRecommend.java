package com.wooxun.geekdol.geekstore.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.soft863.dolphin.common.CommonEntity;

public class GoodsRecommend extends CommonEntity{
	
	private static final long serialVersionUID = 1L;
    /** 主键id */
    private Long id;

    /** 商品id */
    private Long goodsId;

    /** 标题图片 */
    private String titlePicture;

    /** 副标题 */
    private String subTite;

    /** 显示顺序 */
    private Long ordering;

    /** 优惠价 */
    private BigDecimal preferentialPrice;

    /** 广告语 */
    private String advertisement;

    /** 铺货模块-新产品id */
    private Long distributionId;

    /** 上架时间 */
    private Date startTime;

    /** 下架时间 */
    private Date endTime;

    /** 03:销售中;04:下架 */
    private String status;
    /**推荐小区*/
    private List<GoodsRecommendVillage> goodsRecommendVillages;

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
     * 获取  标题图片
     *
     * @return  titlePicture
     */
    public String getTitlePicture() {
        return titlePicture;
    }

    /**
     *
     * 设置  标题图片
     *
     * @param titlePicture
     */
    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture == null ? null : titlePicture.trim();
    }

    /**
     *
     * 获取  副标题
     *
     * @return  subTite
     */
    public String getSubTite() {
        return subTite;
    }

    /**
     *
     * 设置  副标题
     *
     * @param subTite
     */
    public void setSubTite(String subTite) {
        this.subTite = subTite == null ? null : subTite.trim();
    }

    /**
     *
     * 获取  显示顺序
     *
     * @return  ordering
     */
    public Long getOrdering() {
        return ordering;
    }

    /**
     *
     * 设置  显示顺序
     *
     * @param ordering
     */
    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    /**
     *
     * 获取  优惠价
     *
     * @return  preferentialPrice
     */
    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    /**
     *
     * 设置  优惠价
     *
     * @param preferentialPrice
     */
    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    /**
     *
     * 获取  广告语
     *
     * @return  advertisement
     */
    public String getAdvertisement() {
        return advertisement;
    }

    /**
     *
     * 设置  广告语
     *
     * @param advertisement
     */
    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement == null ? null : advertisement.trim();
    }

    /**
     *
     * 获取  铺货模块-新产品id
     *
     * @return  distributionId
     */
    public Long getDistributionId() {
        return distributionId;
    }

    /**
     *
     * 设置  铺货模块-新产品id
     *
     * @param distributionId
     */
    public void setDistributionId(Long distributionId) {
        this.distributionId = distributionId;
    }

    /**
     *
     * 获取  上架时间
     *
     * @return  startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     *
     * 设置  上架时间
     *
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * 获取  下架时间
     *
     * @return  endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     *
     * 设置  下架时间
     *
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * 获取  03:销售中;04:下架
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  03:销售中;04:下架
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public List<GoodsRecommendVillage> getGoodsRecommendVillages() {
		return goodsRecommendVillages;
	}

	public void setGoodsRecommendVillages(
			List<GoodsRecommendVillage> goodsRecommendVillages) {
		this.goodsRecommendVillages = goodsRecommendVillages;
	}
}