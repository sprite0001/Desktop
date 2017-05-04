package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.system.mapper.AppUserMapper;
import com.wooxun.geekdol.system.mapper.AppUserVillageMapper;
import com.wooxun.geekdol.system.mapper.PhoneCodeMapper;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.AppUserVillage;
import com.wooxun.geekdol.system.model.PhoneCode;
import com.wooxun.geekdol.system.service.AppUserService;
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
 *           修改人员 修改日期 描述 1. YK 2016年7月19日 下午4:15:37 创建
 *           ==========================================================
 * 
 */
@Service
public class AppUserServiceImpl extends CrudServiceImpl<AppUser> implements AppUserService<AppUser> {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(AppUserServiceImpl.class);

	private AppUserMapper<AppUser> appUserMapper;
	@Autowired
	private AppUserVillageMapper<AppUserVillage> appUserVillageMapper;
	@Autowired
	private PhoneCodeMapper<PhoneCode> phoneCodeMapper;

	@Autowired
	public AppUserServiceImpl(AppUserMapper<AppUser> appUserMapper) {
		super(appUserMapper);
		this.appUserMapper = appUserMapper;
	}

	@Override
	public List<AppUserVo> queryAppUser(AppUserVo appUserVo) {
		// 设置证件类型type类型
		appUserVo.setCertificateSysType(ConstantStr.CERTIFICATES);
		// 设置用户级别type类型
		appUserVo.setStaffLevelType(ConstantStr.STAFFLEVELTYPE);
		return appUserMapper.queryAppUser(appUserVo);
	}

	@Override
	public Long queryAppUserCount(AppUserVo appUserVo) {
		return appUserMapper.queryAppUserCount(appUserVo);
	}

