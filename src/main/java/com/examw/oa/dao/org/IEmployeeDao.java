package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.model.org.EmployeeInfo;
/**
 * 员工接口。
 * @author lq.
 * @since 2014-06-16.
 */
public interface IEmployeeDao extends IBaseDao<Employee>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Employee> findEmployees(EmployeeInfo info);
	 /**
		 * 查询数据总数。
		 * @param info
		 * 查询条件。
		 * @return
		 * 数据总数。
		 */
	 Long total(EmployeeInfo info);
}