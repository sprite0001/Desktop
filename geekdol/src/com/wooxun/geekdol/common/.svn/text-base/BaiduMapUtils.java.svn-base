package com.wooxun.geekdol.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月19日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月19日  上午11:56:05 		创建
 *==========================================================
 * 
 */
public class BaiduMapUtils {
	
	private static DecimalFormat df = new DecimalFormat("###,###.0");
	
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static String Distance(BigDecimal longs1, BigDecimal lats1, BigDecimal longs2, BigDecimal lats2) {
		if(longs1 == null || lats1 == null){
			return "未知";
		}
		double long1 = Double.valueOf(longs1.toString());
		double lat1 = Double.valueOf(lats1.toString());
		double long2 = Double.valueOf(longs2.toString());
		double lat2 = Double.valueOf(lats2.toString());
		
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double a = radLat1 - radLat2;
		double b = Math.toRadians(long1) - Math.toRadians(long2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
				* Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;
		if(s<=500){
			return s+"m";
		}
		s = s/1000;
		String result = df.format(s);
		if(result.startsWith(".")){
			result = "0"+result;
		}
		return result+"km";
	}
	public static void main(String[] args) {
		System.out.println(Distance(new BigDecimal(113.552041),new BigDecimal(34.802951),
				new BigDecimal(113.552041),new BigDecimal(34.808055)));
	}
}
