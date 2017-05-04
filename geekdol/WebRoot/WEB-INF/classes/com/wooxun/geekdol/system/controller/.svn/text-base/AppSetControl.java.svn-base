package com.wooxun.geekdol.system.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.AppSet;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.AppSetService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月9日09:58:31
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月9日09:58:34 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("appSet")
public class AppSetControl extends BaseController{

    @Autowired
    private SyslogService<Syslog> syslogService;
    @Autowired
    private SysDataService<SysData> sysdataService;
    
    @Autowired
    private AppSetService<AppSet> appSetService;
    
    public static final String ADD = "system/appSet/add";

    /**
     * 
     * @Title
     * @Description 打开上传页面
     * @author:张洋
     * @CreateDate:2016年8月9日14:18:07
     * @return
     */
    @RequiresPermissions("AppSet:manager")
    @RequestMapping(value = "/toAdd", method = {RequestMethod.POST,RequestMethod.GET})
    public String toAdd(Model model){
        List<AppSet> list = appSetService.selectAll(new AppSet());
        //初始化推荐店数量
        if(list != null && list.size() > 0){
            model.addAttribute("recomentNumber", list.get(0).getRecomentNumber());
        }else{
            model.addAttribute("recomentNumber", 0);
        }
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description 增加APP设置信息
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param appSet
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AppSet:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(AppSet appSet,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"APP设置"};
        //由于APP设置只有一条记录，所以查出所有，如果所有为空，则新插入，否则取第一条进行更新
        List<AppSet> list = appSetService.selectAll(new AppSet());
        AppSet a = null;
        if(list != null && list.size() > 0){
            a = list.get(0);
        }
        int count = 0;
        if(a == null){
            this.addAttr(appSet);
            count = appSetService.save(appSet);
            syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.APPSET, appSet.getId(), getUser());
        }else{
            this.editAttr(a);
            a.setRecomentNumber(appSet.getRecomentNumber());
            count = appSetService.updateSelective(a);
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.APPSET, appSet.getId(), getUser());
        }
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
     * 添加共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(AppSet _temp) {
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
    private void editAttr(AppSet _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
