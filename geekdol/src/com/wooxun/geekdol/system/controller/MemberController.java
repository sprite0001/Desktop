package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.HttpRequestUtils;
import com.wooxun.geekdol.common.MD5Util;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.system.model.AppUser;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.AppUserService;
import com.wooxun.geekdol.system.service.MemberService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.AppUserVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月20日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. QZG 2016年7月20日 下午2:26:34 创建
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService<AppUser> memberService;
	@Autowired
	private SyslogService<Syslog> syslogService;

	// 跳转到会员管理列表页面
	private static final String LIST = "system/member/list";

	@Autowired
	private AppUserService<AppUser> appUserService;

	/**
	 * 
	 * @Title
	 * @Description 跳转到列表页面
	 * @author:QZG
	 * @CreateDate:2016年7月20日 下午2:30:14
	 * @return
	 */
	@RequiresPermissions("AppUser:view")
	@RequestMapping("list")
	public String list() {
		// 跳转到列表页面
		return LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 根据条件查询会员信息
	 * @author:QZG
	 * @CreateDate:2016年7月20日 下午4:38:42
	 * @param appUserVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AppUser:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(AppUserVo appUserVo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 设置页码 初始化为1
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request
				.getParameter("page"));
		// 设置每页查询条数 初始化为20
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request
				.getParameter("rows"));
		// 创建map变量用于封装返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建会员列表对象
		List<AppUserVo> appUserList = new ArrayList<AppUserVo>();
		// 初始化会员列表查询数量
		Long count = 0l;
		// 如果昵称查询不为空 则进行表情加密
		if (StringUtils.isNotBlank(appUserVo.getNickName())) {
			appUserVo.setNickName(UnescapeUtil.escape(appUserVo.getNickName()));
		}
		// 设置分页标示为true
		appUserVo.setPageFlag(true);
		// 设置查询起始记录
		appUserVo.setStartPage((page - 1) * rows);
		// 设置查询结束记录
		appUserVo.setEndPage(rows);
		// 查询满足条件的会员列表数量
		count = memberService.findAllCount(appUserVo);
		// 当列表数量大于0
		if (count > 0) {
			// 查询满足查询条件的会员列表
			appUserList = memberService.findAll(appUserVo);
		}
		// 转义表情
		for (AppUserVo incrv : appUserList) {
			incrv.setNickName(UnescapeUtil.unescape(incrv.getNickName()));
		}
		// 封装会员查询列表
		map.put("rows", appUserList);
		// 封装会员列表查询数量
		map.put("total", count);
		// 创建变量
		ObjectMapper o = new ObjectMapper();
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 逻辑删除会员信息
	 * @author:QZG
	 * @CreateDate:2016年8月22日 上午11:27:17
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AppUser:manager")
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String delete(AppUser appUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "会员" };
		// 创建boolean变量 默认为false
		boolean result = false;
		// 根据会员id查询会员信息
		AppUser original = memberService.get(appUser.getId());
		// 当会员信息为空时
		if (original == null) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 先 删除中原网用户
		JSONObject js = new JSONObject();
		js.put("uid", original.getUid());
		js.put("flag", MD5Util.MD5XIAOXIE(original.getUid() + ConstantStr.XQT_KEY));
		JSONObject jsonObject = HttpRequestUtils.httpPost(ConstantStr.Hbridge_DELETE, js);
		int codeResult = (int) jsonObject.get("code"); // 返回的code值
		// 中原网删除失败(codeResult=0 删除成功 codeResult=1 删除失败)
		if (1 == codeResult) {
			int errorCode = (int) jsonObject.get("error"); // 返回的error值
			if (errorCode == -1) {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200130));
			} else if (errorCode == -2) {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200131));
			} else if (errorCode == -3) {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200132));
			} else if (errorCode == -4) {
				// 证明不是通过调用中原网注册接口注册的用户（可能为中原网的用户）
				// 设置更新回数
				appUser.setUpdEac(original.getUpdEac());
				// 修改共通字段属性
				this.editAttr(appUser);
				result = memberService.deleteAppUser(appUser);
				// 当会员信息删除成功
				if (result) {
					// 封装返回信息
					map.put("type", "Success");
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
					// 添加日志信息
					syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
							ConstantStr.APPUSER, appUser.getId(), getUser());
					return o.writeValueAsString(map);
				} else {
					// 封装返回信息
					map.put("type", "Error");
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
					return o.writeValueAsString(map);
				}
			} else if (errorCode == -5) {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO200134));
			}
			return o.writeValueAsString(map);
		} else {
			// 中原网删除成功 然后 删除本地用户(直接物理删除)
			result = appUserService.deleteAppUserByUid2(original.getUid());
			if (result) {
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
				syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
						ConstantStr.APPUSER, appUser.getId(), getUser());
			} else {
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));
			}
			return o.writeValueAsString(map);
		}

	}

	/**
	 * 
	 * @Title
	 * @Description 会员启用功能
	 * @author:QZG
	 * @CreateDate:2016年7月20日 下午4:39:34
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AppUser:manager")
	@RequestMapping(value = "/start", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String start(AppUser appUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "会员" };
		// 创建boolean变量 默认为false
		boolean result = false;
		// 根据会员id查询会员信息
		AppUser original = memberService.get(appUser.getId());
		// 当会员信息为空时
		if (original == null) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 已经为启用标示不能再启用
		if (ConstantStr.QY_FLAG.equals(original.getStatus())) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100013, arr));
		} else {
			// 设置更新回数
			appUser.setUpdEac(original.getUpdEac());
			// 修改共通字段属性
			this.editAttr(appUser);
			// 启用会员 返回值为true 启用成功
			result = memberService.start(appUser);
			// 当会员启用成功
			if (result) {
				// 封装返回信息
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100007, arr));
				// 添加日志信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100007, arr),
						ConstantStr.APPUSER, appUser.getId(), getUser());
			} else {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100008, arr));
			}
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 会员禁用功能
	 * @author:QZG
	 * @CreateDate:2016年7月20日 上午10:57:53
	 * @param appUser
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequiresPermissions("AppUser:manager")
	@RequestMapping(value = "/stop", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String stop(AppUser appUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "会员" };
		// 创建boolean变量 默认为false
		boolean result = false;
		// 根据会员id查询会员信息
		AppUser original = memberService.get(appUser.getId());
		// 当会员信息为空时
		if (original == null) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 已经为禁用标示不能再禁用
		if (ConstantStr.JY_FLAG.equals(original.getStatus())) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100014, arr));
		} else {
			// 设置更新回数
			appUser.setUpdEac(original.getUpdEac());
			// 修改共通字段属性
			this.editAttr(appUser);
			// 会员禁用 禁用成功 返回true
			result = memberService.stop(appUser);
			// 会员禁用成功
			if (result) {
				// 封装返回信息
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100009, arr));
				// 添加日志信息
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100009, arr),
						ConstantStr.APPUSER, appUser.getId(), getUser());
			} else {
				// 封装返回信息
				map.put("type", "Error");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100010, arr));
			}
		}
		// 返回信息
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 重置密码
	 * @author:QZG
	 * @CreateDate:2016年7月21日 上午9:19:14
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("AppUser:manager")
	@RequestMapping(value = "/restPwd", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String restPwd(AppUser appUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 创建map变量用于封装信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建变量用于返回信息
		ObjectMapper o = new ObjectMapper();
		// 创建对象用于信息提示
		Object[] arr = { "会员" };
		// 根据会员id查询会员信息
		AppUser original = memberService.get(appUser.getId());
		// 当会员信息为空时
		if (original == null) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100034, arr));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 当密码已为默认密码123456时
		if (MD5Util.MD5(ConstantStr.DEFAULT_PSW).equals(original.getPwd())) {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100038));
			// 返回信息
			return o.writeValueAsString(map);
		}
		// 设置更新回数
		appUser.setUpdEac(original.getUpdEac());
		// 修改共同属性
		this.editAttr(appUser);
		// 默认密码:123456
		appUser.setPwd(MD5Util.MD5(ConstantStr.DEFAULT_PSW));
		// 修改会员信息 返回值大于0 修改成功
		int result = memberService.updateSelective(appUser);
		// 当会员信息修改成功时
		if (result > 0) {
			// 封装返回信息
			map.put("type", "Success");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100011, arr));
			// 添加日志信息
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100011, arr),
					ConstantStr.APPUSER, appUser.getId(), getUser());
		} else {
			// 封装返回信息
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100012, arr));
		}
		// 返回信息
		return o.writeValueAsString(map);

	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-QZG
	 * @throws Exception
	 */
	private void editAttr(AppUser _temp) throws Exception {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
}
