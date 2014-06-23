package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IBusinessDao;
import com.examw.oa.domain.plan.Business;
import com.examw.oa.model.plan.BusinessInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IBusinessService;

public class BusinessServiceImpl extends BaseDataServiceImpl<Business,BusinessInfo> implements IBusinessService {
	private IBusinessDao businessDao;
	/**
	 * 设置业务系统数据接口。
	 * @param businessDao
	 * 业务系统数据接口。
	 */
	public void setBusinessDao(IBusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	/*
	 * 查询数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Business> find(BusinessInfo info) {
		return this.businessDao.findBusiness(info);
	}
	/*
	 * (类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected BusinessInfo changeModel(Business data) {
		if(data == null)return null;
		BusinessInfo info=new BusinessInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/*
	 * 统计查询数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(BusinessInfo info) {
		return this.businessDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public BusinessInfo update(BusinessInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Business data = StringUtils.isEmpty(info.getId()) ? null : this.businessDao.load(Business.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new Business();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);	
		if(isAdded) this.businessDao.save(data);
		return info;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Business data = this.businessDao.load(Business.class, ids[i]);
			if(data != null) this.businessDao.delete(data);
		}
	}
}