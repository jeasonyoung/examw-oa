package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.plan.IDetailDao;
import com.examw.oa.domain.plan.Detail;
import com.examw.oa.model.plan.DetailInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IDetailService;
/**
 * 计划总结明细服务接口
 * @author lq
 * @since 2014-07-01
 */
public class DetailServiceImpl extends BaseDataServiceImpl<Detail, DetailInfo> implements IDetailService {
	private static Logger logger = Logger.getLogger(DetailServiceImpl.class);
	private IDetailDao detailDao;
	private Map<Integer, String> typeMap;
	/**
	 * 计划总结明细接口
	 * @param detailDao
	 */
	public void setDetailDao(IDetailDao detailDao) {
		if(logger.isDebugEnabled())logger.debug("注入计划总结明细数据接口...");
		this.detailDao = detailDao;
	}
	/**
	 * 类型集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		if(logger.isDebugEnabled()) logger.debug("注入类型集合...");
		this.typeMap = typeMap;
	}
	/*
	 * 查询数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Detail> find(DetailInfo info) {
		if(logger.isDebugEnabled()) logger.debug("数据查询...");
		return this.detailDao.findDetails(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected DetailInfo changeModel(Detail data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null)return null;
		DetailInfo info=new DetailInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/*
	 * 查询数据统计
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(DetailInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.detailDao.total(info);
	}
	/*
	 * 更新数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public DetailInfo update(DetailInfo info) {
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		if(info == null) return null;
		boolean isAdded = false;
		Detail data = StringUtils.isEmpty(info.getId()) ? null : this.detailDao.load(Detail.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new Detail();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(isAdded) this.detailDao.save(data);
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
			Detail data = this.detailDao.load(Detail.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled())logger.debug("删除员工［"+ids[i]+"］");
				this.detailDao.delete(data);
			}
		}
	}
	/*
	 * 加载类型集合
	 * @see com.examw.oa.service.plan.IDetailService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载类型［"+type+"］名称...");
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
}