package com.examw.oa.domain.org;

import java.io.Serializable;
import java.util.Date;
/**
 * 员工。
 * @author lq.
 * @since 2014-06-16.
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,code,phone,email,idCard,password;
	private int gender,status;
	private Date brithday,joinTime,createTime;
	private Department department;
	private Post post;
	private Rank rank;
	/**
	 * 性别－男性。
	 */
	public static final Integer GENDER_MALE = 1;
	/**
	 * 性别－女性。
	 */
	public static final Integer GENDER_FEMALE = 2;
	/**
	 * 状态－离职。
	 */
	public static final Integer STATUS_OUT = 0;
	/**
	 * 状态-在职。
	 */
	public static final Integer STATUS_ON = 1;
	/**
	 * 状态－限制登录。
	 */
	public static final Integer STATUS_LIMIT = -1;
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
	 * 获取员工姓名。
	 * @return 员工姓名。
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
	 * 获取员工编号。
	 * @return  员工编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置员工编号。
	 * @param code
	 * 员工编号。
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return 邮箱地址。
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
	 * 设置身份证号码。
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
	 * 设置初始密码。
	 * @param password
	 * 初始密码。
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取性别。
	 * @return 性别。
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * 设置性别。
	 * @param gender
	 * 性别。
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * 获取状态。
	 * @return 状态。
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * 设置状态。
	 * @param status
	 * 状态。
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 获取出生日期。
	 * @return 出生日期。
	 */
	public Date getBrithday() {
		return brithday;
	}
	/**
	 * 设置出生日期。
	 * @param brithday
	 * 出生日期。
	 */
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	/**
	 * 获取入职时间。
	 * @return 入职时间。
	 */
	public Date getJoinTime() {
		return joinTime;
	}
	/**
	 * 设置入职时间。
	 * @param joinTime
	 * 入职时间。
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
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
	 * 获取所属部门。
	 * @return 所属部门。
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 设置所属部门。
	 * @param department
	 * 所属部门。
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * 获取所属岗位。
	 * @return 所属岗位。
	 */
	public Post getPost() {
		return post;
	}
	/**
	 * 设置所属岗位。
	 * @param post
	 * 所属岗位。
	 */
	public void setPost(Post post) {
		this.post = post;
	}
	/**
	 * 获取所属等级。
	 * @return 所属等级。
	 */
	public Rank getRank() {
		return rank;
	}
	/**
	 * 设置所属等级。
	 * @param rank
	 * 所属等级。
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}
}