package com.wooxun.geekdol.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.soft863.dolphin.common.CommonEntity;
import com.wooxun.geekdol.common.BaiduMapUtils;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.hbridge.model.IncorruptCommentRecommend;
import com.wooxun.geekdol.hmedia.mapper.ActivityCollectionMapper;
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
import com.wooxun.geekdol.hmedia.model.ActivityCollection;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStorePromotion;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.hmedia.model.CommonPhone;
import com.wooxun.geekdol.hmedia.model.CommunityPhone;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.model.HeartBeatLike;
import com.wooxun.geekdol.hmedia.model.HeartBeatReport;
import com.wooxun.geekdol.hmedia.model.HeartBeatUserVillage;
import com.wooxun.geekdol.hmedia.model.HeartCommentReply;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.model.IntimateNewsComment;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentRecommend;
import com.wooxun.geekdol.hmedia.service.ActivityBaomingService;
import com.wooxun.geekdol.hmedia.service.ActivityCollectionService;
import com.wooxun.geekdol.hmedia.service.AroundStoreService;
import com.wooxun.geekdol.hmedia.service.AroundSuggestStoreService;
import com.wooxun.geekdol.hmedia.service.CommonPhoneService;
import com.wooxun.geekdol.hmedia.service.CommunityPhoneService;
import com.wooxun.geekdol.hmedia.service.HeartBeatCommentService;
import com.wooxun.geekdol.hmedia.service.HeartBeatLikeService;
import com.wooxun.geekdol.hmedia.service.HeartBeatReportService;
import com.wooxun.geekdol.hmedia.service.HeartBeatService;
import com.wooxun.geekdol.hmedia.service.HeartBeatUserVillageService;
import com.wooxun.geekdol.hmedia.service.HeartCommentReplyService;
import com.wooxun.geekdol.hmedia.service.IntimateNewsCommentRecommendService;
import com.wooxun.geekdol.hmedia.service.IntimateNewsService;
import com.wooxun.geekdol.hmedia.vo.ActivityBaomingVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityVo;
import com.wooxun.geekdol.hmedia.vo.AppCommonPhoneVo;
import com.wooxun.geekdol.hmedia.vo.AppCommunityPhoneVo;
import com.wooxun.geekdol.hmedia.vo.AppIntimateNewsVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
import com.wooxun.geekdol.hmedia.vo.HeartBeatCommentVo;
import com.wooxun.geekdol.hmedia.vo.HeartBeatReportVo;
import com.wooxun.geekdol.hmedia.vo.HeartBeatVo;
import com.wooxun.geekdol.hmedia.vo.HeartCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsVo;
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
import com.wooxun.geekdol.system.service.AttachService;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.KeywordsService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年8月11日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. QZG 2016年8月11日 上午9:58:19 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("apphmedia")
public class AppHmediaController {

	@Autowired
	private CommonPhoneService<CommonPhone> commonPhoneService;
	@Autowired
	private CommunityPhoneService<CommunityPhone> communityPhoneService;
	@Autowired
	private AroundStoreService<AroundStore> aroundStoreService;
	@Autowired
	private AroundSuggestStoreService<AroundSuggestStore> aroundSuggestStoreService;
	@Autowired
	private ActivityCollectionService<ActivityCollection> activityCollectionService;
	@Autowired
	private KeywordsService<Keywords> keywordsService;
	@Autowired
	private ActivityBaomingService<ActivityBaoming> activityBaomingService;
	@Autowired
	private IntimateNewsService<IntimateNews> intimateNewsService;
	@Autowired
	private IntimateNewsCommentRecommendService<IntimateNewsCommentRecommend> intimateNewsCommentRecommendService;
	@Autowired
	private AppUserService<AppUser> appUserService;
	@Autowired
	private ActivityCollectionMapper<ActivityCollection> activityCollectionMapper;
	@Autowired
	private UserService<User> userService;
	@Autowired
	private VillageService<Village> villageService;
	@Autowired
	private CommunityService<Community> communityService;
	@Autowired
	private CountyService<County> countyService;
	@Autowired
	private CityService<City> cityService;
	@Autowired
	private ProvinceService<Province> provinceService;
	@Autowired
	private SysDataService<SysData> sysDataService;
	@Autowired
	private HeartBeatService<HeartBeat> heartBeatService;
	@Autowired
	private HeartBeatUserVillageService<HeartBeatUserVillage> heartBeatUserVillageService;
	@Autowired
	private HeartBeatReportService<HeartBeatReport> heartBeatReportService;
	@Autowired
	private HeartBeatCommentService<HeartBeatComment> heartBeatCommentService;
	@Autowired
	private HeartCommentReplyService<HeartCommentReply> heartCommentReplyService;
	@Autowired
	private AttachService<Attach> attachService;
	@Autowired
	private HeartBeatLikeService<HeartBeatLike> heartBeatLikeService;

