package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.vo.QuartersVo;
import com.wooxun.geekdol.system.vo.VillageVo;

public interface VillageMapper<T extends Serializable> extends CrudMapper<T> {

	public List<Village> queryVillageByParams(VillageVo searchVillage);

	public Long queryCountByParams(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 根据社区的id和新添加的小区名字判断是不是同一社区下面有相同的小区
	 * @author:王肖东
	 * @CreateDate:2016年7月20日 上午9:30:46
	 * @param findVillageByParams
	 * @return List<Village>
	 */
	public List<Village> findVillageByParams(Village village);

	/**
	 * 
	 * @Title
	 * @Description 根据社区/街道/办事处 ID和社区名查询所有未分配的可用的小区
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
	 * @Title 小区查询
	 * @Description 根据用户id查找分配给此用户的所有数据
	 * @author:张洋
	 * @CreateDate:2016年7月25日17:11:49
	 * @return List集合
	 */
	public List<Village> findByUserId(Map<String, Object> parmMap);

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
	 * @Description 小区查询信息列表
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午4:43:58
	 * @param searchVillage
	 * @return
	 */
	public List<Village> queryQuartersByParams(QuartersVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 小区查询信息列表总数
	 * @author:YK
	 * @CreateDate:2016年7月21日 下午4:44:24
	 * @param searchVillage
	 * @return
	 */
	public Long queryQuartersCountByParams(QuartersVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 根据社区查询下面的所有小区,不分页
	 * @author:YK
	 * @CreateDate:2016年7月23日 下午3:49:13
	 * @param searchVillage
	 * @return List
	 */
	public List<Village> queryVillageByCommuity(VillageVo searchVillage);

	/**
	 * 
	 * @Title
	 * @Description 合作店铺新增时候，查询没有合作店铺管辖的小区
	 * @author:YK
	 * @CreateDate:2016年7月28日 上午9:55:13
	 * @param searchVillage
	 * @return
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
	 * @Description 查询周边店所不属于的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午9:11:19
	 * @param param
	 * @return
	 */
	public List<Village> selectVillageNoAroundStoreByVillageId(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 查询周边店所属于的小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午9:45:50
	 * @param param
	 * @return
	 */
	public List<Village> selectVillageAroundStoreByVillageId(Map<String, Object> param);

	/**
	 * 
	 * @Title
	 * @Description 根据不同的级别查询所属小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午10:21:39
	 * @param villageVo
	 * @return
	 */
	public Village selectVillageByDiffentLevel(VillageVo villageVo);

	/**
	 * 
	 * @Title
	 * @Description 根据用户查询所属小区
	 * @author:kangtianyu
	 * @CreateDate:2016年7月30日 上午11:12:35
	 * @param villageTempList
	 * @return
	 */
	public List<Village> selectVillageNoAroundStoreByUser(List<Village> villageTempList);

	/**
	 * 
	 * @Title
	 * @Description 根据经纬度查询范围内的小区
	 * @author:张洋
	 * @CreateDate:2016年8月1日14:58:38
	 * @param map
	 * @return
	 */
	public List<Village> selectByLongAndLat(Map<String, String> map);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查到对应的小区
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午5:11:56
	 * @param id
	 * @return
	 */
	public String selectVillageByIntimateNewsId(Long id);

	/**
	 * 
	 * @Title
	 * @Description 根据活动汇id查到对应的小区
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 下午5:11:56
	 * @param id
	 * @return
	 */
	public String selectVillageByActivityCollectionId(Long id);


	/**
	 * 
	 * @Title 广告管理 新增时查询用户对应的小区
	 * @Description
	 * @author:QZG
	 * @CreateDate:2016年8月5日 上午9:48:51
	 * @param userId
	 * @return
	 */
	public List<Village> selectAll(Long userId);

	/**
	 * 
	 * @Title
	 * @Description 根据区id查找小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月17日 下午4:31:34
	 * @param countyId
	 * @return
	 */
	public List<Village> selectVillageByCountyId(Long countyId);

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
	 * @Description 常驻小区查询
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午1:57:35
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