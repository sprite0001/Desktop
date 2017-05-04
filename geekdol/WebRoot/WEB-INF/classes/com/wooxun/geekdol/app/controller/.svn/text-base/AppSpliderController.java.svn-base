package com.wooxun.geekdol.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.spider.bean.SpliderMessage;
import com.wooxun.geekdol.spider.core.ExtractService;
import com.wooxun.geekdol.spider.rule.Rule;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu
 * @CreateDate 2016年8月24日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. kangtianyu 2016年8月24日 下午15:26:34 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("/appsplider")
public class AppSpliderController {

	/**
	 * @Title
	 * @Description 获取信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 下午3:33:21
	 * @param spliderType
	 * @return
	 */
	@RequestMapping("/spliderMessage")
	public @ResponseBody String spliderMessage(String spliderType, @RequestParam Long uid, @RequestParam String pwd) {
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 创建数据列表变量用于封装返回的列表数据
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			if (ConstantStr.SPLIDER_TYPE_0.equals(spliderType)) { // 如果请求获取投诉板块内容
				// 设置爬取规则
				Rule rule = new Rule(ComDefine.getMsg(ConstantStr.SPLIDER_URL), null, null, ConstantStr.CLASS_COMPLAIN,
						Rule.CLASS, Rule.GET);
				// 爬取数据
				List<SpliderMessage> spliderComplainList = ExtractService.spliderComplain(rule, uid, pwd);
				// 封装数据
				for (SpliderMessage sm : spliderComplainList) {
					// 创建Map变量用于封装数据
					Map<String, Object> data = new HashMap<String, Object>();
					// 封装周边店id
					data.put("contentType", StringUtils.isEmpty(sm.getContentType()) ? "" : sm.getContentType());
					// 封装店家名字
					data.put("contentSummary",
							StringUtils.isEmpty(sm.getContentSummary()) ? "" : sm.getContentSummary());
					// 封装店家图片
					data.put("contentHref", StringUtils.isEmpty(sm.getContentHref()) ? "" : sm.getContentHref());
					// 封装促销信息
					data.put("userName", StringUtils.isEmpty(sm.getUserName()) ? "" : sm.getUserName());
					// 向list中添加数据信息
					dataList.add(data);
				}
			} else if (ConstantStr.SPLIDER_TYPE_1.equals(spliderType)) { // 如果请求获取回复板块内容
				// 设置爬取规则
				Rule rule = new Rule(ComDefine.getMsg(ConstantStr.SPLIDER_URL), null, null, ConstantStr.CLASS_REPLY,
						Rule.CLASS, Rule.GET);
				// 爬取数据
				List<SpliderMessage> spliderReplyList = ExtractService.spliderReply(rule, uid, pwd);
				// 封装数据
				for (SpliderMessage sm : spliderReplyList) {
					// 创建Map变量用于封装数据
					Map<String, Object> data = new HashMap<String, Object>();
					// 封装周边店id
					data.put("contentType", "");
					// 封装店家名字
					data.put("contentSummary",
							StringUtils.isEmpty(sm.getContentSummary()) ? "" : sm.getContentSummary());
					// 封装店家图片
					data.put("contentHref", StringUtils.isEmpty(sm.getContentHref()) ? "" : sm.getContentHref());
					// 封装促销信息
					data.put("userName", StringUtils.isEmpty(sm.getUserName()) ? "" : sm.getUserName());
					// 向list中添加数据信息
					dataList.add(data);
				}
			}

			// 向map中放入数据列表用于返回
			map.put("data", dataList);
			// 返回相应结果信息
			return fromObject(ConstantStr.APP_CODE200, ComDefine.getMsg(ConstantStr.INFO200001), map);

		} catch (Exception e) {
			// 在控制台打印异常信息
			e.printStackTrace();
			// 返回封装的失败数据信息
			return fromObject(ConstantStr.APP_CODE500, ComDefine.getMsg(ConstantStr.INFO200002) + e.getMessage(), null);
		}

	}

	/**
	 * 通用的格式化返回值方法
	 * 
	 * @Title
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年8月1日 下午3:13:29
	 * @param resultCode
	 * @param msg
	 * @param map
	 * @return
	 */
	private String fromObject(Integer resultCode, String msg, Map<String, Object> map) {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put("resultCode", resultCode);
		map.put("msg", msg);
		return JSONObject.fromObject(map).toString();
	}

}
