package com.wooxun.geekdol.hmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.hmedia.mapper.PositionMapper;
import com.wooxun.geekdol.hmedia.model.Position;
import com.wooxun.geekdol.hmedia.service.PositionService;
import com.wooxun.geekdol.hmedia.vo.PositionVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年8月1日  下午5:50:55 		创建
 *==========================================================
 * 
 */
@Service
public class PositionServiceImpl extends CrudServiceImpl<Position> implements PositionService<Position>{
   
    @Autowired
    private PositionMapper<Position> positionMapper;
    
    @Autowired
    public PositionServiceImpl(PositionMapper<Position> positionMapper) {
        super(positionMapper);
        this.positionMapper=positionMapper;
    }

    @Override
    public List<PositionVo> findAll(PositionVo positionVo) {
        return positionMapper.findAll(positionVo);
    }

    @Override
    public Long findAllCount(PositionVo positionVo) {
        return positionMapper.findAllCount(positionVo);
    }

    @Override
    public Long findCount(Position position) {
        return positionMapper.findCount(position);
    }

    @Override
    public boolean deletePosition(Position position) {
        int res = positionMapper.deletePosition(position);
        return res>0?true:false;
    }

	@Override
	public boolean findPositionCode(String positionCode, Long id) {
		// 创建Map对象用于封装查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中封装参数 */
		param.put("positionCode", positionCode);
		param.put("id", id);
		// 调用mapper方法看广告位置编码是否重复
		int result = positionMapper.findPositionCode(param);
		if (result > 0) { // 如果有则返回存在
			return true;
		} else {
			return false;
		}
	}

}
