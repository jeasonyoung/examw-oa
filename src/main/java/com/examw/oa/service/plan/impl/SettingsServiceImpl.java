package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import com.examw.oa.dao.plan.ISettingsDao;
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
	/**
	 * 设置员工报表数据接口。
	 * @param postDao
	 * 员工报表数据接口。
	 */
	public void setSettingsDao(ISettingsDao settingsDao) {
		this.settingsDao = settingsDao;
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
		BeanUtils.copyProperties(data, info);
		if(data.getEmployee() != null){
			info.setEmployeeId(data.getEmployee().getId());
			info.setEmployeeName(data.getEmployee().getName());
		}
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
				info.setId(info.getEmployeeId());
				info.setCreateTime(new Date());
				//位运算
				int type=info.getType();
				type=type<<2;
				info.setType(type);
			}
			data = new Settings();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(isAdded)this.settingsDao.save(data);
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
			Settings data = this.settingsDao.load(Settings.class, ids[i]);
			if(data != null) this.settingsDao.delete(data);
		}	
	}
}