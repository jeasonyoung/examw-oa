package com.examw.oa.service.org.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IRankDao;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.RankInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IRankService;
/**
 * 员工等级服务实现。
 * @author lq.
 * @since 2014-06-11.
 */
public class RankServiceImpl extends BaseDataServiceImpl<Rank, RankInfo> implements IRankService {
	private IRankDao rankDao;
	/**
	 * 设置
	 * @param rankDao
	 */
	public void setRankDao(IRankDao rankDao) {
		this.rankDao = rankDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Rank> find(RankInfo info) {
		return this.rankDao.findRank(info);
	}
	/*
	 * 类型装换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected RankInfo changeModel(Rank data) {
		if(data == null) return null;
		RankInfo info = new RankInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/*
	 *  统计查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected Long total(RankInfo info) {
		return this.rankDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	public RankInfo update(RankInfo info) {
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
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Rank data = this.rankDao.load(Rank.class, ids[i]);
			if(data != null) this.rankDao.delete(data);
		}
	}
}