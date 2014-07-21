package com.examw.oa.domain.org;

import java.io.Serializable;
import java.util.Set;
/**
 * 部门岗位。
 * @author lq.
 * @since 2014-06-13.
 */
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name;
	private Department department;
	private Post parent;
	private Set<Post> children;
	/**
	 * 获取岗位ID。
	 * @return 岗位ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置岗位ID。
	 * @param id
	 * 岗位ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取岗位名称。
	 * @return 岗位名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置岗位名称。
	 * @param name
	 * 岗位名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取岗位编号。
	 * @return 岗位编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置岗位编号。
	 * @param code
	 * 岗位编号。
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * 获取上级岗位。
	 * @return 上级岗位。
	 */
	public Post getParent() {
		return parent;
	}
	/**
	 * 设置上级岗位。
	 * @param parent
	 * 上级岗位。
	 */
	public void setParent(Post parent) {
		this.parent = parent;
	}
	/**
	 * 获取子岗位集合。
	 * @return 子岗位集合。
	 */
	public Set<Post> getChildren() {
		return children;
	}
	/**
	 * 设置子岗位集合。
	 * @param children
	 * 子岗位集合。
	 */
	public void setChildren(Set<Post> children) {
		this.children = children;
	}
}