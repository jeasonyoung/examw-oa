package com.examw.oa.dao.adm;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.model.adm.LeaveInfo;
/**
 * 我要请假数据操作接口
 * @author lq
 * @since 2014-07-15 
 */
public interface ILeaveDao extends IBaseDao<Leave>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Leave> findLeaves(LeaveInfo info);
	 /**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	 Long total(LeaveInfo info);
}