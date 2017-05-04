package com.wooxun.geekdol.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooxun.geekdol.geekstore.model.CooperationStore;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;
import com.wooxun.geekdol.geekstore.model.GrouponGoods;
import com.wooxun.geekdol.geekstore.service.CooperationStoreService;
import com.wooxun.geekdol.geekstore.service.GoodsRecommendService;
import com.wooxun.geekdol.geekstore.service.GrouponGoodsService;
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.service.AdvertService;
import com.wooxun.geekdol.hmedia.vo.AdvertVo;
import com.wooxun.geekdol.system.vo.AppLunboVo;

/**
 * @Title
 * @Description 吉克店部分--手机app接口
 * @version 1.0.0
 * @Author zhougp
 * @CreateDate 2016年7月23日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. zhougp 2016年7月23日 下午1:57:23 创建
 *           ==========================================================
 * 
 */

@Controller
@RequestMapping("geekstore")
public class AppGeekstoreController {

	@Autowired
	private GoodsRecommendService<GoodsRecommend> goodsRecommendService;

	@Autowired
	private GrouponGoodsService<GrouponGoods> GrouponGoodsService;

	@Autowired
	private CooperationStoreService<CooperationStore> cooperationStoreService;

	@Autowired
	private AdvertService<Advert> advertService;

	@RequestMapping("index")
	public @ResponseBody String goodsrecommed() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> product = new HashMap<String, Object>();
		try {

			map.put("resultCode", 200);
			// 推荐
			List<GoodsRecommend> recommendList = new ArrayList<GoodsRecommend>();// goodsRecommendService.selectRecommed();
			product.put("产品推荐", recommendList);

			// 团购
			List<GrouponGoods> grouponList = new ArrayList<GrouponGoods>();
			// List<GrouponGoods> grouponList =
			// GrouponGoodsService.selectGroupon();
			product.put("小区团", grouponList);

			// 合作店
			List<CooperationStore> cooperationList = cooperationStoreService.selectStore();

			product.put("合作店", cooperationList);
			AppLunboVo appLunboVo = new AppLunboVo();

			// 广告图片url列表
			List<Advert> advertList = advertService.selectPic(appLunboVo);
			List<AdvertVo> advertVoList = new ArrayList<AdvertVo>();
			for (Advert advert : advertList) {
				AdvertVo advertVo = new AdvertVo();
				advertVo.setAdverPic(advert.getAdverPic());
				advertVoList.add(advertVo);
			}
			product.put("广告图片url列表", advertVoList);

			// 广告id 内容
			List<Advert> list = advertService.selectPic(appLunboVo);
			List<AdvertVo> listVo = new ArrayList<AdvertVo>();
			for (Advert advert : list) {
				AdvertVo advertVo = new AdvertVo();
				advertVo.setId(advert.getId());
				advertVo.setAdverContent(advert.getAdverContent());
				listVo.add(advertVo);
			}
			product.put("广告id内容", listVo);

			map.put("data", product);

			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 500);
			map.put("msg", "系统异常！" + e.getMessage());
			return JSONObject.fromObject(map).toString();
		}
	}

}
