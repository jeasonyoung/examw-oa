package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Depart;


public interface IDepartDao extends IBaseDao<Depart>{
	/**
	 * 查询部门数据。
	 * @return
	 * 结果数据。
	 */
	List<Depart> findDepart();
	
}
