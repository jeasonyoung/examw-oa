package com.examw.oa.model.org;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.examw.model.Paging;

@JsonSerialize(include = Inclusion.NON_NULL)
public class RankInfo extends Paging {
	private static final long serialVersionUID = 1L;
	public String id,name;
	public Integer code;
	/**
	 * 获取等级信息ID。
	 * @return
	 * 等级信息ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置等级信息ID。
	 * @return
	 * 等级信息ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取等级信息name。
	 * @return
	 * 等级信息name。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置等级信息name。
	 * @return
	 * 等级信息name。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取等级信息编号。
	 * @return
	 * 等级信息编号。
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * 获取等级信息编号。
	 * @return
	 * 等级信息编号。
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
}
