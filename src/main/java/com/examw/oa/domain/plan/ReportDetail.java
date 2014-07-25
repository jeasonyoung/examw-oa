package com.examw.oa.domain.plan;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
/**
 * 报告明细。
 * @author yangyong.
 * @since 2014-06-23.
 */
public class ReportDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,content;
	private Report report;
	private Integer type;
	private Set<Business> businesses;
	private Date createTime;
	/**
	 * 类型－计划。
	 */
	public static final Integer TYPE_PLAN = 1;
	/**
	 * 类型－总结。
	 */
	public static final Integer TYPE_SUMMARY = 2;
	/**
	 * 类型－困难/支援。
	 */
	public static final Integer TYPE_SUPPORT = 3;
	/**
	 * 类型－建议/意见。
	 */
	public static final Integer TYPE_SUGGESTION = 4;
	/**
	 * 获取明细ID。
	 * @return 明细ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置明细ID。
	 * @param id
	 * 明细ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所属报告。
	 * @return 所属报告。
	 */
	public Report getReport() {
		return report;
	}
	/**
	 * 设置所属报告。
	 * @param report
	 * 所属报告。
	 */
	public void setReport(Report report) {
		this.report = report;
	}
	/**
	 * 获取明细类型。
	 * @return 明细类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置明细类型。
	 * @param type
	 * 明细类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取明细内容。
	 * @return 明细内容。
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置明细内容。
	 * @param content
	 * 明细内容。
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取关联业务系统集合。
	 * @return 关联业务系统集合。
	 */
	public Set<Business> getBusinesses() {
		return businesses;
	}
	/**
	 * 设置关联业务系统集合。
	 * @param businesses
	 * 关联业务系统集合。
	 */
	public void setBusinesses(Set<Business> businesses) {
		this.businesses = businesses;
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