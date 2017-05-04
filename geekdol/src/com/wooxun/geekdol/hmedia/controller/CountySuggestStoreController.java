package com.wooxun.geekdol.hmedia.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.hmedia.model.CountySuggestStore;
import com.wooxun.geekdol.hmedia.model.CountySuggestStoreToperson;
import com.wooxun.geekdol.hmedia.service.AroundStoreService;
import com.wooxun.geekdol.hmedia.service.AroundSuggestStoreService;
import com.wooxun.geekdol.hmedia.service.CountySuggestStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
import com.wooxun.geekdol.hmedia.vo.CountySuggestStoreTopersonVo;
import com.wooxun.geekdol.hmedia.vo.CountySuggestStoreVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.VillageService;

/**
 * 本区推荐周边店Controller
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月9日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月9日  上午10:32:44 		创建
*==========================================================
*
 */
@Controller
@RequestMapping("countySuggestStore")
public class CountySuggestStoreController extends BaseController {
	
	/** 一览界面返回路径 */
	private static final String COUNTY_SUGGEST_LIST = "hmedia/countySuggestStore/list";
	
	/** 一览界面返回路径 */
	private static final String COUNTY_SUGGEST_DETAIL_ASS = "hmedia/countySuggestStore/detailAss";
	
	/** 一览界面返回路径 */
	private static final String COUNTY_SUGGEST_DETAIL_AS = "hmedia/countySuggestStore/detailAs";
	
	/** 再次推荐界面返回路径 */
	private static final String ACCEPT_SUGGEST = "hmedia/countySuggestStore/aroundSuggest";
	
