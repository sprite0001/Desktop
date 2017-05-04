package com.wooxun.geekdol.geekstore.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

public class Order extends CommonEntity{
	private static final long serialVersionUID = 1L;
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
    private Date payTime;

    /** 未付款、未发货、已完成、待处理退货、已同意退货、驳回退货、待收货、待退款、已退单、已关闭 */
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


    /** 小区id */
    private Long villageId;

    /** 关闭理由 */
    private String orderCloseReasons;
    
    
    
    /**自定义属性*/
    /**订单退货信息表*/
    private OrderReturnInfor orderReturnInfor;
    
	public OrderReturnInfor getOrderReturnInfor() {
		return orderReturnInfor;
	}

	public void setOrderReturnInfor(OrderReturnInfor orderReturnInfor) {
		this.orderReturnInfor = orderReturnInfor;
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
    public Date getPayTime() {
        return payTime;
    }

    /**
     *
     * 设置  支付时间
     *
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     *
     * 获取  未付款、未发货、已完成、待处理退货、已同意退货、驳回退货、待收货、待退款、已退单、已关闭
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  未付款、未发货、已完成、待处理退货、已同意退货、驳回退货、待收货、待退款、已退单、已关闭
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
}