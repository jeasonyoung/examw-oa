package com.examw.oa.dao.org.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IRankDao;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.RankInfo;
public class RankDaoImpl extends BaseDaoImpl<Rank> implements IRankDao {
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.RankDao#findRank(com.examw.oa.model.admin.RankInfo)
	 */
	@Override
	public List<Rank> findRank(RankInfo info) {
		String hql = "from Rank l where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by l." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据总数。
	 * @see com.examw.oa.dao.admin.RankDao#findRank(com.examw.oa.model.admin.RankInfo)
	 */
	@Override
	public Long total(RankInfo info) {
		String hql = "select count(*) from Rank l where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}

	protected String addWhere(RankInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getCode())){
			hql += " and (l.code like :code)";
			parameters.put("code", "%" + info.getCode() + "%");
		}
		return hql;
	}

}
