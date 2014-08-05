package com.examw.oa.service.plan.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.check.IEntryDao;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.dao.plan.IDeptPlanDao;
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
		BeanUtils.copyProperties(data,info,new String[]{"members"});
		if(data.getDepartment() != null){
			info.setDeptId(data.getDepartment().getId());
			info.setDeptName(data.getDepartment().getName());
		}
		info.setTypeName(this.loadTypeName(info.getType()));
		info.setStatusName(this.loadStatusName(info.getStatus()));
		if(data.getMembers() != null && data.getMembers().size() > 0){
			Set<DeptPlanMemberInfo> members = new TreeSet<DeptPlanMemberInfo>(new Comparator<DeptPlanMemberInfo>(){
				@Override
				public int compare(DeptPlanMemberInfo o1, DeptPlanMemberInfo o2) {
					return (int)(o1.getStartTime().getTime() - o2.getStartTime().getTime());
				}
			});
			for(DeptPlanMember member : data.getMembers()){
				DeptPlanMemberInfo m = this.changeModel(member);
				if(m != null)members.add(m);
			}
			info.setMembers(members);
		}
		return info;
	}
	/*
	 * 部门计划类型转换。
	 */
	private DeptPlanMemberInfo changeModel(DeptPlanMember member){
		if(member == null)return null;
		DeptPlanMemberInfo info = new DeptPlanMemberInfo();
		BeanUtils.copyProperties(member, info);
		if(member.getEmployee() != null){
			info.setEmpId(member.getEmployee().getId());
			info.setEmpName(member.getEmployee().getName());
		}
		if(member.getEntry() != null){
			info.setEntryId(member.getEntry().getId());
			info.setEntryName(member.getEntry().getName());
		}
		if(member.getPlan() != null){
			info.setPlanId(member.getPlan().getId());
		}
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
			if(StringUtils.isEmpty(info.getId()))info.setId(UUID.randomUUID().toString());
			info.setCreateTime(new Date()); 
			data = new DeptPlan();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		info.setLastTime(new Date());
		info.setStatus(DeptPlan.STATUS_NONE);
		BeanUtils.copyProperties(info, data,new String[]{"members"});
		if(!StringUtils.isEmpty(info.getDeptId()) && (data.getDepartment() == null || !data.getDepartment().getId().equalsIgnoreCase(info.getDeptId()))){
			Department d = this.departmentDao.load(Department.class, info.getDeptId());
			if(d != null) data.setDepartment(d);
		}
		if(data.getDepartment() != null) info.setDeptName(data.getDepartment().getName());
		info.setTypeName(this.loadTypeName(data.getType()));
		//members
		Set<DeptPlanMember> members = new HashSet<>();
		if(info.getMembers() != null && info.getMembers().size() > 0){
			for(DeptPlanMemberInfo dpi : info.getMembers()){
				DeptPlanMember dpm = new DeptPlanMember();
				dpm.setPlan(data);
				if(StringUtils.isEmpty(dpi.getId())){
					dpm.setId(UUID.randomUUID().toString());
				}
				if(dpi.getCreateTime() == null){
					dpm.setCreateTime(new Date());
				}
				if(!StringUtils.isEmpty(dpi.getEmpId())){
					Employee employee = this.employeeDao.load(Employee.class,dpi.getEmpId());
					dpm.setEmployee(employee);
				}
				if(!StringUtils.isEmpty(dpi.getEntryId())){
					Entry entry=this.entryDao.load(Entry.class,dpi.getEntryId());
					dpm.setEntry(entry);
				}
				BeanUtils.copyProperties(dpi, dpm,new String[]{"remarks"});
				members.add(dpm);
			}
		}
		data.setMembers(members);
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
				if(data.getStatus() != null && data.getStatus() != DeptPlan.STATUS_NONE){
					if(logger.isDebugEnabled()) logger.debug("不允许删除数据［"+ ids[i]+"］");
					continue;
				}
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
}