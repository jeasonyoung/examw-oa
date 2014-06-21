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
public class EmplInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,code,phone,email,idCard,password,departId,departName,postId,postName,rankId,rankName;
	private int gender,status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date brithday;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joinTime;
	private Date createTime;
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
	@JsonSerialize(using = CustomDateSerializer.class)
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
	@JsonSerialize(using = CustomDateSerializer.class)
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
	 * 获取所属部门ID。
	 * @param departId
	 * 所属部门ID。
	 */
	public String getDepartId() {
		return departId;
	}
	/**
	 * 设置所属部门ID。
	 * @param departId
	 * 所属部门ID。
	 */
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	/**
	 * 获取所属部门名称。
	 * @param departName
	 * 所属部门名称。
	 */
	public String getDepartName() {
		return departName;
	}
	/**
	 * 设置所属部门名称。
	 * @param departName
	 * 所属部门名称。
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
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