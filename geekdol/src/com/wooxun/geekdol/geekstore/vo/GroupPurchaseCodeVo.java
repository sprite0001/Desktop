package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;
import java.util.List;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 万通	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年8月8日  上午10:36:09 		创建
 *==========================================================
 * 
 */
public class GroupPurchaseCodeVo extends BaseVo  {
	/** 主键Id */
    private Long id;

    /** 团购订单id */
    private Long orderId;

    /** 团购订单表的订单编号 */
    private String orderCode;

    /** 团购商品表的团购编码 */
    private String serialCode;

    /** 后台生成的团购码 */
    private String code;

    /** 客户输入的验证码 */
    private String confirmCode;

    /** 0:未验证;1:已验证;2:已失效 */
    private String status;

    /** 生效日期 */
    private Date effectiveDate;

    /** 失效日期 */
    private Date expirationDate;

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

    /** 0:未删除;1:删除 */
    private String delFlag;
    
    /**自定义*/
    
    private List<String> codeList;


	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

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
     * 获取  团购订单id
     *
     * @return  orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     *
     * 设置  团购订单id
     *
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * 获取  团购订单表的订单编号
     *
     * @return  orderCode
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     *
     * 设置  团购订单表的订单编号
     *
     * @param orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     *
     * 获取  团购商品表的团购编码
     *
     * @return  serialCode
     */
    public String getSerialCode() {
        return serialCode;
    }

    /**
     *
     * 设置  团购商品表的团购编码
     *
     * @param serialCode
     */
    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode == null ? null : serialCode.trim();
    }

    /**
     *
     * 获取  后台生成的团购码
     *
     * @return  code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * 设置  后台生成的团购码
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     *
     * 获取  客户输入的验证码
     *
     * @return  confirmCode
     */
    public String getConfirmCode() {
        return confirmCode;
    }

    /**
     *
     * 设置  客户输入的验证码
     *
     * @param confirmCode
     */
    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode == null ? null : confirmCode.trim();
    }

    /**
     *
     * 获取  0:未验证;1:已验证;2:已失效
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:未验证;1:已验证;2:已失效
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * 获取  生效日期
     *
     * @return  effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *
     * 设置  生效日期
     *
     * @param effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     *
     * 获取  失效日期
     *
     * @return  expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     *
     * 设置  失效日期
     *
     * @param expirationDate
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
     * 获取  0:未删除;1:删除
     *
     * @return  delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     *
     * 设置  0:未删除;1:删除
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
