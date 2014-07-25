package com.examw.oa.service.adm;

import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 我要请假服务接口
 * @author lq.
 * @since 2014-07-15.
 */
public interface ILeaveService extends IBaseDataService<LeaveInfo>{
	/**
	 * 加载类型名称
	 * @param type
	 * 类型值
	 * @return
	 * 类型名称
	 */
	String loadTypeName(Integer type);
	/**
	 * 加载状态名称
	 * @param status
	 * 状态值
	 * @return
	 * 状态名称
	 */
	String loadStatusName(Integer status);
}