	@Autowired
	private CountySuggestStoreService<CountySuggestStore> countySuggestStoreService;

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
	 * 本区推荐周边店管理：初始化界面
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:42:30
	 * @return
	 */
	@RequiresPermissions("CountySuggestS:view")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
		// 初始化界面暂不查询信息，进入页面后由页面请求数据
        return COUNTY_SUGGEST_LIST;
    }
	
	/**
	 * 本区推荐周边店管理：本区推荐周边店信息初始化
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月9日 上午11:30:19
	 * @param countySuggestStoreVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CountySuggestS:view")
	@RequestMapping("/findAll/{flag}")
	public @ResponseBody String findAll(CountySuggestStoreTopersonVo countySuggestStoreTopersonVo, @PathVariable String flag, 
			HttpServletRequest request) throws Exception {
		// 定义页数信息（默认为1）
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		// 定义查询记录数量信息 （默认为20）
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建本区推荐周边店列表变量用于存放本区推荐周边店信息
		List<CountySuggestStoreVo> countySuggestStoreList = new ArrayList<CountySuggestStoreVo>();
		// 创建记录数变量用于存放本区推荐周边店总记录数量
		Long count = 0l;
		
		/* 下面三行代码为向查询条件封装分页查询参数 */
		countySuggestStoreTopersonVo.setPageFlag(true);
		countySuggestStoreTopersonVo.setStartPage((page - 1) * rows);
		countySuggestStoreTopersonVo.setEndPage(rows);
		
		/* 根据请求的不同设置相应的查询条件 */
		if ("0".equals(flag)) {
			countySuggestStoreTopersonVo.setSugToPersonId(getUserId());
		} else if ("1".equals(flag)) {
			countySuggestStoreTopersonVo.setSugPersonId(getUserId());
			if (ConstantStr.HANDLE_STATUS_4.equals(countySuggestStoreTopersonVo.getHandleStatus())) {
				countySuggestStoreTopersonVo.setHandleStatus(null);
				countySuggestStoreTopersonVo.setSuggestFlag(ConstantStr.SUGGEST_FLAG_0);
			}
		}
		
		// 创建Map对象封装sql查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中放入参数 */
		param.put("countySuggestStoreTopersonVo", countySuggestStoreTopersonVo);
		param.put("storeType", ConstantStr.STORETYPE);
		param.put("flag", flag);
		
		// 调用service方法获取本区推荐周边店总记录数
		count = countySuggestStoreService.findCountySuggestStoreListCount(param);
		// 调用service方法获取本区推荐周边店分页后列表
		countySuggestStoreList = countySuggestStoreService.findCountySuggestStoreList(param);
		
		/* 下面两行代码为向Map变量中放入参数 */
		map.put("rows", countySuggestStoreList); 
		map.put("total", count);

		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本区推荐周边店管理：获得还未推荐的小区
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:30:55
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/queryVillageNoSuggest/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String queryVillageNoSuggest(@PathVariable Long id) throws Exception {
		// 创建小区列表变量用于存放小区信息
		List<Village> villageList = new ArrayList<Village>();
		// 创建本区推荐周边店小区对应关系列表变量用于存放本区推荐周边店小区对应关系信息
		List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList = new ArrayList<AroundSuggestStoreVillage>();
		
		// id为0时表示是新增操作，不需要查询已经所属的小区
		if (id != 0) {
			// 调用service方法通过本区推荐周边店id查找已经所属的小区
			aroundSuggestStoreVillageList = aroundSuggestStoreService.findAroundSuggestStoreVillage(id);
		}
		// 调用service方法查找当前本区推荐周边店不属于的小区（去除已经所属的小区）
		villageList = villageService.findVillageNoSuggest(aroundSuggestStoreVillageList, getUserId());
		
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(villageList);
	}
	
	/**
	 * 本区推荐周边店管理：查看店详情
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午3:24:36
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/toViewCountSuggestStore/{id}", method = { RequestMethod.GET,RequestMethod.POST })
	public String toViewCountSuggestStore(@PathVariable Long id, Map<String, Object> map) throws Exception {
		// 根据本区推荐周边店关系表主键id查询本推荐区店信息
		CountySuggestStore countySuggestStore = countySuggestStoreService.findCountySuggestStoreById(id);
		
		// 根据本区推荐店来源不同获取不同的数据
		if (ConstantStr.SUGGEST_TYPE_1.equals(countySuggestStore.getSuggestType())) {
			// 调用service方法根据主键id获取周边店相关信息
			AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(countySuggestStore.getSourceId());
			// 将周边店详细信息封装在map中写入界面
			map.put("aroundStoreVo", aroundStoreVo);
			// 其它信息进入页面后由页面请求数据
			return COUNTY_SUGGEST_DETAIL_AS;
			
		} else if (ConstantStr.SUGGEST_TYPE_2.equals(countySuggestStore.getSuggestType())) {
			// 调用service方法根据主键id获取本网格推荐周边店相关信息
			AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreService.findAroundSuggestStore(countySuggestStore.getSourceId());
			
			// 将本网格推荐周边店详细信息封装在map中写入界面
			map.put("aroundSuggestStoreVo", aroundSuggestStoreVo);
			// 将推荐开始时间转换格式后封装在map中写入界面
			map.put("suggestStart", DateUtil.format(aroundSuggestStoreVo.getSuggestStart(), "yyyy-MM-dd HH:mm:ss"));
			// 将推荐结束时间转换个时候封装在map中写入界面
			map.put("suggestEnd", DateUtil.format(aroundSuggestStoreVo.getSuggestEnd(), "yyyy-MM-dd HH:mm:ss"));
			
			// 创建附件Model变量用于封装sql查询参数
			Attach attach = new Attach();
			// 设置附件类型
			attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
			// 设置源id
			attach.setOwnerId(countySuggestStore.getSourceId());
			// 设置源表名
			attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
			// 调用service方法根据本网格推荐周边店id获取附件信息
			List<Attach> attchList = aroundSuggestStoreService.findAttachByParam(attach);
			
			// 将本网格推荐周边店详细信息封装在map中写入界面
			map.put("attchList", attchList);
			// 将附件的长度封装在map中写入界面
			map.put("attachCount", attchList.size());
			
			// 其它信息进入页面后由页面请求数据
			return COUNTY_SUGGEST_DETAIL_ASS;
		}
		return null;
	}
	
	/**
	 * 本区推荐周边店管理：获取详情页数据
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午3:37:20
	 * @param type
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/findById/{type}/{sid}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable String type, @PathVariable Long sid) throws Exception {
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 根据本区推荐店来源不同获取不同的数据
		if (ConstantStr.SUGGEST_TYPE_1.equals(type)) {
			// 调用service方法根据主键id获取周边店相关信息
			AroundStore aroundStore = aroundStoreService.get(sid);
			// 将数据转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(aroundStore);
			
		} else if (ConstantStr.SUGGEST_TYPE_2.equals(type)) {
			// 调用service方法根据主键id获取本网格推荐周边店相关信息
			AroundSuggestStore aroundSuggestStore = aroundSuggestStoreService.get(sid);
			// 将数据转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(aroundSuggestStore);
		}
		return null;
	}
	
	/**
	 * 本区推荐周边店管理：更改本区推荐周边店推荐状态
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:35:43
	 * @param suggestFlag
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/updateCountySuggestStoreStatus/{flag}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateAroundSuggestStoreStatus(@PathVariable String flag, String suggestFlag, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeCss={"本区推荐周边店"};
		Object[] attributeCsstp={"本区推荐周边店对应人"};
		
		// 调用service方法根据主键id获取本区推荐周边店相关信息用于判断是否已经删除
		CountySuggestStoreToperson original = countySuggestStoreService.findCountySuggestStoreTopersonById(id);
		if(original == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeCsstp));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 调用service方法根据主键id获取本区推荐周边店相关信息用于判断是否已经删除
		CountySuggestStore countySuggestStoreTemp = countySuggestStoreService.get(original.getCountySuggestStoreId());
		if(countySuggestStoreTemp == null){ // 验证是否已经删除
			/* 向map中封装数据已被删除的信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,attributeCss));
			// 将Map变量转变为json变量写入界面由easyui进行操作json
			return o.writeValueAsString(map);
		}
		
		// 创建本区推荐周边店关系表Model对象用于存放更新数据
		CountySuggestStoreToperson countySuggestStoreToperson = new CountySuggestStoreToperson();
		// 设置本区推荐周边店关系表的主键id
		countySuggestStoreToperson.setId(id);
		if ("0".equals(flag)) { // 如果是推荐给我的需要更改状态
			
			// 设置本区推荐周边店关系表的处理状态
			countySuggestStoreToperson.setHandleStatus(suggestFlag);
		} else { // 如果是我的推荐需要更改状态
			
			// 设置本区推荐周边店关系表的推荐状态
			countySuggestStoreToperson.setSuggestFlag(suggestFlag);
		}
		// 设置处理人
		countySuggestStoreToperson.setHandlePersonId(getUserId());
		// 设置处理时间
		countySuggestStoreToperson.setHandleDate(new Date());
		
		// 调用service方法对本区推荐周边店信息进行更新，并返回更新结果
		boolean result = countySuggestStoreService.updateCountySuggestStoreToperson(countySuggestStoreToperson);
		
		if(result){ // 如果更新成功
			
			// 调用系统日志的service方法写入成功信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,attributeCsstp), ConstantStr.M_COUNTY_SUGGEST_STORE_TOPERSON, countySuggestStoreToperson.getId(), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,attributeCsstp));
		}else{ // 如果更新失败
			
			// 调用系统日志的service方法写入失败信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100054,attributeCsstp), ConstantStr.M_COUNTY_SUGGEST_STORE_TOPERSON, countySuggestStoreToperson.getId(), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,attributeCsstp));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本区推荐周边店管理：点击接受推荐
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午2:14:41
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/toSuggest/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toSuggest(@PathVariable Long id, Map<String, Object> map) {
		// 根据本区推荐周边店关系表主键id查询本推荐区店信息
		CountySuggestStore countySuggestStore = countySuggestStoreService.findCountySuggestStoreById(id);
		
		// 根据本区推荐店来源不同获取不同的数据
		if (ConstantStr.SUGGEST_TYPE_1.equals(countySuggestStore.getSuggestType())) {
			// 调用service方法根据主键id获取本网格推荐周边店相关信息
			AroundStoreVo aroundStoreVo = aroundStoreService.findAroundStore(countySuggestStore.getSourceId());
			
			// 将本网格推荐周边店的信息封装在map中写入界面
			map.put("aroundStoreVo", aroundStoreVo);
			
		} else if (ConstantStr.SUGGEST_TYPE_2.equals(countySuggestStore.getSuggestType())) {
			// 调用service方法根据主键id获取本网格推荐周边店相关信息
			AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreService.findAroundSuggestStore(countySuggestStore.getSourceId());
			
			// 将本网格推荐周边店的信息封装在map中写入界面
			map.put("aroundSuggestStoreVo", aroundSuggestStoreVo);
		}
		
		// 将推荐类型封装在map中写入界面
		map.put("type", countySuggestStore.getSuggestType());
		
		// 将来源id封装在map中写入界面
		map.put("sid", countySuggestStore.getSourceId());
		
		// 将数据id封装在map中写入界面
		map.put("countySuggestStoreToPersonId", id);
		
		// 进入再次推荐界面
		return ACCEPT_SUGGEST;
	}
	
	/**
	 * 本区推荐周边店管理：接受推荐本区推荐周边店
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午2:10:08
	 * @param aroundSuggestStoreVo
	 * @param flag
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/acceptSuggest/{flag}/{sid}/{countySuggestStoreToPersonId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String acceptSuggest(AroundSuggestStoreVo aroundSuggestStoreVo, 
			@PathVariable String flag, @PathVariable Long sid, @PathVariable Long countySuggestStoreToPersonId) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 创建信息参数补充对象数组用于信息化参数的补充
		Object[] attributeAss={"本网格推荐周边店"};
		
		// 更新Model中相关共通变量的数据
		this.addAttr(aroundSuggestStoreVo);
		
		// 调用service方法对本区推荐周边店再次推荐
		Map<String, Object> result = countySuggestStoreService.acceptSuggest(aroundSuggestStoreVo, flag, sid, countySuggestStoreToPersonId);
		
		if((boolean) result.get("isSuccess")){ // 如果推荐成功
			
			/* 调用系统日志的service方法写入成功信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, (Long) result.get("aroundSuggestStoreId"), getUser());
			/* 向map中封装成功信息 */
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, attributeAss));
		}else{ // 如果推荐失败
			
			/* 调用系统日志的service方法写入失败信息 */
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100002,attributeAss), ConstantStr.M_AROUND_SUGGEST_STORE, (Long) result.get("aroundSuggestStoreId"), getUser());
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, attributeAss));
		}
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
	 * 本区周边店管理：检查要推荐的店所在区域是否开启推荐
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月10日 下午11:43:16
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("CountySuggestS:manager")
	@RequestMapping(value = "/checkRecommend", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkRecommend(@RequestParam Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		// 调用service方法查看本周边所在区域是否允许推荐
		boolean bl = false;
		
		// 根据本区推荐周边店关系表主键id查询本推荐区店信息
		CountySuggestStore countySuggestStore = countySuggestStoreService.findCountySuggestStoreById(id);
		
		// 根据本区推荐店来源不同获取不同的数据
		if (ConstantStr.SUGGEST_TYPE_1.equals(countySuggestStore.getSuggestType())) {
			// 调用service方法根据主键id获取周边店相关信息
			AroundStore aroundStore = aroundStoreService.get(countySuggestStore.getSourceId());
			// 调用service方法查看本周边所在区域是否允许推荐
			bl = aroundStoreService.checkRecommend(aroundStore);
			
		} else if (ConstantStr.SUGGEST_TYPE_2.equals(countySuggestStore.getSuggestType())) {
			// 调用service方法根据主键id获取本网格推荐周边店相关信息
			AroundSuggestStore aroundSuggestStore = aroundSuggestStoreService.get(countySuggestStore.getSourceId());
			// 调用service方法查看本网格推荐周边所在区域是否允许推荐
			bl = aroundSuggestStoreService.checkRecommend(aroundSuggestStore);
		}
		
		if(bl){ // 如果允许
			
			/* 向map中封装成功信息 */
			map.put("type", "Success");
		}else{ // 如果不允许
			
			/* 向map中封装失败信息 */
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100069));
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
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
