package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

public interface CountyMapper <T extends Serializable> extends CrudMapper<T>{
    
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:35:55
     * @return
     */
    public List<County> findAll(CountyQueryVo countyQueryVo);
    
    /**
     * 
     * @Title
     * @Description 根据市ID查询所有未被分配的可用的县区
     * @author:张洋
     * @CreateDate:2016年7月22日16:44:03
     * @return
     */
    public List<County> selectByCityIdNoUser(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 根据市ID查询所有县区
     * @author:张洋
     * @CreateDate:2016年8月16日16:26:23
     * @return
     */
    public List<County> selectByCityId(Map<String, Object> parmMap);
    
    /**
	 * 
	 * @Title 市查询
	 * @Description 根据用户id查找分配给此用户的所有数据
	 * @author:张洋
	 * @CreateDate:2016年7月25日17:11:49
	 * @return List集合
	 */
    public List<County> findByUserId(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表计数
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:36:27
     * @return
     */
    public Long findAllCount(CountyQueryVo countyQueryVo);
    
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表计数
     * @author:张洋
     * @CreateDate:2016年9月18日12:14:06
     * @return
     */
    public Long selectCountByCityId(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 通过id逻辑删除行政区信息
     * @author:QZG
     * @CreateDate:2016年7月18日 下午4:03:16
     * @param county
     */
    public int deleteCountyById(County county);
     
    /**
     * 
     * @Title
     * @Description 启用功能 返回值大于0启用成功
     * @author:QZG
     * @CreateDate:2016年7月20日 上午10:47:28
     * @param county
     * @return
     */
    public int start(County county);
   
    /**
     * 
     * @Title
     * @Description
     * @author:禁用功能 返回值大于0禁用成功
     * @CreateDate:2016年7月20日 上午10:47:44
     * @param county
     * @return
     */
    public int stop(County county);
    
    /**
     * 
     * @Title
     * @Description 根据市ID查找所有的行政区的条数(返回long类型)
     * @author:田振兴
     * @CreateDate:2016年7月20日 下午2:03:57
     * @param county
     * @return
     */
    public Long findByCountyCityId(County county);
    /**
     * 
     * @Title
     * @Description 新增时查询市编码在数据库中存在的数量
     * @author:QZG
     * @CreateDate:2016年7月22日 下午3:12:44
     * @param county
     * @return
     */
    public Long findCodeCount(County county);
    
    /**
     * 
     * @Title
     * @Description 初始化市下的所有区县
     * @author:YK
     * @CreateDate:2016年7月23日 下午3:33:42
     * @param countyQueryVo
     * @return
     */
    public List<County> queryCountryByCity(CountyQueryVo countyQueryVo);
    
    /**
     * 
     * @Title
     * @Description 心媒体推荐功能 查询满足条件列表
     * @author:QZG
     * @CreateDate:2016年7月28日 下午4:33:31
     * @param countyQueryVo
     * @return
     */
    public List<County> findRecommend(CountyQueryVo countyQueryVo);
    /**
     * 
     * @Title
     * @Description 心媒体推荐功能  返回满足查询条件条数
     * @author:QZG
     * @CreateDate:2016年7月28日 下午4:34:15
     * @param countyQueryVo
     * @return
     */
    public Long  findRecommendCount(CountyQueryVo countyQueryVo);
    /**
     * 
     * @Title
     * @Description 心媒体推荐删除功能 返回值大于0 删除成功 
     * @author:QZG
     * @CreateDate:2016年7月29日 上午9:55:05
     * @param county
     * @return
     */
    public int deleteRecommend(County county);
    /**
     * 
     * @Title
     * @Description 新媒体推荐禁用功能  返回值大于0 禁用成功
     * @author:QZG
     * @CreateDate:2016年7月29日 上午11:22:01
     * @param county
     * @return
     */
    public int disableRecommend(County county);
    /**
     * 
     * @Title
     * @Description 心媒体推荐启用功能  返回值大于0 启用成功
     * @author:QZG
     * @CreateDate:2016年7月29日 上午11:22:47
     * @param county
     * @return
     */
    public int enableRecommend(County county);
    /**
     * 
     * @Title
     * @Description 根据多个县区id查找所有县区,排除删除和禁用的
     * @author:张洋
     * @CreateDate:2016年9月21日15:35:06
     * @return
     */
    public List<County> selectCountyByIds(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 根据id与状态查找对应的数据
     * @author:YK
     * @CreateDate:2016年9月27日 上午11:14:49
     * @param params
     * @return County
     */
    public County haEffectCounty(Map<String,Object> params);
    
}