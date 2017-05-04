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
import com.wooxun.geekdol.common.Jdpush;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hbridge.model.NoticeVillage;
import com.wooxun.geekdol.hbridge.model.VillageNotice;
import com.wooxun.geekdol.hbridge.service.NoticeVillageService;
import com.wooxun.geekdol.hbridge.service.VillageNoticeService;
import com.wooxun.geekdol.hbridge.vo.NoticeVillageVo;
import com.wooxun.geekdol.hbridge.vo.VillageNoticeVo;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.AppUserVillage;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.AppUserService;
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
 * 1.  zhangyang	2016年7月29日14:10:40 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("villageNotice")
public class VillageNoticeControl extends BaseController{

    @Autowired
    private VillageNoticeService<VillageNotice> villageNoticeService;
    @Autowired
    private NoticeVillageService<NoticeVillage> noticeVillageService;
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
    @Autowired
    private AppUserService<AppUser> appUserService;

    public static final String LIST = "hbridge/villageNotice/list";
    private static final String ADD = "hbridge/villageNotice/add";
    private static final String UPDATE = "hbridge/villageNotice/update";
    private static final String ZHIDING = "hbridge/villageNotice/zhidingForm";
    private static final String SHOW = "hbridge/villageNotice/show";
    
    
    
    /**
     * 
     * @Title
     * @Description 跳转到公告界面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:09
     * @return
     */
    @RequiresPermissions("VillageNotice:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list(Model model,HttpServletRequest request,HttpServletResponse response) {
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        //初始化区域等级ID和名称
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
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/toAdd", method = {RequestMethod.POST,RequestMethod.GET})
    public String toAdd(Model model,HttpServletRequest request,HttpServletResponse response){
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        //初始化区域等级ID和名称
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
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/toZhiDing/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toZhiDing(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        VillageNotice villageNotice = new VillageNotice();
        if(id != null){
            villageNotice = villageNoticeService.get(id);
            if(villageNotice == null){
                model.addAttribute("title", "");
                model.addAttribute("topStart", "");
                model.addAttribute("topEnd", "");
            }else{
                model.addAttribute("title", villageNotice.getTitle());
                if(villageNotice.getTopStart() != null){
                    model.addAttribute("topStart", DateUtil.format(villageNotice.getTopStart(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    model.addAttribute("topStart", "");
                }
                if(villageNotice.getTopEnd() != null){
                    model.addAttribute("topEnd", DateUtil.format(villageNotice.getTopEnd(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    model.addAttribute("topEnd", "");
                }
            }
        }
        NoticeVillageVo nv = new NoticeVillageVo();
        nv.setNoticeId(id);
        List<NoticeVillageVo> nvLs = noticeVillageService.queryListWithName(nv);
        String areaName = "";
        if(nvLs != null && nvLs.size() > 0){
            for (int i = 0; i < nvLs.size(); i++) {
                if(i != (nvLs.size() - 1)){
                    areaName = areaName + nvLs.get(i).getVillageName() + ",";
                }else{
                    areaName = areaName + nvLs.get(i).getVillageName();
                }
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
     * @Description 打开置顶页面
     * @author:张洋
     * @CreateDate:2016年9月10日10:15:23
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("VillageNotice:view")
    @RequestMapping(value = "/toShow/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toShow(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        VillageNotice villageNotice = new VillageNotice();
        if(id != null){
            villageNotice = villageNoticeService.get(id);
            if(villageNotice == null){
                model.addAttribute("title", "");
                model.addAttribute("topStart", "");
                model.addAttribute("topEnd", "");
            }else{
                model.addAttribute("title", villageNotice.getTitle());
                model.addAttribute("source", villageNotice.getSource());
                model.addAttribute("content", villageNotice.getContent());
                model.addAttribute("topFlag", villageNotice.getTopFlag());
                if(villageNotice.getNoticeTime() != null){
                	model.addAttribute("noticeTime", DateUtil.format(villageNotice.getNoticeTime(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                	model.addAttribute("noticeTime", "");
                }
                if(villageNotice.getTopStart() != null){
                    model.addAttribute("topStart", DateUtil.format(villageNotice.getTopStart(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    model.addAttribute("topStart", "");
                }
                if(villageNotice.getTopEnd() != null){
                    model.addAttribute("topEnd", DateUtil.format(villageNotice.getTopEnd(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    model.addAttribute("topEnd", "");
                }
            }
        }
        NoticeVillageVo nv = new NoticeVillageVo();
        nv.setNoticeId(id);
        List<NoticeVillageVo> nvLs = noticeVillageService.queryListWithName(nv);
        String areaName = "";
        if(nvLs != null && nvLs.size() > 0){
            for (int i = 0; i < nvLs.size(); i++) {
                if(i != (nvLs.size() - 1)){
                    areaName = areaName + nvLs.get(i).getVillageName() + ",";
                }else{
                    areaName = areaName + nvLs.get(i).getVillageName();
                }
            }
        }
        model.addAttribute("areaName", areaName);
        model.addAttribute("id", id);
        setToken(model, request);
        return SHOW;
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
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/toUpdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        //初始化内容和置顶
        if(id != null){
            VillageNotice villageNotice = villageNoticeService.get(id);
            if(villageNotice == null){
                model.addAttribute("content", "");
                model.addAttribute("topFlag", "");
            }else{
                model.addAttribute("content", villageNotice.getContent());
                model.addAttribute("topFlag", villageNotice.getTopFlag());
            }
        }
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        int page=1;
        int rows=1;
        userAreaVo.setPageFlag(true);
        userAreaVo.setStartPage((page-1)*rows);
        userAreaVo.setEndPage(rows);
        //初始化区域等级ID和名称
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
     * @Title 公告修改
     * @Description 初始化公告编辑页面
     * @author:张洋
     * @CreateDate:2016年7月27日10:51:16
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        VillageNotice villageNotice = villageNoticeService.get(id);
        VillageNoticeVo villageNoticeVo = new VillageNoticeVo();
        if(villageNotice != null){
            BeanUtils.copyProperties(villageNotice, villageNoticeVo);
        }
        //格式化时间
        if(villageNoticeVo.getNoticeTime() != null){
            villageNoticeVo.setNoticeTimeStr(DateUtil.format(villageNoticeVo.getNoticeTime(),"yyyy-MM-dd HH:mm:ss"));
        }
        if(villageNoticeVo.getTopStart() != null){
            villageNoticeVo.setTopStartStr(DateUtil.format(villageNoticeVo.getTopStart(),"yyyy-MM-dd HH:mm:ss"));
        }
        if(villageNoticeVo.getTopEnd() != null){
            villageNoticeVo.setTopEndStr(DateUtil.format(villageNoticeVo.getTopEnd(),"yyyy-MM-dd HH:mm:ss"));
        }
        NoticeVillageVo t = new NoticeVillageVo();
        t.setNoticeId(id);
        //获取已经发布公告的小区并传回前台
        List<NoticeVillageVo> tList = noticeVillageService.queryListWithName(t);
        String villageName = "";
        for (int j = 0; j < tList.size(); j++) {
            if(j == tList.size()-1){
                villageName = villageName + tList.get(j).getVillageName();
            }else{
                villageName = villageName + tList.get(j).getVillageName() + ",";
            }
        }
        villageNoticeVo.setVillageNames(villageName);
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(villageNoticeVo);
    }
    
    /**
     * 
     * @Title
     * @Description 增加公告信息
     * @author:张洋
     * @CreateDate:2016年7月26日16:58:08
     * @param villageNotice
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(VillageNoticeVo villageNoticeVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"公告"};
        List<AppUserVillage> list = new ArrayList<>();
        //格式化时间及制置顶的相关属性
        if(villageNoticeVo.getPublishTimeBeginStr() != null && !villageNoticeVo.getPublishTimeBeginStr().trim().equals("")){
            villageNoticeVo.setPublishTimeBegin(DateUtil.parse(villageNoticeVo.getPublishTimeBeginStr()));
        }
        if(villageNoticeVo.getPublishTimeEndStr() != null && !villageNoticeVo.getPublishTimeEndStr().trim().equals("")){
            villageNoticeVo.setPublishTimeEnd(DateUtil.parse(villageNoticeVo.getPublishTimeEndStr()));
        }
        if(villageNoticeVo.getNoticeTimeStr() != null && !villageNoticeVo.getNoticeTimeStr().trim().equals("")){
            villageNoticeVo.setNoticeTime(DateUtil.parse(villageNoticeVo.getNoticeTimeStr()));
        }
        if(villageNoticeVo.getTopFlag() != null && !villageNoticeVo.getTopFlag().trim().equals("")){
            if(villageNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                if(villageNoticeVo.getTopStartStr() != null && !villageNoticeVo.getTopStartStr().trim().equals("")){
                    villageNoticeVo.setTopStart(DateUtil.parse(villageNoticeVo.getTopStartStr()));
                }
                if(villageNoticeVo.getTopEndStr() != null && !villageNoticeVo.getTopEndStr().trim().equals("")){
                    villageNoticeVo.setTopEnd(DateUtil.parse(villageNoticeVo.getTopEndStr()));
                }
            }
        }
        VillageNotice villageNotice = new VillageNotice();
        BeanUtils.copyProperties(villageNoticeVo, villageNotice);
        //设置新增属性及发布的相关属性
        this.addAttr(villageNotice);
        villageNotice.setScannedNumber(0L);
        if(villageNotice.getPublishStatus() == null || villageNotice.getPublishStatus().trim().equals("")){
            villageNotice.setPublishStatus(ConstantStr.WFB);
        }
        String villageIds = villageNoticeVo.getVillageIds();
        if(villageNotice.getPublishStatus().equals(ConstantStr.FB)){
            villageNotice.setPublishTime(new Date());
            villageNotice.setPublishUser(getUserId());
            //如果此公告置顶并且发布了，则将其推送到到常驻和关注本小区的业主
            if(villageNoticeVo.getTopFlag() != null && !villageNoticeVo.getTopFlag().trim().equals("")){
                if(villageNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                    Date now = new Date();
                    if(villageNoticeVo.getTopStart() != null && villageNoticeVo.getTopEnd() != null && villageNoticeVo.getTopStart().getTime() <= now.getTime()&&villageNoticeVo.getTopEnd().getTime()>now.getTime()){
                        Map<String,Object> param = new HashMap<>();
                        param.put("villageIds", villageIds);
                        list = appUserService.selectByVillageId(param);
                    }
                }
            }
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        Integer count = villageNoticeService.insertBackId(villageNotice,villageIds,getUserId());
        //此处插入是写在service中的，无法获取公告ID，故插入null
        if(count > 0){
            //list内是需要推送的用户列表， villageNotice是要推送的小区公告，将此公告的标题和ID推送给list内的用户，先判断list的长度，长度大于0才推送
        	if (list.size() > 0) {
        		Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
    					ComDefine.getMsg(ConstantStr.APPKEY_USER), villageNotice, list);
			}
            map.put("type", "Success");
            if(ConstantStr.WFB.equals(villageNotice.getPublishStatus())){
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
        	}else{
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100114,arr));
        	}
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100001, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            if(ConstantStr.WFB.equals(villageNotice.getPublishStatus())){
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100002,arr));
        	}else{
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100115,arr));
        	}
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100002, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询公告
     * @author:张洋
     * @CreateDate:2016年7月26日17:28:05
     * @param villageNoticeVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList(VillageNoticeVo villageNoticeVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        Map<String, Object> map = new HashMap<String, Object>();
        villageNoticeVo.setPageFlag(true);
        villageNoticeVo.setStartPage((page-1)*rows);
        villageNoticeVo.setEndPage(rows);
        //格式化时间
        if(villageNoticeVo.getPublishTimeBeginStr() != null && !villageNoticeVo.getPublishTimeBeginStr().trim().equals("")){
            villageNoticeVo.setPublishTimeBegin(DateUtil.parse(villageNoticeVo.getPublishTimeBeginStr()));
        }if(villageNoticeVo.getPublishTimeEndStr() != null && !villageNoticeVo.getPublishTimeEndStr().trim().equals("")){
            villageNoticeVo.setPublishTimeEnd(DateUtil.parse(villageNoticeVo.getPublishTimeEndStr()));
        }
        //获取公告列表及发布的相关小区
        List<VillageNoticeVo> villageNoticeListT = new ArrayList<>();
        //当小区ID为0的时候，代表前台选中的是请选择，而ID为0查询会有问题，故赋空使sql查询略过此条件,而加入所有用户负责小区ID以查出全部
        if(villageNoticeVo.getVillageId() == null || villageNoticeVo.getVillageId().equals(0L)){
            villageNoticeVo.setVillageId(null);
            UserAreaVo userAreaVo = new UserAreaVo();
            userAreaVo.setUserId(getUserId());
            userAreaVo.setAreaLevel(ConstantStr.VILLAGE_CODE);;
            String ids = "";
            List<UserArea> uaList = userAreaService.selectList(userAreaVo);
            for (int i = 0; i < uaList.size(); i++) {
                if(i == (uaList.size() - 1)){
                    ids = ids + uaList.get(i).getAreaId();
                }else{
                    ids = ids + uaList.get(i).getAreaId() + ",";
                }
            }
            villageNoticeVo.setVillageIds(ids);
        }
        villageNoticeVo.setInsId(getUserId());
        //格式化小区公告的相关属性及关联的小区名
        Long count = villageNoticeService.queryCountByParam(villageNoticeVo);
        List<VillageNotice> villageNoticeList = villageNoticeService.queryListByParam(villageNoticeVo);
        if(villageNoticeList .size() > 0){
            for (int i = 0; i < villageNoticeList.size(); i++) {
                VillageNoticeVo obj = new VillageNoticeVo();
                BeanUtils.copyProperties(villageNoticeList.get(i), obj);
                NoticeVillageVo t = new NoticeVillageVo();
                t.setNoticeId(obj.getId());
                List<NoticeVillageVo> tList = noticeVillageService.queryListWithName(t);
                String villageName = "";
                for (int j = 0; j < tList.size(); j++) {
                    if(j == tList.size()-1){
                        villageName = villageName + tList.get(j).getVillageName();
                    }else{
                        villageName = villageName + tList.get(j).getVillageName() + ",";
                    }
                }
                obj.setVillageNames(villageName);
                villageNoticeListT.add(obj);
            }
        }
        map.put("rows", villageNoticeListT);
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
     * @param villageNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(VillageNotice villageNotice,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"小区公告"};
        ObjectMapper o = new ObjectMapper();
        if(villageNotice.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        VillageNotice sta = villageNoticeService.get(villageNotice.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性及更新属性并更新删除
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = villageNoticeService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.VILLAGENOTICE, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.VILLAGENOTICE, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 修改发布状态
     * @Description 修改发布状态
     * @author:张洋
     * @CreateDate:2016年7月27日11:56:48
     * @param villageNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为成功,type=error为失败)
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String changeStatus(VillageNotice villageNotice,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"小区公告"};
        ObjectMapper o = new ObjectMapper();
        List<AppUserVillage> list = new ArrayList<>();
        if(villageNotice.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        VillageNotice sta = villageNoticeService.get(villageNotice.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置发布的相关属性和更新属性并更新发布状态等属性
        villageNotice.setUpdEac(sta.getUpdEac());
        if(villageNotice.getPublishStatus().equals(ConstantStr.FB)){
            villageNotice.setPublishTime(new Date());
            villageNotice.setPublishUser(getUserId());
            if(sta.getTopFlag() != null && !sta.getTopFlag().trim().equals("")){
                if(sta.getTopFlag().equals(ConstantStr.IS_TOP)){
                    Date now = new Date();
                    if(sta.getTopStart() != null && sta.getTopEnd() != null && sta.getTopStart().getTime() <= now.getTime()&&sta.getTopEnd().getTime()>now.getTime()){
                        //查出小区公告关系表，取出此公告关联的小区
                        NoticeVillageVo t = new NoticeVillageVo();
                        t.setNoticeId(sta.getId());
                        List<NoticeVillageVo> tList = noticeVillageService.queryListWithName(t);
                        String villageIds = "";
                        for (int j = 0; j < tList.size(); j++) {
                            if(j == tList.size()-1){
                                villageIds = villageIds + tList.get(j).getVillageId();
                            }else{
                                villageIds = villageIds + tList.get(j).getVillageId() + ",";
                            }
                        }
                        Map<String,Object> param = new HashMap<>();
                        param.put("villageIds", villageIds);
                        list = appUserService.selectByVillageId(param);
                    }
                }
            }
        }else{
            villageNotice.setPublishTime(null);
        }
        this.editAttr(villageNotice);
        int count = villageNoticeService.updateSelective(villageNotice);
        if(count==1){
        	//list内是需要推送的用户列表， villageNotice是要推送的小区公告，将此公告的标题和ID推送给list内的用户，先判断list的长度，长度大于0才推送
        	if (list.size() > 0) {
        		Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
    					ComDefine.getMsg(ConstantStr.APPKEY_USER), villageNotice, list);
			}
        	if(villageNotice.getPublishStatus().equals(ConstantStr.FB)){
        		map.put("type", "Success");
                map.put("msg",ComDefine.getMsg(ConstantStr.INFO100075,arr));
                syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100075,arr), ConstantStr.VILLAGENOTICE, sta.getId(), getUser());
        	} else {
        		map.put("type", "Success");
        		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100077,arr));
        		syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100077,arr), ConstantStr.VILLAGENOTICE, sta.getId(), getUser());
        	}
            
            return o.writeValueAsString(map);
        } else{
        	if(villageNotice.getPublishStatus().equals(ConstantStr.FB)){
        		map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100076));
                insertLog(ConstantStr.UPDATE, ConstantStr.INFO100076, null, ConstantStr.VILLAGENOTICE, sta.getId());
        	} else {
        		map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100078,arr));
                insertLog(ConstantStr.UPDATE, ConstantStr.INFO100078, arr, ConstantStr.VILLAGENOTICE, sta.getId());
        	}
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 更新公告
     * @Description 更新公告
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param villageNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/saveUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveUpdate(VillageNoticeVo villageNoticeVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"小区公告"};
        ObjectMapper o = new ObjectMapper();
        if(villageNoticeVo.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        VillageNotice sta = villageNoticeService.get(villageNoticeVo.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //格式化时间
        if(villageNoticeVo.getPublishTimeBeginStr() != null && !villageNoticeVo.getPublishTimeBeginStr().trim().equals("")){
            villageNoticeVo.setPublishTimeBegin(DateUtil.parse(villageNoticeVo.getPublishTimeBeginStr()));
        }
        if(villageNoticeVo.getPublishTimeEndStr() != null && !villageNoticeVo.getPublishTimeEndStr().trim().equals("")){
            villageNoticeVo.setPublishTimeEnd(DateUtil.parse(villageNoticeVo.getPublishTimeEndStr()));
        }
        if(villageNoticeVo.getNoticeTimeStr() != null && !villageNoticeVo.getNoticeTimeStr().trim().equals("")){
            villageNoticeVo.setNoticeTime(DateUtil.parse(villageNoticeVo.getNoticeTimeStr()));
        }
        //根据指定状态判断是否需要格式化置顶的相关时间
        if(villageNoticeVo.getTopFlag() != null && !villageNoticeVo.getTopFlag().trim().equals("")){
            if(villageNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                if(villageNoticeVo.getTopStartStr() != null && !villageNoticeVo.getTopStartStr().trim().equals("")){
                    villageNoticeVo.setTopStart(DateUtil.parse(villageNoticeVo.getTopStartStr()));
                }
                if(villageNoticeVo.getTopEndStr() != null && !villageNoticeVo.getTopEndStr().trim().equals("")){
                    villageNoticeVo.setTopEnd(DateUtil.parse(villageNoticeVo.getTopEndStr()));
                }
            }
        }
        List<AppUserVillage> list = new ArrayList<>();
        //设置更新的相关属性，以及根据发布状态判断是否设置发布相关属性
        VillageNotice villageNotice = new VillageNotice();
        BeanUtils.copyProperties(villageNoticeVo, villageNotice);
        villageNotice.setUpdEac(sta.getUpdEac());
        this.editAttr(villageNotice);
        String villageIds = villageNoticeVo.getVillageIds();
        if(villageNotice.getPublishStatus() != null && !villageNotice.getPublishStatus().trim().equals("")){
            if(villageNotice.getPublishStatus().equals(ConstantStr.FB) && sta.getPublishStatus().equals(ConstantStr.WFB)){
                villageNotice.setPublishTime(new Date());
                villageNotice.setPublishUser(getUserId());
                if(villageNotice.getTopFlag() != null && !villageNotice.getTopFlag().trim().equals("")){
                    if(villageNotice.getTopFlag().equals(ConstantStr.IS_TOP)){
                        Date now = new Date();
                        if(villageNotice.getTopStart() != null && villageNotice.getTopEnd() != null && villageNotice.getTopStart().getTime() <= now.getTime()&&villageNotice.getTopEnd().getTime()>now.getTime()){
                            //查出小区公告关系表，取出此公告关联的小区
                            Map<String,Object> param = new HashMap<>();
                            param.put("villageIds", villageIds);
                            list = appUserService.selectByVillageId(param);
                        }
                    }
                }
            }
        }
        //更新公告及关联表
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = villageNoticeService.updateBackId(villageNotice,villageIds,getUserId());
        if(count==1){
        	//list内是需要推送的用户列表， villageNotice是要推送的小区公告，将此公告的标题和ID推送给list内的用户，先判断list的长度，长度大于0才推送
        	if (list.size() > 0) {
        		Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
    					ComDefine.getMsg(ConstantStr.APPKEY_USER), villageNotice, list);
			}
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
     * @Title 置顶公告
     * @Description 置顶公告
     * @author:张洋
     * @CreateDate:2016年9月10日10:51:08
     * @param villageNotice
     * @param request
     * @param response
     * @return JSON字符串(type=success为置顶成功,type=error为置顶失败)
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:manager")
    @RequestMapping(value = "/saveZhiDing", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveZhiDing(VillageNoticeVo villageNoticeVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"小区公告"};
        ObjectMapper o = new ObjectMapper();
        if(villageNoticeVo.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        VillageNotice sta = villageNoticeService.get(villageNoticeVo.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //根据指定状态判断是否需要格式化置顶的相关时间
        if(villageNoticeVo.getTopFlag() != null && !villageNoticeVo.getTopFlag().trim().equals("")){
            if(villageNoticeVo.getTopFlag().equals(ConstantStr.IS_TOP)){
                if(villageNoticeVo.getTopStartStr() != null && !villageNoticeVo.getTopStartStr().trim().equals("")){
                    villageNoticeVo.setTopStart(DateUtil.parse(villageNoticeVo.getTopStartStr()));
                }
                if(villageNoticeVo.getTopEndStr() != null && !villageNoticeVo.getTopEndStr().trim().equals("")){
                    villageNoticeVo.setTopEnd(DateUtil.parse(villageNoticeVo.getTopEndStr()));
                }
            }
        }
        //设置更新的相关属性，以及根据发布状态判断是否设置发布相关属性
        VillageNotice villageNotice = new VillageNotice();
        BeanUtils.copyProperties(villageNoticeVo, villageNotice);
        villageNotice.setUpdEac(sta.getUpdEac());
        this.editAttr(villageNotice);
        List<AppUserVillage> list = new ArrayList<>();
        //判断是否需要推送
        if(sta.getPublishStatus().equals(ConstantStr.FB)){
            sta.setPublishTime(new Date());
            sta.setPublishUser(getUserId());
            if(sta.getTopFlag() != null && !sta.getTopFlag().trim().equals("")){
                if(sta.getTopFlag().equals(ConstantStr.IS_TOP)){
                    Date now = new Date();
                    if(sta.getTopStart() != null && sta.getTopEnd() != null && sta.getTopStart().getTime() <= now.getTime()&&sta.getTopEnd().getTime()>now.getTime()){
                        //查出小区公告关系表，取出此公告关联的小区
                        NoticeVillageVo t = new NoticeVillageVo();
                        t.setNoticeId(sta.getId());
                        List<NoticeVillageVo> tList = noticeVillageService.queryListWithName(t);
                        String villageIds = "";
                        for (int j = 0; j < tList.size(); j++) {
                            if(j == tList.size()-1){
                                villageIds = villageIds + tList.get(j).getVillageId();
                            }else{
                                villageIds = villageIds + tList.get(j).getVillageId() + ",";
                            }
                        }
                        Map<String,Object> param = new HashMap<>();
                        param.put("villageIds", villageIds);
                        list = appUserService.selectByVillageId(param);
                    }
                }
            }
        }else{
            villageNotice.setPublishTime(null);
        }
        //验证是否提交并更新置顶时间
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = villageNoticeService.updateSelective(villageNotice);
        if(count==1){
        	//list内是需要推送的用户列表， villageNotice是要推送的小区公告，将此公告的标题和ID推送给list内的用户，先判断list的长度，长度大于0才推送
        	if (list.size() > 0) {
        		Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
    					ComDefine.getMsg(ConstantStr.APPKEY_USER), villageNotice, list);
			}
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
     * @Description 初始化编辑小区公告时公告所属小区
     * @author:张洋
     * @CreateDate:2016年7月29日17:41:15
     * @param request
     * @return Json
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:view")
    @RequestMapping(value = "/initSelectArea/{id}")
    @ResponseBody
    public String initProvince(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {
        ObjectMapper o = new ObjectMapper();
        NoticeVillageVo t = new NoticeVillageVo();
        t.setNoticeId(id);
        //查找小区名
        List<NoticeVillageVo> tList = noticeVillageService.queryListWithName(t);
        return o.writeValueAsString(tList);
    }
    /**
     * 
     * @Title
     * @Description 初始化编辑小区公告时公告所属小区-去除已被选中的
     * @author:张洋
     * @CreateDate:2016年8月1日09:20:372
     * @param request
     * @return Json
     * @throws Exception
     */
    @RequiresPermissions("VillageNotice:view")
    @RequestMapping(value = "/initArea/{id}")
    @ResponseBody
    public String initArea(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {
        ObjectMapper o = new ObjectMapper();
        List<UserAreaVo> userAreaVoList = new ArrayList<>();
        UserAreaVo userAreaVo = new UserAreaVo();
        userAreaVo.setUserId(getUserId());
        List<UserArea> list = userAreaService.selectList(userAreaVo);
        //初始化公告并去除已被选中的小区
        NoticeVillageVo t = new NoticeVillageVo();
        t.setNoticeId(id);
        List<NoticeVillageVo> tList = noticeVillageService.queryListWithName(t);
        for (int i = 0; i < tList.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(tList.get(i).getVillageId().equals(list.get(j).getAreaId())){
                    list.remove(j);
                    break;
                }
            }
        }
        //复制属性并翻译区域名称
        for (int i = 0; i < list.size(); i++) {
            UserAreaVo uao = new UserAreaVo();
            BeanUtils.copyProperties(list.get(i), uao);
            Village c = villageService.get(uao.getAreaId());
            if(c != null){
                uao.setAreaName(c.getVillageName());
            }else{
                continue;
            }
            userAreaVoList.add(uao);
        }
        return o.writeValueAsString(userAreaVoList);
    }
    /**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(VillageNotice _temp) {
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
    private void editAttr(VillageNotice _temp) {
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
