package com.wooxun.geekdol.hbridge.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hbridge.model.NetizenAcumen;
import com.wooxun.geekdol.hbridge.vo.NetizenAcumenVo;

public interface NetizenAcumenMapper <T extends Serializable> extends CrudMapper<T> {
   /**
    * 
    * @Title
    * @Description 查询满足条件的网民法眼信息列表
    * @author:QZG
    * @CreateDate:2016年9月8日 下午3:03:55
    * @param netizenAcumenVo
    * @return
    */
   public List<NetizenAcumenVo> findAll(NetizenAcumenVo netizenAcumenVo);
   
   /**
    * 
    * @Title
    * @Description 查询满足条件的网民法眼信息列表条数
    * @author:QZG
    * @CreateDate:2016年9月8日 下午3:06:03
    * @param netizenAcumenVo
    * @return
    */
   public long findAllCount(NetizenAcumenVo netizenAcumenVo);
   
   /**
    * 
    * @Title
    * @Description 逻辑删除网民法眼信息 删除成功返回true
    * @author:QZG
    * @CreateDate:2016年9月8日 下午9:10:15
    * @param netizenAcumen
    * @return
    */
   public int deleteNetizenacumen(NetizenAcumen netizenAcumen);
}