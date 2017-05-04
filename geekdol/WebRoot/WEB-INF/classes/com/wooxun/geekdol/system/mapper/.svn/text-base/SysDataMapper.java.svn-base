package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.vo.SysdataVo;

public interface SysDataMapper <T extends Serializable> extends CrudMapper<T>{
	
	 public List<SysData> querySysdataByParams(SysdataVo searchSysdata);

	 public List<SysData> querySysdataByBean(SysdataVo searchSysdata);
	    
	 public Long queryCountByParams(SysdataVo searchSysdata);
	 
	 public Long queryCountByYanzheng(SysData yanzhengSysdata);
   
	 public List<SysData> querySysdataByType(String type);
	 
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
	 
	 public SysData selectSysDataByTypeAndValue(Map<String,Object> map);
}