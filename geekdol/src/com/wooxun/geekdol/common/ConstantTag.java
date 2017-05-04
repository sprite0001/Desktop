package com.wooxun.geekdol.common;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ConstantTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3273414537593070065L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**	  常量类	 */
	private String clazz = ConstantStr.class.getName(); 

	/** 指定常量	 */
	private String var; 

	/**
     * 标签开始处理
     * 
     * @return int 是否处理标签体内容 
     */
	@Override
	public int doStartTag() {
		try {
			Class c = Class.forName(clazz);

			if (var == null) {
				Field[] fields = c.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);
				for (Field field : fields) {
					pageContext.setAttribute(field.getName(), field.get(this));
				}
			} else {
				Object value = c.getField(var).get(this);
				pageContext.setAttribute(c.getField(var).getName(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public void release() {
		super.release();
		clazz = null;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
