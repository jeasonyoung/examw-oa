package com.examw.oa.service.check.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.check.ICatalogDao;
import com.examw.oa.dao.check.IEntryDao;
import com.examw.oa.domain.check.Catalog;
import com.examw.oa.domain.check.Entry;
import com.examw.oa.model.check.EntryInfo;
import com.examw.oa.service.check.IEntryService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 奖惩条目设置服务接口实现。
 * @author lq.
 * @since 2014-07-30.
 */
public class EntryServiceImpl extends BaseDataServiceImpl<Entry, EntryInfo> implements IEntryService {
	private static final Logger logger = Logger.getLogger(EntryServiceImpl.class);
	private IEntryDao entryDao;
	private ICatalogDao catalogDao;
	private Map<Integer, String> typeMap;
	/**
	 * 设置奖惩条目设置数据接口。
	 * @param entryDao
	 * 奖惩条目设置数据接口。
	 */
	public void setEntryDao(IEntryDao entryDao) {
		if(logger.isDebugEnabled())logger.debug("奖惩条目设置数据接口...");
		this.entryDao = entryDao;
	}
	/**
	 * 设置奖惩类别设置数据接口。
	 * @param catalogDao
	 * 奖惩类别设置数据接口。
	 */
	public void setCatalogDao(ICatalogDao catalogDao) {
		if(logger.isDebugEnabled())logger.debug("奖惩类别设置数据接口...");
		this.catalogDao = catalogDao;
	}
	/**
	 * 设置奖惩条目设置类型集合。
	 * @param typeMap
	 * 奖惩条目设置类型集合。
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Entry> find(EntryInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据查询...");
		return this.entryDao.findEntrys(info);
	}
	/*
	 * 加载最大代码。
	 * @see com.examw.oa.service.check.IEntryService#loadTypeCodes()
	 */
	@Override
	public Integer loadMaxCodes() {
		if(logger.isDebugEnabled())logger.debug("加载最大代码...");
		List<Entry> list = this.entryDao.findEntrys(new EntryInfo(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getSort(){return "code";}
			@Override
			public String getOrder(){return "desc";}
		});
		if(list == null || list.size() == 0) return null;
		return new Integer(list.get(0).getCode());
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected EntryInfo changeModel(Entry data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null) return null;
		EntryInfo info = new EntryInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getCatalog() !=null){
			info.setCatalogId(data.getCatalog().getId());
			info.setCatalogName(data.getCatalog().getName());
		}
		info.setTypeName(this.loadTypeName(info.getType()));
		return info;
	}
	/*
	 * 统计数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(EntryInfo info) {
		if(logger.isDebugEnabled())logger.debug("统计数据...");
		return this.entryDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public EntryInfo update(EntryInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		if(info == null) return null;
		Boolean isAdded = false;
		Entry data = StringUtils.isEmpty(info.getId()) ? null : this.entryDao.load(Entry.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) info.setId(UUID.randomUUID().toString());
			data = new Entry();
		}
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getCatalogId()) && (data.getCatalog() == null || !data.getCatalog().getId().equalsIgnoreCase(info.getCatalogId()))){
			Catalog c = this.catalogDao.load(Catalog.class, info.getCatalogId());
			if(c != null) data.setCatalog(c);	
		}
		if(data.getCatalog() != null)info.setCatalogName(data.getCatalog().getName());
		info.setTypeName(this.loadTypeName(data.getType()));
		if(isAdded)this.entryDao.save(data);
		return info;
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
			Entry data = this.entryDao.load(Entry.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i]+"］");
				this.entryDao.delete(data);
			} 
		}	
	}
	/*
	 * 加载类型集合
	 * @see com.examw.oa.service.check.IEntryService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载条目类型［"+ type +"］名称...");
		if(type == null  || this.typeMap == null || this.typeMap.size() == 0) return null;
		return this.typeMap.get(type);
	}
}