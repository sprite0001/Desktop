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
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.ProvinceQueryVo;

/**
 * @Title 省管理控制器
 * @Description  省管理的一些具体操作(省的查询，添加，修改，删除，禁用，启用)
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月18日  上午10:10:35 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("province")
public class ProvinceController extends BaseController {
	
	@Autowired
	private ProvinceService<Province> provinceService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	@Autowired
	private CityService<City> cityService;
	private static final String LIST = "system/province/list";
	private static final String ADD = "system/province/add";
	private static final String UPDATE = "system/province/update";
	
	/**
	 * 
	 * @Title 省查询
	 * @Description 跳转到省的查询页面
	 * @author:zhougp
	 * @CreateDate:2016年7月18日 上午11:27:50
	 * @return LIST(system/province/list)页面
	 */
	@RequiresPermissions("Province:view")
	@RequestMapping("list")
	public String list(){
		return LIST;
	}
	
	
	/**
	 * 
	 * @Title 省查询
	 * @Description 省查询及按条件查询(返回查询数据)
	 * @author:zhougp
	 * @CreateDate:2016年7月18日 上午11:28:13
	 * @param provinceQueryVo
	 * @param request
	 * @param response
	 * @return JSON数据
	 * @throws Exception
	 */
	@RequiresPermissions("Province:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(ProvinceQueryVo provinceQueryVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Province> provinceList = new ArrayList<Province>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		provinceQueryVo.setPageFlag(true);
		provinceQueryVo.setStartPage((page-1)*rows);
		provinceQueryVo.setEndPage(rows);
		
		count = provinceService.selectListCount(provinceQueryVo);
		
		if(count>0){
			provinceList = provinceService.selectList(provinceQueryVo);
		}
		
		map.put("rows", provinceList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	/**
	 * 
	 * @Title 省添加
	 * @Description 打开新增页面
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午2:06:49
	 * @return ADD(system/province/add)页面
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
	public String Add(){
		return ADD;
	}
	
	
	/**
	 * 
	 * @Title 省添加
	 * @Description 添加省份的具体信息
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午2:18:48
	 * @param cooperationStoreVo
	 * @param request
	 * @param response
	 * @return json数据(type='success'为添加成功，type='error'为添加失败)
	 * @throws Exception
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/insert", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String insert( ProvinceQueryVo provinceQueryVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"省"};
		Province validateName = new Province();
		validateName.setProvinceName(provinceQueryVo.getProvinceName());
		Long proName = provinceService.selectProvince(validateName);
		Province validateCode = new Province();
		validateCode.setProvinceCode(provinceQueryVo.getProvinceCode());
		Long proCode = provinceService.selectProvince(validateCode);
		if(proName==0 && proCode==0){
			Province province = new Province();
			province.setProvinceName(provinceQueryVo.getProvinceName());
			province.setProvinceCode(provinceQueryVo.getProvinceCode());
			province.setStatus(ConstantStr.QY_FLAG);
			province.setOrdering(provinceQueryVo.getOrdering());
			this.addAttr(province);
			int count = provinceService.insertProvince(province);
			if(count==1){
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
				syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001, arr), ConstantStr.PROVINCE, province.getProvinceId(), getUser());
				return o.writeValueAsString(map);
			} else{
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
				return o.writeValueAsString(map);
			}
		}else if (proName>0) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100019,arr));
			return o.writeValueAsString(map);
		}else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100020,arr));
			return o.writeValueAsString(map);
		}
	}
	/**
	 * 
	 * @Title 省修改
	 * @Description 根据ProvinceId修改省份状态（禁用）
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午3:11:21
	 * @param province
	 * @param request
	 * @param response
	 * @return json数据(type='success'为禁用成功，type='error'为禁用失败)
	 * @throws Exception
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/jystatus", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String jystatus(Province province,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Province prov = provinceService.selectByid(province.getProvinceId());
		City city = new City();
		city.setProvinceId(province.getProvinceId());
		city.setStatus(ConstantStr.QY_FLAG);
		Long cityList = cityService.findBycityProvinceIdCount(city);
		Object[] arr={"省"};
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		if(prov == null || prov.equals("")){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		if(cityList>0){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100022));
			return o.writeValueAsString(map);
		}
			prov.setStatus(ConstantStr.JY_FLAG);			
		this.editAttr(prov);
		int count = provinceService.delete(prov);
		if(count==1){
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100009,arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009,arr), ConstantStr.PROVINCE, prov.getProvinceId(), getUser());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100010,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 省修改
	 * @Description 根据ProvinceId修改省份状态（启用）
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午3:11:21
	 * @param province
	 * @param request
	 * @param response
	 * @return json数据(type='success'为启用成功，type='error'为启用失败)
	 * @throws Exception
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/qystatus", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String qystatus(Province province,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Province prov = provinceService.selectByid(province.getProvinceId());
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr={"省"};
		ObjectMapper o = new ObjectMapper();
		if(prov == null || prov.equals("")){
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
			prov.setStatus(ConstantStr.QY_FLAG);			
		this.editAttr(prov);
		int count = provinceService.delete(prov);
		if(count==1){
			map.put("type", "Success");
			map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100007,arr));
			syslogService.addLog(ConstantStr.UPDATE,  ComDefine.getMsg(ConstantStr.INFO100007,arr), ConstantStr.PROVINCE, prov.getProvinceId(), getUser());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100008,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 省修改(逻辑删除)
	 * @Description 根据ProvinceId删除省份
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午3:11:21
	 * @param province
	 * @param request
	 * @param response
	 * @return json数据(type='success'为删除成功，type='error'为删除失败)
	 * @throws Exception
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String delete(Province province,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Province prov = provinceService.selectByid(province.getProvinceId());
		City city = new City();
		city.setProvinceId(province.getProvinceId());
		Long cityList = cityService.findBycityProvinceIdCount(city);
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr={"省"};
		ObjectMapper o = new ObjectMapper();
		if(prov == null || prov.equals("")){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		if(cityList>0){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100022));
			return o.writeValueAsString(map);
		}
			prov.setDelFlag("1");
		this.editAttr(prov);
		int count = provinceService.delete(prov);
		if(count==1){
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.PROVINCE, prov.getProvinceId(), getUser());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 省修改
	 * @Description 弹出编辑页面
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午4:17:50
	 * @param model
	 * @param provinceId
	 * @param request
	 * @param response
	 * @return  返回UPDATE(system/province/update)页面
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/edit/{provinceId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String edit(Model model,@PathVariable Long provinceId,HttpServletRequest request,HttpServletResponse response){
		Province province = provinceService.selectByid(provinceId);
		model.addAttribute("province", province);
		return UPDATE;
	}
	
	/**
	 * 
	 * @Title 省修改
	 * @Description 修改省份具体信息
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午4:51:51
	 * @param model
	 * @param provinceId
	 * @param provinceQueryVo
	 * @param request
	 * @param response
	 * @return json数据(type='success'为修改成功，type='error'为修改失败)
	 * @throws Exception
	 */
	@RequiresPermissions("Province:manager")
	@RequestMapping(value = "/update/{provinceId}", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String updateByid(@PathVariable Long provinceId, ProvinceQueryVo provinceQueryVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Province province = provinceService.selectByid(provinceId);
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr={"省"};
		ObjectMapper o = new ObjectMapper();
		if(province==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}		
		Province validateName = new Province();
		validateName.setProvinceName(provinceQueryVo.getProvinceName());
		validateName.setProvinceId(provinceId);
		Long proName = provinceService.selectProvince(validateName);
		Province validateCode = new Province();
		validateCode.setProvinceId(provinceId);
		validateCode.setProvinceCode(provinceQueryVo.getProvinceCode());
		Long proCode = provinceService.selectProvince(validateCode);
		if(proName==0 && proCode==0){
			province.setProvinceName(provinceQueryVo.getProvinceName());
			province.setProvinceCode(provinceQueryVo.getProvinceCode());
			province.setOrdering(provinceQueryVo.getOrdering());
			province.setStatus(ConstantStr.QY_FLAG);
			this.editAttr(province);
			int count = provinceService.updateByidAndOrder(province);
			if(count==1){
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.PROVINCE, province.getProvinceId(), getUser());
				return o.writeValueAsString(map);
			} else{
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
				return o.writeValueAsString(map);
			}
		}else if (proName>0) {
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100023,arr));
			return o.writeValueAsString(map);
		}else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100024,arr));
			return o.writeValueAsString(map);
		}
	}
		
	/**
	 * 
	 * @Title 省查询
	 * @Description 市管理里查询用到的省下拉框
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午3:43:35
	 * @return JSON数据(List集合)
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProvince",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findProvince() throws Exception{
		List<Province> provinceList = new ArrayList<Province>();
		Province province = new Province();
		province.setProvinceName("全部");
		provinceList.add(province);
		ProvinceQueryVo provinceQueryVo = new ProvinceQueryVo();
		provinceQueryVo.setStatus(ConstantStr.QY_FLAG);
		provinceList.addAll(provinceService.findProvince(provinceQueryVo));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceList", provinceList);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(provinceList);
	}
	
	/**
	 * 
	 * @Title 省查询
	 * @Description 市管理里添加和修改用到的省下拉框
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午3:43:35
	 * @return JSON数据(List集合)
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProvinceAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findProvinceAll() throws Exception{
		List<Province> provinceList = new ArrayList<Province>();
		ProvinceQueryVo provinceQueryVo = new ProvinceQueryVo();
		provinceQueryVo.setStatus(ConstantStr.QY_FLAG);
		provinceList = provinceService.findProvince(provinceQueryVo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceList", provinceList);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(provinceList);
	}
	
	/**
	 * @Title
	 * @Description 判断省名字是否重复
	 * @author:kangtianyu
	 * @CreateDate:2016年9月20日 下午4:25:54
	 * @param provinceName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkProvinceName", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkProvinceName(String provinceName, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		
		// 调用service方法查看省名字是否重复
		boolean bl = provinceService.findProvinceName(provinceName.replaceAll(" ", "").replaceAll("　", ""), id);
		
		if(!bl){ // 如果不存在
			
			/* 向map中封装成功信息 */
			map.put("type", "Success");
		}else{ // 如果存在
			
			/* 向map中封装失败信息 */
			map.put("type", "Error");
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
	
	/**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void addAttr(Province _temp) {
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
	private void editAttr(Province _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
