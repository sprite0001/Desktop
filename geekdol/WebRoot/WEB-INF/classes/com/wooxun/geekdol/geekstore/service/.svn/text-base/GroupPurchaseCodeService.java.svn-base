package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.GroupPurchaseCode;

/**
 * @Title
 * @Description 团购验证
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月8日  上午10:38:27 		创建
 *==========================================================
 * 
 */
public interface GroupPurchaseCodeService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title 团购管理-团购验证
	 * @Description 根据团购编码和团购码查找团购码的信息
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 上午11:47:46
	 * @param groupPurchaseCode
	 * @return
	 */
	public GroupPurchaseCode verificationCode(GroupPurchaseCode groupPurchaseCode);
	
	/**
	 * 
	 * @Title 团购管理-团购验证
	 * @Description 根据订单ID查找团购码信息
	 * @author:田振兴
	 * @CreateDate:2016年8月8日 上午11:47:46
	 * @param groupPurchaseCode
	 * @return
	 */
	public List<GroupPurchaseCode> findCode(GroupPurchaseCode groupPurchaseCode);
	
	public int updateGroup(GroupPurchaseCode groupPurchaseCode);

}
