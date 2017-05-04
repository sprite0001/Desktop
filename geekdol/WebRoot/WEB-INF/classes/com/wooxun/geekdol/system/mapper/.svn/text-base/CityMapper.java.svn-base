package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.vo.CityVo;


public interface CityMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据页面查询条件，显示列表
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午9:28:54
	 * @param cityVo
	 * @return List集合
	 */
	public List<City> findAll(CityVo cityVo);
    
	
	/**
	 * 
	 * @Title 市查询
	 * @Description 根据页面查询条件，查找所有条数
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 下午9:28:54
	 * @param cityVo
	 * @return Long
	 */
    public Long findAllCount(CityVo cityVo); 
    
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
  	 * @Description 根据省份id查找市列表
  	 * @author:田振兴
  	 * @CreateDate:2016年7月18日 下午5:30:27
  	 * @return List集合
  	 */
      public List<City> findByCityProviceId(Long provinceId);
      
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
     * @CreateDate:2016年7月23日 下午5:16:40
     * @param provinceId
     * @return List
     */
    public List<City> queryCityByProvice(Long provinceId);
    
    /**
	 * 
	 * @Title 市查询
	 * @Description 根据省份id查找省下的所有未被分配的可用的市
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午5:30:27
	 * @return List集合
	 */
    public List<City> findCityByProviceIdNoUser(Long provinceId);
    
    /**
     * 
     * @Title 市查询
     * @Description  根据省份id查找关联市的条数
     * @author:田振兴
     * @CreateDate:2016年7月22日 上午9:32:56
     * @param city
     * @return 返回long类型
     */
    public Long findBycityProvinceIdCount(City city);
    
    /**
     * 
     * @Title 市查询(验证)
     * @Description	根据省ID和(市名称或市编码)来验证是否已经存在
     * @author:田振兴
     * @CreateDate:2016年7月20日 上午9:51:30
     * @param city
     * @return 返回为0时为不存在，大于0时为存在
     */
    public Long selectCityCount(City city);
    
    /**
     * 
     * @Title
     * @Description 初始化所有市
     * @author:YK
     * @CreateDate:2016年7月25日 下午4:25:35
     * @return
     */
    public List<City> findAllCity(); 

    /**
     * 
     * @Title 市查询
     * @Description 根据条件查找市列表-带分页
     * @author:张洋
     * @CreateDate:2016年8月24日11:20:57
     * @param city
     * @return
     */
    public List<City> selectCity(CityVo city);
    
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
	 * @Description 根据省id判断是否启用
	 * @author:YK
	 * @CreateDate:2016年9月27日 上午10:18:23
	 * @param params
	 * @return City
	 */
	public City haEffectCity(Map<String,Object> params);
	
}