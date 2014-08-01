package com.examw.oa.dao.plan;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.plan.DeptPlan;
import com.examw.oa.model.plan.DeptPlanInfo;
/**
 * 部门计划数据接口。
 * @author lq.
 * @since 2014-08-01.
 */
public interface IDeptPlanDao extends IBaseDao<DeptPlan>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<DeptPlan> findDeptPlans(DeptPlanInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(DeptPlanInfo info);
}
