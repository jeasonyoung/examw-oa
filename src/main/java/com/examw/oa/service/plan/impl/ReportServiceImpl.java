package com.examw.oa.service.plan.impl;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map; 
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IBusinessDao;
import com.examw.oa.dao.plan.IDetailDao;
import com.examw.oa.dao.plan.IReportDao; 
import com.examw.oa.domain.plan.Business;
import com.examw.oa.domain.plan.Detail;
import com.examw.oa.domain.plan.Report; 
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IReportService;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 计划报表服务接口。
 * @author lq.
 * @since 2014-07-02.
 */
public class ReportServiceImpl extends BaseDataServiceImpl<Report, ReportInfo> implements IReportService {
	private static Logger logger = Logger.getLogger(ReportServiceImpl.class);
	private IReportDao reportDao;
	private IDetailDao detailDao;
	private IBusinessDao businessDao;
	private Map<String, String> statusMap;
	private ISettingsService settingsService;
	/**
	 * 计划明细数据接口
	 */
	public void setDetailDao(IDetailDao detailDao) {
		this.detailDao = detailDao;
	}
	/**
	 * 设置系统业务数据接口
	 * @param reportDao
	 */
	public void setBusinessDao(IBusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	/**
	 * 设置状态集合
	 * @param reportDao
	 */
	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}
	/**
	 * 设置员工报表数据接口
	 * @param reportDao
	 */
	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/**
	 * 计划设置服务
	 */
	public void setSettingsService(ISettingsService settingsService) {
		this.settingsService = settingsService;
	}
	/*
	 * 业务系统设置函数
	 */
	public String[] buildDetail(Detail detail){
		List<String> list = new ArrayList<>();
		for(Business business :detail.getBusinesses()){
			if(business != null) list.add(business.getId());
		}
		return list.toArray(new String[0]);
	}
	/*
	 * 计划总结明细函数
	 */
	private Detail buildDetail(String id,Integer type,String content,String[] businessId){
		Detail data = StringUtils.isEmpty(id) ? null : this.detailDao.load(Detail.class, id);
		if(data == null){
			if(StringUtils.isEmpty(content)) return null;
			if(StringUtils.isEmpty(id)) id = UUID.randomUUID().toString();
			data = new Detail();
			data.setId(id);
			data.setCreateTime(new Date());
			data.setType(type);
		}
		data.setContent(content);
		//系统业务添加
		Set<Business> busSets = new HashSet<>();
			for(String busId : businessId){
				if(StringUtils.isEmpty(busId)) continue;
				Business business = this.businessDao.load(Business.class, busId);
				if(business != null)busSets.add(business);
		}
		data.setBusinesses(busSets);
		return data;
	}
	/*
	 * 报告任务函数
	 */
	public Integer Task(Integer typeLy,Integer none,Integer lack){
		if(logger.isDebugEnabled()) logger.debug("定时器调用生成日报记录...");
		//1.将未提交的报告变成缺交；
		List<Report> reports = this.reportDao.findReports(typeLy);
		if(reports != null && reports.size() > 0){
			if(logger.isDebugEnabled()) logger.debug("将未提交的报告["+reports.size()+"]变成缺交...");
			for(int i = 0; i < reports.size();i++){
				Report report = reports.get(i);
				if(report == null || report.getStatus() != none)continue;
				report.setStatus(lack);
				if(logger.isDebugEnabled()) logger.debug((i+1) + ".将未提交的报告["+report.getId()+"]变成缺交");
			}
		}
		return lack;
	}
	/*
	 * 添加报告任务函数
	 */
	public List<Settings> list(List<Settings> sett,Integer typeLy,Integer none,Date createTime,Date getTime){
		for(int k = 0; k < sett.size(); k++){
			try{
				Settings setting = sett.get(k);
				if(setting == null || setting.getEmployee() == null) continue;
				Report data = new Report();
				data.setId(UUID.randomUUID().toString());
				data.setEmployee(setting.getEmployee());
				data.setType(typeLy);
				data.setStatus(none);
				data.setCreateTime(createTime);
				data.setLastPostTime(getTime);
				this.reportDao.save(data);
			}catch(Exception e){
				logger.error((k+1) + ".生成报告时发生异常：" + e.getMessage());
				logger.error(e);
			}
		}
		
		return sett;
	}
	/*
	 *	数据查询 
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Report> find(ReportInfo info) {
		return this.reportDao.findReports(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected ReportInfo changeModel(Report data) {
		if(data == null) return null;
		ReportInfo info = new ReportInfo();
		BeanUtils.copyProperties(data, info, new String[]{"type"});
		if(data.getEmployee() != null){
			info.setEmployeeId(data.getEmployee().getId());
			info.setEmployeeName(data.getEmployee().getName());
		}
		//计划总结明细 
		if(data.getDetails()!=null){
			for(Detail detail : data.getDetails()){
				if(detail == null) continue;
				if(detail.getType() == Detail.TYPE_PLAN){
					info.setPlanId(detail.getId());
					info.setPlanDetail(detail.getContent());
					//系统业务
					String[] deta=this.buildDetail(detail);
					info.setBusinessId(deta);
					continue;
				}
				if(detail.getType() == Detail.TYPE_SUMMARY){
					info.setSummaryId(detail.getId());
					info.setSummaryDetail(detail.getContent());
					String[] deta=this.buildDetail(detail);
					info.setBusinessId(deta);
					continue;
				}
				if(detail.getType() == Detail.TYPE_SUPPORT){
					info.setSupportId(detail.getId());
					info.setSupportDetail(detail.getContent());
					String[] deta=this.buildDetail(detail);
					info.setBusinessId(deta);
					continue;
				}
				if(detail.getType() == Detail.TYPE_SUGGESTIONS){
					info.setSuggetsionsId(detail.getId());
					info.setSuggetsionsDetail(detail.getContent());
					String[] deta=this.buildDetail(detail);
					info.setBusinessId(deta);
					continue;
				}
			}
		}
		info.setTypeName(this.settingsService.loadTypeName(data.getType()));
		return info;
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(ReportInfo info) {
		return this.reportDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public ReportInfo update(ReportInfo info) {
		if(info == null) return null;
		//计划总结明细更新
		Detail planDetail = this.buildDetail(info.getPlanId(), Detail.TYPE_PLAN, info.getPlanDetail(),info.getBusinessId()),
			   summaryDetail = this.buildDetail(info.getSummaryId(), Detail.TYPE_SUMMARY, info.getSummaryDetail(),info.getBusinessId()),
			   supportDetail = this.buildDetail(info.getSupportId(), Detail.TYPE_SUPPORT, info.getSupportDetail(),info.getBusinessId()),
			   suggetDetial = this.buildDetail(info.getSuggetsionsId(), Detail.TYPE_SUGGESTIONS, info.getSuggetsionsDetail(),info.getBusinessId());
		info.setPostTime(new Date());
		Report data = this.reportDao.load(Report.class, info.getId());
		if(data == null){
			throw new RuntimeException("报告不存在！"+ info.getId());
		}
		Set<Detail> details = new HashSet<>();
		if(planDetail != null){
			info.setPlanId(planDetail.getId());
			details.add(planDetail);
		}
		if(summaryDetail != null){
			info.setSummaryId(summaryDetail.getId());
			details.add(summaryDetail);
		}
		if(supportDetail != null){
			info.setSupportId(supportDetail.getId());
			details.add(supportDetail);
		}
		if(suggetDetial != null){
			info.setSuggetsionsId(suggetDetial.getId());
			details.add(suggetDetial);
		}
		data.setDetails(details);
		
		BeanUtils.copyProperties(info, data, new String[]{"createTime","lastPostTime","type"});
		if(info.getStatus()!=null){
			data.setStatus(Report.STATUS_AUDIT);
		}else{
			data.setStatus((data.getLastPostTime().getTime() - data.getPostTime().getTime() > 0) ? Report.STATUS_POST : Report.STATUS_LATE);
		}
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
		}
		info.setType(data.getType());
		info.setTypeName(this.settingsService.loadTypeName(data.getType()));
		info.setCreateTime(data.getCreateTime());
		info.setLastPostTime(data.getLastPostTime());
		return info;
	}
	/*
	 * 数据删除
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
	}
	/*
	 * 加载状态名称
	 * @see com.examw.oa.service.plan.IReportService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(statusMap==null || status==null)
			return null;
			return statusMap.get(status.toString());
	}
	/*
	 * 日报
	 * @see com.examw.oa.service.plan.IReportService#taskDaily()
	 */
	@Override
	public void addTaskDaily() {
		Integer task=this.Task(Report.TYPE_DAILY, Report.STATUS_NONE, Report.STATUS_LACK);
		if(task == null) return;
		//2.根据设置生成新的报告；
		List<Settings> settings = this.settingsService.findSettings(Settings.TYPE_DAY);
		if(settings == null || settings.size() == 0){
			if(logger.isDebugEnabled()) logger.debug("未查询到日报设置");
		}
		if(logger.isDebugEnabled()) logger.debug("开始生成日报记录...");
		Date create_time = new Date(),last_post_time = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(last_post_time);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		last_post_time = calendar.getTime();
		list(settings, Report.TYPE_DAILY, Report.STATUS_NONE,create_time,last_post_time);
	}
	/*
	 * 周报
	 * @see com.examw.oa.service.plan.IReportService#taskWeek()
	 */
	@Override
	public void addTaskWeek() {
		Integer task=this.Task(Report.TYPE_WEEKLY, Report.STATUS_NONE, Report.STATUS_LACK);
		if(task == null) return;
		//2.根据设置生成新的报告；
		List<Settings> settings = this.settingsService.findSettings(Settings.TYPE_WEEK);
		if(settings == null || settings.size() == 0){
			if(logger.isDebugEnabled()) logger.debug("未查询到周报设置");
			return;
		}
		if(logger.isDebugEnabled()) logger.debug("开始生成周报记录...");
		Date create_time = new Date();
		Calendar calendar = Calendar.getInstance();
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
		  dayofweek = 7;
		  calendar.add(Calendar.DATE, dayofweek + 5);
		  calendar.set(Calendar.HOUR_OF_DAY, 23);
		  calendar.set(Calendar.MINUTE, 59);
		  calendar.set(Calendar.SECOND, 59); // 每周星期五 
		  list(settings, Report.TYPE_WEEKLY, Report.STATUS_NONE,create_time,calendar.getTime());
	}
	/*
	 * 月报
	 * @see com.examw.oa.service.plan.IReportService#taskMonth()
	 */
	@Override
	public void addTaskMonth() {
		Integer task=this.Task(Report.TYPE_MONTHLY, Report.STATUS_NONE, Report.STATUS_LACK);
		if(task == null) return;
		//2.根据设置生成新的报告；
		List<Settings> settings = this.settingsService.findSettings(Settings.TYPE_MONTH);
		if(settings == null || settings.size() == 0){
			if(logger.isDebugEnabled()) logger.debug("未查询到月报设置");
			return;
		}
		if(logger.isDebugEnabled()) logger.debug("开始生成月报记录...");
		Date create_time = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));//本月最后一天
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		list(settings, Report.TYPE_MONTHLY, Report.STATUS_NONE,create_time,calendar.getTime());
	}
	/*
	 * 状态集合
	 * @see com.examw.oa.service.plan.IReportService#getStatusMap()
	 */
	@Override
	public Map<String, String> getStatusMap() {
		return statusMap;
	}
}