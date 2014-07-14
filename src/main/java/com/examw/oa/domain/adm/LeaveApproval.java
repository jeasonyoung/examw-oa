package com.examw.oa.domain.adm;

import java.io.Serializable;
import java.util.Date;

import com.examw.oa.domain.org.Employee;

/**
 * 请假审批。
 * @author yangyong.
 * @since 2014-07-14.
 */
public class LeaveApproval implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,approval;
	private Integer type,status;
	private Leave leave;
	private Employee employee;
	private Date createTime;
	/**
	 * 类型－部门经理（主管）意见。
	 */
	public static final Integer TYPE_LEADER = 1;
	/**
	 * 类型－行政人事部意见。
	 */
	public static final Integer TYPE_ADM = 2;
	/**
	 * 类型－公司总经理审批。
	 */
	public static final Integer TYPE_BOSS = 3;
	/**
	 * 审批结果－同意。
	 */
	public static final Integer STATUS_AGREE = 1;
	/**
	 * 审批结果－不同意。
	 */
	public static final Integer STATUS_DISAGREE = 2;
	/**
	 * 获取审批ID。
	 * @return 审批ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置审批ID。
	 * @param id
	 * 审批ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所属请假条。
	 * @return 所属请假条。
	 */
	public Leave getLeave() {
		return leave;
	}
	/**
	 * 设置所属请假条。
	 * @param leave
	 * 所属请假条。
	 */
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
	/**
	 * 获取审批类型。
	 * @return 审批类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置审批类型。
	 * @param type
	 * 审批类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 *  获取审批用户。
	 * @return 审批用户。
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * 设置审批用户。
	 * @param employee
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/**
	 * 获取审批状态。
	 * @return 审批状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置审批状态。
	 * @param status
	 * 审批状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取审批内容。
	 * @return 审批内容。
	 */
	public String getApproval() {
		return approval;
	}
	/**
	 * 设置审批内容。
	 * @param approval
	 * 审批内容。
	 */
	public void setApproval(String approval) {
		this.approval = approval;
	}
	/**
	 * 获取创建时间。
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
}