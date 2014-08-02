package com.examw.oa.service.plan;

import java.util.List;

import com.examw.oa.model.plan.DeptPlanMemberInfo;

/**
 * 部门计划成员以外接口。
 * @author lq.
 * @since 2014-08-01.
 */
public interface IDeptPlanMenberService {
	/**
	 * 根据部门计划ID查询部门计划成员数据。
	 * @param deptId
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	List<DeptPlanMemberInfo> findDeptPlanMembers(String deptId);
}
