package com.examw.oa.domain.plan;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.examw.oa.domain.org.Employee;
/**
 * 员工报告。
 * @author yangyong.
 * @since 2014-06-23.
 */
public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private Integer type,status;
	private Employee employee;
	private Set<Detail> details;
	private Date createTime,postTime,lastPostTime;
	/**
	 * 类型－日报。
	 */
	public static final Integer TYPE_DAILY = 0x01;
	/**
	 * 类型－周报。
	 */
	public static final Integer TYPE_WEEKLY = 0x02;
	/**
	 * 类型－月报。
	 */
	public static final Integer TYPE_MONTHLY = 0x04;
	/**
	 * 状态－未提交。
	 */
	public static final Integer STATUS_NONE = 0;
	/**
	 * 状态－已提交。
	 */
	public static final Integer STATUS_POST = 1;
	/**
	 * 状态－已审阅。
	 */
	public static final Integer STATUS_AUDIT = 2;
	/**
	 * 状态－迟交。
	 */
	public static final Integer STATUS_LATE = 3;
	/**
	 * 状态－缺交。
	 */
	public static final Integer STATUS_LACK = 4;
	/**
	 * 获取报告ID。
	 * @return 报告ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 报告ID。
	 * @param id
	 * 报告ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取所属员工。
	 * @return 所属员工。
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * 设置所属员工。
	 * @param employee
	 * 所属员工。
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	 * 获取所属状态。
	 * @return 所属状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置所属状态。
	 * @param status
	 * 所属状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取明细集合。
	 * @return 明细集合。
	 */
	public Set<Detail> getDetails() {
		return details;
	}
	/**
	 * 设置明细集合。
	 * @param details
	 * 明细集合。
	 */
	public void setDetails(Set<Detail> details) {
		this.details = details;
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
	 * 获取提交时间。
	 * @return 提交时间。
	 */
	public Date getPostTime() {
		return postTime;
	}
	/**
	 * 设置提交时间。
	 * @param postTime
	 * 提交时间。
	 */
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	/**
	 * 获取最后提交时间。
	 * @return 最后提交时间。
	 */
	public Date getLastPostTime() {
		return lastPostTime;
	}
	/**
	 * 设置最后提交时间。
	 * @param lastPostTime
	 * 最后提交时间。
	 */
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
}