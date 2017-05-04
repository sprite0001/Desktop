package com.wooxun.geekdol.system.vo;

import java.util.Date;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月21日  上午9:52:03 		创建
 *==========================================================
 * 
 */
public class SyslogVo extends BaseVo{
	
	/** 主键id */
    private Long id;

    /** 用户名 */
    private String userName;

    /** 操作类型 */
    private String optType;

    /** 异常内容 */
    private String exceptionContent;

    /** 操作表名 */
    private String optTableName;

    /** 操作数据id */
    private String optDataId;

    /** 录入用户 */
    private Long insId;

    /** 创建时间 */
    private Date insYmdhms;

    /** 更新用户 */
    private Long updId;

    /** 更新时间 */
    private Date updYmdhms;

    /** 更新回数 */
    private Long updEac;

    /** 删除标记 */
    private String delFlag;

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
     * 获取  用户名
     *
     * @return  userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * 设置  用户名
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     *
     * 获取  操作类型
     *
     * @return  optType
     */
    public String getOptType() {
        return optType;
    }

    /**
     *
     * 设置  操作类型
     *
     * @param optType
     */
    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    /**
     *
     * 获取  异常内容
     *
     * @return  exceptionContent
     */
    public String getExceptionContent() {
        return exceptionContent;
    }

    /**
     *
     * 设置  异常内容
     *
     * @param exceptionContent
     */
    public void setExceptionContent(String exceptionContent) {
        this.exceptionContent = exceptionContent == null ? null : exceptionContent.trim();
    }

    /**
     *
     * 获取  操作表名
     *
     * @return  optTableName
     */
    public String getOptTableName() {
        return optTableName;
    }

    /**
     *
     * 设置  操作表名
     *
     * @param optTableName
     */
    public void setOptTableName(String optTableName) {
        this.optTableName = optTableName == null ? null : optTableName.trim();
    }

    /**
     *
     * 获取  操作数据id
     *
     * @return  optDataId
     */
    public String getOptDataId() {
        return optDataId;
    }

    /**
     *
     * 设置  操作数据id
     *
     * @param optDataId
     */
    public void setOptDataId(String optDataId) {
        this.optDataId = optDataId == null ? null : optDataId.trim();
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
     * 获取  创建时间
     *
     * @return  insYmdhms
     */
    public Date getInsYmdhms() {
        return insYmdhms;
    }

    /**
     *
     * 设置  创建时间
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
