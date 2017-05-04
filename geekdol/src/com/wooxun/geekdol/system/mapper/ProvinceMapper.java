package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.vo.ProvinceQueryVo;

public interface ProvinceMapper <T extends Serializable> extends CrudMapper<T>{
	
	/**
	 * 
	 * @Title 省管理
	 * @Description 查询未分配给用户的可用省份
	 * @author:张洋
	 * @CreateDate:2016年7月22日16:14:55
	 * @param provinceQueryVo
	 * @return List集合
	 */
	public List<Province> selectListNoUser(ProvinceQueryVo provinceQueryVo);
	
	/**
	 * 
	 * @Title 
	 * @Description 省下拉框
	 * @author:田振兴
	 * @CreateDate:2016年8月11日 上午11:06:39
	 * @param provinceQueryVo
	 * @return
	 */
	public List<Province> findProvince(ProvinceQueryVo provinceQueryVo);
	
	/**
	 * 
	 * @Title 省管理
	 * @Description 根据页面查询条件，显示列表
	 * @author:zhougp
	 * @CreateDate:2016年7月18日 上午10:28:09
	 * @param provinceQueryVo
	 * @return List集合
	 */
	public List<Province> selectList(ProvinceQueryVo provinceQueryVo);
	
	/**
	 * 
	 * @Title 省管理
	 * @Description 根据页面查询条件，显示列表计数
	 * @author:zhougp
	 * @CreateDate:2016年7月18日 上午10:28:09
	 * @param provinceQueryVo
	 * @return Long
	 */
	public Long selectListCount(ProvinceQueryVo provinceQueryVo);
	
	
	/**
	 * 
	 * @Title 省管理
	 * @Description 验证省名称或省编码是否已经存在(return 0为不存在，大于0为存在)
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 上午9:39:37
	 * @param provinceQueryVo
	 * @return
	 */
	public Long selectProvince(Province province);

	/**
	 * @Title
	 * @Description 验证省名称是否存在
	 * @author:kangtianyu
	 * @CreateDate:2016年9月20日 下午3:39:54
	 * @param param
	 * @return
	 */
	public int findProvinceName(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 更新省信息(可以更新排序为空)
	 * @author:kangtianyu
	 * @CreateDate:2016年9月20日 下午7:48:02
	 * @param province
	 * @return
	 */
	public int updateByPrimaryKeySelectiveAndOrder(Province province);
	
	/**
	 * 
	 * @Title
	 * @Description 根据省id判断是否启用
	 * @author:YK
	 * @CreateDate:2016年9月27日 上午10:18:23
	 * @param params
	 * @return Province
	 */
	public Province hasEffectProvince(Map<String,Object> params);
}