package com.wooxun.geekdol.hbridge.model;

import java.util.Date;

import com.soft863.dolphin.common.CommonEntity;

public class IncorruptRecommend extends CommonEntity {

	private static final long serialVersionUID = 1L;
    /** 主键id */
    private Long id;

    /** 风清气正id */
    private Long incorruptId;

    /** 收藏人id */
    private Long operatorId;

    /** 收藏时间 */
    private Date operatorTime;

    /** 0:收藏;1不收藏 */
    private String operatorType;

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
     * 获取  风清气正id
     *
     * @return  incorruptId
     */
    public Long getIncorruptId() {
        return incorruptId;
    }

    /**
     *
     * 设置  风清气正id
     *
     * @param incorruptId
     */
    public void setIncorruptId(Long incorruptId) {
        this.incorruptId = incorruptId;
    }

    /**
     *
     * 获取  收藏人id
     *
     * @return  operatorId
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     *
     * 设置  收藏人id
     *
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     *
     * 获取  收藏时间
     *
     * @return  operatorTime
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     *
     * 设置  收藏时间
     *
     * @param operatorTime
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    /**
     *
     * 获取  0:收藏;1不收藏
     *
     * @return  operatorType
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     *
     * 设置  0:收藏;1不收藏
     *
     * @param operatorType
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }
}