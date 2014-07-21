package com.examw.oa.dao.org.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IRankDao;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.RankInfo;
/**
 * 员工等级数据操作接口实现类。
 * @author lq.
 * @since 2014-06-11.
 */
public class RankDaoImpl extends BaseDaoImpl<Rank> implements IRankDao {
	private static Logger logger = Logger.getLogger(RankDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.RankDao#findRank(com.examw.oa.model.admin.RankInfo)
	 */
	@Override
	public List<Rank> findRank(RankInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Rank r where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by r." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.admin.RankDao#findRank(com.examw.oa.model.admin.RankInfo)
	 */
	@Override
	public Long total(RankInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Rank r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	// 查询条件
	private String addWhere(RankInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and ((r.name like :name) or (r.code like :name))";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
}