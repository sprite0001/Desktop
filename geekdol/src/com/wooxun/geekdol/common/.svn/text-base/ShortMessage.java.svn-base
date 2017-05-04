package com.wooxun.geekdol.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年8月22日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年8月22日  下午12:17:00 		创建
 *==========================================================
 * 
 */
public class ShortMessage {
	
	/**
	 * http get请求 发送方法 其他方法同理 返回值>0 提交成功
	 * 
	 * @param Mobile
	 *            手机号码
	 * @param Content
	 *            发送内容
	 * @param send_time
	 *            定时发送的时间；可以为空，为空时为及时发送
	 * @return 返回值
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static int sendSMSGet(String Mobile, String Content, String send_time)
			throws MalformedURLException, UnsupportedEncodingException {
		URL url = null;
		String CorpID = "ZZLKY00556";// 账户名
		String Pwd = "123456";// 密码
		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", " "), "GBK");// 发送内容

		url = new URL("http://yzm.mb345.com/ws/BatchSend2.aspx?CorpID="
				+ CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile + "&Content="
				+ send_content + "&Cell=&SendTime=" + send_time);
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ：" + Mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			inputLine = -2;
		}
		System.out.println("结束发送短信返回值：  " + inputLine);
		return inputLine;
	}

	/**
	 * Hppt POST请求发送方法 返回值>0 为 提交成功
	 * 
	 * @param Mobile
	 *            电话号码
	 * @param Content
	 *            发送内容
	 * @param send_time
	 *            定时发送时间，为空时，为及时发送
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static int sendSMSPost(String Mobile, String Content,
			String send_time) throws MalformedURLException,
			UnsupportedEncodingException {

		String inputLine = "";
		int value = -2;
		DataOutputStream out = null;
		InputStream in = null;

		String CorpID = "ZZLKY00556";// 账户名
		String Pwd = "123456";// 密码
		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", " "), "GBK");// 发送内容

		String strUrl = "https://sdk2.028lk.com/sdk2/BatchSend2.aspx?";
		String param = "CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile
				+ "&Content=" + send_content + "&Cell=&SendTime=" + send_time;

		try {

			inputLine = sendPost(strUrl, param);

			System.out.println("开始发送短信手机号码为 ：" + Mobile);

			value = new Integer(inputLine).intValue();

		} catch (Exception e) {

			System.out.println("网络异常,发送短信失败！");
			value = -2;

		}

		System.out.println(String.format("返回值：%d", value));

		return value;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 发送短信get请求的修改版
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月22日 下午2:28:38
	 * @param mobile
	 * @param send_time
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, Object> sendSMSGet_modify(String mobile, String send_time, String type)
			throws MalformedURLException, UnsupportedEncodingException {
		// 创建Map变量用于存放返回变量
		Map<String, Object> result = new HashMap<String, Object>();
		String corpID = ComDefine.getMsg(ConstantStr.SENDSMS_USER);// 账户名
		String pwd = ComDefine.getMsg(ConstantStr.SENDSMS_PWD);// 密码
		String identifyingCode = getRandNum(6);
		Object[] contentArr={ identifyingCode };
		String content = null;
		if (ConstantStr.SEND_TYPE_REGISTER.equals(type)) {
			content = ComDefine.getMsg(ConstantStr.INFO200030,contentArr);
		} else {
			content = ComDefine.getMsg(ConstantStr.INFO200036,contentArr);
		}
		
		String send_content = URLEncoder.encode(
				content.replaceAll("<br/>", " "), "GBK");// 发送内容
		Object[] urlArr={ corpID, pwd, mobile, send_content, send_time };
		URL url = new URL(ComDefine.getMsg(ConstantStr.SENDSMS_URL,urlArr));
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ：" + mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			inputLine = -2;
		}
		result.put("identifyingCode", identifyingCode);
		result.put("sendCode", inputLine);
		System.out.println("结束发送短信返回值：  " + inputLine);
		return result;
	}
	
	public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
	
	public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		try {

			ShortMessage shortMessage = new ShortMessage();

			// Http Get请求
			shortMessage.sendSMSGet("18538317749",
					"【吉客多】你正在注册成为吉客多会员，验证码为："+ getRandNum(6) +"，请注意不要泄漏此验证码", "");

//			// Http post 请求
//			test.sendSMSPost("18896594024",
//					"【吉客多】你正在注册成为吉客多会员，验证码为：123456,请注意不要泄漏此验证码", "");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
