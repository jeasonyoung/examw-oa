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
	private Integer type,result;
	private Leave leave;
	private Employee employee;
	private Date createTime;
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
	 *  获取审批结果。
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