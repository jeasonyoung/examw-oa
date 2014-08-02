package com.examw.oa.dao.plan;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.plan.DeptPlanMember;
import com.examw.oa.model.plan.DeptPlanMemberInfo;
/**
 * 部门计划成员数据接口。
 * @author lq.
 * @since 2014-08-01.
 */
public interface IDeptPlanMenberDao extends IBaseDao<DeptPlanMember>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<DeptPlanMember> findDeptPlanMembers(DeptPlanMemberInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(DeptPlanMemberInfo info);
	/**
	 * 根据部门计划ID查询部门计划成员。
	 * @param deptId
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<DeptPlanMember> loedDeptPlanMembers(String deptId);
}
