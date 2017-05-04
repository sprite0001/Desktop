package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.CityVo;


/**
 * @Title 市管理
 * @Description 市管理里的一些操作(市查询，添加，修改，删除，启用，禁用)
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月18日  上午10:23:07 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("city")
public class CityController extends BaseController {
	
	//private static final Logger LOG = LoggerFactory.getLogger(CityController.class);
	public static final String CITY = "system/city/list";
	public static final String ADD = "system/city/add";
	private static final String UPDATE = "system/city/update";
	
	@Autowired
	private ProvinceService<Province> provinceService;
	@Autowired
	private CityService<City> cityService;
	@Autowired
	private CountyService<County> countyService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 进入市管理的页面
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 上午10:28:03
	 * @return 返回 CITY(system/city/list)页面
	 */
	@RequiresPermissions("City:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String info() {
		return CITY;
	}
	
	
	/**
	 * 
	 * @Title 市添加
	 * @Description 进入市管理增加页面
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午8:16:43
	 * @return 返回ADD(system/city/add)页面
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
	public String Add(){
		return ADD;
	}
	
	
	/**
	 * 
	 * @Title 市添加
	 * @Description 增加市的信息
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午8:23:16
	 * @param cityVo
	 * @param request
	 * @param response
	 * @return JSON数据 (type=success为添加成功，type=error为添加失败)
	 * @throws Exception
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/insert", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String insert( CityVo cityVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = {"市"};
		ObjectMapper o = new ObjectMapper();
		City cityName = new City();
		cityName.setProvinceId(cityVo.getProvinceId());
		cityName.setCityName(cityVo.getCityName());
		Long cityNa = cityService.selectCityCount(cityName);
		City cityCode = new City();
		cityCode.setProvinceId(cityVo.getProvinceId());
		cityCode.setCityCode(cityVo.getCityCode());
		Long cityCo = cityService.selectCityCount(cityCode);
		if(cityNa==0 && cityCo==0){
			City city = new City();
			city.setCityCode(cityVo.getCityCode());
			city.setCityName(cityVo.getCityName());
			city.setOrdering(cityVo.getOrdering());
			city.setStatus(ConstantStr.QY_FLAG);
			city.setProvinceId(cityVo.getProvinceId());
			this.addAttr(city);
			int count = cityService.insertCity(city);
			if(count==1){
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
				syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.CITY, city.getCityId(), getUser());
				return o.writeValueAsString(map);
			} else{
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
				return o.writeValueAsString(map);
			}
		}else if (cityNa>0) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100025,arr));
			return o.writeValueAsString(map);
		}else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100026,arr));
			return o.writeValueAsString(map);
		}
	}
	
	
	/**
	 * 
	 * @Title 市修改
	 * @Description 弹出市管理编辑页面
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午10:47:30
	 * @param model
	 * @param cityId
	 * @param request
	 * @param response
	 * @return 返回UPDATE(system/city/update)页面
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/edit/{cityId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String edit(Model model,@PathVariable Long cityId,HttpServletRequest request,HttpServletResponse response){		
		model.addAttribute("cityId", cityId);
		return UPDATE;
	}
	
	
	/**
	 * 
	 * @Title 市修改
	 * @Description 初始化市编辑页面数据
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午10:47:30
	 * @param cityId
	 * @param request
	 * @param response
	 * @return JSON数据(city对象)
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/findById/{cityId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long cityId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		City city = cityService.selectByid(cityId);
		CityVo cityVo = new CityVo();
		BeanUtils.copyProperties(city, cityVo);
		cityVo.setProvinceName(city.getProvince().getProvinceName());
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(cityVo);
	}
	
	
	/**
	 * 
	 * @Title 市修改
	 * @Description 编辑市管理信息
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 上午10:01:02
	 * @param cityId
	 * @param cityVo
	 * @param request
	 * @param response
	 * @return JSON字符串(type=success为修改成功,type=error为修改失败)
	 * @throws Exception
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/update/{cityId}", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String updateByid(@PathVariable Long cityId, CityVo cityVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		City city = cityService.selectByid(cityId);
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = {"市"};
		ObjectMapper o = new ObjectMapper();
		if(city==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		County county = new County();
		county.setCityId(city.getCityId());
		county.setProvinceId(cityVo.getProvinceId());
		Long countyList = countyService.findByCountyCityId(county);
		if(countyList>0){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100027));
			return o.writeValueAsString(map);
		}
		City cityName = new City();
		cityName.setCityId(cityId);
		cityName.setProvinceId(cityVo.getProvinceId());
		cityName.setCityName(cityVo.getCityName());
		Long cityNa = cityService.selectCityCount(cityName);
		City cityCode = new City();
		cityCode.setCityId(cityId);
		cityCode.setCityCode(cityVo.getCityCode());
		Long cityCo = cityService.selectCityCount(cityCode);
		if(cityNa==0 && cityCo==0){
			city.setCityCode(cityVo.getCityCode());
			city.setCityName(cityVo.getCityName());
			city.setOrdering(cityVo.getOrdering());
			city.setStatus(ConstantStr.QY_FLAG);
			city.setProvinceId(cityVo.getProvinceId());
			this.editAttr(city);
			int count = cityService.updateByid(city);
			if(count==1){
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.CITY, city.getCityId(), getUser());
				return o.writeValueAsString(map);
			} else{
				map.put("type", "Error");
				map.put("msg",ComDefine.getMsg(ConstantStr.INFO100006,arr));
				return o.writeValueAsString(map);
			}
		}else if (cityNa>0) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100028,arr));
			return o.writeValueAsString(map);
		}else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100029,arr));
			return o.writeValueAsString(map);
		}
	}
	
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 按条件查询市信息
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 上午10:31:17
	 * @param CityVo
	 * @param request
	 * @param response
	 * @return JSON字符串
	 * @throws Exception
	 */
	@RequiresPermissions("City:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( CityVo cityVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<CityVo> citylist= new ArrayList<CityVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		cityVo.setPageFlag(true);
		cityVo.setStartPage((page-1)*rows);
		cityVo.setEndPage(rows);			
		count = cityService.findAllCount(cityVo);
		citylist = cityService.findAll(cityVo);
		map.put("rows", citylist);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	
	/**
	 * 
	 * @Title 市修改
	 * @Description 根据ProvinceId修改状态（禁用）
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 上午11:40:29
	 * @param city
	 * @param request
	 * @param response
	 * @return JSON字符串(type=success为禁用成功,type=error为禁用失败)
	 * @throws Exception
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/jystatus", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String jystatus(City city,HttpServletRequest request,HttpServletResponse response) throws Exception{
		City ct = cityService.selectByid(city.getCityId());
		County county = new County();
		county.setCityId(city.getCityId());
		county.setStatus(ConstantStr.QY_FLAG);
		Long countyList = countyService.findByCountyCityId(county);
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = {"市"};
		ObjectMapper o = new ObjectMapper();
		if(ct == null || ct.equals("")){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		if(countyList>0){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100027));
			return o.writeValueAsString(map);
		}
			ct.setStatus(ConstantStr.JY_FLAG);		
			this.editAttr(ct);
			int count = cityService.delete(ct);
		if(count==1){
			map.put("type", "Success");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100009,arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009,arr), ConstantStr.CITY, ct.getCityId(), getUser());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100010,arr));
			return o.writeValueAsString(map);
		}
	}
	
	
	/**
	 * 
	 * @Title 市修改
	 * @Description 根据ProvinceId修改状态（启用）
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 上午11:40:29
	 * @param city
	 * @param request
	 * @param response
	 * @return JSON字符串(type=success为启用成功,type=error为启用失败)
	 * @throws Exception
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/qystatus", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String qystatus(City city,HttpServletRequest request,HttpServletResponse response) throws Exception{
		City ct = cityService.selectByid(city.getCityId());
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"市"};
		if(ct == null || ct.equals("")){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		// 验证该市归属的省份是否启用
		Province province = provinceService.hasEffectProvince(ct.getProvinceId());
		if(province == null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100116));
			return o.writeValueAsString(map);
		}
		ct.setStatus(ConstantStr.QY_FLAG);		
		this.editAttr(ct);
		int count = cityService.delete(ct);
		if(count>0){
			map.put("type", "Success");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100007,arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007,arr), ConstantStr.CITY, ct.getCityId(), getUser());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100008,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 市修改(逻辑删除)
	 * @Description 根据ProvinceId删除市
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 上午11:40:29
	 * @param city
	 * @param request
	 * @param response
	 * @return JSON字符串(type=success为删除成功,type=error为删除失败)
	 * @throws Exception
	 */
	@RequiresPermissions("City:manager")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String delete(City city,HttpServletRequest request,HttpServletResponse response) throws Exception{
		City ct = cityService.selectByid(city.getCityId());
		County county = new County();
		county.setCityId(city.getCityId());
		Long countyList = countyService.findByCountyCityId(county);
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = {"市"};
		ObjectMapper o = new ObjectMapper();
		if(ct == null || ct.equals("")){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		if(countyList>0){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100027));
			return o.writeValueAsString(map);
		}
		ct.setDelFlag(ConstantStr.DELETE_Y);
		this.editAttr(ct);
		int count = cityService.delete(ct);
		if(count==1){
			map.put("type", "Success");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.CITY, city.getCityId(), getUser());
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
	private void addAttr(City _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
    
    
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttr(City _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
