package com.examw.oa.dao.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.plan.IReportDao;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.model.plan.BaseReportInfo;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.model.plan.ReportReviewInfo;
/**
 * 员工报告数据接口实现类。
 * @author lq.
 * @since 2014-07-03.
 */
public class ReportDaoImpl extends BaseDaoImpl<Report> implements IReportDao {
	private static final Logger logger = Logger.getLogger(ReportDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.plan.IReportDao#findReports(com.examw.oa.model.plan.ReportInfo)
	 */
	@Override
	public List<Report> findReports(ReportInfo info) {
		String hql = "from Report r where 1 = 1 ";
		return this.findReports(hql, info);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.plan.IReportDao#findReports(com.examw.oa.model.plan.ReportReviewInfo)
	 */
	@Override
	public List<Report> findReports(ReportReviewInfo info) {
		String hql = "from Report r where 1 = 1 ";
		return this.findReports(hql, info);
	}
	//查询数据。
	private  List<Report> findReports(String hql, BaseReportInfo info){
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("employeeName")){
				info.setSort("employee.name");
			}
			if(info.getSort().equalsIgnoreCase("deptName")){
				info.setSort("employee.department.name");
			}
			if(info.getSort().equalsIgnoreCase("statusName")){
				info.setSort("status");
			}
			if(info.getSort().equalsIgnoreCase("typeName")){
				info.setSort("type");
			}
			hql += " order by r." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询统计
	 * @see com.examw.oa.dao.plan.IReportDao#total(com.examw.oa.model.plan.ReportInfo)
	 */
	@Override
	public Long total(ReportInfo info) {
		return this.totalBase(info);
	}
	@Override
	public Long total(ReportReviewInfo info) {
		return this.totalBase(info);
	}
	//查询统计。
	private Long totalBase(BaseReportInfo info){
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Report r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//查询条件
	private String addWhere(BaseReportInfo info, String hql, Map<String, Object> parameters){
		if(info instanceof ReportInfo){
			if(!StringUtils.isEmpty(info.getCurrentUserId())){
				hql += " and (r.employee.id = :currentUserId)";
				parameters.put("currentUserId", info.getCurrentUserId());
			}
			return hql;
		}
		if(info instanceof ReportReviewInfo){
			if(!StringUtils.isEmpty(info.getCurrentUserId())){
				hql += " and (r.employee.post.parent.id in (select e.post.id  from Employee e where e.id = :employeeId))";
				parameters.put("employeeId", info.getCurrentUserId());
			}
			if(!StringUtils.isEmpty(((ReportReviewInfo) info).getDeptId())){
				hql += " and (r.employee.department.id = :deptId) ";
				parameters.put("deptId", ((ReportReviewInfo) info).getDeptId());
			}
			if(!StringUtils.isEmpty(info.getEmployeeName())){
				hql += " and (r.employee.name like :name or r.employee.code like :name) ";
				parameters.put("name", "%"+ info.getEmployeeName() +"%");
			}
			return hql;
		}
		return hql;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.plan.IReportDao#findReports(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Report> findReports(Integer type, Integer status) {
		if(logger.isDebugEnabled()) logger.debug("查询数据［type="+ type +",status="+ status +"］...");
		final String hql = "from Report r  ";
		Map<String, Object> parameters = new HashMap<>();
		StringBuilder builder = new StringBuilder();
		if(type != null) {
			builder.append(" where ").append(" r.type").append("=").append(":type");
			parameters.put("type", type);
		}
		if(status != null){
			if(builder.length() > 0)  
				builder.append(" and "); 
			else  
				builder.append(" where ");
			builder.append("r.status").append(" = ").append(":status");
			parameters.put("status", status);
		}
		builder.insert(0, hql);
		if(logger.isDebugEnabled()) logger.debug(builder.toString());
		return this.find(builder.toString(), parameters, null, null);
	}
}