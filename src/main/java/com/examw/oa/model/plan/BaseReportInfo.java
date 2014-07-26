package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.examw.model.Paging;
import com.examw.oa.controllers.IUser;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 员工报告信息基类。
 * @author yangyong.
 * @since 2014-07-25.
 */
public abstract class BaseReportInfo extends Paging implements IUser {
	private static final long serialVersionUID = 1L;
	private String id,employeeName,deptName,postName,rankName, typeName,statusName,planDetail,summaryDetail,supportDetail, suggetsionDetail,currentUserId;
	private Date createTime,postTime,lastPostTime;
	private Integer type,status; 
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