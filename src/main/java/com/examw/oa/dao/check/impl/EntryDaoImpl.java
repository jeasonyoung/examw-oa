package com.examw.oa.dao.check.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.check.IEntryDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.check.Entry;
import com.examw.oa.model.check.EntryInfo;
/**
 * 奖惩条目设置数据操作接口实现类
 * @author lq.
 * @since 2014-07-31.
 */
public class EntryDaoImpl extends BaseDaoImpl<Entry> implements IEntryDao {
	private static final Logger logger = Logger.getLogger(EntryDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.check.IEntryDao#findCatalogs(com.examw.oa.model.check.EntryInfo)
	 */
	@Override
	public List<Entry> findEntrys(EntryInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据查询...");
		String hql = "from Entry e where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("catalogName")){
				info.setSort("catalog.name");
			}
			hql += " order by e." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 统计数据。
	 * @see com.examw.oa.dao.check.IEntryDao#total(com.examw.oa.model.check.EntryInfo)
	 */
	@Override
	public Long total(EntryInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Entry e where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(EntryInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (e.name like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
}