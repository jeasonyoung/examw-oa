package com.examw.oa.service.adm.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.adm.LeaveApproval; 
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveApprovalService;
import com.examw.oa.service.adm.ILeaveService;
import com.examw.oa.service.impl.BaseDataServiceImpl;

/**
 * 请假条审批服务接口实现类。
 * @author yangyong.
 * @since 2014-07-28.
 */
public class LeaveApprovalServiceImpl  extends BaseDataServiceImpl<Leave, LeaveInfo> implements ILeaveApprovalService {
	private static final Logger logger = Logger.getLogger(LeaveApprovalServiceImpl.class);
	private ILeaveDao leaveDao;
	private ILeaveService leaveService;
	private IEmployeeDao employeeDao;
	/**
	 * 设置请假条数据接口。
	 * @param leaveDao
	 */
	public void setLeaveDao(ILeaveDao leaveDao) {
		if(logger.isDebugEnabled()) logger.debug("注入请假条数据接口...");
		this.leaveDao = leaveDao;
	}
	/**
	 * 设置请假条服务接口。
	 * @param leaveService
	 */
	public void setLeaveService(ILeaveService leaveService) {
		if(logger.isDebugEnabled()) logger.debug("注入请假条服务接口...");
		this.leaveService = leaveService;
	}
	/**
	 * 设置员工数据接口。
	 * @param employeeDao
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		if(logger.isDebugEnabled()) logger.debug("注入员工数据接口...");
		this.employeeDao = employeeDao;
	}
	/*
	 * 加载请假条信息。
	 * @see com.examw.oa.service.adm.ILeaveApprovalService#loadLeave(java.lang.String)
	 */
	@Override
	public LeaveInfo loadLeave(String id) {
		if(logger.isDebugEnabled()) logger.debug("加载请假条［id="+ id +"］信息...");
		if(StringUtils.isEmpty(id)) return null; 
		return this.changeModel(this.leaveDao.load(Leave.class, id));
	}
	/*
	 * 加载员工的部门ID。
	 * @see com.examw.oa.service.adm.ILeaveApprovalService#loadDeptIdOfEmployee(java.lang.String)
	 */
	@Override
	public String loadDeptIdOfEmployee(String employeeId) {
		if(logger.isDebugEnabled()) logger.debug("加载员工［"+ employeeId+"］部门ID...");
		if(StringUtils.isEmpty(employeeId)) return null;
		Employee data = this.employeeDao.load(Employee.class, employeeId);
		if(data == null || data.getDepartment() == null) return null;
		return data.getDepartment().getId();
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Leave> find(LeaveInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.leaveDao.findLeaves(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected LeaveInfo changeModel(Leave data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		return this.leaveService.convert(data);
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(LeaveInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.leaveDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public LeaveInfo update(LeaveInfo info) {
		if(logger.isDebugEnabled()) logger.debug("请假条审批...");
		String err = null;
		if(!(info.getStatus() == Leave.STATUS_APPROVAL_DEPT || info.getStatus() == Leave.STATUS_APPROVAL_HR || info.getStatus() == Leave.STATUS_APPROVAL_BOSS)){
			logger.error(err = "审批状态不在允许的范围［"+Leave.STATUS_APPROVAL_DEPT+","+Leave.STATUS_APPROVAL_HR+","+Leave.STATUS_APPROVAL_BOSS+"］内...");
			throw new RuntimeException(err);
		}
		if(!(info.getResult() == Leave.RESULT_AGREE || info.getResult() == Leave.RESULT_DISAGREE)){
			logger.error(err = "审批结果不在允许的范围［"+Leave.RESULT_AGREE+","+Leave.RESULT_DISAGREE+"］内...");
			throw new RuntimeException(err);
		}
		Leave data = this.leaveDao.load(Leave.class, info.getId());
		if(data == null){
			logger.error(err = "未找到请假条［id="+ info.getId() +"］数据...");
			throw new RuntimeException(err);
		}
		if(data.getResult() == Leave.RESULT_DISAGREE){
			logger.error(err = "当前请假条［"+info.getId()+"］的审批结果为［"+ this.leaveService.loadResultName(data.getResult()) +"］不需要再审批...");
			throw new RuntimeException(err);
		}
		if(data.getStatus() != info.getStatus()){
			logger.error(err = "当前请假条须［"+ this.leaveService.loadStatusName(data.getStatus()) +"］审批［本审批："+ this.leaveService.loadStatusName(info.getStatus()) +"］...");
			throw new RuntimeException(err);
		}
		Employee employee = this.employeeDao.load(Employee.class, info.getCurrentUserId());
		if(employee == null){
			logger.error("当前员工［currentEmployeeId = "+ info.getCurrentUserId() +"］不存在！");
			throw new RuntimeException(err);
		}
		if(info.getResult() == Leave.RESULT_DISAGREE){
			data.setResult(info.getResult());
		}
		if(info.getResult() == Leave.RESULT_AGREE) {
			data.setResult(info.getStatus() == Leave.STATUS_APPROVAL_BOSS ?  Leave.RESULT_AGREE : Leave.RESULT_APPROVAL);
			if(info.getStatus() == Leave.STATUS_APPROVAL_DEPT){
				data.setStatus(Leave.STATUS_APPROVAL_HR); 
			}
			if(info.getStatus() == Leave.STATUS_APPROVAL_HR){
				data.setStatus(Leave.STATUS_APPROVAL_BOSS); 
			}
		}
		Set<LeaveApproval> approvals = data.getApprovals();
		if(approvals == null) approvals = new HashSet<>();
		LeaveApproval approval = null;
		if(data.getApprovals() != null && data.getApprovals().size() > 0){
			for(LeaveApproval la : data.getApprovals()){
				if(la.getType() == info.getStatus()){
					approval = la;
					break;
				}
			}
		}
		
		if(approval == null){
			approval = new LeaveApproval();
			approval.setId(UUID.randomUUID().toString());
			approval.setType(info.getStatus());
			approval.setCreateTime(new Date());
			approvals.add(approval);
		}
		
		approval.setLeave(data);
		approval.setEmployee(employee);
		approval.setResult(info.getResult());
		approval.setApproval(info.getApproval());
		
		data.setApprovals(approvals);
		return this.changeModel(data);
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled()) logger.debug("删除数据...");
		this.leaveService.delete(ids);
	}
}