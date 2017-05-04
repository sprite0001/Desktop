package com.wooxun.geekdol.hbridge.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hbridge.model.NetizenSecurity;
import com.wooxun.geekdol.hbridge.service.NetizenSecurityService;
import com.wooxun.geekdol.hbridge.vo.NetizenSecurityVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description 网安报Controller
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年9月8日  上午10:50:25 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("netizenSecurity")
public class NetizenSecurityController extends BaseController {
	
	/** 一览界面返回路径 */
	private static final String NS_LIST = "hbridge/netizenSecurity/list";
	
	/** 新增界面返回路径 */
	private static final String NS_TOADD = "hbridge/netizenSecurity/netizenSecurityAdd";
	
	/** 修改界面返回路径 */
	private static final String NS_TOEDIT = "hbridge/netizenSecurity/netizenSecurityEdit";
	
	/** 详情界面返回路径 */
	private static final String NS_TOVIEW = "hbridge/netizenSecurity/netizenSecurityView";
	
	@Autowired
	private NetizenSecurityService<NetizenSecurity> netizenSecurityService;
	
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	@InitBinder  
    private void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
	/**
	 * @Title
	 * @Description 跳转至列表界面
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午10:58:51
	 * @return
	 */
	@RequiresPermissions("NetizenSecurity:view")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
        return NS_LIST;
    }
	
	/**
	 * @Title
	 * @Description 网安报列表信息初始化
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午2:21:25
	 * @param netizenSecurityVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(NetizenSecurityVo netizenSecurityVo, 
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));
		
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建网安报列表变量用于存放网安报信息
		List<NetizenSecurityVo> netizenSecurityList = new ArrayList<NetizenSecurityVo>();
		// 创建记录数变量用于存放网安报总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		netizenSecurityVo.setPageFlag(true);
		netizenSecurityVo.setStartPage((page - 1) * rows);
		netizenSecurityVo.setEndPage(rows);
		
		// 调用service方法获取网安报总记录数
		count = netizenSecurityService.findNetizenSecurityCountByParam(netizenSecurityVo);
		// 调用service方法获取网安报分页后列表
		netizenSecurityList = netizenSecurityService.findNetizenSecurityListByParam(netizenSecurityVo);

		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", netizenSecurityList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper om = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return om.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 跳转至新增界面
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午10:59:04
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("NetizenSecurity:manager")
	@RequestMapping(value = "/toAddNetizenSecurity", method = { RequestMethod.GET })
	public String toAddNetizenSecurity(Model model, HttpServletRequest request) {
		// 设置令牌 防止重复提交
		setToken(model, request);
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
		return NS_TOADD;
	}
	
	/**
	 * @Title
	 * @Description 保存网安报信息
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午5:13:01
	 * @param netizenSecurityVo
	 * @param publishStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:manager")
	@RequestMapping(value="/saveNetizenSecurity/{publishStatus}",method={RequestMethod.POST})
	public @ResponseBody String saveNetizenSecurity(NetizenSecurityVo netizenSecurityVo, 
			@RequestParam("file") MultipartFile file, @PathVariable String publishStatus, 
			HttpServletRequest request) throws Exception{
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper om = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attribute={"网安报"};
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, attribute));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, attribute, ConstantStr.M_NETIZEN_SECURITY, null);
			return om.writeValueAsString(map);
		}
		// 判断当前文件是否被上传
		if(file.getSize() <= 0){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100109,attribute));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return om.writeValueAsString(map);
		}
		// 获得文件后缀名
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		// 判断文件类型
		if (!ConstantStr.PIC_TYPE_BMP.equals(suffix.toLowerCase()) 
				&& !ConstantStr.PIC_TYPE_GIF.equals(suffix.toLowerCase()) 
				&& !ConstantStr.PIC_TYPE_JPEG.equals(suffix.toLowerCase()) 
				&& !ConstantStr.PIC_TYPE_JPG.equals(suffix.toLowerCase()) 
				&& !ConstantStr.PIC_TYPE_PNG.equals(suffix.toLowerCase()) ) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100109,attribute));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return om.writeValueAsString(map);
		}
		/* 从共通文件中获取上传文件所需信息 */
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
		// 生成新的文件名
		String newFileName = UUID.randomUUID() + suffix;
		// 获取上传文件结果
		boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
		if(rs){ // 如果文件上传成功
			
			// 获取文件上传后的相对路径
			newFileName = httppath+newFileName;
			// 设置网安报缩略图
			netizenSecurityVo.setPicture(newFileName);
		}
		
		// 设置网安报发布状态
		netizenSecurityVo.setPublishStatus(publishStatus);
		if (ConstantStr.FB.equals(publishStatus)) { // 如果是发布状态
			// 设置网安报发布人id
			netizenSecurityVo.setPublishPersonId(getUserId());
			// 设置网安报发布时间
			netizenSecurityVo.setPublishTime(new Date());
		}
		// 向Vo中设置相关共通变量的数据
		this.addAttr(netizenSecurityVo);
		
		// 调用service方法对网安报信息进行保存，并返回保存结果
		boolean result = netizenSecurityService.saveNetizenSecurity(netizenSecurityVo);
		
		if(result){ // 如果保存成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attribute), ConstantStr.M_NETIZEN_SECURITY, netizenSecurityVo.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			if(ConstantStr.WFB.equals(publishStatus)){
				map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,attribute));
			}else{
				map.put("msg",ComDefine.getMsg(ConstantStr.INFO100075,attribute));
			}
		}else{ // 如果保存失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attribute), ConstantStr.M_NETIZEN_SECURITY, netizenSecurityVo.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			if(ConstantStr.FB.equals(publishStatus)){
				map.put("msg",ComDefine.getMsg(ConstantStr.INFO100002,attribute));
			}else{
				map.put("msg",ComDefine.getMsg(ConstantStr.INFO100076,attribute));
			}
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return om.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 跳转至修改界面
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 上午11:07:55
	 * @param id
	 * @param map
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("NetizenSecurity:manager")
	@RequestMapping(value = "/toEditorNetizenSecurity/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toEditorNetizenSecurity(@PathVariable Long id, Map<String, Object> map, 
			Model model, HttpServletRequest request) throws Exception {
		// 将网安报行记录的id封装在map中写入界面
		map.put("id", id);
		if (id != null) { // 如果id不为空
			NetizenSecurity netizenSecurity = netizenSecurityService.get(id);
			map.put("content", netizenSecurity.getContent());
		}
		// 设置令牌 防止重复提交
		setToken(model, request);
		// 其它信息进入页面后由页面请求数据
		return NS_TOEDIT;
	}
	
	/**
	 * @Title
	 * @Description 初始化网安报修改界面数据
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午2:44:30
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:manager")
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id) throws Exception {
		// 调用service方法根据网安报id获取网安报相关信息
		NetizenSecurityVo netizenSecurityVo = netizenSecurityService.getNetizenSecurityVo(id);
		// 设置网安报时间字符串
		netizenSecurityVo.setNewsTimeStr(DateUtil.format(netizenSecurityVo.getNewsTime(), "yyyy-MM-dd HH:mm:ss"));
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper om = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return om.writeValueAsString(netizenSecurityVo);
	}
	
	/**
	 * @Title
	 * @Description 更新网安报信息
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午2:59:00
	 * @param netizenSecurityVo
	 * @param publishStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:manager")
	@RequestMapping(value = "/updateNetizenSecurity/{publishStatus}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateNetizenSecurity(NetizenSecurityVo netizenSecurityVo, 
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable String publishStatus, 
			HttpServletRequest request) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper om = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attribute={"网安报"};

		// 调用service方法根据网安报id获取网安报相关信息用于判断是否已经删除
		NetizenSecurity original = netizenSecurityService.get(netizenSecurityVo.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,attribute));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return om.writeValueAsString(map);
		}
		if (!verifyToken(request)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105, attribute));
			insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, attribute, ConstantStr.M_NETIZEN_SECURITY, null);
			return om.writeValueAsString(map);
		}
		// 如果修改了文件
		if (file.getSize() > 0) {
			// 获得文件后缀名
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			// 判断文件类型
			if (!ConstantStr.PIC_TYPE_BMP.equals(suffix.toLowerCase()) 
					&& !ConstantStr.PIC_TYPE_GIF.equals(suffix.toLowerCase()) 
					&& !ConstantStr.PIC_TYPE_JPEG.equals(suffix.toLowerCase()) 
					&& !ConstantStr.PIC_TYPE_JPG.equals(suffix.toLowerCase()) 
					&& !ConstantStr.PIC_TYPE_PNG.equals(suffix.toLowerCase()) ) {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100109,attribute));
				// 将Map变量转变为json变量写入界面由easyui进行操作json
				return om.writeValueAsString(map);
			}
			/* 从共通文件中获取上传文件所需信息 */
			String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
			String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HBRIDGE);
			// 生成新的文件名
			String newFileName = UUID.randomUUID() + suffix;
			// 获取上传文件结果
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){ // 如果文件上传成功
				
				// 获取文件上传后的相对路径
				newFileName = httppath+newFileName;
				// 设置网安报缩略图
				netizenSecurityVo.setPicture(newFileName);
			}
		}
		
		// 设置网安报时间
		netizenSecurityVo.setNewsTime(DateUtil.parseDate(netizenSecurityVo.getNewsTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		// 设置网安报发布状态
		netizenSecurityVo.setPublishStatus(publishStatus);
		if (ConstantStr.FB.equals(publishStatus)) { // 如果是发布状态
			// 设置网安报发布人id
			netizenSecurityVo.setPublishPersonId(getUserId());
			// 设置网安报发布时间
			netizenSecurityVo.setPublishTime(new Date());
		}
		// 设置网安报的更新回数
		netizenSecurityVo.setUpdEac(original.getUpdEac());
		// 更新Vo中相关共通变量的数据
		this.editAttr(netizenSecurityVo);
		
		// 调用service方法对网安报信息进行更新，并返回更新结果
		boolean result = netizenSecurityService.updateNetizenSecurity(netizenSecurityVo);
		if(result){ // 如果更新成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attribute), ConstantStr.M_NETIZEN_SECURITY, netizenSecurityVo.getId(), getUser());
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,attribute));
		}else{ // 如果更新失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attribute), ConstantStr.M_NETIZEN_SECURITY, netizenSecurityVo.getId(), getUser());
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,attribute));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return om.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 获取网安报详情数据并跳转至详情界面
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午2:47:14
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:view")
	@RequestMapping(value = "/toViewNetizenSecurity/{id}", method = { RequestMethod.GET })
	public String toViewNetizenSecurity(@PathVariable Long id, Map<String, Object> map) {
		// 调用service方法根据网安报id获取网安报相关信息
		NetizenSecurityVo netizenSecurityVo = netizenSecurityService.getNetizenSecurityVo(id);
		// 设置网安报时间字符串
		netizenSecurityVo.setNewsTimeStr(DateUtil.format(netizenSecurityVo.getNewsTime(), "yyyy-MM-dd HH:mm:ss"));
		// 将网安报信息放入map中
		map.put("netizenSecurityVo", netizenSecurityVo);
		// 跳转至查看界面
		return NS_TOVIEW;
	}
	
	/**
	 * @Title
	 * @Description 删除网安报数据
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午3:01:56
	 * @param netizenSecurity
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:manager")
	@RequestMapping(value = "/deleteNetizenSecurity", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteNetizenSecurity(NetizenSecurity netizenSecurity) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper om = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attribute={"网安报"};
		
		// 调用service方法根据网安报id获取网安报相关信息用于判断是否已经删除
		NetizenSecurity original = netizenSecurityService.get(netizenSecurity.getId());
		if(original == null){//验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,attribute));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return om.writeValueAsString(map);
		}
		
		// 设置更新回数
		netizenSecurity.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(netizenSecurity);
		
		// 调用service方法对周边店信息进行保删除，并返回删除结果
		int resultCount = netizenSecurityService.updateSelective(netizenSecurity);
		
		if(resultCount > 0){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,attribute), ConstantStr.M_NETIZEN_SECURITY, netizenSecurity.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,attribute));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004,attribute), ConstantStr.M_NETIZEN_SECURITY, netizenSecurity.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,attribute));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return om.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 更新网安报发布状态
	 * @author:kangtianyu
	 * @CreateDate:2016年9月8日 下午3:02:15
	 * @param publishStatus
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("NetizenSecurity:publish")
	@RequestMapping(value = "/updateNetizenSecurityStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateNetizenSecurityStatus(String publishStatus, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper om = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attribute={"网安报"};
		
		// 调用service方法根据网安报id获取网安报相关信息用于判断是否已经删除
		NetizenSecurity original = netizenSecurityService.get(id);
		if(original == null){//验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,attribute));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return om.writeValueAsString(map);
		}
		
		// 创建网安报对象用于存放更新数据
		NetizenSecurity netizenSecurity = new NetizenSecurity();
		// 设置网安报的主键id
		netizenSecurity.setId(id);
		// 设置网安报的发布状态
		netizenSecurity.setPublishStatus(publishStatus);
		// 如果是取消发布
		if (ConstantStr.WFB.equals(publishStatus)) {
			// 设置发布人为空
			netizenSecurity.setPublishPersonId(null);
			// 设置发布时间为空
			netizenSecurity.setPublishTime(null);
		} else {
			// 设置发布人
			netizenSecurity.setPublishPersonId(getUserId());
			// 设置发布时间
			netizenSecurity.setPublishTime(new Date());
		}
		// 设置网安报的更新回数
		netizenSecurity.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(netizenSecurity);
		
		// 调用service方法对周边店信息进行保删除，并返回删除结果
		int resultCount = netizenSecurityService.updateNetizenSecurityAnyParam(netizenSecurity);
		
		if(resultCount > 0){ // 如果更新成功
			
			// 如果是取消发布
			if (ConstantStr.WFB.equals(publishStatus)) {
				// 调用系统日志的service方法写入成功信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100077,attribute), ConstantStr.M_NETIZEN_SECURITY, id, getUser());
				/* 向map中封装成功信息 */
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100077,attribute));
			} else {
				// 调用系统日志的service方法写入成功信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100075,attribute), ConstantStr.M_NETIZEN_SECURITY, id, getUser());
				/* 向map中封装成功信息 */
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100075,attribute));
			}
		}else{ // 如果更新失败
			// 如果是取消发布
			if (ConstantStr.WFB.equals(publishStatus)) {
				// 调用系统日志的service方法写入失败信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100078,attribute), ConstantStr.M_NETIZEN_SECURITY, id, getUser());
				/* 向map中封装失败信息 */
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100078,attribute));
			} else {
				// 调用系统日志的service方法写入失败信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100076,attribute), ConstantStr.M_NETIZEN_SECURITY, id, getUser());
				/* 向map中封装失败信息 */
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076,attribute));
			}
			
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return om.writeValueAsString(map);
	}
	
	private void addAttr(NetizenSecurityVo _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private void editAttr(NetizenSecurityVo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(NetizenSecurity _temp) {
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
