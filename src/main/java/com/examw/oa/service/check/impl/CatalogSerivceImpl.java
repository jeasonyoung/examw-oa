package com.examw.oa.service.check.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.check.ICatalogDao;
import com.examw.oa.domain.check.Catalog;
import com.examw.oa.model.check.CatalogInfo;
import com.examw.oa.service.check.ICatalogService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 奖惩类别设置服务接口实现。
 * @author lq.
 * @since 2014-07-30.
 */
public class CatalogSerivceImpl extends BaseDataServiceImpl<Catalog,CatalogInfo> implements ICatalogService {
	private static final Logger logger = Logger.getLogger(CatalogSerivceImpl.class);
	private ICatalogDao catalogDao;
	/**
	 * 设置奖惩类别设置数据接口
	 * @param catalogDao
	 * 奖惩类别设置数据接口
	 */
	public void setCatalogDao(ICatalogDao catalogDao) {
		if(logger.isDebugEnabled())logger.debug("注入奖惩类别设置数据接口...");
		this.catalogDao = catalogDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Catalog> find(CatalogInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		return this.catalogDao.findCatalogs(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected CatalogInfo changeModel(Catalog data) {
		if(logger.isDebugEnabled())logger.debug("类型转换...");
		return null;
	}
	/*
	 * 统计数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(CatalogInfo info) {
		if(logger.isDebugEnabled())logger.debug("统计数据...");
		return this.catalogDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public CatalogInfo update(CatalogInfo info) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Catalog data = this.catalogDao.load(Catalog.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i]+"］");
				this.catalogDao.delete(data);
			} 
		}	
	}
}