package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.examw.model.Paging;
import com.examw.oa.controllers.IUser;
import com.examw.oa.support.CustomDateSerializer;
/**
 *  员工报告信息。
 * @author lq
 * @since 2014-07-02
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ReportInfo extends Paging implements IUser {
	private static final long serialVersionUID = 1L;
	private String id,employeeId,employeeName,deptName,postName,rankName, typeName,statusName,
							planId,planDetail,
							summaryId,summaryDetail,
							supportId,supportDetail,
							suggetsionId,suggetsionDetail,
							currentUserId;
	private String[] planBusinessId,summaryBusinessId,supportBusinessId,suggetsionBusinessId;
	private Integer type,status; 
	private Date createTime,postTime,lastPostTime;
	/**
	 * 获取报告ID。
	 * @return 报告ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置报告ID。
	 * @param id
	 * 报告ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所属员工ID。
	 * @return 所属员工ID。
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置所属员工ID。
	 * @param employeeId
	 * 所属员工ID。
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取员工名称。
	 * @return 员工名称。
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置员工名称。
	 * @param employeeName
	 * 员工名称。
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取员工所属部门名称。
	 * @return 员工所属部门名称。
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置员工所属部门名称。
	 * @param deptName
	 * 员工所属部门名称。
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取所属岗位名称。
	 * @return 所属岗位名称。
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置所属岗位名称。
	 * @param postName
	 * 	所属岗位名称。
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取员工等级。
	 * @return 员工等级。
	 */
	public String getRankName() {
		return rankName;
	}
	/**
	 * 设置员工等级。
	 * @param rankName
	 * 员工等级。
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	/**
	 * 获取报告类型。
	 * @return 报告类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置报告类型。
	 * @param type
	 * 报告类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取报告类型名称。
	 * @return 报告类型名称。
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置报告类型名称。
	 * @param typeName
	 * 报告类型名称。
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取报告状态。
	 * @return 报告状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置报告状态。
	 * @param status
	 * 报告状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取报告状态名称。
	 * @return 报告状态名称。
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * 设置报告状态名称。
	 * @param statusName
	 * 报告状态名称。
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * 获取报告计划ID。
	 * @return 报告计划ID。
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * 设置报告计划ID。
	 * @param planId
	 * 报告计划ID。
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * 获取报告计划内容。
	 * @return 报告计划内容。
	 */
	public String getPlanDetail() {
		return planDetail;
	}
	/**
	 * 设置报告计划内容。
	 * @param planDetail
	 * 报告计划内容。
	 */
	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}
	/**
	 * 获取报告计划关联业务系统ID。
	 * @return 报告计划关联业务系统ID。
	 */
	public String[] getPlanBusinessId() {
		return planBusinessId;
	}
	/**
	 * 设置报告计划关联业务系统ID。
	 * @param planBusinessId
	 * 报告计划关联业务系统ID。
	 */
	public void setPlanBusinessId(String[] planBusinessId) {
		this.planBusinessId = planBusinessId;
	}
	/**
	 * 获取报告总结ID。
	 * @return 报告总结ID。
	 */
	public String getSummaryId() {
		return summaryId;
	}
	/**
	 * 设置报告总结ID。
	 * @param summaryId
	 * 报告总结ID。
	 */
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	/**
	 * 获取报告总结内容。
	 * @return 报告总结内容。
	 */
	public String getSummaryDetail() {
		return summaryDetail;
	}
	/**
	 * 设置报告总结内容。
	 * @param summaryDetail
	 * 报告总结内容。
	 */
	public void setSummaryDetail(String summaryDetail) {
		this.summaryDetail = summaryDetail;
	}
	/**
	 * 获取报告总结关联业务系统ID。
	 * @return 报告总结关联业务系统ID。
	 */
	public String[] getSummaryBusinessId() {
		return summaryBusinessId;
	}
	/**
	 * 设置报告总结关联业务系统ID。
	 * @param summaryBusinessId
	 * 报告总结关联业务系统ID。
	 */
	public void setSummaryBusinessId(String[] summaryBusinessId) {
		this.summaryBusinessId = summaryBusinessId;
	}
	/**
	 * 获取报告困难/支援ID。
	 * @return 报告困难/支援ID。
	 */
	public String getSupportId() {
		return supportId;
	}
	/**
	 * 设置报告困难/支援ID。
	 * @param supportId
	 * 报告困难/支援ID。
	 */
	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}
	/**
	 * 获取报告困难/支援内容。
	 * @return 报告困难/支援内容。
	 */
	public String getSupportDetail() {
		return supportDetail;
	}
	/**
	 * 设置报告困难/支援内容。
	 * @param supportDetail
	 * 报告困难/支援内容。
	 */
	public void setSupportDetail(String supportDetail) {
		this.supportDetail = supportDetail;
	}
	/**
	 *  获取报告困难/支援关联业务系统ID。
	 * @return 报告困难/支援关联业务系统ID。
	 */
	public String[] getSupportBusinessId() {
		return supportBusinessId;
	}
	/**
	 * 设置报告困难/支援关联业务系统ID。
	 * @param supportBusinessId
	 * 报告困难/支援关联业务系统ID。
	 */
	public void setSupportBusinessId(String[] supportBusinessId) {
		this.supportBusinessId = supportBusinessId;
	}
	/**
	 * 获取报告建议/意见ID。
	 * @return 报告建议/意见ID。
	 */
	public String getSuggetsionId() {
		return suggetsionId;
	}
	/**
	 * 设置报告建议/意见ID。
	 * @param suggetsionId
	 * 报告建议/意见ID。
	 */
	public void setSuggetsionId(String suggetsionId) {
		this.suggetsionId = suggetsionId;
	}
	/**
	 * 获取报告建议/意见内容。
	 * @return 报告建议/意见内容。
	 */
	public String getSuggetsionDetail() {
		return suggetsionDetail;
	}
	/**
	 * 设置报告建议/意见内容。
	 * @param suggetsionDetail
	 * 报告建议/意见内容。
	 */
	public void setSuggetsionDetail(String suggetsionDetail) {
		this.suggetsionDetail = suggetsionDetail;
	}
	/**
	 *  获取报告建议/意见关联业务系统ID。
	 * @return 报告建议/意见关联业务系统ID。
	 */
	public String[] getSuggetsionBusinessId() {
		return suggetsionBusinessId;
	}
	/**
	 * 设置报告建议/意见关联业务系统ID。
	 * @param suggetsionBusinessId
	 * 报告建议/意见关联业务系统ID。
	 */
	public void setSuggetsionBusinessId(String[] suggetsionBusinessId) {
		this.suggetsionBusinessId = suggetsionBusinessId;
	}
	/**
	 * 获取报告创建时间。
	 * @return 报告创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置报告创建时间。
	 * @param createTime
	 * 报告创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取报告提交时间。
	 * @return 报告提交时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getPostTime() {
		return postTime;
	}
	/**
	 * 设置报告提交时间。
	 * @param postTime
	 * 报告提交时间。
	 */
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	/**
	 * 获取报告最后提交时间。
	 * @return 报告最后提交时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastPostTime() {
		return lastPostTime;
	}
	/**
	 * 设置报告最后提交时间。
	 * @param lastPostTime
	 * 报告最后提交时间。
	 */
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
	/*
	 * 获取当前用户ID。
	 * @see com.examw.oa.controllers.IUser#getCurrentUserId()
	 */
	@Override
	public String getCurrentUserId() {
		return this.currentUserId;
	}
	/*
	 * 设置当前用户ID。
	 * @see com.examw.oa.controllers.IUser#setCurrentUserId(java.lang.String)
	 */
	@Override
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
}