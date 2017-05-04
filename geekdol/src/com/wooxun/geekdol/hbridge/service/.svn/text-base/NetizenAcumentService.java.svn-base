package com.wooxun.geekdol.hbridge.service;

import java.io.Serializable;
import java.util.List;

import com.soft863.dolphin.common.CrudService;
import com.wooxun.geekdol.hbridge.model.NetizenAcumen;
import com.wooxun.geekdol.hbridge.vo.NetizenAcumenVo;
import com.wooxun.geekdol.system.model.Attach;
/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年9月8日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 QZG	2016年9月8日  下午3:09:17 		创建
 *==========================================================
 * 
 */
public interface NetizenAcumentService <T extends Serializable> extends CrudService<T> {
    /**
     * 
     * @Title
     * @Description 查询满足条件的网民法眼信息列表
     * @author:QZG
     * @CreateDate:2016年9月8日 下午3:03:55
     * @param netizenAcumenVo
     * @return
     */
    public List<NetizenAcumenVo> findAll(NetizenAcumenVo netizenAcumenVo);
    
    /**
     * 
     * @Title
     * @Description 查询满足条件的网民法眼信息列表条数
     * @author:QZG
     * @CreateDate:2016年9月8日 下午3:06:03
     * @param netizenAcumenVo
     * @return
     */
    public long findAllCount(NetizenAcumenVo netizenAcumenVo);

    /**
     * 
     * @Title
     * @Description 逻辑删除网民法眼信息 删除成功返回true
     * @author:QZG
     * @CreateDate:2016年9月8日 下午9:10:15
     * @param netizenAcumen
     * @return
     */
    public boolean deleteNetizenacumen(NetizenAcumen netizenAcumen);
    
    /**
     * 
     * @Title
     * @Description 根据网民法眼信息id查询网民法眼信息详情
     * @author:QZG
     * @CreateDate:2016年9月8日 下午10:58:10
     * @param id
     * @return
     */
    public NetizenAcumenVo selectNetizenAcumenVo(Long id);
    /**
     * 
     * @Title
     * @Description 上传网民法眼信息 --app接口用
     * @author:QZG
     * @CreateDate:2016年9月10日 下午1:37:11
     * @param appNetizenAcumenVo
     * @return
     */
    public boolean insertNetizenAcumen(NetizenAcumen netizenAcumen);

    /**
     * @Title
     * @Description 根据附件信息查询附件
     * @author:kangtianyu
     * @CreateDate:2016年9月21日 下午3:26:35
     * @param attach
     * @return
     */
	public List<Attach> findAttachByParam(Attach attach);

}
