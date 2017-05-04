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
import com.wooxun.geekdol.geekstore.model.GoodsClassific;
import com.wooxun.geekdol.geekstore.model.Order;
import com.wooxun.geekdol.geekstore.model.OrderReturnInfor;
import com.wooxun.geekdol.geekstore.service.GoodsClassificService;
import com.wooxun.geekdol.geekstore.service.OrderService;
import com.wooxun.geekdol.geekstore.vo.OrderVo;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.service.UserAreaService;

/**
 * @Title 吉客店管理-商品管理-订单管理
 * @Description 订单管理
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月25日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月25日  下午5:28:49 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController{
	
	public static final String LIST = "geekstore/order/list";
	public static final String DETAILS = "geekstore/order/details";
	public static final String DELIVERY = "geekstore/order/delivery";
	public static final String CLOSE= "geekstore/order/close";
	public static final String RETURNGOOD ="geekstore/order/returnGoods";
	public static final String REFUND ="geekstore/order/refund";
	@Autowired
	private OrderService<Order> orderService;
	@Autowired
	private GoodsClassificService<GoodsClassific> goodsClassificService;
	@Autowired
	private UserAreaService<UserArea> userAreaService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	/**
	 * 
	 * @Title 订单查询
	 * @Description 跳转到订单列表页面
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 下午5:33:46
	 * @return
	 */
	@RequiresPermissions("Order:view")
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
	 * @Title 订单查询
	 * @Description 根据条件查询订单列表
	 * @author:田振兴
	 * @CreateDate:2016年7月25日 下午5:35:22
	 * @param orderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String findAll( OrderVo orderVo,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//翻页功能，找到是第几页
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		//翻页功能，设置一页有多少条数据
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<OrderVo> orderlist= new ArrayList<OrderVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		//设置要翻页
		orderVo.setPageFlag(true);
		//设置开始的条数
		orderVo.setStartPage((page-1)*rows);
		//设置结束的条数
		orderVo.setEndPage(rows);
		// 对Vo进行赋值（登录人ID、支付方式type、订单状态type）
		orderVo.setCurrentUserId(getUserId());
		orderVo.setPayModel(ConstantStr.PAYMODEL);
		orderVo.setOrderStatusType(ConstantStr.ORDERSTATUS);
		//把Vo当参数找到订单信息列表总条数
		count = orderService.findAllOrderCount(orderVo);
		//把Vo当参数找到订单信息列表
		orderlist = orderService.findAllOrder(orderVo);		
		map.put("rows", orderlist);
		map.put("total",count);
		return o.writeValueAsString(map);
	}
				
	/**
	 * 
	 * @Title
	 * @Description 跳转到订单详情页面
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 上午11:23:15
	 * @param model
	 * @param orderId
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/details/{orderId}/{goodsCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String details(Model model,@PathVariable Long orderId,@PathVariable String goodsCode ,
			HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("orderId",orderId);//把订单ID放到MODEL里传到前台页面
		model.addAttribute("goodsCode",goodsCode);//把商品编号放到MODEL里传到前台页面
		return DETAILS;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化订单详情
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 上午11:23:15
	 * @param model
	 * @param orderId
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/detailsInit/{orderId}/{goodsCode}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String detailsInit(Model model,@PathVariable Long orderId,@PathVariable String goodsCode,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//创建Vo对象
		OrderVo orderVo = new OrderVo();
		//对Vo对象进行赋值（订单ID、订单状态type、支付方式type、物流方式type）
		orderVo.setOrderId(orderId);
		//订单状态类型(字典表)
		orderVo.setOrderStatusType(ConstantStr.ORDERSTATUS);
		//支付方式类型(字典表)
		orderVo.setPayModel(ConstantStr.PAYMODEL);
		//物流类型(字典表)
		orderVo.setExpress(ConstantStr.EXPRESS);
		//根据Vo对象查找出订单详情
		orderVo = orderService.findOrderInit(orderVo);
		//对Vo对象进行赋值（商品编号）
		orderVo.setGoodsCode(goodsCode);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(orderVo);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化退货页面
	 * @author:田振兴
	 * @CreateDate:2016年8月16日 下午2:12:24
	 * @param model
	 * @param orderId
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/returnDetailsInit/{orderId}/{id}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String returnDetailsInit(Model model,@PathVariable Long orderId,@PathVariable Long id,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//创建订单对象VO
		OrderVo orderVo = new OrderVo();
		//根据订单ID找到订单
		OrderReturnInfor orderReturnInfor = orderService.findOrderReturnInfor(orderId);
		//把订单的退货理由设置到订单对象VO里
		orderVo.setOrderReturnReason(orderReturnInfor.getOrderReturnReason());
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(orderVo);
	}	
	
	/**
	 * 
	 * @Title 订单管理
	 * @Description 跳转到发货页面
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 上午11:44:23
	 * @param model
	 * @param orderId
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/delivery/{orderId}/{goodsCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String delivery(Model model,@PathVariable Long orderId,@PathVariable String goodsCode ,
			HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("orderId",orderId);//把订单ID放到MODEL里传到前台页面
		model.addAttribute("goodsCode",goodsCode);//把商品编号放到MODEL里传到前台页面
		model.addAttribute("EXPRESS",ConstantStr.EXPRESS);//把物流类型放到MODEL里传到前台页面
		return DELIVERY;
	}
	
	/**
	 * 
	 * @Title订单管理
	 * @Description 发货
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 下午4:22:44
	 * @param orderId
	 * @param orderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:manager")
	@ResponseBody
	@RequestMapping(value = "/shippingSave/{orderId}",method={RequestMethod.POST,RequestMethod.GET})
	public String shippingSave(@PathVariable Long orderId,OrderVo orderVo,
		HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		Order order = new Order();
		//在对象里设置订单ID
		orderVo.setOrderId(orderId);
		//根据订单ID找到订单
		order = orderService.findOrder(orderVo);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"未发货"};
		Object[] arr1 = {"发货"};
		//如果这个订单数据不存在,返回提示信息
		if(order==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是未发货状态，返回提示信息
		if(!order.getStatus().equals(ConstantStr.WFH)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是未发货状态，设置订单状态为已发货，设置公共的属性
		order.setLogisticsNumber(orderVo.getLogisticsNumber());//物流订单号
		order.setDeliveryMode(orderVo.getDeliveryMode());//物流名称
		this.editAttr(order);//公共字段
		order.setStatus(ConstantStr.YFH);//已发货
		//改变订单信息
		int count = orderService.updateOrder(order);
		if(count>0){
			//发货成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1), ConstantStr.ORDER, order.getOrderId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//发货失败信息
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
		
	/**
	 * 
	 * @Title 订单管理
	 * @Description 跳转到关闭页面
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 上午11:44:23
	 * @param model
	 * @param orderId
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/close/{orderId}/{goodsCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String close(Model model,@PathVariable Long orderId,@PathVariable String goodsCode,
			HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("orderId",orderId);//把订单ID放到MODEL里传到前台页面
		model.addAttribute("goodsCode",goodsCode);//把商品编号放到MODEL里传到前台页面
		return CLOSE;
	}
	
	/**
	 * 
	 * @Title 订单管理
	 * @Description 关闭订单
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 下午4:23:27
	 * @param orderId
	 * @param orderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:manager")
	@ResponseBody
	@RequestMapping(value = "/colseSave/{orderId}",method={RequestMethod.POST,RequestMethod.GET})
	public String colseSave(@PathVariable Long orderId,OrderVo orderVo,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//创建订单对象
		Order order = new Order();
		//在对象里设置订单ID
		orderVo.setOrderId(orderId);
		//根据订单ID找到订单的数据
		order = orderService.findOrder(orderVo);
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"未付款"};
		Object[] arr1 = {"关闭"};
		//如果这个订单数据不存在,返回提示信息
		if(order==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是未付款状态，返回提示信息
		if(!order.getStatus().equals(ConstantStr.WFK)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是未付款状态，对对象进行赋值（设置公共属性，关闭理由，订单状态）
		order.setOrderCloseReasons(orderVo.getOrderCloseReasons());
		this.editAttr(order);
		order.setStatus(ConstantStr.YGB);
		//改变订单信息
		int count = orderService.updateOrder(order);
		if(count>0){
			//关闭成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1), ConstantStr.ORDER, order.getOrderId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//关闭失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 订单管理
	 * @Description 跳转到退货页面
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 上午11:44:23
	 * @param model
	 * @param orderId
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/returnGood/{orderId}/{goodsCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String returnGood(Model model,@PathVariable Long orderId,@PathVariable String goodsCode ,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("orderId",orderId);//把订单ID放到MODEL里传到前台页面
		model.addAttribute("goodsCode",goodsCode);//把商品编号放到MODEL里传到前台页面
		//根据订单ID找到退货信息
		OrderReturnInfor orderReturnInfor = orderService.findOrderReturnInfor(orderId);
		//创建附件表model对象
		Attach attach = new Attach();
		//并赋值给对象（退货ID，退货的图片类型，退货的表名字）
		attach.setOwnerId(orderReturnInfor.getId());
		//订单信息退货表
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_03);
		attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
		//找到退货信息的图片路径(附件表)
		List<Attach> attachmentList = orderService.attachList(attach);
		model.addAttribute("id",orderReturnInfor.getId());
		model.addAttribute("attachmentList",attachmentList);
		return RETURNGOOD;
	}
	
	/**
	 * 
	 * @Title 订单管理
	 * @Description 订单退货
	 * @author:田振兴
	 * @CreateDate:2016年7月29日 上午11:34:45
	 * @param orderId
	 * @param orderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:manager")
	@ResponseBody
	@RequestMapping(value = "/returnGoodSave/{orderId}",method={RequestMethod.POST,RequestMethod.GET})
	public String returnGoodSave(@PathVariable Long orderId,OrderVo orderVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		Order order = new Order();
		//在对象里设置订单ID
		orderVo.setOrderId(orderId);
		//根据订单ID找到订单
		order = orderService.findOrder(orderVo);		
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"待处理退货"};
		Object[] arr1 = {"退货"};
		//如果这个订单数据不存在,返回提示信息
		if(order==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果这个订单的退货信息不存在，也返回提示
		if(order.getOrderReturnInfor()==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是待处理退货状态，返回提示信息
		if(!order.getStatus().equals(ConstantStr.DCLTH)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是待处理退货状态，对订单对象进行赋值(处理结果，退货地址，退货理由，公共属性)
		order.getOrderReturnInfor().setDetailResult(orderVo.getDetailResult());
		order.getOrderReturnInfor().setReturnAdress(orderVo.getReturnAdress());
		order.getOrderReturnInfor().setDetailResultReason(orderVo.getDetailResultReason());
		this.editAttr(order);
		//改变订单信息
		int count = orderService.updateReturnGoods(order);
		if(count>0){
			//退货成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1), ConstantStr.OrderReturnInfor, order.getOrderId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//退货失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 订单管理
	 * @Description 跳转到退款页面
	 * @author:田振兴
	 * @CreateDate:2016年7月28日 上午11:44:23
	 * @param model
	 * @param orderId
	 * @param goodsCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("Order:manager")
	@RequestMapping(value = "/refund/{orderId}/{goodsCode}",method={RequestMethod.POST,RequestMethod.GET})
	public String refund(Model model,@PathVariable Long orderId,@PathVariable String goodsCode,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("orderId",orderId);//把订单ID放到MODEL里传到前台页面
		model.addAttribute("goodsCode",goodsCode);//把商品编号放到MODEL里传到前台页面
		return REFUND;
	}
	
	/**
	 * 
	 * @Title 订单管理
	 * @Description 订单退款
	 * @author:田振兴
	 * @CreateDate:2016年7月29日 上午11:34:45
	 * @param orderId
	 * @param orderVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:manager")
	@ResponseBody
	@RequestMapping(value = "/refundSave/{orderId}",method={RequestMethod.POST,RequestMethod.GET})
	public String refundSave(@PathVariable Long orderId,OrderVo orderVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建订单对象
		Order order = new Order();
		//在对象里设置订单ID
		orderVo.setOrderId(orderId);
		//找到订单信息
		order = orderService.findOrder(orderVo);		
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr = {"待退款"};
		Object[] arr1 = {"退款"};
		//如果这个订单数据不存在,返回提示信息
		if(order==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//订单没有退货信息,返回提示信息
		if(order.getOrderReturnInfor()==null){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100021));
			return o.writeValueAsString(map);
		}
		//如果订单状态不是待退款状态，返回提示信息
		if(!order.getStatus().equals(ConstantStr.DTK)){
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100060,arr));
			return o.writeValueAsString(map);
		}
		//如果订单状态是待退款状态，对订单对象进行赋值（设置公共属性，处理结果，退款金额，退款理由）
		order.getOrderReturnInfor().setDetailResult(ConstantStr.YTD);
		order.getOrderReturnInfor().setReturnMoney(orderVo.getReturnMoney());
		order.getOrderReturnInfor().setReturnMoneyMark(orderVo.getReturnMoneyMark());		
		this.editAttr(order);
		//改变订单信息
		int count = orderService.updateRefund(order);
		if(count>0){
			//退款成功提示
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INF0100058,arr1));
			//添加日志
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INF0100058,arr1), ConstantStr.OrderReturnInfor, order.getOrderId(), getUser());
			return o.writeValueAsString(map);
		} else{
			//退款失败提示
			map.put("type", "Error");
			map.put("msg",ComDefine.getMsg(ConstantStr.INF0100059,arr1));
			return o.writeValueAsString(map);
		}
	}
	
	/**
	 * 
	 * @Title 订单
	 * @Description 销售模块的下拉框
	 * @author:田振兴
	 * @CreateDate:2016年8月15日 下午6:52:40
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Order:manager")
	@ResponseBody
	@RequestMapping(value = "/sellerModel",method={RequestMethod.POST,RequestMethod.GET})
	public String sellerModel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ObjectMapper o = new ObjectMapper();
		List<GoodsClassific> goodslist= new ArrayList<GoodsClassific>();
		GoodsClassific goodsClassific = new GoodsClassific();
		goodsClassific.setId(null);
		goodsClassific.setName("全部");
		goodslist.add(goodsClassific);		
		Long pId =(long) ConstantStr.FJD;
		goodslist.addAll(goodsClassificService.getGoodsListByPid(pId));
		return o.writeValueAsString(goodslist);
	}
	
	/**
     * 修改共同属性(Order)
     * @param _temp
     * @author:863SOFT-FZQ
     */
	private void editAttr(Order _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
}
