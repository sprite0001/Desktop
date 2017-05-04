package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.vo.UserVo;

/**
 * 
 * @Title
 * @Description 用户服务类
 * @version 1.0.0
 * @Author zhougp
 * @CreateDate 2016年7月20日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 万通 2016年7月20日 上午10:01:19 创建
 *           ==========================================================
 *
 */
public interface UserService<T extends Serializable> extends CrudService<T> {

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
	 * @Description 根据页面参数查询用户列表，关联的角色可以为空
	 * @author:张洋
	 * @CreateDate:2016年8月9日10:12:30
	 * @return
	 */
	public List<User> selectListNoRole(UserVo userVo);

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
	 * @Description 根据页面参数查询用户列表计数，分页
	 * @author:zhougp
	 * @CreateDate:2016年7月20日 上午10:03:06
	 * @return
	 */
	public Long selectListCount(UserVo userVo);

	/**
	 * 
	 * @Title 插入新用户并插入相关联的角色数据
	 * @Description 插入新用户并插入相关联的角色数据
	 * @author:张洋
	 * @CreateDate:2016年7月21日16:28:18
	 * @return
	 */
	public int insertListCount(User user);

	/**
	 * 
	 * @Title 根据ID更新数据
	 * @Description 根据ID更新数据
	 * @author:张洋
	 * @CreateDate:2016年7月21日18:25:17
	 * @return
	 */
	public int updateById(User user);

	/**
	 * 
	 * @Title 根据ID返回数据
	 * @Description 根据ID返回数据
	 * @author:张洋
	 * @CreateDate:2016年7月21日17:48:31
	 * @return
	 */
	public User selectByUserId(Long userId);

	/**
	 * 
	 * @Title
	 * @Description 合作店申请：根据用户类型查找用户(商家)
	 * @author:YK
	 * @CreateDate:2016年7月30日 上午9:51:34
	 * @param type
	 * @return
	 */
	public List<User> selectUserByUserType(String type);

	/**
	 * 
	 * @Title
	 * @Description 店主用户列表查询
	 * @author:YK
	 * @CreateDate:2016年9月1日 上午10:14:30
	 * @param type
	 * @param storeId
	 * @return List
	 */
	public List<User> selectShopkeeperByUserType(String type,Long storeId);

	/**
	 * 
	 * @Title
	 * @Description 根据合作店id查找店家的推送id
	 * @author:YK
	 * @CreateDate:2016年8月20日 下午5:48:47
	 * @param storeIds
	 * @return
	 */
	public List<String> selectByPushId(String storeIds);

	/**
	 * 
	 * @Title
	 * @Description 根据用户id查到用户 不进行关联查询
	 * @author:王肖东
	 * @CreateDate:2016年9月1日 上午10:19:05
	 * @param userId
	 * @return
	 */
	public User queryUserByUserId(Long userId);
}
