package com.wooxun.geekdol.hbridge.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.common.controller.CommonController;
import com.wooxun.geekdol.hbridge.model.NetizenAcumen;
import com.wooxun.geekdol.hbridge.service.NetizenAcumentService;
import com.wooxun.geekdol.hbridge.vo.NetizenAcumenVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年9月8日  下午3:20:01 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("netizenacumen")
public class NetizenAcumenController extends CommonController {
    
    @Autowired
    private NetizenAcumentService<NetizenAcumen> netizenAcumentService;
    
    @Autowired
    private SyslogService<Syslog> syslogService;
 
    //返回网民法眼信息列表页面
    private static final String LIST = "hbridge/netizenacumen/list";
    
    //返回网民法眼信息详情页面
    private static final String DETAILS = "hbridge/netizenacumen/details";
    /**
     * 
     * @Title
     * @Description 跳转到列表页面
     * @author:QZG
     * @CreateDate:2016年9月8日 下午3:22:44
     * @return
     */
    @RequiresPermissions("NetizenAcumen:view")
    @RequestMapping("list")
    public String list(){
        //跳转到列表页面
        return LIST;
    }
    
    /**
     * 
     * @Title
     * @Description 查询满足条件的网民法眼信息
     * @author:QZG
     * @CreateDate:2016年9月8日 下午9:15:18
     * @param netizenAcumenVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("NetizenAcumen:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(NetizenAcumenVo netizenAcumenVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //设置查询页面 初始化为1
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //设置每页查询条数  初始化为20
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建网名法眼信息列表类
        List<NetizenAcumenVo> netizenAcumenList = new ArrayList<NetizenAcumenVo>();
        //初始化网名法眼信息列表条数
        Long count = 0l;
        //设置分页标示为true
        netizenAcumenVo.setPageFlag(true);
        //设置查询起始记录
        netizenAcumenVo.setStartPage((page-1)*rows);
        //设置查询结束记录
        netizenAcumenVo.setEndPage(rows);
        //查询满足条件的网民法眼信息列表条数
        count = netizenAcumentService.findAllCount(netizenAcumenVo);
        //当列表条数大于0时
        if(count>0){
            //查询满足条件的网名法眼信息列表
            netizenAcumenList = netizenAcumentService.findAll(netizenAcumenVo);
        }
        //封装网民法眼信息列表
        map.put("rows", netizenAcumenList);
        //封装网民法眼信息列表条数
        map.put("total",count);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 网民法眼信息删除功能
     * @author:QZG
     * @CreateDate:2016年7月30日 下午4:24:57
     * @param netizenAcumenVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("NetizenAcumen:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete( NetizenAcumen netizenAcumen,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"网民法眼信息"};
        //创建boolean变量 默认为false
        boolean result = false;
        //根据网民法眼信息id查询网民法眼信息
        NetizenAcumen original=netizenAcumentService.get(netizenAcumen.getId());
        //当返回网民法眼信息为空时
        if(original == null ){
            //封装返回数据
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //设置更新回数
        netizenAcumen.setUpdEac(original.getUpdEac());
        //更新共通字段属性
        this.editAttr(netizenAcumen);
        //删除网民法眼信息  删除成功 返回true
        result=netizenAcumentService.deleteNetizenacumen(netizenAcumen);
        //当删除网民法眼信息成功
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
            //添加日志信息
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.NETIZENACUMEN, netizenAcumen.getId(), getUser());
        }else{
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
        }  
        //返回信息
        return o.writeValueAsString(map);
    }
    
    /**
     * @Title
     * @Description 跳转到网民法眼信息管理详情界面
     * @author:QZG
     * @CreateDate:2016年7月29日 下午2:06:15
     * @return
     * @throws Exception
     * 
     */
    @RequiresPermissions(value = { "NetizenAcumen:manager", "NetizenAcumen:view" }, logical = Logical.OR)
    @RequestMapping(value = "/details/{id}", method = { RequestMethod.POST, RequestMethod.GET })
    public String details(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            //封装id
            map.put("id", id);
            // 创建附件Model变量用于封装sql查询参数
    		Attach attach = new Attach();
    		// 设置附件类型
    		attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
    		// 设置源id
    		attach.setOwnerId(id);
    		// 设置源表名
    		attach.setOwnerTbType(ConstantStr.NETIZENACUMEN_17);
    		// 调用service方法根据网民法眼id获取附件信息
    		List<Attach> attchList = netizenAcumentService.findAttachByParam(attach);
    		//根据网民法眼信息查询网民法眼详细信息
    		NetizenAcumenVo netizenAcumenVo=netizenAcumentService.selectNetizenAcumenVo(id);
    		// 设置网民法眼信息发布时间字符串
    		netizenAcumenVo.setInsYmdhmsStr(DateUtil.format(netizenAcumenVo.getInsYmdhms(), "yyyy-MM-dd"));
    		// 原因解密
    		netizenAcumenVo.setReason(UnescapeUtil.unescape(netizenAcumenVo.getReason()));
    		//封装网民法眼详细信息
    		map.put("netizenAcumenVo", netizenAcumenVo);
    		// 将网民法眼附件信息封装在map中写入界面
    		map.put("attchList", attchList);
            //返回活动汇详情页面
            return DETAILS;
    }
    /**
     * 
     * @Title
     * @Description 根据网民法眼信息id查到找到网民法眼信息
     * @author:QZG
     * @CreateDate:2016年8月8日 下午2:50:51
     * @param id
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = { "ActivityCollection:manager", "ActivityCollection:view" }, logical = Logical.OR)
    @RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr={"网民法眼信息"};
        //根据网民法眼信息id查询网民法眼信息
        NetizenAcumenVo netizenAcumen = netizenAcumentService.selectNetizenAcumenVo(id);
        //当返回网民法眼信息为空时
        if(netizenAcumen == null ){
            //封装返回数据
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        // 原因解密
        netizenAcumen.setReason(UnescapeUtil.unescape(netizenAcumen.getReason()));
        //返回信息
        return o.writeValueAsString(netizenAcumen);
    }
    /**
     * 
     * @Title
     * @Description String自动转成date类型
     * @author:王肖东
     * @CreateDate:2016年7月30日 上午10:02:48
     * @param binder
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-QZG
     * @throws Exception 
     */
    private void editAttr(NetizenAcumen _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
