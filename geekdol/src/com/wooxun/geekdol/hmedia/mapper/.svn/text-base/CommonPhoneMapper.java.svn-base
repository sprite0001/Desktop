package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.CommonPhone;
import com.wooxun.geekdol.hmedia.vo.AppCommonPhoneVo;
import com.wooxun.geekdol.hmedia.vo.CommonPhoneVo;

public interface CommonPhoneMapper <T extends Serializable> extends CrudMapper<T>{
   /**
    * 
    * @Title
    * @Description 根据页面查询条件，显示列表
    * @author:QZG
    * @CreateDate:2016年7月29日 下午4:34:48
    * @param commonPhoneVo
    * @return
    */
    public List<CommonPhoneVo> findAll(CommonPhoneVo commonPhoneVo);
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，返回满足查询条件的数量
     * @author:QZG
     * @CreateDate:2016年7月29日 下午4:35:17
     * @param commonPhoneVo
     * @return
     */
    public Long findAllCount(CommonPhoneVo commonPhoneVo);
    /**
     * 
     * @Title
     * @Description 新增常用电话号码时查询数据库中存在的数量
     * @author:QZG
     * @CreateDate:2016年7月30日 上午9:30:48
     * @param commonPhoneVo
     * @return
     */
    public Long findCount(CommonPhone commonPhone);
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