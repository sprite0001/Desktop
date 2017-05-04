package com.wooxun.geekdol.hmedia.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.soft863.dolphin.common.CrudServiceImpl;
import com.wooxun.geekdol.common.ConstantStr;
import com.wooxun.geekdol.common.DateUtil;
import com.wooxun.geekdol.common.DownLoadUtil;
import com.wooxun.geekdol.common.ExcelOutPutUtil;
import com.wooxun.geekdol.common.UnescapeUtil;
import com.wooxun.geekdol.hmedia.mapper.ActivityBaomingMapper;
import com.wooxun.geekdol.hmedia.mapper.ActivityCollectionCommentMapper;
import com.wooxun.geekdol.hmedia.mapper.ActivityCollectionCommentRMapper;
import com.wooxun.geekdol.hmedia.mapper.ActivityCollectionMapper;
import com.wooxun.geekdol.hmedia.mapper.ActivityCollectionVillageMapper;
import com.wooxun.geekdol.hmedia.model.ActivityBaoming;
import com.wooxun.geekdol.hmedia.model.ActivityCollection;
import com.wooxun.geekdol.hmedia.model.ActivityCollectionComment;
import com.wooxun.geekdol.hmedia.model.ActivityCollectionCommentR;
import com.wooxun.geekdol.hmedia.model.ActivityCollectionVillage;
import com.wooxun.geekdol.hmedia.service.ActivityCollectionService;
import com.wooxun.geekdol.hmedia.vo.ActivityBaomingVo;
import com.wooxun.geekdol.hmedia.vo.ActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityCollectionVo;
import com.wooxun.geekdol.hmedia.vo.AppActivityVo;
import com.wooxun.geekdol.system.mapper.AttachMapper;
import com.wooxun.geekdol.system.model.Attach;
import com.wooxun.geekdol.system.model.User;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author Liys
 * @CreateDate 2016年7月28日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. Liys 2016年7月28日 下午4:46:17 创建
 *           ==========================================================
 * 
 */
