package com.wooxun.geekdol.geekstore.mapper;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.geekstore.model.GrouponGoodsExtendInfo;

public interface GrouponGoodsExtendInfoMapper <T extends Serializable> extends CrudMapper<T>{
	/**
	 * 
	 * @Title
	 * @Description 更新团购商品的时候，先把扩展属性从数据库删除
	 * @author:YK
	 * @CreateDate:2016年8月1日 下午4:36:51
	 * @param grouponId
	 * @return int
	 */
    public int deleteGrouponGoodsExtendInfoByGrouponGoods(Long goodsId);
    
    /**
     * 
     * @Title
     * @Description 根据团购商品id查找扩展属性
     * @author:YK
     * @CreateDate:2016年8月9日 上午10:31:55
     * @param goodsId
     * @return List
     */
    public List<GrouponGoodsExtendInfo> selectByGoodsId(Long goodsId);
    
    /**
     * 
     * @Title
     * @Description 批量插入
     * @author:YK
     * @CreateDate:2016年8月9日 下午4:20:14
     * @param list
     */
    public void insertBatch(List<GrouponGoodsExtendInfo> list);
    
}