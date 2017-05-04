package com.wooxun.geekdol.hmedia.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.wooxun.geekdol.hmedia.mapper.AroundStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreCommentMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreCommentReplyMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStorePromotionMapper;
import com.wooxun.geekdol.hmedia.mapper.AroundSuggestStoreVillageMapper;
import com.wooxun.geekdol.hmedia.mapper.CountySuggestStoreMapper;
import com.wooxun.geekdol.hmedia.mapper.CountySuggestStoreTopersonMapper;
import com.wooxun.geekdol.hmedia.model.AroundStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStore;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreComment;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreCommentReply;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStorePromotion;
import com.wooxun.geekdol.hmedia.model.AroundSuggestStoreVillage;
import com.wooxun.geekdol.hmedia.model.CountySuggestStore;
import com.wooxun.geekdol.hmedia.model.CountySuggestStoreToperson;
import com.wooxun.geekdol.hmedia.service.AroundSuggestStoreService;
import com.wooxun.geekdol.hmedia.vo.AroundStoreVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentReplyVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreCommentVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStorePromotionVo;
import com.wooxun.geekdol.hmedia.vo.AroundSuggestStoreVo;
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
 * 
 * @Title
 * @Description 本网格推荐周边店Service实现
 * @version 1.0.0
 * @Author kangtianyu
 * @CreateDate 2016年8月5日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. kangtianyu 2016年8月5日 下午5:00:35 创建
 *           ==========================================================
 *
 */
