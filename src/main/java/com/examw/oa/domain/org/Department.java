package com.examw.oa.domain.org;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
/**
 * 部门数据
 * @author lq.
 * @since 2014-06-11.
 */
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id,name,code;
	private Integer orderNo;
	private Date createTime;
	private Department parent;
	private Set<Department> children;
	/**
	 * 获取上级菜单。
	 * @return
	 * 	上级菜单。
	 */
	public Department getParent() {
		return parent;
	}
	/**
	 * 设置上级菜单。
	 * @return
	 * 	上级菜单。
	 */
	public void setParent(Department parent) {
		this.parent = parent;
	}
	/**
	 * 获取子菜单集合。
	 * @return
	 * 	子菜单集合。
	 */
	public Set<Department> getChildren() {
		return children;
	}
	/**
	 * 设置子部门集合。
	 * @return
	 * 	子部门集合。
	 */
	public void setChildren(Set<Department> children) {
		this.children = children;
	}
	/**
	 * 获取部门id。
	 * @return
	 * 	部门id。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置部门id。
	 * @return
	 * 	部门id。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取部门名称。
	 * @return
	 * 	部门名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置部门名称。
	 * @return
	 * 	部门名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取部门编号。
	 * @return
	 * 	部门编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置部门编号。
	 * @return
	 * 	设置编号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取排序。
	 * @return
	 * 	排序。
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置排序。
	 * @return
	 * 	排序。
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取创建时间。
	 * @return
	 * 	创建时间。
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @return
	 * 	创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}