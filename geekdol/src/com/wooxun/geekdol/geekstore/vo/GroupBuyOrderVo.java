package com.wooxun.geekdol.geekstore.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title 吉客店管理-团购管理-团购订单管理
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月4日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月4日  下午2:18:35 		创建
 *==========================================================
 * 
 */
public class GroupBuyOrderVo extends BaseVo{
	/** 主键 */
    private Long id;

    /** 订单编号 */
    private String code;

    /** 团购商品、服务id，来自团购主键 */
    private Long goodsId;

    /** 买方 */
    private Long buyerId;

    /** 卖方 */
    private Long sellerId;

    /** 订单时间 */
    private Date orderTime;

    /** 小区id */
    private Long villageId;

    /** 支付流水号 */
    private Long paymentRecordId;

    /** 付款金额--成交价 */
    private BigDecimal paymentAmount;

    /** 实付金额,用户支付后返回 */
    private BigDecimal actualPayment;

    /** 0:线下;1:线上 */
    private String payMode;

    /** 支付时间 */
    private String payTime;

    /** 0:未付款;1:待发布;2:退货申请中;3:已退货;4:交易完成;5:关闭 */
    private String status;

    /** 收货人名称 */
    private String receiverName;

    /** 收货人电话 */
    private String receiverPhone;

    /** 收货人省份 */
    private Long receiverProvince;

    /** 收货人城市 */
    private Long reciverCity;

    /** 收货人区域 */
    private Long reciverCountry;

    /** 收货人小区 */
    private String receiverVillage;

    /** 收货人地址 */
    private String receiverAdress;

    /** 配送方式:自取、物流名称 */
    private String deliveryMode;

    /** 物流、快递名称 */
    private String logisticsNumber;

    /** 商家电话 */
    private String phone;

    /** 物流运费 */
    private BigDecimal freight;
    
    /** 订单关闭理由 */
    private String orderCloseReasons;

    /** 评定时间 */
    private Date insYmdhms;

    /** 录入用户 */
    private Long insId;

    /** 更新用户 */
    private Long updId;

    /** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 删除标记 */
    private String delFlag;
    
    
    /**自定义*/
    /** 团购编号 */
    private String serialCode;
    /** 团购分类 */
    private String grouponClassific;
    /** 处理模式为价格 */
    private BigDecimal price;
    /**订单状态*/
    private String orderStatus;
    /** 退款金额 */
    private BigDecimal returnMoney;

    /** 退款金额备注 */
    private String returnMoneyMark;
    
    /** 处理结果 */
    private String detailResult;    

	/** 退货地址 */
    private String returnAdress;

    /** 处理结果理由 */
    private String detailResultReason;
    /** 前台退货理由 */
    private String orderReturnReason;
    /**买家姓名*/
   private String buyerName;
   /**卖家姓名*/
   private String sellerName;
   /**所属小区*/
   private String villageName;
   /**团购码*/
   private String purchaseCode;
   /**用户区域权限*/
   private String areaLevel;
   /**当前用户id*/
   private Long currentUserId;
   /**字典表：订单状态类型*/
   private String orderStatusType;
   /**字典表:支付方式类型*/
   private String payModel;
   /**字典表：物流类型*/
   private String express;
   /**字典表：物流类型的ID集合*/
   private List<String> deliveryModeList;
   
   

	public String getPurchaseCode() {
	return purchaseCode;
}

public void setPurchaseCode(String purchaseCode) {
	this.purchaseCode = purchaseCode;
}

	public List<String> getDeliveryModeList() {
	return deliveryModeList;
}

public void setDeliveryModeList(List<String> deliveryModeList) {
	this.deliveryModeList = deliveryModeList;
}

	public String getExpress() {
	return express;
}

public void setExpress(String express) {
	this.express = express;
}

	public String getPayModel() {
	return payModel;
}

public void setPayModel(String payModel) {
	this.payModel = payModel;
}

	public String getOrderStatusType() {
	return orderStatusType;
}

public void setOrderStatusType(String orderStatusType) {
	this.orderStatusType = orderStatusType;
}

	public String getAreaLevel() {
	return areaLevel;
}

public void setAreaLevel(String areaLevel) {
	this.areaLevel = areaLevel;
}

public Long getCurrentUserId() {
	return currentUserId;
}

public void setCurrentUserId(Long currentUserId) {
	this.currentUserId = currentUserId;
}

