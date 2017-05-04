package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.CooperationApplication;
import com.wooxun.geekdol.geekstore.model.JService;
import com.wooxun.geekdol.geekstore.model.VillageLikeRecord;
import com.wooxun.geekdol.geekstore.service.CooperationApplicationService;
import com.wooxun.geekdol.geekstore.service.JServiceService;
import com.wooxun.geekdol.geekstore.service.VillageLikeRecordService;
import com.wooxun.geekdol.geekstore.vo.CooperationApplicationVo;
import com.wooxun.geekdol.geekstore.vo.JServiceVo;
import com.wooxun.geekdol.geekstore.vo.VillageLikeRecordVo;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.service.AroundStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.QuartersService;
import com.wooxun.geekdol.system.vo.QuartersVo;

/**
 * 
* @Title
* @Description 小区信息查询控制层
* @version 1.0.0
* @Author YK	
* @CreateDate 2016年7月21日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 YK             	2016年7月21日  下午4:24:54 		创建
*==========================================================
*
 */
@Controller
@RequestMapping("quarters")
public class QuartersController extends BaseController{
	//private static final Logger LOG = LoggerFactory.getLogger(QuartersController.class);
	
	@Autowired
	private QuartersService<Village> quartersService;
	@Autowired
	private AroundStoreService<AroundStore> aroundStoreService;
	@Autowired
	private JServiceService<JService> jServiceService;
	@Autowired
	private CooperationApplicationService<CooperationApplication> cooperationApplicationService;
	@Autowired
	private VillageLikeRecordService<VillageLikeRecord> villageLikeRecordService;
	
    private static final String LIST = "system/quarters/list";
    private static final String SURROUNDSTORE_LIST = "system/quarters/surroundstoreList";
    private static final String JSERVICE_LIST = "system/quarters/jServiceList";
    private static final String COOPERATIONAPPLICATION_LIST = "system/quarters/cooperationApplicationList";
    private static final String VILLAGELIKERECORD_LIST = "system/quarters/villageLikeRecordList";
    
    /**
     * 
     * @Title
     * @Description 列表页面初始化
     * @author:YK
     * @CreateDate: 2016年7月21日 下午4:25:19
     * @return String
     */
    @RequiresPermissions("CommunityInfo:view")
    @RequestMapping("list")
    public String list(){
        return LIST;
    }
    
