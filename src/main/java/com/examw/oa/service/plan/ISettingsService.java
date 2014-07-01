package com.examw.oa.service.plan;

import java.util.List;

import com.examw.oa.domain.plan.Settings;
import com.examw.oa.model.plan.SettingsInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 员工报表服务接口。
 * @author lq
 * @since 2014-06-24.
 */
public interface ISettingsService extends IBaseDataService<SettingsInfo>{
	/**
	 * 加载类型名称。
	 * @param type
	 * 类型值。
	 * @return
	 * 类型名称。
	 */
	String loadTypeName(Integer type);
	/**
	 * 类型查找数据（为运算）
	 * @param type
	 * 类型值
	 * @return
	 * 类型名称
	 */
	List<Settings> findSettings(Integer type);
}