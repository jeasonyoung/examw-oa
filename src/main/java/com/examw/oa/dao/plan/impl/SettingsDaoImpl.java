package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.ISettingsDao;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.SettingsInfo;
/**
 * 员工报告设置服务接口实现类。
 * @author lq.
 * @since 2014-06-24.
 */
public class SettingsDaoImpl extends BaseDaoImpl<Settings> implements ISettingsDao {
	private static final Logger logger = Logger.getLogger(SettingsDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.org.IEmployeeDao#findEmployees(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public List<Settings> findSettings(SettingsInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Settings s where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("employeeName")){
				info.setSort("employee.name");
			}
			if(info.getSort().equalsIgnoreCase("deptName")){
				info.setSort("employee.department.name");
			}
			hql += " order by s." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询统计。
	 * @see com.examw.oa.dao.org.IEmployeeDao#total(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public Long total(SettingsInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Settings s where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//查询条件
	private String addWhere(SettingsInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDeptId())){
			hql += " and ((s.employee.department.id = :departmentId) or (s.employee.department.parent.id = :departmentId))";
			parameters.put("departmentId", info.getDeptId());
		}
		if(!StringUtils.isEmpty(info.getEmployeeName())){
			hql += " and (s.employee.name like :employeeName)";
			parameters.put("employeeName", "%" + info.getEmployeeName() + "%");
		}
		if(info.getType() != null && (info.getType().length == 1) && !StringUtils.isEmpty(info.getType()[0])){
			hql += " and (bitand(s.type,:type) = :type)";
			parameters.put("type", new Integer(info.getType()[0]));
		}
		return hql;
	}
}