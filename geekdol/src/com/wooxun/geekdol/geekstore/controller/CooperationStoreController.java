package com.wooxun.geekdol.geekstore.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.CooperationStore;
import com.wooxun.geekdol.geekstore.model.Order;
import com.wooxun.geekdol.geekstore.model.StoreNotice;
import com.wooxun.geekdol.geekstore.service.CooperationStoreService;
import com.wooxun.geekdol.geekstore.service.OrderService;
import com.wooxun.geekdol.geekstore.service.StoreNoticeService;
import com.wooxun.geekdol.geekstore.vo.CooperationStoreVo;
import com.wooxun.geekdol.geekstore.vo.OrderVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.SendMessage;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.MessageService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.UserAreaVo;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description 合作店管理
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月25日  上午9:47:23 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("cooperationStore")
public class CooperationStoreController extends BaseController{
	
	//private static final Logger LOG = LoggerFactory.getLogger(CooperationStoreController.class);
	
	public static final String COOPERATIONSTORE = "geekstore/cooperationstore/list";
	public static final String COOPERATIONSTORE_ADD = "geekstore/cooperationstore/cooperationstoreAdd";
	public static final String COOPERATIONSTORE_EDITOR = "geekstore/cooperationstore/cooperationstoreEdit";
	public static final String COOPERATIONSTORE_VIEW = "geekstore/cooperationstore/cooperationstoreView";
	public static final String COOPERATIONSTORE_SHOW = "geekstore/cooperationstore/cooperationstoreShow";
	public static final String VILLAGE_SELECT = "geekstore/cooperationstore/villageSelect";
	public static final String PUSHMESSAGE_PAGE = "geekstore/cooperationstore/pushmessage";
	public static final String STORENOTICE_PAGE = "geekstore/cooperationstore/storenotice";
	public static final String COOPERATIONSTORE_STATUS = "geekstore/cooperationstore/cooperationstoreStatus";
	
	@Autowired
	private UserService<User> userService;
	@Autowired
	private VillageService<Village> villageService;
	@Autowired
	private CooperationStoreService<CooperationStore> cooperationStoreService;
	@Autowired
	private StoreNoticeService<StoreNotice> storeNoticeService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	@Autowired
	private UserAreaService<UserArea> userAreaService;
	@Autowired
	private OrderService<Order> orderService;
	@Autowired
	private MessageService<SendMessage> messageService;
	
