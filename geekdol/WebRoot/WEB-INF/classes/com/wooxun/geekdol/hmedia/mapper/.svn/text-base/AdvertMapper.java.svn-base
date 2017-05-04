package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.Advert;
import com.wooxun.geekdol.hmedia.vo.AdvertVo;
import com.wooxun.geekdol.system.vo.AppLunboVo;

public interface AdvertMapper<T extends Serializable> extends CrudMapper<T> {
	/**
	 * 
	 * @Title
	 * @Description app接口 显示广告列表
	 * @author:QZG
	 * @CreateDate:2016年8月2日 上午11:54:03
	 * @return
	 */
	public List<Advert> selectPic(AppLunboVo appLunboVo);

	/**
	 * 
	 * @Title
	 * @Description 根据页面条件查询显示列表
	 * @author:QZG
	 * @CreateDate:2016年8月2日 上午11:56:37
	 * @param advertVo
	 * @return
	 */
	public List<AdvertVo> findAll(AdvertVo advertVo);

	/**
	 * 
	 * @Title
	 * @Description 根据页面查询条件返回列表条数
	 * @author:QZG
	 * @CreateDate:2016年8月2日 上午11:56:42
	 * @param advertVo
	 * @return
	 */
	public Long findAllCount(AdvertVo advertVo);

	/**
	 * 
	 * @Title
	 * @Description 逻辑删除广告信息 返回值大于0 返回成功
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午3:18:22
	 * @param advert
	 * @return
	 */
	public int deleteAdvert(Advert advert);

	/**
	 * 
	 * @Title
	 * @Description 广告管理开始 返回值大于0 操作成功
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午3:59:31
	 * @param advert
	 * @return
	 */
	public int start(Advert advert);

	/**
	 * 
	 * @Title
	 * @Description 广告管理 结束 返回值大于0，操作成功
	 * @author:QZG
	 * @CreateDate:2016年8月2日 下午4:00:02
	 * @param advert
	 * @return
	 */
	public int end(Advert advert);

	/**
	 * 
	 * @Title 条件查询
	 * @Description
	 * @author:张洋
	 * @CreateDate:2016年8月1日 下午6:06:10
	 * @return list
	 */
	public List<Advert> selectByParm(Advert adv);
	/**
	 * 
	 * @Title
	 * @Description 定时器 查询满足条件的广告列表
	 * @author:QZG
	 * @CreateDate:2016年8月20日 上午11:18:23
	 * @param advert
	 * @return
	 */
	public List<Advert> queryAdvertByBeignflag(Advert advert);

	/**
	 * @Title
	 * @Description 判断广告编码是否重复
	 * @author:kangtianyu
	 * @CreateDate:2016年9月23日 下午2:01:36
	 * @param param
	 * @return
	 */
	public int findAdvertCode(Map<String, Object> param);

}