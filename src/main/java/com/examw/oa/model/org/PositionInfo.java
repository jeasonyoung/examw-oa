package com.examw.oa.model.org;

import com.examw.model.Paging;

public class PositionInfo extends Paging {
	public String id,name,jobcode;
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置职位ID。
	 * @param id
	 * 职位ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取职位名称。
	 * @return
	 * 职位名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置职位名称。
	 * @param name
	 * 职位名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取职位编号。
	 * @return
	 * 职位编号。
	 */
	public String getJobcode() {
		return jobcode;
	}
	/**
	 * 设置职位编号。
	 * @param name
	 * 职位编号。
	 */
	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}
}
