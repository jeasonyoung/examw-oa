package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Department;
import com.examw.oa.model.org.DepartmentInfo;

/**
 * 部门信息数据接口。
 * @author lq.
 * @since 2014-06-11.
 */
public interface IDepartmentDao extends IBaseDao<Department>{
	/**
	 * 加载一级部门集合。
	 * @return
	 */
	List<Department> loadFristDepartments();
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<Department> findDepartments(DepartmentInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(DepartmentInfo info);
}