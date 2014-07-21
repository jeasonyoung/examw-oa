package com.examw.oa.dao.adm.impl;

import java.util.HashMap;
import java.util.List;


import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveApprovalDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.model.adm.LeaveApprovalInfo;
/**
 * 请假审批数据接口
 * @author lq.
 * @since 2014-07-16.
 */
public class LeaveApprovalDaoImpl extends BaseDaoImpl<LeaveApproval> implements ILeaveApprovalDao {
	/*
	 * 数据查询
	 * @see com.examw.oa.dao.adm.ILeaveApprovalDao#findLeaveApprovals(com.examw.oa.model.adm.LeaveApprovalInfo)
	 */
	@Override
	public List<LeaveApproval> findLeaveApprovals(LeaveApprovalInfo info) {
		String hql = "from LeaveApproval l where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("employeeName")){
				info.setSort("employee.name");
			}else if(info.getSort().equalsIgnoreCase("leaveName")){
				info.setSort("leave.name");
			}
			hql += " order by l." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.dao.adm.ILeaveApprovalDao#total(com.examw.oa.model.adm.LeaveApprovalInfo)
	 */
	@Override
	public Long total(LeaveApprovalInfo info) {
		String hql = "select count(*) from LeaveApproval l where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	/**
	 * 添加查询条件到HQL。
	 * @param info
	 * 查询条件。
	 * @param hql
	 * HQL
	 * @param parameters
	 * 参数。
	 * @return
	 * HQL
	 */
	protected String addWhere(LeaveApprovalInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getEmployeeName())){
			hql += " and (l.employee.name like :name)";
			parameters.put("name", "%" + info.getEmployeeName() + "%");
		}
		if(info.getType() != null){
			hql += " and (l.type = :type)";
			parameters.put("type", info.getType());
		}
		if(info.getStatus() != null){
			hql += " and (l.status = :status)";
			parameters.put("status", info.getStatus());
		}
		return hql;
	}
}