package com.examw.oa.domain.adm;

import java.io.Serializable;
import java.util.Date;

import com.examw.oa.domain.org.Department;

/**
 * 通知公告。
 * @author yangyong.
 * @since 2014-07-14.
 */
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,title,content;
	private NoticeColumn column;
	private Department department;
	private Integer type;
	private Date createTime;
	/**
	 * 类型－通知公告。
	 */
	public static final Integer TYPE_NOTICE = 1;
	/**
	 * 类型－规章制度。
	 */
	public static final Integer TYPE_RULES = 2;
	/**
	 * 获取通知公告ID。
	 * @return 通知公告ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置通知公告ID。
	 * @param id
	 * 通知公告ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取通知公告标题。
	 * @return 通知公告标题。
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置通知公告标题。
	 * @param title
	 * 通知公告标题。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 *  获取通知公告类型。
	 * @return 通知公告类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置通知公告类型。
	 * @param type
	 * 通知公告类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取所属栏目。
	 * @return 所属栏目。
	 */
	public NoticeColumn getColumn() {
		return column;
	}
	/**
	 * 设置所属栏目。
	 * @param column
	 * 所属栏目。
	 */
	public void setColumn(NoticeColumn column) {
		this.column = column;
	}
	/**
	 * 获取所属部门。
	 * @return 所属部门。
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 设置所属部门。
	 * @param department
	 * 所属部门。
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * 获取通知公告内容。
	 * @return 通知公告内容。
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置通知公告内容。
	 * @param content
	 * 通知公告内容。
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取通知公告创建时间。
	 * @return 通知公告创建时间。
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置通知公告创建时间。
	 * @param createTime
	 * 通知公告创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}