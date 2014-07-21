package com.examw.oa.model.org;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

import com.examw.model.Paging;
import com.examw.oa.support.CustomDateSerializer;
import com.examw.oa.support.CustomShortDateSerializer;
/**
 * 员工信息。
 * @author lq.
 * @since 2014-06-16
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class EmployeeInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,code,name,phone,email,idCard,password,deptId,deptName,postId,postName,rankId,rankName,genderName,statusName;
	private Integer gender,status;
	private Date brithday, joinTime,createTime;
	private String[] roleId;
	/**
	 * 获取员工ID。
	 * @return 员工ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工ID。
	 * @param id 
	 * 员工ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取员工工号。
	 * @return 员工工号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置员工工号。
	 * @param code
	 * 员工工号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取员工姓名。
	 * @return  员工姓名。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置员工姓名。
	 * @param name
	 * 员工姓名。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取手机号码。
	 * @return 手机号码。
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置手机号码。
	 * @param phone
	 * 手机号码。
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取邮箱地址。
	 * @return  邮箱地址。
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置邮箱地址。
	 * @param email
	 * 邮箱地址。
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取身份证号码。
	 * @return 身份证号码。
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 *  设置身份证号码。
	 * @param idCard
	 * 身份证号码。
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取初始密码。
	 * @return 初始密码。
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置员工密码。
	 * @param password
	 * 初始密码。
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取性别。
	 * @return  性别。
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置性别。
	 * @param gender
	 * 性别。
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取性别名称。
	 * @return 性别名称。
	 */
	public String getGenderName() {
		return genderName;
	}
	/**
	 * 设置性别名称。
	 * @param genderName
	 * 性别名称。
	 */
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	/**
	 * 获取状态。
	 * @return 状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置状态。
	 * @param status
	 * 状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取状态名称。
	 * @return 状态名称。
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * 设置状态名称。
	 * @param statusName
	 * 状态名称。
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * 获取出生日期。
	 * @return 出生日期。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getBrithday() {
		return brithday;
	}
	/**
	 * 设置出生日期。
	 * @param brithday
	 * 出生日期。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	/**
	 * 获取入职时间。
	 * @return 入职时间。
	 */
	@JsonSerialize(using = CustomShortDateSerializer.class)
	public Date getJoinTime() {
		return joinTime;
	}
	/**
	 * 设置入职时间。
	 * @param joinTime
	 * 入职时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	/**
	 * 获取创建时间。
	 * @return 创建时间。
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
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
	 * 获取所属部门ID。
	 * @return 所属部门ID。
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置所属部门ID。
	 * @param deptId
	 * 所属部门ID。
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取所属部门名称。
	 * @return 所属部门名称。
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置所属部门名称。
	 * @param deptName
	 * 所属部门名称。
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取所属岗位ID。
	 * @return 所属岗位ID。
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
	 * @return  所属岗位名称。
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
	 * @return  所属等级ID。
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
	 * @return 所属等级名称。
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
	/**
	 *获取用户角色集合。
	 * @return 用户角色集合。
	 */
	public String[] getRoleId() {
		return roleId;
	}
	/**
	 * 设置用户角色集合。
	 * @param roleId
	 * 用户角色集合。
	 */
	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}	
}