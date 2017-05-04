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
import com.wooxun.geekdol.hmedia.model.Position;
import com.wooxun.geekdol.hmedia.service.PositionService;
import com.wooxun.geekdol.hmedia.vo.PositionVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年8月1日  下午6:01:16 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("position")
public class PositionController extends BaseController{
   
    //返回到广告位置管理列表页面
    private static final String LIST = "hmedia/position/list";
    //返回到广告位置管理新增页面
    private static final String ADD = "hmedia/position/add";
    //返回到广告位置管理修改页面
    private static final String UPDATE="hmedia/position/update";
    
    @Autowired
    private PositionService<Position> positionService;
    @Autowired
    private SyslogService<Syslog> syslogService;
    
    /**
     * 
     * @Title
     * @Description 跳转到广告位置管理列表页面
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:32
     * @return
     */
    @RequiresPermissions("Position:view")
    @RequestMapping("list")
    public String list(){
        //跳转到广告位置列表页面
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 返回列表查询结果
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:48
     * @param positionVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Position:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(PositionVo positionVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //设置查询页码 默认为1
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //设置每页查询条数  默认为20
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        //创建map用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建广告位置列表类
        List<PositionVo> positionList = new ArrayList<PositionVo>();
        //初始化广告位置信息查询条数
        Long count = 0l;
        //设置分页标示为true
        positionVo.setPageFlag(true);
        //设置查询起始记录
        positionVo.setStartPage((page-1)*rows);
        //设置查询结束记录
        positionVo.setEndPage(rows);
        //查询满足条件的广告位置列表条数
        count = positionService.findAllCount(positionVo);
        //当广告列表条数大于0时
        if(count>0){
            //查询满足条件的广告位置列表信息
            positionList = positionService.findAll(positionVo);
        }
        //封装广告位置列表
        map.put("rows", positionList);
        //封装广告位置查询条数
        map.put("total",count);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回数据
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 跳转到新增页面
     * @author:QZG
     * @CreateDate:2016年7月21日 下午1:12:05
     * @param model
     * @return
     */
    @RequiresPermissions("Position:manager")
    @RequestMapping(value = "/toAddPosition", method = { RequestMethod.GET })
    public String toAddPosition(Model model) {
       //跳转到广告位置新增页面
        return ADD;
    }
    
    
    
    /**
     * 
     * @Title
     * @Description 新增广告位置信息
     * @author:QZG
     * @CreateDate:2016年8月2日 上午9:24:29
     * @param position
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Position:manager")
    @RequestMapping(value="savePosition",method={RequestMethod.POST})
    public @ResponseBody String saveCommonPhone(Position position,
            HttpServletRequest request, HttpServletResponse response)throws Exception{
        //创建map变量用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper mapper = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr = {"广告位置"};
        //创建对象用于信息提示
        Object[] positionnumber={"广告编码"};
        //返回新增广告编码数据库中存在的数量
        Long i= positionService.findCount(position);
        //当数据库中已存在该广告位置编码
        if(i>0){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100015,positionnumber));
        }else{
            //添加共通字段属性
            this.addAttr(position);
            //广告位置保存操作 返回保存条数 返回值大于0 保存成功
            int res = positionService.saveSelective(position);
            //当保存成功时
            if(res > 0){
               //封装返回信息
               map.put("type", "Success");
               map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
               //添加日志信息
               syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr),ConstantStr.POSITION, position.getPositionId(), getUser());
               }
            else
            {
                //封装返回信息
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
     * @Description 广告位置删除操作
     * @author:QZG
     * @CreateDate:2016年7月21日 下午4:08:38
     * @param position
     * @param request
     * @param response
     * @throws Exception
     */
    @RequiresPermissions("Position:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String delete( Position position,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //创建map对象用于封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于返回信息
        ObjectMapper o = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr={"广告位置"};
        //创建boolean变量 默认为false
        boolean result = false;
        //根据广告位置id查询广告信息
        Position original=positionService.get(position.getPositionId());
        //当广告位置信息为空时
        if(original == null ){
            //封装返回信息
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034,arr));
            //返回信息
            return o.writeValueAsString(map);
        }
        //设置更新回数
        position.setUpdEac(original.getUpdEac());
        //修改共通字段属性
        this.editAttr(position);
        //删除广告位置信息 返回值为true 删除成功
        result=positionService.deletePosition(position);
        //当广告位置删除成功
        if(result){
            //封装返回信息
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
            //添加日志
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.POSITION, position.getPositionId(), getUser());
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
     * @Description 广告位置管理更新操作页面跳转
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:35:29
     * @param positionId
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("Position:manager")
    @RequestMapping(value = "/toupdate/{positionId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toupdate(@PathVariable Long positionId,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response){
        //封装广告位置id信息
        map.put("positionId", positionId);
        //跳转广告位置更新页面
        return UPDATE;
    }
    /**
     * 
     * @Title
     * @Description 初始化页面数据
     * @author:QZG
     * @CreateDate:2016年7月19日 下午9:35:29
     * @param positionId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Position:manager")
    @RequestMapping(value = "/findById/{positionId}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long positionId, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        //通过广告位置id查询广告位置信息
        Position position=positionService.get(positionId);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(position);
    }
    
    /**
     * 
     * @Title 
     * @Description 修改广告位置管理信息
     * @author:QZG
     * @CreateDate:2016年7月19日 下午2:41:35
     * @param keywords
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Position:manager")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(Position position) throws Exception{
        //创建map变量用于返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr = {"广告位置"};
        //封装默认信息
        map.put("type", "Error");
        map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
        //根据广告位置id查询广告位置信息
        Position original=positionService.get(position.getPositionId());
        //设置更新回数
        position.setUpdEac(original.getUpdEac());
        //修改共通字段属性
        this.editAttr(position);
        //修改广告位置信息 返回值大于0 修改成功
        int res=  positionService.updateSelective(position);
        //当修改广告位置成功
        if(res>0){
            //封装返回信息
            map.put("type", "Success");
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.POSITION,position.getPositionId(), getUser());
        }else{
            //封装返回数据
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
        }    
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
        return o.writeValueAsString(map);
    }   
    
    /**
     * @Title
     * @Description 判断广告位置编号是否重复
     * @author:kangtianyu
     * @CreateDate:2016年9月23日 下午2:26:15
     * @param positionCode
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkPositionCode", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String checkPositionCode(String positionCode, Long id) throws Exception {
		// 创建Map变量用于存放数据用于向界面写入
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建对象映射变量用于转换Map变量为json变量
		ObjectMapper o = new ObjectMapper();
		
		// 调用service方法查看广告位置编码是否重复
		boolean bl = positionService.findPositionCode(positionCode.replaceAll(" ", "").replaceAll("　", ""), id);
		
		if(!bl){ // 如果不存在
			
			/* 向map中封装成功信息 */
			map.put("type", "Success");
		}else{ // 如果存在
			
			/* 向map中封装失败信息 */
			map.put("type", "Error");
		}
		
		// 将Map变量转变为json变量写入界面由easyui进行操作json
		return o.writeValueAsString(map);
	}
    
/**
 * 添加共通属性
 * @param _temp
 * @author:863SOFT-QZG
 */
private void addAttr(Position _temp){
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
 */
private void editAttr(Position _temp)  {
    Long userId = getUserId();
    _temp.setUpdEac(_temp.getUpdEac() + 1);
    _temp.setUpdYmdhms(new Date());
    _temp.setUpdId(userId);
}
}
