package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.VillageNotice;
import com.wooxun.geekdol.hbridge.vo.VillageNoticeVo;

/**
 * @Title
 * @Description 小区公告
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月29日11:18:54
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员           修改日期                描述
 * 1.  zhangyang    2016年7月29日11:18:54      创建
 *==========================================================
 * 
 */
public interface VillageNoticeMapper <T extends Serializable> extends CrudMapper<T> {
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
     * @Title 插入新数据，并返回ID
     * @Description 插入新数据，并返回ID
     * @author:张洋
     * @CreateDate:2016年7月29日11:23:03
     * @param villageNotice
     * @return VillageNotice对象
     */
    public Integer insertBackId(VillageNotice villageNotice);
}