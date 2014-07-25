package com.examw.oa.service.plan.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
 
import com.examw.oa.dao.org.IEmployeeDao;
import com.examw.oa.dao.plan.ISettingsDao; 
import com.examw.oa.domain.org.Employee;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.SettingsInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 员工报告设置服务接口实现类。
 * @author lq.
 * @since 2014-06-24.
 */
public class SettingsServiceImpl extends BaseDataServiceImpl<Settings, SettingsInfo> implements ISettingsService {
	private static final Logger logger = Logger.getLogger(SettingsServiceImpl.class);
	private ISettingsDao settingsDao;
	private IEmployeeDao employeeDao;
	private Map<Integer, String> typeMap;
	/**
	 * 设置员工计划总结数据接口。
	 * @param settingsDao
	 * 员工计划总结数据接口。
	 */
	public void setSettingsDao(ISettingsDao settingsDao) {
		if(logger.isDebugEnabled())logger.debug("注入员工计划总结数据接口...");
		this.settingsDao = settingsDao;
	}
	/**
	 * 设置员工数据接口。
	 * @param settingsDao
	 * 员工信息数据接口。
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		if(logger.isDebugEnabled())logger.debug("注入员工数据接口...");
		this.employeeDao = employeeDao;
	}
	/**
	 * 设置类型集合。
	 * @param typeMap
	 * 类型集合。
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		if(logger.isDebugEnabled())logger.debug("注入类型集合...");
		this.typeMap = typeMap;
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Settings> find(SettingsInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据查询...");
		return this.settingsDao.findSettings(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected SettingsInfo changeModel(Settings data) {
		if(logger.isDebugEnabled())logger.debug("类型转换...");
		if(data == null) return null;
		SettingsInfo info = new SettingsInfo();
		BeanUtils.copyProperties(data, info, new String[]{"type"});
		if(data.getEmployee() != null){
			info.setEmployeeId(data.getEmployee().getId());
			info.setEmployeeName(data.getEmployee().getName());
			
			if(data.getEmployee().getDepartment() != null){
				info.setDeptId(data.getEmployee().getDepartment().getId());
				info.setDeptName(data.getEmployee().getDepartment().getName());
			}
		}
		info.setType(this.loadTypeValue(data.getType()));
		info.setTypeName(this.loadTypeName(data.getType()));
		return info;
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(SettingsInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		return this.settingsDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public SettingsInfo update(SettingsInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据更新...");
		if(info == null) return null;
		boolean isAdded = false;
		if(StringUtils.isEmpty(info.getId())) info.setId(info.getEmployeeId());
		Settings data = StringUtils.isEmpty(info.getId()) ? null : this.settingsDao.load(Settings.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) info.setId(info.getEmployeeId());
			data = new Settings();
			info.setCreateTime(new Date());	
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data, new String[]{"type"});
		data.setType(this.calTypeValue(info.getType()));
		if(!StringUtils.isEmpty(info.getEmployeeId()) && (data.getEmployee() == null || !data.getEmployee().getId().equalsIgnoreCase(info.getEmployeeId()))){
			Employee e = this.employeeDao.load(Employee.class, info.getEmployeeId());
			if(e != null) data.setEmployee(e);	
		}
		if(data.getEmployee() != null){
			info.setEmployeeName(data.getEmployee().getName());
			if(data.getEmployee().getDepartment() != null){
				info.setDeptId(data.getEmployee().getDepartment().getId());
				info.setDeptName(data.getEmployee().getDepartment().getName());
			}
		}
		if(isAdded)this.settingsDao.save(data);
		info.setTypeName(this.loadTypeName(data.getType()));
		return info;
	}
	/*
	 * 数据删除
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Settings data = this.settingsDao.load(Settings.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i]+"］");
				this.settingsDao.delete(data);
			} 
		}	
	}
	/*
	 * 位运算类型。
	 */
	private Integer calTypeValue(String[] types){
		Integer result = null;
		if(types == null || types.length == 0) return result;
		for(int i = 0; i < types.length; i++){
			if(StringUtils.isEmpty(types[i]))continue;
			if(result == null) 
				result =  new Integer(types[i]);
			else
				result |= new Integer(types[i]);
		}
		return result;
	}
	//加载位类型包含的值。
	private String[] loadTypeValue(Integer type){
		if(type == null) return null;
		List<String> list = new ArrayList<>();
		if((type & Settings.TYPE_DAILY) == Settings.TYPE_DAILY){
			list.add(Settings.TYPE_DAILY.toString());
		}
		if((type & Settings.TYPE_WEEKLY) == Settings.TYPE_WEEKLY){
			list.add(Settings.TYPE_WEEKLY.toString());
		}
		if((type & Settings.TYPE_MONTHLY) == Settings.TYPE_MONTHLY){
			list.add(Settings.TYPE_MONTHLY.toString());
		}
		return list.toArray(new String[0]);
	}
	/*
	 * 加载位类型名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载类型［"+type+"］名称...");
		if(this.typeMap == null || type == null) return null; 
		StringBuilder sb = new StringBuilder();
		if((type & Settings.TYPE_DAILY) == Settings.TYPE_DAILY){
			sb.append(",").append(this.typeMap.get(Settings.TYPE_DAILY));
		}
		if((type & Settings.TYPE_WEEKLY) == Settings.TYPE_WEEKLY){
			sb.append(",").append(this.typeMap.get(Settings.TYPE_WEEKLY));
		}
		if((type & Settings.TYPE_MONTHLY) == Settings.TYPE_MONTHLY){
			sb.append(",").append(this.typeMap.get(Settings.TYPE_MONTHLY));
		}
		if(sb.length() > 0) 
			return sb.substring(1);
		return null;
	}
	/*
	 * 根据类型查值
	 * @see com.examw.oa.service.plan.ISettingsService#findSettings(java.lang.Integer)
	 */
	@Override
	public List<Settings> findSettings(Integer type) {
		  if(type == null) return null;
		  return this.settingsDao.findSettings(type);
	}
}