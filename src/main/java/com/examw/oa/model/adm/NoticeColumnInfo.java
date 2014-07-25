package com.examw.oa.model.adm;

import java.util.List;

import com.examw.model.Paging;
/**
 * 栏目设置信息。
 * @author lq
 * @since 2014-07-14
 */
public class NoticeColumnInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,pid,name,description,fullName;
	private Integer orderNo;
	private List<NoticeColumnInfo> children;
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
	 * 获取上级栏目ID。
	 * @return 上级栏目ID。
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置上级栏目ID。
	 * @param pid
	 * 上级栏目。
	 */
	public void setPid(String pid) {
		this.pid = pid;
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
	 * 获取栏目排序。
	 * @return 栏目排序。
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置栏目排序。
	 * @param orderNo
	 * 栏目排序。
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取子栏目集合。
	 * @return 子栏目集合。
	 */
	public List<NoticeColumnInfo> getChildren() {
		return children;
	}
	/**
	 * 设置子栏目集合。
	 * @param children
	 * 子栏目集合。
	 */
	public void setChildren(List<NoticeColumnInfo> children) {
		this.children = children;
	}
	/**
	 * 获取栏目全称。
	 * @return 栏目全称。
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * 设置栏目全称。
	 * @param fullName
	 * 栏目全称。
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}