package com.wooxun.geekdol.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.AppVersion;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.AppVersionService;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.SysdataVo;

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
@RequestMapping("appVersion")
public class AppVersionControl extends BaseController{

    @Autowired
    private SyslogService<Syslog> syslogService;
    @Autowired
    private SysDataService<SysData> sysdataService;
    
    @Autowired
    private AppVersionService<AppVersion> appVersionService;
    
    public static final String LIST = "system/appVersion/list";
    public static final String ADD = "system/appVersion/add";

    /**
     * 
     * @Title
     * @Description 跳转到列表界面
     * @author:张洋
     * @CreateDate:2016年8月9日14:18:07
     * @return
     */
    @RequiresPermissions("AppVersion:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String list() {
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 根据条件查询APP版本位置
     * @author:张洋
     * @CreateDate:2016年8月8日11:28:40
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AppVersion:view")
    @RequestMapping(value = "/selectList",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectList()throws Exception{
        //获取版本信息类型
        SysdataVo sysDataVo = new SysdataVo();
        sysDataVo.setType(ConstantStr.APPVERSION);
        List<SysData> list = sysdataService.querySysdataByBean(sysDataVo);
        Map<String,String> hm = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            hm.put(list.get(i).getValue(), list.get(i).getLable());
        }
        //条件查询，查询版本信息
        Map<String, Object> map = new HashMap<String, Object>();
        List<AppVersion> appVersionList = appVersionService.selectAll(new AppVersion());
        for (int i = 0; i < appVersionList.size(); i++) {
            AppVersion a = appVersionList.get(i);
            a.setName(hm.get(a.getName()));
            appVersionList.set(i, a);
        }
        map.put("rows", appVersionList);
        map.put("total",appVersionList.size());

        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(map);
    }
    /**
     * 
     * @Title
     * @Description 打开上传页面
     * @author:张洋
     * @CreateDate:2016年8月9日14:18:07
     * @return
     */
    @RequiresPermissions("AppVersion:manager")
    @RequestMapping(value = "/toAdd", method = {RequestMethod.POST,RequestMethod.GET})
    public String toAdd(){
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description 下载
     * @author:张洋
     * @CreateDate:2016年8月9日14:18:07
     * @return
     */
    @RequestMapping(value = "/download", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity<byte[]> download(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获得文件名并转换编码，拼接源文件路径
        String fileName = request.getParameter("fileName");
        String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        String path=ConstantStr.ANDROID_PATH + fileName;  
        File file=new File(path);  
        //用springMVC 下载文件
        HttpHeaders headers = new HttpHeaders();    
        headers.setContentDispositionFormData("attachment", dfileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED); 
    }
    
    /**
     * 
     * @Title
     * @Description 增加APP版本信息
     * @author:张洋
     * @CreateDate:2016年8月8日15:16:40
     * @param appVersion
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AppVersion:manager")
    @RequestMapping(value = "/saveAdd", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(@RequestParam("androidFile") MultipartFile file,AppVersion appVersion,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"APP版本"};
        String fileName = "";
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = ConstantStr.ANDROID_PATH + file.getOriginalFilename();
                fileName = file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //根据APP名称查询数据判断是新增版本还是更新版本
        String filePath = ConstantStr.WEB_HTTP + "/appVersion" + "/download" + "?fileName=" + fileName;
        AppVersion a = appVersionService.selectByName(appVersion.getName());
        int count = 0;
        if(a == null){
            appVersion.setAndroid(filePath);
            appVersion.setPublishDate(new Date());
            this.addAttr(appVersion);
            count = appVersionService.save(appVersion);
        }else{
            a.setAndroid(filePath);
            a.setVersionCode(appVersion.getVersionCode());
            a.setVersionName(appVersion.getVersionName());
            a.setPublishDate(new Date());
            this.editAttr(a);
            count = appVersionService.updateSelective(a);
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
    private void addAttr(AppVersion _temp) {
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
    private void editAttr(AppVersion _temp) {
        Long userId = getUserId();
        _temp.setUpdEac(_temp.getUpdEac() + 1);
        _temp.setUpdYmdhms(new Date());
        _temp.setUpdId(userId);
    }
}
