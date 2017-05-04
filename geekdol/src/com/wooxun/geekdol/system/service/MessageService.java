package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.SendMessage;
import com.wooxun.geekdol.system.vo.MessageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 发送业务类 ========================================================== No
 *           修改人员 修改日期 描述 1. 发送 2016年7月18日 上午10:07:49 创建
 *           ==========================================================
 * 
 */
public interface MessageService<T extends Serializable> extends CrudService<T> {

	/**
	 * 
	 * @Title
	 * @Description: 新增 发送信息
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:24:16
	 * @param message
	 */
	public void insertMessage(SendMessage message);

	/**
	 * @Title
	 * @Description 根据信息id查出信息
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:26:05
	 * @param id
	 * @return
	 */
	public SendMessage selectMessage(Long id);

	/**
	 * @Title
	 * @Description 根据信息id查出信息
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:26:05
	 * @param id
	 * @return
	 */
	public MessageVo selectMessageTwo(Long id);

	/**
	 * 
	 * @Title
	 * @Description 更新、删除信息
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 下午7:44:22
	 * @param message
	 * @return true:更新成功;false:更新失败
	 */
	public boolean updateMessage(SendMessage message);

	/**
	 * 
	 * @Title
	 * @Description:后台查询信息列表
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchVillage
	 * @return
	 */
	public List<MessageVo> queryMessageByParams(MessageVo searchMessage);

	/**
	 * 
	 * @Title
	 * @Description:后台查询信息列表总数
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午11:51:08
	 * @param searchVillage
	 * @return
	 */
	public Long queryCountByParams(MessageVo searchMessage);

	/**
	 * 
	 * @Title
	 * @Description: 发送信息
	 * @author:王肖东
	 * @CreateDate:2016年7月18日 上午10:24:16
	 * @param message
	 * @param map
	 */
	public Map<String, Object> sendMessage(SendMessage message, Map<String, Object> map);

}
