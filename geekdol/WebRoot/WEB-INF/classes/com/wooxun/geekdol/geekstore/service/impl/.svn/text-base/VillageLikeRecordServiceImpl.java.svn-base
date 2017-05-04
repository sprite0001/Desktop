package com.wooxun.geekdol.geekstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.geekstore.mapper.VillageLikeRecordMapper;
import com.wooxun.geekdol.geekstore.model.VillageLikeRecord;
import com.wooxun.geekdol.geekstore.service.VillageLikeRecordService;
import com.wooxun.geekdol.geekstore.vo.VillageLikeRecordVo;

/**
 * @Title
 * @Description 小区点赞实现层
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年7月22日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年7月22日  下午5:24:40 		创建
 *==========================================================
 * 
 */
@Service
public class VillageLikeRecordServiceImpl extends CrudServiceImpl<VillageLikeRecord> implements
		VillageLikeRecordService<VillageLikeRecord> {
	
	private VillageLikeRecordMapper<VillageLikeRecord> villageLikeRecordMapper;
	
	@Autowired
	public VillageLikeRecordServiceImpl(VillageLikeRecordMapper<VillageLikeRecord> villageLikeRecordMapper) {
		super(villageLikeRecordMapper);
		this.villageLikeRecordMapper = villageLikeRecordMapper;
	}

	@Override
	public List<VillageLikeRecordVo> findAllVillageLikeRecord(VillageLikeRecordVo villageLikeRecordVo) {
		return villageLikeRecordMapper.findAllVillageLikeRecord(villageLikeRecordVo);
	}

	@Override
	public Long findAllVillageLikeRecordCount(VillageLikeRecordVo villageLikeRecordVo) {
		return villageLikeRecordMapper.findAllVillageLikeRecordCount(villageLikeRecordVo);
	}

	@Override
	public List<VillageLikeRecordVo> findVillageLikeRecord(
			VillageLikeRecordVo villageLikeRecordVo) {
		return villageLikeRecordMapper.findVillageLikeRecord(villageLikeRecordVo);
	}

	@Override
	public Long findVillageLikeRecordCount(
			VillageLikeRecordVo villageLikeRecordVo) {
		return villageLikeRecordMapper.findVillageLikeRecordCount(villageLikeRecordVo);
	}

}
