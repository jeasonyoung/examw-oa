package com.examw.oa.dao.check.impl;

import java.util.List;

import com.examw.oa.dao.check.IEntryDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.check.Entry;
import com.examw.oa.model.check.EntryInfo;

public class EntryDaoImpl extends BaseDaoImpl<Entry> implements IEntryDao {
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.check.IEntryDao#findCatalogs(com.examw.oa.model.check.EntryInfo)
	 */
	@Override
	public List<Entry> findCatalogs(EntryInfo info) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 统计数据。
	 * @see com.examw.oa.dao.check.IEntryDao#total(com.examw.oa.model.check.EntryInfo)
	 */
	@Override
	public Long total(EntryInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

}
