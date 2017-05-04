package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.soft863.dolphin.model.UserRole;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.system.mapper.UserMapper;
import com.wooxun.geekdol.system.mapper.UserRoleMapper;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.service.UserService;
import com.wooxun.geekdol.system.vo.UserVo;

@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService<User> {

	private UserMapper<User> userMapper;
	private UserRoleMapper<UserRole> userRoleMapper;

	@Autowired
	public UserServiceImpl(UserMapper<User> userMapper, UserRoleMapper<UserRole> userRoleMapper) {
		super(userMapper);
		this.userMapper = userMapper;
		this.userRoleMapper = userRoleMapper;
	}

	@Override
	public User getByUserName(String userName) {

		return userMapper.getByUserName(userName);
	}

	@Override
	public List<User> selectList(UserVo userVo) {
		return userMapper.selectList(userVo);
	}

	@Override
	public Long selectListCount(UserVo userVo) {
		return userMapper.selectListCount(userVo);
	}

	@Override
	public int insertListCount(User user) {
		int count = userMapper.insertBackId(user);
		if (count > 0 && user.getRoleId() != null) {
			UserRole ur = new UserRole();
			ur.setUserId(user.getId());
			ur.setRoleId(user.getRoleId());
			userRoleMapper.insert(ur);
		}
		return count;
	}

	@Override
	public User selectByUserId(Long userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		List<UserRole> urLs = userRoleMapper.selectRoleIdByUserId(userId);
		if (urLs.size() > 0) {
			user.setRoleId(urLs.get(0).getRoleId());
		}
		return user;
	}

	@Override
	public int updateById(User user) {
		int count = userMapper.updateByPrimaryKeySelective(user);
		if (count > 0 && user.getRoleId() != null) {
			User u = userMapper.selectById(user.getId());
			UserRole oldUR = new UserRole();
			List<UserRole> list = userRoleMapper.selectRoleIdByUserId(user.getId());
			if (list.size() > 0) {
				oldUR = list.get(0);
				userRoleMapper.delete(oldUR);
			}
			if (user.getDelFlag() == null || user.getDelFlag().equals(ConstantStr.DELETE_N)) {
				UserRole newUR = new UserRole();
				newUR.setUserId(user.getId());
				newUR.setRoleId(user.getRoleId());
				userRoleMapper.insert(newUR);
			}
		}
		return count;
	}

	@Override
	public List<User> selectUserByUserType(String type) {
		return userMapper.selectUserByUserType(type);
	}
	
	@Override
	public List<User> selectShopkeeperByUserType(String type,Long storeId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", type);
		if(storeId != null && storeId != 0){
			params.put("storeId", storeId);
		}
		return userMapper.selectShopkeeperByUserType(params);
	}
	
	@Override
	public List<User> selectListNoRole(UserVo userVo) {
		return userMapper.selectListNoRole(userVo);
	}

	@Override
	public List<String> selectByPushId(String storeIds) {
		String storeId[] = storeIds.split(",");
		List<Long> storeIdList = new ArrayList<Long>();
		for (String id : storeId) {
			storeIdList.add(Long.valueOf(id));
		}
		return userMapper.selectByPushId(storeIdList);
	}

	@Override
	public User getByUserNameAll(String userName) {
		return userMapper.getByUserNameAll(userName);
	}

	@Override
	public User queryUserByUserId(Long userId) {

		return userMapper.selectById(userId);
	}

    @Override
    public List<User> getByMobileAll(String mobile) {
        return userMapper.getByMobileAll(mobile);
    }

}
