package com.examw.oa.dao.adm.impl;

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
 * 我要请假数据操作接口实现类
 * @author lq.
 * @since 2014-07-15.
 */
public class LeaveDaoImpl extends BaseDaoImpl<Leave> implements ILeaveDao {
	private static Logger logger = Logger.getLogger(LeaveDaoImpl.class);
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
			}else if(info.getSort().equalsIgnoreCase("shiftEmployeeName")){
				info.setSort("shiftEmployee.name");
			}
			hql += " order by l." + info.getSort() + " " + info.getOrder();
		}
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
		return this.count(hql, parameters);
	}
	//条件查询
	protected String addWhere(LeaveInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getEmployeeName())){
			hql += " and (l.employee.name like :name)";
			parameters.put("name", "%" + info.getEmployeeName() + "%");
		}
		return hql;
	}
}