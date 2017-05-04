package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.SendMessage;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.vo.UserVo;

public interface UserMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * 
	 * @Title 查询
	 * @Description 根据用户名称查询数据
	 * @author:zhougp
	 * @CreateDate:2016年7月20日 上午10:01:42
	 * @param userName
	 * @return
	 */
	public User getByUserName(String userName);
    /**
     * 
     * @Title 查询
     * @Description 根据用户名称查询未被删除的用户
     * @author:张洋
     * @CreateDate:2016年8月31日16:44:31
     * @param userName
     * @return
     */
    public User getByUserNameAll(String userName);

    /**
     * 
     * @Title 查询
     * @Description 根据手机号查询未被删除的用户
     * @author:张洋
     * @CreateDate:2016年9月5日15:49:31
     * @param mobile
     * @return
     */
    public List<User> getByMobileAll(String mobile);
	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户列表
	 * @author:zhougp
	 * @CreateDate:2016年7月20日 上午10:02:08
	 * @return
	 */
	public List<User> selectList(UserVo userVo);

	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户列表，关联的角色可以为空
	 * @author:张洋
	 * @CreateDate:2016年8月9日10:12:30
	 * @return
	 */
	public List<User> selectListNoRole(UserVo userVo);

	/**
	 * 
	 * @Title 查询
	 * @Description 根据页面参数查询用户列表计数，分页
	 * @author:zhougp
	 * @CreateDate:2016年7月20日 上午10:03:06
	 * @return Long
	 */
	public Long selectListCount(UserVo userVo);

	/**
	 * 
	 * @Title 查询
	 * @Description 根据信息的条件查询出发送给哪些用户
	 * @author:王肖东
	 * @CreateDate:2016年7月23日 下午5:29:37
	 * @param message
	 * @return List<User>
	 */
	public List<User> selectListByMessage(SendMessage message);

	/**
	 * 
	 * @Title
	 * @Description 根据用户类型查找用户
	 * @author:YK
	 * @CreateDate:2016年7月30日 上午9:53:52
	 * @param type
	 * @return
	 */
	public List<User> selectUserByUserType(String type);

	/**
	 * 
	 * @Title
	 * @Description 插入新数据，并返回ID
	 * @author:张洋
	 * @CreateDate:2016年8月10日17:05:18
	 * @param user
	 * @return
	 */
	public int insertBackId(User user);
	
	/**
	 * 
	 * @Title
	 * @Description 根据合作店id查找店家的推送id
	 * @author:YK
	 * @CreateDate:2016年8月20日 下午5:48:47
	 * @param list
	 * @return List
	 */
	public List<String> selectByPushId(List<Long> list);
    
    /**
     * 
     * @Title
     * @Description 根据ID返回用户对象，直接返回user对象，不进行关联查询
     * @author:张洋
     * @CreateDate:2016年8月30日17:38:33
     * @param id
     * @return User
     */
    public User selectById(Long id);
    
    /**
	 * 
	 * @Title
	 * @Description 店主用户列表查询
	 * @author:YK
	 * @CreateDate:2016年9月1日 上午10:14:30
	 * @param params
	 * @return List
	 */
	public List<User> selectShopkeeperByUserType(Map<String,Object> params);
}