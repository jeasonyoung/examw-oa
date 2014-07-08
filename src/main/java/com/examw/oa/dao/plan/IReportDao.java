package com.examw.oa.dao.plan;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.model.plan.ReportInfo;
/**
 * 员工报表数据接口
 * @author lq.
 * @since 2014-06-27.
 */
public interface IReportDao extends IBaseDao<Report>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Report> findReports(ReportInfo info);
	 /**
	  * 查询数据总数。
	  * @param info
	  * 查询条件。
	  * @return
	  * 数据总数。
	  */
	 Long total(ReportInfo info);
	 /**
	  * 根据类型查找报表
	  * @param type
	  * 查询条件
	  * @return
	  * 查询结果
	  */
	 List<Report> findReports(Integer type);
}