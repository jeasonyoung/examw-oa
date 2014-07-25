package com.examw.oa.service.plan;

import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 员工报告服务接口。
 * @author lq
 * @since 2014-06-25.
 */
public interface IReportService extends IBaseDataService<ReportInfo> {
	/**
	 * 加载报告状态名称。
	 * @param status
	 * 状态值。
	 * @return
	 * 状态名称。
	 */
	String loadStatusName(Integer status);
	/**
	 * 加载报告明细类型名称。
	 * @param type
	 * 类型值。
	 * @return
	 * 类型名称。
	 */
	String loadDetailTypeName(Integer type);
}