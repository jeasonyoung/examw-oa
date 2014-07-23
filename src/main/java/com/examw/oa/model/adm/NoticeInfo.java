package com.examw.oa.model.adm;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomShortDateSerializer;
/**
 * 通知公告信息。
 * @author lq
 * @since 2014-07-15
 */
public class NoticeInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,title,columnId,columnName,departmentId,departmentName,content;
	private Integer type;
	private Date createTime;
	/**
	 * 获取通知ID。
	 * @return 通知ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置通告ID。
	 * @param id
	 * 通知ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取通告标题。
	 * @return 通告标题。
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置通告标题。
	 * @param title 
	 * 通告标题。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取栏目设置ID。
	 * @return 栏目设置ID。
	 */
	public String getColumnId() {
		return columnId;
	}
	/**
	 * 设置栏目设置ID。
	 * @param columnId
	 * 栏目设置ID。
	 */
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	/**
	 * 获取栏目设置名称。
	 * @return 栏目设置名称。
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * 设置栏目设置名称。
	 * @param columnName
	 * 栏目设置名称。
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * 获取部门ID。
	 * @return 部门ID。
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置部门ID。
	 * @param departmentId
	 * 部门ID。
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取部门名称。
	 * @return 部门名称。
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置部门名称。
	 * @param departmentName
	 * 部门名称。
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * 获取通告内容。
	 * @return 通告内容。
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置通告内容。
	 * @param content
	 * 设置通告内容。
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取通告类型。
	 * @return 通告类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置通告类型。
	 * @param type
	 * 通告类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取通告创建时间。
	 * @return 通告创建时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置通告创建时间。
	 * @param createTime
	 * 通告创建时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}