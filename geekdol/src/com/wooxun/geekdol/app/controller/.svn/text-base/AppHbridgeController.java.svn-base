package com.wooxun.geekdol.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.MD5Util;
import com.wooxun.geekdol.common.MyCompartor;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendService;
import com.wooxun.geekdol.hbridge.model.AdmNewsflash;
import com.wooxun.geekdol.hbridge.model.CityDistrictNotice;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentComment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentCommentR;
import com.wooxun.geekdol.hbridge.model.IncorruptRecommend;
import com.wooxun.geekdol.hbridge.model.NetizenAcumen;
import com.wooxun.geekdol.hbridge.model.NetizenSecurity;
import com.wooxun.geekdol.hbridge.model.NoticeVillage;
import com.wooxun.geekdol.hbridge.model.Staff;
import com.wooxun.geekdol.hbridge.model.VillageNotice;
import com.wooxun.geekdol.hbridge.service.AdmNewsflashService;
import com.wooxun.geekdol.hbridge.service.CityDistrictNoticeService;
import com.wooxun.geekdol.hbridge.service.IncorruptCommentRecommendService;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentCommentRService;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentCommentService;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentService;
import com.wooxun.geekdol.hbridge.service.IncorruptRecommendService;
import com.wooxun.geekdol.hbridge.service.NetizenAcumentService;
import com.wooxun.geekdol.hbridge.service.NetizenSecurityService;
import com.wooxun.geekdol.hbridge.service.NoticeVillageService;
import com.wooxun.geekdol.hbridge.service.StaffService;
import com.wooxun.geekdol.hbridge.service.VillageNoticeService;
import com.wooxun.geekdol.hbridge.vo.AdmNewsflashVo;
import com.wooxun.geekdol.hbridge.vo.CityDistrictNoticeVo;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentRVo;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentVo;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentVo;
import com.wooxun.geekdol.hbridge.vo.NetizenSecurityVo;
import com.wooxun.geekdol.hbridge.vo.StaffVo;
import com.wooxun.geekdol.hbridge.vo.VillageNoticeVo;
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.service.AdvertService;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Keywords;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.AppUserService;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.KeywordsService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.SysdataVo;

