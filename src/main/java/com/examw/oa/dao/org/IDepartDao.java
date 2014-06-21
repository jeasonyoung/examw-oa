package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Depart;
import com.examw.oa.model.org.DepartInfo;

/**
 * 部门信息数据接口。
 * @author lq.
 * @since 2014-06-11.
 */
public interface IDepartDao extends IBaseDao<Depart>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<Depart> findDepart(DepartInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(DepartInfo info);
	/**
	 * 查询菜单数据。
	 * @return
	 * 结果数据。
	 */
	List<Depart> findDeparts();
}
