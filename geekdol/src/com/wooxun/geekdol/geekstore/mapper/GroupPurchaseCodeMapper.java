package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GroupPurchaseCode;


public interface GroupPurchaseCodeMapper <T extends Serializable> extends CrudMapper<T>{
	
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
    
}