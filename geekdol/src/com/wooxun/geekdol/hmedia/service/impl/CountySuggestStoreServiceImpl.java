package com.wooxun.geekdol.hmedia.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.CountySuggestStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.CountySuggestStoreTopersonMapper;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.CountySuggestStore;
import com.wooxun.geekdol.hmedia.model.CountySuggestStoreToperson;
import com.wooxun.geekdol.hmedia.service.CountySuggestStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
import com.wooxun.geekdol.hmedia.vo.CountySuggestStoreVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.User;

/**
 * 本区推荐周边店Service实现
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月9日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月9日  上午11:45:51 		创建
*==========================================================
*
 */
@Service
public class CountySuggestStoreServiceImpl extends CrudServiceImpl<CountySuggestStore>
		implements CountySuggestStoreService<CountySuggestStore> {
	
	private CountySuggestStoreMapper<CountySuggestStore> countySuggestStoreMapper;
	
	@Autowired
	private CountySuggestStoreTopersonMapper<CountySuggestStoreToperson> countySuggestStoreTopersonMapper;
	
	@Autowired
	private AroundSuggestStoreMapper<AroundSuggestStore> aroundSuggestStoreMapper;
	
	@Autowired
	private AttachMapper<Attach> attachMapper;
	
	@Autowired
	public CountySuggestStoreServiceImpl(CountySuggestStoreMapper<CountySuggestStore> countySuggestStoreMapper) {
		super(countySuggestStoreMapper);
		this.countySuggestStoreMapper = countySuggestStoreMapper;
	}

	@Override
	public Long findCountySuggestStoreListCount(Map<String, Object> param) {
		// 调用mapper方法查询符合条件的本区推荐周边店的总记录数
		return countySuggestStoreTopersonMapper.selectCountySuggestStoreListCount(param);
	}

	@Override
	public List<CountySuggestStoreVo> findCountySuggestStoreList(
			Map<String, Object> param) {
		// 调用mapper方法查询符合条件的本区推荐周边店的分页列表数据
		return countySuggestStoreTopersonMapper.selectCountySuggestStoreList(param);
	}

	@Override
	public CountySuggestStoreToperson findCountySuggestStoreTopersonById(Long id) {
		// 调用mapper方法根据主键id查找本区推荐周边店关系表信息
		return countySuggestStoreTopersonMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateCountySuggestStoreToperson(
			CountySuggestStoreToperson countySuggestStoreToperson) {
		// 调用mapper方法更新本区推荐周边店关系表信息
		int result = countySuggestStoreTopersonMapper.updateByPrimaryKeySelective(countySuggestStoreToperson);
		// 返回结果
		return result>0?true:false;
	}

	@Override
	public CountySuggestStore findCountySuggestStoreById(Long id) {
		return countySuggestStoreMapper.findCountySuggestStoreById(id);
	}

	@Override
	public Map<String, Object> acceptSuggest(AroundSuggestStoreVo aroundSuggestStoreVo,
			String flag, Long sid, Long countySuggestStoreToPersonId) {
		// 创建Map变量用于存放结果
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建本网格推荐周边店Model对象用于插入数据
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 创建附件Model对象用于插入数据
		Attach attach = new Attach();
		/* 设置查询参数 */
		attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
		// 判断数据来源
		if (ConstantStr.SUGGEST_TYPE_1.equals(flag)) { // 如果是周边店进行的推荐
			// 将Vo中需要插入的数据放入Model中
			BeanUtils.copyProperties(aroundSuggestStoreVo, aroundSuggestStore);
			// 向对象中插入源id
			aroundSuggestStore.setSourceId(sid);
			// 向对象中插入推荐类型
			aroundSuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_3);
			// 向对象中插入推荐标志位
			aroundSuggestStore.setSuggestFlag(ConstantStr.SUGGEST_FLAG_1);
		} else if (ConstantStr.SUGGEST_TYPE_2.equals(flag)) { // 如果是本网格进行的推荐
			// 根据源id查询本网格推荐周边店信息
			aroundSuggestStore = aroundSuggestStoreMapper.selectByPrimaryKey(sid);
			// 设置Model对象id为空
			aroundSuggestStore.setId(null);
			/* 设置共通数据 */
			addAttr(aroundSuggestStore);
			// 设置推荐开始时间
			aroundSuggestStore.setSuggestStart(aroundSuggestStoreVo.getSuggestStart());
			// 设置推荐结束时间
			aroundSuggestStore.setSuggestEnd(aroundSuggestStoreVo.getSuggestEnd());
		}
		// 调用mapper方法插入数据
		int res = aroundSuggestStoreMapper.insertSelective(aroundSuggestStore);
		
		// 创建对象用于更新数据
		CountySuggestStoreToperson countySuggestStoreToperson = new CountySuggestStoreToperson();
		/* 设置相关参与用于更新 */
		countySuggestStoreToperson.setId(countySuggestStoreToPersonId);
		countySuggestStoreToperson.setHandleStatus(ConstantStr.HANDLE_STATUS_2);
		// 调用mapper方法更新数据
		res = countySuggestStoreTopersonMapper.updateByPrimaryKeySelective(countySuggestStoreToperson);
		
		/* 向Map变量中放入结果 */
		result.put("isSuccess", res>0?true:false);
		result.put("aroundSuggestStoreId", aroundSuggestStore.getId());
		// 返回结果
		return result;
	}
	
	private void addAttr(AroundSuggestStore _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

}
