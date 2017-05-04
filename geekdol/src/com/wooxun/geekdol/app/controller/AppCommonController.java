package com.wooxun.geekdol.app.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.common.BigDecimalUtil;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.HttpRequestUtils;
import com.wooxun.geekdol.common.MD5Util;
import com.wooxun.geekdol.common.ShortMessage;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendService;
import com.wooxun.geekdol.hbridge.model.AdmNewsflash;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernment;
import com.wooxun.geekdol.hbridge.model.NetizenSecurity;
import com.wooxun.geekdol.hbridge.service.AdmNewsflashService;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentService;
import com.wooxun.geekdol.hbridge.service.NetizenSecurityService;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentVo;
import com.wooxun.geekdol.hmedia.model.ActivityCollection;
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.service.ActivityCollectionService;
import com.wooxun.geekdol.hmedia.service.AdvertService;
import com.wooxun.geekdol.hmedia.service.IntimateNewsService;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.AppVersion;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.AppUserService;
import com.wooxun.geekdol.system.service.AppVersionService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.AppLunboVo;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description 手机端共通方法
 * @version 1.0.0
 * @Author HDL
 * @CreateDate 2016年7月23日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. QZG 2016年7月23日 下午2:01:37 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("/appCommon")
public class AppCommonController extends BaseController {

	private final String FILE_PATH = "D:/work/upload/log/";

	/** 贴心报详情页 */
	private static final String SHARE_DETAIL = "appCommon/shareMessage";
	/** 用户协议 */
	private static final String AGREEMENT = "appmine/agreement";

	@Autowired
	private GoodsRecommendService<GoodsRecommend> goodsRecommendService;
	@Autowired
	private AppUserService<AppUser> appUserService;
	@Autowired
	private VillageService<Village> villageService;
	@Autowired
	private AppVersionService<AppVersion> appVersionService;

	@Autowired
	private AdvertService<Advert> advertService;

	@Autowired
	private IntimateNewsService<IntimateNews> intimateNewsService;

	@Autowired
	private ActivityCollectionService<ActivityCollection> activityCollectionService;

	@Autowired
	private AdmNewsflashService<AdmNewsflash> admNewsflashService;

	@Autowired
	private NetizenSecurityService<NetizenSecurity> netizenSecurityService;
	
	@Autowired
	private IncorruptGovernmentService<IncorruptGovernment> incorruptGovernmentService;

