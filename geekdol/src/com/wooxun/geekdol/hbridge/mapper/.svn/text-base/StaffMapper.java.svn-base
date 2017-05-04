package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.Staff;
import com.wooxun.geekdol.hbridge.vo.StaffVo;

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
public interface StaffMapper <T extends Serializable> extends CrudMapper<T> {
    /**
     * 
     * @Title 内参查询
     * @Description 根据页面查询条件，显示列表
     * @author:张洋
     * @CreateDate:2016年7月26日17:36:07
     * @param staffVo
     * @return List集合
     */
    public List<Staff> queryListByParam(StaffVo staffVo);
    /**
     * 
     * @Title 内参查询
     * @Description 根据页面查询条件，查询总数
     * @author:张洋
     * @CreateDate:2016年7月26日17:36:07
     * @param staffVo
     * @return long
     */
    public Long queryCountByParam(StaffVo staffVo);
    /**
     * 
     * @Title 内参查询
     * @Description 条件查询,按浏览量倒序排列
     * @author:张洋
     * @CreateDate:2016年8月3日09:25:44
     * @param staffVo
     * @return List集合
     */
    public List<Staff> queryListByParamOrder(StaffVo staffVo);
}
