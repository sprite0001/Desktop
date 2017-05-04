package com.wooxun.geekdol.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.model.ServiceClassific;
import com.wooxun.geekdol.geekstore.service.ServiceClassificService;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.CommunityVo;
import com.wooxun.geekdol.system.vo.CountyQueryVo;
import com.wooxun.geekdol.system.vo.ProvinceQueryVo;
import com.wooxun.geekdol.system.vo.SysdataVo;
import com.wooxun.geekdol.system.vo.UserAreaVo;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description 公共初始化数据
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. YK 2016年7月18日 下午4:36:02 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("common")
public class CommonController extends BaseController {
	// private static final Logger LOG =
	// LoggerFactory.getLogger(CommonController.class);

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
	private SysDataService<SysData> sysDataService;
	@Autowired
	private ServiceClassificService<ServiceClassific> serviceClassificService;
	@Autowired
	private UserService<User> userService;
	@Autowired
	private UserAreaService<UserArea> userAreaService;

	/**
	 * 
	 * @Title
	 * @Description 初始化所有的省份
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午7:44:54
	 * @param request
	 * @return Json
	 * @throws Exception
	 */
	@RequestMapping(value = "/initProvince")
	@ResponseBody
	public String initProvince(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<Province> provinceList = new ArrayList<Province>();
		Province province = new Province();
		province.setProvinceId(0L);
		province.setProvinceName("全部");
		provinceList.add(province);
		provinceList.addAll(provinceService.selectList(null));
		return o.writeValueAsString(provinceList);
	}

	/**
	 * 
	 * @Title
	 * @Description 初始化所有省份(不包括禁用的省份)
	 * @author:田振兴
	 * @CreateDate:2016年8月17日 下午2:30:27
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initProvinceQY")
	@ResponseBody
	public String initProvinceQY(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<Province> provinceList = new ArrayList<Province>();
		Province province = new Province();
		province.setProvinceId(0L);
		province.setProvinceName("全部");
		provinceList.add(province);
		ProvinceQueryVo provinceQueryVo = new ProvinceQueryVo();
		provinceQueryVo.setStatus(ConstantStr.QY_FLAG);
		provinceList.addAll(provinceService.findProvince(provinceQueryVo));
		return o.writeValueAsString(provinceList);
	}

	/**
	 * 
	 * @Title
	 * @Description 初始化所有的城市
	 * @author:YK
	 * @CreateDate:2016年7月25日 下午4:14:13
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initCity", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String initCity(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<City> cityList = new ArrayList<City>();
		City city = new City();
		city.setCityId(0L);
		city.setCityName("全部");
		cityList.add(city);
		cityList.addAll(cityService.findAllCity());
		return o.writeValueAsString(cityList);
	}

	/**
	 * 
	 * @Title
	 * @Description 跟据type类型映射数据字典的值
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午7:44:36
	 * @param type
	 * @param request
	 * @return Json
	 * @throws Exception
	 */
	@RequestMapping(value = "/initSysData/{type}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String initSysData(@PathVariable String type, HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<SysData> sysDataList = new ArrayList<SysData>();
		SysData sysData = new SysData();
		sysData.setValue("");
		sysData.setLable("全部");
		sysDataList.add(sysData);
		sysDataList.addAll(sysDataService.querySysdataByType(type));
		return o.writeValueAsString(sysDataList);
	}

