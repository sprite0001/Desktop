package com.wooxun.geekdol.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.AppUserMapper;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.service.MemberService;
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
 * 1. 	 QZG    2016年7月20日  下午2:10:17 		创建
 *==========================================================
 * 
 */
@Service
public class MemberServiceImpl extends CrudServiceImpl<AppUser> implements MemberService<AppUser> {
   
    private AppUserMapper<AppUser> appUserMapper;
    
    @Autowired
    public MemberServiceImpl(AppUserMapper<AppUser> appUserMapper) {
        super(appUserMapper);
        this.appUserMapper=appUserMapper;
    }

    /**
     * 
     * @Title
     * @Description 根据条件查询分页信息条数
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:37:07
     * @param appUserVo
     * @return
     */
    @Override
    public Long findAllCount(AppUserVo appUserVo) {
        return appUserMapper.findAllCount(appUserVo);
    }

    /**
     * 
     * @Title
     * @Description 查询满足条件的所有会员信息
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:36:41
     * @param appUserVo
     * @return
     */
    @Override
    public List<AppUserVo> findAll(AppUserVo appUserVo) {
        
        return appUserMapper.findAll(appUserVo);
    }
 
    /**
     * 
     * @Title
     * @Description 通过id逻辑删除会员信息 删除成功返回true
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:35:56
     * @param appUser
     * @return
     */
    @Override
    public boolean deleteAppUser(AppUser appUser) {
        int result = appUserMapper.deleteAppUser(appUser);
        return result>0?true:false;
    }

    /**
     * 
     * @Title
     * @Description 会员信息启用功能 启用成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:35:02
     * @param appUser
     * @return
     */
    @Override
    public boolean start(AppUser appUser) {
        int result =appUserMapper.start(appUser);
        return result>0?true:false;
    }

    /**
     * 
     * @Title
     * @Description 会员信息禁用功能 禁用成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:34:39
     * @param appUser
     * @return
     */
    @Override
    public boolean stop(AppUser appUser) {
        int result =appUserMapper.stop(appUser);
        return result>0?true:false;
    }

   
 

}
