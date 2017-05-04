package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.AppUserVillage;
import com.wooxun.geekdol.system.vo.AppUserVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月19日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. YK 2016年7月19日 下午4:14:49 创建
 *           ==========================================================
 * 
 */
public interface AppUserService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description 组合查询内参人员列表
	 * @author:YK
	 * @CreateDate:2016年7月19日 下午4:24:04
	 * @param appUserVo
	 * @return List
	 */
	public List<AppUserVo> queryAppUser(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 查询总数
	 * @author:YK
	 * @CreateDate:2016年7月19日 下午4:25:03
	 * @param appUserVo
	 * @return Long
	 */
	public Long queryAppUserCount(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 新增app用户
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午6:50:36
	 * @param appUser
	 * @return boolean
	 */
	public boolean insertAppUser(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 更新app用户
	 * @author:YK
	 * @CreateDate:2016年7月19日 下午4:26:47
	 * @param appUser
	 * @return boolean
	 */
	public boolean updateAppUser(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description:根据id获取对象
	 * @author:YK
	 * @CreateDate:2016年7月19日 下午10:16:04
	 * @param id
	 * @return AppUser
	 */
	public AppUser selectAppUser(Long id);

	/**
	 * 验证手机号是否注册过
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年7月20日 下午5:39:58
	 * @param appUser
	 * @return boolean
	 */
	public boolean hasAppUser(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 会员转为内参人员时，会员列表查询
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午6:51:19
	 * @param appUserVo
	 * @return List
	 */
	public List<AppUserVo> queryMemberByParams(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 会员转为内参人员时，会员总数
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午1:06:04
	 * @param appUserVo
	 * @return Long
	 */
	public Long queryMemberCountByParams(AppUserVo appUserVo);

	/**
	 * 
	 * @Title
	 * @Description 校验电话号码是否已经注册过--接口用
	 * @author:zhougp
	 * @CreateDate:2016年7月25日 上午11:44:56
	 * @param mobile
	 * @return int
	 */
	public int selectByMobile(String mobile);

	/**
	 * 
	 * @Title
	 * @Description 登录 --接口用
	 * @author:QZG
	 * @CreateDate:2016年7月26日 上午9:20:35
	 * @param appUser
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
	public AppUser select(String moblie);

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
	 * @Description 保存发送验证码记录
	 * @author:kangtianyu
	 * @CreateDate:2016年8月22日 下午3:16:32
	 * @param moblie
	 * @param identifyingCode
	 * @param ipStr
	 * @param type
	 * @return
	 */
	public boolean addPhoneCode(String moblie, String identifyingCode, String ipStr, String type);

	/**
	 * @Title
	 * @Description 对比验证码
	 * @author:kangtianyu
	 * @CreateDate:2016年8月22日 下午4:03:02
	 * @param moblie
	 * @param code
	 * @param sendTypeRegister
	 * @return
	 */
	public boolean findPhoneCode(String moblie, String code, String sendType);

	/**
	 * @Title
	 * @Description 根据电话获取用户信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月22日 下午5:06:42
	 * @param moblie
	 * @return
	 */
	public AppUser selectAppUserByMobile(String moblie);

	/**
	 * @Title
	 * @Description 根据小区ID查找对应关系
	 * @author:张洋
	 * @CreateDate:2016年9月14日17:07:29
	 * @param param
	 * @return
	 */
	List<AppUserVillage> selectByVillageId(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 1.判断ip是否达到每天限制条数 2.判断手机号是否达到每天发送限制次数
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 上午10:05:30
	 * @param moblie
	 * @param userIp
	 * @return
	 */
	public Map<String, Object> limitCode(String moblie, String userIp);

	/**
	 * @Title
	 * @Description 插入用户小区关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午11:28:01
	 * @param appUserVillage
	 * @return
	 */
	public boolean addAppUserVillage(AppUserVillage appUserVillage);

	/**
	 * @Title
	 * @Description 根据类型找用户小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午11:50:33
	 * @param appUserId
	 * @param villageType
	 * @return
	 */
	public List<Map<String, Object>> findAppUserVillageByType(Long appUserId, String villageType);

	/**
	 * @Title
	 * @Description 删除用户小区关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 下午5:53:55
	 * @param appUserVillage
	 * @return
	 */
	public boolean removeAppUserVillage(AppUserVillage appUserVillage);

	/**
	 * @Title
	 * @Description 根据用户获取用户小区对应关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月25日 上午9:33:27
	 * @param appUserId
	 * @param villageId
	 * @param villageType
	 * @return
	 */
	public AppUserVillage getAppUserVillageByUser(Long appUserId, Long villageId, String villageType);

	/**
	 * @Title
	 * @Description 根据电话获取用户信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月26日 上午11:13:48
	 * @param moblie
	 * @return
	 */
	public AppUser getAppUserByPhone(String moblie);

	/**
	 * 
	 * @Title
	 * @Description 根据uid删除用户(中原网系统调用此用户)
	 * @author:王肖东
	 * @CreateDate:2016年9月26日 下午5:33:27
	 * @param uid
	 * @return AppUser
	 */
	public AppUser selectAppUserByUid(int uid);

	/**
	 * 
	 * @Title
	 * @Description 根据uid删除用户(中原网系统调用此用户 逻辑删除)
	 * @author:王肖东
	 * @CreateDate:2016年9月26日 下午5:33:27
	 * @param appUser
	 * @return
	 */
	public int deleteAppUserByUid(AppUser appUser);

	/**
	 * 
	 * @Title
	 * @Description 根据uid删除用户(中原网系统调用此用户 物理删除)
	 * @author:王肖东
	 * @CreateDate:2016年9月26日 下午5:33:27
	 * @param uid
	 * @return
	 */
	public boolean deleteAppUserByUid2(int uid);
}
