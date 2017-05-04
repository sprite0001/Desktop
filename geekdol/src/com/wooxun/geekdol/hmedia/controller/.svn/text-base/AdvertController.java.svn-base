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
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.model.Position;
import com.wooxun.geekdol.hmedia.service.AdvertService;
import com.wooxun.geekdol.hmedia.service.PositionService;
import com.wooxun.geekdol.hmedia.vo.AdvertVo;
import com.wooxun.geekdol.hmedia.vo.PositionVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.VillageService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年8月2日
 * @param
 * @see
 * @modified ========================================================== No 修改人员
 *           修改日期 描述 1. QZG 2016年8月3日 下午4:42:03 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("advert")
public class AdvertController extends BaseController {

	// 返回广告查询列表页面
	private static final String LIST = "hmedia/advert/list";
	// 返回广告新增页面
	private static final String ADD = "hmedia/advert/add";
	// 返回到广告位置选择页面
	private static final String ADDPOSITION = "hmedia/advert/addposition";
	// 返回到双向绑定village页面
	private static final String VILLAGE_SELECT = "hmedia/advert/villageSelect";
	// 返回到修改页面
	private static final String UPDATE = "hmedia/advert/update";

	@Autowired
	private AdvertService<Advert> advertService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	@Autowired
	private PositionService<Position> positionService;
	@Autowired
	private VillageService<Village> villageService;

