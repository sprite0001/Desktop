package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.HeartBeatComment;
import com.wooxun.geekdol.hmedia.model.HeartCommentReply;
import com.wooxun.geekdol.hmedia.vo.HeartCommentReplyVo;

/**
 * 
* @Title 随心拍二级评论的service
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
public interface HeartCommentReplyService<T extends Serializable> extends CrudService<T> {
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
	   
	/**
	* 
	* @Title
	* @Description 新增评论回复并更新评论的回复量
	* @author:YK
	* @CreateDate:2016年9月23日 上午10:11:37
	* @param heartCommentReply
	* @param heartBeatComment
	* @return boolean
	*/
	public boolean saveReplay(HeartCommentReply heartCommentReply,HeartBeatComment heartBeatComment);
	
	/**
	 * 
	 * @Title
	 * @Description 删除回复并更新评论的回复量
	 * @author:YK
	 * @CreateDate:2016年9月23日 下午6:24:36
	 * @param heartCommentReply
	 * @return boolean
	 */
	public boolean deleteReplay(HeartCommentReply heartCommentReply);
}
