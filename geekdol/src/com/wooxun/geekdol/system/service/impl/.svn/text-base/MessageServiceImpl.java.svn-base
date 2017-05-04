package com.wooxun.geekdol.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.Jdpush;
import com.wooxun.geekdol.system.mapper.SendMessageMapper;
import com.wooxun.geekdol.system.mapper.UserMapper;
import com.wooxun.geekdol.system.model.SendMessage;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.service.MessageService;
import com.wooxun.geekdol.system.vo.MessageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified ========================================================== No 修改人员
 *           修改日期 描述 1. 王肖东 2016年7月18日 上午10:08:27 创建
 *           ==========================================================
 * 
 */

@Service
public class MessageServiceImpl extends CrudServiceImpl<SendMessage> implements MessageService<SendMessage> {

	@Autowired
	private UserMapper<User> userMapper;

	private SendMessageMapper<SendMessage> messageMapper;

	@Autowired
	public MessageServiceImpl(SendMessageMapper<SendMessage> messageMapper) {
		super(messageMapper);
		this.messageMapper = messageMapper;
	}

	@Override
	public void insertMessage(SendMessage message) {
		messageMapper.insert(message);
	}

	@Override
	public SendMessage selectMessage(Long id) {
		return messageMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateMessage(SendMessage message) {
		boolean result = false;
		int i = messageMapper.updateByPrimaryKeySelective(message);
		if (i > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<MessageVo> queryMessageByParams(MessageVo searchMessage) {
		return messageMapper.queryMessageByParams(searchMessage);
	}

	@Override
	public Long queryCountByParams(MessageVo searchVillage) {
		return messageMapper.queryCountByParams(searchVillage);
	}

	@Override
	public Map<String, Object> sendMessage(SendMessage message, Map<String, Object> map) {
		// 根据市区进行推送留作后期扩展

		// 设置推送结果
		boolean pushResult = false;
		// 判断推送类型
		if (ConstantStr.RECEIVERTYPE_01.equals(message.getReceiverType())) { // 如果是推送给店主
			// 向店主发送推送信息 (后期App制作完成后更改key)
			pushResult = Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
					ComDefine.getMsg(ConstantStr.APPKEY_USER), message);
		} else if (ConstantStr.RECEIVERTYPE_02.equals(message.getReceiverType())) { // 如果是推送给网格长
			// 向网格长发送推送信息(后期App制作完成后更改key)
			pushResult = Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
					ComDefine.getMsg(ConstantStr.APPKEY_USER), message);
		} else if (ConstantStr.RECEIVERTYPE_03.equals(message.getReceiverType())) { // 如果是推送给网格长和店长
			// 向店主发送推送信息(后期App制作完成后更改key)
			boolean pushResult_store = Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
					ComDefine.getMsg(ConstantStr.APPKEY_USER), message);
			// 向网格长发送推送信息(后期App制作完成后更改key)
			boolean pushResult_area = Jdpush.sendPush(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER),
					ComDefine.getMsg(ConstantStr.APPKEY_USER), message);
			if (pushResult_store && pushResult_area) {
				pushResult = true;
			}
		}

		if (pushResult) {
			// 设置推送结果
			message.setSendStatus(ConstantStr.SENEDSTATUS_1);
		} else {
			// 设置推送结果
			message.setSendStatus(ConstantStr.SENEDSTATUS_0);
		}

		// 保存信息到数据库
		int result;
		if (message.getId() != null) {
			// 如果为编辑中的发送
			result = messageMapper.updateByPrimaryKeySelective(message);
		} else {
			result = messageMapper.insert(message);
		}
		if (result > 0) { // 如果推送成功
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100039));
		} else { // 如果推送失败
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100040));
		}
		return map;
	}

	@Override
	public MessageVo selectMessageTwo(Long id) {

		return messageMapper.queryMessageById(id);
	}
}
