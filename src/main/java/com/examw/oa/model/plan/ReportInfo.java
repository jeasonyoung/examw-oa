package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;

public class ReportInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,typeName,employeeId,employeeName;
	private Integer type,Status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime,postTime,lastPostTime;
	/**
	 * 获取员工报表ID
	 * @return
	 * 员工报表ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工报表ID
	 * @param id
	 * 员工报表ID
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
	 * @param employeeId
	 * 员工ID
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	 * @param employeeName
	 * 员工名称
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取员工报表类型
	 * @return
	 * 员工报表类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置员工报表类型
	 * @param type
	 * 员工报表类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取员工报表状态
	 * @return
	 * 员工报表状态
	 */
	public Integer getStatus() {
		return Status;
	}
	/**
	 * 设置员工报表状态
	 * @param status
	 * 员工报表状态
	 */
	public void setStatus(Integer status) {
		Status = status;
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
	 * @param createTime
	 * 员工报表创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取员工报表提交时间
	 * @return
	 * 员工报表提交时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getPostTime() {
		return postTime;
	}
	/**
	 * 设置员工报表提交时间
	 * @param postTime
	 * 员工报表提交时间
	 */
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	/**
	 * 获取员工报表最后提交时间
	 * @return
	 * 员工报表最后提交时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastPostTime() {
		return lastPostTime;
	}
	/**
	 * 设置员工报表最后提交时间
	 * @param lastPostTime
	 * 员工报表最后提交时间
	 */
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
}