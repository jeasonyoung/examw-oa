package com.examw.oa.dao.check;

import java.util.List;
import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.check.Entry;
import com.examw.oa.model.check.EntryInfo;
/**
 * 奖惩条目设置数据操作接口。
 * @author lq.
 * @since 2014-07-30.
 */
public interface IEntryDao extends IBaseDao<Entry>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Entry> findEntrys(EntryInfo info);
	 /**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	 Long total(EntryInfo info);
}
