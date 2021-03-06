package com.examw.oa.dao.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.security.IRoleDao;
import com.examw.oa.domain.security.Role;
import com.examw.oa.model.security.RoleInfo;

/**
 * 角色数据接口实现类。
 * @author yangyong.
 * @since 2014-05-05.
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {
	private static Logger logger = Logger.getLogger(RoleDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.dao.admin.IRoleDao#findRoles(com.examw.netplatform.model.admin.RoleInfo)
	 */
	@Override
	public List<Role> findRoles(RoleInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		String hql = "from Role r  where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by r." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据总数。
	 * @see com.examw.netplatform.dao.admin.IRoleDao#total(com.examw.netplatform.model.admin.RoleInfo)
	 */
	@Override
	public Long total(RoleInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from Role r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//添加查询条件到HQL。
	private String addWhere(RoleInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += "  and (r.name like :name)";
			parameters.put("name", "%" + info.getName()+ "%");
		}
		if(info.getStatus() != null){
			hql += " and (r.status = :status)";
			parameters.put("status", info.getStatus());
		}
		return hql;
	}
}