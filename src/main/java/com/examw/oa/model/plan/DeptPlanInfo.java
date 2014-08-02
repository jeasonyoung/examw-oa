package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 部门计划信息。
 * @author lq.
 * @since 2014-08-01.
 */
public class DeptPlanInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,title,deptId,deptName,typeName,statusName;
	private Integer type,status;
	private Date createTime,lastTime,startTime,endTime,finishTime;
	private String mId,mContent,mEmployeeId,mEmployeeName,mPlanId,ePlanName,mEntryId,mEntryName,mSatus,mRemarks;
	private Date mCreateTime,mLastTime,mStartTime,mEndTime,mFinishTime;
	/**
	 * 获取部门计划ID。
	 * @return 部门计划ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置部门计划ID。
	 * @param id
	 * 部门计划ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取部门计划标题。
	 * @return 部门计划标题。
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置部门计划标题。
	 * @param title
	 * 部门计划标题。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取所属部门ID。
	 * @return 所属部门ID。
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置所属部门ID。
	 * @param deptId
	 * 所属部门ID。
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	 * 获取部门计划类型。
	 * @return 部门计划类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置部门计划类型。
	 * @param type
	 * 部门计划类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取部门计划状态。
	 * @return 部门计划状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置部门计划状态。
	 * @param status
	 * 部门计划状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取部门计划创建时间。
	 * @return 部门计划创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置部门计划创建时间。
	 * @param createTime
	 * 部门计划创建时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取部门计划最后修改时间。
	 * @return 部门计划最后修改时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastTime() {
		return lastTime;
	}
	/**
	 * 设置部门计划最后修改时间。
	 * @param lastTime
	 * 部门计划最后修改时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取部门计划开始时间。
	 * @return 部门计划开始时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置部门计划开始时间。
	 * @param startTime
	 * 部门计划开始时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取部门计划结束时间。
	 * @return 部门计划结束时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置部门计划结束时间。
	 * @param endTime
	 * 部门计划结束时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取部门计划完成时间。
	 * @return 部门计划完成时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置部门计划完成时间。
	 * @param finishTime
	 * 部门计划完成时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	public String getmEmployeeId() {
		return mEmployeeId;
	}
	public void setmEmployeeId(String mEmployeeId) {
		this.mEmployeeId = mEmployeeId;
	}
	public String getmEmployeeName() {
		return mEmployeeName;
	}
	public void setmEmployeeName(String mEmployeeName) {
		this.mEmployeeName = mEmployeeName;
	}
	public String getmPlanId() {
		return mPlanId;
	}
	public void setmPlanId(String mPlanId) {
		this.mPlanId = mPlanId;
	}
	public String getePlanName() {
		return ePlanName;
	}
	public void setePlanName(String ePlanName) {
		this.ePlanName = ePlanName;
	}
	public String getmEntryId() {
		return mEntryId;
	}
	public void setmEntryId(String mEntryId) {
		this.mEntryId = mEntryId;
	}
	public String getmEntryName() {
		return mEntryName;
	}
	public void setmEntryName(String mEntryName) {
		this.mEntryName = mEntryName;
	}
	public String getmSatus() {
		return mSatus;
	}
	public void setmSatus(String mSatus) {
		this.mSatus = mSatus;
	}
	public String getmRemarks() {
		return mRemarks;
	}
	public void setmRemarks(String mRemarks) {
		this.mRemarks = mRemarks;
	}
	public Date getmCreateTime() {
		return mCreateTime;
	}
	public void setmCreateTime(Date mCreateTime) {
		this.mCreateTime = mCreateTime;
	}
	public Date getmLastTime() {
		return mLastTime;
	}
	public void setmLastTime(Date mLastTime) {
		this.mLastTime = mLastTime;
	}
	public Date getmStartTime() {
		return mStartTime;
	}
	public void setmStartTime(Date mStartTime) {
		this.mStartTime = mStartTime;
	}
	public Date getmEndTime() {
		return mEndTime;
	}
	public void setmEndTime(Date mEndTime) {
		this.mEndTime = mEndTime;
	}
	public Date getmFinishTime() {
		return mFinishTime;
	}
	public void setmFinishTime(Date mFinishTime) {
		this.mFinishTime = mFinishTime;
	}
}