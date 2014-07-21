package com.examw.oa.service.org.impl;
 
import java.util.Date;
import java.util.List; 
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.dao.org.IRankDao; 
import com.examw.oa.domain.org.Department;
import com.examw.oa.domain.org.Employee;  
import com.examw.oa.domain.org.Post;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.domain.security.User;
import com.examw.oa.model.org.EmployeeInfo;
import com.examw.oa.model.security.UserInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IEmployeeService;
import com.examw.oa.service.security.IUserService;
/**
 * 员工服务接口实现类。
 * @author lq.
 * @since 2014-06-16.
 */
public class EmployeeServiceImpl extends BaseDataServiceImpl<Employee,EmployeeInfo> implements IEmployeeService {
	private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	private IEmployeeDao employeeDao;
	private IDepartmentDao departmentDao;
	private IPostDao postDao;
	private IRankDao rankDao;
	private IUserService userService;
	private Map<Integer, String> gendersMap,statusMap;
	/**
	 * 设置员工数据接口。
	 * @param employeeDao
	 * 员工数据接口。
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		if(logger.isDebugEnabled())logger.debug("注入员工数据接口...");
		this.employeeDao = employeeDao;
	}
	/**
	 * 设置部门数据接口。
	 * @param departmentDao
	 * 部门数据接口。
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		if(logger.isDebugEnabled()) logger.debug("注入部门数据接口...");
		this.departmentDao = departmentDao;
	}
	/**
	 * 设置岗位数据接口。
	 * @param postDao
	 */
	public void setPostDao(IPostDao postDao) {
		if(logger.isDebugEnabled()) logger.debug("注入岗位数据接口...");
		this.postDao = postDao;
	}
	/**
	 * 设置员工级别接口。
	 * @param rankDao
	 * 员工级别接口。
	 */
	public void setRankDao(IRankDao rankDao) {
		if(logger.isDebugEnabled()) logger.debug("注入员工级别数据接口...");
		this.rankDao = rankDao;
	}
	/**
	 * 设置用户服务接口。
	 * @param userService
	 * 用户服务接口。
	 */
	public void setUserService(IUserService userService) {
		if(logger.isDebugEnabled()) logger.debug("注入用户服务接口...");
		this.userService = userService;
	}
	/**
	 * 设置性别集合。
	 * @param gendersMap
	 * 性别集合。
	 */
	public void setGendersMap(Map<Integer, String> gendersMap) {
		if(logger.isDebugEnabled()) logger.debug("注入性别集合...");
		this.gendersMap = gendersMap;
	}
	/**
	 * 设置状态集合。
	 * @param statusMap
	 * 状态集合。
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		if(logger.isDebugEnabled()) logger.debug("注入状态集合...");
		this.statusMap = statusMap;
	}
	/*
	 * 加载性别名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadGenderName(java.lang.Integer)
	 */
	@Override
	public String loadGenderName(Integer gender) {
		if(logger.isDebugEnabled()) logger.debug("加载性别［"+gender+"］名称...");
		if(this.gendersMap == null || gender == null) return null;
		return this.gendersMap.get(gender);
	}
	/*
	 * 加载状态名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(logger.isDebugEnabled()) logger.debug("加载状态［"+status+"］名称...");
		if(this.statusMap == null || status == null) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Employee> find(EmployeeInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.employeeDao.findEmployees(info);
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(EmployeeInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.employeeDao.total(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected EmployeeInfo changeModel(Employee data) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		if(data == null) return null;
		EmployeeInfo info = new EmployeeInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getDepartment() != null){
			info.setDeptId(data.getDepartment().getId());
			info.setDeptName(data.getDepartment().getName());
		}
		if(data.getPost() != null){
			info.setPostId(data.getPost().getId());
			info.setPostName(data.getPost().getName());
		}
		if(data.getRank() != null){
			info.setRankId(data.getRank().getId());
			info.setRankName(data.getRank().getName());
		}
		info.setGenderName(this.loadGenderName(info.getGender()));
		info.setStatusName(this.loadStatusName(info.getStatus()));
		info.setRoleId(this.userService.loadRoles(info.getId()));
		return info;
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public EmployeeInfo update(EmployeeInfo info) {
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		if(info == null) return null;
		Boolean isAdded = false;
		Employee data = StringUtils.isEmpty(info.getId()) ? null : this.employeeDao.load(Employee.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new Employee();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getDeptId()) && (data.getDepartment() == null || !data.getDepartment().getId().equalsIgnoreCase(info.getDeptId()))){
			Department d = this.departmentDao.load(Department.class, info.getDeptId());
			if(d != null) data.setDepartment(d);
		}
		if(!StringUtils.isEmpty(info.getPostId()) && (data.getPost() == null || !data.getPost().getId().equalsIgnoreCase(info.getPostId()))){
			Post p = this.postDao.load(Post.class, info.getPostId());
			if(p != null) data.setPost(p);
		}
		if(!StringUtils.isEmpty(info.getRankId()) && (data.getRank() == null || !data.getRank().getId().equalsIgnoreCase(info.getRankId()))){
			Rank r = this.rankDao.load(Rank.class, info.getRankId());
			if(r != null) data.setRank(r);
		}
		if(data.getDepartment() != null) info.setDeptName(data.getDepartment().getName());
		if(data.getPost() != null) info.setPostName(data.getPost().getName());
		if(data.getRank() != null) info.setRankName(data.getRank().getName());

		if(isAdded)this.employeeDao.save(data);
		if(info.getRoleId() != null && info.getRoleId().length > 0){
			UserInfo usr = new UserInfo();
			BeanUtils.copyProperties(info, usr, new String[]{"status"});
			usr.setAccount(info.getCode());
			usr.setNickName(info.getName());
			usr.setPassword(info.getPassword());
			usr.setStatus(info.getStatus() == Employee.STATUS_ON ? User.STATUS_ENABLED : User.STATUS_DISABLE);
			this.userService.update(usr);
		}
		
		info.setGenderName(this.loadGenderName(info.getGender()));
		info.setStatusName(this.loadStatusName(info.getStatus()));
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length;i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Employee e = this.employeeDao.load(Employee.class, ids[i]);
			if(e != null){
				if(logger.isDebugEnabled())logger.debug("删除员工［"+ids[i]+"］");
				this.employeeDao.delete(e);
			}
		}
	}
}