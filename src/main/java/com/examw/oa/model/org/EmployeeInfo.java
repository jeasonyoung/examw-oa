package com.examw.oa.model.org;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
/**
 * 员工信息。
 * @author lq.
 * @since 2014-06-16
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class EmployeeInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,code,name,phone,email,idCard,password,departmentId,departmentName,postId,postName,rankId,rankName;
	private Integer gender,status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date brithday, joinTime,createTime;
	/**
	 * 获取员工ID。
	 * @return
	 * 员工ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工ID。
	 * @return
	 * 员工ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取工号。
	 * @return
	 *  工号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置工号。
	 * @return
	 * 工号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取姓名。
	 * @return
	 * 姓名。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置姓名。
	 * @return
	 * 姓名。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取手机号码。
	 * @return
	 * 手机号码。
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置手机号码。
	 * @return
	 * 手机号码。
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取邮箱地址。
	 * @return
	 * 邮箱地址。
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置邮箱地址。
	 * @return
	 * 邮箱地址。
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取身份证号码。
	 * @return
	 * 身份证号码。
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置身份证号码。
	 * @return
	 * 身份证号码。
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取员工密码。
	 * @return
	 * 密码。
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置员工密码。
	 * @return
	 * 密码。
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取性别。
	 * @return
	 * 性别。
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置性别。
	 * @return
	 * 性别。
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取状态。
	 * @return
	 * 状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置状态。
	 * @return
	 * 状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取出生日期。
	 * @return
	 * 出生日期。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBrithday() {
		return brithday;
	}
	/**
	 * 设置出生日期。
	 * @return
	 * 出生日期。
	 */
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	/**
	 * 获取入职时间。
	 * @return
	 * 入职时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getJoinTime() {
		return joinTime;
	}
	/**
	 * 设置入职时间。
	 * @return
	 * 入职时间。
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	/**
	 * 获取创建时间。
	 * @return
	 * 创建时间。
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @return
	 * 创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取所属部门ID。
	 * @param departmentId
	 * 所属部门ID。
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置所属部门ID。
	 * @param departmentId
	 * 所属部门ID。
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取所属部门名称。
	 * @param departmentName
	 * 所属部门名称。
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置所属部门名称。
	 * @param departmentName
	 * 所属部门名称。
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * 获取所属岗位ID。
	 * @param postId
	 * 所属岗位ID。
	 */
	public String getPostId() {
		return postId;
	}
	/**
	 * 设置所属岗位ID。
	 * @param postId
	 * 所属岗位ID。
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}
	/**
	 * 获取所属岗位名称。
	 * @param postName
	 * 所属岗位名称。
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置所属岗位名称。
	 * @param postName
	 * 所属岗位名称。
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取所属等级ID。
	 * @param rankId
	 * 所属等级ID。
	 */
	public String getRankId() {
		return rankId;
	}
	/**
	 * 设置所属等级ID。
	 * @param rankId
	 * 所属等级ID。
	 */
	public void setRankId(String rankId) {
		this.rankId = rankId;
	}
	/**
	 * 获取所属等级名称。
	 * @param rankName
	 * 所属等级名称。
	 */
	public String getRankName() {
		return rankName;
	}
	/**
	 * 设置所属等级名称。
	 * @param rankName
	 * 所属等级名称。
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}	
}