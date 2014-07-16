package com.examw.oa.model.adm;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
/**
 * 请假审批
 * @author lq.
 * @since 2014-07-16.
 */
public class LeaveApprovalInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,approval,leaveId,leaveName,employeeId,employeeName;
	private Integer type,status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 获取请假审批ID
	 * @return
	 * 请假审批ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置请假审批ID
	 * @return
	 * 请假审批ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取审批意见
	 * @return
	 * 审批意见
	 */
	public String getApproval() {
		return approval;
	}
	/**
	 * 设置审批意见
	 * @return
	 * 审批意见
	 */
	public void setApproval(String approval) {
		this.approval = approval;
	}
	/**
	 * 获取请假ID
	 * @return
	 * 请假ID
	 */
	public String getLeaveId() {
		return leaveId;
	}
	/**
	 * 设置请假ID
	 * @return
	 * 请假ID
	 */
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	/**
	 * 获取请假人
	 * @return
	 * 请假人
	 */
	public String getLeaveName() {
		return leaveName;
	}
	/**
	 * 设置请假人
	 * @return
	 * 请假人
	 */
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	/**
	 * 获取审批人ID
	 * @return
	 * 审批人ID
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置审批人ID
	 * @return
	 * 审批人ID
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取审批人名称
	 * @return
	 * 审批人名称
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置审批人名称
	 * @return
	 * 审批人名称
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取请假审批类型
	 * @return
	 * 请假审批类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置请假审批类型
	 * @return
	 * 请假审批类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取请假审批状态
	 * @return
	 * 请假审批状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置请假审批状态
	 * @return
	 * 请假审批状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取创建时间
	 * @return
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @param createTime
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}