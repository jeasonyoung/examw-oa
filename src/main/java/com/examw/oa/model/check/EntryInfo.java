package com.examw.oa.model.check;

import java.math.BigDecimal;

import com.examw.model.Paging;
/**
 * 奖惩条目设置信息。
 * @author lq.
 * @since 2014-07-30.
 */
public class EntryInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,code,name,description,catalogId,catalogName,typeName;
	private Integer type;
	private BigDecimal money;
	/**
	 * 获取奖惩条目设置ID。
	 * @return 奖惩条目设置ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置奖惩条目设置ID。
	 * @param id
	 * 奖惩条目设置ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取奖惩条目代码。
	 * @return 奖惩条目设置代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置奖惩条目设置代码。
	 * @param code
	 * 奖惩条目设置代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取奖惩条目设置名称。
	 * @return 奖惩条目设置名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置奖惩条目设置名称。
	 * @param name
	 * 奖惩条目设置名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取奖惩条目设置描述。
	 * @return 奖惩条目设置描述。
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置奖惩条目设置描述。
	 * @param description
	 * 奖惩条目设置描述。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取奖惩条目设置类型。
	 * @return 奖惩条目设置类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置奖惩条目设置类型。
	 * @param type 
	 * 奖惩条目设置类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取奖惩条目设置金额（正数为奖励 负数为惩罚）
	 * @return 奖惩条目设置金额。
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置奖惩条目设置金额（正数为奖励 负数为惩罚）
	 * @param money
	 * 奖惩条目设置金额。
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取奖惩类别设置ID.
	 * @return 奖惩类别设置ID.
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * 设置奖惩类别设置ID.
	 * @param catalogId
	 * 奖惩类别设置ID.
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * 获取奖惩类别设置名称。
	 * @return 奖惩类别设置名称。
	 */
	public String getCatalogName() {
		return catalogName;
	}
	/**
	 * 设置奖惩类别设置名称。
	 * @param catalogName
	 * 奖惩类别设置名称。
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	/**
	 * 获取奖惩条目设置类型名称。
	 * @return 奖惩条目设置类型名称。
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置奖惩条目设置类型名称。
	 * @param typeName
	 * 奖惩条目设置类型名称。
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}