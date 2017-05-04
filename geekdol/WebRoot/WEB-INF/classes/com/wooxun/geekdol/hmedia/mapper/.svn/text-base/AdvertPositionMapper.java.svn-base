package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.AdvertPosition;


public interface AdvertPositionMapper <T extends Serializable> extends CrudMapper<T> {
   /**
    * 
    * @Title
    * @Description 通过广告id查询广告位置id
    * @author:QZG
    * @CreateDate:2016年8月7日 下午9:39:27
    * @param advertId
    * @return
    */
   public AdvertPosition selectPositionId(Long advertId);
   
   /**
    * 
    * @Title
    * @Description 修改时通过广告id删除关系表的数据
    * @author:QZG
    * @CreateDate:2016年8月9日 下午4:58:19
    * @param id
    * @return
    */
   public int deleteById(Long id);
}