package com.examw.oa.dao.org.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IDepartDao;
import com.examw.oa.domain.org.Depart;
import com.examw.oa.model.org.DepartInfo;

/**
 * 部门数据操作实现类。
 * @author lq
 * @since 2014-06-12.
 */
public class DepartDaoImpl extends BaseDaoImpl<Depart> implements IDepartDao {
	/*
	 * 查询所有数据。
	 * @see com.examw.oa.dao.admin.IDepartDao#findRoles(com.examw.netplatform.model.admin.departInfo)
	 */
	@Override
	public List<Depart> findDeparts() {
		String hql =  "from Depart d  where d.parent is null order by d.orderNo";
		return this.find(hql, null, null, null);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.IDepartDao#findRoles(com.examw.netplatform.model.admin.departInfo)
	 */
	@Override
	public List<Depart> findDepart(DepartInfo info) {
		String hql = "from Depart d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("id")){
				info.setSort("id");
			}
			if(info.getSort().equalsIgnoreCase("code")){
				info.setSort("code");
			}
			hql += " order by d." + info.getSort() + " " + info.getOrder();
		}
		return  this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据总数。
	 * @see com.examw.oa.dao.admin.IDepartDao#total(com.examw.oa.model.admin.DepartInfo)
	 */
	@Override
	public Long total(DepartInfo info) {
		String hql = "select count(*) from Depart d where 1 = 1 ";
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
	protected String addWhere(DepartInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getId())){
			hql += " and (d.id = :Id or d.parent.id = :Id)";
			parameters.put("Id", info.getId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (d.name like :Name)";
			parameters.put("Name", "%" + info.getName() + "%");
		}
		if(!StringUtils.isEmpty(info.getPid())){
			hql += " and (d.code like :Code)";
			parameters.put("Code", "%" + info.getCode()+ "%");
		}
		return hql;
	}
	
}