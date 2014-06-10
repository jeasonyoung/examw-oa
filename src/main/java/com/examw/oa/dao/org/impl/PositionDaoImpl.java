package com.examw.oa.dao.org.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.PositionDao;
import com.examw.oa.domain.org.Position;
import com.examw.oa.model.org.PositionInfo;
import com.examw.oa.model.security.RoleInfo;


public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao {

	@Override
	public List<Position> findPosition(PositionInfo info) {
		String hql = "from Position r  where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by r." + info.getId();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	public Long total(PositionInfo info) {
		String hql = "select count(*) from Position r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	protected String addWhere(PositionInfo info, String hql, Map<String, Object> parameters){
		if(info.getJobcode() != null){
			hql += " and (r.status = :status)";
			parameters.put("status", info.getJobcode());
		}
		return hql;
	}
	
}
