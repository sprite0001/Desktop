package com.wooxun.geekdol.geekstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.GoodsClassific;
import com.wooxun.geekdol.geekstore.service.GoodsClassificService;
import com.wooxun.geekdol.geekstore.vo.GoodsClassificVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * 
* @Title
* @Description  商品分类控制类
* @version 1.0.0
* @Author 863soft-王肖东	
* @CreateDate 2016年7月25日 
* @param 
* @see  
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 王肖东	2016年7月25日  上午11:13:35 		创建
*==========================================================
*
 */
@Controller
@RequestMapping("goodsclassific")
public class GoodsClassificController extends BaseController{
	
	
	@Autowired
	private GoodsClassificService<GoodsClassific> goodsClassificService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	private static final String MAIN = "geekstore/goodsclassific/fenleiMain";
	private static final String TREE = "geekstore/goodsclassific/fenleiTree";
    private static final String LIST = "geekstore/goodsclassific/fenleiList";
    private static final String ADD = "geekstore/goodsclassific/fenleiAdd";
    
	public static final String UPDATE = "geekstore/goodsclassific/fenleiEditor";
    
	
	 /**
	  * 
	  * @Title
	  * @Description  跳转到商品分类主页面
	  * @author:王肖东
	  * @CreateDate:2016年7月25日 上午11:15:47
	  * @return
	  */
    @RequestMapping("goodsclassificMain")
    public String sysdataMain(){
        return MAIN;
    }
    
	
    /**
     * 
     * @Title
     * @Description 跳转到商品分类树页面
     * @author:王肖东
     * @CreateDate:2016年7月25日 上午11:16:21
     * @return
     */
    
    @RequestMapping("tree")
    public String tree(){
        return TREE;
    }
    
	
    /**
     * 
     * @Title
     * @Description  跳转到商品分类列表页面
     * @author:王肖东
     * @CreateDate:2016年7月25日 上午11:17:10
     * @return
     */
    @RequestMapping("list")
    public String list(@RequestParam String id,Map<String, Object> map){
    	map.put("id", id);
        return LIST;
    }
    
    /**
     * 
     * @Title
     * @Description  跳转到商品分类新增页面
     * @author:王肖东
     * @CreateDate:2016年7月25日 上午11:17:45
     * @return
     */
    @RequestMapping("toAddGoodsclassific")
    public String toAddGoodsclassific(@RequestParam String id,Map<String, Object> map){
    	User user=getUser();
    	map.put("user", user);
    	map.put("parentId", id);
        return ADD;
    }
    
    /**
     * 
     * @Title
     * @Description  跳转到商品分类修改页面
     * @author:王肖东
     * @CreateDate:2016年7月25日 上午11:18:02
     * @param id
     * @param map
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/toEdit/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public String toEdit(@PathVariable Long id,Map<String, Object> map,HttpServletRequest request, HttpServletResponse response){
		map.put("id", id);
		map.put("user",getUser());
		return UPDATE;
	}
	
	/**
	 * 
	 * @Title
	 * @Description  找到到商品分类详情
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:18:51
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findById/{id}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		GoodsClassific goodsClassific = goodsClassificService.selectGoodsClassificById(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(goodsClassific);
	}
    
    
	/**
	 * 
	 * @Title
	 * @Description  查询出全部商品分类
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:31:36
	 * @param searchSysdata
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findAll")
	public @ResponseBody String findAll(GoodsClassificVo searchGoodsClassific,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<GoodsClassific> goodsClassificListList = new ArrayList<GoodsClassific>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		searchGoodsClassific.setPageFlag(true);
		searchGoodsClassific.setStartPage((page-1)*rows);
		searchGoodsClassific.setEndPage(rows);
		
		count = goodsClassificService.findAllGoodsClassificCount(searchGoodsClassific);
		
		if(count>0){
			goodsClassificListList = goodsClassificService.findAllGoodsClassific(searchGoodsClassific);
		}
		
		map.put("rows", goodsClassificListList);
		map.put("total",count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	
	/**
	 * 
	 * @Title
	 * @Description  商品类别管理页面,根据父ID查询商品类别信息
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:31:36
	 * @param searchSysdata
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("typemag")
	public @ResponseBody String typemag(@RequestParam String id,Map<String, Object> map) throws Exception{

		List<GoodsClassific> typeList = goodsClassificService.getGoodsListByPid(Long.valueOf(id));
		 if((typeList != null) && (typeList.size() >0)){
             map.put("rows", typeList);
         }else{
         }
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	/**
	 * 
	 * @Title
	 * @Description  查询返回json格式的商品分类
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 下午6:38:46
	 * @return
	 */
	@RequestMapping(value = "/getTreeAll")
    @ResponseBody
    public String getTreeAll() throws Exception{
		JSONArray  json=JSONArray.fromObject(goodsClassificService.getGoodsTypeJsonTreeAll());
        return json.toString(); 
    }
	
