package com.examw.oa.service.org.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IRankDao;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.RankInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IRankService;
/**
 *  员工等级服务接口实现类。
 * @author lq.
 * @since 2014-06-11.
 */
public class RankServiceImpl extends BaseDataServiceImpl<Rank, RankInfo> implements IRankService {
	private static Logger logger = Logger.getLogger(RankServiceImpl.class);
	private IRankDao rankDao;
	/**
	 * 设置员工等级数据接口。
	 * @param rankDao
	 */
	public void setRankDao(IRankDao rankDao) {
		if(logger.isDebugEnabled())logger.debug("注入员工等级数据接口...");
		this.rankDao = rankDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Rank> find(RankInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		return this.rankDao.findRank(info);
	}
	/*
	 * 类型装换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected RankInfo changeModel(Rank data) {
		if(logger.isDebugEnabled())logger.debug("类型转换...");
		if(data == null) return null;
		RankInfo info = new RankInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/*
	 *  查询数据统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected Long total(RankInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		return this.rankDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	public RankInfo update(RankInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		if(info == null) return null;
		boolean isAdded = false;
		Rank data = StringUtils.isEmpty(info.getId()) ? null : this.rankDao.load(Rank.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
			}
			data = new Rank();
		}
		BeanUtils.copyProperties(info, data);	
		if(isAdded) this.rankDao.save(data);
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Rank data = this.rankDao.load(Rank.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled())logger.debug("删除数据［"+ids[i]+"］");
				this.rankDao.delete(data);
			}
		}
	}
}