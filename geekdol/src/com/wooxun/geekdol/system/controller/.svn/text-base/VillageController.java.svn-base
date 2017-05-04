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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 小区管理 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年7月18日 下午2:31:23 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("village")
public class VillageController extends BaseController {
	
	private static final String LIST = "system/village/villageList";
	private static final String ADD = "system/village/villageAdd";
	public static final String UPDATE = "system/village/villageEditor";
	
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
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:27:50
	 * @return
	 */
	@RequiresPermissions("Village:view")
	@RequestMapping("list")
	public String list() {
		return LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到新增页面
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:27:50
	 * @return
	 */
	@RequiresPermissions("Village:manager")
	@RequestMapping("toAddVillage")
	public String toAddVillage() {
		return ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 返回列表查询结果
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午3:03:28
	 * @param villageQueryVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Village:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(VillageVo searchVillage, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<VillageVo> villageList = new ArrayList<VillageVo>();
		Long count = 0l;
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		searchVillage.setPageFlag(true);
		searchVillage.setStartPage((page - 1) * rows);
		searchVillage.setEndPage(rows);

		count = villageService.queryCountByParams(searchVillage);

		if (count > 0) {
			villageList = villageService.queryVillageByParams(searchVillage);
		}

		map.put("rows", villageList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到修改页面
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:34:29
	 * @param id
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @param communityId
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Village:manager")
	@RequestMapping(value = "/toEditVillage/{id}/{provinceId}/{cityId}/{countyId}/{communityId}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String toEdit(@PathVariable Long id, @PathVariable Long provinceId, @PathVariable Long cityId,
			@PathVariable Long countyId, @PathVariable Long communityId, Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		map.put("id", id);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("countyId", countyId);
		map.put("communityId", communityId);
		return UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据小区id查到小区
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:35:09
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Village:view")
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Village village = villageService.selectVillage(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(village);
	}

	/**
	 * 
	 * @Title
	 * @Description 新增小区
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:35:52
	 * @param village
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Village:manager")
	@RequestMapping(value = "saveVillage", method = { RequestMethod.POST })
	public @ResponseBody String saveVillage(Village village, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Object[] arr = { "小区" };
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		// 验证相同的社区下面是否有相同名字的小区
		List<Village> villageList = villageService.findVillageByParams(village);
		if (villageList != null && villageList.size() > 0) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100032));
			return mapper.writeValueAsString(map);
		}
		this.addAttr(village);
		int res = villageService.save(village);
		if (res > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001, arr),
					ConstantStr.VILLAGE, village.getVillageId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
		}
		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 修改小区信息
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:36:23
	 * @param village
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Village:manager")
	@RequestMapping(value = "/updateVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String updateVillage(Village village, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "小区" };
		// 如果是删除操作验证小区是不是开过店
		// 康天宇改 去除判断开店校验
		/*
		 * if (StringUtils.isNotBlank(village.getDelFlag()) &&
		 * ConstantStr.DELETE_Y.equals(village.getDelFlag())) { List<Village>
		 * villageList = villageService.validationVillageIsopen(village); if
		 * (villageList != null && villageList.size() > 0) { map.put("type",
		 * "Error"); map.put("msg", ComDefine.getMsg(ConstantStr.INFO100033));
		 * return o.writeValueAsString(map); } }
		 */
		boolean result = false;
		Village original = villageService.selectVillage(village.getVillageId());
		village.setUpdEac(original.getUpdEac());
		this.editAttr(village);
		result = villageService.updateVillage(village);
		if (result) {
			if (StringUtils.isNotBlank(village.getDelFlag()) && ConstantStr.DELETE_Y.equals(village.getDelFlag())) {
				// 为删除操作
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
				syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
						ConstantStr.VILLAGE, village.getVillageId(), getUser());
			} else {
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
						ConstantStr.VILLAGE, village.getVillageId(), getUser());
			}
		} else {
			if (StringUtils.isNotBlank(village.getDelFlag()) && ConstantStr.DELETE_Y.equals(village.getDelFlag())) {
				// 为删除操作
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
			} else {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
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
	@RequiresPermissions("Village:manager")
	@RequestMapping(value = "/rejectOrAcceptVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String rejectOrAcceptVillage(Village village, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = { "小区" };
		boolean result = false;
		Village original = villageService.get(village.getVillageId());
		// 判断小区是否已经被删除
		if (original == null) {
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			return o.writeValueAsString(map);
		}
		village.setUpdEac(original.getUpdEac());
		// 根据原数据状态判断能否禁用启用
		if (StringUtils.isNotBlank(village.getStatus()) && village.getStatus().equals(original.getStatus())) {
			map.put("type", "Error");
			if (ConstantStr.QY_FLAG.equals(village.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100013, arr));
			} else if (ConstantStr.JY_FLAG.equals(village.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100014, arr));
			}
			return o.writeValueAsString(map);
		}
		if (ConstantStr.JY_FLAG.equals(original.getStatus())) {
			Community community = communityService.haEffectCommunity(original.getCommunityId());
			if(community == null){
				 //封装返回信息
	            map.put("type", "Error");
	            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100119));
	            //返回信息
	            return o.writeValueAsString(map);
			}
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
		
		this.editAttr(village);

		result = villageService.updateVillage(village);
		if (result) {
			map.put("type", "Success");
			if (ConstantStr.QY_FLAG.equals(village.getStatus())) {
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007, arr),
						ConstantStr.VILLAGE, village.getVillageId(), getUser());
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100007, arr));
			} else if (ConstantStr.JY_FLAG.equals(village.getStatus())) {
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009, arr),
						ConstantStr.COMMUNITY, village.getVillageId(), getUser());
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100009, arr));
			}
		} else {
			map.put("type", "Error");
			if (ConstantStr.QY_FLAG.equals(village.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100008, arr));
			} else if (ConstantStr.JY_FLAG.equals(village.getStatus())) {
				map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100010, arr));
			}
		}
		return o.writeValueAsString(map);
	}

	/**
	 * 添加用户共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void addAttr(Village _temp) {
		Long userId = getUserId();
		_temp.setStatus(ConstantStr.QY_FLAG);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(Village _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
