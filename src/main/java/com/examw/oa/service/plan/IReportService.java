package com.examw.oa.service.plan;

import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.IBaseDataService;

public interface IReportService extends IBaseDataService<ReportInfo>{
	/**
	 * 加载状态名称。
	 * @param status
	 * 状态值。
	 * @return
	 * 状态名称。
	 */
	String loadStatusName(Integer status);
	/**
	 * 
	 */
	void addTaskDaily();
	void addTaskWeek();
	void addTaskMonth();
}