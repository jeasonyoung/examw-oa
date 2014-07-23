package com.examw.oa.service.plan;

import com.examw.oa.model.plan.BusinessInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 系统业务服务接口。
 * @author lq
 * @since 2014-06-23.
 */
public interface IBusinessService extends IBaseDataService<BusinessInfo>{
	/**
	 * 加载状态名称。
	 * @param status
	 * 状态值。
	 * @return
	 * 状态名称。
	 */
	String loadStatusName(Integer status);
}