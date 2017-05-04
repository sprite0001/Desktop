package com.wooxun.geekdol.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wooxun.geekdol.common.MD5Util;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.vo.UserVo;

/**
 * @Title
 * @Description 省管理控制器
 * @version 1.0.0
 * @Author 
 * @CreateDate 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	  张洋		   2016年7月21日14:34:25 	    新增方法
 * 2. 	 张洋	2016年7月22日17:52:53 			修改新增方法
 *==========================================================
 * 
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	/**用户列表页面*/
	public static final String USER = "system/user/list";
	private static final String ADD = "system/user/add";
	private static final String UPDATE = "system/user/update";
	
	@Autowired
	private UserService<User> userService;
    @Autowired
    private SysDataService<SysData> sysdataService;
    @Autowired
    private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title
	 * @Description 跳转到用户列表页面
	 * @author:zhougp
	 * @CreateDate:2016年7月19日 上午11:32:17
	 * @return
	 */
	@RequiresPermissions("PcUser:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public String list() {
		return USER;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 根据条件查询用户
	 * @author:zhougp
	 * @CreateDate:2016年7月19日 上午11:33:50
	 * @param searchUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("PcUser:view")
	@RequestMapping(value = "/selectList",method={RequestMethod.POST})
	@ResponseBody
	public String selectList(UserVo searchUser,HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> hm = new HashMap<String, String>();
		List<User> userList = new ArrayList<User>();
        List<UserVo> userVoList = new ArrayList<UserVo>();
        //获取用户类型
        List<SysData> sdlist = sysdataService.querySysdataByType(ConstantStr.USERTYPE);
        for (int i = 0; i < sdlist.size(); i++) {
            hm.put(sdlist.get(i).getValue(), sdlist.get(i).getLable());
        }
		searchUser.setPageFlag(true);
		searchUser.setStartPage((page-1)*rows);
		searchUser.setEndPage(rows);
		//查询所有用户
		Long count = userService.selectListCount(searchUser);
        userList = userService.selectListNoRole(searchUser);
		for (int i = 0; i < userList.size(); i++) {
		    UserVo uv = new UserVo();
		    BeanUtils.copyProperties(userList.get(i), uv);
		    uv.setUserTypeName(hm.get(uv.getUserType()));
		    userVoList.add(uv);
        }
		map.put("rows", userVoList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 打开新增页面
	 * @author:张洋
	 * @CreateDate:2016年7月21日14:26:33
	 * @return
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
	public String Add(){
		return ADD;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 打开编辑页面
	 * @author:张洋
	 * @CreateDate:2016年7月21日17:37:13
	 * @return
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/toEditUser/{userId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toEditUser(Model model,@PathVariable Long userId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("userId", userId);
		//初始化用户数据
		User user = userService.selectByUserId(userId);
		model.addAttribute("user",user);
		return UPDATE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 打开分配区域界面
	 * @author:张洋
	 * @CreateDate:2016年7月21日17:37:13
	 * @return
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/managerArea/{userId}", method = {RequestMethod.POST,RequestMethod.GET})
	public String managerArea(Model model,@PathVariable Long userId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("userId", userId);
		//初始化用户数据
		User user = userService.selectByUserId(userId);
		model.addAttribute("user",user);
		return "system/user/villageList";
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化密码
	 * @author:张洋
	 * @CreateDate:2016年7月21日17:37:13
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @return
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/initPassword", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String initPassword(Model model,User u,HttpServletRequest request,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		User user = userService.selectByUserId(u.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		if(user == null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100049));
			return o.writeValueAsString(map);
		}
		if(user.getPassword().equals(MD5Util.MD5(ConstantStr.DEFAULT_PSW))){
		    map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100069));
            return o.writeValueAsString(map);
		}
		//将密码设置为默认密码并设置更新属性
		user.setPassword(MD5Util.MD5(ConstantStr.DEFAULT_PSW));
		user.setUpdEac(user.getUpdEac() + 1L);
		user.setUpdId(getUserId());
		user.setUpdYmdhms(new Date());
		int count = userService.updateById(user);
		if(count==1){
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100050));
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100051));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title
	 * @Description 删除用户，逻辑删除，非物理删除
	 * @author:张洋
	 * @CreateDate:2016年7月22日09:09:54
	 * @return 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/deleteUser", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteUser(Model model,User u ,HttpServletRequest request,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		User user = userService.selectByUserId(u.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = {"用户"};
		ObjectMapper o = new ObjectMapper();
		if(user == null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100049));
			return o.writeValueAsString(map);
		}
		//设置删除属性和更新属性并更新
		user.setDelFlag(ConstantStr.DELETE_Y);
		user.setUpdEac(user.getUpdEac() + 1L);
		user.setUpdId(getUserId());
		user.setUpdYmdhms(new Date());
		int count = userService.updateById(user);
		if(count==1){
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100003, arr, ConstantStr.USER_TABLE, user.getId());
            return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
            insertLog(ConstantStr.DELETE, ConstantStr.INFO100004, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		}
	}
	
    /**
     * 
     * @Title
     * @Description 修改密码
     * @author:张洋
     * @CreateDate:2016年8月10日15:31:20
     * @return 
     * @throws Exception 
     */
    @RequestMapping(value = "/changePsw/{newpass}/{oldPass}", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String changePsw(Model model,@PathVariable String newpass,@PathVariable String oldPass,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] arr = {"用户"};
        ObjectMapper o = new ObjectMapper();
        User user = getUser();
        if(user == null){
            map.put("type", "Error");
            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100049));
            return o.writeValueAsString(map);
        }
        //设置新密码及更新属性并更新数据
        this.editAttr(user);
        String newPsw = MD5Util.MD5(newpass);
        String oldPsw = MD5Util.MD5(oldPass);
        if(!oldPsw.equals(user.getPassword())){
            map.put("type", "Error");
            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100107,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100107, arr, ConstantStr.USER_TABLE, user.getId());
            return o.writeValueAsString(map);
        }
        if(newPsw.equals(user.getPassword())){
            map.put("type", "Error");
            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100106,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100106, arr, ConstantStr.USER_TABLE, user.getId());
            return o.writeValueAsString(map);
        }
        user.setPassword(MD5Util.MD5(newpass));
        int count = userService.updateSelective(user);
        if(count==1){
            map.put("type", "Success");
            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100005, arr, ConstantStr.USER_TABLE, user.getId());
            return o.writeValueAsString(map);
        } else{
            map.put("type", "Error");
            map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100006,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100006, arr, ConstantStr.USER_TABLE, user.getId());
            return o.writeValueAsString(map);
        }
    }
    
    
	/**
	 * 
	 * @Title
	 * @Description 禁用/启用用户
	 * @author:张洋
	 * @CreateDate:2016年7月22日09:09:54
	 * @return 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String delete(Model model,User u ,HttpServletRequest request,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		User user = userService.selectByUserId(u.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"用户"};
		if(user == null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100049));
			return o.writeValueAsString(map);
		}
		if(u.getStatus() == null || u.getStatus().trim().equals("")){
			map.put("type", "Error");
			map.put("msg",  ComDefine.getMsg(ConstantStr.INFO100052));
			return o.writeValueAsString(map);
		}
		//设置新传入的状态和更新属性并更新
		user.setStatus(u.getStatus());
		user.setUpdEac(user.getUpdEac() + 1L);
		user.setUpdId(getUserId());
		user.setUpdYmdhms(new Date());
		int count = userService.updateById(user);
		if(count==1){
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100007));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100009, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100008));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100010, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改初始化用界面
	 * @author:张洋
	 * @CreateDate:2016年7月21日17:41:54
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/findById/{userId}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long userId, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.selectByUserId(userId);
		ObjectMapper o = new ObjectMapper();
		if(user == null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100045));
			return o.writeValueAsString(map);
		}
		if(user.getStatus().equals(ConstantStr.JY_FLAG)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100048));
			return o.writeValueAsString(map);
		}
		//将数据放到vo类中输出
		UserVo userVo = new UserVo();
		userVo.setUserName(user.getUserName());
		userVo.setRealName(user.getRealName());
		userVo.setEmail(user.getEmail());
		userVo.setMoblie(user.getMoblie());
		userVo.setUserType(user.getUserType());
		return o.writeValueAsString(userVo);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 增加用户信息
	 * @author:张洋
	 * @CreateDate:2016年7月21日15:57:42
	 * @param userVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/insert", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String insertOne( User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"用户"};
		this.addAttr(user);
		//判断用户名是否已存在
		User uTmp = userService.getByUserNameAll(user.getUserName());
		if(uTmp != null){
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100046));
			return o.writeValueAsString(map);
		}
		//设置密码，并检查是否有重复手机号
		user.setPassword(MD5Util.MD5(user.getPassword()));
		List<User> moList = userService.getByMobileAll(user.getMoblie());
		if(moList.size() > 0L){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100047));
			return o.writeValueAsString(map);
		}
		int count = userService.insertListCount(user);
		if(count==1){
			map.put("type", "Success");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100001, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
            insertLog(ConstantStr.INSERT, ConstantStr.INFO100002, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		}
	}
	/**
	 * 
	 * @Title
	 * @Description 更新用户信息
	 * @author:张洋
	 * @CreateDate:2016年7月21日18:13:25
	 * @param userVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequiresPermissions("PcUser:manager")
	@RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String update( User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"用户"};
		if(user.getId() == null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100044));
			return o.writeValueAsString(map);
		}
		//获取原始数据并判断是否已被删除
		User uTmp = userService.selectByUserId(user.getId());
		if(uTmp == null){
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100045));
			return o.writeValueAsString(map);
		}
		//判断用户名是否已存在
        User uuerTmp = userService.getByUserNameAll(user.getUserName());
        if(uuerTmp != null && !uuerTmp.getId().equals(uTmp.getId())){
            map.put("type", "Error");
            map.put("msg",ComDefine.getMsg(ConstantStr.INFO100046));
            return o.writeValueAsString(map);
        }
        //如果传入的手机号和根据ID查出的手机号一致，说明手机号未修改，则忽略手机号验证
        if(!uTmp.getMoblie().equals(user.getMoblie())){
            //手机号和原有的不一致，验证此手机号是否存在
            UserVo userVo = new UserVo();
            user.setPassword(MD5Util.MD5(user.getPassword()));
            userVo.setMoblie(user.getMoblie());
            Long mobileNum = userService.selectListCount(userVo);
            if(mobileNum > 0L){
                map.put("type", "Error");
                map.put("msg", ComDefine.getMsg(ConstantStr.INFO100047));
                return o.writeValueAsString(map);
            }
        }
        
		//设置更新属性并更新
		user.setUpdEac(uTmp.getUpdEac() + 1L);
		user.setUpdId(getUserId());
		user.setUpdYmdhms(new Date());
		
		int count = userService.updateById(user);
		if(count==1){
			map.put("type", "Success");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100005,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100005, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		} else{
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INFO100006,arr));
            insertLog(ConstantStr.UPDATE, ConstantStr.INFO100006, arr, ConstantStr.USER_TABLE, user.getId());
			return o.writeValueAsString(map);
		}
	}
	
	/**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
    private void addAttr(User _temp) {
        Long userId = getUserId();
        _temp.setStatus(ConstantStr.QY_FLAG);
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
   private void editAttr(User _temp) {
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
