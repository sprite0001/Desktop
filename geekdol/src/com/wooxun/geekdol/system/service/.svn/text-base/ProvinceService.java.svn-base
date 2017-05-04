package com.wooxun.geekdol.system.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.system.model.Province;
import com.wooxun.geekdol.system.vo.ProvinceQueryVo;

/**
 * @Title 省管理
 * @Description 省管理的一些具体操作(省的查询，添加，修改，删除，禁用，启用)
 * @version 1.0.0
 * @Author 田振兴	
 * @CreateDate 2016年7月18日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 田振兴	2016年7月18日  上午10:07:49 		创建
 *==========================================================
 * 
 */
public interface ProvinceService <T extends Serializable> extends CrudService<T>{
	/**
	 * 
	 * @Title 省查询
	 * @Description 根据页面查询条件，显示列表
	 * @author:zhougp
	 * @CreateDate:2016年7月18日 上午10:28:09
	 * @param provinceQueryVo
	 * @return List集合
	 */
	public List<Province> selectList(ProvinceQueryVo provinceQueryVo);
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
	 * @Title 省查询
	 * @Description 查询未分配给用户的可用省份
	 * @author:张洋
	 * @CreateDate:2016年7月22日16:14:55
	 * @param provinceQueryVo
	 * @return List集合
	 */
	public List<Province> selectListNoUser(ProvinceQueryVo provinceQueryVo);
	
	/**
	 * 
	 * @Title 省查询(统计条数)
	 * @Description 根据页面查询条件，显示列表条数
	 * @author:zhougp
	 * @CreateDate:2016年7月18日 上午10:28:09
	 * @param provinceQueryVo
	 * @return Long
	 */
	public Long selectListCount(ProvinceQueryVo provinceQueryVo);
	
	
	/**
	 * 
	 * @Title 省查询
	 * @Description 根据ProvinceId查询省份具体信息(修改省信息时候初始化)
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午3:15:47
	 * @param ProvinceId
	 * @return Province对象
	 */
	public Province selectByid(Long ProvinceId);
	
	
	/**
	 * 
	 * @Title 省查询(验证)
	 * @Description 验证省名称或省编码是否已经存在(return 0为不存在，大于0为存在)
	 * @author:田振兴
	 * @CreateDate:2016年7月19日 上午9:41:31
	 * @param province
	 * @return
	 */
	public Long selectProvince(Province province);
	
	
	/**
	 * 
	 * @Title 省添加
	 * @Description 添加省份的一些具体信息
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午2:22:58
	 * @param province
	 * @return int(返回 0为添加失败，返回1为添加成功)
	 */
	public int insertProvince(Province province);
	
	
	/**
	 * 
	 * @Title 省修改
	 * @Description 编辑省份的具体信息
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午5:02:19
	 * @param province
	 * @return int(返回 0为修改失败，返回1为修改成功)
	 */
	public int updateByid(Province province);
		
	
	/**
	 * 
	 * @Title 省修改(逻辑删除，启禁用)
	 * @Description 根据ProvinceId删除省份(逻辑删除)和修改状态（启用，禁用）
	 * @author:田振兴
	 * @CreateDate:2016年7月18日 下午3:23:41
	 * @param province
	 * @return int(返回 0为失败，返回1为成功) 
	 */
	public int delete(Province province);
	
	/**
	 * @Title
	 * @Description 根据省份名字判断省份是否存在
	 * @author:kangtianyu
	 * @CreateDate:2016年9月20日 下午3:37:04
	 * @param provinceName
	 * @param id 
	 * @return
	 */
	public boolean findProvinceName(String provinceName, Long id);
	
	/**
	 * @Title
	 * @Description 更新省信息并且更新排序
	 * @author:kangtianyu
	 * @CreateDate:2016年9月20日 下午7:46:13
	 * @param province
	 * @return
	 */
	public int updateByidAndOrder(Province province);				
	
	/**
	 * 
	 * @Title
	 * @Description 根据省id与状态查找对应的省份
	 * @author:YK
	 * @CreateDate:2016年9月27日 上午10:18:23
	 * @param id
	 * @return Province
	 */
	public Province hasEffectProvince(Long id);
}