	public String getVillageName() {
	return villageName;
}

public void setVillageName(String villageName) {
	this.villageName = villageName;
}

	public String getBuyerName() {
	return buyerName;
}

public void setBuyerName(String buyerName) {
	this.buyerName = buyerName;
}

public String getSellerName() {
	return sellerName;
}

public void setSellerName(String sellerName) {
	this.sellerName = sellerName;
}

	public String getOrderReturnReason() {
		return orderReturnReason;
	}

	public void setOrderReturnReason(String orderReturnReason) {
		this.orderReturnReason = orderReturnReason;
	}

	public String getDetailResult() {
		return detailResult;
	}

	public void setDetailResult(String detailResult) {
		this.detailResult = detailResult;
	}

	public String getReturnAdress() {
		return returnAdress;
	}

	public void setReturnAdress(String returnAdress) {
		this.returnAdress = returnAdress;
	}

	public String getDetailResultReason() {
		return detailResultReason;
	}

	public void setDetailResultReason(String detailResultReason) {
		this.detailResultReason = detailResultReason;
	}

	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}

	public String getReturnMoneyMark() {
		return returnMoneyMark;
	}

	public void setReturnMoneyMark(String returnMoneyMark) {
		this.returnMoneyMark = returnMoneyMark;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getGrouponClassific() {
		return grouponClassific;
	}

	public void setGrouponClassific(String grouponClassific) {
		this.grouponClassific = grouponClassific;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

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
     * 获取  订单编号
     *
     * @return  code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * 设置  订单编号
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     *
     * 获取  团购商品、服务id，来自团购主键
     *
     * @return  goodsId
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     *
     * 设置  团购商品、服务id，来自团购主键
     *
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     *
     * 获取  买方
     *
     * @return  buyerId
     */
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     *
     * 设置  买方
     *
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    /**
     *
     * 获取  卖方
     *
     * @return  sellerId
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     *
     * 设置  卖方
     *
     * @param sellerId
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     *
     * 获取  订单时间
     *
     * @return  orderTime
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     *
     * 设置  订单时间
     *
     * @param orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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

    /**
     *
     * 获取  支付流水号
     *
     * @return  paymentRecordId
     */
    public Long getPaymentRecordId() {
        return paymentRecordId;
    }

    /**
     *
     * 设置  支付流水号
     *
     * @param paymentRecordId
     */
    public void setPaymentRecordId(Long paymentRecordId) {
        this.paymentRecordId = paymentRecordId;
    }

    /**
     *
     * 获取  付款金额--成交价
     *
     * @return  paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     *
     * 设置  付款金额--成交价
     *
     * @param paymentAmount
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     *
     * 获取  实付金额,用户支付后返回
     *
     * @return  actualPayment
     */
    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    /**
     *
     * 设置  实付金额,用户支付后返回
     *
     * @param actualPayment
     */
    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    /**
     *
     * 获取  0:线下;1:线上
     *
     * @return  payMode
     */
    public String getPayMode() {
        return payMode;
    }

    /**
     *
     * 设置  0:线下;1:线上
     *
     * @param payMode
     */
    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    /**
     *
     * 获取  支付时间
     *
     * @return  payTime
     */
    public String getPayTime() {
        return payTime;
    }

    /**
     *
     * 设置  支付时间
     *
     * @param payTime
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    /**
     *
     * 获取  0:未付款;1:待发布;2:退货申请中;3:已退货;4:交易完成;5:关闭
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:未付款;1:待发布;2:退货申请中;3:已退货;4:交易完成;5:关闭
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * 获取  收货人名称
     *
     * @return  receiverName
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     *
     * 设置  收货人名称
     *
     * @param receiverName
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     *
     * 获取  收货人电话
     *
     * @return  receiverPhone
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     *
     * 设置  收货人电话
     *
     * @param receiverPhone
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    /**
     *
     * 获取  收货人省份
     *
     * @return  receiverProvince
     */
    public Long getReceiverProvince() {
        return receiverProvince;
    }

    /**
     *
     * 设置  收货人省份
     *
     * @param receiverProvince
     */
    public void setReceiverProvince(Long receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    /**
     *
     * 获取  收货人城市
     *
     * @return  reciverCity
     */
    public Long getReciverCity() {
        return reciverCity;
    }

    /**
     *
     * 设置  收货人城市
     *
     * @param reciverCity
     */
    public void setReciverCity(Long reciverCity) {
        this.reciverCity = reciverCity;
    }

    /**
     *
     * 获取  收货人区域
     *
     * @return  reciverCountry
     */
    public Long getReciverCountry() {
        return reciverCountry;
    }

    /**
     *
     * 设置  收货人区域
     *
     * @param reciverCountry
     */
    public void setReciverCountry(Long reciverCountry) {
        this.reciverCountry = reciverCountry;
    }

    /**
     *
     * 获取  收货人小区
     *
     * @return  receiverVillage
     */
    public String getReceiverVillage() {
        return receiverVillage;
    }

    /**
     *
     * 设置  收货人小区
     *
     * @param receiverVillage
     */
    public void setReceiverVillage(String receiverVillage) {
        this.receiverVillage = receiverVillage == null ? null : receiverVillage.trim();
    }

    /**
     *
     * 获取  收货人地址
     *
     * @return  receiverAdress
     */
    public String getReceiverAdress() {
        return receiverAdress;
    }

    /**
     *
     * 设置  收货人地址
     *
     * @param receiverAdress
     */
    public void setReceiverAdress(String receiverAdress) {
        this.receiverAdress = receiverAdress == null ? null : receiverAdress.trim();
    }

    /**
     *
     * 获取  配送方式:自取、物流名称
     *
     * @return  deliveryMode
     */
    public String getDeliveryMode() {
        return deliveryMode;
    }

    /**
     *
     * 设置  配送方式:自取、物流名称
     *
     * @param deliveryMode
     */
    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode == null ? null : deliveryMode.trim();
    }

    /**
     *
     * 获取  物流、快递名称
     *
     * @return  logisticsNumber
     */
    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    /**
     *
     * 设置  物流、快递名称
     *
     * @param logisticsNumber
     */
    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber == null ? null : logisticsNumber.trim();
    }

    /**
     *
     * 获取  商家电话
     *
     * @return  phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * 设置  商家电话
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *
     * 获取  物流运费
     *
     * @return  freight
     */
    public BigDecimal getFreight() {
        return freight;
    }

    /**
     *
     * 设置  物流运费
     *
     * @param freight
     */
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
    /**
    *
    * 获取  订单关闭理由
    *
    * @return  orderCloseReasons
    */
   public String getOrderCloseReasons() {
       return orderCloseReasons;
   }

   /**
    *
    * 设置  订单关闭理由
    *
    * @param orderCloseReasons
    */
   public void setOrderCloseReasons(String orderCloseReasons) {
       this.orderCloseReasons = orderCloseReasons == null ? null : orderCloseReasons.trim();
   }
    /**
     *
     * 获取  评定时间
     *
     * @return  insYmdhms
     */
    public Date getInsYmdhms() {
        return insYmdhms;
    }

    /**
     *
     * 设置  评定时间
     *
     * @param insYmdhms
     */
    public void setInsYmdhms(Date insYmdhms) {
        this.insYmdhms = insYmdhms;
    }

    /**
     *
     * 获取  录入用户
     *
     * @return  insId
     */
    public Long getInsId() {
        return insId;
    }

    /**
     *
     * 设置  录入用户
     *
     * @param insId
     */
    public void setInsId(Long insId) {
        this.insId = insId;
    }

    /**
     *
     * 获取  更新用户
     *
     * @return  updId
     */
    public Long getUpdId() {
        return updId;
    }

    /**
     *
     * 设置  更新用户
     *
     * @param updId
     */
    public void setUpdId(Long updId) {
        this.updId = updId;
    }

    /**
     *
     * 获取  更新时间
     *
     * @return  updYmdhms
     */
    public Date getUpdYmdhms() {
        return updYmdhms;
    }

    /**
     *
     * 设置  更新时间
     *
     * @param updYmdhms
     */
    public void setUpdYmdhms(Date updYmdhms) {
        this.updYmdhms = updYmdhms;
    }

    /**
     *
     * 获取  更新回数
     *
     * @return  updEac
     */
    public Long getUpdEac() {
        return updEac;
    }

    /**
     *
     * 设置  更新回数
     *
     * @param updEac
     */
    public void setUpdEac(Long updEac) {
        this.updEac = updEac;
    }

    /**
     *
     * 获取  删除标记
     *
     * @return  delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     *
     * 设置  删除标记
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
