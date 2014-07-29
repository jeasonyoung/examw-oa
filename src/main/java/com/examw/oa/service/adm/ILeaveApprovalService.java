package com.examw.oa.service.adm;

import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.IBaseDataService;

/**
 * 请假条审批服务接口。
 * @author yangyong.
 * @since 2014-07-29.
 */
public interface ILeaveApprovalService extends IBaseDataService<LeaveInfo> {
	/**
	 * 加载员工的部门ID。
	 * @param employeeId
	 * @return
	 */
	String loadDeptIdOfEmployee(String employeeId);
	/**
	 * 加载请假条信息。
	 * @param id
	 * 请假条ID。
	 * @return
	 * 请假条信息。
	 */
	LeaveInfo loadLeave(String id);
}