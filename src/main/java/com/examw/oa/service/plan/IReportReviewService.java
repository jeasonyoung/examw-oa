package com.examw.oa.service.plan;

import com.examw.oa.model.plan.ReportReviewInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 报告查阅服务接口。
 * @author yangyong.
 * @since 2014-07-25.
 */
public interface IReportReviewService extends IBaseDataService<ReportReviewInfo> {
	/**
	 * 加载报告信息。
	 * @param id
	 * 报告ID。
	 * @return
	 * 报告信息。
	 */
	ReportReviewInfo loadReportReview(String id);
}