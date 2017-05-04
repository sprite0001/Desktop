package com.wooxun.geekdol.geekstore.controller;

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
import com.wooxun.geekdol.geekstore.model.GroupBuyOrder;
import com.wooxun.geekdol.geekstore.model.GroupOrderReturnInfor;
import com.wooxun.geekdol.geekstore.model.GroupPurchaseCode;
import com.wooxun.geekdol.geekstore.service.GroupBuyOrderService;
import com.wooxun.geekdol.geekstore.service.GroupPurchaseCodeService;
import com.wooxun.geekdol.geekstore.vo.GroupBuyOrderVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;

/**
 * @Title 团购订单管理
 * @Description 
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月4日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月4日  下午2:14:37 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("groupBuyOrder")
public class GroupBuyOrderController extends BaseController{
	
	public static final String LIST = "geekstore/groupBuyOrder/list";
	public static final String LISTVILLAGE = "geekstore/groupBuyOrder/listVillage";
	public static final String DETAILS = "geekstore/groupBuyOrder/details";
	public static final String DELIVERY = "geekstore/groupBuyOrder/delivery";
	public static final String CLOSE= "geekstore/groupBuyOrder/close";
	public static final String RETURNGOOD ="geekstore/groupBuyOrder/returnGoods";
	public static final String REFUND ="geekstore/groupBuyOrder/refund";
	@Autowired
	private GroupBuyOrderService<GroupBuyOrder> groupBuyOrderService;
	@Autowired
	private GroupPurchaseCodeService<GroupPurchaseCode> groupPurchaseCodeService;
	@Autowired
	private UserAreaService<UserArea> userAreaService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	/**
	 * 
	 * @Title 团购供应商订单管理
	 * @Description 跳转到供应商列表页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午3:11:36
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model,HttpServletRequest request,HttpServletResponse response){
		//订单状态类型放到MODEL里传到前台
		model.addAttribute("ORDERSTATUS",ConstantStr.ORDERSTATUS);
		//未发货，传到前台
		model.addAttribute("WFH",ConstantStr.WFH);
		//未付款，传到前台
		model.addAttribute("WFK",ConstantStr.WFK);
		//待处理退货，传到前台
		model.addAttribute("DCLTH",ConstantStr.DCLTH);
		//待退款，传到前台
		model.addAttribute("DTK",ConstantStr.DTK);
		return LIST;
	}
	
	/**
	 * 
	 * @Title 团购合作店订单管理
	 * @Description 跳转到合作店列表页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午3:11:36
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/listVillage", method = {RequestMethod.GET,RequestMethod.POST})
	public String listVillage(Model model,HttpServletRequest request,HttpServletResponse response){
		//订单状态类型放到MODEL里传到前台
		model.addAttribute("ORDERSTATUS",ConstantStr.ORDERSTATUS);
		return LISTVILLAGE;
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 根据条件查询团购合作店订单的列表
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午4:11:12
	 * @param GroupBuyOrderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( GroupBuyOrderVo GroupBuyOrderVo,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//翻页功能，找到是第几页
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		//翻页功能，设置一页有多少条数据
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<GroupBuyOrderVo> groupBuyOrderList= new ArrayList<GroupBuyOrderVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		//设置要翻页
		GroupBuyOrderVo.setPageFlag(true);
		//设置开始的条数
		GroupBuyOrderVo.setStartPage((page-1)*rows);
		//设置结束的条数
		GroupBuyOrderVo.setEndPage(rows);
		//按小区来查
		GroupBuyOrderVo.setCurrentUserId(getUserId());//在VO里设置登录人ID	
		GroupBuyOrderVo.setOrderStatusType(ConstantStr.ORDERSTATUS);//在VO里设置订单状态类型
		//把物流的配送模式放到LIST里(自取和配送)
		List<String> deliveryModeList = new ArrayList<String>();
		deliveryModeList.add(ConstantStr.WLZQ);
		deliveryModeList.add(ConstantStr.XQPS);
		//把物流List放到Vo里
		GroupBuyOrderVo.setDeliveryModeList(deliveryModeList);
		//找VO当做参数查找合作店订单信息列表
		count = groupBuyOrderService.findAllGroupBuyOrderVillageCount(GroupBuyOrderVo);
		//找VO当做参数查找合作店订单信息列表总条数
		groupBuyOrderList = groupBuyOrderService.findAllGroupBuyOrderVillage(GroupBuyOrderVo);		
		map.put("rows", groupBuyOrderList);
		map.put("total",count);
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 根据条件查询团购供应商订单的列表
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午4:11:12
	 * @param GroupBuyOrderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/findAllSupplier",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAllSupplier(GroupBuyOrderVo GroupBuyOrderVo,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//翻页功能，找到是第几页
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		//翻页功能，设置一页有多少条数据
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<GroupBuyOrderVo> groupBuyOrderList = new ArrayList<GroupBuyOrderVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		//设置要翻页
		GroupBuyOrderVo.setPageFlag(true);
		//设置开始的条数
		GroupBuyOrderVo.setStartPage((page-1)*rows);
		//设置一页有多少条数据
		GroupBuyOrderVo.setEndPage(rows);
		// 设置当前用户的ID
		GroupBuyOrderVo.setSellerId(super.getUserId());
		GroupBuyOrderVo.setOrderStatusType(ConstantStr.ORDERSTATUS);
		//把VO当做参数查找出团购订单列表
		count = groupBuyOrderService.findAllGroupBuyOrderCount(GroupBuyOrderVo);
		//把VO当做参数查找出团购订单列表总条数
		groupBuyOrderList = groupBuyOrderService.findAllGroupBuyOrder(GroupBuyOrderVo);		
		map.put("rows", groupBuyOrderList);
		map.put("total",count);
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 跳转到详情页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午4:18:48
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/details/{id}/{serialCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String details(Model model,@PathVariable Long id,@PathVariable String serialCode,
			HttpServletRequest request,HttpServletResponse response){
		//把ID放到MODEL里传到前台页面
		model.addAttribute("id",id);
		//把团购编码放到MODEL里传到前台页面
		model.addAttribute("serialCode",serialCode);
		return DETAILS;
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 初始化详情
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午6:22:03
	 * @param model
	 * @param id
	 * @param serialCode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/detailsInit/{id}/{serialCode}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String detailsInit(Model model,@PathVariable Long id,@PathVariable String serialCode,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//创建VO对象
		GroupBuyOrderVo groupBuyOrderVo = new GroupBuyOrderVo();
		groupBuyOrderVo.setId(id);//在VO对象里设置订单ID
		//设置订单状态、支付方式和物流方式(类型，对应字典表里的type)
		groupBuyOrderVo.setOrderStatusType(ConstantStr.ORDERSTATUS);
		groupBuyOrderVo.setPayModel(ConstantStr.PAYMODEL);
		groupBuyOrderVo.setExpress(ConstantStr.EXPRESS);
		//根据订单ID(传的参数是VO)查询出订单的具体信息
		groupBuyOrderVo = groupBuyOrderService.findGroupBuyOrderInit(groupBuyOrderVo);
		//在VO对象里设置团购编码
		groupBuyOrderVo.setSerialCode(serialCode);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(groupBuyOrderVo);
	}
	
	/**
	 * 
	 * @Title 详情
	 * @Description 详情里的团购码初始化
	 * @author:田振兴
	 * @CreateDate:2016年8月15日 下午7:25:03
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/detailsCodeInit/{id}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String detailsCodeInit(Model model,@PathVariable Long id,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//创建团购码对象
		GroupPurchaseCode groupPurchaseCode = new GroupPurchaseCode();
		Map<String, Object> map = new HashMap<String, Object>();
		//在团购码对象里设置订单ID
		groupPurchaseCode.setOrderId(id);
		//根据订单ID(传过去的是Model)查团购码
		List<GroupPurchaseCode> codeList = groupPurchaseCodeService.findCode(groupPurchaseCode);
		map.put("rows", codeList);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 初始化退货页面的一些信息
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 下午5:01:58
	 * @param model
	 * @param id
	 * @param returnId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:view")
	@RequestMapping(value = "/returnDetailsInit/{id}/{returnId}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String returnDetailsInit(Model model,@PathVariable Long id,@PathVariable Long returnId,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//创建订单对象
		GroupBuyOrderVo groupBuyOrderVo = new GroupBuyOrderVo();
		//根据订单ID找到退货信息
		GroupOrderReturnInfor groupOrderReturnInfor = groupBuyOrderService.findGroupBuyOrderReturnInfor(id);
		//把订单的退货理由设置到订单对象里
		groupBuyOrderVo.setOrderReturnReason(groupOrderReturnInfor.getOrderReturnReason());
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(groupBuyOrderVo);
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 跳转到发货页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午6:24:21
	 * @param model
	 * @param id
	 * @param serialCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@RequestMapping(value = "/delivery/{id}/{serialCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String delivery(Model model,@PathVariable Long id,
			@PathVariable String serialCode,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("id",id);//把id放到MODEL里传到前台页面
		model.addAttribute("EXPRESS",ConstantStr.EXPRESS);//把物流类型type放到MODEL里传到前台页面
		model.addAttribute("serialCode",serialCode);////把团购编码放到MODEL里传到前台页面
		return DELIVERY;
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description订单发货
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 上午11:37:27
	 * @param id
	 * @param groupBuyOrderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@ResponseBody
	@RequestMapping(value = "/shippingSave/{id}",method={RequestMethod.POST,RequestMethod.GET})
	public String shippingSave(@PathVariable Long id,GroupBuyOrderVo groupBuyOrderVo,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		GroupBuyOrder groupBuyOrder = new GroupBuyOrder();
		//在对象里设置订单ID
		groupBuyOrderVo.setId(id);
		//根据订单ID(参数是VO)找到订单
		groupBuyOrder = groupBuyOrderService.findGroupBuyOrder(groupBuyOrderVo);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"未发货"};
		Object[] arr1 = {"发货"};
		//如果这个订单数据不存在,返回提示信息
		if(groupBuyOrder==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是未发货状态，返回提示信息
		if(!groupBuyOrder.getStatus().equals(ConstantStr.WFH)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是未发货状态，设置订单状态为已发货，设置公共的属性
		groupBuyOrder.setLogisticsNumber(groupBuyOrderVo.getLogisticsNumber());//物流单号
		groupBuyOrder.setDeliveryMode(groupBuyOrderVo.getDeliveryMode());//物流名称
		this.editAttr(groupBuyOrder);//公共字段
		groupBuyOrder.setStatus(ConstantStr.YFH);//已发货
		//改变订单状态
		int count = groupBuyOrderService.updateGroupBuyOrder(groupBuyOrder);
		if(count>0){
			//修改成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1),
					ConstantStr.GROUPBUYORDER, groupBuyOrder.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 跳转到关闭页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午6:24:21
	 * @param model
	 * @param id
	 * @param serialCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@RequestMapping(value = "/close/{id}/{serialCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String close(Model model,@PathVariable Long id,
			@PathVariable String serialCode,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("id",id);//把id放到MODEL里传到前台页面
		model.addAttribute("serialCode",serialCode);//把团购编码放到MODEL里传到前台页面
		return CLOSE;
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 关闭
	 * @author:田振兴
	 * @CreateDate:2016年9月1日 下午5:09:06
	 * @param id
	 * @param groupBuyOrderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@ResponseBody
	@RequestMapping(value = "/colseSave/{id}",method={RequestMethod.POST,RequestMethod.GET})
	public String colseSave(@PathVariable Long id,GroupBuyOrderVo groupBuyOrderVo,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		GroupBuyOrder groupBuyOrder = new GroupBuyOrder();
		//在对象里设置订单ID
		groupBuyOrderVo.setId(id);
		//根据订单ID找到订单信息
		groupBuyOrder = groupBuyOrderService.findGroupBuyOrder(groupBuyOrderVo);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"未付款"};
		Object[] arr1 = {"关闭"};
		//如果这个订单数据不存在,返回提示信息
		if(groupBuyOrder==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是未付款状态，返回提示信息
		if(!groupBuyOrder.getStatus().equals(ConstantStr.WFK)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是未付款状态，对对象进行赋值（设置公共属性，关闭理由，订单状态）
		groupBuyOrder.setOrderCloseReasons(groupBuyOrderVo.getOrderCloseReasons());//关闭理由
		this.editAttr(groupBuyOrder);//公共字段
		groupBuyOrder.setStatus(ConstantStr.YGB);//订单状态为关闭
		//改变订单信息
		int count = groupBuyOrderService.updateGroupBuyOrder(groupBuyOrder);
		if(count>0){
			//修改成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1),
					ConstantStr.GROUPBUYORDER, groupBuyOrder.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 跳转到退货页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午6:24:21
	 * @param model
	 * @param id
	 * @param serialCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@RequestMapping(value = "/returnGood/{id}/{serialCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String returnGood(Model model,@PathVariable Long id,@PathVariable String serialCode,
			HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("id",id);//把id放到MODEL里传到前台页面
		model.addAttribute("serialCode",serialCode);//把团购编码放到MODEL里传到前台页面
		//根据订单ID找到退货信息
		GroupOrderReturnInfor groupOrderReturnInfor = groupBuyOrderService.findGroupBuyOrderReturnInfor(id);
		//创建附件表model对象
		Attach attach = new Attach();
		//赋值给对象（退货ID，退货的图片类型，退货的表名字）
		attach.setOwnerId(groupOrderReturnInfor.getId());
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_18);
		attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
		//找到退货信息的图片路径(附件表)
		List<Attach> attachmentList = groupBuyOrderService.attachList(attach);
		//把退货ID放到MODEL里传到前台页面
		model.addAttribute("returnId",groupOrderReturnInfor.getId());
		model.addAttribute("attachmentList",attachmentList);
		return RETURNGOOD;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 保存退货
	 * @author:田振兴
	 * @CreateDate:2016年8月15日 下午7:27:34
	 * @param id
	 * @param groupBuyOrderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@ResponseBody
	@RequestMapping(value = "/returnGoodSave/{id}",method={RequestMethod.POST,RequestMethod.GET})
	public String returnGoodSave(@PathVariable Long id,GroupBuyOrderVo groupBuyOrderVo,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		GroupBuyOrder groupBuyOrder = new GroupBuyOrder();
		//在对象里设置订单ID
		groupBuyOrderVo.setId(id);
		//根据ID(参数是VO)找到订单信息
		groupBuyOrder = groupBuyOrderService.findGroupBuyOrder(groupBuyOrderVo);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"待处理退货"};
		Object[] arr1 = {"退货"};
		//如果这个订单数据不存在,返回提示信息
		if(groupBuyOrder==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果这个订单的退货信息不存在，也返回提示
		if(groupBuyOrder.getGroupOrderReturnInfor()==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是待处理退货状态，返回提示信息
		if(!groupBuyOrder.getStatus().equals(ConstantStr.DCLTH)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是待处理退货状态，对订单对象进行赋值(处理结果，退货地址，退货理由，公共属性)
		groupBuyOrder.getGroupOrderReturnInfor().setDetailResult(groupBuyOrderVo.getDetailResult());
		groupBuyOrder.getGroupOrderReturnInfor().setReturnAdress(groupBuyOrderVo.getReturnAdress());
		groupBuyOrder.getGroupOrderReturnInfor().setDetailResultReason(groupBuyOrderVo.getDetailResultReason());
		this.editAttr(groupBuyOrder);
		//把订单对象当做参数改变订单信息
		int count = groupBuyOrderService.updateReturnGoods(groupBuyOrder);
		if(count>0){
			//修改成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1),
					ConstantStr.GROUPORDERRETURNINFOR, groupBuyOrder.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 跳转到退款页面
	 * @author:田振兴
	 * @CreateDate:2016年8月4日 下午6:30:41
	 * @param model
	 * @param id
	 * @param serialCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@RequestMapping(value = "/refund/{id}/{serialCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String refund(Model model,@PathVariable Long id,@PathVariable String serialCode,
			HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("id",id);//把ID放到MODEL里传到前台页面
		model.addAttribute("serialCode",serialCode);//把团购编码放到MODEL里传到前台页面
		return REFUND;
	}
	
	/**
	 * 
	 * @Title 团购订单管理
	 * @Description 退款
	 * @author:田振兴
	 * @CreateDate:2016年8月5日 下午4:26:44
	 * @param id
	 * @param groupBuyOrderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("GrouponOrder:manager")
	@ResponseBody
	@RequestMapping(value = "/refundSave/{id}",method={RequestMethod.POST,RequestMethod.GET})
	public String refundSave(@PathVariable Long id,GroupBuyOrderVo groupBuyOrderVo,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		GroupBuyOrder groupBuyOrder = new GroupBuyOrder();
		//在对象里设置订单ID
		groupBuyOrderVo.setId(id);
		//根据ID找到订单的信息
		groupBuyOrder = groupBuyOrderService.findGroupBuyOrder(groupBuyOrderVo);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"待退款"};
		Object[] arr1 = {"退款"};
		//如果这个订单数据不存在,返回提示信息
		if(groupBuyOrder==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//查找到的订单没有退货信息,返回提示信息
		if(groupBuyOrder.getGroupOrderReturnInfor()==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是待退款状态，返回提示信息
		if(!groupBuyOrder.getStatus().equals(ConstantStr.DTK)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是待退款状态，对订单对象进行赋值（设置公共属性，处理结果，退款金额，退款理由）
		groupBuyOrder.getGroupOrderReturnInfor().setDetailResult(ConstantStr.YTD);
		groupBuyOrder.getGroupOrderReturnInfor().setReturnMoney(groupBuyOrderVo.getReturnMoney());
		groupBuyOrder.getGroupOrderReturnInfor().setReturnMoneyMark(groupBuyOrderVo.getReturnMoneyMark());		
		this.editAttr(groupBuyOrder);
		//改变订单信息
		int count = groupBuyOrderService.updateRefund(groupBuyOrder);
		if(count>0){
			//修改成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1),
					ConstantStr.GROUPORDERRETURNINFOR, groupBuyOrder.getId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//修改失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	
	/**
     * 修改共同属性(Order)
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttr(GroupBuyOrder _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}	
}
