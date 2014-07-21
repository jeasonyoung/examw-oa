package com.examw.oa.service.adm.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
 * 请假服务
 * @author lq.
 * @since 2014-07-17.
 */
public class LeaveServiceImpl extends BaseDataServiceImpl<Leave, LeaveInfo> implements ILeaveService {
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
		this.leaveDao = leaveDao;
	}
	/**
	 * 员工数据接口
	 * @param employeeDao
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	/**
	 * 部门数据接口
	 * @param departmentDao
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	/**
	 * 请假审核数据接口
	 * @param approvalDao
	 */
	public void setApprovalDao(ILeaveApprovalDao approvalDao) {
		this.approvalDao = approvalDao;
	}
	/**
	 * 请假状态集合
	 * @param statusMap
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		this.statusMap = statusMap;
	}
	/**
	 * 请假类型集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Leave> find(LeaveInfo info) {
		return this.leaveDao.findLeaves(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected LeaveInfo changeModel(Leave data) {
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
		//审批
		if(data.getApprovals() !=null){
			for(LeaveApproval app:data.getApprovals()){
				if(app == null) continue;
					info.setaId(app.getId());
					info.setaLeaveId(app.getLeave().getId());
					info.setaEmplId("3fb96023-292a-4051-844f-622d5e4a70c9");
					info.setApproval(app.getApproval());
					info.setaType(app.getType());
					info.setaStatus(app.getStatus());
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
		//判断是否通过
		if(info.getaStatus()==LeaveApproval.STATUS_AGREE){
			info.setStatus(Leave.STATUS_PASS);
		}
		if(info.getaStatus() == LeaveApproval.STATUS_DISAGREE){
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
		//审批
		LeaveApproval app=this.buildApproval(info.getaId(), info.getApproval(), info.getaType(), info.getaStatus());
		Set<LeaveApproval> approvale = new HashSet<>();
		if(app != null){
			info.setaId(app.getId());
			info.setaType(app.getType());
			info.setaStatus(app.getStatus());
			info.setApproval(app.getApproval());
			//获取当前请假人ID
			info.setaLeaveId(data.getId());
			//审批人ID
			info.setaEmplId(info.getaEmplId());
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
	/*
	 * 加载类型集合
	 * @see com.examw.oa.service.adm.ILeaveService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(this.typeMap == null|| type==null) return null;
		return this.typeMap.get(type);
	}
	/*
	 * 加载状态集合
	 * @see com.examw.oa.service.adm.ILeaveService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(this.statusMap == null|| status==null) return null;
		return this.statusMap.get(status);
	}
}