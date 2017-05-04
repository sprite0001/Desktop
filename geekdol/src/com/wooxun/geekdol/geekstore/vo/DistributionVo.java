package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;
import java.util.List;

import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.model.DistributionVillage;
import com.wooxun.geekdol.system.vo.BaseVo;

public class DistributionVo extends BaseVo{

    /** 铺货id */
    private Long id;

    /** 铺货模块id */
    private Long classificId;

    /** 上架时间 */
    private Date startTime;

    /** 下架时间 */
    private Date endTime;

    /** 0:下架;1:下架;2:发布 */
    private String status;
    
    /**铺货商品*/
    private List<DistributionVillage> distributionVillages;
    
    /**铺货商品小区*/
    private List<DistributionGoods> distributionGoods;
   

    /**
     *
     * 获取  铺货id
     *
     * @return  id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * 设置  铺货id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 获取  铺货模块id
     *
     * @return  classificId
     */
    public Long getClassificId() {
        return classificId;
    }

    /**
     *
     * 设置  铺货模块id
     *
     * @param classificId
     */
    public void setClassificId(Long classificId) {
        this.classificId = classificId;
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
     * 获取  0:下架;1:下架;2:发布
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:下架;1:下架;2:发布
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public List<DistributionVillage> getDistributionVillages() {
		return distributionVillages;
	}

	public void setDistributionVillages(
			List<DistributionVillage> distributionVillages) {
		this.distributionVillages = distributionVillages;
	}

	public List<DistributionGoods> getDistributionGoods() {
		return distributionGoods;
	}

	public void setDistributionGoods(List<DistributionGoods> distributionGoods) {
		this.distributionGoods = distributionGoods;
	}
    
}