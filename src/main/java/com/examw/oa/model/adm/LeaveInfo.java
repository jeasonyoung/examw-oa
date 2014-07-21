package com.examw.oa.model.adm;

import java.util.Date;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 请假条
 * @author lq.
 * @since 2014-07-15.
 */
public class LeaveInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,deptName,postName,resaon,employeeId,employeeName,shiftEmployeeId,shiftEmployeeName,shiftDepartmentId;
	private Integer type,status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime,endTime,supTime,shiftTime,createTime;
	private Boolean isSup;
	private String aId,approval,aLeaveId,aEmplId;
	private Integer aType,aStatus;
	private Set<LeaveApprovalInfo> approvals;
	/**
	 * 获取请假条ID
	 * @return
	 * 请假条ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置请假条ID.
	 * @return 请假条ID.
	 * 
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所在部门名称
	 * @return
	 * 所在部门名称
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置所在部门名称
	 * @return
	 * 所在部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取所在岗位名称
	 * @return
	 * 所在岗位名称
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置所在岗位名称
	 * @return
	 * 所在岗位名称
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取请假原因
	 * @return
	 * 请假原因
	 */
	public String getResaon() {
		return resaon;
	}
	/**
	 * 设置请假原因
	 * @return
	 * 请假原因
	 */
	public void setResaon(String resaon) {
		this.resaon = resaon;
	}
	/**
	 * 获取请假员工ID
	 * @return
	 * 请假员工ID
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置请假员工ID
	 * @return
	 * 请假员工ID
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取请假员工名称
	 * @return
	 * 请假员工名称
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置请假员工名称
	 * @return
	 * 请假员工名称
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取换班人ID
	 * @return
	 * 换班人ID
	 */
	public String getShiftEmployeeId() {
		return shiftEmployeeId;
	}
	/**
	 * 设置换班人ID
	 * @return
	 * 换班人ID
	 */
	public void setShiftEmployeeId(String shiftEmployeeId) {
		this.shiftEmployeeId = shiftEmployeeId;
	}
	/**
	 * 获取换班人名称
	 * @return
	 * 换班人名称
	 */
	public String getShiftEmployeeName() {
		return shiftEmployeeName;
	}
	/**
	 * 设置换班人名称
	 * @return
	 * 换班人名称
	 */
	public void setShiftEmployeeName(String shiftEmployeeName) {
		this.shiftEmployeeName = shiftEmployeeName;
	}
	/**
	 * 获取请假开始时假
	 * @return
	 * 请假开始时假
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置请假开始时假
	 * @return
	 * 请假开始时假
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取请假结束时假
	 * @return
	 * 请假结束时假
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置请假结束时假
	 * @return
	 * 请假结束时假
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取补班日期
	 * @return
	 * 补班日期
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getSupTime() {
		return supTime;
	}
	/**
	 * 设置补班日期
	 * @return
	 * 补班日期
	 */
	public void setSupTime(Date supTime) {
		this.supTime = supTime;
	}
	/**
	 * 获取换班日期
	 * @return
	 * 换班日期
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getShiftTime() {
		return shiftTime;
	}
	/**
	 * 设置换班日期
	 * @return
	 * 换班日期
	 */
	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}
	/**
	 * 获取创建时间
	 * @return
	 * 创建时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @return
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取补班状态
	 * @return
	 * 补班状态
	 */
	public Boolean getIsSup() {
		return isSup;
	}
	/**
	 * 设置补班状态
	 * @return
	 * 补班状态
	 */
	public void setIsSup(Boolean isSup) {
		this.isSup = isSup;
	}
	/**
	 * 获取换班人ID
	 * @return
	 * 换班人ID
	 */
	public Set<LeaveApprovalInfo> getApprovals() {
		return approvals;
	}
	/**
	 * 设置换班人ID
	 * @return
	 * 换班人ID
	 */
	public void setApprovals(Set<LeaveApprovalInfo> approvals) {
		this.approvals = approvals;
	}
	/**
	 * 获取请假类型
	 * @return
	 * 请假类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置请假类型
	 * @param type
	 * 请假类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取换班部门ID
	 * @return
	 * 换班部门ID
	 */
	public String getShiftDepartmentId() {
		return shiftDepartmentId;
	}
	/**
	 * 设置换班部门ID
	 * @param shiftDepartmentId
	 * 换班部门ID
	 */
	public void setShiftDepartmentId(String shiftDepartmentId) {
		this.shiftDepartmentId = shiftDepartmentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getaLeaveId() {
		return aLeaveId;
	}
	public void setaLeaveId(String aLeaveId) {
		this.aLeaveId = aLeaveId;
	}
	public String getaEmplId() {
		return aEmplId;
	}
	public void setaEmplId(String aEmplId) {
		this.aEmplId = aEmplId;
	}
	public Integer getaType() {
		return aType;
	}
	public void setaType(Integer aType) {
		this.aType = aType;
	}
	public Integer getaStatus() {
		return aStatus;
	}
	public void setaStatus(Integer aStatus) {
		this.aStatus = aStatus;
	}
	
}