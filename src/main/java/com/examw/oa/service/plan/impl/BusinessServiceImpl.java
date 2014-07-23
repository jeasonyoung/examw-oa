package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IBusinessDao;
import com.examw.oa.domain.plan.Business;
import com.examw.oa.model.plan.BusinessInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IBusinessService;
/**
 * 业务系统服务接口。
 * @author lq.
 * @since 2014-06-24.
 */
public class BusinessServiceImpl extends BaseDataServiceImpl<Business,BusinessInfo> implements IBusinessService {
	private static final Logger logger = Logger.getLogger(BusinessServiceImpl.class);
	private IBusinessDao businessDao;
	private Map<Integer, String> statusMap;
	/**
	 * 设置业务系统数据接口。
	 * @param businessDao
	 * 业务系统数据接口。
	 */
	public void setBusinessDao(IBusinessDao businessDao) {
		if(logger.isDebugEnabled()) logger.debug("注入业务系统数据接口...");
		this.businessDao = businessDao;
	}
	/**
	 * 设置状态集合。
	 * @param statusMap
	 * 状态集合。
	 */
	public void setStatusMap(Map<Integer, String> statusMap) {
		if(logger.isDebugEnabled()) logger.debug("注入状态集合...");
		this.statusMap = statusMap;
	}
	/*
	 * 加载状态名称。
	 * @see com.examw.oa.service.org.IEmployeeService#loadStatusName(java.lang.Integer)
	 */
	@Override
	public String loadStatusName(Integer status) {
		if(logger.isDebugEnabled()) logger.debug("加载状态［"+status+"］名称...");
		if(this.statusMap == null || status == null) return null;
		return this.statusMap.get(status);
	}
	/*
	 * 查询数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Business> find(BusinessInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.businessDao.findBusiness(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected BusinessInfo changeModel(Business data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null)return null;	
		BusinessInfo info=new BusinessInfo();
		BeanUtils.copyProperties(data, info);
		info.setStatusName(this.loadStatusName(info.getStatus()));
		return info;
	}
	/*
	 * 查询数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(BusinessInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.businessDao.total(info);
	}
	/*
	 * 数据更新
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public BusinessInfo update(BusinessInfo info) {
		if(logger.isDebugEnabled()) logger.debug("数据更新...");
		if(info == null) return null;
		boolean isAdded = false;
		Business data = StringUtils.isEmpty(info.getId()) ? null : this.businessDao.load(Business.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) info.setId(UUID.randomUUID().toString()); 
			data = new Business();
			info.setCreateTime(new Date());
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(isAdded) this.businessDao.save(data);
		info.setStatusName(this.loadStatusName(info.getStatus()));
		return info;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled()) logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Business data = this.businessDao.load(Business.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled())logger.debug("删除数据［"+ids[i]+"］");
				this.businessDao.delete(data);
			} 
		}
	}
}