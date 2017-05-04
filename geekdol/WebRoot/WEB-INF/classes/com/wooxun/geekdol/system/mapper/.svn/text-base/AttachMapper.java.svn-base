package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Attach;

public interface AttachMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * @Title
	 * @Description 批量插入附件
	 * @author:王肖东
	 * @CreateDate:2016年8月4日 上午9:13:48
	 * @param list
	 */
	public int insertBatch(List<Attach> list);

	/**
	 * @Title
	 * @Description 批量插入附件
	 * @author:kangtianyu
	 * @CreateDate:2016年8月3日 下午9:20:16
	 * @param sqlParam
	 * @return int
	 */
	public int insertBatchByMap(Map<String, Object> sqlParam);

	/**
	 * 
	 * @Title
	 * @Description
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午12:47:56
	 * @param intimateNewsId
	 * @param type
	 * @return int
	 */
	public int deleteByIdAndName(Long intimateNewsId, String type);

	/**
	 * @Title
	 * @Description 根据相关条件删除附件信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月5日 上午12:49:09
	 * @param attachTemp
	 * @return int
	 */
	public int deleteByParam(Attach attachTemp);

	/**
	 * @Title
	 * @Description 根据相关信息查询附件信息
	 * @author:kangtianyu
	 * @CreateDate:2016年8月8日 上午11:23:27
	 * @param attach
	 * @return List
	 */
	public List<Attach> selectAttachByParam(Attach attach);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查到附件
	 * @author:王肖东
	 * @CreateDate:2016年8月20日 下午1:35:54
	 * @param intimateNewsId
	 * @return
	 */
	public List<Attach> selectAttachByIntimateNewsId(Long intimateNewsId);
	
	/**
	 * 
	 * @Title
	 * @Description 根据归属id查询附件
	 * @author:YK
	 * @CreateDate:2016年9月19日 上午10:22:39
	 * @param params
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getByOwnerIdAndOwnerTbType(Map<String,Object> params);

}