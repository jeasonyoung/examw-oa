package com.examw.oa.domain.org;

import java.io.Serializable;
/**
 * 部门岗位。
 * @author lq.
 * @since 2014-06-13.
 */
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name;
	private Department department;
	private Integer level;
	/**
	 * 获取岗位ID。
	 * @return
	 * 岗位ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置岗位ID。
	 * @return
	 * 岗位ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取岗位名称。
	 * @return
	 * 岗位名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置岗位名称。
	 * @return
	 * 岗位名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取岗位编号。
	 * @return
	 * 岗位编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置岗位编号。
	 * @return
	 * 岗位编号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取所属部门。
	 * @return
	 * 所属部门。
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 设置所属部门。
	 * @return
	 * 所属部门。
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * 获取岗位值。
	 * @return 岗位值。
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置岗位值。
	 * @param level
	 * 岗位值。
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
}