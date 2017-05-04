package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 小区业务类 ========================================================== No
 *           修改人员 修改日期 描述 1. 万通 2016年7月18日 上午10:07:49 创建
 *           ==========================================================
 * 
 */
public interface VillageService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description:新增小区
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:24:16
	 * @param village
	 */
	public void insertVillage(Village village);

    /**
     * 
     * @Title 小区查询
     * @Description 根据用户id查找分配给此用户的所有数据
     * @author:张洋
     * @CreateDate:2016年9月27日10:57:03
     * @return List集合
     */
    public List<Village> selectByUserId(Map<String, Object> parmMap);

	/**
	 * @Title
	 * @Description
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:26:05
	 * @param id
	 * @return Village
	 */
	public Village selectVillage(Long id);

	/**
	 * 
	 * @Title
	 * @Description 更新、删除小区
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午7:44:22
	 * @param community
	 * @return true:更新成功;false:更新失败
	 */
	public boolean updateVillage(Village village);

	/**
	 * 
	 * @Title
	 * @Description:后台查询小区列表
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchVillage
	 * @return List
	 */
	public List<VillageVo> queryVillageByParams(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description:后台查询小区条数
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param searchVillage
	 * @return Long
	 */
	public Long queryCountByParams(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 根据社区/街道/办事处 ID和小区名查询所有未分配的可用的小区
	 * @author:张洋
	 * @CreateDate:2016年7月22日16:55:03
	 * @param selectByCountyIdNoUser
	 * @return List<Village>
	 */
	public List<Village> selectByCountyIdNoUser(Map<String, Object> parmMap);

	/**
	 * 
	 * @Title
	 * @Description 根据社区/街道/办事处 ID和小区名查询所有小区
	 * @author:张洋
	 * @CreateDate:2016年8月17日10:23:54
	 * @param parmMap
	 * @return List<Village>
	 */
	public List<Village> selectByCountyId(Map<String, Object> parmMap);

	/**
	 * 
	 * @Title 市查询
	 * @Description 根据用户id查找分配给此用户的所有数据
	 * @author:张洋
	 * @param parmMap
	 * @CreateDate:2016年7月25日17:11:49
	 * @return List集合
	 */
	public List<Village> findByUserId(Map<String, Object> parmMap);

	/**
	 * 
	 * @Title
	 * @Description:根据社区的id和新添加的小区名字判断是不是同一社区下面有相同的小区
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchVillage
	 * @return List
	 */
	public List<Village> findVillageByParams(Village village);

	/**
	 * 
	 * @Title
	 * @Description 判断小区是否开过店
	 * @author:王肖东
	 * @CreateDate:2016年7月20日 上午9:30:46
	 * @param findVillageByParams
	 * @return List<Village>
	 */
	public List<Village> validationVillageIsopen(Village village);

	/**
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年7月23日 下午3:45:48
	 * @param searchVillage
	 * @return List
	 */
	public List<VillageVo> queryVillageByCommuity(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 新增合作店铺的时候，查询没有合作店管辖的小区
	 * @author:YK
	 * @CreateDate:2016年7月28日 上午9:58:37
	 * @param searchVillage
	 * @return List
	 */
	public List<VillageVo> queryVillageHasNoCooperationStore(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 铺货、推荐产品的时候，查找有效的合作店小区
	 * @author:YK
	 * @CreateDate:2016年8月22日 上午10:08:29
	 * @param searchVillage
	 * @return List
	 */
	public List<VillageVo> queryVillageHasCooperationStore(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 查询本周边店所不属于的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午8:55:12
	 * @param aroundStoreVillageList
	 * @param userId
	 * @return List
	 */
	public List<Village> findVillageNoAroundStoreByVillageId(List<AroundStoreVillage> aroundStoreVillageList,
			Long userId);

	/**
	 * 
	 * @Title
	 * @Description 查询本周边店所属的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午9:44:45
	 * @param aroundStoreVillageList
	 * @return List
	 */
	public List<Village> findVillageAroundStoreByVillageId(List<AroundStoreVillage> aroundStoreVillageList);

	/**
	 * 
	 * @Title
	 * @Description 根据经纬度查询范围内的小区
	 * @author:张洋
	 * @CreateDate:2016年8月1日14:58:38
	 * @param map
	 * @return List
	 */
	public List<Village> selectByLongAndLat(Map<String, String> map);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查到所对应的小区
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午5:10:05
	 * @param id
	 * @return String
	 */
	public String selectVillageByIntimateNewsId(Long id);

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id查到所对应的小区
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午5:10:05
	 * @param id
	 * @return String
	 */
	public String selectVillageByActivityCollectionId(Long id);

	/**
	 * @Title
	 * @Description 查询本网格推荐周边店还没有推荐的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:17:49
	 * @param aroundSuggestStoreVillageList
	 * @param userId
	 * @return List
	 */
	public List<Village> findVillageNoSuggest(List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList, Long userId);

	/**
	 * @Title
	 * @Description 查询本网格推荐周边店已经推荐的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午1:18:47
	 * @param aroundSuggestStoreVillageList
	 * @return List
	 */
	public List<Village> findVillageHasSuggest(List<AroundSuggestStoreVillage> aroundSuggestStoreVillageList);

	/**
	 * 
	 * @Title 广告管理 新增时查询用户对应的小区
	 * @Description
	 * @author:QZG
	 * @CreateDate:2016年8月5日 上午9:48:51
	 * @param userId
	 * @return List
	 */
	public List<Village> selectAll(Long userId);

	/**
	 * 
	 * @Title
	 * @Description 根据区Id查询拥有小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月17日 下午4:28:43
	 * @param countyId
	 * @return List
	 */
	public List<Village> queryVillageByCountryId(Long countyId);

	/**
	 * 
	 * @Title
	 * @Description
	 * @author:YK
	 * @CreateDate:2016年9月5日 上午10:07:40
	 * @param searchVillage
	 * @return Long
	 */
	public Long findEffectVillage(VillageVo searchVillage);
	
	/**
	 * 
	 * @Title
	 * @Description 根据用户id查找对应的列表
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午12:33:26
	 * @param villageVo
	 * @return List
	 */
	public List<Map<String,Object>> queryVillageList(VillageVo villageVo);
    /**
     * 
     * @Title
     * @Description 根据多个小区id查找所有小区，排除禁用和删除的
     * @author:张洋
     * @CreateDate:2016年9月21日14:28:21
     * @param parm
     * @return
     */
    public List<Village> selectVillageByIds(Map<String,Object> parm);
	
	/**
	 * 
	 * @Title
	 * @Description 常驻小区查询
	 * @author:YK
	 * @CreateDate:2016年9月21日 下午2:12:44
	 * @param villageVo
	 * @return VillageVo
	 */
	public VillageVo queryByUserIdAndVillageType(VillageVo villageVo);
    /**
     * 
     * @Title
     * @Description 常驻小区查询
     * @author:张洋
     * @CreateDate:2016年9月20日 下午1:57:35
     * @param villageVo
     * @return Village
     */
    public Village selectByUserIdAndVillageType(VillageVo villageVo);
}