@Service
public class AroundSuggestStoreServiceImpl extends CrudServiceImpl<AroundSuggestStore> implements
		AroundSuggestStoreService<AroundSuggestStore> {

	private AroundSuggestStoreMapper<AroundSuggestStore> aroundSuggestStoreMapper;

	@Autowired
	private AroundSuggestStoreVillageMapper<AroundSuggestStoreVillage> aroundSuggestStoreVillageMapper;

	@Autowired
	private AroundStoreMapper<AroundStore> aroundStoreMapper;

	@Autowired
	private AppSetMapper<AppSet> appSetMapper;

	@Autowired
	private VillageMapper<Village> villageMapper;

	@Autowired
	private AttachMapper<Attach> attachMapper;

	@Autowired
	private AroundSuggestStoreCommentMapper<AroundSuggestStoreComment> aroundSuggestStoreCommentMapper;

	@Autowired
	private AroundSuggestStoreCommentReplyMapper<AroundSuggestStoreCommentReply> aroundSuggestStoreCommentReplyMapper;

	@Autowired
	private CountySuggestStoreMapper<CountySuggestStore> countySuggestStoreMapper;

	@Autowired
	private CountySuggestStoreTopersonMapper<CountySuggestStoreToperson> countySuggestStoreTopersonMapper;

	@Autowired
	private AroundSuggestStorePromotionMapper<AroundSuggestStorePromotion> aroundSuggestStorePromotionMapper;

	@Autowired
	private UserAreaMapper<UserArea> userAreaMapper;

	@Autowired
	private CountyMapper<County> countyMapper;

	@Autowired
	public AroundSuggestStoreServiceImpl(AroundSuggestStoreMapper<AroundSuggestStore> aroundSuggestStoreMapper) {
		super(aroundSuggestStoreMapper);
		this.aroundSuggestStoreMapper = aroundSuggestStoreMapper;
	}

	@Override
	public List<AroundSuggestStoreVo> findAroundSuggestStoreList(Map<String, Object> param) {
		return aroundSuggestStoreMapper.selectAroundSuggestStoreList(param);
	}

	@Override
	public Long findAroundSuggestStoreListCount(Map<String, Object> param) {
		return aroundSuggestStoreMapper.selectAroundSuggestStoreListCount(param);
	}

	@Override
	public Map<String, Object> judgeAroundSuggestIsMax(AroundSuggestStoreVo aroundSuggestStoreVo) {
		// 创建Map对象用于返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建Model对象
		AppSet appSet = new AppSet();
		// 设置主键id
		appSet.setId(1l);
		// 调用mapper方法查找App设置的本网格推荐店数量最大值
		Integer recomentNumber = appSetMapper.selectByPrimaryKey(appSet).getRecomentNumber();
		// 将所属小区的id以,分隔成数组
		String villageIds[] = aroundSuggestStoreVo.getVillageId().split(",");
		// 因为无法构造批量count查询，所以循环进行查询，不过此方法可在发现满足最大值时直接跳出而不再遍历后面的数据
		for (String villageId : villageIds) {
			// 创建Map变量用于存放sql查询条件
			Map<String, Object> param = new HashMap<String, Object>();
			/* 向map中放入变量 */
			param.put("villageId", Long.valueOf(villageId));
			param.put("suggestFlag", aroundSuggestStoreVo.getSuggestFlag());
			param.put("aroundSuggestStoreId", aroundSuggestStoreVo.getId());
			// 调用mapper方法查询当前小区已推荐的本网格推荐周边店数量
			Integer villageCount = aroundSuggestStoreVillageMapper.selectCountByVillageId(param);
			if (villageCount >= recomentNumber) { // 如果已经达到最大值
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
	public boolean saveAroundSuggestStore(AroundSuggestStoreVo aroundSuggestStoreVo, MultipartFile[] propagandafiles) {
		// 创建本网格推荐本网格推荐周边店Model对象
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(aroundSuggestStoreVo, aroundSuggestStore);
		// 设置需要新增的本网格推荐周边店为启用状态
		aroundSuggestStore.setUseStatus(ConstantStr.USER_STATUS_0);
		// 设置推荐种类
		aroundSuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_2);
		// 调用mapper方法插入本网格推荐周边店主数据
		int result = aroundSuggestStoreMapper.insertSelective(aroundSuggestStore);
		if (result <= 0) { // 如果插入失败
			// 返回结果
			return false;
		}
		// 将所属小区的id以,分隔成数组
		String villageIds[] = aroundSuggestStoreVo.getVillageId().split(",");
		// 创建Map变量用于存放sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入参数 */
		param.put("id", aroundSuggestStore.getId());
		param.put("villageIds", villageIds);
		// 调用mapper方法批量插入本网格推荐周边店与推荐小区映射关系
		result = aroundSuggestStoreVillageMapper.insertBatch(param);
		if (result <= 0) { // 如果插入失败
			// 返回结果
			return false;
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
			result = attachMapper.insertBatchByMap(sqlParam);
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public AroundSuggestStoreVo findAroundSuggestStore(Long id) {
		// 调用mapper方法获取本网格推荐周边店信息
		AroundSuggestStoreVo aroundSuggestStoreVo = aroundSuggestStoreMapper.selectAroundSuggestStore(id);
		if (aroundSuggestStoreVo == null ) {
			return null;
		}
		if (aroundSuggestStoreVo.getSourceId() != null) { // 如果是从周边店推荐来的

			// 调用mapper方法获取周边店信息
			AroundStoreVo aroundStoreVo = aroundStoreMapper.selectAroundStore(aroundSuggestStoreVo.getSourceId());
			// 保存本网格推荐周边店主键id
			Long lastId = aroundSuggestStoreVo.getId();
			// 保存本网格推荐周边店推荐标志位
			String lastSuggestFlag = aroundSuggestStoreVo.getSuggestFlag();
			// 将周边店相关信息复制给本网格推荐周边店
			BeanUtils.copyProperties(aroundStoreVo, aroundSuggestStoreVo);
			/* 将原信息赋值给本网格推荐周边店 */
			aroundSuggestStoreVo.setId(lastId);
			aroundSuggestStoreVo.setSuggestFlag(lastSuggestFlag);
		}
		// 返回结果
		return aroundSuggestStoreVo;
	}

	@Override
	public boolean updateAroundSuggestStore(AroundSuggestStoreVo aroundSuggestStoreVo, MultipartFile[] propagandafiles,
			String[] attchfiles) {
		// 创建本网格推荐周边店Model对象
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(aroundSuggestStoreVo, aroundSuggestStore);
		// 调用mapper方法更新本网格推荐周边店主数据
		int result = aroundSuggestStoreMapper.updateByPrimaryKeySelective(aroundSuggestStore);
		if (result <= 0) { // 如果更新失败
			// 返回结果
			return false;
		}
		// 调用mapper方法删除
		result = aroundSuggestStoreVillageMapper.deleteByAroundSuggestStoreId(aroundSuggestStore.getId());
		if (result <= 0) { // 如果删除失败
			// 返回结果
			return false;
		}
		// 将所属小区的id以,分隔成数组
		String villageIds[] = aroundSuggestStoreVo.getVillageId().split(",");
		// 创建Map变量用于存放sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入参数 */
		param.put("id", aroundSuggestStore.getId());
		param.put("villageIds", villageIds);
		// 调用mapper方法批量插入本网格推荐周边店与推荐小区映射关系
		result = aroundSuggestStoreVillageMapper.insertBatch(param);
		if (result <= 0) { // 如果插入失败
			// 返回结果
			return false;
		}
		/* 从共通文件中获取上传文件所需信息 */
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);

		// 创建附件Model为了封装查询参数
		Attach attachTemp = new Attach();
		/* 设置查询参数 */
		attachTemp.setOwnerId(aroundSuggestStore.getId());
		attachTemp.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
		// 根据条件删除附件
		result = attachMapper.deleteByParam(attachTemp);
		if (result <= 0) { // 如果删除失败
			// 返回结果
			return false;
		}
		// 创建文件名列表变量用于存放上传后的宣传图片文件名
		List<String> fileNameList = new ArrayList<String>();
		// 如果之前存在附件则直接进行赋值
		if (attchfiles != null && attchfiles.length > 0) {
			fileNameList = Arrays.asList(attchfiles);
		}
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
			result = attachMapper.insertBatchByMap(sqlParam);
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public List<AroundSuggestStoreVillage> findAroundSuggestStoreVillage(Long id) {
		// 调用mapper方法根据本网格推荐周边店id查找本网格推荐周边店与小区对应关系
		return aroundSuggestStoreVillageMapper.selectAroundSuggestStoreVillageByASSId(id);
	}

	@Override
	public boolean deleteAroundSuggestStore(AroundSuggestStore aroundSuggestStore) {
		// 调用mapper方法根据本网格推荐周边店相关信息更新删除标志位
		int result = aroundSuggestStoreMapper.updateByPrimaryKeySelective(aroundSuggestStore);
		if (result >= 0) { // 如果更新成功

			// 调用mapper方法根据本网格推荐周边店id删除关系表中数据
			result = aroundSuggestStoreVillageMapper.deleteByAroundSuggestStoreId(aroundSuggestStore.getId());
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public Long findAroundSuggestStoreCommentListCount(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo) {
		// 调用mapper方法查找符合条件的本网格推荐周边店评论的总记录数
		return aroundSuggestStoreCommentMapper.selectAroundSuggestStoreCommentListCount(aroundSuggestStoreCommentVo);
	}

	@Override
	public List<AroundSuggestStoreCommentVo> findAroundSuggestStoreCommentList(
			AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo) {
		// 调用mapper方法查找符合条件的本网格推荐周边店评论分页后的列表
		return aroundSuggestStoreCommentMapper.selectAroundSuggestStoreCommentList(aroundSuggestStoreCommentVo);
	}

	@Override
	public AroundSuggestStoreComment findAroundSuggestStoreComment(Long id) {
		// 调用mapper方法根据主键id查找本网格推荐周边店评论的信息
		return aroundSuggestStoreCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean deleteAroundSuggestStoreComment(AroundSuggestStoreComment aroundSuggestStoreComment) {
		// 调用mapper方法逻辑删除本网格推荐周边店评论
		int result = aroundSuggestStoreCommentMapper.updateByPrimaryKeySelective(aroundSuggestStoreComment);
		
		if (result >= 0) { // 如果删除成功
			// 调用mapper方法获取周边店信息
			AroundSuggestStore temp = aroundSuggestStoreMapper.selectByPrimaryKey(aroundSuggestStoreComment.getAroundSuggestStoreId());
			// 创建周边店对象用于更新数据
			AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
			/* 设置相关属性 */
			aroundSuggestStore.setId(aroundSuggestStoreComment.getAroundSuggestStoreId());
			aroundSuggestStore.setReplyNumber(temp.getReplyNumber()-1);
			aroundSuggestStore.setUpdEac(temp.getUpdEac());
			editAttr(aroundSuggestStore);
			// 调用mapper方法进行更新
			result = aroundSuggestStoreMapper.updateByPrimaryKeySelective(aroundSuggestStore);
		} else {
			return false;
		}

		if (result >= 0) { // 如果删除成功

			// 创建本网格推荐本网格推荐周边店评论的评论对象
			AroundSuggestStoreCommentReply aroundSuggestStoreCommentReply = new AroundSuggestStoreCommentReply();
			// 设置删除标志位为1
			aroundSuggestStoreCommentReply.setDelFlag(ConstantStr.DELETE_Y);
			// 设置本网格推荐周边店评论的id
			aroundSuggestStoreCommentReply.setAroundSuggestStoreCommentId(aroundSuggestStoreComment.getId());
			// 设置更新日期
			aroundSuggestStoreCommentReply.setUpdYmdhms(new Date());
			// 设置更新人id
			aroundSuggestStoreCommentReply.setUpdId(getUserId());
			// 创建Vo对象用于查询个数
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo = new AroundSuggestStoreCommentReplyVo();
			// 设置参数
			aroundSuggestStoreCommentReplyVo.setAroundSuggestStoreCommentId(aroundSuggestStoreComment.getId());
			// 获取周边店评论的评论的数量
			Long count = aroundSuggestStoreCommentReplyMapper
					.selectAroundSuggestStoreCommentReplyListCount(aroundSuggestStoreCommentReplyVo);
			if (count > 0) {
				// 删除关系表数据
				result = aroundSuggestStoreCommentReplyMapper
						.updateByAroundSuggestStoreCommentId(aroundSuggestStoreCommentReply);
			}
		}

		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public AroundSuggestStoreCommentVo findAroundSuggestStoreCommentDetail(Long id) {
		// 调用mapper方法根据主键id查询本网格推荐周边店评论的信息
		return aroundSuggestStoreCommentMapper.selectAroundSuggestStoreCommentDetail(id);
	}

	@Override
	public Long findAroundSuggestStoreCommentReplyListCount(
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo) {
		// 调用mapper方法查询符合查询条件的本网格推荐周边店评论的评论的总数量
		return aroundSuggestStoreCommentReplyMapper
				.selectAroundSuggestStoreCommentReplyListCount(aroundSuggestStoreCommentReplyVo);
	}

	@Override
	public List<AroundSuggestStoreCommentReplyVo> findAroundSuggestStoreCommentReplyList(
			AroundSuggestStoreCommentReplyVo aroundSuggestStoreCommentReplyVo) {
		// 调用mapper方法查询符合查询条件的本网格推荐周边店评论的评论分页后的列表
		return aroundSuggestStoreCommentReplyMapper
				.selectAroundSuggestStoreCommentReplyList(aroundSuggestStoreCommentReplyVo);
	}

	@Override
	public AroundSuggestStoreCommentReply findAroundSuggestStoreCommentReply(Long id) {
		// 调用mapper方法根据主键id查找本网格推荐周边店评论的评论的信息
		return aroundSuggestStoreCommentReplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean deleteAroundSuggestStoreCommentReply(AroundSuggestStoreCommentReply aroundSuggestStoreCommentReply) {
		// 调用mapper方法逻辑本网格推荐周边店评论的评论
		int result = aroundSuggestStoreCommentReplyMapper.updateByPrimaryKeySelective(aroundSuggestStoreCommentReply);
		if (result > 0) { // 如果删除成功
			// 获取推荐店评论源数据
			AroundSuggestStoreComment original = aroundSuggestStoreCommentMapper
					.selectByPrimaryKey(aroundSuggestStoreCommentReply.getAroundSuggestStoreCommentId());
			// 创建推荐店评论model变量
			AroundSuggestStoreComment aroundSuggestStoreComment = new AroundSuggestStoreComment();
			/* 设置相关参数用于更新 */
			aroundSuggestStoreComment.setId(aroundSuggestStoreCommentReply.getAroundSuggestStoreCommentId());
			aroundSuggestStoreComment.setUpdEac(original.getUpdEac());
			aroundSuggestStoreComment.setReplyNumber(original.getReplyNumber() - 1);
			editAttr(aroundSuggestStoreComment);
			// 调用mapper方法用于更新
			result = aroundSuggestStoreCommentMapper.updateByPrimaryKeySelective(aroundSuggestStoreComment);
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean suggestAgain(AroundSuggestStoreVo aroundSuggestStoreVo) {
		// 创建本网格推荐周边店Model对象
		AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
		// 将Vo中的数据传入Model中
		BeanUtils.copyProperties(aroundSuggestStoreVo, aroundSuggestStore);
		// 调用mapper方法更新本网格推荐周边店主数据
		int result = aroundSuggestStoreMapper.updateByPrimaryKeySelective(aroundSuggestStore);
		if (result <= 0) { // 如果更新失败
			// 返回结果
			return false;
		}
		// 调用mapper方法删除
		result = aroundSuggestStoreVillageMapper.deleteByAroundSuggestStoreId(aroundSuggestStore.getId());
		if (result <= 0) { // 如果删除失败
			// 返回结果
			return false;
		}
		// 将所属小区的id以,分隔成数组
		String villageIds[] = aroundSuggestStoreVo.getVillageId().split(",");
		// 创建Map变量用于存放sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向Map中放入参数 */
		param.put("id", aroundSuggestStore.getId());
		param.put("villageIds", villageIds);
		// 调用mapper方法批量插入本网格推荐周边店与推荐小区映射关系
		result = aroundSuggestStoreVillageMapper.insertBatch(param);

		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public Map<String, Object> saveCountySuggest(AroundSuggestStoreVo aroundSuggestStoreVo, String userId) {
		// 创建Map变量用于返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建临时变量用于存放数据库交互结果
		int resultCount = 0;
		// 创建本区推荐周边店对象
		CountySuggestStore countySuggestStore = new CountySuggestStore();
		// 设置源id
		countySuggestStore.setSourceId(aroundSuggestStoreVo.getId());
		// 设置推荐类型
		countySuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_2);
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
	public List<User> findHasSugUser(Long aroundSuggestStoreId) {
		// 创建用户变量用于返回数据
		List<User> userList = new ArrayList<User>();
		// 创建Model变量用于插入sql查询变量
		CountySuggestStore countySuggestStore = new CountySuggestStore();
		/* 向Model变量中设置属性 */
		countySuggestStore.setSourceId(aroundSuggestStoreId);
		countySuggestStore.setSuggestType(ConstantStr.SUGGEST_TYPE_2);
		// 调用mapper方法根据本网格推荐周边店id查找本区推荐周边店的id
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
	public Long findAroundSuggestStorePromotionListCount(AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo) {
		// 调用mapper方法查找本网格推荐周边店促销信息总记录数
		return aroundSuggestStorePromotionMapper
				.selectAroundSuggestStorePromotionListCount(aroundSuggestStorePromotionVo);
	}

	@Override
	public List<AroundSuggestStorePromotionVo> findAroundSuggestStorePromotionList(
			AroundSuggestStorePromotionVo aroundSuggestStorePromotionVo) {
		// 调用mapper方法查找本网格推荐周边店促销信息分页列表
		return aroundSuggestStorePromotionMapper.selectAroundSuggestStorePromotionList(aroundSuggestStorePromotionVo);
	}

	@Override
	public boolean saveAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion,
			MultipartFile[] propagandafiles) {
		// 调用mapper方法插入本网格推荐周边店主数据
		int result = aroundSuggestStorePromotionMapper.insertSelective(aroundSuggestStorePromotion);
		if (result <= 0) { // 如果插入失败
			// 返回结果
			return false;
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
			attach.setOwnerId(aroundSuggestStorePromotion.getId());
			// 设置附件对应表名
			attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_10);
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
			result = attachMapper.insertBatchByMap(sqlParam);
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public AroundSuggestStorePromotion findAroundSuggestStorePromotion(Long id) {
		// 调用mapper方法根据本主键获得本网格推荐促销信息
		return aroundSuggestStorePromotionMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion,
			MultipartFile[] propagandafiles) {
		// 调用mapper方法更新本网格推荐周边店主数据
		int result = aroundSuggestStorePromotionMapper.updateByPrimaryKeySelective(aroundSuggestStorePromotion);
		if (result <= 0) { // 如果更新失败
			// 返回结果
			return false;
		}
		/* 从共通文件中获取上传文件所需信息 */
		String realpath = ComDefine.getRealPath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);
		String httppath = ComDefine.getHttppath(ConstantStr.FILE_IMAGE, ConstantStr.MODEL_HMEDIA);

		// 创建附件Model对象作为查询条件
		Attach attachTemp = new Attach();
		/* 设置相关参数 */
		attachTemp.setOwnerId(aroundSuggestStorePromotion.getId());
		attachTemp.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_10);
		// 调用mapper方法删除附件关系
		result = attachMapper.deleteByParam(attachTemp);
		if (result <= 0) { // 如果删除失败
			// 返回结果
			return false;
		}

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
			attach.setOwnerId(aroundSuggestStorePromotion.getId());
			// 设置附件对应表名
			attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_10);
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
			result = attachMapper.insertBatchByMap(sqlParam);
		}
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteAroundSuggestStorePromotion(AroundSuggestStorePromotion aroundSuggestStorePromotion) {
		// 调用mapper方法根据本网格推荐周边店促销相关信息更新删除标志位
		int result = aroundSuggestStorePromotionMapper.updateByPrimaryKeySelective(aroundSuggestStorePromotion);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateAroundSuggestStorePromotionStatus(AroundSuggestStorePromotion aroundSuggestStorePromotion) {
		// 调用mapper方法根据本网格推荐周边店促销相关信息更新状态标志位
		int result = aroundSuggestStorePromotionMapper.updateByPrimaryKeySelective(aroundSuggestStorePromotion);
		// 返回结果
		return result > 0 ? true : false;
	}

	@Override
	public List<Attach> findAttachByParam(Attach attach) {
		return attachMapper.selectAttachByParam(attach);
	}

	@Override
	public List<AroundSuggestStoreVillage> findAroundSuggestStoreIdByUser(Long userId) {
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
		return aroundSuggestStoreVillageMapper.selectAroundSuggestStoreVillageByVillage(villageList);
	}

	@Override
	public boolean checkRecommend(AroundSuggestStore aroundSuggestStore) {
		// 调用mapper方法查找当前本网格推荐周边店与小区对应关系
		List<AroundSuggestStoreVillage> asvList = aroundSuggestStoreVillageMapper
				.selectAroundSuggestStoreVillageByASSId(aroundSuggestStore.getId());
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

	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

	@Override
	public List<AroundSuggestStoreVillage> findAroundSuggestStoreVillageByVillageId(Long villageId) {
		return aroundSuggestStoreVillageMapper.selectAroundSuggestStoreVillageByVillageId(villageId);
	}

	@Override
	public List<AroundSuggestStore> findAroundSuggestStoreByIdList(Map<String, Object> param) {
		return aroundSuggestStoreMapper.selectAroundSuggestStoreByIdList(param);
	}

	@Override
	public List<String> findAttachById(Long id) {
		// 创建列表变量用于存放图片路径
		List<String> urlList = new ArrayList<String>();
		// 创建Model变量用于存放sql查询数据
		Attach attach = new Attach();
		// 设置附件类型
		attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
		// 设置源id
		attach.setOwnerId(id);
		// 设置源表
		attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_09);
		// 调用mapper方法查找所属附件列表
		List<Attach> attachList = attachMapper.selectAttachByParam(attach);
		// 获取其中的路径
		for (Attach ah : attachList) {
			urlList.add(ah.getUrl());
		}
		return urlList;
	}

	@Override
	public AroundSuggestStorePromotion findOnePromotionByASSId(Long id) {
		// 创建Map变量用于存放sql查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装参数信息 */
		param.put("aroundSuggestStoreId", id);
		param.put("oneFlag", true);
		// 调用mapper方法查询最新的促销信息
		List<AroundSuggestStorePromotion> promotionList = aroundSuggestStorePromotionMapper
				.selectPromotionByASSId(param);
		if (promotionList != null && promotionList.size() > 0) {
			return aroundSuggestStorePromotionMapper.selectPromotionByASSId(param).get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<AroundSuggestStoreComment> findCommentList(AroundSuggestStoreCommentVo aroundSuggestStoreCommentVo) {
		return aroundSuggestStoreCommentMapper.selectCommentList(aroundSuggestStoreCommentVo);
	}

	@Override
	public List<Map<String, Object>> findPromotionDetailsById(Long id) {
		// 创建列表变量用于存放促销详情
		List<Map<String, Object>> promotionDetailsList = new ArrayList<Map<String, Object>>();
		// 创建Map变量用于存放sql查询参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装参数信息 */
		param.put("aroundSuggestStoreId", id);
		param.put("oneFlag", false);
		// 调用mapper方法查找所属促销信息列表
		List<AroundSuggestStorePromotion> promotionList = aroundSuggestStorePromotionMapper
				.selectPromotionByASSId(param);
		// 获取其中的促销详情
		for (AroundSuggestStorePromotion assp : promotionList) {
			// 创建Map对象用于封装数据
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("promotionDetails", assp.getPromotionDetails());
			// 创建列表变量用于存放图片路径
			List<String> promotionUrlList = new ArrayList<String>();
			// 创建Model变量用于存放sql查询数据
			Attach attach = new Attach();
			// 设置附件类型
			attach.setMediaType(ConstantStr.MEDIA_TYPE_1);
			// 设置源id
			attach.setOwnerId(assp.getId());
			// 设置源表
			attach.setOwnerTbType(ConstantStr.ATTACH_OWNERTBTYPE_10);
			// 调用mapper方法查找所属附件列表
			List<Attach> attachList = attachMapper.selectAttachByParam(attach);
			// 获取其中的路径
			for (Attach ah : attachList) {
				promotionUrlList.add(ah.getUrl());
			}
			result.put("promotionUrlList", promotionUrlList);
			promotionDetailsList.add(result);
		}
		return promotionDetailsList;
	}

	@Override
	public int addComment(Long storeId, Long appUserId, String content, boolean illegal, String opreaterIp,
			String paramType) {
		// 创建结果变量
		int result = 0;
		// 获取推荐店数据
		AroundSuggestStore original = aroundSuggestStoreMapper.selectByPrimaryKey(storeId);
		if (original == null) { // 如果推荐店已经被删除
			return result;
		}
		if (original.getSourceId() != null && original.getSourceId() != 0) {
			// 获取周边店数据
			AroundStore old = aroundStoreMapper.selectByPrimaryKey(original.getSourceId());
			// 创建周边店Model对象用于更新
			AroundStore aroundStore = new AroundStore();
			/* 设置参数用于更新 */
			aroundStore.setId(original.getSourceId());
			aroundStore.setReplyNumber(old.getReplyNumber() + 1);
			aroundStore.setUpdEac(old.getUpdEac() + 1);
			switch (paramType) {
			case ConstantStr.COMMENT_ONESTAR:
				aroundStore.setOneCount(old.getOneCount() + 1);
				break;
			case ConstantStr.COMMENT_TWOSTAR:
				aroundStore.setTwoCount(old.getTwoCount() + 1);
				break;
			case ConstantStr.COMMENT_THREESTAR:
				aroundStore.setThreeCount(old.getThreeCount() + 1);
				break;
			case ConstantStr.COMMENT_FOURSTAR:
				aroundStore.setFourCount(old.getFourCount() + 1);
				break;
			case ConstantStr.COMMENT_FIVESTAR:
				aroundStore.setFiveCount(old.getFiveCount() + 1);
				break;
			default:
				break;
			}
			if (illegal) {
				aroundStore.setIllegalNumber(old.getIllegalNumber() + 1);
			}
			// 更新主表数据
			result = aroundStoreMapper.updateByPrimaryKeySelective(aroundStore);
		} else {
			// 创建推荐店Model对象用于更新
			AroundSuggestStore aroundSuggestStore = new AroundSuggestStore();
			/* 设置参数用于更新 */
			aroundSuggestStore.setId(storeId);
			aroundSuggestStore.setReplyNumber(original.getReplyNumber() + 1);
			aroundSuggestStore.setUpdEac(original.getUpdEac() + 1);
			switch (paramType) {
			case ConstantStr.COMMENT_ONESTAR:
				aroundSuggestStore.setOneCount(original.getOneCount() + 1);
				break;
			case ConstantStr.COMMENT_TWOSTAR:
				aroundSuggestStore.setTwoCount(original.getTwoCount() + 1);
				break;
			case ConstantStr.COMMENT_THREESTAR:
				aroundSuggestStore.setThreeCount(original.getThreeCount() + 1);
				break;
			case ConstantStr.COMMENT_FOURSTAR:
				aroundSuggestStore.setFourCount(original.getFourCount() + 1);
				break;
			case ConstantStr.COMMENT_FIVESTAR:
				aroundSuggestStore.setFiveCount(original.getFiveCount() + 1);
				break;
			default:
				break;
			}
			if (illegal) {
				aroundSuggestStore.setIllegalNumber(original.getIllegalNumber() + 1);
			}
			// 更新主表数据
			result = aroundSuggestStoreMapper.updateByPrimaryKeySelective(aroundSuggestStore);
		}
		if (result <= 0) { // 如果更新失败则返回
			return 0;
		}
		// 创建推荐店评论Model变量用于数据插入
		AroundSuggestStoreComment aroundSuggestStoreComment = new AroundSuggestStoreComment();
		// 设置推荐店id
		aroundSuggestStoreComment.setAroundSuggestStoreId(storeId);
		// 设置评论内容
		aroundSuggestStoreComment.setContent(content);
		// 设置违规状态
		aroundSuggestStoreComment.setIllegalStatus(illegal ? ConstantStr.ILLEAGL_FLAG_WG : ConstantStr.ILLEAGL_FLAG_ZC);
		// 设置评论人id
		aroundSuggestStoreComment.setInsId(appUserId);
		// 设置评论人ip
		aroundSuggestStoreComment.setOpreaterIp(opreaterIp);
		// 设置评论星级
		aroundSuggestStoreComment.setStarType(StringUtils.isEmpty(paramType) ? "" : paramType);
		// 设置相关初始变量
		addInitAttr(aroundSuggestStoreComment);
		// 调用mapper插入数据
		result = aroundSuggestStoreCommentMapper.insertSelective(aroundSuggestStoreComment);
		// 返回结果
		return result;
	}

	private void addInitAttr(AroundSuggestStoreComment _temp) {
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

	@Override
	public int addCommentReply(Long commentId, Long appUserId, String content, boolean illegal, String opreaterIp) {
		// 创建结果变量
		int result = 0;
		// 获取一级评论数据
		AroundSuggestStoreComment original = aroundSuggestStoreCommentMapper.selectByPrimaryKey(commentId);
		if (original == null) { // 如果评论已经被删除
			return result;
		}
		// 创建评论Model对象用于更新
		AroundSuggestStoreComment aroundSuggestStoreComment = new AroundSuggestStoreComment();
		/* 设置参数用于更新 */
		aroundSuggestStoreComment.setId(commentId);
		aroundSuggestStoreComment.setReplyNumber(original.getReplyNumber() + 1);
		aroundSuggestStoreComment.setUpdEac(original.getUpdEac() + 1);
		if (illegal) {
			aroundSuggestStoreComment.setIllegalNumber(original.getIllegalNumber() + 1);
		}
		// 更新主表数据
		result = aroundSuggestStoreCommentMapper.updateByPrimaryKeySelective(aroundSuggestStoreComment);
		if (result <= 0) { // 如果更新失败则返回
			return 0;
		}
		// 创建推荐店评论的回复Model变量用于数据插入
		AroundSuggestStoreCommentReply aroundSuggestStoreCommentReply = new AroundSuggestStoreCommentReply();
		// 设置推荐店评论id
		aroundSuggestStoreCommentReply.setAroundSuggestStoreCommentId(commentId);
		// 设置评论内容
		aroundSuggestStoreCommentReply.setContent(content);
		// 设置违规状态
		aroundSuggestStoreCommentReply.setIllegalStatus(illegal ? ConstantStr.ILLEAGL_FLAG_WG
				: ConstantStr.ILLEAGL_FLAG_ZC);
		// 设置评论人id
		aroundSuggestStoreCommentReply.setInsId(appUserId);
		// 设置评论人ip
		aroundSuggestStoreCommentReply.setOpreaterIp(opreaterIp);
		// 设置相关初始变量
		addInitAttr(aroundSuggestStoreCommentReply);
		// 调用mapper插入数据
		result = aroundSuggestStoreCommentReplyMapper.insertSelective(aroundSuggestStoreCommentReply);
		// 返回结果
		return result;
	}

	private void addInitAttr(AroundSuggestStoreCommentReply _temp) {
		_temp.setViewNumber(0);
		_temp.setReplyNumber(0);
		_temp.setIllegalNumber(0);
		_temp.setInsYmdhms(new Date());
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

	@Override
	public int addStoreParam(AroundSuggestStore aroundSuggestStore) {
		return aroundSuggestStoreMapper.updateByPrimaryKeySelective(aroundSuggestStore);
	}

	@Override
	public void updateAroundStoreComment(AroundSuggestStoreComment aroundSuggestStoreCommentTemp) {
		aroundSuggestStoreCommentMapper.updateByPrimaryKeySelective(aroundSuggestStoreCommentTemp);
	}

	@Override
	public int addCommentParam(AroundSuggestStoreComment temp) {
		return aroundSuggestStoreCommentMapper.updateByPrimaryKeySelective(temp);
	}

	@Override
	public AroundSuggestStoreComment getAroundSuggestStoreCommentByDay(Long storeId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("storeId", storeId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		List<AroundSuggestStoreComment> asscList = aroundSuggestStoreCommentMapper
				.selectAroundSuggestStoreCommentByDay(param);
		if (asscList != null && asscList.size() > 0) {
			// 返回结果
			return asscList.get(0);
		} else {
			// 返回结果
			return null;
		}
	}

	@Override
	public boolean findOrganizationCode(String organizationCode) {
		// 调用mapper方法看周边店中是否有此组织代码
		int asResult = aroundStoreMapper.findCountOrganizationCode(organizationCode);
		// 调用mapper方法看本网格周边店中是否有此组织代码
		int assResult = aroundSuggestStoreMapper.findCountOrganizationCode(organizationCode);
		if (asResult > 0 || assResult > 0) { // 如果任意一个有则返回存在
			return true;
		} else {
			return false;
		}
	}

	private void editAttr(AroundSuggestStoreComment _temp) {
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
		AroundSuggestStoreComment aroundSuggestStoreComment = aroundSuggestStoreCommentMapper.selectCommentByDay(param);
		if (aroundSuggestStoreComment != null) {
			// 返回结果
			return true;
		} else {
			// 返回结果
			return false;
		}
	}

	@Override
	public boolean getAroundSuggestStoreByDay(Long storeId, Long appUserId) {
		// 创建Map变量用于封装sql参数
		Map<String, Object> param = new HashMap<String, Object>();
		/* 向map中封装数据 */
		param.put("storeId", storeId);
		param.put("appUserId", appUserId);
		param.put("startTime", DateUtil.weeHours(new Date(), 0));
		param.put("endTime", DateUtil.weeHours(new Date(), 1));
		// 调用mapper方法查找结果
		AroundSuggestStore aroundSuggestStore = aroundSuggestStoreMapper.selectAroundSuggestStoreByDay(param);
		if (aroundSuggestStore != null) {
			// 返回结果
			return true;
		} else {
			// 返回结果
			return false;
		}
	}

	@Override
	public boolean judgeStatus(Long storeId) {
		int result = aroundSuggestStoreMapper.selectStatus(storeId);
		return result > 0 ? true : false;
	}

	private void editAttr(AroundSuggestStore _temp) {
		Long userId = getUserId();
		_temp.setUpdEac(_temp.getUpdEac() + 1);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(userId);
	}
	
}
