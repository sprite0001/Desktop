package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.model.IntimateNewsLike;

/**
 * 
* @Title 贴心报点赞记录的service
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
public interface IntimateNewsLikeService<T extends Serializable> extends CrudService<T> {
    /**
     * 
     * @Title
     * @Description 条件查询
     * @author:张洋
     * @CreateDate:2016年9月20日17:51:32
     * @param intimateNewsLike
     * @return 列表
     */
     public List<IntimateNewsLike> queryListByParam(IntimateNewsLike intimateNewsLike);
     
     /**
      * 
      * @Title
      * @Description 点赞\取消点赞
      * @author:YK
      * @CreateDate:2016年9月22日 下午4:35:33
      * @param hbl
      * @param hb
      * @param list
      * @return boolean
      */
     public boolean updateIntimateNewsLike(IntimateNewsLike hbl,IntimateNews hb,List<IntimateNewsLike> list);
}
