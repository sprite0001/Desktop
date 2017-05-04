package com.wooxun.geekdol.geekstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.wooxun.geekdol.geekstore.model.GrouponDemand;
import com.wooxun.geekdol.geekstore.model.GrouponDemandComment;
import com.wooxun.geekdol.geekstore.service.GrouponDemandService;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentReturnInfoVo;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandCommentVo;
import com.wooxun.geekdol.geekstore.vo.GrouponDemandVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * @Title 团购管理-我要团管理
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月1日  下午2:42:54 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("grouponDemand")
public class GrouponDemandController extends BaseController{
	public static final String LIST = "geekstore/grouponDemand/list";
	public static final String DETAILS = "geekstore/grouponDemand/details";
	public static final String RETURNDETAILS = "geekstore/grouponDemand/returnDetails";
	public static final String ECHARTS = "geekstore/grouponDemand/census";	
	@Autowired
	private GrouponDemandService<GrouponDemand> grouponDemandService;
	@Autowired
	private UserAreaService<UserArea> userAreaService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 跳转到我要团管理页面
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午3:08:03
	 * @return
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(){
		return LIST;
	}
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 跳转到统计页面
	 * @author:田振兴
	 * @CreateDate:2016年8月19日 下午3:02:59
	 * @return
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/echarts", method = {RequestMethod.GET,RequestMethod.POST})
	public String echarts(){
		return ECHARTS;
	}
	
