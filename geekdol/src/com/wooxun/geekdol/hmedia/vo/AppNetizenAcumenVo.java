package com.wooxun.geekdol.hmedia.vo;
/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年9月10日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年9月10日  上午10:43:40 		创建
 *==========================================================
 * 
 */
public class AppNetizenAcumenVo {
    /** 主键id */
    private Long id;

    /** 文章标题 */
    private String title;

    /** 文章来源 */
    private String source;

    /** 举报原因 */
    private String reason;

    /** 暴力、恐怖、色情、赌博 、其它（来自数据字典表）
            多个类型用逗号分隔 */
    private String type;

    /** 发布人IP */
    private String insIp;

    /** 发布者电话 */
    private String phone;

    /** 发布人地址 */
    private String address;
    

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getReason() {
        return reason;
    }

    public String getType() {
        return type;
    }

    public String getInsIp() {
        return insIp;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInsIp(String insIp) {
        this.insIp = insIp;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
