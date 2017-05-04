package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ComDefine;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.FileUtil;
import com.wooxun.geekdol.hmedia.mapper.AroundStoreCommentMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundStoreCommentReplyMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundStoreVillageMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreVillageMapper;
import com.wooxun.geekdol.hmedia.mapper.CountySuggestStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.CountySuggestStoreTopersonMapper;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundStoreCommentReply;
import com.wooxun.geekdol.hmedia.model.AroundStoreVillage;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.hmedia.model.CountySuggestStore;
import com.wooxun.geekdol.hmedia.model.CountySuggestStoreToperson;
import com.wooxun.geekdol.hmedia.service.AroundStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.system.mapper.AppSetMapper;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.mapper.CountyMapper;
import com.wooxun.geekdol.system.mapper.UserAreaMapper;
import com.wooxun.geekdol.system.mapper.VillageMapper;
import com.wooxun.geekdol.system.model.AppSet;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.County;
import com.wooxun.geekdol.system.model.User;
import com.wooxun.geekdol.system.model.UserArea;
import com.wooxun.geekdol.system.model.Village;
import com.wooxun.geekdol.system.vo.UserAreaVo;
import com.wooxun.geekdol.system.vo.VillageVo;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author YK
 * @CreateDate 2016年7月22日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. YK 2016年7月22日 上午9:46:43 创建
 *           ==========================================================
 * 
 */
@Service
public class AroundStoreServiceImpl extends CrudServiceImpl<AroundStore> implements AroundStoreService<AroundStore> {

	private AroundStoreMapper<AroundStore> aroundStoreMapper;

	@Autowired
	private AroundStoreVillageMapper<AroundStoreVillage> aroundStoreVillageMapper;

	@Autowired
	private AroundStoreCommentMapper<AroundStoreComment> aroundStoreCommentMapper;

	@Autowired
	private AroundStoreCommentReplyMapper<AroundStoreCommentReply> aroundStoreCommentReplyMapper;

	@Autowired
	private AroundSuggestStoreMapper<AroundSuggestStore> aroundSuggestStoreMapper;

	@Autowired
	private AroundSuggestStoreVillageMapper<AroundSuggestStoreVillage> aroundSuggestStoreVillageMapper;

	@Autowired
	private CountySuggestStoreMapper<CountySuggestStore> countySuggestStoreMapper;

	@Autowired
	private CountySuggestStoreTopersonMapper<CountySuggestStoreToperson> countySuggestStoreTopersonMapper;

	@Autowired
	private AttachMapper<Attach> attachMapper;

	@Autowired
	private UserAreaMapper<UserArea> userAreaMapper;

	@Autowired
	private AppSetMapper<AppSet> appSetMapper;

	@Autowired
	private VillageMapper<Village> villageMapper;

	@Autowired
	private CountyMapper<County> countyMapper;

	@Autowired
	public AroundStoreServiceImpl(AroundStoreMapper<AroundStore> aroundStoreMapper) {
		super(aroundStoreMapper);
		this.aroundStoreMapper = aroundStoreMapper;
	}

	@Override
	public List<AroundStoreVo> findAllAroundStore(AroundStoreVo aroundStoreVo) {
		// 设置周边店在数据字典中type类型
		aroundStoreVo.setStoreTypeType(ConstantStr.STORETYPE);
		return aroundStoreMapper.findAllAroundStore(aroundStoreVo);
	}

	@Override
	public Long findAllAroundStoreCount(AroundStoreVo aroundStoreVo) {
		return aroundStoreMapper.findAllAroundStoreCount(aroundStoreVo);
	}

	@Override
	public Long findAroundStoreListCount(Map<String, Object> param) {
		return aroundStoreMapper.selectAroundStoreListCount(param);
	}

	@Override
	public List<AroundStoreVo> findAroundStoreList(Map<String, Object> param) {
		return aroundStoreMapper.selectAroundStoreList(param);
	}

	@Override
	public AroundStoreVo findAroundStore(Long id) {
		return aroundStoreMapper.selectAroundStore(id);
	}

