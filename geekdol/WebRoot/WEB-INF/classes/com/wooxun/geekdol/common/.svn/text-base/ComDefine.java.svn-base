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
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
public class ComDefine {
	static private Properties prop;
	static{
		loads();
	}
	synchronized static public void loads(){
  if(prop == null)
  {
		InputStream is = ComDefine.class.getResourceAsStream("/ComDefine.properties");
		prop= new Properties();
		try {
			prop.load(is);
		}
		catch (Exception e) {
			System.err.println("不能读取属性文件.请确保ComDefine.properties在resource目录下.");
		}
  }
	}
	public static String getDefine(String strType){
		if(prop==null)loads();
		return prop.getProperty(strType);
	}
	
	public static String getMsg(String strType,Object arr[]){
		String pattern = getDefine(strType);
		MessageFormat format = new MessageFormat(pattern, Locale.CHINA);
		String result = format.format(arr);
		return result;
	}
	
	public static String getMsg(String strType){
		String pattern = getDefine(strType);
		MessageFormat format = new MessageFormat(pattern, Locale.CHINA);
		String result = format.toPattern();
		return result;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 根据文件类型上传到对应的目录
	 * @author:YK
	 * @CreateDate:2016年8月4日 上午11:18:45
	 * @param attType 文件类型 
	 * @param model 模块名称
	 * @return
	 */
	public static String getRealPath(String attType,String model){
		String result = getMsg("realpath");
		if(StringUtils.isNotBlank(attType)&& ConstantStr.FILE_IMAGE.equals(attType)){
			result = result+model+"/upload/image/"+DateUtil.format(new Date(), "yyyyMMdd")+"/";
		}else if(StringUtils.isNotBlank(attType)&& ConstantStr.FILE_VIDEO.equals(attType)){
			result = result+model+"/upload/video/"+DateUtil.format(new Date(), "yyyyMMdd")+"/";
		}else if(StringUtils.isNotBlank(attType)&& ConstantStr.FILE_FILE.equals(attType)){
			result = result+model+"/upload/file/"+DateUtil.format(new Date(), "yyyyMMdd")+"/";
		}
		return result;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 获取访问路径
	 * @author:YK
	 * @CreateDate:2016年8月4日 上午11:34:31
	 * @param attType 文件类型 
	 * @param model 模块名称
	 * @return
	 */
	public static String getHttppath(String attType,String model){
		String result = getMsg("httppath");
		if(StringUtils.isNotBlank(attType)&& ConstantStr.FILE_IMAGE.equals(attType)){
			result = result+model+"/upload/image/"+DateUtil.format(new Date(), "yyyyMMdd")+"/";
		}else if(StringUtils.isNotBlank(attType)&& ConstantStr.FILE_VIDEO.equals(attType)){
			result = result+model+"/upload/video/"+DateUtil.format(new Date(), "yyyyMMdd")+"/";
		}else if(StringUtils.isNotBlank(attType)&& ConstantStr.FILE_FILE.equals(attType)){
			result = result+model+"/upload/file/"+DateUtil.format(new Date(), "yyyyMMdd")+"/";
		}
		return result;
	}
	
	public static void main(String[] args) {
		Object arr[] ={"用户"};
		System.out.print(getMsg("100001",arr));
	}
}