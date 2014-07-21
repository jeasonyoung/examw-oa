package com.examw.oa.domain.org;

import java.io.Serializable;
/**
 * 员工等级。
 * @author lq.
 * @since 2014-05-11.
 */
public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name;
	/**
	 * 获取等级ID。
	 * @return 等级ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置等级ID。
	 * @param id
	 * 等级ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取等级代码。
	 * @return 等级代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 *设置等级代码。
	 * @param code
	 * 等级代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取等级名称。
	 * @return 等级名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置等级名称。
	 * @param name
	 * 等级名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
}