package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.CommunityVo;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description 后台系统设置-社区管理
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月18日  上午11:30:35 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("community")
public class CommunityController extends BaseController{
	//private static final Logger LOG = LoggerFactory.getLogger(CommunityController.class);

	public static final String COMMUNITY = "system/community/list";
	public static final String COMMUNITY_ADD = "system/community/communityAdd";
	public static final String COMMUNITY_UPDATE = "system/community/communityEditor";

	@Autowired
	private ProvinceService<Province> provinceService;
	@Autowired
	private CityService<City> cityService;
	@Autowired
    private CountyService<County> countyService;
	@Autowired
	private CommunityService<Community> communityService;
	@Autowired
	private VillageService<Village> villageService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title
	 * @Description 跳转到列表页面
	 * @author:YK
	 * @CreateDate:2016年7月18日 下午2:01:24
	 * @return String
	 */
	@RequiresPermissions("Community:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return COMMUNITY;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 返回列表查询结果
	 * @author:YK
	 * @CreateDate:2016年7月18日 下午2:01:47
	 * @param searchCommunity
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Community:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(CommunityVo searchCommunity,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		List<CommunityVo> communityList = new ArrayList<CommunityVo>();
		Long count = 0l;
		searchCommunity.setPageFlag(true);
		searchCommunity.setStartPage((page - 1) * rows);
		searchCommunity.setEndPage(rows);
		count = communityService.queryCountByParams(searchCommunity);
		communityList = communityService.queryCommunityByParams(searchCommunity);
		
		map.put("rows", communityList); 
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 跳转到新增页面
	 * @author:YK
	 * @CreateDate:2016年7月19日 上午9:46:39
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value = "/toAddCommunity", method = { RequestMethod.GET })
	public String toAddCommunity(Model model) {
		return COMMUNITY_ADD;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 新增保存
	 * @author:YK
	 * @CreateDate:2016年7月19日 上午9:46:23
	 * @param community
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value="saveCommunity",method={RequestMethod.POST})
	public @ResponseBody String saveCommunity(Community community,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr={"社区"};
		ObjectMapper mapper = new ObjectMapper();
		
		// 校验数据库是否存在相同的社区code与名称
		if(hasCommunity(community)){
			map.put("type", "Error");
			map.put("Msg",ComDefine.getMsg(ConstantStr.INFO100030));
		}else{
			community.setStatus(ConstantStr.QY_FLAG);
			this.addAttr(community);
			int res = communityService.save(community);
			if(res > 0){
				map.put("type", "Success");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
				syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.COMMUNITY, community.getCommunityId(), getUser());
			}else{
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
			}
		}
		
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:42:02
	 * @param id
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value = "/toEdit/{id}/{provinceId}/{cityId}/{countyId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toEdit(@PathVariable Long id,@PathVariable Long provinceId,@PathVariable Long cityId,@PathVariable Long countyId,
		Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
		map.put("id", id);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("countyId", countyId);
		
		return COMMUNITY_UPDATE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改初始化社区页面数据
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:42:31
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Community community = communityService.selectCommunity(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(community);
	}
	
	/**
	 * @Title
	 * @Description 更新数据
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:42:55
	 * @param community
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value = "/updateCommunity", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateCommunity(Community community, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		
		Object[] arr={"社区"};
		boolean result = false;
		Community original = communityService.selectCommunity(community.getCommunityId());
		//判断该社区是否已经被删除
		if(original==null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		// 根据该社区下是否有可用小区，禁止变更该社区的区县
		if(hasEffectVillage(original)&&original.getCountyId()!=community.getCountyId()){
			map.put("type", "Error");
			Object[] message = {"变更社区的行政区"};
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100031,message));
			return o.writeValueAsString(map);
		}
		community.setUpdEac(original.getUpdEac());
		this.editAttr(community);
		// 更新社区
		result = communityService.updateCommunity(community);
		if(result){
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.COMMUNITY, community.getCommunityId(), getUser());
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
	 * @Description 社区删除
	 * @author:YK
	 * @CreateDate:2016年7月23日 下午2:23:08
	 * @param community
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value = "/deleteCommunity", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteCommunity(Community community, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"社区"};
		boolean result = false;
		Community original = communityService.selectCommunity(community.getCommunityId());
		// 判断该社区是否被删除
		if(original==null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		community.setUpdEac(original.getUpdEac());
		this.editAttr(community);
		// 删除操作判断：该社区下是否有可用小区
		if(hasEffectVillage(original)){
			map.put("type", "Error");
			Object[] message = {"删除"};
			map.put("Msg",ComDefine.getMsg(ConstantStr.INFO100031,message));
		}else{
			result = communityService.updateCommunity(community);
			if(result){
				syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.COMMUNITY, community.getCommunityId(), getUser());
				map.put("type", "Success");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
			}else{
				map.put("type", "Error");
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
			}
		}
		return o.writeValueAsString(map);
	} 
	
	/**
	 * 
	 * @Title
	 * @Description 禁用启用
	 * @author:YK
	 * @CreateDate:2016年7月23日 下午1:40:48
	 * @param community
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Community:manager")
	@RequestMapping(value = "/rejectOrAcceptCommunity", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String rejectOrAcceptCommunity(Community community, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"社区"};
		boolean result = false;
		Community original = communityService.selectCommunity(community.getCommunityId());
		// 判断该社区是否已经被删除
		if(original==null){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		community.setUpdEac(original.getUpdEac());
		// 根据原数据状态判断能否禁用启用
		if(StringUtils.isNotBlank(community.getStatus())&&community.getStatus().equals(original.getStatus())){
			map.put("type", "Error");
			if(ConstantStr.QY_FLAG.equals(community.getStatus())){
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100013,arr));
			}else if(ConstantStr.JY_FLAG.equals(community.getStatus())){
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100014,arr));
			}
			return o.writeValueAsString(map);
		}
		// 启用状态的数据验证是否可以禁用
		if(ConstantStr.QY_FLAG.equals(original.getStatus())){
			//该社区下是否有可用小区
			if(hasEffectVillage(original)){
				map.put("type", "Error");
				Object[] message = {"禁用"};
				map.put("Msg",ComDefine.getMsg(ConstantStr.INFO100031,message));
				return o.writeValueAsString(map);
			}
		}
		// 禁用状态的数据验证是否可以启用
		if(ConstantStr.JY_FLAG.equals(original.getStatus())){
			County county = countyService.haEffectCounty(original.getCountyId());
			if(county == null){
				 //封装返回信息
	            map.put("type", "Error");
	            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100118));
	            //返回信息
	            return o.writeValueAsString(map);
			}
			// 验证该社区所归属的市是否启用
	        City city = cityService.haEffectCity(original.getCityId());
	        if(city == null){
	        	 //封装返回信息
	            map.put("type", "Error");
	            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100117));
	            //返回信息
	            return o.writeValueAsString(map);
	        }
	        // 验证该社区属的省份是否启用
	 		Province province = provinceService.hasEffectProvince(original.getProvinceId());
	 		if(province == null){
	 			map.put("type", "Error");
	 			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100116));
	 			return o.writeValueAsString(map);
	 		}
		}
		
		this.editAttr(community);
		
		result = communityService.updateCommunity(community);
		if(result){
			map.put("type", "Success");
			if(ConstantStr.QY_FLAG.equals(community.getStatus())){
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007,arr), ConstantStr.COMMUNITY, community.getCommunityId(), getUser());
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100007,arr));
			}else if(ConstantStr.JY_FLAG.equals(community.getStatus())){
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009,arr), ConstantStr.COMMUNITY, community.getCommunityId(), getUser());
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100009,arr));
			}
		}else{
			map.put("type", "Error");
			if(ConstantStr.QY_FLAG.equals(community.getStatus())){
				map.put("Msg",ComDefine.getMsg(ConstantStr.INFO100008,arr));
			}else if(ConstantStr.JY_FLAG.equals(community.getStatus())){
				map.put("Msg",ComDefine.getMsg(ConstantStr.INFO100010,arr));
			}
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 该社区下是否有可用小区
	 * @author:YK
	 * @CreateDate:2016年7月20日 上午11:24:02
	 * @param community
	 * @return boolean
	 */
	private boolean hasEffectVillage(Community community){
		Long villageCount = (long) 0;
		if(StringUtils.isNotBlank(community.getDelFlag())&& ConstantStr.DELETE_N.equals(community.getDelFlag())){
			VillageVo searchVillage = new VillageVo();
			// 设置要删除的社区id
			searchVillage.setCommunityId(community.getCommunityId());
			// 设置启用标示
			searchVillage.setStatus(ConstantStr.QY_FLAG);
			villageCount = villageService.findEffectVillage(searchVillage);
		}
		return villageCount>0?true:false;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 校验数据库是否存在相同的社区code与名称
	 * @author:YK
	 * @CreateDate:2016年7月20日 上午10:09:43
	 * @param community
	 * @return boolean
	 */
	private boolean hasCommunity(Community community){
		CommunityVo searchCommunity = new CommunityVo();
		BeanUtils.copyProperties(community, searchCommunity);
		Long count = communityService.querySameCountByParams(searchCommunity);
		return count>0?true:false;
	}
	
	/**
	 * @Title
	 * @Description 添加用户共通属性
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:44:21
	 * @param _temp
	 */
	private void addAttr(Community _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag("0");
	}
    
    
    /**
     * @Title
     * @Description 修改共同属性
     * @author:YK
     * @CreateDate:2016年8月3日 下午4:44:33
     * @param _temp
     */
	private void editAttr(Community _temp) {
		Long userId = getUserId();
		_temp.setInsId(userId);
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
