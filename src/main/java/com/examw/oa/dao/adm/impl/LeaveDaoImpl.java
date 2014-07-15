package com.examw.oa.dao.adm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.ILeaveDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.model.adm.LeaveInfo;
/**
 * 请假条数据接口实现类
 * @author lq.
 * @since 2014-07-15.
 */
public class LeaveDaoImpl extends BaseDaoImpl<Leave> implements ILeaveDao {
	/*
	 * 数据查询
	 * @see com.examw.oa.dao.adm.ILeaveDao#findLeaves(com.examw.oa.model.adm.LeaveInfo)
	 */
	@Override
	public List<Leave> findLeaves(LeaveInfo info) {
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
	 * 数据统计
	 * @see com.examw.oa.dao.adm.ILeaveDao#total(com.examw.oa.model.adm.LeaveInfo)
	 */
	@Override
	public Long total(LeaveInfo info) {
		String hql = "select count(*) from Leave l where 1 = 1 ";
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
	protected String addWhere(LeaveInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getEmployeeName())){
			hql += " and (e.employee.name like :name)";
			parameters.put("name", "%" + info.getEmployeeName() + "%");
		}
		return hql;
	}
}