/**
 * @Title
 * @Description 手机端心通桥方法
 * @version 1.0.0
 * @Author 张洋
 * @CreateDate 2016年8月1日15:57:31
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 张洋 2016年8月1日15:57:31 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("/appHbridge")
public class AppHbridgeController extends BaseController {

	@Autowired
	private GoodsRecommendService<GoodsRecommend> goodsRecommendService;
	@Autowired
	private AppUserService<AppUser> appUserService;
	@Autowired
	private AdvertService<Advert> advertService;
	@Autowired
	private AdmNewsflashService<AdmNewsflash> admNewsflashService;
	@Autowired
	private StaffService<Staff> staffService;
	@Autowired
	private SysDataService<SysData> sysDataService;
	@Autowired
	private CityDistrictNoticeService<CityDistrictNotice> cityDistrictNoticeService;
	@Autowired
	private VillageNoticeService<VillageNotice> villageNoticeService;
	@Autowired
	private NoticeVillageService<NoticeVillage> noticeVillageService;
	@Autowired
	private ProvinceService<Province> provinceService;
	@Autowired
	private CityService<City> cityService;
	@Autowired
	private CountyService<County> countyService;
	@Autowired
	private CommunityService<Community> communityService;
	@Autowired
	private VillageService<Village> villageService;
	@Autowired
	private UserService<User> userService;
	@Autowired
	private NetizenAcumentService<NetizenAcumen> netizenAcumentService;
	@Autowired
	private SysDataService<SysData> sysDateService;
	@Autowired
	private NetizenSecurityService<NetizenSecurity> netizenSecurityService;
	@Autowired
	private IncorruptGovernmentService<IncorruptGovernment> incorruptGovernmentService;
	@Autowired
	private IncorruptRecommendService<IncorruptRecommend> incorruptRecommendService;
	@Autowired
	private IncorruptGovernmentCommentService<IncorruptGovernmentComment> commentService;
	@Autowired
	private IncorruptCommentRecommendService<IncorruptCommentRecommend> commentRecommendService;
	@Autowired
	private IncorruptGovernmentCommentRService<IncorruptGovernmentCommentR> commentRService;
	@Autowired
	private KeywordsService<Keywords> keywordsService;

	/**
	 * 
	 * @Title
	 * @Description 获取网络问政接口
	 * @author:王肖东
	 * @CreateDate:2016年9月20日 下午12:30:42
	 * @param uid
	 * @param pwd
	 * @return
	 */
	@RequestMapping("getNetUrl")
	public @ResponseBody String getNetUrl(@RequestParam Long uid, @RequestParam String pwd) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String url = ConstantStr.Hbridge_WLWZ + "?pwd=" + StringUtils.lowerCase(pwd) + "&uid=" + uid + "&flag="
					+ MD5Util.MD5XIAOXIE(StringUtils.lowerCase(pwd) + ConstantStr.XQT_KEY) + "&xmt_app_login_in=1";
			// 返回的网络问政url(此接口先不做调用)
			// String wlwzUrl = HttpRequestUtils.httpGet(url);
			// 成功
			// 封装返回信息
			map.put("wlwzUrl", url);
			map.put("resultCode", ConstantStr.APP_CODE200);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));
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
	 * @Description 获取广告图片列表
	 * @author:张洋
	 * @CreateDate:2016年8月1日17:01:08
	 * @return
	 */
	@RequestMapping("getAdvert")
	public @ResponseBody String getAdvert() {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "广告图片" };
		try {
			// List<Map<String,Object>> hmList = new
			// ArrayList<Map<String,Object>>();
			Advert adv = new Advert();
			adv.setAdverType(ConstantStr.AD_TYPE_IMG);
			adv.setBeginFlag(ConstantStr.AD_IS_RUNING);
			List<Advert> list = advertService.selectByParm(adv);
			map.put("data", list);
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
	 * @Description 获取行政快报列表
	 * @author:张洋
	 * @CreateDate:2016年8月2日09:20:44
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getNewsflash")
	public @ResponseBody String getNewsflash(HttpServletRequest request, HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "行政快报" };
		try {
			// 条件查询
			String title = request.getParameter("title");
			if (title != null && title.trim().equals("")) {
				title = null;
			}
			List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
			AdmNewsflashVo admNewsflashVo = new AdmNewsflashVo();
			admNewsflashVo.setTitle(title);
			admNewsflashVo.setVerifyStatus(ConstantStr.VERIFY_YES);
			admNewsflashVo.setPageFlag(true);
			admNewsflashVo.setStartPage((page - 1) * rows);
			admNewsflashVo.setEndPage(rows);
			List<AdmNewsflash> list = admNewsflashService.queryListByParam(admNewsflashVo);
			// 封装数据传回
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> hm = new HashMap<>();
				hm.put("id", list.get(i).getId());
				hm.put("title", list.get(i).getTitle());
				hm.put("source", list.get(i).getSource());
				hm.put("scannedNumber", list.get(i).getScannedNumber());
				hmList.add(hm);
			}
			map.put("data", hmList);
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
	 * @Description 获取行政快报推荐列表
	 * @author:张洋
	 * @CreateDate:2016年8月2日09:20:44
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getNewsflashRecommended")
	public @ResponseBody String getNewsflashRecommended(HttpServletRequest request, HttpServletResponse response) {
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
        String id = request.getParameter("id");
		Object[] arr = { "行政快报推荐" };
		if (StringUtil.isBlank(pageStr) || StringUtil.isBlank(rowsStr) || StringUtil.isBlank(id)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 条件查询
			List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
			AdmNewsflashVo admNewsflashVo = new AdmNewsflashVo();
			admNewsflashVo.setVerifyStatus(ConstantStr.VERIFY_YES);
			admNewsflashVo.setPageFlag(true);
			admNewsflashVo.setStartPage((page - 1) * rows);
			admNewsflashVo.setEndPage(rows);
			admNewsflashVo.setOutId(Long.valueOf(id));
			List<AdmNewsflash> list = admNewsflashService.queryListByParamOrder(admNewsflashVo);
			// 封装数据传回
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> hm = new HashMap<>();
				hm.put("id", list.get(i).getId());
				hm.put("title", list.get(i).getTitle());
				hm.put("source", list.get(i).getSource());
				hm.put("publishTime", list.get(i).getPublishTime());
				hmList.add(hm);
			}
			map.put("data", hmList);
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
	 * @Description 获取政务内参推荐列表
	 * @author:张洋
	 * @CreateDate:2016年8月3日09:06:17
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getStaffRecommended")
	public @ResponseBody String getStaffRecommended(HttpServletRequest request, HttpServletResponse response) {
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		Object[] arr = { "政务内参推荐" };
		if (StringUtil.isBlank(pageStr) || StringUtil.isBlank(rowsStr)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 条件查询
			List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
			StaffVo staffVo = new StaffVo();
			staffVo.setPageFlag(true);
			staffVo.setStartPage((page - 1) * rows);
			staffVo.setEndPage(rows);
			staffVo.setPublishStatus(ConstantStr.FB);
			List<Staff> list = staffService.queryListByParamOrder(staffVo);
			// 封装数据传回
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> hm = new HashMap<>();
				hm.put("id", list.get(i).getId());
				hm.put("title", list.get(i).getTitle());
				hm.put("source", list.get(i).getSource());
				hm.put("publishTime", list.get(i).getPublishTime());
				hmList.add(hm);
			}
			map.put("data", hmList);
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
	 * @Description 查看行政快报详情
	 * @author:张洋
	 * @CreateDate:2016年8月2日10:27:00
	 * @param id
	 * @return
	 */
	@RequestMapping("viewNewsflash")
	public @ResponseBody String viewNewsflash(@RequestParam String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "行政快报详情" };
		try {
			if (StringUtils.isBlank(id)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
			}
			// 根据ID获取数据
			AdmNewsflash admNewsflash = admNewsflashService.get(Long.valueOf(id));
			if (admNewsflash == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 取出需要的数据
			Map<String, Object> data = new HashMap<>();
			data.put("title", admNewsflash.getTitle());
			data.put("verifyTime", admNewsflash.getVerifyTime());
			data.put("newsflashTime", admNewsflash.getNewsflashTime());
			data.put("source", admNewsflash.getSource());
			data.put("content", admNewsflash.getContent());
			// 摘要
			data.put("summary", admNewsflash.getSummary());
			// 分享链接
			data.put("shareUrl", ConstantStr.SHARE_URL + "?id=" + id + "&flag=" + ConstantStr.SHAREFLAG_ADMNEWS);
			map.put("data", data);
			// 更新浏览量
			AdmNewsflash anf = new AdmNewsflash();
			anf.setId(admNewsflash.getId());
			anf.setScannedNumber(admNewsflash.getScannedNumber() + 1);
			anf.setUpdEac(admNewsflash.getUpdEac() + 1);
			anf.setUpdId(admNewsflash.getUpdId());
			admNewsflashService.updateSelective(anf);
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
	 * @Description 获取政务内参列表
	 * @author:张洋
	 * @CreateDate:2016年8月2日11:00:50
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getStaff")
	public @ResponseBody String getStaff(HttpServletRequest request, HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		String staffLevel = request.getParameter("staffLevel");
		Object[] arr = { "政务内参列表" };
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (StringUtil.isAllBlank(staffLevel)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
			}
			// 条件查询
			List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
			StaffVo staffVo = new StaffVo();
			staffVo.setStaffLevel(staffLevel);
			staffVo.setPageFlag(true);
			staffVo.setStartPage((page - 1) * rows);
			staffVo.setEndPage(rows);
			List<Staff> list = staffService.queryListByParam(staffVo);
			// 查出内参类型，方便把编码翻译成汉字
			SysdataVo sysDataVo = new SysdataVo();
			sysDataVo.setType("STAFFTYPE");
			Map<String, String> valMap = new HashMap<>();
			List<SysData> sdList = sysDataService.querySysdataByBean(sysDataVo);
			for (int i = 0; i < sdList.size(); i++) {
				valMap.put(sdList.get(i).getValue(), sdList.get(i).getLable());
			}
			// 取出需要的数据
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> hm = new HashMap<>();
				hm.put("id", list.get(i).getId());
				hm.put("title", list.get(i).getTitle());
				hm.put("source", list.get(i).getSource());
				hm.put("content", list.get(i).getContent());
				hm.put("type", list.get(i).getType());
				hm.put("typeName", valMap.get(list.get(i).getType()));
				hmList.add(hm);
			}
			map.put("data", hmList);
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
	 * @Description 查看政务内参详情
	 * @author:张洋
	 * @CreateDate:2016年8月2日10:27:00
	 * @param id
	 * @return
	 */
	@RequestMapping("viewStaff")
	public @ResponseBody String viewStaff(@RequestParam String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "政务内参详情" };
		try {
			if (StringUtils.isBlank(id)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
			}
			// 根据ID获取数据
			Staff staff = staffService.get(Long.valueOf(id));
			if (staff == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 取出需要的数据
			Map<String, Object> data = new HashMap<>();
			data.put("title", staff.getTitle());
			data.put("publishTime", staff.getPublishTime());
			data.put("source", staff.getSource());
			data.put("content", staff.getContent());
			data.put("shareUrl", "");
			map.put("data", data);
			// 更新浏览量
			Staff s = new Staff();
			s.setId(staff.getId());
			s.setScannedNumber(staff.getScannedNumber() + 1);
			s.setUpdEac(staff.getUpdEac() + 1);
			s.setUpdId(staff.getUpdId());
			staffService.update(s);

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
	 * @Description 获取政务云列表
	 * @author:张洋
	 * @CreateDate:2016年8月2日11:00:50
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getNotice")
	public @ResponseBody String getNotice(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		Object[] arr = { "政务云列表" };
		// 由于此列表数据涵盖两张表，所以定义如下：
		// 1、当四个区域ID都传递过来，则为全部标签页数据，两张表都查出，并进行手动排序和分页并返回
		// 2、当只有一个区域ID时，则为单个区域查询，正常分页查询即可
		String title = request.getParameter("title");
		if (title != null && title.trim().equals("")) {
			title = null;
		}
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		String communityId = request.getParameter("communityId");
		String villageId = request.getParameter("villageId");
		String[] all = { cityId, countyId, communityId, villageId };
		if (StringUtil.isAllBlank(all)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		// 最终输出结果LIST
		List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
		// 临时存放全部数据的list
		List<Map<String, Object>> hmListT = new ArrayList<Map<String, Object>>();
		// 置顶数据的list
		List<Map<String, Object>> zdList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!StringUtil.isBlank(cityId) && !StringUtil.isBlank(countyId) && !StringUtil.isBlank(communityId)
					&& !StringUtil.isBlank(villageId)) {
				// 都不为空，为全部标签页，查两张表并进行手动分页和排序
				// 由于必须将两张表的数据合并，则不能按照传递过来的页码和数据计算开始数据下标，故一律从第一条数据开始
				// 然后再将取出的数据进行分页操作，防止漏查、重查数据
				Map<String, Object> queryMap = new HashMap<>();
				queryMap.put("pageFlag", true);
				queryMap.put("startPage", 0);
				queryMap.put("endPage", rows * page);
				queryMap.put("villageId", villageId);
				queryMap.put("publishStatus", ConstantStr.FB);
				queryMap.put("title", title);
				// 分别查出置顶公告和不置顶公告
				queryMap.put("topFlag", ConstantStr.BZD);
				List<VillageNotice> vnList = villageNoticeService.queryListByVillageId(queryMap);
				queryMap.put("topFlag", ConstantStr.ZD);
				List<VillageNotice> vnListZD = villageNoticeService.queryListByVillageId(queryMap);
				Village v = villageService.get(Long.valueOf(villageId));
				if (v == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				City city = cityService.get(Long.valueOf(cityId));
				if (city == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				County county = countyService.get(Long.valueOf(countyId));
				if (county == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				Community community = communityService.get(Long.valueOf(communityId));
				if (community == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				for (int i = 0; i < vnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", vnList.get(i).getId());
					hm.put("title", vnList.get(i).getTitle());
					hm.put("areaName", v.getVillageName());
					hm.put("areaLevel", ConstantStr.VILLAGE_CODE);
					hm.put("publishTime", vnList.get(i).getPublishTime());
					if (vnList.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", vnList.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", vnList.get(i).getUpdYmdhms());
					}
					hmListT.add(hm);
				}
				// 将置顶并且时间在置顶范围内的公告单独取出，并把不在范围内的公告放入通用列表内
				for (int i = 0; i < vnListZD.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", vnListZD.get(i).getId());
					hm.put("title", vnListZD.get(i).getTitle());
					hm.put("areaName", v.getVillageName());
					hm.put("areaLevel", ConstantStr.VILLAGE_CODE);
					hm.put("publishTime", vnListZD.get(i).getPublishTime());
					if (vnListZD.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", vnListZD.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", vnListZD.get(i).getUpdYmdhms());
					}
					Long nowTime = new Date().getTime();
					if (vnListZD.get(i).getTopStart() != null && vnListZD.get(i).getTopEnd() != null) {
						if (nowTime >= vnListZD.get(i).getTopStart().getTime()
								&& nowTime <= vnListZD.get(i).getTopEnd().getTime()) {
							zdList.add(hm);
						} else {
							hmListT.add(hm);
						}
					} else {
						hmListT.add(hm);
					}
				}
				// 按区域级别查询市、区、办事处公告，并分为置顶和不置顶两个列表，处理方式类似小区公告
				CityDistrictNoticeVo cityDistrictNoticeVo = new CityDistrictNoticeVo();
				cityDistrictNoticeVo.setPageFlag(true);
				cityDistrictNoticeVo.setStartPage(0);
				cityDistrictNoticeVo.setEndPage(rows * page);
				cityDistrictNoticeVo.setPublishStatus(ConstantStr.FB);
				cityDistrictNoticeVo.setTitle(title);
				List<CityDistrictNotice> cdnList = new ArrayList<>();

				cityDistrictNoticeVo.setAreaLevel(ConstantStr.CITY_CODE);
				cityDistrictNoticeVo.setAreaId(Long.valueOf(cityId));
				cityDistrictNoticeVo.setTopFlag(ConstantStr.BZD);
				cdnList = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
				cityDistrictNoticeVo.setTopFlag(ConstantStr.ZD);
				List<CityDistrictNotice> cdnListZD = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
				for (int i = 0; i < cdnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnList.get(i).getId());
					hm.put("title", cdnList.get(i).getTitle());
					hm.put("areaName", city.getCityName());
					hm.put("areaLevel", ConstantStr.CITY_CODE);
					hm.put("publishTime", cdnList.get(i).getPublishTime());
					if (cdnList.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", cdnList.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", cdnList.get(i).getUpdYmdhms());
					}
					hmListT.add(hm);
				}
				for (int i = 0; i < cdnListZD.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnListZD.get(i).getId());
					hm.put("title", cdnListZD.get(i).getTitle());
					hm.put("areaName", city.getCityName());
					hm.put("areaLevel", ConstantStr.CITY_CODE);
					hm.put("publishTime", cdnListZD.get(i).getPublishTime());
					if (cdnListZD.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", cdnListZD.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", cdnListZD.get(i).getUpdYmdhms());
					}
					Long nowTime = new Date().getTime();
					if (cdnListZD.get(i).getTopStart() != null && cdnListZD.get(i).getTopEnd() != null) {
						if (nowTime >= cdnListZD.get(i).getTopStart().getTime()
								&& nowTime <= cdnListZD.get(i).getTopEnd().getTime()) {
							zdList.add(hm);
						} else {
							hmListT.add(hm);
						}
					} else {
						hmListT.add(hm);
					}
				}
				cityDistrictNoticeVo.setAreaLevel(ConstantStr.COUNTY_CODE);
				cityDistrictNoticeVo.setAreaId(Long.valueOf(countyId));
				cityDistrictNoticeVo.setTopFlag(ConstantStr.BZD);
				cdnList = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
				cityDistrictNoticeVo.setTopFlag(ConstantStr.ZD);
				cdnListZD = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
				for (int i = 0; i < cdnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnList.get(i).getId());
					hm.put("title", cdnList.get(i).getTitle());
					hm.put("areaName", county.getCountyName());
					hm.put("areaLevel", ConstantStr.COUNTY_CODE);
					hm.put("publishTime", cdnList.get(i).getPublishTime());
					if (cdnList.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", cdnList.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", cdnList.get(i).getUpdYmdhms());
					}
					hmListT.add(hm);
				}
				for (int i = 0; i < cdnListZD.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnListZD.get(i).getId());
					hm.put("title", cdnListZD.get(i).getTitle());
					hm.put("areaName", county.getCountyName());
					hm.put("areaLevel", ConstantStr.COUNTY_CODE);
					hm.put("publishTime", cdnListZD.get(i).getPublishTime());
					if (cdnListZD.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", cdnListZD.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", cdnListZD.get(i).getUpdYmdhms());
					}
					Long nowTime = new Date().getTime();
					if (cdnListZD.get(i).getTopStart() != null && cdnListZD.get(i).getTopEnd() != null) {
						if (nowTime >= cdnListZD.get(i).getTopStart().getTime()
								&& nowTime <= cdnListZD.get(i).getTopEnd().getTime()) {
							zdList.add(hm);
						} else {
							hmListT.add(hm);
						}
					} else {
						hmListT.add(hm);
					}
				}
				cityDistrictNoticeVo.setAreaLevel(ConstantStr.COMMUNITY_CODE);
				cityDistrictNoticeVo.setAreaId(Long.valueOf(communityId));
				cityDistrictNoticeVo.setTopFlag(ConstantStr.BZD);
				cdnList = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
				cityDistrictNoticeVo.setTopFlag(ConstantStr.ZD);
				cdnListZD = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
				for (int i = 0; i < cdnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnList.get(i).getId());
					hm.put("title", cdnList.get(i).getTitle());
					hm.put("areaName", community.getCommunityName());
					hm.put("areaLevel", ConstantStr.COMMUNITY_CODE);
					hm.put("publishTime", cdnList.get(i).getPublishTime());
					if (cdnList.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", cdnList.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", cdnList.get(i).getUpdYmdhms());
					}
					hmListT.add(hm);
				}
				for (int i = 0; i < cdnListZD.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnListZD.get(i).getId());
					hm.put("title", cdnListZD.get(i).getTitle());
					hm.put("areaName", community.getCommunityName());
					hm.put("areaLevel", ConstantStr.COMMUNITY_CODE);
					hm.put("publishTime", cdnListZD.get(i).getPublishTime());
					if (cdnListZD.get(i).getUpdYmdhms() == null) {
						hm.put("sortTime", cdnListZD.get(i).getInsYmdhms());
					} else {
						hm.put("sortTime", cdnListZD.get(i).getUpdYmdhms());
					}
					Long nowTime = new Date().getTime();
					if (cdnListZD.get(i).getTopStart() != null && cdnListZD.get(i).getTopEnd() != null) {
						if (nowTime >= cdnListZD.get(i).getTopStart().getTime()
								&& nowTime <= cdnListZD.get(i).getTopEnd().getTime()) {
							zdList.add(hm);
						} else {
							hmListT.add(hm);
						}
					} else {
						hmListT.add(hm);
					}
				}
				// 将所有公告按发布时间排序
				MyCompartor mc = new MyCompartor("publishTime");
				Collections.sort(hmListT, mc);
				Collections.reverse(hmListT);
				MyCompartor mc2 = new MyCompartor("publishTime");
				Collections.sort(zdList, mc2);
				Collections.reverse(zdList);
				// 将置顶公告放在最前面
				List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
				hmListT.removeAll(zdList);
				tmpList.addAll(zdList);
				tmpList.addAll(hmListT);
				hmListT = tmpList;
				// 手动分页
				int start = (page - 1) * rows;
				int end = start + rows;
				int endNum = 0;
				if (hmListT.size() > start) {
					if (hmListT.size() <= end) {
						endNum = hmListT.size();
					} else {
						endNum = end;
					}
					for (int i = start; i < endNum; i++) {
						hmList.add(hmListT.get(i));
					}
				}
				map.put("data", hmList);
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001, arr), map);
			} else {
				// 有空值，单个区域查询
				String areaLevel = "";
				String areaId = "";
				if (!StringUtil.isBlank(cityId)) {
					areaId = cityId;
					areaLevel = ConstantStr.CITY_CODE;
				} else if (!StringUtil.isBlank(countyId)) {
					areaId = countyId;
					areaLevel = ConstantStr.COUNTY_CODE;
				} else if (!StringUtil.isBlank(communityId)) {
					areaId = communityId;
					areaLevel = ConstantStr.COMMUNITY_CODE;
				} else if (!StringUtil.isBlank(villageId)) {
					areaId = villageId;
					areaLevel = ConstantStr.VILLAGE_CODE;
				}
				if (areaLevel.equals(ConstantStr.VILLAGE_CODE)) {
					Map<String, Object> queryMap = new HashMap<>();
					Village v = villageService.get(Long.valueOf(areaId));
					if (v == null) {
						return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
					}
					// 分别取出置顶公告和不置顶公告
					queryMap.put("pageFlag", true);
					queryMap.put("startPage", (page - 1) * rows);
					queryMap.put("endPage", rows);
					queryMap.put("villageId", areaId);
					queryMap.put("title", title);
					queryMap.put("publishStatus", ConstantStr.FB);
					queryMap.put("topFlag", ConstantStr.BZD);
					List<VillageNotice> vnList = villageNoticeService.queryListByVillageId(queryMap);
					queryMap.put("topFlag", ConstantStr.ZD);
					List<VillageNotice> vnListZD = villageNoticeService.queryListByVillageId(queryMap);
					for (int i = 0; i < vnList.size(); i++) {
						Map<String, Object> hm = new HashMap<>();
						hm.put("id", vnList.get(i).getId());
						hm.put("title", vnList.get(i).getTitle());
						hm.put("areaName", v.getVillageName());
						hm.put("areaLevel", areaLevel);
						hm.put("publishTime", vnList.get(i).getPublishTime());
						if (vnList.get(i).getUpdYmdhms() == null) {
							hm.put("sortTime", vnList.get(i).getInsYmdhms());
						} else {
							hm.put("sortTime", vnList.get(i).getUpdYmdhms());
						}
						hmList.add(hm);
					}
					// 将置顶时间在范围内的公告放入置顶列表，其他的放入通用列表
					for (int i = 0; i < vnListZD.size(); i++) {
						Map<String, Object> hm = new HashMap<>();
						hm.put("id", vnListZD.get(i).getId());
						hm.put("title", vnListZD.get(i).getTitle());
						hm.put("areaName", v.getVillageName());
						hm.put("areaLevel", ConstantStr.VILLAGE_CODE);
						hm.put("publishTime", vnListZD.get(i).getPublishTime());
						if (vnListZD.get(i).getUpdYmdhms() == null) {
							hm.put("sortTime", vnListZD.get(i).getInsYmdhms());
						} else {
							hm.put("sortTime", vnListZD.get(i).getUpdYmdhms());
						}
						Long nowTime = new Date().getTime();
						if (vnListZD.get(i).getTopStart() != null && vnListZD.get(i).getTopEnd() != null) {
							if (nowTime >= vnListZD.get(i).getTopStart().getTime()
									&& nowTime <= vnListZD.get(i).getTopEnd().getTime()) {
								zdList.add(hm);
							} else {
								hmList.add(hm);
							}
						} else {
							hmList.add(hm);
						}
					}
				} else {
					// 按区域级别查询市、区、办事处公告，并分为置顶和不置顶两个列表，处理方式类似小区公告
					CityDistrictNoticeVo cityDistrictNoticeVo = new CityDistrictNoticeVo();
					cityDistrictNoticeVo.setPageFlag(true);
					cityDistrictNoticeVo.setStartPage((page - 1) * rows);
					cityDistrictNoticeVo.setEndPage(rows);
					cityDistrictNoticeVo.setPublishStatus(ConstantStr.FB);
					cityDistrictNoticeVo.setAreaLevel(areaLevel);
					cityDistrictNoticeVo.setAreaId(Long.valueOf(areaId));
					cityDistrictNoticeVo.setTopFlag(ConstantStr.BZD);
					cityDistrictNoticeVo.setTitle(title);
					List<CityDistrictNotice> cdnList = cityDistrictNoticeService.queryListByParam(cityDistrictNoticeVo);
					cityDistrictNoticeVo.setTopFlag(ConstantStr.ZD);
					List<CityDistrictNotice> cdnListZD = cityDistrictNoticeService
							.queryListByParam(cityDistrictNoticeVo);
					String areaName = "";
					if (areaLevel.equals(ConstantStr.CITY_CODE)) {
						City city = cityService.get(Long.valueOf(areaId));
						if (city == null) {
							return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr),
									null);
						}
						areaName = city.getCityName();
					} else if (areaLevel.equals(ConstantStr.COUNTY_CODE)) {
						County county = countyService.get(Long.valueOf(areaId));
						if (county == null) {
							return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr),
									null);
						}
						areaName = county.getCountyName();
					} else if (areaLevel.equals(ConstantStr.COMMUNITY_CODE)) {
						Community community = communityService.get(Long.valueOf(areaId));
						if (community == null) {
							return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr),
									null);
						}
						areaName = community.getCommunityName();
					}
					for (int i = 0; i < cdnList.size(); i++) {
						Map<String, Object> hm = new HashMap<>();
						hm.put("id", cdnList.get(i).getId());
						hm.put("title", cdnList.get(i).getTitle());
						hm.put("areaName", areaName);
						hm.put("areaLevel", areaLevel);
						hm.put("publishTime", cdnList.get(i).getPublishTime());
						if (cdnList.get(i).getUpdYmdhms() == null) {
							hm.put("sortTime", cdnList.get(i).getInsYmdhms());
						} else {
							hm.put("sortTime", cdnList.get(i).getUpdYmdhms());
						}
						hmList.add(hm);
					}
					for (int i = 0; i < cdnListZD.size(); i++) {
						Map<String, Object> hm = new HashMap<>();
						hm.put("id", cdnListZD.get(i).getId());
						hm.put("title", cdnListZD.get(i).getTitle());
						hm.put("areaName", areaName);
						hm.put("areaLevel", ConstantStr.CITY_CODE);
						hm.put("publishTime", cdnListZD.get(i).getPublishTime());
						if (cdnListZD.get(i).getUpdYmdhms() == null) {
							hm.put("sortTime", cdnListZD.get(i).getInsYmdhms());
						} else {
							hm.put("sortTime", cdnListZD.get(i).getUpdYmdhms());
						}
						Long nowTime = new Date().getTime();
						if (cdnListZD.get(i).getTopStart() != null && cdnListZD.get(i).getTopEnd() != null) {
							if (nowTime >= cdnListZD.get(i).getTopStart().getTime()
									&& nowTime <= cdnListZD.get(i).getTopEnd().getTime()) {
								zdList.add(hm);
							} else {
								hmList.add(hm);
							}
						} else {
							hmList.add(hm);
						}
					}
				}
			}
            // 将所有公告按发布时间排序
			MyCompartor mc = new MyCompartor("publishTime");
			Collections.sort(hmList, mc);
			Collections.reverse(hmList);
			MyCompartor mc2 = new MyCompartor("publishTime");
			Collections.sort(zdList, mc2);
			Collections.reverse(zdList);
			// 最终输出结果LIST
			List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
			hmList.removeAll(zdList);
			outList.addAll(zdList);
			outList.addAll(hmList);
			map.put("data", outList);
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
	 * @Description 获取政务云推荐列表
	 * @author:张洋
	 * @CreateDate:2016年8月3日09:48:06
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getNoticeRecommended")
	public @ResponseBody String getNoticeRecommended(HttpServletRequest request, HttpServletResponse response) {
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
        String idStr = request.getParameter("id");
        String areaLevelOut = request.getParameter("areaLevel");
		Object[] arr = { "政务云推荐" };
		if (StringUtil.isBlank(pageStr) || StringUtil.isBlank(rowsStr) || StringUtil.isBlank(idStr) || StringUtil.isBlank(areaLevelOut)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		// 此接口主要算法和getNotice接口一致，主要差别如下：
		// 1、分页数据变为必传，以取得推荐数据
		// 2、从数据库中查询时的排序变为按发布时间倒序排序
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		String communityId = request.getParameter("communityId");
		String villageId = request.getParameter("villageId");
		String[] all = { cityId, countyId, communityId, villageId };
		if (StringUtil.isAllBlank(all)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> hmListT = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!StringUtil.isBlank(cityId) && !StringUtil.isBlank(countyId) && !StringUtil.isBlank(communityId)
					&& !StringUtil.isBlank(villageId)) {
				// 都不为空，全部标签页
				VillageNoticeVo villageNoticeVo = new VillageNoticeVo();
				villageNoticeVo.setPageFlag(true);
				villageNoticeVo.setStartPage(0);
				villageNoticeVo.setEndPage(rows * page);
				villageNoticeVo.setPublishStatus(ConstantStr.FB);
				if(ConstantStr.VILLAGE_CODE.equals(areaLevelOut)){
	                villageNoticeVo.setOutId(Long.valueOf(idStr));
				}
				List<VillageNotice> vnList = villageNoticeService.queryListByParamOrder(villageNoticeVo);
				Village v = villageService.get(Long.valueOf(villageId));
				if (v == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				City city = cityService.get(Long.valueOf(cityId));
				if (city == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				County county = countyService.get(Long.valueOf(countyId));
				if (county == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				Community community = communityService.get(Long.valueOf(communityId));
				if (community == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				for (int i = 0; i < vnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", vnList.get(i).getId());
					hm.put("title", vnList.get(i).getTitle());
					hm.put("areaName", v.getVillageName());
					hm.put("areaLevel", ConstantStr.VILLAGE_CODE);
					hm.put("publishTime", vnList.get(i).getPublishTime());
					hmListT.add(hm);
				}

				CityDistrictNoticeVo cityDistrictNoticeVo = new CityDistrictNoticeVo();
                if(!ConstantStr.VILLAGE_CODE.equals(areaLevelOut)){
                    cityDistrictNoticeVo.setOutId(Long.valueOf(idStr));
                }
				cityDistrictNoticeVo.setPageFlag(true);
				cityDistrictNoticeVo.setStartPage(0);
				cityDistrictNoticeVo.setEndPage(rows * page);
				cityDistrictNoticeVo.setPublishStatus(ConstantStr.FB);
				List<CityDistrictNotice> cdnList = new ArrayList<>();

				cityDistrictNoticeVo.setAreaLevel(ConstantStr.CITY_CODE);
				cityDistrictNoticeVo.setAreaId(Long.valueOf(cityId));
				cdnList = cityDistrictNoticeService.queryListByParamOrder(cityDistrictNoticeVo);
				for (int i = 0; i < cdnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnList.get(i).getId());
					hm.put("title", cdnList.get(i).getTitle());
					hm.put("areaName", city.getCityName());
					hm.put("areaLevel", ConstantStr.CITY_CODE);
					hm.put("publishTime", cdnList.get(i).getPublishTime());
					hmListT.add(hm);
				}
				cityDistrictNoticeVo.setAreaLevel(ConstantStr.COUNTY_CODE);
				cityDistrictNoticeVo.setAreaId(Long.valueOf(countyId));
				cdnList = cityDistrictNoticeService.queryListByParamOrder(cityDistrictNoticeVo);
				for (int i = 0; i < cdnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnList.get(i).getId());
					hm.put("title", cdnList.get(i).getTitle());
					hm.put("areaName", county.getCountyName());
					hm.put("areaLevel", ConstantStr.COUNTY_CODE);
					hm.put("publishTime", cdnList.get(i).getPublishTime());
					hmListT.add(hm);
				}
				cityDistrictNoticeVo.setAreaLevel(ConstantStr.COMMUNITY_CODE);
				cityDistrictNoticeVo.setAreaId(Long.valueOf(communityId));
				cdnList = cityDistrictNoticeService.queryListByParamOrder(cityDistrictNoticeVo);
				for (int i = 0; i < cdnList.size(); i++) {
					Map<String, Object> hm = new HashMap<>();
					hm.put("id", cdnList.get(i).getId());
					hm.put("title", cdnList.get(i).getTitle());
					hm.put("areaName", community.getCommunityName());
					hm.put("areaLevel", ConstantStr.COMMUNITY_CODE);
					hm.put("publishTime", cdnList.get(i).getPublishTime());
					hmListT.add(hm);
				}
				MyCompartor mc = new MyCompartor("publishTime");
				Collections.sort(hmListT, mc);
				Collections.reverse(hmListT);

				int start = (page - 1) * rows;
				int end = start + rows;
				int endNum = 0;
				if (hmListT.size() > start) {
					if (hmListT.size() <= end) {
						endNum = hmListT.size();
					} else {
						endNum = end;
					}
					for (int i = start; i < endNum; i++) {
						hmList.add(hmListT.get(i));
					}
				}
			} else {
				// 有空值，则为单个区域查询
				String areaLevel = "";
				String areaId = "";
				if (!StringUtil.isBlank(cityId)) {
					areaId = cityId;
					areaLevel = ConstantStr.CITY_CODE;
				} else if (!StringUtil.isBlank(countyId)) {
					areaId = countyId;
					areaLevel = ConstantStr.COUNTY_CODE;
				} else if (!StringUtil.isBlank(communityId)) {
					areaId = communityId;
					areaLevel = ConstantStr.COMMUNITY_CODE;
				} else if (!StringUtil.isBlank(villageId)) {
					areaId = villageId;
					areaLevel = ConstantStr.VILLAGE_CODE;
				}
				if (areaLevel.equals(ConstantStr.VILLAGE_CODE)) {
					Village v = villageService.get(Long.valueOf(areaId));
					if (v == null) {
						return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
					}
					VillageNoticeVo villageNoticeVo = new VillageNoticeVo();
					villageNoticeVo.setPageFlag(true);
					villageNoticeVo.setStartPage((page - 1) * rows);
					villageNoticeVo.setEndPage(rows);
					villageNoticeVo.setPublishStatus(ConstantStr.FB);
					villageNoticeVo.setVillageId(v.getVillageId());
	                if(ConstantStr.VILLAGE_CODE.equals(areaLevelOut)){
	                    villageNoticeVo.setOutId(Long.valueOf(idStr));
	                }
					List<VillageNotice> vnList = villageNoticeService.queryListByParamOrder(villageNoticeVo);
					for (int i = 0; i < vnList.size(); i++) {
						Map<String, Object> hm = new HashMap<>();
						hm.put("id", vnList.get(i).getId());
						hm.put("title", vnList.get(i).getTitle());
						hm.put("areaName", v.getVillageName());
						hm.put("areaLevel", areaLevel);
						hm.put("publishTime", vnList.get(i).getPublishTime());
						hmList.add(hm);
					}
				} else {
					CityDistrictNoticeVo cityDistrictNoticeVo = new CityDistrictNoticeVo();
					cityDistrictNoticeVo.setPageFlag(true);
					cityDistrictNoticeVo.setStartPage((page - 1) * rows);
					cityDistrictNoticeVo.setEndPage(rows);
					cityDistrictNoticeVo.setPublishStatus(ConstantStr.FB);
					cityDistrictNoticeVo.setAreaLevel(areaLevel);
					cityDistrictNoticeVo.setAreaId(Long.valueOf(areaId));
	                if(!ConstantStr.VILLAGE_CODE.equals(areaLevelOut)){
	                    cityDistrictNoticeVo.setOutId(Long.valueOf(idStr));
	                }
					List<CityDistrictNotice> cdnList = cityDistrictNoticeService
							.queryListByParamOrder(cityDistrictNoticeVo);
					String areaName = "";
					if (areaLevel.equals(ConstantStr.CITY_CODE)) {
						City city = cityService.get(Long.valueOf(areaId));
						if (city == null) {
							return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr),
									null);
						}
						areaName = city.getCityName();
					} else if (areaLevel.equals(ConstantStr.COUNTY_CODE)) {
						County county = countyService.get(Long.valueOf(areaId));
						if (county == null) {
							return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr),
									null);
						}
						areaName = county.getCountyName();
					} else if (areaLevel.equals(ConstantStr.COMMUNITY_CODE)) {
						Community community = communityService.get(Long.valueOf(areaId));
						if (community == null) {
							return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr),
									null);
						}
						areaName = community.getCommunityName();
					}
					for (int i = 0; i < cdnList.size(); i++) {
						Map<String, Object> hm = new HashMap<>();
						hm.put("id", cdnList.get(i).getId());
						hm.put("title", cdnList.get(i).getTitle());
						hm.put("areaName", areaName);
						hm.put("areaLevel", areaLevel);
						hm.put("publishTime", cdnList.get(i).getPublishTime());
						hmList.add(hm);
					}
				}
			}
			map.put("data", hmList);
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
	 * @Description 查看政务云详情
	 * @author:张洋
	 * @CreateDate:2016年8月2日10:27:00
	 * @param id
	 * @return
	 */
	@RequestMapping("viewNotice")
	public @ResponseBody String viewNotice(@RequestParam String id, @RequestParam String areaLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "政务云详情" };
		try {
			if (StringUtils.isBlank(id) || StringUtils.isBlank(areaLevel)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
			}
			String userName = "";
			Map<String, Object> data = new HashMap<>();
			// 判断是小区公告还是市区办事处公告，两者查询方式不同
			if (areaLevel.equals(ConstantStr.VILLAGE_CODE)) {
				VillageNotice v = villageNoticeService.get(Long.valueOf(id));
				if (v == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				if (v.getPublishUser() != null) {
					User user = userService.get(v.getPublishUser());
					if (user != null) {
						userName = user.getRealName();
					}
				}
				data.put("title", v.getTitle());
				data.put("publishUser", userName);
                data.put("source", v.getSource());
				data.put("publishTime", v.getPublishTime());
				data.put("noticeTime", v.getNoticeTime());
				data.put("content", v.getContent());
				// 更新浏览量
				VillageNotice vn = new VillageNotice();
				vn.setId(v.getId());
				vn.setScannedNumber(v.getScannedNumber() + 1);
				vn.setUpdEac(v.getUpdEac() + 1);
				vn.setUpdId(v.getUpdId());
				villageNoticeService.updateSelective(vn);

			} else {
				CityDistrictNotice cdn = cityDistrictNoticeService.get(Long.valueOf(id));
				if (cdn == null) {
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
				}
				if (cdn.getPublishUser() != null) {
					User user = userService.get(cdn.getPublishUser());
					if (user != null) {
						userName = user.getRealName();
					}
				}
				data.put("title", cdn.getTitle());
                data.put("source", cdn.getSource());
				data.put("publishTime", cdn.getPublishTime());
				data.put("noticeTime", cdn.getNoticeTime());
				data.put("publishUser", userName);
				data.put("content", cdn.getContent());
				// 更新浏览量
				CityDistrictNotice newCdn = new CityDistrictNotice();
				newCdn.setId(cdn.getId());
				newCdn.setScannedNumber(cdn.getScannedNumber() + 1);
				newCdn.setUpdEac(cdn.getUpdEac() + 1);
				newCdn.setUpdId(cdn.getUpdId());
				cityDistrictNoticeService.updateSelective(newCdn);
			}
			map.put("data", data);
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
	 * @Description 获取数据字典表里网民法眼信息管理类别
	 * @author:QZG
	 * @CreateDate:2016年9月10日 上午9:32:45
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getNetizenAcumenType")
	public @ResponseBody String getNetizenAcumenType(String type, HttpServletRequest request,
			HttpServletResponse response) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));
			// 查询网民法眼信息的所有类别
			List<SysData> sysDataList = sysDateService.querySysdataByType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
			// 封装返回信息
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (SysData sysData : sysDataList) {
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("type", sysData.getValue());
				object.put("lable", sysData.getLable());
				result.add(object);
			}
			map.put("data", result);
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 网民法眼信息录入
	 * @author:QZG
	 * @CreateDate:2016年9月10日 上午10:55:08
	 * @param title
	 *            标题
	 * @param source
	 *            来源
	 * @param reason
	 *            原因
	 * @param files
	 *            图片
	 * @param type
	 *            类型
	 * @param phone
	 *            当前用户手机号
	 * @param villageId
	 *            当前位置
	 * @return
	 */
	@RequestMapping("insertNetizenAcumen")
	public @ResponseBody String insertNetizenAcumen(@RequestParam String title, @RequestParam String source,
			@RequestParam String reason, @RequestParam("file") MultipartFile[] files, @RequestParam String type,
			@RequestParam String phone, @RequestParam String villageId, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建网民法眼信息类用于封装注册参数
		NetizenAcumen netizenAcumen = new NetizenAcumen();
		// 创建对象用于信息提示
		Object[] arr = {""};
		try {
			// 判断标题是否为空
			if (StringUtils.isBlank(title)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200062));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 判断来源是否为空
			if (StringUtils.isBlank(source)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200063));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 判断原因是否为空
			if (StringUtils.isBlank(reason)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200064));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// MOD YK 2016-09-24 begin
			// 验证原因的长度
			// 原因解密
			reason = UnescapeUtil.unescape(reason);
			// 最多支持输入长度255
			if (reason.length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, "您输入的原因过长~", null);
			}
			// 原因加密
			reason = UnescapeUtil.escape(reason);
			// MOD YK 2016-09-24 end
			/*
			 * // 判断图片是否为空 if (files为空) { // 封装返回信息 map.put("resultCode",
			 * ConstantStr.APP_CODE401); map.put("msg",
			 * ComDefine.getMsg(ConstantStr.INFO200065)); // 返回JSON return
			 * JSONObject.fromObject(map).toString(); }
			 */
			// 判断原因类型是否为空
			if (StringUtils.isBlank(type)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200066));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 文件上传路径相关
			String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HBRIDGE);
			String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HBRIDGE);
			// 新建附件类表类
			List<Attach> attachs = new ArrayList<Attach>();
			// 创建文件名
			String newFileName = "";
			// 创建boolean变量 默认为false
			boolean rs = false;
			// 遍历上传的文件
			for (int j = 0; j < files.length; j++) {
				// 创建文件对象用于遍历的文件操作
				MultipartFile file = files[j];
				// 判断当前文件是否被上传
				if (files[j].getSize() > 0) {
					// 创建附件类
					Attach attach = new Attach();
					// 设置rs为false
					rs = false;
					// 生成新的文件名
					newFileName = UUID.randomUUID()
							+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					// 上传文件
					rs = FileUtil.uploadfile(file, realpath, newFileName);
					// 如果上传成功
					if (rs) {
						// 获取文件上传后的相对路径
						newFileName = httppath + newFileName;
						// 设置文件地址
						attach.setUrl(newFileName);
						// 传入附件列表
						attachs.add(attach);
					}
				}
			}

			netizenAcumen.setAttachs(attachs);
			// 获取用户ip
			String opreaterIp = getIpAddr(request);
			// 传入标题参数
			netizenAcumen.setTitle(new String(title.getBytes("ISO8859-1"), "UTF-8"));
			// 传入来源参数
			netizenAcumen.setSource(new String(source.getBytes("ISO8859-1"), "UTF-8"));
			// 传入原因参数
			netizenAcumen.setReason(reason);
			// 传入类型参数
			netizenAcumen.setType(type);
			// 传入手机号参数
			netizenAcumen.setPhone(phone);
			// 传入用户ip
			netizenAcumen.setInsIp(opreaterIp);
			// 以下四行为添加共通字段属性
			netizenAcumen.setDelFlag(ConstantStr.DELETE_N);
			netizenAcumen.setInsYmdhms(new Date());
			netizenAcumen.setUpdEac(0l);
			netizenAcumen.setUpdYmdhms(new Date());
			// 传入附件
			// 根据小区id获取小区名
			String villageName = villageService.get(villageId).getVillageName();
			// 根据小区id获取社区名
			String communityName = communityService.get(villageService.get(villageId).getCommunityId())
					.getCommunityName();
			// 根据小区id获取区名
			String countyName = countyService.get(villageService.get(villageId).getCountyId()).getCountyName();
			// 根据小区id获取市名
			String cityName = cityService.get(villageService.get(villageId).getCityId()).getCityName();
			// 根据小区id获取省名
			String provinceName = provinceService.get(villageService.get(villageId).getProvinceId()).getProvinceName();
			// 传入地址 省+市+区+社区+小区
			netizenAcumen.setAddress(provinceName + cityName + countyName + communityName + villageName);

			// 上传网民法眼信息 上传成功 返回true
			boolean res = netizenAcumentService.insertNetizenAcumen(netizenAcumen);
			// 如果上传成功
			if (res) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200067, arr));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			} else {
				// 封装注册失败信息
				map.put("resultCode", ConstantStr.APP_CODE403);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200068, arr));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}

	}

	/**
	 * @Title
	 * @Description 获取网安报列表数据
	 * @author:kangtianyu
	 * @CreateDate:2016年9月10日 上午9:41:23
	 * @param title
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNetizenSecurity")
	public @ResponseBody String getNetizenSecurity(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "excludeId", required = false) Long excludeId, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建页数变量获取请求页数
		int page = Integer.parseInt(StringUtils.isEmpty(request.getParameter("page")) ? "1" : request
				.getParameter("page"));
		// 创建记录变量获取请求的记录数
		int rows = Integer.parseInt(StringUtils.isEmpty(request.getParameter("rows")) ? "10" : request
				.getParameter("rows"));
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			// 创建Vo对象用于封装参数查询列表信息
			NetizenSecurityVo netizenSecurityVo = new NetizenSecurityVo();
			if (title != null && "".equals(title.trim())) {
				// 如果标题不为空
				netizenSecurityVo.setTitle(title);
			}
			// 设置分页标志位
			netizenSecurityVo.setPageFlag(true);
			// 设置开始记录
			netizenSecurityVo.setStartPage((page - 1) * rows);
			// 设置结束记录
			netizenSecurityVo.setEndPage(rows);
			// 判断店名是否为空
			if (StringUtils.isNotBlank(title)) { // 如果不为空
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					title = new String(title.getBytes("iso8859-1"), "UTF-8");
				}
				// 设置标题
				netizenSecurityVo.setTitle(title);
			}
			// 设置发布状态
			netizenSecurityVo.setPublishStatus(ConstantStr.FB);
			// 调用service方法查询网安报信息
			List<NetizenSecurityVo> netizenSecurityList = new ArrayList<NetizenSecurityVo>();
			if (excludeId == null) {
				netizenSecurityList = netizenSecurityService.findNetizenSecurityListByParam(netizenSecurityVo);
			} else {
				netizenSecurityVo.setId(excludeId);
				netizenSecurityList = netizenSecurityService.findHotNetizenSecurityListByParam(netizenSecurityVo);
			}
			// 封装数据
			for (NetizenSecurityVo ns : netizenSecurityList) {
				// 创建Map变量用于封装返回数据
				Map<String, Object> data = new HashMap<>();
				// 封装网安报id
				data.put("id", ns.getId());
				// 封装网安报标题
				data.put("title", ns.getTitle());
				// 封装网安报发布时间
				data.put("publishTime", ns.getPublishTime());
				// 封装网安报来源
				data.put("source", ns.getSource());
				// 封装网安报缩略图
				data.put("picture", ns.getPicture());
				// 向list中添加数据信息
				dataList.add(data);
			}
			// 向map中放入数据列表用于返回
			map.put("data", dataList);
			// 返回封装的成功数据信息
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
	 * @Description 获取网安报详情数据
	 * @author:kangtianyu
	 * @CreateDate:2016年9月10日 上午9:41:37
	 * @param netizenSecurityId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNetizenSecurityDetail")
	public @ResponseBody String getNetizenSecurityDetail(@RequestParam Long netizenSecurityId,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装返回数据
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			// 获取网安报源数据
			NetizenSecurity original = netizenSecurityService.get(netizenSecurityId);
			if (original == null) { // 如果已经被删除
				Object[] attribute = { "网安报" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute), null);
			}
			// 创建网安报Model对象更新网安报浏览次数
			NetizenSecurity netizenSecurity = new NetizenSecurity();
			// 设置网安报id
			netizenSecurity.setId(netizenSecurityId);
			// 设置网安报浏览次数
			netizenSecurity.setViewsNumber(original.getViewsNumber() + 1);
			// 设置网安报更新次数
			netizenSecurity.setUpdEac(original.getUpdEac() + 1);
			// 设置网安报更新时间
			netizenSecurity.setUpdYmdhms(new Date());
			// 调用service方法更新网安报数据
			netizenSecurityService.updateSelective(netizenSecurity);

			// 封装网安报id
			data.put("id", netizenSecurityId);
			// 封装网安报标题
			data.put("title", original.getTitle());
			// 封装网安报
			data.put("content", original.getContent());
			// 封装网安报来源
			data.put("source", original.getSource());
			// 封装网安报发布时间
			data.put("publishTime", original.getPublishTime());
			// 封装网安报分享链接
			data.put("shareUrl", ConstantStr.SHARE_URL + "?id=" + netizenSecurityId + "&flag="
					+ ConstantStr.SHAREFLAG_NETIZENSECURITY);
			// 向map中放入详情数据用于返回
			map.put("data", data);
			// 返回封装的成功数据信息
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 根据风清气正type类型获取数据字典的valueAndLable
	 * @author:YK
	 * @CreateDate:2016年9月13日 上午10:17:45
	 * @param type
	 * @return
	 */
	@RequestMapping("/getSysDataByType")
	public @ResponseBody String getSysDataByType() {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));
			// 查询网民法眼信息的所有类别
			List<SysData> sysDataList = sysDateService.querySysdataByType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
			// 封装返回信息
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String, Object> all = new HashMap<String, Object>();
			all.put("type", "");
			all.put("lable", "全部");
			result.add(all);
			for (SysData sysData : sysDataList) {
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("type", sysData.getValue());
				object.put("lable", sysData.getLable());
				result.add(object);
			}
			map.put("data", result);
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 风清气正列表查询
	 * @author:YK
	 * @CreateDate:2016年9月12日 下午2:29:30
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getIncorruptGovernment")
	public @ResponseBody String getIncorruptGovernment(IncorruptGovernmentVo incorruptGovernmentVo,
			HttpServletRequest request, HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "风清气正" };
		try {
			incorruptGovernmentVo.setPageFlag(true);
			incorruptGovernmentVo.setStartPage((page - 1) * rows);
			incorruptGovernmentVo.setEndPage(rows);
			List<Map<String, Object>> list = incorruptGovernmentService.queryListByParam(incorruptGovernmentVo);

			map.put("data", list);
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
	 * @Description 热门推荐
	 * @author:YK
	 * @CreateDate:2016年9月14日 上午11:14:12
	 * @param incorruptGovernmentVo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getRecommend")
	public @ResponseBody String getRecommend(IncorruptGovernmentVo incorruptGovernmentVo, HttpServletRequest request,
			HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "3" : request.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "风清气正" };
		try {
			// 设置分页等条件
			incorruptGovernmentVo.setPageFlag(true);
			incorruptGovernmentVo.setStartPage((page - 1) * rows);
			incorruptGovernmentVo.setEndPage(rows);
			// 获取热门推荐列表
			List<Map<String, Object>> list = incorruptGovernmentService.queryHotListByParam(incorruptGovernmentVo);

			map.put("data", list);
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
	 * @Description 风清气正详情查看
	 * @author:YK
	 * @CreateDate:2016年9月12日 下午4:27:32
	 * @param id
	 * @return
	 */
	@RequestMapping("viewIncorruptGovernment")
	public @ResponseBody String viewIncorruptGovernment(@RequestParam Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "风清气正详情" };
		try {
			if (id == null) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
			}
			// 根据ID获取数据
			IncorruptGovernmentVo incorruptGovernmentVo = new IncorruptGovernmentVo();
			incorruptGovernmentVo.setId(id);
			incorruptGovernmentVo = incorruptGovernmentService.findById(incorruptGovernmentVo);
			if (incorruptGovernmentVo == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 查询评论列表数据
			IncorruptGovernmentCommentVo commentVo = new IncorruptGovernmentCommentVo();
			// 设置评论归属风清气正id
			commentVo.setIncorruptGovernmentId(id);
			// 设置只获取正常评论
			commentVo.setIllegalStatus(ConstantStr.ILLEAGL_FLAG_ZC);
			List<IncorruptGovernmentCommentVo> commentList = commentService.selectByIncorruptGovernmentId(commentVo);
			// 更新浏览量
			IncorruptGovernment incorruptGovernment = new IncorruptGovernment();
			incorruptGovernment.setId(incorruptGovernmentVo.getId());
			incorruptGovernment.setViewNumber(incorruptGovernmentVo.getViewNumber() + 1);
			incorruptGovernment.setUpdEac(incorruptGovernmentVo.getUpdEac() + 1);
			incorruptGovernment.setUpdYmdhms(new Date());
			incorruptGovernmentService.updateIncorruptGovernment(incorruptGovernment, null);
			// 设置查询结果
			Map<String, Object> data = new HashMap<String, Object>();
			// 设置风清气正详情
			data.put("id", incorruptGovernmentVo.getId());
			data.put("title", incorruptGovernmentVo.getTitle());
			data.put("contentFrom", incorruptGovernmentVo.getContentFrom());
			data.put("content", incorruptGovernmentVo.getContent());
			data.put("likesNumber", incorruptGovernmentVo.getLikesNumber());
			data.put("hateNumber", incorruptGovernmentVo.getHateNumber());
			data.put("publishTime", incorruptGovernmentVo.getPublishTime());
			data.put("shareUrl", ConstantStr.SHARE_URL + "?id=" + id + "&flag=" + ConstantStr.SHAREFLAG_INCORRUPTGOVERNMENT);
			// 评论列表数据组装
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (IncorruptGovernmentCommentVo comment : commentList) {
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("id", comment.getId());
				object.put("headPortrait", comment.getHeadPortrait());
				object.put("nickName", comment.getNickName());
				object.put("content", comment.getContent());
				object.put("opreaterTime", comment.getOpreaterTime());
				object.put("replyNumber", comment.getReplyNumber());
				object.put("likesNumber", comment.getLikesNumber());
				object.put("hateNumber", comment.getHateNumber());
				result.add(object);
			}
			// 设置评论列表
			data.put("commentList", result);
			map.put("data", data);

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
	 * @Description 关注、不关注动作
	 * @author:YK
	 * @CreateDate:2016年9月13日 上午10:05:12
	 * @param id
	 * @param operationId
	 * @return
	 */
	@RequestMapping("updateLikeOrHate")
	public @ResponseBody String updateLikeOrHate(@RequestParam Long id, @RequestParam String operationId,
			@RequestParam Long appUserId) {
		Object[] arr = { "风清气正" };
		try {
			IncorruptGovernmentVo incorruptGovernmentVo = new IncorruptGovernmentVo();
			// 设置主键id
			incorruptGovernmentVo.setId(id);
			incorruptGovernmentVo = incorruptGovernmentService.findById(incorruptGovernmentVo);
			if (incorruptGovernmentVo == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 根据用户查询是否关注
			String message = "";
			IncorruptRecommend recommend = incorruptRecommendService.queryIncorruptRecommend(id, appUserId, null);
			if (recommend != null) {
				if (ConstantStr.ZZ_GZ_SC.equals(recommend.getOperatorType())) {
					message = "您已关注过~";
				} else if (ConstantStr.DZ_BGZ_BSC.equals(recommend.getOperatorType())) {
					message = "您已不关注过~";
				}
				return fromObject(ConstantStr.APP_CODE403, message, null);
			}
			// 更新关注与非关注量
			IncorruptGovernment incorruptGovernment = new IncorruptGovernment();
			incorruptGovernment.setId(incorruptGovernmentVo.getId());
			if (ConstantStr.ZZ_GZ_SC.equals(operationId)) {// 更新关注量
				// 创建关注记录对象
				recommend = fromatIncorruptRecommend(ConstantStr.ZZ_GZ_SC, id, appUserId);
				// 设置关注量+1
				incorruptGovernment.setLikesNumber(incorruptGovernmentVo.getLikesNumber() + 1);
				// 增加消息
				arr = addMessage(arr, "关注");
			} else if (ConstantStr.DZ_BGZ_BSC.equals(operationId)) {// 更新非关注量
				// 创建关注记录对象
				recommend = fromatIncorruptRecommend(ConstantStr.DZ_BGZ_BSC, id, appUserId);
				// 设置非关注量+1
				incorruptGovernment.setHateNumber(incorruptGovernmentVo.getHateNumber() + 1);
				// 增加消息
				arr = addMessage(arr, "不关注");
			}
			// 设置更新回数
			incorruptGovernment.setUpdEac(incorruptGovernmentVo.getUpdEac() + 1);
			// 设置更新时间
			incorruptGovernment.setUpdYmdhms(new Date());
			// 更新数据,并新增关注记录
			boolean bl = incorruptGovernmentService.updateIncorruptGovernment(incorruptGovernment, recommend);
			if (bl) {
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200070, arr), null);
			} else {
				return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200071, arr), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002, arr) + e.getMessage(),
					null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 评论正赞、倒赞
	 * @author:YK
	 * @CreateDate:2016年9月13日 下午5:29:23
	 * @param id
	 * @param operationId
	 * @return
	 */
	@RequestMapping("updateCommentLikeOrHate")
	public @ResponseBody String updateCommentLikeOrHate(@RequestParam Long id, @RequestParam Long appUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "点赞" };
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			IncorruptGovernmentComment comment = commentService.findById(id);
			if (comment == null) {// 验证评论是否已经被删除
				Object[] nodataArr = { "评论" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, nodataArr), null);
			}
			// 验证是否点过赞
			IncorruptCommentRecommend commendRecommend = 
					commentRecommendService.queryIncorruptCommentRecommend(id, appUserId, null);
			// 更新正赞数、倒赞数
			if (commendRecommend == null) {// 更新正赞数
				// 创建正赞记录对象
				commendRecommend = fromat(ConstantStr.ZZ_GZ_SC, id, appUserId);
				// 设置赞+1
				comment.setLikesNumber(comment.getLikesNumber() + 1);
				// 设置负赞量－1
				if(comment.getHateNumber()!=null && comment.getHateNumber()>0){
					comment.setHateNumber(comment.getHateNumber() - 1);
				}else{
					comment.setHateNumber(0);
				}
				// 增加消息
				arr = addMessage(arr, "");
				data.put("result", true);
			} else {// 更新倒赞数
				// 设置赞量-1
				if(comment.getLikesNumber()!=null && comment.getLikesNumber()>0){
					comment.setLikesNumber(comment.getLikesNumber() - 1);
				}else{
					comment.setLikesNumber(0);
				}
				// 设置非收藏量+1
				comment.setHateNumber(comment.getHateNumber() + 1);
				// 增加消息
				arr = addMessage(arr, "取消");
				data.put("result", false);
			}
			// 设置更新回数
			comment.setUpdEac(comment.getUpdEac() + 1);
			// 设置更新时间
			comment.setUpdYmdhms(new Date());
			// 更新数据并保存点赞记录
			boolean result = commentService.updateComent(comment, commendRecommend);
			if (result) {
				map.put("data", data);
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200070, arr), map);
			} else {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200071, arr), map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002, arr) + e.getMessage(),
					null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 新增风清气正评论
	 * @author:YK
	 * @CreateDate:2016年9月13日 上午11:32:55
	 * @param comment
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addComment", method = { RequestMethod.POST })
	public @ResponseBody String addComment(IncorruptGovernmentComment comment, @RequestParam String safekey,
			HttpServletRequest request) {
		Object[] arr = { "评论", "" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 验证评论内容是否为空
			if (StringUtils.isBlank(comment.getContent())) { // 如果为空
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200004), null);
			}
			// 内容解密
			comment.setContent(UnescapeUtil.unescape(comment.getContent()));
			// 最多支持输入长度255
			if (comment.getContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(comment.getContent());
			if (illegal) {// 设置违规标示
				comment.setIllegalStatus(ConstantStr.WG_SUM);
			} else {// 设置正常标示
				comment.setIllegalStatus(ConstantStr.ZC_SUM);
			}
			// 内容加密
			comment.setContent(UnescapeUtil.escape(comment.getContent()));
			comment.setOpreaterIp(getIpAddr(request));// 设置评论人ip
			comment.setOpreaterTime(new Date());// 设置评论时间
			comment.setInsId(comment.getOpreaterId());// 新增评论的插入人
			comment.setInsYmdhms(new Date());// 新增评论的插入时间
			comment.setUpdEac((long) 0);// 新增评论的更新回数
			comment.setUpdYmdhms(new Date());// 新增评论的更新时间
			comment.setDelFlag(ConstantStr.DELETE_N);// 设置删除标示符
			boolean result = commentService.saveComment(comment);// 保存评论内容
			if (result) {
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200070, arr), null);
			} else {
				return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200071, arr), null);
			}
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 获取评论的回复列表
	 * @author:YK
	 * @CreateDate:2016年9月13日 下午4:53:22
	 * @param commentRVo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getCommentRlist")
	public @ResponseBody String getCommentRlist(IncorruptGovernmentCommentRVo commentRVo, @RequestParam Long appUserId, 
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "风清气正评论" };
		try {
			// 查询评论
			IncorruptGovernmentCommentVo commentVo = commentService.findCommentVoById(commentRVo
					.getIncorruptGovernmentCommentId());
			if (commentVo == null) {// 验证评论数据是否存在
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 更新评论的浏览量
			IncorruptGovernmentComment comment = commentService.findById(commentRVo.getIncorruptGovernmentCommentId());
			// 设置浏览量+1
			comment.setViewNumber(comment.getViewNumber() + 1);
			// 设置更新回数+1
			comment.setUpdEac(comment.getUpdEac() + 1);
			comment.setUpdYmdhms(new Date());
			commentService.updateSelective(comment);// 更新评论的主数据信息
			// 设置只获取正常评论
			commentRVo.setIllegalStatus(ConstantStr.ILLEAGL_FLAG_ZC);
			// 根据评论id查找评论的回复
			List<Map<String, Object>> list = commentRService.queryListByParam(commentRVo);
			// 查询点赞记录
			IncorruptCommentRecommend commendRecommend = 
					commentRecommendService.queryIncorruptCommentRecommend(commentRVo.getIncorruptGovernmentCommentId(), appUserId, null);

			// 设置查询结果
			Map<String, Object> data = new HashMap<String, Object>();
			// 设置评论详情
			data.put("id", commentVo.getId());
			data.put("headPortrait", commentVo.getHeadPortrait());
			data.put("nickName", commentVo.getNickName());
			data.put("content", commentVo.getContent());
			data.put("opreaterTime", commentVo.getOpreaterTime());
			data.put("hateNumber", commentVo.getHateNumber());
			data.put("likesNumber", commentVo.getLikesNumber());

			data.put("returnList", list);
			// 如果存在点赞记录，则返回true
			if(commendRecommend != null){
				data.put("result", true);
			}else{
				data.put("result", false);
			}
			map.put("data", data);
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
	 * @Description 保存风清气正评论回复内容
	 * @author:YK
	 * @CreateDate:2016年9月14日 上午9:38:37
	 * @param commentR
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addCommentR", method = { RequestMethod.POST })
	public @ResponseBody String addCommentR(IncorruptGovernmentCommentR commentR, @RequestParam String safekey,
			HttpServletRequest request) {
		Object[] arr = { "评论", "" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 验证评论内容是否为空
			if (StringUtils.isBlank(commentR.getContent())) { // 如果为空
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200004), null);
			}
			// 内容解密
			commentR.setContent(UnescapeUtil.unescape(commentR.getContent()));
			// 最多支持输入长度255
			if (commentR.getContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(commentR.getContent());
			if (illegal) {// 设置违规标示
				commentR.setIllegalStatus(ConstantStr.WG_SUM);
			} else {// 设置正常标示
				commentR.setIllegalStatus(ConstantStr.ZC_SUM);
			}
			// 内容加密
			commentR.setContent(UnescapeUtil.escape(commentR.getContent()));
			commentR.setOpreaterIp(getIpAddr(request));// 设置评论回复人ip
			commentR.setOpreaterTime(new Date());// 设置评论回复时间
			commentR.setInsId(commentR.getOpreaterId());// 新增评论回复的插入人
			commentR.setInsYmdhms(new Date());// 新增评论回复的插入时间
			commentR.setUpdEac((long) 0);// 新增评论回复的更新回数
			commentR.setUpdYmdhms(new Date());// 新增评论回复的更新时间
			commentR.setDelFlag(ConstantStr.DELETE_N);// 设置删除标示符
			boolean result = commentRService.saveCommentR(commentR); // 保存评论回复数据
			if (result) {
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200070, arr), null);
			} else {
				return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200071, arr), null);
			}
		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 风清气正关注不关注记录封装
	 * @author:YK
	 * @CreateDate:2016年9月21日 上午9:52:05
	 * @param operatorType
	 * @param id
	 * @param userId
	 * @return IncorruptRecommend
	 */
	public IncorruptRecommend fromatIncorruptRecommend(String operatorType, Long id, Long userId) {
		IncorruptRecommend recommend = new IncorruptRecommend();
		recommend.setIncorruptId(id);
		recommend.setOperatorType(operatorType);
		recommend.setOperatorId(userId);
		recommend.setOperatorTime(new Date());
		recommend.setInsId(userId);
		recommend.setInsYmdhms(new Date());
		recommend.setUpdId(userId);
		recommend.setUpdEac(0L);
		recommend.setUpdYmdhms(new Date());
		recommend.setDelFlag(ConstantStr.DELETE_N);
		return recommend;
	}

	/**
	 * 
	 * @Title
	 * @Description 点赞记录封装
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午9:20:18
	 * @param commentId
	 * @param userId
	 * @return IncorruptCommentRecommend
	 */
	private IncorruptCommentRecommend fromat(String operatorType, Long commentId, Long userId) {
		IncorruptCommentRecommend commendRecommend = new IncorruptCommentRecommend();
		commendRecommend.setIncorruptCommentId(commentId);
		commendRecommend.setOperatorType(operatorType);
		commendRecommend.setOperatorId(userId);
		commendRecommend.setOperatorTime(new Date());
		commendRecommend.setInsId(userId);
		commendRecommend.setInsYmdhms(new Date());
		commendRecommend.setUpdId(userId);
		commendRecommend.setUpdEac(0L);
		commendRecommend.setUpdYmdhms(new Date());
		commendRecommend.setDelFlag(ConstantStr.DELETE_N);
		return commendRecommend;
	};

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

	/**
	 * 
	 * @Title
	 * @Description 添加提示消息
	 * @author:YK
	 * @CreateDate:2016年9月13日 上午9:05:48
	 * @param original
	 * @param message
	 * @return Object
	 */
	private Object[] addMessage(Object[] original, String message) {
		List<Object> messageList = new ArrayList<Object>();
		messageList.add(message);
		for (Object obj : original) {
			messageList.add(obj);
		}
		return messageList.toArray();
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
