package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月5日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月5日  上午11:14:14 		创建
 *==========================================================
 * 
 */
public class GrouponCommentVo extends BaseVo {
    /** 主键Id */
    private Long id;

    /** 商品id */
    private Long goodsId;

    /** 0:正常;1:违规 */
    private String status;
    /**状态名称*/
    private String statusStr;

    /** 星级数量 */
    private Integer startNumber;

    /** 评论人 */
    private Long opreater;
    
    /** 评论人名称 */
    private String opreaterName;

    /** 评论Ip */
    private String opreaterIp;

    /** 评论时间 */
    private Date discussTime;
    /** 评论时间 字符串*/
    private String discussTimeStr;

    /** 评论内容 */
    private String comment;

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
     * 获取  0:正常;1:违规
     *
     * @return  status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * 设置  0:正常;1:违规
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * 获取  星级数量
     *
     * @return  startNumber
     */
    public Integer getStartNumber() {
        return startNumber;
    }

    /**
     *
     * 设置  星级数量
     *
     * @param startNumber
     */
    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    /**
     *
     * 获取  评论人
     *
     * @return  opreater
     */
    public Long getOpreater() {
        return opreater;
    }

    /**
     *
     * 设置  评论人
     *
     * @param opreater
     */
    public void setOpreater(Long opreater) {
        this.opreater = opreater;
    }

    /**
     *
     * 获取  评论Ip
     *
     * @return  opreaterIp
     */
    public String getOpreaterIp() {
        return opreaterIp;
    }

    /**
     *
     * 设置  评论Ip
     *
     * @param opreaterIp
     */
    public void setOpreaterIp(String opreaterIp) {
        this.opreaterIp = opreaterIp == null ? null : opreaterIp.trim();
    }

    /**
     *
     * 获取  评论时间
     *
     * @return  discussTime
     */
    public Date getDiscussTime() {
        return discussTime;
    }

    /**
     *
     * 设置  评论时间
     *
     * @param discussTime
     */
    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
    }

    /**
     *
     * 获取  评论内容
     *
     * @return  comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * 设置  评论内容
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

	public String getOpreaterName() {
		return opreaterName;
	}

	public void setOpreaterName(String opreaterName) {
		this.opreaterName = opreaterName;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getDiscussTimeStr() {
		return discussTimeStr;
	}

	public void setDiscussTimeStr(String discussTimeStr) {
		this.discussTimeStr = discussTimeStr;
	}
	
}
