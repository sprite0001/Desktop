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
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.CommunityVo;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

/**
 * @Title
 * @Description 行政区管理控制器
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月18日  下午1:12:04 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("county")
public class CountyController  extends BaseController{
	
	@Autowired
	private ProvinceService<Province> provinceService;
	@Autowired
	private CityService<City> cityService;
    @Autowired
    private CountyService<County> countyService;
    @Autowired
    private CommunityService<Community> communityService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    //返回行政区列表页面
    private static final String LIST = "system/county/list";
    //返回行政区新增页面
    private static final String ADD = "system/county/add";
    //返回行政区修改页面
    private static final String UPDATE="system/county/update";
    
    /**
     * 
     * @Title
     * @Description 跳转到列表页面
     * @author:QZG
     * @CreateDate:2016年7月18日 上午11:27:50
     * @return
     */
    @RequiresPermissions("County:view")
    @RequestMapping("list")
    public String list(){
        //跳转到列表页面
        return LIST;
    }
    
    /**
     * 
     * @Title
     * @Description 返回列表查询结果
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:51:35
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("County:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(CountyQueryVo countyQueryVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //设置查询页面 初始化为1
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //设置每页查询条数  初始化为20
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建区列表类
        List<CountyQueryVo> countyList = new ArrayList<CountyQueryVo>();
        //初始化区列表条数
        Long count = 0l;
        //设置分页标示为true
        countyQueryVo.setPageFlag(true);
        //设置查询起始记录
        countyQueryVo.setStartPage((page-1)*rows);
        //设置查询结束记录
        countyQueryVo.setEndPage(rows);
        //查询满足条件的区列表条数
        count = countyService.findAllCount(countyQueryVo);
        //当列表条数大于0时
        if(count>0){
            //查询满足条件的区列表
            countyList = countyService.findAll(countyQueryVo);
        }
        //封装区列表
        map.put("rows", countyList);
        //封装区列表条数
        map.put("total",count);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 逻辑删除行政区
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:53:01
     * @param storeId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void delete( County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map=new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr={"区"};
        //封装默认提示信息
        map.put("type", "Error");
        map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
        //创建boolean变量 默认为false
        boolean result = false;
        //根据区id查询区信息
        County original=countyService.get(county.getCountyId());
        //设置更新回数
        county.setUpdEac(original.getUpdEac());
        //更新共通字段属性
        this.editAttr(county);
        //删除操作判断：该区下是否有社区
        //创建searchCommunity对象
        CommunityVo searchCommunity=new CommunityVo();
        //设置searchCommunity对象区id
        searchCommunity.setCountyId(county.getCountyId());
        //查询该区下的社区数量
        Long communityCount=communityService.queryCountByParams(searchCommunity);
        //当该区下有社区时
        if(communityCount>0){
            //封装返回信息
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100097));
        }else{
          //逻辑删除区  删除成功 返回true
          result = countyService.deleteCountyById(county);
        }
        //当删除成功时
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            //返回日志信息
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
        } 
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //创建string变量str
        String str=o.writeValueAsString(map);
        //返回信息
        response.getWriter().write(str);
    }
    /**
     * 
     * @Title
     * @Description 跳转到新增页面
     * @author:QZG
     * @CreateDate:2016年7月23日 下午3:59:46
     * @param model
     * @return
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/insert", method = {RequestMethod.GET})
    public String insert(Model model){
        //跳转到区新增页面
        return ADD;
    }
    /**
     * 
     * @Title
     * @Description 新增行政区区保存操作
     * @author:QZG
     * @CreateDate:2016年7月18日 下午5:52:16
     * @param county
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public String add(County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr={"区"};
        //校验数据库是否存在相同的区code与名称
        //查询新增该区编码在数据库中的数量
        Long result=countyService.findCodeCount(county);
        //当已存在区编码数量大于0
        if(result>0){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100017));
        }else
        {  
           //设置区默认状态为启用
           county.setStatus(ConstantStr.QY_FLAG);
           //添加共通字段属性
           this.addAttr(county);
           //保存区信息 返回值大于0 保存成功
           int res=countyService.saveSelective(county);
           //当保存区成功时
           if(res > 0){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
           }else{
            //区保存失败时
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
           }            
        }
        //创建变量
        ObjectMapper mapper = new ObjectMapper();
        //返回信息
        return mapper.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 区更新页面跳转
     * @author:QZG
     * @CreateDate:2016年8月18日 上午11:50:39
     * @param countyId
     * @param provinceId
     * @param cityId
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/toupdate/{countyId}/{provinceId}/{cityId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toupdate(@PathVariable Long countyId,@PathVariable Long provinceId,@PathVariable Long cityId,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response){
        //封装区id
        map.put("countyId", countyId);
        //封装省id
        map.put("provinceId", provinceId);
        //封装市id
        map.put("cityId", cityId);
        //返回区修改页面
        return UPDATE;
    }
    /**
     * 
     * @Title
     * @Description 修改页面初始化数据
     * @author:QZG
     * @CreateDate:2016年7月19日 下午9:35:29
     * @param id
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //根据区id查找区信息
        County county=countyService.get(id);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(county);
    }
    
    
    
    /**
     * 
     * @Title 修改数据
     * @Description
     * @author:QZG
     * @CreateDate:2016年7月19日 下午2:41:35
     * @param county
     * @return
     * @throws Exception
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(County county) throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"区"};
        //根据区id查找区信息
        County original=countyService.get(county.getCountyId());
        //当区信息为空时
        if(original==null){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //当区状态已为禁用时
        if(ConstantStr.JY_FLAG.equals(original.getStatus())){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100014,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //更新判断  不能更新区所属省市
        //创建searchCommunity对象
        CommunityVo searchCommunity=new CommunityVo();
        //设置searchCommunity对象区id
        searchCommunity.setCountyId(county.getCountyId());
        //查询该区下的社区数量
        Long communityCount=communityService.queryCountByParams(searchCommunity);
        //当区下的社区数量大于0 不允许更新所属省市
        if(communityCount>0)
        {
            //当更新的区所属省市不等于区的所属省市时,不允许更新
            if(original.getCityId() != county.getCityId()||original.getProvinceId()!=county.getProvinceId()){
            //封装返回信息
            map.put("type", "Error"); 
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100098));
            //返回信息
            return o.writeValueAsString(map);
          }
        }
        //设置更新回数
        county.setUpdEac(original.getUpdEac());
        //修改共通字段属性
        this.editAttr(county);
        //修改区信息  当返回值大于0时  修改成功
        int result=  countyService.updateSelective(county);
        //当区修改成功时
        if(result>0){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
        }else{
            //区修改失败
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
        } 
        //返回信息
        return o.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 启用
     * @author:QZG
     * @CreateDate:2016年7月20日 上午10:57:22
     * @param county
     * @param request
     * @param response
     * @throws Exception
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/start", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String start( County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"区"};
        //创建boolean变量 默认为false
        boolean result = false;
        //通过区id查找区信息
        County original = countyService.get(county.getCountyId());
        //当区状态已为启用时
        if(ConstantStr.QY_FLAG.equals(original.getStatus())){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100013,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        // 验证该行政区所归属的市是否启用
        City city = cityService.haEffectCity(original.getCityId());
        if(city == null){
        	 //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100117));
            //返回信息
            return o.writeValueAsString(map);
        }
        // 验证该行政区属的省份是否启用
 		Province province = provinceService.hasEffectProvince(original.getProvinceId());
 		if(province == null){
 			map.put("type", "Error");
 			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100116));
 			return o.writeValueAsString(map);
 		}
        //设置更新回数
        county.setUpdEac(original.getUpdEac());
        //更新共通字段属性
        this.editAttr(county);
        //调用区启用方法 返回值为true 启用成功
        result=countyService.start(county);
        //当区启用成功时
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100007,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
        }else{
            //区启用失败
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100008,arr));
        }   
        //返回信息
        return o.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 禁用
     * @author:QZG
     * @CreateDate:2016年7月20日 上午10:57:53
     * @param county
     * @param request
     * @param response
     * @throws Exception
     */
    @RequiresPermissions("County:manager")
    @RequestMapping(value = "/stop", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String stop( County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"区"};
        //创建boolean变量result 默认为false
        boolean result = false;
        //根据区id查询区信息
        County original=countyService.get(county.getCountyId());
        //设置更新回数
        county.setUpdEac(original.getUpdEac());
        //修改共同字段属性
        this.editAttr(county);
        //禁用操作判断：该区下是否有可用社区
        //创建searchCommunity对象
        CommunityVo searchCommunity=new CommunityVo();
        //设置searchCommunity对象的区id
        searchCommunity.setCountyId(county.getCountyId());
        //查询后台可用社区条数
        Long communityCount=communityService.queryCount(searchCommunity);
        //当区下有可用社区时
        if(communityCount>0){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100087));
            //返回信息
            return o.writeValueAsString(map);
        }else{
            //当区状态已为禁用时
            if(ConstantStr.JY_FLAG.equals(original.getStatus())){
                //封装返回信息
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100014,arr));
                //返回信息
                return o.writeValueAsString(map);
            }
           //调用区禁用方法 
           result=countyService.stop(county);
           }
           //当区禁用成功时
           if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100009,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
           }else{
             //区禁用失败
             //封装返回信息
             map.put("type", "Error");
             map.put("msg", ComDefine.getMsg(ConstantStr.INFO100010,arr));
        }   
        //返回信息   
        return o.writeValueAsString(map);
    }
    
    
    
    /**
     * 添加共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(County _temp) {
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
     * @author:863SOFT-QZG
     */
    private void editAttr(County _temp)  {
        Long userId = getUserId();
        _temp.setInsId(userId);
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
