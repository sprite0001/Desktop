package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.vo.AppUserVo;

/**
 * @Title
 * @Description 
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月20日  下午2:09:24 		创建
 *==========================================================
 * 
 */
public interface MemberService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title
     * @Description 会员管理 根据页面查询条件，显示列表
     * @author:QZG
     * @CreateDate:2016年7月20日 下午2:10:55
     * @param appUserVo
     * @return
     */
    public List<AppUserVo> findAll(AppUserVo appUserVo);
    /**
     * 
     * @Title
     * @Description
     * @author:QZG 会员管理 根据页面查询条件，查询列表条数
     * @CreateDate:2016年7月20日 下午2:11:30
     * @param appUserVo
     * @return
     */
    public Long findAllCount(AppUserVo appUserVo);
    
    /**
     * 
     * @Title
     * @Description 会员管理 通过id逻辑删除信息 删除成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:31:03
     * @param appUser
     * @return
     */
    public boolean deleteAppUser(AppUser appUser);
    
    /**
     * 
     * @Title
     * @Description 会员信息启用
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:32:40
     * @param appUser
     * @return
     */
    public boolean start(AppUser appUser);
    /**
     * 
     * @Title
     * @Description 会员信息禁用
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:33:11
     * @param appUser
     * @return
     */
    public boolean stop(AppUser appUser);
     
}
