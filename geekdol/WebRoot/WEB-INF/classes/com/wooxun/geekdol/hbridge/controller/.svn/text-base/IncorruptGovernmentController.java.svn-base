package com.wooxun.geekdol.hbridge.controller;

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
import com.wooxun.geekdol.hbridge.model.IncorruptGovernment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentComment;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernmentCommentR;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentCommentRService;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentCommentService;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentService;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentRVo;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentCommentVo;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * 
* @Title
* @Description 清风气正管理
* @version 1.0.0
* @Author YK	
* @CreateDate 2016年9月8日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 YK             	2016年9月8日  下午2:06:46 		创建
*==========================================================
*
 */
@Controller
@RequestMapping("incorruptGovernment")
public class IncorruptGovernmentController extends BaseController {
	
	//private static final Logger LOG = LoggerFactory.getLogger(IncorruptGovernmentController.class);
	public static final String List = "hbridge/incorruptGovernment/list";
	public static final String INCORRUPTGOVERNMENT_ADD = "hbridge/incorruptGovernment/incorruptGovernmentAdd";
	public static final String INCORRUPTGOVERNMENT_VIEW = "hbridge/incorruptGovernment/incorruptGovernmentView";
	public static final String INCORRUPTGOVERNMENT_EDITOR = "hbridge/incorruptGovernment/incorruptGovernmentEditor";
	public static final String INCORRUPTGOVERNMENTCOMMENT_VIEW = "hbridge/incorruptGovernment/incorruptGovernmentCommentView";
	public static final String INCORRUPTGOVERNMENTCOMMENTR_VIEW = "hbridge/incorruptGovernment/commentReturnInfo";
	
