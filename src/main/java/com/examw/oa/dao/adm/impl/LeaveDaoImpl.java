package com.examw.oa.dao.adm.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.model.adm.LeaveInfo;
/**
 * 请假条数据操作接口实现类。
 * @author lq.
 * @since 2014-07-15.
 */
public class LeaveDaoImpl extends BaseDaoImpl<Leave> implements ILeaveDao {
	private static final Logger logger = Logger.getLogger(LeaveDaoImpl.class);
	/*
	 * 数据查询
	 * @see com.examw.oa.dao.adm.ILeaveDao#findLeaves(com.examw.oa.model.adm.LeaveInfo)
	 */
	@Override
	public List<Leave> findLeaves(LeaveInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据查询...");
		String hql = "from Leave l where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("employeeName")){
				info.setSort("employee.name");
			}
			hql += " order by l." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计
	 * @see com.examw.oa.dao.adm.ILeaveDao#total(com.examw.oa.model.adm.LeaveInfo)
	 */
	@Override
	public Long total(LeaveInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Leave l where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(LeaveInfo info, String hql, Map<String, Object> parameters){
		if(info.getStatus() != null){
			hql += " and (l.status = :status) ";
			parameters.put("status", info.getStatus());
		}
		if(!StringUtils.isEmpty(info.getCurrentUserId())){
			hql += " and (l.employee.id = :employeeId) ";
			parameters.put("employeeId", info.getCurrentUserId());
		}
		if(!StringUtils.isEmpty(info.getDeptId())){
			hql += " and (l.employee.department.id = :deptId) ";
			parameters.put("deptId", info.getDeptId());
		}
		if(!StringUtils.isEmpty(info.getEmployeeName())){
			hql += " and (l.employee.name like :name)";
			parameters.put("name", "%" + info.getEmployeeName() + "%");
		}
		if(info.getStartTime() != null && info.getEndTime() != null){
			hql += " and (l.startTime between :startTime and :endTime) ";
			Calendar startCal = Calendar.getInstance(),endCal = Calendar.getInstance();
			startCal.setTime(info.getStartTime());
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startCal.set(Calendar.MINUTE, 0);
			startCal.set(Calendar.SECOND, 1);
			endCal.setTime(info.getEndTime());
			endCal.set(Calendar.HOUR_OF_DAY, 23);
			endCal.set(Calendar.MINUTE, 59);
			endCal.set(Calendar.SECOND, 58);
			parameters.put("startTime", startCal.getTime());
			parameters.put("endTime", endCal.getTime());
		}else if(info.getStartTime() != null && info.getEndTime() == null){
			hql += " and (l.startTime >= :startTime) ";
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(info.getStartTime());
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startCal.set(Calendar.MINUTE, 0);
			startCal.set(Calendar.SECOND, 1);
			parameters.put("startTime", startCal.getTime());
		}else if(info.getStartTime() == null && info.getEndTime() != null){
			hql += " and (l.startTime <= :endTime) ";
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(info.getEndTime());
			endCal.set(Calendar.HOUR_OF_DAY, 23);
			endCal.set(Calendar.MINUTE, 59);
			endCal.set(Calendar.SECOND, 58);
			parameters.put("endTime", endCal.getTime());
		}
		return hql;
	}
}