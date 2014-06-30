package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.IReportDao;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.model.plan.ReportInfo;

public class ReportDaoImpl extends BaseDaoImpl<Report> implements IReportDao {
	/*
	 * 数据查询
	 * @see com.examw.oa.dao.plan.IReportDao#findReports(com.examw.oa.model.plan.ReportInfo)
	 */
	@Override
	public List<Report> findReports(ReportInfo info) {
		String hql = "from Report r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("employeeName")){
				info.setSort("employee.name");
			}
			hql += " order by r." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 数据统计
	 * @see com.examw.oa.dao.plan.IReportDao#total(com.examw.oa.model.plan.ReportInfo)
	 */
	@Override
	public Long total(ReportInfo info) {
		String hql = "select count(*) from Report r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	/**
	 * 添加查询条件到HQL。
	 * @param info
	 * 查询条件。
	 * @param hql
	 * HQL
	 * @param parameters
	 * 参数。
	 * @return
	 * HQL
	 */
	protected String addWhere(ReportInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getCreateTime())){
			hql += " and (r.createTime like :createTime)";
			parameters.put("createTime", "%" + info.getCreateTime() + "%");
		}
		if(!StringUtils.isEmpty(info.getType())){
			hql += " and (r.type like :type)";
			parameters.put("type", "%" + info.getType() + "%");
		}
		return hql;
	}
	@Override
	public List<Report> findReports(Integer type) {
		final String hql = "from Report r where (r.type = :type) and (r.status = :status)";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("type", type);
		parameters.put("status",Report.STATUS_NONE);
		return this.find(hql, parameters, null, null);
	}
	@Override
	public void saveReports(List<Report> reports) {
		if(reports == null || reports.size() == 0) return;
		Session session = null;
		try{
			session = this.getCurrentSession();
			if(session == null)return;
			session.beginTransaction();
			Report data = null;
			for(int i = 0; i < reports.size();i++){
				data = reports.get(i);
				if(data == null) continue;
				session.save(data);
				if(i % 10 == 0){
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		}catch(Exception e){
			if(session != null){
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}
}