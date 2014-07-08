package com.examw.oa.domain.plan;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
/**
 * 业务系统。
 * @author yangyong.
 * @since 2014-06-23.
 */
public class Business implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,url;
	private Integer status;
	private Set<Detail> details;
	private Date createTime;
	/**
	 * 状态－停用。
	 */
	public static final Integer STATUS_STOP = 0;
	/**
	 * 状态－正常。
	 */
	public static final Integer STATUS_NORMAL = 1;
	/**
	 * 状态－测试。
	 */
	public static final Integer STATUS_TEST = 2;
	/**
	 * 获取业务系统ID。
	 * @return 业务系统ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置业务系统ID。
	 * @param id
	 * 业务系统ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取业务系统名称。
	 * @return 业务系统名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置业务系统名称。
	 * @param name
	 * 业务系统名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取业务系统状态。
	 * @return 业务系统状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置业务系统状态。
	 * @param status
	 * 业务系统状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取业务系统URL。
	 * @return 业务系统URL。
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置业务系统URL。
	 * @param url
	 * 业务系统URL。
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取创建时间。
	 * @return 创建时间。
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @param createTime
	 * 创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取所属明细集合。
	 * @return 所属明细集合。
	 */
	public Set<Detail> getDetails() {
		return details;
	}
	/**
	 * 设置所属明细集合。
	 * @param details
	 * 所属明细集合。
	 */
	public void setDetails(Set<Detail> details) {
		this.details = details;
	}
}