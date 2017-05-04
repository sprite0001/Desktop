package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.Attach;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年9月18日14:58:13
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年9月18日14:58:10 		创建
 *==========================================================
 * 
 */
public interface AttachService <T extends Serializable> extends CrudService<T>{
    /**
     * @Title
     * @Description 批量插入附件
     * @author:张洋
     * @CreateDate:2016年9月18日14:59:42
     * @param list
     */
    public int insertBatch(List<Attach> list);
    

    /**
     * @Title
     * @Description 批量插入附件
     * @author:张洋
     * @CreateDate:2016年9月18日14:59:42
     * @param sqlParam
     * @return int
     */
    public int insertBatchByMap(Map<String, Object> sqlParam);

    /**
     * @Title
     * @Description 根据相关条件删除附件信息
     * @author:张洋
     * @CreateDate:2016年9月18日14:59:42
     * @param attachTemp
     * @return int
     */
    public int deleteByParam(Attach attachTemp);
    

    /**
     * @Title
     * @Description 根据相关信息查询附件信息
     * @author:张洋
     * @CreateDate:2016年9月18日14:59:42
     * @param attach
     * @return List
     */
    public List<Attach> selectAttachByParam(Attach attach);
}
