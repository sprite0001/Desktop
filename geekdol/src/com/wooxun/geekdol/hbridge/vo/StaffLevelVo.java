package com.wooxun.geekdol.hbridge.vo;

import com.wooxun.geekdol.system.vo.BaseVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 zhangyang	2016年7月26日  上午9:20:15 		创建
 *==========================================================
 * 
 */
public class StaffLevelVo extends BaseVo{
    /** 主键id */
    private Long id;
    /**
     * 级别编码
     */
    private String staffCode;
    /**
     * 级别名称
     */
    private String staffName;
    /**
     * 级别等级:科级,处级,厅级
     */
    private String staffLevel;
    /**
     * 備註
     */
    private String remark;
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getStaffLevel() {
        return staffLevel;
    }
    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}
