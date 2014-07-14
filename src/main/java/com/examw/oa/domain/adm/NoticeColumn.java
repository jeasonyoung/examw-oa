package com.examw.oa.domain.adm;

import java.io.Serializable;
import java.util.Set;

/**
 * 通知公告栏目。
 * @author yangyong.
 * @since 2014-06-14.
 */
public class NoticeColumn implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,description;
	private Integer orderNo;
	private NoticeColumn parent;
	private Set<NoticeColumn> children;
	/**
	 * 获取上级栏目。
	 * @return 上级栏目。
	 */
	public NoticeColumn getParent() {
		return parent;
	}
	/**
	 * 设置上级栏目。
	 * @param parent
	 * 上级栏目。
	 */
	public void setParent(NoticeColumn parent) {
		this.parent = parent;
	}
	/**
	 * 获取栏目ID。
	 * @return 栏目ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置栏目ID。
	 * @param id
	 * 栏目ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取栏目名称。
	 * @return 栏目名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置栏目名称。
	 * @param name
	 * 栏目名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取栏目描述。
	 * @return 栏目描述。
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置栏目描述。
	 * @param description
	 * 栏目描述。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取栏目排序号。
	 * @return 栏目排序号。
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置栏目排序号。
	 * @param orderNo
	 * 栏目排序号。
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取子栏目集合。
	 * @return 子栏目集合。
	 */
	public Set<NoticeColumn> getChildren() {
		return children;
	}
	/**
	 * 设置子栏目集合。
	 * @param children
	 * 子栏目集合。
	 */
	public void setChildren(Set<NoticeColumn> children) {
		this.children = children;
	}
}