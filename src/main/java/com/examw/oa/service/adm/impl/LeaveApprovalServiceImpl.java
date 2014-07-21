package com.examw.oa.service.adm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.examw.oa.dao.adm.ILeaveApprovalDao;
import com.examw.oa.domain.adm.LeaveApproval;
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
	private Map<String, String> typeMap;
	private Map<String, String> statusMap;
	/**
	 * 请假审核数据接口
	 * @param approvalDao
	 */
	public void setApprovalDao(ILeaveApprovalDao approvalDao) {
		this.approvalDao = approvalDao;
	}
	/**
	 * 类型数据集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
	}
	/**
	 * 状态数据集合
	 * @param statusMap
	 */
	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}
	/*
	 * 加载类型名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(typeMap==null || type==null)
			return null;
			return typeMap.get(type.toString());
	}
	/*
	 * 状态集合
	 * @see com.examw.oa.service.adm.ILeaveApprovalService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(statusMap==null || status==null)
			return null;
			return statusMap.get(status.toString());
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
		return null;
	}
	/*
	 * 数据删除
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
	}
	@Override
	public Map<String, String> getTypeMap() {
		
		return typeMap;
	}
	@Override
	public Map<String, String> getStatusMap() {
		// TODO Auto-generated method stub
		return statusMap;
	}
}