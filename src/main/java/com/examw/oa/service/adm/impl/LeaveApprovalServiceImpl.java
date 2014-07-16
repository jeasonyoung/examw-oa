package com.examw.oa.service.adm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveApprovalDao;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.model.adm.LeaveApprovalInfo;
import com.examw.oa.service.adm.ILeaveApprovalService;
import com.examw.oa.service.impl.BaseDataServiceImpl;

public class LeaveApprovalServiceImpl extends BaseDataServiceImpl<LeaveApproval, LeaveApprovalInfo> implements ILeaveApprovalService {
	private ILeaveApprovalDao approvalDao;
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
	 * 类型集合
	 * @see com.examw.oa.service.adm.ILeaveApprovalService#loadTypeName(java.lang.Integer)
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
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(LeaveApprovalInfo info) {
		return this.approvalDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public LeaveApprovalInfo update(LeaveApprovalInfo info) {
		// TODO Auto-generated method stub
		return null;
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