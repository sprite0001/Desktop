package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.Position;
import com.wooxun.geekdol.hmedia.vo.PositionVo;
 
public interface PositionMapper <T extends Serializable> extends CrudMapper<T> {
    /**
     * 
     * @Title
     * @Description 广告位置管理  根据页面查询条件返回列表
     * @author:QZG
     * @CreateDate:2016年8月1日 下午5:45:31
     * @param positionVo
     * @return
     */
    public List<PositionVo> findAll(PositionVo positionVo);
    /**
     * 
     * @Title 广告位置管理 根据页面查询条件返回列表条数
     * @Description
     * @author:QZG
     * @CreateDate:2016年8月1日 下午5:47:00
     * @param positionVo
     * @return
     */
    public Long findAllCount(PositionVo positionVo);
    /**
     * 
     * @Title
     * @Description 新增操作时查询数据库中存在广告编号的数量
     * @author:QZG
     * @CreateDate:2016年8月2日 上午9:31:16
     * @param position
     * @return
     */
    public Long findCount(Position position);
    /**
     * 
     * @Title 
     * @Description 逻辑删除位置管理信息  删除成功 返回值大于0
     * @author:QZG
     * @CreateDate:2016年8月2日 上午10:45:02
     * @param position
     * @return
     */
    public int deletePosition(Position position);
    
    /**
     * @Title
     * @Description 判断广告位置编码是否重复
     * @author:kangtianyu
     * @CreateDate:2016年9月23日 下午2:29:14
     * @param param
     * @return
     */
	public int findPositionCode(Map<String, Object> param);
}