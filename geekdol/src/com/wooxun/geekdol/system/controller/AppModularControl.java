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
import com.wooxun.geekdol.system.model.AppModular;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.AppModularService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.AppModularVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月8日14:45:19
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月8日  上午10:58:36 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("appModular")
public class AppModularControl extends BaseController{

    @Autowired
    private SyslogService<Syslog> syslogService;

    @Autowired
    private AppModularService<AppModular> appModularService;
    
    public static final String LIST = "system/appModular/list";
    public static final String ADD = "system/appModular/add";
    public static final String UPDATE = "system/appModular/update";

    /**
     * 
     * @Title
     * @Description 跳转到列表界面
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @return
     */
    @RequiresPermissions("AppModular:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 打开新增页面
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @return
     */
    @RequiresPermissions("AppModular:manager")
    @RequestMapping(value = "/toAdd", method = {RequestMethod.POST,RequestMethod.GET})
    public String toAdd(){
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description 打开编辑页面
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("AppModular:manager")
    @RequestMapping(value = "/toUpdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toUpdate(Model model,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        model.addAttribute("id", id);
        return UPDATE;
    }
    
    /**
     * 
     * @Title APP功能修改
     * @Description 初始化APP功能编辑页面
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param id
     * @param request
     * @param response
     * @return JSON数据(city对象)
     */
    @RequiresPermissions("AppModular:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        AppModular appModular = appModularService.get(id);
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(appModular);
    }
    
    /**
     * 
     * @Title
     * @Description 增加APP功能信息
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param appModular
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AppModular:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(AppModular appModular,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"APP功能"};
        this.addAttr(appModular);
        int count = appModularService.save(appModular);
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
     * @Description 根据条件查询APP功能
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param appModularVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AppModular:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList(AppModularVo appModularVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

        Map<String, Object> map = new HashMap<String, Object>();
        appModularVo.setPageFlag(true);
        appModularVo.setStartPage((page-1)*rows);
        appModularVo.setEndPage(rows);
        //条件查询并格式化位置
        List<AppModular> appModularList = appModularService.queryListByParam(appModularVo);
        List<AppModularVo> appModularVoList = new ArrayList<>();
        for (int i = 0; i < appModularList.size(); i++) {
            AppModular am = appModularList.get(i);
            AppModularVo amv = new AppModularVo();
            BeanUtils.copyProperties(am, amv);
            if(amv.getPosition() != null){
                amv.setPositionName(amv.getPosition().getName());
                appModularVoList.add(amv);
            }
        }
        map.put("rows", appModularVoList);
        map.put("total",appModularVoList.size());

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title 删除APP功能，逻辑删除，非物理删除
     * @Description 根据id删除APP功能，逻辑删除，非物理删除
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param appModular
     * @param request
     * @param response
     * @return JSON字符串(type=success为删除成功,type=error为删除失败)
     * @throws Exception
     */
    @RequiresPermissions("AppModular:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete(AppModular appModular,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"APP功能"};
        ObjectMapper o = new ObjectMapper();
        if(appModular.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052));
            return o.writeValueAsString(map);
        }
        //获取原有数据，并判断是否已被删除
        AppModular sta = appModularService.get(appModular.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置删除属性和编辑属性并更新
        sta.setDelFlag(ConstantStr.DELETE_Y);
        this.editAttr(sta);
        int count = appModularService.updateSelective(sta);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100003,arr));
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.STAFF, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 
     * @Title 更新APP功能
     * @Description 更新APP功能
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param appModular
     * @param request
     * @param response
     * @return JSON字符串(type=success为更新成功,type=error为更新失败)
     * @throws Exception
     */
    @RequiresPermissions("AppModular:manager")
    @RequestMapping(value = "/saveUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveUpdate(AppModular appModular,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"APP功能"};
        ObjectMapper o = new ObjectMapper();
        if(appModular.getId() == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100052));
            return o.writeValueAsString(map);
        }
        //获取原有数据，并判断是否已被删除
        AppModular sta = appModularService.get(appModular.getId());
        if(sta == null){
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034));
            return o.writeValueAsString(map);
        }
        //设置更新属性并更新
        appModular.setUpdEac(sta.getUpdEac());
        this.editAttr(appModular);
        int count = appModularService.updateSelective(appModular);
        if(count==1){
            map.put("type", "Success");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.STAFF, sta.getId(), getUser());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            return o.writeValueAsString(map);
        }
    }
    
    /**
     * 添加共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(AppModular _temp) {
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
    private void editAttr(AppModular _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
