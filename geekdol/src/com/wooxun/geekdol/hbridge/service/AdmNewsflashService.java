package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.AdmNewsflash;
import com.wooxun.geekdol.hbridge.vo.AdmNewsflashVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月1日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月1日  下午4:11:54 		创建
 *==========================================================
 * 
 */
public interface AdmNewsflashService <T extends Serializable> extends CrudService<T>{

    /**
     * 
     * @Title 条件查询
     * @Description 条件查询
     * @author:张洋
     * @CreateDate:2016年8月2日09:59:26
     * @param admNewsflashVo
     * @return List集合
     */
    public List<AdmNewsflash> queryListByParam(AdmNewsflashVo admNewsflashVo);
    /**
     * 
     * @Title 条件查询 ,提交人被删除的话，则不显示
     * @Description 条件查询 ,提交人被删除的话，则不显示
     * @author:张洋
     * @CreateDate:2016年9月8日09:38:06
     * @param admNewsflashVo
     * @return List集合
     */
    public List<AdmNewsflashVo> queryListNotDel(AdmNewsflashVo admNewsflashVo);
    /**
     * 
     * @Title 条件查询
     * @Description 条件查询 查询出全部数量
     * @author:张洋
     * @CreateDate:2016年8月26日14:33:57
     * @param admNewsflashVo
     * @return Long
     */
    public Long queryCountByParam(AdmNewsflashVo admNewsflashVo);
    /**
     * 
     * @Title 条件查询，查询出全部数量,提交人被删除的话，则不计数
     * @Description 条件查询，查询出全部数量,提交人被删除的话，则不计数
     * @author:张洋
     * @CreateDate:2016年8月26日14:33:57
     * @param admNewsflashVo
     * @return Long
     */
    public Long queryCountNotDel(AdmNewsflashVo admNewsflashVo);
    /**
     * 
     * @Title 条件查询,按浏览量倒序排列
     * @Description 条件查询,按浏览量倒序排列
     * @author:张洋
     * @CreateDate:2016年8月2日17:59:54
     * @param admNewsflashVo
     * @return List集合
     */
    public List<AdmNewsflash> queryListByParamOrder(AdmNewsflashVo admNewsflashVo);
}
