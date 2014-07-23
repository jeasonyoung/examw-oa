package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 员工报告设置信息。
 * @author lq.
 * @since 2014-06-24
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SettingsInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,typeName,employeeId,employeeName,deptId,deptName;
	private String[] type;
	private Date createTime;
	/**
	 * 获取报告设置ID。
	 * @return 报告设置ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置报告设置ID。
	 * @param id
	 * 报告设置ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所属员工ID。
	 * @return 所属员工ID。
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置所属员工ID。
	 * @param employeeId
	 * 所属员工ID。
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取员工名称。
	 * @return 员工名称。
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置员工名称。
	 * @param employeeName
	 * 员工名称。
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取报告类型。
	 * @return 报告类型。
	 */
	public String[] getType() {
		return type;
	}	
	/**
	 * 设置报告类型。
	 * @param type 
	 * 报告类型。
	 */
	public void setType(String[] type) {
		this.type = type;
	}
	/**
	 * 获取报告类型名称。
	 * @return 报告类型名称。	
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置报告类型名称。
	 * @param typeName
	 *  报告类型名称。
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取所属部门ID。
	 * @return 所属部门ID。
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
	 * @return 所属部门名称。
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置所属部门名称。
	 * @param deptName
	 * 所属部门名称。
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取创建时间。
	 * @return 创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @param createTime
	 * 创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}