package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.VillageMapper;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.QuartersService;
import com.wooxun.geekdol.system.vo.QuartersVo;

/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author YK	
* @CreateDate 2016年7月21日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 YK             	2016年7月21日  下午4:42:14 		创建
*==========================================================
*
*/

@Service
public class QuartersServiceImpl extends CrudServiceImpl<Village> implements QuartersService<Village>{

	private VillageMapper<Village> villageMapper;

	@Autowired
	public QuartersServiceImpl(VillageMapper<Village> villageMapper) {
		super(villageMapper);
		this.villageMapper = villageMapper;
	}
	
	@Override
	public List<QuartersVo> queryQuartersByParams(QuartersVo searchVillage) {
		// 查询数据库
		List<Village> villageList = villageMapper.queryQuartersByParams(searchVillage);
		// 返回组装后的列表数据
		return formateData(villageList);
	}

	@Override
	public Long queryQuartersCountByParams(QuartersVo searchVillage) {
		return villageMapper.queryQuartersCountByParams(searchVillage);
	}

	/**
	 * 列表数据组装
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午4:41:23
	 * @param villageList
	 * @return
	 */
	private List<QuartersVo> formateData(List<Village> villageList){
		List<QuartersVo> result = new ArrayList<QuartersVo>();
		for(Village village : villageList){
			QuartersVo villageVo = new QuartersVo();
			BeanUtils.copyProperties(village, villageVo);
			villageVo.setProvinceName(village.getProvince().getProvinceName());
			villageVo.setCityName(village.getCity().getCityName());
			villageVo.setCountyName(village.getCounty().getCountyName());
			villageVo.setCommunityName(village.getCommunity().getCommunityName());
			villageVo.setVillageLongitude(village.getVillageLongitude());
			villageVo.setVillageLatitude(village.getVillageLatitude());
			result.add(villageVo);
		}
		return result;
	}
}
