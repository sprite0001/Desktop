package com.wooxun.geekdol.hmedia.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hmedia.vo.HeartBeatCommentVo;
import com.wooxun.geekdol.hmedia.vo.HeartBeatReportVo;
import com.wooxun.geekdol.hmedia.vo.HeartBeatVo;
import com.wooxun.geekdol.hmedia.vo.HeartCommentReplyVo;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.model.HeartBeatReport;
import com.wooxun.geekdol.hmedia.model.HeartCommentReply;
import com.wooxun.geekdol.hmedia.service.HeartBeatCommentService;
import com.wooxun.geekdol.hmedia.service.HeartBeatReportService;
import com.wooxun.geekdol.hmedia.service.HeartBeatService;
import com.wooxun.geekdol.hmedia.service.HeartCommentReplyService;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.AppUserService;
import com.wooxun.geekdol.system.service.AttachService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.VillageService;

/**
 * @Title 随心拍
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年9月13日14:28:08
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年9月13日14:28:08 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("heartBeat")
public class HeartBeatControl extends BaseController{

    @Autowired
    private HeartBeatService<HeartBeat> heartBeatService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    @Autowired
    private SysDataService<SysData> sysDataService;
    @Autowired
    private AppUserService<AppUser> appUserService;
    @Autowired
    private VillageService<Village> villageService;
    @Autowired
    private AttachService<Attach> attachService;
    @Autowired
    private HeartBeatReportService<HeartBeatReport> heartBeatReportService;
    @Autowired
    private HeartBeatCommentService<HeartBeatComment> heartBeatCommentService;
    @Autowired
    private HeartCommentReplyService<HeartCommentReply> heartCommentReplyService;
    
    /**
     * 随心拍列表页
     */
    public static final String LIST = "hmedia/heartBeat/list";
    /**
     * 随心拍审核页
     */
    private static final String VERIFY = "hmedia/heartBeat/verify";
    /**
     * 随心拍详情页
     */
    private static final String SHOW = "hmedia/heartBeat/show";
    /**
     * 随心拍举报管理页
     */
    private static final String REPORT = "hmedia/heartBeat/report";
    /**
     * 随心拍评论管理页
     */
    private static final String COMMENT = "hmedia/heartBeat/comment";
    /**
     * 随心拍评论回复管理页
     */
    private static final String COMMENTREPLY = "hmedia/heartBeat/commentReply";
    private static final String URL = "http://192.168.1.200:8080";
    
    
    
    /**
     * 
     * @Title
     * @Description 跳转到列表界面
     * @author:张洋
     * @CreateDate:2016年9月13日14:31:33
     * @return
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list(Model model,HttpServletRequest request,HttpServletResponse response) {
        //获取当天时间
        Date today = new Date();
        String todayStr = DateUtil.format(today,"yyyy-MM-dd");
        //获取一周前时间
        Calendar cal = Calendar.getInstance();  
        cal.setTime(today);  
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        String tomorrowStr = DateUtil.format(cal.getTime(),"yyyy-MM-dd");
        model.addAttribute("todayStr", tomorrowStr);
        model.addAttribute("tomorrowStr", todayStr);
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 打开详情页面
     * @author:张洋
     * @CreateDate:2016年9月13日14:32:05
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/toShow/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toShow(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //初始化随心拍的内容
        HeartBeat heartBeat = heartBeatService.get(id);
        if(heartBeat != null){
            model.addAttribute("content", UnescapeUtil.unescape(heartBeat.getContent()));
        }
        //初始化随心拍附属的图片
        Attach att = new Attach();
        att.setMediaType(ConstantStr.FILE_IMAGE);
        att.setOwnerTbType(ConstantStr.HEARTBEAT_18);
        att.setOwnerId(id);
        List<Attach> attList = attachService.selectAttachByParam(att);
        for (Attach attach : attList) {
            attach.setUrl(URL+attach.getUrl());
        }
        model.addAttribute("attList", attList);
        model.addAttribute("id", id);
        AppUser user = appUserService.get(heartBeat.getInsId());
        Village village = villageService.get(heartBeat.getVillageId());
        //取出用户名并解密，取出手机号
        if(user != null){
        	model.addAttribute("userName", UnescapeUtil.unescape(user.getNickName()));
        	model.addAttribute("mobile", user.getMoblie());
        }
        //取出小区名
        if(village != null){
        	model.addAttribute("villageName", village.getVillageName());
        }
        //取出字典值，方便翻译
        //随心拍类型
        List<SysData> syslist1 = sysDataService.querySysdataByType(ConstantStr.HEARTBEATTYPE);
        Map<String,String> hm1 = new HashMap<>();
        for (int i = 0; i < syslist1.size(); i++) {
            hm1.put(syslist1.get(i).getValue(), syslist1.get(i).getLable());
        }
        //随心拍状态
        List<SysData> syslist2 = sysDataService.querySysdataByType(ConstantStr.HEARTBEATSTATUS);
        Map<String,String> hm2 = new HashMap<>();
        for (int i = 0; i < syslist2.size(); i++) {
            hm2.put(syslist2.get(i).getValue(), syslist2.get(i).getLable());
        }
        //翻译随心拍的状态和类型
        if(heartBeat != null){
        	model.addAttribute("content", UnescapeUtil.unescape(heartBeat.getContent()));
        	model.addAttribute("currentLocation", UnescapeUtil.unescape(heartBeat.getCurrentLocation()));
        	model.addAttribute("contentTypeName",hm1.get(heartBeat.getContentType()));
            model.addAttribute("treatmentStatusName",hm2.get(heartBeat.getTreatmentStatus()));
        }
        return SHOW;
    }
    
    /**
     * 
     * @Title
     * @Description 打开评论页面，此处传回的ID是随心拍id
     * @author:张洋
     * @CreateDate:2016年9月13日14:32:05
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/toComment/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toComment(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        HeartBeat heartBeat = heartBeatService.get(id);
        //浏览量，评论量，举报量
        String str1 = "";
        //点赞量
        String str2 = "";
        if(heartBeat != null){
            str1 = heartBeat.getViewNumber().toString();
            str2 = heartBeat.getLikesNumber().toString();
        }
        //获取小区名称
        Village village = villageService.get(heartBeat.getVillageId());
        if(village != null){
            model.addAttribute("villageName", village.getVillageName());
        }
        //获取回复量和举报量
        HeartBeatCommentVo hbc = new HeartBeatCommentVo();
        HeartBeatReportVo hbr = new HeartBeatReportVo();
        hbr.setHearId(id);
        hbc.setHearId(id);
        Long count2 = heartBeatCommentService.queryCountByParam(hbc);
        Long count = heartBeatReportService.queryCountAndUser(hbr);
        str1 = str1+"/"+count2+"/"+count;
        model.addAttribute("id", id);
        model.addAttribute("hotnumber", str1);
        model.addAttribute("likes", str2);
        return COMMENT;
    }
    
    /**
     * 
     * @Title
     * @Description 打开评论回复页面，此处传回的ID是评论id/随心拍ID
     * @author:张洋
     * @CreateDate:2016年9月13日14:32:05
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/toCommentReply/{id}/{hbId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toCommentReply(Model model,@PathVariable Long id,@PathVariable Long hbId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        HeartBeat heartBeat = heartBeatService.get(hbId);
        HeartBeatComment heartBeatComment = heartBeatCommentService.get(id);
        //获取小区名
        Village village = villageService.get(heartBeat.getVillageId());
        if(village != null){
            model.addAttribute("villageName", village.getVillageName());
        }
        model.addAttribute("id", id);
        model.addAttribute("hbId", hbId);
        //获取回复人的信息
        AppUser au = appUserService.get(heartBeatComment.getInsId());
        if(heartBeatComment != null){
            model.addAttribute("content", UnescapeUtil.unescape(heartBeatComment.getContent()));
            String time = DateUtil.format(heartBeatComment.getInsYmdhms(),"yyyy-MM-dd HH:mm:ss");
            model.addAttribute("commentDatetime", time);
            if(au != null){
                model.addAttribute("insName", UnescapeUtil.unescape(au.getNickName()));
            }
        }
        return COMMENTREPLY;
    }
    
    /**
     * 
     * @Title
     * @Description 打开审核页面
     * @author:张洋
     * @CreateDate:2016年9月13日14:33:48
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("HeartBeat:verify")
    @RequestMapping(value = "/toVerify/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        //初始化内容
        HeartBeat heartBeat = heartBeatService.get(id);
        if(heartBeat != null){
            model.addAttribute("content", UnescapeUtil.unescape(heartBeat.getContent()));
        }
        //初始化随心拍附属图片
        Attach att = new Attach();
        att.setMediaType(ConstantStr.FILE_IMAGE);
        att.setOwnerTbType(ConstantStr.HEARTBEAT_18);
        att.setOwnerId(id);
        List<Attach> attList = attachService.selectAttachByParam(att);
        for (Attach attach : attList) {
            attach.setUrl(URL+attach.getUrl());
        }
        model.addAttribute("attList", attList);
        return VERIFY;
    }
    
    /**
     * 
     * @Title
     * @Description 打开举报管理页面
     * @author:张洋
     * @CreateDate:2016年9月13日14:33:48
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("HeartBeat:manager")
    @RequestMapping(value = "/toReport/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toReport(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        //初始化随心拍内容
        HeartBeat heartBeat = heartBeatService.get(id);
        if(heartBeat != null){
            model.addAttribute("content", UnescapeUtil.unescape(heartBeat.getContent()));
        }
        //初始化随心拍附属图片
        Attach att = new Attach();
        att.setMediaType(ConstantStr.FILE_IMAGE);
        att.setOwnerTbType(ConstantStr.HEARTBEAT_18);
        att.setOwnerId(id);
        List<Attach> attList = attachService.selectAttachByParam(att);
        for (Attach attach : attList) {
            attach.setUrl(URL+attach.getUrl());
        }
        model.addAttribute("attList", attList);
        return REPORT;
    }
    
    /**
     * 
     * @Title 根据ID初始化数据
     * @Description 根据ID初始化数据，初始化详情，审核界面
     * @author:张洋
     * @CreateDate:2016年9月13日14:33:44
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //分别获取随心拍，小区，插入用户数据
        HeartBeat heartBeat = heartBeatService.get(id);
        AppUser user = appUserService.get(heartBeat.getInsId());
        Village village = villageService.get(heartBeat.getVillageId());
        Map<String,Object> map = new HashMap<>();
        //取出用户名并解密，取出手机号
        if(user != null){
            map.put("userName", UnescapeUtil.unescape(user.getNickName()));
            map.put("mobile", user.getMoblie());
        }
        //取出小区名
        if(village != null){
            map.put("villageName", village.getVillageName());
        }
        //取出字典值，方便翻译
        //随心拍类型
        List<SysData> syslist1 = sysDataService.querySysdataByType(ConstantStr.HEARTBEATTYPE);
        Map<String,String> hm1 = new HashMap<>();
        for (int i = 0; i < syslist1.size(); i++) {
            hm1.put(syslist1.get(i).getValue(), syslist1.get(i).getLable());
        }
        //随心拍状态
        List<SysData> syslist2 = sysDataService.querySysdataByType(ConstantStr.HEARTBEATSTATUS);
        Map<String,String> hm2 = new HashMap<>();
        for (int i = 0; i < syslist2.size(); i++) {
            hm2.put(syslist2.get(i).getValue(), syslist2.get(i).getLable());
        }
        //翻译随心拍的状态和类型
        if(heartBeat != null){
            map.put("content", UnescapeUtil.unescape(heartBeat.getContent()));
            map.put("currentLocation", UnescapeUtil.unescape(heartBeat.getCurrentLocation()));
            map.put("contentTypeName",hm1.get(heartBeat.getContentType()));
            map.put("treatmentStatusName",hm2.get(heartBeat.getTreatmentStatus()));
        }
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询随心拍
     * @author:张洋
     * @CreateDate:2016年9月13日14:34:36
     * @param heartBeatVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList(HeartBeatVo heartBeatVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        Map<String, Object> map = new HashMap<String, Object>();
        heartBeatVo.setPageFlag(true);
        heartBeatVo.setStartPage((page-1)*rows);
        heartBeatVo.setEndPage(rows);
        //初始化传递过来的区域id和时间范围
        heartBeatVo.initAreaLevl();
        heartBeatVo.initTime();
        //取出字典值，方便翻译
        //随心拍类型
        List<SysData> syslist1 = sysDataService.querySysdataByType(ConstantStr.HEARTBEATTYPE);
        Map<String,String> hm1 = new HashMap<>();
        for (int i = 0; i < syslist1.size(); i++) {
            hm1.put(syslist1.get(i).getValue(), syslist1.get(i).getLable());
        }
        //随心拍状态
        List<SysData> syslist2 = sysDataService.querySysdataByType(ConstantStr.HEARTBEATSTATUS);
        Map<String,String> hm2 = new HashMap<>();
        for (int i = 0; i < syslist2.size(); i++) {
            hm2.put(syslist2.get(i).getValue(), syslist2.get(i).getLable());
        }
        Long count = 0L;
        List<HeartBeatVo> heartBeatList = new ArrayList<HeartBeatVo>();
        //当省，市，区县，社区id为空时，表示查出全部区域下符合条件的随心拍
        if(heartBeatVo.getAreaLevel() == null){
            count = heartBeatService.queryCountAndUser(heartBeatVo);
            heartBeatList = heartBeatService.queryListAndUser(heartBeatVo);
        }else if(heartBeatVo.getAreaLevel().equals(ConstantStr.PROVINCE_CODE)){
            count = heartBeatService.queryCountAndUserPro(heartBeatVo);
            heartBeatList = heartBeatService.queryListAndUserPro(heartBeatVo);
        }else if(heartBeatVo.getAreaLevel().equals(ConstantStr.CITY_CODE)){
            count = heartBeatService.queryCountAndUserCity(heartBeatVo);
            heartBeatList = heartBeatService.queryListAndUserCity(heartBeatVo);
        }else if(heartBeatVo.getAreaLevel().equals(ConstantStr.COUNTY_CODE)){
            count = heartBeatService.queryCountAndUserCounty(heartBeatVo);
            heartBeatList = heartBeatService.queryListAndUserCounty(heartBeatVo);
        }else if(heartBeatVo.getAreaLevel().equals(ConstantStr.COMMUNITY_CODE)){
            count = heartBeatService.queryCountAndUserCommunity(heartBeatVo);
            heartBeatList = heartBeatService.queryListAndUserCommunity(heartBeatVo);
        }
        //翻译随心拍的类型和状态
        for (HeartBeatVo hbv : heartBeatList) {
            hbv.setContentTypeName(hm1.get(hbv.getContentType()));
            hbv.setTreatmentStatusName(hm2.get(hbv.getTreatmentStatus()));
            hbv.setContent(UnescapeUtil.unescape(hbv.getContent()));
            hbv.setCurrentLocation(UnescapeUtil.unescape(hbv.getCurrentLocation()));
        }
        map.put("rows", heartBeatList);
        map.put("total",count);

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询随心拍评论
     * @author:张洋
     * @CreateDate:2016年9月13日14:34:36
     * @param heartBeatCommentVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/selectComment",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectComment(HeartBeatCommentVo heartBeatCommentVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        Map<String, Object> map = new HashMap<String, Object>();
        heartBeatCommentVo.setPageFlag(true);
        heartBeatCommentVo.setStartPage((page-1)*rows);
        heartBeatCommentVo.setEndPage(rows);
        //由于数据库中用户名和内容是加密之后的，但是前台传过来的是中文，故在此加密
        if(heartBeatCommentVo.getInsName() != null){
            heartBeatCommentVo.setInsName(UnescapeUtil.escape(heartBeatCommentVo.getInsName()));
            heartBeatCommentVo.setContent(UnescapeUtil.escape(heartBeatCommentVo.getContent()));
        }
        //将查出的用户名解密以输出
        List<HeartBeatCommentVo> heartBeatCommentList = heartBeatCommentService.queryListByUser(heartBeatCommentVo);
        for (HeartBeatCommentVo hbc : heartBeatCommentList) {
            hbc.setInsName(UnescapeUtil.unescape(hbc.getInsName()));
            hbc.setContent(UnescapeUtil.unescape(hbc.getContent()));
        }
        Long count = heartBeatCommentService.queryCountByUser(heartBeatCommentVo);
        map.put("rows", heartBeatCommentList);
        map.put("total",count);
        
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询随心拍评论回复
     * @author:张洋
     * @CreateDate:2016年9月13日14:34:36
     * @param heartCommentReplyVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/selectCommentReply",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectCommentReply(HeartCommentReplyVo heartCommentReplyVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        Map<String, Object> map = new HashMap<String, Object>();
        heartCommentReplyVo.setPageFlag(true);
        heartCommentReplyVo.setStartPage((page-1)*rows);
        heartCommentReplyVo.setEndPage(rows);
        //由于数据库中用户名和内容是加密之后的，但是前台传过来的是中文，故在此加密
        if(heartCommentReplyVo.getInsName() != null){
            heartCommentReplyVo.setInsName(UnescapeUtil.escape(heartCommentReplyVo.getInsName()));
            heartCommentReplyVo.setContent(UnescapeUtil.escape(heartCommentReplyVo.getContent()));
        }
        //将查出的用户名解密以输出
        List<HeartCommentReplyVo> heartBeatCommentList = heartCommentReplyService.queryListByUser(heartCommentReplyVo);
        Long count = heartCommentReplyService.queryCountByUser(heartCommentReplyVo);
        for (HeartCommentReplyVo hcr : heartBeatCommentList) {
            hcr.setInsName(UnescapeUtil.unescape(hcr.getInsName()));
            hcr.setContent(UnescapeUtil.unescape(hcr.getContent()));
        }
        map.put("rows", heartBeatCommentList);
        map.put("total",count);
        
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询举报数据
     * @author:张洋
     * @CreateDate:2016年9月18日16:22:25
     * @param heartBeatVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:view")
    @RequestMapping(value = "/selectReportList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectReportList(HeartBeatVo heartBeatVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        HeartBeatReportVo hbr = new HeartBeatReportVo();
        hbr.setPageFlag(true);
        hbr.setStartPage((page-1)*rows);
        hbr.setEndPage(rows);
        hbr.setHearId(heartBeatVo.getId());

        //随心拍审核状态
        List<SysData> syslist2 = sysDataService.querySysdataByType(ConstantStr.HEARTREPORTTYPE);
        Map<String,String> hm2 = new HashMap<>();
        for (int i = 0; i < syslist2.size(); i++) {
            hm2.put(syslist2.get(i).getValue(), syslist2.get(i).getLable());
        }

        //翻译审核状态，将编码翻译为汉字
        List<HeartBeatReportVo> hbrList = heartBeatReportService.queryListAndUser(hbr);
        for (HeartBeatReportVo heartBeatReportVo : hbrList) {
            heartBeatReportVo.setReportTypeName(hm2.get(heartBeatReportVo.getReportType()));
            heartBeatReportVo.setReportContent(UnescapeUtil.unescape(heartBeatReportVo.getReportContent()));
            heartBeatReportVo.setReportName(UnescapeUtil.unescape(heartBeatReportVo.getReportName()));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Long count = heartBeatReportService.queryCountAndUser(hbr);
        map.put("rows", hbrList);
        map.put("total",count);

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title 删除随心拍，逻辑删除，非物理删除
     * @Description 根据id删除公告，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param heartBeat
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(HeartBeat heartBeat,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"随心拍"};
        ObjectMapper o = new ObjectMapper();
        //判断参数是否传递错误
        if(heartBeat.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        HeartBeat sta = heartBeatService.get(heartBeat.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性及更新属性并更新删除
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = heartBeatService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.HEARTBEAT, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.HEARTBEAT, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 删除随心拍举报，逻辑删除，非物理删除
     * @Description 根据id删除举报信息，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年9月18日17:59:45
     * @param heartBeatReport
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:manager")
    @RequestMapping(value = "/deleteReport", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String deleteReport(HeartBeatReport heartBeatReport,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"随心拍举报信息"};
        ObjectMapper o = new ObjectMapper();
        //判断参数是否传递错误
        if(heartBeatReport.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        HeartBeatReport sta = heartBeatReportService.get(heartBeatReport.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性及更新属性并更新删除
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = heartBeatReportService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.HEARTBEAT, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.HEARTBEAT, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    /**
     * 
     * @Title 删除随心拍评论，逻辑删除，非物理删除
     * @Description 根据id删除评论信息，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年9月18日17:59:45
     * @param heartBeatReport
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:manager")
    @RequestMapping(value = "/deleteComment", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String deleteComment(HeartBeatComment heartBeatComment,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"随心拍评论信息"};
        ObjectMapper o = new ObjectMapper();
        //判断参数是否传递错误
        if(heartBeatComment.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        HeartBeatComment sta = heartBeatCommentService.get(heartBeatComment.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性及更新属性并更新删除
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        boolean result = heartBeatCommentService.deleteHeartBeatComment(sta);
        if(result){
            HeartBeat heartBeat = heartBeatService.get(sta.getHearId());
            //浏览量，评论量，举报量
            String str1 = "";
            if(heartBeat != null){
                str1 = heartBeat.getViewNumber().toString();
            }
            //获取回复量和举报量
            HeartBeatCommentVo hbc = new HeartBeatCommentVo();
            HeartBeatReportVo hbr = new HeartBeatReportVo();
            hbr.setHearId(sta.getHearId());
            hbc.setHearId(sta.getHearId());
            Long count2 = heartBeatCommentService.queryCountByParam(hbc);
            Long count1 = heartBeatReportService.queryCountAndUser(hbr);
            str1 = str1+"/"+count2+"/"+count1;
            map.put("type", "Success");
            map.put("dataStr", str1);
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.HEARTBEAT, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.HEARTBEAT, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    /**
     * 
     * @Title 删除随心拍评论回复，逻辑删除，非物理删除
     * @Description 根据id删除评论信息回复，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年9月18日17:59:45
     * @param heartBeatReport
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:manager")
    @RequestMapping(value = "/deleteCommentReply", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String deleteCommentReply(HeartCommentReply heartCommentReply,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"随心拍评论回复信息"};
        ObjectMapper o = new ObjectMapper();
        //判断参数是否传递错误
        if(heartCommentReply.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        HeartCommentReply sta = heartCommentReplyService.get(heartCommentReply.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性及更新属性并更新删除
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        boolean reuslt = heartCommentReplyService.deleteReplay(sta);
        if(reuslt){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.HEARTBEAT, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.HEARTBEAT, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    /**
     * 
     * @Title 审核随心拍
     * @Description 审核随心拍
     * @author:张洋
     * @CreateDate:2016年8月5日10:20:57
     * @param admNewsflash
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("HeartBeat:verify")
    @RequestMapping(value = "/saveVerify", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveVerify(HeartBeat heartBeat,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"随心拍"};
        ObjectMapper o = new ObjectMapper();
        //判断参数是否传递错误
        if(heartBeat.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //获取原始数据
        HeartBeat sta = heartBeatService.get(heartBeat.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //将需要更新的数据放入最终更新要用的实体类中
        HeartBeat hb = new HeartBeat();
        hb.setId(sta.getId());
        hb.setTreatmentStatus(heartBeat.getTreatmentStatus());
        hb.setVerrifyContent(heartBeat.getVerrifyContent());
        hb.setVerifyId(getUserId());
        hb.setVerifyYmdhms(new Date());
        hb.setUpdEac(sta.getUpdEac());
        this.editAttr(hb);
        //如果审核通过的话，直接发布
        //更新数据
        int count = heartBeatService.updateSelective(hb);
        if(count==1){
            // 如果审核通过
            // 调用系统日志的service方法写入成功信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100100,arr), ConstantStr.HEARTBEAT, sta.getId(), getUser());
            /* 向map中封装成功信息 */
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100100,arr));
            return o.writeValueAsString(map);
        } else{
            // 调用系统日志的service方法写入失败信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100111,arr), ConstantStr.HEARTBEAT, sta.getId(), getUser());
            /* 向map中封装失败信息 */
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100111,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void editAttr(HeartBeat _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void editAttr(HeartCommentReply _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void editAttr(HeartBeatComment _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void editAttr(HeartBeatReport _temp) {
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
