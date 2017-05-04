package com.wooxun.geekdol.geekstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsCommentMapper;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsExtendInforMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendCommentMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.mapper.GoodsRecommendMapper;
import com.wooxun.geekdol.geekstore.model.DistributionGoods;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsComment;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsCommentReturnInfo;
import com.wooxun.geekdol.geekstore.model.Goods;
import com.wooxun.geekdol.geekstore.model.GoodsExtendInfor;
import com.wooxun.geekdol.geekstore.model.GoodsRecommend;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendComment;
import com.wooxun.geekdol.geekstore.model.GoodsRecommendCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.GoodsService;
import com.wooxun.geekdol.geekstore.vo.GoodsVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年8月11日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年8月11日  上午9:48:15 		创建
 *==========================================================
 * 
 */
@Service
public class GoodsServiceImpl extends CrudServiceImpl<Goods> implements
		GoodsService<Goods> {
	
	private GoodsMapper<Goods> goodsMapper;
	@Autowired
	private AttachMapper<Attach> attachMapper;
	@Autowired
	private GoodsExtendInforMapper<GoodsExtendInfor> goodsExtendInforMapper;
	@Autowired
	private GoodsRecommendCommentMapper<GoodsRecommendComment> goodsRecommendCommentMapper;
	@Autowired
	private DistributionGoodsCommentMapper<DistributionGoodsComment> distributionGoodsCommentMapper;
	@Autowired
	private GoodsRecommendCommentReturnInfoMapper<GoodsRecommendCommentReturnInfo> goodsRecommendCommentReturnInfoMapper;
	@Autowired
	private DistributionGoodsCommentReturnInfoMapper<DistributionGoodsCommentReturnInfo> distributionGoodsCommentReturnInfoMapper;
	@Autowired
	private DistributionGoodsMapper<DistributionGoods> distributionGoodsMapper;
	@Autowired
	private GoodsRecommendMapper<GoodsRecommend> goodsRecommendMapper;
	@Autowired
	public GoodsServiceImpl(GoodsMapper<Goods> goodsMapper){
		super(goodsMapper);
		this.goodsMapper = goodsMapper;
	}
	@Override
	public Long queryGoodsCountByParams(GoodsVo goodsVo) {
		return goodsMapper.queryGoodsCountByParams(goodsVo);
	}
	@Override
	public List<GoodsVo> queryGoodsByParams(GoodsVo goodsVo) {
		return goodsMapper.queryGoodsByParams(goodsVo);
	}
	@Override
	public Goods findById(Long id) {
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		Attach attach = new Attach();
		attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
		attach.setOwnerId(id);
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_11);
		goods.setAttachs(attachMapper.selectAttachByParam(attach));
		return goods;
	}
	@Override
	public boolean deleteGoods(Goods goods) {
		int i = goodsMapper.updateByPrimaryKeySelective(goods);
		// 删除附件
		Attach attach = new Attach();
		attach.setOwnerId(goods.getId());
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_11);
		i = attachMapper.deleteByParam(attach);
		// 删除扩展属性
		i = goodsExtendInforMapper.deleteGoodsExtendInforByGoods(goods.getId());
		return i>0?true:false;
	}
	@Override
	public boolean insertGoods(Goods goods) {
		// 未发布状态设置
		goods.setStatus(ConstantStr.GOODSSTATUS_01);
		int i = goodsMapper.insertSelective(goods);
		int k = 0;
		//附件新增
		List<Attach> attachs = new ArrayList<Attach>();
		for(Attach attach: goods.getAttachs()){
			attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
			attach.setOwnerId(goods.getId());
			attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_11);
			attach.setOrdering(k);
			attach.setOpreator(goods.getInsId());
			attach.setInsId(goods.getInsId());
			attach.setInsYmdhms(goods.getInsYmdhms());
			attach.setUpdId(goods.getUpdId());
			attach.setUpdEac(goods.getUpdEac());
			attach.setUpdYmdhms(goods.getUpdYmdhms());
			attach.setDelFlag(ConstantStr.DELETE_N);
			attachs.add(attach);
			k++;
		}
		attachMapper.insertBatch(attachs);
		//扩展属性新增
		List<GoodsExtendInfor> grouponGoodsExtendInfos = new ArrayList<GoodsExtendInfor>();
		for(GoodsExtendInfor goodsExtendInfor :goods.getGoodsExtendInfors()){
			goodsExtendInfor.setGoodsId(goods.getId());
			goodsExtendInfor.setInsId(goods.getInsId());
			goodsExtendInfor.setInsYmdhms(goods.getInsYmdhms());
			goodsExtendInfor.setUpdId(goods.getUpdId());
			goodsExtendInfor.setUpdEac(goods.getUpdEac());
			goodsExtendInfor.setUpdYmdhms(goods.getUpdYmdhms());
			goodsExtendInfor.setDelFlag(ConstantStr.DELETE_N);
			grouponGoodsExtendInfos.add(goodsExtendInfor);
		}
		goodsExtendInforMapper.insertBatch(grouponGoodsExtendInfos);
		return i>0?true:false;
	}
	
	@Override
	public boolean updateGoods(Goods goods) {
		int i = goodsMapper.updateByPrimaryKeySelective(goods);
		if(i>0){
			int k = 0;
			//附件新增
			List<Attach> attachs = new ArrayList<Attach>();
			i = attachMapper.deleteByIdAndName(goods.getId(),ConstantStr.ATTACH_OWNERTBTYPE_11);
			for(Attach attach: goods.getAttachs()){
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(goods.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_11);
				attach.setOrdering(k);
				attach.setOpreator(goods.getInsId());
				attach.setInsId(goods.getInsId());
				attach.setInsYmdhms(goods.getInsYmdhms());
				attach.setUpdId(goods.getUpdId());
				attach.setUpdEac(goods.getUpdEac());
				attach.setUpdYmdhms(goods.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				attachs.add(attach);
				k++;
			}
			attachMapper.insertBatch(attachs);
			//扩展属性新增
			List<GoodsExtendInfor> grouponGoodsExtendInfos = new ArrayList<GoodsExtendInfor>();
			i = goodsExtendInforMapper.deleteGoodsExtendInforByGoods(goods.getId());
			for(GoodsExtendInfor goodsExtendInfor :goods.getGoodsExtendInfors()){
				goodsExtendInfor.setGoodsId(goods.getId());
				goodsExtendInfor.setInsId(goods.getInsId());
				goodsExtendInfor.setInsYmdhms(goods.getInsYmdhms());
				goodsExtendInfor.setUpdId(goods.getUpdId());
				goodsExtendInfor.setUpdEac(goods.getUpdEac());
				goodsExtendInfor.setUpdYmdhms(goods.getUpdYmdhms());
				goodsExtendInfor.setDelFlag(ConstantStr.DELETE_N);
				grouponGoodsExtendInfos.add(goodsExtendInfor);
			}
			goodsExtendInforMapper.insertBatch(grouponGoodsExtendInfos);
		}
		return i>0?true:false;
	}	
	
	
	@Override
	public List<GoodsVo> findSelectGoods(GoodsVo goodsVo) {
		return goodsMapper.findSelectGoods(goodsVo);
	}
	
	
	@Override
	public int rejectGoods(Goods goods) {
		// 更新商品主数据
		int i = goodsMapper.updateByPrimaryKeySelective(goods);
		// 下架非新产品模块的商品
		List<DistributionGoods> list = distributionGoodsMapper.selectByGoods(goods.getId());
		for(DistributionGoods distributionGoods:list){
			// 设置商品下架
			distributionGoods.setStatus(ConstantStr.GOODSSTATUS_04);
			distributionGoods.setUpdId(goods.getUpdId());
			distributionGoods.setUpdYmdhms(new Date());
			distributionGoods.setUpdEac(distributionGoods.getUpdEac()+1);
		}
		if(list!=null && list.size()>0){
			distributionGoodsMapper.updateBatch(list);
		}
		// 下架新商品信息
		List<GoodsRecommend> goodsRecommendList = goodsRecommendMapper.selectByGoodsId(goods.getId());
		for(GoodsRecommend goodsRecommend:goodsRecommendList){
			// 设置商品下架
			goodsRecommend.setStatus(ConstantStr.GOODSSTATUS_04);
			goodsRecommend.setUpdId(goods.getUpdId());
			goodsRecommend.setUpdYmdhms(new Date());
			goodsRecommend.setUpdEac(goodsRecommend.getUpdEac()+1);
		}
		if(goodsRecommendList!=null && goodsRecommendList.size()>0){
			i = goodsRecommendMapper.updateBatch(goodsRecommendList);
		}
		return i;
	}
	@Override
	public boolean offGoods(Goods goods) {
		// 更新商品主数据
		int i = goodsMapper.updateByPrimaryKeySelective(goods);
		// 下架即可送模块的商品
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("goodsId", goods.getId());
		params.put("classicfic", ConstantStr.JKS);
		List<DistributionGoods> list = distributionGoodsMapper.selectByGoodsAndClassfic(params);
		for(DistributionGoods distributionGoods:list){
			// 设置商品下架
			distributionGoods.setStatus(ConstantStr.GOODSSTATUS_04);
			distributionGoods.setUpdId(goods.getUpdId());
			distributionGoods.setUpdYmdhms(new Date());
			distributionGoods.setUpdEac(distributionGoods.getUpdEac()+1);
		}
		if(list.size()>0){
			distributionGoodsMapper.updateBatch(list);
		}
		// 下架新商品信息
		List<GoodsRecommend> goodsRecommendList = goodsRecommendMapper.selectByGoodsId(goods.getId());
		for(GoodsRecommend goodsRecommend:goodsRecommendList){
			// 设置商品下架
			goodsRecommend.setStatus(ConstantStr.GOODSSTATUS_04);
			goodsRecommend.setUpdId(goods.getUpdId());
			goodsRecommend.setUpdYmdhms(new Date());
			goodsRecommend.setUpdEac(goodsRecommend.getUpdEac()+1);
		}
		if(goodsRecommendList.size()>0){
			i = goodsRecommendMapper.updateBatch(goodsRecommendList);
		}
		return i>0?true:false;
	}

}
