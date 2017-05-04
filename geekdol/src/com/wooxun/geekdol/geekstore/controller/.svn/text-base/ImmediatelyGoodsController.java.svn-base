package com.wooxun.geekdol.geekstore.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.common.controller.BaseController;
import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendVillage;
import com.wooxun.geekdol.geekstore.service.DistributionGoodsService;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendService;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsVo;
import com.wooxun.geekdol.system.model.Syslog;
import com.wooxun.geekdol.system.service.SyslogService;

/**
 * @Title
 * @Description 即可送管理
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月11日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月11日  上午9:15:55 		创建
 *==========================================================
 * 
 */
@Controller
@RequestMapping("immediately")
public class ImmediatelyGoodsController extends BaseController {
	//private static final Logger LOG = LoggerFactory.getLogger(ImmediatelyGoodsController.class);
	public static final String GOODS = "geekstore/immediately/list";
	public static final String GOODS_RECOMMEND = "geekstore/immediately/goodsRecommend";
	@Autowired
	private DistributionGoodsService<DistributionGoods> distributionGoodsService;
	@Autowired
	private GoodsRecommendService<GoodsRecommend> goodsRecommendService;
	@Autowired
	private SyslogService<Syslog> syslogService;
	
	/**
	 * 
	 * @Title
	 * @Description 列表页面初始化
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:32:31
	 * @return String
	 */
	@RequiresPermissions("Immediately:view")
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String timeout() {
		return GOODS;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 列表数据查找
	 * @author:YK
	 * @CreateDate:2016年8月11日 上午9:32:44
	 * @param goodsVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Immediately:view")
	@RequestMapping("/findAll")
	public @ResponseBody String findAll(DistributionGoodsVo distributionGoodsVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		List<DistributionGoodsVo> goodsVoList = new ArrayList<DistributionGoodsVo>();
		int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "20" : request.getParameter("rows"));
		Long count = 0l;
		
		distributionGoodsVo.setPageFlag(true);
		distributionGoodsVo.setStartPage((page - 1) * rows);
		distributionGoodsVo.setEndPage(rows);
		// 设置商品状态在数据字典中的type类型
		distributionGoodsVo.setStatusType(ConstantStr.GOODSSTATUS);
		// 设置铺货模块为即可送
		distributionGoodsVo.setClassificCode(ConstantStr.JKS);
		
		count = distributionGoodsService.queryCountByParmas(distributionGoodsVo);
		goodsVoList = distributionGoodsService.queryByParmas(distributionGoodsVo);
		
		map.put("rows", goodsVoList); 
		map.put("total", count);

		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 初始化推荐页面
	 * @author:YK
	 * @CreateDate:2016年8月16日 下午4:38:31
	 * @param goodsId
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequiresPermissions("Immediately:manager")
	@RequestMapping(value = "/recomendGoods/{goodsId}", method = { RequestMethod.GET })
	public String recomendGoods(@PathVariable Long goodsId,Map<String,Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		map.put("goodsId", goodsId);
		return GOODS_RECOMMEND;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 产品推荐
	 * @author:YK
	 * @CreateDate:2016年8月17日 上午9:41:56
	 * @param goodsRecommend
	 * @param villageId
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Immediately:manager")
	@RequestMapping(value = "/saveRecommentGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String saveRecommentGoods(GoodsRecommend goodsRecommend,@RequestParam String villageId,
			@RequestParam("file") MultipartFile[] files,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"推荐产品"};
		// 处理推荐产品与小区关系
		String villageIds[] = {};
		if(StringUtils.isNotBlank(villageId)){
			villageIds = villageId.split(",");
		}
		// 验证是否选择推荐小区
		if(villageIds.length<=0){
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100093));
			return o.writeValueAsString(map);
		}
		List<GoodsRecommendVillage> goodsRecommendVillages = new ArrayList<GoodsRecommendVillage>();
		for(int i=0;i<villageIds.length;i++){
			GoodsRecommendVillage goodsRecommendVillage = new GoodsRecommendVillage();
			goodsRecommendVillage.setVillageId(Long.valueOf(villageIds[i]));
			goodsRecommendVillages.add(goodsRecommendVillage);
		}
		goodsRecommend.setGoodsRecommendVillages(goodsRecommendVillages);
		// 上传文件设置标题图片url
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE,ConstantStr.MODEL_GEEKSTORE);
		String newFileName = "";
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if(file.getSize()<=0){
				goodsRecommend.setTitlePicture("");
				continue;
			}
			newFileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if(rs){
				newFileName = httppath+newFileName;
				goodsRecommend.setTitlePicture(newFileName);
			}
		}
		
		// 设置推荐信息公共属性并保存
		addAttr(goodsRecommend);
		boolean bl = goodsRecommendService.saveGoodsRecommed(goodsRecommend);
		if(bl){
			syslogService.addLog(ConstantStr.INSERT, ComDefine.getMsg(ConstantStr.INFO100001,arr), ConstantStr.J_GOODSRECOMMEND, goodsRecommend.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100001,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100002,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 撤销推荐
	 * @author:YK
	 * @CreateDate:2016年8月17日 上午10:03:55
	 * @param goodsRecommend
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("Immediately:manager")
	@RequestMapping(value = "/offRecomendGoods", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String offRecomendGoods(GoodsRecommend goodsRecommend, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper o = new ObjectMapper();
		Object[] arr={"撤销推荐"};
		// 查找销售中的商品进行撤销(下架)
		goodsRecommend.setStatus(ConstantStr.GOODSSTATUS_03);
		List<GoodsRecommend> list = goodsRecommendService.queryByParams(goodsRecommend);
		for(GoodsRecommend originalGoodsRecommend:list){
			originalGoodsRecommend.setStatus(ConstantStr.GOODSSTATUS_04);
			editAttr(originalGoodsRecommend);
		}
		boolean bl = goodsRecommendService.updateBatch(list);
		if(bl){
			syslogService.addLog(ConstantStr.UPDATE, ComDefine.getMsg(ConstantStr.INFO100053,arr), ConstantStr.J_GOODSRECOMMEND, goodsRecommend.getId(), getUser());
			map.put("type", "Success");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100053,arr));
		}else{
			map.put("type", "Error");
			map.put("Msg", ComDefine.getMsg(ConstantStr.INFO100054,arr));
		}
		return o.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 公共属性新增
	 * @author:YK
	 * @CreateDate:2016年8月8日 下午3:49:25
	 * @param _temp
	 */
	private void addAttr(GoodsRecommend _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改共同属性
	 * @author:YK
	 * @CreateDate:2016年8月4日 下午5:21:53
	 * @param _temp
	 */
	private void editAttr(GoodsRecommend _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
	/**
	 * 
	 * @Title
	 * @Description 时间提交转换
	 * @author:YK
	 * @CreateDate:2016年8月11日 下午2:58:11
	 * @param binder
	 */
	@InitBinder  
    private void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
	
}