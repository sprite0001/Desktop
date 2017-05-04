package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.mapper.CommunityMapper;
import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.service.CommunityService;
import com.wooxun.geekdol.system.vo.CommunityVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK     	2016年7月18日  上午10:17:48 		创建
 *==========================================================
 * 
 */
@Service
public class CommunityServiceImpl extends CrudServiceImpl<Community> implements CommunityService<Community> {
	
	private CommunityMapper<Community> communityMapper;
	
	@Autowired
	public CommunityServiceImpl(CommunityMapper<Community> communityMapper) {
		super(communityMapper);
		this.communityMapper = communityMapper;
	}

	@Override
	public void insertCommunity(Community community) {
		communityMapper.insert(community);
	}

	@Override
	public Community selectCommunity(Long id) {
		return communityMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateCommunity(Community community) {
		int i = communityMapper.updateByPrimaryKeySelective(community);
		return i>0?true:false;
	}

	@Override
	public List<CommunityVo> queryCommunityByParams(CommunityVo searchCommunity) {
		// 查询数据库
		List<Community> communityList = communityMapper.queryCommunityByParams(searchCommunity);
		// 返回组装后的列表数据
		return formateData(communityList);
	}
	
	@Override
	public List<CommunityVo> queryCommunityByCountry(CommunityVo searchCommunity) {
		// 查询数据库
		List<Community> communityList = communityMapper.queryCommunityByCountry(searchCommunity);
		// 返回组装后的列表数据
		return formateData(communityList);
	}
	
	@Override
	public Long queryCountByParams(CommunityVo searchCommunity) {
		return communityMapper.queryCountByParams(searchCommunity);
	}
	
    /**
     * 
     * @Title
     * @Description 根据省市区id查找区下的所有社区的的条数
     * @author:QZG
     * @CreateDate:2016年7月22日 下午4:15:50
     * @param community
     * @return
     */
    @Override
    public Long findCount(Community community) {
        return communityMapper.findCount(community);
    }

	@Override
	public List<Community> selectByCountyIdNoUser(Map<String, Object> parmMap) {
		return communityMapper.selectByCountyIdNoUser(parmMap);
	}

	@Override
	public List<Community> findByUserId(Map<String, Object> parmMap) {
		return communityMapper.findByUserId(parmMap);
	}

    @Override
    public Long queryCount(CommunityVo communityVo) {
        return communityMapper.queryCount(communityVo);
    }

    @Override
    public List<Community> selectByCountyId(Map<String, Object> parmMap) {
        return communityMapper.selectByCountyId(parmMap);
    }
    
    /**
	 * 
	 * @Title
	 * @Description 列表数据组装
	 * @author:YK
	 * @CreateDate:2016年7月18日 下午2:47:00
	 * @param communityList
	 * @return
	 */
	private List<CommunityVo> formateData(List<Community> communityList){
		List<CommunityVo> result = new ArrayList<CommunityVo>();
		for(Community community : communityList){
			CommunityVo communityVo = new CommunityVo();
			BeanUtils.copyProperties(community, communityVo);
			communityVo.setProvinceName(community.getProvince().getProvinceName());
			communityVo.setCityName(community.getCity().getCityName());
			communityVo.setCountyName(community.getCounty().getCountyName());
			result.add(communityVo);
		}
		return result;
	}

	@Override
	public Long querySameCountByParams(CommunityVo communityVo) {
		return communityMapper.querySameCountByParams(communityVo);
	}

    @Override
    public List<Community> selectCommunityByIds(Map<String, Object> parmMap) {
        return communityMapper.selectCommunityByIds(parmMap);
    }

	@Override
	public Community haEffectCommunity(Long communityId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("communityId", communityId);
		params.put("status", ConstantStr.QY_FLAG);
		return communityMapper.haEffectCommunity(params);
	}

}
