package com.examw.oa.domain.org;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工信息。
 * @author lq.
 * @since 2014-06-16.
 */
public class Empl implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,code,phone,email,idCard,password;
	private int gender,status;
	private Date brithday,joinTime,createTime;
	private Depart depart;
	private Post post;
	private Rank rank;
	/**
	 * 获取员工信息ID。
	 * @return
	 * 员工信息ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置员工信息ID。
	 * @return
	 * 员工信息ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取员工名称。
	 * @return
	 * 员工名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置员工名称。
	 * @return
	 * 员工名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取员工编号。
	 * @return
	 * 员工编号。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置员工编号。
	 * @return
	 * 员工编号。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取员工手机号码。
	 * @return
	 * 手机号码。
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置员工手机号码。
	 * @return
	 * 手机号码。
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取员工邮箱地址。
	 * @return
	 * 邮箱地址。
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置员工邮箱地址。
	 * @return
	 * 邮箱地址。
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取员工身份证号码。
	 * @return
	 * 身份证号码。
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置员工邮箱地址。
	 * @return
	 * 邮箱地址。
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
	 * 获取员工性别。
	 * @return
	 * 性别。
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * 设置员工性别。
	 * @return
	 * 性别。
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * 获取员工状态。
	 * @return
	 * 状态。
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * 设置员工状态。
	 * @return
	 * 状态。
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 获取员工出生。
	 * @return
	 * 出生。
	 */
	public Date getBrithday() {
		return brithday;
	}
	/**
	 * 设置员工出生。
	 * @return
	 * 出生。
	 */
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	/**
	 * 获取员工入职时间。
	 * @return
	 * 入职时间。
	 */
	public Date getJoinTime() {
		return joinTime;
	}
	/**
	 * 设置员工入职时间。
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
	 * 获取所属部门。
	 * @return
	 * 所属部门。
	 */
	public Depart getDepart() {
		return depart;
	}
	/**
	 * 设置所属部门。
	 * @return
	 * 所属部门。
	 */
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	/**
	 * 获取所属岗位。
	 * @return
	 * 所属岗位。
	 */
	public Post getPost() {
		return post;
	}
	/**
	 * 设置所属岗位。
	 * @return
	 * 所属岗位。
	 */
	public void setPost(Post post) {
		this.post = post;
	}
	/**
	 * 获取所属等级。
	 * @return
	 * 所属等级。
	 */
	public Rank getRank() {
		return rank;
	}
	/**
	 * 设置所属等级。
	 * @return
	 * 所属等级。
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}
}
