package com.examw.oa.dao.plan;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.SettingsInfo;
/**
 * 员工计划总结设置数据接口。
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
	  * 查询数据总数。
	  * @param info
	  * 查询条件。
	  * @return
	  * 数据总数。
	  */
	 Long total(SettingsInfo info);
}