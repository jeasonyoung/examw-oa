package com.examw.oa.model.plan;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 计划总结明细
 * @author lq
 * @since 2014-07-01
 */
public class DetailInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,content;
	private Integer type;
	private Date createTime;
	/**
	 * 获取计划总结明细ID。
	 * @return 计划总结明细ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置计划总结明细ID。
	 * @param id
	 * 计划总结明细ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取计划总结明细内容。
	 * @return 计划总结明细内容。
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置计划总结明细内容。
	 * @param content
	 * 计划总结明细内容。
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取计划总结明细类型。
	 * @return 计划总结明细类型。
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置计划总结明细类型。
	 * @param type
	 * 计划总结明细类型。
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取计划总结明细创建时间。
	 * @return 计划总结明细创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置计划总结明细创建时间。
	 * @param createTime
	 * 计划总结明细创建时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}