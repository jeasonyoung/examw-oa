package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.RankInfo;

/**
 * 员工等级数据操作接口。
 * @author lq.
 * @since 2014-06-11.
 */
public interface IRankDao extends IBaseDao<Rank>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<Rank> findRank(RankInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(RankInfo info);
}