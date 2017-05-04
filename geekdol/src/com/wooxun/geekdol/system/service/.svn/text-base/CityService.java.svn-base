package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.vo.CityVo;
/**
 * @Title 市管理
 * @Description 市管理里的一些操作(市查询，添加，修改，删除，启用，禁用)
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月18日  上午10:08:22 		创建
 *==========================================================
 * 
 */
public interface CityService <T extends Serializable> extends CrudService<T> {
	
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据页面条件查询，显示列表
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午2:20:46
	 * @param cityVo
	 * @return List集合
	 */
	public List<CityVo> findAll(CityVo cityVo);
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据用户id查找分配给此用户的所有数据
	 * @author:张洋
	 * @CreateDate:2016年7月25日17:11:49
	 * @return List集合
	 */
    public List<City> findByUserId(Map<String, Object> parmMap);
    
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据页面条件查询，查找列表条数
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午2:20:46
	 * @param cityVo
	 * @return Long
	 */
	public Long findAllCount(CityVo cityVo);
	
		
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据省份id查找市列表
	 * @author:YK
	 * @CreateDate:2016年7月18日 下午5:30:27
	 * @param cityVo
	 * @return List集合
	 */
	public List<City> findByCityProviceId(Long proviceId);
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据条件查找市列表
	 * @author:田振兴
	 * @CreateDate:2016年8月17日 下午2:53:16
	 * @param city
	 * @return
	 */
	public List<City> findCity(City city);
	
	/**
	 * 
	 * @Title
	 * @Description 公共Controller:根据省份查找省下的所有市  无分页
	 * @author:YK
	 * @CreateDate:2016年7月23日 下午5:17:44
	 * @param proviceId
	 * @return List
	 */
	public List<City> queryCityByProvice(Long provinceId);
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据省份id查找省下的所有未被分配的可用的市
	 * @author:张洋
	 * @CreateDate:2016年7月22日16:35:08
	 * @param cityVo
	 * @return List集合
	 */
	public List<City> findCityByProviceIdNoUser(Long proviceId);
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据省份id查找关联市的条数
	 * @author:田振兴
	 * @CreateDate:2016年7月22日 上午9:30:06
	 * @param city
	 * @return 返回long
	 */
	public Long findBycityProvinceIdCount(City city);
	
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据省ID和(市名称或市编码)来验证是否已经存在
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午9:36:21
	 * @param city
	 * @return 返回为0时为不存在，大于0时为存在
	 */
	public Long selectCityCount(City city);
	
	
	/**
	 * 
	 * @Title 市添加
	 * @Description 添加市的信息
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午9:53:01
	 * @param city
	 * @return int 返回0为添加失败，返回1为添加成功
	 */
	public int insertCity(City city);
	
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据市ID查询市的具体信息
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午10:53:01
	 * @param cityId
	 * @return City对象
	 */
	public City selectByid(Long cityId);
	
	
	/**
	 * 
	 * @Title 市修改
	 * @Description 编辑市管理的信息
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 上午9:57:20
	 * @param city
	 * @return int 返回0为修改失败，返回1为修改成功
	 */
	public int updateByid(City city);
	
	
	/**
	 * 
	 * @Title 市修改(逻辑删除和禁启用)
	 * @Description 删除市管理的信息和市禁用启用
	 * @author:田振兴
	 * @CreateDate:2016年7月20日 下午1:52:01
	 * @param city
	 * @return int 返回0为失败，返回1为成功
	 */
	public int delete(City city);
	
	/**
	 * 
	 * @Title
	 * @Description 初始化所有的市
	 * @author:YK
	 * @CreateDate:2016年7月25日 下午4:26:23
	 * @return
	 */
	public List<City> findAllCity();
    /**
     * 
     * @Title 市查询
     * @Description 根据条件查找市列表-带分页
     * @author:张洋
     * @CreateDate:2016年8月24日11:20:57
     * @param c
     * @return
     */
    public List<City> selectCity(CityVo c);
    /**
     * 
     * @Title 市查询
     * @Description 根据多个市id查找所有市,排除删除和禁用的
     * @author:张洋
     * @CreateDate:2016年9月21日15:45:08
     * @return List集合
     */
    public List<City> selectCityByIds(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 根据是id查找该市是否可用数据
     * @author:YK
     * @CreateDate:2016年9月27日 上午10:53:54
     * @param cityId City
     * @return
     */
    public City haEffectCity(Long cityId);
}