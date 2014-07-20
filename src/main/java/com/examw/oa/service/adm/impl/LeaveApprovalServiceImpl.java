package com.examw.oa.service.adm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveApprovalDao;
import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.domain.org.Post;
import com.examw.oa.model.adm.LeaveApprovalInfo;
import com.examw.oa.service.adm.ILeaveApprovalService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 请假审批服务
 * @author lq.
 * @since 2014-07-17.
 */
public class LeaveApprovalServiceImpl extends BaseDataServiceImpl<LeaveApproval, LeaveApprovalInfo> implements ILeaveApprovalService {
	private ILeaveApprovalDao approvalDao;
	private ILeaveDao leaveDao;
	private IEmployeeDao employeeDao;
	private IPostDao postDao;
	private Map<Integer, String> typeMap;
	private Map<Integer, String> statusMap;
	/**
	 * 请假审核数据接口
	 * @param approvalDao
	 */
	public void setApprovalDao(ILeaveApprovalDao approvalDao) {
		this.approvalDao = approvalDao;
	}
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
	 * 类型数据集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}
	/**
	 * 状态数据集合
	 * @param statusMap
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		this.statusMap = statusMap;
	}
	/*
	 * 加载类型名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
	/*
	 * 状态集合
	 * @see com.examw.oa.service.adm.ILeaveApprovalService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(this.statusMap == null || status == null) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<LeaveApproval> find(LeaveApprovalInfo info) {
		return this.approvalDao.findLeaveApprovals(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected LeaveApprovalInfo changeModel(LeaveApproval data) {
		if(data == null) return null;
		LeaveApprovalInfo info = new LeaveApprovalInfo();
		BeanUtils.copyProperties(data, info);
		
		if(data.getEmployee() != null){
			info.setEmployeeId(data.getEmployee().getId());
			info.setEmployeeName(data.getEmployee().getName());
		}
		if(data.getLeave() != null){
			info.setLeaveId(data.getLeave().getId());
			info.setLeaveName(data.getLeave().getPostName());
		}
		if(data.getPost() != null){
			info.setPostId(data.getPost().getId());
		}
		info.setTypeName(this.loadTypeName(data.getType()));
		return info;
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(LeaveApprovalInfo info){
		return this.approvalDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public LeaveApprovalInfo update(LeaveApprovalInfo info) {
		if(info == null) return null;
		LeaveApproval data = StringUtils.isEmpty(info.getId()) ?  null : this.approvalDao.load(LeaveApproval.class, info.getId());
		BeanUtils.copyProperties(info, data);
		
		
		if(!StringUtils.isEmpty(info.getLeaveId()) && (data.getLeave() == null || !data.getLeave().getId().equalsIgnoreCase(info.getLeaveId()))){
			Leave leave = this.leaveDao.load(Leave.class, info.getLeaveId());
			if(leave != null)data.setLeave(leave);
		}
		if(!StringUtils.isEmpty(info.getEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEmployeeId()))){
			Employee empl = this.employeeDao.load(Employee.class, info.getEmployeeId());
			if(empl != null)data.setEmployee(empl);
		}
		if(!StringUtils.isEmpty(info.getPostId()) && (data.getPost() == null || !data.getPost().getId().equalsIgnoreCase(info.getPostId()))){
			Post post = this.postDao.load(Post.class, info.getPostId());
			if(post != null)data.setPost(post);
		}
		if(data.getLeave() != null){
			info.setLeaveName(data.getLeave().getPostName());
		}
		
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
		}
		info.setTypeName(this.loadTypeName(data.getType()));
		return info;
	}
	/*
	 * 数据删除
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length;i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			LeaveApproval e = this.approvalDao.load(LeaveApproval.class, ids[i]);
			if(e != null) this.approvalDao.delete(e);
		}
	}
}