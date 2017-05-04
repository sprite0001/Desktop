package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.vo.MessageVo;

public interface SendMessageMapper<T extends Serializable> extends CrudMapper<T> {
	public List<MessageVo> queryMessageByParams(MessageVo searchMessage);

	public Long queryCountByParams(MessageVo searchMessage);

	/**
	 * 
	 * @Title
	 * @Description 根据消息id查询出消息
	 * @author:王肖东
	 * @CreateDate:2016年8月22日 上午10:22:19
	 * @param id
	 * @return
	 */
	public MessageVo queryMessageById(Long id);
}