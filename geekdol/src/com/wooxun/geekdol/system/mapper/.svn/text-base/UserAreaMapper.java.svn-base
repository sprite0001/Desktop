package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.vo.UserAreaVo;
/**
 * 
* @Title
* @Description 用户负责区域mapper类
* @version 1.0.0
* @Author 张洋
* @CreateDate 2016年7月22日14:36:26
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 张洋	2016年7月22日14:36:34 		创建
*==========================================================
*
 */
public interface UserAreaMapper <T extends Serializable> extends CrudMapper<T>{
	
	
	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户负责区域列表
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:35:04
	 * @return
	 */
	public List<UserArea> selectList(UserAreaVo userAreaVo);
	
	/**
	 * 
	 * @Title 批量插入
	 * @Description 批量插入用户区域关系表
	 * @author:张洋
	 * @CreateDate:2016年7月25日15:47:07
	 * @return
	 */
	public void insertList(List<UserArea> list);
    /**
     * 
     * @Title 批量删除
     * @Description 批量删除用户区域关系表
     * @author:张洋
     * @CreateDate:2016年8月17日10:55:01
     * @return
     */
    public void deleteList(List<UserArea> list);
	
	/**
	 * 
	 * @Title 查询
	 * @Description 查询传入的区域ID是否有已被分配的区域
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:35:04
	 * @return
	 */
	public Long selectIsManagerCount(String areaIds);
	
	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户负责区域列表的数量
	 * @author:张洋
	 * @CreateDate:2016年7月22日14:35:04
	 * @return
	 */
	public Long selectListCount(UserAreaVo userAreaVo);
	
	/**
	 * 
	 * @Title
	 * @Description 社区电话管理   广告管理   新增时查询该用户下的所有小区
	 * @author:QZG
	 * @CreateDate:2016年8月1日 上午11:19:14
	 * @param userId
	 * @return
	 */
	public List<UserAreaVo> selectAll(Long userId);
	
	/**
	 * 
	 * @Title
	 * @Description 根据相关参数查询用户负责区域
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 上午12:25:37
	 * @param param
	 * @return
	 */
	public List<UserArea> selectUserAreaByParam(Map<String, Object> param);
    
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