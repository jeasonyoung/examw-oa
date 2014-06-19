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
 * 等级信息实现。
 * @author lq.
 * @since 2014-06-11.
 */
public class RankServiceImpl extends BaseDataServiceImpl<Rank, RankInfo> implements IRankService {
	private IRankDao rankdao;
	public void setRankdao(IRankDao rankdao) {
		this.rankdao = rankdao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	//查询等级信息
	@Override
	protected List<Rank> find(RankInfo info) {
		
		return this.rankdao.findRank(info);
	}
	//等级信息类型转换
	@Override
	protected RankInfo changeModel(Rank data) {
		if(data == null) return null;
		RankInfo info = new RankInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	//等级信息统计
	@Override
	protected Long total(RankInfo info) {
		
		return this.rankdao.total(info);
	}
	//等级信息更新
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
		int sum=0;
		if(StringUtils.isEmpty(info.getCode())){
				for(int i=0;i<10000000;i++){
					sum=i++;
				}
		}
		String code=String.valueOf("S-"+sum);
		info.setCode(code);
		BeanUtils.copyProperties(info, data);
		
		if(isAdded) this.rankdao.save(data);
		return info;
	}
	//等级信息删除
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			Rank data = this.rankdao.load(Rank.class, ids[i]);
			if(data != null) this.rankdao.delete(data);
		}
	}
}