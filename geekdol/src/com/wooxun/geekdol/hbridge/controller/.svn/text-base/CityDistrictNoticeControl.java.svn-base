package com.wooxun.geekdol.hbridge.controller;

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
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hbridge.model.CityDistrictNotice;
import com.wooxun.geekdol.hbridge.service.CityDistrictNoticeService;
import com.wooxun.geekdol.hbridge.vo.CityDistrictNoticeVo;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年7月26日  上午10:36:24 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("cityDistrictNotice")
public class CityDistrictNoticeControl extends BaseController{

    @Autowired
    private CityDistrictNoticeService<CityDistrictNotice> cityDistrictNoticeService;
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
    private UserAreaService<UserArea> userAreaService;
    @Autowired
    private SyslogService<Syslog> syslogService;

    public static final String LIST = "hbridge/cityDistrictNotice/list";
    private static final String ADD = "hbridge/cityDistrictNotice/add";
    private static final String UPDATE = "hbridge/cityDistrictNotice/update";
    private static final String SHOW = "hbridge/cityDistrictNotice/show";
    private static final String ZHIDING = "hbridge/cityDistrictNotice/zhidingForm";
    
    
    
    /**
     * 
     * @Title
     * @Description 跳转到公告界面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:09
     * @return
     */
    @RequiresPermissions("CityDistrictNotice:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list(Model model,HttpServletRequest request,HttpServletResponse response) {
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        //查出用户负责区域，初始化区域等级和名称
        List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
        Map<String,String> al = new HashMap<>();
        al.put(ConstantStr.PROVINCE_CODE, ConstantStr.PROVINCE_CODE_STR);
        al.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
        al.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
        al.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
        al.put(ConstantStr.VILLAGE_CODE, ConstantStr.VILLAGE_CODE_STR);
        if(userAreaList == null || userAreaList.size() < 1){
            model.addAttribute("areaLevel", "");
            model.addAttribute("areaName", "");
        }else{
            model.addAttribute("areaLevel", userAreaList.get(0).getAreaLevel());
            model.addAttribute("areaName", al.get(userAreaList.get(0).getAreaLevel()));
        }
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 打开新增页面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:34
     * @return
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/toAdd", method = {RequestMethod.POST,RequestMethod.GET})
    public String toAdd(Model model,HttpServletRequest request,HttpServletResponse response){
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        //查出用户负责区域，初始化区域等级和名称
        List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
        Map<String,String> al = new HashMap<>();
        al.put(ConstantStr.PROVINCE_CODE, ConstantStr.PROVINCE_CODE_STR);
        al.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
        al.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
        al.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
        al.put(ConstantStr.VILLAGE_CODE, ConstantStr.VILLAGE_CODE_STR);
        if(userAreaList == null || userAreaList.size() < 1){
            model.addAttribute("areaLevel", "");
            model.addAttribute("areaName", "");
        }else{
            model.addAttribute("areaLevel", userAreaList.get(0).getAreaLevel());
            model.addAttribute("areaName", al.get(userAreaList.get(0).getAreaLevel()));
        }
        setToken(model, request);
        return ADD;
    }
    /**
     * 
     * @Title
     * @Description 打开置顶页面
     * @author:张洋
     * @CreateDate:2016年9月10日10:15:23
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/toZhiDing/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toZhiDing(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        CityDistrictNotice cityDistrictNotice = new CityDistrictNotice();
        if(id != null){
            cityDistrictNotice = cityDistrictNoticeService.get(id);
            if(cityDistrictNotice == null){
                model.addAttribute("title", "");
                model.addAttribute("topStart", "");
                model.addAttribute("topEnd", "");
            }else{
                model.addAttribute("title", cityDistrictNotice.getTitle());
                if(cityDistrictNotice.getTopStart() != null){
                    model.addAttribute("topStart", DateUtil.format(cityDistrictNotice.getTopStart(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    model.addAttribute("topStart", "");
                }
                if(cityDistrictNotice.getTopEnd() != null){
                    model.addAttribute("topEnd", DateUtil.format(cityDistrictNotice.getTopEnd(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    model.addAttribute("topEnd", "");
                }
            }
        }
        String areaName = "";
        if(cityDistrictNotice.getAreaLevel().equals(ConstantStr.CITY_CODE)){
            City c = cityService.get(cityDistrictNotice.getAreaId());
            if(c != null){
                areaName = c.getCityName();
            }
        }else if(cityDistrictNotice.getAreaLevel().equals(ConstantStr.COUNTY_CODE)){
            County co = countyService.get(cityDistrictNotice.getAreaId());
            if(co != null){
                areaName = co.getCountyName();
            }
        }else if(cityDistrictNotice.getAreaLevel().equals(ConstantStr.COMMUNITY_CODE)){
            Community comm = communityService.get(cityDistrictNotice.getAreaId());
            if(comm != null){
                areaName = comm.getCommunityName();
            }
        }
        model.addAttribute("areaName", areaName);
        model.addAttribute("id", id);
        setToken(model, request);
        return ZHIDING;
    }
    /**
     * 
     * @Title
     * @Description 打开编辑页面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:28
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/toUpdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        //初始化置顶，区域ID，内容
        if(id != null){
            CityDistrictNotice cityDistrictNotice = cityDistrictNoticeService.get(id);
            model.addAttribute("content", cityDistrictNotice.getContent());
            model.addAttribute("areaId", cityDistrictNotice.getAreaId());
            model.addAttribute("topFlag", cityDistrictNotice.getTopFlag());
        }
        //查出用户负责区域，初始化区域等级和名称
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
        Map<String,String> al = new HashMap<>();
        al.put(ConstantStr.PROVINCE_CODE, ConstantStr.PROVINCE_CODE_STR);
        al.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
        al.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
        al.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
        al.put(ConstantStr.VILLAGE_CODE, ConstantStr.VILLAGE_CODE_STR);
        if(userAreaList == null || userAreaList.size() < 1){
            model.addAttribute("areaLevel", "");
            model.addAttribute("areaName", "");
        }else{
            model.addAttribute("areaLevel", userAreaList.get(0).getAreaLevel());
            model.addAttribute("areaName", al.get(userAreaList.get(0).getAreaLevel()));
        }
        setToken(model, request);
        return UPDATE;
    }
    /**
     * 
     * @Title
     * @Description 打开查看详情页面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:28
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("CityDistrictNotice:view")
    @RequestMapping(value = "/toShow/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toShow(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        Map<String,String> al = new HashMap<>();
        al.put(ConstantStr.PROVINCE_CODE, ConstantStr.PROVINCE_CODE_STR);
        al.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
        al.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
        al.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
        al.put(ConstantStr.VILLAGE_CODE, ConstantStr.VILLAGE_CODE_STR);
        //初始化置顶，区域ID，内容
        if(id != null){
            CityDistrictNotice cityDistrictNotice = cityDistrictNoticeService.get(id);
            model.addAttribute("content", cityDistrictNotice.getContent());
            model.addAttribute("title", cityDistrictNotice.getTitle());
            model.addAttribute("source", cityDistrictNotice.getSource());
            model.addAttribute("areaId", cityDistrictNotice.getAreaId());
            model.addAttribute("topFlag", cityDistrictNotice.getTopFlag());
            if(cityDistrictNotice.getTopStart() != null){
                model.addAttribute("topStartStr", DateUtil.format(cityDistrictNotice.getTopStart(),"yyyy-MM-dd HH:mm:ss"));
            }
            if(cityDistrictNotice.getTopEnd() != null){
                model.addAttribute("topEndStr", DateUtil.format(cityDistrictNotice.getTopEnd(),"yyyy-MM-dd HH:mm:ss"));
            }
            if(cityDistrictNotice.getNoticeTime() != null){
                model.addAttribute("noticeTime", DateUtil.format(cityDistrictNotice.getNoticeTime(),"yyyy-MM-dd HH:mm:ss"));
            }
            model.addAttribute("areaLevel", cityDistrictNotice.getAreaLevel());
            model.addAttribute("areaName", al.get(cityDistrictNotice.getAreaLevel()));
            if(ConstantStr.PROVINCE_CODE.equals(cityDistrictNotice.getAreaLevel())){
                Province pro = provinceService.get(cityDistrictNotice.getAreaId());
                if(pro != null){
                    model.addAttribute("provinceName", pro.getProvinceName());
                }else{
                    model.addAttribute("provinceName", "");
                }
            }
            if(ConstantStr.CITY_CODE.equals(cityDistrictNotice.getAreaLevel())){
                City c = cityService.get(cityDistrictNotice.getAreaId());
                if(c != null){
                    model.addAttribute("cityName", c.getCityName());
                }else{
                    model.addAttribute("cityName", "");
                }
            }
            if(ConstantStr.COUNTY_CODE.equals(cityDistrictNotice.getAreaLevel())){
                County c = countyService.get(cityDistrictNotice.getAreaId());
                if(c != null){
                    model.addAttribute("countyName", c.getCountyName());
                }else{
                    model.addAttribute("countyName", "");
                }
            }
            if(ConstantStr.COMMUNITY_CODE.equals(cityDistrictNotice.getAreaLevel())){
                Community c = communityService.get(cityDistrictNotice.getAreaId());
                if(c != null){
                    model.addAttribute("communityName", c.getCommunityName());
                }else{
                    model.addAttribute("communityName", "");
                }
            }
        }
        return SHOW;
    }
    /**
     * 
     * @Title 公告修改
     * @Description 初始化公告编辑页面
     * @author:张洋
     * @CreateDate:2016年7月27日10:51:16
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //根据ID获取数据
        CityDistrictNotice cityDistrictNotice = cityDistrictNoticeService.get(id);
        CityDistrictNoticeVo cityDistrictNoticeVo = new CityDistrictNoticeVo();
        if(cityDistrictNotice != null){
            BeanUtils.copyProperties(cityDistrictNotice, cityDistrictNoticeVo);
        }
        //格式化各个时间
        if(cityDistrictNoticeVo.getNoticeTime() != null){
            cityDistrictNoticeVo.setNoticeTimeStr(DateUtil.format(cityDistrictNoticeVo.getNoticeTime(),"yyyy-MM-dd HH:mm:ss"));
        }
        if(cityDistrictNoticeVo.getTopStart() != null){
            cityDistrictNoticeVo.setTopStartStr(DateUtil.format(cityDistrictNoticeVo.getTopStart(),"yyyy-MM-dd HH:mm:ss"));
        }
        if(cityDistrictNoticeVo.getTopEnd() != null){
            cityDistrictNoticeVo.setTopEndStr(DateUtil.format(cityDistrictNoticeVo.getTopEnd(),"yyyy-MM-dd HH:mm:ss"));
        }
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        //查出用户负责区域，初始化区域等级和名称
        List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
        Map<String,String> al = new HashMap<>();
        al.put(ConstantStr.PROVINCE_CODE, ConstantStr.PROVINCE_CODE_STR);
        al.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
        al.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
        al.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
        al.put(ConstantStr.VILLAGE_CODE, ConstantStr.VILLAGE_CODE_STR);
        if(userAreaList == null || userAreaList.size() < 1){
            cityDistrictNoticeVo.setAreaLevel("");
            cityDistrictNoticeVo.setAreaName("");
        }else{
            cityDistrictNoticeVo.setAreaLevel(userAreaList.get(0).getAreaLevel());
            cityDistrictNoticeVo.setAreaName(al.get(userAreaList.get(0).getAreaLevel()));
        }
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(cityDistrictNoticeVo);
    }
    
    /**
     * 
     * @Title
     * @Description 增加公告信息
     * @author:张洋
     * @CreateDate:2016年7月26日16:58:08
     * @param cityDistrictNotice
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(CityDistrictNoticeVo cityDistrictNoticeVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"公告"};
        //格式化时间
        if(cityDistrictNoticeVo.getPublishTimeBeginStr() != null && !cityDistrictNoticeVo.getPublishTimeBeginStr().trim().equals("")){
            cityDistrictNoticeVo.setPublishTimeBegin(DateUtil.parse(cityDistrictNoticeVo.getPublishTimeBeginStr()));
        }
        if(cityDistrictNoticeVo.getPublishTimeEndStr() != null && !cityDistrictNoticeVo.getPublishTimeEndStr().trim().equals("")){
            cityDistrictNoticeVo.setPublishTimeEnd(DateUtil.parse(cityDistrictNoticeVo.getPublishTimeEndStr()));
        }
        if(cityDistrictNoticeVo.getNoticeTimeStr() != null && !cityDistrictNoticeVo.getNoticeTimeStr().trim().equals("")){
            cityDistrictNoticeVo.setNoticeTime(DateUtil.parse(cityDistrictNoticeVo.getNoticeTimeStr()));
        }
        //如果置顶，则格式化置顶的相关时间
        if(cityDistrictNoticeVo.getTopFlag() != null && !cityDistrictNoticeVo.getTopFlag().trim().equals("")){
            if(cityDistrictNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                if(cityDistrictNoticeVo.getTopStartStr() != null && !cityDistrictNoticeVo.getTopStartStr().trim().equals("")){
                    cityDistrictNoticeVo.setTopStart(DateUtil.parse(cityDistrictNoticeVo.getTopStartStr()));
                }
                if(cityDistrictNoticeVo.getTopEndStr() != null && !cityDistrictNoticeVo.getTopEndStr().trim().equals("")){
                    cityDistrictNoticeVo.setTopEnd(DateUtil.parse(cityDistrictNoticeVo.getTopEndStr()));
                }
            }
        }
        //初始化时间
        cityDistrictNoticeVo.initArea();
        if(cityDistrictNoticeVo.getAreaId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100043,arr));
            return o.writeValueAsString(map);
        }
        //复制属性并增加新增属性
        CityDistrictNotice cityDistrictNotice = new CityDistrictNotice();
        BeanUtils.copyProperties(cityDistrictNoticeVo, cityDistrictNotice);
        this.addAttr(cityDistrictNotice);
        cityDistrictNotice.setScannedNumber(0L);
        //根据发布状态增加相关发布信息
        if(cityDistrictNotice.getPublishStatus() == null || cityDistrictNotice.getPublishStatus().trim().equals("")){
            cityDistrictNotice.setPublishStatus(ConstantStr.WFB);
        }
        if(cityDistrictNotice.getPublishStatus().equals(ConstantStr.FB)){
            cityDistrictNotice.setPublishTime(new Date());
            cityDistrictNotice.setPublishUser(getUserId());
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = cityDistrictNoticeService.save(cityDistrictNotice);
        if(count>0){
        	map.put("type", "Success");
        	if(ConstantStr.WFB.equals(cityDistrictNotice.getPublishStatus())){
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
        	}else{
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100114,arr));
        	}
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            if(ConstantStr.WFB.equals(cityDistrictNotice.getPublishStatus())){
            	map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
        	}else{
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100115,arr));
        	}
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 置顶公告
     * @Description 置顶公告
     * @author:张洋
     * @CreateDate:2016年9月10日10:51:08
     * @param cityDistrictNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为置顶成功,type=error为置顶失败)
     * @throws Exception
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/saveZhiDing", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveZhiDing(CityDistrictNoticeVo cityDistrictNoticeVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市区办事处公告"};
        ObjectMapper o = new ObjectMapper();
        if(cityDistrictNoticeVo.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        CityDistrictNotice sta = cityDistrictNoticeService.get(cityDistrictNoticeVo.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //根据指定状态判断是否需要格式化置顶的相关时间
        if(cityDistrictNoticeVo.getTopFlag() != null && !cityDistrictNoticeVo.getTopFlag().trim().equals("")){
            if(cityDistrictNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                if(cityDistrictNoticeVo.getTopStartStr() != null && !cityDistrictNoticeVo.getTopStartStr().trim().equals("")){
                    cityDistrictNoticeVo.setTopStart(DateUtil.parse(cityDistrictNoticeVo.getTopStartStr()));
                }
                if(cityDistrictNoticeVo.getTopEndStr() != null && !cityDistrictNoticeVo.getTopEndStr().trim().equals("")){
                    cityDistrictNoticeVo.setTopEnd(DateUtil.parse(cityDistrictNoticeVo.getTopEndStr()));
                }
            }
        }
        //设置更新的相关属性，以及根据发布状态判断是否设置发布相关属性
        CityDistrictNotice cityDistrictNotice = new CityDistrictNotice();
        BeanUtils.copyProperties(cityDistrictNoticeVo, cityDistrictNotice);
        cityDistrictNotice.setUpdEac(sta.getUpdEac());
        this.editAttr(cityDistrictNotice);
        //验证是否提交并更新置顶时间
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = cityDistrictNoticeService.updateSelective(cityDistrictNotice);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.VILLAGENOTICE, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100006, arr, ConstantStr.VILLAGENOTICE, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询公告
     * @author:张洋
     * @CreateDate:2016年7月26日17:28:05
     * @param cityDistrictNoticeVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CityDistrictNotice:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList(CityDistrictNoticeVo cityDistrictNoticeVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        cityDistrictNoticeVo.setUserId(getUserId());
        //如果区域级别是空，则是查找全部级别的
        if(cityDistrictNoticeVo.getAreaLevel() == null || cityDistrictNoticeVo.getAreaLevel().trim().equals("")){
            UserAreaVo userAreaVo = new UserAreaVo();
            userAreaVo.setUserId(getUserId());
            userAreaVo.setPageFlag(true);
            userAreaVo.setStartPage(0);
            userAreaVo.setEndPage(1);
            List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
            if(userAreaList == null || userAreaList.size() < 1){
                cityDistrictNoticeVo.setAreaLevel(null);
            }else{
                cityDistrictNoticeVo.setAreaLevel(userAreaList.get(0).getAreaLevel());
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        cityDistrictNoticeVo.setPageFlag(true);
        cityDistrictNoticeVo.setStartPage((page-1)*rows);
        cityDistrictNoticeVo.setEndPage(rows);
        //格式化时间
        if(cityDistrictNoticeVo.getPublishTimeBeginStr() != null && !cityDistrictNoticeVo.getPublishTimeBeginStr().trim().equals("")){
            if(cityDistrictNoticeVo.getPublishTimeBeginStr().length() < 17){
                cityDistrictNoticeVo.setPublishTimeBeginStr(cityDistrictNoticeVo.getPublishTimeBeginStr() + " 00:00:00");
            }
            cityDistrictNoticeVo.setPublishTimeBegin(DateUtil.parse(cityDistrictNoticeVo.getPublishTimeBeginStr()));
        }if(cityDistrictNoticeVo.getPublishTimeEndStr() != null && !cityDistrictNoticeVo.getPublishTimeEndStr().trim().equals("")){
            if(cityDistrictNoticeVo.getPublishTimeEndStr().length() < 17){
                cityDistrictNoticeVo.setPublishTimeEndStr(cityDistrictNoticeVo.getPublishTimeEndStr() + " 23:59:59");
            }
            cityDistrictNoticeVo.setPublishTimeEnd(DateUtil.parse(cityDistrictNoticeVo.getPublishTimeEndStr()));
        }
        cityDistrictNoticeVo.initArea();
        //如果区域等级为空，默认为市
        if(cityDistrictNoticeVo.getAreaLevel() == null || cityDistrictNoticeVo.getAreaLevel().trim().equals("")){
            cityDistrictNoticeVo.setAreaLevel(ConstantStr.CITY_CODE);
        }
        Map<String,String> levelMap = new HashMap<>();
        levelMap.put(ConstantStr.CITY_CODE, ConstantStr.CITY_CODE_STR);
        levelMap.put(ConstantStr.COUNTY_CODE, ConstantStr.COUNTY_CODE_STR);
        levelMap.put(ConstantStr.COMMUNITY_CODE, ConstantStr.COMMUNITY_CODE_STR);
        if(cityDistrictNoticeVo.getAreaId() != null && cityDistrictNoticeVo.getAreaId().equals(0L)){
            cityDistrictNoticeVo.setAreaId(null);
        }
        cityDistrictNoticeVo.setInsId(getUserId());
        //根据不同的区域等级获取不同的公告
        List<CityDistrictNoticeVo> cityDistrictNoticeListT = new ArrayList<>();
        Long count = 0L;
        if(ConstantStr.CITY_CODE.equals(cityDistrictNoticeVo.getAreaLevel())){
            cityDistrictNoticeListT = cityDistrictNoticeService.queryListCityByParam(cityDistrictNoticeVo);
            count = cityDistrictNoticeService.queryCountCityByParam(cityDistrictNoticeVo);
        }
        if(ConstantStr.COUNTY_CODE.equals(cityDistrictNoticeVo.getAreaLevel())){
            cityDistrictNoticeListT = cityDistrictNoticeService.queryListCountyByParam(cityDistrictNoticeVo);
            count = cityDistrictNoticeService.queryCountCountyByParam(cityDistrictNoticeVo);
        }
        if(ConstantStr.COMMUNITY_CODE.equals(cityDistrictNoticeVo.getAreaLevel())){
            cityDistrictNoticeListT = cityDistrictNoticeService.queryListCommunityByParam(cityDistrictNoticeVo);
            count = cityDistrictNoticeService.queryCountCommunityByParam(cityDistrictNoticeVo);
        }
        for (int i = 0; i < cityDistrictNoticeListT.size(); i++) {
            CityDistrictNoticeVo obj = cityDistrictNoticeListT.get(i);
            obj.setAreaLevelName(levelMap.get(obj.getAreaLevel()));
            cityDistrictNoticeListT.set(i, obj);
        }
        map.put("rows", cityDistrictNoticeListT);
        map.put("total",count);

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title 删除公告，逻辑删除，非物理删除
     * @Description 根据id删除公告，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param cityDistrictNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(CityDistrictNotice cityDistrictNotice,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市政公告"};
        ObjectMapper o = new ObjectMapper();
        if(cityDistrictNotice.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取原有数据
        CityDistrictNotice sta = cityDistrictNoticeService.get(cityDistrictNotice.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性和更新相关属性
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = cityDistrictNoticeService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.CITYDISTRICTNOTICE, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 修改发布状态
     * @Description 修改发布状态
     * @author:张洋
     * @CreateDate:2016年7月27日11:56:48
     * @param cityDistrictNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为成功,type=error为失败)
     * @throws Exception
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String changeStatus(CityDistrictNotice cityDistrictNotice,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市政公告"};
        ObjectMapper o = new ObjectMapper();
        if(cityDistrictNotice.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取原有数据
        CityDistrictNotice sta = cityDistrictNoticeService.get(cityDistrictNotice.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //如果是未发布改为发布，则设置相关属性，并设置编辑属性
        cityDistrictNotice.setUpdEac(sta.getUpdEac());
        if(cityDistrictNotice.getPublishStatus().equals(ConstantStr.FB)){
            cityDistrictNotice.setPublishTime(new Date());
            cityDistrictNotice.setPublishUser(getUserId());
        }else{
            cityDistrictNotice.setPublishTime(null);
        }
        //设置编辑属性并更新
        this.editAttr(cityDistrictNotice);
        int count = cityDistrictNoticeService.updateSelective(cityDistrictNotice);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.CITYDISTRICTNOTICE, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 更新公告
     * @Description 更新公告
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param cityDistrictNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("CityDistrictNotice:manager")
    @RequestMapping(value = "/saveUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveUpdate(CityDistrictNoticeVo cityDistrictNoticeVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市政公告"};
        ObjectMapper o = new ObjectMapper();
        //ADD YK 556 校验区域是否选择区域 ST
        if(ConstantStr.COMMUNITY_CODE.equals(cityDistrictNoticeVo.getAreaLevel())){
            if(cityDistrictNoticeVo.getCommunityId() == null || cityDistrictNoticeVo.getCommunityId() == 0){
            	map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100043));
                return o.writeValueAsString(map);
            }
        }
        if(ConstantStr.COUNTY_CODE.equals(cityDistrictNoticeVo.getAreaLevel())){
        	if(cityDistrictNoticeVo.getCountyId() == null || cityDistrictNoticeVo.getCountyId() == 0){
        		map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100043));
                return o.writeValueAsString(map);
            }
        }
        if(ConstantStr.CITY_CODE.equals(cityDistrictNoticeVo.getAreaLevel())){
            if(cityDistrictNoticeVo.getCityId() == null || cityDistrictNoticeVo.getCityId() == 0){
            	map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100043));
                return o.writeValueAsString(map);
            }
        }
        //ADD YK 556 校验区域是否选择区域 ED
        cityDistrictNoticeVo.initArea();
        //格式化时间
        if(cityDistrictNoticeVo.getNoticeTimeStr() != null && !cityDistrictNoticeVo.getNoticeTimeStr().trim().equals("")){
            cityDistrictNoticeVo.setNoticeTime(DateUtil.parse(cityDistrictNoticeVo.getNoticeTimeStr()));
        }
        //复制属性
        CityDistrictNotice cityDistrictNotice = new CityDistrictNotice();
        BeanUtils.copyProperties(cityDistrictNoticeVo, cityDistrictNotice);
        if(cityDistrictNotice.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //获取原有数据
        CityDistrictNotice sta = cityDistrictNoticeService.get(cityDistrictNotice.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置更新的相关属性
        cityDistrictNotice.setUpdEac(sta.getUpdEac());
        this.editAttr(cityDistrictNotice);
        
        if(cityDistrictNotice.getPublishStatus() != null && !cityDistrictNotice.getPublishStatus().trim().equals("")){
            if(cityDistrictNotice.getPublishStatus().equals(ConstantStr.FB) && sta.getPublishStatus().equals(ConstantStr.WFB)){
                cityDistrictNotice.setPublishTime(new Date());
                cityDistrictNotice.setPublishUser(getUserId());
            }
        }else{
            cityDistrictNotice.setPublishStatus(null);
        }

        //如果置顶，则格式化置顶的相关时间
        if(cityDistrictNoticeVo.getTopFlag() != null && !cityDistrictNoticeVo.getTopFlag().trim().equals("")){
            if(cityDistrictNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                if(cityDistrictNoticeVo.getTopStartStr() != null && !cityDistrictNoticeVo.getTopStartStr().trim().equals("")){
                    cityDistrictNotice.setTopStart(DateUtil.parse(cityDistrictNoticeVo.getTopStartStr()));
                }
                if(cityDistrictNoticeVo.getTopEndStr() != null && !cityDistrictNoticeVo.getTopEndStr().trim().equals("")){
                    cityDistrictNotice.setTopEnd(DateUtil.parse(cityDistrictNoticeVo.getTopEndStr()));
                }
            }
        }else{
            cityDistrictNotice.setTopFlag(null);
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = cityDistrictNoticeService.updateSelective(cityDistrictNotice);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.CITYDISTRICTNOTICE, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 初始化当前登陆用户下区域
     * @author:张洋
     * @CreateDate:2016年7月28日17:06:02
     * @param request
     * @return Json
     * @throws Exception
     */
    @RequestMapping(value = "/initArea")
    @ResponseBody
    public String initProvince(HttpServletRequest request) throws Exception {
        ObjectMapper o = new ObjectMapper();
        List<UserAreaVo> userAreaVoList = new ArrayList<>();
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        //获得用户负责区域
        List<UserArea> list = userAreaService.selectList(userAreaVo);
        if(list.size() > 1){
            UserAreaVo uao = new UserAreaVo();
            uao.setAreaId(0L);
            uao.setAreaName("请选择");
            userAreaVoList.add(uao);
        }
        //根据不同的区域级别来初始化数据
        for (int i = 0; i < list.size(); i++) {
            UserAreaVo uao = new UserAreaVo();
            BeanUtils.copyProperties(list.get(i), uao);
            if(ConstantStr.CITY_CODE.equals(uao.getAreaLevel())){
                City c = cityService.get(uao.getAreaId());
                if(c != null && c.getStatus() != null && c.getStatus().equals(ConstantStr.QY_FLAG)){
                    uao.setAreaName(c.getCityName());
                    userAreaVoList.add(uao);
                }
            }
            if(ConstantStr.COUNTY_CODE.equals(uao.getAreaLevel())){
                County c = countyService.get(uao.getAreaId());
                if(c != null && c.getStatus() != null && c.getStatus().equals(ConstantStr.QY_FLAG)){
                    uao.setAreaName(c.getCountyName());
                    userAreaVoList.add(uao);
                }
            }
            if(ConstantStr.COMMUNITY_CODE.equals(uao.getAreaLevel())){
                Community c = communityService.get(uao.getAreaId());
                if(c != null && c.getStatus() != null && c.getStatus().equals(ConstantStr.QY_FLAG)){
                    uao.setAreaName(c.getCommunityName());
                    userAreaVoList.add(uao);
                }
            }
            if(ConstantStr.VILLAGE_CODE.equals(uao.getAreaLevel())){
                Village c = villageService.get(uao.getAreaId());
                if(c != null && ConstantStr.QY_FLAG.equals(c.getStatus())){
                    uao.setAreaName(c.getVillageName());
                    userAreaVoList.add(uao);
                }
            }
        }
        return o.writeValueAsString(userAreaVoList);
    }
    
    /**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(CityDistrictNotice _temp) {
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
    private void editAttr(CityDistrictNotice _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
    /**
     * 封装用插入日志的方法
     * @Title
     * @Description
     * @author:张洋
     * @CreateDate:2016年8月10日 下午4:00:42
     * @param type 操作类型
     * @param log 日志内容
     * @param arr 操作的内容头
     * @param table 表名
     * @param id 数据ID
     */
    private void insertLog(String type,String log,Object[] arr,String table,Long id){
        syslogService.addLog(type, ComDefine.getMsg(log,arr),table, id, getUser());
    }
}
