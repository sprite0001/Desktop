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
import com.wooxun.geekdol.hbridge.model.StaffLevel;
import com.wooxun.geekdol.hbridge.service.StaffLevelService;
import com.wooxun.geekdol.hbridge.vo.StaffLevelVo;
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
@RequestMapping("staffLevel")
public class StaffLevelControl extends BaseController{

    @Autowired
    private StaffLevelService<StaffLevel> staffLevelService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    
    public static final String LIST = "hbridge/staffLevel/list";
    private static final String ADD = "hbridge/staffLevel/add";
    private static final String UPDATE = "hbridge/staffLevel/update";
    
    /**
     * 
     * @Title
     * @Description 跳转到内参级别界面
     * @author:张洋
     * @CreateDate:2016年7月26日11:19:39
     * @return
     */
    @RequiresPermissions("StaffLevel:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public String list() {
        return LIST;
    }
    
    /**
     * 
     * @Title
     * @Description 打开新增页面
     * @author:张洋
     * @CreateDate:2016年7月26日11:44:18
     * @return
     */
    @RequiresPermissions("StaffLevel:manager")
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
     * @CreateDate:2016年7月26日11:44:18
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("StaffLevel:manager")
    @RequestMapping(value = "/toUpdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        setToken(model, request);
        return UPDATE;
    }
    
    /**
     * 
     * @Title
     * @Description 根据条件查询内参级别
     * @author:张洋
     * @CreateDate:2016年7月26日11:28:27
     * @param staffLevelVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("StaffLevel:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST})
    @ResponseBody
    public String selectList(StaffLevelVo staffLevelVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

        Map<String, Object> map = new HashMap<String, Object>();
        List<StaffLevel> staffLevelList = new ArrayList<StaffLevel>();
        staffLevelVo.setPageFlag(true);
        staffLevelVo.setStartPage((page-1)*rows);
        staffLevelVo.setEndPage(rows);
        //条件查询
        staffLevelList = staffLevelService.queryListByParam(staffLevelVo);
        
        map.put("rows", staffLevelList);
        map.put("total",staffLevelList.size());

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 查询全部内参级别，用于下拉框展示
     * @author:张洋
     * @CreateDate:2016年7月26日17:19:18
     * @param staffLevelVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectAll",method={RequestMethod.POST})
    @ResponseBody
    public String selectAll(StaffLevelVo staffLevelVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        List<StaffLevel> staffLevelList = staffLevelService.queryListByParam(staffLevelVo);
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(staffLevelList);
    }
    
    /**
     * 
     * @Title
     * @Description 增加内参级别信息
     * @author:张洋
     * @CreateDate:2016年7月26日11:52:50
     * @param staffLevelVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("StaffLevel:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(StaffLevel staffLevel,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"内参级别"};
        this.addAttr(staffLevel);
        //条件查询，查询是否有重复数据，有的话提示错误，否则正常保存
        StaffLevelVo slv = new StaffLevelVo();
        slv.setStaffCode(staffLevel.getStaffCode());
        List<StaffLevel> list = staffLevelService.queryListByParam(slv);
        if(list.size() > 0){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100072,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100072, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        }
        if(!verifyToken(request)){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100105,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100105, arr, ConstantStr.VILLAGENOTICE, null);
            return o.writeValueAsString(map);
        }
        int count = staffLevelService.save(staffLevel);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100001, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100002, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title
     * @Description 更新内参级别信息
     * @author:张洋
     * @CreateDate:2016年7月26日11:52:50
     * @param staffLevelVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("StaffLevel:manager")
    @RequestMapping(value = "/saveUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveUpdate(StaffLevel staffLevel,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"内参级别"};
        if(staffLevel.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //条件查询，查询是否有重复数据，有的话提示错误，否则正常保存
        StaffLevelVo slv = new StaffLevelVo();
        slv.setStaffCode(staffLevel.getStaffCode());
        List<StaffLevel> list = staffLevelService.queryListByParam(slv);
        if(list.size() > 0 && !list.get(0).getId().equals(staffLevel.getId())){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100072,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100072, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        }
        StaffLevel sl = staffLevelService.get(staffLevel.getId());
        if(sl == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021,arr));
            return o.writeValueAsString(map);
        }
        //设置更新属性并更新
        staffLevel.setUpdEac(sl.getUpdEac()+1L);
        staffLevel.setUpdId(getUserId());
        staffLevel.setUpdYmdhms(new Date());
        int count = staffLevelService.updateSelective(staffLevel);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100005, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100006, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        }
    }
    /**
     * 
     * @Title
     * @Description 删除内参级别信息
     * @author:张洋
     * @CreateDate:2016年7月26日11:52:50
     * @param staffLevelVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("StaffLevel:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(StaffLevel staffLevel,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"内参级别"};
        if(staffLevel.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //首先查找数据是否存在
        StaffLevel sl = staffLevelService.get(staffLevel.getId());
        if(sl == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021,arr));
            return o.writeValueAsString(map);
        }
        //设置删除属性和更新属性并更新 
        staffLevel.setUpdEac(sl.getUpdEac()+1L);
        staffLevel.setUpdId(getUserId());
        staffLevel.setUpdYmdhms(new Date());
        staffLevel.setDelFlag(ConstantStr.DELETE_Y);
        int count = staffLevelService.updateSelective(staffLevel);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100003, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.STAFFLEVEL, staffLevel.getId());
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 内参级别修改初始化
     * @Description 内参级别修改初始化
     * @author:张洋
     * @CreateDate:2016年7月26日14:05:59
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"内参级别"};
        if(id == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052,arr));
            return o.writeValueAsString(map);
        }
        //根据ID获取数据
        StaffLevel sl = staffLevelService.get(id);
        if(sl == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            return o.writeValueAsString(map);
        }
        return o.writeValueAsString(sl);
    }
    
    /**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(StaffLevel _temp) {
        Long userId = getUserId();
        _temp.setInsYmdhms(new Date());
        _temp.setInsId(userId);
        _temp.setUpdEac(0L);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
        _temp.setDelFlag(ConstantStr.DELETE_N);
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
