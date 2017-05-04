package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.CommonPhone;
import com.wooxun.geekdol.hmedia.vo.AppCommonPhoneVo;
import com.wooxun.geekdol.hmedia.vo.CommonPhoneVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月29日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月29日  下午4:36:03 		创建
 *==========================================================
 * 
 */
public interface CommonPhoneService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表
     * @author:QZG
     * @CreateDate:2016年7月29日 下午4:38:03
     * @param commonPhoneVo
     * @return
     */
    public List<CommonPhoneVo> findAll(CommonPhoneVo commonPhoneVo);
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，返回满足查询条件的数量
     * @author:QZG
     * @CreateDate:2016年7月29日 下午4:38:22
     * @param commonPhoneVo
     * @return
     */
    public Long findAllCount(CommonPhoneVo commonPhoneVo);
    /**
     * 
     * @Title
     * @Description 新增常用号码时查询数据库中存在该常用号码的数量
     * @author:QZG
     * @CreateDate:2016年7月30日 上午9:31:44
     * @param commonPhoneVo
     * @return
     */
    public Long findCount(CommonPhone commonPhone);
    /**
     * 
     * @Title
     * @Description 修改常用电话信息  修改成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月30日 上午10:07:32
     * @param commonPhone
     * @return
     */
    public boolean updateCommonPhone(CommonPhone commonPhone);
    
    /**
     * 
     * @Title
     * @Description app接口用  查找常用电话
     * @author:QZG
     * @CreateDate:2016年8月11日 下午12:59:54
     * @return
     */
    public List<AppCommonPhoneVo> findCommonPhone();
    
}