	/**
	 * 
	 * @Title
	 * @Description 心通桥调用接口 删除本地系统app用户
	 * @author:王肖东
	 * @CreateDate:2016年8月19日 下午12:04:16
	 * @param flag
	 * @return
	 */
	@RequestMapping("/deleteAppUserFromX")
	public @ResponseBody String deleteAppUserFromX(@RequestParam int uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "用户" };
		try {
			// 返回封装失败数据信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			// 返回封装的失败数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002, arr));
			// 根据uid查询出用户
			// AppUser original = appUserService.selectAppUserByUid(uid);
			// AppUser appUser = new AppUser();
			// appUser.setUpdId(getUserId());
			// appUser.setUid(uid);
			// appUser.setUpdEac(original.getUpdEac());
			// 编辑共通属性
			// editAttr(appUser);
			// 直接物理删除
			boolean result = appUserService.deleteAppUserByUid2(uid);
			if (result) {
				map.put("resultCode", ConstantStr.INFO100003);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
			}

			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			// 控制台打印异常
			e.printStackTrace();
			// 封装失败信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002, arr) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * 
	 * @Title
	 * @Description app上部图片轮播接口
	 * @author:王肖东
	 * @CreateDate:2016年8月19日 下午12:04:16
	 * @param flag
	 * @return
	 */
	@RequestMapping("/pictureLunbo")
	public @ResponseBody String pictureLunbo(String flag, Long villageId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "轮播图" };
		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001, arr));
			AppLunboVo appLunboVo = new AppLunboVo();
			appLunboVo.setModuleCode(flag);
			/*
			 * appLunboVo.setAdverType("1"); appLunboVo.setLinkType("4");
			 */
			appLunboVo.setBeginFlag(ConstantStr.Advertisement_Status);
			appLunboVo.setVillageId(villageId);
			List<Advert> advertList = advertService.selectPic(appLunboVo);
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (int i = 0; i < advertList.size(); i++) {
				Map<String, String> map2 = new HashMap<String, String>();
				map2.put("adverPic", advertList.get(i).getAdverPic());
				map2.put("linkContenturl", advertList.get(i).getLinkContenturl());
				map2.put("advertTitle", advertList.get(i).getAdvertTitle());
				list.add(map2);
			}
			map.put("data", list);
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			// 控制台打印异常
			e.printStackTrace();
			// 封装失败信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002, arr) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/***
	 * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("fileUpload")
	public String fileUpload(@RequestParam("logFile") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		String filename = request.getParameter("fileName");
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = FILE_PATH + filename;
				// 转存文件
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 重定向
		return null;
	}

	/**
	 * 
	 * @Title
	 * @Description 登录
	 * @author:QZG
	 * @CreateDate:2016年7月25日 下午3:55:30
	 * @param moblie
	 *            (手机号 或者 中原网用户名)
	 * @param pwd
	 *            密码
	 * @param jPushID
	 *            推送码
	 * @return
	 */
	@RequestMapping("login")
	public @ResponseBody String login(@RequestParam String moblie, @RequestParam String pwd,
			@RequestParam String jPushID, HttpServletRequest request) {
		// 此mobile可能为手机号 也可能为用户编码

		// 如果是get请求中文会乱码,需要进行转码
		if ("GET".equals(request.getMethod())) {
			// get请求进行转码
			try {
				moblie = new String(moblie.getBytes("iso8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建appUser对象用户封装查询条件
		AppUser appUser = new AppUser();
		// 传入手机号参数
		appUser.setMoblie(moblie);
		// 传入加密密码参数
		appUser.setPwd(MD5Util.MD5(pwd));
		try {
			// 判断手机号码是否为空
			if (StringUtils.isBlank(moblie)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200016));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}
			// 判断密码是否为空
			if (StringUtils.isBlank(pwd)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200017));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}

			// 登录操作 返回值大于0 登录成功
			int res = appUserService.login(appUser);
			// 登录成功
			if (res > 0) {
				// 返回用户信息
				appUser = appUserService.select(moblie);
				// 根据当前时间和手机号生成鉴权key
				String safekey = MD5Util.MD5NR(DateUtil.nowTime() + moblie);
				// 鉴权key存入appUser类
				appUser.setSafekey(safekey);
				// 设置pushId
				appUser.setPushId(jPushID);
				// 调用更新方法
				editAttr(appUser);
				// 更新appUser类
				appUserService.updateAppUser(appUser);
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200018));
				// 封装返回数据
				map.put("data", appUser);
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			} else {
				// 判断是否存在该手机号 返回值大于0 即存在该手机号或者用户(包含禁用和启用的数据)
				int reCount = appUserService.selectByMobileAll(moblie);
				// 后台存在手机号
				if (reCount > 0) {
					// 当手机号启用时(查启用的)
					int reCoun = appUserService.selectByMobile(moblie);
					if (reCoun > 0) {
						// 登录失败原因为密码错误
						map.put("resultCode", ConstantStr.APP_CODE403);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200055));
					} else// 当手机号
					{ // 登录失败原因为账号被禁用
						map.put("resultCode", ConstantStr.APP_CODE403);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200060));
					}
					// 返回JSON数据
					return JSONObject.fromObject(map).toString();
				} else {
					// 有可能为心通桥老用户登录app
					JSONObject js = new JSONObject();
					js.put("username", moblie);
					js.put("password", MD5Util.MD5XIAOXIE(pwd));
					js.put("flag", MD5Util.MD5XIAOXIE(MD5Util.MD5XIAOXIE(pwd) + ConstantStr.XQT_KEY));
					JSONObject jsonObject = HttpRequestUtils.httpPost(ConstantStr.Hbridge_LOGIN, js);
					int codeResult = (int) jsonObject.get("code"); // 返回的code值
					// 心通桥存在此用户
					if (0 == codeResult) {
						String emailString = (String) jsonObject.get("email"); // 返回的email
						int uid = (int) jsonObject.get("uid"); // 返回的uid
						// 心通桥存在此用户(geekdol系统保存此用户) 0代表成功
						AppUser saveAppUser = new AppUser();
						saveAppUser.setEmail(emailString);
						saveAppUser.setUid(uid);
						saveAppUser.setUserCode(moblie);
						saveAppUser.setPwd(MD5Util.MD5(pwd));
						saveAppUser.setStatus("0");
						String safekey = MD5Util.MD5NR(DateUtil.nowTime() + moblie);
						saveAppUser.setSafekey(safekey);
						saveAppUser.setPushId(jPushID);
						// 注册用户用户性别默认赋值-1
						appUser.setSex(ConstantStr.DEFAULTSEX);
						// 随机生成长度为10的字母与数字组合用户昵称
						StringBuffer name = getString(10);
						// StringBuffer转为String型
						String nickName = name.toString();
						// 设置用户昵称
						appUser.setNickName("XTQfans_"+nickName);
						// 编辑共通属性 addAttr(saveAppUser);
						addAttr(saveAppUser);
						int count = appUserService.saveSelective(saveAppUser); // geekdol系统保存成功
						if (count > 0) {
							map.put("resultCode", ConstantStr.APP_CODE200);
							map.put("msg", ComDefine.getMsg(ConstantStr.INFO200018)); //
							map.put("data", saveAppUser); // 返回JSON return
						}
					} else {
						// 吉客多 中原网都不存在此用户
						map.put("resultCode", ConstantStr.APP_CODE403);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO100049));
					}
					return JSONObject.fromObject(map).toString();
				}
			}

		} catch (Exception e) {
			// 控制台打印异常
			e.printStackTrace();
			// 封装返回信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * 
	 * @Title
	 * @Description app用户注册接口
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午11:02:45
	 * @param moblie
	 *            用户手机号码
	 * @param code
	 *            手机验证码
	 * @param pwd
	 *            密码
	 * @return
	 */
	@RequestMapping("register")
	public @ResponseBody String register(@RequestParam String moblie, @RequestParam String code,
			@RequestParam String pwd, @RequestParam String type) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建appUser类用于封装注册参数
		AppUser appUser = new AppUser();
		// 创建Model用于获取原数据
		AppUser original = null;
		try {
			// 判断手机号码或者用户名是否为空
			if (StringUtils.isBlank(moblie)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200016));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			if (ConstantStr.SEND_TYPE_REGISTER.equals(type)) { // 如果是注册
				// 判断该电话号码是否被注册 返回值大于0 该电话号码已被注册
				int reCount = appUserService.selectByMobile(moblie);

				// 电话号码已被注册
				if (reCount > 0) {
					// 封装返回信息
					map.put("resultCode", ConstantStr.APP_CODE402);
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					// 返回JSON数据
					return JSONObject.fromObject(map).toString();
				}
			} else if (ConstantStr.SEND_TYPE_FORGET.equals(type)) { // 如果是忘记密码
				// 获取原信息
				original = appUserService.selectAppUserByMobile(moblie);
			}

			// 判断密码是否为空
			if (StringUtils.isBlank(pwd)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200017));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}

			// 调用service方法插入验证码记录
			boolean result = appUserService.findPhoneCode(moblie, code, type);
			if (!result) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200032));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}

			// 传入加密密码参数
			appUser.setPwd(MD5Util.MD5(pwd));

			if (ConstantStr.SEND_TYPE_REGISTER.equals(type)) { // 如果是注册
				// 先调用心通桥接口保存用户
				JSONObject jo = new JSONObject();
				jo.put("username", moblie);
				jo.put("email", moblie + ConstantStr.XTQ_EMAIL_HOUZHUI);
				jo.put("password", pwd);
				jo.put("mobile", moblie);
				jo.put("flag", MD5Util.MD5XIAOXIE(pwd + ConstantStr.XQT_KEY));

				JSONObject jsonObject = HttpRequestUtils.httpPost(ConstantStr.Hbridge_SAVEUSER, jo);
				int codeResult = (int) jsonObject.get("code");
				int uid = (int) jsonObject.get("uid");
				// 心通桥用户注册失败
				if (1 == codeResult) {
					// 用户名不合法
					if (-1 == uid) {
						// 用户名不合法
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					} else if (-2 == uid) {
						// 包含要允许注册的词语
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					} else if (-3 == uid) {
						// 用户名已经存在
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					} else if (-4 == uid) {
						// Email 格式有误
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					} else if (-5 == uid) {
						// Email 不允许注册
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					} else if (-6 == uid) {
						// 该 Email 已经被注册
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
					} else if (-7 == uid) {
						// 用户为空
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200016));
					} else if (-8 == uid) {
						// email为空
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200016));
					} else if (-9 == uid) {
						// 密码为空
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200017));
					} else if (-10 == uid) {
						// 手机号(11位)错误
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200016));
					} else if (-11 == uid) {
						// flag错误
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200016));
					} else {
						// 其他错误
						map.put("resultCode", ConstantStr.APP_CODE602);
						map.put("msg", ComDefine.getMsg(ConstantStr.INFO200069));
					}
					// 返回JSON数据
					return JSONObject.fromObject(map).toString();
				}

				// 传入中原网返回的uid标识
				appUser.setUid(uid);
				// 传入手机号参数
				appUser.setMoblie(moblie);
				// 设置是否启用，默认启用
				appUser.setStatus(ConstantStr.QY_FLAG);
				// 设置是否为内参人员，默认非内参
				appUser.setStaffFlag(ConstantStr.STAFFFLAG_N);
				// 注册用户用户性别默认赋值-1
				appUser.setSex(ConstantStr.DEFAULTSEX);
				// 随机生成长度为10的字母与数字组合用户昵称
				StringBuffer name = getString(10);
				// StringBuffer转为String型
				String nickName = name.toString();
				// 设置用户昵称
				appUser.setNickName("XTQfans_"+nickName);
				// 编辑共通属性
				addAttr(appUser);
				// 保存注册信息 返回值大于0 注册成功
				int count = appUserService.saveSelective(appUser);
				// 注册失败
				if (count == 0) {
					// 封装注册失败信息
					map.put("resultCode", ConstantStr.APP_CODE403);
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO200014));
					// 返回JSON数据
					return JSONObject.fromObject(map).toString();
				}
			} else if (ConstantStr.SEND_TYPE_FORGET.equals(type)) { // 如果是忘记密码
				// 设置id
				appUser.setId(original.getId());
				// 设置更新回数
				appUser.setUpdEac(original.getUpdEac());
				// 编辑共通属性
				editAttr(appUser);
				// 保存注册信息 返回值大于0 注册成功
				int count = appUserService.updateSelective(appUser);
				// 操作失败
				if (count == 0) {
					// 封装注册失败信息
					map.put("resultCode", ConstantStr.APP_CODE403);
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO200014));
					// 返回JSON数据
					return JSONObject.fromObject(map).toString();
				}
			}
			// 成功
			// 封装返回信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200033));
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
			// 系统异常
		} catch (Exception e) {
			// 控制台打印异常信息
			e.printStackTrace();
			// 封装返回信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 发送验证码
	 * @author:QZG
	 * @CreateDate:2016年7月26日 上午10:18:12
	 * @param moblie
	 *            手机号
	 * @return
	 */
	@RequestMapping("getCode")
	public @ResponseBody String getCode(@RequestParam String moblie, @RequestParam String type,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 判断手机号码是否为空
			if (StringUtils.isBlank(moblie)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200012));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}
			if (ConstantStr.SEND_TYPE_FORGET.equals(type)) { // 如果是忘记密码请求
				// 判断用户是否被删除
				AppUser original = appUserService.getAppUserByPhone(moblie);
				if (original == null) { // 如果已经被删除
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200049), null);
				}
			}

			// 获取用户ip
			String userIp = getIpAddr(request);

			// 此service方法完成三个功能1.判断ip是否达到每天限制条数 2.判断手机号是否达到每天发送限制次数
			Map<String, Object> judgeResult = appUserService.limitCode(moblie, userIp);
			if ((boolean) judgeResult.get("isMax")) { // 如果达到限制
				/* 向map中封装达到限制的信息 */
				map.put("resultCode", ConstantStr.APP_CODE403);
				map.put("msg", (String) judgeResult.get("msg"));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}

			// 判断该电话号码是否被注册 返回值大于0 该电话号码已被注册
			int reCount = appUserService.selectByMobile(moblie);

			// 如果是注册请求
			if (ConstantStr.SEND_TYPE_REGISTER.equals(type) && reCount > 0) { // 如果是注册请求且账号已被注册
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE402);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200020));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			} else { // 账号未被注册
				// 发送验证码
				Map<String, Object> sendResult = ShortMessage.sendSMSGet_modify(moblie, "", type);
				if ((int) sendResult.get("sendCode") <= 0) {
					// 封装返回信息
					map.put("resultCode", ConstantStr.APP_CODE500);
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO200031));
					// 反复JSON数据
					return JSONObject.fromObject(map).toString();
				}
				// 获取发送的验证码
				String identifyingCode = (String) sendResult.get("identifyingCode");
				// 调用service方法插入验证码记录
				boolean result = appUserService.addPhoneCode(moblie, identifyingCode, userIp, type);
				if (!result) {
					// 封装返回信息
					map.put("resultCode", ConstantStr.APP_CODE500);
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002));
					// 反复JSON数据
					return JSONObject.fromObject(map).toString();
				}
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200021));
				// 反复JSON数据
				return JSONObject.fromObject(map).toString();
			}
			// 系统异常
		} catch (Exception e) {
			// 控制台打印异常信息
			e.printStackTrace();
			// 封装返回信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 返回安卓版本信息
	 * @author:张洋
	 * @CreateDate:2016年8月1日16:16:01
	 * @param
	 * @return
	 */
	@RequestMapping("getAppVersion")
	public @ResponseBody String getAppVersion() {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "安卓版本信息" };
		try {
			Long id = 1L;
			// 根据ID返回版本信息
			AppVersion appVersion = appVersionService.get(id);
			map.put("downloadUrl", appVersion.getAndroid());
			map.put("appVersionName", appVersion.getVersionName());
			map.put("appVersion", appVersion.getVersionCode());
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001, arr), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 返回当前定位小区及附近小区
	 * @author:张洋
	 * @CreateDate:2016年8月1日14:46:52
	 * @param moblie
	 * @return
	 */
	@RequestMapping("getNearVillage")
	public @ResponseBody String getNearVillage(@RequestParam Long appUserId, @RequestParam String safekey,
			@RequestParam String longitude, @RequestParam String latitude) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "附近小区" };
		try {
			if (!isLogin(safekey)) {
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 判断手机号码是否为空
			if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
			}
			// 计算出符合条件的经纬度范围，先将定义的距离范围换算成经纬度范围，然后算出经纬度的上下限，根据这个上下限初步筛选数据库数据
			String longDeff = String.valueOf(BigDecimalUtil.mul(String.valueOf(ConstantStr.SCOPE / 100),
					String.valueOf(ConstantStr.LONG_100M)));
			String latDeff = String.valueOf(BigDecimalUtil.mul(String.valueOf(ConstantStr.SCOPE / 100),
					String.valueOf(ConstantStr.LAT_100M)));
			Double longBegin = BigDecimalUtil.sub(longitude, longDeff);
			Double longEnd = BigDecimalUtil.add(longitude, longDeff);
			Double latBegin = BigDecimalUtil.sub(latitude, latDeff);
			Double latEnd = BigDecimalUtil.add(latitude, latDeff);
			Map<String, String> queryMap = new HashMap<>();
			queryMap.put("longitudeBegin", longBegin.toString());
			queryMap.put("longitudeEnd", longEnd.toString());
			queryMap.put("latitudeBegin", latBegin.toString());
			queryMap.put("latitudeEnd", latEnd.toString());
			List<Village> list = villageService.selectByLongAndLat(queryMap);
			List<Village> list2 = new ArrayList<>();
			Double deffest = 0D;
			Village nearest = new Village();
			// 计算当前经纬度与数据库中的经纬度距离，找出在定义的范围内的数据，并将最近的数据放入第一条，当做当前小区传回
			for (int i = 0; i < list.size(); i++) {
				Village v = list.get(i);
				Double deff = Distance(longitude, latitude, v.getVillageLongitude(), v.getVillageLatitude());
				Double d = BigDecimalUtil.sub(deff.toString(), ConstantStr.SCOPE.toString());
				if (d <= deffest) {
					deffest = d;
					nearest = v;
				}
				if (d <= 0) {
					list2.add(v);
				}
			}
			if (list2.size() > 0) {
				if (!list2.get(0).equals(nearest)) {
					list2.add(list2.get(0));
					list2.remove(nearest);
					list2.set(0, nearest);
				}
			} else {

			}
			// YK 查询常驻小区 begin
			VillageVo villageVo = new VillageVo();
			// 设置查找条件常驻人id
			villageVo.setUserId(appUserId);
			// 设置查找条件常驻类型
			villageVo.setVillageType(ConstantStr.VILLAGE_TYPE_2);
			// 查询常驻小区的精度、纬度
			Village resident = villageService.selectByUserIdAndVillageType(villageVo);
			// YK 查询常驻小区 end
			// 格式化数据
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if (resident != null) {
				Map<String, Object> residentMap = new HashMap<>();
				residentMap.put("villageId", resident.getVillageId());
				residentMap.put("provinceId", resident.getProvinceId());
				residentMap.put("provinceName", resident.getProvince().getProvinceName());
				residentMap.put("cityId", resident.getCityId());
				residentMap.put("cityName", resident.getCity().getCityName());
				residentMap.put("countyId", resident.getCountyId());
				residentMap.put("countyName", resident.getCounty().getCountyName());
				residentMap.put("communityId", resident.getCommunityId());
				residentMap.put("communityName", resident.getCommunity().getCommunityName());
				residentMap.put("villageName", resident.getVillageName());
				mapList.add(residentMap);
			}
			for (int i = 0; i < list2.size(); i++) {
				Map<String, Object> hm = new HashMap<>();
				hm.put("villageId", list2.get(i).getVillageId());
				hm.put("villageName", list2.get(i).getVillageName());
				hm.put("communityId", list2.get(i).getCommunity().getCommunityId());
				hm.put("communityName", list2.get(i).getCommunity().getCommunityName());
				hm.put("countyId", list2.get(i).getCounty().getCountyId());
				hm.put("countyName", list2.get(i).getCounty().getCountyName());
				hm.put("cityId", list2.get(i).getCity().getCityId());
				hm.put("cityName", list2.get(i).getCity().getCityName());
				hm.put("provinceId", list2.get(i).getProvince().getProvinceId());
				hm.put("provinceName", list2.get(i).getProvince().getProvinceName());
				mapList.add(hm);
			}
			map.put("data", mapList);
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001, arr), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002, arr) + e.getMessage(),
					null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 根据小区名搜索小区
	 * @author:张洋
	 * @CreateDate:2016年8月1日15:41:24
	 * @param villageName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryByParm")
	public @ResponseBody String queryByParm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "20" : request.getParameter("rows"));
		Object[] arr = { "小区" };
		String villageName = request.getParameter("villageName");
		if (StringUtil.isBlank(villageName)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		// 条件查询
		VillageVo searchVillage = new VillageVo();
		searchVillage.setVillageName(villageName);
		Map<String, Object> map = new HashMap<String, Object>();
		List<VillageVo> villageList = new ArrayList<VillageVo>();
		Long count = 0l;
		searchVillage.setPageFlag(true);
		searchVillage.setStartPage((page - 1) * rows);
		searchVillage.setEndPage(rows);
		searchVillage.setStatus(ConstantStr.QY_FLAG);
		count = villageService.queryCountByParams(searchVillage);

		if (count > 0) {
			villageList = villageService.queryVillageByParams(searchVillage);
		}
		map.put("data", villageList);
		return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001, arr), map);
	}

	/**
	 * @Title
	 * @Description 分享
	 * @author:kangtianyu
	 * @CreateDate:2016年9月2日 上午10:16:25
	 * @param id
	 * @param flag
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/share")
	public String share(@RequestParam Long id, @RequestParam String flag, Map<String, Object> map) throws Exception {
		// 创建分享链接变量用于返回
		String shareUrl = SHARE_DETAIL;
		// 根据不同的标志位进行不同的处理
		switch (flag) {
		case ConstantStr.SHAREFLAG_INTIMATENEWS: // 如果是分享贴心报
			// 根据id获取贴心报详情
			IntimateNews intimateNews = intimateNewsService.get(id);
			// 设置标题
			map.put("title", intimateNews.getTitle());
			// 设置来源
			map.put("source", intimateNews.getSource());
			// 设置发布时间
			map.put("publishTime", DateFormatUtils.format(intimateNews.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
			// 设置内容
			map.put("content", intimateNews.getContent());
			// 设置页面标题
			map.put("pageTitle", ConstantStr.SHARETITLE_INTIMATENEWS);
			break;
		case ConstantStr.SHAREFLAG_ACTIVITY: // 如果是分享活动汇
			// 根据id获取活动汇详情
			ActivityCollection activityCollection = activityCollectionService.getById(id);
			// 设置标题
			map.put("title", activityCollection.getTitle());
			// 设置来源
			map.put("source",
					StringUtils.isBlank(activityCollection.getActivityShopName()) ? "" : activityCollection
							.getActivityShopName());
			// 设置发布时间
			map.put("publishTime", DateFormatUtils.format(activityCollection.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
			// 设置内容
			map.put("content", activityCollection.getContent());
			// 设置页面标题
			map.put("pageTitle", ConstantStr.SHARETITLE_ACTIVITY);
			break;
		case ConstantStr.SHAREFLAG_ADMNEWS: // 如果是分享行政快报
			// 根据id获取行政快报详情
			AdmNewsflash admNewsflash = admNewsflashService.get(id);
			// 设置标题
			map.put("title", admNewsflash.getTitle());
			// 设置来源
			map.put("source", admNewsflash.getSource());
			// 设置发布时间
			map.put("publishTime", DateFormatUtils.format(admNewsflash.getNewsflashTime(), "yyyy-MM-dd HH:mm:ss"));
			// 设置内容
			map.put("content", admNewsflash.getContent());
			// 设置页面标题
			map.put("pageTitle", ConstantStr.SHARETITLE_ADMNEWS);
			break;
		case ConstantStr.SHAREFLAG_NETIZENSECURITY: // 如果是分享网安报
			// 根据id获取网安报详情
			NetizenSecurity netizenSecurity = netizenSecurityService.get(id);
			// 设置标题
			map.put("title", netizenSecurity.getTitle());
			// 设置来源
			map.put("source", netizenSecurity.getSource());
			// 设置发布时间
			map.put("publishTime", DateFormatUtils.format(netizenSecurity.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
			// 设置内容
			map.put("content", netizenSecurity.getContent());
			// 设置页面标题
			map.put("pageTitle", ConstantStr.SHARETITLE_NETIZENSECURITY);
			break;
		case ConstantStr.SHAREFLAG_INCORRUPTGOVERNMENT:
			IncorruptGovernmentVo incorruptGovernmentVo = new IncorruptGovernmentVo();
			incorruptGovernmentVo.setId(id);
			incorruptGovernmentVo = incorruptGovernmentService.findById(incorruptGovernmentVo);
			// 设置标题
			map.put("title", incorruptGovernmentVo.getTitle());
			// 设置来源
			map.put("source", incorruptGovernmentVo.getContentFrom());
			// 设置发布时间
			map.put("publishTime", DateFormatUtils.format(incorruptGovernmentVo.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
			// 设置内容
			map.put("content", incorruptGovernmentVo.getContent());
			// 设置页面标题
			map.put("pageTitle", ConstantStr.SHARETITLE_INCORRUPTGOVERNMENT);
			break;
		default:
			break;
		}
		return shareUrl;
	}

	/**
	 * @Title
	 * @Description 用户协议
	 * @author:张洋
	 * @CreateDate:2016年9月27日20:33:08
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/agreement")
	public String agreement(Map<String, Object> map) throws Exception {
		// 创建分享链接变量用于返回
		String shareUrl = AGREEMENT;
		return shareUrl;
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
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public double Distance(String longs1, String lats1, String longs2, String lats2) {
		double a, b, R;
		double long1 = Double.valueOf(longs1);
		double lat1 = Double.valueOf(lats1);
		double long2 = Double.valueOf(longs2);
		double lat2 = Double.valueOf(lats2);
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = Math.abs(lat1 - lat2);
		b = Math.abs(long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
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

	/**
	 * 
	 * @Title
	 * @Description 生成固定长度的子母与数字组合
	 * @author:QZG
	 * @CreateDate:2016年9月27日 下午3:32:53
	 * @param length
	 * @return
	 */
	public static StringBuffer getString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sbuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sbuffer.append(base.charAt(number));
		}
		return sbuffer;
	}

	/**
	 * 
	 * @Title
	 * @Description 增加记录时，所需要的共通属性
	 * @author:zhougp
	 * @CreateDate:2016年7月25日 上午10:34:58
	 * @param _temp
	 */
	private void addAttr(CommonEntity _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	/**
	 * 
	 * @Title
	 * @Description 更新实体类中 所需要的共通属性
	 * @author:QZG
	 * @CreateDate:2016年7月25日 上午10:33:45
	 * @param _temp
	 */
	private void editAttr(CommonEntity _temp) {
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
	}

	/**
	 * 
	 * @Title
	 * @Description 验证app用户是否登录
	 * @author:王肖东
	 * @CreateDate:2016年8月18日 下午2:55:21
	 * @param safekey
	 * @return
	 */
	private Boolean isLogin(String safekey) {
		AppUser appUser = appUserService.selectBySafekey(safekey);
		return appUser != null ? true : false;
	}

}
