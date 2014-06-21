package com.examw.oa.model.org;


import java.util.Date;
import java.util.List;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * 部门信息。
 * @author lq.
 * @since 2014-06-11.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DepartmentInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String pid,id,name,code,fullName;
	private Integer orderNo;
	private Date createTime;
	private List<DepartmentInfo> children;
	/**
	 * 获取上级部门ID。
	 * @return
	 * 上级部门ID。
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置上级部门ID。
	 * @return
	 * 上级部门ID。
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取部门id。
	 * @return
	 * 	部门id。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置部门id。
	 * @return
	 * 	部门id。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取部门名称。
	 * @return
	 * 	部门名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置部门名称。
	 * @return
	 * 	部门名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取部门编号。
	 * @return
	 * 	部门编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置部门编号。
	 * @return
	 * 	设置编号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取部门全称。
	 * @return
	 * 	部门全称。
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * 设置部门全称。
	 * @return
	 * 	部门全称。
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * 获取排序。
	 * @return
	 * 	排序。
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置排序。
	 * @return
	 * 	排序。
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取创建时间。
	 * @return
	 * 	创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @return
	 * 	创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取子菜单集合。
	 * @return
	 * 	子菜单集合。
	 */
	public List<DepartmentInfo> getChildren() {
		return children;
	}
	/**
	 * 设置子部门集合。
	 * @return
	 * 	子部门集合。
	 */
	public void setChildren(List<DepartmentInfo> children) {
		this.children = children;
	}
}