package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.HeartBeat;
import com.wooxun.geekdol.hmedia.model.HeartBeatReport;
import com.wooxun.geekdol.hmedia.vo.HeartBeatReportVo;

/**
 * 
* @Title 随心拍举报信息的service
* @Description
* @version 1.0.0
* @Author zhangyang	
* @CreateDate 2016年9月13日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1.  zhangyang	2016年9月13日  下午1:44:28 		创建
*==========================================================
*
 */
public interface HeartBeatReportService<T extends Serializable> extends CrudService<T> {

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
      
      /**
       * @Title
       * @Description 举报
       * @author:YK
       * @CreateDate:2016年9月20日 下午5:14:21
       * @param heartBeatReport
       * @param heartBeat
       * @return boolean
       */
      public boolean saveHeartBeatReport(HeartBeatReport heartBeatReport,HeartBeat heartBeat);
}
