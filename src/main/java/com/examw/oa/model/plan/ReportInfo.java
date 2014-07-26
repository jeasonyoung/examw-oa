package com.examw.oa.model.plan;
 
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 *  员工报告信息。
 * @author lq
 * @since 2014-07-02
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ReportInfo extends BaseReportInfo {
	private static final long serialVersionUID = 1L;
	private String planId,summaryId,supportId,suggetsionId;
	private String[] planBusinessId,summaryBusinessId,supportBusinessId,suggetsionBusinessId;
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
}