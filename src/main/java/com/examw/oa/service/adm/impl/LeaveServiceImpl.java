package com.examw.oa.service.adm.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveApprovalDao;
import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.domain.org.Department;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 我要请假服务接口实现。
 * @author lq.
 * @since 2014-07-17.
 */
public class LeaveServiceImpl extends BaseDataServiceImpl<Leave, LeaveInfo> implements ILeaveService {
	private static Logger logger = Logger.getLogger(LeaveServiceImpl.class);
	private ILeaveDao leaveDao;
	private IEmployeeDao employeeDao;
	private IDepartmentDao departmentDao;
	private ILeaveApprovalDao approvalDao;
	private Map<Integer, String> typeMap;
	private Map<Integer, String> statusMap;
	/**
	 * 请假数据接口
	 * @param leaveDao
	 */
	public void setLeaveDao(ILeaveDao leaveDao) {
		if(logger.isDebugEnabled())logger.debug("注入请假数据接口...");
		this.leaveDao = leaveDao;
	}
	/**
	 * 员工数据接口
	 * @param employeeDao
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		if(logger.isDebugEnabled())logger.debug("注入员工信息数据接口...");
		this.employeeDao = employeeDao;
	}
	/**
	 * 部门数据接口
	 * @param departmentDao
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao){
		if(logger.isDebugEnabled())logger.debug("注入部门信息数据接口...");
		this.departmentDao = departmentDao;
	}
	/**
	 * 请假审核数据接口
	 * @param approvalDao
	 */
	public void setApprovalDao(ILeaveApprovalDao approvalDao) {
		if(logger.isDebugEnabled())logger.debug("注入请假审核数据接口...");
		this.approvalDao = approvalDao;
	}
	/**
	 * 请假状态集合
	 * @param statusMap
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		if(logger.isDebugEnabled())logger.debug("状态集合...");
		this.statusMap = statusMap;
	}
	/**
	 * 请假类型集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		if(logger.isDebugEnabled())logger.debug("类型集合...");
		this.typeMap = typeMap;
	}
	/*
	 * 加载类型名称
	 * @see com.examw.oa.service.adm.ILeaveService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载类型［"+type+"］名称...");
		if(this.typeMap == null|| type==null) return null;
		return this.typeMap.get(type);
	}
	/*
	 * 加载状态名称
	 * @see com.examw.oa.service.adm.ILeaveService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(logger.isDebugEnabled()) logger.debug("加载状态［"+status+"］名称...");
		if(this.statusMap == null|| status==null) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Leave> find(LeaveInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据查询...");
		return this.leaveDao.findLeaves(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected LeaveInfo changeModel(Leave data) {
		if(logger.isDebugEnabled())logger.debug("类型转换...");
		if(data == null) return null;
		LeaveInfo info = new LeaveInfo();
		BeanUtils.copyProperties(data, info, new String[] {"approvals"});
		
		if(data.getEmployee() != null){
			info.setEmployeeId(data.getEmployee().getId());
			info.setEmployeeName(data.getEmployee().getName());
		}
		if(data.getShiftEmployee() != null){
			info.setShiftEmployeeId(data.getShiftEmployee().getId());
			info.setShiftEmployeeName(data.getShiftEmployee().getName());
		}
		if(data.getDepartment() !=null){
			info.setShiftDepartmentId(data.getDepartment().getId());
		}
		//请假审批类型转换
		if(data.getApprovals() !=null){
			for(LeaveApproval app:data.getApprovals()){
				if(app == null) continue;
					info.setApprovalId(app.getId());
					info.setApprovalLeaveId(app.getLeave().getId());
					info.setApprovalEmplId("3fb96023-292a-4051-844f-622d5e4a70c9");
					info.setApproval(app.getApproval());
					info.setApprovalType(app.getType());
					info.setApprovalStatus(app.getStatus());
					continue;
			}
		}
		return info;
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(LeaveInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据统计...");
		return this.leaveDao.total(info);
	}
	/*
	 * 请假审批函数
	 */
	private LeaveApproval buildApproval(String id,String approval,Integer status,Integer type){
		LeaveApproval data = StringUtils.isEmpty(id) ? null : this.approvalDao.load(LeaveApproval.class, id);
		if(data == null){
			if(StringUtils.isEmpty(approval)) return null;
			if(StringUtils.isEmpty(id)) id = UUID.randomUUID().toString();
			data = new LeaveApproval();
			data.setId(id);
			data.setCreateTime(new Date());
			data.setType(type);
			data.setStatus(status);
			data.setApproval(approval);
		}
		return data;
	}
	/*
	 * 更新数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public LeaveInfo update(LeaveInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		if(info == null) return null;
		Boolean isAdded = false;
		Leave data = StringUtils.isEmpty(info.getId()) ? null : this.leaveDao.load(Leave.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new Leave();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		//判断是否审批通过 同意--批准
		if(info.getApprovalStatus()==LeaveApproval.STATUS_AGREE){
			info.setStatus(Leave.STATUS_PASS);
		}
		//不同意--未批准
		if(info.getApprovalStatus() == LeaveApproval.STATUS_DISAGREE){
			info.setStatus(Leave.STATUS_NOPASS);
		}
		
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEmployeeId()))){
			Employee e = this.employeeDao.load(Employee.class, info.getEmployeeId());
			if(e != null) data.setEmployee(e);	
		}
		if(!StringUtils.isEmpty(info.getShiftEmployeeId()) && (data.getShiftEmployee() == null ||!data.getShiftEmployee().getId().equalsIgnoreCase(info.getShiftEmployeeId()))){
			Employee s = this.employeeDao.load(Employee.class, info.getShiftEmployeeId());
			if(s != null) data.setShiftEmployee(s);
		}
		if(!StringUtils.isEmpty(info.getShiftDepartmentId()) && (data.getDepartment() == null || !data.getDepartment().getId().equalsIgnoreCase(info.getShiftDepartmentId()))){
			Department d = this.departmentDao.load(Department.class, info.getShiftDepartmentId());
			if(d != null) data.setDepartment(d);
		}
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
		}
		if(data.getShiftEmployee() != null){
			info.setShiftEmployeeName(data.getShiftEmployee().getName());
		}
		//添加请假审批数据
		LeaveApproval app=this.buildApproval(info.getApprovalId(), info.getApproval(), info.getApprovalType(), info.getApprovalStatus());
		Set<LeaveApproval> approvale = new HashSet<>();
		if(app != null){
			info.setApprovalId(app.getId());
			info.setApprovalType(app.getType());
			info.setApprovalStatus(app.getStatus());
			info.setApproval(app.getApproval());
			//获取当前请假人ID
			info.setApprovalLeaveId(data.getId());
			//审批人ID
			info.setApprovalEmplId(info.getApprovalEmplId());
			approvale.add(app);
		}
		data.setApprovals(approvale);
		
		if(isAdded)this.leaveDao.save(data);
		return info;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
	}
}