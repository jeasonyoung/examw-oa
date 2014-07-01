package com.examw.oa.service.plan;

import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 计划总结服务接口。
 * @author lq
 * @since 2014-06-25.
 */
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
	 * 日报任务
	 */
	void addTaskDaily();
	/**
	 * 周报任务
	 */
	void addTaskWeek();
	/**
	 * 月报任务
	 */
	void addTaskMonth();
}