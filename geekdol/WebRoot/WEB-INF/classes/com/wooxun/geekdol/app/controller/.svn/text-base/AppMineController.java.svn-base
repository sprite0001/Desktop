package com.wooxun.geekdol.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
import com.wooxun.geekdol.hmedia.service.ActivityBaomingService;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.AppUserVillage;
import com.wooxun.geekdol.system.model.Cooperation;
import com.wooxun.geekdol.system.model.Suggestion;
import com.wooxun.geekdol.system.service.AppUserService;
import com.wooxun.geekdol.system.service.CooperationService;
import com.wooxun.geekdol.system.service.SuggestionService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年8月23日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年8月23日  下午2:19:34 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("appmine")
public class AppMineController {
	
	/** 关于我们界面url */
	private static final String ABOUT_US_URL = "appmine/aboutusDetail";
	
	/** 关于我们界面 */
	private static final String ABOUT_US = "appmine/aboutus";
	
	@Autowired
	private AppUserService<AppUser> appUserService;
	
	@Autowired
	private CooperationService<Cooperation> cooperationService;
	
	@Autowired
	private SuggestionService<Suggestion> suggestionService;
	
	@Autowired
	private ActivityBaomingService<ActivityBaoming> activityBaomingService;
	
	/**
	 * @Title
	 * @Description 我的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 上午12:20:38
	 * @param appUserId
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("/myVillage")
	public @ResponseBody String myVillage(@RequestParam Long appUserId,
			@RequestParam String safeKey, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装数据
		Map<String, Object> data = new HashMap<>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 调用service方法根据类别查询小区列表
			List<Map<String, Object>> concernVillageList = appUserService.findAppUserVillageByType(appUserId, ConstantStr.VILLAGE_TYPE_1);
			// 调用service方法根据类别查询小区列表
			List<Map<String, Object>> residentVillageList = appUserService.findAppUserVillageByType(appUserId, ConstantStr.VILLAGE_TYPE_2);
			// 封装关注小区列表
			data.put("concernVillageList", concernVillageList);
			// 封装常住小区列表
			data.put("residentVillageList", residentVillageList);
			// 向map中放入详情数据用于返回
			map.put("data", data);
			// 返回相应结果信息
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 添加我的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午11:35:59
	 * @param appUserId
	 * @param safekey
	 * @param villageId
	 * @param villageType
	 * @return
	 */
	@RequestMapping("/addMyVillage")
	public @ResponseBody String addMyVillage(@RequestParam Long appUserId, @RequestParam String safeKey, 
			@RequestParam Long villageId, @RequestParam String villageType) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 判断用户是否被删除
			AppUser original = appUserService.get(appUserId);
			if (original == null) { // 如果已经被删除
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200049), null);
			}
			// 判断用户和小区关系是否存在
			AppUserVillage appUserVillageTemp = appUserService.getAppUserVillageByUser(appUserId, villageId, villageType);
			if (appUserVillageTemp != null) { // 如果已经存在
				// 返回操作失败信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200048), null);
			}
			// 创建Model对象用于封装数据
			AppUserVillage appUserVillage = new AppUserVillage();
			// 设置用户id
			appUserVillage.setUserId(appUserId);
			// 设置小区id
			appUserVillage.setVillageId(villageId);
			// 设置小区类型
			appUserVillage.setVillageType(villageType);
			// 更新共通属性
			addAttr(appUserVillage);
			// 调用service方法插入用户小区信息
			boolean result = appUserService.addAppUserVillage(appUserVillage);
			if (result) { // 如果插入成功
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200044), map);
			} else { // 如果插入失败
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200045), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 我的活动
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午9:57:27
	 * @param appUserId
	 * @param safekey
	 * @return
	 */
	@RequestMapping("/myActivity")
	public @ResponseBody String myActivity(@RequestParam Long appUserId, @RequestParam String safeKey) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			// 判断用户是否被删除
			AppUser appUser = appUserService.get(appUserId);
			if (appUser == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200049), null);
			} else {
				// 调用service方法根据参数查询活动信息
				List<ActivityBaoming> activityBaomingList = activityBaomingService.selectActivityCollectionByUserId(appUserId);
				for (ActivityBaoming ab:activityBaomingList) { // 遍历数据
					// 创建Map变量用于封装返回数据
					Map<String, Object> data = new HashMap<>();
					if (ab.getActivityCollection() == null) {
						continue;
					}
					// 封装id
					data.put("id", ab.getActivityCollection().getId());
					// 封装活动标题
					data.put("title", ab.getActivityCollection().getTitle());
					// 封装活动时间
					data.put("startTime", ab.getActivityCollection().getStartTime());
					// 封装活动内容
					data.put("summary", ab.getActivityCollection().getSummary());
					// 向list中添加数据信息
					dataList.add(data);
				}
				// 向map中放入详情数据用于返回
				map.put("data", dataList);
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 获取用户信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午9:30:05
	 * @param appUserId
	 * @param safekey
	 * @return
	 */
	@RequestMapping("/myMessage")
	public @ResponseBody String myMessage(@RequestParam Long appUserId, @RequestParam String safeKey) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装数据
		Map<String, Object> data = new HashMap<>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 判断用户是否被删除
			AppUser appUser = appUserService.get(appUserId);
			if (appUser == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200049), null);
			} else {
				// 封装用户昵称
				data.put("nickName", StringUtils.isEmpty(appUser.getNickName())?appUser.getMoblie(): appUser.getNickName());
				// 封装用户名字
				data.put("name", StringUtils.isEmpty(appUser.getNickName())?"": appUser.getNickName());
				// 封装用户真实姓名
				data.put("realName", StringUtils.isEmpty(appUser.getRealName())?"": appUser.getRealName());
				// 封装用户性别
				data.put("sex", StringUtils.isEmpty(appUser.getSex())?"-1": appUser.getSex());
				// 封装用户生日
				data.put("birthday", StringUtils.isEmpty(appUser.getBirthDay())?"": appUser.getBirthDay());
				// 封装用户家庭住址
				data.put("address", StringUtils.isEmpty(appUser.getAddress())?"": appUser.getAddress());
				// 封装用户绑定手机号
				data.put("mobile", appUser.getMoblie());
				// 封装用户头像
				data.put("headPortrait", appUser.getHeadPortrait());
				// 封装用户绑定邮箱
				data.put("email", StringUtils.isEmpty(appUser.getEmail())?"": appUser.getEmail());
				// 封装用户证件类型
				data.put("certificateType", StringUtils.isEmpty(appUser.getCertificateType())?"1": appUser.getCertificateType());
				// 封装用户证件号
				data.put("certificateNumber", StringUtils.isEmpty(appUser.getCertificateNumber())?"": appUser.getCertificateNumber());
				// 向map中放入详情数据用于返回
				map.put("data", data);
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 更新用户信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午9:22:19
	 * @param appUserId
	 * @param safekey
	 * @param appUser
	 * @return
	 */
	@RequestMapping("/completeMessage")
	public @ResponseBody String completeMessage(@RequestParam Long appUserId, @RequestParam String safeKey, 
			@ModelAttribute AppUser appUser, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 判断用户是否被删除
			AppUser original = appUserService.get(appUserId);
			if (original == null) {
				Object[] attribute = { "用户信息" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute), null);
			}
			// 判断用户是否有手机
			if (StringUtils.isNotBlank(original.getMoblie())) {
				if (appUser.getMoblie() != null && original.getMoblie().equals(appUser.getMoblie())) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200073), null);
				} else {
					appUser.setMoblie(null);
				}
			}
			if ("GET".equals(request.getMethod())) {
				/* 转码 */
				if (StringUtils.isNotBlank(appUser.getNickName())) {
					appUser.setNickName(new String(appUser.getNickName().getBytes("iso8859-1"), "UTF-8"));
				}
				if (StringUtils.isNotBlank(appUser.getAddress())) {
					appUser.setAddress(new String(appUser.getAddress().getBytes("iso8859-1"), "UTF-8"));
				}
				if (StringUtils.isNotBlank(appUser.getRealName())) {
					appUser.setRealName(new String(appUser.getRealName().getBytes("iso8859-1"), "UTF-8"));
				}
			}
			// 判断昵称的长度
			if (StringUtils.isNotBlank(appUser.getNickName())) {
				if (length(UnescapeUtil.unescape(appUser.getNickName())) > 24) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200072), null);
				}
			}
			// 判断真实姓名的长度
			if (StringUtils.isNotBlank(appUser.getRealName())) {
				if (length(appUser.getRealName()) > 34) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200074), null);
				}
			}
			// 判断地址的长度
			if (StringUtils.isNotBlank(appUser.getAddress())) {
				if (length(appUser.getAddress()) > 200) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200075), null);
				}
			}
			// 设置用户id
			appUser.setId(appUserId);
			// 设置更新回数
			appUser.setUpdEac(original.getUpdEac());
			// 更新共通属性
			editAttr(appUser);
			// 调用service方法更新用户信息
			int result = appUserService.updateSelective(appUser);
			
			if (result > 0) { // 如果更新成功
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200042), map);
			} else { // 如果更新失败
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200043), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 我要合作
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午6:23:54
	 * @param cooperation
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperation")
	public @ResponseBody String cooperation(@ModelAttribute Cooperation cooperation, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			// 如果是get请求中文会乱码,需要进行转码
			if ("GET".equals(request.getMethod())) { // get请求进行转码
				if (StringUtils.isNotBlank(cooperation.getRealName())) {
					cooperation.setRealName(new String(cooperation.getRealName().getBytes("iso8859-1"), "UTF-8"));
				}
				if (StringUtils.isNotBlank(cooperation.getContent())) {
					cooperation.setContent(new String(cooperation.getContent().getBytes("iso8859-1"), "UTF-8"));
				}
			}
			// 设置共通属性
			addAttr(cooperation);
			// 调用service方法插入建议
			int result = cooperationService.saveSelective(cooperation);
			
			if (result > 0) { // 如果插入成功
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200040), map);
			} else { // 如果插入失败
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200041), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 投诉建议
	 * @author:kangtianyu
	 * @CreateDate:2016年8月23日 下午6:23:40
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/complain")
	public @ResponseBody String complain(@RequestParam String content, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			// 判断评论内容是否为空
			if (StringUtils.isBlank(content)) { // 如果为空
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200039), null);
			} else { // 如果是get请求中文会乱码,需要进行转码
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					content = new String(content.getBytes("iso8859-1"), "UTF-8");
				}
			}
			// 创建Model变量用于插入数据
			Suggestion suggestion = new Suggestion();
			//解密
			suggestion.setContent(content);
			// 设置共通属性
			addAttr(suggestion);
			// 调用service方法插入建议
			int result = suggestionService.saveSelective(suggestion);
			
			if (result > 0) { // 如果插入成功
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200037), map);
			} else { // 如果插入失败
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200038), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 关于我们
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 上午10:46:12
	 * @return
	 */
	@RequestMapping("/aboutus")
	public @ResponseBody String aboutus() {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装数据
		Map<String, Object> data = new HashMap<String, Object>();
		
		try {
			// 封装数据
			data.put("url", ABOUT_US_URL);
			
			// 向map中放入关于我们链接用于返回
			map.put("data", data);
			// 返回相应结果信息
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 关于我们界面
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 上午10:45:58
	 * @return
	 */
	@RequestMapping("/aboutusDetail")
    public String aboutusDetail() {
		// 跳转界面
        return ABOUT_US;
    }
	
	/**
	 * @Title
	 * @Description 删除我的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 下午5:57:42
	 * @param appUserId
	 * @param safeKey
	 * @param villageId
	 * @param villageType
	 * @return
	 */
	@RequestMapping("/removeMyVillage")
	public @ResponseBody String removeMyVillage(@RequestParam Long appUserId, @RequestParam String safeKey, 
			@RequestParam Long villageId, @RequestParam String villageType) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 判断用户是否被删除
			AppUser original = appUserService.get(appUserId);
			if (original == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200049), null);
			}
			// YK 2016-09-24 验证数据库用户与小区的关系是否删除 begin
			AppUserVillage originalUserVillage = appUserService.getAppUserVillageByUser(appUserId, villageId, villageType);
			if(originalUserVillage == null){
				Object []arr = {"您关注或常驻小区数据"};
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003,arr), null);
			}
			// YK 2016-09-24 验证数据库用户与小区的关系是否删除 end
			// 创建Model对象用于封装数据
			AppUserVillage appUserVillage = new AppUserVillage();
			// 设置用户id
			appUserVillage.setUserId(appUserId);
			// 设置小区id
			appUserVillage.setVillageId(villageId);
			// 设置小区类型
			appUserVillage.setVillageType(villageType);
			// 更新共通属性
			addAttr(appUserVillage);
			// 调用service方法插入用户小区信息
			boolean result = appUserService.removeAppUserVillage(appUserVillage);
			if (result) { // 如果删除成功
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200046), map);
			} else { // 如果删除失败
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200047), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * @Title
	 * @Description 上传头像
	 * @author:kangtianyu
	 * @CreateDate:2016年9月7日 上午10:34:23
	 * @param appUserId
	 * @param safeKey
	 * @param headPortrait
	 * @return
	 */
	@RequestMapping("/uploadHeadPortrait")
	public @ResponseBody String uploadHeadPortrait(@RequestParam Long appUserId, @RequestParam String safeKey, 
			@RequestParam MultipartFile headPortrait) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否登录
		if (!isLogin(safeKey)) {
			if (StringUtils.isNotBlank(safeKey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 判断用户是否被删除
			AppUser original = appUserService.get(appUserId);
			if (original == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200049), null);
			}
			// 判断当前文件是否被上传
			if(headPortrait.getSize() <= 0) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200057), null);
			}
			/* 从共通文件中获取上传文件所需信息 */
			String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_APPMINE);
			String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_APPMINE);
			// 生成新的文件名
			String newFileName = UUID.randomUUID() + headPortrait.getOriginalFilename().substring(headPortrait.getOriginalFilename().indexOf("."));
			newFileName = newFileName.replaceAll("\"", "");
			// 上传文件并获取是否成功的结果
			boolean rs = FileUtil.uploadfile(headPortrait, realpath, newFileName);
			if(rs){ // 如果文件上传成功
				// 获取文件上传后的相对路径
				newFileName = httppath+newFileName;
			} else {
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200058), map);
			}
			// 创建用户对象
			AppUser appUser = new AppUser();
			// 设置用户id
			appUser.setId(appUserId);
			// 设置用户头像
			appUser.setHeadPortrait(newFileName);
			// 设置更新回数
			appUser.setUpdEac(original.getUpdEac());
			// 更新共通属性
			editAttr(appUser);
			// 调用service方法更新用户信息
			int result = appUserService.updateSelective(appUser);
			
			if (result > 0) { // 如果更新成功
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200059), map);
			} else { // 如果更新失败
				// 返回相应结果信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200058), map);
			}
			
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
		
	}
	
	/**
	 * 通用的格式化返回值方法
	 * 
	 * @Title
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年8月1日 下午3:13:29
	 * @param resultCode
	 * @param msg
	 * @param map
	 * @return
	 */
	private String fromObject(Integer resultCode, String msg, Map<String, Object> map) {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put("resultCode", resultCode);
		map.put("msg", msg);
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * @Title
	 * @Description 获取客户端真实ip
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 上午9:21:26
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	private void addAttr(AppUserVillage _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private void editAttr(AppUser _temp) {
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
	}
	
	private void addAttr(Cooperation _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private void addAttr(Suggestion _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private Boolean isLogin(String safekey) {
		AppUser appUser = appUserService.selectBySafekey(safekey);
		return appUser != null ? true : false;
	}
	
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
	
}
