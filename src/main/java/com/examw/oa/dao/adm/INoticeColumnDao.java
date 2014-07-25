package com.examw.oa.dao.adm;

import java.util.List;
import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.adm.NoticeColumn;
import com.examw.oa.model.adm.NoticeColumnInfo;
/**
 * 栏目设置数据操作接口
 * @author lq
 * @since 2014-07-14
 */
public interface INoticeColumnDao extends IBaseDao<NoticeColumn>{
	/**
	 * 加载一级栏目集合。
	 * @return
	 */
	List<NoticeColumn> loadFristNoticeColumn();
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<NoticeColumn> findNoticeColumn(NoticeColumnInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(NoticeColumnInfo info);
}