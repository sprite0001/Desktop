package com.wooxun.geekdol.spider.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.MD5Util;
import com.wooxun.geekdol.spider.bean.SpliderMessage;
import com.wooxun.geekdol.spider.rule.Rule;
import com.wooxun.geekdol.spider.rule.RuleException;

/**
 * 
 * @author zhy
 * 
 */
public class ExtractService {
	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule) {
		String url = rule.getUrl();
		if (StringUtils.isEmpty(url)) {
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null) {
			if (rule.getParams().length != rule.getValues().length) {
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}

	public static List<SpliderMessage> spliderComplain(Rule rule, long uid, String pwd) {

		// 进行对rule的必要校验
		validateRule(rule);

		List<SpliderMessage> datas = new ArrayList<SpliderMessage>();
		try {
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			/**
			 * 如果是查询表单，需要传入参数这些是key
			 */
			String[] params = rule.getParams();
			/**
			 * 如果是查询表单，需要传入参数这些是key
			 */
			String[] values = rule.getValues();
			/**
			 * 需要选择的标签的名称
			 */
			String resultTagName = rule.getResultTagName();
			/**
			 * 按照那种筛选条件查询
			 */
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			Connection conn = Jsoup.connect(url);

			conn.userAgent(ConstantStr.SPLIDER_BROWER).cookie(ConstantStr.SPLIDER_AUTH, ConstantStr.SPLIDER_TOKEN);
			// 设置查询参数

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
			}

			// 设置请求类型
			Document doc = null;
			switch (requestType) {
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}

			// 处理返回数据
			Elements results = new Elements();
			switch (type) {
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				// 当resultTagName为空时默认去body标签
				if (StringUtils.isEmpty(resultTagName)) {
					results = doc.getElementsByTag(ConstantStr.HTML_TAG_BODY);
				}
			}

			for (Element result : results) {
				// 链接
				Elements links = result.getElementsByTag(ConstantStr.HTML_TAG_A);
				// 投诉或建议
				Elements ems = result.getElementsByTag(ConstantStr.HTML_TAG_EM);
				// 作者
				Elements spans = result.getElementsByTag(ConstantStr.HTML_TAG_SPAN);

				for (int i = 0; i < links.size(); i++) {

					Element link = links.get(i);
					Element em = ems.get(i);
					Element span = spans.get(i);
					// 必要的筛选
					String linkHref = link.attr(ConstantStr.HTML_TAG_HREF);
					String linkText = link.text();

					SpliderMessage data = new SpliderMessage();
					data.setContentType(em.text());
					data.setContentSummary(linkText);
					data.setContentHref(ComDefine.getMsg(ConstantStr.SPLIDER_URL) + linkHref + "&pwd="
							+ StringUtils.lowerCase(pwd) + "&uid=" + uid + "&flag="
							+ MD5Util.MD5XIAOXIE(StringUtils.lowerCase(pwd) + ConstantStr.XQT_KEY)
							+ "&xmt_app_login_in=1");
					data.setUserName(span.text());

					datas.add(data);

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datas;
	}

	public static List<SpliderMessage> spliderReply(Rule rule, long uid, String pwd) {

		// 进行对rule的必要校验
		validateRule(rule);

		List<SpliderMessage> datas = new ArrayList<SpliderMessage>();
		try {
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			/**
			 * 如果是查询表单，需要传入参数这些是key
			 */
			String[] params = rule.getParams();
			/**
			 * 如果是查询表单，需要传入参数这些是key
			 */
			String[] values = rule.getValues();
			/**
			 * 需要选择的标签的名称
			 */
			String resultTagName = rule.getResultTagName();
			/**
			 * 按照那种筛选条件查询
			 */
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			Connection conn = Jsoup.connect(url);

			conn.userAgent(ConstantStr.SPLIDER_BROWER).cookie(ConstantStr.SPLIDER_AUTH, ConstantStr.SPLIDER_TOKEN);
			// 设置查询参数

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
			}

			// 设置请求类型
			Document doc = null;
			switch (requestType) {
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}

			// 处理返回数据
			Elements results = new Elements();
			switch (type) {
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				// 当resultTagName为空时默认去body标签
				if (StringUtils.isEmpty(resultTagName)) {
					results = doc.getElementsByTag(ConstantStr.HTML_TAG_BODY);
				}
			}

			for (Element result : results) {
				// 链接
				Elements links = result.getElementsByTag(ConstantStr.HTML_TAG_A);
				// 作者
				Elements spans = result.getElementsByTag(ConstantStr.HTML_TAG_SPAN);

				for (int i = 0; i < links.size(); i++) {

					Element link = links.get(i);
					Element span = spans.get(i);
					// 必要的筛选
					String linkHref = link.attr(ConstantStr.HTML_TAG_HREF);
					String linkText = link.text();

					SpliderMessage data = new SpliderMessage();
					data.setContentSummary(linkText);
					data.setContentHref(ComDefine.getMsg(ConstantStr.SPLIDER_URL) + linkHref + "&pwd="
							+ StringUtils.lowerCase(pwd) + "&uid=" + uid + "&flag="
							+ MD5Util.MD5XIAOXIE(StringUtils.lowerCase(pwd) + ConstantStr.XQT_KEY)
							+ "&xmt_app_login_in=1");
					data.setUserName(span.text());

					datas.add(data);

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datas;
	}

}
