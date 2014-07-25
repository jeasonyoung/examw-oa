package com.examw.oa.service.plan;
/**
 * 定时任务接口。
 * @author yangyong.
 * 2014-07-23.
 */
public interface IQuartzTask {
	/**
	 * 新增日报。
	 */
	void addTaskDaily();
	/**
	 * 新增周报。
	 */
	void addTaskWeek();
	/**
	 * 新增月报任务
	 */
	void addTaskMonth();
}