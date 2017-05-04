package com.wooxun.geekdol.common;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhougp	
 * @CreateDate 2016年7月14日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年7月14日  下午4:56:46 		创建
 *==========================================================
 * 
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class DateUtil {
	private static final String sdf1reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String sdf2reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2}$";

	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	private static final String sdf3reg = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static final String sdf4reg = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2}$";

	private static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd");

	private static final String sdf5reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}$";

	private static final SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static final String sdf6reg = "^\\d{2,4}\\d{1,2}\\d{1,2}$";

	private static final SimpleDateFormat sdf6 = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 
	 * @Title
	 * @Description 凌晨到夜晚
	 * @author:王肖东
	 * @CreateDate:2016年8月30日 上午9:30:12
	 * @param date
	 * @param flag
	 *            0 返回yyyy-MM-dd 00:00:00日期<br>
	 *            1 返回yyyy-MM-dd 23:59:59日期
	 * @return
	 */
	public static String weeHours(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (flag == 0) {
			return dateToString(cal.getTime());
		} else if (flag == 1) {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
		}
		return dateToString(cal.getTime());
	}

	/**
	 * 将Timestamp类型转换字符串(yyyyMMddHHmmss)
	 *
	 * @param date
	 * @return
	 */
	public static String getStringTimestampE(Timestamp date) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * <p/>
	 * 将日期格式化成相应格式的字符串，如：
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd
	 * <li>yyyy/MM/dd HH:mm:ss
	 * <li>yyyy/MM/dd
	 * </p>
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null)
			return "";
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将Timestamp类型转换成字符串（yyyyMMdd）
	 *
	 * @param date
	 * @return
	 */
	public static String getStringTimestamp1(Timestamp date) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将DATE类型转换成字符串（yyyyMMdd）
	 *
	 * @param date
	 * @return
	 */
	public static String getStringDate1(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 
	 * @Title
	 * @Description 将date类型格式 转换为yyyy-MM-dd HH:mm:ss字符串
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午11:50:33
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = form.format(date);
		return str;
	}

	public String getStringTimestampDelSlash(Timestamp date) {

		if (date == null) {
			return "";
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);
		str = str.substring(0, 4) + str.substring(5, 7) + str.substring(8);

		return str;
	}

	/**
	 * 取得Calendar实例
	 *
	 * @return
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 根据日期偏移天数取得日期。offset > 0 ,往后延迟offset天， offset < 0 向前推进 offset天
	 *
	 * @param date
	 *            :基日期
	 * @param offset
	 *            :日期天数偏移量
	 * @return
	 */
	public static Date getDate(Date date, int offset) {
		if (date == null)
			return date;
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		return calendar.getTime();
	}

	/**
	 * <p/>
	 * 将日期字符串解析成日期对象，支持一下格式
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd
	 * <li>yyyy/MM/dd HH:mm:ss
	 * <li>yyyy/MM/dd
	 * </p>
	 *
	 * @param str
	 * @return
	 */
	public static Date parse(String str) {
		Date date = null;
		Pattern p1 = Pattern.compile(sdf1reg);
		Matcher m1 = p1.matcher(str);
		Pattern p2 = Pattern.compile(sdf2reg);
		Matcher m2 = p2.matcher(str);
		Pattern p3 = Pattern.compile(sdf3reg);
		Matcher m3 = p3.matcher(str);
		Pattern p4 = Pattern.compile(sdf4reg);
		Matcher m4 = p4.matcher(str);
		Pattern p5 = Pattern.compile(sdf5reg);
		Matcher m5 = p5.matcher(str);
		Pattern p6 = Pattern.compile(sdf6reg);
		Matcher m6 = p6.matcher(str);
		try {
			if (m1.matches()) {
				date = sdf1.parse(str);
			} else if (m2.matches()) {
				date = sdf2.parse(str);
			} else if (m3.matches()) {
				date = sdf3.parse(str);
			} else if (m4.matches()) {
				date = sdf4.parse(str);
			} else if (m5.matches()) {
				date = sdf5.parse(str);
			} else if (m6.matches())
				date = sdf6.parse(str);
		} catch (ParseException e) {
			// throw new RuntimeException("非法日期字符串，解析失败：" + str, e);
			return null;
		}
		return date;
	}

	/**
	 * 获取系统时间 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String nowTime() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return dateStr;
	}

	/**
	 * yyyy.MM.dd to yyyyMMdd
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static String formatDate(String date) throws Exception {
		if (date == "") {
			return date;
		}
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tempDate = format.parse(date);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		dateStr = fmt.format(tempDate);
		return dateStr;
	}

	public static String formatDateYMD(String date, String formatStr, String fmtStr) throws Exception {
		if (date == "") {
			return date;
		}
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date tempDate = format.parse(date);
		SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
		dateStr = fmt.format(tempDate);
		return dateStr;
	}

	public static String formatDateYm(String date) throws Exception {
		if (date == "") {
			return date;
		}
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
		Date tempDate = format.parse(date);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
		dateStr = fmt.format(tempDate);
		return dateStr;
	}

	public static int getMonth(String dateStr, String fromatStr) {
		SimpleDateFormat format = new SimpleDateFormat(fromatStr);
		Date tempDate = new Date();
		try {
			tempDate = format.parse(dateStr);
		} catch (ParseException e) {

		}
		Calendar c = Calendar.getInstance();
		c.setTime(tempDate);
		int month = c.get(Calendar.MONTH);

		return month + 1;
	}

	public static int getResult(String dateStr, String dateStr2, String fromatStr) {
		SimpleDateFormat format = new SimpleDateFormat(fromatStr);
		Date tempDate1 = new Date();
		Date tempDate2 = new Date();
		try {
			tempDate1 = format.parse(dateStr);
			tempDate2 = format.parse(dateStr2);
		} catch (ParseException e) {

		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(tempDate1);
		c2.setTime(tempDate2);
		int result = c1.compareTo(c2);
		return result;
	}

	/**
	 * 获取系统时间 yyyyMMdd
	 * 
	 * @return
	 */
	public static String nowDate() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return dateStr;
	}

	/**
	 * 获取系统时间 yyyyMMdd
	 * 
	 * @return
	 */
	public static int nowMoth() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM", Locale.CHINA);
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return Integer.parseInt(dateStr);
	}

	/**
	 * 获取昨日
	 * 
	 * @return
	 */
	public static String yesTodate() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		dateStr = format.format(getDate(new Date(), -1));

		return dateStr;
	}

	/**
	 * 获取昨日
	 * 
	 * @return
	 */
	public static String yesTodate(String formatDtr, String dateStr) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat(formatDtr);
		try {
			calendar.setTime(form.parse(dateStr));
			calendar.add(Calendar.DATE, -1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return dateStr;
		}

		return form.format(calendar.getTime());
	}

	/**
	 * 获取当时时间 HHMMSS
	 * 
	 * @return
	 */
	public static String nowTimeHms() {
		String hms = nowTime().substring(8);

		return hms;
	}

	/**
	 * 获取当时时间 yyyyMMddHHmm
	 * 
	 * @return
	 */
	public static String nowTimeYmdHm() {
		String hms = nowTime().substring(0, nowTime().length() - 2);

		return hms;
	}

	/**
	 * 将yyyyMMddHHmm转换成 yyyy/MM/dd HH:mm
	 * 
	 * @return
	 */
	public static String parseTimeYmdHm(String time) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		SimpleDateFormat target = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			dateStr = target.format(format.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr;
	}

	/**
	 * 将yyyy/MM/dd转换成 yyyyMMdd
	 * 
	 * @return
	 */
	public static String parseTimeYmd(String time) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat target = new SimpleDateFormat("yyyyMMdd");
		try {
			dateStr = target.format(format.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr;
	}

	/**
	 * 将yyyyMMdd转换成 MM.dd
	 * 
	 * @return
	 */
	public static String parsemd(String time) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat target = new SimpleDateFormat("yyyy.MM.dd");
		try {
			dateStr = target.format(format.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr.substring(5);
	}

	/**
	 * 将yyyy.MM.dd转换成 yyyy.MM
	 * 
	 * @return
	 */
	public static String parseYm(String time, String format) {
		String dateStr = "";
		SimpleDateFormat target = new SimpleDateFormat(format);
		try {
			dateStr = target.format(target.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr.substring(0, 7);
	}

	/**
	 * 将yyyyMMdd转换成 yyyy.MM
	 * 
	 * @return
	 */
	public static String parseYm(String time, String format, String sFormat) {
		String dateStr = "";
		SimpleDateFormat temp = new SimpleDateFormat(sFormat);
		SimpleDateFormat target = new SimpleDateFormat(format);
		try {
			dateStr = target.format(temp.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr.substring(0, 7);
	}

	/**
	 * 将yyyyMMdd转换成 yyyy.MM.dd
	 * 
	 * @return
	 */
	public static String parseYmd(String time, String format, String sFormat) {
		String dateStr = "";
		SimpleDateFormat temp = new SimpleDateFormat(sFormat);
		SimpleDateFormat target = new SimpleDateFormat(format);
		try {
			dateStr = target.format(temp.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr;
	}

	/**
	 * 将yyyy/MM/dd转换成 MM.dd
	 * 
	 * @return
	 */
	public static String parseTimemd(String time) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat target = new SimpleDateFormat("yyyy.MM.dd");
		try {
			dateStr = target.format(format.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr.substring(5);
	}

	/**
	 * 将yyyyMMddHHmmss转换成 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String parseTime(String time) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateStr = target.format(format.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr;
	}

	/**
	 * 获取系统时间 yyyy年mm月 hh:MM:ss
	 * 
	 * @return
	 */
	public static String getTimeP() {
		String time = nowTime();
		String tmpTime = time.substring(0, 4) + "年" + time.substring(4, 6) + "月" + time.substring(6, 8) + "日 "
				+ time.substring(8, 10) + ":" + time.substring(10, 12) + ":" + time.substring(12, 14);

		return tmpTime;
	}

	/**
	 * 将DATE类型转换成字符串（yyyy-MM-dd）
	 *
	 * @param date
	 * @return
	 */
	public static String getStringDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将Timestamp类型转换成字符串（yyyy-MM-dd）
	 *
	 * @param date
	 * @return
	 */
	public static String getStringTimestamp(Timestamp date) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDay(Date theDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(calendar.getTime());
		return day_first;

	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static String getLastDay(Date theDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		// 上个月最后一天
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());

		return day_last;

	}

	/**
	 * 判断是否是工作日
	 * 
	 * @return true 工作日，false：周末
	 */
	public static boolean isWeekDay(Date theDate) {
		boolean flg = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
			flg = false;
		} else {
			flg = true;
		}

		return flg;
	}

	/**
	 * 判断是否是工作日
	 * 
	 * @return true 工作日，false：周末
	 */
	public static int getDaysOfMonth(String year, String month) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		int days = 0;
		try {
			rightNow.setTime(simpleDate.parse(year + "/" + month)); // 要计算你想要的月份，改变这里即可
			days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	public static int getGzts(String aab) {
		String[] c = aab.split(";");
		int dd = 0;
		for (int i = 0; i < c.length; i++) {
			String[] d = c[i].split("-");
			int a = getJg(d[0], d[1]);
			dd += a;
		}
		return dd;
	}

	public static int getJg(String a, String b) {
		String[] aa = a.split("\\.");
		String[] bb = b.split("\\.");
		int c = Integer.parseInt(bb[1]) - Integer.parseInt(aa[1]) + 1;
		return c;

	}

	public static Double getYearlj(String yearlj) {
		Integer day = 0;
		if (yearlj.contains("个月")) {
			String[] c = yearlj.split("个月");
			if (yearlj.contains("天")) {
				String[] e = c[1].split("天");
				day += Integer.valueOf(e[0]);
			}
			day += Integer.valueOf(c[0]) * 30;
		} else {
			String[] d = yearlj.split("天");
			day += Integer.valueOf(d[0]);
		}

		return new Double(day);

	}

	/**
	 * 本年累计时间
	 * 
	 * @param days
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static String getDays(Double days) throws Exception {
		if (days == null)
			return "";
		Long month = (long) (days / 30);
		Long day = (long) (days % 30);
		if (month == 0) {
			return day + "天";
		}
		if (day == 0) {
			return month + "个月";
		}
		return month + "个月" + day + "天";
	}

	/**
	 * 两个时间段之间的工作日
	 * 
	 * @param strStartDate
	 * @param strEndDate
	 * @return
	 * @author:Admin
	 */

	public static Double getWorkDate(String strStartDate, String strEndDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = sdf.parse(strStartDate);
			endDate = sdf.parse(strEndDate);
		} catch (ParseException e) {
			System.out.println("非法的日期格式,无法进行转换");
			e.printStackTrace();
		}
		int result = 0;
		while (startDate.compareTo(endDate) <= 0) {
			if (startDate.getDay() != 6 && startDate.getDay() != 0)
				result++;
			startDate.setDate(startDate.getDate() + 1);
		}
		return new Double(result);
	}

	/**
	 * 取得时间间隔
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static Long getdayInterval(String day1, String day2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date1 = sdf.parse(day1);
		Date date2 = sdf.parse(day2);
		long day = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);

		return day;
	}

	/**
	 * 取得顺延一个月的时间
	 * 
	 * @param day
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static String getSy(String day) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得顺延两个月的时间
	 * 
	 * @param day
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static String getSyT(String day, int count, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, count);
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 下个月
	 * 
	 * @param day
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static String getNextDateMd(String day) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date date = sdf.parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 下个月
	 * 
	 * @param day
	 * @return
	 * @throws Exception
	 * @author:Admin
	 */
	public static String getNextDate(String day) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = sdf.parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * yyyyMM转化成yyyy.MM
	 * 
	 * @param day
	 * @return
	 * @author:Admin
	 */
	public static String getYm(String day) {
		String dateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy.MM");
		try {
			simpleDate.format(sdf.parse(day));
		} catch (ParseException e) {
			return day;
		}
		return dateStr;
	}

	/* 时间比大小 */
	public static int timeCompare(String t1, String t2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(t1));
			c2.setTime(formatter.parse(t2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}

	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	public static Date parseDate(String dateTime, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateTime);
	}

	public static void main(String[] args) throws Exception {
		/*
		 * int aa = getResult("20140801","20140501","yyyyMMdd");
		 * System.out.println(getCurrentMonth() );
		 */
		/*
		 * List<UserJcInfo> list = Lists.newArrayList(); list.add(new
		 * UserJcInfo(1,"1","1","2014.08.01")); list.add(new
		 * UserJcInfo(2,"2","1","2014.08.08")); list.add(new
		 * UserJcInfo(5,"1","1","2014.08.10")); list.add(new
		 * UserJcInfo(3,"1","2","2014.08.02")); list.add(new
		 * UserJcInfo(4,"2","2","2014.08.07")); getCurrentMonth
		 * SortList<UserJcInfo> sortList = new SortList<UserJcInfo>();
		 * sortList.Sort(list, "getDateTime", null); for(UserJcInfo user :
		 * list){ System.out.println(user.getDateTime()); }
		 */
		Date nextMonthFirstDate = nextMonthFirstDate();
		printDate(nextMonthFirstDate);
	}

	public static int compareToDate(String str, Date date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		str = format(date, "yyyy-MM-dd") + " " + str;
		cal1.setTime(date);
		cal2.setTime(formatter.parse(str));
		return cal1.compareTo(cal2);
	}

	/**
	 * 
	 * @Title
	 * @Description 获取当月第一天的日期
	 * @author:863soft-zhougp
	 * @CreateDate:2016年6月6日 下午2:30:00
	 * @return
	 */
	public static String getMonthFirstDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());

		return first;
	}

	/**
	 * 
	 * @Title
	 * @Description 获取当月最后一天的日期
	 * @author:863soft-zhougp
	 * @CreateDate:2016年6月6日 下午2:31:55
	 * @return
	 */
	public static String getMonthLastDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());

		return last;
	}

	public static Date nextMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static void printDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
	}

	/**
	 * 
	 * @Title
	 * @Description 获取传入月份的上月
	 * @author:Administrator
	 * @CreateDate:2016年6月14日 下午2:06:14
	 * @param dateStr
	 * @param fromatStr
	 * @return
	 */
	public static String getLastMonth(String dateStr, String fromatStr) {
		SimpleDateFormat format = new SimpleDateFormat(fromatStr);
		Date tempDate = new Date();
		try {
			tempDate = format.parse(dateStr);
		} catch (ParseException e) {

		}
		Calendar c = Calendar.getInstance();
		c.setTime(tempDate);
		c.add(Calendar.MONTH, -1);
		String month = format.format(c.getTime());
		return month;
	}

	/**
	 * 
	 * @Title
	 * @Description 时间比较
	 * @author:YK
	 * @CreateDate:2016年8月10日 下午7:01:31
	 * @param oneDate
	 * @param towDate
	 * @return
	 */
	public static boolean compareTime(Date oneDate, Date towDate) {
		boolean flg = false;
		if (oneDate.getTime() >= towDate.getTime()) {
			flg = true;
		}
		return flg;
	}
}
