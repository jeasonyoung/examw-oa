package com.examw.oa.service.plan.impl;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map; 
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IBusinessDao;
import com.examw.oa.dao.plan.IReportDao; 
import com.examw.oa.domain.plan.Business;
import com.examw.oa.domain.plan.Report; 
import com.examw.oa.domain.plan.ReportDetail;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IQuartzTask;
import com.examw.oa.service.plan.IReportService;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 员工报告服务接口实现类。
 * @author lq.
 * @since 2014-07-02.
 */
public class ReportServiceImpl extends BaseDataServiceImpl<Report, ReportInfo> implements IReportService,IQuartzTask {
	private static final Logger logger = Logger.getLogger(ReportServiceImpl.class);
	private IReportDao reportDao;
	private IBusinessDao businessDao;
	private ISettingsService settingsService;
	private Map<Integer, String> statusMap,detailTypeMap;
	/**
	 * 设置报告数据接口。
	 * @param reportDao
	 * 员工报表数据接口。
	 */
	public void setReportDao(IReportDao reportDao) {
		if(logger.isDebugEnabled())logger.debug("注入报告数据接口...");
		this.reportDao = reportDao;
	}
	/**
	 * 设置业务系统数据接口。
	 * @param reportDao
	 * 业务系统数据接口。
	 */
	public void setBusinessDao(IBusinessDao businessDao) {
		if(logger.isDebugEnabled())logger.debug("注入业务系统数据接口...");
		this.businessDao = businessDao;
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
	/**
	 * 设置报告状态集合。
	 * @param reportDao
	 * 状态集合。
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		if(logger.isDebugEnabled())logger.debug("注入状态集合...");
		this.statusMap = statusMap;
	}
	/**
	 * 设置报告明细类型集合。
	 * @param detailTypeMap
	 */
	public void setDetailTypeMap(Map<Integer, String> detailTypeMap) {
		this.detailTypeMap = detailTypeMap;
	}
	/*
	 * 加载报告状态名称。
	 * @see com.examw.oa.service.plan.IReportService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(logger.isDebugEnabled()) logger.debug("加载报告状态［"+ status +"］名称...");
		if(status == null  || this.statusMap == null || this.statusMap.size() == 0) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 加载报告明细类型名称。
	 * @see com.examw.oa.service.plan.IReportService#loadDetailTypeName(java.lang.Integer)
	 */
	@Override
	public String loadDetailTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载报告明细类型［"+ type +"］名称...");
		if(type == null || this.detailTypeMap == null || this.detailTypeMap.size() == 0) return null;
		return this.detailTypeMap.get(type);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Report> find(ReportInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.reportDao.findReports(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected ReportInfo changeModel(Report data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null) return null;
		ReportInfo info = new ReportInfo();
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
		info.setStatusName(this.loadStatusName(data.getStatus()));
		info.setTypeName(this.settingsService.loadTypeName(data.getType()));
		if(data.getDetails() != null && data.getDetails().size() > 0){
			for(ReportDetail detail : data.getDetails()){
				if(detail == null) continue;
				if(detail.getType() == ReportDetail.TYPE_PLAN){
					info.setPlanId(detail.getId());
					info.setPlanDetail(detail.getContent());
					info.setPlanBusinessId(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
				if(detail.getType() == ReportDetail.TYPE_SUMMARY){
					info.setSummaryId(detail.getId());
					info.setSummaryDetail(detail.getContent());
					info.setSummaryBusinessId(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
				if(detail.getType() == ReportDetail.TYPE_SUPPORT){
					info.setSupportId(detail.getId());
					info.setSupportDetail(detail.getContent());
					info.setSupportBusinessId(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
				if(detail.getType() == ReportDetail.TYPE_SUGGESTION){
					info.setSuggetsionId(detail.getId());
					info.setSuggetsionDetail(detail.getContent());
					info.setSuggetsionBusinessId(this.loadDetailBusiness(detail.getBusinesses()));
					continue;
				}
			}
		}
		return info;
	}
	//加载报告明细关联的业务系统ID集合。
	private String[] loadDetailBusiness(Set<Business>  businesses){
		if(businesses == null || businesses.size() == 0) return null;
		List<String> list = new ArrayList<>();
		for(Business b : businesses){
			if(b == null) continue;
			list.add(b.getId());
		}
		return list.size() > 0 ? list.toArray(new String[0]) :  null;
	}
	/*
	 * 查询统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(ReportInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.reportDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public ReportInfo update(ReportInfo info) {
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		String err = null;
		if(info == null || StringUtils.isEmpty(info.getId())){
			logger.error(err = "更新对象或更新对象ID为空！");
			throw new RuntimeException(err);
		}
		Report data = this.reportDao.load(Report.class, info.getId());
		if(data == null){
			logger.error(err = "报告［ID＝"+ info.getId() +"］数据不存在！");
			throw new RuntimeException(err);
		}
		if(StringUtils.isEmpty(info.getPlanDetail()) || StringUtils.isEmpty(info.getSummaryDetail())){
			logger.error(err = "计划或总结为空！");
			throw new RuntimeException(err);
		}
		if(data.getStatus() == Report.STATUS_AUDIT || data.getStatus() == Report.STATUS_LACK){
			logger.error(err = "报告已审阅或缺交状态，不允许修改！");
			throw new RuntimeException(err);
		}
		Set<ReportDetail> details = new HashSet<>();
		ReportDetail planDetail = this.buildReportDetail(data, info.getPlanId(), ReportDetail.TYPE_PLAN, info.getPlanDetail(), info.getPlanBusinessId()),
						summaryDetail = this.buildReportDetail(data, info.getSummaryId(), ReportDetail.TYPE_SUMMARY, info.getSummaryDetail(), info.getSummaryBusinessId()),
						supportDetail = this.buildReportDetail(data, info.getSupportId(), ReportDetail.TYPE_SUPPORT, info.getSupportDetail(), info.getSupportBusinessId()),
						suggetsionDetail = this.buildReportDetail(data, info.getSuggetsionId(), ReportDetail.TYPE_SUGGESTION, info.getSuggetsionDetail(), info.getSuggetsionBusinessId());
		if(planDetail != null) {
			if(StringUtils.isEmpty(info.getPlanId())) info.setPlanId(planDetail.getId());
			details.add(planDetail);
		}
		if(summaryDetail != null){
			if(StringUtils.isEmpty(info.getSummaryId())) info.setSummaryId(summaryDetail.getId());
			details.add(summaryDetail);
		}
		if(supportDetail != null) {
			if(StringUtils.isEmpty(info.getSupportId())) info.setSupportId(supportDetail.getId());
			details.add(supportDetail);
		}
		if(suggetsionDetail != null){
			if(StringUtils.isEmpty(info.getSuggetsionId())) info.setSuggetsionId(suggetsionDetail.getId());
			details.add(suggetsionDetail);
		}
		data.setDetails(details);
		data.setPostTime(new Date());
		data.setStatus(data.getLastPostTime().getTime() - data.getPostTime().getTime() > 0 ? Report.STATUS_POST : Report.STATUS_LATE);
		
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
		BeanUtils.copyProperties(data, info);
		info.setStatusName(this.loadStatusName(data.getStatus()));
		info.setTypeName(this.settingsService.loadTypeName(data.getType()));
		return info;
	}
	//构建报告明细。
	private ReportDetail buildReportDetail(Report report, String id, Integer type,String content,String[] businessId){
		if(report == null || StringUtils.isEmpty(content)) return null;
		ReportDetail data = null;
		if(report.getDetails() != null && report.getDetails().size() > 0){
			for(ReportDetail detail : report.getDetails()){
				if(detail == null) continue;
				if(detail.getId().equalsIgnoreCase(id) || detail.getType() == type){
					data = detail;
					break;
				}
			}
		}
		if(data == null){
			data = new ReportDetail();
			data.setId(UUID.randomUUID().toString());
			data.setReport(report);
			data.setCreateTime(new Date());
		}
		data.setType(type);
		data.setContent(content);
		if(businessId == null || businessId.length == 0) 
			data.setBusinesses(null);
		else {
			Set<Business> businesses = new HashSet<>();
			for(String bid : businessId){
				if(StringUtils.isEmpty(bid)) continue;
				Business b = this.businessDao.load(Business.class, bid);
				if(b != null) businesses.add(b);
			}
			data.setBusinesses(businesses);
		}
		return data;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled()) logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Report data = this.reportDao.load(Report.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i] +"］");
				this.reportDao.delete(data);
			}
		}
	}
	/*
	 * 新增日报。
	 * @see com.examw.oa.service.plan.IQuartzTask#addTaskDaily()
	 */
	@Override
	public void addTaskDaily() {
		if(logger.isDebugEnabled()) logger.debug("开始新增日报...");
		try{
			this.updateReportStatus(Settings.TYPE_DAILY);
		}catch(Exception e){
			logger.error("更新日报缺交状态时发生异常", e);
		}
		if(logger.isDebugEnabled()) logger.debug("查找设置表生成日报...");
		try{
			Date createTime = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createTime);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 58);
			this.createReport(Settings.TYPE_DAILY, createTime, calendar.getTime());
		}catch(Exception e){
			logger.error("新增日报时发生异常", e);
		}
		if(logger.isDebugEnabled()) logger.debug("新增日报结束！");
	}
	/*
	 * 新增周报。
	 * @see com.examw.oa.service.plan.IQuartzTask#addTaskWeek()
	 */
	@Override
	public void addTaskWeek() {
		if(logger.isDebugEnabled()) logger.debug("开始新增周报...");
		try{
			this.updateReportStatus(Settings.TYPE_WEEKLY);
		}catch(Exception e){
			logger.error("更新周报缺交状态时发生异常", e);
		}
		if(logger.isDebugEnabled()) logger.debug("查找设置表生成周报...");
		try{
			Date createTime = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(createTime);
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 4);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 58);
			this.createReport(Settings.TYPE_WEEKLY, createTime, calendar.getTime());
		}catch(Exception e){
			logger.error("新增周报时发生异常", e);
		}
		if(logger.isDebugEnabled()) logger.debug("新增周报结束！");
	}
	/*
	 * 新增月报。
	 * @see com.examw.oa.service.plan.IQuartzTask#addTaskMonth()
	 */
	@Override
	public void addTaskMonth() {
		if(logger.isDebugEnabled()) logger.debug("开始新增月报...");
		try{
			this.updateReportStatus(Settings.TYPE_MONTHLY);
		}catch(Exception e){
			logger.error("更新月报缺交状态时发生异常", e);
		}
		if(logger.isDebugEnabled()) logger.debug("查找设置表生成月报...");
		try{
			Date createTime = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createTime); 
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 58);
			this.createReport(Settings.TYPE_MONTHLY, createTime, calendar.getTime());
		}catch(Exception e){
			logger.error("新增月报时发生异常", e);
		}
		if(logger.isDebugEnabled()) logger.debug("新增月报结束！");
	}
	//更新报告状态。
	private void updateReportStatus(Integer type){
		if(logger.isDebugEnabled()) logger.debug("检查未提交的报告，将其变为缺交状态！");
		List<Report> list = this.reportDao.findReports(type, Report.STATUS_NONE);
		if(logger.isDebugEnabled()) logger.debug("找到未提交的报告：" + ((list == null || list.size() == 0) ? 0 : list.size()));
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				Report data = list.get(i);
				if(data == null) continue;
				try{
					if(logger.isDebugEnabled()) logger.debug("［"+ (i+1) +"］将报告［id="+ data.getId() +"］变为缺交状态！");
					data.setStatus(Report.STATUS_LACK);
				}catch(Exception e){
					logger.error("［"+ (i+1) +",id="+ data.getId() +"］变为为缺交状态时异常", e);
				}
			}
		}
	}
	//创建报告。
	private void createReport(Integer type,Date createTime,Date lastPostTime){
		List<Settings> settings = this.settingsService.findSettings(type);
		if(logger.isDebugEnabled()) logger.debug("共找到须生成报告员工："+ ((settings == null || settings.size() == 0) ? 0 : settings.size()));
		if(settings != null && settings.size() > 0){
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(lastPostTime);
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 5);
			
			for(int i = 0; i < settings.size(); i++){
				Settings data = settings.get(i);
				if(data == null || data.getEmployee() == null) continue;
				try{
					Report report = new Report();
					report.setId(UUID.randomUUID().toString());
					report.setEmployee(data.getEmployee());
					report.setType(type);
					report.setCreateTime(createTime);
					report.setPostTime(null);
					if((type == Settings.TYPE_WEEKLY) && (data.getEmployee().getPost() != null  && data.getEmployee().getPost().getChildren() != null && data.getEmployee().getPost().getChildren().size() > 0)){
						lastPostTime = calendar.getTime();
					}
					report.setLastPostTime(lastPostTime);
					report.setStatus(Report.STATUS_NONE);
					this.reportDao.save(report);
					if(logger.isDebugEnabled()) logger.debug("［"+ (i+1) +"］生成报告［type="+ type +"］！");
				}catch(Exception e){
					logger.error("［"+ (i+1) +",setting_id="+ data.getId() +",type="+ type +"］创建报告时异常", e);
				}
			}
		}
	}
}