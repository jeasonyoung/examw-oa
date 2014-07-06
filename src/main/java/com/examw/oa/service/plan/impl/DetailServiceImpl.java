package com.examw.oa.service.plan.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import com.examw.oa.dao.plan.IDetailDao;
import com.examw.oa.domain.plan.Business;
import com.examw.oa.domain.plan.Detail;
import com.examw.oa.model.plan.DetailInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IDetailService;
/**
 * 计划总结明细服务接口
 * @author lq
 * @since 2014-07-01
 *
 */
public class DetailServiceImpl extends BaseDataServiceImpl<Detail, DetailInfo> implements IDetailService {
	private IDetailDao detailDao;
	private Map<Integer, String> typeMap;
	/**
	 * 计划总结明细接口
	 * @param detailDao
	 */
	public void setDetailDao(IDetailDao detailDao) {
		this.detailDao = detailDao;
	}
	/**
	 * 类型集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}
	/*
	 * 查询数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Detail> find(DetailInfo info) {
		return this.detailDao.findDetails(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected DetailInfo changeModel(Detail data) {
		if(data == null)return null;
		DetailInfo info=new DetailInfo();
		BeanUtils.copyProperties(data, info);
		//业务系统
		if(data.getBusinesses() != null){
				info.setBusIdPlan(info.getBusIdPlan());
				info.setBusIdSummary(info.getBusIdSummary());
				info.setBusIdSupport(info.getBusIdSupport());
				info.setBusIdsuggetsion(info.getBusIdsuggetsion());
			}
		return info;
	}
	/*
	 * 统计数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(DetailInfo info) {
		return this.detailDao.total(info);
	}
	/*
	 * 更新数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public DetailInfo update(DetailInfo info) {
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
		//业务系统
		Set<Business> business = new HashSet<>();
		data.setBusinesses(business);
		if(isAdded) this.detailDao.save(data);
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
			Detail data = this.detailDao.load(Detail.class, ids[i]);
			if(data != null) this.detailDao.delete(data);
		}
	}
	/*
	 * 加载类型集合
	 * @see com.examw.oa.service.plan.IDetailService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
}