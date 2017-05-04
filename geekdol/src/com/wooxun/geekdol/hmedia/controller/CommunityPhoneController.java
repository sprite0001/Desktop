package com.wooxun.geekdol.hmedia.controller;

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
import com.wooxun.geekdol.hmedia.model.CommunityPhone;
import com.wooxun.geekdol.hmedia.service.CommunityPhoneService;
import com.wooxun.geekdol.hmedia.vo.CommunityPhoneVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月30日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月30日  上午11:51:12 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("communityphone")
public class CommunityPhoneController extends BaseController{
    //返回社区电话查询列表页面
    private static final String LIST = "hmedia/communityphone/list";
    //返回社区电话新增页面
    private static final String ADD = "hmedia/communityphone/add";
    //返回社区电话修改页面
    private static final String UPDATE="hmedia/communityphone/update";
      
    @Autowired
    private CommunityPhoneService<CommunityPhone> communityPhoneService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    @Autowired
    private VillageService<Village> villageService;
    /**
     * 
     * @Title
     * @Description 跳转到社区电话列表页面
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:32
     * @return
     */
    @RequiresPermissions("CommunityPhone:view")
    @RequestMapping("list")
    public String list(){
        //跳转到社区电话列表页面
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 返回列表查询结果
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:48
     * @param communityPhoneVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(CommunityPhoneVo communityPhoneVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //设置查询页码 默认为0
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //设置每页查询条数 默认为20
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //创建map变量用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建社区电话列表类
        List<CommunityPhoneVo> communityPhoneList = new ArrayList<CommunityPhoneVo>();
        //初始化社区电话列表查询条数
        Long count = 0l;
        //设置分页标示为true
        communityPhoneVo.setPageFlag(true);
        //设置查询的起始记录
        communityPhoneVo.setStartPage((page-1)*rows);
        //设置查询的结束记录
        communityPhoneVo.setEndPage(rows);
        //取得登录人id
        communityPhoneVo.setUserId(getUserId());
        //查询满足条件的社区电话列表条数
        count = communityPhoneService.findAllCount(communityPhoneVo);
        //当社区电话列表条数大于0
        if(count>0){
            //查询满足条件的社区电话列表
            communityPhoneList = communityPhoneService.findAll(communityPhoneVo);
        }
        //封装社区电话列表
        map.put("rows", communityPhoneList);
        //封装社区电话条数
        map.put("total",count);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 社区电话启用功能
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:24:57
     * @param communityPhone
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/enableCommunityPhone", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String enableCommunityPhone( CommunityPhone communityPhone,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"社区电话"};
        //创建boolean变量 默认为false
        boolean result = false;
        //根据社区电话id查询社区电话信息
        CommunityPhone original=communityPhoneService.get(communityPhone.getId());
        //当返回社区电话信息为空时
        if(original == null ){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //已经为启用标示不能再启用
        if(ConstantStr.QY_FLAG.equals(original.getStatus()))
        {   
            //封装返回信息
            map.put("type", "Error");
            map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100013,arr));
        }else{
            //设置更新回数
            communityPhone.setUpdEac(original.getUpdEac());
            //更新共通字段
            this.editAttr(communityPhone);
            //启用社区电话  返回值为true 启用成功
            result=communityPhoneService.enableCommunityPhone(communityPhone);
            //启用社区电话成功
            if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100007,arr));
            //添加日志
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007,arr), ConstantStr.COMMUNITYPHONE, communityPhone.getId(), getUser());
        }else{
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100008,arr));
        } 
      }  
        //返回信息
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 社区电话禁用功能
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:24:34
     * @param communityPhone
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/disableCommunityPhone", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String disableCommunityPhone( CommunityPhone communityPhone,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map对象用于封装信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于返回数据
        ObjectMapper o = new ObjectMapper();
        //创建对象  用于提示信息
        Object[] arr={"社区电话"};
        //创建boolean变量 默认为false
        boolean result = false;
        //根据社区电话id查询社区电话信息
        CommunityPhone original=communityPhoneService.get(communityPhone.getId());
        //当社区电话信息为空时
        if(original == null ){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //已经为禁用标示不能再禁用
        if(ConstantStr.JY_FLAG.equals(original.getStatus())){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100014,arr));
        }else{
            //设置更新回数
            communityPhone.setUpdEac(original.getUpdEac());   
            //更新共通字段属性
        this.editAttr(communityPhone);
        //禁用社区电话  返回值为true 禁用成功
        result=communityPhoneService.disableCommunityPhone(communityPhone);
        //如果禁用社区电话成功
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100009,arr));
            //添加日志
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009,arr), ConstantStr.COMMUNITYPHONE, communityPhone.getId(), getUser());
        }else{
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100010,arr));
        }    }
        //返回信息
        return o.writeValueAsString(map);
    }
    
    
    /**
     * 
     * @Title
     * @Description 社区电话删除功能
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:24:57
     * @param communityPhone
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/deleteCommunityPhone", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String deleteCommunityPhone( CommunityPhone communityPhone,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"社区电话"};
        //创建boolean变量 默认为false
        boolean result = false;
        //根据社区电话id查询社区电话信息
        CommunityPhone original=communityPhoneService.get(communityPhone.getId());
        //但返回社区电话信息为空时
        if(original == null ){
            //封装返回数据
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //设置更新回数
        communityPhone.setUpdEac(original.getUpdEac());
        //更新共通字段属性
        this.editAttr(communityPhone);
        //删除社区电话信息  删除成功 返回true
        result=communityPhoneService.deleteCommunityPhone(communityPhone);
        //当删除社区电话成功
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.COMMUNITYPHONE, communityPhone.getId(), getUser());
        }else{
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
        }  
        //返回信息
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 跳转到社区电话新增页面
     * @author:QZG
     * @CreateDate:2016年7月21日 下午1:12:05
     * @param model
     * @return
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/toAddCommunityPhone", method = { RequestMethod.GET })
    public String toAddCommunityPhone(Model model) {
        //跳转到社区电话新增页面
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description 保存社区电话操作
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:30:25
     * @param communityPhone
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value="saveCommunityPhone",method={RequestMethod.POST})
    public @ResponseBody String saveCommunityPhone(CommunityPhone communityPhone,
            HttpServletRequest request, HttpServletResponse response)throws Exception{
        //创建map变量用于返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建mapper变量用户返回信息
        ObjectMapper mapper = new ObjectMapper();
        //创建对象用于提示信息
        Object[] arr = {"社区电话"};
        //新增社区电话操作判断是否存在已有社区电话  查询新增社区电话在数据库中数量
        Long i= communityPhoneService.findCount(communityPhone);
        //数据库中已存在该社区电话
        if(i>0){
           //封装返回信息
           map.put("type", "Error");
           map.put("msg", ComDefine.getMsg(ConstantStr.INFO100015,arr));
        }else{
            //添加共通字段属性
            this.addAttr(communityPhone);
            //设置社区电话表省id
            communityPhone.setProvinceId(villageService.get(communityPhone.getVillageId()).getProvinceId());
            //设置社区电话表市id
            communityPhone.setCityId(villageService.get(communityPhone.getVillageId()).getCityId());
            //设置社区电话表区id
            communityPhone.setCountryId(villageService.get(communityPhone.getVillageId()).getCountyId());
            //设置社区电话表社区id
            communityPhone.setCommunityId(villageService.get(communityPhone.getVillageId()).getCommunityId());

            //社区电话保存操作 返回保存条数  返回值大于0 保存成功
            int res = communityPhoneService.saveSelective(communityPhone);
            //社区电话保存成功
            if(res > 0){
               map.put("type", "Success");
               map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
               //添加日志信息
               syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.COMMUNITYPHONE, communityPhone.getId(), getUser());
               }
            else
            {   //保存失败
                //封装返回信息
                map.put("type", "Error");
                map.put("msg",ComDefine.getMsg(ConstantStr.INFO100002,arr));
            }
        }
        //返回数据
        return mapper.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 社区电话更新操作页面跳转
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:35:29
     * @param id
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/toupdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toupdate(@PathVariable Long id,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response){
        //封装社区电话id
        map.put("id", id);
        //返回社区电话修改页面
        return UPDATE;
    }
    /**
     * 
     * @Title
     * @Description 初始化页面数据
     * @author:QZG
     * @CreateDate:2016年7月19日 下午9:35:29
     * @param id
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //通过社区电话id查询社区电话信息        
        CommunityPhone communityPhone=communityPhoneService.get(id);
        //创建对象
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(communityPhone);
    }
    
    /**
     * 
     * @Title 
     * @Description 修改社区电话信息
     * @author:QZG
     * @CreateDate:2016年7月19日 下午2:41:35
     * @param communityPhone
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommunityPhone:manager")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(CommunityPhone communityPhone) throws Exception{
        //创建map对象用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于提示信息
        Object[] arr = {"社区电话"};
        //封装默认返回信息
        map.put("type", "Error");
        map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
        //创建boolean变量默认值为false
        boolean result = false;
        //根据社区电话id查询常用电话信息
        CommunityPhone original=communityPhoneService.get(communityPhone.getId());
        //设置跟新回数
        communityPhone.setUpdEac(original.getUpdEac());
        //更新共通属性
        this.editAttr(communityPhone);
        //修改社区电话信息 返回值为true 修改成功
        result=  communityPhoneService.updateCommunityPhone(communityPhone);
        //如果修改成功
        if(result){
            //封装返回信息
            map.put("type", "Success");
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.COMMUNITYPHONE, communityPhone.getId(), getUser());
        }else{
            //封装返回信息
            map.put("type", "Error");
            //添加日志信息
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
        }    
        //创建对象
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(map);
    }   
    
    /**
     * 
     * @Title
     * @Description 新增时操作时初始化社区下拉列表
     * @author:QZG
     * @CreateDate:2016年8月1日 上午10:55:26
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/initCommunity")
    @ResponseBody
    public String initCommunity(HttpServletRequest request) throws Exception {
        //创建变量用于返回数据
        ObjectMapper o = new ObjectMapper();
        //获取当前登录者id
        Long userId = getUserId();
        //创建用户级别列表对象
        List<UserAreaVo> userAreaList = new ArrayList<UserAreaVo>();
        //根据登录者id返回所管辖的所有小区
        userAreaList.addAll(communityPhoneService.selectAll(userId));
        //返回数据
        return o.writeValueAsString(userAreaList);
    }
    /**
     * 添加共通属性
     * @param _temp
     * @author:863SOFT-QZG
     */
    private void addAttr(CommunityPhone _temp){
        Long userId = getUserId();
        _temp.setInsId(userId);
        _temp.setUpdEac(0L);
        _temp.setUpdYmdhms(new Date());
        _temp.setInsYmdhms(new Date());
        _temp.setUpdId(userId);
        _temp.setDelFlag(ConstantStr.DELETE_N);
        _temp.setStatus(ConstantStr.QY_FLAG);
    }
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-QZG
     * @throws Exception 
     */
    private void editAttr(CommunityPhone _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
