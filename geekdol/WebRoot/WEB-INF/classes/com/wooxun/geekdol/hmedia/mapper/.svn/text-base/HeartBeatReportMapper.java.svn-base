package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.vo.HeartBeatReportVo;


public interface HeartBeatReportMapper <T extends Serializable> extends CrudMapper<T>{

    /**
     * 
     * @Title
     * @Description 条件查询
     * @author:张洋
     * @CreateDate:2016年9月13日15:10:54
     * @param heartBeatReportVo
     * @return 列表
     */
     public List<HeartBeatReportVo> queryListAndUser(HeartBeatReportVo heartBeatReportVo);
     /**
      * 
      * @Title
      * @Description 条件查询总数量，用户被删除则不计数
      * @author:张洋
      * @CreateDate:2016年9月18日16:39:57
      * @param heartBeatReportVo
      * @return 总数量
      */
      public Long queryCountAndUser(HeartBeatReportVo heartBeatReportVo);
}