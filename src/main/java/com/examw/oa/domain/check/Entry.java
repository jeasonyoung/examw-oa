package com.examw.oa.domain.check;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 奖惩条目。
 * <p>
 * 为最基础考核点，负责具体的奖惩幅度控制；
 * </p>
 * <p>
 * code：由四位数字构成，前两位为所属类别代码，决定排序。
 * <br/>
 * money：为负数时表示罚钱，为正数时表示奖励；
 * </p>
 * @author yangyong.
 * @since 2014-07-30.
 */
public class Entry implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name,description;
	private Catalog catalog;
	private Integer type;
	private BigDecimal money;
	/**
	 * 奖惩类型－按次考核。
	 */
	public static final Integer TYPE_TIMES = 1;
	/**
	 * 奖惩类型－按天考核。
	 */
	public static final Integer TYPE_DAY = 2;
	/**
	 * 奖惩类型－按月考核。
	 */
	public static final Integer TYPE_MONTH = 3;
	/**
	 * 获取条目ID。
	 * @return 条目ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 *  设置条目ID。
	 * @param id
	 * 条目ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取条目代码。
	 * @return 条目代码。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置条目代码。
	 * @param code
	 * 条目代码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取条目名称。
	 * @return 条目名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置条目名称。
	 * @param name
	 * 条目名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取所属考核类别。
	 * @return 所属考核类别。
	 */
	public Catalog getCatalog() {
		return catalog;
	}
	/**
	 * 设置所属考核类别。
	 * @param catalog
	 * 所属考核类别。
	 */
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	/**
	 * 获取奖惩类型。
	 * @return 奖惩类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置奖惩类型。
	 * @param type
	 * 奖惩类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取奖惩金额。
	 * @return 奖惩金额。
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置奖惩金额。
	 * @param money
	 * 奖惩金额。
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
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
}