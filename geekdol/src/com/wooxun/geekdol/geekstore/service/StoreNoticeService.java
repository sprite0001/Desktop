package com.wooxun.geekdol.geekstore.service;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.geekstore.model.StoreNotice;

/**
 * @Title
 * @Description 店铺公告
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月27日  下午3:39:58 		创建
 *==========================================================
 * 
 */
public interface StoreNoticeService <T extends Serializable> extends CrudService<T>{
	
	/**
	 * 
	 * @Title
	 * @Description 保存店铺公告
	 * @author:YK
	 * @CreateDate:2016年8月3日 下午5:40:44
	 * @param storeNotice
	 * @return boolean
	 */
	public boolean insertStoreNotice(StoreNotice storeNotice);
}
