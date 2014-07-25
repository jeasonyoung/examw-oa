package com.examw.oa.dao.plan;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.SettingsInfo;
/**
 * 员工报告设置数据接口。
 * @author lq.
 * @since 2014-06-24.
 */
public interface ISettingsDao extends IBaseDao<Settings>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Settings> findSettings(SettingsInfo info);
	 /**
	  * 根据报告类型查询数据。
	  * @param type
	  * 报告类型。
	  * @return
	  * 查询结果。
	  */
	 List<Settings> findSettings(Integer type);
	 /**
	  * 查询数据总数。
	  * @param info
	  * 查询条件。
	  * @return
	  * 数据总数。
	  */
	 Long total(SettingsInfo info);
}