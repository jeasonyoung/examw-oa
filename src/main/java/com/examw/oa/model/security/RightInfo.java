package com.examw.oa.model.security;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.examw.model.IPaging;
import com.examw.oa.domain.security.Right;
/**
 * 基础权限信息。
 * @author yangyong.
 * @since 2014-05-03.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RightInfo extends Right implements IPaging {
	private static final long serialVersionUID = 1L;
	private Integer rows,page;
	private String  sort,order;
	
	/**
	 * 构造函数。
	 */
	public RightInfo(){}
	/**
	 * 构造函数。
	 * @param id
	 * 权限ID。
	 * @param name
	 * 权限名称。
	 * @param value
	 * 权限值。
	 */
	public RightInfo(String id, String name,int value){
		this.setId(id);
		this.setName(name);
		this.setValue(value);
		this.setOrderNo(value);
	}
	
	@Override
	public Integer getRows() {
		return this.rows;
	}

	@Override
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String getSort() {
		return this.sort;
	}

	@Override
	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String getOrder() {
		return this.order;
	}

	@Override
	public void setOrder(String order) {
		this.order = order;
	}

}