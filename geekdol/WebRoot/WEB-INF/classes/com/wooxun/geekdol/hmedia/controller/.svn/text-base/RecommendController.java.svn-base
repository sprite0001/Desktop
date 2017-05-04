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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.hmedia.service.RecommendService;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月28日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月28日  下午1:56:35 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("recommend")
public class RecommendController extends BaseController{
    
    @Autowired
    private RecommendService<County> recommendService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    
    //返回列表页面
    private static final String LIST = "hmedia/recommend/list";
    //返回新增页面
    private static final String ADD = "hmedia/recommend/add";
     
    
    /**
     * 
     * @Title
     * @Description 跳转到列表页面
     * @author:QZG
     * @CreateDate:2016年7月18日 上午11:27:50
     * @return
     */
    @RequiresPermissions("Recommend:view")
    @RequestMapping("list")
    public String list(){
        //跳转到列表页面
        return LIST;
    }
    
    /**
     * 
     * @Title
     * @Description 返回列表查询结果
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:51:35
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Recommend:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(CountyQueryVo countyQueryVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
         //设置页面 默认为1
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //设置每页查询条数 默认为20
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //创建map变量用于返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建区列表类
        List<CountyQueryVo> countyList = new ArrayList<CountyQueryVo>();
        //初始化区查询条数
        Long count = 0l;
        //设置分页标示为true
        countyQueryVo.setPageFlag(true);
        //设置查询起始记录
        countyQueryVo.setStartPage((page-1)*rows);
        //设置查询结束记录
        countyQueryVo.setEndPage(rows);
        //查询满足条件的区列表条数
        count = recommendService.findRecommendCount(countyQueryVo);
        //当列表条数大于0
        if(count>0){
            //查询满足条件的区列表
            countyList = recommendService.findRecommend(countyQueryVo);
        }
        //封装区查询列表
        map.put("rows", countyList);
        //封装区查询条数
        map.put("total",count);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(map);
    }
    
    
    /**
     * 
     * @Title
     * @Description 新媒体推荐删除
     * @author:QZG
     * @CreateDate:2016年7月29日 上午10:00:01
     * @param county
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Recommend:manager")
    @RequestMapping(value = "/deleteRecommend", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String deleteRecommend( County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr={"区推荐"};
        //创建boolean变量result 默认为false
        boolean result = false;
        //根据区id查询区信息
        County original=recommendService.get(county.getCountyId());
        //设置更新回数
        county.setUpdEac(original.getUpdEac());
        //修改共通字段属性
        this.editAttr(county);
        //删除区推荐  返回值为true 删除成功
        result=recommendService.deleteRecommend(county);
        //当删除区推荐功能成功
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
        }else{
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
        }  
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 心媒体推荐启用功能 
     * @author:QZG
     * @CreateDate:2016年7月20日 下午4:39:34
     * @param appUser
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Recommend:manager")
    @RequestMapping(value = "/enableRecommend", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String enableRecommend( County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建变量用于封装信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"区推荐"};
        //创建boolean默认为false
        boolean result = false;
        //根据区id查询区信息
        County original=recommendService.get(county.getCountyId());
        //当区信息为空时
        if(original == null ){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //已经为启用标示不能再启用
        if(ConstantStr.QY_FLAG.equals(original.getRecommendFlag()))
        {
            //封装返回信息
            map.put("type", "Error");
            map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100013,arr));
        }else{
            //设置更新回数
            county.setUpdEac(original.getUpdEac());
            //修改共通字段属性
            this.editAttr(county);
            //启用社区推荐功能
            result=recommendService.enableRecommend(county);
            //当启用成功
            if(result){
                //封装返回信息
                map.put("type", "Success");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100007,arr));
                //添加日志信息
                syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
            }else{
                //启用失败
                //封装返回信息
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100008,arr));
        }  }  
        //返回信息
        return o.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 心媒体推荐禁用功能 
     * @author:QZG
     * @CreateDate:2016年7月20日 上午10:57:53
     * @param county
     * @param request
     * @param response
     * @throws Exception
     */
    @RequiresPermissions("Recommend:manager")
    @RequestMapping(value = "/disableRecommend", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String disableRecommend( County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map用于封装信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"区推荐"};
        //创建boolean变量 默认为false
        boolean result = false;
        //根据区id查询区信息
        County original=recommendService.get(county.getCountyId());
        //当返回区信息为空时
        if(original == null ){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //已经为禁用标示不能再禁用
        if(ConstantStr.JY_FLAG.equals(original.getRecommendFlag())){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100014,arr));
        }else{
            //设置更新回数
            county.setUpdEac(original.getUpdEac());   
            //修改共通字段属性
            this.editAttr(county);
            //禁用区推荐功能
            result=recommendService.disableRecommend(county);
        //当禁用区推荐功能成功
        if(result){
            //封装信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100009,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
        }else{
           //封装返回数据
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100010,arr));
        }    }
        //返回信息
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 跳转到新增页面
     * @author:QZG
     * @CreateDate:2016年7月23日 下午3:59:46
     * @param model
     * @return
     */
    @RequiresPermissions("Recommend:manager")
    @RequestMapping(value = "/insert", method = {RequestMethod.GET})
    public String insert(Model model){
        //跳转新增页面
        return ADD;
    }
    /**
     * 
     * @Title
     * @Description 新增推荐功能
     * @author:QZG
     * @CreateDate:2016年7月18日 下午5:52:16
     * @param county
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Recommend:manager")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public String add(County county,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr={"区推荐"};
        //创建boolean变量 默认为false
        boolean result=false;
        //根据区id查询区信息
        County original=recommendService.get(county.getCountyId());
        //设置更新回数
        county.setUpdEac(original.getUpdEac());   
        //已经为推荐标示不能再推荐
        if(original.getRecommendFlag()!=null)
        {   
            //封装返回信息
            map.put("type", "Error");
            map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100013,arr));
        }else
        {
            //更新共通字段属性
            this.editAttr(county);
            //推荐操作  返回值为true 操作成功
            result=recommendService.enableRecommend(county);
           //当推荐成功
           if(result){
              //封装返回信息
              map.put("type", "Success");
              map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
              //添加 日志信息
              syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.COUNTY, county.getCountyId(), getUser());
           }else{
               //封装返回信息
               map.put("type", "Error");
               map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
           }            
        }  
        //创建变量
        ObjectMapper mapper = new ObjectMapper();
        //返回信息
        return mapper.writeValueAsString(map);
    }
   
    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-QZG
     * @throws Exception 
     */
    private void editAttr(County _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
