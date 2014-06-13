package com.examw.oa.domain.org;

import java.io.Serializable;
/**
 * 岗位信息。
 * @author lq.
 * @since 2014-06-13.
 */
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,code;
	private Depart depart;
	/**
	 * 获取岗位ID。
	 * @return
	 * 岗位ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置岗位ID。
	 * @return
	 * 岗位ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取岗位名称。
	 * @return
	 * 岗位名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置岗位名称。
	 * @return
	 * 岗位名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取岗位编号。
	 * @return
	 * 岗位编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置岗位编号。
	 * @return
	 * 岗位编号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取所属部门。
	 * @return
	 * 所属部门。
	 */
	public Depart getDepart() {
		return depart;
	}
	/**
	 * 设置所属部门。
	 * @return
	 * 所属部门。
	 */
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	
}