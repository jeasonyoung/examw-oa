package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 部门计划成员信息。
 * @author lq.
 * @since 2014-08-01.
 */
public class DeptPlanMemberInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,content,remarks,deptPlanId,deptPlanName,emplId,emplName,entryId,entryName;
	private Integer status;
	private Date createTime,lastTime,startTime,endTime,finishTime;
	/**
	 * 获取部门计划成员ID。
	 * @return 部门计划成员ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置部门计划成员ID。
	 * @param id
	 * 部门计划成员ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取部门计划成员内容。
	 * @return 部门计划成员内容。
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置部门计划成员内容。
	 * @param content
	 * 部门计划成员内容。
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取部门计划成员备注。
	 * @return 部门计划成员备注。
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置部门计划成员备注。
	 * @param remarks 
	 * 部门计划成员备注。
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取所属部门计划ID。
	 * @return 所属部门计划ID。
	 */
	public String getDeptPlanId() {
		return deptPlanId;
	}
	/**
	 * 设置所属部门计划ID。
	 * @param deptPlanId
	 * 所属部门计划ID。
	 */
	public void setDeptPlanId(String deptPlanId) {
		this.deptPlanId = deptPlanId;
	}
	/**
	 * 获取所属部门计划名称。
	 * @return 所属部门计划名称。
	 */
	public String getDeptPlanName() {
		return deptPlanName;
	}
	/**
	 * 设置所属部门计划名称。
	 * @param deptPlanName
	 * 所属部门计划名称。
	 */
	public void setDeptPlanName(String deptPlanName) {
		this.deptPlanName = deptPlanName;
	}
	/**
	 * 获取所属员工ID。
	 * @return 所属员工ID。
	 */
	public String getEmplId() {
		return emplId;
	}
	/**
	 * 设置所属员工ID。
	 * @param emplId
	 * 所属员工ID。
	 */
	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}
	/**
	 * 获取所属员工名称。
	 * @return 所属员工名称。
	 */
	public String getEmplName() {
		return emplName;
	}
	/**
	 * 设置所属员工名称。
	 * @param emplName
	 * 所属以名称。
	 */
	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}
	/**
	 * 获取所属奖惩条目ID。
	 * @return 所属奖惩条目ID。
	 */
	public String getEntryId() {
		return entryId;
	}
	/**
	 * 设置所属奖惩条目ID。
	 * @param entryId
	 * 所属奖惩条目ID。
	 */
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	/**
	 * 获取所属奖惩条目名称。
	 * @return 所属奖惩条目名称。
	 */
	public String getEntryName() {
		return entryName;
	}
	/**
	 * 设置所属奖惩条目名称。
	 * @param entryName
	 * 所属奖惩条目名称。
	 */
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	/**
	 * 获取部门计划成员状态。
	 * @return 部门计划成员状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置部门计划成员状态。
	 * @param status
	 * 部门计划成员状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取部门计划成员创建时间。
	 * @return 部门计划成员创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置部门计划成员创建时间。
	 * @param createTime
	 * 部门计划成员创建时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取部门计划成员最后修改时间。
	 * @return 部门计划成员最后修改时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastTime() {
		return lastTime;
	}
	/**
	 * 设置部门计划成员最后修改时间。
	 * @param lastTime
	 * 部门计划成员最后修改时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取部门计划成员开始时间。
	 * @return 部门计划成员开始时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置部门计划成员开始时间。
	 * @param startTime
	 * 部门计划成员开始时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取部门计划成员结束时间。
	 * @return 部门计划成员结束时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置部门计划成员结束时间。
	 * @param endTime
	 * 部门计划成员结束时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取部门计划成员完成时间。
	 * @return 部门计划成员完成时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置部门计划成员完成时间。
	 * @param finishTime
	 * 部门计划成员完成时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
}