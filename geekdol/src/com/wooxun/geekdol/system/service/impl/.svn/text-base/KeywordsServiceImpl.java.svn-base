package com.wooxun.geekdol.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.SensitivewordFilter;
import com.wooxun.geekdol.system.mapper.KeywordsMapper;
import com.wooxun.geekdol.system.model.Keywords;
import com.wooxun.geekdol.system.service.KeywordsService;
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
 * 1. 	 QZG 	2016年7月21日  上午10:42:31 		创建
 *==========================================================
 * 
 */
@Service
public class KeywordsServiceImpl extends CrudServiceImpl<Keywords> implements KeywordsService<Keywords>{
    
    @Autowired
    private KeywordsMapper<Keywords> keywordsMapper;
    
    @Autowired
    public KeywordsServiceImpl(KeywordsMapper<Keywords> keywordsMapper) {
        super(keywordsMapper);
        this.keywordsMapper=keywordsMapper;
    }
    /**
     * 
     * @Title
     * @Description 关键字库 根据查询条件返回列表
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:53:38
     * @param keywordsVo
     * @return
     */
    @Override
    public List<KeywordsVo> findAll(KeywordsVo keywordsVo) {
        return keywordsMapper.findAll(keywordsVo);
    }
    /**
     * 
     * @Title
     * @Description 关键字库 根据条件查询条数
     * @author:QZG
     * @CreateDate:2016年7月21日 上午10:52:44
     * @param keywordsVo
     * @return
     */
    @Override
    public Long findAllCount(KeywordsVo keywordsVo) {
        return keywordsMapper.findAllCount(keywordsVo);
    }
    
     
    /**
     * 
     * @Title
     * @Description 修改关键字库信息 修改成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月21日 下午3:54:38
     * @param keywords
     * @return
     */
    @Override
    public boolean updateKeywords(Keywords keywords) {
        int result = keywordsMapper.updateByPrimaryKeySelective(keywords);
        return result>0?true:false;
    }
    /**
     * 
     * @Title 
     * @Description 新增操作 返回新增该关键字数据库中数量
     * @author:QZG
     * @CreateDate:2016年7月21日 下午5:45:56
     * @param keywordsVo
     * @return
     */
    @Override
    public Long findCount(Keywords keywords) {
        return keywordsMapper.findCount(keywords);
    }
    
	@Override
	public boolean judgeIlleagl(String content) {
		// 查找关键字list
		List<KeywordsVo> keywordsList = keywordsMapper.findAll(new KeywordsVo());
		List<String> keyList = new ArrayList<String>();
		for (KeywordsVo kv:keywordsList) {
			keyList.add(kv.getKeywordsContent());
		}
		SensitivewordFilter filter = new SensitivewordFilter(keyList);
		// 查找敏感词
		Set<String> set = filter.getSensitiveWord(content, 1);
		if (set.size() > 0) { // 如果存在敏感词
			return true;
		} else { // 如果不存在敏感词
			return false;
		}
	}
    @Override
    public void insertList(List<Keywords> list) {
        keywordsMapper.insertList(list);      
    }
   
}
