package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Position;
import com.examw.oa.domain.security.User;
import com.examw.oa.model.org.PositionInfo;
import com.examw.oa.model.security.UserInfo;


public interface PositionDao extends IBaseDao<Position>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<Position> findPosition(PositionInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(PositionInfo info);
	
}
