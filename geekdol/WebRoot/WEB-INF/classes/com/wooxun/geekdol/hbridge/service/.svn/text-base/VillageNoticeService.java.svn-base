package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.VillageNotice;
import com.wooxun.geekdol.hbridge.vo.VillageNoticeVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月29日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年7月29日  上午11:22:32 		创建
 *==========================================================
 * 
 */
public interface VillageNoticeService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示总数量
     * @author:张洋
     * @CreateDate2016年8月26日10:54:30
     * @param villageNoticeVo
     * @return long
     */
    public long queryCountByParam(VillageNoticeVo villageNoticeVo);
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示列表
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param villageNoticeVo
     * @return List集合
     */
    public List<VillageNotice> queryListByParam(VillageNoticeVo villageNoticeVo);
    /**
     * 
     * @Title 条件查询,按浏览量倒序排列 
     * @Description 条件查询,按浏览量倒序排列 
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param villageNoticeVo
     * @return List集合
     */
    public List<VillageNotice> queryListByParamOrder(VillageNoticeVo villageNoticeVo);

    /**
     * 
     * @Title 公告查询
     * @Description 根据小区ID返回所有公告
     * @author:张洋
     * @CreateDate:2016年8月2日14:32:19
     * @param map
     * @return List集合
     */
    public List<VillageNotice> queryListByVillageId(Map<String,Object> map);
    
    /**
     * 
     * @Title 插入新数据，并插入相关的关联表
     * @Description 插入新数据，并插入相关的关联表
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param villageNotice
     * @param villageIds
     * @param userId
     * @return VillageNotice对象
     */
    public Integer insertBackId(VillageNotice villageNotice,String villageIds,Long userId);
    
    /**
     * 
     * @Title 更新数据，并更新相应的关联表
     * @Description 更新数据，并更新相应的关联表
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param villageNotice
     * @param villageIds
     * @param userId
     * @return VillageNotice对象
     */
    public Integer updateBackId(VillageNotice villageNotice,String villageIds,Long userId);
}
