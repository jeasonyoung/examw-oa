package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.IDeptPlanMenberDao;
import com.examw.oa.domain.plan.DeptPlanMember;
import com.examw.oa.model.plan.DeptPlanMemberInfo;
/**
 * 部门计划成员数据接口。
 * @author lq.
 * @since 2014-08-01.
 */
public class DeptPlanMenberDaoImpl extends BaseDaoImpl<DeptPlanMember> implements IDeptPlanMenberDao {
	private static final Logger logger = Logger.getLogger(DeptPlanMenberDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.plan.IDeptPlanMenberDao#findDeptPlanMembers(com.examw.oa.model.plan.DeptPlanMemberInfo)
	 */
	@Override
	public List<DeptPlanMember> findDeptPlanMembers(DeptPlanMemberInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from DeptPlanMember d where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("deptPlanName")){
				info.setSort("plan.name");
			}
			if(info.getSort().equalsIgnoreCase("emplName")){
				info.setSort("employee.name");
			}
			if(info.getSort().equalsIgnoreCase("entryName")){
				info.setSort("entry.name");
			}
			hql += " order by d." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 统计数据。
	 * @see com.examw.oa.dao.plan.IDeptPlanMenberDao#total(com.examw.oa.model.plan.DeptPlanMemberInfo)
	 */
	@Override
	public Long total(DeptPlanMemberInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from DeptPlanMember d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(DeptPlanMemberInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getEmplName())){
			hql += " and (d.employee.name like :name)";
			parameters.put("name", "%" + info.getEmplName() + "%");
		}
		return hql;
	}
}