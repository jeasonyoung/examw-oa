package com.examw.oa.service.plan.impl;
 

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map; 
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IReportDao; 
import com.examw.oa.domain.plan.Report; 
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IReportService;
import com.examw.oa.service.plan.ISettingsService;

public class ReportServiceImpl extends BaseDataServiceImpl<Report, ReportInfo> implements IReportService {
	private static Logger logger = Logger.getLogger(ReportServiceImpl.class);
	private IReportDao reportDao;
	private ISettingsService settingsService;
	private Map<Integer, String> statusMap;
	/**
	 * 设置员工报表数据接口
	 * @param reportDao
	 */
	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	public void setSettingsService(ISettingsService settingsService) {
		this.settingsService = settingsService;
	}
	/**
	 * 设置状态集合
	 * @param statusMap
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		this.statusMap = statusMap;
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
		Report data = this.reportDao.load(Report.class, info.getId());
		if(data == null){
			throw new RuntimeException("报告不存在！"+ info.getId());
		}
		info.setPostTime(new Date());
		BeanUtils.copyProperties(info, data, new String[]{"createTime","lastPostTime","type"});
		data.setStatus((data.getLastPostTime().getTime() - data.getPostTime().getTime() > 0) ? Report.STATUS_POST : Report.STATUS_LATE);
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
		}
		info.setTypeName(this.settingsService.loadTypeName(data.getType()));
		return info;
	}
	/*
	 * 数据删除
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Report  data = this.reportDao.load(Report.class, ids[i]);
			if(data != null) this.reportDao.delete(data);
		}
	}
	/*
	 * 加载状态名称
	 * @see com.examw.oa.service.plan.IReportService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(this.statusMap == null || status == null) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 日报
	 * @see com.examw.oa.service.plan.IReportService#taskDaily()
	 */
	@Override
	public void addTaskDaily() {
		if(logger.isDebugEnabled()) logger.debug("定时器调用生成日报记录...");
		//1.将未提交的报告变成缺交；
		List<Report> reports = this.reportDao.findReports(Report.TYPE_DAILY);
		if(reports != null && reports.size() > 0){
			if(logger.isDebugEnabled()) logger.debug("将未提交的报告["+reports.size()+"]变成缺交...");
			for(int i = 0; i < reports.size();i++){
				Report report = reports.get(i);
				if(report == null || report.getStatus() != Report.STATUS_NONE)continue;
				report.setStatus(Report.STATUS_LACK);
				if(logger.isDebugEnabled()) logger.debug((i+1) + ".将未提交的报告["+report.getId()+"]变成缺交");
			}
		}
		//2.根据设置生成新的报告；
		List<Settings> settings = this.settingsService.findSettings(Settings.TYPE_DAY);
		if(settings == null || settings.size() == 0){
			if(logger.isDebugEnabled()) logger.debug("未查询到日报设置");
			return;
		}
		if(logger.isDebugEnabled()) logger.debug("开始生成日报记录...");
		Date create_time = new Date(),last_post_time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(last_post_time);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		last_post_time = cal.getTime();
		
		List<Report> list = new ArrayList<>();
		for(int k = 0; k < settings.size(); k++){
			try{
				Settings setting = settings.get(k);
				if(setting == null || setting.getEmployee() == null) continue;
				Report data = new Report();
				data.setId(UUID.randomUUID().toString());
				data.setEmployee(setting.getEmployee());
				data.setType(Report.TYPE_DAILY);
				data.setStatus(Report.STATUS_NONE);
				data.setCreateTime(create_time);
				data.setLastPostTime(last_post_time);
				list.add(data);
			}catch(Exception e){
				logger.error((k+1) + ".生成报告时发生异常：" + e.getMessage());
				logger.error(e);
			}
		}
		//保存数据。
		this.reportDao.saveReports(list);
	}
	/*
	 * 周报
	 * @see com.examw.oa.service.plan.IReportService#taskWeek()
	 */
	@Override
	public void addTaskWeek() {
		if(logger.isDebugEnabled()) logger.debug("定时器调用生成周报记录...");
		//1.将未提交的报告变成缺交；
		List<Report> reports = this.reportDao.findReports(Report.TYPE_WEEKLY);
		if(reports != null && reports.size() > 0){
			if(logger.isDebugEnabled()) logger.debug("将未提交的报告["+reports.size()+"]变成缺交...");
			for(int i = 0; i < reports.size();i++){
				Report report = reports.get(i);
				if(report == null || report.getStatus() != Report.STATUS_NONE)continue;
				report.setStatus(Report.STATUS_LACK);
				if(logger.isDebugEnabled()) logger.debug((i+1) + ".将未提交的报告["+report.getId()+"]变成缺交");
			}
		}
		//2.根据设置生成新的报告；
		List<Settings> settings = this.settingsService.findSettings(Settings.TYPE_WEEK);
		if(settings == null || settings.size() == 0){
			if(logger.isDebugEnabled()) logger.debug("未查询到周报设置");
			return;
		}
		if(logger.isDebugEnabled()) logger.debug("开始生成周报记录...");
		Date create_time = new Date();
		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+5); // 每周星期五 
		List<Report> list = new ArrayList<>();
		for(int k = 0; k < settings.size(); k++){
			try{
				Settings setting = settings.get(k);
				if(setting == null || setting.getEmployee() == null) continue;
				Report data = new Report();
				data.setId(UUID.randomUUID().toString());
				data.setEmployee(setting.getEmployee());
				data.setType(Report.TYPE_WEEKLY);
				data.setStatus(Report.STATUS_NONE);
				data.setCreateTime(create_time);
				data.setLastPostTime(c.getTime());
				list.add(data);
			}catch(Exception e){
				logger.error((k+1) + ".生成报告时发生异常：" + e.getMessage());
				logger.error(e);
			}
		}
		//保存数据。
		this.reportDao.saveReports(list);
		
	}
	/*
	 * 月报
	 * @see com.examw.oa.service.plan.IReportService#taskMonth()
	 */
	@Override
	public void addTaskMonth() {
		if(logger.isDebugEnabled()) logger.debug("定时器调用生成月报记录...");
		//1.将未提交的报告变成缺交；
		List<Report> reports = this.reportDao.findReports(Report.TYPE_MONTHLY);
		if(reports != null && reports.size() > 0){
			if(logger.isDebugEnabled()) logger.debug("将未提交的报告["+reports.size()+"]变成缺交...");
			for(int i = 0; i < reports.size();i++){
				Report report = reports.get(i);
				if(report == null || report.getStatus() != Report.STATUS_NONE)continue;
				report.setStatus(Report.STATUS_LACK);
				if(logger.isDebugEnabled()) logger.debug((i+1) + ".将未提交的报告["+report.getId()+"]变成缺交");
			}
		}
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
		List<Report> list = new ArrayList<>();
		for(int k = 0; k < settings.size(); k++){
			try{
				Settings setting = settings.get(k);
				if(setting == null || setting.getEmployee() == null) continue;
				Report data = new Report();
				data.setId(UUID.randomUUID().toString());
				data.setEmployee(setting.getEmployee());
				data.setType(Report.TYPE_MONTHLY);
				data.setStatus(Report.STATUS_NONE);
				data.setCreateTime(create_time);
				data.setLastPostTime(calendar.getTime());
				list.add(data);
			}catch(Exception e){
				logger.error((k+1) + ".生成报告时发生异常：" + e.getMessage());
				logger.error(e);
			}
		}
		//保存数据。
		this.reportDao.saveReports(list);
		
	}
}