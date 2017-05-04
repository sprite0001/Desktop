package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.vo.HeartBeatCommentVo;


public interface HeartBeatCommentMapper <T extends Serializable> extends CrudMapper<T>{
    /**
     * 
     * @Title
     * @Description 条件查询
     * @author:张洋
     * @CreateDate:2016年9月13日15:10:54
     * @param heartBeatCommentVo
     * @return 列表
     */
     public List<HeartBeatComment> queryListByParam(HeartBeatCommentVo heartBeatCommentVo);
     /**
      * 
      * @Title
      * @Description 条件查询
      * @author:张洋
      * @CreateDate:2016年9月13日15:10:54
      * @param heartBeatCommentVo
      * @return 总数量
      */
      public Long queryCountByParam(HeartBeatCommentVo heartBeatCommentVo);
      /**
       * 
       * @Title
       * @Description 条件查询，关联用户
       * @author:张洋
       * @CreateDate:2016年9月13日15:10:54
       * @param heartBeatCommentVo
       * @return 列表
       */
       public List<HeartBeatCommentVo> queryListByUser(HeartBeatCommentVo heartBeatCommentVo);
       /**
        * 
        * @Title
        * @Description 根据随心拍id查出所有评论，关联用户,app用，查出头像和二级回复量
        * @author:张洋
        * @CreateDate:2016年9月20日16:07:58
        * @param heartBeatCommentVo
        * @return 列表
        */
        public List<HeartBeatCommentVo> queryListByUserApp(HeartBeatCommentVo heartBeatCommentVo);
       /**
        * 
        * @Title
        * @Description 条件查询，关联用户
        * @author:张洋
        * @CreateDate:2016年9月13日15:10:54
        * @param heartBeatCommentVo
        * @return 总数量
        */
        public Long queryCountByUser(HeartBeatCommentVo heartBeatCommentVo);
}