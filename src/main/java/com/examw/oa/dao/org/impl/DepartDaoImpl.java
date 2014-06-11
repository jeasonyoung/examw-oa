package com.examw.oa.dao.org.impl;


import java.util.List;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IDepartDao;
import com.examw.oa.domain.org.Depart;
/**
 * 部门数据操作实现类。
 * @author yangyong.
 * @since 2014-04-28.
 */
public class DepartDaoImpl extends BaseDaoImpl<Depart> implements IDepartDao {

	@Override
	public List<Depart> findDepart() {
		String hql =  "from Depart d  where d.code = null order by d.orderNo";
		return this.find(hql, null, null, null);
	}
	

}
