package com.examw.oa.domain.org;

import java.io.Serializable;
/**
 * 员工级别。
 * @author lq.
 * @since 2014-05-11.
 */
public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name;
	/**
	 * 获取员工级别ID。
	 * @return
	 * 员工级别ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工级别ID。
	 * @return
	 * 等级信息ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取员工级别代码。
	 * @return
	 * 员工级别代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 获取员工级别代码。
	 * @return
	 * 员工级别代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取员工级别名称。
	 * @return
	 * 员工级别名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置员工级别名称。
	 * @return
	 * 员工级别名称。
	 */
	public void setName(String name) {
		this.name = name;
	}	
}