	/**
	 * 
	 * @Title
	 * @Description  修改商品分类
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:34:41
	 * @param sysData
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveGoodsClassific",method={RequestMethod.POST})
	public @ResponseBody String saveGoodsClassific(GoodsClassific goodsClassific,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("type", "Error");
		map.put("msg", "保存失败 !!!");
		
		// 同一个类别下  编号 名称不能相等  
		if(hasGoodsClassific(goodsClassific)){
			map.put("type", "Error");
			map.put("msg", "存在相同名称和编号的商品分类!");
		}else{
			this.addAttr(goodsClassific);
			int res = goodsClassificService.save(goodsClassific);
			if(res > 0){
				map.put("type", "Success");
				map.put("msg", "保存成功!!!");
				syslogService.addLog(ConstantStr.INSERT, "商品分类保存成功!", ConstantStr.CITY, goodsClassific.getId(), getUser());
				
			}
		}
		
		return mapper.writeValueAsString(map);
	}
	
	
	/**
	 * 
	 * @Title
	 * @Description  拖拽  更换节点信息
	 * @author:王肖东
	 * @CreateDate:2016年7月27日 下午1:01:42
	 * @param goodsClassific
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="changeNode",method={RequestMethod.POST})
	public @ResponseBody String changeNode(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("type", "Error");
		map.put("msg", "节点拖拽失败 !");
		
		String id = request.getParameter("id");
		String targetId = request.getParameter("targetId");
		String point = request.getParameter("point");
		
		//原始节点
		GoodsClassific orginalNode =goodsClassificService.get(id);
		//目标节点
		GoodsClassific targetNode =goodsClassificService.get(targetId);
		
		if(orginalNode == null || targetNode == null){
			return mapper.writeValueAsString(map);
		}
		int result = 0;
		if("append".equals(point)){
			result=appendNode(orginalNode, targetNode);
		} else if("top".equals(point)){
			result=topNode(orginalNode, targetNode);
		}else if ("bottom".equals(point)) {
			result=bottomNode(orginalNode, targetNode);
		}
		if (result>0) {
			map.put("type", "Success");
			map.put("msg", "节点拖拽成功!");
		}
		return mapper.writeValueAsString(map);
	}
	
	private int appendNode(GoodsClassific orginalNode,GoodsClassific targetNode) throws Exception{
		orginalNode.setParentId(targetNode.getId());
		orginalNode.setUpdEac(orginalNode.getUpdEac());
		editAttr(orginalNode);
		return goodsClassificService.update(orginalNode);
	}
	private int topNode(GoodsClassific orginalNode,GoodsClassific targetNode) throws Exception{
		orginalNode.setParentId(targetNode.getParentId());
		orginalNode.setUpdEac(orginalNode.getUpdEac());
		editAttr(orginalNode);
		return goodsClassificService.update(orginalNode);
	}
	private int bottomNode(GoodsClassific orginalNode,GoodsClassific targetNode) throws Exception{
		orginalNode.setParentId(targetNode.getId());
		orginalNode.setUpdEac(orginalNode.getUpdEac());
		editAttr(orginalNode);
		return  goodsClassificService.update(orginalNode);
	}
	
	/**
	 * 
	 * @Title 
	 * @Description   更新子分类
	 * @author:王肖东  
	 * @CreateDate:2016年7月26日 上午11:28:27
	 * @param goodsClassific
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String update(GoodsClassific goodsClassific, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "Error");
		map.put("msg", "更新数据失败！");
		
		// 同一个类别下  编号 名称不能相等  
		if(hasGoodsClassific(goodsClassific)){
			map.put("type", "Error");
			map.put("msg", "存在相同名称和编号的商品分类!");
		}else{
			int result;
			this.editAttr(goodsClassific);
	        result = goodsClassificService.updateGoodsClassific(goodsClassific);
			if(result>0){
				map.put("type", "Success");
				map.put("msg", "更新数据成功！");
				syslogService.addLog(ConstantStr.UPDATE, "更新数据成功", "商品分类表", goodsClassific.getId(), getUser());
			}
		}
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	
	/**
	 * 
	 * @Title
	 * @Description  删除商品分类
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:41:44
	 * @param sysData
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String delete(GoodsClassific goodsClassific, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		
		ObjectMapper o = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "Error");
		map.put("msg", "删除数据失败！");
		
		//判断要删除的节点下面是否有子分类  如果有则不能删除
		List<GoodsClassific> childrenList=goodsClassificService.getGoodsListByPid(goodsClassific.getId());
		 if (childrenList.size()>0) {
			map.put("type", "Error");
			map.put("msg", "请先删除子分类！");
			return o.writeValueAsString(map);
		 }
		int result;
		this.editAttr(goodsClassific);
		result = goodsClassificService.updateGoodsClassific(goodsClassific);
		if(result>0){
			map.put("type", "Success");
			map.put("msg", "删除分类成功！");
			syslogService.addLog(ConstantStr.DELETE, "删除分类成功", "商品分类表", goodsClassific.getId(), getUser());
		}
		return o.writeValueAsString(map);
	}
	
	/**
     * 添加用户共通属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void addAttr(GoodsClassific _temp) {
		Long userId = getUserId();
		_temp.setStatus(ConstantStr.QY_FLAG);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag("0");
	}
	
	 /**
     * 修改共同属性
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttr(GoodsClassific _temp) {
		GoodsClassific original = goodsClassificService.selectGoodsClassificById(_temp.getId());
		Long userId = getUserId();
		_temp.setUpdEac(original.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description  同一个类别下  编号 名称不能相等   
	 * @author:王肖东
	 * @CreateDate:2016年7月25日 上午11:36:45
	 * @param goodsClassific
	 * @return
	 */
	private boolean hasGoodsClassific(GoodsClassific goodsClassific){
		Long count =goodsClassificService.queryCountByYanzheng(goodsClassific);
		return count>0?true:false;
	}
}
