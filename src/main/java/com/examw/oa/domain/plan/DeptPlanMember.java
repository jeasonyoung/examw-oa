package com.examw.oa.domain.plan;

import java.io.Serializable;
import java.util.Date;

import com.examw.oa.domain.check.Entry;
import com.examw.oa.domain.org.Employee;

/**
 * 部门计划成员。
 * <pre>
 * 部门下的员工可以在［查看我的计划］中查看到自己的计划安排;
 * 并可以进行［完成申请］，由部门负责人确认后更新<code>status</code>并记录<code>finishTime</code>
 * content:由部门负责人分配的计划内容；
 * remarks:为计划成员在［完成申请］时提交的备注信息；
 * </pre>
 * @author yangyong.
 * @since 2014-07-30.
 */
public class DeptPlanMember implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,content,remarks;
	private DeptPlan plan;
	private Employee employee;
	private Entry entry;
	private Integer status;
	private Date createTime,lastTime,startTime,endTime,finishTime;
	/**
	 * 获取所属部门计划。
	 * @return 所属部门计划。
	 */
	public DeptPlan getPlan() {
		return plan;
	}
	/**
	 * 设置所属部门计划。
	 * @param plan
	 * 所属部门计划。
	 */
	public void setPlan(DeptPlan plan) {
		this.plan = plan;
	}
	/**
	 * 获取成员计划ID。
	 * @return 成员计划ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置成员计划ID。
	 * @param id
	 * 成员计划ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取成员计划内容。
	 * @return 成员计划内容。
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置成员计划内容。
	 * @param content
	 * 成员计划内容。
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取所属部门员工。
	 * @return 所属部门员工。
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * 设置所属部门员工。
	 * @param employee
	 * 所属部门员工。
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/**
	 * 获取所属奖惩条目。
	 * @return 所属奖惩条目。
	 */
	public Entry getEntry() {
		return entry;
	}
	/**
	 * 设置所属奖惩条目。
	 * @param entry
	 * 所属奖惩条目。
	 */
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	/**
	 * 获取成员计划状态(0-未开始，1-开始，2-确认请求，3-完成)。
	 * @return 成员计划状态(0-未开始，1-开始，2-确认请求，3-完成)。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置成员计划状态(0-未开始，1-开始，2-确认请求，3-完成)。
	 * @param status
	 * 成员计划状态(0-未开始，1-开始，2-确认请求，3-完成)。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取员工备注。
	 * @return 员工备注。
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置员工备注。
	 * @param remarks
	 * 员工备注。
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	/**
	 * 获取最后修改时间。
	 * @return 最后修改时间。
	 */
	public Date getLastTime() {
		return lastTime;
	}
	/**
	 * 设置最后修改时间。
	 * @param lastTime
	 * 最后修改时间。
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取成员计划开始时间。
	 * @return 成员计划开始时间。
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置成员计划开始时间。
	 * @param startTime
	 * 成员计划开始时间。
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取成员计划结束时间。
	 * @return 成员计划结束时间。
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置成员计划结束时间。
	 * @param endTime
	 * 成员计划结束时间。
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取成员计划完成时间。
	 * @return 成员计划完成时间。
	 */
	public Date getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置成员计划完成时间。
	 * @param finishTime
	 * 成员计划完成时间。
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
}