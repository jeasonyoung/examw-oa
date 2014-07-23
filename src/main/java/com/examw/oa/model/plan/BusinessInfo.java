package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 业务系统设置信息。
 * @author lq.
 * @since 2014-06-23
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class BusinessInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,url,statusName;
	private Integer status;
	private Date createTime;
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
	public void setId(String id){
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
	 * 获取业务系统路径。
	 * @return 业务系统路径。
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置业务系统路径。
	 * @param url
	 * 业务系统路径。
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * 获取业务系统状态名称。
	 * @return 业务系统状态名称。
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * 设置业务系统状态名称。
	 * @param statusName
	 * 业务系统状态名称。
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * 获取业务系统创建时间。
	 * @return 业务系统创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置业务系统创建时间。
	 * @param createTime
	 * 业务系统创建时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}