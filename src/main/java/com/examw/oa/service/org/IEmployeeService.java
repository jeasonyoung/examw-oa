package com.examw.oa.service.org;

import java.util.List;

import com.examw.oa.model.org.EmployeeInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 员工服务。
 * @author lq.
 * @since 2014-06-16.
 */
public interface IEmployeeService extends IBaseDataService<EmployeeInfo>{
	/**
	 * 加载性别名称。
	 * @param gender
	 * 性别值。
	 * @return
	 * 性别名称。
	 */
	String loadGenderName(Integer gender);
	/**
	 * 加载状态名称。
	 * @param status
	 * 状态值。
	 * @return
	 * 状态名称。
	 */
	String loadStatusName(Integer status);
	/**
	 * 根据部门ID获员工信息。
	 * @param departmentId
	 * @return
	 */
	List<EmployeeInfo> loadEmployee(String departmentId);
}