package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.Position;
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
 * 1. 	 QZG	2016年8月1日  下午5:48:31 		创建
 *==========================================================
 * 
 */
public interface PositionService <T extends Serializable> extends CrudService<T> {
   /**
    * 
    * @Title
    * @Description 广告位置管理 根据查询条件返回列表
    * @author:QZG
    * @CreateDate:2016年8月1日 下午5:49:18
    * @param positionVo
    * @return
    */
    public List<PositionVo> findAll(PositionVo positionVo);
   /**
    * 
    * @Title
    * @Description 广告位置管理 根据页面查询条件返回列表条数
    * @author:QZG
    * @CreateDate:2016年8月1日 下午5:49:46
    * @param positionVo
    * @return
    */
    public Long findAllCount(PositionVo positionVo);
    /**
     * 
     * @Title
     * @Description 新增操作时查询数据库中存在广告编码的数量
     * @author:QZG
     * @CreateDate:2016年8月2日 上午9:33:08
     * @param position
     * @return
     */
    public Long findCount(Position position);
    /**
     * 
     * @Title
     * @Description 逻辑删除位置管理信息 删除成功 返回true
     * @author:QZG
     * @CreateDate:2016年8月2日 上午10:46:24
     * @param position
     * @return
     */
    public boolean  deletePosition(Position position);
    
    /**
     * @Title
     * @Description 判断广告位置编码是否重复
     * @author:kangtianyu
     * @CreateDate:2016年9月23日 下午2:27:17
     * @param replaceAll
     * @param id
     * @return
     */
	public boolean findPositionCode(String replaceAll, Long id);
}
