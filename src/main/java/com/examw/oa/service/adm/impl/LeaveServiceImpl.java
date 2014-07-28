package com.examw.oa.service.adm.impl;

import java.util.Date; 
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 请假条服务接口实现。
 * @author lq.
 * @since 2014-07-17.
 */
public class LeaveServiceImpl extends BaseDataServiceImpl<Leave, LeaveInfo> implements ILeaveService {
	private static final Logger logger = Logger.getLogger(LeaveServiceImpl.class);
	private ILeaveDao leaveDao;
	private IEmployeeDao employeeDao;
	private Map<Integer, String> typeMap,statusMap,resultMap;
	/**
	 * 设置请假条数据接口。
	 * @param leaveDao
	 * 请假条数据接口。
	 */
	public void setLeaveDao(ILeaveDao leaveDao) {
		if(logger.isDebugEnabled())logger.debug("注入请假数据接口...");
		this.leaveDao = leaveDao;
	}
	/**
	 * 设置员工数据接口。
	 * @param employeeDao
	 * 员工数据接口。
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		if(logger.isDebugEnabled())logger.debug("注入员工信息数据接口...");
		this.employeeDao = employeeDao;
	}
	/**
	 * 设置请假状态集合。
	 * @param statusMap
	 * 请假状态集合。
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		if(logger.isDebugEnabled())logger.debug("注入状态集合...");
		this.statusMap = statusMap;
	}
	/**
	 * 设置请假类型集合。
	 * @param typeMap
	 * 请假类型集合。
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		if(logger.isDebugEnabled())logger.debug("注入类型集合...");
		this.typeMap = typeMap;
	}
	/**
	 * 设置审批结果集合。
	 * @param resultMap
	 */
	public void setResultMap(Map<Integer, String> resultMap) {
		if(logger.isDebugEnabled()) logger.debug("注入审批结果集合...");
		this.resultMap = resultMap;
	}
	/*
	 * 加载类型名称。
	 * @see com.examw.oa.service.adm.ILeaveService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载类型［"+type+"］名称...");
		if(this.typeMap == null|| type==null) return null;
		return this.typeMap.get(type);
	}
	/*
	 * 加载状态名称。
	 * @see com.examw.oa.service.adm.ILeaveService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(logger.isDebugEnabled()) logger.debug("加载状态［"+status+"］名称...");
		if(this.statusMap == null|| status==null) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 加载审批结果名称。
	 * @see com.examw.oa.service.adm.ILeaveService#loadResultName(java.lang.Integer)
	 */
	@Override
	public String loadResultName(Integer result) {
		if(logger.isDebugEnabled()) logger.debug("加载审批状态［"+result+"］名称...");
		if(this.resultMap == null || result == null) return null;
		return this.resultMap.get(result);
	}
	/*
	 * 加载请假员工信息。
	 * @see com.examw.oa.service.adm.ILeaveService#loadLeaveEmployee(java.lang.String)
	 */
	@Override
	public LeaveInfo loadLeaveEmployee(String employeeId) {
		if(logger.isDebugEnabled()) logger.debug("加载请假员工［employeeId="+ employeeId +"］");
		 if(StringUtils.isEmpty(employeeId)) return null;
		 Employee data = this.employeeDao.load(Employee.class, employeeId);
		 if(data == null) return null;
		 LeaveInfo info = new LeaveInfo();
		 info.setEmployeeId(data.getId());
		 info.setEmployeeName(data.getName());
		 if(data.getDepartment() != null){
			 info.setDeptId(data.getDepartment().getId());
			 info.setDeptName(data.getDepartment().getName());
		 }
		 if(data.getPost() != null)info.setPostName(data.getPost().getName()); 
		 return info;
	}
	/*
	 * 数据查询。
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
		BeanUtils.copyProperties(data, info);
		
		if(data.getEmployee() != null){
			info.setEmployeeId(data.getEmployee().getId());
			info.setEmployeeName(data.getEmployee().getName());
		}
		info.setTypeName(this.loadTypeName(info.getType()));
		info.setStatusName(this.loadStatusName(info.getStatus()));
		info.setResultName(this.loadResultName(info.getResult()));
		
		this.addApprovals(data, info);
		
		return info;
	}
	//添加审批信息。
	private void addApprovals(Leave data,LeaveInfo info){
		if(info == null || data == null || data.getApprovals() == null || data.getApprovals().size() == 0) return;
		for(LeaveApproval approval : data.getApprovals()){
			if(approval == null) continue;
			if(approval.getType() == Leave.STATUS_APPROVAL_DEPT){
				info.setDeptApprovalId(approval.getId());
				info.setDeptApprovalResult(approval.getResult());
				info.setDeptApprovalResultName(this.loadResultName(approval.getResult()));
				info.setDeptApproval(approval.getApproval());
				info.setDeptApprovalTime(approval.getCreateTime());
				continue;
			}
			if(approval.getType() == Leave.STATUS_APPROVAL_HR){
				info.setHrApprovalId(approval.getId());
				info.setHrApprovalResult(approval.getResult());
				info.setHrApprovalResultName(this.loadResultName(approval.getResult()));
				info.setHrApproval(approval.getApproval());
				info.setHrApprovalTime(approval.getCreateTime());
				continue;
			}
			if(approval.getType() == Leave.STATUS_APPROVAL_BOSS){
				info.setBossApprovalId(approval.getId());
				info.setBossApprovalResult(approval.getResult());
				info.setBossApprovalResultName(this.loadResultName(approval.getResult()));
				info.setBossApproval(approval.getApproval());
				info.setBossApprovalTime(approval.getCreateTime());
				continue;
			}
		}
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
			if(StringUtils.isEmpty(info.getId())) info.setId(UUID.randomUUID().toString());
			info.setCreateTime(new Date());
			data = new Leave();
			data.setStatus(Leave.STATUS_APPROVAL_DEPT);
			data.setResult(Leave.RESULT_POST);
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data, new String[]{"status","result"});
		if(!StringUtils.isEmpty(info.getEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEmployeeId()))){
			Employee e = this.employeeDao.load(Employee.class, info.getEmployeeId());
			if(e != null) {
				data.setEmployee(e);	
				if(e.getDepartment() != null) data.setDeptName(e.getDepartment().getName());
				if(e.getPost() != null) data.setPostName(e.getPost().getName());
			}
		} 
		info.setDeptName(data.getDeptName());
		info.setPostName(data.getPostName());
		
		info.setTypeName(this.loadTypeName(data.getType()));
		info.setStatusName(this.loadStatusName(data.getStatus()));
		info.setResultName(this.loadResultName(data.getResult()));
		if(isAdded)this.leaveDao.save(data);
		return info;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled()) logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Leave data = this.leaveDao.load(Leave.class, ids[i]);
			if(data != null) {
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i] +"］");
				this.leaveDao.delete(data);
			}
		}
	}
}