package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.IDeptPlanDao;
import com.examw.oa.domain.plan.DeptPlan;
import com.examw.oa.model.plan.DeptPlanInfo;
/**
 * 部门计划数据接口实现。
 * @author lq.
 * @since 2014-08-01.
 */
public class DeptPlanDaoImpl extends BaseDaoImpl<DeptPlan> implements IDeptPlanDao{
	private static final Logger logger = Logger.getLogger(DeptPlanDaoImpl.class);
	/*
	 * 数据查询。
	 * @see com.examw.oa.dao.plan.IDeptPlanDao#findDeptPlans(com.examw.oa.model.plan.DeptPlanInfo)
	 */
	@Override
	public List<DeptPlan> findDeptPlans(DeptPlanInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from DeptPlan d where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("deptName")){
				info.setSort("department.name");
			}
			if(info.getSort().equalsIgnoreCase("typeName")){
				info.setSort("type");
			}
			if(info.getSort().equalsIgnoreCase("statusName")){
				info.setSort("status");
			}
			hql += " order by d." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 统计数据，
	 * @see com.examw.oa.dao.plan.IDeptPlanDao#total(com.examw.oa.model.plan.DeptPlanInfo)
	 */
	@Override
	public Long total(DeptPlanInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from DeptPlan d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(DeptPlanInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDeptId())){
			hql += " and ((d.department.id = :deptId) or (d.department.parent.id = :deptId))";
			parameters.put("deptId", info.getDeptId());
		}
		if(!StringUtils.isEmpty(info.getTitle())){
			hql += " and (d.title like :title)";
			parameters.put("title", "%" + info.getTitle() + "%");
		}
		return hql;
	}
}