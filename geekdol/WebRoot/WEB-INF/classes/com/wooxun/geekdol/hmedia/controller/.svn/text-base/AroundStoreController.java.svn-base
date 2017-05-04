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
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundStoreCommentReply;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.hmedia.service.AroundStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.hmedia.vo.CountySuggestStoreVo;
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
 * @Description 周边店Controller
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年7月28日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年7月28日  下午4:42:03 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("aroundStore")
public class AroundStoreController extends BaseController {
	
	/** 一览界面返回路径 */
	private static final String AROUND_LIST = "hmedia/aroundStore/list";
	
	/** 新增界面返回路径 */
	private static final String AROUND_TOADD = "hmedia/aroundStore/aroundstoreAdd";
	
	/** 修改界面返回路径 */
	private static final String AROUND_TOEDIT = "hmedia/aroundStore/aroundstoreEdit";
	
	/** 小区选择界面 */
	public static final String VILLAGE_SELECT = "hmedia/aroundStore/villageSelect";
	
	/** 回复管理界面返回路径 */
	private static final String AROUND_COMMENT = "hmedia/aroundStore/aroundstoreComment";
	
	/** 回复管理的回复管理界面返回路径 */
	private static final String AROUND_COMMENT_REPLY = "hmedia/aroundStore/aroundstoreCommentReply";
	
	/** 本网格推荐界面返回路径 */
	private static final String AROUND_SUGGEST = "hmedia/aroundStore/aroundSuggest";
	
	/** 本区推荐界面返回路径 */
	private static final String COUNTY_SUGGEST = "hmedia/aroundStore/countySuggest";
	
	@Autowired
	private AroundStoreService<AroundStore> aroundStoreService;
	
	@Autowired
	private VillageService<Village> villageService;
	
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	@Autowired
	private CommunityService<Community> communityService;
	
