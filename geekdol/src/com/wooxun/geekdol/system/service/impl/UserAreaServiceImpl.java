package com.wooxun.geekdol.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.system.mapper.UserAreaMapper;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.service.UserAreaService;
import com.wooxun.geekdol.system.vo.UserAreaVo;

@Service
public class UserAreaServiceImpl extends CrudServiceImpl<UserArea> implements UserAreaService<UserArea>{

	private UserAreaMapper<UserArea> userAreaMapper;
	
	@Autowired
	public UserAreaServiceImpl(UserAreaMapper<UserArea> userAreaMapper) {
		super(userAreaMapper);
		this.userAreaMapper = userAreaMapper;
	}

	@Override
	public List<UserArea> selectList(UserAreaVo userAreaVo) {
		return userAreaMapper.selectList(userAreaVo);
	}

	@Override
	public Long selectListCount(UserAreaVo userAreaVo) {
		return userAreaMapper.selectListCount(userAreaVo);
	}

	@Override
	public int insert(UserArea userArea) {
		return userAreaMapper.insert(userArea);
	}

	@Override
	public UserArea selectById(Long id) {
		return userAreaMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(UserArea userArea) {
		return userAreaMapper.updateByPrimaryKeySelective(userArea);
	}

	@Override
	public void insertList(List<UserArea> list) {
	    userAreaMapper.deleteList(list);
		userAreaMapper.insertList(list);
	}

	@Override
	public Long selectIsManagerCount(String areaIds) {
		return userAreaMapper.selectIsManagerCount(areaIds);
	}

    @Override
    public List<UserAreaVo> selectListAndAreaUser(Map<String, Object> param) {
        return userAreaMapper.selectListAndAreaUser(param);
    }

    @Override
    public Long selectCountAndAreaUser(Map<String, Object> param) {
        return userAreaMapper.selectCountAndAreaUser(param);
    }

    @Override
    public List<Map<String, Object>> selectVillageByUserId(
            Map<String, Object> param) {
        return userAreaMapper.selectVillageByUserId(param);
    }

    @Override
    public List<Map<String, Object>> selectCommunityByUserId(
            Map<String, Object> param) {
        return userAreaMapper.selectCommunityByUserId(param);
    }

    @Override
    public List<Map<String, Object>> selectCountyByUserId(
            Map<String, Object> param) {
        return userAreaMapper.selectCountyByUserId(param);
    }

    @Override
    public List<Map<String, Object>> selectCityByUserId(
            Map<String, Object> param) {
        return userAreaMapper.selectCityByUserId(param);
    }

    @Override
    public List<Map<String, Object>> selectProvinceByUserId(
            Map<String, Object> param) {
        return userAreaMapper.selectProvinceByUserId(param);
    }

	
}
