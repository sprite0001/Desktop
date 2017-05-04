package com.wooxun.geekdol.hbridge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.hbridge.mapper.NetizenAcumenMapper;
import com.wooxun.geekdol.hbridge.model.NetizenAcumen;
import com.wooxun.geekdol.hbridge.service.NetizenAcumentService;
import com.wooxun.geekdol.hbridge.vo.NetizenAcumenVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.mapper.SysDataMapper;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.SysData;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年9月8日  下午3:11:03 		创建
 *==========================================================
 * 
 */
@Service
public class NetizenAcumenServiceImpl extends CrudServiceImpl<NetizenAcumen> implements NetizenAcumentService<NetizenAcumen>
{
    @Autowired
    private NetizenAcumenMapper<NetizenAcumen> netizenAcumenMapper;
   
    @Autowired
    private AttachMapper<Attach> attachMapper;
    @Autowired
    private SysDataMapper<SysData> sysDataMapper;
    
    @Autowired
    public NetizenAcumenServiceImpl(NetizenAcumenMapper<NetizenAcumen> netizenAcumenMapper) {
        super(netizenAcumenMapper);
        this.netizenAcumenMapper=netizenAcumenMapper;
    }

    @Override
    public List<NetizenAcumenVo> findAll(NetizenAcumenVo netizenAcumenVo) {
    	List<NetizenAcumenVo> allList = netizenAcumenMapper.findAll(netizenAcumenVo);
    	// 查询网民法眼类型
    	List<SysData> sysDatas = sysDataMapper.querySysdataByType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
    	// 组装类型值为对应名称
    	String typeValue = "";
    	for(NetizenAcumenVo modelVo:allList){
    		typeValue = modelVo.getType();
    		for(SysData sysData:sysDatas){
    			if(typeValue.contains(sysData.getValue())){
    				typeValue = typeValue.replace(sysData.getValue(), sysData.getLable());
    			}
    		}
    		modelVo.setType(typeValue);
    		typeValue = "";
    	}
        return allList;
    }

    @Override
    public long findAllCount(NetizenAcumenVo netizenAcumenVo) {
        return netizenAcumenMapper.findAllCount(netizenAcumenVo);
    }

    @Override
    public boolean deleteNetizenacumen(NetizenAcumen netizenAcumen) {
        int res=netizenAcumenMapper.deleteNetizenacumen(netizenAcumen);
        return res>0?true:false;
    }

    @Override
    public NetizenAcumenVo selectNetizenAcumenVo(Long id) {
        NetizenAcumen netizenAcumen = netizenAcumenMapper.selectByPrimaryKey(id);
        // 查询网民法眼类型
    	List<SysData> sysDatas = sysDataMapper.querySysdataByType(ConstantStr.INCORRUPTGOVERNMENTTYPE);
    	// 组装类型值为对应名称
    	String typeValue = netizenAcumen.getType();
		for(SysData sysData:sysDatas){
			if(typeValue.contains(sysData.getValue())){
				typeValue = typeValue.replace(sysData.getValue(), sysData.getLable());
			}
		}
		netizenAcumen.setType(typeValue);
        NetizenAcumenVo netizenAcumenVo = formateData(netizenAcumen);
        return netizenAcumenVo;
    }
    
    /**
     * 
     * @Title
     * @Description 返回封装好的NetizenAcumenVo数据
     * @author:QZG
     * @CreateDate:2016年8月5日 下午4:48:00
     * @param netizenAcumen
     * @return
     */
    private NetizenAcumenVo formateData(NetizenAcumen netizenAcumen) {
        NetizenAcumenVo netizenAcumenVo = new NetizenAcumenVo();
        BeanUtils.copyProperties(netizenAcumen, netizenAcumenVo);
        netizenAcumenVo.setInsYmdhmsStr(DateUtil.dateToString(netizenAcumen.getInsYmdhms()));
        return netizenAcumenVo;
    }

    @Override
    public boolean insertNetizenAcumen(NetizenAcumen netizenAcumen) {
        int res=netizenAcumenMapper.insertSelective(netizenAcumen);
        if(res<0)
        {
            return false;
        }else{
            // 保存附件
            List<Attach> listAttach = new ArrayList<Attach>();
            int k = 0;
            for (Attach attach : netizenAcumen.getAttachs()) {
                attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
                attach.setOwnerId(netizenAcumen.getId());
                attach.setOwnerTbType(ConstantStr.NETIZENACUMEN_17);
                attach.setOrdering(k);
                attach.setOpreator(netizenAcumen.getInsId());
                attach.setInsYmdhms(netizenAcumen.getInsYmdhms());
                attach.setUpdEac(netizenAcumen.getUpdEac());
                attach.setUpdYmdhms(netizenAcumen.getUpdYmdhms());
                attach.setDelFlag(ConstantStr.DELETE_N);
                listAttach.add(attach);
                k++;
            }
            if (listAttach.size() != 0) {
                attachMapper.insertBatch(listAttach);
            }
        }
        return true;
    }

	@Override
	public List<Attach> findAttachByParam(Attach attach) {
		return attachMapper.selectAttachByParam(attach);
	}

}
