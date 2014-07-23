package com.examw.oa.model.adm;

import java.util.Date;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomShortDateSerializer;
/**
 * 我要请假信息。
 * @author lq.
 * @since 2014-07-15.
 */
public class LeaveInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,deptName,postName,resaon,employeeId,employeeName,shiftEmployeeId,shiftEmployeeName,shiftDepartmentId;
	private Integer type,status;
	private Date startTime,endTime,supTime,shiftTime,createTime;
	private Boolean isSup;
	private String approvalId,approval,approvalLeaveId,approvalEmplId;
	private Integer approvalType,approvalStatus;
	private Set<LeaveApprovalInfo> approvals;
	/**
	 * 获取请假条ID。
	 * @return 请假条ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置请假条ID。
	 * @param id
	 * 请假条ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所在部门名称。
	 * @return 所在部门名称。
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置所在部门名称。
	 * @param deptName
	 * 所在部门名称。
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取所在岗位名称。
	 * @return 所在岗位名称。
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置所在岗位名称。
	 * @param postName
	 * 所在岗位名称。
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取请假原因。
	 * @return 请假原因。
	 */
	public String getResaon() {
		return resaon;
	}
	/**
	 * 设置请假原因。
	 * @param resaon
	 * 请假原因。
	 */
	public void setResaon(String resaon) {
		this.resaon = resaon;
	}
	/**
	 * 获取请假员工ID。
	 * @return 请假员工ID。
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置请假员工ID。
	 * @param employeeId
	 * 请假员工ID。
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取请假员工名称。
	 * @return 请假员工名称。
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置请假员工名称。
	 * @param employeeName
	 * 请假员工名称。
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取换班人ID。
	 * @return 换班人ID。
	 */
	public String getShiftEmployeeId() {
		return shiftEmployeeId;
	}
	/**
	 * 设置换班人ID。
	 * @param shiftEmployeeId
	 * 换班人ID。
	 */
	public void setShiftEmployeeId(String shiftEmployeeId) {
		this.shiftEmployeeId = shiftEmployeeId;
	}
	/**
	 * 获取换班人名称。
	 * @return 换班人名称。
	 */
	public String getShiftEmployeeName() {
		return shiftEmployeeName;
	}
	/**
	 * 设置换班人名称。
	 * @param shiftEmployeeName
	 * 换班人名称。
	 */
	public void setShiftEmployeeName(String shiftEmployeeName) {
		this.shiftEmployeeName = shiftEmployeeName;
	}
	/**
	 * 获取请假开始时间。
	 * @return 请假开始时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置请假开始时间。
	 * @param startTime
	 * 请假开始时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取请假结束时间。
	 * @return 请假结束时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置请假结束时间。
	 * @param endTime
	 * 请假结束时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取补班时间。
	 * @return 补班时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getSupTime() {
		return supTime;
	}
	/**
	 * 设置补班时间。
	 * @param supTime
	 * 补班时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setSupTime(Date supTime) {
		this.supTime = supTime;
	}
	/**
	 * 获取换班日期。
	 * @return 换班日期。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getShiftTime() {
		return shiftTime;
	}
	/**
	 * 设置换班日期。
	 * @param shiftTime
	 * 换班日期。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}
	/**
	 * 获取创建时间。
	 * @return 创建时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @param createTime
	 * 创建时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取补班状态。
	 * @return 补班状态。
	 */
	public Boolean getIsSup() {
		return isSup;
	}
	/**
	 * 设置补班状态。
	 * @param isSup
	 * 补班状态。
	 */
	public void setIsSup(Boolean isSup) {
		this.isSup = isSup;
	}
	/**
	 * 获取换班人ID。
	 * @return 换班人ID。
	 */
	public Set<LeaveApprovalInfo> getApprovals() {
		return approvals;
	}
	/**
	 * 设置换班人ID。
	 * @param approvals
	 * 换班人ID。
	 */
	public void setApprovals(Set<LeaveApprovalInfo> approvals) {
		this.approvals = approvals;
	}
	/**
	 * 获取请假类型。
	 * @return 请假类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置请假类型。
	 * @param type
	 * 请假类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取换班部门ID。
	 * @return 换班部门ID。
	 */
	public String getShiftDepartmentId() {
		return shiftDepartmentId;
	}
	/**
	 * 设置换班部门ID。
	 * @param shiftDepartmentId
	 * 换班部门ID。
	 */
	public void setShiftDepartmentId(String shiftDepartmentId) {
		this.shiftDepartmentId = shiftDepartmentId;
	}
	/**
	 * 获取审批结果状态。
	 * @return
	 * 审批结果状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置审批结果状态。
	 * @param status
	 * 审批结果状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取请假审批ID。
	 * @return 请假审批ID。
	 */
	public String getApprovalId() {
		return approvalId;
	}
	/**
	 * 设置请假审批ID。
	 * @param approvalId
	 * 请假审批ID。
	 */
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	/**
	 * 获取请假审批意见。
	 * @return 请假审批意见。
	 */
	public String getApproval() {
		return approval;
	}
	/**
	 * 设置请假审批意见。
	 * @param approval
	 * 请假审批意见。
	 */
	public void setApproval(String approval) {
		this.approval = approval;
	}
	/**
	 * 获取请假人ID。
	 * @return 请假人ID。
	 */
	public String getApprovalLeaveId() {
		return approvalLeaveId;
	}
	/**
	 * 设置请假人ID。
	 * @param approvalLeaveId
	 * 请假人ID。
	 */
	public void setApprovalLeaveId(String approvalLeaveId) {
		this.approvalLeaveId = approvalLeaveId;
	}
	/**
	 * 获取审批人ID。
	 * @return 审批人ID。
	 */
	public String getApprovalEmplId() {
		return approvalEmplId;
	}
	/**
	 * 设置审批人ID。
	 * @param approvalEmplId
	 * 审批人ID。
	 */
	public void setApprovalEmplId(String approvalEmplId) {
		this.approvalEmplId = approvalEmplId;
	}
	/**
	 * 获取审批类型。
	 * @return 审批类型。
	 */
	public Integer getApprovalType() {
		return approvalType;
	}
	/**
	 * 设置审批类型。
	 * @param approvalType
	 * 审批类型。
	 */
	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}
	/**
	 * 获取审批状态。
	 * @return 审批状态。
	 */
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * 设置审批状态。
	 * @param approvalStatus
	 * 审批状态。
	 */
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}