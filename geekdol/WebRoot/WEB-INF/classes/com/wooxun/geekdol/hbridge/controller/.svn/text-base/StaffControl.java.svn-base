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
import com.wooxun.geekdol.hbridge.model.Staff;
import com.wooxun.geekdol.hbridge.model.StaffLevel;
import com.wooxun.geekdol.hbridge.service.StaffLevelService;
import com.wooxun.geekdol.hbridge.service.StaffService;
import com.wooxun.geekdol.hbridge.vo.StaffLevelVo;
import com.wooxun.geekdol.hbridge.vo.StaffVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

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
 * 1.  zhangyang	2016年7月26日  上午10:37:52 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("staff")
public class StaffControl extends BaseController{

    @Autowired
    private StaffService<Staff> staffService;
    
    @Autowired
    private StaffLevelService<StaffLevel> staffLevelService;
    
    @Autowired
    private SyslogService<Syslog> syslogService;
    
    public static final String LIST = "hbridge/staff/list";
    private static final String ADD = "hbridge/staff/add";
    private static final String UPDATE = "hbridge/staff/update";
    
    /**
     * 
     * @Title
     * @Description 跳转到内参界面
     * @author:张洋
     * @CreateDate:2016年7月26日14:40:09
     * @return
     */
    @RequiresPermissions("Staff:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
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
    @RequiresPermissions("Staff:manager")
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
    @RequiresPermissions("Staff:manager")
    @RequestMapping(value = "/toUpdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        //初始化内容参数
        if(id != null){
            Staff staff = staffService.get(id);
            model.addAttribute("content", staff.getContent());
        }
        setToken(model, request);
        return UPDATE;
    }
    
    /**
     * 
     * @Title 内参修改
     * @Description 初始化内参编辑页面
     * @author:张洋
     * @CreateDate:2016年7月27日10:51:16
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequiresPermissions("Staff:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        Staff staff = staffService.get(id);
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(staff);
    }
    
    /**
     * 
     * @Title
     * @Description 增加内参信息
     * @author:张洋
     * @CreateDate:2016年7月26日16:58:08
     * @param staff
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Staff:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(Staff staff,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"内参"};
        this.addAttr(staff);
        staff.setScannedNumber(0L);
        //为发布状态设置默认值并当状态为发布的时候初始化时间
        if(staff.getPublishStatus() == null || staff.getPublishStatus().trim().equals("")){
            staff.setPublishStatus(ConstantStr.WFB);
        }
        if(staff.getPublishStatus().equals(ConstantStr.FB)){
            staff.setPublishTime(new Date());
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        //保存数据
        int count = staffService.save(staff);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询内参
     * @author:张洋
     * @CreateDate:2016年7月26日17:28:05
     * @param staffVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Staff:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList(StaffVo staffVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> staffList = new ArrayList<>();
        //格式化时间
        if(staffVo.getPublishTimeBeginStr() != null && !staffVo.getPublishTimeBeginStr().trim().equals("")){
            staffVo.setPublishTimeBegin(DateUtil.parse(staffVo.getPublishTimeBeginStr()));
        }if(staffVo.getPublishTimeEndStr() != null && !staffVo.getPublishTimeEndStr().trim().equals("")){
            staffVo.setPublishTimeEnd(DateUtil.parse(staffVo.getPublishTimeEndStr()));
        }
        staffVo.setPageFlag(true);
        staffVo.setStartPage((page-1)*rows);
        staffVo.setEndPage(rows);
        //查询内参等级列表并放入map，方便翻译为汉字
        List<StaffLevel> slList = staffLevelService.queryListByParam(new StaffLevelVo());
        List<StaffVo> staffVoList = new ArrayList<>();
        Map<String,String> slMap = new HashMap<>();
        for (StaffLevel staffLevel : slList) {
            slMap.put(staffLevel.getStaffCode(), staffLevel.getStaffName());
        }
        //条件查询，并将内参级别翻译成汉字
        staffList = staffService.queryListByParam(staffVo);
        Long total = staffService.queryCountByParam(staffVo);
        for (int i = 0; i < staffList.size(); i++) {
            Staff obj = staffList.get(i);
            StaffVo sv = new StaffVo();
            BeanUtils.copyProperties(obj, sv);
            sv.setStaffLevelName(slMap.get(obj.getStaffLevel()));
            staffVoList.add(sv);
        }
        map.put("rows", staffVoList);
        map.put("total",total);

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title 删除内参，逻辑删除，非物理删除
     * @Description 根据id删除内参，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param staff
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("Staff:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(Staff staff,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市政内参"};
        ObjectMapper o = new ObjectMapper();
        if(staff.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        Staff sta = staffService.get(staff.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性和更新属性
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = staffService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.STAFF, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.STAFF, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 修改发布状态
     * @Description 修改发布状态
     * @author:张洋
     * @CreateDate:2016年7月27日11:56:48
     * @param staff
     * @param request
     * @param response
     * @return JSON字符串(type=success为成功,type=error为失败)
     * @throws Exception
     */
    @RequiresPermissions("Staff:manager")
    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String changeStatus(Staff staff,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市政内参"};
        ObjectMapper o = new ObjectMapper();
        if(staff.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        Staff sta = staffService.get(staff.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置更新属性和发布属性并更新数据
        staff.setUpdEac(sta.getUpdEac());
        if(staff.getPublishStatus().equals(ConstantStr.FB)){
            staff.setPublishTime(new Date());
        }else{
            staff.setPublishTime(null);
        }
        this.editAttr(staff);
        int count = staffService.updateSelective(staff);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.STAFF, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100006,arr), ConstantStr.STAFF, sta.getId(), getUser());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 更新内参
     * @Description 更新内参
     * @author:张洋
     * @CreateDate:2016年7月27日10:40:41
     * @param staff
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("Staff:manager")
    @RequestMapping(value = "/saveUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveUpdate(Staff staff,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"市政内参"};
        ObjectMapper o = new ObjectMapper();
        if(staff.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        Staff sta = staffService.get(staff.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置更新属性并更新数据
        staff.setUpdEac(sta.getUpdEac());
        this.editAttr(staff);
        if(staff.getPublishStatus() != null && !staff.getPublishStatus().trim().equals("")){
            if(staff.getPublishStatus().equals(ConstantStr.FB) && sta.getPublishStatus().equals(ConstantStr.WFB)){
                staff.setPublishTime(new Date());
            }
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = staffService.updateSelective(staff);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.STAFF, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100006, arr, ConstantStr.STAFF, sta.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(Staff _temp) {
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
    private void editAttr(Staff _temp) {
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