	/**
	 * 
	 * @Title 团购管理-我要团管理
	 * @Description 我要团管理列表
	 * @author:田振兴
	 * @CreateDate:2016年8月19日 下午3:03:47
	 * @param grouponDemandVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( GrouponDemandVo grouponDemandVo,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//翻页功能，找到是第几页
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		//翻页功能，设置一页有多少条数据
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<GrouponDemandVo> grouponDemandlist= new ArrayList<GrouponDemandVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		//设置要翻页
		grouponDemandVo.setPageFlag(true);
		//设置开始的条数
		grouponDemandVo.setStartPage((page-1)*rows);
		//设置结束的条数
		grouponDemandVo.setEndPage(rows);
		// 获取当前用户的级别
		String areaLevel = getCurrentAreaLevel();
		//如果用户没有级别，返回空数据
		if(StringUtils.isBlank(areaLevel)){
			return o.writeValueAsString("");
		}
		//给Vo赋值（用户的级别、用户ID、商品分类类型）
		grouponDemandVo.setCurrentUserId(getUserId());
		grouponDemandVo.setAreaLevel(areaLevel);
		//商品分类类型，在常量里定死，对应字典表里的type
		grouponDemandVo.setClassificType(ConstantStr.COMMODITYCLASSIFICATION);
		//把VO当做参数查找出我要团列表
		count = grouponDemandService.findAllGrouponDemandCount(grouponDemandVo);
		//把VO当做参数查找出我要团列表总条数
		grouponDemandlist =grouponDemandService.findAllGrouponDemand(grouponDemandVo);
		map.put("rows", grouponDemandlist);
		map.put("total",count);
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 收藏
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午6:13:38
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:manager")
	@RequestMapping(value = "/collectGrouponDemand",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String collectGrouponDemand(Long id,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//根据ID找到这条数据的信息
		GrouponDemand grouponDemand = grouponDemandService.findByIdGrouponDemand(id);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"收藏"};
		//如果这条数据信息不存在，返回信息提示
		if(grouponDemand==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果这条数据信息存在，修改这条数据的公共字段，把收藏字段设置为已经收藏
		grouponDemand.setCollectFlag(ConstantStr.YSC);//已收藏
		this.editAttr(grouponDemand);//修改公共字段
		//修改这条数据的信息
		int count = grouponDemandService.updateSelective(grouponDemand);
		if(count>0){
			//修改成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr));
			//修改成功后添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr),
					ConstantStr.GROUPONDEMAND, grouponDemand.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 删除
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午6:13:38
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:manager")
	@RequestMapping(value = "/deleteGrouponDemand",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteGrouponDemand(Long id,HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		//根据ID找到这条数据的信息
		GrouponDemand grouponDemand = grouponDemandService.findByIdGrouponDemand(id);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"删除"};
		//如果这条数据信息不存在，返回信息提示
		if(grouponDemand==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果这条数据信息存在，修改这条数据的公共字段，把删除字段设置为已经删除
		grouponDemand.setDelFlag(ConstantStr.DELETE_Y);//公共字段设置为删除
		this.editAttr(grouponDemand);//修改公共字段
		//修改这条数据的信息（逻辑删除）
		int count = grouponDemandService.updateSelective(grouponDemand);
		if(count>0){
			//修改成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr));
			//修改成功后添加日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INF0100058,arr),
					ConstantStr.GROUPONDEMAND, grouponDemand.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 发布
	 * @author:田振兴
	 * @CreateDate:2016年8月1日 下午6:13:38
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:manager")
	@RequestMapping(value = "/publishGrouponDemand",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String publishGrouponDemand(Long id,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//根据ID找到这条数据的信息
		GrouponDemand grouponDemand = grouponDemandService.findByIdGrouponDemand(id);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"发布"};
		//如果这条数据信息不存在，返回信息提示
		if(grouponDemand==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果这条数据信息存在，修改这条数据的公共字段，把发布字段设置为已经发布
		grouponDemand.setStatus(ConstantStr.FB); //发布
		this.editAttr(grouponDemand);//修改公共字段
		//修改这条数据的信息
		int count = grouponDemandService.updateSelective(grouponDemand);
		if(count>0){
			//提示修改成功
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr));
			//修改成功后添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr),
					ConstantStr.GROUPONDEMAND, grouponDemand.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr));
			return o.writeValueAsString(map);
		}
	}
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 跳转到详情页面
	 * @author: 田振兴
	 * @CreateDate:2016年8月2日 下午3:51:59
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/details/{id}",method={RequestMethod.POST,RequestMethod.GET})
	public String details(Model model,@PathVariable Long id,
			HttpServletRequest request,HttpServletResponse response){
		//把ID放到Model里传到前台
		model.addAttribute("id",id);
		return DETAILS;
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 详情页面初始化
	 * @author:田振兴
	 * @CreateDate:2016年8月2日 下午4:02:04
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/detailsInit/{id}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String detailsInit(@PathVariable Long id,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//创建我要团VO对象
		GrouponDemandVo grouponDemandVo = new GrouponDemandVo();
		//给VO对象赋值
		grouponDemandVo.setId(id);
		//商品分类
		grouponDemandVo.setClassificType(ConstantStr.COMMODITYCLASSIFICATION);
		//已发布
		grouponDemandVo.setReleaseStatus(ConstantStr.WYTYFB);
		grouponDemandVo.setReleaseNumber(ConstantStr.FB);
		//未发布
		grouponDemandVo.setUnReleaseStatus(ConstantStr.WYTWFB);
		grouponDemandVo.setUnReleaseNumber(ConstantStr.WFB);
		//把VO当做参数查找我要团详情数据
		grouponDemandVo = grouponDemandService.findGrouponDemand(grouponDemandVo);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(grouponDemandVo);
	}
	
	/**
	 * 
	 * @Title我要团管理
	 * @Description 根据条件查询评论人的列表
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 上午9:00:40
	 * @param grouponDemandCommentVo
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/findComments/{id}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findComments( GrouponDemandCommentVo grouponDemandCommentVo,@PathVariable Long id,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		grouponDemandCommentVo.setDemandId(id);
		//根据我要团数据ID（传过去的是Vo）查找出所有的评论信息
		List<GrouponDemandCommentVo> list = grouponDemandService.findComments(grouponDemandCommentVo);	
		map.put("rows", list);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);					
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 跳转到回复人页面
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 下午2:17:20
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/returnDetails/{id}",method={RequestMethod.POST,RequestMethod.GET})
	public String returnDetails(Model model,@PathVariable Long id,
			HttpServletRequest request,HttpServletResponse response){
		//把ID放到Model里传到前台
		model.addAttribute("commentId",id);
		model.addAttribute("id",id);
		return RETURNDETAILS;
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 回复人页面初始化
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 上午9:44:35
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/returnDetailsInit/{id}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String returnDetailsInit(Model model,@PathVariable Long id,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//创建VO对象
		GrouponDemandCommentVo grouponDemandCommentVo = new GrouponDemandCommentVo();
		//给VO对象赋值（id）
		grouponDemandCommentVo.setId(id);
		//把VO当做参数查找评论人信息
		grouponDemandCommentVo = grouponDemandService.findOneComments(grouponDemandCommentVo);
		//创建回复表VO对象
		GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo = new GrouponDemandCommentReturnInfoVo();
		//给回复表VO赋值（commentId）
		grouponDemandCommentReturnInfoVo.setCommentId(id);
		//把回复表VO当做参数查找评论信息的回复数
		Long reply = grouponDemandService.findReturnInfoCount(grouponDemandCommentReturnInfoVo);
		//给回复表VO赋值（回复违规标志）
		grouponDemandCommentReturnInfoVo.setStatus(ConstantStr.ILLEAGL_FLAG_WG);
		//把回复表VO当做参数查找评论信息的回复中的违规数
		Long illegal = grouponDemandService.findReturnInfoCount(grouponDemandCommentReturnInfoVo);
		//把回复数和违规数赋给评论表VO对象
		grouponDemandCommentVo.setReply(reply);
		grouponDemandCommentVo.setIllegal(illegal);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(grouponDemandCommentVo);
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 回复人列表
	 * @author:田振兴
	 * @CreateDate:2016年8月3日 上午9:27:02
	 * @param grouponDemandCommentReturnInfoVo
	 * @param commentId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/findReturnInfo/{commentId}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findReturnInfo( GrouponDemandCommentReturnInfoVo grouponDemandCommentReturnInfoVo,
			@PathVariable Long commentId,HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		grouponDemandCommentReturnInfoVo.setCommentId(commentId);
		//根据评论人ID（传过去的是VO对象）查找所有的回复列表
		List<GrouponDemandCommentReturnInfoVo> list = grouponDemandService.findReturnInfo(grouponDemandCommentReturnInfoVo);
		map.put("rows", list);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);					
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 删除评论
	 * @author:田振兴
	 * @CreateDate:2016年8月9日 下午5:12:54
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:manager")
	@RequestMapping(value = "/deleteGrouponDemandComment",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteGrouponDemandComment(Long id,HttpServletRequest request,
				HttpServletResponse response) throws Exception{
		//根据评论ID找到评论的信息，把评论信息赋给评论对象
		GrouponDemandComment grouponDemandComment = grouponDemandService.findOneCommentsEasy(id);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"删除"};
		//如果评论对象为空，返回提示信息
		if(grouponDemandComment==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果评论对象不是空，给评论对象进行赋值（把删除标志设置为删除）
		grouponDemandComment.setDelFlag(ConstantStr.DELETE_Y);////设为删除
		this.editAttrComment(grouponDemandComment);//修改公共字段
		//逻辑删除评论
		int count = grouponDemandService.deleteGrouponDemandComment(grouponDemandComment);
		if(count>0){
			//修改成功
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr));
			//.删除成功后添加日志
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INF0100058,arr), 
					ConstantStr.GROUPONDEMANDCOMMENT, grouponDemandComment.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 我要团管理
	 * @Description 我要团统计
	 * @author:田振兴
	 * @CreateDate:2016年8月10日 上午11:17:19
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponDemand:view")
	@RequestMapping(value = "/echartsInit",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String echartsInit(Model model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		//创建我要团VO对象
		GrouponDemandVo grouponDemandVo = new GrouponDemandVo();
		//给VO赋值(商品分类)
		grouponDemandVo.setClassificType(ConstantStr.COMMODITYCLASSIFICATION);//商品分类类型，在常量定死，对应字典表里的TYPE属性
		//找到所有的商品分类以及他们的总团购人数，存放到List里
		List<GrouponDemandVo> chartList = grouponDemandService.findCensus(grouponDemandVo);
		List<String> className = new ArrayList<String>();
		List<Integer> number = new ArrayList<Integer>();
		//对List循环遍历，把所有的商品分类放到一个list(className)里边，把所有的分类总团购数放到另外的List(number)里（这2个LIst的数据要对应的起来）
		for(int i =0;i<chartList.size();i++){
			className.add(chartList.get(i).getClassificName());
			number.add(chartList.get(i).getNumber());
			
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//把上边的2个List放到MAP里边，返回这个MAP
		map.put("className", className);
		map.put("number", number);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
     * 修改共同属性(GrouponDemand)
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttr(GrouponDemand _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
     * 修改共同属性(GrouponDemandComment)
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttrComment(GrouponDemandComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 获取当前用户的区域级别
	 * @author:YK
	 * @CreateDate:2016年7月29日 下午4:47:03
	 * @return
	 */
	private String getCurrentAreaLevel(){
		UserAreaVo userAreaVo = new UserAreaVo();
		userAreaVo.setUserId(super.getUserId());
		userAreaVo.setPageFlag(false);
		List<UserArea> userAreaList = userAreaService.selectList(userAreaVo);
		if(userAreaList.size()<=0){
			return "";
		}
		String areaLevel = userAreaList.get(0).getAreaLevel();
		return areaLevel;
	}		
}
