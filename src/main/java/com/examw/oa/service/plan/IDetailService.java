package com.examw.oa.service.plan;

import com.examw.oa.model.plan.DetailInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 计划总结明细
 * @author lq
 * @since 2014-07-01
 */
public interface IDetailService extends IBaseDataService<DetailInfo>{
	/**
	 * 加载类型名称
	 * @param type
	 * 类型值
	 * @return
	 * 类型名称
	 */
	String loadTypeName(Integer type);
}