	@Override
	public boolean insertAppUser(AppUser appUser) {
		int result = appUserMapper.insertSelective(appUser);
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateAppUser(AppUser appUser) {
		int result = appUserMapper.updateByPrimaryKeySelective(appUser);
		return result > 0 ? true : false;
	}

	@Override
	public AppUser selectAppUser(Long id) {
		return appUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean hasAppUser(AppUser appUser) {
		return appUserMapper.hasAppUser(appUser) > 0 ? true : false;
	}

	@Override
	public List<AppUserVo> queryMemberByParams(AppUserVo appUserVo) {
		// 设置证件类型type类型
		appUserVo.setCertificateSysType(ConstantStr.CERTIFICATES);
		return appUserMapper.queryMemberByParams(appUserVo);
	}

	@Override
	public Long queryMemberCountByParams(AppUserVo appUserVo) {
		return appUserMapper.queryMemberCountByParams(appUserVo);
	}

	@Override
	public int selectByMobile(String mobile) {
		return appUserMapper.selectByMobile(mobile);
	}

	@Override
	public int login(AppUser appUser) {
		return appUserMapper.login(appUser);
	}

	@Override
	public AppUser select(String moblie) {
		return appUserMapper.select(moblie);
	}

	@Override
	public AppUser selectBySafekey(String safekey) {
		return appUserMapper.selectBySafekey(safekey);
	}

	@Override
	public boolean addPhoneCode(String moblie, String identifyingCode, String ipStr, String type) {
		// 创建Model对象用于插入记录
		PhoneCode phoneCode = new PhoneCode();
		// 设置电话号
		phoneCode.setPhone(moblie);
		// 设置验证码
		phoneCode.setCode(identifyingCode);
		// 设置添加时间
		phoneCode.setAddtime(new Date());
		// 设置发送类型
		phoneCode.setType(type);
		// 设置请求ip
		phoneCode.setIp(ipStr);
		// 调用mapper方法插入数据
		int result = phoneCodeMapper.insertSelective(phoneCode);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean findPhoneCode(String moblie, String code, String sendType) {
		// 创建Map对象用于封装查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		// 创建Model对象用于查询记录
		PhoneCode phoneCode = new PhoneCode();
		// 设置电话号
		phoneCode.setPhone(moblie);
		// 设置验证码
		phoneCode.setCode(code);
		// 设置添加时间
		phoneCode.setAddtime(new Date());
		// 设置发送类型
		phoneCode.setType(sendType);
		// 向map中放入参数
		param.put("phoneCode", phoneCode);
		param.put("minutes", ConstantStr.MINUTES - 1);
		// 调用mapper方法插入数据
		int result = phoneCodeMapper.selectCountCode(param);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public AppUser selectAppUserByMobile(String moblie) {
		return appUserMapper.selectAppUserByMobile(moblie);
	}

	@Override
	public Map<String, Object> limitCode(String moblie, String userIp) {
		// 创建Map变量用于封装返回的数据
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建Map变量用于封装sql查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		// 获取当前日期
		String nowDate = DateUtil.format(new Date(), "yyyy-MM-dd");
		/* 向map中封装查询参数 */
		param.put("nowDate", nowDate);
		param.put("moblie", moblie);
		param.put("userIp", userIp);
		// 创建临时变量用于判断数据库返回结果
		int resultCount = 0;
		// 判断ip当天的验证码发送条数
		resultCount = phoneCodeMapper.selectIpByDay(param);
		if (ConstantStr.IP_LIMIT == resultCount) { // 如果ip获取的验证码数量已经达到当天的限制
			/* 放入返回结果 */
			result.put("isMax", true);
			result.put("msg", ComDefine.getMsg(ConstantStr.INFO200034));
			// 返回结果
			return result;
		}
		// 判断手机号当天的验证码发送条数
		resultCount = phoneCodeMapper.selectPhoneByDay(param);
		if (ConstantStr.PHONE_LIMIT == resultCount) { // 如果电话号获取的验证码数量已经达到当天的限制
			/* 放入返回结果 */
			result.put("isMax", true);
			result.put("msg", ComDefine.getMsg(ConstantStr.INFO200035));
			// 返回结果
			return result;
		}
		/* 放入返回结果 */
		result.put("isMax", false);
		// 返回结果
		return result;
	}

	@Override
	public boolean addAppUserVillage(AppUserVillage appUserVillage) {
		// 调用mapper方法插入用户与小区对应关系
		int result = appUserVillageMapper.insertSelective(appUserVillage);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public List<Map<String, Object>> findAppUserVillageByType(Long appUserId, String villageType) {
		// 创建列表对象用于返回
		List<Map<String, Object>> villageMessage = new ArrayList<Map<String, Object>>();
		// 创建Map变量用于存放查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中放入参数 */
		param.put("appUserId", appUserId);
		param.put("villageType", villageType);
		// 调用mapper方法根据参数查询信息
		List<AppUserVillage> appUserVillageList = appUserVillageMapper.selectAppUserVillageByParam(param);
		// 遍历结果放入返回结果中
		for (AppUserVillage auv : appUserVillageList) {
			// 创建Map对象用于封装数据
			Map<String, Object> data = new HashMap<String, Object>();
			// 获取小区id
			data.put("villageId", auv.getVillageId());
			// 获取小区名字
			data.put("villageName", auv.getVillageName());
			// 将数据追加进list列表
			villageMessage.add(data);
		}
		// 返回结果
		return villageMessage;
	}

	@Override
	public boolean removeAppUserVillage(AppUserVillage appUserVillage) {
		// 调用mapper方法插入用户与小区对应关系
		int result = appUserVillageMapper.deleteAppUserVillage(appUserVillage);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public AppUserVillage getAppUserVillageByUser(Long appUserId, Long villageId, String villageType) {
		// 创建用户小区对应关系对象
		AppUserVillage appUserVillage = new AppUserVillage();
		// 设置用户id
		appUserVillage.setUserId(appUserId);
		// 设置小区id
		appUserVillage.setVillageId(villageId);
		// 设置关注小区类型
		appUserVillage.setVillageType(villageType);
		// 调用mepper方法获取用户与小区关系
		appUserVillage = appUserVillageMapper.selectAppUserVillageByUser(appUserVillage);
		// 返回结果
		return appUserVillage;
	}

	@Override
	public AppUser getAppUserByPhone(String moblie) {
		return appUserMapper.selectAppUserByMobile(moblie);
	}

	@Override
	public int selectByMobileAll(String mobile) {
		return appUserMapper.selectByMobileAll(mobile);
	}

	@Override
	public List<AppUserVillage> selectByVillageId(Map<String, Object> param) {
		return appUserVillageMapper.selectByVillageId(param);
	}

	@Override
	public AppUser selectAppUserByUid(int uid) {

		return appUserMapper.selectAppUserByUid(uid);
	}

	@Override
	public int deleteAppUserByUid(AppUser appUser) {
		return appUserMapper.deleteAppUserByUserId(appUser);
	}

	@Override
	public boolean deleteAppUserByUid2(int uid) {
		int result = appUserMapper.deleteAppUserByUserId2(uid);
		return result > 0 ? true : false;
	}
}
