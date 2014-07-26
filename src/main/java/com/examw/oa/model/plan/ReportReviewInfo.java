package com.examw.oa.model.plan;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 员工报告查阅信息。
 * @author yangyong.
 * @since 2014-07-25.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ReportReviewInfo extends BaseReportInfo {
	private static final long serialVersionUID = 1L;
	private String deptId, planBusiness,summaryBusiness,supportBusiness,suggetsionBusiness;
	/**
	 * 获取部门ID。
	 * @return 部门ID。
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 *  设置部门ID。
	 * @param deptId
	 * 部门ID。
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取报告计划关联业务系统。
	 * @return 报告计划关联业务系统。
	 */
	public String getPlanBusiness() {
		return planBusiness;
	}
	/**
	 * 设置报告计划关联业务系统。
	 * @param planBusiness
	 * 报告计划关联业务系统。
	 */
	public void setPlanBusiness(String planBusiness) {
		this.planBusiness = planBusiness;
	}
	/**
	 * 获取报告总结关联业务系统。
	 * @return 报告总结关联业务系统。
	 */
	public String getSummaryBusiness() {
		return summaryBusiness;
	}
	/**
	 * 设置报告总结关联业务系统。
	 * @param summaryBusiness
	 * 报告总结关联业务系统。
	 */
	public void setSummaryBusiness(String summaryBusiness) {
		this.summaryBusiness = summaryBusiness;
	}
	/**
	 * 获取报告困难/支援关联业务系统。
	 * @return 报告困难/支援关联业务系统。
	 */
	public String getSupportBusiness() {
		return supportBusiness;
	}
	/**
	 * 设置报告困难/支援关联业务系统。
	 * @param supportBusiness
	 * 报告困难/支援关联业务系统。
	 */
	public void setSupportBusiness(String supportBusiness) {
		this.supportBusiness = supportBusiness;
	}
	/**
	 * 获取报告建议/意见关联业务系统。
	 * @return 报告建议/意见关联业务系统。
	 */
	public String getSuggetsionBusiness() {
		return suggetsionBusiness;
	}
	/**
	 * 设置报告建议/意见关联业务系统。
	 * @param suggetsionBusiness
	 * 报告建议/意见关联业务系统。
	 */
	public void setSuggetsionBusiness(String suggetsionBusiness) {
		this.suggetsionBusiness = suggetsionBusiness;
	}
}