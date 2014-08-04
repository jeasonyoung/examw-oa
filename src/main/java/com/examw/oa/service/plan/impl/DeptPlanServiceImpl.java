package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.check.IEntryDao;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.dao.plan.IDeptPlanDao;
import com.examw.oa.dao.plan.IDeptPlanMenberDao;
import com.examw.oa.domain.check.Entry;
import com.examw.oa.domain.org.Department;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.domain.plan.DeptPlan;
import com.examw.oa.domain.plan.DeptPlanMember;
import com.examw.oa.model.plan.DeptPlanInfo;
import com.examw.oa.model.plan.DeptPlanMemberInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IDeptPlanService;
/**
 * 部门计划服务接口实现。
 * @author lq.
 * @since 2014-08-01.
 */
public class DeptPlanServiceImpl extends BaseDataServiceImpl<DeptPlan, DeptPlanInfo> implements IDeptPlanService {
	private static final Logger logger = Logger.getLogger(DeptPlanServiceImpl.class);
	private IDeptPlanDao deptPlanDao;
	private IDepartmentDao departmentDao;
	private IDeptPlanMenberDao deptPlanMenberDao;
	private IEntryDao entryDao;
	private IEmployeeDao employeeDao;
	private Map<Integer,String> typeMap,statusMap;
	/**
	 * 设置部门计划数据接口。
	 * @param deptPlanDao
	 * 部门计划数据接口。
	 */
	public void setDeptPlanDao(IDeptPlanDao deptPlanDao){
		if(logger.isDebugEnabled())logger.debug("注入部门计划数据接口...");
		this.deptPlanDao = deptPlanDao;
	}
	/**
	 * 设置员工信息数据接口。
	 * @param employeeDao
	 * 员工信息数据接口。
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	/**
	 * 设置部门计划成员数据接口。
	 * @param deptPlanMenberDao
	 * 部门计划成员数据接口。
	 */
	public void setDeptPlanMenberDao(IDeptPlanMenberDao deptPlanMenberDao) {
		this.deptPlanMenberDao = deptPlanMenberDao;
	}
	/**
	 * 设置奖惩条目数据接口。
	 * @param entryDao
	 * 奖惩条目数据接口。
	 */
	public void setEntryDao(IEntryDao entryDao) {
		this.entryDao = entryDao;
	}
	/**
	 * 设置部门计划数据接口。
	 * @param departmentDao
	 * 部门计划数据接口。
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	/**
	 * 设置部门计划类型集合。
	 * @param tpyeMap
	 * 部门计划类型集合。
	 */
	public void setTypeMap(Map<Integer, String> typeMap){
		if(logger.isDebugEnabled()) logger.debug("注入部门计划类型集合...");
		this.typeMap = typeMap;
	}
	/**
	 * 设置部门计划状态集合。
	 * @param statuMap
	 * 部门计划状态集合。
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		if(logger.isDebugEnabled())logger.debug("注入部门计划状态集合...");
		this.statusMap = statusMap;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<DeptPlan> find(DeptPlanInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		return this.deptPlanDao.findDeptPlans(info);
	}
	/*
	 * 统计数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(DeptPlanInfo info) {
		if(logger.isDebugEnabled())logger.debug("统计数据...");
		return this.deptPlanDao.total(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected DeptPlanInfo changeModel(DeptPlan data) {
		if(logger.isDebugEnabled()) logger.debug("开始类型转换...");
		DeptPlanInfo info = new DeptPlanInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getDepartment() != null){
			info.setDeptId(data.getDepartment().getId());
			info.setDeptName(data.getDepartment().getName());
		}
		info.setTypeName(this.loadTypeName(info.getType()));
		info.setStatusName(this.loadStatusName(info.getStatus()));
		return info;
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public DeptPlanInfo update(DeptPlanInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		if(info == null) return null;
		Boolean isAdded = false;
		DeptPlan  data = StringUtils.isEmpty(info.getId()) ? null : this.deptPlanDao.load(DeptPlan.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new DeptPlan();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getDeptId()) && (data.getDepartment() == null || !data.getDepartment().getId().equalsIgnoreCase(info.getDeptId()))){
			Department d = this.departmentDao.load(Department.class, info.getDeptId());
			if(d != null) data.setDepartment(d);
		}
		if(data.getDepartment() != null) info.setDeptName(data.getDepartment().getName());
		info.setTypeName(this.loadTypeName(data.getType()));
		if(isAdded)this.deptPlanDao.save(data);
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
			DeptPlan data = this.deptPlanDao.load(DeptPlan.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i]+"］");
				this.deptPlanDao.delete(data);
			}
		}
	}
	/*
	 * 加载状态。
	 * @see com.examw.oa.service.plan.IDeptPlanService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(logger.isDebugEnabled()) logger.debug("加载部门计划状态［"+ status +"］名称...");
		if(status == null  || this.statusMap == null || this.statusMap.size() == 0) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 加载类型。
	 * @see com.examw.oa.service.plan.IDeptPlanService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载部门计划类型［"+ type +"］名称...");
		if(type == null  || this.typeMap == null || this.typeMap.size() == 0) return null;
		return this.typeMap.get(type);
	}
	/*
	 * 更新计划成员。
	 * @see com.examw.oa.service.plan.IDeptPlanService#updateDeptPlanMember(java.lang.String, com.examw.oa.model.plan.DeptPlanMemberInfo)
	 */
	@Override
	public DeptPlanMemberInfo updateDeptPlanMember(String deptPlanId, DeptPlanMemberInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新计划成员数据...");
		if(info == null) return null;
		Boolean isAdded = false;
		DeptPlanMember  data = StringUtils.isEmpty(info.getId()) ? null : this.deptPlanMenberDao.load(DeptPlanMember.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new DeptPlanMember();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getEntryId()) && (data.getEntry() == null || !data.getEntry().getId().equalsIgnoreCase(info.getEntryId()))){
			Entry e = this.entryDao.load(Entry.class, info.getEntryId());
			if(e != null) data.setEntry(e);
		}
		if(!StringUtils.isEmpty(info.getEmplId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEntryId()))){
			Employee e = this.employeeDao.load(Employee.class, info.getEmplId());
			if(e != null) data.setEmployee(e);
		}
		if(data.getEntry() != null) info.setEntryName(data.getEntry().getName());
		if(data.getEmployee() != null) info.setEmplName(data.getEmployee().getName());
		DeptPlan plan=this.deptPlanDao.load(DeptPlan.class, deptPlanId);
		if(plan !=null){
			info.setDeptPlanId(deptPlanId);
		}
		if(isAdded)this.deptPlanMenberDao.save(data);
		return info;
	}
}