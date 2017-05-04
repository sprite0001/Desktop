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
import com.wooxun.geekdol.hbridge.model.AdmNewsflash;
import com.wooxun.geekdol.hbridge.service.AdmNewsflashService;
import com.wooxun.geekdol.hbridge.vo.AdmNewsflashVo;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.service.CountyService;
import com.wooxun.geekdol.system.service.ProvinceService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.SysdataVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月3日11:49:29
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
@RequestMapping("admNewsflash")
public class AdmNewsflashControl extends BaseController{

    @Autowired
    private AdmNewsflashService<AdmNewsflash> admNewsflashService;
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
    private SysDataService<SysData> sysDataService;
    @Autowired
    private UserService<User> userService;

    public static final String LIST = "hbridge/admnewsflash/list";
    private static final String ADD = "hbridge/admnewsflash/add";
    private static final String UPDATE = "hbridge/admnewsflash/update";
    public static final String VERIFYLIST = "hbridge/admnewsflash/verifyList";
    public static final String VERIFY = "hbridge/admnewsflash/verify";
    public static final String VIEW = "hbridge/admnewsflash/view";
    private static final String SHOW = "hbridge/admnewsflash/show";
    
    
    
    /**
     * 
     * @Title
     * @Description 跳转到行政快报界面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:09
     * @return
     */
    @RequiresPermissions("AdmNewsflash:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list(Model model,HttpServletRequest request,HttpServletResponse response) {
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 跳转到行政快报审核界面
     * @author:张洋
     * @CreateDate:2016年8月4日15:13:27
     * @return
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/verifyList", method = {RequestMethod.POST,RequestMethod.GET})
    public String verifyList(Model model,HttpServletRequest request,HttpServletResponse response) {
        return VERIFYLIST;
    }
    /**
     * 
     * @Title
     * @Description 打开新增页面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:34
     * @return
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/toAdd", method = {RequestMethod.POST,RequestMethod.GET})
    public String toAdd(Model model,HttpServletRequest request,HttpServletResponse response){
        setToken(model, request);
        return ADD;
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
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/toUpdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        if(id != null){
            //预加载内容
            AdmNewsflash admNewsflash = admNewsflashService.get(id);
            model.addAttribute("content", admNewsflash.getContent());
        }
        setToken(model, request);
        return UPDATE;
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
    @RequiresPermissions("AdmNewsflash:view")
    @RequestMapping(value = "/toShow/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toShow(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        if(id != null){
            //预加载内容
            AdmNewsflash admNewsflash = admNewsflashService.get(id);
            model.addAttribute("content", admNewsflash.getContent());
        }
        setToken(model, request);
        return SHOW;
    }
    /**
     * 
     * @Title
     * @Description 打开查看审核页面
     * @author:张洋
     * @CreateDate:2016年8月5日10:04:16
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("AdmNewsflashV:verify")
    @RequestMapping(value = "/toVerify/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toVerify(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        String url = "";
        if(id != null){
            //根据不同的状态返回到不同的界面
            AdmNewsflash admNewsflash = admNewsflashService.get(id);
            model.addAttribute("content", admNewsflash.getContent());
            model.addAttribute("title", admNewsflash.getTitle());
            model.addAttribute("source", admNewsflash.getSource());
            model.addAttribute("summary", admNewsflash.getSummary());
            if(ConstantStr.VERIFY_YES.equals(admNewsflash.getVerifyStatus()) || ConstantStr.VERIFY_NO.equals(admNewsflash.getVerifyStatus())){
                url = VIEW;
            }else{
                url = VERIFY;
            }
        }
        return url;
    }
    
    /**
     * 
     * @Title 行政快报修改
     * @Description 初始化行政快报编辑页面
     * @author:张洋
     * @CreateDate:2016年7月27日10:51:16
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //根据ID获取数据并复制到vo对象
        AdmNewsflash admNewsflash = admNewsflashService.get(id);
        AdmNewsflashVo admNewsflashVo = new AdmNewsflashVo();
        if(admNewsflash != null){
            BeanUtils.copyProperties(admNewsflash, admNewsflashVo);
        }
        //格式化时间
        if(admNewsflashVo.getNewsflashTime() != null){
            admNewsflashVo.setNewsflashTimeStr(DateUtil.dateToString(admNewsflashVo.getNewsflashTime()));
        }
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(admNewsflashVo);
    }
    
    /**
     * 
     * @Title
     * @Description 增加行政快报信息
     * @author:张洋
     * @CreateDate:2016年7月26日16:58:08
     * @param admNewsflash
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(AdmNewsflashVo admNewsflashVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"行政快报"};
        //格式化时间
        if(admNewsflashVo.getNewsflashTimeBeginStr() != null && !admNewsflashVo.getNewsflashTimeBeginStr().trim().equals("")){
            admNewsflashVo.setNewsflashTimeBegin(DateUtil.parse(admNewsflashVo.getNewsflashTimeBeginStr()));
        }
        if(admNewsflashVo.getNewsflashTimeEndStr() != null && !admNewsflashVo.getNewsflashTimeEndStr().trim().equals("")){
            admNewsflashVo.setNewsflashTimeEnd(DateUtil.parse(admNewsflashVo.getNewsflashTimeEndStr()));
        }
        if(admNewsflashVo.getNewsflashTimeStr() != null && !admNewsflashVo.getNewsflashTimeStr().trim().equals("")){
            admNewsflashVo.setNewsflashTime(DateUtil.parse(admNewsflashVo.getNewsflashTimeStr()));
        }
        //复制属性到VO类并加入通用属性
        AdmNewsflash admNewsflash = new AdmNewsflash();
        BeanUtils.copyProperties(admNewsflashVo, admNewsflash);
        this.addAttr(admNewsflash);
        admNewsflash.setScannedNumber(0L);
        if(admNewsflash.getPublishStatus() == null || admNewsflash.getPublishStatus().trim().equals("")){
            admNewsflash.setPublishStatus(ConstantStr.WFB);
        }
        //只有当审核状态为提交时才加入提交属性
        if(admNewsflash.getVerifyStatus().equals(ConstantStr.VERIFY_TO)){
            admNewsflash.setSubmitTime(new Date());
            admNewsflash.setSubmitUser(getUserId());
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = admNewsflashService.save(admNewsflash);
        if(count>0){
        	map.put("type", "Success");
        	if(ConstantStr.NO_SUBMIT.equals(admNewsflash.getVerifyStatus())){
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
        	}else{
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100079,arr));
        	}
        	return o.writeValueAsString(map);
        } else{
        	map.put("type", "Error");
        	if(ConstantStr.NO_SUBMIT.equals(admNewsflash.getVerifyStatus())){
        		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
        	}else{
        		map.put("msg",ComDefine.getMsg(ConstantStr.INFO100080,arr));
        	}
        	return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询行政快报
     * @author:张洋
     * @CreateDate:2016年7月26日17:28:05
     * @param admNewsflashVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflash:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList(AdmNewsflashVo admNewsflashVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        Map<String, Object> map = new HashMap<String, Object>();
        admNewsflashVo.setPageFlag(true);
        admNewsflashVo.setStartPage((page-1)*rows);
        admNewsflashVo.setEndPage(rows);
        //选择全部时的数据处理
        if(admNewsflashVo.getVerifyStatus() == null || admNewsflashVo.getVerifyStatus().trim().equals("")){
            admNewsflashVo.setVerifyStatus(null);
        }
        //格式化时间，方便查询
        if(admNewsflashVo.getNewsflashTimeBeginStr() != null && !admNewsflashVo.getNewsflashTimeBeginStr().trim().equals("")){
            if(admNewsflashVo.getNewsflashTimeBeginStr().length() < 17){
                admNewsflashVo.setNewsflashTimeBeginStr(admNewsflashVo.getNewsflashTimeBeginStr() + " 00:00:00");
            }
            admNewsflashVo.setNewsflashTimeBegin(DateUtil.parse(admNewsflashVo.getNewsflashTimeBeginStr()));
        }if(admNewsflashVo.getNewsflashTimeEndStr() != null && !admNewsflashVo.getNewsflashTimeEndStr().trim().equals("")){
            if(admNewsflashVo.getNewsflashTimeEndStr().length() < 17){
                admNewsflashVo.setNewsflashTimeEndStr(admNewsflashVo.getNewsflashTimeEndStr() + " 23:59:59");
            }
            admNewsflashVo.setNewsflashTimeEnd(DateUtil.parse(admNewsflashVo.getNewsflashTimeEndStr()));
        }
        //条件查询
        admNewsflashVo.setInsId(getUserId());
        List<AdmNewsflashVo> admNewsflashListT = new ArrayList<>();
        Long count = admNewsflashService.queryCountByParam(admNewsflashVo);
        if(count > 0){
            List<AdmNewsflash> list = admNewsflashService.queryListByParam(admNewsflashVo);
            
            //查出类型，将编码翻译成汉字
            SysdataVo sdv = new SysdataVo();
            sdv.setType(ConstantStr.VERIFY_CODE);
            List<SysData> sdList = sysDataService.querySysdataByBean(sdv);
            Map<String,String> hm = new HashMap<>();
            for (SysData sysData : sdList) {
                hm.put(sysData.getValue(), sysData.getLable());
            }
            for (int i = 0; i < list.size(); i++) {
                AdmNewsflash af = list.get(i);
                AdmNewsflashVo afv = new AdmNewsflashVo();
                BeanUtils.copyProperties(af, afv);
                afv.setVerifyStatusName(hm.get(afv.getVerifyStatus()));
                //由于有的数据内容会影响json格式化，且列表页不需要内容数据，故赋空
                afv.setContent(null);
                admNewsflashListT.add(afv);
            }
        }
        map.put("rows", admNewsflashListT);
        map.put("total",count);

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询行政快报-待审核列表
     * @author:张洋
     * @CreateDate:2016年8月5日09:24:11
     * @param admNewsflashVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflashV:view")
    @RequestMapping(value = "/selectListVerify",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectListVerify(AdmNewsflashVo admNewsflashVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        Map<String, Object> map = new HashMap<String, Object>();
        admNewsflashVo.setPageFlag(true);
        admNewsflashVo.setStartPage((page-1)*rows);
        admNewsflashVo.setEndPage(rows);
        //选择全部时的数据处理
        if(admNewsflashVo.getVerifyStatus() == null || admNewsflashVo.getVerifyStatus().trim().equals("")){
            admNewsflashVo.setVerifyStatus(null);
        }
        //格式化时间
        if(admNewsflashVo.getNewsflashTimeBeginStr() != null && !admNewsflashVo.getNewsflashTimeBeginStr().trim().equals("")){
            if(admNewsflashVo.getNewsflashTimeBeginStr().length() < 17){
                admNewsflashVo.setNewsflashTimeBeginStr(admNewsflashVo.getNewsflashTimeBeginStr() + " 00:00:00");
            }
            admNewsflashVo.setNewsflashTimeBegin(DateUtil.parse(admNewsflashVo.getNewsflashTimeBeginStr()));
        }if(admNewsflashVo.getNewsflashTimeEndStr() != null && !admNewsflashVo.getNewsflashTimeEndStr().trim().equals("")){
            if(admNewsflashVo.getNewsflashTimeEndStr().length() < 17){
                admNewsflashVo.setNewsflashTimeEndStr(admNewsflashVo.getNewsflashTimeEndStr() + " 23:59:59");
            }
            admNewsflashVo.setNewsflashTimeEnd(DateUtil.parse(admNewsflashVo.getNewsflashTimeEndStr()));
        }
        List<AdmNewsflashVo> admNewsflashListT = new ArrayList<>();
        //条件查询
        
        if(admNewsflashVo.getVerifyStatus() == null || admNewsflashVo.getVerifyStatus().trim().equals("") || admNewsflashVo.getVerifyStatus().trim().equals("0")){
            String lsStr = ConstantStr.VERIFY_TO+","+ConstantStr.VERIFY_YES+","+ConstantStr.VERIFY_NO;
            admNewsflashVo.setVerifyStatusLs(lsStr);
            admNewsflashVo.setVerifyStatus(null);
        }else{
            admNewsflashVo.setVerifyStatusLs(null);
        }
        Long count = admNewsflashService.queryCountByParam(admNewsflashVo);
        if(count > 0){
            List<AdmNewsflash> list = admNewsflashService.queryListByParam(admNewsflashVo);
            SysdataVo sdv = new SysdataVo();
            sdv.setType(ConstantStr.VERIFY_CODE);
            //查询出类型，并将编码翻译成汉字
            List<SysData> sdList = sysDataService.querySysdataByBean(sdv);
            Map<String,String> hm = new HashMap<>();
            for (SysData sysData : sdList) {
                hm.put(sysData.getValue(), sysData.getLable());
            }
            for (int i = 0; i < list.size(); i++) {
                AdmNewsflash af = list.get(i);
                //不显示未提交的数据
                if(ConstantStr.NO_SUBMIT.equals(af.getVerifyStatus())){
                    continue;
                }
                AdmNewsflashVo afv = new AdmNewsflashVo();
                BeanUtils.copyProperties(af, afv);
                if(afv.getSubmiter() != null){
                    afv.setSubmitUserName(afv.getSubmiter().getRealName());
                }
                afv.setVerifyStatusName(hm.get(afv.getVerifyStatus()));
                //由于有的数据内容会影响json格式化，且列表页不需要内容数据，故赋空
                afv.setContent(null);
                admNewsflashListT.add(afv);
            }
        }
        
        map.put("rows", admNewsflashListT);
        map.put("total",count);

        ObjectMapper o = new ObjectMapper();
        String str = o.writeValueAsString(map);
        return str;
    }
    
    /**
     * 
     * @Title 删除行政快报，逻辑删除，非物理删除
     * @Description 根据id删除行政快报，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param admNewsflash
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(AdmNewsflash admNewsflash,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"行政快报"};
        ObjectMapper o = new ObjectMapper();
        if(admNewsflash.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        AdmNewsflash sta = admNewsflashService.get(admNewsflash.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = admNewsflashService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 修改发布状态或发布状态
     * @Description 修改发布状态或发布状态
     * @author:张洋
     * @CreateDate:2016年7月27日11:56:48
     * @param admNewsflash
     * @param request
     * @param response
     * @return JSON字符串(type=success为成功,type=error为失败)
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String changeStatus(AdmNewsflash admNewsflash,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"行政快报"};
        ObjectMapper o = new ObjectMapper();
        if(admNewsflash.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        AdmNewsflash sta = admNewsflashService.get(admNewsflash.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置修改属性及发布的相关属性
        admNewsflash.setUpdEac(sta.getUpdEac());
        if(admNewsflash.getPublishStatus() != null && admNewsflash.getPublishStatus().equals(ConstantStr.FB)){
            admNewsflash.setPublishTime(new Date());
            admNewsflash.setPublishUser(getUserId());
        }else{
            admNewsflash.setPublishTime(null);
        }
        if(admNewsflash.getVerifyStatus() != null && admNewsflash.getVerifyStatus().equals(ConstantStr.VERIFY_TO)){
            admNewsflash.setSubmitTime(new Date());
            admNewsflash.setSubmitUser(getUserId());
        }else{
            admNewsflash.setSubmitTime(null);
        }
        this.editAttr(admNewsflash);
        //更新数据
        int count = admNewsflashService.updateSelective(admNewsflash);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 更新行政快报
     * @Description 更新行政快报
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param admNewsflash
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflash:manager")
    @RequestMapping(value = "/saveUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveUpdate(AdmNewsflashVo admNewsflashVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"行政快报"};
        ObjectMapper o = new ObjectMapper();
        if(admNewsflashVo.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取原有数据
        AdmNewsflash sta = admNewsflashService.get(admNewsflashVo.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //格式化时间
        if(admNewsflashVo.getNewsflashTimeStr() != null && !admNewsflashVo.getNewsflashTimeStr().trim().equals("")){
            admNewsflashVo.setNewsflashTime(DateUtil.parse(admNewsflashVo.getNewsflashTimeStr()));
        }
        AdmNewsflash admNewsflash = new AdmNewsflash();
        BeanUtils.copyProperties(admNewsflashVo, admNewsflash);
        //设置修改的相关属性
        admNewsflash.setUpdEac(sta.getUpdEac());
        this.editAttr(admNewsflash);

        if(admNewsflash.getVerifyStatus() != null && !admNewsflash.getVerifyStatus().trim().equals("")){
            //只有原状态为未提交或未通过并提交的数据才设置提交属性
            if(admNewsflash.getVerifyStatus().equals(ConstantStr.VERIFY_TO)){
                if(sta.getVerifyStatus().equals(ConstantStr.NO_SUBMIT) || sta.getVerifyStatus().equals(ConstantStr.VERIFY_NO)){
                    admNewsflash.setSubmitTime(new Date());
                    admNewsflash.setSubmitUser(getUserId());
                }else{
                    map.put("type", "Error");
                    map.put("msg", ComDefine.getMsg(ConstantStr.INFO100068,arr));
                    return o.writeValueAsString(map);
                }
            }
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        //更新数据
        int count = admNewsflashService.updateSelective(admNewsflash);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 审核行政快报
     * @Description 审核行政快报
     * @author:张洋
     * @CreateDate:2016年8月5日10:20:57
     * @param admNewsflash
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("AdmNewsflashV:verify")
    @RequestMapping(value = "/saveVerify", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveVerify(AdmNewsflash admNewsflash,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"行政快报"};
        ObjectMapper o = new ObjectMapper();
        if(admNewsflash.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //获取原始数据
        AdmNewsflash sta = admNewsflashService.get(admNewsflash.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //将需要更新的数据放入最终更新要用的实体类中
        AdmNewsflash anf = new AdmNewsflash();
        anf.setId(admNewsflash.getId());
        anf.setVerifyStatus(admNewsflash.getVerifyStatus());
        anf.setVerifySug(admNewsflash.getVerifySug());
        anf.setUpdEac(sta.getUpdEac());
        this.editAttr(anf);
        anf.setVerifyTime(new Date());
        anf.setVerifyUser(getUserId());
        //如果审核通过的话，直接发布
        if(anf.getVerifyStatus().equals(ConstantStr.VERIFY_YES)){
            anf.setPublishStatus(ConstantStr.FB);
            anf.setPublishTime(new Date());
            anf.setPublishUser(getUserId());
        }
        //更新数据
        int count = admNewsflashService.updateSelective(anf);
        if(count==1){
        	
        	// 如果审核通过
			if (ConstantStr.VERIFY_YES.equals(admNewsflash.getVerifyStatus())) {
				// 调用系统日志的service方法写入成功信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100100,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
				/* 向map中封装成功信息 */
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100100,arr));
			} else { // 如果审核不通过
				// 调用系统日志的service方法写入成功信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100110,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
				/* 向map中封装成功信息 */
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100110,arr));
			}
            return o.writeValueAsString(map);
        } else{
        	// 如果审核通过
			if (ConstantStr.VERIFY_YES.equals(admNewsflash.getVerifyStatus())) {
				// 调用系统日志的service方法写入失败信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100101,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
				/* 向map中封装失败信息 */
				map.put("type", "Error");
	            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100101,arr));
			} else { // 如果审核不通过
				// 调用系统日志的service方法写入失败信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100111,arr), ConstantStr.ADMNEWSFLASH, sta.getId(), getUser());
				/* 向map中封装失败信息 */
				map.put("type", "Error");
	            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100111,arr));
			}
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 初始化审核状态，去掉未提交
     * @author:张洋
     * @CreateDate:2016年8月4日15:28:00
     * @return Json
     * @throws Exception
     */
    @RequestMapping(value = "/selectVerifyType")
    @ResponseBody
    public String selectVerifyType() throws Exception {
        ObjectMapper o = new ObjectMapper();
        SysdataVo sdv = new SysdataVo();
        sdv.setType(ConstantStr.VERIFY_CODE);
        SysData sysData = new SysData();
        sysData.setValue("");
        sysData.setLable("全部");
        List<SysData> sdList = new ArrayList<SysData>();
        sdList.add(sysData);
        sdList.addAll(sysDataService.querySysdataByBean(sdv));
        int removeNum = 0;
        //去掉未提交的状态
        for (int i = 0; i < sdList.size(); i++) {
            if(ConstantStr.NO_SUBMIT.equals(sdList.get(i).getValue())){
                removeNum = i;
                break;
            }
        }
        if(sdList.size() > 0){
            sdList.remove(removeNum);
        }
        return o.writeValueAsString(sdList);
    }
    
    /**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(AdmNewsflash _temp) {
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
    private void editAttr(AdmNewsflash _temp) {
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