	/**
	 * 
	 * @Title
	 * @Description 跟据type类型映射数据字典的物流方式(不包括自取和配送)
	 * @author:田振兴
	 * @CreateDate:2016年8月20日 下午5:12:31
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deliveryModel/{type}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deliveryModel(@PathVariable String type, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<SysData> sysDataList = new ArrayList<SysData>();
		SysData sysData = new SysData();
		sysData.setValue("");
		sysData.setLable("全部");
		sysDataList.add(sysData);
		SysdataVo sysdataVo = new SysdataVo();
		// 把物流的配送模式放到LIST里
		List<String> deliveryModeList = new ArrayList<String>();
		deliveryModeList.add(ConstantStr.WLZQ);
		deliveryModeList.add(ConstantStr.XQPS);
		sysdataVo.setDeliveryModeList(deliveryModeList);
		sysdataVo.setType(type);
		sysDataList.addAll(sysDataService.querySysdata(sysdataVo));
		return o.writeValueAsString(sysDataList);
	}

	/**
	 * @Title
	 * @Description 没有全部标签的数据字典
	 * @author:kangtianyu
	 * @CreateDate:2016年8月18日 上午9:39:30
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initSysDataNoAll/{type}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String initSysDataNoAll(@PathVariable String type, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<SysData> sysDataList = sysDataService.querySysdataByType(type);
		return o.writeValueAsString(sysDataList);
	}

	/**
	 * 
	 * @Title
	 * @Description 吉客店服务分类初始化
	 * @author:YK
	 * @CreateDate:2016年7月23日 上午10:19:07
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initJServiceClassific", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String initJServiceClassific(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<ServiceClassific> serviceClassificList = new ArrayList<ServiceClassific>();
		ServiceClassific serviceClassific = new ServiceClassific();
		serviceClassific.setClassificCode("");
		serviceClassific.setClassificName("全部");
		serviceClassificList.add(serviceClassific);
		serviceClassificList.addAll(serviceClassificService.queryServiceClassificByType());
		return o.writeValueAsString(serviceClassificList);
	}

	/**
	 * 
	 * @Title
	 * @Description 获取省份下的所有城市
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午7:44:12
	 * @param proviceId
	 * @param request
	 * @return Json
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCityByProvice/{proviceId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getCityByProvice(@PathVariable Long proviceId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<City> cityList = new ArrayList<City>();
		City city = new City();
		city.setCityId(0L);
		city.setCityName("全部");
		cityList.add(city);
		cityList.addAll(cityService.queryCityByProvice(proviceId));
		return o.writeValueAsString(cityList);
	}

	/**
	 * 
	 * @Title
	 * @Description 获取省份下的所有城市(不包括禁用的)
	 * @author:田振兴
	 * @CreateDate:2016年8月17日 下午3:12:27
	 * @param proviceId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCityByProviceQY/{proviceId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getCityByProviceQY(@PathVariable Long proviceId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<City> cityList = new ArrayList<City>();
		City city = new City();
		city.setCityId(0L);
		city.setCityName("全部");
		cityList.add(city);
		city.setProvinceId(proviceId);
		city.setStatus(ConstantStr.QY_FLAG);
		cityList.addAll(cityService.findCity(city));
		return o.writeValueAsString(cityList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据市id查找市下面的所有区/县
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午7:43:36
	 * @param cityId
	 * @param request
	 * @return Json
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCountryByCity/{cityId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getCountryByCity(@PathVariable Long cityId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		CountyQueryVo countyQueryVo = new CountyQueryVo();
		countyQueryVo.setCityId(cityId);
		List<CountyQueryVo> dataList = countyService.queryCountryByCity(countyQueryVo);
		countyQueryVo.setCityId(0L);
		countyQueryVo.setCountyId(0L);
		countyQueryVo.setCountyName("全部");
		dataList.add(0, countyQueryVo);
		return o.writeValueAsString(dataList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据市id查找市下面的所有区/县(不包括禁用的区县)
	 * @author:田振兴
	 * @CreateDate:2016年8月17日 下午3:18:57
	 * @param cityId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCountryByCityQY/{cityId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getCountryByCityQY(@PathVariable Long cityId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<CountyQueryVo> countyList = new ArrayList<CountyQueryVo>();
		CountyQueryVo countyQueryVo = new CountyQueryVo();
		countyQueryVo.setCityId(0L);
		countyQueryVo.setCountyId(0L);
		countyQueryVo.setCountyName("全部");
		countyList.add(countyQueryVo);
		countyQueryVo.setCityId(cityId);
		countyQueryVo.setStatus(ConstantStr.QY_FLAG);
		countyList.addAll(countyService.queryCountryByCity(countyQueryVo));
		return o.writeValueAsString(countyList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据区/县id查找下面的所有社区
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午7:43:15
	 * @param countryId
	 * @param request
	 * @return Json
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCommunityByCounty/{countryId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getCommunityByCountry(@PathVariable Long countryId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<CommunityVo> communityList = new ArrayList<CommunityVo>();
		CommunityVo communityVo = new CommunityVo();
		communityVo.setCountyId(countryId);
		communityVo.setCommunityId(0L);
		communityVo.setCommunityName("全部");
		communityList.add(communityVo);
		communityList.addAll(communityService.queryCommunityByCountry(communityVo));
		
		return o.writeValueAsString(communityList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据区/县id查找下面的所有社区(不包括禁用的社区)
	 * @author:田振兴
	 * @CreateDate:2016年8月17日 下午3:25:19
	 * @param countryId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCommunityByCountyQY/{countryId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getCommunityByCountyQY(@PathVariable Long countryId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<CommunityVo> communityList = new ArrayList<CommunityVo>();
		CommunityVo communityVo = new CommunityVo();
		communityVo.setCommunityId(0L);
        communityVo.setCommunityName("全部");
        communityList.add(communityVo);
		communityVo.setCountyId(countryId);
		communityVo.setStatus(ConstantStr.QY_FLAG);
		communityList.addAll(communityService.queryCommunityByCountry(communityVo));
		
		
		return o.writeValueAsString(communityList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据社区id获取社区下所有的小区
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午7:42:38
	 * @param communityId
	 * @param request
	 * @return Json
	 * @throws Exception
	 */
	@RequestMapping(value = "/getVillageByCommunity/{communityId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getVillageByCommunity(@PathVariable Long communityId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		VillageVo villageVo = new VillageVo();
		villageVo.setCommunityId(communityId);
		List<VillageVo> villageList = new ArrayList<VillageVo>();
		villageVo.setVillageId(0L);
		villageVo.setVillageName("全部");
		villageList.add(villageVo);
		villageList.addAll(villageService.queryVillageByCommuity(villageVo));
		return o.writeValueAsString(villageList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据市id查找小区
	 * @author:YK
	 * @CreateDate:2016年8月8日 下午2:52:25
	 * @param cityId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getVillageByCity/{cityId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getVillageByCity(@PathVariable Long cityId, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		VillageVo villageVo = new VillageVo();
		villageVo.setCityId(cityId);
		List<VillageVo> villageList = new ArrayList<VillageVo>();
		villageVo.setVillageId(0L);
		villageVo.setVillageName("全部");
		villageList.add(villageVo);
		villageList.addAll(villageService.queryVillageByCommuity(villageVo));
		return o.writeValueAsString(villageList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据类型查找同一类型的用户非分页
	 * @author:YK
	 * @CreateDate:2016年7月30日 上午10:04:01
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserByUserType/{userType}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String getUserByUserType(@PathVariable String userType, HttpServletRequest request)
			throws Exception {
		ObjectMapper o = new ObjectMapper();
		List<User> userList = new ArrayList<User>();
		userList.addAll(userService.selectUserByUserType(userType));
		return o.writeValueAsString(userList);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据当前登录级别查询所管辖范围下的小区
	 * @author:王肖东
	 * @CreateDate:2016年8月3日 下午2:51:03
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectVillage", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String selectVillage(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("userId", getUserId());
		List<Village> villageList = new ArrayList<Village>();
		Village village = new Village();
		village.setVillageId(0L);
		village.setVillageName("全部");
		villageList.add(village);
		List<Village> list = villageService.findByUserId(parmMap);
		villageList.addAll(list);
		return o.writeValueAsString(villageList);
	}

	/**
	 * 
	 * @Title
	 * @Description 铺货、推荐的时候，获取有效(登录人管辖并且开通合作店)的小区
	 * @author:YK
	 * @CreateDate:2016年8月22日 上午10:55:11
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryVillageHasCooperationStore", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String queryVillageHasCooperationStore(HttpServletRequest request) throws Exception {
		ObjectMapper o = new ObjectMapper();
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		if (StringUtils.isBlank(areaLevel)) {
			return o.writeValueAsString("");
		}
		VillageVo villageVo = new VillageVo();
		List<VillageVo> villageList = new ArrayList<VillageVo>();
		villageVo.setUserId(getUserId());
		villageVo.setAreaLevel(areaLevel);
		villageList.addAll(villageService.queryVillageHasCooperationStore(villageVo));
		return o.writeValueAsString(villageList);
	}

	/**
	 * @Title
	 * @Description 获取当前用户的区域级别
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午4:34:06
	 * @return String
	 */
	private String getCurrentAreaLevel() {
		UserAreaVo userAreaVo = new UserAreaVo();
		userAreaVo.setUserId(super.getUserId());
		userAreaVo.setPageFlag(false);
		List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
		if (userAreaList.size() <= 0) {
			return "";
		}
		String areaLevel = userAreaList.get(0).getAreaLevel();
		return areaLevel;
	}
}
