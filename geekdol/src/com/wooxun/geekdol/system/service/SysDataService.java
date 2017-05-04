package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.vo.SysdataVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月20日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月20日  上午10:42:12 		创建
 *==========================================================
 * 
 */
public interface SysDataService <T extends Serializable> extends CrudService<T>{
	
	/**
	 * 
	 * @Title
	 * @Description:新增字典
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:24:16
	 * @param village
	 */
	public void insertSysdata(SysData sysData);
	
	/**
	 * @Title
	 * @Description 根据主键id查询出modle
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:26:05
	 * @param id
	 * @return
	 */
	public SysData selectSysData(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 更新、删除小区
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午7:44:22
	 * @param sysdata
	 * @return true:更新成功;false:更新失败
	 */
	public boolean updateSysdata(SysData sysData);
	
	 /**
	  * 
	  * @Title
	  * @Description 根据类型查到所有的字典表物流(不包括自取和配送)
	  * @author:田振兴
	  * @CreateDate:2016年8月20日 下午4:50:50
	  * @param searchSysdata
	  * @return
	  */
	 public List<SysData> querySysdata(SysdataVo searchSysdata);
	
	/**
	 * 
	 * @Title
	 * @Description:后台查询字典列表
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param queryVillageByParams
	 * @return
	 */
	public List<SysData> querySysdataByParams(SysdataVo searchSysdata);
	
	/**
	 * 
	 * @Title
	 * @Description:后台查询字典列表
	 * @author: 张洋
	 * @CreateDate:2016年7月21日16:57:43
	 * @param querySysdataByBean
	 * @return
	 */
	public List<SysData> querySysdataByBean(SysdataVo searchSysdata);
	
	/**
	 * 
	 * @Title
	 * @Description:后台查询字典条数
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param searchVillage
	 * @return
	 */
	public Long queryCountByParams(SysdataVo searchSysdata);
	
	/**
	 * 
	 * @Title
	 * @Description:同一个类别下  名称不能相等   value值不能相等
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param searchVillage
	 * @return
	 */
	public Long queryCountByYanzheng(SysData yanzhengSysdata);
	
	/**
	 * 
	 * @Title
	 * @Description 跟据type类型映射数据字典的值
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午3:49:05
	 * @param type
	 * @return
	 */
	public List<SysData> querySysdataByType(String type);
   
}
