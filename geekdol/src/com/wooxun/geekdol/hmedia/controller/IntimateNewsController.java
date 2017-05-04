package com.wooxun.geekdol.hmedia.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.model.IntimateNewsComment;
import com.wooxun.geekdol.hmedia.model.IntimateNewsCommentReply;
import com.wooxun.geekdol.hmedia.service.IntimateNewsService;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsCommentVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author Liys
 * @CreateDate 2016年7月28日
 * @param
 * @see
 * @modified 贴心报管理 ========================================================== No
 *           修改人员 修改日期 描述 1. Liys 2016年7月28日 下午4:46:17 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("intimateNews")
public class IntimateNewsController extends BaseController {

	public static final String MANAGER_LIST = "hmedia/intimateNewsManager/managerList";
	public static final String MANAGER_ADD = "hmedia/intimateNewsManager/managerAdd";
	public static final String SELECT_VILLAGE = "hmedia/intimateNewsManager/villageSelect";

	public static final String UPDATE = "hmedia/intimateNewsManager/managerEditor";

	/** 回复管理界面返回路径 */
	private static final String IntimateNews_COMMENT = "hmedia/intimateNewsManager/intimateNewsComment";

	private static final String SHENHE_LIST = "hmedia/intimateNewsManager/shenheList";

	private static final String SELF_SHENHE_LIST = "hmedia/intimateNewsManager/selfshenheList";

	/** 回复管理的回复管理界面返回路径 */
	private static final String COMMENT_REPLY = "hmedia/intimateNewsManager/intimateNewsCommentReply";

	/** 跳转到审核form页面 */
	private static final String SHENHE_FORM = "hmedia/intimateNewsManager/shenhefrom";

	/** 跳转到发布页面 */
	private static final String FABU_LIST = "hmedia/intimateNewsManager/fabuList";

	/** 跳转到新增发布页面 */
	private static final String FABU_ADD = "hmedia/intimateNewsManager/fabuAdd";

	/** 跳转到贴心报转发列表 */
	private static final String ZHUANFA_LIST = "hmedia/intimateNewsManager/zhuanfaList";

	/** 跳转到贴心报转发form页面 */
	private static final String ZHUANFA_FORM = "hmedia/intimateNewsManager/zhuanfaForm";

	/** 跳转到置顶页面 */
	private static final String ZHIDING_FORM = "hmedia/intimateNewsManager/zhidingForm";

	/** 跳转到发布修改页面 */
	public static final String FABU_FORM = "hmedia/intimateNewsManager/fabuEditor";

	/** 贴心报查看详情页面 */
	public static final String DETAILS = "hmedia/intimateNewsManager/details";

	@Autowired
	private VillageService<Village> villageService;

	@Autowired
	private IntimateNewsService<IntimateNews> intimateNewsService;

	@Autowired
	private SyslogService<Syslog> syslogService;