	/**
	 * 周边店管理：初始化界面
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月28日 下午5:45:12
	 * @return
	 */
	@RequiresPermissions("AroundStore:view")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
        return AROUND_LIST;
    }
	
	/**
	 * 周边店管理：周边店信息初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 上午9:52:55
	 * @param aroundStoreVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(AroundStoreVo aroundStoreVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建周边店列表变量用于存放周边店信息
		List<AroundStoreVo> aroundStoreList = new ArrayList<AroundStoreVo>();
		// 创建记录数变量用于存放周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundStoreVo.setPageFlag(true);
		aroundStoreVo.setStartPage((page - 1) * rows);
		aroundStoreVo.setEndPage(rows);
		
		// 调用service方法查找当前用户所能查看的周边店的id
		List<AroundStoreVillage> asvList = aroundStoreService.findAroundStoreIdByUser(getUserId());
		
		if (asvList != null && asvList.size() > 0) { // 如果有可查看的
			// 创建Map变量用于插入sql查询参数
			Map<String, Object> param = new HashMap<String, Object>();
			/* 向map中放入数据 */
			param.put("aroundStoreVo", aroundStoreVo);
			param.put("storeType", ConstantStr.STORETYPE);
			param.put("asvList", asvList);
			
			// 调用service方法获取周边店总记录数
			count = aroundStoreService.findAroundStoreListCount(param);
			// 调用service方法获取周边店分页后列表
			aroundStoreList = aroundStoreService.findAroundStoreList(param);
		}
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundStoreList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：新增界面初始化
	 * @Title
	 * @Description 
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午12:06:03
	 * @param model
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/toAddAroundStore", method = { RequestMethod.GET })
	public String toAddAroundStore(Model model) {
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
		return AROUND_TOADD;
	}
	
	/**
	 * 周边店管理：保存周边店信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午5:10:40
	 * @param aroundStoreVo
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value="/saveAroundStore",method={RequestMethod.POST})
	public @ResponseBody String saveAroundStore(AroundStoreVo aroundStoreVo) throws Exception{
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAs={"周边店"};
		
		// 向Vo中设置相关共通变量的数据
		this.addAttr(aroundStoreVo);
		
		// 调用service方法对周边店信息进行保存，并返回保存结果
		boolean t = aroundStoreService.saveAroundStore(aroundStoreVo);
		
		if(t){ // 如果保存成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAs));
		}else{ // 如果保存失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAs));
		}
		
		// 将Map变量转变为json变量写入界面
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：修改界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午2:09:49
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/toEditorAroundStore/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toEditorAroundStore(@PathVariable Long id, Map<String, Object> map) {
		// 将周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 其它信息进入页面后由页面请求数据
		return AROUND_TOEDIT;
	}
	
	/**
	 * 周边店管理：修改操作时根据周边店id获取周边店的信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午2:25:13
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id) throws Exception {
		// 调用service方法根据周边店id获取周边店相关信息
		AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(id);
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将周边店的信息转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(aroundStoreVo);
	}
	
	/**
	 * 周边店管理：修改选中的周边店信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午2:33:51
	 * @param aroundStoreVo
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/updateAroundStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateAroundStore(AroundStoreVo aroundStoreVo) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAs={"周边店"};

		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreVo original = aroundStoreService.findAroundStore(aroundStoreVo.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAs));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置周边店的更新回数
		aroundStoreVo.setUpdEac(original.getUpdEac());
		// 更新Vo中相关共通变量的数据
		this.editAttr(aroundStoreVo);
		
		// 调用service方法对周边店信息进行更新，并返回更新结果
		boolean bl = aroundStoreService.updateAroundStoreVo(aroundStoreVo);
		if(bl){ // 如果更新成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,attributeAs));
		}else{ // 如果更新失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,attributeAs));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：小区选择
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午3:55:09
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/initVillagePage/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String initVillagePage(@PathVariable Long id, Map<String, Object> map) {
		// 将周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 其它信息进入页面后由页面请求数据
		return VILLAGE_SELECT;
	}
	
	/**
	 * 周边店管理：查询周边店所不属的小区
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月29日 下午4:41:31
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/queryVillageNoAroundStore/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryVillageNoAroundStore(@PathVariable Long id) throws Exception {
		// 创建小区列表变量用于存放小区信息
		List<Village> villageList = new ArrayList<Village>();
		// 创建周边店小区对应关系列表变量用于存放周边店小区对应关系信息
		List<AroundStoreVillage> aroundStoreVillageList = new ArrayList<AroundStoreVillage>();
		
		// id为0时表示是新增操作，不需要查询已经所属的小区
		if (id != 0) {
			// 调用service方法通过周边店id查找已经所属的小区
			aroundStoreVillageList = aroundStoreService.findAroundStoreVillage(id);
		}
		// 调用service方法查找当前周边店不属于的小区（去除已经所属的小区）
		villageList = villageService.findVillageNoAroundStoreByVillageId(aroundStoreVillageList, getUserId());
		
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(villageList);
	}
	
	/**
	 * 周边店管理：查询周边店所属小区
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午2:34:22
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/queryVillageAroundStore/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryVillageAroundStore(@PathVariable Long id) throws Exception {
		// 创建小区列表变量用于存放小区信息
		List<Village> villageList = new ArrayList<Village>();
		// 创建周边店小区对应关系列表变量用于存放周边店小区对应关系信息
		List<AroundStoreVillage> aroundStoreVillageList = new ArrayList<AroundStoreVillage>();
		
		// id为0时表示是新增操作，不需要查询已经所属的小区
		if (id != 0) {
			// 调用service方法通过周边店id查找已经所属的小区
			aroundStoreVillageList = aroundStoreService.findAroundStoreVillage(id);
		}
		
		// 调用service方法查找当前周边店属于的小区
		villageList = villageService.findVillageAroundStoreByVillageId(aroundStoreVillageList);
		
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(villageList);
	}
	
	/**
	 * 周边店管理：删除周边店信息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午12:30:02
	 * @param aroundStore
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/deleteAroundStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteAroundStore(AroundStore aroundStore) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAs={"周边店"};
		
		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreVo original = aroundStoreService.findAroundStore(aroundStore.getId());
		if(original == null){//验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAs));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置更新回数
		aroundStore.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundStore);
		
		// 调用service方法对周边店信息进行保删除，并返回删除结果
		boolean bl = aroundStoreService.deleteAroundStore(aroundStore);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,attributeAs), ConstantStr.M_AROUND_STORE, aroundStore.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,attributeAs));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004,attributeAs), ConstantStr.M_AROUND_STORE, aroundStore.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,attributeAs));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理： 更改周边店状态
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午2:34:59
	 * @param useStatus
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/updateAroundStoreStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateAroundStoreStatus(int useStatus, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAs={"周边店"};
		
		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreVo original = aroundStoreService.findAroundStore(id);
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAs));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 创建周边店对象用于存放更新数据
		AroundStore aroundStore = new AroundStore();
		// 设置周边店的主键id
		aroundStore.setId(id);
		// 设置周边店的状态
		aroundStore.setUseStatus(String.valueOf(Math.abs(useStatus - 1)));
		// 设置周边店的更新回数
		aroundStore.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundStore);
		
		// 调用service方法对周边店信息进行更新，并返回更新结果
		int i = aroundStoreService.updateSelective(aroundStore);
		
		if(i > 0){ // 如果更新成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,attributeAs), ConstantStr.M_AROUND_STORE, aroundStore.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,attributeAs));
		}else{ // 如果更新失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100054,attributeAs), ConstantStr.M_AROUND_STORE, aroundStore.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,attributeAs));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：回复管理
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午4:53:14
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/toCommentAroundStore/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toCommentAroundStore(@PathVariable Long id, Map<String, Object> map) {
		// 调用service方法根据周边店id获取周边店相关信息
		AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(id);
		
		// 将周边店行记录的id封装在map中写入界面
		map.put("id", id);
		// 将周边店信息封装在map中写入界面
		map.put("aroundStoreVo", aroundStoreVo);
		
		// 其它信息进入页面后由页面请求数据
		return AROUND_COMMENT;
	}
	
	/**
	 * 周边店管理：查询回复列表
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 下午5:35:25
	 * @param aroundStoreCommentVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping("/findCommentAll")
	public @ResponseBody String findCommentAll(AroundStoreCommentVo aroundStoreCommentVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建周边店评论列表变量用于存放周边店评论信息
		List<AroundStoreCommentVo> aroundStoreCommentList = new ArrayList<AroundStoreCommentVo>();
		// 创建记录数变量用于存放周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundStoreCommentVo.setPageFlag(true);
		aroundStoreCommentVo.setStartPage((page - 1) * rows);
		aroundStoreCommentVo.setEndPage(rows);
		
		// 调用service方法获取周边店评论总记录数
		count = aroundStoreService.findAroundStoreCommentListCount(aroundStoreCommentVo);
		// 调用service方法获取周边店评论分页后列表
		aroundStoreCommentList = aroundStoreService.findAroundStoreCommentList(aroundStoreCommentVo);
		
		/* 转义表情 */
		for (AroundStoreCommentVo ascv : aroundStoreCommentList) {
			ascv.setInsName(UnescapeUtil.unescape(ascv.getInsName()));
			ascv.setContent(UnescapeUtil.unescape(ascv.getContent()));
		}
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundStoreCommentList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：删除周边店回复
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午3:01:24
	 * @param aroundStoreComment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/deleteComment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteComment(AroundStoreComment aroundStoreComment) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAsc={"周边店回复"};
		
		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreCommentVo original = aroundStoreService.findAroundStoreComment(aroundStoreComment.getId());
		if(original == null){//验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAsc));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置周边店回复的更新回数
		aroundStoreComment.setUpdEac(original.getUpdEac());
		// 设置周边店id
		aroundStoreComment.setAroundStoreId(original.getAroundStoreId());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundStoreComment);
		
		// 调用service方法对周边店的评论信息进行删除，并返回删除结果
		boolean bl = aroundStoreService.deleteAroundStoreComment(aroundStoreComment);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100003,attributeAsc), ConstantStr.M_AROUND_STORE_COMMENT, aroundStoreComment.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,attributeAsc));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100004,attributeAsc), ConstantStr.M_AROUND_STORE_COMMENT, aroundStoreComment.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,attributeAsc));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：周边店回复的回复初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午9:43:07
	 * @param id
	 * @param aroundStoreId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/toReplyComment/{id}/{aroundStoreId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toReplyAroundStore(@PathVariable Long id, @PathVariable Long aroundStoreId, Map<String, Object> map) {
		// 将周边店回复的行记录的id封装在map中写入界面
		map.put("aroundStoreCommentId", id);
		
		// 将周边店的行记录的id封装在map中写入界面
		map.put("aroundStoreId", aroundStoreId);
		
		// 调用service方法根据周边店id获取周边店相关信息
		AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(aroundStoreId);
		// 调用service方法根据周边店的评论id获取周边店的评论相关信息
		AroundStoreCommentVo aroundStoreCommentVo = aroundStoreService.findAroundStoreCommentDetail(id);
		
		/* 转义表情 */
		aroundStoreCommentVo.setInsName(UnescapeUtil.unescape(aroundStoreCommentVo.getInsName()));
		aroundStoreCommentVo.setContent(UnescapeUtil.unescape(aroundStoreCommentVo.getContent()));
		
		// 将周边店的信息封装在map中写入界面
		map.put("aroundStoreVo", aroundStoreVo);
		// 将周边店评论的信息封装在map中写入界面
		map.put("aroundStoreCommentVo", aroundStoreCommentVo);
		// 在后台对评论时间进行格式化后将评论时间封装在map中写入界面
		map.put("commentDatetime", DateUtil.format(aroundStoreCommentVo.getInsYmdhms(), "yyyy-MM-dd HH:mm:ss"));
		
		// 其它信息进入页面后由页面请求数据
		return AROUND_COMMENT_REPLY;
	}
	
	/**
	 * 周边店管理：周边店回复的回复列表查询
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午9:56:43
	 * @param aroundStoreCommentReplyVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping("/findCommentReplyAll")
	public @ResponseBody String findCommentReplyAll(AroundStoreCommentReplyVo aroundStoreCommentReplyVo,
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建周边店评论的评论列表变量用于存放周边店评论的评论信息
		List<AroundStoreCommentReplyVo> aroundStoreCommentReplyList = new ArrayList<AroundStoreCommentReplyVo>();
		// 创建记录数变量用于存放周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		aroundStoreCommentReplyVo.setPageFlag(true);
		aroundStoreCommentReplyVo.setStartPage((page - 1) * rows);
		aroundStoreCommentReplyVo.setEndPage(rows);
		
		// 调用service方法获取周边店评论的评论总记录数
		count = aroundStoreService.findAroundStoreCommentReplyListCount(aroundStoreCommentReplyVo);
		// 调用service方法获取周边店评论的评论分页后列表
		aroundStoreCommentReplyList = aroundStoreService.findAroundStoreCommentReplyList(aroundStoreCommentReplyVo);
		
		/* 转义表情 */
		for (AroundStoreCommentReplyVo ascrv : aroundStoreCommentReplyList) {
			ascrv.setInsName(UnescapeUtil.unescape(ascrv.getInsName()));
			ascrv.setContent(UnescapeUtil.unescape(ascrv.getContent()));
		}
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", aroundStoreCommentReplyList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：删除回复的回复
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年7月31日 下午10:19:26
	 * @param aroundStoreCommentReply
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/deleteCommentReply", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteCommentReply(AroundStoreCommentReply aroundStoreCommentReply) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAscr={"周边店回复的回复"};
		
		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreCommentReplyVo original = aroundStoreService.findAroundStoreCommentReply(aroundStoreCommentReply.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAscr));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 设置周边店的评论id
		aroundStoreCommentReply.setAroundStoreCommentId(original.getAroundStoreCommentId());
		// 设置周边店回复的回复的更新回数
		aroundStoreCommentReply.setUpdEac(original.getUpdEac());
		// 更新Model中相关共通变量的数据
		this.editAttr(aroundStoreCommentReply);
		
		// 调用service方法对周边店评论的评论信息进行删除，并返回删除结果
		boolean bl = aroundStoreService.deleteAroundStoreCommentReply(aroundStoreCommentReply);
		
		if(bl){ // 如果删除成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAscr), ConstantStr.M_AROUND_STORE_COMMENT_REPLY, aroundStoreCommentReply.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,attributeAscr));
		}else{ // 如果删除失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAscr), ConstantStr.M_AROUND_STORE_COMMENT_REPLY, aroundStoreCommentReply.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,attributeAscr));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：本网格推荐界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月1日 上午10:32:50
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/toAroundSuggest/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toAroundSuggest(@PathVariable Long id, Map<String, Object> map) {
		// 调用service方法根据周边店id获取周边店相关信息
		AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(id);
		
		// 将周边店行记录的id封装在map中写入界面
		map.put("aroundStoreId", id);
		// 将周边店信息封装在map中写入界面
		map.put("aroundStoreVo", aroundStoreVo);
		
		// 其它信息进入页面后由页面请求数据
		return AROUND_SUGGEST;
	}
	
	/**
	 * 周边店管理：本域推荐周边店信息保存
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月1日 下午3:41:47
	 * @param aroundSuggestStoreVo
	 * @param files
	 * @param propagandafiles
	 * @param aroundStoreId
	 * @param suggestStart
	 * @param suggestEnd
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value="/saveAroundSuggestStore/{aroundStoreId}",method={RequestMethod.POST})
	public @ResponseBody String saveAroundSuggestStore(AroundStoreVo aroundStoreVo, @RequestParam("file") MultipartFile[] files, 
			@RequestParam("propagandafile") MultipartFile[] propagandafiles, @PathVariable Long aroundStoreId, 
			@RequestParam("suggestStart") Date suggestStart, @RequestParam("suggestEnd") Date suggestEnd) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		/* 创建信息参数补充对象数组用于信息化参数的补充 */
		Object[] attributeAs={"周边店"};
		Object[] attributeAss={"本网格推荐店"};
		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreVo original = aroundStoreService.findAroundStore(aroundStoreId);
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAs));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return mapper.writeValueAsString(map);
		}
		// 调用service方法判断小区是否达到本网格推荐店最大值
		Map<String, Object> resultCount = aroundStoreService.judgeAroundSuggestIsMax(aroundStoreVo);
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
					aroundStoreVo.setIcon("");
				}
				if(i == 1){ // 如果第二个文件没有上传
					
					// 设置相关属性为空字符串
					aroundStoreVo.setStoreImage("");
				}
				if(i == 2){ // 如果第三个文件没有上传
					
					// 设置相关属性为空字符串
					aroundStoreVo.setLicenseImage("");
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
					aroundStoreVo.setIcon(newFileName);
				}
				if(i == 1){
					aroundStoreVo.setStoreImage(newFileName);
				}
				if(i == 2){
					aroundStoreVo.setLicenseImage(newFileName);
				}
			}
		}
		
		// 设置周边店的主键id
		aroundStoreVo.setId(aroundStoreId);
		// 设置周边店的推荐状态
		aroundStoreVo.setSuggestFlag(ConstantStr.SUGGEST_FLAG_1);
		// 设置周边店更新回数
		aroundStoreVo.setUpdEac(original.getUpdEac());
		// 更新Vo中相关共通变量的数据
		this.editAttr(aroundStoreVo);
		// 调用service方法对本网格推荐周边店信息进行保存，并返回保存结果
		Map<String, Object> result = aroundStoreService.saveAroundSuggestStoreByAS(aroundStoreVo, propagandafiles, suggestStart, suggestEnd);
		if((boolean) result.get("isSuccess")){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, (Long)result.get("aroundSuggestStoreId"), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAss));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, (Long)result.get("aroundSuggestStoreId"), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAss));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 周边店管理：本区推荐界面初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月1日 上午10:33:40
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/toCountySuggest/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toCountySuggest(@PathVariable Long id, Map<String, Object> map) {
		// 调用service方法根据周边店id获取周边店相关信息
		AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(id);
		
		// 将周边店行记录的id封装在map中写入界面
		map.put("aroundStoreId", id);
		// 将周边店信息封装在map中写入界面
		map.put("aroundStoreVo", aroundStoreVo);
		
		// 其它信息进入页面后由页面请求数据
		return COUNTY_SUGGEST;
	}
	
	/**
	 * 周边店管理：查询本区推荐还没有推荐的网格长
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月2日 下午5:20:55
	 * @param aroundStoreId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/queryAroundNoLeader/{aroundStoreId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryAroundNoLeader(@PathVariable Long aroundStoreId) throws Exception {
		// 创建用户列表变量用于存放用户信息
		List<User> userList = new ArrayList<User>();
		// 调用service方法根据周边店id获取已经推荐的用户
		List<User> hasSugUserList = aroundStoreService.findHasSugUser(aroundStoreId);
		// 调用service方法根据周边店id获取周边店所属区
		Long countyId = aroundStoreService.findAroundStoreVillage(aroundStoreId).get(0).getVillage().getCountyId();
		
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
	 * 周边店管理：查询本区推荐已经推荐的网格长
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月2日 下午5:19:41
	 * @param aroundStoreId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/queryAroundLeader/{aroundStoreId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryAroundLeader(@PathVariable Long aroundStoreId) throws Exception {
		// 调用service方法通过周边店id获取已经被推荐的用户信息
		List<User> hasSugUserList = aroundStoreService.findHasSugUser(aroundStoreId);
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将用户列表变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(hasSugUserList);
	}
	
	/**
	 * 周边店管理：本区推荐店信息保存
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月2日 下午5:18:33
	 * @param aroundStoreVo
	 * @param countySuggestStoreVo
	 * @param userId
	 * @param file
	 * @param aroundStoreId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value="/saveCountySuggestStore/{aroundStoreId}",method={RequestMethod.POST})
	public @ResponseBody String saveCountySuggestStore(AroundStoreVo aroundStoreVo, CountySuggestStoreVo countySuggestStoreVo,
			@RequestParam("userId") String userId, @RequestParam(value = "file", required = false) MultipartFile file, 
			@PathVariable Long aroundStoreId) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper mapper = new ObjectMapper();
		/* 创建信息参数补充对象数组用于信息化参数的补充 */
		Object[] attributeAs={"周边店"};
		Object[] attributeAss={"本区推荐店"};
		// 向Vo中封装主键id用于后续操作
		aroundStoreVo.setId(aroundStoreId);
		
		// 调用service方法根据周边店id获取周边店相关信息用于判断是否已经删除
		AroundStoreVo original = aroundStoreService.findAroundStore(aroundStoreVo.getId());
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeAs));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return mapper.writeValueAsString(map);
		}
		
		// 判断当前周边店是否进行过本网格推荐
		if (!ConstantStr.SUGGEST_FLAG_1.equals(aroundStoreVo.getSuggestFlag())) {
			
			/* 从共通文件中获取上传文件所需信息 */
			String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HMEDIA);
			String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_HMEDIA);
			
			if(file.getSize() <= 0){ // 如果没有文件进行上传
				
				// 没有文件上传设置相关链接属性为空字符串
				aroundStoreVo.setLicenseImage("");
			} else { // 如果有文件进行上传
				
				// 生成上传文件在服务器中的新文件名
				String newFileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
				// 判断文件是是否上传成功
				boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
				if(rs){ // 如果更新成功
					
					// 获取上传后的相对路径
					newFileName = httppath+newFileName;
					// 设置相关链接属性
					aroundStoreVo.setLicenseImage(newFileName);
					// 设置周边店更新回数
					aroundStoreVo.setUpdEac(original.getUpdEac());
					// 更新Vo中相关共通变量的数据
					this.editAttr(aroundStoreVo);
				}
			}
		}
		// 调用service方法保存数据
		Map<String, Object> result = aroundStoreService.saveCountySuggestStoreByAS(aroundStoreVo, userId);
		if((boolean) result.get("isSuccess")){ // 如果保存成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAss), ConstantStr.M_COUNTY_SUGGEST_STORE, (Long)result.get("countySuggestStoreId"), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAss));
		}else{ // 如果保存失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,attributeAs), ConstantStr.M_AROUND_STORE, aroundStoreVo.getId(), getUser());
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAss), ConstantStr.M_COUNTY_SUGGEST_STORE, (Long)result.get("countySuggestStoreId"), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAss));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 周边点管理：查询周边店所在区域是否允许推荐
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午10:57:34
	 * @param aroundStore
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/checkRecommend", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkRecommend(AroundStore aroundStore) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		
		// 调用service方法查看本周边所在区域是否允许推荐
		boolean bl = aroundStoreService.checkRecommend(aroundStore);
		
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
	 * @Description 查询分配给当前登录用户所管理的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年9月7日 下午2:40:41
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AroundStore:manager")
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
	 * @CreateDate:2016年9月7日 下午3:03:11
	 * @param map
	 * @param request
	 * @return
	 */
	@RequiresPermissions("AroundStore:manager")
	@RequestMapping(value = "/selectVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectVillage(Map<String, Object> map, HttpServletRequest request) {
		String villageId = request.getParameter("villageId");
		map.put("villageId", villageId);
		return VILLAGE_SELECT;
	}

	private void addAttr(AroundStoreVo _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	private void editAttr(AroundStoreVo _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundStore _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundStoreComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	private void editAttr(AroundStoreCommentReply _temp) {
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
