package com.wooxun.geekdol.system.model;

import java.util.List;

import com.soft863.dolphin.common.CommonEntity;
import com.soft863.dolphin.model.Role;

public class User extends CommonEntity{
	
	private static final long serialVersionUID = 1L;

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
    /**备注*/
    private String remarks;
    /** 用户所属省id */
    private Long provinceId;
    /** 用户所属市id */
    private Long cityId;
    /** 极光推送id */
    private String pushId;
    
    
    /********************扩展属性************************/
    /**角色名称*/
    private String roleName;
    
    /**权限控制，用户所拥有的角色*/
    private List<Role> userRoles;
    /** 角色id */
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie == null ? null : moblie.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public List<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	/**
    *
    * 获取  用户所属省id
    *
    * @return  provinceId
    */
   public Long getProvinceId() {
       return provinceId;
   }

   /**
    *
    * 设置  用户所属省id
    *
    * @param provinceId
    */
   public void setProvinceId(Long provinceId) {
       this.provinceId = provinceId;
   }

   /**
    *
    * 获取  用户所属市id
    *
    * @return  cityId
    */
   public Long getCityId() {
       return cityId;
   }

   /**
    *
    * 设置  用户所属市id
    *
    * @param cityId
    */
   public void setCityId(Long cityId) {
       this.cityId = cityId;
   }

   /**
    *
    * 获取  极光推送id
    *
    * @return  pushId
    */
   public String getPushId() {
       return pushId;
   }

   /**
    *
    * 设置  极光推送id
    *
    * @param pushId
    */
   public void setPushId(String pushId) {
       this.pushId = pushId == null ? null : pushId.trim();
   }
   
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}