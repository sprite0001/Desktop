package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.vo.CommunityVo;

public interface CommunityMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description:后台查询社区列表
	 * @author:YK
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchCommunity
	 * @return List
	 */
    public List<Community> queryCommunityByParams(CommunityVo searchCommunity);
    /**
	 * 
	 * @Title
	 * @Description:后台查询社区条数
	 * @author:YK
	 * @CreateDate:2016年7月18日 上午11:51:37
	 * @param searchCommunity
	 * @return Long
	 */
    public Long queryCountByParams(CommunityVo searchCommunity);
    
    /**
     * 
     * @Title
     * @Description 根据区县id查找下的所有社区
     * @author:YK
     * @CreateDate:2016年7月23日 下午3:57:00
     * @param searchCommunity
     * @return List
     */
    public List<Community> queryCommunityByCountry(CommunityVo searchCommunity);
    
    /**
     * 
     * @Title
     * @Description 查找可用的社区数量
     * @author:QZG
     * @CreateDate:2016年8月10日 上午10:19:03
     * @param communityVo
     * @return Long
     */
    public Long queryCount(CommunityVo communityVo);
    /**
     * 
     * @Title
     * @Description 根据县区ID查询所有未分配的可用的社区/街道/办事处
     * @author:张洋
     * @CreateDate:2016年7月22日16:44:03
     * @param countyId
     * @return List<Community>
     */
    public List<Community> selectByCountyIdNoUser(Map<String, Object> parmMap);
    /**
     * 
     * @Title
     * @Description 根据县区ID查询所有社区/街道/办事处
     * @author:张洋
     * @CreateDate:2016年8月16日16:28:47
     * @param parmMap
     * @return List<Community>
     */
    public List<Community> selectByCountyId(Map<String, Object> parmMap);
    /**
	 * 
	 * @Title 市查询
	 * @Description 根据用户id查找分配给此用户的所有数据
	 * @author:张洋
	 * @CreateDate:2016年7月25日17:11:49
	 * @param parmMap
	 * @return List集合
	 */
    public List<Community> findByUserId(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 根据省市区id查找区下的所有社区的的条数
     * @author:QZG
     * @CreateDate:2016年7月22日 下午4:09:08
     * @param community
     * @return Long
     */
    public Long findCount(Community community);
    
    /**
     * 
     * @Title
     * @Description 校验同一行政区下是否有相同的code或相同名称的社区
     * @author:YK
     * @CreateDate:2016年9月18日 下午3:19:28
     * @param communityVo
     * @return Long
     */
    public Long querySameCountByParams(CommunityVo communityVo);
    /**
     * 
     * @Title
     * @Description 根据多个小区id查找所有小区,排除删除和禁用的
     * @author:张洋
     * @CreateDate:2016年9月21日15:18:22
     * @param parmMap
     * @return List<Community>
     */
    public List<Community> selectCommunityByIds(Map<String, Object> parmMap);
    
    /**
     * 
     * @Title
     * @Description 根据行政区id与状态查找数据
     * @author:YK
     * @CreateDate:2016年9月27日 上午11:11:53
     * @param params
     * @return Community
     */
    public Community haEffectCommunity(Map<String, Object> params);
}