	/**
	 * 
	 * @Title
	 * @Description 生活热线
	 * @author:QZG
	 * @CreateDate:2016年8月13日 上午11:24:13
	 * @param villageId
	 *            小区id
	 * @return
	 */
	@RequestMapping("/phone")
	public @ResponseBody String phone(@RequestParam Long villageId) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建product变量用户封装返回数据
		Map<String, Object> product = new HashMap<String, Object>();
		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));

			// 创建communityPhone用于封装查询条件
			CommunityPhone communityPhone = new CommunityPhone();
			// 传入小区id参数
			communityPhone.setVillageId(villageId);

			// 返回小区电话列表社区电话
			List<AppCommunityPhoneVo> communityPhoneListSQ = communityPhoneService.findPhoneSQ(communityPhone);
			// 封装小区电话列表
			product.put("communityPhoneListSQ", communityPhoneListSQ);

			// 返回小区电话列表服务电话
			List<AppCommunityPhoneVo> communityPhoneListFW = communityPhoneService.findPhoneFW(communityPhone);
			// 封装小区电话列表
			product.put("communityPhoneListFW", communityPhoneListFW);

			// 返回常用电话列表
			List<AppCommonPhoneVo> commonphoneList = commonPhoneService.findCommonPhone();
			// 封装常用电话列表
			product.put("commonphone", commonphoneList);

			// 封装返回的信息
			map.put("data", product);
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			// 控制台打印异常
			e.printStackTrace();
			// 封装失败信息
			map.put("resultCode", ConstantStr.APP_CODE500);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage());
			// 返回JSON数据
			return JSONObject.fromObject(map).toString();
		}
	}

	/**
	 * @Title
	 * @Description App获取周边店或者推荐店信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午3:54:23
	 * @param villageId
	 * @param asFlag
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStoreMessage")
	public @ResponseBody String getStoreMessage(@RequestParam Long villageId, @RequestParam String asFlag,
			@RequestParam(value = "storeName", required = false) String storeName, HttpServletRequest request) {
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
			if (ConstantStr.ASFALG_AS.equals(asFlag)) { // 如果请求来源是周边店
				// 调用service方法根据小区id获取周边店和小区的对应关系
				List<AroundStoreVillage> asvList = aroundStoreService.findAroundStoreVillageByVillageId(villageId);
				// 创建Vo对象用于封装参数查询列表信息
				AroundStoreVo aroundStoreVo = new AroundStoreVo();
				// 设置分页标志位
				aroundStoreVo.setPageFlag(true);
				// 设置开始记录
				aroundStoreVo.setStartPage((page - 1) * rows);
				// 设置结束记录
				aroundStoreVo.setEndPage(rows);
				// 判断店名是否为空
				if (StringUtils.isNotBlank(storeName)) { // 如果不为空
					if ("GET".equals(request.getMethod())) { // get请求进行转码
						storeName = new String(storeName.getBytes("iso8859-1"), "UTF-8");
					}
				}
				// 创建Map变量用于存放sql查询参数
				Map<String, Object> param = new HashMap<String, Object>();
				/* 向map中放入参数 */
				param.put("asvList", asvList);
				param.put("aroundStoreVo", aroundStoreVo);
				param.put("storeName", storeName);
				// 调用service方法查询周边店信息
				List<AroundStore> aroundStoreList = new ArrayList<AroundStore>();
				if (asvList.size() > 0) { // 如果有对应关系
					aroundStoreList = aroundStoreService.findAroundStoreByIdList(param);
				}
				// 封装数据
				for (AroundStore as : aroundStoreList) {
					// 获取周边店的宣传图片
					List<String> urlList = aroundStoreService.findAttachById(as.getId());
					// 创建Map变量用于封装返回数据
					Map<String, Object> data = new HashMap<>();
					// 封装周边店id
					data.put("id", as.getId());
					// 封装店家名字
					data.put("name", as.getName());
					// 封装店家图片
					data.put("storeImage", as.getStoreImage());
					// 封装促销信息
					data.put("promotionInfo", "");
					// 封装拨打电话次数
					data.put("dialCount", as.getDialCount());
					// 封装店家联系电话
					data.put("concatPhone", as.getConcatPhone());
					// 封装宣传图片路径
					data.put("urlList", urlList);
					// 向list中添加数据信息
					dataList.add(data);
				}
			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) { // 如果请求来源是推荐店
				// 调用service方法根据小区id获取推荐店和小区的对应关系
				List<AroundSuggestStoreVillage> assvList = aroundSuggestStoreService
						.findAroundSuggestStoreVillageByVillageId(villageId);
				// 创建Vo对象用于封装参数查询列表信息
				AroundSuggestStoreVo aroundSuggestStoreVo = new AroundSuggestStoreVo();
				// 设置分页标志位
				aroundSuggestStoreVo.setPageFlag(true);
				// 设置开始记录
				aroundSuggestStoreVo.setStartPage((page - 1) * rows);
				// 设置结束记录
				aroundSuggestStoreVo.setEndPage(rows);
				// 创建Map变量用于存放sql查询参数
				Map<String, Object> param = new HashMap<String, Object>();
				/* 向map中放入参数 */
				param.put("assvList", assvList);
				param.put("aroundSuggestStoreVo", aroundSuggestStoreVo);
				param.put("storeName", storeName);
				param.put("nowDate", new Date());
				// 调用service方法查询推荐店信息
				List<AroundSuggestStore> aroundSuggestStoreList = new ArrayList<AroundSuggestStore>();
				if (assvList.size() > 0) { // 如果有对应关系
					aroundSuggestStoreList = aroundSuggestStoreService.findAroundSuggestStoreByIdList(param);
				}
				// 封装数据
				for (AroundSuggestStore ass : aroundSuggestStoreList) {
					// 调用service方法获取推荐店的宣传图片
					List<String> urlList = aroundSuggestStoreService.findAttachById(ass.getId());
					String promotionInfo = "";
					// 调用service方法查询对应的促销信息
					AroundSuggestStorePromotion aroundSuggestStorePromotion = aroundSuggestStoreService
							.findOnePromotionByASSId(ass.getId());
					// 如果没有促销信息,设置为暂无
					if (aroundSuggestStorePromotion == null
							|| "".equals(aroundSuggestStorePromotion.getPromotionInfo().trim())) {
						promotionInfo = "暂无";
					} else {
						promotionInfo = aroundSuggestStorePromotion.getPromotionInfo();
					}
					// 创建Map变量用于封装返回数据
					Map<String, Object> data = new HashMap<>();
					// 封装推荐店id
					data.put("id", ass.getId());
					// 封装店家名字
					data.put("name", ass.getName());
					// 封装店家图片
					data.put("storeImage", ass.getStoreImage());
					// 封装促销信息
					data.put("promotionInfo", promotionInfo);
					// 封装拨打电话次数
					data.put("dialCount", ass.getDialCount());
					// 封装店家联系电话
					data.put("concatPhone", ass.getConcatPhone());
					// 封装宣传图片路径
					data.put("urlList", urlList);
					// 向list中添加数据信息
					dataList.add(data);
				}
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
	 * @Description App获取周边店或者推荐店详情
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午3:54:48
	 * @param storeId
	 * @param asFlag
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStoreMessageDetail")
	public @ResponseBody String getStoreMessageDetail(@RequestParam Long storeId, @RequestParam String asFlag,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装返回数据
		Map<String, Object> data = new HashMap<>();
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 调用service方法根据小区id获取周边店
				AroundStore aroundStore = aroundStoreService.get(storeId);
				if (aroundStore == null) { // 如果已经被删除
					Object[] attribute = { "周边店" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}
				// 创建周边店Model对象
				AroundStore aroundStoreTemp = new AroundStore();
				// 设置周边店id
				aroundStoreTemp.setId(storeId);
				// 更新周边店浏览次数
				aroundStoreTemp.setViewNumber(aroundStore.getViewNumber() + 1);
				// 更新周边店更新回数
				aroundStoreTemp.setUpdEac(aroundStore.getUpdEac() + 1);
				// 调用service方法更新数据
				int result = aroundStoreService.updateSelective(aroundStoreTemp);
				if (result <= 0) { // 如果更新失败
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200002), null);
				}
				// 创建Map对象用于存放店家详情
				Map<String, Object> storeDetail = new HashMap<String, Object>();

				// 创建Map对象用于存放星级数量
				Map<String, Integer> stars = new HashMap<String, Integer>();
				/* 向星级中封装数据 */
				stars.put("oneStar", aroundStore.getOneCount());
				stars.put("twoStar", aroundStore.getTwoCount());
				stars.put("threeStar", aroundStore.getThreeCount());
				stars.put("fourStar", aroundStore.getFourCount());
				stars.put("fiveStar", aroundStore.getFiveCount());

				// 计算星级评分
				Double starLevel = ((aroundStore.getOneCount() * 1.0 + aroundStore.getTwoCount() * 2.0
						+ aroundStore.getThreeCount() * 3.0 + aroundStore.getFourCount() * 4.0 + aroundStore
						.getFiveCount() * 5.0) / (aroundStore.getOneCount() + aroundStore.getTwoCount()
						+ aroundStore.getThreeCount() + aroundStore.getFourCount() + aroundStore.getFiveCount()));
				if (starLevel.isNaN()) {
					starLevel = 0.0;
				}

				// 封装店家名字
				data.put("name", aroundStore.getName());
				// 封装店家经度
				data.put("longitude", aroundStore.getLongitude());
				// 封装店家纬度
				data.put("latitude", aroundStore.getLatitude());
				// 封装店家星级
				data.put("stars", stars);
				// 封装店家星级评分
				data.put("starLevel", starLevel);
				// 封装店家图片
				data.put("storeImage",
						StringUtils.isEmpty(aroundStore.getStoreImage()) ? "" : aroundStore.getStoreImage());
				// 封装联系电话
				data.put("concatPhone", aroundStore.getConcatPhone());
				// 封装详细地址
				data.put("address", aroundStore.getAdress());
				// 封装网格长推荐的店家详情
				data.put("storeDetail", storeDetail);

				// 创建Vo对象用于封装参数查询列表信息
				AroundStoreCommentVo aroundStoreCommentVo = new AroundStoreCommentVo();
				// 设置分页标志位
				aroundStoreCommentVo.setPageFlag(false);
				// 设置周边店id
				aroundStoreCommentVo.setAroundStoreId(storeId);
				// 设置周边店评论违规flag
				aroundStoreCommentVo.setIllegalFlag(true);

				// 调用service方法获取评论
				List<AroundStoreComment> commentDataList = aroundStoreService.findCommentList(aroundStoreCommentVo);

				for (AroundStoreComment asc : commentDataList) {
					AppUser appUser = asc.getAppUser();
					// 创建Map变量用于封装返回数据
					Map<String, Object> comment = new HashMap<>();
					// 封装评论id
					comment.put("commentId", asc.getId());
					// 封装评论人id
					comment.put("commentUserId", appUser == null ? 0 : appUser.getId());
					// 封装评论人头像
					comment.put("commentHead", appUser == null ? "" : appUser.getHeadPortrait());
					// 封装评论人名称
					comment.put("commentUserName",
							appUser == null ? ConstantStr.COMMENT_USER_NAME : appUser.getNickName());
					// 封装评论内容
					comment.put("commentContent", asc.getContent());
					// 封装评论时间
					comment.put("commentDate", asc.getInsYmdhms());
					// 封装被评论条数
					comment.put("commentReplyCount", asc.getReplyNumber());
					// 封装该条评论点赞数
					comment.put("commentLikes", asc.getLikesNumber());
					// 封装该条评论倒赞数
					comment.put("commentHates", asc.getHateNumber());
					// 封装该条评论的星级
					comment.put("commentStarType", asc.getStarType());
					// 向list中添加数据
					commentList.add(comment);
				}

			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 调用service方法根据小区id获取推荐店和小区的对应关系
				AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreService.findAroundSuggestStore(storeId);
				if (aroundSuggestStoreVo == null) { // 如果已经被删除
					Object[] attribute = { "推荐店" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}
				// 创建推荐店Model对象
				AroundSuggestStore aroundSuggestStoreTemp = new AroundSuggestStore();
				// 设置推荐店id
				aroundSuggestStoreTemp.setId(storeId);
				// 更新推荐店浏览次数
				aroundSuggestStoreTemp.setViewNumber(aroundSuggestStoreVo.getViewNumber() + 1);
				// 更新推荐店更新回数
				aroundSuggestStoreTemp.setUpdEac(aroundSuggestStoreVo.getUpdEac() + 1);
				// 调用service方法更新
				int result = aroundSuggestStoreService.updateSelective(aroundSuggestStoreTemp);
				if (result <= 0) { // 如果更新失败
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200002), null);
				}
				// 创建Map对象用于存放星级数量
				Map<String, Integer> stars = new HashMap<String, Integer>();
				/* 向星级中封装数据 */
				stars.put("oneStar", aroundSuggestStoreVo.getOneCount());
				stars.put("twoStar", aroundSuggestStoreVo.getTwoCount());
				stars.put("threeStar", aroundSuggestStoreVo.getThreeCount());
				stars.put("fourStar", aroundSuggestStoreVo.getFourCount());
				stars.put("fiveStar", aroundSuggestStoreVo.getFiveCount());

				// 计算星级评分
				Double starLevel = ((aroundSuggestStoreVo.getOneCount() * 1.0 + aroundSuggestStoreVo.getTwoCount()
						* 2.0 + aroundSuggestStoreVo.getThreeCount() * 3.0 + aroundSuggestStoreVo.getFourCount() * 4.0 + aroundSuggestStoreVo
						.getFiveCount() * 5.0) / (aroundSuggestStoreVo.getOneCount()
						+ aroundSuggestStoreVo.getTwoCount() + aroundSuggestStoreVo.getThreeCount()
						+ aroundSuggestStoreVo.getFourCount() + aroundSuggestStoreVo.getFiveCount()));
				if (starLevel.isNaN()) {
					starLevel = 0.0;
				}

				// 创建Map对象用于存放店家详情
				Map<String, Object> storeDetail = new HashMap<String, Object>();
				/* 向店家详情中封装数据 */
				storeDetail.put("introduce", StringUtils.isEmpty(aroundSuggestStoreVo.getIntroduce()) ? ""
						: aroundSuggestStoreVo.getIntroduce());
				// 调用service方法获取推荐店的宣传图片
				storeDetail.put("urlList", aroundSuggestStoreService.findAttachById(aroundSuggestStoreVo.getId()));
				storeDetail.put("promotionList",
						aroundSuggestStoreService.findPromotionDetailsById(aroundSuggestStoreVo.getId()));

				// 封装店家名字
				data.put("name", aroundSuggestStoreVo.getName());
				// 封装店家经度
				data.put("longitude", aroundSuggestStoreVo.getLongitude());
				// 封装店家纬度
				data.put("latitude", aroundSuggestStoreVo.getLatitude());
				// 封装店家星级
				data.put("stars", stars);
				// 封装店家星级评分
				data.put("starLevel", starLevel);
				// 封装店家图片
				data.put("storeImage", StringUtils.isEmpty(aroundSuggestStoreVo.getStoreImage()) ? ""
						: aroundSuggestStoreVo.getStoreImage());
				// 封装联系电话
				data.put("concatPhone", aroundSuggestStoreVo.getConcatPhone());
				// 封装详细地址
				data.put("address", aroundSuggestStoreVo.getAdress());
				// 封装网格长推荐的店家详情
				data.put("storeDetail", storeDetail);

				// 创建Vo对象用于封装参数查询列表信息
				AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo = new AroundSuggestStoreCommentVo();
				// 设置分页标志位
				aroundSuggestStoreCommentVo.setPageFlag(false);
				// 设置推荐店id
				aroundSuggestStoreCommentVo.setAroundSuggestStoreId(storeId);
				// 设置推荐店评论违规flag
				aroundSuggestStoreCommentVo.setIllegalFlag(true);

				// 调用service方法获取评论
				List<AroundSuggestStoreComment> commentDataList = aroundSuggestStoreService
						.findCommentList(aroundSuggestStoreCommentVo);

				for (AroundSuggestStoreComment assc : commentDataList) {
					AppUser appUser = assc.getAppUser();
					// 创建Map变量用于封装返回数据
					Map<String, Object> comment = new HashMap<>();
					// 封装评论id
					comment.put("commentId", assc.getId());
					// 封装评论人id
					comment.put("commentUserId", appUser == null ? 0 : appUser.getId());
					// 封装评论人头像
					comment.put("commentHead", appUser == null ? "" : appUser.getHeadPortrait());
					// 封装评论人名称
					comment.put("commentUserName",
							appUser == null ? ConstantStr.COMMENT_USER_NAME : appUser.getNickName());
					// 封装评论内容
					comment.put("commentContent", assc.getContent());
					// 封装评论时间
					comment.put("commentDate", assc.getInsYmdhms());
					// 封装被评论条数
					comment.put("commentReplyCount", assc.getReplyNumber());
					// 封装该条评论点赞数
					comment.put("commentLikes", assc.getLikesNumber());
					// 封装该条评论倒赞数
					comment.put("commentHates", assc.getHateNumber());
					// 封装该条评论的星级
					comment.put("commentStarType", assc.getStarType());
					// 向list中添加数据
					commentList.add(comment);
				}
			}
			// 向map中放入评论详情数据用于返回
			data.put("comment", commentList);
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
	 * @Title
	 * @Description 一级评论详情
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 上午12:04:13
	 * @param commentId
	 * @param asFlag
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCommentDetail")
	public @ResponseBody String getCommentDetail(@RequestParam Long commentId, @RequestParam String asFlag,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装返回数据
		Map<String, Object> data = new HashMap<>();
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> commentReplyList = new ArrayList<Map<String, Object>>();
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 调用service方法根据评论id获取评论相关信息
				AroundStoreCommentVo aroundStoreCommentVo = aroundStoreService.findAroundStoreComment(commentId);
				if (aroundStoreCommentVo == null) { // 如果已经被删除
					Object[] attribute = { "评论" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}
				/* 更新评论浏览量 */
				AroundStoreComment aroundStoreComment = new AroundStoreComment();
				aroundStoreComment.setId(commentId);
				aroundStoreComment.setViewNumber(aroundStoreCommentVo.getViewNumber() + 1);
				aroundStoreComment.setUpdEac(aroundStoreCommentVo.getUpdEac() + 1);
				aroundStoreService.updateAroundStoreComment(aroundStoreComment);
				// 封装被评论内容id
				data.put("commentId", aroundStoreCommentVo.getId());
				// 封装被评论内容的作者
				data.put("commentUserName", aroundStoreCommentVo.getAppUser().getNickName());
				// 封装被评论内容的作者的头像
				data.put("commentHead", StringUtils.isEmpty(aroundStoreCommentVo.getAppUser().getHeadPortrait()) ? ""
						: aroundStoreCommentVo.getAppUser().getHeadPortrait());
				// 封装被评论内容的内容
				data.put("commentContent", aroundStoreCommentVo.getContent());
				// 封装被评论内容的时间
				data.put("commentDate", aroundStoreCommentVo.getInsYmdhms());
				// 封装被评论内容的点赞数
				data.put("commentLikes", aroundStoreCommentVo.getLikesNumber());
				// 封装被评论内容的点倒赞数
				data.put("commentHates", aroundStoreCommentVo.getHateNumber());

				// 创建Vo对象用于封装参数查询列表信息
				AroundStoreCommentReplyVo aroundStoreCommentReplyVo = new AroundStoreCommentReplyVo();
				// 设置分页标志位
				aroundStoreCommentReplyVo.setPageFlag(false);
				// 设置周边店评论id
				aroundStoreCommentReplyVo.setAroundStoreCommentId(commentId);
				// 设置周边店违规flag
				aroundStoreCommentReplyVo.setIllegalFlag(true);

				// 调用service方法获取评论
				List<AroundStoreCommentReplyVo> commentReplyDataList = aroundStoreService
						.findAroundStoreCommentReplyList(aroundStoreCommentReplyVo);

				for (AroundStoreCommentReplyVo ascrv : commentReplyDataList) {
					// 创建Map变量用于封装返回数据
					Map<String, Object> commentReply = new HashMap<>();
					// 封装评论人id
					commentReply.put("replyUserId", ascrv.getInsId());
					// 封装评论人头像
					commentReply.put("replyUserHead", ascrv.getInsHead());
					// 封装评论人名称
					commentReply.put("replyUserName", ascrv.getInsName());
					// 封装评论内容
					commentReply.put("replyContent", ascrv.getContent());
					// 封装评论时间
					commentReply.put("replyDate", ascrv.getInsYmdhms());
					// 向list中添加数据
					commentReplyList.add(commentReply);
				}

			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 调用service方法根据评论id获取评论相关信息
				AroundSuggestStoreComment aroundSuggestStoreComment = aroundSuggestStoreService
						.findAroundSuggestStoreComment(commentId);
				if (aroundSuggestStoreComment == null) { // 如果已经被删除
					Object[] attribute = { "评论" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}
				/* 更新评论浏览量 */
				AroundSuggestStoreComment aroundSuggestStoreCommentTemp = new AroundSuggestStoreComment();
				aroundSuggestStoreCommentTemp.setId(commentId);
				aroundSuggestStoreCommentTemp.setViewNumber(aroundSuggestStoreComment.getViewNumber() + 1);
				aroundSuggestStoreCommentTemp.setUpdEac(aroundSuggestStoreComment.getUpdEac() + 1);
				aroundSuggestStoreService.updateAroundStoreComment(aroundSuggestStoreCommentTemp);
				// 封装被评论内容id
				data.put("commentId", aroundSuggestStoreComment.getId());
				// 封装被评论内容的作者
				data.put("commentUserName", aroundSuggestStoreComment.getAppUser().getNickName());
				// 封装被评论内容的作者的头像
				data.put("commentHead",
						StringUtils.isEmpty(aroundSuggestStoreComment.getAppUser().getHeadPortrait()) ? ""
								: aroundSuggestStoreComment.getAppUser().getHeadPortrait());
				// 封装被评论内容的内容
				data.put("commentContent", aroundSuggestStoreComment.getContent());
				// 封装被评论内容的时间
				data.put("commentDate", aroundSuggestStoreComment.getInsYmdhms());
				// 封装被评论内容的点赞数
				data.put("commentLikes", aroundSuggestStoreComment.getLikesNumber());
				// 封装被评论内容的点倒赞数
				data.put("commentHates", aroundSuggestStoreComment.getHateNumber());

				// 创建Vo对象用于封装参数查询列表信息
				AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo = new AroundSuggestStoreCommentReplyVo();
				// 设置分页标志位
				aroundSuggestStoreCommentReplyVo.setPageFlag(false);
				// 设置推荐店评论id
				aroundSuggestStoreCommentReplyVo.setAroundSuggestStoreCommentId(commentId);
				// 设置周边店违规flag
				aroundSuggestStoreCommentReplyVo.setIllegalFlag(true);

				// 调用service方法获取评论
				List<AroundSuggestStoreCommentReplyVo> commentReplyDataList = aroundSuggestStoreService
						.findAroundSuggestStoreCommentReplyList(aroundSuggestStoreCommentReplyVo);

				for (AroundSuggestStoreCommentReplyVo asscrv : commentReplyDataList) {
					// 创建Map变量用于封装返回数据
					Map<String, Object> commentReply = new HashMap<>();
					// 封装评论人id
					commentReply.put("replyUserId", asscrv.getInsId());
					// 封装评论人头像
					commentReply.put("replyUserHead", asscrv.getInsHead());
					// 封装评论人名称
					commentReply.put("replyUserName", asscrv.getInsName());
					// 封装评论内容
					commentReply.put("replyContent", asscrv.getContent());
					// 封装评论时间
					commentReply.put("replyDate", asscrv.getInsYmdhms());
					// 向list中添加数据
					commentReplyList.add(commentReply);
				}
			}
			// 向map中放入评论详情数据用于返回
			data.put("commentReply", commentReplyList);
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
	 * @Title
	 * @Description App店一级评论
	 * @author:kangtianyu
	 * @CreateDate:2016年8月11日 下午11:16:08
	 * @param storeId
	 * @param appUserId
	 * @param asFlag
	 * @param paramType
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("/commentStore")
	public @ResponseBody String commentStore(@RequestParam Long storeId, @RequestParam Long appUserId,
			@RequestParam String asFlag, @RequestParam String paramType, @RequestParam String safekey,
			HttpServletRequest request) {
		if (!isLogin(safekey)) {
			if (StringUtils.isNotBlank(safekey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		// 获取评论内容
		String content = StringUtils.isEmpty(request.getParameter("content")) ? "" : request.getParameter("content");
		try {
			// 判断评论内容是否为空
			if (StringUtils.isBlank(content)) { // 如果为空
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200004), null);
			} else { // 如果是get请求中文会乱码,需要进行转码
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					content = new String(content.getBytes("iso8859-1"), "UTF-8");
				}
			}

			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(content);

			// 获取评论者ip
			String opreaterIp = getIpAddr(request);
			opreaterIp = StringUtils.isEmpty(opreaterIp) ? "" : opreaterIp;

			int result = 0;
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 判断周边店是否已经被禁用
				boolean status = aroundStoreService.judgeStatus(storeId);
				if (!status) {
					return fromObject(ConstantStr.APP_CODE601, ComDefine.getMsg(ConstantStr.INFO200052), null);
				}
				/*
				 * // 判断用户当天有没有评论过 AroundStoreComment aroundStoreComment =
				 * aroundStoreService.getAroundStoreCommentByDay(storeId,
				 * appUserId); if (aroundStoreComment != null) { // 如果已经评论过 //
				 * 返回封装的失败数据信息 return fromObject(ConstantStr.APP_CODE403,
				 * ComDefine.getMsg(ConstantStr.INFO200005), null); }
				 */
				// 调用service方法插入周边店一级评论
				result = aroundStoreService.addComment(storeId, appUserId, content, illegal, opreaterIp, paramType);

			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 判断推荐店是否已经被撤销推荐
				boolean status = aroundSuggestStoreService.judgeStatus(storeId);
				if (!status) {
					return fromObject(ConstantStr.APP_CODE601, ComDefine.getMsg(ConstantStr.INFO200052), null);
				}
				/*
				 * // 判断用户当天有没有评论过 AroundSuggestStoreComment
				 * aroundSuggestStoreComment = aroundSuggestStoreService
				 * .getAroundSuggestStoreCommentByDay(storeId, appUserId); if
				 * (aroundSuggestStoreComment != null) { // 如果已经评论过 //
				 * 返回封装的失败数据信息 return fromObject(ConstantStr.APP_CODE403,
				 * ComDefine.getMsg(ConstantStr.INFO200005), null); }
				 */
				// 调用service方法插入推荐店一级评论
				result = aroundSuggestStoreService.addComment(storeId, appUserId, content, illegal, opreaterIp,
						paramType);

			}
			if (result > 0) {
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200006), null);
			} else {
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200007), null);
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
	 * @Description 二级评论
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 上午12:03:03
	 * @param commentId
	 * @param appUserId
	 * @param asFlag
	 * @param request
	 * @param safekey
	 * @return
	 */
	@RequestMapping("/replyCommentStore")
	public @ResponseBody String replyCommentStore(@RequestParam Long commentId, @RequestParam Long appUserId,
			@RequestParam String asFlag, @RequestParam String safekey, HttpServletRequest request) {
		if (!isLogin(safekey)) {
			if (StringUtils.isNotBlank(safekey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		// 获取评论内容
		String content = StringUtils.isEmpty(request.getParameter("content")) ? "" : request.getParameter("content");
		try {
			// 判断评论内容是否为空
			if (StringUtils.isBlank(content)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200004), null);
			} else { // 如果是get请求中文会乱码,需要进行转码
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					content = new String(content.getBytes("iso8859-1"), "UTF-8");
				}
			}

			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(content);

			// 获取评论者ip
			String opreaterIp = getIpAddr(request);
			opreaterIp = StringUtils.isEmpty(opreaterIp) ? "" : opreaterIp;

			int result = 0;
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 调用service方法插入周边店二级评论
				result = aroundStoreService.addCommentReply(commentId, appUserId, content, illegal, opreaterIp);

			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 调用service方法插入推荐店二级评论
				result = aroundSuggestStoreService.addCommentReply(commentId, appUserId, content, illegal, opreaterIp);

			}
			if (result > 0) {
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200006), null);
			} else {
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200007), null);
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
	 * @Description 增加店的指定属性
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 上午11:02:54
	 * @param storeId
	 * @param asFlag
	 * @param paramType
	 * @param request
	 * @return
	 */
	@RequestMapping("/addStoreParam")
	public @ResponseBody String addStoreParam(@RequestParam Long storeId, @RequestParam String asFlag,
			@RequestParam Long appUserId, @RequestParam String paramType, HttpServletRequest request) {
		try {
			int result = 0;
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 判断周边店是否已经被删除
				AroundStore original = aroundStoreService.get(storeId);
				if (original == null) { // 如果已经被删除
					Object[] attribute = { "周边店" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}

				// 判断周边店是否已经被禁用
				boolean status = aroundStoreService.judgeStatus(storeId);
				if (!status) {
					return fromObject(ConstantStr.APP_CODE601, ComDefine.getMsg(ConstantStr.INFO200052), null);
				}

				// 如果是评星或者点赞行为 判断是否当天进行过此操作
				switch (paramType) {
				case ConstantStr.STORE_PARAM_ONESTAR:
				case ConstantStr.STORE_PARAM_TWOSTAR:
				case ConstantStr.STORE_PARAM_THREESTAR:
				case ConstantStr.STORE_PARAM_FOURSTAR:
				case ConstantStr.STORE_PARAM_FIVESTAR:
				case ConstantStr.STORE_PARAM_LIKE:
				case ConstantStr.STORE_PARAM_HATE:
					// 判断当天是否对周边店进行过 评星或者点赞倒赞行为
					boolean isHave = aroundStoreService.getAroundStoreByDay(storeId, appUserId);
					if (isHave) {
						return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200051), null);
					}
					break;
				}

				// 创建Model变量用于更改数据
				AroundStore aroundStore = new AroundStore();
				/* 设置相关数据 */
				aroundStore.setId(storeId);

				if (ConstantStr.STORE_PARAM_ONESTAR.equals(paramType)) { // 一星
					aroundStore.setOneCount(original.getOneCount() + 1);
				} else if (ConstantStr.STORE_PARAM_TWOSTAR.equals(paramType)) { // 二星
					aroundStore.setTwoCount(original.getTwoCount() + 1);
				} else if (ConstantStr.STORE_PARAM_THREESTAR.equals(paramType)) { // 三星
					aroundStore.setThreeCount(original.getThreeCount() + 1);
				} else if (ConstantStr.STORE_PARAM_FOURSTAR.equals(paramType)) { // 四星
					aroundStore.setFourCount(original.getFourCount() + 1);
				} else if (ConstantStr.STORE_PARAM_FIVESTAR.equals(paramType)) { // 五星
					aroundStore.setFiveCount(original.getFiveCount() + 1);
				} else if (ConstantStr.STORE_PARAM_LIKE.equals(paramType)) { // 点赞
					aroundStore.setLikesNumber(original.getLikesNumber() + 1);
				} else if (ConstantStr.STORE_PARAM_HATE.equals(paramType)) { // 倒赞
					aroundStore.setHateNumber(original.getHateNumber() + 1);
				} else if (ConstantStr.STORE_PARAM_DIAL.equals(paramType)) { // 拨打电话次数
					aroundStore.setDialCount(original.getDialCount() + 1);
				}

				// 设置更新回数
				aroundStore.setUpdEac(original.getUpdEac());
				editAttr(aroundStore, appUserId);
				// 调用service方法插入周边店属性
				result = aroundStoreService.addStoreParam(aroundStore);
			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 判断周边店是否已经被删除
				AroundSuggestStore original = aroundSuggestStoreService.get(storeId);
				if (original == null) { // 如果已经被删除
					Object[] attribute = { "推荐店" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}

				// 判断推荐店是否已经被撤销推荐
				boolean status = aroundSuggestStoreService.judgeStatus(storeId);
				if (!status) {
					return fromObject(ConstantStr.APP_CODE601, ComDefine.getMsg(ConstantStr.INFO200052), null);
				}

				// 如果是评星或者点赞行为 判断是否当天进行过此操作
				switch (paramType) {
				case ConstantStr.STORE_PARAM_ONESTAR:
				case ConstantStr.STORE_PARAM_TWOSTAR:
				case ConstantStr.STORE_PARAM_THREESTAR:
				case ConstantStr.STORE_PARAM_FOURSTAR:
				case ConstantStr.STORE_PARAM_FIVESTAR:
				case ConstantStr.STORE_PARAM_LIKE:
				case ConstantStr.STORE_PARAM_HATE:
					// 判断当天是否对周边店进行过 评星或者点赞倒赞行为
					boolean isHave = aroundSuggestStoreService.getAroundSuggestStoreByDay(storeId, appUserId);
					if (isHave) {
						return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200051), null);
					}
					break;
				}

				// 创建Model变量用于更改数据
				AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
				/* 设置相关数据 */
				aroundSuggestStore.setId(storeId);

				if (ConstantStr.STORE_PARAM_ONESTAR.equals(paramType)) { // 一星
					aroundSuggestStore.setOneCount(original.getOneCount() + 1);
				} else if (ConstantStr.STORE_PARAM_TWOSTAR.equals(paramType)) { // 二星
					aroundSuggestStore.setTwoCount(original.getTwoCount() + 1);
				} else if (ConstantStr.STORE_PARAM_THREESTAR.equals(paramType)) { // 三星
					aroundSuggestStore.setThreeCount(original.getThreeCount() + 1);
				} else if (ConstantStr.STORE_PARAM_FOURSTAR.equals(paramType)) { // 四星
					aroundSuggestStore.setFourCount(original.getFourCount() + 1);
				} else if (ConstantStr.STORE_PARAM_FIVESTAR.equals(paramType)) { // 五星
					aroundSuggestStore.setFiveCount(original.getFiveCount() + 1);
				} else if (ConstantStr.STORE_PARAM_LIKE.equals(paramType)) { // 点赞
					aroundSuggestStore.setLikesNumber(original.getLikesNumber() + 1);
				} else if (ConstantStr.STORE_PARAM_HATE.equals(paramType)) { // 倒赞
					aroundSuggestStore.setHateNumber(original.getHateNumber() + 1);
				} else if (ConstantStr.STORE_PARAM_DIAL.equals(paramType)) { // 拨打电话次数
					aroundSuggestStore.setDialCount(original.getDialCount() + 1);
				}

				// 设置更新回数
				aroundSuggestStore.setUpdEac(original.getUpdEac());
				editAttr(aroundSuggestStore, appUserId);
				// 调用service方法插入推荐店属性
				result = aroundSuggestStoreService.addStoreParam(aroundSuggestStore);
			}
			if (result > 0) {
				Object[] attribute = { "店" };
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200008, attribute), null);
			} else {
				Object[] attribute = { "店" };
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200009, attribute), null);
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
	 * @Description 增加评论的指定属性
	 * @author:kangtianyu
	 * @CreateDate:2016年8月12日 下午4:17:13
	 * @param commentId
	 * @param asFlag
	 * @param paramType
	 * @param request
	 * @return
	 */
	@RequestMapping("/addCommentParam")
	public @ResponseBody String addCommentParam(@RequestParam Long commentId, @RequestParam String asFlag,
			@RequestParam Long appUserId, @RequestParam String paramType, HttpServletRequest request) {
		try {
			int result = 0;
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 判断周边店评论是否已经被删除
				AroundStoreCommentVo original = aroundStoreService.findAroundStoreComment(commentId);
				if (original == null) { // 如果已经被删除
					Object[] attribute = { "评论" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}

				// 判断用户当天有没有对周边店一级评论进行过点赞或者倒赞过
				boolean isLikeOrhate = aroundStoreService.getCommentByDay(commentId, appUserId);
				if (isLikeOrhate) { // 如果已经点赞过 // 返回封装的失败数据信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200051), null);
				}

				// 创建Model变量用于更改数据
				AroundStoreComment aroundStoreComment = new AroundStoreComment();
				/* 设置相关数据 */
				aroundStoreComment.setId(commentId);

				if (ConstantStr.STORE_PARAM_LIKE.equals(paramType)) { // 点赞
					aroundStoreComment.setLikesNumber(original.getLikesNumber() + 1);
				} else if (ConstantStr.STORE_PARAM_HATE.equals(paramType)) { // 倒赞
					aroundStoreComment.setHateNumber(original.getHateNumber() + 1);
				}

				// 设置更新回数
				aroundStoreComment.setUpdEac(original.getUpdEac());
				editAttr(aroundStoreComment, appUserId);
				// 调用service方法插入周边店一级评论属性
				result = aroundStoreService.addCommentParam(aroundStoreComment);
			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 判断周边店是否已经被删除
				AroundSuggestStoreComment original = aroundSuggestStoreService.findAroundSuggestStoreComment(commentId);
				if (original == null) { // 如果已经被删除
					Object[] attribute = { "评论" };
					return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute),
							null);
				}

				// 判断用户当天有没有对推荐店一级评论进行过点赞或者倒赞过
				boolean isLikeOrhate = aroundSuggestStoreService.getCommentByDay(commentId, appUserId);
				if (isLikeOrhate) { // 如果已经点赞过 // 返回封装的失败数据信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200051), null);
				}

				// 创建Model变量用于更改数据
				AroundSuggestStoreComment temp = new AroundSuggestStoreComment();
				/* 设置相关数据 */
				temp.setId(commentId);

				if (ConstantStr.STORE_PARAM_LIKE.equals(paramType)) { // 点赞
					temp.setLikesNumber(original.getLikesNumber() + 1);
				} else if (ConstantStr.STORE_PARAM_HATE.equals(paramType)) { // 倒赞
					temp.setHateNumber(original.getHateNumber() + 1);
				}

				// 设置更新回数
				temp.setUpdEac(original.getUpdEac());
				editAttr(temp, appUserId);
				// 调用service方法插入推荐店一级评论属性
				result = aroundSuggestStoreService.addCommentParam(temp);
			}
			if (result > 0) {
				Object[] attribute = { "评论" };
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200008, attribute), null);
			} else {
				Object[] attribute = { "店" };
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200009, attribute), null);
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
	 * @Description 判断店的状态是否可以进行操作
	 * @author:kangtianyu
	 * @CreateDate:2016年8月31日 下午2:14:32
	 * @param storeId
	 * @param asFlag
	 * @return
	 */
	@RequestMapping("/getStoreStatus")
	public @ResponseBody String getStoreStatus(@RequestParam Long storeId, @RequestParam String asFlag) {
		try {

			boolean status = false;
			if (ConstantStr.ASFALG_AS.equals(asFlag)) {
				// 判断周边店是否已经被禁用
				status = aroundStoreService.judgeStatus(storeId);

			} else if (ConstantStr.ASFALG_ASS.equals(asFlag)) {
				// 判断推荐店是否已经被撤销推荐
				status = aroundSuggestStoreService.judgeStatus(storeId);

			}
			if (status) {
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), null);
			} else {
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200052), null);
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
	 * @Description 活动汇列表接口
	 * @author:QZG
	 * @CreateDate:2016年8月13日 下午3:12:54
	 * @param villageId
	 *            小区id
	 * @param title
	 *            活动汇标题
	 * @return
	 */
	@RequestMapping("/activityCollection")
	public @ResponseBody String activityCollection(@RequestParam Long villageId, @RequestParam String title,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询页码 默认为1
		int page = Integer.parseInt(StringUtils.isEmpty(request.getParameter("page")) ? "1" : request
				.getParameter("page"));
		// 设置每页查询条数 默认为10
		int rows = Integer.parseInt(StringUtils.isEmpty(request.getParameter("rows")) ? "10" : request
				.getParameter("rows"));
		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));
			// 如果是get请求中文会乱码,需要进行转码
			if ("GET".equals(request.getMethod())) {
				// get请求进行转码
				title = new String(title.getBytes("iso8859-1"), "UTF-8");
			}
			// 创建appActivityCollectionVo用于封装查询条件
			AppActivityCollectionVo appActivityCollectionVo = new AppActivityCollectionVo();
			// 设置分页标示为true
			appActivityCollectionVo.setPageFlag(true);
			// 设置查询起始记录
			appActivityCollectionVo.setStartPage((page - 1) * rows);
			// 设置查询结束记录
			appActivityCollectionVo.setEndPage(rows);
			// 传入小区id参数
			appActivityCollectionVo.setVillageId(villageId);
			// 传入活动汇标题参数
			appActivityCollectionVo.setTitle(title);

			// 返回满足条件活动汇查询列表
			List<AppActivityCollectionVo> activitylist = activityCollectionService
					.queryActivityCollection(appActivityCollectionVo);
			// 封装返回信息
			map.put("data", activitylist);
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
	 * @Description 活动汇详情接口
	 * @author:QZG
	 * @CreateDate:2016年8月13日 下午3:12:54
	 * @param id
	 *            活动汇id
	 * @return
	 */
	@RequestMapping("/activity")
	public @ResponseBody String activity(@RequestParam Long id) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));
			// 创建appActivityVo用于封装查询条件
			AppActivityVo appActivityVo = new AppActivityVo();
			// 传入活动汇id
			appActivityVo.setId(id);
			// 返回活动汇详细信息
			AppActivityVo activity = activityCollectionService.queryActivity(appActivityVo);
			// 分享链接
			activity.setShareUrl(ConstantStr.SHARE_URL + "?id=" + id + "&flag=" + ConstantStr.SHAREFLAG_ACTIVITY);
			// 根据活动汇id查询活动汇信息
			ActivityCollection activityCollection = activityCollectionService.get(id);
			// 浏览数+1
			activityCollection.setViewNumber(activityCollection.getViewNumber() + 1);
			// 调用更新方法
			editAttr(activityCollection);
			// 更新活动汇表
			activityCollectionService.updateSelective(activityCollection);
			// 详情传入浏览数
			activity.setViewNumber(Long.valueOf(activityCollection.getViewNumber()));
			// 封装返回信息
			map.put("data", activity);
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
	 * @Description 报名接口
	 * @author:王肖东
	 * @CreateDate:2016年9月23日 下午4:12:53
	 * @param id
	 *            活动汇id
	 * @param name
	 *            姓名
	 * @param phone
	 *            手机号
	 * @param remark
	 *            备注
	 * @param villageId
	 *            小区id
	 * @param appUserId
	 *            用户id
	 * @param request
	 * @return
	 */
	@RequestMapping("/baoming")
	public @ResponseBody String baoming(@RequestParam Long id, @RequestParam String name, @RequestParam String phone,
			@RequestParam String remark, @RequestParam Long villageId, @RequestParam Long appUserId,
			HttpServletRequest request) {

		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建activityBaoming类用于封装注册参数
		ActivityBaoming activityBaoming = new ActivityBaoming();
		try {
			// 判断姓名是否为空
			if (StringUtils.isBlank(name)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200011));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 判断手机号是否为空
			if (StringUtils.isBlank(phone)) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200012));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}

			// 接口检索条件
			ActivityBaomingVo activityBaomingVo = new ActivityBaomingVo();
			// 电话号码
			activityBaomingVo.setPhone(phone);
			activityBaomingVo.setVillageId(villageId);
			activityBaomingVo.setActivityCollectionId(id);

			// 判断该人是否已报名 返回值大于0 该人已报名
			int reCount = activityBaomingService.selectByName(activityBaomingVo);

			// 该人已报名
			if (reCount > 0) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE402);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200013));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}
			// 如果当前时间大于报名截止时间
			Date aDate = new Date();
			if (aDate.compareTo(activityCollectionService.get(id).getEnrollEnd()) > 0) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE402);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200135));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			}

			// 报名人id
			activityBaoming.setUserId(appUserId);
			// 传入活动汇id
			activityBaoming.setActivityCollectionId(id);
			// 传入姓名
			activityBaoming.setName(name);
			// 传入手机号参数
			activityBaoming.setPhone(phone);
			// 传入报名时间
			activityBaoming.setTime(new Date());
			if ("GET".equals(request.getMethod())) { // get请求进行转码
				remark = new String(remark.getBytes("iso8859-1"), "UTF-8");
			}
			// 传入备注
			activityBaoming.setRemark(remark);
			// 获取报名者ip
			String baomingIp = getIpAddr(request);
			baomingIp = StringUtils.isEmpty(baomingIp) ? "" : baomingIp;
			// 传入报名者ip
			activityBaoming.setIp(baomingIp);
			// 编辑共通属性
			addAttr(activityBaoming);
			// 保存报名信息 返回值大于0 报名成功
			int count = activityBaomingService.saveSelective(activityBaoming);
			// 报名失败
			if (count == 0) {
				// 封装注册失败信息
				map.put("resultCode", ConstantStr.APP_CODE403);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200014));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			} else {
				// 报名成功

				// 根据活动汇id获取活动汇类
				ActivityCollection activityCollection = activityCollectionService.get(id);

				// 活动表报名人数+1
				activityCollection.setEnrollCount(activityCollection.getEnrollCount() + 1);
				// 调用更新方法
				editAttr(activityCollection);
				// 更新活动汇表
				activityCollectionService.updateSelective(activityCollection);

				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200015));
				// 返回JSON数据
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
	 * @Description 贴心报列表接口
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午2:27:53
	 * @param villageId
	 * @param appFlag
	 *            (最新信息 最热信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryIntimateNewsByParams")
	public @ResponseBody String queryIntimateNewsByParams(Long villageId, String appFlag,
			@RequestParam(value = "title", required = false) String title, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		int page = Integer.parseInt(StringUtils.isEmpty(request.getParameter("page")) ? "1" : request
				.getParameter("page"));
		int rows = Integer.parseInt(StringUtils.isEmpty(request.getParameter("rows")) ? "10" : request
				.getParameter("rows"));

		try {
			map.put("resultCode", ConstantStr.APP_CODE200);
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));

			IntimateNewsVo intimateNewsVo = new IntimateNewsVo();
			intimateNewsVo.setPageFlag(true);
			intimateNewsVo.setStartPage((page - 1) * rows);
			intimateNewsVo.setEndPage(rows);
			intimateNewsVo.setAppFlag(appFlag);
			intimateNewsVo.setPublishStatus(ConstantStr.FB);
			// 判断标题是否为空
			if (StringUtils.isNotBlank(title)) { // 如果不为空
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					title = new String(title.getBytes("iso8859-1"), "UTF-8");
				}
				intimateNewsVo.setTitle(title);
			}
			if (villageId != null) {
				intimateNewsVo.setVillageId(String.valueOf(villageId));
			}
			List<AppIntimateNewsVo> intimateNewsList = intimateNewsService.appQueryIntimateNewsByParams(intimateNewsVo);
			// 封装返回信息
			map.put("data", intimateNewsList);
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
	 * @Description 获取贴心报详情
	 * @author:王肖东
	 * @CreateDate:2016年9月29日 下午3:52:06
	 * @param id
	 * @param request
	 * @param flag
	 *            (app是否刷新调用此接口 如果是刷新操作 则浏览量不加1 flag=false 不加1 flag=true 加1)
	 * @return
	 */
	@RequestMapping("/getIntimateNewsDetail")
	public @ResponseBody String getIntimateNewsDetail(@RequestParam Long id, HttpServletRequest request, boolean flag) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装返回数据
		Map<String, Object> data = new HashMap<>();
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();

			IntimateNews intimateNews = intimateNewsService.get(id);
			if (intimateNews == null) { // 如果已经被删除
				Object[] attribute = { "贴心报" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute), null);
			}
			// flag=true 表是不是来自刷新
			if (flag) {
				// 更新贴心报浏览次数
				IntimateNews in = new IntimateNews();
				in.setId(id);
				in.setViewNumber(intimateNews.getViewNumber() + 1);
				in.setUpdEac(intimateNews.getUpdEac() + 1);
				in.setUpdYmdhms(new Date());
				intimateNewsService.updateSelective(in);
			}

			// 创建Vo对象用于封装参数查询列表信息
			IntimateNewsCommentVo intimateNewsCommentVo = new IntimateNewsCommentVo();
			// 设置分页标志位
			intimateNewsCommentVo.setPageFlag(false);
			intimateNewsCommentVo.setIntimateNewsId(id);
			// 设置贴心报评论违规flag
			intimateNewsCommentVo.setIllegalFlag(true);

			// 调用service方法获取评论
			List<IntimateNewsComment> commentDataList = intimateNewsService.appQueryComment(intimateNewsCommentVo);

			for (IntimateNewsComment asc : commentDataList) {
				// 创建Map变量用于封装返回数据
				Map<String, Object> comment = new HashMap<>();
				// 封装评论id
				comment.put("commentId", asc.getId());
				// 封装评论人id
				comment.put("commentUserId", asc.getAppUser().getId());
				// 封装评论人头像
				comment.put("commentHead", StringUtils.isEmpty(asc.getAppUser().getHeadPortrait()) ? "" : asc
						.getAppUser().getHeadPortrait());
				// 封装评论人名称
				comment.put("commentUserName", asc.getAppUser().getNickName());
				// 封装评论内容
				comment.put("commentContent", asc.getContent());
				// 封装评论时间
				comment.put("commentDate", asc.getInsYmdhms());
				// 封装被评论条数
				comment.put("commentReplyCount", asc.getReplyNumber());
				// 封装该条评论点赞数
				comment.put("commentLikes", asc.getLikesNumber());
				// 封装该条评论倒赞数
				comment.put("commentHates", asc.getHateNumber());
				// 向list中添加数据
				commentList.add(comment);
			}
			data.put("title", intimateNews.getTitle());
			data.put("content", intimateNews.getContent());
			data.put("likesNumber", intimateNews.getLikesNumber());
			data.put("hateNumber", intimateNews.getHateNumber());
			data.put("source", intimateNews.getSource());
			data.put("reportTime", intimateNews.getReportTime());
			data.put("publishTime", intimateNews.getPublishTime());
			// 查询user不进行关联查询
			data.put("publishPerson", userService.queryUserByUserId(intimateNews.getInsId()).getRealName());
			data.put("icon", intimateNews.getIcon());
			data.put("replyFlag", intimateNews.getReplyFlag());
			// 摘要
			data.put("summary", intimateNews.getSummary());
			// 分享链接
			data.put("shareUrl", ConstantStr.SHARE_URL + "?id=" + id + "&flag=" + ConstantStr.SHAREFLAG_INTIMATENEWS);
			// 向map中放入评论详情数据用于返回
			data.put("comment", commentList);
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
	 * @Description 贴心报一级评论和二级评论 详情
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午10:02:36
	 * @param commentId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getIntimateNewsCommentDetail")
	public @ResponseBody String getIntimateNewsCommentDetail(@RequestParam Long commentId, 
		   @RequestParam Long appUserId ,HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建Map变量用于封装返回数据
		Map<String, Object> data = new HashMap<>();
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> commentReplyList = new ArrayList<Map<String, Object>>();
			// 调用service方法根据评论id获取评论相关信息
			IntimateNewsCommentVo intimateNewsCommentVo = intimateNewsService.getCommentDetailByCommentId(commentId);
			if (intimateNewsCommentVo == null) { // 如果已经被删除
				Object[] attribute = { "评论" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute), null);
			}
			/* 更新评论浏览量 */
			IntimateNewsComment intimateNewsComment = new IntimateNewsComment();
			intimateNewsComment.setId(commentId);
			intimateNewsComment.setViewNumber(intimateNewsCommentVo.getViewNumber() + 1);
			intimateNewsComment.setUpdEac(intimateNewsCommentVo.getUpdEac() + 1);
			intimateNewsService.updateIntimateNewsComment(intimateNewsComment);
			// 封装被评论内容id
			data.put("commentId", intimateNewsCommentVo.getId());
			// 封装被评论内容的作者
			data.put("commentUserName", intimateNewsCommentVo.getAppUser().getNickName());
			// 封装被评论内容的作者的头像
			data.put("commentHead", StringUtils.isEmpty(intimateNewsCommentVo.getAppUser().getHeadPortrait()) ? ""
					: intimateNewsCommentVo.getAppUser().getHeadPortrait());
			// 封装被评论内容的内容
			data.put("commentContent", intimateNewsCommentVo.getContent());
			// 封装被评论内容的时间
			data.put("commentDate", intimateNewsCommentVo.getInsYmdhms());
			// 封装被评论内容的点赞数
			data.put("commentLikes", intimateNewsCommentVo.getLikesNumber());
			// 封装被评论内容的点倒赞数
			data.put("commentHates", intimateNewsCommentVo.getHateNumber());
			
			//查询点赞历史记录
			IntimateNewsCommentRecommend commentRecommend = 
					intimateNewsCommentRecommendService.selectByCommendAndUserId(commentId, appUserId,null);
			if(commentRecommend != null){
				data.put("result", true);
			}else{
				data.put("result", false);
			}

			// 创建Vo对象用于封装参数查询列表信息
			IntimateNewsCommentReplyVo intimateNewsCommentReplyVo = new IntimateNewsCommentReplyVo();
			// 设置分页标志位
			intimateNewsCommentReplyVo.setPageFlag(false);
			// 设置贴心报评论id
			intimateNewsCommentReplyVo.setInCommentId(commentId);
			// 设置贴心报违规flag
			intimateNewsCommentReplyVo.setIllegalFlag(true);

			// 调用service方法获取二级评论
			List<IntimateNewsCommentReplyVo> commentReplyDataList = intimateNewsService
					.findIntimateNewsCommentReplyList(intimateNewsCommentReplyVo);

			for (IntimateNewsCommentReplyVo ascrv : commentReplyDataList) {
				// 创建Map变量用于封装返回数据
				Map<String, Object> commentReply = new HashMap<>();
				// 封装评论人id
				commentReply.put("replyUserId", ascrv.getInsId());
				// 封装评论人头像
				commentReply.put("replyUserHead", ascrv.getInsHead());
				// 封装评论人名称
				commentReply.put("replyUserName", ascrv.getInsName());
				// 封装评论内容
				commentReply.put("replyContent", ascrv.getContent());
				// 封装评论时间
				commentReply.put("replyDate", ascrv.getInsYmdhms());
				// 向list中添加数据
				commentReplyList.add(commentReply);
			}
			// 向map中放入评论详情数据用于返回
			data.put("commentReply", commentReplyList);
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
	 * @Description 贴心报一级评论
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午10:53:33
	 * @param id
	 * @param appUserId
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("/commentIntimateNews")
	public @ResponseBody String commentIntimateNews(@RequestParam Long id, @RequestParam Long appUserId,
			@RequestParam String safekey, HttpServletRequest request) {
		if (!isLogin(safekey)) {
			if (StringUtils.isNotBlank(safekey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		// 获取评论内容
		String content = StringUtils.isEmpty(request.getParameter("content")) ? "" : request.getParameter("content");
		try {
			// 判断评论内容是否为空
			if (StringUtils.isBlank(content)) { // 如果为空
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200004), null);
			} else { // 如果是get请求中文会乱码,需要进行转码
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					content = new String(content.getBytes("iso8859-1"), "UTF-8");
				}
			}
			// 内容解密
			content = UnescapeUtil.unescape(content);
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(content);
			// 内容加密
			content = UnescapeUtil.escape(content);
			// 获取评论者ip
			String opreaterIp = getIpAddr(request);
			opreaterIp = StringUtils.isEmpty(opreaterIp) ? "" : opreaterIp;

			int result = 0;
			/*
			 * // 判断用户当天有没有评论过 IntimateNewsComment intimateNewsComment =
			 * intimateNewsService.getIntimateNewsCommentByDay(id, appUserId);
			 * if (intimateNewsComment != null) { // 如果已经评论过 // 返回封装的失败数据信息
			 * return fromObject(ConstantStr.APP_CODE403,
			 * ComDefine.getMsg(ConstantStr.INFO200005), null); }
			 */
			// 调用service方法插入贴心报一级评论
			result = intimateNewsService.addComment(id, appUserId, content, illegal, opreaterIp);

			if (result > 0) {
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200082), null);
			} else {
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200007), null);
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
	 * @Description 贴心报二级评论
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:24:09
	 * @param commentId
	 * @param appUserId
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("/replyIntimateNews")
	public @ResponseBody String replyIntimateNews(@RequestParam Long commentId, @RequestParam Long appUserId,
			@RequestParam String safekey, HttpServletRequest request) {
		if (!isLogin(safekey)) {
			if (StringUtils.isNotBlank(safekey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		// 获取评论内容
		String content = StringUtils.isEmpty(request.getParameter("content")) ? "" : request.getParameter("content");
		try {
			// 判断评论内容是否为空
			if (StringUtils.isBlank(content)) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200004), null);
			} else { // 如果是get请求中文会乱码,需要进行转码
				if ("GET".equals(request.getMethod())) { // get请求进行转码
					content = new String(content.getBytes("iso8859-1"), "UTF-8");
				}
			}

			// 内容解密
			content = UnescapeUtil.unescape(content);
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(content);
			// 内容加密
			content = UnescapeUtil.escape(content);

			// 获取评论者ip
			String opreaterIp = getIpAddr(request);
			opreaterIp = StringUtils.isEmpty(opreaterIp) ? "" : opreaterIp;

			int result = 0;
			// 调用service方法插入贴心报二级评论
			result = intimateNewsService.addCommentReply(commentId, appUserId, content, illegal, opreaterIp);

			if (result > 0) {
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200082), null);
			} else {
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200007), null);
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
	 * @Description 对贴心报进行点赞倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月18日 上午11:29:19
	 * @param id
	 * @param paramType
	 * @param request
	 * @return
	 */
	@RequestMapping("/addIntimateNews")
	public @ResponseBody String addIntimateNews(@RequestParam Long id, @RequestParam String paramType,
			@RequestParam Long appUserId, HttpServletRequest request) {
		try {
			int result = 0;
			// 判断贴心报是否已经被删除
			IntimateNews original = intimateNewsService.get(id);
			if (original == null) { // 如果已经被删除
				Object[] attribute = { "贴心报" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute), null);
			}

			// 判断用户当天有没有对贴心报进行过点赞或者倒赞过
			boolean isLikeOrhate = intimateNewsService.getIntimateNewsByDay(id, appUserId);
			if (isLikeOrhate) { // 如果已经点赞过 // 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200051), null);
			}

			// 创建Model变量用于更改数据
			IntimateNews intimateNews = new IntimateNews();
			/* 设置相关数据 */
			intimateNews.setId(id);

			if (ConstantStr.STORE_PARAM_LIKE.equals(paramType)) { // 点赞
				intimateNews.setLikesNumber(original.getLikesNumber() + 1);
			} else if (ConstantStr.STORE_PARAM_HATE.equals(paramType)) { // 倒赞
				intimateNews.setHateNumber(original.getHateNumber() + 1);
			}
			// 设置更新回数
			intimateNews.setUpdEac(original.getUpdEac());
			editAttr(intimateNews, appUserId);
			// 调用service方法插入贴心报属性
			result = intimateNewsService.addIntimateNewsParam(intimateNews);

			if (result > 0) {
				Object[] attribute = { "贴心报" };
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200006, attribute), null);
			} else {
				Object[] attribute = { "贴心报" };
				// 返回封装的失败数据信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200007, attribute), null);
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
	 * @Description 对一级评论进行点赞 倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:53:31
	 * @param commentId
	 * @param appUserId
	 * @param request
	 * @return
	 */
	@RequestMapping("/addIntimateNewsCommentParam")
	public @ResponseBody String addIntimateNewsCommentParam(@RequestParam Long commentId, 
			@RequestParam Long appUserId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "点赞" };
		try {
			int result = 0;
			Map<String, Object> data = new HashMap<String, Object>();
			// 判断贴心报评论是否已经被删除
			IntimateNewsCommentVo original = intimateNewsService.findAppIntimateNewsCommentDetail(commentId);
			if (original == null) { // 如果已经被删除
				Object[] attribute = { "评论" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, attribute), null);
			}
			//查询点赞历史记录
			IntimateNewsCommentRecommend commentRecommend = 
					intimateNewsCommentRecommendService.selectByCommendAndUserId(commentId, appUserId,null);

			// 创建Model变量用于更改数据
			IntimateNewsComment intimateNewsComment = new IntimateNewsComment();
			/* 设置相关数据 */
			intimateNewsComment.setId(commentId);

			if (commentRecommend == null) { // 点赞
				// 创建正赞记录对象
				commentRecommend = fromat(ConstantStr.ZZ_GZ_SC, commentId, appUserId);
				intimateNewsComment.setLikesNumber(original.getLikesNumber() + 1);
				// 倒赞数量-1
				if(original.getHateNumber() != null && original.getHateNumber()>0){
					intimateNewsComment.setHateNumber(original.getHateNumber() - 1);
				}else{
					intimateNewsComment.setHateNumber(0);
				}
				arr = addMessage(arr, "");
				data.put("result", true);
			} else { // 倒赞
				intimateNewsComment.setHateNumber(original.getHateNumber() + 1);
				// 正赞数量-1
				if(original.getLikesNumber() != null && original.getLikesNumber()>0){
					intimateNewsComment.setLikesNumber(original.getLikesNumber() - 1);
				}else{
					intimateNewsComment.setLikesNumber(0);
				}
				// 增加消息
				arr = addMessage(arr, "取消");
				data.put("result", false);
			}

			// 设置更新回数
			intimateNewsComment.setUpdEac(original.getUpdEac());
			editAttr(intimateNewsComment, appUserId);
			// 调用service方法插入贴心报一级评论属性
			result = intimateNewsService.addCommentParam(intimateNewsComment,commentRecommend);

			if (result > 0) {
				map.put("data", data);
				// 返回封装的成功数据信息
				return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200070, arr), map);
			} else {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200071, arr), map);
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
	 * @Description 获取本小区下面 贴心报推荐列表(排除当前包含的那条数据)
	 * @author:王肖东
	 * @CreateDate:2016年8月18日 上午9:05:36
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getIntimateNewsRecommendedList")
	public @ResponseBody String getIntimateNewsRecommendedList(Long villageId, HttpServletRequest request,
			HttpServletResponse response, long intimateNewsId) {
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		if (StringUtil.isBlank(pageStr) || StringUtil.isBlank(rowsStr)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029), null);
		}
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> hmList = new ArrayList<Map<String, Object>>();
			IntimateNewsVo intimateNewsVo = new IntimateNewsVo();
			intimateNewsVo.setPageFlag(true);
			intimateNewsVo.setStartPage((page - 1) * rows);
			intimateNewsVo.setEndPage(rows);
			intimateNewsVo.setPublishStatus(ConstantStr.FB);
			if (villageId != null) {
				intimateNewsVo.setVillageId(String.valueOf(villageId));
			}
			intimateNewsVo.setId(intimateNewsId);
			List<IntimateNews> list = intimateNewsService.queryListByParamOrder(intimateNewsVo);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> hm = new HashMap<>();
				hm.put("id", list.get(i).getId());
				hm.put("title", list.get(i).getTitle());
				hm.put("source", list.get(i).getSource());
				hm.put("publishTime", list.get(i).getPublishTime());
				hm.put("icon", list.get(i).getIcon());
				hmList.add(hm);
			}
			map.put("data", hmList);
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 随心拍列表查询
	 * @author:YK
	 * @CreateDate:2016年9月19日 上午10:31:36
	 * @param heartBeatVo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getHeartBeatList")
	public @ResponseBody String getHeartBeatList(HeartBeatVo heartBeatVo, HttpServletRequest request,
			@RequestParam String searchFrom, @RequestParam String safekey, @RequestParam Long appUserId,
			HttpServletResponse response) {

		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if ("1".equals(searchFrom)) {// 选择小区查询
				if (!isLogin(safekey)) {// 验证是否登录
					if (StringUtils.isNotBlank(safekey)) {
						return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
					} else {
						// 未登录则返回未登录的信息
						return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
					}
				}
				// 验证是否选择小区
				String village[] = heartBeatVo.getVillageIds().split(",");
				// 保存选择的小区 上传成功 返回true
				boolean res = heartBeatUserVillageService.saveHeartBeatUserVillage(appUserId, village);
				/*
				 * 如果上次搜索历史记录与本次搜索小区都为空，则返回false，所以注释掉一下代码 if (!res) { return
				 * fromObject(ConstantStr.APP_CODE403,
				 * ComDefine.getMsg(ConstantStr.INFO200002), null); }
				 */
			}
			// 设置登录人为发布人用于service查询点赞记录
			heartBeatVo.setPublishPersonId(appUserId);
			// 设置查询条件分页信息
			heartBeatVo.setPageFlag(true);
			heartBeatVo.setStartPage((page - 1) * rows);
			heartBeatVo.setEndPage(rows);
			// 设置查询条件为非违规状态
			heartBeatVo.setTreatmentStatus(ConstantStr.HEARTBEATSTATUS_02);
			// 根据查询条件获取数据
			List<Map<String, Object>> hmList = heartBeatService.queryHeartBeatList(heartBeatVo);
			map.put("data", hmList);
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 获取随心拍分类
	 * @author:YK
	 * @CreateDate:2016年9月20日 上午9:32:51
	 * @return
	 */
	@RequestMapping("/getSysDataByType")
	public @ResponseBody String getSysDataByType(@RequestParam String type) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));

			List<SysData> sysDataList = new ArrayList<SysData>();
			if (StringUtil.isNotBlank(type) && ConstantStr.HEARTREPORTTYPE_01.equals(type)) {// 举报页面类型查询
				sysDataList = sysDataService.querySysdataByType(ConstantStr.HEARTREPORTTYPE);
			} else {
				// 发布的时候，查询随心拍信息的所有类别
				sysDataList = sysDataService.querySysdataByType(ConstantStr.HEARTBEATTYPE);
			}
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
	 * @Description 随心拍发布
	 * @author:YK
	 * @CreateDate:2016年9月20日 上午11:15:32
	 * @param heartBeat
	 * @return
	 */
	@RequestMapping("saveHeartBeatPhotos")
	public @ResponseBody String saveHeartBeatPhotos(HeartBeat heartBeat, @RequestParam String safekey,
			@RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象用于信息提示
		Object[] arr = { "随心拍信息" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}

			// 查询发布人的常驻小区判断是否可以发布
			VillageVo villageVo = new VillageVo();
			villageVo.setUserId(heartBeat.getPublishPersonId());
			villageVo.setVillageType(ConstantStr.VILLAGE_TYPE_2);
			VillageVo resident = villageService.queryByUserIdAndVillageType(villageVo);
			if (resident == null) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200080), null);
			}
			/*
			 * // 判断内容是否为空 if (StringUtils.isBlank(heartBeat.getContent())) { //
			 * 封装返回信息 map.put("resultCode", ConstantStr.APP_CODE401);
			 * map.put("msg", ComDefine.getMsg(ConstantStr.INFO200077)); //
			 * 返回JSON return JSONObject.fromObject(map).toString(); }
			 */
			// 内容解密
			heartBeat.setContent(UnescapeUtil.unescape(heartBeat.getContent()));
			// 最多支持输入长度255
			if (heartBeat.getContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(heartBeat.getContent());
			if (illegal) {// 敏感字符禁止发布
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200079), null);
			}
			// 内容加密
			heartBeat.setContent(UnescapeUtil.escape(heartBeat.getContent()));
			// 判断随心拍分类是否选择
			if (StringUtils.isBlank(heartBeat.getContentType())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200078));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 文件上传路径相关
			String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
			String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
			// 新建附件类表类
			List<Attach> attachs = new ArrayList<Attach>();
			// 创建文件名
			String newFileName = "";
			// 创建boolean变量 默认为false
			boolean rs = false;
			// 遍历上传的文件
			for (int j = 0; j < files.length && j < 6; j++) {
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
					rs = FileUtil.uploadCompressPic(file, realpath, newFileName);
					// 如果上传成功
					if (rs) {
						// 获取文件上传后的相对路径
						newFileName = httppath + newFileName;
						// 设置文件地址
						attach.setUrl(newFileName);
						// 传入广告列表
						attachs.add(attach);
					}
				}
			}
			// 设置附件传出
			heartBeat.setAttachs(attachs);
			// 上传随心拍信息 上传成功 返回true
			boolean res = heartBeatService.saveHeartBeat(heartBeat);
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
	 * 
	 * @Title
	 * @Description 随心拍发布
	 * @author:YK
	 * @CreateDate:2016年9月20日 上午11:15:32
	 * @param heartBeat
	 * @return
	 */
	@RequestMapping("saveHeartBeat")
	public @ResponseBody String saveHeartBeat(HeartBeat heartBeat, @RequestParam String safekey,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象用于信息提示
		Object[] arr = { "随心拍信息" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}

			// 查询发布人的常驻小区判断是否可以发布
			VillageVo villageVo = new VillageVo();
			villageVo.setUserId(heartBeat.getPublishPersonId());
			villageVo.setVillageType(ConstantStr.VILLAGE_TYPE_2);
			VillageVo resident = villageService.queryByUserIdAndVillageType(villageVo);
			if (resident == null) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200080), null);
			}

			// 判断内容是否为空
			if (StringUtils.isBlank(heartBeat.getContent())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200077));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 最多支持输入长度255
			if (heartBeat.getContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}
			// 内容解密
			heartBeat.setContent(UnescapeUtil.unescape(heartBeat.getContent()));
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(heartBeat.getContent());
			if (illegal) {// 敏感字符禁止发布
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200079), null);
			}
			// 内容加密
			heartBeat.setContent(UnescapeUtil.escape(heartBeat.getContent()));
			// 判断随心拍分类是否选择
			if (StringUtils.isBlank(heartBeat.getContentType())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200078));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 上传随心拍信息 上传成功 返回true
			boolean res = heartBeatService.saveHeartBeat(heartBeat);
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
	 * 
	 * @Title
	 * @Description 随心拍选项：小区选择
	 * @author:YK
	 * @CreateDate:2016年9月20日 上午11:33:50
	 * @return
	 */
	@RequestMapping("getVillageList")
	public @ResponseBody String getVillageList(@RequestParam String safekey, @RequestParam Long userId,
			@RequestParam String curLongitude, @RequestParam String curLatitude, @RequestParam String content,
			@RequestParam Long villageId) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		VillageVo villageVo = new VillageVo();
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 设置小区搜索名称
			villageVo.setVillageName(content);
			// 封装查询用户相关的小区
			villageVo.setUserId(userId);

			// 当前纬度
			villageVo.setCurLatitude(curLatitude);
			// 当前经度
			villageVo.setCurLongitude(curLongitude);

			// 当前小区id，如果有常驻小区就是常驻小区id，如果没有常驻小区就是定位小区id
			villageVo.setVillageId(villageId);

			// 返回封装成功数据信息
			map.put("resultCode", ConstantStr.APP_CODE200);
			// 返回封装的成功数据信息
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200001));
			// 查询关注、常驻、本省的小区
			List<Map<String, Object>> list = villageService.queryVillageList(villageVo);
			// 查询上次选择的小区
			List<Map<String, Object>> lastLst = heartBeatUserVillageService.queryLastVillage(userId);
			map.put("lastLst", lastLst);
			map.put("allList", list);
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
	 * @Description 保存随心拍举报内容
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午5:43:35
	 * @param heartBeatReport
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("saveHeartBeatReport")
	public @ResponseBody String saveHeartBeatReport(HeartBeatReport heartBeatReport, @RequestParam String safekey,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象用于信息提示
		Object[] arr = { "", "举报" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 判断内容是否为空
			if (StringUtils.isBlank(heartBeatReport.getReportContent())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200081));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 内容解密
			heartBeatReport.setReportContent(UnescapeUtil.unescape(heartBeatReport.getReportContent()));
			// 最多支持输入长度255
			if (heartBeatReport.getReportContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}
			// 获取随心拍数据并验证是否存在
			HeartBeat hb = heartBeatService.selectById(heartBeatReport.getHearId());
			if (hb == null) {
				Object[] nodata = { "随心拍" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, nodata), null);
			}
			/*// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(heartBeatReport.getReportContent());
			if (illegal) {// 敏感字符禁止发布
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200079), null);
			}*/
			// 内容加密
			heartBeatReport.setReportContent(UnescapeUtil.escape(heartBeatReport.getReportContent()));
			// 判断随心拍举报类型是否选择
			if (StringUtils.isBlank(heartBeatReport.getReportType())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200078));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 设置举报ip
			heartBeatReport.setOpreaterIp(getIpAddr(request));
			// 上传随心拍举报信息 上传成功 返回true
			boolean res = heartBeatReportService.saveHeartBeatReport(heartBeatReport, hb);
			// 如果上传成功
			if (res) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200070, arr));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			} else {
				// 封装注册失败信息
				map.put("resultCode", ConstantStr.APP_CODE403);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200071, arr));
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
	 * 
	 * @Title
	 * @Description 随心拍详情
	 * @author:张洋
	 * @CreateDate:2016年9月20日14:46:45
	 * @param id
	 *            ,longitude,latitude
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewHeartBeat")
	@ResponseBody
	public String viewHeartBeat(@RequestParam Long id, @RequestParam Long userId, @RequestParam String longitude,
			@RequestParam String latitude, HttpServletRequest request, HttpServletResponse response) {
		Object[] arr = { "随心拍详情" };
		// 验证数据
		if (id == null || userId == null || StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取随心拍数据并验证是否存在
			HeartBeat hb = heartBeatService.selectById(id);
			if (hb == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 获取用户数据和小区数据，并验证是否存在
			AppUser au = appUserService.get(hb.getPublishPersonId());
			Village v = villageService.get(hb.getVillageId());
			if (v == null || au == null) {
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 获取回复量和举报量
			HeartBeatCommentVo hbc = new HeartBeatCommentVo();
			HeartBeatReportVo hbr = new HeartBeatReportVo();
			hbr.setHearId(id);
			hbc.setHearId(id);
			// 回复量
			Long count2 = heartBeatCommentService.queryCountByParam(hbc);
			// 举报量
			Long count = heartBeatReportService.queryCountAndUser(hbr);
			// 将用到的数据传回
			data.put("nickName", UnescapeUtil.unescape(au.getNickName()));
			data.put("villageName", v.getVillageName());
			data.put("publicTime", hb.getPublishTime());
			data.put("headPortrait", au.getHeadPortrait());
			data.put("content", UnescapeUtil.unescape(hb.getContent()));
			data.put("currentLocation", UnescapeUtil.unescape(hb.getCurrentLocation()));
			data.put("viewNumber", hb.getViewNumber());
			data.put("likesNumber", hb.getLikesNumber());
			data.put("commemt", count2);
			data.put("report", count);
			// 初始化随心拍附属图片
			Attach att = new Attach();
			att.setMediaType(ConstantStr.FILE_IMAGE);
			att.setOwnerTbType(ConstantStr.HEARTBEAT_18);
			att.setOwnerId(id);
			List<Attach> attList = attachService.selectAttachByParam(att);
			List<String> imgList = new ArrayList<>();
			for (Attach a : attList) {
				imgList.add(a.getUrl());
			}
			// 获取用户是否点过赞
			HeartBeatLike hbl = new HeartBeatLike();
			hbl.setHearId(id);
			hbl.setInsId(userId);
			List<HeartBeatLike> list = heartBeatLikeService.queryListByParam(hbl);
			if (list.size() > 0) {
				data.put("like", true);
			} else {
				data.put("like", false);
			}
			// 计算当前设备与随心拍发布地的距离
			String distance = BaiduMapUtils.Distance(new BigDecimal(longitude), new BigDecimal(latitude),
					hb.getLongitude(), hb.getLatitude());
			data.put("distance", distance);
			data.put("imgList", imgList);
			map.put("data", data);
			// 更新浏览量
			HeartBeat hbDate = new HeartBeat();
			hbDate.setId(hb.getId());
			hbDate.setUpdEac(hb.getUpdEac() + 1);
			hbDate.setUpdYmdhms(new Date());
			if (hb.getViewNumber() == null || hb.getViewNumber() == 0) {
				hbDate.setViewNumber(1);
			} else {
				hbDate.setViewNumber(hb.getViewNumber() + 1);
			}
			// 更新随心拍主数据
			heartBeatService.updateHeartBeat(hbDate);
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 随心拍评论列表
	 * @author:张洋
	 * @CreateDate:2016年9月20日14:46:45
	 * @param id
	 *            ,longitude,latitude
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewHeartComment")
	@ResponseBody
	public String viewHeartBeatComment(@RequestParam Long id, HttpServletRequest request,
			@RequestParam String longitude, @RequestParam String latitude, HttpServletResponse response) {
		HeartBeatCommentVo heartBeatCommentVo = new HeartBeatCommentVo();
		// 验证是否分页
		if (request.getParameter("page") != null && request.getParameter("rows") != null) {
			int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
					.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
					.getParameter("rows"));
			heartBeatCommentVo.setPageFlag(true);
			heartBeatCommentVo.setStartPage((page - 1) * rows);
			heartBeatCommentVo.setEndPage(rows);
		}
		Object[] arr = { "随心拍评论列表" };
		// 验证数据
		if (id == null) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		heartBeatCommentVo.setHearId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		try {
			// 将查出的用户名解密以输出
			List<HeartBeatCommentVo> heartBeatCommentList = heartBeatCommentService
					.queryListByUserApp(heartBeatCommentVo);
			for (HeartBeatCommentVo hbc : heartBeatCommentList) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("nickName", UnescapeUtil.unescape(hbc.getInsName()));
				dataMap.put("headPortrait", hbc.getHeadPortrait());
				dataMap.put("publicTime", hbc.getInsYmdhms());
				dataMap.put("content", hbc.getContent());
				dataMap.put("id", hbc.getId());
				dataMap.put("replyNumber", hbc.getReplyNumber());
				// 计算设备所在地点与评论时的地点的距离
				if (hbc.getLongitude() == null || hbc.getLatitude() == null) {
					dataMap.put("distance", "未知");
				} else {
					BigDecimal lo = hbc.getLongitude();
					BigDecimal la = hbc.getLatitude();
					String distance = BaiduMapUtils.Distance(new BigDecimal(longitude), new BigDecimal(latitude), lo,
							la);
					dataMap.put("distance", distance);
				}
				dataList.add(dataMap);
			}
			map.put("data", dataList);
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 随心拍评论回复列表
	 * @author:张洋
	 * @CreateDate:2016年9月20日14:46:45
	 * @param id
	 *            ,longitude,latitude
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewHeartBeatReply")
	@ResponseBody
	public String viewHeartBeatReply(@RequestParam Long commentId, HttpServletRequest request,
			@RequestParam String longitude, @RequestParam String latitude, HttpServletResponse response) {
		HeartCommentReplyVo heartCommentReplyVo = new HeartCommentReplyVo();
		heartCommentReplyVo.setHeartCommentId(commentId);
		// 验证是否分页
		if (request.getParameter("page") != null && request.getParameter("rows") != null) {
			int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
					.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
					.getParameter("rows"));
			heartCommentReplyVo.setPageFlag(true);
			heartCommentReplyVo.setStartPage((page - 1) * rows);
			heartCommentReplyVo.setEndPage(rows);
		}
		Object[] arr = { "随心拍评论回复列表" };
		// 验证数据
		if (commentId == null) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		try {
			// 将查出的用户名解密以输出
			List<HeartCommentReplyVo> heartBeatCommentList = heartCommentReplyService
					.queryListByUserApp(heartCommentReplyVo);
			for (HeartCommentReplyVo hbc : heartBeatCommentList) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("nickName", UnescapeUtil.unescape(hbc.getInsName()));
				dataMap.put("headPortrait", hbc.getHeadPortrait());
				dataMap.put("publicTime", hbc.getInsYmdhms());
				dataMap.put("content", hbc.getContent());
				if (hbc.getLongitude() == null || hbc.getLatitude() == null) {
					dataMap.put("distance", "未知");
				} else {
					BigDecimal lo = hbc.getLongitude();
					BigDecimal la = hbc.getLatitude();
					String distance = BaiduMapUtils.Distance(new BigDecimal(longitude), new BigDecimal(latitude), lo,
							la);
					dataMap.put("distance", distance);
				}
				dataList.add(dataMap);
			}
			map.put("data", dataList);
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 随心拍点赞/取消赞
	 * @author:张洋
	 * @CreateDate:2016年9月20日14:46:45
	 * @param hearId
	 *            ,insId,safekey
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewHeartLike")
	@ResponseBody
	public String viewHeartLike(@RequestParam Long hearId, HttpServletRequest request, @RequestParam Long insId,
			@RequestParam String safekey, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "随心拍点赞/取消赞" };
		if (hearId == null || insId == null || safekey == null) {
			return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200029, arr), null);
		}
		if (!isLogin(safekey)) {// 验证是否登录
			if (StringUtils.isNotBlank(safekey)) {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
			} else {
				// 未登录则返回未登录的信息
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
			}
		}
		try {
			// 查询随心拍主数据
			HeartBeat hb = heartBeatService.get(hearId);
			if (hb == null) {// 验证随心拍是否被删除
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, arr), null);
			}
			// 更新随心拍更新回数、更新时间
			hb.setUpdEac(hb.getUpdEac() + 1);
			hb.setUpdYmdhms(new Date());
			// 设置
			HeartBeatLike hbl = new HeartBeatLike();
			hbl.setHearId(hearId);
			hbl.setInsId(insId);

			List<HeartBeatLike> list = heartBeatLikeService.queryListByParam(hbl);
			// 更新随心拍点赞量与插入点赞历史记录
			boolean bl = heartBeatLikeService.updateHeartBeatLike(hbl, hb, list);
			if (bl) {
				map.put("resultCode", ConstantStr.APP_CODE200);
				if (list.size() > 0) {
					return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200084), null);
				} else {
					return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200083), null);
				}
			} else {
				return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200002), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}
	}

	/**
	 * 
	 * @Title
	 * @Description 评论随心拍
	 * @author:张洋
	 * @CreateDate:2016年9月20日19:34:18
	 * @param heartBeatComment
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("saveHeartComment")
	@ResponseBody
	public String saveHeartComment(HeartBeatComment heartBeatComment, @RequestParam String safekey,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象用于信息提示
		Object[] ars = { "", "评论" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 判断内容是否为空
			if (StringUtils.isBlank(heartBeatComment.getContent())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200077));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}
			// 内容解密
			heartBeatComment.setContent(UnescapeUtil.unescape(heartBeatComment.getContent()));
			// 最多支持输入长度255
			if (heartBeatComment.getContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}

			// 查询随新拍主数据
			HeartBeat hb = heartBeatService.get(heartBeatComment.getHearId());
			// 验证随心拍主数据是否被删除
			if (hb == null) {
				Object[] noData = { "随心拍" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, noData), null);
			}
			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(heartBeatComment.getContent());
			if (illegal) {// 敏感字符违规状态
				heartBeatComment.setIllegalStatus(ConstantStr.WG_SUM);
			} else {
				heartBeatComment.setIllegalStatus(ConstantStr.ZC_SUM);
			}

			// 内容加密
			heartBeatComment.setContent(UnescapeUtil.escape(heartBeatComment.getContent()));
			// 设置评论ip
			heartBeatComment.setOpreaterIp(getIpAddr(request));
			heartBeatComment.setReplyNumber(0);
			this.addAttr(heartBeatComment);
			// 保存评论数据并更新随心拍评论数
			boolean result = heartBeatCommentService.saveHeartBeatComment(heartBeatComment, hb);

			// 如果评论保存成功
			if (result) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200070, ars));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			} else {
				// 封装注册失败信息
				map.put("resultCode", ConstantStr.APP_CODE403);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200071, ars));
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
	 * 
	 * @Title
	 * @Description 回复随心拍的评论
	 * @author:张洋
	 * @CreateDate:2016年9月20日19:34:18
	 * @param heartCommentReply
	 * @param safekey
	 * @param request
	 * @return
	 */
	@RequestMapping("saveHeartCommentReply")
	@ResponseBody
	public String saveHeartCommentReply(HeartCommentReply heartCommentReply, @RequestParam String safekey,
			HttpServletRequest request) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象用于信息提示
		Object[] ars = { "", "回复评论" };
		try {
			if (!isLogin(safekey)) {// 验证是否登录
				if (StringUtils.isNotBlank(safekey)) {
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200050), null);
				} else {
					// 未登录则返回未登录的信息
					return fromObject(ConstantStr.APP_CODE403, ComDefine.getMsg(ConstantStr.INFO200024), null);
				}
			}
			// 判断内容是否为空
			if (StringUtils.isBlank(heartCommentReply.getContent())) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE401);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200077));
				// 返回JSON
				return JSONObject.fromObject(map).toString();
			}

			// 内容解密
			heartCommentReply.setContent(UnescapeUtil.unescape(heartCommentReply.getContent()));

			// 最多支持输入长度255
			if (heartCommentReply.getContent().length() >= 255) {
				return fromObject(ConstantStr.APP_CODE401, ComDefine.getMsg(ConstantStr.INFO200076), null);
			}

			// 修改随心拍评论的回复量
			HeartBeatComment hbc = heartBeatCommentService.get(heartCommentReply.getHeartCommentId());
			if (hbc == null) {
				Object[] noData = { "随心拍评论" };
				return fromObject(ConstantStr.APP_CODE405, ComDefine.getMsg(ConstantStr.INFO200003, noData), null);
			}

			// 判断评论内容是否违规
			boolean illegal = keywordsService.judgeIlleagl(heartCommentReply.getContent());
			if (illegal) {// 敏感字符违规状态
				heartCommentReply.setIllegalStatus(ConstantStr.WG_SUM);
			} else {
				heartCommentReply.setIllegalStatus(ConstantStr.ZC_SUM);
			}
			// 内容加密
			heartCommentReply.setContent(UnescapeUtil.escape(heartCommentReply.getContent()));
			// 设置评论ip
			heartCommentReply.setOpreaterIp(getIpAddr(request));
			this.addAttr(heartCommentReply);

			// 上传随心拍评论信息并更新评论的回复量 上传成功 返回true
			boolean result = heartCommentReplyService.saveReplay(heartCommentReply, hbc);
			// 如果评论的回复保存成功
			if (result) {
				// 封装返回信息
				map.put("resultCode", ConstantStr.APP_CODE200);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200070, ars));
				// 返回JSON数据
				return JSONObject.fromObject(map).toString();
			} else {
				// 封装注册失败信息
				map.put("resultCode", ConstantStr.APP_CODE403);
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200071, ars));
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
	 * @Description 组装点赞历史记录数据
	 * @author:YK
	 * @CreateDate:2016年9月30日 上午11:15:02
	 * @param operatorType
	 * @param commentId
	 * @param userId
	 * @return IntimateNewsCommentRecommend
	 */
	private IntimateNewsCommentRecommend fromat(String operatorType, Long commentId, Long userId) {
		IntimateNewsCommentRecommend commendRecommend = new IntimateNewsCommentRecommend();
		commendRecommend.setCommentId(commentId);
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

	// 贴心报点赞倒赞使用
	private void editAttr(IntimateNews intimateNews, Long appuserId) {
		intimateNews.setUpdId(appuserId);
		intimateNews.setUpdEac(intimateNews.getUpdEac() + 1);
		intimateNews.setUpdYmdhms(new Date());
	}

	// 贴心报一级评论点赞倒赞
	private void editAttr(IntimateNewsComment intimateNewsComment, Long appuserId) {
		intimateNewsComment.setUpdId(appuserId);
		intimateNewsComment.setUpdEac(intimateNewsComment.getUpdEac() + 1);
		intimateNewsComment.setUpdYmdhms(new Date());
	}

	// 周边店 点赞倒赞 评星 使用
	private void editAttr(AroundStore aroundStore, Long appuserId) {
		aroundStore.setUpdId(appuserId);
		aroundStore.setUpdEac(aroundStore.getUpdEac() + 1);
		aroundStore.setUpdYmdhms(new Date());
	}

	// 推荐店 点赞倒赞 评星使用
	private void editAttr(AroundSuggestStore aroundSuggestStore, Long appuserId) {
		aroundSuggestStore.setUpdId(appuserId);
		aroundSuggestStore.setUpdEac(aroundSuggestStore.getUpdEac() + 1);
		aroundSuggestStore.setUpdYmdhms(new Date());
	}

	// 周边店一级评论 点赞 倒赞
	private void editAttr(AroundStoreComment _temp, Long appUserId) {
		_temp.setUpdId(appUserId);
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
	}

	// 推荐店一级评论 点赞 倒赞
	private void editAttr(AroundSuggestStoreComment _temp, Long appUserId) {
		_temp.setUpdId(appUserId);
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
	}

	private void addAttr(ActivityBaoming _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	private void addAttr(HeartBeatComment _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	private void addAttr(HeartCommentReply _temp) {
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	private void editAttr(CommonEntity _temp) {
		_temp.setUpdEac(_temp.getUpdEac() + 1);
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
