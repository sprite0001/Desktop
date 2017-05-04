package com.wooxun.geekdol.geekstore.vo;

import java.util.Date;
import java.util.List;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 万通	
 * @CreateDate 2016年8月12日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年8月12日  上午11:04:11 		创建
 *==========================================================
 * 
 */
public class GoodsRecommendCommentVo extends BaseVo{
	/** 主键Id */
    private Long id;

    /** 商品id */
    private Long goodsId;

    /** 0:正常;1:违规 */
    private String status;

    /** 星级数量 */
    private Integer startNumber;

    /** 评论人 */
    private Long opreater;

    /** 评论Ip */
    private String opreaterIp;

    /** 评论时间 */
    private Date discussTime;

    /** 评论内容 */
    private String comment;
    
    /**自定义 */
    /** 评论人姓名 */
    private String opreaterName;
    /** 评论状态 */
    private String commentStatus;
    /**铺货ID*/
    private Long classificId;
    /** 评论时间 */
    private String discussTimeStr;
    
    private List<Integer> sumList;
    
    /** 字典表违规类型的类型指定*/
    private String illegalType;

    public String getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}

	public List<Integer> getSumList() {
		return sumList;
	}

	public void setSumList(List<Integer> sumList) {
		this.sumList = sumList;
	}

	public String getDiscussTimeStr() {
		return discussTimeStr;
	}

	public void setDiscussTimeStr(String discussTimeStr) {
		this.discussTimeStr = discussTimeStr;
	}
    
    public Long getClassificId() {
		return classificId;
	}

	public void setClassificId(Long classificId) {
		this.classificId = classificId;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getOpreaterName() {
		return opreaterName;
	}

	public void setOpreaterName(String opreaterName) {
		this.opreaterName = opreaterName;
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
}
