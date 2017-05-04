package com.wooxun.geekdol.system.vo;
/**
 * 
* @Title
* @Description 用户页面包装类
* @version 1.0.0
* @Author zhougp	
* @CreateDate 2016年7月20日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 万通	2016年7月20日  上午11:12:24 		创建
*==========================================================
*
 */
public class UserVo extends BaseVo{
	private Long id;
	/**登录账号(pc账号登陆）*/
    private String userName;
    /**密码*/
    private String password;
    /**用户真实姓名*/
    private String realName;
    /**邮箱*/
    private String email;
    /**用户绑定手机号*/
    private String moblie;
    /**使用状态(0:使用，1:禁用)*/
    private String status;
    /**用户类型*/
    private String userType;
    /**用户类型名称*/
    private String userTypeName;
    /**角色名称*/
    private String roleName;
    /** 角色id */
    private Long roleId;
    
	public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
