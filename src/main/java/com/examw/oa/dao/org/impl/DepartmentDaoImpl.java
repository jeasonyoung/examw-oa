package com.examw.oa.dao.org.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.domain.org.Department;
import com.examw.oa.model.org.DepartmentInfo;
/**
 * 部门数据操作接口实现类。
 * @author lq
 * @since 2014-06-12.
 */
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements IDepartmentDao {
	private static Logger logger = Logger.getLogger(DepartmentDaoImpl.class);
	/*
	 * 加载一级部门数据集合。
	 * @see com.examw.oa.dao.org.IDepartmentDao#loadFristDepartments()
	 */
	@Override
	public List<Department> loadFristDepartments() {
		if(logger.isDebugEnabled())logger.debug("加载一级部门数据...");
		final String hql = "from Department d where (d.parent is null) order by d.orderNo";
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.find(hql, null, null, null);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.IDepartDao#findDepart(com.examw.netplatform.model.admin.departInfo)
	 */
	@Override
	public List<Department> findDepartments(DepartmentInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Department d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by d." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.admin.IDepartDao#total(com.examw.oa.model.admin.DepartInfo)
	 */
	@Override
	public Long total(DepartmentInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from Department d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.count(hql, parameters);
	}
	//查询条件
	private String addWhere(DepartmentInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getId())){
			hql += " and (d.id = :id or d.parent.id = :id)";
			parameters.put("id", info.getId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (d.name like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.dao.impl.BaseDaoImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(Department data){
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(data == null) return;
		if(data.getChildren() != null){
			for(Department d : data.getChildren()){
				if(d == null) continue;
				if(logger.isDebugEnabled())logger.debug("删除数据［"+d.getId()+"］");
				this.delete(d);
			}
		}
		super.delete(data);
	}
}