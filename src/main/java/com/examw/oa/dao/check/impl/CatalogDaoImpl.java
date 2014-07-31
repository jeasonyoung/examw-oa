package com.examw.oa.dao.check.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.check.ICatalogDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.check.Catalog;
import com.examw.oa.model.check.CatalogInfo;
/**
 * 奖惩类别设置数据操作接口实现类
 * @author lq.
 * @since 2014-07-31.
 */
public class CatalogDaoImpl extends BaseDaoImpl<Catalog> implements ICatalogDao{
	private static final Logger logger = Logger.getLogger(CatalogDaoImpl.class);
	/*
	 * 数据查询。
	 * @see com.examw.oa.dao.check.ICatalogDao#findCatalogs(com.examw.oa.model.check.CatalogInfo)
	 */
	@Override
	public List<Catalog> findCatalogs(CatalogInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Catalog c where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by c." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 统计数据
	 * @see com.examw.oa.dao.check.ICatalogDao#total(com.examw.oa.model.check.CatalogInfo)
	 */
	@Override
	public Long total(CatalogInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Catalog c where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(CatalogInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (c.name like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
}