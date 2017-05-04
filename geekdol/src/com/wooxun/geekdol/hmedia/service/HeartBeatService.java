package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.vo.HeartBeatVo;

/**
 * 
 * @Title 随心拍的service
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年9月13日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. zhangyang 2016年9月13日 下午1:44:28 创建
 *           ==========================================================
 *
 */
public interface HeartBeatService<T extends Serializable> extends
		CrudService<T> {
	/**
	 * 
	 * @Title
	 * @Description 条件查询
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatVo
	 * @return 列表
	 */
	public List<HeartBeat> queryListByParam(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联查询app用户的手机号，用户被删除则不查出
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatVo
	 * @return 列表
	 */
	public List<HeartBeatVo> queryListAndUser(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联查询app用户的手机号和小区id，用户或小区被删除则不查出，查出省下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:42:39
	 * @param heartBeatVo
	 * @return 列表
	 */
	public List<HeartBeatVo> queryListAndUserPro(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联查询app用户的手机号和小区id，用户或小区被删除则不查出，查出市下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:42:39
	 * @param heartBeatVo
	 * @return 列表
	 */
	public List<HeartBeatVo> queryListAndUserCity(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联查询app用户的手机号和小区id，用户或小区被删除则不查出，查出区县下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:42:39
	 * @param heartBeatVo
	 * @return 列表
	 */
	public List<HeartBeatVo> queryListAndUserCounty(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询，关联查询app用户的手机号和小区id，用户或小区被删除则不查出，查出社区下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:42:39
	 * @param heartBeatVo
	 * @return 列表
	 */
	public List<HeartBeatVo> queryListAndUserCommunity(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatVo
	 * @return 总数量
	 */
	public Long queryCountByParam(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询总数量，关联查询app用户的手机号，用户被删除则不查出
	 * @author:张洋
	 * @CreateDate:2016年9月13日15:10:54
	 * @param heartBeatVo
	 * @return 总数量
	 */
	public Long queryCountAndUser(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询总数量，关联查询app用户的手机号和小区id，用户或小区被删除则不查出 ，查出省下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:44:42
	 * @param heartBeatVo
	 * @return 总数量
	 */
	public Long queryCountAndUserPro(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询总数量，关联查询app用户的手机号和小区id，用户或小区被删除则不查出 ，查出市下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:44:42
	 * @param heartBeatVo
	 * @return 总数量
	 */
	public Long queryCountAndUserCity(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询总数量，关联查询app用户的手机号和小区id，用户或小区被删除则不查出 ，查出区县下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:44:42
	 * @param heartBeatVo
	 * @return 总数量
	 */
	public Long queryCountAndUserCounty(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 条件查询总数量，关联查询app用户的手机号和小区id，用户或小区被删除则不查出 ，查出社区下所有小区的数据
	 * @author:张洋
	 * @CreateDate:2016年9月14日15:44:42
	 * @param heartBeatVo
	 * @return 总数量
	 */
	public Long queryCountAndUserCommunity(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 手机app获取随心拍列表
	 * @author:YK
	 * @CreateDate:2016年9月19日 上午9:48:06
	 * @param heartBeatVo
	 * @return List
	 */
	public List<Map<String, Object>> queryHeartBeatList(HeartBeatVo heartBeatVo);

	/**
	 * 
	 * @Title
	 * @Description 发布随心拍
	 * @author:YK
	 * @CreateDate:2016年9月20日 上午10:23:51
	 * @param heartBeat
	 * @return boolean
	 */
	public boolean saveHeartBeat(HeartBeat heartBeat);
	
	/**
	 * 
	 * @Title
	 * @Description 根据id查找随心拍数据
	 * @author:YK
	 * @CreateDate:2016年9月23日 下午1:59:44
	 * @param id
	 * @return HeartBeat
	 */
	public HeartBeat selectById(Long id);
	
	/**
	 * 
	 * @Title
	 * @Description 更新随心拍
	 * @author:YK
	 * @CreateDate:2016年9月23日 下午2:18:52
	 * @param heartBeat
	 * @return boolean
	 */
	public boolean updateHeartBeat(HeartBeat heartBeat);
}
