package com.examw.oa.service.plan.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IReportDao;
import com.examw.oa.domain.plan.Business;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.domain.plan.ReportDetail;
import com.examw.oa.model.plan.ReportReviewInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IReportReviewService;
import com.examw.oa.service.plan.IReportService;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 报告查阅服务接口实现类。
 * @author yangyong.
 * @since 2014-07-25.
 */
public class ReportReviewServiceImpl extends BaseDataServiceImpl<Report,ReportReviewInfo> implements IReportReviewService {
	private static final Logger logger = Logger.getLogger(ReportReviewServiceImpl.class);
	private IReportDao reportDao;
	private IReportService reportService;
	private ISettingsService settingsService;
	/**
	 * 设置报告数据接口。
	 * @param reportDao
	 * 	报告数据接口。
	 */
	public void setReportDao(IReportDao reportDao) {
		if(logger.isDebugEnabled()) logger.debug("注入报告数据接口...");
		this.reportDao = reportDao;
	}
	/**
	 * 设置报告服务接口。
	 * @param reportService
	 */
	public void setReportService(IReportService reportService) {
		if(logger.isDebugEnabled()) logger.debug("注入报告服务接口...");
		this.reportService = reportService;
	}
	/**
	 * 设置报告设置服务接口。
	 * @param settingsService
	 * 计划设置服务。
	 */
	public void setSettingsService(ISettingsService settingsService) {
		if(logger.isDebugEnabled())logger.debug("注入报告设置服务接口...");
		this.settingsService = settingsService;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Report> find(ReportReviewInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.reportDao.findReports(info);
	}
	/*
	 * 类型转换。.
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected ReportReviewInfo changeModel(Report data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null) return null;
		ReportReviewInfo info = new ReportReviewInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
			if(data.getEmployee().getDepartment() != null){
				info.setDeptName(data.getEmployee().getDepartment().getName());
			}
			if(data.getEmployee().getPost() != null){
				info.setPostName(data.getEmployee().getPost().getName());
			}
			if(data.getEmployee().getRank() != null){
				info.setRankName(data.getEmployee().getRank().getName());
			}
		}
		info.setStatusName(this.reportService.loadStatusName(data.getStatus()));
		info.setTypeName(this.settingsService.loadTypeName(data.getType()));
		if(data.getDetails() != null && data.getDetails().size() > 0){
			for(ReportDetail detail : data.getDetails()){
				if(detail == null) continue;
				if(detail.getType() == ReportDetail.TYPE_PLAN){
					info.setPlanDetail(detail.getContent());
					info.setPlanBusiness(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
				if(detail.getType() == ReportDetail.TYPE_SUMMARY){
					info.setSummaryDetail(detail.getContent());
					info.setSummaryBusiness(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
				if(detail.getType() == ReportDetail.TYPE_SUPPORT){
					info.setSupportDetail(detail.getContent());
					info.setSupportBusiness(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
				if(detail.getType() == ReportDetail.TYPE_SUGGESTION){
					info.setSuggetsionDetail(detail.getContent());
					info.setSuggetsionBusiness(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
			}
		}
		return info;
	}
	//加载报告明细关联的业务系统ID集合。
	private String loadDetailBusiness(Set<Business>  businesses){
		if(businesses == null || businesses.size() == 0) return null;
		StringBuilder builder = new StringBuilder();
		for(Business b : businesses){
			if(b == null) continue;
			if(builder.length() > 0) builder.append(",");
			builder.append(b.getName());
		}
		return builder.toString();
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(ReportReviewInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.reportDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public ReportReviewInfo update(ReportReviewInfo info) {
		if(logger.isDebugEnabled()) logger.debug("更新数据（报告审阅）...");
		String err = null;
		if(info == null || StringUtils.isEmpty(info.getId())) {
			if(logger.isDebugEnabled()) logger.error(err = "未传入报告对象或报告ID为NULL！");
			throw new RuntimeException(err);
		}
		Report data = this.reportDao.load(Report.class, info.getId());
		if(data == null){
			if(logger.isDebugEnabled()) logger.error(err = "未找到报告［id="+ info.getId() +"］！");
			throw new RuntimeException(err);
		}
		if(data.getStatus() == Report.STATUS_POST){
			data.setStatus(Report.STATUS_AUDIT);
			info.setStatusName(this.reportService.loadStatusName(data.getStatus()));
		}
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		 if(logger.isDebugEnabled()) logger.debug("删除数据...");
		 this.reportService.delete(ids);
	}
}