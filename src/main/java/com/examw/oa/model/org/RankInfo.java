package com.examw.oa.model.org;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.examw.model.Paging;
/**
 * 员工级别信息。
 * 
 * 修正级别代码类型。
 * @author yangyong.
 * @since 2014-06-11.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RankInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,code;
	 
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