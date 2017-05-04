package com.wooxun.geekdol.hmedia.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.vo.CountyQueryVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月28日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年7月28日  下午4:35:59 		创建
 *==========================================================
 * 
 */
public interface  RecommendService <T extends Serializable> extends CrudService<T>{
    /**
     * 
     * @Title
     * @Description 心媒体推荐功能 查询满足条件列表
     * @author:QZG
     * @CreateDate:2016年7月28日 下午4:42:13
     * @param countyQueryVo
     * @return
     */
    public List<CountyQueryVo> findRecommend(CountyQueryVo countyQueryVo);
    /**
     * 
     * @Title
     * @Description 心媒体推荐功能  查询满足条件列表条数
     * @author:QZG
     * @CreateDate:2016年7月28日 下午4:43:08
     * @param countyQueryVo
     * @return
     */
    public Long  findRecommendCount(CountyQueryVo countyQueryVo);
    /**
     * 
     * @Title
     * @Description 心媒体推荐删除功能   删除成功 返回true
     * @author:QZG
     * @CreateDate:2016年7月29日 上午9:56:31
     * @param county
     * @return
     */
    public boolean deleteRecommend(County county);
    /**
     * 
     * @Title
     * @Description 心媒体推荐禁用功能  返回值大于0 禁用成功
     * @author:QZG
     * @CreateDate:2016年7月29日 上午11:24:11
     * @param county
     * @return
     */
    public boolean disableRecommend(County county);
    /**
     * 
     * @Title
     * @Description 心媒体启用功能 返回值大于0 启用成功
     * @author:QZG
     * @CreateDate:2016年7月29日 上午11:24:48
     * @param county
     * @return
     */
    public boolean enableRecommend(County county);
}
