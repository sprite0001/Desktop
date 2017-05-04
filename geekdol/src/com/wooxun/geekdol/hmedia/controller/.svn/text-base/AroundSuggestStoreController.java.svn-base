package com.wooxun.geekdol.hmedia.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreCommentReply;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStorePromotion;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.hmedia.service.AroundStoreService;
import com.wooxun.geekdol.hmedia.service.AroundSuggestStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStorePromotionVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.VillageService;

/**
* @Title
* @Description 本域推荐周边店Controller
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月3日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月3日  下午11:29:15 		创建
*==========================================================
*
 */
@Controller
@RequestMapping("aroundSuggestStore")
public class AroundSuggestStoreController extends BaseController {
	
	/** 一览界面返回路径 */
	private static final String AROUND_SUGGEST_LIST = "hmedia/aroundSuggestStore/list";
	
	/** 新增界面返回路径 */
	private static final String AROUND_SUGGEST_TOADD = "hmedia/aroundSuggestStore/aroundsuggeststoreAdd";
	
	/** 修改界面返回路径 */
	private static final String AROUND_SUGGEST_TOEDIT = "hmedia/aroundSuggestStore/aroundsuggeststoreEdit";
	
	/** 小区选择界面 */
	public static final String VILLAGE_SELECT = "hmedia/aroundSuggestStore/villageSelect";
	
	/** 回复管理界面返回路径 */
	private static final String AROUND_SUGGEST_COMMENT = "hmedia/aroundSuggestStore/aroundsuggeststoreComment";
	
	/** 回复管理的回复管理界面返回路径 */
	private static final String AROUND_SUGGEST_COMMENT_REPLY = "hmedia/aroundSuggestStore/aroundsuggeststoreCommentReply";
	
	/** 再次推荐界面返回路径 */
	private static final String AROUND_SUGGEST_AGAIN = "hmedia/aroundSuggestStore/aroundSuggest";
	
	/** 本区推荐界面返回路径 */
	private static final String COUNTY_SUGGEST = "hmedia/aroundSuggestStore/countySuggest";
	
	/** 促销管理一览界面返回路径 */
	private static final String SALES_LIST = "hmedia/aroundSuggestStore/salesList";
	
	/** 促销管理新增界面返回路径 */
	private static final String SALES_ADD = "hmedia/aroundSuggestStore/salesAdd";
	
	/** 促销管理新增界面返回路径 */
	private static final String SALES_EDIT = "hmedia/aroundSuggestStore/salesEdit";
	
	@Autowired
	private AroundSuggestStoreService<AroundSuggestStore> aroundSuggestStoreService;
	
	@Autowired
	private AroundStoreService<AroundStore> aroundStoreService;
	
	@Autowired
	private VillageService<Village> villageService;
	
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	@Autowired
	private CommunityService<Community> communityService;
	
	/**
	 * 本网格推荐周边店管理：初始化界面
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月28日 下午5:45:12
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:view")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
        return AROUND_SUGGEST_LIST;
    }
	
	/**
	 * 本网格推荐周边店管理：本网格推荐周边店信息初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 上午9:52:55
	 * @param AroundSuggestStoreVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(AroundSuggestStoreVo aroundSuggestStoreVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建本网格推荐周边店列表变量用于存放本网格推荐周边店信息
		List<AroundSuggestStoreVo> aroundSuggestStoreList = new ArrayList<AroundSuggestStoreVo>();
		// 创建记录数变量用于存放本网格推荐周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundSuggestStoreVo.setPageFlag(true);
		aroundSuggestStoreVo.setStartPage((page - 1) * rows);
		aroundSuggestStoreVo.setEndPage(rows);
		
		// 调用service方法查找当前用户所能查看的周边店的id
		List<AroundSuggestStoreVillage> assvList = aroundSuggestStoreService.findAroundSuggestStoreIdByUser(getUserId());
		
		if (assvList != null && assvList.size() > 0) { // 如果有可查看的
			// 创建Map对象封装sql查询参数
			Map<String, Object> param = new HashMap<String, Object>();
			/* 向map中放入参数 */
			param.put("aroundSuggestStoreVo", aroundSuggestStoreVo);
			param.put("storeType", ConstantStr.STORETYPE);
			param.put("assvList", assvList);
			
