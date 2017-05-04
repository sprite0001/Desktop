package com.wooxun.geekdol.geekstore.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title 极客店管理-商品管理-订单管理
 * @Description 
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月25日  下午4:10:04 		创建
 *==========================================================
 * 
 */
public class OrderVo extends BaseVo {
	
	 /** 订单Id */
    private Long orderId;

    /** 订单编号 */
    private String orderCode;

    /** 买方 */
    private Long buyerId;

    /** 卖方 */
    private Long sellerId;

    /** 下单时间 */
    private Date orderTime;

    /** 支付流水号 */
    private Long paymentRecordId;

    /** 付款金额--成交价 */
    private Long payAmount;

    /** 实付金额,用户支付后返回 */
    private Long actualPayment;

    /** 支付方式 */
    private String payMode;

    /** 支付时间 */
    private String payTime;

    /** 退货、未付款、交易中、已退货、交易完成、代发货 */
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

    /** 物流、快递单号 */
    private String logisticsNumber;

    /** 商家电话 */
    private String phone;

    /** 物流运费 */
    private Long freight;
    
    /** 关闭理由 */
    private String orderCloseReasons;
    
    /** 录入时间 */
    private Date insYmdhms;

    /** 录入用户 */
    private Long insId;

    /** 更新用户 */
    private Long updId;

	/** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 0:未删除;1:删除; */
    private String delFlag;
    
    /**以下是自定义*/
    
    /** 商品编号 */
    private String goodsCode;
    
	/** 成本价 */
    private String costPrice;
    
    /** 处理结果 */
    private String detailResult;    

	/** 退货地址 */
    private String returnAdress;

    /** 处理结果理由 */
    private String detailResultReason;
    
    /** 退款金额 */
    private BigDecimal returnMoney;

    /** 退款金额备注 */
    private String returnMoneyMark;
    
    /** 销售模块 */
    private String classificName;
    /** 销售模块 ID*/
    private Long sellModel;
    /**销售价格*/
    private String sellPrice;
    
    /** 买方 名称*/
    private String buyerName;

    /** 卖方名称 */
    private String sellerName;    
    /**订单状态*/
    private String orderStatus;
    /** 前台退货理由 */
    private String orderReturnReason;
    
    private List<Attach> attachment;

    /**用户区域权限*/
    private String areaLevel;
    /**当前用户id*/
    private Long currentUserId;
    /**店铺id*/
    private Long storeId;
    /**字典表:支付方式类型*/
    private String payModel;
    /**字典表：订单状态类型*/
    private String orderStatusType;
    /**字典表：物流类型*/
    private String express;
    /**团购商品处理模式*/
    private String detailModel;
    /**05:自取*/
    private String deliveryMode_05;
    /**06:小区配送*/
    private String deliveryMode_06;
    
	public String getDeliveryMode_05() {
		return deliveryMode_05;
	}

	public void setDeliveryMode_05(String deliveryMode_05) {
		this.deliveryMode_05 = deliveryMode_05;
	}

	public String getDeliveryMode_06() {
		return deliveryMode_06;
	}

	public void setDeliveryMode_06(String deliveryMode_06) {
		this.deliveryMode_06 = deliveryMode_06;
	}

	public String getDetailModel() {
		return detailModel;
	}

	public void setDetailModel(String detailModel) {
		this.detailModel = detailModel;
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

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public List<Attach> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attach> attachment) {
		this.attachment = attachment;
	}

    public Long getCurrentUserId() {
		return currentUserId;
	}
    
    public String getOrderReturnReason() {
		return orderReturnReason;
	}

	public void setOrderReturnReason(String orderReturnReason) {
		this.orderReturnReason = orderReturnReason;
	}
	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Long getSellModel() {
		return sellModel;
	}

	public void setSellModel(Long sellModel) {
		this.sellModel = sellModel;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getClassificName() {
		return classificName;
	}

	public void setClassificName(String classificName) {
		this.classificName = classificName;
	}

	public String getDetailResult() {
		return detailResult;
	}

	public void setDetailResult(String detailResult) {
		this.detailResult = detailResult;
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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}



    public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	/**
     *
     * 获取  订单Id
     *
     * @return  orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     *
     * 设置  订单Id
     *
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * 获取  订单编号
     *
     * @return  orderCode
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     *
     * 设置  订单编号
     *
     * @param orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
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
     * 获取  下单时间
     *
     * @return  orderTime
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     *
     * 设置  下单时间
     *
     * @param orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
     * @return  payAmount
     */
    public Long getPayAmount() {
        return payAmount;
    }

    /**
     *
     * 设置  付款金额--成交价
     *
     * @param payAmount
     */
    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    /**
     *
     * 获取  实付金额,用户支付后返回
     *
     * @return  actualPayment
     */
    public Long getActualPayment() {
        return actualPayment;
    }

    /**
     *
     * 设置  实付金额,用户支付后返回
     *
     * @param actualPayment
     */
    public void setActualPayment(Long actualPayment) {
        this.actualPayment = actualPayment;
    }

    /**
     *
     * 获取  支付方式
     *
     * @return  payMode
     */
    public String getPayMode() {
        return payMode;
    }

    /**
     *
     * 设置  支付方式
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
     * 获取  退货、未付款、交易中、已退货、交易完成、代发货
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  退货、未付款、交易中、已退货、交易完成、代发货
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
     * 获取  物流、快递单号
     *
     * @return  logisticsNumber
     */
    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    /**
     *
     * 设置  物流、快递单号
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
    public Long getFreight() {
        return freight;
    }

    /**
     *
     * 设置  物流运费
     *
     * @param freight
     */
    public void setFreight(Long freight) {
        this.freight = freight;
    }  
    
    /**
    *
    * 获取  关闭理由
    *
    * @return  orderCloseReasons
    */
   public String getOrderCloseReasons() {
       return orderCloseReasons;
   }

   /**
    *
    * 设置  关闭理由
    *
    * @param orderCloseReasons
    */
   public void setOrderCloseReasons(String orderCloseReasons) {
       this.orderCloseReasons = orderCloseReasons == null ? null : orderCloseReasons.trim();
   }
   public Date getInsYmdhms() {
		return insYmdhms;
	}

	public void setInsYmdhms(Date insYmdhms) {
		this.insYmdhms = insYmdhms;
	}

	public Long getInsId() {
		return insId;
	}

	public void setInsId(Long insId) {
		this.insId = insId;
	}

	public Long getUpdId() {
		return updId;
	}

	public void setUpdId(Long updId) {
		this.updId = updId;
	}

	public Date getUpdYmdhms() {
		return updYmdhms;
	}

	public void setUpdYmdhms(Date updYmdhms) {
		this.updYmdhms = updYmdhms;
	}

	public Long getUpdEac() {
		return updEac;
	}

	public void setUpdEac(Long updEac) {
		this.updEac = updEac;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
}
