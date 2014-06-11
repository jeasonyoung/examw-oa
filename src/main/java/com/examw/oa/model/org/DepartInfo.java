package com.examw.oa.model.org;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;



/**
 * 部门信息。
 * @author yangyong.
 * @since 2014-05-17.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DepartInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pid,id,name,code;
	private int orderNo;
	private Date createTime;
	private List<DepartInfo> children;
	
	/**
	 * 获取子菜单集合。
	 * @return
	 * 	子菜单集合。
	 */
	public List<DepartInfo> getChildren() {
		return children;
	}
	
	/**
	 * 设置子部门集合。
	 * @return
	 * 	子部门集合。
	 */
	public void setChildren(List<DepartInfo> children) {
		this.children = children;
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
	 * 获取排序。
	 * @return
	 * 	排序。
	 */
	public int getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置排序。
	 * @return
	 * 	排序。
	 */
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取创建时间。
	 * @return
	 * 	创建时间。
	 */
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
}