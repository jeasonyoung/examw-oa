package com.examw.oa.domain.plan;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.examw.oa.domain.org.Department;

/**
 * 部门计划。
 * <pre>
 * 由部门负责人制定部门计划；
 * startTime：默认为成员中最早的开始时间；
 * endTime：默认为成员中最晚的结束时间；
 * status：为<code>STATUS_NONE</code>时部门成员查询不显示数据；
 * 当计划状态为<code>STATUS_START</code>时<code>startTime</code>记录计划开始时间；
 * 当计划状态为<code>STATUS_FINISHED</code>时<code>finishTime</code>记录计划完成时间，
 * 此为强制完成；
 * 当最后一个成员计划完成时将状态更新为<code>STATUS_FINISHED</code>
 * 同时<code>finishTime</code>记录计划完成时间，为自然完成；
 * </pre>
 * @author yangyong.
 * @since 2014-07-30.
 */
public class DeptPlan implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,title;
	private Department department;
	private Integer type,status;
	private Date createTime,lastTime,startTime,endTime,finishTime;
	private Set<DeptPlanMember> members;
	/**
	 * 计划类型－周计划；
	 */
	public static final Integer TYPE_WEEK = 1;
	/**
	 * 计划类型－月计划；
	 */
	public static final Integer TYPE_MONTH = 2;
	/**
	 * 计划状态－未开始。
	 */
	public static final Integer STATUS_NONE = 0;
	/**
	 * 计划状态－开始执行。
	 */
	public static final Integer STATUS_START = 1;
	/**
	 * 计划状态－确认请求（由计划成员使用）。
	 */
	public static final Integer STATUS_FINISH_REQ= 2;
	/**
	 * 计划状态－执行完成。
	 */
	public static final Integer STATUS_FINISHED= 3;
	/**
	 * 获取计划ID。
	 * @return 计划ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置计划ID。
	 * @param id
	 * 计划ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取计划标题。
	 * @return 计划标题。
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置计划标题。
	 * @param title
	 * 计划标题。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取所属部门。
	 * @return 所属部门。
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 设置所属部门。
	 * @param department
	 * 所属部门。
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * 获取计划成员集合。
	 * @return 计划成员集合。
	 */
	public Set<DeptPlanMember> getMembers() {
		return members;
	}
	/**
	 * 设置计划成员集合。
	 * @param members
	 * 	计划成员集合。
	 */
	public void setMembers(Set<DeptPlanMember> members) {
		this.members = members;
	}
	/**
	 * 获取计划类型（1-周计划，2-月计划）。
	 * @return 计划类型（1-周计划，2-月计划）。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置计划类型（1-周计划，2-月计划）。
	 * @param type
	 * 计划类型（1-周计划，2-月计划）。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取计划状态（0-未开始，1-开始，3-完成）。
	 * @return 计划状态（0-未开始，1-开始，3-完成）。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置计划状态（0-未开始，1-开始，3-完成）。
	 * @param status
	 * 计划状态（0-未开始，1-开始，3-完成）。
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * 获取计划开始时间。
	 * @return 计划开始时间。
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置计划开始时间。
	 * @param startTime
	 * 计划开始时间。
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取计划结束时间。
	 * @return 计划结束时间。
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置计划结束时间。
	 * @param endTime
	 * 计划结束时间。
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取计划完成时间。
	 * @return 计划完成时间。
	 */
	public Date getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置计划完成时间。
	 * @param finishTime
	 * 计划完成时间。
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
}