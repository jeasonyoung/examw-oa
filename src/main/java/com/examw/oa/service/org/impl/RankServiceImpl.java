package com.examw.oa.service.org.impl;

import java.util.List;




import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IRankDao;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.RankInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IRankService;
/**
 * 等级信息实现。
 * @author lq.
 * @since 2014-06-11.
 */
public class RankServiceImpl extends BaseDataServiceImpl<Rank, RankInfo> implements IRankService {
	private IRankDao rankdao;
	private Map<String, String> typeRanks;
	/**
	 * 设置等级类型名称。
	 * @param typeNames
	 * 等级类型名称。
	 */
	public void setTypeRanks(Map<String, String> typeRanks) {
		this.typeRanks = typeRanks;
	}
	/**
	 * 设置等级数据接口。
	 * @param roleDao
	 * 等级数据接口。
	 */
	public void setRankdao(IRankDao rankdao) {
		this.rankdao = rankdao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Rank> find(RankInfo info) {
		return this.rankdao.findRank(info);
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
		return this.rankdao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	public RankInfo update(RankInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Rank data = StringUtils.isEmpty(info.getId()) ? null : this.rankdao.load(Rank.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
			}
			data = new Rank();
		}
		BeanUtils.copyProperties(info, data);	
		if(isAdded) this.rankdao.save(data);
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
			Rank data = this.rankdao.load(Rank.class, ids[i]);
			if(data != null) this.rankdao.delete(data);
		}
	}
	/*
	 * 加载等级类型集合。
	 * @see com.examw.oa.service.admin.IEmplService#loadRankTypes()
	 */
	@Override
	public Map<String, String> loadRankTypes() {
		return this.typeRanks;
	}
}