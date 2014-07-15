package com.examw.oa.service.adm.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveService;
import com.examw.oa.service.impl.BaseDataServiceImpl;

public class LeaveServiceImpl extends BaseDataServiceImpl<Leave, LeaveInfo> implements ILeaveService {
	private ILeaveDao leaveDao;
	private IEmployeeDao employeeDao;
	private Map<Integer, String> typeMap;
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
		if(data.getEmployee() != null){
			info.setShiftEmployeeId(data.getEmployee().getId());
			info.setShiftEmployeeName(data.getEmployee().getName());
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
		BeanUtils.copyProperties(info, data,new String[]{"approvals"});
		if(!StringUtils.isEmpty(info.getEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEmployeeId()))){
			Employee e = this.employeeDao.load(Employee.class, info.getEmployeeId());
			if(e != null) data.setEmployee(e);
		}
		if(!StringUtils.isEmpty(info.getShiftEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getShiftEmployeeId()))){
			Employee s = this.employeeDao.load(Employee.class, info.getShiftEmployeeId());
			if(s != null) data.setEmployee(s);
		}
		if(data.getEmployee() != null)
			info.setEmployeeName(data.getEmployee().getName());
		
		if(data.getEmployee() != null)
			info.setShiftEmployeeName(data.getEmployee().getName());
		if(isAdded)this.leaveDao.save(data);
		return info;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length;i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Leave e = this.leaveDao.load(Leave.class, ids[i]);
			if(e != null) this.leaveDao.delete(e);
		}
	}
	/*
	 * 加载类型集合
	 * @see com.examw.oa.service.adm.ILeaveService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
}