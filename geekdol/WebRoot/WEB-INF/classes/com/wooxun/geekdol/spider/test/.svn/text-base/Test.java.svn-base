package com.wooxun.geekdol.spider.test;

import java.util.List;

import com.wooxun.geekdol.spider.bean.SpliderMessage;
import com.wooxun.geekdol.spider.core.ExtractService;
import com.wooxun.geekdol.spider.rule.Rule;

public class Test {

	@org.junit.Test
	public void getDatasByCssQuery() {
		// Rule rule = new Rule("http://xtq.zynews.cn/",
		// null, null,
		// "newsList2_12", Rule.CLASS, Rule.GET);
		// List<SpliderMessage> extracts = ExtractService.spliderComplain(rule);
		Rule rule = new Rule("http://xtq.zynews.cn/", null, null, "newsList2_ico", Rule.CLASS, Rule.GET);
		long uid = 498472;
		String pwd = "123456";
		List<SpliderMessage> extracts = ExtractService.spliderReply(rule, uid, pwd);
		printf(extracts);
	}

	public void printf(List<SpliderMessage> datas) {
		for (SpliderMessage data : datas) {
			System.out.println(data.getContentType());
			System.out.println(data.getContentSummary());
			System.out.println(data.getUserName());
			System.out.println(data.getContentHref());
			System.out.println("***********************************");
		}

	}
}