    /**
     * 
     * @Title
     * @Description
     * @author:YK
     * @CreateDate:2016年7月21日 下午4:25:19
     * @param searchVillage
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(QuartersVo searchVillage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<QuartersVo> villageList = new ArrayList<QuartersVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		searchVillage.setPageFlag(true);
		searchVillage.setStartPage((page-1)*rows);
		searchVillage.setEndPage(rows);
		
		count = quartersService.queryQuartersCountByParams(searchVillage);
		
		if(count>0){
			villageList = quartersService.queryQuartersByParams(searchVillage);
		}
		
		map.put("rows", villageList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 周边店页面初始化
	 * @author:YK
	 * @CreateDate:2016年7月22日 上午11:27:56
	 * @param villageId
	 * @return String
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="/aroundStore/{villageId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String aroundStore(@PathVariable Long villageId, HttpServletRequest request, 
    		Map<String, Object> map, HttpServletResponse response){
		map.put("id", villageId);
        return SURROUNDSTORE_LIST;
    }
	
	/**
	 * 
	 * @Title
	 * @Description 周边店
	 * @author:YK
	 * @CreateDate:2016年7月22日 上午11:29:15
	 * @param searchAroundStore
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="findAllAroundStore", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findAllAroundStore(AroundStoreVo searchAroundStore,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<AroundStoreVo> aroundStoreList = new ArrayList<AroundStoreVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		searchAroundStore.setPageFlag(true);
		searchAroundStore.setStartPage((page-1)*rows);
		searchAroundStore.setEndPage(rows);
		
		count = aroundStoreService.findAllAroundStoreCount(searchAroundStore);
		
		if(count!=null&&count>0){
			aroundStoreList = aroundStoreService.findAllAroundStore(searchAroundStore);
		}
		
		map.put("rows", aroundStoreList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询：初始化服务社列表页面
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:43:36
	 * @param villageId
	 * @param request
	 * @param map
	 * @param response
	 * @return String
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="/jService/{villageId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String jService(@PathVariable Long villageId, HttpServletRequest request, 
    		Map<String, Object> map, HttpServletResponse response){
		map.put("id", villageId);
        return JSERVICE_LIST;
    }
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询：服务社查询
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:42:25
	 * @param searchJServiceVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="searchService", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String searchSearch(JServiceVo searchJServiceVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<JServiceVo> jServiceVoList = new ArrayList<JServiceVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		searchJServiceVo.setPageFlag(true);
		searchJServiceVo.setStartPage((page-1)*rows);
		searchJServiceVo.setEndPage(rows);
		
		count = jServiceService.findAllJServiceCount(searchJServiceVo);
		
		if(count!=null&&count>0){
			jServiceVoList = jServiceService.findAllJService(searchJServiceVo);
		}
		
		map.put("rows", jServiceVoList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询：申请列表数据
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:45:28
	 * @param villageId
	 * @param request
	 * @param map
	 * @param response
	 * @return String
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="/cooperationApplication/{villageId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String cooperationApplication(@PathVariable Long villageId, HttpServletRequest request, 
    		Map<String, Object> map, HttpServletResponse response){
		map.put("id", villageId);
        return COOPERATIONAPPLICATION_LIST;
    }
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询：初始化申请列表页面
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:56:29
	 * @param searchCooperationApplication
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="searchCooperationApplication", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String searchCooperationApplication(CooperationApplicationVo searchCooperationApplication,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<CooperationApplicationVo> cooperationApplicationList = new ArrayList<CooperationApplicationVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		searchCooperationApplication.setPageFlag(true);
		searchCooperationApplication.setStartPage((page-1)*rows);
		searchCooperationApplication.setEndPage(rows);
		
		count = cooperationApplicationService.findAllCooperationApplicationCount(searchCooperationApplication);
		
		if(count!=null&&count>0){
			cooperationApplicationList = cooperationApplicationService.findAllCooperationApplication(searchCooperationApplication);
		}
		
		map.put("rows", cooperationApplicationList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询：初始化小区点赞列表页面
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午5:46:38
	 * @param villageId
	 * @param request
	 * @param map
	 * @param response
	 * @return String
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="/villageLikeRecord/{villageId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String villageLikeRecord(@PathVariable Long villageId, HttpServletRequest request, 
    		Map<String, Object> map, HttpServletResponse response){
		map.put("id", villageId);
        return VILLAGELIKERECORD_LIST;
    }
	
	/**
	 * 
	 * @Title
	 * @Description 系统设置--小区查询：小区点赞列表
	 * @author:YK
	 * @CreateDate:2016年7月22日 下午6:12:53
	 * @param villageLikeRecord
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("CommunityInfo:view")
	@RequestMapping(value="searchVillageLikeRecord", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String searchVillageLikeRecord(VillageLikeRecordVo villageLikeRecord,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<VillageLikeRecordVo> villageLikeRecordList = new ArrayList<VillageLikeRecordVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		villageLikeRecord.setPageFlag(true);
		villageLikeRecord.setStartPage((page-1)*rows);
		villageLikeRecord.setEndPage(rows);
		
		count = villageLikeRecordService.findAllVillageLikeRecordCount(villageLikeRecord);
		
		if(count!=null&&count>0){
			villageLikeRecordList = villageLikeRecordService.findAllVillageLikeRecord(villageLikeRecord);
		}
		
		map.put("rows", villageLikeRecordList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
}
