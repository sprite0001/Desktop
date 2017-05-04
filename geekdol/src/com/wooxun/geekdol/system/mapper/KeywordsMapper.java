package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Keywords;
import com.wooxun.geekdol.system.vo.KeywordsVo;

public interface KeywordsMapper <T extends Serializable> extends CrudMapper<T>{
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，显示列表
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:35:29
     * @param keywordsVo
     * @return
     */
    public List<KeywordsVo> findAll(KeywordsVo keywordsVo);
    /**
     * 
     * @Title
     * @Description 根据页面查询条件，返回满足查询条件的数量
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:35:51
     * @param keywordsVo
     * @return
     */
    public Long findAllCount(KeywordsVo keywordsVo);
 
    
    /**
     * 
     * @Title
     * @Description 新增操作查询数据库已有该关键字数量
     * @author:QZG
     * @CreateDate:2016年7月21日 下午5:43:22
     * @param keywords
     * @return
     */
    public Long findCount(Keywords keywords);
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