	/**
	 * 
	 * @Title
	 * @Description 合作店列表页面初始化
	 * @author:YK
	 * @CreateDate:2016年7月25日 上午10:08:14
	 * @return String
	 */
	@RequiresPermissions("Cooperation:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return COOPERATIONSTORE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 合作店数据查询
	 * @author:YK
	 * @CreateDate:2016年7月25日 上午10:08:35
	 * @param cooperationStoreVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(CooperationStoreVo cooperationStoreVo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CooperationStoreVo> cooperationStoreList = new ArrayList<CooperationStoreVo>();
		ObjectMapper o = new ObjectMapper();
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		Long count = 0l;
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if(StringUtils.isBlank(areaLevel)){
			return o.writeValueAsString("");
		}
		
		//设置当前用户的级别
		cooperationStoreVo.setAreaLevel(areaLevel);
		cooperationStoreVo.setPageFlag(true);
		cooperationStoreVo.setStartPage((page - 1) * rows);
		cooperationStoreVo.setEndPage(rows);
		// 设置当前登录人，用于查询登录人管辖的合作店
		cooperationStoreVo.setCurrentUserId(getUserId());
		// 合作店总数查询
		count = cooperationStoreService.findCooperationStoreCount(cooperationStoreVo);
		// 合作店列表查询
		cooperationStoreList = cooperationStoreService.findCooperationStore(cooperationStoreVo);
		
		map.put("rows", cooperationStoreList); 
		map.put("total", count);

		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化新增页面
	 * @author:YK
	 * @CreateDate:2016年7月25日 下午10:14:35
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/toAddCooperationStore", method = { RequestMethod.GET })
	public String toAddCooperationStore(Model model) {
		return COOPERATIONSTORE_ADD;
	}
	
	/**
	   * 
	   * @Title
	   * @Description 新增合作店铺的时候，查询没有合作店管辖的小区
	   * @author:YK
	   * @CreateDate:2016年7月28日 上午9:58:37
	   * @param searchVillage
	   * @return
	   */
	@RequestMapping(value = "/queryVillageHsNoCooperationStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryVillageHsNoCooperationStore(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if(StringUtils.isBlank(areaLevel)){
			return o.writeValueAsString("");
		}
		VillageVo villageVo = new VillageVo();
		List<VillageVo> villageList = new ArrayList<VillageVo>();
		// 设置登录人
		villageVo.setUserId(getUserId());
		// 设置登录人区域级别
		villageVo.setAreaLevel(areaLevel);
		villageList.addAll(villageService.queryVillageHasNoCooperationStore(villageVo));
		return o.writeValueAsString(villageList);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 查找没有管辖合作店的店家
	 * @author:YK
	 * @CreateDate:2016年9月1日 上午10:22:18
	 * @param userType
	 * @param storeId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectShopkeeperByUserType/{userType}/{storeId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String selectShopkeeperByUserType(@PathVariable String userType,
			@PathVariable Long storeId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<User> userList = new ArrayList<User>();
		userList.addAll(userService.selectShopkeeperByUserType(userType,storeId));
		return o.writeValueAsString(userList);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存合作店数据
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午2:01:15
	 * @param cooperationStoreVo
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value="saveCooperationStore",method={RequestMethod.POST})
	public @ResponseBody String saveCooperationStore(CooperationStoreVo cooperationStoreVo,
			@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Object[] arr={"合作店"};
		// 附件上传并设置到对应的字段中
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				if(i==0){
					cooperationStoreVo.setThumbnail("");
				}
				if(i==1){
					cooperationStoreVo.setBusinessLicense("");
				}
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				if(i==0){
					cooperationStoreVo.setThumbnail(newFileName);
				}
				if(i==1){
					cooperationStoreVo.setBusinessLicense(newFileName);
				}
			}
		}
		// 增加公共属性
		this.addAttr(cooperationStoreVo);
		// 保存合作店铺数据
		boolean result = cooperationStoreService.insertCooperationStoreVo(cooperationStoreVo);
		// 根据操作结果返回相应的信息
		if(result){
			// 插入系统日志
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), 
					ConstantStr.J_COOPERATION_STORE, cooperationStoreVo.getId(), getUser());
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
			return mapper.writeValueAsString(map);
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,arr), 
					ConstantStr.J_COOPERATION_STORE, cooperationStoreVo.getId(), getUser());
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
			return mapper.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title
	 * @Description 跳转到推送消息页面
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午2:19:21
	 * @param storeId
	 * @param storeName
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/toPushMessage/{storeId}/{storeName}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toPushMessage(@PathVariable String storeId,@PathVariable String storeName,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		try {
			storeName = URLDecoder.decode(storeName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 前台显示html内容拼接
		String storeIdArr[] = storeId.split(",");
		String storeNameArr[] = storeName.split(",");
		String html = "";
		for(int i=0;i<storeIdArr.length;i++){
			html += "<a href='javaScript:viewStore("+storeIdArr[i]+")'>"+storeNameArr[i]+"</a>,";
		}
		map.put("storeId", storeId);
		map.put("storeName", html.substring(0, html.length()-1));
		return PUSHMESSAGE_PAGE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 消息推送
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午2:49:26
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/pushMessage", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String pushMessage(SendMessage message,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String storeId = request.getParameter("storeId");
		// 获取所有店长的pushId
		List<String> list = userService.selectByPushId(storeId);
		map.put("type", "Error");
		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100040));
		this.addAttr(message);
		map = messageService.sendMessage(message, map);
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化发布公告页面
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午2:57:24
	 * @param storeId
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/toAddStoreNotice/{storeId}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toAddStoreNotic(@PathVariable Long storeId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		map.put("storeId", storeId);
		// 查询合作店信息用于返回前台名称与状态
		CooperationStoreVo cooperationStoreVo = cooperationStoreService.selectCooperationStore(storeId);
		map.put("storeName", cooperationStoreVo.getStoreName());
		map.put("status", cooperationStoreVo.getStatusStr());
		return STORENOTICE_PAGE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存公告信息
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:12:07
	 * @param storeNotice
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "saveStoreNotice", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String saveStoreNotice(StoreNotice storeNotice,@RequestParam("file") MultipartFile[] files, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"发布"};
		// 附件上传
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		List<Attach> attachs = new ArrayList<Attach>();
		String newFileName = "";
		boolean rs = false;
		
		for(MultipartFile file:files){
			if(file.getSize()>0){
				Attach attach = new Attach();
				rs = false;
				newFileName = UUID.randomUUID()+file.getOriginalFilename()
						.substring(file.getOriginalFilename().indexOf("."));
				rs = FileUtil.uploadfile(file, realpath, newFileName);
				if(rs){
					newFileName = httppath+newFileName;
					attach.setUrl(newFileName);
					attachs.add(attach);
				}
			}
		}
		storeNotice.setAttachs(attachs);
		//新增公共属性
		addStoreNoticeAttr(storeNotice);
		// 保存公告数据
		boolean result = storeNoticeService.insertStoreNotice(storeNotice);
		if(result){
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), 
					ConstantStr.J_STORE_NOTICE, storeNotice.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100055));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100056));
		}
		
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化双向绑定village页面
	 * @author:YK
	 * @CreateDate:2016年7月26日 下午6:21:30
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/initVillagePage/{villageId}/{villageName}", method = {RequestMethod.POST,RequestMethod.GET})
	public String initVillagePage(Map<String, Object> map, @PathVariable String villageId,
			@PathVariable String villageName,
			HttpServletRequest request, HttpServletResponse response){
		try {
			villageName = URLDecoder.decode(villageName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("villageId", villageId);
		map.put("villageName", villageName);
		return VILLAGE_SELECT;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 跳转到更新状态页面
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:32:03
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/toUpdateCooperationStoreStatus/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toUpdateCooperationStoreStatus(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			map.put("id", id);
			return COOPERATIONSTORE_STATUS;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 合作店状态变更
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:49:34
	 * @param cooperationStore
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/updateCooperationStoreStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateCooperationStoreStatus(CooperationStore cooperationStore,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"合作店"};
		// 验证目标状态 0:全部
		if(cooperationStore != null && (StringUtils.isBlank(cooperationStore.getStatus()) || "0".equals(cooperationStore.getStatus()))){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100057));
			return o.writeValueAsString(map);
		}
		// 验证是否已经删除
		CooperationStoreVo original = cooperationStoreService.selectCooperationStore(cooperationStore.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 设置更新回数
		cooperationStore.setUpdEac(original.getUpdEac());
		// 更新公共属性
		this.editAttr(cooperationStore);
		// 更新数据
		int i = cooperationStoreService.updateSelective(cooperationStore);
		if(i>0){
			// 系统日志新增
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,arr), 
					ConstantStr.J_COOPERATION_STORE, cooperationStore.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化修改页面
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:27:50
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/toEditorCooperationStore/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toEditorCooperationStore(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			map.put("id", id);
			return COOPERATIONSTORE_EDITOR;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 根据id查找合作店的详情
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:26:24
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		// 合作店数据查询
		CooperationStoreVo cooperationStoreVo = cooperationStoreService.selectCooperationStore(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(cooperationStoreVo);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 更新合作店数据
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:14:09
	 * @param cooperationStoreVo
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/updateCooperationStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateCooperationStore(CooperationStoreVo cooperationStoreVo,
			@RequestParam("file") MultipartFile[] files, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"合作店"};
		// 验证是否已经删除
		CooperationStoreVo original = cooperationStoreService.selectCooperationStore(cooperationStoreVo.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 附件更新
		String newFileName = "";
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				if(i==0){
					cooperationStoreVo.setThumbnail(cooperationStoreVo.getThumbnail());
				}
				if(i==1){
					cooperationStoreVo.setBusinessLicense(cooperationStoreVo.getBusinessLicense());
				}
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				if(i==0){
					cooperationStoreVo.setThumbnail(newFileName);
				}
				if(i==1){
					cooperationStoreVo.setBusinessLicense(newFileName);
				}
			}
		}
		// 设置更新回数
		cooperationStoreVo.setUpdEac(original.getUpdEac());
		// 公共属性设置
		this.editCooperationStoreVoAttr(cooperationStoreVo);
		// 更新合作店数据
		boolean bl = cooperationStoreService.updateCooperationStoreVo(cooperationStoreVo);
		if(bl){
			// 新增系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), 
					ConstantStr.J_COOPERATION_STORE, cooperationStoreVo.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除合作店
	 * @author:YK
	 * @CreateDate:2016年7月27日 上午11:54:51
	 * @param cooperationStore
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:manager")
	@RequestMapping(value = "/deleteCooperationStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteCooperationStore(CooperationStore cooperationStore,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"合作店"};
		// 验证是否已经删除
		CooperationStoreVo original = cooperationStoreService.selectCooperationStore(cooperationStore.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 更新回数设置
		cooperationStore.setUpdEac(original.getUpdEac());
		// 修改公共属性
		this.editAttr(cooperationStore);
		boolean bl = cooperationStoreService.deleteCooperationStore(cooperationStore);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
					ConstantStr.J_COOPERATION_STORE, cooperationStore.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 店铺预览
	 * @author:YK
	 * @CreateDate:2016年7月28日 下午4:02:53
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Cooperation:view")
	@RequestMapping(value = "/viewCooperationStore/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String viewCooperationStore(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			map.put("id", id);
			return COOPERATIONSTORE_VIEW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 合作店一览
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午1:51:27
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Cooperation:view")
	@RequestMapping(value = "/showCooperationStore/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String showCooperationStore(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			map.put("id", id);
			return COOPERATIONSTORE_SHOW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 合作店订单查询
	 * @author:YK
	 * @CreateDate:2016年7月31日 下午4:02:42
	 * @param orderVo
	 * @param storeId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Cooperation:view")
	@RequestMapping(value = "/viewCooperationStoreOrder/{storeId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String viewCooperationStoreOrder(OrderVo orderVo,
			@PathVariable Long storeId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		
		List<OrderVo> orderList = new ArrayList<OrderVo>();
		Long count = 0l;
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if(StringUtils.isBlank(areaLevel)){
			return o.writeValueAsString("");
		}
		
		//设置当前用户的级别
		orderVo.setAreaLevel(areaLevel);
		orderVo.setPageFlag(true);
		orderVo.setStartPage((page - 1) * rows);
		orderVo.setEndPage(rows);
		orderVo.setCurrentUserId(getUserId());
		orderVo.setStoreId(storeId);
		// 设置团购订单的处理模式为直销的，用于排除非合作店的订单
		orderVo.setDetailModel(ConstantStr.PROCESSINGMODE_01);
		// 设置订单自取
		orderVo.setDeliveryMode_05(ConstantStr.DELIVERYMODE_05);
		// 设置订单小区配送
		orderVo.setDeliveryMode_06(ConstantStr.DELIVERYMODE_06);
		// 订单总数查询
		count = orderService.queryOrderCountByStore(orderVo);
		// 订单内容查询
		orderList = orderService.queryOrderByStore(orderVo);
		
		map.put("rows", orderList); 
		map.put("total", count);
		return o.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 添加用户共通属性
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:33:01
	 * @param _temp
	 */
	private void addAttr(CooperationStoreVo _temp) {
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
	 * @Description 公告公共属性新增
	 * @author:YK
	 * @CreateDate:2016年7月28日 下午3:33:36
	 * @param _temp
	 */
	private void addStoreNoticeAttr(StoreNotice _temp) {
		Long userId = getUserId();
		_temp.setOpreator(userId);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
    
    /**
     * @Title
     * @Description 修改共同属性
     * @author:YK
     * @CreateDate:2016年8月3日 下午4:33:30
     * @param _temp
     */
	private void editAttr(CooperationStore _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改CooperationStoreVo共同属性
	 * @author:YK
	 * @CreateDate:2016年7月27日 下午12:00:06
	 * @param _temp
	 */
	private void editCooperationStoreVoAttr(CooperationStoreVo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 前台时间接收
	 * @author:YK
	 * @CreateDate:2016年7月30日 上午10:01:53
	 * @param binder
	 */
	@InitBinder  
    private void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
	/**
	 * @Title
	 * @Description 获取当前用户的区域级别
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:34:06
	 * @return String
	 */
	private String getCurrentAreaLevel(){
		UserAreaVo userAreaVo = new UserAreaVo();
		userAreaVo.setUserId(super.getUserId());
		userAreaVo.setPageFlag(false);
		List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
		if(userAreaList.size()<=0){
			return "";
		}
		String areaLevel = userAreaList.get(0).getAreaLevel();
		return areaLevel;
	}
	
	/**
	 * 添加用户共通属性
	 * @param _temp
	 * @author:YK
	 */
	private void addAttr(SendMessage _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

}
