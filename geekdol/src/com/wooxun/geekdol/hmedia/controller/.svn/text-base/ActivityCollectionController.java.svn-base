package com.wooxun.geekdol.hmedia.controller;

import java.io.IOException;
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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
import com.wooxun.geekdol.hmedia.model.ActivityCollection;
import com.wooxun.geekdol.hmedia.service.ActivityBaomingService;
import com.wooxun.geekdol.hmedia.service.ActivityCollectionService;
import com.wooxun.geekdol.hmedia.vo.ActivityBaomingVo;
import com.wooxun.geekdol.hmedia.vo.ActivityCollectionVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * 
 * @Title
 * @Description 活动汇管理
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年8月5日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年8月5日 下午5:44:40 创建
 *           ==========================================================
 *
 */
@Controller
@RequestMapping("activityCollection")
public class ActivityCollectionController extends BaseController {

	/** 跳转到活动汇列表页面 */
	public static final String AC_LIST = "hmedia/activityCollection/activityCollectionList";

	/** 跳转到活动汇新增页面 */
	public static final String AC_ADD = "hmedia/activityCollection/activityCollectionAdd";

	/** 跳转到置顶页面 */
	private static final String ZHIDING_FORM = "hmedia/activityCollection/zhidingForm";

	/** 跳转到修改页面 */
	public static final String UPDATE = "hmedia/activityCollection/activityCollectionEditor";

	/** 跳转到小区选择页面 */
	public static final String SELECT_VILLAGE = "hmedia/activityCollection/villageSelect";

	/** 跳转到报名管理页面 */
	private static final String BAOMING_FORM = "hmedia/activityCollection/baoming";

	/** 跳转到活动汇详情页面 */
	private static final String DETAILS = "hmedia/activityCollection/details";

	@Autowired
	private ActivityCollectionService<ActivityCollection> activityCollectionService;

	@Autowired
	private VillageService<Village> villageService;

	@Autowired
	private SyslogService<Syslog> syslogService;

	@Autowired
	private UserAreaService<UserArea> userAreaService;

	@Autowired
	private ActivityBaomingService<ActivityBaoming> activityBaomingService;
	
	@Autowired
	private UserService<User> userService;

