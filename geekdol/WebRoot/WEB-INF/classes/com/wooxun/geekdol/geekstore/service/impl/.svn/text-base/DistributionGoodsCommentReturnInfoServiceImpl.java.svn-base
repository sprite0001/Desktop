package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.DistributionGoodsCommentReturnInfoMapper;
import com.wooxun.geekdol.geekstore.model.DistributionGoodsCommentReturnInfo;
import com.wooxun.geekdol.geekstore.service.DistributionGoodsCommentReturnInfoService;
import com.wooxun.geekdol.geekstore.vo.DistributionGoodsCommentReturnInfoVo;

/**
 * @Title 吉客店管理-商品管理-评论管理
 * @Description 极客店回复信息
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年8月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年8月18日  下午4:13:47 		创建
 *==========================================================
 * 
 */
@Service
public class DistributionGoodsCommentReturnInfoServiceImpl extends CrudServiceImpl<DistributionGoodsCommentReturnInfo> 
	implements DistributionGoodsCommentReturnInfoService<DistributionGoodsCommentReturnInfo>{
	
	private DistributionGoodsCommentReturnInfoMapper<DistributionGoodsCommentReturnInfo> distributionGoodsCommentReturnInfoMapper;
	@Autowired
	public DistributionGoodsCommentReturnInfoServiceImpl(DistributionGoodsCommentReturnInfoMapper
			<DistributionGoodsCommentReturnInfo> distributionGoodsCommentReturnInfoMapper) {
		super(distributionGoodsCommentReturnInfoMapper);
		this.distributionGoodsCommentReturnInfoMapper = distributionGoodsCommentReturnInfoMapper;
	}
	
	@Override
	public List<DistributionGoodsCommentReturnInfoVo> findDistributionGoodsCommentReturnInfo(
			DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo) {
		return distributionGoodsCommentReturnInfoMapper.commentReturninfo(distributionGoodsCommentReturnInfoVo);
	}
	
	@Override
	public Long findReturnInfoCount(
			DistributionGoodsCommentReturnInfoVo distributionGoodsCommentReturnInfoVo) {
		return distributionGoodsCommentReturnInfoMapper.findReturnInfoCount(distributionGoodsCommentReturnInfoVo);
	}

	@Override
	public DistributionGoodsCommentReturnInfo findById(Long id) {
		return distributionGoodsCommentReturnInfoMapper.selectByPrimaryKey(id);
	}

}
