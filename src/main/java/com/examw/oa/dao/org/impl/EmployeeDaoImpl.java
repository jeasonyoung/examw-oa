package com.examw.oa.dao.org.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.org.EmployeeInfo;
/**
 * 员工信息数据操作实现类。
 * @author lq
 * @since 2014-06-16.
 */
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements IEmployeeDao {
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.org.IEmployeeDao#findEmployees(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public List<Employee> findEmployees(EmployeeInfo info) {
		String hql = "from Employee e where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("departmentName")){
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
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.org.IEmployeeDao#total(com.examw.oa.model.org.EmployeeInfo)
	 */
	@Override
	public Long total(EmployeeInfo info) {
		String hql = "select count(*) from Employee e where 1 = 1 ";
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
	protected String addWhere(EmployeeInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDepartmentId())){
			hql += " and ((e.department.id = :deptmentId) or (e.department.parent.id = :deptmentId))";
			parameters.put("deptmentId", info.getDepartmentId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and ((e.code like :name) or (e.name like :name))";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
	/*
	 * 部门ID查询员工信息
	 * @see com.examw.oa.dao.org.IEmployeeDao#loadEmployee(java.lang.String)
	 */
	@Override
	public List<Employee> loadEmployee(String departmentId) {
		Map<String, Object> parameters = new HashMap<>();
		String hql = "from Employee e where 1=1 ";
		if(!StringUtils.isEmpty(departmentId)){
			hql +=" and (e.department.id = :departmentId)";
			parameters.put("departmentId", departmentId);
		}
		return this.find(hql, parameters, null, null);
	}
}