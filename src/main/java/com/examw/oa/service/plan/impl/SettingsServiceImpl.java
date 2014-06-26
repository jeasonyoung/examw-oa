package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
 * 员工报表服务接口。
 * @author lq.
 * @since 2014-06-24.
 */
public class SettingsServiceImpl extends BaseDataServiceImpl<Settings, SettingsInfo> implements ISettingsService {
	private ISettingsDao settingsDao;
	private IEmployeeDao employeeDao;
	private Map<Integer, String> typeMap;
	/**
	 * 设置类型集合。
	 * @param typeMap
	 * 类型集合。
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}
	/**
	 * 设置员工报表数据接口。
	 * @param settingsDao
	 * 员工报表数据接口。
	 */
	public void setSettingsDao(ISettingsDao settingsDao) {
		this.settingsDao = settingsDao;
	}
	
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Settings> find(SettingsInfo info) {
		return this.settingsDao.findSettings(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected SettingsInfo changeModel(Settings data) {
		if(data == null) return null;
		SettingsInfo info = new SettingsInfo();
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
	protected Long total(SettingsInfo info) {
		return this.settingsDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public SettingsInfo update(SettingsInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Settings data = StringUtils.isEmpty(info.getId()) ? null : this.settingsDao.load(Settings.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());			
			}
			data = new Settings();
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
		}
		
		if(isAdded)this.settingsDao.save(data);
		info.setTypeName(this.loadTypeNameFromValue(data.getType()));
		return info;
	}
	/*
	 * 转换为数字
	 */
	private Integer calTypeValue(Integer[] types){
		Integer result = null;
		if(types == null || types.length == 0) return result;
		for(int i = 0; i < types.length; i++){
			if(types[i] == null)continue;
			if(result == null) 
				result = types[i];
			else
				result |= types[i];
		}
		return result;
	}
	/*
	 * 转换为汉字
	 */
	private String loadTypeNameFromValue(Integer type){
		if(type ==  null) return null;
		StringBuilder sb = new StringBuilder();
		if((type & Settings.TYPE_DAY) == Settings.TYPE_DAY){
			sb.append(",").append(this.loadTypeName(Settings.TYPE_DAY));
		}
		if((type & Settings.TYPE_WEEK) == Settings.TYPE_WEEK){
			sb.append(",").append(this.loadTypeName(Settings.TYPE_WEEK));
		}
		if((type & Settings.TYPE_MONTH) == Settings.TYPE_MONTH){
			sb.append(",").append(this.loadTypeName(Settings.TYPE_MONTH));
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
			Settings data = this.settingsDao.load(Settings.class, ids[i]);
			if(data != null) this.settingsDao.delete(data);
		}	
	}
	/*
	 * 加载类型名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
}