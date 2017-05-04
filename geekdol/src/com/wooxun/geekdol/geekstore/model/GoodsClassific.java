package com.wooxun.geekdol.geekstore.model;

import com.soft863.dolphin.common.CommonEntity;

public class GoodsClassific extends CommonEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 分类ID */
    private Long id;

    /** 分类编号 */
    private String code;

    /** 分类名称 */
    private String name;

    /** 备注 */
    private String remarks;

    /** 父类Id */
    private Long parentId;

    /** 排序  */
    private Long ordering;

    /** 0:禁用;1:启用 */
    private String status;

    /** 操作人 */
    private Long opreator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getOrdering() {
		return ordering;
	}

	public void setOrdering(Long ordering) {
		this.ordering = ordering;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOpreator() {
		return opreator;
	}

	public void setOpreator(Long opreator) {
		this.opreator = opreator;
	}
}