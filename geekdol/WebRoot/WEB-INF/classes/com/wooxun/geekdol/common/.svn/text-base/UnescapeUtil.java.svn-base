package com.wooxun.geekdol.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年9月7日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年9月7日  下午4:14:09 		创建
 *==========================================================
 * 
 */
public class UnescapeUtil {
	
	/**
	 * 
	 * @Title
	 * @Description 解密
	 * @CreateDate:2016年9月19日 下午3:25:52
	 * @param src
	 * @return String
	 */
	public static String unescape(String src) {
		if (StringUtils.isBlank(src)) {
			return "";
		} else {
			StringBuffer tmp = new StringBuffer();  
	        tmp.ensureCapacity(src.length());  
	        int lastPos = 0, pos = 0;  
	        char ch;  
	        while (lastPos < src.length()) {  
	            pos = src.indexOf("%", lastPos);  
	            if (pos == lastPos) {  
	                if (src.charAt(pos + 1) == 'u') {  
	                    ch = (char) Integer.parseInt(src  
	                            .substring(pos + 2, pos + 6), 16);  
	                    tmp.append(ch);  
	                    lastPos = pos + 6;  
	                } else {  
	                    ch = (char) Integer.parseInt(src  
	                            .substring(pos + 1, pos + 3), 16);  
	                    tmp.append(ch);  
	                    lastPos = pos + 3;  
	                }  
	            } else {  
	                if (pos == -1) {  
	                    tmp.append(src.substring(lastPos));  
	                    lastPos = src.length();  
	                } else {  
	                    tmp.append(src.substring(lastPos, pos));  
	                    lastPos = pos;  
	                }  
	            }  
	        }  
	        return tmp.toString();
		}
    }  
	
	/**
     * 加密
     * @param src 待加密串
     * @return 加密后的串
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }


}
