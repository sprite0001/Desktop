package com.wooxun.geekdol.hmedia.model;

import com.soft863.dolphin.common.CommonEntity;

public class Position extends CommonEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long positionId;

    /** 位置代码 */
    private String positionCode;

    /** 模块编码,模块在数据字典表中定义 */
    private String moduleCode;

    /** 名称 */
    private String positionName;

    /** 备注 */
    private String remark;
 
    /**
     *
     * 获取  主键id
     *
     * @return  positionId
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     *
     * 设置  主键id
     *
     * @param positionId
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     *
     * 获取  位置代码
     *
     * @return  positionCode
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     *
     * 设置  位置代码
     *
     * @param positionCode
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    /**
     *
     * 获取  模块编码,模块在数据字典表中定义
     *
     * @return  moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     *
     * 设置  模块编码,模块在数据字典表中定义
     *
     * @param moduleCode
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    /**
     *
     * 获取  名称
     *
     * @return  positionName
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     *
     * 设置  名称
     *
     * @param positionName
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     *
     * 获取  备注
     *
     * @return  remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     *
     * 设置  备注
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
 
}