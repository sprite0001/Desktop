package com.wooxun.geekdol.geekstore.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description 支付流水
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月8日  下午3:40:56 		创建
 *==========================================================
 * 
 */
public class PaymentRecomentVo extends BaseVo{
	/** 主键 */
    private Long id;

    /** 手机号 */
    private String phone;

    /** 订单编号 */
    private String orderCode;

    /** 第三方交易号 */
    private String transactionNumber;

    /** 第三方平台 */
    private String platform;

    /** 金额 */
    private BigDecimal amount;

    /** 交易日期 */
    private Date transactionDate;

    /** 操作用户 */
    private Long insId;

    /** 登记时间 */
    private Date insYmdhms;

    /** 更新用户 */
    private Long updId;

    /** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 0:未删除;1:删除; */
    private String delFlag;
    
    private String platformName;
    /**字典表:支付方式类型*/
    private String payModel;
    
    public String getPayModel() {
		return payModel;
	}

	public void setPayModel(String payModel) {
		this.payModel = payModel;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
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
     * 获取  手机号
     *
     * @return  phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * 设置  手机号
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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
     * 获取  第三方交易号
     *
     * @return  transactionNumber
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     *
     * 设置  第三方交易号
     *
     * @param transactionNumber
     */
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber == null ? null : transactionNumber.trim();
    }

    /**
     *
     * 获取  第三方平台
     *
     * @return  platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     *
     * 设置  第三方平台
     *
     * @param platform
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    /**
     *
     * 获取  金额
     *
     * @return  amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *
     * 设置  金额
     *
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     *
     * 获取  交易日期
     *
     * @return  transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     *
     * 设置  交易日期
     *
     * @param transactionDate
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     *
     * 获取  操作用户
     *
     * @return  insId
     */
    public Long getInsId() {
        return insId;
    }

    /**
     *
     * 设置  操作用户
     *
     * @param insId
     */
    public void setInsId(Long insId) {
        this.insId = insId;
    }

    /**
     *
     * 获取  登记时间
     *
     * @return  insYmdhms
     */
    public Date getInsYmdhms() {
        return insYmdhms;
    }

    /**
     *
     * 设置  登记时间
     *
     * @param insYmdhms
     */
    public void setInsYmdhms(Date insYmdhms) {
        this.insYmdhms = insYmdhms;
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
     * 获取  0:未删除;1:删除;
     *
     * @return  delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     *
     * 设置  0:未删除;1:删除;
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
