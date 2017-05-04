package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.CityVo;
import com.wooxun.geekdol.system.vo.ProvinceQueryVo;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title
 * @Description 用户区域管理控制器
 * @version 1.0.0
 * @Author 张洋
 * @CreateDate 2016年7月22日14:45:37
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	  张洋		   2016年7月22日14:45:41 	        创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("userArea")
public class UserAreaController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserAreaController.class);
	
	public static final String USER = "system/user/villageList";
	
	@Autowired
	private UserService<User> userService;
	
	@Autowired
	private UserAreaService<UserArea> userAreaService;
	
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
	
	private Map<String,String> dataMap = new HashMap<>();
	/**
	 * 
	 * @Title
	 * @Description 创建缓存，方便根据ID翻译出省的名字
	 * 				(暂时放在此处，最好创建一个缓存管理类，方便缓存的管理)
	 * @author:张洋
	 * @CreateDate:2016年7月25日10:49:18
	 * @return
	 * @throws 
	 */
	private void initDataCache(){
		List<Province> ProvinceLs = provinceService.selectList(null);
		if(ProvinceLs != null){
			for (Province province : ProvinceLs) {
				dataMap.put("province"+province.getProvinceId(), province.getProvinceName());
                dataMap.put("province"+province.getProvinceId()+"status", province.getStatus());
			}
		}
	}
	
	/**
	 * 
	 * @Title 根据id和类型返回区域名称
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年8月18日 下午3:03:18
	 * @param type
	 * @param id
	 * @return
	 */
	private String cn(String type,Long id){
		Map<String,String> keyMap = new HashMap<>();
		keyMap.put(ConstantStr.PROVINCE_CODE, "province");
		return this.dataMap.get(keyMap.get(type)+id);
	}

    /**
     * 
     * @Title 根据id和类型返回状态
     * @Description
     * @author:张洋
     * @CreateDate:2016年9月22日09:30:39
     * @param type
     * @param id
     * @return
     */
    private String cnStatus(String type,Long id){
        Map<String,String> keyMap = new HashMap<>();
        keyMap.put(ConstantStr.PROVINCE_CODE, "province");
        return this.dataMap.get(keyMap.get(type)+id+"status");
    }
	
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查询用户负责区域
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:53:10
	 * @param userAreaVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectList",method={RequestMethod.POST})
	@ResponseBody
	public String selectList(UserAreaVo userAreaVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		//获取
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
		//查出用户是哪个级别区域的管理员
        this.initDataCache();
		UserAreaVo usa = new UserAreaVo();
		usa.setUserId(userAreaVo.getUserId());
		usa.setPageFlag(true);
		usa.setStartPage((page-1)*rows);
		usa.setEndPage(rows);
        if(userAreaVo.getAreaLevel() == null || userAreaVo.getAreaLevel().trim().equals("")){
            List<UserArea> ls = userAreaService.selectList(usa);
            if(ls != null && ls.size() > 0){
                userAreaVo.setAreaLevel(ls.get(0).getAreaLevel());
            }else{
                userAreaVo.setAreaLevel(ConstantStr.VILLAGE_CODE);
            }
        }
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String> al = new HashMap<>();
		al.put(ConstantStr.PROVINCE_CODE, ConstantStr.PROVINCE_CODE_STR);
		al.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
		al.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
		al.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
		al.put(ConstantStr.VILLAGE_CODE, ConstantStr.VILLAGE_CODE_STR);
		List<UserAreaVo> userAreaList = new ArrayList<UserAreaVo>();
		List<UserAreaVo> UserAreaVoList = new ArrayList<>();
		userAreaVo.setPageFlag(true);
		userAreaVo.setStartPage((page-1)*rows);
		userAreaVo.setEndPage(rows);
		userAreaVo.initAreaId();
		//条件查询，并将其中的编码翻译成汉字解释，根据不同的区域级别设置查询条件
		Map<String,Object> parm = new HashMap<>();
        //设置本级条件和上级条件
		parm.put("villageName", userAreaVo.getVillageName());
        if(userAreaVo.getCommunityId() != null && userAreaVo.getCommunityId().equals(0L)){
            parm.put("communityId", null);
        }else{
            parm.put("communityId", userAreaVo.getCommunityId());
        }
        if(userAreaVo.getProvinceId() != null && userAreaVo.getProvinceId().equals(0L)){
            parm.put("provinceId", null);
        }else{
            parm.put("provinceId", userAreaVo.getProvinceId());
        }
        if(userAreaVo.getCityId() != null && userAreaVo.getCityId().equals(0L)){
            parm.put("cityId", null);
        }else{
            parm.put("cityId", userAreaVo.getCityId());
        }
        if(userAreaVo.getCountyId() != null && userAreaVo.getCountyId().equals(0L)){
            parm.put("countyId", null);
        }else{
            parm.put("countyId", userAreaVo.getCountyId());
        }
        parm.put("userId", userAreaVo.getUserId());
        parm.put("areaLevel", userAreaVo.getAreaLevel());
        parm.put("pageFlag",true);
        parm.put("startPage",(page-1)*rows);
        parm.put("endPage",rows);
		userAreaList = userAreaService.selectListAndAreaUser(parm);
		Long total = userAreaService.selectCountAndAreaUser(parm);
		if(userAreaVo.getAreaLevel().equals(ConstantStr.PROVINCE_CODE)){
			for (int i = 0; i < userAreaList.size(); i++) {
				UserAreaVo uav = new UserAreaVo();
				UserAreaVo ua = userAreaList.get(i);
				uav.setId(ua.getId());
				uav.setProvinceId(ua.getAreaId());
				uav.setProvinceName(cn(ConstantStr.PROVINCE_CODE,ua.getAreaId()));
				uav.setAreaId(ua.getAreaId());
				uav.setAreaLevel(ua.getAreaLevel());
                uav.setStatus(cnStatus(ConstantStr.PROVINCE_CODE,ua.getAreaId()));
				uav.setAreaLevelName(al.get(uav.getAreaLevel()));
				UserAreaVoList.add(uav);
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.CITY_CODE)){
		    Map<String,Object> parmMap = new HashMap<>();
		    //设置上级条件和本级条件
            if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("provinceId", null);
            }else{
                parmMap.put("provinceId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getCityId() != null && userAreaVo.getCityId().equals(0L)){
                parmMap.put("cityId", null);
            }else{
                parmMap.put("cityId", userAreaVo.getCityId());
            }
            parmMap.put("pageFlag",true);
            parmMap.put("startPage",(page-1)*rows);
            parmMap.put("endPage",rows);
            parmMap.put("userId", userAreaVo.getUserId());
			List<City> list = cityService.findByUserId(parmMap);
			for (int i = 0; i < list.size(); i++) {
				UserAreaVo uav = new UserAreaVo();
				City cityT = list.get(i);
				for (int j = 0; j < userAreaList.size(); j++) {
				    UserAreaVo ua = userAreaList.get(j);
		            //设置区域ID
					if(ua.getAreaId().equals(cityT.getCityId())){
						uav.setId(ua.getId());
		                uav.setProvinceId(cityT.getProvinceId());
		                uav.setProvinceName(cityT.getProvince().getProvinceName());
		                uav.setCityId(cityT.getCityId());
		                uav.setCityName(cityT.getCityName());
		                uav.setAreaId(cityT.getCityId());
		                uav.setStatus(cityT.getStatus());
		                uav.setAreaLevel(userAreaVo.getAreaLevel());
		                uav.setAreaLevelName(al.get(uav.getAreaLevel()));
		                UserAreaVoList.add(uav);
					}
				}
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.COUNTY_CODE)){
            Map<String,Object> parmMap = new HashMap<>();
            //设置上级条件和本级条件
            if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("cityId", null);
            }else{
                parmMap.put("cityId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getCountyId() != null && userAreaVo.getCountyId().equals(0L)){
                parmMap.put("countyId", null);
            }else{
                parmMap.put("countyId", userAreaVo.getCountyId());
            }
            parmMap.put("pageFlag",true);
            parmMap.put("startPage",(page-1)*rows);
            parmMap.put("endPage",rows);
            parmMap.put("userId", userAreaVo.getUserId());
			List<County> list = countyService.findByUserId(parmMap);
			for (int i = 0; i < list.size(); i++) {
				UserAreaVo uav = new UserAreaVo();
				County countyT = list.get(i);
				for (int j = 0; j < userAreaList.size(); j++) {
				    UserAreaVo ua = userAreaList.get(j);
		            //设置区域ID
					if(ua.getAreaId().equals(countyT.getCountyId())){
						uav.setId(ua.getId());
		                uav.setProvinceId(countyT.getProvinceId());
		                uav.setProvinceName(countyT.getProvince().getProvinceName());
		                uav.setCityId(countyT.getCityId());
		                uav.setCityName(countyT.getCity().getCityName());
		                uav.setCountyId(countyT.getCountyId());
		                uav.setCountyName(countyT.getCountyName());
		                uav.setAreaId(countyT.getCountyId());
                        uav.setStatus(countyT.getStatus());
		                uav.setAreaLevel(userAreaVo.getAreaLevel());
		                uav.setAreaLevelName(al.get(uav.getAreaLevel()));
		                UserAreaVoList.add(uav);
					}
				}
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.COMMUNITY_CODE)){
            //设置上级条件和本级条件
            Map<String,Object> parmMap = new HashMap<>();
            if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("countyId", null);
            }else{
                parmMap.put("countyId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getCommunityId() != null && userAreaVo.getCommunityId().equals(0L)){
                parmMap.put("communityId", null);
            }else{
                parmMap.put("communityId", userAreaVo.getCommunityId());
            }
            parmMap.put("pageFlag",true);
            parmMap.put("startPage",(page-1)*rows);
            parmMap.put("endPage",rows);
            parmMap.put("userId", userAreaVo.getUserId());
			List<Community> list = communityService.findByUserId(parmMap);
			for (int i = 0; i < list.size(); i++) {
				UserAreaVo uav = new UserAreaVo();
				Community communityT = list.get(i);
				for (int j = 0; j < userAreaList.size(); j++) {
				    UserAreaVo ua = userAreaList.get(j);
		            //设置区域id
					if(ua.getAreaId().equals(communityT.getCommunityId())){
						uav.setId(ua.getId());
		                uav.setProvinceId(communityT.getProvinceId());
		                uav.setProvinceName(communityT.getProvince().getProvinceName());
		                uav.setCityId(communityT.getCityId());
		                uav.setCityName(communityT.getCity().getCityName());
		                uav.setCountyId(communityT.getCountyId());
		                uav.setCountyName(communityT.getCounty().getCountyName());
		                uav.setCommunityId(communityT.getCommunityId());
		                uav.setCommunityName(communityT.getCommunityName());
		                uav.setAreaId(communityT.getCommunityId());
                        uav.setStatus(communityT.getStatus());
		                uav.setAreaLevel(userAreaVo.getAreaLevel());
		                uav.setAreaLevelName(al.get(uav.getAreaLevel()));
		                UserAreaVoList.add(uav);
					}
				}
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.VILLAGE_CODE)){
			Map<String,Object> parmMap = new HashMap<>();
			//设置本级条件和上级条件
			parmMap.put("villageName", userAreaVo.getVillageName());
			if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("communityId", null);
            }else{
                parmMap.put("communityId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getProvinceId() != null && userAreaVo.getProvinceId().equals(0L)){
                parmMap.put("provinceId", null);
            }else{
                parmMap.put("provinceId", userAreaVo.getProvinceId());
            }
            if(userAreaVo.getCityId() != null && userAreaVo.getCityId().equals(0L)){
                parmMap.put("cityId", null);
            }else{
                parmMap.put("cityId", userAreaVo.getCityId());
            }
            if(userAreaVo.getCountyId() != null && userAreaVo.getCountyId().equals(0L)){
                parmMap.put("countyId", null);
            }else{
                parmMap.put("countyId", userAreaVo.getCountyId());
            }
            //parmMap.put("status", ConstantStr.QY_FLAG);
            parmMap.put("userId", userAreaVo.getUserId());
            parmMap.put("pageFlag",true);
            parmMap.put("startPage",(page-1)*rows);
            parmMap.put("endPage",rows);
			List<Village> list = villageService.selectByUserId(parmMap);
			for (int i = 0; i < list.size(); i++) {
				UserAreaVo uav = new UserAreaVo();
				Village villageT = list.get(i);
				for (int j = 0; j < userAreaList.size(); j++) {
				    UserAreaVo ua = userAreaList.get(j);
					//设置区域id
					if(ua.getAreaId().equals(villageT.getVillageId())){
						uav.setId(ua.getId());
						uav.setProvinceId(villageT.getProvinceId());
		                uav.setProvinceName(villageT.getProvince().getProvinceName());
		                uav.setCityId(villageT.getCityId());
		                uav.setCityName(villageT.getCity().getCityName());
		                uav.setCountyId(villageT.getCountyId());
		                uav.setCountyName(villageT.getCounty().getCountyName());
		                uav.setCommunityId(villageT.getCommunityId());
		                uav.setCommunityName(villageT.getCommunity().getCommunityName());
		                uav.setVillageId(villageT.getVillageId());
		                uav.setVillageName(villageT.getVillageName());
		                uav.setVillageAddress(villageT.getVillageAddress());
		                uav.setAreaId(villageT.getVillageId());
                        uav.setStatus(villageT.getStatus());
		                uav.setAreaLevel(userAreaVo.getAreaLevel());
		                uav.setAreaLevelName(al.get(uav.getAreaLevel()));
		                UserAreaVoList.add(uav);
					}
				}
			}
		}
		map.put("rows", UserAreaVoList);
		map.put("total",total);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存用户负责区域
	 * @author:张洋
	 * @CreateDate:2016年7月25日14:46:12
	 * @param userAreaVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUserArea/{areaId}/{userId}/{areaLevel}",method={RequestMethod.POST})
	@ResponseBody
	public String saveUserArea(@PathVariable String areaId,@PathVariable Long userId,@PathVariable String areaLevel,HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserArea> userAreaList = new ArrayList<UserArea>();
		ObjectMapper o = new ObjectMapper();
        Object[] arr={"用户负责区域"};
		if(areaId == null || areaId.trim().equals("")){
			map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
			return o.writeValueAsString(map);
		}
		if(areaLevel == null || areaLevel.trim().equals("")){
			map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
			return o.writeValueAsString(map);
		}
		if(userId == null){
			map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
			return o.writeValueAsString(map);
		}
		User user = userService.selectByUserId(userId);
		if(user == null){
			map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
			return o.writeValueAsString(map);
		}
		String queryIds = "";
		String[] areaIds = areaId.split(",");
		int num = areaIds.length;
		for (int i = 0; i < areaIds.length; i++) {
			UserArea ua = new UserArea();
			ua.setAreaId(Long.valueOf(areaIds[i]));
			ua.setAreaLevel(areaLevel);
			ua.setUserId(userId);
			addAttr(ua);
			userAreaList.add(ua);
			if(i == areaIds.length - 1){
				queryIds += "'" + areaIds[i] + "'";
			}else{
				queryIds += "'" + areaIds[i] + "'" + ",";
			}
		}
        Map<String,Object> param = new HashMap<>();
        param.put("queryIds", queryIds);
        param.put("userId", userId);
        param.put("areaLevel", areaLevel);
        Map<String,Object> data = new HashMap<>();
		if(ConstantStr.VILLAGE_CODE.equals(areaLevel)){
		    //保存的区域是小区级别，判断已保存的小区和新保存的小区是不是在同一省下
		    Object[] ars={"省","小区"};
		    //先判断新增的小区是不是都在同一省下
            List<Village> selectArea = villageService.selectVillageByIds(param);
            for (Village village : selectArea) {
                data.put(village.getProvinceId().toString(), village.getProvinceId());
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
            //判断新增的小区和已保存的小区是不是都在同一省下
		    List<Map<String, Object>> saveArea = userAreaService.selectVillageByUserId(param);
		    for (Map<String, Object> map2 : saveArea) {
		        data.put(map2.get("province_id").toString(), map2.get("province_id"));
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
		}else if(ConstantStr.COMMUNITY_CODE.equals(areaLevel)){
            //保存的区域是社区级别，判断已保存的社区和新保存的社区是不是在同一县区下
            Object[] ars={"县区","社区"};
            //判断新增的社区是不是都在同一县区下
            List<Community> selectArea = communityService.selectCommunityByIds(param);
            for (Community c : selectArea) {
                data.put(c.getCountyId().toString(), c.getCountyId());
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
            //判断新增的社区和已保存的社区是不是都在同一县区下
            List<Map<String, Object>> saveArea = userAreaService.selectCommunityByUserId(param);
            for (Map<String, Object> map2 : saveArea) {
                data.put(map2.get("county_id").toString(), map2.get("county_id"));
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
		    
		}else if(ConstantStr.COUNTY_CODE.equals(areaLevel)){
            //保存的区域是县区级别，判断已保存的县区和新保存的县区是不是在同一市下
            Object[] ars={"市","县区"};
            //判断新增的县区是不是都在同一市下
            List<County> selectArea = countyService.selectCountyByIds(param);
            for (County c : selectArea) {
                data.put(c.getCityId().toString(), c.getCityId());
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
            //判断新增的县区和已保存的县区是不是都在同一市下
            List<Map<String, Object>> saveArea = userAreaService.selectCountyByUserId(param);
            for (Map<String, Object> map2 : saveArea) {
                data.put(map2.get("city_id").toString(), map2.get("city_id"));
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
            
        }else if(ConstantStr.CITY_CODE.equals(areaLevel)){
            //保存的区域是市级别，判断已保存的市和新保存的市是不是在同一省下
            Object[] ars={"省","市"};
            //判断新增的市是不是都在同一省下
            List<City> selectArea = cityService.selectCityByIds(param);
            for (City c : selectArea) {
                data.put(c.getProvinceId().toString(), c.getProvinceId());
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
            //判断新增的市和和已保存的市是不是都在同一省下
            List<Map<String, Object>> saveArea = userAreaService.selectCityByUserId(param);
            for (Map<String, Object> map2 : saveArea) {
                data.put(map2.get("province_id").toString(), map2.get("province_id"));
            }
            if(data.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100112,ars));
                return o.writeValueAsString(map);
            }
            
        }else if(ConstantStr.PROVINCE_CODE.equals(areaLevel)){
            //保存的区域是省级别，判断已保存的省和新保存的省总数是不是大于1
            List<Map<String, Object>> saveArea = userAreaService.selectProvinceByUserId(param);
            if(saveArea.size() > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100113,arr));
                return o.writeValueAsString(map);
            }
            if((num + saveArea.size()) > 1){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100113,arr));
                return o.writeValueAsString(map);
            }
        }
		//判断是否有已被分配的区域，目前已被分配仍可继续分配，故注释掉
		/*Long count = userAreaService.selectIsManagerCount(queryIds);
		if(count > 0){
			map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100084,arr));
			return o.writeValueAsString(map);
		}*/
		userAreaService.insertList(userAreaList);
		map.put("type", "Success");
        map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查询未分配的区域
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:53:10
	 * @param userAreaVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectListNoUser",method={RequestMethod.POST})
	@ResponseBody
	public String selectListNoUser(UserAreaVo userAreaVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
	    int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //查出用户是哪个级别区域的管理员
        UserAreaVo usa = new UserAreaVo();
        usa.setUserId(userAreaVo.getUserId());
        usa.setPageFlag(true);
        usa.setStartPage((page-1)*rows);
        usa.setEndPage(rows);
        if(userAreaVo.getAreaLevel() == null || userAreaVo.getAreaLevel().trim().equals("")){
            List<UserArea> ls = userAreaService.selectList(usa);
            if(ls != null && ls.size() > 0){
                userAreaVo.setAreaLevel(ls.get(0).getAreaLevel());
            }else{
                userAreaVo.setAreaLevel(ConstantStr.VILLAGE_CODE);
            }
        }
	    Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		List<UserAreaVo> UserAreaVoList = new ArrayList<>();
        Long total = 0L;
        Object[] arr={"用户负责区域"};
        //初始化区域ID
		userAreaVo.initAreaId();
		if(userAreaVo.getAreaLevel() == null || userAreaVo.getAreaLevel().trim().equals("")){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100042,arr));
			return o.writeValueAsString(map);
		}
		//查询出用户已被分配的区域
        /*List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
        Map<String,Long> hm = new HashMap<String,Long>();
        for (int i = 0; i < userAreaList.size(); i++) {
            UserArea ua = userAreaList.get(i);
            hm.put(ua.getAreaLevel() + ua.getAreaId(), ua.getAreaId());
        }*/
		//根据不同省区域级别使用不同的查询方方法获取数据
		if(userAreaVo.getAreaLevel().equals(ConstantStr.PROVINCE_CODE)){
		    //这个是查询未被分配区域的查询方式
			//List<Province> list = provinceService.selectListNoUser(new ProvinceQueryVo());
		    ProvinceQueryVo pro = new ProvinceQueryVo();
		    pro.setPageFlag(true);
		    pro.setStartPage((page-1)*rows);
		    pro.setEndPage(rows);
		    pro.setStatus(ConstantStr.QY_FLAG);
		    pro.setProvinceId(userAreaVo.getProvinceId());
			List<Province> list = provinceService.selectList(pro);
			total = provinceService.selectListCount(pro);
			for (int i = 0; i < list.size(); i++) {
                Province provinceT = list.get(i);
			    /*if(hm.size() > 0 && hm.get(ConstantStr.PROVINCE_CODE + provinceT.getProvinceId()) != null){
			        continue;
			    }*/
				UserAreaVo uav = new UserAreaVo();
				uav.setProvinceId(provinceT.getProvinceId());
				uav.setProvinceName(provinceT.getProvinceName());
				uav.setAreaId(provinceT.getProvinceId());
				uav.setAreaLevel(userAreaVo.getAreaLevel());
				UserAreaVoList.add(uav);
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.CITY_CODE)){
			//List<City> list = cityService.findCityByProviceIdNoUser(userAreaVo.getAreaId());
            //设置本级条件和上级条件
		    CityVo c = new CityVo();
		    City ci = new City();
		    c.setProvinceId(userAreaVo.getAreaId());
		    ci.setProvinceId(userAreaVo.getAreaId());
		    c.setStatus(ConstantStr.QY_FLAG);
            ci.setStatus(ConstantStr.QY_FLAG);
            total = cityService.selectCityCount(ci);
		    c.setPageFlag(true);
	        c.setStartPage((page-1)*rows);
	        c.setEndPage(rows);
	        c.setCityId(userAreaVo.getCityId());
			List<City> list = cityService.selectCity(c);
			for (int i = 0; i < list.size(); i++) {
                City cityT = list.get(i);
                /*if(hm.size() > 0 && hm.get(ConstantStr.CITY_CODE + cityT.getCityId()) != null){
                    continue;
                }*/
				UserAreaVo uav = new UserAreaVo();
				uav.setProvinceId(cityT.getProvinceId());
				uav.setProvinceName(cn(ConstantStr.PROVINCE_CODE,cityT.getProvinceId()));
				uav.setCityId(cityT.getCityId());
				uav.setCityName(cityT.getCityName());
				uav.setAreaId(cityT.getCityId());
				uav.setAreaLevel(userAreaVo.getAreaLevel());
				UserAreaVoList.add(uav);
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.COUNTY_CODE)){
			//List<County> list = countyService.selectByCityIdNoUser(userAreaVo.getAreaId());
		    Map<String,Object> parmMap = new HashMap<>();
            //设置本级条件和上级条件
            if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("cityId", null);
            }else{
                parmMap.put("cityId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getCountyId() != null && userAreaVo.getCountyId().equals(0L)){
                parmMap.put("countyId", null);
            }else{
                parmMap.put("countyId", userAreaVo.getCountyId());
            }
            if(userAreaVo.getProvinceId() != null && userAreaVo.getProvinceId().equals(0L)){
                parmMap.put("provinceId", null);
            }else{
                parmMap.put("provinceId", userAreaVo.getProvinceId());
            }
            parmMap.put("userId", userAreaVo.getUserId());
            parmMap.put("status", ConstantStr.QY_FLAG);
            List<County> listTmp = countyService.selectByCityIdNoUser(parmMap);
            total = Long.valueOf(listTmp.size());
            parmMap.put("pageFlag", true);
            parmMap.put("startPage", (page-1)*rows);
            parmMap.put("endPage", rows);
            List<County> list = countyService.selectByCityIdNoUser(parmMap);
			for (int i = 0; i < list.size(); i++) {
                County countyT = list.get(i);
				UserAreaVo uav = new UserAreaVo();
				uav.setProvinceId(countyT.getProvinceId());
				uav.setProvinceName(cn(ConstantStr.PROVINCE_CODE,countyT.getProvinceId()));
				uav.setCityId(countyT.getCityId());
				uav.setCityName(countyT.getCity().getCityName());
				uav.setCountyId(countyT.getCountyId());
				uav.setCountyName(countyT.getCountyName());
				uav.setAreaId(countyT.getCountyId());
				uav.setAreaLevel(userAreaVo.getAreaLevel());
				UserAreaVoList.add(uav);
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.COMMUNITY_CODE)){
			//List<Community> list = communityService.selectByCountyIdNoUser(userAreaVo.getAreaId());
            Map<String,Object> parmMap = new HashMap<>();
            //设置本级条件和上级条件
            if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("countyId", null);
            }else{
                parmMap.put("countyId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getCommunityId() != null && userAreaVo.getCommunityId().equals(0L)){
                parmMap.put("communityId", null);
            }else{
                parmMap.put("communityId", userAreaVo.getCommunityId());
            }
            if(userAreaVo.getProvinceId() != null && userAreaVo.getProvinceId().equals(0L)){
                parmMap.put("provinceId", null);
            }else{
                parmMap.put("provinceId", userAreaVo.getProvinceId());
            }
            if(userAreaVo.getCityId() != null && userAreaVo.getCityId().equals(0L)){
                parmMap.put("cityId", null);
            }else{
                parmMap.put("cityId", userAreaVo.getCityId());
            }
            parmMap.put("userId", userAreaVo.getUserId());
            parmMap.put("status", ConstantStr.QY_FLAG);
            List<Community> listTmp = communityService.selectByCountyIdNoUser(parmMap);
            total = Long.valueOf(listTmp.size());
            parmMap.put("pageFlag", true);
            parmMap.put("startPage", (page-1)*rows);
            parmMap.put("endPage", rows);
            List<Community> list = communityService.selectByCountyIdNoUser(parmMap);
			for (int i = 0; i < list.size(); i++) {
                Community communityT = list.get(i);
				UserAreaVo uav = new UserAreaVo();
				uav.setProvinceId(communityT.getProvinceId());
				uav.setProvinceName(communityT.getProvince().getProvinceName());
				uav.setCityId(communityT.getCityId());
				uav.setCityName(communityT.getCity().getCityName());
				uav.setCountyId(communityT.getCountyId());
				uav.setCountyName(communityT.getCounty().getCountyName());
				uav.setCommunityId(communityT.getCommunityId());
				uav.setCommunityName(communityT.getCommunityName());
				uav.setAreaId(communityT.getCommunityId());
				uav.setAreaLevel(userAreaVo.getAreaLevel());
				UserAreaVoList.add(uav);
			}
		}
		if(userAreaVo.getAreaLevel().equals(ConstantStr.VILLAGE_CODE)){
			Map<String,Object> parmMap = new HashMap<>();
            //设置本级条件和上级条件
			parmMap.put("villageName", userAreaVo.getVillageName());
            if(userAreaVo.getAreaId() != null && userAreaVo.getAreaId().equals(0L)){
                parmMap.put("communityId", null);
            }else{
                parmMap.put("communityId", userAreaVo.getAreaId());
            }
            if(userAreaVo.getProvinceId() != null && userAreaVo.getProvinceId().equals(0L)){
                parmMap.put("provinceId", null);
            }else{
                parmMap.put("provinceId", userAreaVo.getProvinceId());
            }
            if(userAreaVo.getCityId() != null && userAreaVo.getCityId().equals(0L)){
                parmMap.put("cityId", null);
            }else{
                parmMap.put("cityId", userAreaVo.getCityId());
            }
            if(userAreaVo.getCountyId() != null && userAreaVo.getCountyId().equals(0L)){
                parmMap.put("countyId", null);
            }else{
                parmMap.put("countyId", userAreaVo.getCountyId());
            }
            parmMap.put("status", ConstantStr.QY_FLAG);
            parmMap.put("userId", userAreaVo.getUserId());
            List<Village> listTmp = villageService.selectByCountyIdNoUser(parmMap);
            total = Long.valueOf(listTmp.size());
            parmMap.put("pageFlag", true);
            parmMap.put("startPage", (page-1)*rows);
            parmMap.put("endPage", rows);
			List<Village> list = villageService.selectByCountyIdNoUser(parmMap);
			for (int i = 0; i < list.size(); i++) {
                Village villageT = list.get(i);
				UserAreaVo uav = new UserAreaVo();
				uav.setProvinceId(villageT.getProvinceId());
				uav.setProvinceName(villageT.getProvince().getProvinceName());
				uav.setCityId(villageT.getCityId());
				uav.setCityName(villageT.getCity().getCityName());
				uav.setCountyId(villageT.getCountyId());
				uav.setCountyName(villageT.getCounty().getCountyName());
				uav.setCommunityId(villageT.getCommunityId());
				uav.setCommunityName(villageT.getCommunity().getCommunityName());
				uav.setVillageId(villageT.getVillageId());
				uav.setVillageName(villageT.getVillageName());
				uav.setVillageAddress(villageT.getVillageAddress());
				uav.setAreaId(villageT.getVillageId());
				uav.setAreaLevel(userAreaVo.getAreaLevel());
				UserAreaVoList.add(uav);
			}
		}
		map.put("rows", UserAreaVoList);
		map.put("total",total);
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 打开管理区域界面
	 * @author:张洋
	 * @CreateDate:2016年7月21日17:37:13
	 * @return
	 */
	@RequestMapping(value = "/addList/{userId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String addList(Model model,@PathVariable Long userId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("userId", userId);
		UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(userId);
        //初始化负责的区域级别，没有负责的区域则默认为小区
        List<UserArea> list = userAreaService.selectList(userAreaVo);
        if(list != null && list.size() > 0){
            model.addAttribute("areaLevel", list.get(0).getAreaLevel());
            model.addAttribute("oldAL", list.get(0).getAreaLevel());
        }else{
            model.addAttribute("areaLevel", ConstantStr.VILLAGE_CODE);
            model.addAttribute("oldAL", "");
        }
		return "system/user/villageList";
	}
	
	/**
	 * 
	 * @Title
	 * @Description 打开分配区域界面
	 * @author:张洋
	 * @CreateDate:2016年7月25日09:21:06
	 * @return
	 */
	@RequestMapping(value = "/managerArea/{userId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String managerArea(Model model,@PathVariable Long userId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("userId", userId);
		UserAreaVo userAreaVo = new UserAreaVo();
		userAreaVo.setUserId(userId);
		//初始化负责的区域级别，没有负责的区域则默认为小区
		List<UserArea> list = userAreaService.selectList(userAreaVo);
		if(list != null && list.size() > 0){
		    model.addAttribute("areaLevel", list.get(0).getAreaLevel());
		}else{
            model.addAttribute("areaLevel", ConstantStr.VILLAGE_CODE);
		}
		return "system/user/managerArea";
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除用户负责区域,物理删除
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:56:46
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deleteUserArea", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteUserArea(Model model,UserArea u ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"用户负责区域"};
		int count = userAreaService.delete(u.getId());
		if(count==1){
			map.put("type", "Success");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
			return o.writeValueAsString(map);
		}
	}
	
	
	/**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(UserArea _temp) {
        Long userId = getUserId();
        _temp.setInsYmdhms(new Date());
        _temp.setInsId(userId);
        _temp.setUpdEac(0L);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
        _temp.setDelFlag(ConstantStr.DELETE_N);
    }
}
