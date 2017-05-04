package com.wooxun.geekdol.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.wooxun.geekdol.system.model.SysData;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SysDataService;
import com.wooxun.geekdol.system.service.SyslogService;
import com.wooxun.geekdol.system.vo.SysdataVo;

/**
 * @Title
 * @Description 字典控制类
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年7月18日
 * @param
 * @see
 * @modified 字典管理 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年7月18日 下午2:31:23 创建 2. 张洋
 *           2016年7月22日17:52:53 修改新增方法
 *           ==========================================================
 * 
 */
@Controller
@RequestMapping("sysdata")
public class SysDataController extends BaseController {

	@Autowired
	private SysDataService<SysData> sysdataService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	private static final String MAIN = "system/sysdata/sysdataMain";
	private static final String TREE = "system/sysdata/sysdataTree";
	private static final String LIST = "system/sysdata/sysdataList";
	private static final String ADD = "system/sysdata/sysdataAdd";

	public static final String UPDATE = "system/sysdata/sysdataEditor";

	/**
	 * 
	 * @Title
	 * @Description 跳转到字典管理主页面
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:38:59
	 * @return
	 */
	@RequiresPermissions("SysData:view")
	@RequestMapping("sysdataMain")
	public String sysdataMain() {
		return MAIN;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到树页面
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:39:10
	 * @return
	 */
	@RequestMapping("tree")
	public String tree() {
		return TREE;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到列表页面
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:39:46
	 * @return
	 */
	@RequiresPermissions("SysData:view")
	@RequestMapping("list")
	public String list() {
		return LIST;
	}

	/**
	 * 
	 * @Title
	 * @Description 跳转到新增页面
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:39:58
	 * @return
	 */
	@RequiresPermissions("SysData:manager")
	@RequestMapping("toAddSysdata")
	public String toAddSysdata() {
		return ADD;
	}

	/**
	 * 
	 * @Title
	 * @Description 修改页面跳转
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:40:09
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("SysData:manager")
	@RequestMapping(value = "/toEdit/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String toEdit(@PathVariable Long id, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		map.put("id", id);
		return UPDATE;
	}

	/**
	 * 
	 * @Title
	 * @Description 修改初始字典页面数据
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:40:24
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("SysData:view")
	@RequestMapping(value = "/findById/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysData sysData = sysdataService.selectSysData(id);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(sysData);
	}

	/**
	 * 
	 * @Title
	 * @Description 根据数据字典类型查询，方便取出特定类型的数据
	 * @author:张洋
	 * @CreateDate:2016年7月21日15:31:28
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectByType/{type}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String selectByType(@PathVariable String type, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysdataVo sysDataVo = new SysdataVo();
		sysDataVo.setType(type);
		List<SysData> list = sysdataService.querySysdataByBean(sysDataVo);
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(list);
	}

	/**
	 * 
	 * @Title
	 * @Description 查询出全部字典
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:40:44
	 * @param searchSysdata
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("SysData:view")
	@RequestMapping("findAll")
	public @ResponseBody String findAll(SysdataVo searchSysdata, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page") == null ? ConstantStr.PAGE : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? ConstantStr.ROWS : request.getParameter("rows"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<SysData> sysdataList = new ArrayList<SysData>();
		Long count = 0l;
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		searchSysdata.setPageFlag(true);
		searchSysdata.setStartPage((page - 1) * rows);
		searchSysdata.setEndPage(rows);

		count = sysdataService.queryCountByParams(searchSysdata);

		if (count > 0) {
			sysdataList = sysdataService.querySysdataByParams(searchSysdata);
		}

		map.put("rows", sysdataList);
		map.put("total", count);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 修改字典
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:41:20
	 * @param sysData
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("SysData:manager")
	@RequestMapping(value = "saveSysdata", method = { RequestMethod.POST })
	public @ResponseBody String saveSysdata(SysData sysData, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "字典" };
		ObjectMapper mapper = new ObjectMapper();
		map.put("type", "Error");
		map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100002, arr));

		// 同一个类别下 value值不能相等
		if (hasSysdata(sysData)) {
			map.put("type", "Error");
			map.put("msg", ComDefine.getMsg(ConstantStr.INFO100041));
		} else {
			this.addAttr(sysData);
			int res = sysdataService.save(sysData);
			if (res > 0) {
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100001, arr));
				syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001, arr),
						ConstantStr.SYSDATA, sysData.getId(), getUser());

			}
		}

		return mapper.writeValueAsString(map);
	}

	/**
	 * 
	 * @Title
	 * @Description 更改字典
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:41:44
	 * @param sysData
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("SysData:manager")
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String update(SysData sysData, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "字典" };
		map.put("type", "Error");
		map.put("msg", ComDefine.getMsg(ConstantStr.INFO100006, arr));

		boolean result = false;
		SysData orginal = sysdataService.get(sysData.getId());
		// 如果为原封不动的修改
		if (sysData.getType().equals(orginal.getType()) && sysData.getValue().equals(orginal.getValue())) {
			this.editAttr(sysData);
			result = sysdataService.updateSysdata(sysData);
			if (result) {
				map.put("type", "Success");
				map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
				syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
						ConstantStr.SYSDATA, sysData.getId(), getUser());
			}
		} else {
			if (!hasSysdata(sysData)) {
				this.editAttr(sysData);
				result = sysdataService.updateSysdata(sysData);
				if (result) {
					map.put("type", "Success");
					map.put("msg", ComDefine.getMsg(ConstantStr.INFO100005, arr));
					syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100005, arr),
							ConstantStr.SYSDATA, sysData.getId(), getUser());
				}
			}
		}

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);

	}

	/**
	 * 
	 * @Title
	 * @Description 删除字典
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:41:44
	 * @param sysData
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("SysData:manager")
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String delete(SysData sysData, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Object[] arr = { "字典" };
		map.put("type", "Error");
		map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100004, arr));

		boolean result = false;
		this.editAttr(sysData);
		result = sysdataService.updateSysdata(sysData);

		if (result) {
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100003, arr));
			syslogService.addLog(ConstantStr.DELETE, ComDefine.getMsg(ConstantStr.INFO100003, arr),
					ConstantStr.SYSDATA, sysData.getId(), getUser());
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
	private void addAttr(SysData _temp) {
		Long userId = getUserId();
		_temp.setStatus(ConstantStr.QY_FLAG);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag("0");
	}

	/**
	 * 修改共同属性
	 * 
	 * @param _temp
	 * @author:863SOFT-FZQ
	 */
	private void editAttr(SysData _temp) {
		SysData original = sysdataService.selectSysData(_temp.getId());
		Long userId = getUserId();
		_temp.setUpdEac(original.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	/**
	 * 
	 * @Title
	 * @Description 同一个类别下 value值不能相等
	 * @author:王肖东
	 * @CreateDate:2016年7月22日 下午4:42:10
	 * @param sysData
	 * @return
	 */
	private boolean hasSysdata(SysData sysData) {
		Long count = sysdataService.queryCountByYanzheng(sysData);
		return count > 0 ? true : false;
	}
}
