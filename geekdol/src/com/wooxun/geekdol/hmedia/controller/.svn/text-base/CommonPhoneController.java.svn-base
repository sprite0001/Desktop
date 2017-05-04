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
import com.wooxun.geekdol.hmedia.model.CommonPhone;
import com.wooxun.geekdol.hmedia.service.CommonPhoneService;
import com.wooxun.geekdol.hmedia.vo.CommonPhoneVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员           修改日期                描述
 * 1.    QZG      2016年7月21日  上午10:55:25        创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("commonphone")
public class CommonPhoneController extends BaseController {
    //返回常用电话查询列表页面
    private static final String LIST = "hmedia/commonphone/list";
    //返回常用电话新增页面
    private static final String ADD = "hmedia/commonphone/add";
    //返回常用电话修改页面
    private static final String UPDATE="hmedia/commonphone/update";
    
    @Autowired
    private CommonPhoneService<CommonPhone> commonPhoneService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    
    /**
     * 
     * @Title
     * @Description 跳转到常用电话列表页面
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:32
     * @return
     */
    @RequiresPermissions("CommonPhone:view")
    @RequestMapping("list")
    public String list(){
        //跳转到常用电话列表页面
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 返回列表查询结果
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:48
     * @param commonPhoneVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommonPhone:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(CommonPhoneVo commonPhoneVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //设置查询页码 默认为0
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //设置每页查询条数 默认为20
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //创建map变量用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建常用电话列表类
        List<CommonPhoneVo> commonphoneList = new ArrayList<CommonPhoneVo>();
        //初始化常用电话列表查询条数
        Long count = 0l;
        //设置分页标示为true
        commonPhoneVo.setPageFlag(true);
        //设置查询的起始序号
        commonPhoneVo.setStartPage((page-1)*rows);
        //设置查询的结束序号
        commonPhoneVo.setEndPage(rows);
        //查询满足条件的常用电话列表条数
        count = commonPhoneService.findAllCount(commonPhoneVo);
        //当常用电话列表条数大于0
        if(count>0){
            //查询满足条件的常用电话列表
            commonphoneList = commonPhoneService.findAll(commonPhoneVo);
        }
        //封装常用电话列表
        map.put("rows", commonphoneList);
        //封装常用电话列表条数
        map.put("total",count);
        //创建对象
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 跳转到常用电话新增页面
     * @author:QZG
     * @CreateDate:2016年7月21日 下午1:12:05
     * @param model
     * @return
     */
    @RequiresPermissions("CommonPhone:manager")
    @RequestMapping(value = "/toAddCommonPhone", method = { RequestMethod.GET })
    public String toAddCommonPhone(Model model) {
        //跳转到常用电话新增页面
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description 常用电话保存操作
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:30:25
     * @param commonPhone
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommonPhone:manager")
    @RequestMapping(value="saveCommonPhone",method={RequestMethod.POST})
    public @ResponseBody String saveCommonPhone(CommonPhone commonPhone,
            HttpServletRequest request, HttpServletResponse response)throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建mapper变量用于返回信息
        ObjectMapper mapper = new ObjectMapper();
        /* 创建信息参数补充对象数组用于信息化参数的补充 */
        Object[] arr = {"常用电话"};
        //查询数据库中已存在该电话号码的条数 返回值大于0 标示已存在该电话号码
        Long i= commonPhoneService.findCount(commonPhone);
        //已存在该电话号码
        if(i>0){
           //封装返回的信息
           map.put("type", "Error");
           map.put("msg", ComDefine.getMsg(ConstantStr.INFO100015,arr));
        }else{
            //添加共通新增属性
            this.addAttr(commonPhone);
            //常用电话保存操作 返回保存条数 返回值大于0 保存成功
            int res = commonPhoneService.saveSelective(commonPhone);
            //保存成功
            if(res > 0){
                //封装返回的信息
               map.put("type", "Success");
               map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
               //调用日志service进行日志添加
               syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.COMMONPHONE, commonPhone.getId(), getUser());
               }
            //保存常用电话号码失败
            else
            {  
                //封装返回的信息
                map.put("type", "Error");
                map.put("msg",ComDefine.getMsg(ConstantStr.INFO100002,arr));
            }
        }
        //返回信息
        return mapper.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 常用电话删除操作
     * @author:QZG
     * @CreateDate:2016年7月21日 下午4:08:38
     * @param commonPhone
     * @param request
     * @param response
     * @throws Exception
     */
    @RequiresPermissions("CommonPhone:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void delete(CommonPhone commonPhone,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装信息
        Map<String, Object> map=new HashMap<String, Object>();
        /* 创建信息参数补充对象数组用于信息化参数的补充 */
        Object[] arr = {"常用电话"};
        //调用常用电话删除方法  返回值大于0 删除成功
        int result =commonPhoneService.delete(commonPhone);
        //删除成功
        if(result>0){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
            //调用日志service进行日志添加
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.COMMONPHONE, commonPhone.getId(), getUser());
        }else{
            //删除失败
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
        }
        //创建对象
        ObjectMapper o = new ObjectMapper();
        //信息转为string型
        String str=o.writeValueAsString(map);
        //信息写入response
        response.getWriter().write(str);
    }
    
    /**
     * 
     * @Title
     * @Description 常用电话更新操作页面跳转
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:35:29
     * @param id
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("CommonPhone:manager")
    @RequestMapping(value = "/toupdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toupdate(@PathVariable Long id,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response){
      map.put("id", id);
      //返回常用电话更新页面
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
    @RequiresPermissions("CommonPhone:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //通过常用电话id返回常用电话信息
        CommonPhone commonPhone=commonPhoneService.get(id);
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(commonPhone);
    }
    
    /**
     * 
     * @Title 
     * @Description 修改常用电话信息
     * @author:QZG
     * @CreateDate:2016年7月19日 下午2:41:35
     * @param commonPhone
     * @return
     * @throws Exception
     */
    @RequiresPermissions("CommonPhone:manager")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(CommonPhone commonPhone) throws Exception{
        //创建map变量用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        /* 创建信息参数补充对象数组用于信息化参数的补充 */
        Object[] arr = {"常用电话"};
        //封装默认返回信息
        map.put("type", "Error");
        map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
        //创建boolean变量 默认值为false
        boolean result = false;
        //通过常用电话id查询常用电话信息
        CommonPhone original=commonPhoneService.get(commonPhone.getId());
        //设置更新回数
        commonPhone.setUpdEac(original.getUpdEac());
        //修改共通属性
        this.editAttr(commonPhone);
        //修改常用电话信息  返回值为true 修改成功
        result=  commonPhoneService.updateCommonPhone(commonPhone);
        //修改常用电话成功
        if(result){
            //封装返回的信息
            map.put("type", "Success");
            //调用日志service方法进行日志添加
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.COMMONPHONE, commonPhone.getId(), getUser());
        }else{
            //修改常用电话失败
            //封装返回的信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
        }    
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(map);
    }   
    
/**
 * 添加共通属性
 * @param _temp
 * @author:863SOFT-QZG
 */
private void addAttr(CommonPhone _temp){
    Long userId = getUserId();
    _temp.setInsId(userId);
    _temp.setUpdEac(0L);
    _temp.setUpdYmdhms(new Date());
    _temp.setInsYmdhms(new Date());
    _temp.setUpdId(userId);
    _temp.setDelFlag(ConstantStr.DELETE_N);
}
/**
 * 修改共同属性
 * @param _temp
 * @author:863SOFT-QZG
 * @throws Exception 
 */
private void editAttr(CommonPhone _temp) throws Exception {
    CommonPhone original = commonPhoneService.get(_temp.getId());
    Long userId = getUserId();
    _temp.setUpdEac(original.getUpdEac() + 1);
    _temp.setUpdYmdhms(new Date());
    _temp.setUpdId(userId);
}
}
