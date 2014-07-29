package com.examw.oa.service.adm;

import com.examw.oa.domain.adm.Leave;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 请假条服务接口。
 * @author lq.
 * @since 2014-07-15.
 */
public interface ILeaveService extends IBaseDataService<LeaveInfo>{
	/**
	 * 加载请假员工信息。
	 * @param employeeId
	 * 员工ID。
	 * @return
	 * 请假员工信息。
	 */
	LeaveInfo loadLeaveEmployee(String employeeId);
	/**
	 * 加载补班类型名称。
	 * @param sup
	 * 补班类型值。
	 * @return
	 * 补班类型名称。
	 */
	String loadSupName(Integer sup);
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
	/**
	 * 加载审批结果名称。
	 * @param approval
	 * 审批结果值。
	 * @return
	 * 审批结果名称。
	 */
	String loadResultName(Integer result);
	/**
	 * 请假条类型转换。
	 * @param data
	 * 请假条对象。
	 * @return
	 * 请假条信息。
	 */
	LeaveInfo convert(Leave data);
}