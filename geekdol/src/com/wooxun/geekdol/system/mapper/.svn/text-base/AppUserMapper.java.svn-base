package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.vo.AppUserVo;

public interface AppUserMapper<T extends Serializable> extends CrudMapper<T> {
	/**
	 * 
	 * @Title
	 * @Description 内参人员组合条件查询
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:12:34
	 * @param appUserVo
	 * @return List
	 */
	public List<AppUserVo> queryAppUser(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 组合条件查询内参人员总数
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:13:04
	 * @param appUserVo
	 * @return Long
	 */
	public Long queryAppUserCount(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 根据查询条件查询会员列表
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午9:53:15
	 * @param appUserVo
	 * @return List
	 */
	public List<AppUserVo> findAll(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 根据查询条件查询会员列表数量
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午9:52:27
	 * @param appUserVo
	 * @return Long
	 */
	public Long findAllCount(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 逻辑删除会员信息 返回值大于0，删除成功
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午9:53:46
	 * @param appUser
	 * @return int
	 */
	public int deleteAppUser(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 会员启用功能，返回值大于0，启用成功
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午9:51:58
	 * @param appUser
	 * @return int
	 */
	public int start(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 会员禁用功能，返回值大于0，禁用成功
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午9:51:16
	 * @param appUser
	 * @return int
	 */
	public int stop(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 根据手机号查询是否存在记录,用于新增内参人员校验
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:11:10
	 * @param appUser
	 * @return Long
	 */
	public Long hasAppUser(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 会员转为内参人员时，会员总数
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:10:48
	 * @param appUserVo
	 * @return Long
	 */
	public Long queryMemberCountByParams(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 会员转为内参人员时，会员列表查询
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:10:27
	 * @param appUserVo
	 * @return List
	 */
	public List<AppUserVo> queryMemberByParams(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 校验电话号码是否已经注册过--接口用
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午11:44:56
	 * @param mobile
	 * @return int
	 */
	public int selectByMobile(String mobile);

	/**
	 * 
	 * @Title
	 * @Description 校验电话号码是否存在--app接口用
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午11:44:56
	 * @param mobile
	 * @return int
	 */
	public int selectByMobileAll(String mobile);

	/**
	 * 
	 * @Title
	 * @Description 登录--接口用
	 * @author:QZG
	 * @CreateDate:2016年7月26日 上午9:19:48
	 * @param mobile
	 * @param pwd
	 * @return int
	 */
	public int login(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 登录成功 返回实体类--接口用
	 * @author:QZG
	 * @CreateDate:2016年7月26日 上午11:40:13
	 * @param mobile
	 * @return AppUser
	 */
	public AppUser select(String mobile);

	/**
	 * 
	 * @Title
	 * @Description 根据safekey获取用户，用于判断app用户是否登录
	 * @author:张洋
	 * @CreateDate:2016年8月4日09:38:00
	 * @param safekey
	 * @return AppUser
	 */
	public AppUser selectBySafekey(String safekey);

	/**
	 * @Title
	 * @Description 根据电话获取AppUser
	 * @author:kangtianyu
	 * @CreateDate:2016年8月22日 下午5:21:42
	 * @param moblie
	 * @return
	 */
	public AppUser selectAppUserByMobile(String moblie);

	/**
	 * 
	 * @Title
	 * @Description 根据uid查到appuser
	 * @author:王肖东
	 * @CreateDate:2016年9月26日 下午5:36:10
	 * @param uid
	 * @return AppUser
	 */
	public AppUser selectAppUserByUid(int uid);

	/**
	 * 
	 * @Title
	 * @Description 根据uid删除用户（逻辑删除）
	 * @author:王肖东
	 * @CreateDate:2016年9月26日 下午5:36:10
	 * @param uid
	 * @return
	 */
	public int deleteAppUserByUserId(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 根据uid删除用户（物理删除）
	 * @author:王肖东
	 * @CreateDate:2016年9月26日 下午5:36:10
	 * @param uid
	 * @return
	 */
	public int deleteAppUserByUserId2(int uid);
}