@Service
public class ActivityCollectionServiceImpl extends CrudServiceImpl<ActivityCollection> implements
		ActivityCollectionService<ActivityCollection> {

	private ActivityCollectionMapper<ActivityCollection> activityCollectionMapper;

	@Autowired
	private AttachMapper<Attach> attachMapper;

	@Autowired
	private ActivityCollectionVillageMapper<ActivityCollection> activityCollectionVillageMapper;

	@Autowired
	private ActivityCollectionCommentMapper<ActivityCollectionComment> activityCollectionCommentMapper;

	@Autowired
	private ActivityCollectionCommentRMapper<ActivityCollectionCommentR> activityCollectionCommentReplyMapper;

	@Autowired
	private ActivityBaomingMapper<ActivityBaoming> activityBaomingMapper;

	@Autowired
	public ActivityCollectionServiceImpl(ActivityCollectionMapper<ActivityCollection> activityCollectionMapper) {
		super(activityCollectionMapper);
		this.activityCollectionMapper = activityCollectionMapper;
	}

	@Override
	public boolean insertActivityCollection(ActivityCollection activityCollection, String villageId) {
		int i = activityCollectionMapper.insertSelective(activityCollection);
		if (i <= 0) {
			return false;
		} else {

			// 保存小区活动汇关系表
			List<ActivityCollectionVillage> listActivityCollection = new ArrayList<ActivityCollectionVillage>();
			String[] village = villageId.split(",");
			for (int j = 0; j < village.length; j++) {
				ActivityCollectionVillage av = new ActivityCollectionVillage();
				av.setActivityCollectionId(activityCollection.getId());
				av.setVillageId(Long.valueOf(village[j]));
				listActivityCollection.add(av);
			}
			activityCollectionVillageMapper.insertBatch(listActivityCollection);

			// 保存附件
			List<Attach> listAttach = new ArrayList<Attach>();
			int k = 0;
			for (Attach attach : activityCollection.getAttachs()) {
				attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
				attach.setOwnerId(activityCollection.getId());
				attach.setOwnerTbType(ConstantStr.ATTACH_ACTIVITY_15);
				attach.setOrdering(k);
				attach.setOpreator(activityCollection.getInsId());
				attach.setInsId(activityCollection.getInsId());
				attach.setInsYmdhms(activityCollection.getInsYmdhms());
				attach.setUpdId(activityCollection.getUpdId());
				attach.setUpdEac(activityCollection.getUpdEac());
				attach.setUpdYmdhms(activityCollection.getUpdYmdhms());
				attach.setDelFlag(ConstantStr.DELETE_N);
				listAttach.add(attach);
				k++;
			}
			attachMapper.insertBatch(listAttach);

		}
		return true;
	}

	@Override
	public Long queryCountByParams(ActivityCollectionVo searchActivityCollection) {
		return activityCollectionMapper.queryCountByParams(searchActivityCollection);
	}

	@Override
	public List<ActivityCollectionVo> queryActivityCollectionByParams(ActivityCollectionVo searchActivityCollection) {

		return activityCollectionMapper.queryActivityCollectionByParams(searchActivityCollection);
	}

	@Override
	public ActivityCollectionVo selectActivityCollectionVo(Long id) {

		ActivityCollection activityCollection = activityCollectionMapper.selectByPrimaryKey(id);
		// 返回封装好的数据
		ActivityCollectionVo activityCollectionVo = formateData(activityCollection);

		return activityCollectionVo;
	}

	/**
	 * 
	 * @Title
	 * @Description 返回封装好的 activityCollectionVo 数据
	 * @author:王肖东
	 * @CreateDate:2016年7月31日 下午9:22:14
	 * @param cooperationStore
	 * @return
	 */
	private ActivityCollectionVo formateData(ActivityCollection activityCollection) {
		ActivityCollectionVo activityCollectionVo = new ActivityCollectionVo();
		BeanUtils.copyProperties(activityCollection, activityCollectionVo);
		String villageId = "";
		String villageName = "";
		for (ActivityCollectionVillage activityCollectionVillage : activityCollection
				.getActivityCollectionVillageList()) {
			// 如果为null 证明小区有可能被禁用 或者删除
			if (activityCollectionVillage.getVillage() != null) {
				villageId += activityCollectionVillage.getVillageId() + ",";
				villageName += activityCollectionVillage.getVillage().getVillageName() + ",";
			}
		}
		activityCollectionVo.setVillageId(villageId.substring(0, villageId.lastIndexOf(",")));
		activityCollectionVo.setVillageName(villageName.substring(0, villageName.lastIndexOf(",")));
		activityCollectionVo.setActivityShops(activityCollection.getActivityShops());
		activityCollectionVo.setHotnumber(activityCollection.getViewNumber() + "/"
				+ activityCollection.getReplyNumber() + "/" + activityCollection.getIllegalNumber());

		activityCollectionVo.setLikesOrhate(activityCollection.getLikesNumber() + "/"
				+ activityCollection.getHateNumber());
		// 是否需要报名
		if (activityCollection.getEnrollFlag().equals("1")) {
			// 报名截止日期
			activityCollectionVo.setEnrollEndStr(DateUtil.dateToString(activityCollection.getEnrollEnd()));
		}
		if (activityCollection.getTopStart() != null) {
			activityCollectionVo.setTopStartStr(DateUtil.dateToString(activityCollection.getTopStart()));
		}
		if (activityCollection.getTopEnd() != null) {
			activityCollectionVo.setTopEndStr(DateUtil.dateToString(activityCollection.getTopEnd()));
		}
		if (activityCollection.getStartTime() != null) {
			activityCollectionVo.setStartTimeStr(DateUtil.dateToString(activityCollection.getStartTime()));
		}
		if (activityCollection.getEndTime() != null) {
			activityCollectionVo.setEndTimeStr(DateUtil.dateToString(activityCollection.getEndTime()));
		}
		return activityCollectionVo;
	}

	@Override
	public boolean deleteActivityCollection(ActivityCollection activityCollection) {

		int i = activityCollectionMapper.updateByPrimaryKeySelective(activityCollection);
		if (i >= 0) {
			i = activityCollectionVillageMapper.deleteByActivityCollectionId(activityCollection.getId());
		}
		// 删除附件表
		int j = attachMapper.deleteByIdAndName(activityCollection.getId(), "15");
		return j > 0 ? true : false;
	}

	public Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(ConstantStr.USER);
		Long userId = (Long) user.getId();
		return userId;
	}

	/**
	 * 
	 * @Title
	 * @Description 活动汇 报名导出
	 * @author:QZG
	 * @CreateDate:2016年8月11日 下午3:58:45
	 * @param activityBaomingVo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Override
	public void export(ActivityBaomingVo activityBaomingVo, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, IOException {
		// 根据条件查询报名列表类
		List<ActivityBaomingVo> baomingList = activityBaomingMapper.queryActivityBaomingByParams(activityBaomingVo);
		// 新建变量
		List<List<String>> dataList = new ArrayList<List<String>>();
		// 序号默认从1开始
		int i = 1;
		// 当报名列表不为空时
		if (null != baomingList && baomingList.size() > 0) {
			// 遍历报名列表
			for (ActivityBaomingVo original : baomingList) {
				// 数据封装
				List<String> temList = new ArrayList<String>();
				// 序号
				temList.add(String.valueOf(i));
				// 姓名
				temList.add(getData(original.getName()));
				// 联系方式
				temList.add(getData(original.getPhone()));
				// 报名人IP
				temList.add(getData(original.getIp()));
				// 报名时间
				temList.add(getData(DateUtil.format(original.getTime(), "YYYY-MM-dd")));
				// 备注  需要解密
				temList.add(getData(UnescapeUtil.unescape(original.getRemark())));
				// 添加数据
				dataList.add(temList);
				// 序号+1
				i++;
			}
			//
			String excelPath = "";
			// excel模版服务器路径
			String excelModePath = "";

			String exportPath = "";

			excelPath = request.getSession().getServletContext().getRealPath(ConstantStr.EXCEL_PATH);
			// 创建报名表导出文件夹
			File payFile = new File(excelPath + "\\" + "报名表导出");
			// 判断文件夹是否存在
			if (!payFile.exists()) {
				// 判断文件是否真正存在,如果不存在,创建一个;
				payFile.mkdirs();
			}
			// 读取模板excel
			excelModePath = request.getSession().getServletContext().getRealPath(ConstantStr.EXCELMODE_PATH);
			// 导出excel
			exportPath = payFile + "\\" + "报名表模板.xls";
			OutputStream outPath = new FileOutputStream(exportPath);
			// 生成Excel文件
			ExcelOutPutUtil.exportExcelFile(excelModePath + "\\" + "报名表模板.xls", outPath, dataList, null);
			// Excel文件下载
			DownLoadUtil.downLoadFile(exportPath, response, "报名表.xls", "xls");
		}

	}

	private String getData(String str) {
		String data = "";
		if (!StringUtils.isEmpty(str)) {
			data = str;
		}
		return data;
	}

	@Override
	public List<AppActivityCollectionVo> queryActivityCollection(AppActivityCollectionVo appActivityCollectionVo) {
		return activityCollectionMapper.queryActivityCollection(appActivityCollectionVo);
	}

	@Override
	public AppActivityVo queryActivity(AppActivityVo appActivityVo) {
		return activityCollectionMapper.queryActivity(appActivityVo);
	}

	@Override
	public ActivityCollection getById(Long id) {
		return activityCollectionMapper.getById(id);
	}

	@Override
	public boolean updateActivityVo(ActivityCollectionVo activityCollectionVo, String villageId) {
		ActivityCollection activityCollection = new ActivityCollection();
		BeanUtils.copyProperties(activityCollectionVo, activityCollection);
		int i = activityCollectionMapper.updateByPrimaryKeySelective(activityCollection);
		i = activityCollectionVillageMapper.deleteByActivityCollectionId(activityCollection.getId());
		// 保存附件
		List<Attach> listAttach = new ArrayList<Attach>();
		int k = 0;
		for (Attach attach : activityCollection.getAttachs()) {
			attach.setMediaType(ConstantStr.ATTACH_MEDIATYPE_IMG);
			attach.setOwnerId(activityCollection.getId());
			attach.setOwnerTbType(ConstantStr.ATTACH_ACTIVITY_15);
			attach.setOrdering(k);
			attach.setOpreator(activityCollection.getInsId());
			attach.setInsId(activityCollection.getInsId());
			attach.setInsYmdhms(activityCollection.getInsYmdhms());
			attach.setUpdId(activityCollection.getUpdId());
			attach.setUpdEac(activityCollection.getUpdEac());
			attach.setUpdYmdhms(activityCollection.getUpdYmdhms());
			attach.setDelFlag(ConstantStr.DELETE_N);
			listAttach.add(attach);
			k++;
		}
		if (listAttach.size() != 0) {
			attachMapper.insertBatch(listAttach);
		}
		// 保存小区活动汇关系表
		List<ActivityCollectionVillage> listActivityCollection = new ArrayList<ActivityCollectionVillage>();
		String[] village = villageId.split(",");
		for (int j = 0; j < village.length; j++) {
			ActivityCollectionVillage av = new ActivityCollectionVillage();
			av.setActivityCollectionId(activityCollection.getId());
			av.setVillageId(Long.valueOf(village[j]));
			listActivityCollection.add(av);
		}
		activityCollectionVillageMapper.insertBatch(listActivityCollection);

		return i > 0 ? true : false;
	}

	@Override
	public ActivityCollectionVo selectActivityVo(Long id) {
		ActivityCollection activityCollection = activityCollectionMapper.selectByPrimaryKey(id);
		ActivityCollectionVo activityCollectionVo = formateData(activityCollection);

		return activityCollectionVo;
	}

}