			// 调用service方法获取本网格推荐周边店总记录数
			count = aroundSuggestStoreService.findAroundSuggestStoreListCount(param);
			// 调用service方法获取本网格推荐周边店分页后列表
			aroundSuggestStoreList = aroundSuggestStoreService.findAroundSuggestStoreList(param);
		}
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundSuggestStoreList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：新增界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:15:35
	 * @param model
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toAddAroundSuggestStore", method = { RequestMethod.GET })
	public String toAddAroundSuggestStore(Model model) {
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
		return AROUND_SUGGEST_TOADD;
	}
	
	/**
	 * 本网格推荐周边店管理：推荐小区界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:16:01
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/initVillagePage/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String initVillagePage(@PathVariable Long id, Map<String, Object> map) {
		// 将本网格推荐周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 其它信息进入页面后由页面请求数据
		return VILLAGE_SELECT;
	}
	
	/**
	 * 本网格推荐周边店管理：获得还未推荐的小区
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:30:55
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/queryVillageNoSuggest/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryVillageNoSuggest(@PathVariable Long id) throws Exception {
		// 创建小区列表变量用于存放小区信息
		List<Village> villageList = new ArrayList<Village>();
		// 创建本网格推荐周边店小区对应关系列表变量用于存放本网格推荐周边店小区对应关系信息
		List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList = new ArrayList<AroundSuggestStoreVillage>();
		
		// id为0时表示是新增操作，不需要查询已经所属的小区
		if (id != 0) {
			// 调用service方法通过本网格推荐周边店id查找已经所属的小区
			aroundSuggestStoreVillageList = aroundSuggestStoreService.findAroundSuggestStoreVillage(id);
		}
		// 调用service方法查找当前本网格推荐周边店不属于的小区（去除已经所属的小区）
		villageList = villageService.findVillageNoSuggest(aroundSuggestStoreVillageList, getUserId());
		
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(villageList);
	}
	
	/**
	 * 本网格推荐周边店管理：获得已推荐的小区
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:31:21
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/queryVillageSuggest/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryVillageSuggest(@PathVariable Long id) throws Exception {
		// 创建小区列表变量用于存放小区信息
		List<Village> villageList = new ArrayList<Village>();
		// 创建本网格推荐周边店小区对应关系列表变量用于存放本网格推荐周边店小区对应关系信息
		List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList = new ArrayList<AroundSuggestStoreVillage>();
		
		// id为0时表示是新增操作，不需要查询已经所属的小区
		if (id != 0) {
			// 调用service方法通过本网格推荐周边店id查找已经所属的小区
			aroundSuggestStoreVillageList = aroundSuggestStoreService.findAroundSuggestStoreVillage(id);
		}
		
		// 调用service方法查找当前本网格推荐周边店属于的小区
		villageList = villageService.findVillageHasSuggest(aroundSuggestStoreVillageList);
		
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(villageList);
	}
	
	/**
	 * 本网格推荐周边店管理：保存本网格推荐周边店信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:16:21
	 * @param aroundSuggestStoreVo
	 * @param files
	 * @param propagandafiles
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value="/saveAroundSuggestStore",method={RequestMethod.POST})
	public @ResponseBody String saveAroundSuggestStore(AroundSuggestStoreVo aroundSuggestStoreVo, @RequestParam("file") MultipartFile[] files, 
			@RequestParam("propagandafile") MultipartFile[] propagandafiles) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		/* 创建信息参数补充对象数组用于信息化参数的补充 */
		Object[] attributeAss={"本网格推荐周边店"};
		// 调用service方法判断小区是否达到本网格推荐店最大值
		Map<String, Object> resultCount = aroundSuggestStoreService.judgeAroundSuggestIsMax(aroundSuggestStoreVo);
		if ((boolean) resultCount.get("isMax")) { // 如果达到最大值
			// 创建信息参数补充对象数组用于信息化参数的补充
			Object[] villageName = {(String) resultCount.get("villageName")};
			/* 向map中封装成功信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100067, villageName));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return mapper.writeValueAsString(map);
		}
		
		/* 从共通文件中获取上传文件所需信息 */
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HMEDIA);
		// 遍历上传的文件
		for(int i=0; i<files.length; i++){
			// 创建文件对象用于遍历的文件操作
			MultipartFile file = files[i];
			// 判断当前文件是否被上传
			if(file.getSize()<=0){
				if(i == 0){ // 如果第一个文件没有上传
					
					// 设置相关属性为空字符串
					aroundSuggestStoreVo.setIcon("");
				}
				if(i == 1){ // 如果第二个文件没有上传
					
					// 设置相关属性为空字符串
					aroundSuggestStoreVo.setStoreImage("");
				}
				if(i == 2){ // 如果第三个文件没有上传
					
					// 设置相关属性为空字符串
					aroundSuggestStoreVo.setLicenseImage("");
				}
				// 进入下次循环
				continue;
			}
			// 生成新的文件名
			String newFileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			// 上传文件
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){ // 如果文件上传成功
				
				// 获取文件上传后的相对路径
				newFileName = httppath+newFileName;
				/* 对相应的属性进行设值 */
				if(i == 0){
					aroundSuggestStoreVo.setIcon(newFileName);
				}
				if(i == 1){
					aroundSuggestStoreVo.setStoreImage(newFileName);
				}
				if(i == 2){
					aroundSuggestStoreVo.setLicenseImage(newFileName);
				}
			}
		}
		// 设置本网格推荐周边店的推荐状态
		aroundSuggestStoreVo.setSuggestFlag(ConstantStr.SUGGEST_FLAG_1);
		// 更新Vo中相关共通变量的数据
		this.addAttr(aroundSuggestStoreVo);
		// 调用service方法对本网格推荐周边店信息进行保存，并返回保存结果
		boolean result = aroundSuggestStoreService.saveAroundSuggestStore(aroundSuggestStoreVo, propagandafiles);
		if(result){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStoreVo.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAss));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStoreVo.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAss));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：修改界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:46:03
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toEditorAroundSuggestStore/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toEditorAroundSuggestStore(@PathVariable Long id, Map<String, Object> map) {
		// 将本网格推荐周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 创建附件Model变量用于封装sql查询参数
		Attach attach = new Attach();
		// 设置附件类型
		attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
		// 设置源id
		attach.setOwnerId(id);
		// 设置源表名
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
		// 调用service方法根据本网格推荐周边店id获取附件信息
		List<Attach> attchList = aroundSuggestStoreService.findAttachByParam(attach);
		// 将本网格推荐周边店详细信息封装在map中写入界面
		map.put("attchList", attchList);
		// 将附件的长度封装在map中写入界面
		map.put("attachCount", attchList.size());
		// 其它信息进入页面后由页面请求数据
		return AROUND_SUGGEST_TOEDIT;
	}
	
	/**
	 * 本网格推荐周边店管理：根据主键id查找本网格推荐周边店详细信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月4日 下午10:46:34
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id) throws Exception {
		// 调用service方法根据本网格推荐周边店id获取详细信息
		AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreService.findAroundSuggestStore(id);
		// 设置推荐开始时间字符串
		aroundSuggestStoreVo.setSuggestStartStr(DateUtil.format(aroundSuggestStoreVo.getSuggestStart(), "yyyy-MM-dd HH:mm:ss"));
		// 设置推荐结束时间字符串
		aroundSuggestStoreVo.setSuggestEndStr(DateUtil.format(aroundSuggestStoreVo.getSuggestEnd(), "yyyy-MM-dd HH:mm:ss"));
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将本网格推荐周边店的信息转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(aroundSuggestStoreVo);
	}
	
	/**
	 * 本网格推荐周边店管理：更新本网格推荐周边店信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午12:51:57
	 * @param aroundSuggestStoreVo
	 * @param files
	 * @param propagandafiles
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value="/updateAroundSuggestStore",method={RequestMethod.POST})
	public @ResponseBody String updateAroundSuggestStore(AroundSuggestStoreVo aroundSuggestStoreVo, @RequestParam("file") MultipartFile[] files, 
			@RequestParam("propagandafile") MultipartFile[] propagandafiles, 
			@RequestParam(value="attchfiles", required=false) String[] attchfiles) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		/* 创建信息参数补充对象数组用于信息化参数的补充 */
		Object[] attributeAss={"本网格推荐周边店"};
		
		// 调用service方法判断小区是否达到本网格推荐店最大值
		Map<String, Object> resultCount = aroundSuggestStoreService.judgeAroundSuggestIsMax(aroundSuggestStoreVo);
		if ((boolean) resultCount.get("isMax")) { // 如果达到最大值
			// 创建信息参数补充对象数组用于信息化参数的补充
			Object[] villageName = {(String) resultCount.get("villageName")};
			/* 向map中封装成功信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100067, villageName));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return mapper.writeValueAsString(map);
		}
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息用于判断是否已经删除
		AroundSuggestStore original = aroundSuggestStoreService.get(aroundSuggestStoreVo.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAss));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return mapper.writeValueAsString(map);
		}
		
		/* 从共通文件中获取上传文件所需信息 */
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HMEDIA);
		// 遍历上传的文件
		for(int i=0; i<files.length; i++){
			// 创建文件对象用于遍历的文件操作
			MultipartFile file = files[i];
			// 判断当前文件是否被上传
			if(file.getSize() <= 0){
				// 进入下次循环
				continue;
			}
			// 生成新的文件名
			String newFileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			// 上传文件
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){ // 如果文件上传成功
				
				// 获取文件上传后的相对路径
				newFileName = httppath+newFileName;
				/* 对相应的属性进行设值 */
				if(i == 0){
					aroundSuggestStoreVo.setIcon(newFileName);
				}
				if(i == 1){
					aroundSuggestStoreVo.setStoreImage(newFileName);
				}
				if(i == 2){
					aroundSuggestStoreVo.setLicenseImage(newFileName);
				}
			}
		}
		
		// 设置推荐开始时间
		aroundSuggestStoreVo.setSuggestStart(DateUtil.parseDate(aroundSuggestStoreVo.getSuggestStartStr(), "yyyy-MM-dd HH:mm:ss"));
		// 设置推荐结束时间
		aroundSuggestStoreVo.setSuggestEnd(DateUtil.parseDate(aroundSuggestStoreVo.getSuggestEndStr(), "yyyy-MM-dd HH:mm:ss"));
		// 设置本网格推荐周边店的更新回数
		aroundSuggestStoreVo.setUpdEac(original.getUpdEac());
		// 更新Vo中相关共通变量的数据
		this.editAttr(aroundSuggestStoreVo);
		// 调用service方法对本网格推荐周边店信息进行保存，并返回保存结果
		boolean result = aroundSuggestStoreService.updateAroundSuggestStore(aroundSuggestStoreVo, propagandafiles, attchfiles);
		if(result){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStoreVo.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, attributeAss));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStoreVo.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, attributeAss));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：删除本网格推荐周边店信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:34:00
	 * @param aroundSuggestStore
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/deleteAroundSuggestStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteAroundSuggestStore(AroundSuggestStore aroundSuggestStore) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAss={"本网格推荐周边店"};
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息用于判断是否已经删除
		AroundSuggestStore original = aroundSuggestStoreService.get(aroundSuggestStore.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAss));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置更新回数
		aroundSuggestStore.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStore);
		
		// 调用service方法对本网格推荐周边店信息进行保删除，并返回删除结果
		boolean bl = aroundSuggestStoreService.deleteAroundSuggestStore(aroundSuggestStore);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStore.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,attributeAss));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStore.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,attributeAss));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：更改本网格推荐周边店推荐状态
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:35:43
	 * @param suggestFlag
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/updateAroundSuggestStoreStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateAroundSuggestStoreStatus(String suggestFlag, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAss={"本网格推荐周边店"};
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息用于判断是否已经删除
		AroundSuggestStore original = aroundSuggestStoreService.get(id);
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAss));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 创建本网格推荐周边店Model对象用于存放更新数据
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 设置本网格推荐周边店的主键id
		aroundSuggestStore.setId(id);
		// 设置本网格推荐周边店的状态
		aroundSuggestStore.setSuggestFlag(suggestFlag);
		// 设置本网格推荐周边店的更新回数
		aroundSuggestStore.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStore);
		
		// 调用service方法对本网格推荐周边店信息进行更新，并返回更新结果
		int i = aroundSuggestStoreService.updateSelective(aroundSuggestStore);
		
		if(i >= 0){ // 如果更新成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStore.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,attributeAss));
		}else{ // 如果更新失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100054,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStore.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,attributeAss));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：评论管理界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:05:36
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toCommentASS/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toCommentASS(@PathVariable Long id, Map<String, Object> map) {
		// 调用service方法根据主键id获取本网格推荐周边店相关信息
		AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreService.findAroundSuggestStore(id);
		
		// 将本网格推荐周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 将本网格推荐周边店信息封装在map中写入界面
		map.put("aroundSuggestStoreVo", aroundSuggestStoreVo);
		
		// 其它信息进入页面后由页面请求数据
		return AROUND_SUGGEST_COMMENT;
	}
	
	/**
	 * 本网格推荐周边店管理：评论管理界面信息分页数据获取
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:53:22
	 * @param aroundSuggestStoreCommentVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping("/findCommentAll")
	public @ResponseBody String findCommentAll(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建本网格推荐周边店评论列表变量用于存放本网格推荐周边店评论信息
		List<AroundSuggestStoreCommentVo> aroundSuggestStoreCommentList = new ArrayList<AroundSuggestStoreCommentVo>();
		// 创建记录数变量用于存放本网格推荐周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundSuggestStoreCommentVo.setPageFlag(true);
		aroundSuggestStoreCommentVo.setStartPage((page - 1) * rows);
		aroundSuggestStoreCommentVo.setEndPage(rows);
		
		// 调用service方法获取本网格推荐周边店评论总记录数
		count = aroundSuggestStoreService.findAroundSuggestStoreCommentListCount(aroundSuggestStoreCommentVo);
		// 调用service方法获取本网格推荐周边店评论分页后列表
		aroundSuggestStoreCommentList = aroundSuggestStoreService.findAroundSuggestStoreCommentList(aroundSuggestStoreCommentVo);
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundSuggestStoreCommentList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：删除本网格推荐周边店评论的信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午10:53:41
	 * @param aroundSuggestStoreComment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/deleteComment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteComment(AroundSuggestStoreComment aroundSuggestStoreComment) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAssc={"本网格推荐周边店回复"};
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息用于判断是否已经删除
		AroundSuggestStoreComment original = aroundSuggestStoreService.findAroundSuggestStoreComment(aroundSuggestStoreComment.getId());
		if(original == null){//验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAssc));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置本网格推荐周边店回复的更新回数
		aroundSuggestStoreComment.setUpdEac(original.getUpdEac());
		// 设置推荐店id
		aroundSuggestStoreComment.setAroundSuggestStoreId(original.getAroundSuggestStoreId());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStoreComment);
		
		// 调用service方法对本网格推荐周边店的评论信息进行删除，并返回删除结果
		boolean bl = aroundSuggestStoreService.deleteAroundSuggestStoreComment(aroundSuggestStoreComment);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,attributeAssc), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT, aroundSuggestStoreComment.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,attributeAssc));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004,attributeAssc), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT, aroundSuggestStoreComment.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,attributeAssc));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：本网格推荐周边店回复的回复界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:18:57
	 * @param id
	 * @param aroundSuggestStoreId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toReplyComment/{id}/{aroundSuggestStoreId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toReplyComment(@PathVariable Long id, @PathVariable Long aroundSuggestStoreId, Map<String, Object> map) {
		// 将本网格推荐周边店回复的行记录的id封装在map中写入界面
		map.put("aroundSuggestStoreCommentId", id);
		
		// 将周边店的行记录的id封装在map中写入界面
		map.put("aroundSuggestStoreId", aroundSuggestStoreId);
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息
		AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreService.findAroundSuggestStore(aroundSuggestStoreId);
		// 调用service方法根据主键id获取本网格推荐周边店的评论相关信息
		AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo = aroundSuggestStoreService.findAroundSuggestStoreCommentDetail(id);
		
		// 将本网格推荐周边店的信息封装在map中写入界面
		map.put("aroundSuggestStoreVo", aroundSuggestStoreVo);
		// 将本网格推荐周边店评论的信息封装在map中写入界面
		map.put("aroundSuggestStoreCommentVo", aroundSuggestStoreCommentVo);
		// 在后台对评论时间进行格式化后将评论时间封装在map中写入界面
		map.put("commentDatetime", DateUtil.format(aroundSuggestStoreCommentVo.getInsYmdhms(), "yyyy-MM-dd HH:mm:ss"));
		
		// 其它信息进入页面后由页面请求数据
		return AROUND_SUGGEST_COMMENT_REPLY;
	}
	
	/**
	 * 本网格推荐周边店管理：本网格推荐周边店回复的回复界面列表信息初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午11:19:28
	 * @param aroundSuggestStoreCommentReplyVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping("/findCommentReplyAll")
	public @ResponseBody String findCommentReplyAll(AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建本网格推荐周边店评论的评论列表变量用于存放本网格推荐周边店评论的评论信息
		List<AroundSuggestStoreCommentReplyVo> aroundSuggestStoreCommentReplyList = new ArrayList<AroundSuggestStoreCommentReplyVo>();
		// 创建记录数变量用于存放本网格推荐周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundSuggestStoreCommentReplyVo.setPageFlag(true);
		aroundSuggestStoreCommentReplyVo.setStartPage((page - 1) * rows);
		aroundSuggestStoreCommentReplyVo.setEndPage(rows);
		
		// 调用service方法获取本网格推荐周边店评论的评论总记录数
		count = aroundSuggestStoreService.findAroundSuggestStoreCommentReplyListCount(aroundSuggestStoreCommentReplyVo);
		// 调用service方法获取本网格推荐周边店评论的评论分页后列表
		aroundSuggestStoreCommentReplyList = aroundSuggestStoreService.findAroundSuggestStoreCommentReplyList(aroundSuggestStoreCommentReplyVo);
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundSuggestStoreCommentReplyList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：删除本网格推荐周边店评论的评论
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午4:31:44
	 * @param aroundSuggestStoreCommentReply
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/deleteCommentReply", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteCommentReply(AroundSuggestStoreCommentReply aroundSuggestStoreCommentReply) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAsscr={"周边店回复的回复"};
		
		// 调用service方法根据主键id获取周边店相关信息用于判断是否已经删除
		AroundSuggestStoreCommentReply original = aroundSuggestStoreService.findAroundSuggestStoreCommentReply(aroundSuggestStoreCommentReply.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAsscr));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		// 设置推荐店的评论id
		aroundSuggestStoreCommentReply.setAroundSuggestStoreCommentId(original.getAroundSuggestStoreCommentId());
		// 设置本网格推荐周边店回复的回复的更新回数
		aroundSuggestStoreCommentReply.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStoreCommentReply);
		
		// 调用service方法对本网格推荐周边店评论的评论信息进行删除，并返回删除结果
		boolean bl = aroundSuggestStoreService.deleteAroundSuggestStoreCommentReply(aroundSuggestStoreCommentReply);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAsscr), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_REPLY, aroundSuggestStoreCommentReply.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,attributeAsscr));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAsscr), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_REPLY, aroundSuggestStoreCommentReply.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,attributeAsscr));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：根据请求的不同进入 再次推荐初始化界面或本区推荐初始化界面
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午3:36:53
	 * @param id
	 * @param flag
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toSuggest/{id}/{flag}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toSuggest(@PathVariable Long id, @PathVariable int flag, Map<String, Object> map) {
		// 将本网格推荐周边店回复的行记录的id封装在map中写入界面
		map.put("id", id);
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息
		AroundSuggestStoreVo aroundSuggestStorevo = aroundSuggestStoreService.findAroundSuggestStore(id);
		
		// 将本网格推荐周边店的信息封装在map中写入界面
		map.put("aroundSuggestStorevo", aroundSuggestStorevo);
		
		// 根据请求标志位的不同进入不同的界面
		if (flag == 0) {
			// 如果请求标志位为0进入再次推荐界面
			return AROUND_SUGGEST_AGAIN;
		} else {
			// 如果请求标志位不为0进入本区推荐界面
			return COUNTY_SUGGEST;
		}
	}
	
	/**
	 * 本网格推荐周边店管理：再次推荐本网格推荐周边店
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午5:12:12
	 * @param aroundSuggestStoreVo
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/suggestAgain", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String suggestAgain(AroundSuggestStoreVo aroundSuggestStoreVo) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAss={"本网格推荐周边店"};
		
		// 调用service方法判断小区是否达到本网格推荐店最大值
		Map<String, Object> resultCount = aroundSuggestStoreService.judgeAroundSuggestIsMax(aroundSuggestStoreVo);
		if ((boolean) resultCount.get("isMax")) { // 如果达到最大值
			// 创建信息参数补充对象数组用于信息化参数的补充
			Object[] villageName = {(String) resultCount.get("villageName")};
			/* 向map中封装成功信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100067, villageName));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息用于判断是否已经删除
		AroundSuggestStore original = aroundSuggestStoreService.get(aroundSuggestStoreVo.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAss));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		// 设置推荐标志位
		aroundSuggestStoreVo.setSuggestFlag(ConstantStr.SUGGEST_FLAG_1);
		// 设置本网格推荐周边店回复的回复的更新回数
		aroundSuggestStoreVo.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStoreVo);
		
		// 调用service方法对本网格推荐周边店再次推荐
		boolean bl = aroundSuggestStoreService.suggestAgain(aroundSuggestStoreVo);
		
		if(bl){ // 如果推荐成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStoreVo.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,attributeAss));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStoreVo.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,attributeAss));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边店管理：查询还未推荐的网格长
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午5:50:39
	 * @param aroundSuggestStoreId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/queryAroundNoLeader/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryAroundNoLeader(@PathVariable Long id) throws Exception {
		// 创建用户列表变量用于存放用户信息
		List<User> userList = new ArrayList<User>();
		// 调用service方法根据本网格推荐周边店id获取已经推荐的用户
		List<User> hasSugUserList = aroundSuggestStoreService.findHasSugUser(id);
		// 调用service方法根据本网格推荐周边店id获取本网格推荐周边店所属区
		Long countyId = aroundSuggestStoreService.findAroundSuggestStoreVillage(id).get(0).getVillage().getCountyId();
		
		// 调用service方法根据区对象查询去信息
		List<Village> villageList= villageService.queryVillageByCountryId(countyId);
		
		// 创建Map变量用于查询相关信息
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 创建User对象用于封装当前用户
		User nowUser = new User();
		// 向User对象设置相应用户id
		nowUser.setId(getUserId());
		// 在已推荐用户中加入当前用户（防止查询出当前用户用于推荐）
		hasSugUserList.add(nowUser);
		
		/* 向map中封装相关参数用于sql动态查询 */
		param.put("hasSugUserList", hasSugUserList);
		param.put("villageList", villageList);
		param.put("areaLevel", ConstantStr.AREA_LEVEL_05);
		param.put("roleName", ConstantStr.ROLE_NAME_1);
		
		// 调用service方法通过map中封装的参数查询符合条件的用户区域对应关系
		List<UserArea> userAreaList = aroundStoreService.findUserAreaByParam(param);
		
		// 从符合条件的用户区域对应关系中获取用户信息添加进用户列表变量中
		for (UserArea ua:userAreaList) {
			userList.add(ua.getUser());
		}
		
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将用户列表变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(userList);
	}
	
	/**
	 * 本网格推荐周边店管理：查询已经推荐的网格长
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午5:50:15
	 * @param aroundSuggestStoreId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/queryAroundLeader/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryAroundLeader(@PathVariable Long id) throws Exception {
		// 调用service方法通过本网格推荐周边店id获取已经被推荐的用户信息
		List<User> hasSugUserList = aroundSuggestStoreService.findHasSugUser(id);
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将用户列表变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(hasSugUserList);
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：对选中的本网格推荐周边店进行本区推荐
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 下午5:31:48
	 * @param aroundSuggestStoreVo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/countySuggest", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String countySuggest(AroundSuggestStoreVo aroundSuggestStoreVo, @RequestParam("userId") String userId) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAss={"本区推荐周边店"};
		
		// 调用service方法对本网格推荐周边店进行本区推荐
		Map<String, Object> result = aroundSuggestStoreService.saveCountySuggest(aroundSuggestStoreVo, userId);
		
		if((boolean) result.get("isSuccess")){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAss), ConstantStr.M_COUNTY_SUGGEST_STORE, (Long)result.get("countySuggestStoreId"), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAss));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAss), ConstantStr.M_COUNTY_SUGGEST_STORE, (Long)result.get("countySuggestStoreId"), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAss));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：对选中的本网格推荐周边店进行促销管理
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午9:36:14
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toSales/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toSales(@PathVariable Long id, Map<String, Object> map) throws Exception {
		// 将本网格推荐周边店回复的行记录的id封装在map中写入界面
		map.put("aroundSuggestStoreId", id);
		
		// 调用service方法根据主键id获取本网格推荐周边店相关信息
		AroundSuggestStoreVo aroundSuggestStorevo = aroundSuggestStoreService.findAroundSuggestStore(id);
		
		// 将本网格推荐周边店的信息封装在map中写入界面
		map.put("aroundSuggestStorevo", aroundSuggestStorevo);
		
		// 其它信息进入页面后由页面请求数据
		return SALES_LIST;
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：获取本网格推荐周边店的促销信息的列表数据
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午10:07:20
	 * @param aroundSuggestStorePromotionVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping("/salesFindAll")
	public @ResponseBody String salesFindAll(AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建列表变量用于存放本网格推荐周边店的促销信息
		List<AroundSuggestStorePromotionVo> aroundSuggestStorePromotionList = new ArrayList<AroundSuggestStorePromotionVo>();
		// 创建记录数变量用于存放本网格推荐周边店的促销信息总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundSuggestStorePromotionVo.setPageFlag(true);
		aroundSuggestStorePromotionVo.setStartPage((page - 1) * rows);
		aroundSuggestStorePromotionVo.setEndPage(rows);
		
		// 调用service方法获取本网格推荐周边店的促销信息总记录数
		count = aroundSuggestStoreService.findAroundSuggestStorePromotionListCount(aroundSuggestStorePromotionVo);
		// 调用service方法获取本网格推荐周边店的促销信息分页后列表
		aroundSuggestStorePromotionList = aroundSuggestStoreService.findAroundSuggestStorePromotionList(aroundSuggestStorePromotionVo);
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundSuggestStorePromotionList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：新增本网格推荐周边店促销信息初始化
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午10:23:25
	 * @param aroundSuggestStoreId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toAddAroundSuggestStorePromotion/{aroundSuggestStoreId}", method = { RequestMethod.GET })
	public String toAddAroundSuggestStorePromotion(@PathVariable Long aroundSuggestStoreId, Map<String, Object> map) {
		// 根据主键id查询本网格推荐周边店的信息
		AroundSuggestStoreVo aroundSuggestStorevo = aroundSuggestStoreService.findAroundSuggestStore(aroundSuggestStoreId);
		// 向界面放入参数
		map.put("aroundSuggestStorevo", aroundSuggestStorevo);
		// 向界面放入参数
		map.put("aroundSuggestStoreId", aroundSuggestStoreId);
		// 跳转到新增界面
		return SALES_ADD;
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：保存本网格推荐周边店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:44:56
	 * @param aroundSuggestStorePromotion
	 * @param propagandafiles
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value="/saveAroundSuggestStorePromotion/{flag}",method={RequestMethod.POST})
	public @ResponseBody String saveAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion,  
			@RequestParam("propagandafile") MultipartFile[] propagandafiles, @PathVariable String flag) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAssp={"本网格推荐周边店促销信息"};
		/* 根据标志位设置本网格推荐周边店的推荐状态 */
		if ("1".equals(flag)) {
			aroundSuggestStorePromotion.setPublishStatus(ConstantStr.FB);
		} else {
			aroundSuggestStorePromotion.setPublishStatus(ConstantStr.WFB);
		}
		// 设置发布人
		aroundSuggestStorePromotion.setPublishPerson(getUserId());
		// 设置发布时间
		aroundSuggestStorePromotion.setPublishTime(new Date());
		// 更新Vo中相关共通变量的数据
		this.addAttr(aroundSuggestStorePromotion);
		// 调用service方法对本网格推荐周边店信息进行保存，并返回保存结果
		boolean result = aroundSuggestStoreService.saveAroundSuggestStorePromotion(aroundSuggestStorePromotion, propagandafiles);
		if(result){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_PROMOTION, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAssp));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_PROMOTION, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAssp));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：修改本网格推荐周边店促销信息初始化
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:45:37
	 * @param id
	 * @param aroundSuggestStoreId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/toEditorAroundSuggestStorePromotion/{id}/{aroundSuggestStoreId}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toEditorAroundSuggestStorePromotion(@PathVariable Long id, @PathVariable Long aroundSuggestStoreId, Map<String, Object> map) {
		// 将本网格推荐周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 根据主键id查询本网格推荐周边店的信息
		AroundSuggestStoreVo aroundSuggestStorevo = aroundSuggestStoreService.findAroundSuggestStore(aroundSuggestStoreId);
		// 向界面放入参数
		map.put("aroundSuggestStorevo", aroundSuggestStorevo);
		// 创建附件Model变量用于封装sql查询参数
		Attach attach = new Attach();
		// 设置附件类型
		attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
		// 设置源id
		attach.setOwnerId(id);
		// 设置源表名
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_10);
		// 调用service方法根据促销信息id获取附件信息
		List<Attach> attchList = aroundSuggestStoreService.findAttachByParam(attach);
		// 将本网格推荐周边店详细信息封装在map中写入界面
		map.put("attchList", attchList);
		// 将附件的长度封装在map中写入界面
		map.put("attachCount", attchList.size());
		// 其它信息进入页面后由页面请求数据
		return SALES_EDIT;
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：修改界面获取选中本网格推荐周边店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:46:20
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/findByPromotionId/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findByPromotionId(@PathVariable Long id) throws Exception {
		// 调用service方法根据主键id获取本网格推荐周边店促销信息
		AroundSuggestStorePromotion aroundSuggestStorePromotion = aroundSuggestStoreService.findAroundSuggestStorePromotion(id);
		// 设置开始时间字符串
		aroundSuggestStorePromotion.setStartTimeStr(DateUtil.format(aroundSuggestStorePromotion.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		// 设置结束时间字符串
		aroundSuggestStorePromotion.setEndTimeStr(DateUtil.format(aroundSuggestStorePromotion.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		// 设置申请时间字符串
		aroundSuggestStorePromotion.setApplicationTimeStr(DateUtil.format(aroundSuggestStorePromotion.getApplicationTime(), "yyyy-MM-dd HH:mm:ss"));
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将周边店的信息转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(aroundSuggestStorePromotion);
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：更新本网格推荐周边店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:46:59
	 * @param aroundSuggestStorePromotion
	 * @param files
	 * @param propagandafiles
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value="/updateAroundSuggestStorePromotion",method={RequestMethod.POST})
	public @ResponseBody String updateAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion, @RequestParam("file") MultipartFile[] files, 
			@RequestParam("propagandafile") MultipartFile[] propagandafiles) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		/* 创建信息参数补充对象数组用于信息化参数的补充 */
		Object[] attributeAssp={"本网格推荐周边店促销信息"};
		
		// 调用service方法根据主键id获取本网格推荐周边店促销相关信息用于判断是否已经删除
		AroundSuggestStorePromotion original = aroundSuggestStoreService.findAroundSuggestStorePromotion(aroundSuggestStorePromotion.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAssp));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return mapper.writeValueAsString(map);
		}
		
		// 设置开始时间
		aroundSuggestStorePromotion.setStartTime(DateUtil.parseDate(aroundSuggestStorePromotion.getStartTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		// 设置结束时间
		aroundSuggestStorePromotion.setEndTime(DateUtil.parseDate(aroundSuggestStorePromotion.getEndTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		// 设置申请时间
		aroundSuggestStorePromotion.setApplicationTime(DateUtil.parseDate(aroundSuggestStorePromotion.getApplicationTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		// 设置本网格推荐周边店促销的更新回数
		aroundSuggestStorePromotion.setUpdEac(original.getUpdEac());
		// 更新Vo中相关共通变量的数据
		this.editAttr(aroundSuggestStorePromotion);
		// 调用service方法对本网格推荐周边店促销信息进行保存，并返回保存结果
		boolean result = aroundSuggestStoreService.updateAroundSuggestStorePromotion(aroundSuggestStorePromotion, propagandafiles);
		if(result){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_PROMOTION, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, attributeAssp));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_PROMOTION, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, attributeAssp));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：删除选中的本网格推荐周边店的促销信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:47:20
	 * @param aroundSuggestStorePromotion
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/deleteAroundSuggestStorePromotion", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAssp={"本网格推荐周边店促销信息"};
		
		// 调用service方法根据主键id获取本网格推荐周边店促销相关信息用于判断是否已经删除
		AroundSuggestStorePromotion original = aroundSuggestStoreService.findAroundSuggestStorePromotion(aroundSuggestStorePromotion.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAssp));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置更新回数
		aroundSuggestStorePromotion.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStorePromotion);
		
		// 调用service方法对本网格推荐周边店促销信息进行保删除，并返回删除结果
		boolean bl = aroundSuggestStoreService.deleteAroundSuggestStorePromotion(aroundSuggestStorePromotion);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_PROMOTION, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,attributeAssp));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE_COMMENT_PROMOTION, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,attributeAssp));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}

	/**
	 * @Title
	 * @Description 本网格推荐周边店管理：更改本网格推荐周边店的促销信息的状态
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 下午2:47:48
	 * @param publishStatus
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/updateAroundSuggestStorePromotionStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateAroundSuggestStorePromotionStatus(String publishStatus, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAssp={"本网格推荐周边店促销信息"};
		
		// 调用service方法根据主键id获取本网格推荐周边店促销相关信息用于判断是否已经删除
		AroundSuggestStorePromotion original = aroundSuggestStoreService.findAroundSuggestStorePromotion(id);
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAssp));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 创建本网格推荐周边店促销Model对象用于存放更新数据
		AroundSuggestStorePromotion aroundSuggestStorePromotion = new AroundSuggestStorePromotion();
		// 设置本网格推荐周边店促销的主键id
		aroundSuggestStorePromotion.setId(id);
		// 设置本网格推荐周边店促销的状态
		aroundSuggestStorePromotion.setPublishStatus(publishStatus);
		// 设置本网格推荐周边店促销的更新回数
		aroundSuggestStorePromotion.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundSuggestStorePromotion);
		
		// 调用service方法对本网格推荐周边店促销信息进行保存，并返回保存结果
		boolean result = aroundSuggestStoreService.updateAroundSuggestStorePromotionStatus(aroundSuggestStorePromotion);
		
		if(result){ // 如果更新成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,attributeAssp));
		}else{ // 如果更新失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100054,attributeAssp), ConstantStr.M_AROUND_SUGGEST_STORE, aroundSuggestStorePromotion.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,attributeAssp));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本网格推荐周边点管理：检查本网格推荐周边店所在区域是否开启推荐功能
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午11:33:49
	 * @param aroundSuggestStore
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/checkRecommend", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkRecommend(AroundSuggestStore aroundSuggestStore) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		
		// 调用service方法查看本网格推荐周边所在区域是否允许推荐
		boolean bl = aroundSuggestStoreService.checkRecommend(aroundSuggestStore);
		
		if(bl){ // 如果允许
			
			/* 向map中封装成功信息 */
			map.put("type", "Success");
		}else{ // 如果不允许
			
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100070));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 检查组织代码证是否存在(共通 不加权限)
	 * @author:kangtianyu
	 * @CreateDate:2016年8月17日 下午10:59:52
	 * @param organizationCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkOrganizationCode", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkOrganizationCode(String organizationCode) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		
		// 调用service方法查看本网格推荐周边所在区域是否允许推荐
		boolean bl = aroundSuggestStoreService.findOrganizationCode(organizationCode);
		
		if(!bl){ // 如果允许
			
			/* 向map中封装成功信息 */
			map.put("type", "Success");
		}else{ // 如果不允许
			
			/* 向map中封装失败信息 */
			map.put("type", "Error");
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description 查询分配给当前登录用户所管理的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年9月7日 下午2:40:41
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundSuggestS:manager")
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
	 * @Title
	 * @Description 跳转到小区选择界面
	 * @author:kangtianyu
	 * @CreateDate:2016年9月7日 下午3:03:22
	 * @param map
	 * @param request
	 * @return
	 */
	@RequiresPermissions("AroundSuggestS:manager")
	@RequestMapping(value = "/selectVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectVillage(Map<String, Object> map, HttpServletRequest request) {
		String villageId = request.getParameter("villageId");
		map.put("villageId", villageId);
		return VILLAGE_SELECT;
	}
	
	private void addAttr(AroundSuggestStoreVo _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private void addAttr(AroundSuggestStorePromotion _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private void editAttr(AroundSuggestStoreVo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundSuggestStoreComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundSuggestStore _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundSuggestStoreCommentReply _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundSuggestStorePromotion _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	@InitBinder  
    private void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

}
