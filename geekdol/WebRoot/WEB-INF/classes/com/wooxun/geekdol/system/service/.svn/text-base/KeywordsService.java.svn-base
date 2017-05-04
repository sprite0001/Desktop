package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.Keywords;
import com.wooxun.geekdol.system.vo.KeywordsVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月21日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG  2016年7月21日  上午10:36:56 		创建
 *==========================================================
 * 
 */
public interface KeywordsService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title
     * @Description 关键字库 根据查询条件查询列表
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:40:35
     * @param keywordsVo
     * @return
     */
    public List<KeywordsVo> findAll(KeywordsVo keywordsVo);
    /**
     * 
     * @Title
     * @Description 关键字库  根据查询条件显示列表条数
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:41:14
     * @param keywordsVo
     * @return
     */
    public Long findAllCount(KeywordsVo keywordsVo);
     
 
    /**
     * 
     * @Title
     * @Description 修改关键字库 修改成功返回true
     * @author:QZG
     * @CreateDate:2016年7月21日 下午3:51:41
     * @param keywords
     * @return
     */
    public boolean updateKeywords(Keywords keywords);
    /**
     * 
     * @Title
     * @Description 新增操作返回查询数量
     * @author:QZG
     * @CreateDate:2016年7月21日 下午5:44:58
     * @param keywordsVo
     * @return
     */
    public Long  findCount(Keywords keywords);
    
    /**
     * 
     * @Title
     * @Description 判断内容是否含有关键字
     * @author:kangtianyu
     * @CreateDate:2016年8月13日 上午10:02:08
     * @param content
     * @return
     */
	public boolean judgeIlleagl(String content);
    /**
     * 
     * @Title
     * @Description 批量插入关键词库
     * @author:张洋
     * @CreateDate:2016年9月9日14:36:29
     * @param list
     * @return
     */
    public void insertList(List<Keywords> list);
}