	@Autowired
	private IncorruptGovernmentService<IncorruptGovernment> incorruptGovernmentService;
	@Autowired
	private IncorruptGovernmentCommentService<IncorruptGovernmentComment> commentService;
	@Autowired
	private IncorruptGovernmentCommentRService<IncorruptGovernmentCommentR> returnInfoService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title
	 * @Description 清风气正列表页面初始化
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午2:28:05
	 * @return
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return List;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 列表数据查找
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午2:28:28
	 * @param incorruptGovernmentVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(IncorruptGovernmentVo incorruptGovernmentVo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Long count = 0l;
		List<IncorruptGovernmentVo> incorruptGovernmentList = new ArrayList<IncorruptGovernmentVo>();
		// 设置查询页数，每页显示条数
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		// 设置分页表示
		incorruptGovernmentVo.setPageFlag(true);
		// 设置起始条数
		incorruptGovernmentVo.setStartPage((page - 1) * rows);
		// 设置结束条数
		incorruptGovernmentVo.setEndPage(rows);
		// 获取列表总数
		count = incorruptGovernmentService.findIncorruptGovernmentCount(incorruptGovernmentVo);
		// 获取列表数据		
		incorruptGovernmentList = incorruptGovernmentService.findIncorruptGovernment(incorruptGovernmentVo);
		// 传递总数、列表到前台
		map.put("total", count);
		map.put("rows", incorruptGovernmentList); 

		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化新增页面
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午5:14:56
	 * @return String
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/toAdd", method = { RequestMethod.GET })
	public String toAdd() {
		return INCORRUPTGOVERNMENT_ADD;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存风清气正信息
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午5:57:33
	 * @param incorruptGovernment
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value="saveIncorruptGovernment",method={RequestMethod.POST})
	public @ResponseBody String saveIncorruptGovernment(IncorruptGovernment incorruptGovernment,
		@RequestParam("file") MultipartFile[] files,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Object[] arr={"风清气正信息"};
		// 验证类别是否选择
		if(incorruptGovernment != null && StringUtils.isBlank(incorruptGovernment.getType())){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100108));
			return mapper.writeValueAsString(map);
		}
		// 附件上传并设置到对应的字段中
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				if(i==0){
					incorruptGovernment.setPic("");
				}
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				if(i==0){
					incorruptGovernment.setPic(newFileName);
				}
			}
		}
		// 增加公共属性
		this.addAttr(incorruptGovernment);
		// 保存风清气正数据
		boolean result = incorruptGovernmentService.insertIncorruptGovernment(incorruptGovernment);
		// 根据操作结果返回相应的信息
		if(result){
			// 插入系统日志
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
			return mapper.writeValueAsString(map);
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
			return mapper.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title
	 * @Description 详情信息查看
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午10:47:25
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping(value = "/views/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String views(@PathVariable Long id, Map<String, Object> map, 
			HttpServletRequest request, HttpServletResponse response){
			IncorruptGovernmentVo incorruptGovernmentVo = new IncorruptGovernmentVo();
			// 设置主键id
			incorruptGovernmentVo.setId(id);
			incorruptGovernmentVo = incorruptGovernmentService.findById(incorruptGovernmentVo);
			map.put("incorruptGovernment", incorruptGovernmentVo);
			return INCORRUPTGOVERNMENT_VIEW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化修改页面
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午10:47:36
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/toEditor/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toEditor(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			IncorruptGovernment incorruptGovernment = incorruptGovernmentService.findIncorruptGovernment(id);
			map.put("id", id);
			// 详情内容传递
			map.put("content", incorruptGovernment.getContent());
			return INCORRUPTGOVERNMENT_EDITOR;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 风清气正详情查看
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午10:54:32
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		ObjectMapper o = new ObjectMapper();
		// 清风气正详情查询
		IncorruptGovernment incorruptGovernment = incorruptGovernmentService.findIncorruptGovernment(id);
		// 时间转化显示
		incorruptGovernment.setMessageTimeStr(DateUtil.format(incorruptGovernment.getMessageTime(), "yyyy-MM-dd hh:MM:ss"));
		return o.writeValueAsString(incorruptGovernment);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 风清气正数据修改
	 * @author:YK
	 * @CreateDate:2016年9月9日 上午10:55:20
	 * @param incorruptGovernment
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/updateIncorruptGovernment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateIncorruptGovernment(IncorruptGovernment incorruptGovernment,
		@RequestParam("file") MultipartFile[] files,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"风清气正"};
		// 验证是否已经删除
		IncorruptGovernment original = incorruptGovernmentService.findIncorruptGovernment(incorruptGovernment.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 验证类别是否选择
		if(incorruptGovernment != null && StringUtils.isBlank(incorruptGovernment.getType())){
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100108));
			return o.writeValueAsString(map);
		}
		// 已经发布的数据禁止修改
		if(StringUtils.isBlank(original.getPublishStatus()) || 
				ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY.equals(original.getPublishStatus())){
			arr = addMessage(arr,"已经发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 附件更新
		String newFileName = "";
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				if(i==0){
					incorruptGovernment.setPic(original.getPic());
				}
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				if(i==0){
					incorruptGovernment.setPic(newFileName);
				}
			}
		}
		// 设置更新回数
		incorruptGovernment.setUpdEac(original.getUpdEac());
		// 公共属性设置
		this.editAttr(incorruptGovernment);
		// 更新风清气正数据
		boolean bl = incorruptGovernmentService.updateIncorruptGovernment(incorruptGovernment,null);
		if(bl){
			// 新增系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
		}else{
			// 新增系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除风清气正
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午1:47:13
	 * @param incorruptGovernment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/deleteIncorruptGovernment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteIncorruptGovernment(IncorruptGovernment incorruptGovernment,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"风清气正"};
		
		// 验证是否已经删除
		IncorruptGovernment original = incorruptGovernmentService.findIncorruptGovernment(incorruptGovernment.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 已经发布的数据禁止删除
		if(StringUtils.isBlank(original.getPublishStatus()) || 
				ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY.equals(original.getPublishStatus())){
			arr = addMessage(arr,"已经发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置删除标示
		incorruptGovernment.setDelFlag(ConstantStr.DELETE_Y);
		// 更新回数设置
		incorruptGovernment.setUpdEac(original.getUpdEac());
		// 修改公共属性
		this.editAttr(incorruptGovernment);
		boolean bl = incorruptGovernmentService.updateIncorruptGovernment(incorruptGovernment,null);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 发布
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午2:18:15
	 * @param incorruptGovernment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:publish")
	@RequestMapping(value = "/fabuIncorruptGovernment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String fabuIncorruptGovernment(IncorruptGovernment incorruptGovernment,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"风清气正"};
		
		// 验证是否已经删除
		IncorruptGovernment original = incorruptGovernmentService.findIncorruptGovernment(incorruptGovernment.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 已经发布的数据禁止发布
		if(StringUtils.isBlank(original.getPublishStatus()) || 
				ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY.equals(original.getPublishStatus())){
			arr = addMessage(arr,"已经发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置发布状态
		incorruptGovernment.setPublishStatus(ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY);
		// 设置发布人
		incorruptGovernment.setPublishPersonId(getUserId());
		// 设置发布时间
		incorruptGovernment.setPublishTime(new Date());
		// 更新回数设置
		incorruptGovernment.setUpdEac(original.getUpdEac());
		// 修改公共属性
		this.editAttr(incorruptGovernment);
		// 修改数据
		boolean bl = incorruptGovernmentService.updateIncorruptGovernment(incorruptGovernment,null);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100075,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100075,arr));
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100076), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100076,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 取消发布
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午2:37:00
	 * @param incorruptGovernment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:publish")
	@RequestMapping(value = "/quxiaofabuIncorruptGovernment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String quxiaofabuIncorruptGovernment(IncorruptGovernment incorruptGovernment,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"风清气正"};
		
		// 验证是否已经删除
		IncorruptGovernment original = incorruptGovernmentService.findIncorruptGovernment(incorruptGovernment.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 未发布的数据禁止取消发布
		if(StringUtils.isBlank(original.getPublishStatus()) || 
				!ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY.equals(original.getPublishStatus())){
			arr = addMessage(arr,"未发布");
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100071,arr));
			return o.writeValueAsString(map);
		}
		// 设置未发布状态
		incorruptGovernment.setPublishStatus(ConstantStr.INCORRUPTGOVERNMENTSTATUS_DEFAULT);
		// 更新回数设置
		incorruptGovernment.setUpdEac(original.getUpdEac());
		// 修改公共属性
		this.editAttr(incorruptGovernment);
		// 修改数据
		boolean bl = incorruptGovernmentService.updateIncorruptGovernment(incorruptGovernment,null);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100077,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100077,arr));
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100078,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT, incorruptGovernment.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100078,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化风清气正页面及其部分信息
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午3:23:06
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping(value = "/viewIncorruptGovernmentComment/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String viewGrouponComment(@PathVariable Long id,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
			// 初始化风清气正数据
			IncorruptGovernmentVo incorruptGovernmentVo = new IncorruptGovernmentVo();
			incorruptGovernmentVo.setId(id);
			incorruptGovernmentVo = incorruptGovernmentService.findById(incorruptGovernmentVo);
			map.put("incorruptGovernment", incorruptGovernmentVo);
			return INCORRUPTGOVERNMENTCOMMENT_VIEW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 评论列表查询
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午4:23:40
	 * @param commentVo
	 * @param incorruptGovernmentId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping(value = "/findCommentList/{incorruptGovernmentId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findCommentList(IncorruptGovernmentCommentVo commentVo,@PathVariable Long incorruptGovernmentId,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Long count = 0L;
		// 设置查询页数，每页显示条数
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		// 设置分页表示
		commentVo.setPageFlag(true);
		// 设置起始条数
		commentVo.setStartPage((page - 1) * rows);
		// 设置结束条数
		commentVo.setEndPage(rows);
		// 评论总数查询(总数与列表数据不可调换位置)
		count = commentService.selectCountByIncorruptGovernmentId(commentVo);
		// 获取评论列表数据
		List<IncorruptGovernmentCommentVo> list = commentService.selectByIncorruptGovernmentId(commentVo);
		for(IncorruptGovernmentCommentVo comment:list){
			// 昵称解密
			if(StringUtils.isNotBlank(comment.getNickName())){
				comment.setNickName(UnescapeUtil.unescape(comment.getNickName()));
			}
			// 内容解密
			if(StringUtils.isNotBlank(comment.getContent())){
				comment.setContent(UnescapeUtil.unescape(comment.getContent()));
			}
		}
		// 传递总数、列表到前台
		map.put("total", count);
		map.put("rows", list);
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除评论
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午6:03:03
	 * @param comment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/deleteComment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteComment(IncorruptGovernmentComment comment,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"风清气正评论"};
		
		// 验证是否已经删除
		IncorruptGovernmentComment original = commentService.findById(comment.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 设置删除标示
		comment.setDelFlag(ConstantStr.DELETE_Y);
		// 设置风清气正id、违规状态,用于更新风清气正违规量
		comment.setIncorruptGovernmentId(original.getIncorruptGovernmentId());
		comment.setIllegalStatus(original.getIllegalStatus());
		// 更新回数设置
		comment.setUpdEac(original.getUpdEac());
		// 修改公共属性
		this.editCommentAttr(comment);
		// 删除评论信息
		boolean bl = commentService.delteIncorruptGovernmentComment(comment);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT_COMMENT, comment.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT_COMMENT, comment.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 评论的回复
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午10:03:10
	 * @param incorruptGovernmentId
	 * @param commentId
	 * @param map
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping(value = "/toViewCommentReturninfo/{incorruptGovernmentId}/{commentId}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toViewCommentReturninfo(@PathVariable Long incorruptGovernmentId,@PathVariable Long commentId,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
		// 初始化风清气正数据
		IncorruptGovernmentVo incorruptGovernmentVo = new IncorruptGovernmentVo();
		incorruptGovernmentVo.setId(incorruptGovernmentId);
		incorruptGovernmentVo = incorruptGovernmentService.findById(incorruptGovernmentVo);
		// 评论内容查找
		IncorruptGovernmentCommentVo commentVo = commentService.findCommentVoById(commentId);
		// 昵称解密
		if(commentVo != null && StringUtils.isNotBlank(commentVo.getNickName())){
			commentVo.setNickName(UnescapeUtil.unescape(commentVo.getNickName()));
		}
		// 评论内容解密
		if(commentVo != null && StringUtils.isNotBlank(commentVo.getContent())){
			commentVo.setContent(UnescapeUtil.unescape(commentVo.getContent()));
		}
		map.put("incorruptGovernment", incorruptGovernmentVo);
		map.put("commentVo", commentVo);
		return INCORRUPTGOVERNMENTCOMMENTR_VIEW;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 评论的回复列表数据
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午10:53:27
	 * @param grouponCommentReturnInfoVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:view")
	@RequestMapping(value = "/findReturnInfo/{incorruptGovernmentCommentId}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findReturnInfo(IncorruptGovernmentCommentRVo returnInfoVo,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		// 设置查询页数，每页显示条数
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		Long count = 0L;
		// 设置分页表示
		returnInfoVo.setPageFlag(true);
		// 设置起始条数
		returnInfoVo.setStartPage((page - 1) * rows);
		// 设置结束条数
		returnInfoVo.setEndPage(rows);
		// 获取评论回复总数(总数与列表查询不能更改位置)
		count = returnInfoService.queryCountByParams(returnInfoVo);
		// 查找评论的回复列表数据
		List<IncorruptGovernmentCommentRVo> returnInfolist = returnInfoService.queryByParams(returnInfoVo);
		for(IncorruptGovernmentCommentRVo commentR:returnInfolist){
			// 昵称解密
			if(StringUtils.isNotBlank(commentR.getNickName())){
				commentR.setNickName(UnescapeUtil.unescape(commentR.getNickName()));
			}
			// 内容解密
			if(StringUtils.isNotBlank(commentR.getContent())){
				commentR.setContent(UnescapeUtil.unescape(commentR.getContent()));
			}
		}
		// 传递总数、列表到前台
		map.put("total", count);
		map.put("rows", returnInfolist);
		
		return o.writeValueAsString(map);					
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除评论的回复
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午11:40:03
	 * @param returnInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("IncorruptGovernment:manager")
	@RequestMapping(value = "/destroyReturnInfo", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String destroyReturnInfo(IncorruptGovernmentCommentR returnInfo,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"风清气正评论回复"};
		// 验证是否已经删除
		IncorruptGovernmentCommentR original = returnInfoService.findById(returnInfo.getId());
		if(original == null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 设置删除标志、更新回数、公共属性修改
		returnInfo.setDelFlag(ConstantStr.DELETE_Y);
		returnInfo.setUpdEac(original.getUpdEac());
		this.editAttrReturnInfo(returnInfo);
		// 更新回复信息
		boolean bl = returnInfoService.deleteCommentReturnInfo(returnInfo);
		if(bl){
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT_COMMENT_R, returnInfo.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
		}else{
			// 插入系统日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100004,arr), 
					ConstantStr.M_INCORRUPT_GOVERNMENT_COMMENT_R, returnInfo.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改评论的回复公共属性信息
	 * @author:YK
	 * @CreateDate:2016年9月10日 上午11:48:17
	 * @param _temp
	 */
	private void editAttrReturnInfo(IncorruptGovernmentCommentR _temp) {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
	/**
	 * 
	 * @Title
	 * @Description 新增公共属性
	 * @author:YK
	 * @CreateDate:2016年9月8日 下午5:56:28
	 * @param _temp
	 */
	private void addAttr(IncorruptGovernment _temp) {
		Long userId = getUserId();
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
	private void editAttr(IncorruptGovernment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 评论公共属性修改
	 * @author:YK
	 * @CreateDate:2016年9月9日 下午6:08:01
	 * @param _temp
	 */
	private void editCommentAttr(IncorruptGovernmentComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 提示消息添加
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午3:58:11
	 * @param original
	 * @param message
	 * @return
	 */
	private Object[] addMessage(Object[] original,String message){
		List<Object> messageList = new ArrayList<Object>();
		for(Object obj:original){
			messageList.add(obj);
		}
		messageList.add(message);
		return messageList.toArray();
	}
}
