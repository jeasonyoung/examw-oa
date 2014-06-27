package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.dao.plan.IReportDao;
import com.examw.oa.domain.org.Employee;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IReportService;

public class ReportServiceImpl extends BaseDataServiceImpl<Report, ReportInfo> implements IReportService {
	private IReportDao reportDao;
	private IEmployeeDao employeeDao;
	private Map<Integer, String> typeMap;
	private Map<Integer, String> statusMap;
	/**
	 * 设置员工报表数据接口
	 * @param reportDao
	 */
	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/**
	 * 设置员工数据接口
	 * @param employeeDao
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	/**
	 * 设置类型集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
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
		info.setTypeName(this.loadTypeNameFromValue(data.getType()));
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
		boolean isAdded = false;
		Report data = StringUtils.isEmpty(info.getId()) ? null : this.reportDao.load(Report.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());			
			}
			data = new Report();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEmployeeId()))){
				Employee e = this.employeeDao.load(Employee.class, info.getEmployeeId());
				if(e != null) data.setEmployee(e);	
		}
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
		}
		if(isAdded)this.reportDao.save(data);
		info.setTypeName(this.loadTypeNameFromValue(data.getType()));
		return info;
	}
	/*
	 * 转换为汉字
	 */
	private String loadTypeNameFromValue(Integer type){
		if(type ==  null) return null;
		StringBuilder sb = new StringBuilder();
		if((type & Report.TYPE_DAILY) == Report.TYPE_DAILY){
			sb.append(",").append(this.loadTypeName(Report.TYPE_DAILY));
		}
		if((type & Report.TYPE_WEEKLY) == Report.TYPE_WEEKLY){
			sb.append(",").append(this.loadTypeName(Report.TYPE_WEEKLY));
		}
		if((type & Report.TYPE_MONTHLY) == Report.TYPE_MONTHLY){
			sb.append(",").append(this.loadTypeName(Report.TYPE_MONTHLY));
		}
		if(sb.length() > 0) 
			return sb.substring(1);
		return null;
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
	 * 加载类型名称
	 * @see com.examw.oa.service.plan.IReportService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
}