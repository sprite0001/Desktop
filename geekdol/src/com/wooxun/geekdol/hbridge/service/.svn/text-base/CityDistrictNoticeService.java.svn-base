package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.CityDistrictNotice;
import com.wooxun.geekdol.hbridge.vo.CityDistrictNoticeVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年7月26日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年7月26日  上午10:26:14 		创建
 *==========================================================
 * 
 */
public interface CityDistrictNoticeService <T extends Serializable> extends CrudService<T> {
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示列表
     * @author:张洋
     * @CreateDate:2016年7月27日14:29:59
     * @param cityDistrictNoticeVo
     * @return List集合
     */
    public List<CityDistrictNotice> queryListByParam(CityDistrictNoticeVo cityDistrictNoticeVo);
    /**
     * 
     * @Title 条件查询,按浏览量倒序排列
     * @Description 条件查询,按浏览量倒序排列
     * @author:张洋
     * @CreateDate:2016年7月27日14:29:59
     * @param cityDistrictNoticeVo
     * @return List集合
     */
    public List<CityDistrictNotice> queryListByParamOrder(CityDistrictNoticeVo cityDistrictNoticeVo);
    
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示列表，关联查询市表，返回值带city的名称
     * @author:张洋
     * @CreateDate:2016年7月28日10:25:41
     * @param cityDistrictNoticeVo
     * @return List集合
     */
    public List<CityDistrictNoticeVo> queryListCityByParam(CityDistrictNoticeVo cityDistrictNoticeVo);
    
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，查询出所有市公告数量
     * @author:张洋
     * @CreateDate:2016年8月26日11:03:32
     * @param cityDistrictNoticeVo
     * @return Long
     */
    public Long queryCountCityByParam(CityDistrictNoticeVo cityDistrictNoticeVo);
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，查询出所有县区公告数量
     * @author:张洋
     * @CreateDate:2016年8月26日11:03:32
     * @param cityDistrictNoticeVo
     * @return Long
     */
    public Long queryCountCountyByParam(CityDistrictNoticeVo cityDistrictNoticeVo);

    
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，查询出所有社区公告数量
     * @author:张洋
     * @CreateDate:2016年8月26日11:03:32
     * @param cityDistrictNoticeVo
     * @return Long
     */
    public Long queryCountCommunityByParam(CityDistrictNoticeVo cityDistrictNoticeVo);
    
    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示列表，关联查询县区表，返回值带县区的名称
     * @author:张洋
     * @CreateDate:2016年7月28日10:25:41
     * @param cityDistrictNoticeVo
     * @return List集合
     */
    public List<CityDistrictNoticeVo> queryListCountyByParam(CityDistrictNoticeVo cityDistrictNoticeVo);

    /**
     * 
     * @Title 公告查询
     * @Description 根据页面查询条件，显示列表，关联查询办事处表，返回值带办事处的名称
     * @author:张洋
     * @CreateDate:2016年7月28日10:25:41
     * @param cityDistrictNoticeVo
     * @return List集合
     */
    public List<CityDistrictNoticeVo> queryListCommunityByParam(CityDistrictNoticeVo cityDistrictNoticeVo);
}
