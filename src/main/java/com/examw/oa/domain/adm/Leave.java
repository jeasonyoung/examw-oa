package com.examw.oa.domain.adm;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.examw.oa.domain.org.Employee;
/**
 * 请假条。
 * @author yangyong.
 * @since 2014-07-14.
 */
public class Leave implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,deptName,postName,resaon,shiftEmployee;
	private Employee employee;
	private Date createTime,startTime,endTime,supTime,shiftTime;
	private Integer sup,type,status,result;
	private Set<LeaveApproval> approvals;
	/**
	 * 类型－休假。
	 */
	public static final Integer TYPE_VACATION= 1;
	/**
	 * 类型－事假。
	 */
	public static final Integer TYPE_COMPA = 2;
	/**
	 * 类型－其他。
	 */
	public static final Integer TYPE_OTHER = 3;
	/**
	 * 类型－病假证明有。
	 */
	public static final Integer TYPE_SICK_PROVE = 4;
	/**
	 * 类型－病假证明无。
	 */
	public static final Integer TYPE_SICK_NONPROVE = 5;
	
	/**
	 * 状态－部门经理（主管）审批。
	 */
	public static final Integer STATUS_APPROVAL_DEPT = 1;
	/**
	 * 状态－行政人事部门审批。
	 */
	public static final Integer STATUS_APPROVAL_HR = 2;
	/**
	 * 状态－公司总经理审批。
	 */
	public static final Integer STATUS_APPROVAL_BOSS = 3;
	
	/**
	 * 审批结果－提交。
	 */
	public static final Integer RESULT_POST = -1;
	/**
	 * 审批结果－审批中
	 */
	public static final Integer RESULT_APPROVAL = 0;
	/**
	 * 审批结果－同意。
	 */
	public static final Integer RESULT_AGREE = 1;
	/**
	 * 审批结果－不同意。
	 */
	public static final Integer RESULT_DISAGREE = 2;
	/**
	 * 补班状态－补班。
	 */
	public static final Integer SUP_YES = 1;
	/**
	 * 补班状态－不补班。
	 */
	public static final Integer SUP_NO = 0;
	
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
	 * 获取请假员工。
	 * @return 请假员工。
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * 设置请假员工。
	 * @param employee
	 * 请假员工。
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	 * 获取所属职务名称。
	 * @return 所属职务名称。
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置所属职务名称。
	 * @param postName
	 * 所属职务名称。
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取请假开始时间。
	 * @return 请假开始时间。
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置请假开始时间。
	 * @param startTime
	 * 请假开始时间。
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取请假结束时间。
	 * @return 请假结束时间。
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置请假结束时间。
	 * @param endTime
	 * 请假结束时间。
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * 获取请假条状态。
	 * @return 请假条状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置请假条状态。
	 * @param status
	 * 请假条状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取审批结果。
	 * @return 审批结果。
	 */
	public Integer getResult() {
		return result;
	}
	/**
	 * 设置审批结果。
	 * @param result
	 * 审批结果。
	 */
	public void setResult(Integer result) {
		this.result = result;
	}
	/**
	 *  获取补班类型。
	 * @return 补班类型（0-不补班，1-补班）。
	 */
	public Integer getSup() {
		return sup;
	}
	/**
	 * 设置补班类型。
	 * @param sup
	 * 补班类型（0-不补班，1-补班）。
	 */
	public void setSup(Integer sup) {
		this.sup = sup;
	}
	/**
	 * 获取补班日期。
	 * @return 补班日期。
	 */
	public Date getSupTime() {
		return supTime;
	}
	/**
	 * 设置补班日期。
	 * @param supTime
	 * 补班日期。
	 */
	public void setSupTime(Date supTime) {
		this.supTime = supTime;
	}
	/**
	 * 获取换班日期。
	 * @return 换班日期。
	 */
	public Date getShiftTime() {
		return shiftTime;
	}
	/**
	 * 设置换班日期。
	 * @param shiftTime
	 * 换班日期。
	 */
	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}
	/**
	 * 获取换班员工。
	 * @return 换班员工。
	 */
	public String getShiftEmployee() {
		return shiftEmployee;
	}
	/**
	 * 设置换班员工。
	 * @param shiftEmployee
	 * 换班员工。
	 */
	public void setShiftEmployee(String shiftEmployee) {
		this.shiftEmployee = shiftEmployee;
	}
	/**
	 *  获取创建时间。
	 * @return 创建时间。
	 */
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
	/**
	 * 获取审批集合。
	 * @return 审批集合。
	 */
	public Set<LeaveApproval> getApprovals() {
		return approvals;
	}
	/**
	 * 设置审批集合。
	 * @param approvals
	 * 审批集合。
	 */
	public void setApprovals(Set<LeaveApproval> approvals) {
		this.approvals = approvals;
	}
}