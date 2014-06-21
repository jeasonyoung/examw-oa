package com.examw.oa.model.org;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.examw.model.Paging;
/**
 * 员工岗位信息。
 * @author lq.
 * @since 2014-06-12.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class PostInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,code,name,departmentId,departmentName;
	/**
	 * 获取部门岗位ID。
	 * @return
	 * 部门岗位ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置部门岗位ID。
	 * @param id
	 * 部门岗位ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取部门岗位编码。
	 * @return
	 * 部门岗位编码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置部门岗位编码。
	 * @param code
	 * 部门岗位编码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取部门岗位名称。
	 * @return
	 * 部门岗位名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置部门岗位名称。
	 * @return
	 * 部门岗位名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取所属部门ID。
	 * @return
	 * 所属部门ID。
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置所属部门ID。
	 * @param departmentId
	 * 所属部门ID。
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取所属部门名称。
	 * @return
	 * 部门名称。
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置所属部门名称。
	 * @param departmentName
	 * 设置所属部门名称。
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}