	@Autowired
	private UserAreaService<UserArea> userAreaService;

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报管理列表界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:36
	 * @return
	 */
	@RequiresPermissions("IntimateNewM:view")
	@RequestMapping(value = "/managerlist", method = { RequestMethod.POST, RequestMethod.GET })
	public String managerlist() {
		return MANAGER_LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报审核界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:36
	 * @return
	 */
	@RequiresPermissions("IntimateNewV:view")
	@RequestMapping(value = "/shenhelist", method = { RequestMethod.POST, RequestMethod.GET })
	public String shenhelist() {
		return SHENHE_LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到自媒体审核界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:36
	 * @return
	 */
	@RequiresPermissions("IntimateNewV:view")
	@RequestMapping(value = "/selfmedialist", method = { RequestMethod.POST, RequestMethod.GET })
	public String selfmedialist() {
		return SELF_SHENHE_LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报管理新增界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:44
	 * @return
	 */
	@RequiresPermissions("IntimateNewM:manager")
	@RequestMapping(value = "/managerAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public String managerAdd(Model model, HttpServletRequest request) {
		setToken(model, request);
		return MANAGER_ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报审核界面
	 * @author:王肖东
	 * @CreateDate:2016年9月14日 下午5:51:47
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewV:manager")
	@RequestMapping(value = "/toshenheform/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toshenheform(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		map.put("id", id);
		if (id != null) {
			IntimateNews intimateNews = intimateNewsService.get(id);
			map.put("content", intimateNews.getContent());
			map.put("topFlag", intimateNews.getTopFlag());
			map.put("replyFlag", intimateNews.getReplyFlag());
		}
		setToken(model, request);
		return SHENHE_FORM;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报详情界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:44
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewV:manager")
	@RequestMapping(value = "/details/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String details(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		map.put("id", id);
		if (id != null) {
			IntimateNews intimateNews = intimateNewsService.get(id);
			map.put("content", intimateNews.getContent());
			map.put("topFlag", intimateNews.getTopFlag());
			map.put("replyFlag", intimateNews.getReplyFlag());
			IntimateNewsVo intimateNewsVo = intimateNewsService.selectIntimateNewsVo(id);
			// 判断类型
			if ("1".equals(intimateNewsVo.getType())) {
				map.put("type", "图片");
			} else {
				map.put("type", "文字");
			}
			// 当不需要置顶
			if ("0".equals(intimateNewsVo.getTopFlag())) {
				map.put("zd", "否");
			} else {
				map.put("zd", "是");
			}
			// 允许回复
			if ("0".equals(intimateNewsVo.getReplyFlag())) {
				map.put("hf", "否");
			} else {
				map.put("hf", "是");
			}
			map.put("intimateNewsVo", intimateNewsVo);

		}
		return DETAILS;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报置顶界面
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午4:56:22
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/toZhiding/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toZhiding(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		map.put("id", id);
		if (id != null) {
			IntimateNews intimateNews = intimateNewsService.get(id);
			String villageName = villageService.selectVillageByIntimateNewsId(id);
			map.put("title", intimateNews.getTitle());
			map.put("villageName", villageName);
			// 如果没有置顶的情况下
			if (intimateNews.getTopFlag().equals("0")) {
				map.put("topStart", DateUtil.dateToString(new Date()));
				map.put("topEnd", DateUtil.dateToString(new Date()));
			} else {
				map.put("topStart", DateUtil.dateToString(intimateNews.getTopStart()));
				map.put("topEnd", DateUtil.dateToString(intimateNews.getTopEnd()));
			}
		}
		setToken(model, request);
		return ZHIDING_FORM;
	}

	/**
	 * 
	 * @Title
	 * @Description 贴心报回复管理
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午2:40:33
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/toCommentIntimateNews/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toCommentIntimateNews(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		map.put("id", id);
		IntimateNewsVo intimateNewsVo = intimateNewsService.selectIntimateNewsVo(id);
		map.put("intimateNewsVo", intimateNewsVo);
		return IntimateNews_COMMENT;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报管理选择小区界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:15
	 * @return
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/selectVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectVillage(Map<String, Object> map, HttpServletRequest request) {
		String villageId = request.getParameter("villageId");
		map.put("villageId", villageId);
		return SELECT_VILLAGE;
	}

	/**
	 * @Title
	 * @Description 跳转到贴心报管理修改界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:15
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequiresPermissions("IntimateNewM:manager")
	@RequestMapping(value = "/toEdit/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toEdit(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		map.put("id", id);
		if (id != null) {
			IntimateNews intimateNews = intimateNewsService.get(id);
			map.put("content", intimateNews.getContent());
			map.put("topFlag", intimateNews.getTopFlag());
			map.put("replyFlag", intimateNews.getReplyFlag());
		}
		setToken(model, request);
		return UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报发布修改界面
	 * @author:王肖东
	 * @CreateDate:2016年8月5日 上午9:42:12
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/tofabuEdit/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String tofabuEdit(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		map.put("id", id);
		if (id != null) {
			IntimateNews intimateNews = intimateNewsService.get(id);
			map.put("content", intimateNews.getContent());
			map.put("topFlag", intimateNews.getTopFlag());
			map.put("replyFlag", intimateNews.getReplyFlag());
		}
		setToken(model, request);
		return FABU_FORM;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查到找到贴心报
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:35:09
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IntimateNewsVo intimateNewsVo = intimateNewsService.selectIntimateNewsVo(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(intimateNewsVo);
	}

	/**
	 * 
	 * @Title
	 * @Description 查询分配给当前登录用户所管理的小区
	 * @author:王肖东
	 * @CreateDate:2016年8月22日 下午2:50:38
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/queryVillageByUserId", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String queryVillageByUserId(HttpServletRequest request) throws Exception {
		List<Village> villageList = new ArrayList<Village>();
		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("userId", getUserId());
		villageList = villageService.findByUserId(parmMap);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(villageList);
	}

	/**
	 * 
	 * @Title
	 * @Description 当前登录用户所管理的区域的贴心报
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 上午9:28:19
	 * @param searchVillage
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewM:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(IntimateNewsVo searchIntimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<IntimateNewsVo> intimateNewsList = new ArrayList<IntimateNewsVo>();
		Long count = 0l;
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if (StringUtils.isBlank(areaLevel)) {
			return o.writeValueAsString("");
		}
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		searchIntimateNews.setAreaLevel(areaLevel);
		searchIntimateNews.setPageFlag(true);
		searchIntimateNews.setStartPage((page - 1) * rows);
		searchIntimateNews.setEndPage(rows);
		searchIntimateNews.setCurrentUserId(getUserId());
		count = intimateNewsService.queryCountByParams(searchIntimateNews);

		if (count > 0) {
			intimateNewsList = intimateNewsService.queryIntimateNewsByParams(searchIntimateNews);
		}

		map.put("rows", intimateNewsList);
		map.put("total", count);

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 查询已提交贴心报（用户区域级别为 市级别或者区级别）
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 上午9:28:19
	 * @param searchVillage
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewV:view")
	@RequestMapping("findYitijiao")
	public @ResponseBody String findYitijiao(IntimateNewsVo searchIntimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<IntimateNewsVo> intimateNewsList = new ArrayList<IntimateNewsVo>();
		Long count = 0l;
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if (StringUtils.isBlank(areaLevel)) {
			return o.writeValueAsString("");
		}
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		searchIntimateNews.setAreaLevel(areaLevel);
		searchIntimateNews.setPageFlag(true);
		searchIntimateNews.setStartPage((page - 1) * rows);
		searchIntimateNews.setEndPage(rows);
		searchIntimateNews.setCurrentUserId(getUserId());
		count = intimateNewsService.findIntimateNewsYitijiaoCount(searchIntimateNews);

		if (count > 0) {
			intimateNewsList = intimateNewsService.findIntimateNewsYitijiaoList(searchIntimateNews);
		}

		map.put("rows", intimateNewsList);
		map.put("total", count);

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 删除贴心报
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 上午11:00:35
	 * @param cooperationStore
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/deleteIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteIntimateNews(IntimateNews intimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };

		IntimateNewsVo original = intimateNewsService.selectIntimateNewsVo(intimateNews.getId());
		if (original == null) {// 验证是否已经删除
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		intimateNews.setUpdEac(original.getUpdEac());
		this.editAttr(intimateNews);
		boolean bl = intimateNewsService.deleteIntimateNews(intimateNews);
		if (bl) {
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.M_INTIMATE_NEW_VILLAGE, intimateNews.getId(), getUser());
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
		}
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 保存贴心报
	 * @author:王肖东
	 * @CreateDate:2016年8月5日 上午11:30:13
	 * @param villageId
	 * @param intimateNews
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "saveIntimateNews", method = { RequestMethod.POST })
	public @ResponseBody String saveIntimateNews(@RequestParam String villageId, IntimateNews intimateNews,
			@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		boolean rs = false;
		// 由于数据库表只涉及了一个icon字段 如果上传多个图片则需要拼接路径到一个字段上
		StringBuffer imgBuffer = new StringBuffer();
		for (int j = 0; j < files.length; j++) {
			MultipartFile file = files[j];
			if (files[j].getSize() > 0) {
				Attach attach = new Attach();
				rs = false;
				newFileName = UUID.randomUUID()
						+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
				rs = FileUtil.uploadfile(file, realpath, newFileName);
				if (rs) {
					newFileName = httppath + newFileName;
					attach.setUrl(newFileName);
					attachs.add(attach);
					imgBuffer.append(newFileName);
					imgBuffer.append(",");
				}
			}
		}

		if (attachs.size() == 0) {
			map.put("type", "Error");
			map.put("msg", "请添加图片！");
			return o.writeValueAsString(map);
		}
		// 去掉最后一个空格
		String icon = imgBuffer.substring(0, imgBuffer.length() - 1);
		List<Attach> newAttachs = new ArrayList<Attach>();
		// 如果选择的是文字类
		if (intimateNews.getType().equals("0")) {
			// 如果包含逗号 需要截取 并且取到第一个
			if (icon.contains(",")) {
				String[] ss = icon.split(",");
				icon = ss[0];
				newAttachs.add(attachs.get(0));
			} else {
				newAttachs = attachs;
			}
		} else {
			newAttachs = attachs;
		}
		intimateNews.setIcon(icon);
		intimateNews.setAttachs(newAttachs);
		// 新增公共属性
		this.addAttr(intimateNews);

		// 点击了发布按钮
		if (intimateNews.getPublishStatus().equals("1")) {
			// 贴心报发布时间
			intimateNews.setPublishTime(new Date());
			// 记录发布人
			intimateNews.setPublishPersonid(getUserId());
		}

		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.IntimateNews, null);
			return o.writeValueAsString(map);
		}
		boolean result = intimateNewsService.insertIntimateNews(intimateNews, villageId);
		if (result) {
			map.put("type", "Success");
			// 如果点了贴心报管理中的保存按钮或者贴心报快捷发布中的保存按钮
			if ("0".equals(intimateNews.getVerifyStatus()) || "0".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
			}
			// 如果点了贴心报快管理中的提交审核按钮
			if ("1".equals(intimateNews.getVerifyStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100079, arr));
			}
			// 如果点了贴心报快捷发布中的发布按钮
			if ("1".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			}
		} else {
			map.put("type", "Error");
			// 如果点了贴心报管理中的保存按钮或者贴心报快捷发布中的保存按钮
			if ("0".equals(intimateNews.getVerifyStatus()) || "0".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
			}
			// 如果点了贴心报快管理中的提交审核按钮
			if ("1".equals(intimateNews.getVerifyStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100080, arr));
			}
			// 如果点了贴心报快捷发布中的发布按钮
			if ("1".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076, arr));
			}
		}

		return o.writeValueAsString(map);
	}

	/**
	 * 添加用户共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void addAttr(IntimateNews _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	/**
	 * 
	 * @Title
	 * @Description String自动转成date类型
	 * @author:王肖东
	 * @CreateDate:2016年7月30日 上午10:02:48
	 * @param binder
	 */
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 
	 * @Title
	 * @Description 获取当前用户的区域级别
	 * @author:YK
	 * @CreateDate:2016年7月29日 下午4:47:03
	 * @return
	 */
	private String getCurrentAreaLevel() {
		UserAreaVo userAreaVo = new UserAreaVo();
		userAreaVo.setUserId(super.getUserId());
		userAreaVo.setPageFlag(false);
		List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
		if (userAreaList.size() <= 0) {
			return "";
		}
		String areaLevel = userAreaList.get(0).getAreaLevel();
		return areaLevel;
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(IntimateNews _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	private void editAttr(IntimateNewsComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	private void editAttr(IntimateNewsCommentReply _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	/**
	 * 
	 * @Title
	 * @Description 查询评价列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午6:01:22
	 * @param aroundStoreCommentVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping("/findCommentAll")
	public @ResponseBody String findCommentAll(IntimateNewsCommentVo intimateNewsCommentVo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		List<IntimateNewsCommentVo> intimateNewsVoList = new ArrayList<IntimateNewsCommentVo>();
		Long count = 0l;
		intimateNewsCommentVo.setPageFlag(true);
		intimateNewsCommentVo.setStartPage((page - 1) * rows);
		intimateNewsCommentVo.setEndPage(rows);
		// 评论内容进行加密 评论人进行加密
		if (intimateNewsCommentVo.getContent() != null) {
			intimateNewsCommentVo.setContent(UnescapeUtil.escape(intimateNewsCommentVo.getContent()));
		}
		if (intimateNewsCommentVo.getInsName() != null) {
			intimateNewsCommentVo.setInsName(UnescapeUtil.escape(intimateNewsCommentVo.getInsName()));
		}
		count = intimateNewsService.queryCountCommentByParams(intimateNewsCommentVo);
		intimateNewsVoList = intimateNewsService.queryListCommentByParams(intimateNewsCommentVo);
		/* 转义表情 */
		for (IntimateNewsCommentVo incv : intimateNewsVoList) {
			incv.setInsName(UnescapeUtil.unescape(incv.getInsName()));
			incv.setContent(UnescapeUtil.unescape(incv.getContent()));
		}

		map.put("rows", intimateNewsVoList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 回复的回复初始化
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午9:57:31
	 * @param id
	 * @param aroundStoreId
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/toReplyComment/{id}/{intimateNewsId}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toReplyIntimateNews(@PathVariable Long id, @PathVariable Long intimateNewsId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		map.put("aroundStoreCommentId", id);
		IntimateNewsVo intimateNewsVo = intimateNewsService.selectIntimateNewsVo(intimateNewsId);
		IntimateNewsCommentVo intimateNewsCommentVo = intimateNewsService.findIntimateNewsCommentDetail(id);
		map.put("intimateNewsVo", intimateNewsVo);
		intimateNewsCommentVo.setInsName(UnescapeUtil.unescape(intimateNewsCommentVo.getInsName()));
		intimateNewsCommentVo.setContent(UnescapeUtil.unescape(intimateNewsCommentVo.getContent()));
		map.put("intimateNewsCommentVo", intimateNewsCommentVo);
		map.put("commentDatetime", DateUtil.format(intimateNewsCommentVo.getInsYmdhms(), "yyyy-MM-dd HH:mm:ss"));
		return COMMENT_REPLY;
	}

	/**
	 * 
	 * @Title
	 * @Description 贴心报回复的回复列表查询
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午10:35:06
	 * @param aroundStoreCommentReplyVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping("/findCommentReplyAll")
	public @ResponseBody String findCommentReplyAll(IntimateNewsCommentReplyVo intimateNewsCommentReplyVo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		List<IntimateNewsCommentReplyVo> intimateNewsCommentReplyList = new ArrayList<IntimateNewsCommentReplyVo>();
		Long count = 0l;
		intimateNewsCommentReplyVo.setPageFlag(true);
		intimateNewsCommentReplyVo.setStartPage((page - 1) * rows);
		intimateNewsCommentReplyVo.setEndPage(rows);
		// 评论内容进行加密 评论人进行加密
		if (intimateNewsCommentReplyVo.getContent() != null) {
			intimateNewsCommentReplyVo.setContent(UnescapeUtil.escape(intimateNewsCommentReplyVo.getContent()));
		}
		if (intimateNewsCommentReplyVo.getInsName() != null) {
			intimateNewsCommentReplyVo.setInsName(UnescapeUtil.escape(intimateNewsCommentReplyVo.getInsName()));
		}
		count = intimateNewsService.findIntimateNewsCommentReplyListCount(intimateNewsCommentReplyVo);
		intimateNewsCommentReplyList = intimateNewsService.findIntimateNewsCommentReplyList(intimateNewsCommentReplyVo);

		/* 转义表情 */
		for (IntimateNewsCommentReplyVo incrv : intimateNewsCommentReplyList) {
			incrv.setInsName(UnescapeUtil.unescape(incrv.getInsName()));
			incrv.setContent(UnescapeUtil.unescape(incrv.getContent()));
		}

		map.put("rows", intimateNewsCommentReplyList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 列表页面直接提交 审核状态为未提交的贴心报时调用的方法
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 上午11:20:14
	 * @param intimateNews
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewM:manager")
	@RequestMapping(value = "/tjshIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String tjshIntimateNews(IntimateNews intimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		IntimateNews original = intimateNewsService.get(intimateNews.getId());
		// 状态改为已提交
		original.setVerifyStatus("1");
		this.editAttr(original);
		int result = intimateNewsService.updateSelective(original);
		if (result > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100079, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100079, arr),
					ConstantStr.M_INTIMATE_NEW_VILLAGE, intimateNews.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100080, arr));
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 列表页面直接发布 发布状态为未发布的贴心报时调用的方法
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 上午11:20:14
	 * @param intimateNews
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/fabuIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String fabuIntimateNews(IntimateNews intimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		IntimateNews original = intimateNewsService.get(intimateNews.getId());
		// 状态变为已发布
		original.setPublishStatus(intimateNews.getPublishStatus());
		// 记录发布时间
		original.setPublishTime(new Date());
		// 记录发布人
		original.setPublishPersonid(getUserId());
		this.editAttr(original);
		int result = intimateNewsService.updateSelective(original);
		if (result > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100075, arr),
					ConstantStr.M_INTIMATE_NEW_VILLAGE, intimateNews.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076, arr));
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 转发贴心报(类似新增 不是当前用户管辖小区下面的贴心报转发到自己管辖小区下面)
	 * @author:王肖东
	 * @CreateDate:2016年8月5日 上午11:30:13
	 * @param villageId
	 * @param intimateNews
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewF:manager")
	@RequestMapping(value = "zhuanfaIntimateNews", method = { RequestMethod.POST })
	public @ResponseBody String zhuanfaIntimateNews(@RequestParam String villageId, IntimateNews intimateNews,
			@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };

		IntimateNews orginal = intimateNewsService.selectIntimateNews(intimateNews.getId());
		// 查询原来贴心报对应的附件
		List<Attach> listAttachs = intimateNewsService.queryListByParamOrder(intimateNews.getId());
		List<Attach> newList = new ArrayList<Attach>();
		for (int i = 0; i < listAttachs.size(); i++) {
			Attach attach = new Attach();
			attach.setUrl(listAttachs.get(i).getUrl());
			newList.add(attach);
		}
		IntimateNews newIntimateNews = new IntimateNews();
		// 复制属性(排除id)
		BeanUtils.copyProperties(orginal, newIntimateNews, new String[] { "id" });
		// 下面为替换掉要修改的属性

		// 置顶状态
		if (intimateNews.getTopFlag().equals("1")) {
			newIntimateNews.setTopFlag("1");
			// 置顶开始时间
			newIntimateNews.setTopStart(intimateNews.getTopStart());
			// 置顶结束时间
			newIntimateNews.setTopEnd(intimateNews.getTopEnd());
		} else {
			newIntimateNews.setTopFlag("0");
		}
		// 设置是否允许回复
		newIntimateNews.setReplyFlag(intimateNews.getReplyFlag());

		// 新的附件
		newIntimateNews.setAttachs(newList);

		// 从别人转发过来的贴新报
		newIntimateNews.setRepeatFlag("1");

		// 新增公共属性
		this.addAttr(newIntimateNews);
		// 贴心报发布时间
		newIntimateNews.setPublishTime(new Date());
		// 记录发布人
		newIntimateNews.setPublishPersonid(getUserId());
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.IntimateNews, null);
			return o.writeValueAsString(map);
		}
		boolean result = intimateNewsService.insertIntimateNews(newIntimateNews, villageId);
		if (result) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200026, arr));
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200027, arr));
		}

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 列表页面直接取消发布
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 上午11:20:14
	 * @param intimateNews
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/quxiaofabuIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String quxiaofabuIntimateNews(IntimateNews intimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		IntimateNews original = intimateNewsService.get(intimateNews.getId());
		intimateNews.setUpdEac(original.getUpdEac());
		this.editAttr(intimateNews);
		int result = intimateNewsService.updateSelective(intimateNews);
		if (result > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100077, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100077, arr),
					ConstantStr.M_INTIMATE_NEW_VILLAGE, intimateNews.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100078, arr));
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 审核贴心报(审核通过 直接变为发布)
	 * @author:王肖东
	 * @CreateDate:2016年9月23日 下午2:12:06
	 * @param verifyOpinion
	 * @param verifyStatus
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewV:manager")
	@RequestMapping(value = "/shenheIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String shenheIntimateNews(String verifyOpinion, String verifyStatus, String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		IntimateNews original = intimateNewsService.get(id);
		// 通过
		if (verifyStatus.equals("2")) {
			// 记录审核人
			original.setVerifyId(getUserId());
			// 记录审核时间
			original.setVerifyTime(new Date());
			// 发布状态变为1
			original.setPublishStatus("1");
			// 贴心报发布时间
			original.setPublishTime(new Date());
			// 记录发布人
			original.setPublishPersonid(getUserId());
		} else { // 不通过的情况
			// 记录审核人
			original.setVerifyId(getUserId());
			// 记录审核时间
			original.setVerifyTime(new Date());
			// 发布状态变为0
			original.setPublishStatus("0");
		}
		// 审核状态
		original.setVerifyStatus(verifyStatus);
		// 审核意见
		original.setVerifyOpinion(verifyOpinion);
		this.editAttr(original);
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.IntimateNews, null);
			return o.writeValueAsString(map);
		}
		int result = intimateNewsService.updateSelective(original);
		if (result > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100085, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100085, arr),
					ConstantStr.M_INTIMATE_NEW_VILLAGE, original.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100086, arr));
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 编辑贴心报
	 * @author:王肖东
	 * @CreateDate:2016年8月23日 下午6:17:19
	 * @param files
	 * @param villageId
	 * @param intimateNews
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/updateIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String updateIntimateNews(@RequestParam("file") MultipartFile[] files,
			@RequestParam String villageId, IntimateNews intimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		// 附件上传
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		StringBuffer imgBuffer = new StringBuffer();
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			Attach attach = new Attach();
			attach.setOrdering(i);
			if (file.getSize() <= 0) {
				if (i == 0) {
					// 如果选择的是文字类的话
					if ("0".equals(intimateNews.getType())) {
						attach.setUrl(intimateNews.getPic1());
						imgBuffer.append(intimateNews.getPic1());
						imgBuffer.append(",");
					}
				} else if (i == 1) {
					attach.setUrl(intimateNews.getPic2());
					imgBuffer.append(intimateNews.getPic2());
					imgBuffer.append(",");
				} else if (i == 2) {
					attach.setUrl(intimateNews.getPic3());
					imgBuffer.append(intimateNews.getPic3());
					imgBuffer.append(",");
				} else if (i == 3) {
					attach.setUrl(intimateNews.getPic4());
					imgBuffer.append(intimateNews.getPic4());
					imgBuffer.append(",");
				}
				attachs.add(attach);
				continue;
			}
			newFileName = UUID.randomUUID()
					+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if (rs) {
				newFileName = httppath + newFileName;
				attach.setUrl(newFileName);
				attachs.add(attach);
				imgBuffer.append(newFileName);
				imgBuffer.append(",");
			}
		}
		IntimateNews original = intimateNewsService.get(intimateNews.getId());
		List<Attach> newAttachs = new ArrayList<Attach>();
		// 去掉最后一个逗号
		String icon = imgBuffer.substring(0, imgBuffer.length() - 1);
		// 如果选择的是文字类
		if (intimateNews.getType().equals("0")) {
			// 如果包含逗号 需要截取 并且取到第一个
			if (icon.contains(",")) {
				String[] ss = icon.split(",");
				icon = ss[0];
				newAttachs.add(attachs.get(0));
			}
		} else {
			newAttachs = attachs;
		}
		intimateNews.setIcon(icon);
		intimateNews.setAttachs(newAttachs);
		intimateNews.setUpdEac(original.getUpdEac());
		// 如果为发布操作 记录发布人 发布时间
		if ("1".equals(intimateNews.getPublishStatus())) {
			// 贴心报发布时间
			intimateNews.setPublishTime(new Date());
			// 记录发布人
			intimateNews.setPublishPersonid(getUserId());
		}
		editAttr(intimateNews);
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.IntimateNews, null);
			return o.writeValueAsString(map);
		}
		boolean result = intimateNewsService.updateIntimateNews(intimateNews, villageId);
		if (result) {
			map.put("type", "Success");
			// 如果点了贴心报管理中的保存按钮或者贴心报快捷发布中的保存按钮
			if ("0".equals(intimateNews.getVerifyStatus()) || "0".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
			}
			// 如果点了贴心报快管理中的提交审核按钮
			if ("1".equals(intimateNews.getVerifyStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100079, arr));
			}
			// 如果点了贴心报快捷发布中的发布按钮
			if ("1".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			}
		} else {
			map.put("type", "Error");
			// 如果点了贴心报管理中的保存按钮或者贴心报快捷发布中的保存按钮
			if ("0".equals(intimateNews.getVerifyStatus()) || "0".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
			}
			// 如果点了贴心报快管理中的提交审核按钮
			if ("1".equals(intimateNews.getVerifyStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100080, arr));
			}
			// 如果点了贴心报快捷发布中的发布按钮
			if ("1".equals(intimateNews.getPublishStatus())) {
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076, arr));
			}
		}
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 置顶贴心报
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:57:47
	 * @param village
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/zhidingIntimateNews", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String zhidingIntimateNews(IntimateNews intimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "贴心报" };
		IntimateNews original = intimateNewsService.get(intimateNews.getId());
		intimateNews.setUpdEac(original.getUpdEac());
		this.editAttr(intimateNews);
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.IntimateNews, null);
			return o.writeValueAsString(map);
		}
		int result = intimateNewsService.updateSelective(intimateNews);
		if (result > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100073, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100073, arr),
					ConstantStr.M_INTIMATE_NEW_VILLAGE, intimateNews.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100074, arr));
		}

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 查询贴心报状态>0的贴心报
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 上午9:28:19
	 * @param searchVillage
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewV:view")
	@RequestMapping("findselfAll")
	public @ResponseBody String findselfAll(IntimateNewsVo searchIntimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<IntimateNewsVo> intimateNewsList = new ArrayList<IntimateNewsVo>();
		Long count = 0l;
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		searchIntimateNews.setPageFlag(true);
		searchIntimateNews.setStartPage((page - 1) * rows);
		searchIntimateNews.setEndPage(rows);
		searchIntimateNews.setCurrentUserId(getUserId());
		count = intimateNewsService.findselfIntimateNewsYitijiaoCount(searchIntimateNews);

		if (count > 0) {
			intimateNewsList = intimateNewsService.findselfIntimateNewsYitijiaoList(searchIntimateNews);
		}

		map.put("rows", intimateNewsList);
		map.put("total", count);

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报发布界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:44
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewQ:manager")
	@RequestMapping(value = "/tofabulist", method = { RequestMethod.POST, RequestMethod.GET })
	public String tofabulist(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return FABU_LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 查询贴心报状态为已审核的贴心报(verifyStatus=2)
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 上午9:28:19
	 * @param searchVillage
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewQ:manager")
	@RequestMapping("findyishenheAll")
	public @ResponseBody String findyishenheAll(IntimateNewsVo searchIntimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<IntimateNewsVo> intimateNewsList = new ArrayList<IntimateNewsVo>();
		Long count = 0l;
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if (StringUtils.isBlank(areaLevel)) {
			return o.writeValueAsString("");
		}
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		// 区域级别
		searchIntimateNews.setAreaLevel(areaLevel);
		searchIntimateNews.setPageFlag(true);
		searchIntimateNews.setStartPage((page - 1) * rows);
		searchIntimateNews.setEndPage(rows);
		searchIntimateNews.setCurrentUserId(getUserId());
		count = intimateNewsService.findIntimateNewsYiShenheCount(searchIntimateNews);

		if (count > 0) {
			intimateNewsList = intimateNewsService.findIntimateNewsYishenheList(searchIntimateNews);
		}

		map.put("rows", intimateNewsList);
		map.put("total", count);

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报快捷发布新增界面
	 * @author:王肖东
	 * @CreateDate:2016年9月14日 下午4:00:28
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("IntimateNewQ:manager")
	@RequestMapping(value = "/fabuAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public String fabuadd(Model model, HttpServletRequest request) {
		setToken(model, request);
		return FABU_ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报转发列表
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:44
	 * @return
	 */
	@RequiresPermissions("IntimateNewF:view")
	@RequestMapping(value = "/zhuanfalist", method = { RequestMethod.POST, RequestMethod.GET })
	public String zhuanfalist() {
		return ZHUANFA_LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 查询不是本区域的并且是已发布的贴心报(查到后可以转发)
	 * @author:王肖东
	 * @CreateDate:2016年8月3日 上午11:23:20
	 * @param searchIntimateNews
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewF:view")
	@RequestMapping("findzhaunfaList")
	public @ResponseBody String findzhaunfaList(IntimateNewsVo searchIntimateNews, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<IntimateNewsVo> intimateNewsList = new ArrayList<IntimateNewsVo>();
		Long count = 0l;
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if (StringUtils.isBlank(areaLevel)) {
			return o.writeValueAsString("");
		}
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		// 区域级别
		searchIntimateNews.setAreaLevel(areaLevel);
		searchIntimateNews.setPageFlag(true);
		searchIntimateNews.setStartPage((page - 1) * rows);
		searchIntimateNews.setEndPage(rows);
		searchIntimateNews.setCurrentUserId(getUserId());
		count = intimateNewsService.querotherpersonListCount(searchIntimateNews);

		if (count > 0) {
			intimateNewsList = intimateNewsService.querotherpersonList(searchIntimateNews);
		}

		map.put("rows", intimateNewsList);
		map.put("total", count);

		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到贴心报审核界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:44
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IntimateNewF:manager")
	@RequestMapping(value = "/tozhuanfaform/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String tozhuanfaform(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		map.put("id", id);
		if (id != null) {
			IntimateNews intimateNews = intimateNewsService.get(id);
			map.put("content", intimateNews.getContent());
			map.put("topFlag", intimateNews.getTopFlag());
			map.put("replyFlag", intimateNews.getReplyFlag());
		}
		setToken(model, request);
		return ZHUANFA_FORM;
	}

	/**
	 * 
	 * @Title
	 * @Description 删除一级评论
	 * @author:QZG
	 * @CreateDate:2016年8月10日 下午10:11:23
	 * @param intimateNewsComment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/deleteComment", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteComment(IntimateNewsComment intimateNewsComment) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "贴心报评论" };

		IntimateNewsCommentVo original = intimateNewsService.findIntimateNewsCommentDetail(intimateNewsComment.getId());
		if (original == null) {// 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}

		// 设置贴心报回复的更新回数
		intimateNewsComment.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(intimateNewsComment);

		// 调用service方法对贴心报的评论信息进行删除，并返回删除结果
		boolean bl = intimateNewsService.deleteIntimateNewsComment(intimateNewsComment, original);

		if (bl) { // 如果删除成功
			IntimateNews intimateNews = intimateNewsService.selectIntimateNews(original.getIntimateNewsId());
			// 浏览量，评论量，违规量
			String str1 = "";
			if (intimateNews != null) {
				str1 = intimateNews.getViewNumber() + "/" + intimateNews.getReplyNumber() + "/"
						+ intimateNews.getIllegalNumber();
			}
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.M_INTIMATE_NEW_COMMENT, intimateNewsComment.getId(), getUser());
			map.put("dataStr", str1);
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
		} else { // 如果删除失败

			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004, arr),
					ConstantStr.M_INTIMATE_NEW_COMMENT, intimateNewsComment.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 删除二级评论
	 * @author:QZG
	 * @CreateDate:2016年8月10日 下午10:55:23
	 * @param intimateNewsCommentReply
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "IntimateNewM:view", "IntimateNewQ:manager" }, logical = Logical.OR)
	@RequestMapping(value = "/deleteCommentReply", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteCommentReply(IntimateNewsCommentReply intimateNewsCommentReply) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "贴心报评论的评论" };

		IntimateNewsCommentReplyVo original = intimateNewsService.findIntimateNewsCommentReply(intimateNewsCommentReply
				.getId());
		if (original == null) { // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}

		// 设置周边店回复的回复的更新回数
		intimateNewsCommentReply.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(intimateNewsCommentReply);

		boolean bl = intimateNewsService.deleteIntimateNewsCommentReply(intimateNewsCommentReply, original);

		if (bl) { // 如果删除成功

			IntimateNewsComment intimateNewsComment = intimateNewsService.selectIntimateNewsComment(original
					.getInCommentId());

			// 浏览量，评论量，违规量
			String str1 = "";
			if (intimateNewsComment != null) {
				str1 = intimateNewsComment.getViewNumber() + "/" + intimateNewsComment.getReplyNumber() + "/"
						+ intimateNewsComment.getIllegalNumber();
			}

			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
					ConstantStr.M_INTIMATE_NEW_COMMENT_R, intimateNewsCommentReply.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("dataStr", str1);
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
		} else { // 如果删除失败

			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006, arr),
					ConstantStr.M_AROUND_STORE_COMMENT_REPLY, intimateNewsCommentReply.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}

	/**
	 * 封装用插入日志的方法
	 * 
	 * @Title
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年8月10日 下午4:00:42
	 * @param type
	 *            操作类型
	 * @param log
	 *            日志内容
	 * @param arr
	 *            操作的内容头
	 * @param table
	 *            表名
	 * @param id
	 *            数据ID
	 */
	private void insertLog(String type, String log, Object[] arr, String table, Long id) {
		syslogService.addLog(type, ComDefine.getMsg(log, arr), table, id, getUser());
	}
}
