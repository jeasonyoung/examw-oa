package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Empl;

import com.examw.oa.model.org.EmplInfo;
/**
 * 员工接口。
 * @author lq.
 * @since 2014-06-16.
 */
public interface IEmplDao extends IBaseDao<Empl>{
	/**
	 * 加载数据。
	 * @param info
	 * @return
	 */
	Empl load(EmplInfo info);
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Empl> findEmpls(EmplInfo info);
	 /**
	  * 查询部门ID下的所有员工信息。
	  * @param menuId
	  * 部门ID
	  * @return
	  * 员工集合。
	  */
	 List<Empl> findEmpls(String departId);
	 /**
		 * 查询数据总数。
		 * @param info
		 * 查询条件。
		 * @return
		 * 数据总数。
		 */
	 Long total(EmplInfo info);
}