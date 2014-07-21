package com.examw.oa.model.org;

import java.util.Set;

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
	private String pid,id,code,name,fullName,deptId,deptName;
	private Set<PostInfo> children;
	/**
	 * 获取上级岗位ID。
	 * @return 上级岗位ID。
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置上级岗位ID。
	 * @param pid
	 * 上级岗位ID。
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
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
	 * 获取岗位全称。
	 * @return 岗位全称。
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * 设置岗位全称。
	 * @param fullName
	 * 岗位全称。
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * 获取所属部门ID。
	 * @return
	 * 所属部门ID。
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置所属部门ID。
	 * @param deptId
	 * 所属部门ID。
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取所属部门名称。
	 * @return
	 * 所属部门名称。
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置所属部门名称。
	 * @param deptName
	 * 设置所属部门名称。
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取子岗位集合。
	 * @return 子岗位集合。
	 */
	public Set<PostInfo> getChildren() {
		return children;
	}
	/**
	 * 设置子岗位集合。
	 * @param children
	 * 子岗位集合。
	 */
	public void setChildren(Set<PostInfo> children) {
		this.children = children;
	}
}