package com.wooxun.geekdol.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.BaiduMapUtils;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.system.mapper.UserAreaMapper;
import com.wooxun.geekdol.system.mapper.VillageMapper;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.service.VillageService;
import com.wooxun.geekdol.system.vo.UserAreaVo;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 万通 2016年7月18日 上午10:08:27 创建
 *           ==========================================================
 * 
 */

@Service
public class VillageServiceImpl extends CrudServiceImpl<Village> implements VillageService<Village> {

	private VillageMapper<Village> villageMapper;

	@Autowired
	private UserAreaMapper<UserArea> userAreaMapper;

	@Autowired
	public VillageServiceImpl(VillageMapper<Village> villageMapper) {
		super(villageMapper);
		this.villageMapper = villageMapper;
	}

	@Override
	public void insertVillage(Village village) {
		villageMapper.insert(village);
	}

	@Override
	public Village selectVillage(Long id) {
		return villageMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateVillage(Village village) {
		boolean result = false;
		int i = villageMapper.updateByPrimaryKeySelective(village);
		if (i > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<VillageVo> queryVillageByParams(VillageVo searchVillage) {
		// 查询数据库
		List<Village> villageList = villageMapper.queryVillageByParams(searchVillage);
		// 列表数据组装
		List<VillageVo> result = new ArrayList<VillageVo>();
		result = formateData(villageList);
		return result;
	}

	@Override
	public List<VillageVo> queryVillageByCommuity(VillageVo searchVillage) {
		// 查询数据库
		List<Village> villageList = villageMapper.queryVillageByCommuity(searchVillage);
		// 列表数据组装
		return formateData(villageList);
	}

	@Override
	public Long queryCountByParams(VillageVo searchVillage) {
		return villageMapper.queryCountByParams(searchVillage);
	}

	public List<VillageVo> queryVillageHasNoCooperationStore(VillageVo searchVillage) {
		return villageMapper.queryVillageHasNoCooperationStore(searchVillage);
	}

	@Override
	public List<VillageVo> queryVillageHasCooperationStore(VillageVo searchVillage) {
		return villageMapper.queryVillageHasCooperationStore(searchVillage);
	}

	/**
	 * 
	 * @Title
	 * @Description 列表数据组装
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午2:47:00
	 * @param communityList
	 * @return
	 */
	private List<VillageVo> formateData(List<Village> villageList) {
		List<VillageVo> result = new ArrayList<VillageVo>();
		for (Village village : villageList) {
			VillageVo villageVo = new VillageVo();
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

	@Override
	public List<Village> findVillageByParams(Village village) {

		return villageMapper.findVillageByParams(village);
	}

	@Override
	public List<Village> validationVillageIsopen(Village village) {

		return villageMapper.validationVillageIsopen(village);
	}

	@Override
	public List<Village> selectByCountyIdNoUser(Map<String, Object> parmMap) {
		return villageMapper.selectByCountyIdNoUser(parmMap);
	}

	@Override
	public List<Village> findByUserId(Map<String, Object> parmMap) {
		return villageMapper.findByUserId(parmMap);
	}

	@Override
	public List<Village> findVillageNoAroundStoreByVillageId(List<AroundStoreVillage> aroundStoreVillageList,
			Long userId) {
		// 创建用户和地域关系对象用于封装查询数据
		UserAreaVo userAreaVo = new UserAreaVo();
		// 设置用户id
		userAreaVo.setUserId(userId);
		// 查找用户与地域对应关系
		List<UserArea> userAreaList = userAreaMapper.selectList(userAreaVo);
		// 创建小区列表变量用于封装查询数据
		List<Village> villageTempList = new ArrayList<Village>();
		/* 循环判断用户级别 */
		for (UserArea ua : userAreaList) {
			// 创建小区对象用于封装查询数据
			VillageVo villageVo = new VillageVo();
			if ("05".equals(ua.getAreaLevel())) { // 05为小区管理员级别
				// 根据主键查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectByPrimaryKey(ua.getAreaId()));
			} else if ("04".equals(ua.getAreaLevel())) { // 04为网格长级别
				// 设置网格id查询条件
				villageVo.setCommunityId(ua.getAreaId());
				// 根据网格级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if ("03".equals(ua.getAreaLevel())) { // 03为区管理员级别
				// 设置国家id查询条件
				villageVo.setCountyId(ua.getAreaId());
				// 根据国家级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if ("02".equals(ua.getAreaLevel())) { // 02为城市管理员级别
				// 设置城市id查询条件
				villageVo.setCityId(ua.getAreaId());
				// 根据城市级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if ("01".equals(ua.getAreaLevel())) { // 01为省管理员级别
				// 设置省id查询条件
				villageVo.setProvinceId(ua.getAreaId());
				// 根据省级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			}
		}
		if (aroundStoreVillageList == null || aroundStoreVillageList.size() == 0) { // 如果已推荐小区列表为空
			// 调用mapper方法根据用户查找所拥有的小区
			return villageMapper.selectVillageNoAroundStoreByUser(villageTempList);
		} else { // 如果不为空
			// 创建map变量用于存放查询参数
			Map<String, Object> param = new HashMap<String, Object>();
			/* 向map中放入查询参数 */
			param.put("villageTempList", villageTempList);
			param.put("aroundStoreVillageList", aroundStoreVillageList);
			// 调用mapper方法根据查询参数查询小区信息
			return villageMapper.selectVillageNoAroundStoreByVillageId(param);
		}
	}

	@Override
	public List<Village> findVillageAroundStoreByVillageId(List<AroundStoreVillage> aroundStoreVillageList) {
		// 创建Map变量用于存放sql查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		// 向map中放入参数
		param.put("list", aroundStoreVillageList);
		return villageMapper.selectVillageAroundStoreByVillageId(param);
	}

	@Override
	public List<Village> selectByLongAndLat(Map<String, String> map) {
		return villageMapper.selectByLongAndLat(map);
	}

	@Override
	public String selectVillageByIntimateNewsId(Long id) {

		return villageMapper.selectVillageByIntimateNewsId(id);
	}

	@Override
	public List<Village> findVillageNoSuggest(List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList, Long userId) {
		// 创建用户和地域关系对象用于封装查询数据
		UserAreaVo userAreaVo = new UserAreaVo();
		// 设置用户id
		userAreaVo.setUserId(userId);
		// 查找用户与地域对应关系
		List<UserArea> userAreaList = userAreaMapper.selectList(userAreaVo);
		// 创建小区列表变量用于封装查询数据
		List<Village> villageTempList = new ArrayList<Village>();
		/* 循环判断用户级别 */
		for (UserArea ua : userAreaList) {
			// 创建小区对象用于封装查询数据
			VillageVo villageVo = new VillageVo();
			if ("05".equals(ua.getAreaLevel())) { // 05为小区管理员级别
				// 根据主键查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectByPrimaryKey(ua.getAreaId()));
			} else if ("04".equals(ua.getAreaLevel())) { // 04为网格长级别
				// 设置网格id查询条件
				villageVo.setCommunityId(ua.getAreaId());
				// 根据网格级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if ("03".equals(ua.getAreaLevel())) { // 03为区管理员级别
				// 设置国家id查询条件
				villageVo.setCountyId(ua.getAreaId());
				// 根据国家级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if ("02".equals(ua.getAreaLevel())) { // 02为城市管理员级别
				// 设置城市id查询条件
				villageVo.setCityId(ua.getAreaId());
				// 根据城市级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if ("01".equals(ua.getAreaLevel())) { // 01为省管理员级别
				// 设置省份id查询条件
				villageVo.setProvinceId(ua.getAreaId());
				// 根据省份级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			}
		}
		if (aroundSuggestStoreVillageList == null || aroundSuggestStoreVillageList.size() == 0) { // 如果已推荐小区列表为空
			// 调用mapper方法根据用户查找所拥有的小区
			return villageMapper.selectVillageNoAroundStoreByUser(villageTempList);
		} else { // 如果不为空
			// 创建map变量用于存放查询参数
			Map<String, Object> param = new HashMap<String, Object>();
			/* 向map中放入查询参数 */
			param.put("villageTempList", villageTempList);
			param.put("aroundStoreVillageList", aroundSuggestStoreVillageList);
			// 调用mapper方法根据查询参数查询小区信息
			return villageMapper.selectVillageNoAroundStoreByVillageId(param);
		}
	}

	@Override
	public List<Village> findVillageHasSuggest(List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList) {
		// 创建Map变量用于存放sql查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		// 向map中放入参数
		param.put("list", aroundSuggestStoreVillageList);
		return villageMapper.selectVillageAroundStoreByVillageId(param);
	}

	@Override
	public List<Village> selectAll(Long userId) {
		return villageMapper.selectAll(userId);
	}

	@Override
	public String selectVillageByActivityCollectionId(Long id) {

		return villageMapper.selectVillageByActivityCollectionId(id);
	}

	@Override
	public List<Village> selectByCountyId(Map<String, Object> parmMap) {
		return villageMapper.selectByCountyId(parmMap);
	}

	@Override
	public List<Village> queryVillageByCountryId(Long countyId) {
		return villageMapper.selectVillageByCountyId(countyId);
	}

	@Override
	public Long findEffectVillage(VillageVo searchVillage) {
		return villageMapper.findEffectVillage(searchVillage);
	}

	@Override
	public List<Map<String, Object>> queryVillageList(VillageVo villageVo) {
		// 设置查找条件常驻类型
		//villageVo.setVillageType(ConstantStr.VILLAGE_TYPE_2);
		// 查询常驻小区的精度、纬度
		//VillageVo resident = villageMapper.queryByUserIdAndVillageType(villageVo);
		// 当前用户的纬度
		BigDecimal latitude1 = new BigDecimal(villageVo.getCurLatitude());
		// 当前用户的经度
		BigDecimal longitude1 = new BigDecimal(villageVo.getCurLongitude());
		// 本省份下的小区纬度
		BigDecimal latitude2 = new BigDecimal(0);
		// 本省份下的小区经度
		BigDecimal longitude2 = new BigDecimal(0);
		// 差距
		String distance = "0.00";
		String villageType = "";
		List<Map<String,Object>> list = villageMapper.queryVillageList(villageVo);
		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> object:list){
			distance = "0.00";
			// 小区类型赋值
			villageType = object.get("villageType")+"";
			// 本小区省份下的非关注与非常驻计算距离
			if(StringUtil.isBlank(villageType)){
				latitude2 = new BigDecimal(object.get("latitude")+"");
				longitude2 = new BigDecimal(object.get("longitude")+"");
				distance = BaiduMapUtils.Distance(longitude1, latitude1, longitude2, latitude2);
				object.put("distance", distance);
				newList.add(object);
			}
		}
		// 根据距离排序
		if(newList.size()>1){
			Collections.sort(newList, new DistanceComparator());
		}
		for(Map<String,Object> object:list){
			// 小区类型赋值
			villageType = object.get("villageType")+"";
			if(StringUtil.isNotBlank(villageType)){
				// 常驻、关注类型区分
				if(ConstantStr.VILLAGE_TYPE_2.endsWith(villageType)){
					object.put("distance", "常驻小区");
				}else if(ConstantStr.VILLAGE_TYPE_1.endsWith(villageType)){
					object.put("distance", "关注小区");
				}
				resultList.add(object);
			}
			// 移除部分属性，节省手机端流量
			object.remove("villageType");
			object.remove("latitude");
			object.remove("longitude");
		}
		resultList.addAll(newList);
		return resultList;
	}

    @Override
    public List<Village> selectVillageByIds(Map<String, Object> parm) {
        return villageMapper.selectVillageByIds(parm);
    }

	@Override
	public VillageVo queryByUserIdAndVillageType(VillageVo villageVo) {
		return villageMapper.queryByUserIdAndVillageType(villageVo);
	}

    @Override
    public List<Village> selectByUserId(Map<String, Object> parmMap) {
        return villageMapper.selectByUserId(parmMap);
    }

    @Override
    public Village selectByUserIdAndVillageType(VillageVo villageVo) {
        return villageMapper.selectByUserIdAndVillageType(villageVo);
    }
    
    static class DistanceComparator implements Comparator<Object> {  
        public int compare(Object object1, Object object2) {// 实现接口中的方法  
        	@SuppressWarnings("unchecked")
			Map<String,Object> p1 = (Map<String,Object>) object1; // 强制转换  
        	@SuppressWarnings("unchecked")
			Map<String,Object> p2 = (Map<String,Object>) object2; 
        	String p1Str = p1.get("distance").toString();
        	String p2Str = p2.get("distance").toString();
        	if(p1Str.indexOf("未知")>0||p2Str.indexOf("未知")>0){
        		return 0;
        	}
        	Double s1,s2 = 0.00;
        	if(p1Str.indexOf("km")>0){
        		p1Str = p1Str.substring(0,p1Str.lastIndexOf("km")).replace(",", "");
        		s1 = Double.parseDouble(p1Str)*1000;
        	}else{
        		p1Str = p1Str.substring(0,p1Str.lastIndexOf("m"));
        		s1 = Double.parseDouble(p1Str);
        	}
        	
        	if(p2Str.indexOf("km")>0){
        		p2Str = p2Str.substring(0,p2Str.lastIndexOf("km")).replace(",", "");
        		s2 = Double.parseDouble(p2Str)*1000;
        	}else{
        		p2Str = p2Str.substring(0,p2Str.lastIndexOf("m"));
        		s2 = Double.parseDouble(p2Str);
        	}
            return s1.compareTo(s2);  
        }  
    } 
    
    public static void main(String[] args) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String,Object> obj1 = new HashMap<String,Object>();
    	obj1.put("distance", "600m");
    	Map<String,Object> obj2 = new HashMap<String,Object>();
    	obj2.put("distance", "500m");
    	Map<String,Object> obj3 = new HashMap<String,Object>();
    	obj3.put("distance", "400m");
    	list.add(obj1);
    	list.add(obj2);
    	list.add(obj3);
    	Collections.sort(list, new DistanceComparator());
    	for(Map<String,Object> obj : list){
    		System.out.println(obj.get("distance"));
    	}
	}
}
