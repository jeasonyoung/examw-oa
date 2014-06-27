package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 员工计划总结类型设置信息。
 * @author lq.
 * @since 2014-06-24
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SettingsInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,typeName, employeeId,employeeName;
	private Integer[] type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 获取员工计划总结类型ID
	 * @return
	 * 员工计划总结类型ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工计划总结类型ID
	 * @return
	 * 员工计划总结类型ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取员工ID
	 * @return
	 * 员工ID
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置员工ID
	 * @param employee_id
	 * 员工ID
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取员工报表类型
	 * @return
	 * 员工报表类型
	 */
	
	public Integer[] getType() {
		return type;
	}	
	/**
	 * 获取员工报表类型名称
	 * @return
	 * 员工报表类型名称
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置员工报表类型名称
	 * @return
	 * 员工报表类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * 设置员工报表类型
	 * @param type
	 * 员工报表类型
	 */
	public void setType(Integer[] type) {
		this.type = type;
	}
	/**
	 * 获取员工名称
	 * @return
	 * 员工名称
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置员工名称
	 * @return
	 * 员工名称
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取员工报表创建时间
	 * @return
	 * 员工报表创建时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置员工报表创建时间
	 * @param createTime、
	 * 员工报表创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}