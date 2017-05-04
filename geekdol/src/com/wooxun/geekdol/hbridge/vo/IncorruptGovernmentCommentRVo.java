package com.wooxun.geekdol.hbridge.vo;

import java.util.Date;

import com.wooxun.geekdol.system.vo.BaseVo;

public class IncorruptGovernmentCommentRVo extends BaseVo {

    /** 主键 */
    private Long id;

    /** 风清气正评论id */
    private Long incorruptGovernmentCommentId;

    /** 评论内容 */
    private String content;

    /** 是否违规:是,否 */
    private String illegalStatus;

    /** 评论人 */
    private Long opreaterId;
    
    /** 评论人名称 */
    private String nickName;

    /** 发布评论人的Ip */
    private String opreaterIp;

    /** 评论时间 */
    private Date opreaterTime;

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
     * 获取  风清气正评论id
     *
     * @return  incorruptGovernmentCommentId
     */
    public Long getIncorruptGovernmentCommentId() {
        return incorruptGovernmentCommentId;
    }

    /**
     *
     * 设置  风清气正评论id
     *
     * @param incorruptGovernmentCommentId
     */
    public void setIncorruptGovernmentCommentId(Long incorruptGovernmentCommentId) {
        this.incorruptGovernmentCommentId = incorruptGovernmentCommentId;
    }

    /**
     *
     * 获取  评论内容
     *
     * @return  content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * 设置  评论内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *
     * 获取  是否违规:是,否
     *
     * @return  illegalStatus
     */
    public String getIllegalStatus() {
        return illegalStatus;
    }

    /**
     *
     * 设置  是否违规:是,否
     *
     * @param illegalStatus
     */
    public void setIllegalStatus(String illegalStatus) {
        this.illegalStatus = illegalStatus == null ? null : illegalStatus.trim();
    }

    /**
     *
     * 获取  评论人
     *
     * @return  opreaterId
     */
    public Long getOpreaterId() {
        return opreaterId;
    }

    /**
     *
     * 设置  评论人
     *
     * @param opreaterId
     */
    public void setOpreaterId(Long opreaterId) {
        this.opreaterId = opreaterId;
    }

    /**
     *
     * 获取  发布评论人的Ip
     *
     * @return  opreaterIp
     */
    public String getOpreaterIp() {
        return opreaterIp;
    }

    /**
     *
     * 设置  发布评论人的Ip
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
     * @return  opreaterTime
     */
    public Date getOpreaterTime() {
        return opreaterTime;
    }

    /**
     *
     * 设置  评论时间
     *
     * @param opreaterTime
     */
    public void setOpreaterTime(Date opreaterTime) {
        this.opreaterTime = opreaterTime;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
}