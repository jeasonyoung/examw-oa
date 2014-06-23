package com.examw.oa.domain.plan;

import java.io.Serializable;
import java.util.Date;

import com.examw.oa.domain.org.Employee;

/**
 * 员工报告设置。
 * @author yangyong.
 * @since 2014-06-23.
 */
public class Settings implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Employee employee;
	private Integer type;
	private Date createTime;
	/**
	 * 获取报告设置ID。
	 * @return 报告设置ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 *  设置报告设置ID。
	 * @param id
	 * 报告设置ID。
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
	 * 获取报告类型（位运算）。
	 * @return 报告类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置报告类型（位运算）。
	 * @param type
	 * 报告类型。
	 */
	public void setType(Integer type) {
		this.type = type;
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