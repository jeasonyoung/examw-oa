package com.examw.oa.service.plan.impl;

import java.util.List;

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

	@Override
	protected List<Settings> find(SettingsInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SettingsInfo changeModel(Settings data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Long total(SettingsInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettingsInfo update(SettingsInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		
	}
}