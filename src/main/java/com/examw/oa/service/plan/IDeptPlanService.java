package com.examw.oa.service.plan;

import com.examw.oa.model.plan.DeptPlanInfo;
import com.examw.oa.service.IBaseDataService;

/**
 * 部门计划服务接口。
 * @author lq.
 * @since 2014-08-01.
 */
public interface IDeptPlanService extends IBaseDataService<DeptPlanInfo>{
	/**
	 * 加载报告状态名称。
	 * @param status
	 * 状态值。
	 * @return
	 * 状态名称。
	 */
	String loadStatusName(Integer status);
	/**
	 * 加载类型名称。
	 * @param type
	 * 类型值。
	 * @return
	 * 类型名称。
	 */
	String loadTypeName(Integer type);
}
