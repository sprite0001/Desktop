package com.wooxun.geekdol.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soft863.dolphin.common.CrudMapper;
import com.wooxun.geekdol.system.model.AppUserVillage;

public interface AppUserVillageMapper<T extends Serializable> extends CrudMapper<T>{

	/**
	 * @Title
	 * @Description 根据参数查询用户和小区
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 上午12:08:09
	 * @param param
	 * @return
	 */
	List<AppUserVillage> selectAppUserVillageByParam(Map<String, Object> param);
    /**
     * @Title
     * @Description 根据小区ID查找对应关系
     * @author:张洋
     * @CreateDate:2016年9月14日17:07:29
     * @param param
     * @return
     */
    List<AppUserVillage> selectByVillageId(Map<String, Object> param);

	/**
	 * @Title
	 * @Description 根据参数删除用户和小区关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月24日 下午5:55:11
	 * @param appUserVillage
	 * @return
	 */
	int deleteAppUserVillage(AppUserVillage appUserVillage);

	/**
	 * @Title
	 * @Description 根据用户查询用户和小区关系
	 * @author:kangtianyu
	 * @CreateDate:2016年8月25日 上午9:37:05
	 * @param appUserVillage
	 * @return
	 */
	AppUserVillage selectAppUserVillageByUser(AppUserVillage appUserVillage);
   
}