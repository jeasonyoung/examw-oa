package com.examw.oa.dao.adm;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.model.adm.LeaveApprovalInfo;
/**
 * 请假审批接口
 * @author lq.
 * @since 2014-07-16.
 */
public interface ILeaveApprovalDao extends IBaseDao<LeaveApproval>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<LeaveApproval> findLeaveApprovals(LeaveApprovalInfo info);
	 /**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	 Long total(LeaveApprovalInfo info);
}
