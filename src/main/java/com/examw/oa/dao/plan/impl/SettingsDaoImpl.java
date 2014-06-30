package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.ISettingsDao;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.SettingsInfo;
/**
 * 员工报表数据接口实现。
 * @author lq.
 * @since 2014-06-24.
 */
public class SettingsDaoImpl extends BaseDaoImpl<Settings> implements ISettingsDao {
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.org.IEmployeeDao#findEmployees(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public List<Settings> findSettings(SettingsInfo info) {
		String hql = "from Settings s where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("employeeName")){
				info.setSort("employee.name");
			}
			hql += " order by s." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.org.IEmployeeDao#total(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public Long total(SettingsInfo info) {
		String hql = "select count(*) from Settings s where 1 = 1 ";
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
	protected String addWhere(SettingsInfo info, String hql, Map<String, Object> parameters){
//		if(!StringUtils.isEmpty(info.getId())){
//			hql += " and ((s.employee.department.id = :id) or (s.employee.department.parent.id = :id))";
//			parameters.put("id", info.getId());
//		}
		if(!StringUtils.isEmpty(info.getEmployeeName())){
			hql += " and (s.employee.name like :employeeName)";
			parameters.put("employeeName", "%" + info.getEmployeeName() + "%");
		}
		if(info.getType() != null && info.getType().length == 1){
			hql += " and ((s.type & :type) = :type)";
			parameters.put("type", info.getType()[0]);
		}
		return hql;
	}
}