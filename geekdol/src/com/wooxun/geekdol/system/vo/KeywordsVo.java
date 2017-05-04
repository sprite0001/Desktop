package com.wooxun.geekdol.system.vo;

import java.util.Date;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG  2016年7月21日  上午10:22:50 		创建
 *==========================================================
 * 
 */
public class KeywordsVo extends BaseVo{
    /** 主键id */
    private Long id;

    /** 关键字内容 */
    private String keywordsContent;
    
    private Date insYmdhms;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeywordsContent() {
        return keywordsContent;
    }

    public void setKeywordsContent(String keywordsContent) {
        this.keywordsContent = keywordsContent;
    }

    public Date getInsYmdhms() {
        return insYmdhms;
    }

    public void setInsYmdhms(Date insYmdhms) {
        this.insYmdhms = insYmdhms;
    }

   
    
}
