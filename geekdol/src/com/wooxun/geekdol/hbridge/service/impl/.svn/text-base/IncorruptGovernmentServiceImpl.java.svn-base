package com.wooxun.geekdol.hbridge.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.hbridge.mapper.IncorruptGovernmentMapper;
import com.wooxun.geekdol.hbridge.mapper.IncorruptRecommendMapper;
import com.wooxun.geekdol.hbridge.model.IncorruptGovernment;
import com.wooxun.geekdol.hbridge.model.IncorruptRecommend;
import com.wooxun.geekdol.hbridge.service.IncorruptGovernmentService;
import com.wooxun.geekdol.hbridge.vo.IncorruptGovernmentVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK	
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 YK	2016年9月8日  下午2:18:34 		创建
 *==========================================================
 * 
 */
@Service
public class IncorruptGovernmentServiceImpl extends CrudServiceImpl<IncorruptGovernment>
	implements IncorruptGovernmentService<IncorruptGovernment> {
	private static final Logger LOG = LoggerFactory.getLogger(IncorruptGovernmentServiceImpl.class);
	
	private IncorruptGovernmentMapper<IncorruptGovernment> incorruptGovernmentMapper;
	@Autowired
	private IncorruptRecommendMapper<IncorruptRecommend> incorruptRecommendMapper;
	
	@Autowired
	public IncorruptGovernmentServiceImpl(IncorruptGovernmentMapper<IncorruptGovernment> incorruptGovernmentMapper) {
		super(incorruptGovernmentMapper);
		this.incorruptGovernmentMapper = incorruptGovernmentMapper;
	}

	@Override
	public Long findIncorruptGovernmentCount(IncorruptGovernmentVo incorruptGovernmentVo) {
		return incorruptGovernmentMapper.findIncorruptGovernmentCount(incorruptGovernmentVo);
	}

	@Override
	public List<IncorruptGovernmentVo> findIncorruptGovernment(IncorruptGovernmentVo incorruptGovernmentVo) {
		// 设置风清气正类型在数据字典中定义
		incorruptGovernmentVo.setTypeType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
		// 设置风清气正状态类型在数据字典定义
		incorruptGovernmentVo.setPublishStatusType(ConstantStr.INCORRUPTGOVERNMENTSTATUS);
		return incorruptGovernmentMapper.findIncorruptGovernment(incorruptGovernmentVo);
	}

	@Override
	public boolean insertIncorruptGovernment(IncorruptGovernment incorruptGovernment) {
		// 设置风清气正默认状态：未发布
		incorruptGovernment.setPublishStatus(ConstantStr.INCORRUPTGOVERNMENTSTATUS_DEFAULT);
		// 保存数据
		int i = incorruptGovernmentMapper.insertSelective(incorruptGovernment);
		return i>0?true:false;
	}

	@Override
	public IncorruptGovernmentVo findById(IncorruptGovernmentVo incorruptGovernmentVo) {
		// 设置风清气正类型在数据字典中定义
				incorruptGovernmentVo.setTypeType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
				// 设置风清气正状态类型在数据字典定义
				incorruptGovernmentVo.setPublishStatusType(ConstantStr.INCORRUPTGOVERNMENTSTATUS);
		return incorruptGovernmentMapper.findById(incorruptGovernmentVo);
	}

	@Override
	public IncorruptGovernment findIncorruptGovernment(Long id) {
		return incorruptGovernmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateIncorruptGovernment(IncorruptGovernment incorruptGovernment,IncorruptRecommend recommend) {
		try{
			// 设置选择的信息时间
			incorruptGovernment.setMessageTime(DateUtil.parseDate(
					incorruptGovernment.getMessageTimeStr(), "yyyy-MM-dd hh:MM:ss"));
		}catch(Exception ex){
			LOG.error("更新风清气正时候,信息时间转换出错:"+ex.getMessage());
		}
		int i = incorruptGovernmentMapper.updateByPrimaryKeySelective(incorruptGovernment);
		// 关注的时候，新增关注记录
		if(recommend != null){
			i = incorruptRecommendMapper.insertSelective(recommend);
		}
		return i>0?true:false;
	}

	@Override
	public List<Map<String, Object>> queryListByParam(IncorruptGovernmentVo incorruptGovernmentVo) {
		// 查找已发布的数据
		incorruptGovernmentVo.setPublishStatus(ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY);
		// 设置风清气正type类型在数据字典中的定义
		incorruptGovernmentVo.setTypeType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
		return incorruptGovernmentMapper.queryListByParam(incorruptGovernmentVo);
	}

	@Override
	public List<Map<String, Object>> queryHotListByParam(IncorruptGovernmentVo incorruptGovernmentVo) {
		// 查找已发布的数据
		incorruptGovernmentVo.setPublishStatus(ConstantStr.INCORRUPTGOVERNMENTSTATUS_AREADY);
		// 设置风清气正type类型在数据字典中的定义
		incorruptGovernmentVo.setTypeType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
		return incorruptGovernmentMapper.queryHotListByParam(incorruptGovernmentVo);
	}
}