	@Override
	public boolean saveAroundStore(AroundStoreVo aroundStoreVo) {
		// 创建周边店Model变量
		AroundStore aroundStore = new AroundStore();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(aroundStoreVo, aroundStore);
		// 设置需要新增的周边店为启用状态
		aroundStore.setUseStatus(ConstantStr.USER_STATUS_0);
		// 调用mapper方法插入周边店主数据
		int result = aroundStoreMapper.insertSelective(aroundStore);
		if (result <= 0) { // 如果插入失败
			// 返回结果
			return false;
		}
		// 将所属小区的id以,分隔成数组
		String villageIds[] = aroundStoreVo.getVillageId().split(",");
		// 创建Map变量用于存放sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入参数 */
		param.put("id", aroundStore.getId());
		param.put("villageIds", villageIds);
		// 调用mapper方法批量插入周边店与小区映射关系
		result = aroundStoreVillageMapper.insertBatch(param);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateAroundStoreVo(AroundStoreVo aroundStoreVo) {
		AroundStore aroundStore = new AroundStore();
		BeanUtils.copyProperties(aroundStoreVo, aroundStore);
		int result = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
		if (result <= 0) { // 如果更新失败
			// 返回结果
			return false;
		}
		// 根据店铺id删除关系表所有相关的数据
		result = aroundStoreVillageMapper.deleteByAroundStoreId(aroundStore.getId());
		if (result <= 0) { // 如果删除失败
			// 返回结果
			return false;
		}
		// 新增周边店与小区关系表
		String villageIds[] = aroundStoreVo.getVillageId().split(",");
		// 创建Map变量用于存放sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入参数 */
		param.put("id", aroundStore.getId());
		param.put("villageIds", villageIds);
		// 调用mapper方法批量插入周边店与小区映射关系
		result = aroundStoreVillageMapper.insertBatch(param);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteAroundStore(AroundStore aroundStore) {
		// 调用mapper方法根据周边店相关信息更新删除标志位
		int result = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
		if (result >= 0) { // 如果更新成功

			// 调用mapper方法根据周边店id删除关系表中数据
			result = aroundStoreVillageMapper.deleteByAroundStoreId(aroundStore.getId());
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public List<AroundStoreVillage> findAroundStoreVillage(Long id) {
		// 调用mapper方法根据周边店id查找周边店与小区对应关系
		return aroundStoreVillageMapper.selectAroundStoreVillageByAroundStoreId(id);
	}

	@Override
	public Long findAroundStoreCommentListCount(AroundStoreCommentVo aroundStoreCommentVo) {
		// 调用mapper方法根据周边店评论的相关信息查找周边店评论的总数
		return aroundStoreCommentMapper.selectAroundStoreCommentListCount(aroundStoreCommentVo);
	}

	@Override
	public List<AroundStoreCommentVo> findAroundStoreCommentList(AroundStoreCommentVo aroundStoreCommentVo) {
		// 调用mapper方法根据周边店评论的相关信息查找周边店评论分页后的列表
		return aroundStoreCommentMapper.selectAroundStoreCommentList(aroundStoreCommentVo);
	}

	@Override
	public AroundStoreCommentVo findAroundStoreComment(Long id) {
		// 调用mapper方法根据周边店评论的主键
		AroundStoreComment aroundStoreComment = aroundStoreCommentMapper.selectByPrimaryKey(id);
		/* 格式化查询出的数据 */
		AroundStoreCommentVo aroundStoreCommentVo = new AroundStoreCommentVo();
		BeanUtils.copyProperties(aroundStoreComment, aroundStoreCommentVo);
		// 返回周边店评论信息
		return aroundStoreCommentVo;
	}

	@Override
	public boolean deleteAroundStoreComment(AroundStoreComment aroundStoreComment) {
		// 调用mapper方法逻辑删除周边店评论
		int result = aroundStoreCommentMapper.updateByPrimaryKeySelective(aroundStoreComment);
		
		if (result >= 0) { // 如果删除成功
			// 调用mapper方法获取周边店信息
			AroundStore temp = aroundStoreMapper.selectByPrimaryKey(aroundStoreComment.getAroundStoreId());
			// 创建周边店对象用于更新数据
			AroundStore aroundStore = new AroundStore();
			/* 设置相关属性 */
			aroundStore.setId(aroundStoreComment.getAroundStoreId());
			aroundStore.setReplyNumber(temp.getReplyNumber()-1);
			aroundStore.setUpdEac(temp.getUpdEac());
			editAttr(aroundStore);
			// 调用mapper方法进行更新
			result = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
		} else {
			return false;
		}

		if (result >= 0) { // 如果删除成功

			// 创建周边店评论的评论对象
			AroundStoreCommentReply aroundStoreCommentReply = new AroundStoreCommentReply();
			// 设置删除标志位为1
			aroundStoreCommentReply.setDelFlag(ConstantStr.DELETE_Y);
			// 设置周边店评论的id
			aroundStoreCommentReply.setAroundStoreCommentId(aroundStoreComment.getId());
			// 设置更新日期
			aroundStoreCommentReply.setUpdYmdhms(new Date());
			// 设置更新人id
			aroundStoreCommentReply.setUpdId(getUserId());
			// 创建Vo对象用于查询个数
			AroundStoreCommentReplyVo aroundStoreCommentReplyVo = new AroundStoreCommentReplyVo();
			// 设置参数
			aroundStoreCommentReplyVo.setAroundStoreCommentId(aroundStoreComment.getId());
			// 获取周边店评论的评论的数量
			Long count = aroundStoreCommentReplyMapper
					.selectAroundStoreCommentReplyListCount(aroundStoreCommentReplyVo);
			if (count > 0) {
				// 删除关系表数据
				result = aroundStoreCommentReplyMapper.updateByAroundStoreCommentId(aroundStoreCommentReply);
			}
		}

		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public AroundStoreCommentVo findAroundStoreCommentDetail(Long id) {
		// 调用mapper方法根据周边店评论的id查询周边店评论的信息
		return aroundStoreCommentMapper.selectAroundStoreCommentDetail(id);
	}

	@Override
	public Long findAroundStoreCommentReplyListCount(AroundStoreCommentReplyVo aroundStoreCommentReplyVo) {
		// 调用mapper方法查询符合查询条件的周边店评论的评论的总数量
		return aroundStoreCommentReplyMapper.selectAroundStoreCommentReplyListCount(aroundStoreCommentReplyVo);
	}

	@Override
	public List<AroundStoreCommentReplyVo> findAroundStoreCommentReplyList(
			AroundStoreCommentReplyVo aroundStoreCommentReplyVo) {
		// 调用mapper方法查询符合查询条件的周边店评论的评论分页后的列表
		return aroundStoreCommentReplyMapper.selectAroundStoreCommentReplyList(aroundStoreCommentReplyVo);
	}

	@Override
	public AroundStoreCommentReplyVo findAroundStoreCommentReply(Long id) {
		// 调用mapper方法根据id查询周边店评论的评论的信息
		AroundStoreCommentReply aroundStoreCommentReply = aroundStoreCommentReplyMapper.selectByPrimaryKey(id);
		/* 格式化查询出的数据 */
		AroundStoreCommentReplyVo aroundStoreCommentReplyVo = new AroundStoreCommentReplyVo();
		BeanUtils.copyProperties(aroundStoreCommentReply, aroundStoreCommentReplyVo);
		// 返回周边店评论的评论
		return aroundStoreCommentReplyVo;
	}

	@Override
	public boolean deleteAroundStoreCommentReply(AroundStoreCommentReply aroundStoreCommentReply) {
		// 调用mapper方法逻辑删除周边店评论的评论
		int result = aroundStoreCommentReplyMapper.updateByPrimaryKeySelective(aroundStoreCommentReply);
		if (result > 0) { // 如果删除成功
			// 获取周边店评论源数据
			AroundStoreComment original = aroundStoreCommentMapper.selectByPrimaryKey(aroundStoreCommentReply
					.getAroundStoreCommentId());
			// 创建周边店评论model变量
			AroundStoreComment aroundStoreComment = new AroundStoreComment();
			/* 设置相关参数用于更新 */
			aroundStoreComment.setId(aroundStoreCommentReply.getAroundStoreCommentId());
			aroundStoreComment.setUpdEac(original.getUpdEac());
			aroundStoreComment.setReplyNumber(original.getReplyNumber() - 1);
			editAttr(aroundStoreComment);
			// 调用mapper方法用于更新
			result = aroundStoreCommentMapper.updateByPrimaryKeySelective(aroundStoreComment);
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public Map<String, Object> saveAroundSuggestStoreByAS(AroundStoreVo aroundStoreVo, MultipartFile[] propagandafiles,
			Date suggestStart, Date suggestEnd) {
		// 创建Map变量用于返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建周边店对象
		AroundStore aroundStore = new AroundStore();
		// 将Vo中的变量数据放入Model中
		BeanUtils.copyProperties(aroundStoreVo, aroundStore);
		// 调用mapper方法更新周边店数据
		int resultCount = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
		if (resultCount <= 0) { // 如果更新失败
			// 向map中放入失败信息
			result.put("isSuccess", false);
			// 直接返回结果
			return result;
		}
		// 创建本网格推荐店对象
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 设置源id
		aroundSuggestStore.setSourceId(aroundStoreVo.getId());
		// 设置推荐开始时间
		aroundSuggestStore.setSuggestStart(suggestStart);
		// 设置推荐结束时间
		aroundSuggestStore.setSuggestEnd(suggestEnd);
		// 设置推荐标志位
		aroundSuggestStore.setSuggestFlag(ConstantStr.SUGGEST_FLAG_1);
		// 设置推荐种类
		aroundSuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_2);
		// 添加共通属性
		this.addAttr(aroundSuggestStore);
		// 调用mapper方法新增本网格推荐店数据
		resultCount = aroundSuggestStoreMapper.insertSelective(aroundSuggestStore);
		if (resultCount <= 0) { // 如果保存失败
			// 向map中放入失败信息
			result.put("isSuccess", false);
			// 直接返回结果
			return result;
		}
		// 将小区id以,分隔为数组
		String villageIds[] = aroundStoreVo.getVillageId().split(",");
		// 创建Map变量用于存放sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入相关参数 */
		param.put("id", aroundSuggestStore.getId());
		param.put("villageIds", villageIds);

		// 调用mapper方法批量插入周边店与小区映射关系
		resultCount = aroundSuggestStoreVillageMapper.insertBatch(param);
		if (resultCount <= 0) { // 如果插入失败
			// 向map中放入失败信息
			result.put("isSuccess", false);
			// 直接返回结果
			return result;
		}

		/* 从共通文件中获取上传文件所需信息 */
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);

		// 创建文件名列表变量用于存放上传后的宣传图片文件名
		List<String> fileNameList = new ArrayList<String>();
		// 遍历上传的文件
		for (int i = 0; i < propagandafiles.length; i++) {
			// 创建文件对象用于遍历的文件操作
			MultipartFile file = propagandafiles[i];
			if (file.getSize() <= 0) { // 如果文件没有被上传
				// 进入下次循环
				continue;
			}
			// 生成新的文件名
			String newFileName = UUID.randomUUID()
					+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			// 上传文件
			boolean rs = FileUtil.uploadfile(file, realpath, newFileName);
			if (rs) { // 如果文件上传成功

				// 获取文件上传后的相对路径
				newFileName = httppath + newFileName;
				// 向文件名列表中加入新的文件名
				fileNameList.add(newFileName);
			}
		}
		if (fileNameList.size() > 0) {
			// 创建附件对象
			Attach attach = new Attach();
			// 设置附件类型
			attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
			// 设置附件源id
			attach.setOwnerId(aroundSuggestStore.getId());
			// 设置附件对应表名
			attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
			// 设置操作人
			attach.setOpreator(getUserId());
			// 设置共通变量的值
			this.addAttr(attach);
			// 创建Map参数用于存放sql查询参数
			Map<String, Object> sqlParam = new HashMap<String, Object>();
			/* 向Map中放入参数 */
			sqlParam.put("attach", attach);
			sqlParam.put("fileNameList", fileNameList);
			// 调用mapper方法批量插入附件信息
			resultCount = attachMapper.insertBatchByMap(sqlParam);
		}
		/* 向Map变量中放入结果信息 */
		result.put("isSuccess", resultCount > 0 ? true : false);
		result.put("aroundSuggestStoreId", aroundSuggestStore.getId());
		return result;
	}

	@Override
	public Map<String, Object> saveCountySuggestStoreByAS(AroundStoreVo aroundStoreVo, String userId) {
		// 创建Map变量用于返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建临时变量用于存放数据库交互结果
		int resultCount = 0;

		if (!ConstantStr.SUGGEST_FLAG_1.equals(aroundStoreVo.getSuggestFlag())) { // 如果没有被推荐过

			// 创建周边店Model
			AroundStore aroundStore = new AroundStore();
			// 将Vo数据封装进Model中
			BeanUtils.copyProperties(aroundStoreVo, aroundStore);
			// 调用mapper方法更新周边店相关信息
			resultCount = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
			if (resultCount <= 0) { // 如果更新失败
				// 向map中放入失败信息
				result.put("isSuccess", false);
				// 直接返回结果
				return result;
			}
		}
		// 创建本区推荐周边店对象
		CountySuggestStore temp = new CountySuggestStore();
		// 设置源id
		temp.setSourceId(aroundStoreVo.getId());
		// 设置推荐类型
		temp.setSuggestType(ConstantStr.SUGGEST_TYPE_1);
		// 设置更新人
		temp.setUpdId(getUserId());
		// 设置更新时间
		temp.setUpdYmdhms(new Date());
		// 设置删除标志位
		temp.setDelFlag(ConstantStr.DELETE_Y);
		// 查询之前是否进行过本区推荐
		List<CountySuggestStore> cssList = countySuggestStoreMapper.selectCountySuggestStoreByParam(temp);
		// 如果之前推荐过需要进行删除
		if (cssList != null && cssList.size() > 0) {
			// 调用mapper方法删除本区推荐周边店信息
			resultCount = countySuggestStoreMapper.deleteSelective(temp);
			if (resultCount <= 0) { // 如果删除失败
				// 向map中放入失败信息
				result.put("isSuccess", false);
				// 直接返回结果
				return result;
			}
		}
		// 创建本区推荐周边店对象
		CountySuggestStore countySuggestStore = new CountySuggestStore();
		// 设置源id
		countySuggestStore.setSourceId(aroundStoreVo.getId());
		// 设置推荐类型
		countySuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_1);
		// 设置共通属性
		this.addAttr(countySuggestStore);
		// 调用mapper方法保存本区推荐周边店信息
		resultCount = countySuggestStoreMapper.insertSelective(countySuggestStore);
		if (resultCount <= 0) { // 如果保存失败
			// 向map中放入失败信息
			result.put("isSuccess", false);
			// 直接返回结果
			return result;
		}
		// 创建本区推荐周边店对用户关系对象
		CountySuggestStoreToperson countySuggestStoreToperson = new CountySuggestStoreToperson();
		// 将所有用户id以,分隔成数组
		String userIds[] = userId.split(",");
		// 设置本区推荐店id
		countySuggestStoreToperson.setCountySuggestStoreId(countySuggestStore.getId());
		// 设置处理状态
		countySuggestStoreToperson.setHandleStatus(ConstantStr.HANDLE_STATUS_1);
		// 设置处理用户id
		countySuggestStoreToperson.setHandlePersonId(getUserId());
		// 设置处理日期
		countySuggestStoreToperson.setHandleDate(new Date());
		// 设置推荐人id
		countySuggestStoreToperson.setSugPersonId(getUserId());
		// 设置推荐标志位
		countySuggestStoreToperson.setSuggestFlag(ConstantStr.SUGGEST_FLAG_1);
		// 设置推荐时间
		countySuggestStoreToperson.setSugDate(new Date());
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入参数 */
		param.put("countySuggestStoreToperson", countySuggestStoreToperson);
		param.put("userIds", userIds);
		// 调用mapper方法批量保存本区推荐周边店批量信息
		resultCount = countySuggestStoreTopersonMapper.insertBatch(param);
		/* 向Map变量中放入结果信息 */
		result.put("isSuccess", resultCount > 0 ? true : false);
		result.put("countySuggestStoreId", countySuggestStore.getId());
		return result;
	}

	@Override
	public List<User> findHasSugUser(Long aroundStoreId) {
		// 创建用户变量用于返回数据
		List<User> userList = new ArrayList<User>();
		// 创建Model变量用于插入sql查询变量
		CountySuggestStore countySuggestStore = new CountySuggestStore();
		/* 向Model变量中设置属性 */
		countySuggestStore.setSourceId(aroundStoreId);
		countySuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_1);
		// 调用mapper方法根据周边店id查找本区推荐周边店的id
		Long countySuggestStoreId = countySuggestStoreMapper.selectCountySuggestStoreIdBySourceId(countySuggestStore);

		if (countySuggestStoreId == null) { // 如果不存在
			// 返回空的用户列表
			return userList;
		} else { // 如果存在
			// 根据本区推荐周边店id查找本区推荐周边店的关系表
			List<CountySuggestStoreToperson> topersonList = countySuggestStoreTopersonMapper
					.selectBySourceId(countySuggestStoreId);
			// 遍历取关系表中的用户数据添加是用户列表
			for (CountySuggestStoreToperson csst : topersonList) {
				userList.add(csst.getUser());
			}
		}
		// 返回用户列表
		return userList;
	}

	@Override
	public List<UserArea> findUserAreaByParam(Map<String, Object> param) {
		// 调用mapper方法根据参数查找用户和管理域对应关系
		return userAreaMapper.selectUserAreaByParam(param);
	}

	@Override
	public Map<String, Object> judgeAroundSuggestIsMax(AroundStoreVo aroundStoreVo) {
		// 创建Map对象用于返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建Model对象
		AppSet appSet = new AppSet();
		// 设置主键id
		appSet.setId(1l);
		// 调用mapper方法查找App设置的本网格推荐店数量最大值
		Integer recomentNumber = appSetMapper.selectByPrimaryKey(appSet).getRecomentNumber();
		// 将所属小区的id以,分隔成数组
		String villageIds[] = aroundStoreVo.getVillageId().split(",");
		// 因为无法构造批量count查询，所以循环进行查询，不过此方法可在发现满足最大值时直接跳出而不再遍历后面的数据
		for (String villageId : villageIds) {
			// 创建Map变量用于存放sql查询条件
			Map<String, Object> param = new HashMap<String, Object>();
			/* 向map中放入变量 */
			param.put("villageId", Long.valueOf(villageId));
			param.put("suggestFlag", ConstantStr.SUGGEST_FLAG_0);
			// 调用mapper方法查询当前小区已推荐的周边店数量
			Integer villageCount = aroundSuggestStoreVillageMapper.selectCountByVillageId(param);
			if (villageCount == recomentNumber) { // 如果已经达到最大值
				// 创建小区Model对象
				Village village = new Village();
				// 获取小区信息
				village = villageMapper.selectByPrimaryKey(Long.valueOf(villageId));
				/* 向map中放入信息 */
				result.put("isMax", true);
				result.put("villageName", village.getVillageName());
				// 返回结果
				return result;
			}
		}
		// 向map中放入信息
		result.put("isMax", false);
		// 返回结果
		return result;
	}

	@Override
	public List<AroundStoreVillage> findAroundStoreIdByUser(Long userId) {
		// 创建用户和地域关系对象用于封装查询数据
		UserAreaVo userAreaVo = new UserAreaVo();
		// 设置用户id
		userAreaVo.setUserId(userId);
		// 查找用户与地域对应关系
		List<UserArea> userAreaList = userAreaMapper.selectList(userAreaVo);
		// 创建小区列表变量用于封装查询数据
		List<Village> villageTempList = new ArrayList<Village>();
		/* 循环判断用户级别 */
		for (UserArea ua : userAreaList) {
			// 创建小区对象用于封装查询数据
			VillageVo villageVo = new VillageVo();
			if (ConstantStr.AREA_LEVEL_05.equals(ua.getAreaLevel())) { // 05为小区管理员级别
				// 根据主键查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectByPrimaryKey(ua.getAreaId()));
			} else if (ConstantStr.AREA_LEVEL_04.equals(ua.getAreaLevel())) { // 04为网格长级别
				// 设置网格id查询条件
				villageVo.setCommunityId(ua.getAreaId());
				// 根据网格级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if (ConstantStr.AREA_LEVEL_03.equals(ua.getAreaLevel())) { // 03为区管理员级别
				// 设置国家id查询条件
				villageVo.setCountyId(ua.getAreaId());
				// 根据国家级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if (ConstantStr.AREA_LEVEL_02.equals(ua.getAreaLevel())) { // 02为城市管理员级别
				// 设置城市id查询条件
				villageVo.setCityId(ua.getAreaId());
				// 根据城市级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			} else if (ConstantStr.AREA_LEVEL_01.equals(ua.getAreaLevel())) { // 01为省管理员级别
				// 设置省id查询条件
				villageVo.setProvinceId(ua.getAreaId());
				// 根据省级别查询小区信息后加入临时变量中
				villageTempList.add(villageMapper.selectVillageByDiffentLevel(villageVo));
			}
		}
		// 调用mapper方法根据用户查找所拥有的小区
		List<Village> villageList = villageMapper.selectVillageNoAroundStoreByUser(villageTempList);
		// 调用mapper方法根据小区查找周边店和小区对应关系
		return aroundStoreVillageMapper.selectAroundStoreVillageByVillage(villageList);
	}

	@Override
	public boolean checkRecommend(AroundStore aroundStore) {
		// 调用mapper方法查找当前周边店与小区对应关系
		List<AroundStoreVillage> asvList = aroundStoreVillageMapper.selectAroundStoreVillageByAroundStoreId(aroundStore
				.getId());
		// 如果没有对应关系就返回否
		if (asvList == null || asvList.size() == 0) {
			return false;
		}
		// 根据第一个小区的区id查找区信息
		County county = countyMapper.selectByPrimaryKey(asvList.get(0).getVillage().getCounty());
		/* 判断是否开启 */
		if (ConstantStr.RECOMMENDFLAG_0.equals(county.getRecommendFlag())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<AroundStoreVillage> findAroundStoreVillageByVillageId(Long villageId) {
		return aroundStoreVillageMapper.selectAroundStoreVillageByVillageId(villageId);
	}

	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

	@Override
	public List<AroundStore> findAroundStoreByIdList(Map<String, Object> param) {
		return aroundStoreMapper.selectAroundStoreByIdList(param);
	}

	@Override
	public List<String> findAttachById(Long id) {
		// 创建列表变量用于存放图片路径
		List<String> urlList = new ArrayList<String>();
		// 创建Model变量用于存放sql查询数据
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 设置源id
		aroundSuggestStore.setSourceId(id);
		// 设置推荐类型
		aroundSuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_2);
		// 根据相关参数查询本网格推荐周边店信息
		aroundSuggestStore = aroundSuggestStoreMapper.selectAroundSuggestStoreByParam(aroundSuggestStore);
		if (aroundSuggestStore == null) { // 如果没有信息直接返回
			return urlList;
		}
		// 创建Model变量用于存放sql查询数据
		Attach attach = new Attach();
		// 设置附件类型
		attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
		// 设置源id
		attach.setOwnerId(aroundSuggestStore.getId());
		// 设置源表
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
		// 返回所属附件列表
		List<Attach> attachList = attachMapper.selectAttachByParam(attach);
		// 获取其中的路径
		for (Attach ah : attachList) {
			urlList.add(ah.getUrl());
		}
		return urlList;
	}

	@Override
	public List<AroundStoreComment> findCommentList(AroundStoreCommentVo aroundStoreCommentVo) {
		return aroundStoreCommentMapper.selectCommentList(aroundStoreCommentVo);
	}

	@Override
	public int addComment(Long storeId, Long appUserId, String content, boolean illegal, String opreaterIp,
			String paramType) {
		// 创建结果变量
		int result = 0;
		// 获取周边店数据
		AroundStore original = aroundStoreMapper.selectByPrimaryKey(storeId);
		if (original == null) { // 如果评论已经被删除
			return result;
		}
		// 创建周边店Model对象用于更新
		AroundStore aroundStore = new AroundStore();
		/* 设置参数用于更新 */
		aroundStore.setId(storeId);
		aroundStore.setReplyNumber(original.getReplyNumber() + 1);
		aroundStore.setUpdEac(original.getUpdEac() + 1);
		switch (paramType) {
		case ConstantStr.COMMENT_ONESTAR:
			aroundStore.setOneCount(original.getOneCount() + 1);
			break;
		case ConstantStr.COMMENT_TWOSTAR:
			aroundStore.setTwoCount(original.getTwoCount() + 1);
			break;
		case ConstantStr.COMMENT_THREESTAR:
			aroundStore.setThreeCount(original.getThreeCount() + 1);
			break;
		case ConstantStr.COMMENT_FOURSTAR:
			aroundStore.setFourCount(original.getFourCount() + 1);
			break;
		case ConstantStr.COMMENT_FIVESTAR:
			aroundStore.setFiveCount(original.getFiveCount() + 1);
			break;
		default:
			break;
		}
		if (illegal) {
			aroundStore.setIllegalNumber(original.getIllegalNumber() + 1);
		}
		// 更新主表数据
		result = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
		if (result <= 0) { // 如果更新失败则返回
			return result;
		}
		// 创建周边店评论Model变量用于数据插入
		AroundStoreComment aroundStoreComment = new AroundStoreComment();
		// 设置周边店id
		aroundStoreComment.setAroundStoreId(storeId);
		// 设置评论内容
		aroundStoreComment.setContent(content);
		// 设置违规状态
		aroundStoreComment.setIllegalStatus(illegal ? ConstantStr.ILLEAGL_FLAG_WG : ConstantStr.ILLEAGL_FLAG_ZC);
		// 设置评论人id
		aroundStoreComment.setInsId(appUserId);
		// 设置评论人ip
		aroundStoreComment.setOpreaterIp(opreaterIp);
		// 设置评论星级
		aroundStoreComment.setStarType(StringUtils.isEmpty(paramType) ? "" : paramType);
		// 设置相关初始变量
		addInitAttr(aroundStoreComment);
		// 调用mapper插入数据
		result = aroundStoreCommentMapper.insertSelective(aroundStoreComment);
		// 返回结果
		return result;
	}

	@Override
	public int addCommentReply(Long commentId, Long appUserId, String content, boolean illegal, String opreaterIp) {
		// 创建结果变量
		int result = 0;
		// 获取一级评论数据
		AroundStoreComment original = aroundStoreCommentMapper.selectByPrimaryKey(commentId);
		if (original == null) { // 如果评论已经被删除
			return result;
		}
		// 创建评论Model对象用于更新
		AroundStoreComment aroundStoreComment = new AroundStoreComment();
		/* 设置参数用于更新 */
		aroundStoreComment.setId(commentId);
		aroundStoreComment.setReplyNumber(original.getReplyNumber() + 1);
		aroundStoreComment.setUpdEac(original.getUpdEac() + 1);
		if (illegal) {
			aroundStoreComment.setIllegalNumber(original.getIllegalNumber() + 1);
		}
		// 更新主表数据
		result = aroundStoreCommentMapper.updateByPrimaryKeySelective(aroundStoreComment);
		if (result <= 0) { // 如果更新失败则返回
			return 0;
		}
		// 创建周边店评论的回复Model变量用于数据插入
		AroundStoreCommentReply aroundStoreCommentReply = new AroundStoreCommentReply();
		// 设置周边店评论id
		aroundStoreCommentReply.setAroundStoreCommentId(commentId);
		// 设置评论内容
		aroundStoreCommentReply.setContent(content);
		// 设置违规状态
		aroundStoreCommentReply.setIllegalStatus(illegal ? ConstantStr.ILLEAGL_FLAG_WG : ConstantStr.ILLEAGL_FLAG_ZC);
		// 设置评论人id
		aroundStoreCommentReply.setInsId(appUserId);
		// 设置评论人ip
		aroundStoreCommentReply.setOpreaterIp(opreaterIp);
		// 设置相关初始变量
		addInitAttr(aroundStoreCommentReply);
		// 调用mapper插入数据
		result = aroundStoreCommentReplyMapper.insertSelective(aroundStoreCommentReply);
		// 返回结果
		return result;
	}

	@Override
	public int addStoreParam(AroundStore aroundStore) {
		return aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
	}

	@Override
	public void updateAroundStoreComment(AroundStoreComment aroundStoreComment) {
		aroundStoreCommentMapper.updateByPrimaryKeySelective(aroundStoreComment);
	}

	@Override
	public int addCommentParam(AroundStoreComment aroundStoreComment) {
		return aroundStoreCommentMapper.updateByPrimaryKeySelective(aroundStoreComment);
	}

	@Override
	public AroundStoreComment getAroundStoreCommentByDay(Long storeId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("storeId", storeId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		List<AroundStoreComment> ascList = aroundStoreCommentMapper.selectAroundStoreCommentByDay(param);
		if (ascList != null && ascList.size() > 0) {
			// 返回结果
			return ascList.get(0);
		} else {
			// 返回结果
			return null;
		}
	}

	private void addInitAttr(AroundStoreComment _temp) {
		_temp.setLikesNumber(0);
		_temp.setHateNumber(0);
		_temp.setViewNumber(0);
		_temp.setReplyNumber(0);
		_temp.setIllegalNumber(0);
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	private void addInitAttr(AroundStoreCommentReply _temp) {
		_temp.setViewNumber(0);
		_temp.setReplyNumber(0);
		_temp.setIllegalNumber(0);
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
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

	private void addAttr(CountySuggestStore _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	private void addAttr(Attach _temp) {
		Long userId = getUserId();
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(userId);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	private void editAttr(AroundStoreComment _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

	@Override
	public boolean getCommentByDay(Long commentId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("commentId", commentId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		AroundStoreComment aroundStoreComment = aroundStoreCommentMapper.selectCommentByDay(param);
		if (aroundStoreComment != null) {
			// 返回结果
			return true;
		} else {
			// 返回结果
			return false;
		}
	}

	@Override
	public boolean getAroundStoreByDay(Long aroundStoreId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("aroundStoreId", aroundStoreId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		AroundStore aroundStore = aroundStoreMapper.selectAroundStoreByDay(param);
		if (aroundStore != null) {
			// 返回结果
			return true;
		} else {
			// 返回结果
			return false;
		}
	}

	@Override
	public boolean judgeStatus(Long storeId) {
		int result = aroundStoreMapper.selectStatus(storeId);
		return result > 0 ? true : false;
	}
	
	private void editAttr(AroundStore _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}

}
