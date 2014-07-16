package com.examw.oa.service.adm;

import com.examw.oa.model.adm.LeaveApprovalInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 请假审批服务
 * @author lq.
 * @since 2014-07-16.
 */
public interface ILeaveApprovalService extends IBaseDataService<LeaveApprovalInfo>{
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
	 * 类型值
	 * @return
	 * 状态名称
	 */
	String loadStatusName(Integer status);
}
