package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.IBusinessDao;
import com.examw.oa.domain.plan.Business;
import com.examw.oa.model.plan.BusinessInfo;
/**
 * 业务系统数据操作接口实现。
 * @author lq.
 * @since 2014-06-24.
 */
public class BusinessDaoImpl extends BaseDaoImpl<Business> implements IBusinessDao {
	private static final Logger logger = Logger.getLogger(BusinessDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.plan.IBusinessDao#findBusiness(com.examw.oa.model.plan.BusinessInfo)
	 */
	@Override
	public List<Business> findBusiness(BusinessInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Business b where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("statusName")){
				info.setSort("status");
			}
			hql += " order by b." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询统计。
	 * @see com.examw.oa.dao.plan.IBusinessDao#total(com.examw.oa.model.plan.BusinessInfo)
	 */
	@Override
	public Long total(BusinessInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Business b where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(BusinessInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (b.name like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
}