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
	private String id,name,code,deptId,departName;
	
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
	}/**
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
	 * 获取所属部门ID。
	 * @return
	 * 部门ID。
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置所属部门ID。
	 * @return
	 * 部门ID。
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取所属部门名称。
	 * @return
	 * 部门名称。
	 */
	public String getDepartName() {
		return departName;
	}
	/**
	 * 设置所属部门名称。
	 * @return
	 * 部门名称。
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}
}