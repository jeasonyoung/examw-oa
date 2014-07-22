package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.IDetailDao;
import com.examw.oa.domain.plan.Detail;import com.examw.oa.model.plan.DetailInfo;
/**
 * 计划总结明细数据操作接口实现。
 * @author lq
 * @since 2014-07-01
 */
public class DetailDaoImpl extends BaseDaoImpl<Detail> implements IDetailDao {
	private static Logger logger = Logger.getLogger(DetailDaoImpl.class);
	/*
	 * 查询数据
	 * @see com.examw.oa.dao.plan.IDetailDao#findDetails(com.examw.oa.model.plan.DetailInfo)
	 */
	@Override
	public List<Detail> findDetails(DetailInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Detail d where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by d." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询统计
	 * @see com.examw.oa.dao.plan.IDetailDao#total(com.examw.oa.model.plan.DetailInfo)
	 */
	@Override
	public Long total(DetailInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Detail d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	//查询条件
	protected String addWhere(DetailInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getCreateTime())){
			hql += " and (d.createTime =:createTime)";
			parameters.put("createTime", info.getCreateTime());
		}
		return hql;
	}
}