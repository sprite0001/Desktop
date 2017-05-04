package com.wooxun.geekdol.system.vo;
/**
 * @Title
 * @Description 投诉建议
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月20日  下午5:07:15 		创建
 *==========================================================
 * 
 */
public class SuggestionVo extends BaseVo{
	/** 主键id */
    private Long id;

    /** 姓名 */
    private String realName;

    /** 内容 */
    private String content;

    /** l联系方式 */
    private String contactInfo;
    

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
     * 获取  姓名
     *
     * @return  realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     *
     * 设置  姓名
     *
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     *
     * 获取  内容
     *
     * @return  content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * 设置  内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *
     * 获取  l联系方式
     *
     * @return  contactInfo
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     *
     * 设置  l联系方式
     *
     * @param contactInfo
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }    
}
