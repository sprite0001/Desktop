package com.wooxun.geekdol.hmedia.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.hmedia.model.IntimateNews;
import com.wooxun.geekdol.hmedia.vo.AppIntimateNewsVo;
import com.wooxun.geekdol.hmedia.vo.IntimateNewsVo;

public interface IntimateNewsMapper<T extends Serializable> extends CrudMapper<T> {

	/**
	 * 
	 * @Title
	 * @Description 查询verifyStatus为0的贴心报总数量
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:55
	 * @param searchIntimateNews
	 * @return
	 */
	public Long queryCountByParams(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 查询verifyStatus为0的贴心报列表
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:55
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsVo> queryIntimateNewsByParams(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查出贴心报封装后的vo
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:33:06
	 * @param id
	 * @return
	 */
	public IntimateNewsVo queryIntimateNewsVoById(Long id);

	/**
	 * 
	 * @Title
	 * @Description 找到已提交的贴心报总数量(verifyStatus>0)
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public Long queryYitijiaoCount(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 找到已提交的贴心报总列表(verifyStatus>0)
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsVo> queryYitijiaoList(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 找查找已提交的贴心报总数（即 verifyStatus>0 自媒体专用）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public Long querySelfYitijiaoCount(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 找到已提交的贴心报总列表(verifyStatus>0 自媒体专用)
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsVo> querySelfYitijiaoList(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 查找已审核的贴心报总数量（即 verifyStatus=2）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public Long queryYishenheCount(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 查找已审核的贴心报列表（即 verifyStatus=2）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsVo> querYishenheList(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 查找已审核的贴心报列表（即 verifyStatus=2）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public List<IntimateNewsVo> querotherpersonList(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 查找已审核的贴心报总数量（即 verifyStatus=2）
	 * @author:王肖东
	 * @CreateDate:2016年8月1日 下午11:31:30
	 * @param searchIntimateNews
	 * @return
	 */
	public Long querotherpersonListCount(IntimateNewsVo searchIntimateNews);

	/**
	 * 
	 * @Title
	 * @Description 根据贴心报id查询贴心报信息
	 * @author:QZG
	 * @CreateDate:2016年8月10日 下午11:16:47
	 * @param id
	 * @return
	 */
	public IntimateNewsVo selectIntimateNews(Long id);

	/**
	 * 
	 * @Title
	 * @Description app查询贴心报列表
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午2:54:31
	 * @param intimateNewsVo
	 * @return
	 */
	public List<AppIntimateNewsVo> appQueryIntimateNewsByParams(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 贴心报推荐 查询 app调用 按浏览量降序排列
	 * @author:王肖东
	 * @CreateDate:2016年8月18日 上午9:28:56
	 * @param intimateNewsVo
	 * @return
	 */
	public List<IntimateNews> queryListByParamOrder(IntimateNewsVo intimateNewsVo);

	/**
	 * 
	 * @Title
	 * @Description 判断当前用户是否对贴心报进行过点赞或者倒赞
	 * @author:王肖东
	 * @CreateDate:2016年8月16日 下午11:05:18
	 * @param param
	 * @return
	 */
	IntimateNews selectIntimateNewsByDay(Map<String, Object> param);

}