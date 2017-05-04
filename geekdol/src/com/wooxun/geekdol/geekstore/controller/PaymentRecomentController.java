package com.wooxun.geekdol.geekstore.controller;

import java.util.ArrayList;
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

import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.PaymentRecoment;
import com.wooxun.geekdol.geekstore.service.PaymentRecomentService;
import com.wooxun.geekdol.geekstore.vo.PaymentRecomentVo;

/**
 * @Title 支付流水
 * @Description
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月8日  下午4:29:33 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("paymentRecoment")
public class PaymentRecomentController extends BaseController{
	
	public static final String LIST = "geekstore/paymentRecoment/list";
	@Autowired
	private PaymentRecomentService<PaymentRecoment> paymentRecomentService;
	
	
	/**
	 * 
	 * @Title 支付流水
	 * @Description 跳转到支付流水页面
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 下午5:25:02
	 * @return
	 */
	@RequiresPermissions("PaymentRecoment:view")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("PAYMODEL",ConstantStr.PAYMODEL);
		return LIST;
	}
	

	/**
	 * 
	 * @Title 支付流水
	 * @Description 支付流水列表
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 下午5:26:04
	 * @param paymentRecomentVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("PaymentRecoment:view")
	@RequestMapping(value = "/findAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody	
	public String findAll( PaymentRecomentVo paymentRecomentVo,HttpServletRequest request,HttpServletResponse response)throws Exception{
		//翻页功能，找到是第几页
		int page=Integer.parseInt(request.getParameter("page")== null ? ConstantStr.PAGE:request.getParameter("page"));
		//翻页功能，设置一页有多少条数据
		int rows=Integer.parseInt(request.getParameter("rows")== null ? ConstantStr.ROWS:request.getParameter("rows"));
		ObjectMapper o = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<PaymentRecomentVo> orderlist= new ArrayList<PaymentRecomentVo>();
		Long count = 0l;
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		//设置要翻页
		paymentRecomentVo.setPageFlag(true);
		//设置开始的条数
		paymentRecomentVo.setStartPage((page-1)*rows);
		//设置一页有多少条数据
		paymentRecomentVo.setEndPage(rows);
		//对VO进行赋值（支付方式类型（字典表里的type））
		paymentRecomentVo.setPayModel(ConstantStr.PAYMODEL);
		//把VO当做参数找到支付流水信息的列表总条数
		count = paymentRecomentService.findAllCount(paymentRecomentVo);
		//把VO当做参数找到支付流水信息的列表
		orderlist = paymentRecomentService.findAll(paymentRecomentVo);		
		map.put("rows", orderlist);
		map.put("total",count);
		return o.writeValueAsString(map);
	}
}
