package com.examw.oa.model.adm;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.controllers.IUser;
import com.examw.oa.support.CustomDateSerializer;
import com.examw.oa.support.CustomShortDateSerializer;
/**
 * 请假条信息。
 * @author lq.
 * @since 2014-07-15.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class LeaveInfo extends Paging implements IUser{
	private static final long serialVersionUID = 1L;
	private String id,employeeId,employeeName,deptId,deptName,postName,resaon,
		supName,typeName,statusName,resultName,approval,shiftEmployee,current_user_id,
		deptApprovalId,deptApproval,deptApprovalResultName,
		hrApprovalId,hrApproval,hrApprovalResultName,
		bossApprovalId,bossApproval,bossApprovalResultName;
	private Integer sup,type,status,result,
		deptApprovalResult,hrApprovalResult,bossApprovalResult;
	private Date createTime,startTime,endTime,supTime,shiftTime,
		deptApprovalTime,hrApprovalTime,bossApprovalTime;
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
	 * 获取请假员工所属部门ID。
	 * @return 请假员工所属部门ID。
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置请假员工所属部门ID。
	 * @param deptId
	 * 请假员工所属部门ID。
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取请假员工所属部门名称。
	 * @return 请假员工所属部门名称。
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置请假员工所属部门名称。
	 * @param deptName
	 *  请假员工所属部门名称。
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
	 * 获取请假开始时间。
	 * @return 请假开始时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置请假开始时间。
	 * @param startTime
	 * 请假开始时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取请假结束时间。
	 * @return 请假结束时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置请假结束时间。
	 * @param endTime
	 * 请假结束时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 *  获取请假天数。
	 * @return
	 */
	public Long getTotal(){
		if(this.getStartTime() != null && this.getEndTime() != null){
			long dif = (this.getEndTime().getTime() - this.getStartTime().getTime()) / 1000;
			return dif / 3600 / 24;
		}
		return 0L;
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
	 * 获取换班人。
	 * @return 换班人。
	 */
	public String getShiftEmployee() {
		return shiftEmployee;
	}
	/**
	 * 设置换班人。
	 * @param shiftEmployee
	 * 换班人。
	 */
	public void setShiftEmployee(String shiftEmployee) {
		this.shiftEmployee = shiftEmployee;
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
	/**
	 * 获取补班类型。
	 * @return 补班类型。
	 */
	public Integer getSup() {
		return sup;
	}
	/**
	 * 设置补班类型。
	 * @param sup
	 * 补班类型。
	 */
	public void setSup(Integer sup) {
		this.sup = sup;
	}
	/**
	 * 获取补班类型名称。
	 * @return 补班类型名称。
	 */
	public String getSupName() {
		return supName;
	}
	/**
	 * 设置补班类型名称。
	 * @param supName
	 * 补班类型名称。
	 */
	public void setSupName(String supName) {
		this.supName = supName;
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
	 * 获取请假类型名称。
	 * @return 请假类型名称。
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置请假类型名称。
	 * @param typeName
	 * 请假类型名称。
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	 * 设置请假条状态。
	 * @param status
	 * 审批结果状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取请假条状态名称。
	 * @return 请假条状态名称。
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * 设置请假条状态名称。
	 * @param statusName
	 * 请假条状态名称。
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * 获取请假条审批结果。
	 * @return 请假条审批结果。
	 */
	public Integer getResult() {
		return result;
	}
	/**
	 * 设置请假条审批结果。
	 * @param result
	 * 请假条审批结果。
	 */
	public void setResult(Integer result) {
		this.result = result;
	}
	/**
	 * 获取请假条审批结果名称。
	 * @return 请假条审批结果名称。
	 */
	public String getResultName() {
		return resultName;
	}
	/**
	 * 设置请假条审批结果名称。
	 * @param resultName
	 * 请假条审批结果名称。
	 */
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	/**
	 * 获取请假条审批信息。
	 * @return 请假条审批信息。
	 */
	public String getApproval() {
		return approval;
	}
	/**
	 * 设置请假条审批信息。
	 * @param approval
	 * 请假条审批信息。
	 */
	public void setApproval(String approval) {
		this.approval = approval;
	}
	/*
	 * 获取当前用户ID。
	 * @see com.examw.oa.controllers.IUser#getCurrentUserId()
	 */
	@Override
	public String getCurrentUserId() {
		return this.current_user_id;
	}
	/*
	 * 设置当前用户ID。
	 * @see com.examw.oa.controllers.IUser#setCurrentUserId(java.lang.String)
	 */
	@Override
	public void setCurrentUserId(String currentUserId) {
		this.current_user_id = currentUserId;
	}
	/**
	 * 获取部门审批ID。
	 * @return 部门审批ID。
	 */
	public String getDeptApprovalId() {
		return deptApprovalId;
	}
	/**
	 * 设置部门审批ID。
	 * @param deptApprovalId
	 * 部门审批ID。
	 */
	public void setDeptApprovalId(String deptApprovalId) {
		this.deptApprovalId = deptApprovalId;
	}
	/**
	 * 获取部门审批结果。
	 * @return 部门审批结果。
	 */
	public Integer getDeptApprovalResult() {
		return deptApprovalResult;
	}
	/**
	 * 设置部门审批状态。
	 * @param deptApprovalResult
	 * 部门审批状态。
	 */
	public void setDeptApprovalResult(Integer deptApprovalResult) {
		this.deptApprovalResult = deptApprovalResult;
	}
	/**
	 * 获取部门审批结果名称。
	 * @return 部门审批结果名称。
	 */
	public String getDeptApprovalResultName() {
		return deptApprovalResultName;
	}
	/**
	 * 设置部门审批结果名称。
	 * @param deptApprovalResultName
	 * 部门审批结果名称。
	 */
	public void setDeptApprovalResultName(String deptApprovalResultName) {
		this.deptApprovalResultName = deptApprovalResultName;
	}
	/**
	 * 获取部门审批意见。
	 * @return 部门审批意见。
	 */
	public String getDeptApproval() {
		return deptApproval;
	}
	/**
	 * 设置部门审批意见。
	 * @param deptApproval
	 * 部门审批意见。
	 */
	public void setDeptApproval(String deptApproval) {
		this.deptApproval = deptApproval;
	}
	/**
	 * 获取部门审批时间。
	 * @return 部门审批时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getDeptApprovalTime() {
		return deptApprovalTime;
	}
	/**
	 * 设置部门审批时间。
	 * @param deptApprovalTime
	 * 部门审批时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setDeptApprovalTime(Date deptApprovalTime) {
		this.deptApprovalTime = deptApprovalTime;
	}
	/**
	 * 获取HR审批ID。
	 * @return HR审批ID。
	 */
	public String getHrApprovalId() {
		return hrApprovalId;
	}
	/**
	 * 设置HR审批ID。
	 * @param hrApprovalId
	 * HR审批ID。
	 */
	public void setHrApprovalId(String hrApprovalId) {
		this.hrApprovalId = hrApprovalId;
	}
	/**
	 * 获取HR审批结果。
	 * @return HR审批结果。
	 */
	public Integer getHrApprovalResult() {
		return hrApprovalResult;
	}
	/**
	 * 设置HR审批结果。
	 * @param hrApprovalResult
	 * HR审批结果。
	 */
	public void setHrApprovalResult(Integer hrApprovalResult) {
		this.hrApprovalResult = hrApprovalResult;
	}
	/**
	 * 获取HR审批结果名称。
	 * @return HR审批结果名称。
	 */
	public String getHrApprovalResultName() {
		return hrApprovalResultName;
	}
	/**
	 * 设置HR审批结果名称。
	 * @param hrApprovalResultName
	 * HR审批结果名称。
	 */
	public void setHrApprovalResultName(String hrApprovalResultName) {
		this.hrApprovalResultName = hrApprovalResultName;
	}
	/**
	 * 获取HR审批意见。
	 * @return HR审批意见。
	 */
	public String getHrApproval() {
		return hrApproval;
	}
	/**
	 * 设置HR审批意见。
	 * @param hrApproval
	 * HR审批意见。
	 */
	public void setHrApproval(String hrApproval) {
		this.hrApproval = hrApproval;
	}
	/**
	 * 获取HR审批时间。
	 * @return HR审批时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getHrApprovalTime() {
		return hrApprovalTime;
	}
	/**
	 * 设置HR审批时间。
	 * @param hrApprovalTime
	 * HR审批时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setHrApprovalTime(Date hrApprovalTime) {
		this.hrApprovalTime = hrApprovalTime;
	}
	/**
	 * 获取总经理审批ID。
	 * @return 总经理审批ID。
	 */
	public String getBossApprovalId() {
		return bossApprovalId;
	}
	/**
	 * 设置总经理审批ID。
	 * @param bossApprovalId
	 * 总经理审批ID。
	 */
	public void setBossApprovalId(String bossApprovalId) {
		this.bossApprovalId = bossApprovalId;
	}
	/**
	 * 获取总经理审批结果。
	 * @return 总经理审批结果。
	 */
	public Integer getBossApprovalResult() {
		return bossApprovalResult;
	}
	/**
	 * 设置总经理审批结果。
	 * @param bossApprovalResult
	 * 总经理审批结果。
	 */
	public void setBossApprovalResult(Integer bossApprovalResult) {
		this.bossApprovalResult = bossApprovalResult;
	}
	/**
	 * 获取总经理审批结果。
	 * @return 总经理审批结果。
	 */
	public String getBossApprovalResultName() {
		return bossApprovalResultName;
	}
	/**
	 * 设置总经理审批结果。
	 * @param bossApprovalResultName
	 * 总经理审批结果。
	 */
	public void setBossApprovalResultName(String bossApprovalResultName) {
		this.bossApprovalResultName = bossApprovalResultName;
	}
	/**
	 * 获取总经理审批意见。
	 * @return 总经理审批意见。
	 */
	public String getBossApproval() {
		return bossApproval;
	}
	/**
	 * 设置总经理审批意见。
	 * @param bossApproval
	 * 总经理审批意见。
	 */
	public void setBossApproval(String bossApproval) {
		this.bossApproval = bossApproval;
	}
	/**
	 * 获取总经理审批时间。
	 * @return 总经理审批时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getBossApprovalTime() {
		return bossApprovalTime;
	}
	/**
	 * 设置总经理审批时间。
	 * @param bossApprovalTime
	 * 总经理审批时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setBossApprovalTime(Date bossApprovalTime) {
		this.bossApprovalTime = bossApprovalTime;
	}
}