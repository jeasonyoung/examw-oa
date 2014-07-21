package com.examw.oa.dao.org.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.domain.org.Department;
import com.examw.oa.model.org.DepartmentInfo;

/**
 * 部门数据操作实现类。
 * @author lq
 * @since 2014-06-12.
 */
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements IDepartmentDao {
	/*
	 * 加载一级部门数据集合。
	 * @see com.examw.oa.dao.org.IDepartmentDao#loadFristDepartments()
	 */
	@Override
	public List<Department> loadFristDepartments() {
		final String hql = "from Department d where (d.parent is null) order by d.orderNo";
		return this.find(hql, null, null, null);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.IDepartDao#findDepart(com.examw.netplatform.model.admin.departInfo)
	 */
	@Override
	public List<Department> findDepartments(DepartmentInfo info) {
		String hql = "from Department d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by d." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据总数。
	 * @see com.examw.oa.dao.admin.IDepartDao#total(com.examw.oa.model.admin.DepartInfo)
	 */
	@Override
	public Long total(DepartmentInfo info) {
		String hql = "select count(*) from Department d where 1 = 1 ";
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
	protected String addWhere(DepartmentInfo info, String hql, Map<String, Object> parameters){
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
		if(data == null) return;
		if(data.getChildren() != null){
			for(Department d : data.getChildren()){
				if(d == null) continue;
				this.delete(d);
			}
		}
		super.delete(data);
	}
}