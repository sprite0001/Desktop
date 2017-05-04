package com.wooxun.geekdol.system.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringBufferInputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.AppVersion;
import com.wooxun.geekdol.system.model.Keywords;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.KeywordsService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.KeywordsVo;

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
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG  	  2016年7月21日  上午10:55:25 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("keywords")
public class KeyWordsController extends BaseController {
   
    /**返回关键字库查询列表页面*/
    private static final String LIST = "system/keywords/list";
    
    /**返回关键字库新增页面*/
    private static final String ADD = "system/keywords/add";
    
    /**返回关键字库修改页面*/
    private static final String UPDATE="system/keywords/update";

    /**返回关键字库导入页面*/
    private static final String IMPORT = "system/keywords/import";
    
    @Autowired
    private KeywordsService<Keywords> keywordsService;
   
    @Autowired
    private SyslogService<Syslog> syslogService;
     
    /**
     * 
     * @Title
     * @Description 跳转到关键字库列表页面
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:32
     * @return
     */
    @RequiresPermissions("Keywords:view")
    @RequestMapping("list")
    public String list(){
        //跳转到关键字列表页面
        return LIST;
    }
    /**
     * 
     * @Title
     * @Description 返回列表查询结果
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:59:48
     * @param countyQueryVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Keywords:view")
    @RequestMapping("findAll")
    public @ResponseBody String findAll(KeywordsVo keywordsVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //定义页数信息(默认为1)
        int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
        //定义每页查询数量(默认为20)
        int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
        // 创建Map变量用于存放数据用于向界面写入
        Map<String, Object> map = new HashMap<String, Object>();
        //创建关键字库列表对象
        List<KeywordsVo> keywordsList = new ArrayList<KeywordsVo>();
        //初始化关键字查询条数
        Long count = 0l;
        //设置分页标示为启用
        keywordsVo.setPageFlag(true);
        //设置查询起始记录
        keywordsVo.setStartPage((page-1)*rows);
        //设置查询结束记录
        keywordsVo.setEndPage(rows);
        //查询满足条件的关键字列表
        count = keywordsService.findAllCount(keywordsVo);
        //当关键字数量大于0时
        if(count>0){
            //查询满足条件的关键字列表
            keywordsList = keywordsService.findAll(keywordsVo);
        }
        //封装关键字列表
        map.put("rows", keywordsList);
        //封装关键字查询条数
        map.put("total",count);
        //创建变量
        ObjectMapper o = new ObjectMapper();
        //返回信息
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
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value = "/toAddKeywords", method = { RequestMethod.GET })
    public String toAddKeywords(Model model) {
        //跳转到关键字新增页面
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description 跳转到导入页面
     * @author:张洋
     * @CreateDate:2016年9月9日14:22:41
     * @param model
     * @return
     */
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value = "/toImportKeywords", method = { RequestMethod.GET })
    public String toImportKeywords(Model model) {
        //跳转到关键字导入页面
        return IMPORT;
    }
    
    /**
     * 
     * @Title
     * @Description 保存关键字库操作
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:30:25
     * @param keywords
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value="saveKeyWords",method={RequestMethod.POST})
    public @ResponseBody String saveKeyWords(Keywords keywords,
            HttpServletRequest request, HttpServletResponse response)throws Exception{
        //创建map变量用于封装信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建变量用于返回信息
        ObjectMapper mapper = new ObjectMapper();
        //创建对象用于信息提示
        Object[] arr = {"关键字"};
        //新增关键字操作判断是否存在已有关键字 查询新增关键字在数据库中数量
        Long i= keywordsService.findCount(keywords);
        //当数据库中已存在该新增关键字
        if(i>0){
           //封装返回信息
           map.put("type", "Error");
           map.put("msg", ComDefine.getMsg(ConstantStr.INFO100015,arr));
        }else{
            //封装返回信息
            this.addAttr(keywords);
            //关键字保存操作 返回保存条数
            int res = keywordsService.saveSelective(keywords);
            //保存成功
            if(res > 0){
               //封装返回信息
               map.put("type", "Success");
               map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
               //添加日志信息
               syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.KEYWORDS, keywords.getId(), getUser());
               }
            else
            {   //封装返回信息
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
     * @Description 导入关键词库信息
     * @author:张洋
     * @CreateDate:2016年9月9日14:24:48
     * @param file
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("AppVersion:manager")
    @RequestMapping(value = "/saveImport", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String saveAdd(@RequestParam("keywordFile") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper o = new ObjectMapper();
        Object[] arr = {"关键字"};
        String filePath = "";
        if (!file.isEmpty()) {
            try {
                // 文件保存路径，临时使用一下，过后删除
                filePath = ConstantStr.ANDROID_PATH + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //读取文件内容到list中
        final List<Keywords> list = new ArrayList<>();
        FileReader reader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(reader);
        while(br.readLine() != null) {
            Keywords kw = new Keywords();
            kw.setKeywordsContent(br.readLine());
            this.addAttr(kw);
            list.add(kw);
        }
        br.close();
        reader.close();
        //由于关键词库可能会很多，插入会很慢，为了能及时给前台反馈，故新启线程插入，不影响前后台交互
        new Thread(){
            public void run() {
                keywordsService.insertList(list);
            }
        }.start();
        //插入完成后删除临时文件
        File f = new File(filePath);
        if(f.exists()){
            f.delete();
        }
        map.put("type", "Success");
        map.put("msg",ComDefine.getMsg(ConstantStr.INFO100001,arr));
        syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.KEYWORDS, null, getUser());
        return o.writeValueAsString(map);
    }
    
    /**
     * 
     * @Title
     * @Description 关键字库删除操作
     * @author:QZG
     * @CreateDate:2016年7月21日 下午4:08:38
     * @param keywords
     * @param request
     * @param response
     * @throws Exception
     */
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void delete(Keywords keywords,HttpServletRequest request,HttpServletResponse response) throws Exception{
        
        Map<String, Object> map=new HashMap<String, Object>();
        
        Object[] arr = {"关键字"};
        
        int result =keywordsService.delete(keywords);
        
        if(result>0){
          
            map.put("type", "Success");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003,arr));
           
            syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003,arr), ConstantStr.KEYWORDS, keywords.getId(), getUser());
        }else{
           
            map.put("type", "Error");
            map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004,arr));
        }
       
        ObjectMapper o = new ObjectMapper();
        
        String str=o.writeValueAsString(map);
       
        response.getWriter().write(str);
    }
    
    /**
     * 
     * @Title
     * @Description 关键字库更新操作页面跳转
     * @author:QZG
     * @CreateDate:2016年7月21日 下午7:35:29
     * @param id
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value = "/toupdate/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String toupdate(@PathVariable Long id,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response){
      
        map.put("id", id);
      
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
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
            HttpServletResponse response)throws Exception {
        
        Keywords keywords=keywordsService.get(id);
        
        ObjectMapper o = new ObjectMapper();
       
        return o.writeValueAsString(keywords);
    }
    
    /**
     * 
     * @Title 
     * @Description 修改关键字库信息
     * @author:QZG
     * @CreateDate:2016年7月19日 下午2:41:35
     * @param keywords
     * @return
     * @throws Exception
     */
    @RequiresPermissions("Keywords:manager")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(Keywords keywords) throws Exception{
        //创建map变量用于封装信息
        Map<String, Object> map = new HashMap<String, Object>();
        //创建对象用于信息提示
        Object[] arr = {"关键字"};
        //封装默认信息
        map.put("type", "Error");
        map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005,arr));
        //创建boolean 变量 默认为false
        boolean result = false;
        //根据关键字id查询关键字信息
        Keywords original=keywordsService.get(keywords.getId());
        //设置更新回数
        keywords.setUpdEac(original.getUpdEac());
        //修改共通字段属性
        this.editAttr(keywords);
        //更新关键字信息 返回值为true 更新成功
        result=  keywordsService.updateKeywords(keywords);
        //当关键字更新成功时
        if(result){
            //封装返回信息
            map.put("type", "Success");
            //添加日志信息
            syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005,arr), ConstantStr.KEYWORDS, keywords.getId(), getUser());
        }else{
            //封装返回信息
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
private void addAttr(Keywords _temp){
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
private void editAttr(Keywords _temp) throws Exception {
    Long userId = getUserId();
    _temp.setUpdEac(_temp.getUpdEac() + 1);
    _temp.setUpdYmdhms(new Date());
    _temp.setUpdId(userId);
}
}
