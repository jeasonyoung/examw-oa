package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 计划总结
 * @author lq
 * @since 2014-07-02
 */
public class ReportInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,typeName,employeeId,employeeName;
	private Integer type,Status;
	private String planId,summaryId,suggetsionsId,supportId;
	private String planDetail,summaryDetail,suggetsionsDetail,supportDetail;
	private String[] businessId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postTime,lastPostTime;
	/**
	 * 获取员工报表ID
	 * @return
	 * 员工报表ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工报表ID
	 * @param id
	 * 员工报表ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取员工ID
	 * @return
	 * 员工ID
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置员工ID
	 * @param employeeId
	 * 员工ID
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取员工名称
	 * @return
	 * 员工名称
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * 设置员工名称
	 * @param employeeName
	 * 员工名称
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * 获取类型名称
	 * @return
	 * 类型名称
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置类型名称
	 * @param typeName
	 * 类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取员工报表类型
	 * @return
	 * 员工报表类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置员工报表类型
	 * @param type
	 * 员工报表类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取员工报表状态
	 * @return
	 * 员工报表状态
	 */
	public Integer getStatus() {
		return Status;
	}
	/**
	 * 设置员工报表状态
	 * @param status
	 * 员工报表状态
	 */
	public void setStatus(Integer status) {
		Status = status;
	}
	/**
	 * 获取员工报表创建时间
	 * @return
	 * 员工报表创建时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置员工报表创建时间
	 * @param createTime
	 * 员工报表创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取员工报表提交时间
	 * @return
	 * 员工报表提交时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getPostTime() {
		return postTime;
	}
	/**
	 * 设置员工报表提交时间
	 * @param postTime
	 * 员工报表提交时间
	 */
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	/**
	 * 获取员工报表最后提交时间
	 * @return
	 * 员工报表最后提交时间
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastPostTime() {
		return lastPostTime;
	}
	/**
	 * 设置员工报表最后提交时间
	 * @param lastPostTime
	 * 员工报表最后提交时间
	 */
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
	/**
	 * 设置计划ID
	 * @return
	 * 计划ID
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * 获取计划ID
	 * @return
	 * 计划ID
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * 获取总结ID
	 * @return
	 * 总结ID
	 */
	public String getSummaryId() {
		return summaryId;
	}
	/**
	 * 设置总结ID
	 * @return
	 * 总结ID
	 */
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	/**
	 * 获取建议ID
	 * @return
	 * 建议ID
	 */
	public String getSuggetsionsId() {
		return suggetsionsId;
	}
	/**
	 * 设置建议ID
	 * @return
	 * 建议ID
	 */
	public void setSuggetsionsId(String suggetsionsId) {
		this.suggetsionsId = suggetsionsId;
	}
	/**
	 * 获取困难ID
	 * @return
	 * 困难ID
	 */
	public String getSupportId() {
		return supportId;
	}
	/**
	 * 设置困难ID
	 * @return
	 * 困难ID
	 */
	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}
	/**
	 * 获取计划内容
	 * @return
	 * 计划内容
	 */
	public String getPlanDetail() {
		return planDetail;
	}
	/**
	 * 设置计划内容
	 * @return
	 * 计划内容
	 */
	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}
	/**
	 * 获取总结内容
	 * @return
	 * 总结内容
	 */
	public String getSummaryDetail() {
		return summaryDetail;
	}
	/**
	 * 设置总结内容
	 * @return
	 * 总结内容
	 */
	public void setSummaryDetail(String summaryDetail) {
		this.summaryDetail = summaryDetail;
	}
	/**
	 * 获取建议内容
	 * @return
	 * 建议内容
	 */
	public String getSuggetsionsDetail() {
		return suggetsionsDetail;
	}
	/**
	 * 设置建议内容
	 * @return
	 * 建议内容
	 */
	public void setSuggetsionsDetail(String suggetsionsDetail) {
		this.suggetsionsDetail = suggetsionsDetail;
	}
	/**
	 * 获取困难内容
	 * @return
	 * 困难内容
	 */
	public String getSupportDetail() {
		return supportDetail;
	}
	/**
	 * 设置困难内容
	 * @return
	 * 困难内容
	 */
	public void setSupportDetail(String supportDetail) {
		this.supportDetail = supportDetail;
	}
	/**
	 * 获取系统业务数组
	 * @return
	 * 系统业务数组
	 */
	public String[] getBusinessId() {
		return businessId;
	}
	/**
	 * 设置系统业务数组
	 * @param businessId
	 * 系统业务数组
	 */
	public void setBusinessId(String[] businessId) {
		this.businessId = businessId;
	}
}