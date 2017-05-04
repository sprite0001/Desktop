package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月18日  下午1:02:07 		创建
 *==========================================================
 * 
 */
public interface CountyService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:40:17
     * @return
     */
    public List<CountyQueryVo> findAll(CountyQueryVo CountyQueryVo);

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
     * @CreateDate:2016年8月16日16:25:23
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
     * @Description 根据页面查询条件，返回满足查询条件条数
     * @author:QZG
     * @CreateDate:2016年7月18日 下午1:41:12
     * @return
     */
    public Long findAllCount(CountyQueryVo CountyQueryVo);
    
    /**
     * 
     * @Title
     * @Description 逻辑删除行政区信息 删除成功返回true
     * @author:QZG
     * @CreateDate:2016年7月18日 下午4:04:47
     * @param county
     */
    public boolean deleteCountyById(County county);
    
    /**
     * 
     * @Title
     * @Description 行政区启用功能 启用成功返回true
     * @author:QZG
     * @CreateDate:2016年7月20日 上午10:49:39
     * @param county
     * @return
     */
    public boolean start(County county);
    /**
     * 
     * @Title
     * @Description 行政区禁用功能 禁用成功返回true
     * @author:QZG
     * @CreateDate:2016年7月20日 上午10:50:03
     * @param county
     * @return
     */
    public boolean stop(County county);
    
    /**
     * 
     * @Title
     * @Description 根据市ID查找所有的行政区的条数
     * @author:田振兴
     * @CreateDate:2016年7月20日 下午2:05:49
     * @param county
     * @return
     */
    public Long findByCountyCityId(County county);
    /**
     * 
     * @Title
     * @Description 新增时查询区编码在数据库中的数量
     * @author:QZG
     * @CreateDate:2016年7月22日 下午3:15:09
     * @param county
     * @return
     */
    public Long findCodeCount(County county);
   
    /**
     * 
     * @Title
     * @Description 根据市id查询到该市下的所有区县
     * @author:YK
     * @CreateDate:2016年7月23日 下午3:30:10
     * @param CountyQueryVo
     * @return List
     */
    public List<CountyQueryVo> queryCountryByCity(CountyQueryVo CountyQueryVo);
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
     * @Description 根据行政区id与状态查找数据
     * @author:YK
     * @CreateDate:2016年9月27日 上午11:11:53
     * @param countyId
     * @return County
     */
    public County haEffectCounty(Long countyId);
}
