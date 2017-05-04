package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.City;
import com.wooxun.geekdol.system.model.SendMessage;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.CityService;
import com.wooxun.geekdol.system.service.MessageService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.MessageVo;

/**
 * @Title
 * @Description 给店长和网格长发消息控制器
 * @version 1.0.0
 * @Author 王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified ========================================================== No 修改人员
 *           修改日期 描述 1. 王肖东 2016年7月18日 上午10:10:35 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("message")
public class MessageController extends BaseController {

	@Autowired
	private MessageService<SendMessage> messageService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	@Autowired
	private CityService<City> cityService;
	private static final String LIST = "system/message/messageList";
	private static final String ADD = "system/message/messageAdd";
	private static final String UPDATE = "system/message/messageEditor";

	/**
	 * 
	 * @Title
	 * @Description 发送信息查询
	 * @author:王肖东
	 * @CreateDate:2016年7月23日 上午11:00:47
	 * @return
	 */
	@RequestMapping("list")
	public String list() {
		return LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 给网格张或店主发信息修改
	 * @author:王肖东
	 * @CreateDate:2016年8月3日 下午4:23:57
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/toEditMessage/{id}/{provinceId}/{cityId}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String toEdit(@PathVariable Long id, @PathVariable Long provinceId, @PathVariable Long cityId,
			Map<String, Object> map) {
		map.put("id", id);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		return UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据信息id查到信息
	 * @author:王肖东
	 * @CreateDate:2016年7月24日 下午11:29:44
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageVo message = messageService.selectMessageTwo(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(message);
	}

	/**
	 * 
	 * @Title
	 * @Description 发信息查询
	 * @author:王肖东
	 * @CreateDate:2016年7月23日 上午11:06:48
	 * @param provinceQueryVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findAll")
	public @ResponseBody String findAll(MessageVo messageQueryVo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<MessageVo> messageList = new ArrayList<MessageVo>();
		Long count = 0l;
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		messageQueryVo.setPageFlag(true);
		messageQueryVo.setStartPage((page - 1) * rows);
		messageQueryVo.setEndPage(rows);

		count = messageService.queryCountByParams(messageQueryVo);

		if (count > 0) {
			messageList = messageService.queryMessageByParams(messageQueryVo);
		}

		map.put("rows", messageList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到发送信息页面
	 * @author:王肖东
	 * @CreateDate:2016年7月23日 下午1:50:06
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
	public String toAdd() {
		return ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 修改发送的信息
	 * @author:王肖东
	 * @CreateDate:2016年7月24日 下午11:23:44
	 * @param village
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String update(SendMessage message, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "消息" };
		ObjectMapper o = new ObjectMapper();
		boolean result = false;
		SendMessage original = messageService.selectMessage(message.getId());
		message.setUpdEac(original.getUpdEac());
		this.editAttr(message);
		// 未发送
		message.setSendStatus("0");
		result = messageService.updateMessage(message);
		if (result) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
					ConstantStr.MANAGERMESSAGE, message.getId(), getUser());
		} else {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));
		}

		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 新增发送信息
	 * @author:王肖东
	 * @CreateDate:2016年7月23日 下午2:01:21
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveMessage", method = { RequestMethod.POST })
	public @ResponseBody String saveMessage(SendMessage message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "消息" };
		ObjectMapper mapper = new ObjectMapper();
		map.put("type", "Error");
		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));
		this.addAttr(message);
		// 未发送
		message.setSendStatus("0");
		int res = messageService.save(message);
		if (res > 0) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
		}
		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 发送信息
	 * @author:王肖东
	 * @CreateDate:2016年7月23日 下午2:01:21
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sendMessage", method = { RequestMethod.POST })
	public @ResponseBody String sendMessage(SendMessage message, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("type", "Error");
		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100040));
		if (message.getId() != null) {
			SendMessage original = messageService.selectMessage(message.getId());
			message.setUpdEac(original.getUpdEac());
			this.editAttr(message);
		} else {
			this.addAttr(message);
		}
		map = messageService.sendMessage(message, map);
		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 删除发送过的消息
	 * @author:王肖东
	 * @CreateDate:2016年7月24日 下午10:56:13
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMessage", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteRole(SendMessage message, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "消息" };
		map.put("type", "Error");
		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));

		boolean result = false;
		editAttr(message);
		result = messageService.updateMessage(message);

		if (result) {
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.MANAGERMESSAGE, message.getId(), getUser());
		}
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);

	}

	/**
	 * 添加用户共通属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void addAttr(SendMessage _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(SendMessage _temp) {
		SendMessage original = messageService.selectMessage(_temp.getId());
		Long userId = getUserId();
		_temp.setUpdEac(original.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
