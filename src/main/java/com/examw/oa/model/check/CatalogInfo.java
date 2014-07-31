package com.examw.oa.model.check;

import com.examw.model.Paging;
/**
 * 奖惩类别设置信息
 * @author lq.
 * @since 2014-07-30.
 */
public class CatalogInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,code,name,description; 
	/**
	 * 获取奖惩类别设置ID。
	 * @return 奖惩类别设置ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置奖惩类别设置ID。
	 * @param id
	 * 奖惩类别设置ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取奖惩类别设置代码。
	 * @return 奖惩类别设置代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置奖惩类别设置代码。
	 * @param code
	 * 奖惩类别设置代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取奖惩类别设置名称。
	 * @return 奖惩类别设置名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置奖惩类别设置名称。
	 * @param name 
	 * 奖惩类别设置名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取奖惩类别设置描述。
	 * @return 奖惩类别设置描述。
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置奖惩类别设置描述。
	 * @param description
	 * 奖惩类别设置描述。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}