	// 时间格式化
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到广告列表页面
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午4:16:49
	 * @return
	 */
	@RequiresPermissions("Advert:view")
	@RequestMapping("list")
	public String list() {
		// 返回广告列表页面
		return LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据页面查询条件返回列表
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午4:17:10
	 * @param advertVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(AdvertVo advertVo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 设置查询页面 默认为1
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		// 设置页面查询条数 默认为20
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建广告列表对象
		List<AdvertVo> advertList = new ArrayList<AdvertVo>();
		// 创建count变量用于存放满足查询条件的广告查询数量
		Long count = 0l;
		// 设置分页标示为true
		advertVo.setPageFlag(true);
		// 设置查询页面开始数据序号
		advertVo.setStartPage((page - 1) * rows);
		// 设置查询页面结束数据序号
		advertVo.setEndPage(rows);
		// 查询满足条件广告数量
		count = advertService.findAllCount(advertVo);
		// 当查询广告数量大于0时
		if (count > 0) {
			// 查询满足条件的广告列表
			advertList = advertService.findAll(advertVo);
		}
		// 封装满足条件广告列表
		map.put("rows", advertList);
		// 封装满足条件广告条数
		map.put("total", count);
		// 创建变量
		ObjectMapper o = new ObjectMapper();
		// 返回数据
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title 逻辑删除广告信息
	 * @Description
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午4:16:18
	 * @param advert
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String delete(Advert advert, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "广告" };
		// 创建变量result 默认为false
		boolean result = false;
		// 通过广告id返回广告类 返回值为null时 广告已经被删除
		Advert original = advertService.get(advert.getId());
		// 广告已经删除
		if (original == null) {
			// 封装返回数据
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回JSON数据
			return o.writeValueAsString(map);
		}
		// 只有状态为未开始的才可以删除，其他状态不能删除
		// 当广告状态不是未开始时，不能删除
		if (!ConstantStr.AD_NOT_BEGIN.equals(original.getBeginFlag())) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100082));
			// 返回JSON数据
			return o.writeValueAsString(map);
		}
		// 设置广告的更新回数
		advert.setUpdEac(original.getUpdEac());
		// 更新Model中相关共同变量的数据
		this.editAttr(advert);
		// 删除广告 返回值为true 删除成功
		result = advertService.deleteAdvert(advert);
		// 如果删除成功
		if (result) {
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
			// 调用日志service方法写入日志信息
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.GADVERT, advert.getId(), getUser());
		} else {
			// 删除失败
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
		}
		// 返回数据
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 广告管理 开始操作
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午4:36:15
	 * @param advert
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/start", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String start(Advert advert, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "广告" };
		// 创建变量result 默认为false
		boolean result = false;
		// 根据广告id查询广告信息
		Advert original = advertService.get(advert.getId());
		// 设置广告更新回数
		advert.setUpdEac(original.getUpdEac());
		// 广告已经为开始标示不能再开始
		if (ConstantStr.AD_IS_RUNING.equals(original.getBeginFlag())) {
			// 封装返回数据
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100061, arr));
		} else {
			// 更新共同中共同变量
			this.editAttr(advert);
			// 广告开始操作 返回值为true 操作成功
			result = advertService.start(advert);
			// 开始操作成功
			if (result) {
				// 封装返回数据
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100063, arr));
				// 调用日志service方法添加日志
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100063, arr),
						ConstantStr.GADVERT, advert.getId(), getUser());
			} else {
				// 开始操作失败
				// 封装返回数据
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100065, arr));
			}
		}
		// 返回数据
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 广告管理 结束操作
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午4:38:03
	 * @param advert
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/end", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String end(Advert advert, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "广告" };
		// 创建变量result 默认为false
		boolean result = false;
		// 根据广告id查询广告信息
		Advert original = advertService.get(advert.getId());
		// 设置广告更新回数
		advert.setUpdEac(original.getUpdEac());
		// 已经为结束标示不能再结束
		if (ConstantStr.AD_IS_END.equals(original.getBeginFlag())) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100062, arr));
		} else {
			// 更新相关共同变量
			this.editAttr(advert);
			// 广告结束操作 返回值为true 操作成功
			result = advertService.end(advert);
			// 结束操作成功
			if (result) {
				// 封装返回数据
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100064, arr));
				// 调用日志server方法添加日志
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100064, arr),
						ConstantStr.GADVERT, advert.getId(), getUser());
			} else {
				// 结束操作失败
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100066, arr));
			}
		}
		// 返回数据
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title 跳转到广告新增页面
	 * @Description
	 * @author:QZG
	 * @CreateDate:2016年8月3日 上午10:56:49
	 * @param model
	 * @return
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/toAddAdvert", method = { RequestMethod.GET })
	public String toAddAdvert(Model model) {
		// 跳转到广告新增页面
		return ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到广告位置选择页面
	 * @author:QZG
	 * @CreateDate:2016年8月3日 上午10:58:08
	 * @param model
	 * @return
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/toAddPositioin", method = { RequestMethod.GET })
	public String toAddPositioin(Model model) {
		// 跳转到广告位置选择页面
		return ADDPOSITION;
	}

	/**
	 * 
	 * @Title
	 * @Description 新增时广告位置选择初始化数据
	 * @author:QZG
	 * @CreateDate:2016年8月3日 下午2:57:41
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/initPositionName")
	@ResponseBody
	public String initPositionName(HttpServletRequest request) throws Exception {
		// 创建变量用于返回数据
		ObjectMapper o = new ObjectMapper();
		// 创建广告位置列表类
		List<PositionVo> positionList = new ArrayList<PositionVo>();
		// 广告位置列表类添加所有广告位置信息
		positionList.addAll(positionService.findAll(null));
		// 返回信息
		return o.writeValueAsString(positionList);
	}

	/**
	 * 
	 * @Title
	 * @Description 新增广告
	 * @author:QZG
	 * @CreateDate:2016年8月3日 下午5:56:45
	 * @param advert
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "saveAdvert", method = { RequestMethod.POST })
	public @ResponseBody String saveAdvert(@RequestParam String villageId, Advert advert, @RequestParam String urlString,
			@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建mapper变量用于返回信息
		ObjectMapper mapper = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "广告" };
		// 上传文件所需要的信息
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		// 附件列表对象
		List<Attach> attachs = new ArrayList<Attach>();
		// 创建文件名
		String newFileName = "";
		// 创建boolean变量 默认为false
		boolean rs = false;
		// 当上传广告类型为文字广告并且广告链接形式为网址时不进入批量上传文件操作
		if (!(advert.getAdverType().equals("2") && advert.getLinkType().equals("4"))) {
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
					rs = FileUtil.uploadCompressPic(file, realpath, newFileName);
					// 如果上传成功
					if (rs) {
						// 获取文件上传后的相对路径
						newFileName = httppath + newFileName;
						// 设置文件地址
						attach.setUrl(newFileName);
						// 传入广告列表
						attachs.add(attach);
						// 上传广告图片
						if (j == 0) {
							advert.setAdverPic(newFileName);
						}else {
							advert.setLinkContenturl(newFileName);
						}
					}
				}
			}
			// 如果没有上传文件
			if (attachs.size() == 0) {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getDefine(ConstantStr.INFO100083));
				// 返回信息
				return mapper.writeValueAsString(map);
			}
		}
		if (StringUtils.isNotBlank(urlString)) {
			advert.setLinkContenturl(urlString);
		}
		// 添加附件
		advert.setAttachs(attachs);
		// 添加共通属性
		this.addAttr(advert);
		// 广告添加操作 返回值为true 添加成功
		boolean res = advertService.insertAdvert(advert, villageId);
		// 广告添加成功
		if (res) {
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
			// 调用日志server方法进行日志添加
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001, arr),
					ConstantStr.GADVERT, advert.getId(), getUser());
		} else {
			// 广告添加失败
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
		}
		// 返回信息
		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到活动汇管理选择小区界面
	 * @author:王肖东
	 * @CreateDate:2016年7月29日 下午2:06:15
	 * @return
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/selectVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectVillage(Map<String, Object> map, HttpServletRequest request) {
		// 获取villageId
		String villageId = request.getParameter("villageId");
		// 封装villageId
		map.put("villageId", villageId);
		// 返回小区选择页面
		return VILLAGE_SELECT;
	}

	/**
	 * 
	 * @Title
	 * @Description 新增时初始化用户所管辖小区
	 * @author:QZG
	 * @CreateDate:2016年8月4日 下午2:28:55
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/initCommunity")
	@ResponseBody
	public String initCommunity(HttpServletRequest request) throws Exception {
		// 创建变量用于返回数据
		ObjectMapper o = new ObjectMapper();
		// 根据当前用户id查询其管辖下的所有小区
		List<Village> villageList = villageService.selectAll(getUserId());
		// 返回数据
		return o.writeValueAsString(villageList);
	}

	/**
	 * 
	 * @Title
	 * @Description 返回到修改页面
	 * @author:QZG
	 * @CreateDate:2016年8月5日 上午10:39:52
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/toEditorAdvert/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String toEditorCooperationStore(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		// 封装id
		map.put("id", id);
		// 通过广告id查询广告信息
		AdvertVo advertVo = advertService.selectAdvertVo(id);
		map.put("advertVo", advertVo);
		// 返回到修改页面
		return UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 初始化修改页面
	 * @author:QZG
	 * @CreateDate:2016年8月5日 上午10:53:35
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 通过广告id查询广告信息
		AdvertVo advertVo = advertService.selectAdvertVo(id);
		if (ConstantStr.LINK_TYPE_4.equals(advertVo.getLinkType())) {
			advertVo.setUrlString(advertVo.getLinkContenturl());
		}
		// 创建变量
		ObjectMapper o = new ObjectMapper();
		// 返回信息
		return o.writeValueAsString(advertVo);
	}

	/**
	 * 
	 * @Title
	 * @Description 修改广告
	 * @author:QZG
	 * @CreateDate:2016年8月7日 下午10:03:15
	 * @param advertVo
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Advert:manager")
	@RequestMapping(value = "/updateAdvert", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String updateAdvert(@RequestParam String villageId, AdvertVo advertVo, @RequestParam String urlString, 
			@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] arr = { "广告" };
		// 通过广告id查询广告信息 返回值为null 广告已被删除
		AdvertVo original = advertService.selectAdvertVo(advertVo.getId());
		// 验证是否已经删除
		if (original == null) {
			// 封装返回数据
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 获取上传文件所需要的信息
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		// 创建广告列表类
		List<Attach> attachs = new ArrayList<Attach>();
		// 创建文件名
		String newFileName = "";
		// 创建boolean变量 默认为false
		boolean rs = false;
		// 当上传广告类型为文字广告并且广告链接形式为网址时不进入批量上传文件操作
		if (!(advertVo.getAdverType().equals("2") && advertVo.getLinkType().equals("4"))) {
			// 遍历上传文件
			for (int j = 0; j < files.length; j++) {
				// 创建文件对象用于遍历的文件操作
				MultipartFile file = files[j];
				// 判断当前文件是否被上传
				if (files[j].getSize() > 0) {
					// 创建附件类
					Attach attach = new Attach();
					// 变量赋值为false
					rs = false;
					// 生成新的文件名
					newFileName = UUID.randomUUID()
							+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					// 上传文件
					rs = FileUtil.uploadCompressPic(file, realpath, newFileName);
					// 如果文件上传成功
					if (rs) {
						// 获取文件的相对路径
						newFileName = httppath + newFileName;
						// 设置附件url
						attach.setUrl(newFileName);
						// 添加附件
						attachs.add(attach);
						// 上传广告图片
						if (j == 0) {
							advertVo.setAdverPic(newFileName);
						}
					}
				}
			}
		}
		if (StringUtils.isNotBlank(urlString)) {
			advertVo.setLinkContenturl(urlString);
		}
		// 添加附件列表
		advertVo.setAttachs(attachs);
		// 设置更新回数
		advertVo.setUpdEac(original.getUpdEac());
		// 更新共通变量
		this.editAdvertVo(advertVo);
		// 广告更新操作 返回值为true 更新成功
		boolean res = advertService.updateAdvertVo(advertVo, villageId);
		// 如果更新操作成功
		if (res) {
			// 封装返回数据
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
			// 调用日志service进行日志添加
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
					ConstantStr.GADVERT, advertVo.getId(), getUser());
		} else {
			// 广告更新失败
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 判断广告编号是否重复
	 * @author:kangtianyu
	 * @CreateDate:2016年9月23日 下午1:56:57
	 * @param adverCode
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkAdvertCode", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkAdvertCode(String advertCode, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		
		// 调用service方法查看广告编码是否重复
		boolean bl = advertService.findAdvertCode(advertCode.replaceAll(" ", "").replaceAll("　", ""), id);
		
		if(!bl){ // 如果不存在
			
			/* 向map中封装成功信息 */
			map.put("type", "Success");
		}else{ // 如果存在
			
			/* 向map中封装失败信息 */
			map.put("type", "Error");
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}

	/**
	 * 添加共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-QZG
	 */
	private void addAttr(Advert _temp) {
		Long userId = getUserId();
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setInsYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
		_temp.setBeginFlag(ConstantStr.AD_NOT_BEGIN);
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-QZG
	 */
	private void editAttr(Advert _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	/**
	 * 修改AdvertVo共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-QZG
	 */
	private void editAdvertVo(AdvertVo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
