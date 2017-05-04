package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.vo.UserAreaVo;

/**
 * 
* @Title
* @Description 用户负责区域服务类
* @version 1.0.0
* @Author 张洋
* @CreateDate 2016年7月22日14:37:03
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 张洋	2016年7月22日14:37:11 		创建
*==========================================================
*
 */
public interface UserAreaService  <T extends Serializable> extends CrudService<T>{
	
	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户负责区域列表
	 * @author:这样
	 * @CreateDate:2016年7月22日14:38:06
	 * @param userAreaVo
	 * @return List
	 */
	public List<UserArea> selectList(UserAreaVo userAreaVo);
	
	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户负责区域列表数据的数量
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:38:06
	 * @param userAreaVo
	 * @return Long
	 */
	public Long selectListCount(UserAreaVo userAreaVo);
	
	/**
	 * 
	 * @Title 插入用户负责新区域
	 * @Description 插入用户负责新区域
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:38:58
	 * @param userArea
	 * @return int
	 */
	public int insert(UserArea userArea);
	/**
	 * 
	 * @Title 根据ID更新数据
	 * @Description 根据ID更新数据
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:39:48
	 * @param userArea
	 * @return int
	 */
	public int updateById(UserArea userArea);
	/**
	 * 
	 * @Title 根据ID返回数据
	 * @Description 根据ID返回数据
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:40:15
	 * @param id
	 * @return UserArea
	 */
	public UserArea selectById(Long id);
	
	/**
	 * 
	 * @Title 批量插入
	 * @Description 批量插入用户区域关系表
	 * @author:张洋
	 * @CreateDate:2016年7月25日15:47:07
	 * @param list
	 */
	public void insertList(List<UserArea> list);
	
	/**
	 * 
	 * @Title 查询
	 * @Description 查询传入的区域ID是否有已被分配的区域
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:35:04
	 * @param areaIds
	 * @return Long
	 */
	public Long selectIsManagerCount(String areaIds);
    
    /**
     * 
     * @Title
     * @Description 条件查询，查询列表，关联查询区域和用户表
     * @author:张洋
     * @CreateDate:2016年9月20日13:35:51
     * @param param
     * @return list
     */
    public List<UserAreaVo> selectListAndAreaUser(Map<String, Object> param);
    /**
     * 
     * @Title 查询
     * @Description 条件查询，查询数量，关联查询区域和用户表
     * @author:张洋
     * @CreateDate:2016年9月20日13:35:51
     * @param param
     * @return total
     */
    public Long selectCountAndAreaUser(Map<String, Object> param);
    /**
     * 
     * @Title
     * @Description 根据用户id查出所有管理的小区
     * @author:张洋
     * @CreateDate:2016年9月21日14:16:23
     * @param param
     * @return list
     */
    public List<Map<String, Object>> selectVillageByUserId(Map<String, Object> param);
    /**
     * 
     * @Title
     * @Description 根据用户id查出所有管理的社区
     * @author:张洋
     * @CreateDate:2016年9月21日14:16:23
     * @param param
     * @return list
     */
    public List<Map<String, Object>> selectCommunityByUserId(Map<String, Object> param);
    /**
     * 
     * @Title
     * @Description 根据用户id查出所有管理的县区
     * @author:张洋
     * @CreateDate:2016年9月21日14:16:23
     * @param param
     * @return list
     */
    public List<Map<String, Object>> selectCountyByUserId(Map<String, Object> param);
    /**
     * 
     * @Title
     * @Description 根据用户id查出所有管理的市
     * @author:张洋
     * @CreateDate:2016年9月21日14:16:23
     * @param param
     * @return list
     */
    public List<Map<String, Object>> selectCityByUserId(Map<String, Object> param);
    /**
     * 
     * @Title
     * @Description 根据用户id查出所有管理的省
     * @author:张洋
     * @CreateDate:2016年9月21日14:16:23
     * @param param
     * @return list
     */
    public List<Map<String, Object>> selectProvinceByUserId(Map<String, Object> param);
}
