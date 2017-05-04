package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.vo.HeartBeatReportVo;
import com.wooxun.geekdol.hmedia.vo.HeartCommentReplyVo;

public interface HeartCommentReplyMapper <T extends Serializable> extends CrudMapper<T>{
    /**
     * 
     * @Title
     * @Description 条件查询，关联用户
     * @author:张洋
     * @CreateDate:2016年9月13日15:10:54
     * @param heartCommentReplyVo
     * @return 列表
     */
     public List<HeartCommentReplyVo> queryListByUser(HeartCommentReplyVo heartCommentReplyVo);
     /**
      * 
      * @Title
      * @Description 条件查询，关联用户
      * @author:张洋
      * @CreateDate:2016年9月13日15:10:54
      * @param heartCommentReplyVo
      * @return 总数量
      */
      public Long queryCountByUser(HeartCommentReplyVo heartCommentReplyVo);
      /**
       * 
       * @Title
       * @Description 根据随心拍评论id查出所有二级评论，关联用户,app用，查出头像和二级回复量
       * @author:张洋
       * @CreateDate:2016年9月20日16:07:58
       * @param heartCommentReplyVo
       * @return 列表
       */
       public List<HeartCommentReplyVo> queryListByUserApp(HeartCommentReplyVo heartCommentReplyVo);
}