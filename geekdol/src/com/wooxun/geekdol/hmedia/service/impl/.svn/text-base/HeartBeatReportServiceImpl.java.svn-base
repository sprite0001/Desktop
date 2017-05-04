package com.wooxun.geekdol.hmedia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatMapper;
import com.wooxun.geekdol.hmedia.mapper.HeartBeatReportMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatReport;
import com.wooxun.geekdol.hmedia.service.HeartBeatReportService;
import com.wooxun.geekdol.hmedia.vo.HeartBeatReportVo;
/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author zhangyang	
* @CreateDate 2016年9月13日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1.  zhangyang	2016年9月13日  下午1:46:21 		创建
*==========================================================
*
 */

@Service
public class HeartBeatReportServiceImpl extends CrudServiceImpl<HeartBeatReport> implements HeartBeatReportService<HeartBeatReport> {

	private HeartBeatReportMapper<HeartBeatReport> heartBeatReportMapper;
	@Autowired
	private HeartBeatMapper<HeartBeat> heartBeatMapper;

	@Autowired
	public HeartBeatReportServiceImpl(HeartBeatReportMapper<HeartBeatReport> heartBeatReportMapper) {
		super(heartBeatReportMapper);
		this.heartBeatReportMapper = heartBeatReportMapper;
	}

    @Override
    public List<HeartBeatReportVo> queryListAndUser(
            HeartBeatReportVo heartBeatReportVo) {
        return heartBeatReportMapper.queryListAndUser(heartBeatReportVo);
    }

    @Override
    public Long queryCountAndUser(HeartBeatReportVo heartBeatReportVo) {
        return heartBeatReportMapper.queryCountAndUser(heartBeatReportVo);
    }

	@Override
	public boolean saveHeartBeatReport(HeartBeatReport heartBeatReport,HeartBeat heartBeat) {
		int i = 0;
		if(heartBeat != null){
			// 如果随心拍状态为正常则更新处理状态为举报状态
			if(ConstantStr.HEARTBEATSTATUS_01.equals(heartBeat.getTreatmentStatus())){
				// 设置随心拍状态为举报状态
				heartBeat.setTreatmentStatus(ConstantStr.HEARTBEATSTATUS_03);
				// 更新回数+1
				heartBeat.setUpdEac(heartBeat.getUpdEac()+1);
				heartBeat.setUpdYmdhms(new Date());
				// 更新随心拍数据
				i = heartBeatMapper.updateByPrimaryKeySelective(heartBeat);
				// 设置举报状态0：有效
				heartBeatReport.setEffectiveState(ConstantStr.HEARTBEATREPORTSTATUS_0);
			}else{// 如果随心拍状态为非正常则不更新随心拍状态
				// 设置随心拍举报状态为无效
				heartBeatReport.setEffectiveState(ConstantStr.HEARTBEATREPORTSTATUS_1);
			}
			// 修改随心拍举报公共属性
			addAttr(heartBeatReport);
			// 新增举报内容
			i = heartBeatReportMapper.insertSelective(heartBeatReport);
		}
		return i>0?true:false;
	}
	
	/**
	 * 
	 * @Title
	 * @Description 修改随心拍公共属性
	 * @author:YK
	 * @CreateDate:2016年9月20日 下午5:46:58
	 * @param heartBeatReport
	 * @return
	 */
	private HeartBeatReport addAttr(HeartBeatReport heartBeatReport){
		heartBeatReport.setInsYmdhms(new Date());
		heartBeatReport.setUpdId(heartBeatReport.getInsId());
		heartBeatReport.setUpdEac(0L);
		heartBeatReport.setUpdYmdhms(new Date());
		heartBeatReport.setDelFlag(ConstantStr.DELETE_N);
		return heartBeatReport;
	}

}
