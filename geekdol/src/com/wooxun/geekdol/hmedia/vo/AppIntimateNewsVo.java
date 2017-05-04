package com.wooxun.geekdol.hmedia.vo;


/**
 * 
 * @Title
 * @Description 贴心报显示实体类
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年7月30日
 * @param
 * @see
 * @modified ========================================================== No 修改人员
 *           修改日期 描述 1. 王肖东 2016年7月30日 下午1:55:23 创建
 *           ==========================================================
 *
 */
public class AppIntimateNewsVo {

	/** 贴心报Id */
	private Long id;

	/** 贴心报标题 */
	private String title;

	/** 贴心报来源 */
	private String source;

	/** 类型：文字类、图片类 */
	private String type;

	/** 图片 */
	private String icon;

	/** app所需参数 一级评论数量 */
	private int commentNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
}