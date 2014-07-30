package com.examw.oa.domain.check;

import java.io.Serializable;
import java.util.Set;

/**
 * 奖惩类别。
 * <p>
 * 负责维护考核条目所属的类别，每个考核条目都必须有所属的类别；
 * </p>
 * <p>
 * code由两位数字构成，表示排序；
 * </p>
 * @author yangyong.
 * @since 2014-07-30.
 */
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name,description; 
	private Set<Entry> entries;
	/**
	 * 获取类别ID。
	 * @return 类别ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置类别ID。
	 * @param id
	 * 类别ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取类别代码。
	 * @return 类别代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置类别代码。
	 * @param code
	 * 类别代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取类别名称。
	 * @return 类别名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置类别名称。
	 * @param name
	 * 类别名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取描述。
	 * @return 描述。
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置描述。
	 * @param description
	 * 描述。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取奖惩条目集合。
	 * @return 奖惩条目集合。
	 */
	public Set<Entry> getEntries() {
		return entries;
	}
	/**
	 * 设置奖惩条目集合。
	 * @param entries
	 * 奖惩条目集合。
	 */
	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}
}