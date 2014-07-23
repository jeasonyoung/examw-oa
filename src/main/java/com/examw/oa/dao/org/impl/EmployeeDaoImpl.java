package com.examw.oa.dao.org.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.org.EmployeeInfo;
/**
 * 员工数据操作接口实现类。
 * @author lq
 * @since 2014-06-16.
 */
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements IEmployeeDao {
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.org.IEmployeeDao#findEmployees(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public List<Employee> findEmployees(EmployeeInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		String hql = "from Employee e where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("deptName")){
				info.setSort("department.name");
			}else if(info.getSort().equalsIgnoreCase("postName")){
				info.setSort("post.name");
			}else if(info.getSort().equalsIgnoreCase("rankName")){
				info.setSort("rank.name");
			}else if(info.getSort().equalsIgnoreCase("genderName")){
				info.setSort("gender");
			}else if(info.getSort().equalsIgnoreCase("statusName")){
				info.setSort("status");
			}
			hql += " order by e." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.org.IEmployeeDao#total(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public Long total(EmployeeInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from Employee e where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	//查询条件。
	private String addWhere(EmployeeInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDeptId())){
			hql += " and ((e.department.id = :deptmentId) or (e.department.parent.id = :deptmentId))";
			parameters.put("deptmentId", info.getDeptId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and ((e.code like :name) or (e.name like :name))";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
	/*
	 * 查询部门下的员工集合。
	 * @see com.examw.oa.dao.org.IEmployeeDao#findEmployees(java.lang.String)
	 */
	@Override
	public List<Employee> findEmployees(String deptId) {
		final String hql = "from Employee e where e.department.id = :deptId order by e.name";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("deptId", deptId);
		return this.find(hql, parameters, null, null);
	}
}