	/**
	 * 
	 * @Title
	 * @Description 跳转到活动汇管理列表界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:36
	 * @return
	 */
	@RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
	@RequestMapping(value = "/tolist", method = { RequestMethod.POST, RequestMethod.GET })
	public String tolist() {
		// 跳转到活动汇列表页面
		return AC_LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到活动汇管理新增界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:36
	 * @return
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/toAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public String toAdd(Model model, HttpServletRequest request) {
		// 跳转到活动汇新增页面
		setToken(model, request);
		return AC_ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 当前登录用户所管理的区域的活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月5日 下午6:21:13
	 * @param searchActivityCollection
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
	@RequestMapping("findAll")
	public @ResponseBody String findAll(ActivityCollectionVo searchActivityCollection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 设置查询页码 默认为1
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		// 设置每页查询条数 默认为20
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		// 创建对象用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建map变量封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建活动汇列表类
		List<ActivityCollectionVo> activityCollectionList = new ArrayList<ActivityCollectionVo>();
		// 默认活动汇查询条数
		Long count = 0l;
		// 创建区域级别变量
		String areaLevel = "";
		// 获取当前用户类型 当当前用户不是活动汇商家时
		if (!getUser().getUserType().equals(ConstantStr.USERTYPE_07)) {
			// 获取当前用户的区域级别
			areaLevel = getCurrentAreaLevel();
			// 当用户区域级别为空时
			if (StringUtils.isBlank(areaLevel)) {
				// 直接返回
				return o.writeValueAsString("");
			}

		}
		// 设置当前登录用户id
		searchActivityCollection.setCurrentUserId(getUserId());
		// 设置用户区域级别
		searchActivityCollection.setAreaLevel(areaLevel);
		// 设置分页标示为true
		searchActivityCollection.setPageFlag(true);
		// 设置查询起始记录
		searchActivityCollection.setStartPage((page - 1) * rows);
		// 设置查询结束记录
		searchActivityCollection.setEndPage(rows);
		// 设置当前用户id
		searchActivityCollection.setCurrentUserId(getUserId());
		// 设置当前用户类型
		searchActivityCollection.setUserType(getUser().getUserType());
		// 查询满足条件的活动汇数量
		count = activityCollectionService.queryCountByParams(searchActivityCollection);
		// 当活动汇数量大于0时
		if (count > 0) {
			// 查询满足条件的活动汇列表
			activityCollectionList = activityCollectionService
					.queryActivityCollectionByParams(searchActivityCollection);
			//循环活动汇列表
			for (int i = 0; i < activityCollectionList.size(); i++) {
			    //遍历活动汇
				ActivityCollectionVo activityCollectionVo = activityCollectionList.get(i);
				//当活动汇为发布状态
				if("1".equals(activityCollectionVo.getPublishStatus()))
				{
				//如果当前时间位于开始时间和结束时间之间时是否过期状态为已开始
				if (new Date().getTime() >= activityCollectionVo.getStartTime().getTime()
						&& new Date().getTime() <= activityCollectionVo.getEndTime().getTime()) {
					activityCollectionVo.setIsGuoqi(ConstantStr.GQ1);
				} //如果当前时间大于结束时间  过期状态为已过期
				 else if(new Date().getTime() >= activityCollectionVo.getEndTime().getTime()){
					activityCollectionVo.setIsGuoqi(ConstantStr.GQ2);
				} //如果当前时间小于开始时间  过期状态为未开始
                 else if(new Date().getTime() <= activityCollectionVo.getStartTime().getTime()){
                     activityCollectionVo.setIsGuoqi(ConstantStr.GQ0);
                 }
				}
			}
		}
		// 封装活动汇列表
		map.put("rows", activityCollectionList);
		// 封装活动汇列表查询数量
		map.put("total", count);
		// 返回信息
		return o.writeValueAsString(map);
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
		// 新建用户区域级别类
		UserAreaVo userAreaVo = new UserAreaVo();
		// 设置当前用户id
		userAreaVo.setUserId(super.getUserId());
		// 设置分页标示为false
		userAreaVo.setPageFlag(false);
		// 查询满足条件的用户区域界别列表
		List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
		// 当用户区域级别列表查询数量小于0时
		if (userAreaList.size() <= 0) {
			// 直接返回
			return "";
		}
		// 获取当前用于区域级别
		String areaLevel = userAreaList.get(0).getAreaLevel();
		// 返回当前用户区域级别
		return areaLevel;
	}

	/**
	 * 
	 * @Title
	 * @Description 保存活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月7日 下午8:04:31
	 * @param villageId
	 * @param activityCollection
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "saveActivityCollection", method = { RequestMethod.POST })
	public @ResponseBody String saveActivityCollection(@RequestParam String villageId,
			ActivityCollection activityCollection, @RequestParam("file") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 文件上传路径相关
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		// 新建附件类表类
		List<Attach> attachs = new ArrayList<Attach>();
		// 创建对象用于信息提示
		Object[] arr = { "活动汇" };
		// 新建文件名变量
		String newFileName = "";
		// 创建boolean变量 默认为false
		boolean rs = false;
		StringBuffer imgBuffer = new StringBuffer();
		// 遍历上传文件
		for (int j = 0; j < files.length; j++) {
			// 创建文件对象用于遍历的文件操作
			MultipartFile file = files[j];
			// 判断文件上传
			if (files[j].getSize() > 0) {
				// 新建附件类
				Attach attach = new Attach();
				// 赋值rs为false
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
					// 传入图片
					attachs.add(attach);
					imgBuffer.append(newFileName);
					// 如果是最后一项 不加逗号
					if (j != files.length - 1) {
						imgBuffer.append(",");
					}
				}
			}

		}
		// 设置图片
		activityCollection.setPic(imgBuffer.toString());
		// 当上传图片数量为空时
		if (attachs.size() == 0) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100083));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 设置附件
		activityCollection.setAttachs(attachs);
		// 新增公共属性
		this.addAttr(activityCollection);
		// 如果为1则为发布状态 记录发布人 发布人id 发布人时间
		if (activityCollection.getPublishStatus().equals("1")) {
			// 记录发布人id
			activityCollection.setPublishPerson(getUserId());
			// 记录发布时间
			activityCollection.setPublishTime(new Date());
		}
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.ACTIVITYCOLLECTION, null);
			return o.writeValueAsString(map);
		}
		// 保存活动汇 保存成功 返回true
		boolean result = activityCollectionService.insertActivityCollection(activityCollection, villageId);
		// 活动汇保存成功
		if (result) {
			if ("1".equals(activityCollection.getPublishStatus())) {
				// 封装返回信息
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			} else {
				// 封装返回信息
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
			}
		}
		// 活动汇保存失败
		else {

			if ("1".equals(activityCollection.getPublishStatus())) {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			} else {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076, arr));
			}
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 添加用户共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void addAttr(ActivityCollection _temp) {
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
	 * @Description 删除活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 上午11:44:35
	 * @param activityCollection
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/deleteActivityCollection", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteActivityCollection(ActivityCollection activityCollection,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于信息返回
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "活动汇" };
		// 根据活动汇id查询活动汇详细信息
		ActivityCollectionVo original = activityCollectionService
				.selectActivityCollectionVo(activityCollection.getId());
		// 当活动汇信息为空时
		if (original == null) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 设置更新回数
		activityCollection.setUpdEac(original.getUpdEac());
		// 修改共通字段属性
		this.editAttr(activityCollection);
		// 删除活动汇 删除成功 返回true
		boolean bl = activityCollectionService.deleteActivityCollection(activityCollection);
		// 当活动汇删除成功
		if (bl) {
			// 添加日志信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.ACTIVITYCOLLECTION, activityCollection.getId(), getUser());
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
		}
		// 活动汇删除失败
		else {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 修改活动汇共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(ActivityCollection _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	/**
	 * 修改ActivityBaoming共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editBaomingAttr(ActivityBaoming _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到活动汇置顶界面
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午4:56:22
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/toZhiding/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toZhiding(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		// 封装id
		map.put("id", id);
		// 当id不为空时
		if (id != null) {
			// 根据活动汇id查询活动汇信息
			ActivityCollection activityCollection = activityCollectionService.get(id);
			// 根据活动汇id查询对应的小区
			String villageName = villageService.selectVillageByActivityCollectionId(id);
			// 封装活动汇名称
			map.put("title", activityCollection.getTitle());
			// 封装小区名称
			map.put("villageName", villageName);
			// 当活动汇置顶开始时间不为空时
			if (activityCollection.getTopStart() != null) {
				// 封装活动汇置顶开始时间
				map.put("topStart", DateUtil.dateToString(activityCollection.getTopStart()));
			}
			// 当活动汇置顶结束时间不为空时
			if (activityCollection.getTopEnd() != null) {
				// 封装活动汇置顶结束时间
				map.put("topEnd", DateUtil.dateToString(activityCollection.getTopEnd()));
			}
		}
		// 跳转到活动汇置顶页面
		setToken(model, request);
		return ZHIDING_FORM;
	}

	/**
	 * 
	 * @Title
	 * @Description 置顶活动汇
	 * @author:QZG
	 * @CreateDate:2016年8月1日 下午11:57:47
	 * @param activityCollection
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/zhidingActivity", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String zhidingIntimateNews(ActivityCollection activityCollection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "活动汇" };
		// 根据活动汇id查询活动汇详细信息
		ActivityCollection original = activityCollectionService.get(activityCollection.getId());
		// 设置更新回数
		activityCollection.setUpdEac(original.getUpdEac());
		// 修改共通字段属性
		this.editAttr(activityCollection);
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.ACTIVITYCOLLECTION, null);
			return o.writeValueAsString(map);
		}
		// 置顶活动汇 置顶成功 返回值大于0
		int result = activityCollectionService.updateSelective(activityCollection);
		// 当活动汇置顶成功
		if (result > 0) {
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100073, arr));
			// 添加日志信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100073, arr),
					ConstantStr.ACTIVITYCOLLECTION, activityCollection.getId(), getUser());
		}
		// 活动汇置顶失败
		else {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100074, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 列表页面直接取消发布
	 * @author:QZG
	 * @CreateDate:2016年8月4日 上午11:20:14
	 * @param activityCollection
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/quxiaofabuActivity", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String quxiaofabuIntimateNews(ActivityCollection activityCollection,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "活动汇" };
		// 根据活动汇id查询活动汇详细信息
		ActivityCollection original = activityCollectionService.get(activityCollection.getId());
		// 设置更新回数
		activityCollection.setUpdEac(original.getUpdEac());
		// 修改共通字段属性
		this.editAttr(activityCollection);
		// 活动汇取消发布 返回值大于0 取消发布成功
		int result = activityCollectionService.updateSelective(activityCollection);
		// 活动汇取消发布成功
		if (result > 0) {
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100077, arr));
			// 添加日志信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100077, arr),
					ConstantStr.ACTIVITYCOLLECTION, activityCollection.getId(), getUser());
		}
		// 活动汇取消发布失败
		else {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100078, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到报名管理界面
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午7:51:20
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
	@RequestMapping(value = "/toBaoming/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toBaoming(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 封装id
		map.put("id", id);
		// 当id不为空时
		if (id != null) {
			// 根据活动汇id查询活动汇详细信息
			ActivityCollectionVo activityCollectionVo = activityCollectionService.selectActivityCollectionVo(id);
			// 封装信息
			map.put("activityCollectionVo", activityCollectionVo);
		}
		// 返回活动汇报名管理页面
		return BAOMING_FORM;
	}

	/**
	 * @Title
	 * @Description 跳转到活动汇管理修改界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:15
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/toEdit/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toEdit(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		// 封装id
		map.put("id", id);
		// 当id不为空时
		if (id != null) {
			// 根据活动汇id查询活动汇信息
			ActivityCollection activityCollection = activityCollectionService.get(id);
			// 封装活动汇内容
			map.put("content", activityCollection.getContent());
			// 封装活动汇置顶标记
			map.put("topFlag", activityCollection.getTopFlag());
			// 封装活动汇回复标记
			map.put("replyFlag", activityCollection.getReplyFlag());
		}
		// 返回修改页面
		setToken(model, request);
		return UPDATE;
	}

	/**
	 * @Title
	 * @Description 跳转到活动汇管理详情界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:15
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
	@RequestMapping(value = "/details/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String details(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 封装id
		map.put("id", id);
		// 当id不为空时
		if (id != null) {
			// 根据活动汇id查询活动汇详细信息
			ActivityCollection activityCollection = activityCollectionService.get(id);
			// 封装活动汇内容
			map.put("content", activityCollection.getContent());
			// 封装活动汇置顶标记
			map.put("topFlag", activityCollection.getTopFlag());
			// 封装活动汇回复标记
			map.put("replyFlag", activityCollection.getReplyFlag());
			// 根据活动汇id查询活动汇
	        ActivityCollectionVo activityCollectionVo = activityCollectionService.selectActivityCollectionVo(id);
	        // 封装活动汇详细信息
            map.put("activityCollectionVo", activityCollectionVo); 
            //当不需要报名
            if("0".equals(activityCollectionVo.getEnrollFlag())){
                map.put("bm", "否");
            }else{
                map.put("bm", "是");
            }
            //当不需要置顶
            if("0".equals(activityCollectionVo.getTopFlag())){
                map.put("zd", "否");
            }else{
                map.put("zd", "是");
            }
            //得到活动汇商家名称
            String  realName=  userService.get(Long.valueOf(activityCollection.getActivityShops())).getRealName();
            //封装活动汇商家名称
            map.put("shops", realName);
		}
		// 返回活动汇详情页面
		return DETAILS;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id查到找到活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午2:50:51
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 根据活动汇id查询活动汇
		ActivityCollectionVo activityCollectionVo = activityCollectionService.selectActivityCollectionVo(id);
		// 创建变量
		ObjectMapper o = new ObjectMapper();
		// 返回信息
		return o.writeValueAsString(activityCollectionVo);
	}

	/**
	 * 
	 * @Title
	 * @Description 修改活动汇
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午3:33:28
	 * @param activityCollection
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/updateActivityCollection", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String updateActivityCollection(@RequestParam String villageId,
			ActivityCollectionVo activityCollectionVo, @RequestParam("file") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "活动汇" };
		// 根据活动汇id查询活动汇
		ActivityCollectionVo original = activityCollectionService.selectActivityVo(activityCollectionVo.getId());
		// 验证是否已经删除
		if (original == null) {
			// 封装返回数据
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 文件上传路径相关
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		// 新建附件类表类
		List<Attach> attachs = new ArrayList<Attach>();
		// 新建文件名变量
		String newFileName = "";
		StringBuffer imgBuffer = new StringBuffer();
		// 遍历上传文件
		for (int i = 0; i < files.length; i++) {
			// 创建文件对象用于遍历的文件操作
			MultipartFile file = files[i];
			Attach attach = new Attach();
			attach.setOrdering(i);
			// 判断文件上传
			if (file.getSize() <= 0) {
				if (i == 0) {
					attach.setUrl(activityCollectionVo.getPic1());
					imgBuffer.append(activityCollectionVo.getPic1());
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
			}

		}
		// 设置图片
		activityCollectionVo.setPic(imgBuffer.toString());
		// 设置附件字段属性
		activityCollectionVo.setAttachs(attachs);
		// 设置更新回数
		activityCollectionVo.setUpdEac(original.getUpdEac());
		// 如果为1则为发布状态 记录发布人 发布人id 发布人时间
		if (activityCollectionVo.getPublishStatus().equals("1")) {
			// 记录发布人id
			activityCollectionVo.setPublishPerson(getUserId());
			// 记录发布时间
			activityCollectionVo.setPublishTime(new Date());
		}
		// 修改共通字段属性
		this.editActivityVo(activityCollectionVo);
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, arr));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.ACTIVITYCOLLECTION, null);
			return o.writeValueAsString(map);
		}
		// 修改活动汇 修改成功 返回true
		boolean result = activityCollectionService.updateActivityVo(activityCollectionVo, villageId);
		// 活动回修改成功
		if (result) {
			if ("1".equals(activityCollectionVo.getPublishStatus())) {
				// 封装返回信息
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			} else {
				// 封装返回信息
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
			}
		} else {
			if ("1".equals(activityCollectionVo.getPublishStatus())) {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075, arr));
			} else {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076, arr));
			}
		}
		// 返回信息
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到活动汇管理选择小区界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:15
	 * @return
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/selectVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectVillage(Map<String, Object> map, HttpServletRequest request) {
		// 获取villageId
		String villageId = request.getParameter("villageId");
		// 封装villageId
		map.put("villageId", villageId);
		// 返回小区选择页面
		return SELECT_VILLAGE;
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
	@RequiresPermissions("ActivityCollection:manager")
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
	 * @Description 列表页面直接发布 发布状态为未发布的活动汇时调用的方法
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午5:37:21
	 * @param activityCollection
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/fabuActivityCollection", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String fabuActivityCollection(ActivityCollection activityCollection,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "活动汇" };
		// 根据活动汇id查询活动汇详细信息
		ActivityCollection original = activityCollectionService.get(activityCollection.getId());
		// 状态变为已发布
		original.setPublishStatus(activityCollection.getPublishStatus());
		// 记录发布时间
		original.setPublishTime(new Date());
		// 修改共通字段属性
		this.editAttr(original);
		// 更新活动汇 即状态改为发布 返回值大于0 修改成功
		int result = activityCollectionService.updateSelective(original);
		// 当活动汇发布成功
		if (result > 0) {
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
			// 添加日志信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
					ConstantStr.ACTIVITYCOLLECTION, activityCollection.getId(), getUser());
		} else {
			// 活动汇发布失败
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 活动汇报名人数
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 下午8:48:37
	 * @param activityBaomingVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
	@RequestMapping("/findBaomingAll")
	public @ResponseBody String findBaomingAll(ActivityBaomingVo activityBaomingVo, HttpServletRequest request)
			throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建活动汇报名表变量用于存放报名信息
		List<ActivityBaomingVo> baomingList = new ArrayList<ActivityBaomingVo>();
		// 创建记录数变量用于存放报名数量
		Long count = 0l;
		/* 下面三行代码为向查询条件封装分页查询参数 */
		activityBaomingVo.setPageFlag(true);
		activityBaomingVo.setStartPage((page - 1) * rows);
		activityBaomingVo.setEndPage(rows);
		// 调用service方法获取报名数量
		count = activityBaomingService.queryCountByParams(activityBaomingVo);
		// 调用service方法获取报名列表
		baomingList = activityBaomingService.queryActivityBaomingByParams(activityBaomingVo);
		// 备注进行解密
		List<ActivityBaomingVo> list = new ArrayList<ActivityBaomingVo>();
		if (count > 0) {
			for (int i = 0; i < baomingList.size(); i++) {
				ActivityBaomingVo aVo = baomingList.get(i);
				aVo.setRemark(UnescapeUtil.unescape(aVo.getRemark()));
				list.add(aVo);
			}
		}
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", list);
		map.put("total", count);
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 删除报名人
	 * @author:王肖东
	 * @CreateDate:2016年8月8日 上午11:44:35
	 * @param activityBaoming
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/deleteActivityBaoming", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteActivityBaoming(ActivityBaoming activityBaoming, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "报名" };
		// 根据报名id查询报名信息
		ActivityBaoming original = activityBaomingService.get(activityBaoming.getId());
		// 当报名信息为空时
		if (original == null) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 设置更新回数
		activityBaoming.setUpdEac(original.getUpdEac());
		// 修改共通字段属性
		this.editBaomingAttr(activityBaoming);
		// 修改即删除报名信息 返回值大于0 删除成功
		int bl = activityBaomingService.updateSelective(activityBaoming);
		// 删除报名信息成功
		if (bl > 0) {
			// 根据报名id查到对应的活动汇信息
			ActivityCollection activityCollection = activityCollectionService.get(original.getActivityCollectionId());
			// 把活动汇报名人数-1
			activityCollection.setEnrollCount(activityCollection.getEnrollCount() - 1);
			// 修改活动汇共通属性
			this.editAttr(activityCollection);
			// 修改活动汇
			activityCollectionService.updateSelective(activityCollection);
			// 添加日志信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.ACTIVITYBAOMING, activityBaoming.getId(), getUser());
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
		} else {// 删除报名信息失败
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 导出报名表excel
	 * @author:QZG
	 * @CreateDate:2016年8月14日 下午5:54:46
	 * @param activityBaomingVo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequiresPermissions("ActivityCollection:manager")
	@RequestMapping(value = "/exportExcle", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String exportExcle(ActivityBaomingVo activityBaomingVo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		//导出设置分页flag为false
		activityBaomingVo.setPageFlag(false);
		// 调用service方法获取报名列表
		List<ActivityBaomingVo> baomingList = activityBaomingService.queryActivityBaomingByParams(activityBaomingVo);
		// 当报名列表数量大于0时
		if (baomingList.size() > 0) {
			// 调用导出excel方法
			activityCollectionService.export(activityBaomingVo, request, response);
		} else {
			// 封装返回信息 没有报名信息，无法导出！
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO200061));
		}
		return o.writeValueAsString(map);
	}

	/**
	 * 修改ActivityCollectionVo共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-QZG
	 */
	private void editActivityVo(ActivityCollectionVo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
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
