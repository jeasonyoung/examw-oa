package com.examw.oa.dao.check.impl;

import java.util.List;
import com.examw.oa.dao.check.ICatalogDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.check.Catalog;
import com.examw.oa.model.check.CatalogInfo;
/**
 * 奖惩类别设置数据操作接口实现类
 * @author Administrator
 *
 */
public class CatalogDaoImpl extends BaseDaoImpl<Catalog> implements ICatalogDao{
	/*
	 * 数据查询。
	 * @see com.examw.oa.dao.check.ICatalogDao#findCatalogs(com.examw.oa.model.check.CatalogInfo)
	 */
	@Override
	public List<Catalog> findCatalogs(CatalogInfo info) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 统计数据
	 * @see com.examw.oa.dao.check.ICatalogDao#total(com.examw.oa.model.check.CatalogInfo)
	 */
	@Override
	public Long total(CatalogInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

}
